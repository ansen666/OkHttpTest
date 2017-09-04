package com.ansen.entity;

/**
 * Created by  ansen
 * Create Time 2017-06-06
 */
public class BaseResult {
    private String errorReason;//错误说明
    private int errorCode;//0正常

    public String getErrorReason() {
        return errorReason;
    }

    public void setErrorReason(String errorReason) {
        this.errorReason = errorReason;
    }


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
