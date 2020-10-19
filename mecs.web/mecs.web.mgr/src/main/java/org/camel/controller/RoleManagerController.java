package org.camel.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.RowBounds;
import org.camel.service.RoleManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.Admin;
import mecs.camel.utils.LimitUtil;

/**
 * 
 * 
 * <p>
 * Title : RoleManagerController
 * </p>
 * 
 * <p>
 * Description :
 * </p>
 * 
 * <p>
 * DevelopTools : Eclipse_x64_8.5
 * </p>
 * 
 * <p>
 * DevelopSystem : macOS Sierra 10.12.1
 * </p>
 * 
 * <p>
 * Company : org.camel
 * </p>
 * 
 * @author : camel
 * 
 * @date : 2019年6月12日 下午10:59:21
 * 
 * @version : 0.0.1
 */
@Controller
public class RoleManagerController {

	@Autowired
	private RoleManagerService roleManagerService;

	@RequestMapping("/queryRole.action")
	@ResponseBody
	public JSONObject queryRole(String currPage, String roleName, String beginDate, String endDate) {
		JSONObject obj = new JSONObject();
		if (currPage == null) {
			currPage = "1";
		}
		int limit = LimitUtil.ROLELIMIT;
		obj.put("limit", limit);
		int begin = (Integer.parseInt(currPage) - 1) * limit;
		obj = roleManagerService.queryRoleTable(roleName, beginDate, endDate, new RowBounds(begin, limit));
		return obj;
	}

	/**
	 * 
	 * 删除角色
	 * 
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/delRole.action")
	@ResponseBody
	public JSONObject delRole(HttpSession session, String roleId) {
		Admin admin = (Admin) session.getAttribute("sessionAdminKey");
		String doId = admin.getAdminId();
		JSONObject delRole = roleManagerService.delRole(doId, roleId);
		return delRole;
	}

	/**
	 * 初始化所有菜单
	 * 
	 * @return
	 */
	@RequestMapping("/showAllMenu.action")
	@ResponseBody
	public JSONObject showAllMenu() {
		JSONObject showAllMenu = roleManagerService.showAllMenu();
		return showAllMenu;
	}

	/**
	 * 增加角色以及对应菜单
	 * 
	 * @param req
	 * @param role
	 * @return
	 */
	@RequestMapping("/addRoleMenu.action")
	@ResponseBody
	public JSONObject addRoleMenu(HttpServletRequest req, String role) {
		Admin admin = (Admin) req.getSession().getAttribute("sessionAdminKey");
		String doId = admin.getAdminId();
		JSONObject addRoleMenu = new JSONObject();
		String[] menu = req.getParameterValues("cksVal[]");

		if (menu != null) {
			ArrayList<String> menuList = new ArrayList<>();
			for (int i = 0; i < menu.length; i++) {
				menuList.add(menu[i]);
			}
			addRoleMenu = roleManagerService.addRoleMenu(doId, role, menuList);
		} else {
			addRoleMenu = roleManagerService.addRoleMenu(doId, role, null);
		}

		return addRoleMenu;
	}

	/**
	 * 存角色id
	 * 
	 * @param req
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/toSetRoleId.action")
	@ResponseBody
	public JSONObject toSetRoleId(HttpServletRequest req, String roleId) {
		req.getSession().setAttribute("roleId", roleId);
		JSONObject obj = new JSONObject();
		obj.put("res", "成功");

		return obj;
	}

	/**
	 * 初始化修改界面
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/showRoleUpdate.action")
	@ResponseBody
	public JSONObject showRoleUpdate(HttpServletRequest req) {
		String roleId = (String) req.getSession().getAttribute("roleId");
		JSONObject showUpdatePanel = roleManagerService.showUpdatePanel(roleId);
		return showUpdatePanel;
	}

	/**
	 * 修改角色
	 * 
	 * @param req
	 * @param roleId
	 * @param roleName
	 * @return
	 */
	@RequestMapping("/toUpdateRole.action")
	@ResponseBody
	public JSONObject toUpdateRole(HttpServletRequest req, String roleId, String roleName) {
		JSONObject updateRoleMenu = new JSONObject();
		//将存入session里面的String取出转换成Admin对象
		Admin admin = (Admin) req.getSession().getAttribute("sessionAdminKey");
//		  Admin admin = JSONObject.parseObject(ad, Admin.class);
//		Admin admin = (Admin) req.getSession().getAttribute("sessionAdminKey");
		String doId = admin.getAdminId();
		String[] menu = req.getParameterValues("cksVal[]");
		if (menu != null) {
			ArrayList<String> menuList = new ArrayList<>();
			for (int i = 0; i < menu.length; i++) {
				menuList.add(menu[i]);
			}
			updateRoleMenu = roleManagerService.toUpdateRoleMenu(doId, roleId, roleName, menuList);

		} else {
			updateRoleMenu = roleManagerService.toUpdateRoleMenu(doId, roleId, roleName, null);
		}

		return updateRoleMenu;
	}

}
