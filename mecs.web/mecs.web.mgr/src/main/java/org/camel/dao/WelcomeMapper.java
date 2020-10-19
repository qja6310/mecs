package org.camel.dao;

import org.apache.ibatis.annotations.Param;

public interface WelcomeMapper {

	/**
	 * 获取售卡数
	 * @return
	 */
	Integer getSellCardNum();
	
	/**
	 * 获取用户数
	 * @return
	 */
	Integer getUserNum();
	
	/**
	 * 获取营业额
	 * @return
	 */
	String getTurnover();
	
	/**
	 * 获取可用卡数
	 * @return
	 */
	Integer getUsableCardNum();
	
	/**
	 * 获取当月售卡数
	 * @param firstday
	 * @param currDate
	 * @return
	 */
	Integer getSell(@Param("firstday") String firstday,@Param("currDate") String currDate);
	
	/**
	 * 获取当月用户数
	 * @param firstday
	 * @param currDate
	 * @return
	 */
	Integer getUser(@Param("firstday") String firstday,@Param("currDate") String currDate);
	
	/**
	 * 获取当月营业额
	 * @param firstday
	 * @param currDate
	 * @return
	 */
	String getTurnoverM(@Param("firstday") String firstday,@Param("currDate") String currDate);
}
