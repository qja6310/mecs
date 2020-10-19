package org.camel.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import mecs.camel.model.Dict;

public interface ParameterService {
	
	/**
	 * 获取所有科室信息
	 * @param beginTime
	 * @param endTime
	 * @param name
	 * @param rb
	 * @return
	 */
	ArrayList<Dict> getDict(String beginTime,String endTime,String name ,RowBounds rb);
	
	/**
	 * 获取所有科室总数
	 * @param beginTime
	 * @param endTime
	 * @param name
	 * @return
	 */
	Integer getCount(String beginTime,String endTime,String name);
	/**
	 * 根据参数ID删除参数
	 * @param dictId
	 * @return
	 */
	boolean delDict(String dictId);
	
	/**
	 * 根据Id获取参数名称
	 * @param dictId
	 * @return
	 */
	String getDictName(String dictId);
	/**
	 * 根据Id获取参数描述
	 * @param dictId
	 * @return
	 */
	String getDictDescribe(String dictId);
	/**
	 * 根据ID修改参数信息
	 * @param dictName
	 * @param dictDescribe
	 * @param dictId
	 * @return
	 */
	boolean updateDict(String dictName,String dictDescribe,String dictId);
	
	/**
	 * 新增参数信息
	 * @param dictCode
	 * @param dictName
	 * @param dictType
	 * @param dictDescribe
	 * @return
	 */
	boolean addDict(String dictCode,String dictName,String dictType,String dictDescribe);
	
	
}
