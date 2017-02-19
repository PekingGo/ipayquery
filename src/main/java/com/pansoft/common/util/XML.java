package com.pansoft.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

/**
 * 读取XML文件中的配置信息
 * @author guanxp
 * 2015-3-2
 */
public class XML {

    /**
     * 获得XML文件中的参数值
     * @param fileName
     * @param key
     * @param def
     * @return
     */
    public static Object getValue(String fileName, String key, Object def) {
        
        JSONObject obj = null;
        try {
            // 返回读取指定资源的输入流
            InputStream is = XML.class.getClassLoader().getResourceAsStream(fileName+".xml");
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "GBK"));
            String line;
            String xmlStr = "";
            while ((line = br.readLine()) != null) {
                xmlStr += line;
            }
            XMLSerializer xml = new XMLSerializer();
            obj = (JSONObject) xml.read(xmlStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return (obj!=null && obj.containsKey(key)) ? obj.get(key) : def;
    }
}
