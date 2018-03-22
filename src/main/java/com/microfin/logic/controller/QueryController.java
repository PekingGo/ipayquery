package com.microfin.logic.controller;

import java.io.StringReader;
import java.net.URLDecoder;
import java.util.*;

import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.microfin.common.util.*;
import com.microfin.common.util.Properties;
import com.microfin.logic.entity.DiscuzArticle;
import com.microfin.logic.service.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.microfin.logic.consts.QueryConsts;
import com.microfin.logic.entity.Keyword;
import com.microfin.logic.entity.KeywordTableMap;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * 信息查询主处理器 观察者模式
 *
 * @author manxiaolei 2017-3-4 21:12:13
 *
 */
@Controller
public class QueryController {

    @Autowired
    private ServletContext servletContext;
    @Autowired
    private KeywordService keywordServie;
    @Autowired
    private KeywordResultService keywordResultService;
    @Autowired
    private QueryLogService queryLogService;
    @Autowired
    private DiscuzArticleService discuzArticleService;

    /**
     * 主查询控制器
     *
     * @param key
     * @param jsonpCallback
     * @return
     * @throws Exception
     */
    @RequestMapping(value = QueryConsts.QUERY, method = RequestMethod.GET)
    public @ResponseBody JSONPObject query(String key, String jsonpCallback) throws Exception {
        // 获取ioc容器
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        Map<String, Object> returnMap = new HashMap<String, Object>();
        // JSONObject json = JSONObject.fromObject(key);
        Keyword keyword = new Keyword();
        List<KeywordTableMap> list = null;
        key = URLDecoder.decode(key,"UTF-8");
        // key不能为空
        if (StringUtil.isNotEmpty(key)) {
            keyword.setKey_word(key);
            list = keywordServie.query(keyword);
        }
        // 查询参数Map
        Map<String, List<Keyword>> queryMap = new HashMap<String, List<Keyword>>();
        keywordResultService.removeAllService();
        for (KeywordTableMap keywordResult : list) {
            if (keywordResult == null) {
                continue;
            }
            //当输入key为数字并且长度少于6时一定不是查询银行卡bin或者开户联行号
            if(StringUtil.isNumeric(key)&&(("t_bank_detail".equals(keywordResult.getKey())&&key.length()<6)||("t_bank_card_detail".equals(keywordResult.getKey())&&key.length()<6))){
                continue;
            }
            queryMap.put(keywordResult.getKey(), keywordResult.getValue());
            // 此处前提是把表名对应service的名字用@Service("name")配置成和表名一致
//            keywordResultService.delWatchService((WatchService) ctx.getBean(keywordResult.getKey()));
            keywordResultService.addWatchServcie((WatchService) ctx.getBean(keywordResult.getKey()));
        }
        // 查询内容
        returnMap.put("key", key);
        long cur =System.currentTimeMillis();
        keywordResultService.notifyServiceToQuery(queryMap, returnMap);
        long cur1 =System.currentTimeMillis();
        System.out.println("查询用时："+(cur1-cur)/1000+"s");
        if(returnMap.get("list")==null){
            returnMap.put("list",new JSONArray());
        }
        JSONArray orginList = JSONArray.fromObject(returnMap.get("list"));
        //根据更多结果的大小排序，目前算法是认为搜索结果越少越贴近用户想要搜索的内容
//        Collections.sort(orginList, new Comparator<JSONObject>() {
//            @Override
//            public int compare(JSONObject o1, JSONObject o2) {
//                int o1Size = JSONArray.fromObject(o1.get("moreList")).size();
//                int o2Size = JSONArray.fromObject(o2.get("moreList")).size();
//                return o1Size -o2Size;
//            }
//        });
        final Map<String,Integer> sortMap = new HashMap<String, Integer>();
        sortMap.put("支付牌照",1);sortMap.put("收单机构号",2);
        sortMap.put("POS应答码",3);sortMap.put("银联MCC码行业扣率",4);
        sortMap.put("银行卡BIN",5);sortMap.put("开户行联行号",6);
        sortMap.put("地区代码",7);sortMap.put("银联认证机构",8);
        sortMap.put("手刷黑名单",9);sortMap.put("批发市场白名单",10);sortMap.put("other",100);
        Collections.sort(orginList,new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject o1, JSONObject o2) {
                int o1Size = sortMap.get(o1.get("category")==null?"other":o1.get("category"));
                int o2Size = sortMap.get(o2.get("category")==null?"other":o2.get("category"));
                return o1Size -o2Size;
            }
        });
        long cur2 =System.currentTimeMillis();
        System.out.println("排序用时："+(cur2-cur1)/1000+"s");
        //将数组索引写入元素对象中
        for(int i=0;i<orginList.size();i++){
            JSONObject json = orginList.getJSONObject(i);
            json.element("index",i);
        }
        List<DiscuzArticle> infoList = discuzArticleService.query(key);
        returnMap.put("news",infoList);
        System.out.println("共计用时："+(System.currentTimeMillis()-cur)/1000+"s");
        returnMap.put("list",orginList);
        return new JSONPObject(jsonpCallback, returnMap);
    }
    @RequestMapping(value = QueryConsts.CATEGORY_QUERY, method = RequestMethod.GET)
    public @ResponseBody JSONPObject categoryQuery(String key, String catg,String jsonpCallback) throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        catg = URLDecoder.decode(catg,"UTF-8");
        key = URLDecoder.decode(key,"UTF-8");
        if(StringUtil.isNotEmpty(catg)){
            String serviceName = Properties.getValue("language-zh-CN",catg,"");
            //银行卡BIN和开户行联行号特殊验证
            if("t_bank_card_detail".equals(serviceName)||"t_bank_detail".equals(serviceName)){
                if(StringUtil.isNumeric(key)&&key.length()<6){
                    map.put("invalid",11);
                    JSONPObject result = new JSONPObject(jsonpCallback,map);
                    return  result;
                }
                if("t_bank_card_detail".equals(serviceName)&&!StringUtil.isNumeric(key)){
                    map.put("invalid",12);
                    JSONPObject result = new JSONPObject(jsonpCallback,map);
                    return  result;
                }
            }
            //必须输入完整的15位商户号
            if("t_cmbc_noscore".equals(serviceName)&&key.length()<15){
                map.put("invalid",13);
                JSONPObject result = new JSONPObject(jsonpCallback,map);
                return  result;
            }
            WatchService queryService = (WatchService) ctx.getBean(serviceName);
            map = queryService.queryByCategory(key);
        }else {
            map.put("invalid",1);
        }
        JSONPObject result = new JSONPObject(jsonpCallback,map);
        return  result;
    }

    /**
     * 热门词汇查询
     *
     * @return
     */
    @RequestMapping(value = QueryConsts.HOT_KEYWORDS, method = RequestMethod.GET)
    public  @ResponseBody JSONPObject hotKeywordsQuery(String cate,String jsonpCallback){
        List<String> hotKeywords = queryLogService.query();
        JSONPObject result = new JSONPObject(jsonpCallback,JSONArray.fromObject(hotKeywords));
        return result;
    }

    /**
     *新闻头条共享 解析返回的js代码
     *
     * @param
     */
    @RequestMapping(value = QueryConsts.ZFZJ_HOTNEWS, method = RequestMethod.GET)
    public @ResponseBody JSONPObject queryZFZJNews(String jsonpCallback) throws Exception{
        String url = "http://www.zfzj.cn/api.php?mod=js&bid=402";
        HttpClient client = new HttpClient();
        GetMethod get = new GetMethod(url);
        client.executeMethod(get);
        String result = new String(get.getResponseBodyAsString().getBytes("GBK"));
        result = result.replace("\n","");
        result = result.replace("document.write('","").replace("');","").replace("aid","amp;aid");
        Document document = string2Doc(result);
        NodeList list = document.getElementsByTagName("li");
        JSONArray jsonArray = new JSONArray();
        for(int i=0;i<list.getLength();i++){
            Node node = list.item(i);
            NodeList nodelist1 =node.getChildNodes();
            JSONObject json = new JSONObject();
            json.element("id",i);
            for(int j =0;j<nodelist1.getLength();j++){
                Node node1 = nodelist1.item(j);
                if("a".equals(node1.getNodeName())){
                    json.element("url",node1.getAttributes().getNamedItem("href").getNodeValue());
                    NodeList nodeList2 = node1.getChildNodes();
                    for(int k=0;k<nodeList2.getLength();k++){
                        Node node2 = nodeList2.item(k);
                        if("img".equals(node2.getNodeName())){
                            json.element("style","height:40vw;background-image:url("+node2.getAttributes().getNamedItem("src").getNodeValue()+")");
//                            json.element("style",node2.getAttributes().getNamedItem("src").getNodeValue());
                        }
                    }
                }
                if("div".equals(node1.getNodeName())){
                    NodeList nodeList3 = node1.getChildNodes();
                    for(int m=0;m<nodeList3.getLength();m++){
                        Node node2 = nodeList3.item(m);
                        if("h3".equals(node2.getNodeName())){
                            NodeList nodeList4 = node2.getChildNodes();
                            for(int n =0;n<nodeList4.getLength();n++){
                                Node node3 = nodeList4.item(n);
                                if("em".equals(node3.getNodeName())){
                                    json.element("type",node3.getFirstChild().getNodeValue());
                                }
                                if("a".equals(node3.getNodeName())){
                                    json.element("title",node3.getFirstChild().getNodeValue());
                                }
                            }
                        }
                        if("span".equals(node2.getNodeName())){
                            json.element("subText",node2.getFirstChild().getNodeValue());
                        }
                    }
                }
            }
            jsonArray.add(json);
        }
//        System.out.println(jsonArray);
        JSONPObject jsonpObject = new JSONPObject(jsonpCallback,jsonArray);
        return jsonpObject;
    }

    /**
     * String 转换为 Document 对象
     *
     * @param xml 字符串
     * @return Document 对象
     */
    public static Document string2Doc(String xml) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document doc = null;
        InputSource source = null;
        StringReader reader = null;
        try {
            builder = factory.newDocumentBuilder();
            reader = new StringReader(xml);
            source = new InputSource(reader);//使用字符流创建新的输入源
            doc = builder.parse(source);
            return doc;
        } catch (Exception e) {
            return null;
        } finally {
            if(reader != null){
                reader.close();
            }
        }
    }

}
