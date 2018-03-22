    //倒计时
    (function($){
            var intervalDate;
            var day,hour,min,sec;

            $.fn.extend({
              countDown: function(options){  //生成倒计时字符串
                var opts = $.extend({},defaults,options);
                this.each(function(){
                  var $this = $(this);
                  var nowTime = new Date().getTime();
                  var startTime = new Date(opts.startTimeStr).getTime(); //开始时间
                  var endTime = new Date(opts.endTimeStr).getTime();  //结束时间
                  endTime = endTime > startTime ? endTime : startTime;
                  startTime = endTime > startTime ? startTime : endTime;

                  intervalDate = setInterval(function(){
                      nowTime = new Date().getTime();
                      if(startTime >= nowTime){
                        $this.beforeAction(opts);
                        clearInterval(intervalDate);
                      }else if(endTime >= nowTime){
                        //显示倒计时
                        var t = endTime - nowTime;
                        day = Math.floor(t/1000/60/60/24);
                        hour = Math.floor(t/1000/60/60%24);
                        min = Math.floor(t/1000/60%60);
                        sec = Math.floor(t/1000%60);
                        $(opts.daySelector).html($this.doubleNum(day));
                        $(opts.hourSelector).html($this.doubleNum(hour));
                        $(opts.minSelector).html($this.doubleNum(min));
                        $(opts.secSelector).html($this.doubleNum(sec));
                      }else{
                        $this.afterAction(opts);
                        clearInterval(intervalDate);
                      }
                  },1000);
                  
                });
              },
              doubleNum:function(num){ //将个位数字变成两位
                if(num<10){
                    return "0"+num;
                }else{
                    return num+"";
                }
              },
              beforeAction:function(options){
                //父容器显示，特定文字
                $(options.daySelector).parent().html("敬请期待");
              },
              afterAction:function(options){
                //父容器显示，特定文字
                $(options.daySelector).parent().html("活动结束");
              }
                   
            });

            var defaults = {
                    startTimeStr: "2017/01/10 00:00:00",
                    endTimeStr: "2017/01/17 23:59:59",
                    daySelector:".day",
                    hourSelector:".hour",
                    minSelector:".min",
                    secSelector:".sec"
            }


    })(jQuery)

    Date.prototype.pattern=function(fmt) {

        var o = {

            "M+" : this.getMonth()+1, //月份

            "d+" : this.getDate(), //日

            "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时

            "H+" : this.getHours(), //小时

            "m+" : this.getMinutes(), //分

            "s+" : this.getSeconds(), //秒

            "q+" : Math.floor((this.getMonth()+3)/3), //季度

            "S" : this.getMilliseconds() //毫秒

        };

        var week = {

            "0" : "\u65e5",

            "1" : "\u4e00",

            "2" : "\u4e8c",

            "3" : "\u4e09",

            "4" : "\u56db",

            "5" : "\u4e94",

            "6" : "\u516d"

        };

        if(/(y+)/.test(fmt)){

            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));

        }

        if(/(E+)/.test(fmt)){

            fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\u661f\u671f" : "\u5468") : "")+week[this.getDay()+""]);

        }

        for(var k in o){

            if(new RegExp("("+ k +")").test(fmt)){

                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));

            }

        }

        return fmt;

    }





















































