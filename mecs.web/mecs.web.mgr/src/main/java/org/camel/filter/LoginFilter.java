package org.camel.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.Admin;
public class LoginFilter  implements Filter{
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		Filter.super.destroy();
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		String url = request.getRequestURI();
		//将存入session里面的String取出转换成Admin对象
		Admin user = (Admin) request.getSession().getAttribute("sessionAdminKey");
//		  Admin user = JSONObject.parseObject(admin, Admin.class);
//		Admin user = (Admin) request.getSession().getAttribute("sessionAdminKey");
		if(user != null) {
			chain.doFilter(request, response);
			return;
		}else if(url.contains("getKeyCode")||url.contains("login")) {
			chain.doFilter(request, response);
			return;
		}else {
			response.sendRedirect("/mecs.web.mgr/view/adminlogin.html");
			return ;
		}
		
	}

}
