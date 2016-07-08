package com.uiautomator.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.uiautomator.bean.ParamsBean;

/** 
 * @author  linhong: 
 * @date 2016年7月8日 上午10:50:19 
 * @Description: TODO
 * @version 1.0  
 */
public class CaseParser {
	/**
	 * 读取case列表
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public List<ParamsBean> parse(String fileName) throws Exception{
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		File file = new File(fileName);
		if(!file.exists()){
			throw new Exception("file not found");
		}
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
		BufferedReader bw = new BufferedReader(isr);
		String str = bw.readLine();
		while(str!=null&&!"".equals(str)){
			 ParamsBean pb = new ParamsBean();
			 JSONObject jsonObject = JSONObject.parseObject(str);
			 pb.setAction(jsonObject.getString("action"));
			 pb.setUnique(jsonObject.getString("unique"));
			 pb.setParams(parseParams(jsonObject.getString("params")));
			 list.add(pb);
			 str = bw.readLine();
		}
		System.out.println(list.size());
		return list;
	}
	
	
	/**
	 * json to map
	 * @param json
	 * @return
	 */
	private Map<String, String> parseParams(String json){
        Map<String, String> map = new HashMap<String, String>();
        JSONObject jsonObject = JSONObject.parseObject(json);
        try{
            for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                map.put(entry.getKey(), entry.getValue().toString());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return map;
    }
	
	
	public static void main(String[] args) {
		try {
			new CaseParser().parse("SearchFreight.txt");
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}

