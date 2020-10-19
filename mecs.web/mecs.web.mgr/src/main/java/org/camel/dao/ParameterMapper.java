package org.camel.dao;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import mecs.camel.model.Department;
import mecs.camel.model.Dict;

public interface ParameterMapper {
	
	/**
	 * 参数查看
	 * @param beginTime
	 * @param endTime
	 * @param name
	 * @return
	 */
	ArrayList<Dict> getDict(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("name")String name,RowBounds rb);
	
	/**
	 * 获取所有参数数量
	 * @param beginTime
	 * @param endTime
	 * @param name
	 * @return
	 */
	Integer getAllCount(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("name")String name);
	
	/**
	 * 通过ID删除参数信息
	 * @param dictId
	 * @return
	 */
	Integer delDict(@Param("dictId")String dictId);
	
	/**
	 * 获取参数名回填到参数编辑面板中
	 * @param dictId
	 * @return
	 */
	String getDictName(@Param("dictId")String dictId);
	/**
	 * 获取参数描述回填到参数编辑面板中
	 * @param dictId
	 * @return
	 */
	String getDictDescribe(@Param("dictId")String dictId);
	
	/**
	 * 根据ID修改参数信息
	 * @param dictName
	 * @param dictDescribe
	 * @param dictId
	 * @return
	 */
	Integer updateDict(@Param("dictName")String dictName,@Param("dictDescribe")String dictDescribe,@Param("dictId")String dictId);
	
	/**
	 * 
	 * @param dictName
	 * @param dictDescribe
	 * @return
	 */
	Integer addDict(@Param("dictCode")String dictCode,@Param("dictName")String dictName,@Param("dictType")String dictType,@Param("dictDescribe")String dictDescribe);
	

}
