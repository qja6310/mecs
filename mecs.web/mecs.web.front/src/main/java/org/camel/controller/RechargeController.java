package org.camel.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.camel.alipay.AlipayConfig;
import org.camel.service.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

/**
 * 充值

     * <p>Title : RechargeController</p>

     * <p>Description : </p>

     * <p>DevelopTools : Eclipse_x64_8.5</p>

     * <p>DevelopSystem : macOS Sierra 10.12.1</p>

     * <p>Company : org.camel</p>

     * @author : camel

     * @date : 2019年6月25日 下午1:58:53

     * @version : 0.0.1
 */
@Controller
public class RechargeController {

	@Autowired
	private RechargeService rechargeService;
	
	/*
	 * 获取卡号回填
	 */
	@RequestMapping("/getCardNumForRecharge.action")
	@ResponseBody
	public JSONObject getCardNumForRecharge(HttpServletRequest req) {
		JSONObject obj = new JSONObject();
		String cardNum= (String) req.getSession().getAttribute("cardNum");
		obj.put("cardNum", cardNum);
		/*
		 * 获取卡号余额
		 */
		String balance = rechargeService.getBalanceByCardNum(cardNum);
		obj.put("balance", balance);
		/*
		 * 获取商户订单号
		 */
		String aoNum = rechargeService.getAccountOrderNum();
		req.getSession().setAttribute("aoNum", aoNum);
		obj.put("aoNum", aoNum);
		return obj;
	}	
	
	/*
	 * 充值业务跳转转账jsp
	 */
	@RequestMapping("/recharge.action")
	public void recharge(HttpServletRequest req,String rechrgeMoney, HttpServletResponse response) throws Exception {
		req.getSession().setAttribute("rechrgeMoney", rechrgeMoney);
		/*
		 * 付款金额
		 */
		String aoNum = (String) req.getSession().getAttribute("aoNum");
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		
		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
			
		//商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = aoNum;
		//付款金额，必填
		String total_amount = rechrgeMoney;
		//订单名称，必填
		String subject = "自助充值";
		//商品描述，可空
		String body = "";
		
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
				+ "\"total_amount\":\""+ total_amount +"\"," 
				+ "\"subject\":\""+ subject +"\"," 
				+ "\"body\":\""+ body +"\"," 
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		
		//若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
		//alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
		//		+ "\"total_amount\":\""+ total_amount +"\"," 
		//		+ "\"subject\":\""+ subject +"\"," 
		//		+ "\"body\":\""+ body +"\"," 
		//		+ "\"timeout_express\":\"10m\"," 
		//		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		//请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节
		
		//请求
		String result = alipayClient.pageExecute(alipayRequest).getBody();
		
		//输出
//		out.println(result);
		
		response.setCharacterEncoding("UTF-8");//设置将字符以"UTF-8"编码输出到客户端浏览器
		//通过设置响应头控制浏览器以UTF-8的编码显示数据
		response.setHeader("content-type", "text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(result);
	}
	
	/*
	 * 充值完成
	 */
	@RequestMapping("/finishRecharge.action")
	public void finishRecharge(HttpServletRequest req,HttpServletResponse resp) throws IOException {
//		ModelAndView mv = new ModelAndView();
		String cardNum= (String) req.getSession().getAttribute("cardNum");
		String aoNum= (String) req.getSession().getAttribute("aoNum");
		String rechrgeMoney= (String) req.getSession().getAttribute("rechrgeMoney");
		String finishRecharge = rechargeService.finishRecharge(cardNum, aoNum, rechrgeMoney);
		if("suc".equals(finishRecharge)) {
//			mv.setViewName("recharge");
			resp.sendRedirect("view/healthCheckup.html?res=" + finishRecharge);
		}
	}
}
