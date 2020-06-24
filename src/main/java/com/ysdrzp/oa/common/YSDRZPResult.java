package com.ysdrzp.oa.common;

import com.ysdrzp.oa.constant.YSDRZPConstant;
import lombok.Data;

import java.io.Serializable;

@Data
public class YSDRZPResult implements Serializable {

	private Integer code;
	private String msg = "";
	private Long count = 0L;
	private Object data;
	
	public YSDRZPResult() {
	}

	public YSDRZPResult(Integer code) {
		this.code = code;
	}

	public YSDRZPResult(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static YSDRZPResult ok(){
		return new YSDRZPResult(YSDRZPConstant.RETURN_SUCCESS_CODE_INT);
	}
	
	public static YSDRZPResult ok(Object list){
		YSDRZPResult result = new YSDRZPResult();
		result.setCode(YSDRZPConstant.RETURN_SUCCESS_CODE_INT);
		result.setData(list);;
		return result;
	}

	public static YSDRZPResult ok(String msg){
		YSDRZPResult result = new YSDRZPResult();
		result.setCode(YSDRZPConstant.RETURN_SUCCESS_CODE_INT);
		result.setMsg(msg);
		return result;
	}

	public static YSDRZPResult ok(String msg, Object data){
		YSDRZPResult result = new YSDRZPResult();
		result.setCode(YSDRZPConstant.RETURN_SUCCESS_CODE_INT);
		result.setMsg(msg);
		result.setData(data);
		return result;
	}

	public static YSDRZPResult ok(Long count, Object list){
		YSDRZPResult result = new YSDRZPResult();
		result.setCode(YSDRZPConstant.RETURN_SUCCESS_CODE_INT);
		result.setMsg(YSDRZPConstant.RETURN_SUCCESS_MSG);
		result.setCount(count);
		result.setData(list);
		return result;
	}

	public static YSDRZPResult ok(String msg, Long count, Object list){
		YSDRZPResult result = new YSDRZPResult();
		result.setCode(YSDRZPConstant.RETURN_SUCCESS_CODE_INT);
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
