package org.camel.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.camel.service.ItemsReceiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.Admin;
import mecs.camel.utils.LimitUtil;

/**
 * 项目接收，小结
 * 
 * 
 * <p>
 * Title : ItemsRecordController.java
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
 * @date : 2019年6月18日 下午11:38:33
 * 
 * @version : 0.0.1
 */
@Controller
public class ItemsRecordController {
	String itemsRecordIdAs = "";
	String cardNumAs  = "";
	String itemsNameAs  = "";
	/**
	 * 根据卡号去获取体检人信息
	 * 
	 * @param cardNum
	 * @return
	 */
	@Autowired
	private ItemsReceiveService irs;

	@RequestMapping("/itemsReceice.action")
	@ResponseBody
	public JSONObject itemsReceive(HttpSession session, String currPage, String cardNum, String itemsName,
			String itemsState) {
		Admin admin = (Admin) session.getAttribute("sessionAdminKey");
		String admindepId = admin.getDepId();
		int limit = LimitUtil.ITEMSECORD;
		int beginPage = Integer.valueOf(currPage) * limit - limit;
		JSONObject jo = new JSONObject();
		jo = irs.itemsReceive(currPage,session,cardNum, admindepId, itemsName, itemsState, new RowBounds(beginPage, limit));
		return jo;
	}

	/**
	 * 接收项目
	 * 
	 * @param cardNum
	 * @return
	 */
	@RequestMapping("/changState.action")
	@ResponseBody
	public JSONObject changState(HttpSession session, String itemsRecordId, String cardNum, String itemsName) {
		Admin admin = (Admin) session.getAttribute("sessionAdminKey");
		String adminId = admin.getAdminId();
		String adminName = admin.getAdminName();
		JSONObject jo = new JSONObject();
		jo = irs.changeItemsReceive(adminName,adminId, itemsRecordId, cardNum, itemsName);
		return jo;
	}

	/**
	 * 上传图片
	 * 
	 * @return
	 */
	@RequestMapping("/image.action")
	@ResponseBody
	public String image(HttpServletRequest request, @Param("file") MultipartFile file) throws IOException {
		String oldFileName = file.getOriginalFilename();
		System.out.println("oldFileName = " + oldFileName);
		String rootPath ="D:\\mgr\\";
		// 将文件放于项目部署路径下的upload文件夹下
		File targetFile = new File(rootPath + oldFileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		file.transferTo(targetFile);
		// 完整的url
		String fileUrl = "\\uploads\\" + oldFileName;
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		map.put("code", 0);// 0表示成功，1失败
		map.put("msg", "上传成功");// 提示消息
		map.put("data", map2);
		map2.put("src", fileUrl);// 图片url
		map2.put("title", oldFileName);// 名称，这个会显示在输入框里
		String result = new JSONObject(map).toString();
		return result;
	}
	/**
	 * 录入小结
	 * @param session
	 * @param itemsRecordId
	 * @param result
	 * @param cardNum
	 * @param itemsName
	 * @return
	 */
	@RequestMapping("/smallResult.action")
	@ResponseBody
	public JSONObject smallResult(HttpSession session,String result) {
		Admin admin = (Admin) session.getAttribute("sessionAdminKey");
		String adminId = admin.getAdminId();
		String adminName = admin.getAdminName();
		JSONObject jo = new JSONObject();
		  jo = irs.addResult(adminName,itemsRecordIdAs, result, adminId, cardNumAs, itemsNameAs);
		return jo;
	}
	/**
	 * 录入小结传递项目记录id，卡号，项目名
	 * @param itemsRecordId
	 * @param cardNum
	 * @param itemsName
	 * @return
	 */
	@RequestMapping("/sendParam.action")
	@ResponseBody
	public JSONObject sendParam(String itemsRecordId, String cardNum, String itemsName) {
		itemsRecordIdAs = itemsRecordId;
		cardNumAs = cardNum;
		itemsNameAs = itemsName;
		JSONObject jo = new JSONObject();
		return jo;
	}
	
}
