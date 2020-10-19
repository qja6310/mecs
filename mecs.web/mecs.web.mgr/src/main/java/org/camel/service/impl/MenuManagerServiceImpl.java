package org.camel.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.camel.dao.LogMapper;
import org.camel.dao.MenuManagerMapper;
import org.camel.service.MenuManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.Menu;
import mecs.camel.model.Role;

@Service
public class MenuManagerServiceImpl implements MenuManagerService {
	@Autowired
	private MenuManagerMapper menuManagerMapper;
	@Autowired
	private LogMapper logMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject queryMenuTable(String menuName, String url, RowBounds rb) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		// 查询表格数据
		List<Role> queryMenuTable = menuManagerMapper.queryMenuTable(menuName, url, rb);

		// 总条数
		Integer roleCount = menuManagerMapper.getMenuCount(menuName, url);
		obj.put("table", queryMenuTable);
		obj.put("count", roleCount);
		return obj;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject delMenu(String doId, String menuId) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		Menu menu = menuManagerMapper.getMenu(menuId);
		// 删除菜单
		Integer delMenu = menuManagerMapper.delMenu(menuId);
		// 删除他的子菜单
		Integer dellMenuByPid = menuManagerMapper.dellMenuByPid(menuId); // 可能没有子菜单 所以会有失败的可能
		// 删除对应表
		Integer delRoleMenu = menuManagerMapper.delMenuRole(menuId);
		if (delMenu > 0) {
			obj.put("res", "删除成功");
			// 插入日志
			
			logMapper.addLog(Integer.valueOf(doId), "删除菜单", "删除了：" + menu.getMenuName() + "的菜单");
		} else {
			obj.put("res", "删除失败");
		}
		return obj;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject addMenu(String doId, String url, String iccon, String menuName, String pid) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		// 查询菜单名是否存在
		String queryMenuName = menuManagerMapper.queryMenuName(menuName);
		if (queryMenuName == null) {
			Menu menu = new Menu();
			menu.setMenuName(menuName);
			menu.setMenuIcon(iccon);
			menu.setMenuUrl(url);
			menu.setMenuPid(pid);//
			Integer addMenu = menuManagerMapper.addMenu(menu);
			if (addMenu > 0) {
				obj.put("res", "增加成功");
				// 插入日志
				logMapper.addLog(Integer.valueOf(doId), "增加菜单", "增加了" + menuName + "菜单");
			} else {
				obj.put("res", "增加失败");
			}
		} else {
			// 菜单名存在
			obj.put("res", "菜单名已存在");

		}

		return obj;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject updateMenuPanel(String menuId) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		// 获得菜单信息
		Menu menu = menuManagerMapper.getMenu(menuId);
		obj.put("menu", menu);
		return obj;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject toUpdateMenu(String doId, String menuId, String url, String menuName, String iccon) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		// 查询菜单名是否存在
		String queryMenuName = menuManagerMapper.queryMenuName(menuName);
		if (queryMenuName == null) {
			Integer updateMenu = menuManagerMapper.updateMenu(menuId, menuName, url, iccon);
			if (updateMenu > 0) {
				obj.put("res", "修改成功");
				// 插入日志
				Menu menu = menuManagerMapper.getMenu(menuId);
				logMapper.addLog(Integer.valueOf(doId), "修改菜单", "修改了" + menu.getMenuName() + "的菜单");
			} else {
				obj.put("res", "修改失败");
			}
		} else {
			// 是否为原来名字
			Menu menu = menuManagerMapper.getMenu(menuId);
			if (menu.getMenuName().equals(menuName)) {
				Integer updateMenu = menuManagerMapper.updateMenu(menuId, menuName, url, iccon);
				if (updateMenu > 0) {
					obj.put("res", "修改成功");
					// 插入日志
					logMapper.addLog(Integer.valueOf(doId), "修改菜单", "修改了" + menu.getMenuName() + "的菜单");
				} else {
					obj.put("res", "修改失败");
				}

			} else {
				obj.put("res", "菜单名已存在");
			}
		}
		return obj;
	}

}
