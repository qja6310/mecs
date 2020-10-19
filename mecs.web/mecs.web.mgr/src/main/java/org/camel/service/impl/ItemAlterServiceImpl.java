package org.camel.service.impl;

import org.camel.dao.ItemMapper;
import org.camel.dao.LogMapper;
import org.camel.service.ItemAlterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.Item;

@Service
public class ItemAlterServiceImpl implements ItemAlterService {

	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private LogMapper logMapper;
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject alterItem(String doId,String itemName, String itemId) {

		JSONObject obj = new JSONObject();

		String selectItemName = itemMapper.selectItemName(itemName);
		if (selectItemName == null) {

			Integer delCard = itemMapper.alterItemName(itemName, itemId);

			if (delCard > 0) {
				obj.put("alter", "修改成功");
				 //插入日志
				Item iemById = itemMapper.getIemById(itemId);
				logMapper.addLog(Integer.valueOf(doId),"修改细项", "修改了"+iemById.getItemName()+"细项");
			} else {
				obj.put("alter", "修改失败");
			}
		} else {
			obj.put("alter", "细项名已存在！请重新命名");
		}

		return obj;

	}

}
