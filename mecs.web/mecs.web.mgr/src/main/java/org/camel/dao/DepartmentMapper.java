package org.camel.dao;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import mecs.camel.model.Department;

public interface DepartmentMapper {
	
	/**
	 * 科室查看  获取科室信息
	 * @param beginTime
	 * @param endTime
	 * @param name
	 * @return
	 */
	ArrayList<Department> getDepartment(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("name")String name,RowBounds rb);
	
	/**
	 * 获取所有科室数量
	 * @param beginTime
	 * @param endTime
	 * @param name
	 * @return
	 */
	Integer getAllCount(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("name")String name);
	
	/**
	 * 通过科室ID获得科室名
	 * @param depId
	 * @return
	 */
	String getDepName(@Param("depId")String depId);
	/**
	 * 逻辑删除科室
	 * @param depId
	 * @param depName
	 * @return
	 */
	Integer delDep(@Param("depId")String depId,@Param("depName")String depName);
	
	/**
	 * 新增科室
	 * @param depName
	 * @param depDescribe
	 * @return
	 */
	Integer addDep(@Param("depName")String depName,@Param("depDescribe")String depDescribe);
	
	/**
	 * 根据科室名检查是否有这个科室
	 * @param depName
	 * @return
	 */
	String checkdepName(@Param("depName")String depName);
	
	/**
	 * 编辑科室
	 * @param depName
	 * @param depDescribe
	 * @return
	 */
	Integer updateDep(@Param("depName")String depName,@Param("depDescribe")String depDescribe,@Param("depId")String depId);
	
	/**
	 * 根据ID获取科室介绍
	 * @param depId
	 * @return
	 */
	String getDepDescribe(@Param("depId")String depId);
	/**
	 * 根据科室ID跟科室名字 判断要修改的科室是否有重名 排除传入科室ID对应的科室名
	 * @param depId
	 * @param depName
	 * @return
	 */
	String checkEditDepName(@Param("depId")String depId,@Param("depName")String depName);

}
