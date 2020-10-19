package org.camel.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import mecs.camel.model.Dict;

public interface DictMapper {

	/**
	 * 从数据库获取体检卡的前缀
	 * 
	 * @return
	 */
	String getCardPrefix();

	/**
	 * 从数据库获取体检卡的位数
	 * 
	 * @return
	 */
	Integer getCardFigure();
	   /**
     * 获取下拉框动态查询的数据 通过字典type
     */
    ArrayList<Dict> getDeskInfoByDictType(@Param("type")String type);
}
