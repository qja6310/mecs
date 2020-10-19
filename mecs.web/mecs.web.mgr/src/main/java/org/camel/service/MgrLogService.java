package org.camel.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import mecs.camel.model.Log;

public interface MgrLogService {
	
	
	
	/**
	 * 查看前端日志
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param name 用户名
	 * @param offset 第几条开始
	 * @param limit 每页显示条数
	 * @return
	 */
	ArrayList<Log> getLog(String beginTime,String endTime,String name);
	
	/**
	 * 查看前端日志  获取总条数
	 * @param beginTime
	 * @param endTime
	 * @param name
	 * @param offset
	 * @param limit
	 * @return
	 */
	Integer getAllCount(String beginTime,String endTime,String name);
	
	/**
	 * 传入ID集合批量删除
	 * @param logIdList
	 * @return
	 */
	boolean delLog(List<String> logIdList);
}
