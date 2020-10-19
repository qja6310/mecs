package org.camel.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.camel.service.PrintMecReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.Admin;
import mecs.camel.model.ItemsRecord;
import mecs.camel.model.MecRecord;
import mecs.camel.model.User;
import mecs.camel.utils.IsNullUtil;

/**
 * 体检报告信息打印获取

     * <p>Title : PrintMecReportController</p>

     * <p>Description : </p>

     * <p>DevelopTools : Eclipse_x64_8.5</p>

     * <p>DevelopSystem : macOS Sierra 10.12.1</p>

     * <p>Company : org.camel</p>

     * @author : camel

     * @date : 2019年6月21日 上午10:18:52

     * @version : 0.0.1
 */
@Controller
public class PrintMecReportController {

	@Autowired
	private PrintMecReportService printMecReportService;
	
	@RequestMapping("/getPrintMecReportInfo.action")
	@ResponseBody
	public JSONObject getPrintMecReport(String mrNum,HttpServletRequest req) {
		JSONObject obj = new JSONObject();
		List<String> isNullList = new ArrayList<String>();
		isNullList.add(mrNum);
		boolean notNull = IsNullUtil.isNull(isNullList);
		if(notNull) {
			obj = printMecReportService.getPrintMecReport(mrNum);
		}else {
			obj.put("res", "isNull");
		}
		return obj;
	}
	
	@RequestMapping("/printMecReport.action")
	public ModelAndView getPrintMecReportInfo(String mrNum,HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		JSONObject obj = new JSONObject();
		List<String> isNullList = new ArrayList<String>();
		isNullList.add(mrNum);
		boolean notNull = IsNullUtil.isNull(isNullList);
		//将存入session里面的String取出转换成Admin对象
		  String cardN = (String) req.getSession().getAttribute("cardNum");
		if(notNull) {
			obj = printMecReportService.getPrintMecReportInfo(mrNum,cardN);
			User u = (User) obj.get("user");
			MecRecord mecRecord = (MecRecord) obj.get("mecRecord");
			List<ItemsRecord> itemsList = (List<ItemsRecord>) obj.get("itemsList");
			mv.addObject("mrNum", mrNum);
			mv.addObject("user", u);
			mv.addObject("mecRecord", mecRecord);
			mv.addObject("itemsList", itemsList);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			mv.addObject("printTime", df.format(new Date()));
			mv.setViewName("printMecReport");
		}else {
			obj.put("res", "isNull");
		}
		return mv;
	}
	
}
