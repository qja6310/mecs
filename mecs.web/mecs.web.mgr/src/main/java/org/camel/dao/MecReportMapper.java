package org.camel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mecs.camel.model.MecRecord;

public interface MecReportMapper {

	/**
	 * 根据卡号或者体检号码获取体检记录
	 * @param cardNum
	 * @param mecCode
	 * @return
	 */
	List<MecRecord> getMecRecord(@Param("cardNum") String cardNum, @Param("mecCode") String mecCode);
	
}
