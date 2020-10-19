package org.camel.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.camel.dao.ItemsLinkItemMapper;
import org.camel.dao.ItemsMapper;
import org.camel.dao.LogMapper;
import org.camel.dao.PersonnelMgMapper;
import org.camel.service.ItemsManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.Department;
import mecs.camel.model.Item;
import mecs.camel.model.Items;
import mecs.camel.utils.LimitUtil;

@Service
public class ItemsManagerServiceImpl implements ItemsManagerService {

	@Autowired
	private ItemsMapper itemsMapper;
	@Autowired
	private PersonnelMgMapper personnelMgMapper;
	@Autowired
	private ItemsLinkItemMapper itemsLinkItemMapper;
	@Autowired
	private LogMapper logMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject getItems(String nowPage, String itemsName, String depId) {

		if (nowPage == null) {
			nowPage = "1";
		}
		int limit = LimitUtil.ITEMLIMIT;
		int begin = (Integer.parseInt(nowPage) - 1) * limit;

		RowBounds rb = new RowBounds(begin, limit);

		List<Items> allItems = itemsMapper.getAllItems(itemsName, depId, rb);
		Integer allItemsCount = itemsMapper.getAllItemsCount(itemsName);

		JSONObject obj = new JSONObject();
		obj.put("items", allItems);
		obj.put("count", allItemsCount);
		obj.put("limit", limit);

		return obj;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject delItems(String doId, String itemsId) {
		Items items = itemsMapper.getItemsById(itemsId);
		Integer delItems = itemsMapper.delItems(itemsId);
		Integer delItemsLink = itemsLinkItemMapper.delItemsLink(itemsId);

		JSONObject obj = new JSONObject();

		if (delItems > 0) {
			obj.put("del", "删除成功");
			// 插入日志
			logMapper.addLog(Integer.valueOf(doId), "删除项目", "删除了" + items.getItemsName() + "的项目");
		} else {
			obj.put("del", "删除失败");
		}

		return obj;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject addItems(String doId, String depId, String itemsName, String itemsPrice, List<String> itemList) {
		JSONObject obj = new JSONObject();
		// 查询项目是否存在
		String queryItemsName = itemsMapper.QueryItemsName(itemsName);
		if (queryItemsName == null) {

			Items it = new Items();
			it.setItemsName(itemsName);
			it.setItemsPrice(itemsPrice);
			it.setDepId(depId);

			Integer addItems = itemsMapper.addItems(it);
			Integer addItemsLink = 0;
			if (itemList != null) {
				addItemsLink = itemsLinkItemMapper.addItemsLink(it.getItemsId(), itemList);

				if (addItems > 0 && addItemsLink > 0) {
					obj.put("add", "添加成功");
					// 插入日志
					logMapper.addLog(Integer.valueOf(doId), "增加项目", "增加：" + itemsName + "项目");
				} else {
					obj.put("add", "添加失败");
				}

			} else {

				if (addItems > 0) {
					obj.put("add", "添加成功");
					// 插入日志
					logMapper.addLog(Integer.valueOf(doId), "增加项目", "增加：" + itemsName + "项目");
				} else {
					obj.put("add", "添加失败");
				}
			}
		} else {
			obj.put("add", "项目名已存在");

		}

		return obj;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject alterItems(String doId, String itemsId, String itemsName, String itemsPrice, String depId,
			List<String> itemList) {
		JSONObject obj = new JSONObject();
		Items itemsById = itemsMapper.getItemsById(itemsId);
		// 查询项目名字是否重复
		String queryItemsName = itemsMapper.QueryItemsName(itemsName);
		if (queryItemsName == null) {
			// 修改项目信息
			Integer alterItems = itemsMapper.alterItems(itemsId, itemsName, itemsPrice, depId);
			// 删除对应信息
			Integer delItemsLink = itemsLinkItemMapper.delItemsLink(itemsId); // 可能原本就没对应关系 ，删除会失败
			if (itemList != null) {
				Integer addItemsLink = itemsLinkItemMapper.addItemsLink(itemsId, itemList);
				if (alterItems > 0 && addItemsLink > 0) {
					obj.put("res", "修改成功");
					// 插入日志
					Items items = itemsMapper.getItemsById(itemsId);
					logMapper.addLog(Integer.valueOf(doId), "修改项目", "修改了" + items.getItemsName() + "项目");
				} else {
					obj.put("res", "修改失败");
				}
			} else {
				if (alterItems > 0) {
					obj.put("res", "修改成功");
					// 插入日志
					Items items = itemsMapper.getItemsById(itemsId);
					logMapper.addLog(Integer.valueOf(doId), "修改项目", "修改了" + items.getItemsName() + "项目");
				} else {
					obj.put("res", "修改失败");
				}

			}

		} else {
			// 判断项目名字是否是他原本的名字
			if (itemsById.getItemsName().equals(itemsName)) {
				// 修改项目信息
				Integer alterItems = itemsMapper.alterItems(itemsId, itemsName, itemsPrice, depId);
				// 删除对应信息
				Integer delItemsLink = itemsLinkItemMapper.delItemsLink(itemsId); // 可能原本就没对应关系 ，删除会失败
				if (itemList != null) {
					Integer addItemsLink = itemsLinkItemMapper.addItemsLink(itemsId, itemList);
					if (alterItems > 0 && addItemsLink > 0) {
						obj.put("res", "修改成功");
						// 插入日志
						Items items = itemsMapper.getItemsById(itemsId);
						logMapper.addLog(Integer.valueOf(doId), "修改项目", "修改了" + items.getItemsName() + "项目");
					} else {
						obj.put("res", "修改失败");
					}
				} else {
					if (alterItems > 0) {
						obj.put("res", "修改成功");
						// 插入日志
						Items items = itemsMapper.getItemsById(itemsId);
						logMapper.addLog(Integer.valueOf(doId), "修改项目", "修改了" + items.getItemsName() + "项目");
					} else {
						obj.put("res", "修改失败");
					}

				}

			} else {
				obj.put("res", "项目名已存在");
			}

		}

		return obj;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject showItemsAdd() {
		JSONObject obj = new JSONObject();
		List<Department> depSelect = personnelMgMapper.getDepByState();
		List<Item> allItem = itemsMapper.getAllItem();
		obj.put("dep", depSelect); // 科室
		obj.put("item", allItem); // 细项
		return obj;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject showItemsUpdate(String itemsId) {
		JSONObject obj = new JSONObject();
		// 查询这个项目
		Items itemsById = itemsMapper.getItemsById(itemsId);
		// 获取科室下拉框
		List<Department> depSelect = personnelMgMapper.getDepByState();
		// 获取所有细项
		List<Item> allItem = itemsMapper.getAllItem();
		// 获取这个项目的细项
		List<String> itemByItemsId = itemsLinkItemMapper.getItemByItemsId(itemsId);
		obj.put("items", itemsById);
		obj.put("dep", depSelect);
		obj.put("itemAll", allItem);
		obj.put("item", itemByItemsId);
		return obj;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject lookItems(String itemsId) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		Items itemsById = itemsMapper.lookItemsById(itemsId);
		obj.put("items", itemsById);
		List<Item> lookItemById = itemsLinkItemMapper.lookItemById(itemsId);
		obj.put("itemAll", lookItemById);
		return obj;
	}

}
