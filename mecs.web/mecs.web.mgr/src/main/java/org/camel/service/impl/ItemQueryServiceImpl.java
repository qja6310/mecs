package org.camel.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.camel.dao.ItemMapper;
import org.camel.dao.ItemsLinkItemMapper;
import org.camel.dao.LogMapper;
import org.camel.service.ItemQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.Item;
import mecs.camel.utils.LimitUtil;

@Service
public class ItemQueryServiceImpl implements ItemQueryService {

	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private LogMapper logMapper;

	@Autowired
	private ItemsLinkItemMapper itemsLinkItemMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject getItem(String currPage, String itemName) {

		if (currPage == null) {
			currPage = "1";
		}
		int limit = LimitUtil.ITEMLIMIT;
		int begin = (Integer.parseInt(currPage) - 1) * limit;

		RowBounds rb = new RowBounds(begin, limit);

		List<Item> allItem = itemMapper.getAllItem(itemName, rb);
		Integer allItemCount = itemMapper.getAllItemCount(itemName);

		JSONObject obj = new JSONObject();
		obj.put("item", allItem);
		obj.put("count", allItemCount);
		obj.put("limit", limit);

		return obj;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject addItm(String doId,String itemName, String itemUnit) {

		JSONObject obj = new JSONObject();

		String selectItemName = itemMapper.selectItemName(itemName);
		if (selectItemName == null) {

			Integer addItem = itemMapper.addItem(itemName, itemUnit);

			if (addItem > 0) {
				obj.put("add", "添加成功");
				 //插入日志
				logMapper.addLog(Integer.valueOf(doId),"增加细项", "增加了"+itemName+"细项");
			} else {
				obj.put("add", "添加失败");
			}
		} else {
			obj.put("add", "细项名已存在！请重新命名");
		}

		return obj;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject delItem(String doId,String itemId) {

		JSONObject obj = new JSONObject();
		Item iemById = itemMapper.getIemById(itemId);
		Integer delItem = itemMapper.delItem(itemId);
		Integer delItemLink = itemsLinkItemMapper.delItemLink(itemId);

		if (delItem > 0 ) {
			obj.put("del", "删除成功");
			 //插入日志
		
			logMapper.addLog(Integer.valueOf(doId),"删除细项", "删除了"+iemById.getItemName()+"细项");
			
		} else {
			obj.put("del", "删除失败");
		}

		return obj;
	}

}
