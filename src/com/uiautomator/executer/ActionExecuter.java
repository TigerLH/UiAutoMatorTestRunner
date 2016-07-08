package com.uiautomator.executer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.uiautomator.action.Action;
import com.uiautomator.action.ClickByClassName;
import com.uiautomator.action.ClickById;
import com.uiautomator.action.ClickByText;
import com.uiautomator.bean.ParamsBean;

/** 
 * @author  linhong: 
 * @date 2016年7月8日 上午11:39:45 
 * @Description: TODO
 * @version 1.0  
 */
public class ActionExecuter {
	private static Map<String, Action> map = new HashMap<String, Action>();
	
	static{
		map.put("ClickById", new ClickById());
		map.put("ClickByText", new ClickByText());
		map.put("ClickByClassName", new ClickByClassName());
	}
	
	
	/**
	 * 执行操作
	 * @param params
	 * @throws Exception 
	 * @throws Throwable 
	 */
	public void execute(List<ParamsBean> params) throws Exception{
		for(ParamsBean bean: params){
			map.get(bean.getAction()).execute(bean.getParams());
		}
	}
}
