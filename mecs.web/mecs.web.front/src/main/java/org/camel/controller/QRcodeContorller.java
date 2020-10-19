package org.camel.controller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import mecs.camel.utils.QRCodeUtil;

/**
 * 生成二维码

     * <p>Title : QRcodeContorller</p>

     * <p>Description : </p>

     * <p>DevelopTools : Eclipse_x64_8.5</p>

     * <p>DevelopSystem : macOS Sierra 10.12.1</p>

     * <p>Company : org.camel</p>

     * @author : camel

     * @date : 2019年6月18日 上午9:29:47

     * @version : 0.0.1
 */
@Controller
public class QRcodeContorller {

	@RequestMapping("/getQRcode.action")
	public void getQRCode(HttpServletRequest req,HttpServletResponse response, String mrNum) throws Exception {
		/*
		 * 利用工具包生成一个二维码,将图片存入到工程目录下的QRcode文件夹中
		 * 以体检记录ID为二维码的生成条件及命名
		 */		
		BufferedImage QRcode = QRCodeUtil.createImage(mrNum,null,true);
		ImageIO.write(QRcode, "jpg", response.getOutputStream()); // 将图片验证码输出
	}
	
}
