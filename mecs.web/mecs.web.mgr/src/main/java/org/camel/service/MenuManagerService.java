package org.camel.service;

import org.apache.ibatis.session.RowBounds;

import com.alibaba.fastjson.JSONObject;

public interface MenuManagerService {
	/**
	 * 查询角色列表
	 * 
	 * @param roleName  角色名
	 * @param beginDate 开始时间
	 * @param endDate   结束时间
	 * @param rb        翻页
	 * @return
	 */
	JSONObject queryMenuTable(String menuName, String url, RowBounds rb);

	/**
	 * 删除菜单
	 * 
	 * @param menuId
	 * @return
	 */
	JSONObject delMenu(String doId,String menuId);

	/**
	 * 增加父类菜单
	 * 
	 * @param url
	 * @param iccon
	 * @param menuName
	 * @return
	 */
	JSONObject addMenu(String doId,String url, String iccon, String menuName, String pid);

	/**
	 * 初始化编辑界面
	 * 
	 * @param menuId
	 * @return
	 */
	JSONObject updateMenuPanel(String menuId);

	/**
	 * 修改菜单
	 * 
	 * @param menuId
	 * @param url
	 * @param menuName
	 * @param iccon
	 * @return
	 */

	JSONObject toUpdateMenu(String doId,String menuId, String url, String menuName, String iccon);
}
