package com.pansoft.common.fileHandler;

import java.math.BigDecimal;

import org.apache.commons.fileupload.ProgressListener;
import org.springframework.stereotype.Component;

/**
 * 文件上传进度的监听器
 * 
 * @author guanxp 2015/02/28
 */
@Component
public class FileUploadProgressListener implements ProgressListener {

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
    // 已下载文件的大小是否可以检测到
    private boolean contentLengthKnown = false;

    /**
     * pBytesRead 到目前为止读取文件的比特数； pContentLength 文件总大小； pItems 目前正在读取第几个文件；
     */
    /* @Override */
    public void update(long pBytesRead, long pContentLength, int pItems) {

        if (pContentLength > -1) {
            contentLengthKnown = true;
        }
        this.pBytesRead = pBytesRead;
        this.pContentLength = pContentLength;
        this.pItems = pItems;

        long _p100KsRead = pBytesRead / 100000;
        if (_p100KsRead > p100KsRead) {
            p100KsRead = _p100KsRead;
            if (contentLengthKnown) {
                BigDecimal bg = new BigDecimal(100.00 * pBytesRead / pContentLength);
                percentDone = bg.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
        }
    }

    /**
     * 当前已下载文件的大小（单位：100K）
     * 
     * @return
     */
    public long getp100KsRead() {
        return p100KsRead;
    }

    /**
     * 当前已下载文件的大小（单位：比特）
     * 
     * @return
     */
    public long getpBytesRead() {
        return pBytesRead;
    }

    /**
     * 文件的总大小
     * 
     * @return
     */
    public long getpContentLength() {
        return pContentLength;
    }

    /**
     * 当前正在下载第几个文件
     * 
     * @return
     */
    public int getpItems() {
        return pItems;
    }

    /**
     * 当前下载文件的进度（单位：%）
     * 
     * @return
     */
    public double getPercentDone() {
        return percentDone;
    }
}
