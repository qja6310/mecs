package org.camel.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.camel.service.ItemsManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.Admin;

/**
 * 
 * 
 * 
 * <p>
 * Title : ItemsController.java
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
 * @date : 2019年6月18日 下午3:43:43
 * 
 * @version : 0.0.1
 */
@Controller
public class ItemsController {

	@Autowired
	private ItemsManagerService itemsManagerService;

	@RequestMapping("/itemsQuery.action")
	@ResponseBody
	public JSONObject itemsQuery(String nowPage, String itemsName, String depId) {

		JSONObject item = itemsManagerService.getItems(nowPage, itemsName, depId);

		return item;
	}

	@RequestMapping("/itemsDelete.action")
	@ResponseBody
	public JSONObject itemsDelete(String itemsId, HttpServletRequest request) {

		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("sessionAdminKey");
		String adminId = admin.getAdminId();
		JSONObject delItems = itemsManagerService.delItems(adminId, itemsId);

		return delItems;
	}

	@RequestMapping("/showItemsAdd.action")
	@ResponseBody
	public JSONObject showItemsAdd(String itemsId) {

		JSONObject showItemsAdd = itemsManagerService.showItemsAdd();

		return showItemsAdd;
	}

	/**
	 * 增加项目
	 * 
	 * @param req
	 * @param itemsName
	 * @param price
	 * @param depId
	 * @return
	 */
	@RequestMapping("/addItems.action")
	@ResponseBody
	public JSONObject addItems(HttpServletRequest req, String itemsName, String price, String depId,
			HttpServletRequest request) {
		JSONObject addItems = new JSONObject();
		String[] item = req.getParameterValues("cksVal[]");

		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("sessionAdminKey");
		String adminId = admin.getAdminId();

		if (item != null) { // 如果有选细项
			ArrayList<String> itemList = new ArrayList<>();
			for (int i = 0; i < item.length; i++) {
				itemList.add(item[i]);
			}
			addItems = itemsManagerService.addItems(adminId, depId, itemsName, price, itemList);
		} else {
			addItems = itemsManagerService.addItems(adminId, depId, itemsName, price, null);
		}
		return addItems;
	}

	/**
	 * 存项目id
	 * 
	 * @param req
	 * @param itemsId
	 * @return
	 */
	@RequestMapping("/toSetItemsId.action")
	@ResponseBody
	public JSONObject toSetItemsId(HttpServletRequest req, String itemsId) {
		JSONObject obj = new JSONObject();
		req.getSession().setAttribute("itemsId", itemsId);
		obj.put("res", "成功");
		return obj;
	}

	/**
	 * 初始化编辑界面
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/showItemsUpdate.action")
	@ResponseBody
	public JSONObject showItemsUpdate(HttpServletRequest req) {
		JSONObject obj = new JSONObject();
		String itemsId = (String) req.getSession().getAttribute("itemsId");
		obj = itemsManagerService.showItemsUpdate(itemsId);
		return obj;
	}
	/**
	 * 修改项目
	 * @param req
	 * @param itemsName
	 * @param price
	 * @param depId
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateItems.action")
	@ResponseBody
	public JSONObject updateItems(HttpServletRequest req, String itemsName, String price, String depId,
			HttpServletRequest request) {

		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("sessionAdminKey");
		String adminId = admin.getAdminId();

		JSONObject obj = new JSONObject();
		String itemsId = (String) req.getSession().getAttribute("itemsId");
		String[] item = req.getParameterValues("cksVal[]");
		if (item != null) { // 如果有选细项
			ArrayList<String> itemList = new ArrayList<>();
			for (int i = 0; i < item.length; i++) {
				itemList.add(item[i]);
			}
			obj = itemsManagerService.alterItems(adminId, itemsId, itemsName, price, depId, itemList);
		} else {
			obj = itemsManagerService.alterItems(adminId, itemsId, itemsName, price, depId, null);
		}
		return obj;
	}
		/**
		 * 查看项目
		 * @param req
		 * @return
		 */
	@RequestMapping("/lookItems.action")
	@ResponseBody
	public JSONObject lookItems(HttpServletRequest req) {
		String itemsId = (String) req.getSession().getAttribute("itemsId");
		JSONObject lookItems = itemsManagerService.lookItems(itemsId);

		return lookItems;
	}

}
