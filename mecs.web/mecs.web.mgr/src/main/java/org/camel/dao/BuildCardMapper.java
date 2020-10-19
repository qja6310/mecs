package org.camel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mecs.camel.model.Card;
import mecs.camel.model.User;

public interface BuildCardMapper {
	/**
	 * 给界面提示的3个卡号。查询状态是待销售的卡，卡号返回3个卡号给界面，显示中不用这样，是售卡元输入卡号或者刷卡输入卡号
	 * @param cardState 
	 * @return
	 */
	String queryCardNumber(@Param("cardState")Integer cardState);
	
	/**
	 * 获得查到下一个序列，这序列插入User表用作为自增序列，插入user表的时候，就不用自增序列了
	 * @param user
	 * @return
	 */
	Integer getUserId();
	
	/**
	 * 创建用户
	 * @param user
	 * @return
	 */
	Integer buildUser(User user);
	
	
	
	/**
	 * 通过过卡号和状态，先查数据库有没有这个卡号，状态待销售的卡，查下有没有这个条件的CardID
	 * @param cardNumber
	 * @param cardState 待销售卡状态为0
	 * @return
	 */
	String queryCardIdByCardNumberAndState(@Param("cardNumber")String cardNumber,@Param("cardState")Integer cardState);
	
	/**
	 * 在字典表里面传入 buildcard，到字典表查下 ，开单的单价，界面得到金额要减去这个单价，才能存到卡里
	 * @param buildcard
	 * @return
	 */
	Integer queryBuildCardMoney(@Param("buildcard")String buildcard);
	
	
	/**
	 * 注意这边传入数据的金额，必须要减去开卡的钱，再存入数据库
	 * 
	 * 
	 * 从界面获得卡号，充值的金额，查到cardID，以cardID为条件，插入userID，然后改状态为已经销售的状态
	 * @param user
	 * @return
	 */
	Integer buildCard(@Param("userId")String userId,@Param("carId")String carId,@Param("cardState")Integer cardState,@Param("sellAdminId")String sellAdminId,@Param("balance")Integer balance);
}
