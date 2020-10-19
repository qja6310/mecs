package org.camel.service;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;

import com.alibaba.fastjson.JSONObject;

public interface RoleManagerService {
	/**
	 * 查询角色列表
	 * 
	 * @param roleName  角色名
	 * @param beginDate 开始时间
	 * @param endDate   结束时间
	 * @param rb        翻页
	 * @return
	 */
	JSONObject queryRoleTable(String roleName, String beginDate, String endDate, RowBounds rb);

	/**
	 * 删除角色
	 * 
	 * @param roleId 角色id
	 * @return
	 */
	JSONObject delRole(String doId,String roleId);

	/**
	 * 求所有菜单
	 * 
	 * @return
	 */
	JSONObject showAllMenu();

	/**
	 * 增加角色 菜单
	 * 
	 * @return
	 */
	JSONObject addRoleMenu(String doId,String roleName, ArrayList<String> list);

	/**
	 * 初始化角色编辑界面
	 * 
	 * @param roleId
	 * @return
	 */
	JSONObject showUpdatePanel(String roleId);

	/**
	 * 修改角色菜单
	 * 
	 * @param roleId
	 * @param roleName
	 * @param list
	 * @return
	 */
	JSONObject toUpdateRoleMenu(String doId,String roleId, String roleName, ArrayList<String> list);

}
