package org.camel.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import mecs.camel.model.Log;

public interface MgrLogMapper {
	
	/**
	 * 日志查看  获取表格数据
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param name 操作人名字
	 * @return
	 */
	ArrayList<Log> getLog(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("name")String name);
	
	/**
	 * 日志查看  获取总条数
	 * @param beginTime
	 * @param endTime
	 * @param name
	 * @return
	 */
	Integer getAllCount(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("name")String name);
	
	
	/**
	 * 传入ID集合批量删除
	 * @param logIdList
	 * @return
	 */
	Integer delLog(@Param("logIdList")List<String> logIdList);
}
