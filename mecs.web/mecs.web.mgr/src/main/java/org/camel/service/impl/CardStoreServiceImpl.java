package org.camel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.camel.dao.CardMapper;
import org.camel.dao.DictMapper;
import org.camel.dao.LogMapper;
import org.camel.service.CardStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

@Service
public class CardStoreServiceImpl implements CardStoreService {

	@Autowired
	private CardMapper cardMapper;

	@Autowired
	private DictMapper dictMapper;

	@Autowired
	private LogMapper logMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject cardStore(String startNum, String endNum, String cardPrefix, String adminId) {

		Integer sNum = Integer.parseInt(startNum);
		Integer eNum = Integer.parseInt(endNum);
		Integer num = sNum;
		List<String> storeList = new ArrayList<String>();// 生成的卡号
		List<String> cardList = new ArrayList<String>();// 可以正确添加的卡
		List<String> errorCardNumber = new ArrayList<String>();// 错误的卡号，已存在的卡号

		/*
		 * 获取前缀，卡的位数（数字部分） for，拼接次数
		 * 
		 */
		Integer figure = dictMapper.getCardFigure();

		for (int i = 0; i <= eNum - sNum; i++) {
			String strNum = num + "";
			int strNumLen = strNum.length();
			for (int j = 0; j < figure - strNumLen; j++) {
				strNum = "0" + strNum;
			}
			strNum = cardPrefix + strNum;
			storeList.add(strNum);
			num++;
		}
		List<String> allCard = cardMapper.getAllCardNumber();

		for (int i = 0; i < storeList.size(); i++) {
			if (allCard.size() == 0) {
				cardList.add(storeList.get(i));
			} else {
				for (int j = 0; j < allCard.size(); j++) {
					if (storeList.get(i).equals(allCard.get(j))) {
						errorCardNumber.add(storeList.get(i));
						break;
					} else if (j == allCard.size() - 1) {
						cardList.add(storeList.get(i));
					}
				}
			}
		}
		if (cardList.size() != 0) {
			cardMapper.addCard(cardList, adminId);
			Integer number = eNum - sNum + 1;
			logMapper.addLog(Integer.parseInt(adminId), "卡入库", "管理员添加" + number + "张卡入库");
		}

		JSONObject obj = new JSONObject();
		obj.put("list", storeList);
		obj.put("right", cardList);
		obj.put("error", errorCardNumber);
		return obj;

	}

}
