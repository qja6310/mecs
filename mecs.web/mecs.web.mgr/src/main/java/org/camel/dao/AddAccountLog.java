package org.camel.dao;

import mecs.camel.model.Account;

public interface AddAccountLog {
	/**
	 * 类里面需要设置的字段为：
	 * #{cardNum},#{accType},#{accDescribe},#{accMoney}
	 * @param account
	 * @return
	 */
	//个人对账表插入数据
	Integer insertAccount(Account account);
}
