package com.ysdrzp.oa.common;

/**
 * 封装结果
 */
public class YSDRZPResult {

    /**
     * 处理结结果
     */
    private boolean result;

    /**
     * 响应数据
     */
    private String data;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
