package org.camel.controller;
import javax.servlet.http.HttpSession;

import org.camel.service.MecSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.Admin;
/**
 * 体检总结
 * 

     * <p>Title : MecSummaryController.java</p>

     * <p>Description : </p>

     * <p>DevelopTools : Eclipse_x64_8.5</p>

     * <p>DevelopSystem : macOS Sierra 10.12.1</p>

     * <p>Company : org.camel</p>

     * @author : camel

     * @date : 2019年6月20日 下午6:39:04

     * @version : 0.0.1
 */
@Controller
public class MecSummaryController {
	/**
	 * 获取 用户，项目记录信息
	 */
	@Autowired
	private MecSummaryService bs;
	@RequestMapping("/getInfo.action")
	@ResponseBody
	public  JSONObject getInfoByMecNum(String mecNum) {
		JSONObject jo = new JSONObject();
		jo = bs.getAllInfo(mecNum);
		return jo;
	}
	/**
	 * 通过项目记录id拿到体检小结
	 * @param irId
	 * @return
	 */
	@RequestMapping("/getResult.action")
	@ResponseBody
	public  JSONObject getResult(String irId) {
		JSONObject jo = new JSONObject();
		jo = bs.getResult(irId);
		return jo;
	}
	/**
	 * 通过 体检号码 插入 体检总结
	 * @param irId
	 * @return
	 */
	@RequestMapping("/summaryLast.action")
	@ResponseBody
	public  JSONObject summaryLast(HttpSession session,String mecNum ,String req,String guide ) {
		Admin ad=(Admin) session.getAttribute("sessionAdminKey");
		String docId = ad.getAdminId();
		String docName = ad.getAdminName();
		JSONObject ob = new JSONObject();
		ob = bs.addResult(docName,docId, mecNum, req, guide);
		return ob;
	}

}
