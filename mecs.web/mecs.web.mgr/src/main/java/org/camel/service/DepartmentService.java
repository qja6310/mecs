package org.camel.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import mecs.camel.model.Department;

public interface DepartmentService {
	
	/**
	 * 获取所有科室信息
	 * @param beginTime
	 * @param endTime
	 * @param name
	 * @param rb
	 * @return
	 */
	ArrayList<Department> getDepartment(String beginTime,String endTime,String name ,RowBounds rb);
	
	/**
	 * 获取所有科室总数
	 * @param beginTime
	 * @param endTime
	 * @param name
	 * @return
	 */
	Integer getCount(String beginTime,String endTime,String name);
	/**
	 * 根据科室Id获得科室名字
	 * @param depId
	 * @return
	 */
	String getDepName(String depId);
	/**
	 * 逻辑删除科室
	 * @param depId
	 * @param depName
	 * @return
	 */
	boolean delDep(String depId,String depName);
	
	/**
	 * 新增科室
	 * @param depName
	 * @param depDescribe
	 * @return
	 */
	boolean addDep(String depName,String depDescribe);
	
	/**
	 * 检查科室名是否重复
	 * @param depName
	 * @return
	 */
	String checkdepName(String depName);
	
	/**
	 * 根据科室ID修改科室信息
	 * @param depId
	 * @param depName
	 * @param depDescribe
	 * @return
	 */
	boolean update(String depId, String depName,String depDescribe);
	/**
	 * 获取科室介绍信息
	 * @param depId
	 * @return
	 */
	String getdepDescribe(String depId);
	
	/**
	 * 根据科室ID跟科室名字 判断要修改的科室是否有重名 排除传入科室ID对应的科室名
	 * @param depId
	 * @param depName
	 * @return
	 */
	String checkEditDepName(String depId,String depName);
}
