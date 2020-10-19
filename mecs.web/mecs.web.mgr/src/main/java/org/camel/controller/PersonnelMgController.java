package org.camel.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.RowBounds;
import org.camel.service.PersonnelMgService;
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
 * Title : PersonnelMgController
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
public class PersonnelMgController {

	@Autowired
	private PersonnelMgService personnelMgService;

	/**
	 * 查询后台人员列表
	 * 
	 * @param currPage  页码数
	 * @param adName    姓名
	 * @param adAcc     账号
	 * @param adDepId   科室id
	 * @param beginDate 开始时间
	 * @param endDate   结束时间
	 * @return
	 */
	@RequestMapping("/queryAdmin.action")
	@ResponseBody
	public JSONObject queryAdmin(String currPage, String adName, String adAcc, String adDepId, String state,
			String beginDate, String endDate) {
		JSONObject obj = new JSONObject();
		if (currPage == null) {
			currPage = "1";
		}
		int limit = LimitUtil.ADMINLIMIT;
		obj.put("limit", limit);
		int begin = (Integer.parseInt(currPage) - 1) * limit;
		obj = personnelMgService.queryAdminTable(adName, adAcc, adDepId, state, beginDate, endDate,
				new RowBounds(begin, limit));

		return obj;
	}

	/**
	 * 增加用户
	 * 
	 * @param adName  姓名
	 * @param adAcc   账号
	 * @param adPswd  密码
	 * @param adDepId 科室id
	 * @param state   状态
	 * @param roleId  角色id
	 * @return
	 */
	@RequestMapping("/addAdmin.action")
	@ResponseBody
	public JSONObject addAdmin(HttpSession session,HttpServletRequest req, String adName, String adAcc, String adPswd, String adDepId) {
		JSONObject addAdmin= new JSONObject();
		Admin admin = (Admin) session.getAttribute("sessionAdminKey");
		String doId = admin.getAdminId();
		String[] role = req.getParameterValues("cksVal[]");
	
		if(role!=null) { //如果有选角色
			ArrayList<String> roleList = new ArrayList<>();
			for (int i = 0; i < role.length; i++) {
				roleList.add(role[i]);
			}
			 addAdmin = personnelMgService.addAdmin(doId,adName, adAcc, adPswd, adDepId, roleList);
		}else {
			addAdmin = personnelMgService.addAdmin(doId,adName, adAcc, adPswd, adDepId, null);
		}
	
		return addAdmin;
	}

	/**
	 * 删除用户
	 * 
	 * @param adId 用户id
	 * @return
	 */
	@RequestMapping("/delAdmin.action")
	@ResponseBody
	public JSONObject delAdmin(HttpSession session,String adId) {
		Admin admin = (Admin) session.getAttribute("sessionAdminKey");
		String doId = admin.getAdminId();
		JSONObject delAdmin = personnelMgService.delAdmin(doId,adId);
		return delAdmin;
	}

	/**
	 * 初始化人员下拉框
	 * 
	 * @return
	 */
	@RequestMapping("/initAdmSelect.action")
	@ResponseBody
	public JSONObject initAdmSelect() {
		JSONObject initAdmSelect = personnelMgService.initAdmSelect();

		return initAdmSelect;
	}

	/**
	 * 初始化增加人员界面
	 * 
	 * @return
	 */
	@RequestMapping("/initAddAdmSelect.action")
	@ResponseBody
	public JSONObject initAddAdmSelect() {

		return personnelMgService.initAddAdmSelect();
	}

	/**
	 * 修改人员状态
	 * 
	 * @return
	 */
	@RequestMapping("/toAdminState.action")
	@ResponseBody
	public JSONObject toAdminState(HttpSession session,String adId, String state) {
		Admin admin = (Admin) session.getAttribute("sessionAdminKey");
		String doId = admin.getAdminId();
		return personnelMgService.toAdminState(doId,adId, state);
	}

	/**
	 * 重置人员密码
	 * 
	 * @return
	 */
	@RequestMapping("/rePswd.action")
	@ResponseBody
	public JSONObject rePswd(HttpSession session,String adId) {
		Admin admin = (Admin) session.getAttribute("sessionAdminKey");
		String doId = admin.getAdminId();
		return personnelMgService.rePswd(doId,adId);
	}

	/**
	 * 人员id存入后端
	 * 
	 * @param adId
	 * @return
	 */
	@RequestMapping("/toSetAdminId.action")
	@ResponseBody
	public JSONObject toSetAdminId(HttpServletRequest req, String adId) {
		req.getSession().setAttribute("adminId", adId);
		JSONObject obj = new JSONObject();
		obj.put("res", "成功");

		return obj;
	}

	/**
	 * 展示人员界面
	 * 
	 * @return
	 */
	@RequestMapping("/showAdminUpdate.action")
	@ResponseBody
	public JSONObject showAdminUpdate(HttpServletRequest req) {

		String adminid = (String) req.getSession().getAttribute("adminId");
		JSONObject showUpdatePanel = personnelMgService.showUpdatePanel(adminid);
		return showUpdatePanel;
	}

	/**
	 * 修改人员信息
	 * 
	 * @return
	 */
	@RequestMapping("/toUpdateAdmin.action")
	@ResponseBody
	public JSONObject toUpdateAdmin(HttpSession session,HttpServletRequest req, String adName, String adAcc, String adDepId, String adId) {
		String[] role = req.getParameterValues("cksVal[]");
		Admin admin = (Admin) session.getAttribute("sessionAdminKey");
		String doId = admin.getAdminId();
		JSONObject updateAdmin=new JSONObject();
		if(role!=null) {
			ArrayList<String> roleList = new ArrayList<>();
			for (int i = 0; i < role.length; i++) {
				roleList.add(role[i]);
				 updateAdmin = personnelMgService.toUpdateAdmin(doId,adName, adAcc, adDepId, adId, roleList);

			}
		}else {
			updateAdmin= personnelMgService.toUpdateAdmin(doId,adName, adAcc, adDepId, adId, null);
		}
		
		
		return updateAdmin;
	}

}
