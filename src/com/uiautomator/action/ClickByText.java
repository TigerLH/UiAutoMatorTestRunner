package com.uiautomator.action;

import java.util.Map;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.uiautomator.bean.ParamsBean;
import com.uiautomator.constansts.FindBy;

/** 
 * @author  linhong: 
 * @date 2016年7月8日 上午11:24:18 
 * @Description: TODO
 * @version 1.0  
 */
public class ClickByText implements Action{
	@Override
	public void execute(Map<String,String> params) throws UiObjectNotFoundException{
		String text = params.get(FindBy.text.toString());
		UiObject uiobject = new UiObject(new UiSelector().text(text));
		uiobject.click();
	}

}
