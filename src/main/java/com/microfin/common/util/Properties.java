package com.microfin.common.util;

import java.util.ResourceBundle;

/**
 * 读取properties文件中的配置信息
 * 
 * @author guanxp 2015-3-2
 */
public class Properties {

    /**
     * 获得Properties文件中的参数值
     * 
     * @param fileName
     * @param key
     * @param def
     * @return
     */
    public static String getValue(String fileName, String key, String def) {

        // 返回读取指定资源的输入流
        ResourceBundle bundle = ResourceBundle.getBundle(fileName);

        return bundle.containsKey(key) ? bundle.getString(key) : def;
    }
}
