package org.camel.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.camel.dao.CardMapper;
import org.camel.service.CardQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.Card;
import mecs.camel.utils.LimitUtil;

@Service
public class CardQueryServiceImpl implements CardQueryService {

	@Autowired
	private CardMapper cardMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject cardQuery(String currPage, String cardNumber, String userName, String cardState, String startTime,
			String endTime) {

		if (currPage == null) {
			currPage = "1";
		}
		int limit = LimitUtil.CARDLIMIT;
		int begin = (Integer.parseInt(currPage) - 1) * limit;

		RowBounds rb = new RowBounds(begin, limit);

		List<Card> allCard = cardMapper.getAllCard(cardNumber, userName, cardState, startTime, endTime, rb);
		Integer allCardCount = cardMapper.getAllCardCount(cardNumber, userName, cardState, startTime, endTime);

		JSONObject obj = new JSONObject();
		obj.put("card", allCard);
		obj.put("count", allCardCount);
		obj.put("limit", limit);

		return obj;
	}

}
