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

    public YSDRZPResult(boolean result, String data){
        this.result = result;
        this.data = data;
    }

    public static YSDRZPResult success = new YSDRZPResult(true, "成功");

    public static YSDRZPResult fail = new YSDRZPResult(false, "失败");

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
