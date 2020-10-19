package org.camel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import mecs.camel.model.Menu;
import mecs.camel.model.Role;

public interface MenuManagerMapper {
	/**
	 * 查询角色列表
	 * 
	 * @param roleName  角色名
	 * @param beginDate 开始时间
	 * @param endDate   结束时间
	 * @return
	 */
	List<Role> queryMenuTable(@Param("menuName") String menuName, @Param("url") String url, RowBounds rb);

	/**
	 * 求角色列表总数
	 * 
	 * @param roleName  角色名
	 * @param beginDate 开始时间
	 * @param endDate   结束时间
	 * @return
	 */
	Integer getMenuCount(@Param("menuName") String menuName, @Param("url") String url);

	/**
	 * 删除菜单
	 * 
	 * @param menuId
	 * @return
	 */
	Integer delMenu(@Param("menuId") String menuId);

	/**
	 * 删除对应角色菜单
	 * 
	 * @param menuId
	 * @return
	 */
	Integer delMenuRole(@Param("menuId") String menuId);

	/**
	 * 新增菜单
	 * 
	 * @param menu
	 * @return
	 */
	Integer addMenu(Menu menu);

	/**
	 * 根据菜单id获得菜单信息
	 * 
	 * @param menuId
	 * @return
	 */
	Menu getMenu(@Param("menuId") String menuId);

	/**
	 * 修改菜单
	 * 
	 * @param menuId
	 * @param menuName
	 * @param menuUrl
	 * @return
	 */
	Integer updateMenu(@Param("menuId") String menuId, @Param("menuName") String menuName,
			@Param("menuUrl") String menuUrl, @Param("iccon") String iccon);

	/**
	 * 查询菜单名字是否存在
	 * 
	 * @param menuName
	 * @return
	 */
	String queryMenuName(@Param("menuName") String menuName);
	
	/**
	 * 根据父类id删除子菜单
	 * @param pid
	 * @return
	 */
	Integer dellMenuByPid(@Param("pid") String pid);

}
