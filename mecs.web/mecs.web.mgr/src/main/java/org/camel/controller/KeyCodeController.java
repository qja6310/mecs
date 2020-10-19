package org.camel.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

import mecs.camel.utils.ImgCodeUtil;


/**
 * 

     * <p>Title : KeyCodeController</p>

     * <p>Description : </p>

     * <p>DevelopTools : Eclipse_x64_8.5</p>

     * <p>DevelopSystem : macOS Sierra 10.12.1</p>

     * <p>Company : org.camel</p>

     * @author : camel

     * @date : 2019年6月12日 下午10:59:21

     * @version : 0.0.1
 */
@Controller
public class KeyCodeController {

	@RequestMapping("/getKeyCode.action")
	public void getKeyCode(HttpServletRequest req,HttpServletResponse response, String time) throws IOException {
		String code = ImgCodeUtil.getFourCode();
		//把验证码存入session
		// 用户存入session
//		   String codeString = JSON.toJSONString(code);
		   req.getSession().setAttribute("sessionKeyCode", code);
//		req.getSession().setAttribute("sessionKeyCode", code);
		BufferedImage newImgCode = ImgCodeUtil.newImgCode(110, 30, code);
		ImageIO.write(newImgCode, "jpg", response.getOutputStream()); // 将图片验证码输出
	}
	
}
