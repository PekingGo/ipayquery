<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <!DOCTYPE HTML>
	<head>
		<title>Home</title>
		<link href="css/style.css" rel="stylesheet" type="text/css"  media="all" />
		<script src="http://code.jquery.com/jquery-1.9.1.min.js" type="text/javascript"></script>
		<script src="js/countDown.js" type="text/javascript"></script>
	    <link rel="stylesheet" type="text/css" href="css/component.css" />
	</head>
	<body>
		<div class="container1">
		<!-----start-wrap----->
		<div class="wrap">
			<!-----start-Content----->
			<div class="content">
				<div class="content-header">
					<h1>this website will overthrow your mind</h1>
					<p>Our website is under construction but we are in the final stage of the development program.</p>
				</div>
				<!---start-countdown-timer----->
				<ul id="countdown">
					<li>
						<span class="days">00</span>
						<h3>Days</h3>
					</li>
					<li>
						<span class="hours">00</span>
						<h3>hours</h3>
					</li>
					<li>
						<span class="minutes">00</span>
						<h3>minutes</h3>
					</li>
					<li>
						<span class="seconds">00</span>
						<h3>seconds</h3>
					</li>	
				</ul>
				<!---End-countdown-timer----->
				<!---start-notifie----->
				<div class="notify" class="main clearfix column">
					<form>
						<input type="text" class="textbox" value="Email:" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Email';}">
						<input  class="md-trigger md-setperspective" data-modal="top-scorll" type="button" value="Subscribe">
					</form>
					<div class="md-modal md-effect-19" id="top-scorll">
						<div class="md-content">
							<h3>SOON</h3>
							<div>
								<p>THank for Subscribe</p>
								<button class="md-close">Close me!</button>
							</div>
						</div>
					</div>
					<script src="js/classie.js"></script>
					<script src="js/modalEffects.js"></script>
					<script>
						// this is important for IEs
						var polyfilter_scriptpath = '/js/';
					</script>
				</div>
				<!---End-notifie----->
				<!---start-social-icons---->
				<div class="social-icons">
				<h3>Catch on</h3>
				<ul>
					<li><a href="#"><img src="images/wechat.png" title="facebook"></a></li>
					<li><a href="#"><img src="images/weibo.png" title="Twiiter"></a></li>
					<li><a href="#"><img src="images/QQ.png" title="Rss"></a></li>
					<li><a href="#"><img src="images/baidu.png" title="Google+"></a></li>
				</ul>
			</div>
				<!---End-social-icons---->
				<!---start-copy-right---->
				<div class="copy-right">
					<p>Copyright &copy; 2017.MicorFin All rights reserved.<a target="_blank" href="http://www.zfzj.cn">www.zfzj.cn</a></p>
				</div>
				<!---End-copy-right---->
			</div>
			<!-----End-Content----->
		</div>
		<!-----End-wrap----->
		</div>
		<script type="text/javascript">
			var str = new Date().pattern("yyyy-MM-dd HH:mm:ss");
			console.log(str);
            $(function(){
                $(".content").countDown({
                    startTimeStr:str,//开始时间
                    endTimeStr:'2018/05/01 23:59:59',//结束时间
                    daySelector:".days",
                    hourSelector:".hours",
                    minSelector:".minutes",
                    secSelector:".seconds"
                });
            })
		</script>
</body>
</html>
</html>
