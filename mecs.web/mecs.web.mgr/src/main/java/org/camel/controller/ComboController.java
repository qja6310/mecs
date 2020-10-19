package org.camel.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.camel.service.ComboManagerService;
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
 * Title : ComboController.java
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
 * @date : 2019年6月20日 下午2:37:11
 * 
 * @version : 0.0.1
 */
@Controller
public class ComboController {

	@Autowired
	private ComboManagerService comboManagerService;

	@RequestMapping("/comboQuery.action")
	@ResponseBody
	public JSONObject comboQuery(String nowPage, String comboName) {

		JSONObject combo = comboManagerService.getCombo(nowPage, comboName);

		return combo;
	}

	@RequestMapping("/comboDel.action")
	@ResponseBody
	public JSONObject comboDel(HttpServletRequest req, String ComboId) {
		Admin admin = (Admin) req.getSession().getAttribute("sessionAdminKey");
		String doId = admin.getAdminId();
		JSONObject delCombo = comboManagerService.delCombo(doId, ComboId);

		return delCombo;
	}

	@RequestMapping("/getAllCombo.action")
	@ResponseBody
	public JSONObject getAllCombo() {
		JSONObject allCombo = comboManagerService.getAllCombo();
		return allCombo;
	}

	@RequestMapping("/alterComboState.action")
	@ResponseBody
	public JSONObject alterComboState(HttpServletRequest req, String comboId, String comboState) {
		Admin admin = (Admin) req.getSession().getAttribute("sessionAdminKey");
		String doId = admin.getAdminId();
		JSONObject alterComboState = comboManagerService.alterComboState(doId, comboId, comboState);
		return alterComboState;
	}

	@RequestMapping("/addCombo.action")
	@ResponseBody
	public JSONObject addCombo(HttpServletRequest req, String comboName, String price) {
		String[] items = req.getParameterValues("cksVal[]");
		Admin admin = (Admin) req.getSession().getAttribute("sessionAdminKey");
		String doId = admin.getAdminId();
		JSONObject obj = new JSONObject();
		if (items != null) {
			ArrayList<String> itemsList = new ArrayList<>();
			for (int i = 0; i < items.length; i++) {
				itemsList.add(items[i]);
			}

			obj = comboManagerService.addCombo(doId, comboName, price, itemsList);
		} else {
			obj = comboManagerService.addCombo(doId, comboName, price, null);
		}
		return obj;
	}

	/**
	 * 修改界面初始化
	 * 
	 * @param req
	 * @param comboName
	 * @param price
	 * @return
	 */
	@RequestMapping("/showUpdateComboPanel.action")
	@ResponseBody
	public JSONObject showUpdateComboPanel(String comboId) {
		JSONObject showUpdateComboPanel = comboManagerService.showUpdateComboPanel(comboId);
		return showUpdateComboPanel;
	}

	@RequestMapping("/toUpdateCombo.action")
	@ResponseBody
	public JSONObject toUpdateCombo(String comboId, HttpServletRequest req, String comboName, String price) {
		if (price == null) {
			price = "0";
		}
		String[] items = req.getParameterValues("cksVal[]");
		Admin admin = (Admin) req.getSession().getAttribute("sessionAdminKey");
		String doId = admin.getAdminId();
		JSONObject obj = new JSONObject();
		if (items != null) {
			ArrayList<String> itemsList = new ArrayList<>();
			for (int i = 0; i < items.length; i++) {
				itemsList.add(items[i]);
			}
			obj = comboManagerService.toUpdateCombo(doId, comboId, comboName, price, itemsList);
		} else {
			obj = comboManagerService.toUpdateCombo(doId, comboId, comboName, price, null);
		}

		return obj;
	}
	/**]
	 * 查看套餐
	 * @param comboId
	 * @return
	 */
	@RequestMapping("/lookCombo.action")
	@ResponseBody
	public JSONObject lookCombo(String comboId) {
		JSONObject lookCombo = comboManagerService.lookCombo(comboId);
		return lookCombo;
	}
}
