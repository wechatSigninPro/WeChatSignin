package com.nenu.edu.server.exception;

/**
 * @Author: Liang Jiayue
 * @Description:下载异常
 */
public class DownloadException extends RuntimeException {
    public DownloadException(String message) {
        super(message);
    }
}