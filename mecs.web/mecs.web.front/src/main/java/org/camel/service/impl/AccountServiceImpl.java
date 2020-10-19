package org.camel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.camel.dao.AccountMapper;
import org.camel.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mecs.camel.model.Account;
import mecs.camel.model.Dict;
@Service
public class AccountServiceImpl implements AccountService {
	 /**
     * 卡对账时查询总条数
     */
	@Autowired
	private AccountMapper mapper;
    @Transactional(rollbackFor = Exception.class)
	public Integer getAccountCountByCard(String cardNum, String beginTime, String overTime, String type) {
         int count =  mapper.getAccountCountByCard(cardNum, beginTime, overTime, type);
		return count;
	}
   /**
    * 卡对账时根据卡号 查询所有对账记录集合
    */
	@Override
	public List<Account> getAccountByCard(String cardNum, String beginTime, String overTime, String type,
			RowBounds rb) {
		ArrayList<Account> list = (ArrayList<Account>) mapper.getAccountByCard(cardNum, beginTime, overTime, type, rb);
		return list;
	}
	/**
	 * 根据卡号查询余额
	 */
	public String getBalanceByCard(String cardNum) {
		String s = mapper.getBalanceByCard(cardNum);
		return s;
	}
   /**
    * 根据卡号获取用户id
    */
	public Integer getUserIdByCard(String cardNum) {
		int userId = mapper.getUserIdByCard(cardNum);
		return userId;
	}
	/**
	 * //对账增加日志
	 */
	public Integer addLogForAccount(int operId, String operType, String logRemark) {
		
    	int ret = mapper.addLogForAccount(operId, operType, logRemark);
		return ret;

		
}
	@Override
	public String getUserNameByUserId(Integer userId) {
		String name = mapper.getUserNameByUserId(userId);
		
		return name;
	}
	@Override
	public ArrayList<Dict> getDeskInfoByDictType(String type) {
		 ArrayList<Dict>  list = mapper.getDeskInfoByDictType(type);
		 return list;
	}


}
