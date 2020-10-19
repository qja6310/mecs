package org.camel.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.camel.dao.AdminLoginMapper;
import org.camel.dao.SettleAccountsCommitMapper;
import org.camel.service.SettleAccountsCommitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mecs.camel.model.Account;
import mecs.camel.model.Admin;
import mecs.camel.model.Log;

@Service
public class SettleAccountsCommitServiceImpl implements SettleAccountsCommitService {

	@Autowired
	private SettleAccountsCommitMapper mapper;

	// 登录的日志插入
	@Autowired
	private AdminLoginMapper logmap;

	@Override
	@Transactional(rollbackFor = Exception.class) // 事务必须要写的
	public String commit(String mrId, HttpServletRequest req) {
		// 插入日志,登录的是有写好插入日志的mapper直接调用就用了
		Admin ad = (Admin) req.getSession().getAttribute("sessionAdminKey");
		if (ad == null) {
			return "notLogIn";
		}
		// String转Integer的时候，必须进行非空判断，不然可能出现空指针
		Integer mrId2 = null;
		if (mrId != null) {
			mrId2 = Integer.valueOf(mrId);
		}
		// TODO Auto-generated method stub
		// 先要获得余额和一行体检的钱，这两个再去数据库拿，不要从界面过来，这样保险点
		// 去拿这个两个钱的时候，要先判断有没有结算，如果这个业务就不要做了，

		// System.out.println("mrId2="+mrId2);
		// 01，查询是否是 未结算，只能对未结算的，进行操作，因为除了 有四种状态，直接通过体检ID，看下查到的是否等于1
		Integer stateId = mapper.querySettleState(mrId2);
		if (stateId != 1) {
			return "haveSettle";
		} else {

			// 去体检ID查出ID，再去查余额
			Integer balance = mapper.quaryBalance(mrId2);
			// 体检ID，查体检费用
			Integer mrPrice = mapper.quaryMrPrice(mrId2);
			if (balance < mrPrice) {
				return "balanceInsuf";
			}
			// 修改卡的余额
			Integer newBalance = balance - mrPrice;

			Integer newBa = mapper.updateBalance(newBalance, mrId2);
			if (newBa != 1) {
				return "updateBalanceErr";
			}

			// 修改体检记录表的状态，改状态为：已经结算
			Integer mrState = mapper.updateMrState(mrId2,ad.getAdminId());
			if (mrState != 1) {
				return "Err1";
			}

			/**
			 * 这个表，不能忘记修改了，非常重要，修改项目记录表的状态，改状态为：已经结算
			 */
			Integer itemsRecord = mapper.updateItemsRecord(mrId2);
			if (itemsRecord <= 0) {
				return "Err2";
			}

			Log log = new Log();
			log.setLogUserId(ad.getAdminId());// 操作的id
			log.setLogType("结算");
			/*
			 * 确认对哪个体检记录做结算
			 */
			String mrNum = mapper.getMrNumberByMrId(mrId);
			log.setLogRemark("操作账号为:" + ad.getAdminAcc() + "对体检记录[" + mrNum + "]作结算");

			Integer doLog = logmap.doLog(log);
			if (doLog != 1) {
				return "Err3";
			}
			// 个人对账表插入数据
			Account ac = new Account();
			// 去数据库，通过 体检记录表ID，获得卡Id，嵌套去查卡号
			String cardNum = mapper.queryCardNum(mrId2);
			// System.out.println("去数据库，通过 体检记录表ID，获得卡Id，嵌套去查卡号 cardNum="+cardNum);
			if (cardNum == null) {
				return "cardNumIsNull";
			}
			ac.setCardNum(cardNum);// 对账卡号
			ac.setAccType("1");// 对账类型 1为消费，0是充值

			/*
			 * 获取admin的名字
			 */
			String adminName = mapper.getAdminNumeByAdminId(ad.getAdminId());
			ac.setAccDescribe("进行了[" + mrNum + "]结算,结算人为[" + adminName + "]");// 对账描述
			ac.setAccMoney(String.valueOf(mrPrice));// 金额

			Integer acc = mapper.insertAccount(ac);
			// System.out.println("个人对账表插入数据 acc="+acc);
			if (acc != 1) {
				return "Err4";
			}

			return "suc";

		}

	}

}
