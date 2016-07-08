package com.uiautomator.bean;

import java.util.Map;

/** 
 * @author  linhong: 
 * @date 2016年7月8日 上午10:45:01 
 * @Description: TODO
 * @version 1.0  
 */
public class ParamsBean {
	private String unique = "";
	private String action = "";
	private Map<String,String> params = null;
	public String getUnique() {
		return unique;
	}
	public void setUnique(String unique) {
		this.unique = unique;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	@Override
	public String toString() {
		return "ParamsBean [unique=" + unique + ", action=" + action
				+ ", params=" + params + "]";
	}
	
}
