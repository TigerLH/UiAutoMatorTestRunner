package com.uiautomator.caserunner;

import java.util.List;

import junit.framework.TestResult;
import android.os.Bundle;

import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.uiautomator.bean.ParamsBean;
import com.uiautomator.executer.ActionExecuter;
import com.uiautomator.util.CaseParser;

/** 
 * @author  linhong: 
 * @date 2016��7��8�� ����11:48:08 
 * @Description: TODO
 * @version 1.0  
 */
public class UiAutoTestCase extends UiAutomatorTestCase{
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}



	public void testRun() throws Exception{
		 Bundle bundle = getParams();
	     String fileName = bundle.getString("file");
		 List<ParamsBean> params = new CaseParser().parse(fileName);
		 ActionExecuter ae = new ActionExecuter();
		 ae.execute(params);
	}
	
	
	
	
	@Override
	public void run(TestResult result) {
		Bundle bundle = getParams();
	    String fileName = bundle.getString("file");
	    /**
	     * ע��Listenser
	     */
	    result.addListener(new UiAutomatorTestListenser(result,fileName.substring(fileName.lastIndexOf("/")+1, fileName.indexOf("."))));
		/**
		 * ����case,������ظ�Listenser,�ص������д���������xml
		 */
	    super.run(result);
	}



	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
}
