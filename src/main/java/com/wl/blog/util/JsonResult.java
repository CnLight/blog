package com.wl.blog.util;

/**
 * 返回结果的定义类
 */
public class JsonResult {
    private int ResultCode;
    private String ResultReason;
    private boolean flag;

    public int getResultCode() {
        return ResultCode;
    }

    public void setResultCode(int resultCode) {
        ResultCode = resultCode;
    }

    public String getResultReason() {
        return ResultReason;
    }

    public void setResultReason(String resultReason) {
        ResultReason = resultReason;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
