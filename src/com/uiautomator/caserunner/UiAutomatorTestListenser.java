package com.uiautomator.caserunner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;
import java.util.List;

import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import android.os.Bundle;
import android.os.Environment;
import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestFailure;
import junit.framework.TestListener;
import junit.framework.TestResult;

/** 
 * @author  linhong: 
 * @date 2016年7月8日 下午1:40:22 
 * @Description: TODO
 * @version 1.0  
 */
public class UiAutomatorTestListenser implements TestListener {
	private Writer mWriter;
	private XmlSerializer mTestSuiteSerializer;
	private long mTestStarted;
	private TestResult result = null;
	private String casename = "";
	private final static  String JUNIT_XML_FILE = "/data/local/tmp/TestResult.xml";
	
	public UiAutomatorTestListenser(TestResult result,String casename){
		this.result = result;
		this.casename = casename;
	}
	
	
	public void startJUnitOutput(Writer writer) {
        try {
            mWriter = writer;
            mTestSuiteSerializer = newSerializer(mWriter);
            mTestSuiteSerializer.startDocument("utf-8", true);
            mTestSuiteSerializer.startTag(null, "testsuite");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	
    public void recordTestStart() { 
        mTestStarted = System.currentTimeMillis();
    }
	
	private XmlSerializer newSerializer(Writer writer) {
        try {
            XmlPullParserFactory pf = XmlPullParserFactory.newInstance();
            XmlSerializer serializer = pf.newSerializer();
            serializer.setOutput(writer);
            return serializer;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }        
	}
	
	
	
	@Override
	public void startTest(Test test) {
		try {
			startJUnitOutput(new FileWriter(new File(JUNIT_XML_FILE)));
			recordTestStart();
			} catch (IOException e) {
				throw new RuntimeException(e);
		}
	}
	
	
	@Override
	public void addError(Test test, Throwable t) {
		float time = (System.currentTimeMillis() - mTestStarted) / 1000.0f;
		String message = t.toString();
		try {
			mTestSuiteSerializer.startTag(null, "testcase");
			mTestSuiteSerializer.startTag(null, "name");
			mTestSuiteSerializer.text(casename);
			mTestSuiteSerializer.endTag(null,"name");
			mTestSuiteSerializer.startTag(null, "status");
			mTestSuiteSerializer.text("Error");
			mTestSuiteSerializer.endTag(null,"status");
			mTestSuiteSerializer.startTag(null, "message");
			mTestSuiteSerializer.text(message);
			mTestSuiteSerializer.endTag(null,"message");
			mTestSuiteSerializer.startTag(null, "time");
			mTestSuiteSerializer.text(String.format("%.3f", time));
			mTestSuiteSerializer.endTag(null,"time");
	        mTestSuiteSerializer.endTag(null, "testcase");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void addFailure(Test test, AssertionFailedError t) {
		float time = (System.currentTimeMillis() - mTestStarted) / 1000.0f;
		String message = t.toString();
		try {
			mTestSuiteSerializer.startTag(null, "testcase");
			mTestSuiteSerializer.startTag(null, "name");
			mTestSuiteSerializer.text(casename);
			mTestSuiteSerializer.endTag(null,"name");
			mTestSuiteSerializer.startTag(null, "status");
			mTestSuiteSerializer.text("Failure");
			mTestSuiteSerializer.endTag(null,"status");
			mTestSuiteSerializer.startTag(null, "message");
			mTestSuiteSerializer.text(message);
			mTestSuiteSerializer.endTag(null,"message");
			mTestSuiteSerializer.startTag(null, "time");
			mTestSuiteSerializer.text(String.format("%.3f", time));
			mTestSuiteSerializer.endTag(null,"time");
	        mTestSuiteSerializer.endTag(null, "testcase");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void endTest(Test test) {
		float time = (System.currentTimeMillis() - mTestStarted) / 1000.0f;
		if(result.wasSuccessful()){
			try {
				mTestSuiteSerializer.startTag(null, "testcase");
				mTestSuiteSerializer.startTag(null, "name");
				mTestSuiteSerializer.text(casename);
				mTestSuiteSerializer.endTag(null,"name");
				mTestSuiteSerializer.startTag(null, "status");
				mTestSuiteSerializer.text("PASS");
				mTestSuiteSerializer.endTag(null,"status");
				mTestSuiteSerializer.startTag(null, "message");
				mTestSuiteSerializer.text("");
				mTestSuiteSerializer.endTag(null,"message");
				mTestSuiteSerializer.startTag(null, "time");
				mTestSuiteSerializer.text(String.format("%.3f", time));
				mTestSuiteSerializer.endTag(null,"time");
		        mTestSuiteSerializer.endTag(null, "testcase");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		endTestSuites();
	}
		
	
	
    public void endTestSuites() {
		try {
		    mTestSuiteSerializer.endTag(null, "testsuite");
		    mTestSuiteSerializer.endDocument();
		    mTestSuiteSerializer.flush();
		    mWriter.flush();
		    mWriter.close();
		} catch (IOException e) {
		    throw new RuntimeException(e);
		}
    }
    
}
