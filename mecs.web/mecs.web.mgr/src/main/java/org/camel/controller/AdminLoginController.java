package org.camel.controller;

import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.camel.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.Utils;
import com.alibaba.fastjson.JSONObject;

import mecs.camel.utils.IsNullUtil;
import mecs.camel.utils.Md5Util;
import mecs.camel.utils.RsaUtils;
@Controller
public class AdminLoginController {
	
	@Autowired
	private AdminLoginService loginService;
	/**
	 * 修改密码  
	 * @param oldPsd 旧密码
	 * @param newPsd 新密码
	 * @return 
	 * 
	 * 返回suc:表示修改成功
	 * oldErr:旧密码错误
	 * err：插入日志失败
	 */
	@RequestMapping("/changePsd.action")
	@ResponseBody
	public JSONObject changePsd(String oldPsd,String newPsd,HttpSession session,HttpServletResponse resp) {
		JSONObject obj=new JSONObject();
		//把旧密码和新密码都用md5加密
		String oldPsd2=Md5Util.encryption(oldPsd);
		String newPsd2=Md5Util.encryption(newPsd);
		String bo=loginService.changPsd(oldPsd2, newPsd2, session);
		//如果登录成功移除存在session里面的值
		 if("suc".equals(bo)) {
			 try {
		          //移除用户session,清明改
				 session.removeAttribute("sessionAdminKey");
				 
				 
		      }catch (Exception e){
		      } 
		 }
		
		obj.put("changPsdKey", bo);
		return obj;
	}

	/**
	 * 获得rsa加密的公钥和私钥    清明加
	 * @return
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 */
	@RequestMapping("/getPublicKey.action")
	@ResponseBody
	public JSONObject getPublic(HttpSession sess) throws NoSuchAlgorithmException, InvalidKeySpecException {
		JSONObject obj=new JSONObject();
		Map<String, Object> key = RsaUtils.getKeys();
		RSAPublicKey  publicKey = (RSAPublicKey) key.get("public");
		RSAPrivateKey privateKey = (RSAPrivateKey) key.get("private");
		sess.setAttribute("privateKey", privateKey);
		// 将公钥传到前端
		// 注意返回modulus和exponent以16为基数的BigInteger的字符串表示形式
		obj.put("modulus", publicKey.getModulus().toString(16));
		obj.put("exponent", publicKey.getPublicExponent().toString(16));
		return obj;

	}

	
	
	/**
      * 退出登录
   * @param sess 传入的参数为，界面的url
   * @return 返回的url为登录界面的url
   */
  @RequestMapping("/doLogout")
  public String doLogout(HttpSession sess){
      try {
          //移除用户session,清明改
          sess.removeAttribute("sessionAdminKey");
      }catch (Exception e){
      }
      return "redirect:view/adminlogin.html";
  }
	
  
  
    /**
	 * 登录，
	 * @param keyCode
	 * @param adminAcc
	 * @param adminPswd
	 * @param req
	 * @return 
	 * 如果返回codeErr：验证错误。
	 * haveNull：输入框有空，
	 * err：账号或者密码错误，
	 * suc：登录成功，
	 * forb：验证码错误
     * @throws Exception 
	 */
	@RequestMapping("/do-login.action")
	@ResponseBody
	public JSONObject adminLogin(String keyCode, String adminAcc, String adminPswd, HttpServletRequest req) throws Exception {
		//System.out.println("从界面传过来的值="+adminAcc+":"+adminPswd+":"+keyCode);
		/*
		 * 判断验证码的正确性 注意验证码的大写转换
		 */
		JSONObject obj = new JSONObject();
		RSAPrivateKey privateKey = (RSAPrivateKey) req.getSession().getAttribute("privateKey");
		//rsa解密后的密码 pawd
		String pawd = RsaUtils.decryptByPrivateKey(adminPswd, privateKey);
		//上面rsa解密之后才是人能看懂的密码
		//调用md5加密
		String psdMD5=Md5Util.encryption(pawd);
		
		//对前端传来的数据进行非空判断
		List<String> list = new ArrayList<String>();
		list.add(adminAcc);
		list.add(pawd);
		list.add(keyCode);
		//全部不为空的时候，返回true
		boolean notNull = IsNullUtil.isNull(list);
		notNull=true;
		//如果都不为空
		if(notNull) {
			//判断验证码对不对
			/*HttpServletRequest req=ServletActionContext.getRequest();*/
			//System.out.println("req="+req);
			
			HttpSession session=req.getSession();
			//将存入session里面的String取出转换成Admin对象
			 String codek = (String) session.getAttribute("sessionKeyCode");
//			  String codek = JSONObject.parseObject(codek1, String.class);
//			String codek=(String)session.getAttribute("sessionKeyCode");
			//System.out.println("获得之前存到session 验证码的值为："+codek);
			//或者转化成小写的和大写，主要看你存的时候是转化成大写还是小写了，没用到
			//codek.toLowerCase();
			//codek.toUpperCase();
			//不看大小的相等判断
			if(codek.equalsIgnoreCase(keyCode)) {
				//这里ssesion也传过去，方便存用户信息 到ssesion
				String st=loginService.queryUser(adminAcc,psdMD5,session);	
				obj.put("loginback",st);
			}else {
				obj.put("loginback","codeErr");
			}
		}else {
			obj.put("loginback","haveNull");
		}

		return obj;

	}

}
