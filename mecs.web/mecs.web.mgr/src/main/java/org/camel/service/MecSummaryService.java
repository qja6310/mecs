package org.camel.service;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

public interface MecSummaryService {
	/**
	 * 总结时先获取页面的所有信息 通过体检记录号码
	 * @param mecNum
	 * @return
	 */
	JSONObject getAllInfo(String mecNum);
	/**
	 * 通过项目记录id或者体检结果
	 * 
	 * @param irId
	 * @return
	 */
	JSONObject getResult(String irId);
	
	/**
	 * 通过体检记录号码 插入 体检建议和指导
	 * @param docName
	 * @param mecNum
	 * @param req
	 * @param guide
	 * @return
	 */
	JSONObject addResult(String docId,String docName,String mecNum ,String req,String guide);


}
