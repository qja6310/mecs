package org.camel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.camel.dao.BillingMapper;
import org.camel.dao.ComboLinkItemsMapper;
import org.camel.dao.ComboMapper;
import org.camel.dao.LogMapper;
import org.camel.service.ComboManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.Combo;
import mecs.camel.model.dto.ItemsDto;
import mecs.camel.utils.LimitUtil;

@Service
public class ComboManagerServiceImpl implements ComboManagerService {

	@Autowired
	private ComboMapper comboMapper;
	
	@Autowired
	private BillingMapper billingMapper;
	
	@Autowired
	private ComboLinkItemsMapper comboLinkItemsMapper;
	@Autowired
	private LogMapper logMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject getCombo(String nowPage, String comboName) {

		if (nowPage == null) {
			nowPage = "1";
		}
		int limit = LimitUtil.ITEMLIMIT;
		int begin = (Integer.parseInt(nowPage) - 1) * limit;

		RowBounds rb = new RowBounds(begin, limit);

		List<Combo> allCombo = comboMapper.getAllCombo(comboName, rb);
		Integer allComboCount = comboMapper.getAllComboCount(comboName);

		JSONObject obj = new JSONObject();
		obj.put("combo", allCombo);
		obj.put("count", allComboCount);
		obj.put("limit", limit);

		return obj;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject delCombo(String doId, String comboId) {
		JSONObject obj = new JSONObject();
		Combo comboById = comboMapper.getComboById(comboId);
		Integer delCombo = comboMapper.delCombo(comboId);
		Integer delComboLinkItems = comboLinkItemsMapper.delComboLinkItems(comboId);
		if (delCombo > 0 && delComboLinkItems > 0) {
			obj.put("del", "删除成功");
			// 插入日志
			logMapper.addLog(Integer.valueOf(doId), "删除套餐", "删除了" + comboById.getComboName() + "的套餐");
		} else {
			obj.put("del", "删除失败");
		}

		return obj;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject getAllCombo() {

		JSONObject obj = new JSONObject();
		List<ItemsDto> detailsByCombo = comboMapper.getDetailsByCombo();
		obj.put("itemsDtoList", detailsByCombo);
		return obj;

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject alterComboState(String doId, String comboId, String comboState) {

		JSONObject obj = new JSONObject();

		Integer alterComboState = comboMapper.alterComboState(comboId, comboState);

		if (alterComboState > 0 && Integer.parseInt(comboState) == 1) {
			obj.put("alt", "上架成功");
			// 插入日志
			Combo comboById = comboMapper.getComboById(comboId);
			logMapper.addLog(Integer.valueOf(doId), "上架套餐", "上架了" + comboById.getComboName() + "的套餐");
		} else if (alterComboState > 0 && Integer.parseInt(comboState) == 0) {
			obj.put("alt", "下架成功");
			// 插入日志
			Combo comboById = comboMapper.getComboById(comboId);
			logMapper.addLog(Integer.valueOf(doId), "下架套餐", "下架了：" + comboById.getComboName() + "的套餐");
		} else if (alterComboState <= 0 && Integer.parseInt(comboState) == 1) {
			obj.put("alt", "上架失败");
		} else {
			obj.put("alt", "下架失败");
		}

		return obj;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject addCombo(String doId, String comboName, String price, ArrayList<String> list) {
		JSONObject obj = new JSONObject();
		String queryComboNameByName = comboMapper.queryComboNameByName(comboName);
		if (queryComboNameByName == null) { // 名字不存在
			// 增加套餐表
			Combo cm = new Combo();
			cm.setComboName(comboName);
			cm.setComboPrice(price);
			cm.setComboState("1");
			Integer addCombo = comboMapper.addCombo(cm);
			if (list == null) {
				if (addCombo > 0) {
					obj.put("res", "新增成功");
					// 插入日志
					logMapper.addLog(Integer.valueOf(doId), "增加套餐", "增加了：" + comboName + "套餐");
				} else {
					obj.put("res", "新增失败");
				}
			} else {
				Integer addComboItems = comboMapper.addComboItems(cm.getComboId(), list);
				if (addCombo > 0 && addComboItems > 0) {
					obj.put("res", "新增成功");
					// 插入日志
					logMapper.addLog(Integer.valueOf(doId), "增加套餐", "增加了" + comboName + "的套餐");
				} else {
					obj.put("res", "新增失败");
				}
			}

		} else { // 名字存在
			obj.put("res", "套餐名已存在");
		}

		return obj;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject showUpdateComboPanel(String comboId) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		// 全部的项目
		List<ItemsDto> detailsByCombo = comboMapper.getDetailsByCombo();
		obj.put("itemsDtoList", detailsByCombo);
		// 套餐详情
		Combo comboById = comboMapper.getComboById(comboId);
		obj.put("combo", comboById);
		// 套餐包含的项目
		List<String> itemsByCombo = comboMapper.getItemsByCombo(comboId);
		obj.put("items", itemsByCombo);

		return obj;
	}

	@Override
	public JSONObject toUpdateCombo(String doId, String comboId, String comboName, String price,
			ArrayList<String> list) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		// 判断名字是否重复
		String queryComboNameByName = comboMapper.queryComboNameByName(comboName);
		if (queryComboNameByName == null) {
			Integer updateCombo = comboMapper.updateCombo(comboId, comboName, price);
			if (list == null) {
				// 删除原本对应表
				Integer delComboLinkItems = comboLinkItemsMapper.delComboLinkItems(comboId);
				if (updateCombo > 0) {
					obj.put("res", "修改成功");
					// 插入日志
					Combo comboById = comboMapper.getComboById(comboId);
					logMapper.addLog(Integer.valueOf(doId), "修改套餐", "修改了" + comboById.getComboName() + "的套餐");
				} else {
					obj.put("res", "修改失败");
				}
			} else {
				// 删除原本对应表
				Integer delComboLinkItems = comboLinkItemsMapper.delComboLinkItems(comboId);
				// 新增对应关系
				Integer addComboItems = comboMapper.addComboItems(comboId, list);
				if (updateCombo > 0 && addComboItems > 0) {
					obj.put("res", "修改成功");
					// 插入日志
					Combo comboById = comboMapper.getComboById(comboId);
					logMapper.addLog(Integer.valueOf(doId), "修改套餐", "修改了" + comboById.getComboName() + "的套餐");
				} else {
					obj.put("res", "修改失败");
				}
			}

		} else {
			// 判断是否和原来一样
			if (queryComboNameByName.equals(comboName)) {
				Integer updateCombo = comboMapper.updateCombo(comboId, comboName, price);
				if (list == null) {
					// 删除原本对应表
					Integer delComboLinkItems = comboLinkItemsMapper.delComboLinkItems(comboId);
					if (updateCombo > 0) {
						obj.put("res", "修改成功");
						// 插入日志
						Combo comboById = comboMapper.getComboById(comboId);
						logMapper.addLog(Integer.valueOf(doId), "修改套餐", "修改了" + comboById.getComboName() + "的套餐");
					} else {
						obj.put("res", "修改失败");
					}
				} else {
					// 删除原本对应表
					Integer delComboLinkItems = comboLinkItemsMapper.delComboLinkItems(comboId);
					// 新增对应关系
					Integer addComboItems = comboMapper.addComboItems(comboId, list);
					if (updateCombo > 0 && addComboItems > 0) {
						obj.put("res", "修改成功");
						// 插入日志
						Combo comboById = comboMapper.getComboById(comboId);
						logMapper.addLog(Integer.valueOf(doId), "修改套餐", "修改了" + comboById.getComboName() + "的套餐");
					} else {
						obj.put("res", "修改失败");
					}
				}

			} else {
				// 重复
				obj.put("res", "套餐名已存在");
			}
		}

		return obj;
	}

	@Override
	public JSONObject lookCombo(String comboId) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		// 套餐详情
		Combo comboById = comboMapper.getComboById(comboId);
		obj.put("combo", comboById);
		List<ItemsDto> detailsByComboId = billingMapper.getDetailsByComboId(comboId);
		obj.put("items", detailsByComboId);
		return obj;
	}

}
