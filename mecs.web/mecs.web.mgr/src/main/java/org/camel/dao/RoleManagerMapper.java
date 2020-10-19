package org.camel.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import mecs.camel.model.Menu;
import mecs.camel.model.Role;

public interface RoleManagerMapper {
	/**
	 * 查询角色列表
	 * 
	 * @param roleName  角色名
	 * @param beginDate 开始时间
	 * @param endDate   结束时间
	 * @return
	 */
	List<Role> queryRoleTable(@Param("roleName") String roleName, @Param("beginDate") String beginDate,
			@Param("endDate") String endDate, RowBounds rb);

	/**
	 * 求角色列表总数
	 * 
	 * @param roleName  角色名
	 * @param beginDate 开始时间
	 * @param endDate   结束时间
	 * @return
	 */
	Integer getRoleCount(@Param("roleName") String roleName, @Param("beginDate") String beginDate,
			@Param("endDate") String endDate);

	/**
	 * 删除角色
	 * 
	 * @param roleId 角色id
	 * @return
	 */
	Integer delRole(@Param("roleId") String roleId);

	/**
	 * 删除人员角色
	 * 
	 * @param roleId 角色id
	 * @return
	 */
	Integer delAdRole(@Param("roleId") String roleId);

	/**
	 * 删除菜单角色
	 * 
	 * @param roleId 角色id
	 * @return
	 */
	Integer delRoleMenu(@Param("roleId") String roleId);

	/**
	 * 得到全部菜单
	 * 
	 * @return
	 */
	ArrayList<Menu> showAllMenu();

	/**
	 * 增加角色
	 * 
	 * @param role
	 * @return
	 */
	Integer addRole(Role role);

	/**
	 * 增加角色菜单
	 * 
	 * @param roleId   角色id
	 * @param menuList 菜单集合
	 * @return
	 */
	Integer addRoleMenu(@Param("roleId") String roleId, @Param("list") ArrayList<String> list);

	/**
	 * 查询角色名字是否存在
	 * 
	 * @param roleName
	 * @return
	 */
	String queryRoleName(@Param("roleName") String roleName);

	/**
	 * 根据角色id查询角色信息
	 * 
	 * @param roleId
	 * @return
	 */
	Role getRole(@Param("roleId") String roleId);

	/**
	 * 得到这个角色有多少菜单
	 * 
	 * @param roleId
	 * @return
	 */
	ArrayList<String> getRoleMenu(@Param("roleId") String roleId);

	/**
	 * 修改角色名
	 * 
	 * @param roleId
	 * @param roleName
	 * @return
	 */
	Integer updateRoleName(@Param("roleId") String roleId, @Param("roleName") String roleName);

}
