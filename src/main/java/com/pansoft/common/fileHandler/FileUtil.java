package com.pansoft.common.fileHandler;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

/**
 * 文件工具类
 * 
 * @author guanxp 2015/3/17
 */
public class FileUtil {

    /**
     * 获取服务器的绝对路径
     *
     * <p>
     * 利用javax.servlet.ServletContext.getRealPath(String
     * path)可以获取服务器的绝对路径，但是在WebLogic、Tomcat、JBoss下运行时，其结果会不一致。
     * <p>
     * 在Tomcat、JBoss下运行时，输出结果最后一个字符是分隔符'\'，而在WebLogic下运行时则没有。
     * <p>
     * 举例：
     * <p>
     * String path = pageContext.getServletContext().getRealPath("/");
     * <p>
     * Tomcat/JBoss：D:\primeton\jboss-3.2.6\server\default\deploy\eos4jboss\
     * default.war\
     * <p>
     * WebLogic：D:\primeton\jboss-3.2.6\server\default\deploy\eos4jboss\default.
     * war
     *
     * <p>
     * File.separator的值：在UNIX系统上是 '/'; 在Windows系统上是 '\'.
     *
     * @param request
     * @return
     */
    public static String getServerAbsolutePath(HttpServletRequest request) {

        String path = request.getSession().getServletContext().getRealPath("/"); // 例：D:\Tomcat7\wtpwebapps\TestWeb\
        if (path.endsWith("\\")) {
            path = path.substring(0, path.length() - 1); // 例：D:\Tomcat7\wtpwebapps\TestWeb
        }
        int pos = path.lastIndexOf("\\");
        path = path.substring(0, pos); // 例：D:\Tomcat7\wtpwebapps
        path = path.replace("\\", "/"); // 例：D:/Tomcat7/wtpwebapps

        return path;
    }

    /**
     * 根据路径获取文件名称（带后缀名）
     */
    public static String getFileName(String filePath) {

        int startPos = filePath.lastIndexOf("/");
        if (startPos == filePath.length() - 1) {
            return "";
        } else {
            return filePath.substring(startPos + 1);
        }
    }

    /**
     * 根据路径获取文件大小（字节）
     * 
     * @param filePath
     * @return
     */
    public static long getFileSize(String filePath) {

        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            return file.length();
        } else {
            return -1L;
        }
    }

    /**
     * 根据CommonsMultipartFile获取文件大小（字节）
     * 
     * @param mfile
     * @return
     */
    /*
     * public static long getFileSize(CommonsMultipartFile mfile) { return
     * mfile.getFileItem().getSize(); }
     */

    /**
     * 根据文件路径获取文件后缀名
     * 
     * @param filePath
     * @return
     */
    public static String getFileExtension(String filePath) {

        int idx = filePath.lastIndexOf(".");
        return idx == -1 ? "" : filePath.substring(idx + 1, filePath.length());
    }
}
