package com.uiautomator.action;

import java.util.Map;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.uiautomator.bean.ParamsBean;
import com.uiautomator.constansts.FindBy;

/** 
 * @author  linhong: 
 * @date 2016��7��8�� ����11:24:18 
 * @Description: TODO
 * @version 1.0  
 */
public class ClickByClassName implements Action{
	@Override
	public void execute(Map<String,String> params) throws UiObjectNotFoundException{
		String className = params.get(FindBy.className.toString());
		int index = Integer.parseInt(params.get("index"));
		UiObject uiobject = new UiObject(new UiSelector().className(className).instance(index));
		uiobject.click();
	}

}
