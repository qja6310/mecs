package org.camel.controller;

import org.camel.service.MecSynthesizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

/**
 * 体检综合查询

     * <p>Title : MecSynthesizeController</p>

     * <p>Description : </p>

     * <p>DevelopTools : Eclipse_x64_8.5</p>

     * <p>DevelopSystem : macOS Sierra 10.12.1</p>

     * <p>Company : org.camel</p>

     * @author : camel

     * @date : 2019年6月19日 下午10:22:54

     * @version : 0.0.1
 */
@Controller
public class MecSynthesizeController {

	@Autowired
	private MecSynthesizeService mecSynthesizeService;
	
	/**
	 * 有条件的获取体检综合信息
	 * @param nowPage
	 * @param sTime
	 * @param eTime
	 * @param userName
	 * @param userTel
	 * @param mecCode
	 * @return
	 */
	@RequestMapping("/getMecSynthesize.action")
	@ResponseBody
	public JSONObject getMecSynthesize(String nowPage,String sTime,String eTime,String userName,String userTel,String mecCode) {
		JSONObject obj = new JSONObject();
		obj = mecSynthesizeService.getMecSynthesize(nowPage, sTime, eTime, userName, userTel, mecCode);
		return obj;
	}
}
