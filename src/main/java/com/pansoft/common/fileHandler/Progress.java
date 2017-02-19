package com.pansoft.common.fileHandler;

import java.io.Serializable;

/**
 * 返回文件上传进度信息
 * @author guanxp
 * 2015/02/28
 */
public class Progress implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6071457090490048857L;
    
    // 当前已下载文件的大小（单位：100K）    
    private long p100KsRead = 0;
    // 当前已下载文件的大小（单位：比特）
    private long pBytesRead = 0;
    // 文件的总大小
    private long pContentLength = -1;
    // 当前正在下载第几个文件
    private int pItems = 0;
    // 当前下载文件的进度（单位：%）
    private double percentDone = 0.0;
    
    public long getp100KsRead() {
        return p100KsRead;
    }
    
    public void setp100KsRead(long p100KsRead) {
        this.p100KsRead = p100KsRead;
    }
    
    public long getpBytesRead() {
        return pBytesRead;
    }
    
    public void setpBytesRead(long pBytesRead) {
        this.pBytesRead = pBytesRead;
    }
    
    public long getpContentLength() {
        return pContentLength;
    }
    
    public void setpContentLength(long pContentLength) {
        this.pContentLength = pContentLength;
    }
    
    public int getpItems() {
        return pItems;
    }
    
    public void setpItems(int pItems) {
        this.pItems = pItems;
    }
    
    public double getPercentDone() {
        return percentDone;
    }
    
    public void setPercentDone(double percentDone) {
        this.percentDone = percentDone;
    }
}
