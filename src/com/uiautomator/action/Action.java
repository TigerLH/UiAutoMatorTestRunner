package com.uiautomator.action;

import java.util.Map;

import com.android.uiautomator.core.UiObjectNotFoundException;

/** 
 * @author  linhong: 
 * @date 2016��7��8�� ����10:43:13 
 * @Description: TODO
 * @version 1.0  
 */
public interface Action {
	void execute(Map<String,String> map) throws Exception;
}
