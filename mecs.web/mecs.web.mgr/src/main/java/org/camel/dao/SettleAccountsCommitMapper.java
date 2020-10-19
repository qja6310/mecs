package org.camel.dao;

import org.apache.ibatis.annotations.Param;

import mecs.camel.model.Account;

public interface SettleAccountsCommitMapper {
	//先要获得余额和一行体检的钱，这两个再去数据库拿，不要从界面过来，这样保险点
	//去拿这个两个钱的时候，要先判断有没有结算，如果这个业务就不要做了，
	
	//查询是否是 未结算，只能对未结算的，进行操作，因为除了 有四种状态，直接通过体检ID，看下查到的是否等于1
	Integer querySettleState(@Param("mrId") Integer mrId);
	//去体检ID查出ID，再去查余额
	Integer quaryBalance(@Param("mrId") Integer mrId);
	//体检ID，查体检费用
	Integer quaryMrPrice(@Param("mrId") Integer mrId);
	//修改卡的余额
	Integer updateBalance(@Param("newBalance") Integer newBalance,@Param("mrId") Integer mrId);
	
	/**
	 * 修改状态和用户Id
	 * @param mrId
	 * @param adminId
	 * @return
	 */
	Integer updateMrState(@Param("mrId") Integer mrId,@Param("adminId") String adminId);
	
	/**
	 *   这个表，不能忘记修改了，非常重要，修改项目记录表的状态，改状态为：已经结算
	 */
	Integer updateItemsRecord(@Param("mrId") Integer mrId);
	
	//插入日志,登录的是有写好插入日志的mapper直接调用就用了
	
	//去数据库，通过 体检记录表ID，获得卡Id，嵌套去查卡号
	String queryCardNum(@Param("mrId") Integer mrId);
	
	//个人对账表插入数据
	Integer insertAccount(Account account);
	
	/**
	 * 根据体检记录ID获取体检记录码
	 * @param mrId
	 * @return
	 */
	String getMrNumberByMrId(@Param("mrId") String mrId);
	
	/**
	 * 获取操作者的名字
	 * @param adminId
	 * @return
	 */
	String getAdminNumeByAdminId(@Param("adminId") String adminId);
}
