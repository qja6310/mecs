package org.camel.service.impl;

import org.camel.dao.DictMapper;
import org.camel.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

@Service
public class DictServiceImpl implements DictService {

	@Autowired
	private DictMapper dictMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject getCardPrefix() {

		String cardPrefix = dictMapper.getCardPrefix();
		
		JSONObject obj = new JSONObject();
		obj.put("prefix", cardPrefix);
		
		return obj;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject getCardFigure() {

		Integer cardFigure = dictMapper.getCardFigure();
		
		JSONObject obj = new JSONObject();
		obj.put("figure", cardFigure);
		
		return obj;
	}

}
