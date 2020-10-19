package org.camel.controller;

import javax.servlet.http.HttpServletRequest;

import org.camel.service.MecReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

/**
 * 体检报告打印

     * <p>Title : MecReportController</p>

     * <p>Description : </p>

     * <p>DevelopTools : Eclipse_x64_8.5</p>

     * <p>DevelopSystem : macOS Sierra 10.12.1</p>

     * <p>Company : org.camel</p>

     * @author : camel

     * @date : 2019年6月20日 下午2:42:18

     * @version : 0.0.1
 */
@Controller
public class MecReportController {

	@Autowired
	private MecReportService mecReportService;
	
	@RequestMapping("/getMecRecord.action")
	@ResponseBody
	public JSONObject getMecRecord(String cardNum,String mecCode,HttpServletRequest req) {
		JSONObject obj = new JSONObject();
		
		//将存入session里面的String取出转换成Admin对象
		  String cardN = (String) req.getSession().getAttribute("cardNum");
		if(cardNum != null && !"".equals(cardNum)) {
			if(!(cardN.equals(cardNum))) {
				obj.put("res", "cardNumErr");//输入的卡号和登录的卡号不一致
				return obj;
			}
		}
		if(cardN != null && !"".equals(cardN)) {
			obj = mecReportService.getMecRecord(cardN,mecCode);
		}else {
			obj.put("res", "isNull");
		}
		return obj;
	}
}
