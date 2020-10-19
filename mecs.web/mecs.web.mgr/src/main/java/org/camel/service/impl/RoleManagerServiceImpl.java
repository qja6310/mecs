package org.camel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.camel.dao.LogMapper;
import org.camel.dao.RoleManagerMapper;
import org.camel.service.RoleManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.Menu;
import mecs.camel.model.Role;

@Service
public class RoleManagerServiceImpl implements RoleManagerService {
	@Autowired
	private RoleManagerMapper roleManagerMapper;
	@Autowired
	private LogMapper logMapper;
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject queryRoleTable(String roleName, String beginDate, String endDate, RowBounds rb) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		// 查询表格数据
		List<Role> queryRoleTable = roleManagerMapper.queryRoleTable(roleName, beginDate, endDate, rb);
		// 总条数
		Integer roleCount = roleManagerMapper.getRoleCount(roleName, beginDate, endDate);
		obj.put("table", queryRoleTable);
		obj.put("count", roleCount);
		return obj;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject delRole(String doId,String roleId) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		Role role = roleManagerMapper.getRole(roleId);
		// 删除角色表
		Integer delRole = roleManagerMapper.delRole(roleId);
		// 删除人员角色对应表
		Integer delAdRole = roleManagerMapper.delAdRole(roleId);
		// 删除菜单角色对应表
		Integer delRoleMenu = roleManagerMapper.delRoleMenu(roleId);
		// 对应表可能在删除人员、菜单时已经被删除
		if (delRole > 0) {
			obj.put("res", "删除成功");
			 //插入日志
			logMapper.addLog(Integer.valueOf(doId),"删除角色", "删除了："+role.getRoleName()+"角色");
		} else {
			obj.put("res", "删除失败");
		}

		return obj;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject showAllMenu() {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		ArrayList<Menu> showAllMenu = roleManagerMapper.showAllMenu();
		obj.put("menu", showAllMenu);
		return obj;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject addRoleMenu(String doId,String roleName, ArrayList<String> list) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		// 查询角色名字是否存在
		String queryRoleName = roleManagerMapper.queryRoleName(roleName);
		if (queryRoleName == null) {
			Role role = new Role();
			role.setRoleName(roleName);
			// 增加角色
			Integer addRole = roleManagerMapper.addRole(role);
			if (list != null) {
				// 增加对应表
				Integer addRoleMenu = roleManagerMapper.addRoleMenu(role.getRoleId(), list);
				if (addRole > 0 && addRoleMenu > 0) {
					obj.put("res", "增加成功");
					 //插入日志
					logMapper.addLog(Integer.valueOf(doId),"增加角色", "增加了"+role.getRoleName()+"角色");
				} else {
					obj.put("res", "增加失败");
				}

			} else {
				if (addRole > 0) {
					obj.put("res", "增加成功");
					 //插入日志
					logMapper.addLog(Integer.valueOf(doId),"增加角色", "增加了"+role.getRoleName()+"角色");
				} else {
					obj.put("res", "增加失败");
				}
			}

		} else {
			obj.put("res", "角色名已存在，增加失败");
		}

		return obj;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject showUpdatePanel(String roleId) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		// 根据角色id查询角色名字
		Role role = roleManagerMapper.getRole(roleId);
		// 查询所有菜单
		ArrayList<Menu> showAllMenu = roleManagerMapper.showAllMenu();
		// 查询这个角色有多少菜单
		ArrayList<String> roleMenu = roleManagerMapper.getRoleMenu(roleId);
		obj.put("role", role);
		obj.put("allMenu", showAllMenu);
		obj.put("roleMenu", roleMenu);
		return obj;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject toUpdateRoleMenu(String doId,String roleId, String roleName, ArrayList<String> list) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		// 判断角色名是否存在
		String queryRoleName = roleManagerMapper.queryRoleName(roleName);
		if (queryRoleName == null) {
			// 修改角色名
			Integer updateRoleName = roleManagerMapper.updateRoleName(roleId, roleName);
			// 删除原本角色菜单
			Integer delRoleMenu = roleManagerMapper.delRoleMenu(roleId);
			if (list != null) {
				Integer addRoleMenu = roleManagerMapper.addRoleMenu(roleId, list);
				if (updateRoleName > 0 && addRoleMenu > 0) {
					obj.put("res", "修改成功");
					 //插入日志
					Role role = roleManagerMapper.getRole(roleId);
					logMapper.addLog(Integer.valueOf(doId),"修改角色", "修改了"+role.getRoleName()+"角色");
				} else {
					obj.put("res", "修改失败");
				}
			} else {
				if (updateRoleName > 0) {
					obj.put("res", "修改成功");
					 //插入日志
					Role role = roleManagerMapper.getRole(roleId);
					logMapper.addLog(Integer.valueOf(doId),"修改角色", "修改了"+role.getRoleName()+"角色");
				} else {
					obj.put("res", "修改失败");
				}
			}

		} else {
			// 名字存在判断是否和原来一样
			Role role = roleManagerMapper.getRole(roleId);
			if (role.getRoleName().equals(roleName)) { // 如果名字和原来一样
				// 修改角色名
				Integer updateRoleName = roleManagerMapper.updateRoleName(roleId, roleName);
				// 删除原本角色菜单
				Integer delRoleMenu = roleManagerMapper.delRoleMenu(roleId);
				if (list != null) {
					Integer addRoleMenu = roleManagerMapper.addRoleMenu(roleId, list);
					if (updateRoleName > 0 && addRoleMenu > 0) {
						obj.put("res", "修改成功");
						//插入日志
						logMapper.addLog(Integer.valueOf(doId),"修改角色", "修改了"+role.getRoleName()+"角色");
					} else {
						obj.put("res", "修改失败");
					}
				} else {
					if (updateRoleName > 0) {
						obj.put("res", "修改成功");
						//插入日志
						logMapper.addLog(Integer.valueOf(doId),"修改角色", "修改了"+role.getRoleName()+"角色");
					} else {
						obj.put("res", "修改失败");
					}
				}

			} else {
				obj.put("res", "该角色已存在");

			}

		}

		return obj;
	}

}
