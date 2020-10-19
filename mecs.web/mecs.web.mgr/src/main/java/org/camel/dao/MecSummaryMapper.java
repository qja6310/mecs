package org.camel.dao;

import org.apache.ibatis.annotations.Param;

import mecs.camel.model.dto.MecRecordDto;
import mecs.camel.model.dto.UserDto;

public interface MecSummaryMapper {
	/**
	 * 通过体检号码来获取用户的基本信息
	 * 
	 * @param mecNum
	 * @return
	 */
	UserDto getUserInfoByMecNum(@Param("mecNum") String mecNum);

	/**
	 * 通过体检号码获取多个项目记录信息
	 * 
	 * @param mecNum
	 * @return
	 */
	MecRecordDto getUserMecRecordByMecNum(@Param("mecNum") String mecNum);

	/**
	 * 通过项目记录id或者体检结果
	 * 
	 * @param irId
	 * @return
	 */
	String getResult(@Param("irId") String irId);
	/**
	 * 通过体检记录号码 插入 体检建议和指导
	 * @param mecNum
	 * @param req
	 * @param guide
	 * @return
	 */
	Integer addResult(@Param("docName") String docName,@Param("mecNum") String mecNum ,@Param("req") String req,@Param("guide") String guide);

}
