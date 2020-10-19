package org.camel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import mecs.camel.model.MecSynthesize;

public interface MecSynthesizeMapper {

	/**
	 * 获取体检综合信息
	 * @param nowPage
	 * @param sTime
	 * @param eTime
	 * @param userName
	 * @param userTel
	 * @param mecCode
	 * @return
	 */
	Integer getMecSynthesizeCount(@Param("sTime") String sTime,@Param("eTime") String eTime,@Param("userName") String userName,@Param("userTel") String userTel,@Param("mecCode") String mecCode);
	
	/**
	 * 获取体检综合信息
	 * @param nowPage
	 * @param sTime
	 * @param eTime
	 * @param userName
	 * @param userTel
	 * @param mecCode
	 * @return
	 */
	List<MecSynthesize> getMecSynthesize(@Param("sTime") String sTime,@Param("eTime") String eTime,@Param("userName") String userName,@Param("userTel") String userTel,@Param("mecCode") String mecCode,RowBounds rb);
}
