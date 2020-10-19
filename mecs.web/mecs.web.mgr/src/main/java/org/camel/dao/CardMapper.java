package org.camel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import mecs.camel.model.Admin;
import mecs.camel.model.Card;

public interface CardMapper {
	/**
	 * 获取数据库中已存在的所有卡
	 * 
	 * @return
	 */
	List<Card> getAllCard(@Param("cardNumber") String cardNumber, @Param("name") String userName,
			@Param("state") String cardState, @Param("start") String startTime, @Param("end") String endTime,
			RowBounds rb);

	/**
	 * 获取卡片的数量
	 * 
	 * @param cardNumber
	 * @param userName
	 * @param cardState
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	Integer getAllCardCount(@Param("cardNumber") String cardNumber, @Param("name") String userName,
			@Param("state") String cardState, @Param("start") String startTime, @Param("end") String endTime);

	/**
	 * 获取已存在所有卡号
	 * 
	 * @return
	 */
	List<String> getAllCardNumber();

	/**
	 * 卡入库
	 * 
	 * @param cardList
	 * @param admin
	 * @return
	 */
	Integer addCard(@Param("cardList") List<String> cardList, @Param("adminId") String adminId);

	/**
	 * 卡片删除
	 * 
	 * @param cardId
	 * @return
	 */
	Integer delCard(@Param("id") String cardId);

}
