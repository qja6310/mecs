package org.camel.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.RowBounds;
import org.camel.service.MenuManagerService;
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
 * Title : MenuManagerController
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
public class MenuManagerController {

	@Autowired
	private MenuManagerService menuManagerService;

	@RequestMapping("/queryMenu.action")
	@ResponseBody
	public JSONObject queryMenu(String currPage, String menuName, String url) {
		JSONObject obj = new JSONObject();
		if (currPage == null) {
			currPage = "1";
		}
		int limit = LimitUtil.MENULIMIT;
		obj.put("limit", limit);
		int begin = (Integer.parseInt(currPage) - 1) * limit;
		obj = menuManagerService.queryMenuTable(menuName, url, new RowBounds(begin, limit));
		return obj;
	}

	@RequestMapping("/delMenu.action")
	@ResponseBody
	public JSONObject delMenu(HttpServletRequest req, String menuId) {
		Admin admin = (Admin) req.getSession().getAttribute("sessionAdminKey");
		String doId = admin.getAdminId();
		JSONObject delMenu = menuManagerService.delMenu(doId, menuId);
		return delMenu;
	}

	/**
	 * 增加父类菜单
	 * 
	 * @param menu
	 * @param URL
	 * @param icon
	 * @return
	 */
	@RequestMapping("/addParentMenu.action")
	@ResponseBody
	public JSONObject addParentMenu(HttpServletRequest req, String menu, String icon) {
		Admin admin = (Admin) req.getSession().getAttribute("sessionAdminKey");
		String doId = admin.getAdminId();
		JSONObject addParentMenu = menuManagerService.addMenu(doId, "-", icon, menu, "0");
		return addParentMenu;
	}

	/**
	 * 存菜单id
	 * 
	 * @param req
	 * @param menuId
	 * @return
	 */
	@RequestMapping("/toSetMenuId.action")
	@ResponseBody
	public JSONObject toSetMenuId(HttpServletRequest req, String menuId) {
		JSONObject obj = new JSONObject();
		req.getSession().setAttribute("MenuId", menuId);
		obj.put("res", "成功");
		return obj;
	}

	/**
	 * 增加子菜单
	 * 
	 * @param req
	 * @param menu
	 * @param URL
	 * @param icon
	 * @return
	 */
	@RequestMapping("/addSubMenu.action")
	@ResponseBody
	public JSONObject addSubMenu(HttpServletRequest req, String menu, String URL, String icon) {
		String pId = (String) req.getSession().getAttribute("MenuId");
		Admin admin = (Admin) req.getSession().getAttribute("sessionAdminKey");
		String doId = admin.getAdminId();
		JSONObject addParentMenu = menuManagerService.addMenu(doId, URL, icon, menu, pId);
		return addParentMenu;
	}

	/**
	 * 初始编辑界面
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/updateMenu.action")
	@ResponseBody
	public JSONObject updateMenu(HttpServletRequest req) {
		String pId = (String) req.getSession().getAttribute("MenuId");
		JSONObject updateMenuPanel = menuManagerService.updateMenuPanel(pId);

		return updateMenuPanel;
	}

	/**
	 * 修改菜单
	 * 
	 * @param req
	 * @param menu
	 * @param URL
	 * @param icon
	 * @return
	 */
	@RequestMapping("/toUpdateMenu.action")
	@ResponseBody
	public JSONObject toUpdateMenu(HttpServletRequest req, String menu, String URL, String icon) {
		String pId = (String) req.getSession().getAttribute("MenuId");
		Admin admin = (Admin) req.getSession().getAttribute("sessionAdminKey");
		String doId = admin.getAdminId();
		JSONObject updateMenu = menuManagerService.toUpdateMenu(doId, pId, URL, menu, icon);
		return updateMenu;
	}
}
