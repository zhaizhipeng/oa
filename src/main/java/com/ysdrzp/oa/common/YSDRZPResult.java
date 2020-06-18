package com.ysdrzp.oa.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class YSDRZPResult implements Serializable {

	private Integer code;
	private String msg = "";
	private Long count = 0L;
	private Object data;
	
	public YSDRZPResult() {
		super();
	}

	public YSDRZPResult(Integer code) {
		super();
		this.code = code;
	}

	public YSDRZPResult(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public static YSDRZPResult ok(){
		return new YSDRZPResult(0);
	}
	
	public static YSDRZPResult ok(Object list){
		YSDRZPResult result = new YSDRZPResult();
		result.setCode(0);
		result.setData(list);;
		return result;
	}

	public static YSDRZPResult ok(String msg){
		YSDRZPResult result = new YSDRZPResult();
		result.setCode(0);
		result.setMsg(msg);
		return result;
	}

	public static YSDRZPResult ok(String msg, Long count, Object list){
		YSDRZPResult result = new YSDRZPResult();
		result.setCode(0);
		result.setMsg(msg);
		result.setCount(count);
		result.setData(list);
		return result;
	}
	
	public static YSDRZPResult error(){
		return new YSDRZPResult(500,"没有此权限，请联系超管！");
	}

	public static YSDRZPResult error(String str){
		return new YSDRZPResult(500, str);
	}

}
