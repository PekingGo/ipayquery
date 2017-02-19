package com.pansoft.common.fileHandler;

import java.io.Serializable;

/**
 * 文件信息
 *
 * @author guanxp 2015/3/1
 */
public class FileInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6902269086107435789L;

    // 文件名称
    private String fileName;

    // 文件名称(不带扩展名)
    private String fileNameNoEx;

    // 文件扩展名
    private String fileExtensionName;

    // 文件大小（单位：字节）
    private long fileSize;

    // 文件绝对路径
    private String filePath;

    // 文件原始名称
    private String fileOriginalName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileNameNoEx() {
        return fileNameNoEx;
    }

    public void setFileNameNoEx(String fileNameNoEx) {
        this.fileNameNoEx = fileNameNoEx;
    }

    public String getFileExtensionName() {
        return fileExtensionName;
    }

    public void setFileExtensionName(String fileExtensionName) {
        this.fileExtensionName = fileExtensionName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return the fileOriginalName
     */
    public String getFileOriginalName() {
        return fileOriginalName;
    }

    /**
     * @param fileOriginalName
     *            the fileOriginalName to set
     */
    public void setFileOriginalName(String fileOriginalName) {
        this.fileOriginalName = fileOriginalName;
    }
}
