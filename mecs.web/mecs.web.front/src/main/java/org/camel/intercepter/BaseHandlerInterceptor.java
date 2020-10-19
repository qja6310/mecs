package org.camel.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>Title			: BaseHandlerInterceptor</p>
 * <p>Description	: 系统最高权限拦截器</p>
 * <p>DevelopTools	: Eclipse_x64_v4.6.2</p>
 * <p>DevelopSystem	: Windows10</p>
 * <p>Company		: org.xujun</p>
 * @author			: XuJun
 * @date			: 2016年11月23日 上午9:55:39
 * @version	 		: 6.0.0
 */
public class BaseHandlerInterceptor implements HandlerInterceptor {
	
	/**
	 * 进入Handler方法之前执行
	 * @param request 	HttpServletRequest
	 * @param response 	HttpServletResponse
	 * @param handler 	handler
	 * @return true(放行) or false(拦截)
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("before");
		return true;
	}

	/**
	 * 进入Handler方法之后，返回modelAndView之前执行(可以往MV里面填充公用的ModelAndView)
	 * @param request 	HttpServletRequest
	 * @param response 	HttpServletResponse
	 * @param handler 	handler
	 * @param mv 		ModelAndView
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) throws Exception {
		System.out.println("mv 执行");
	}

	/**
	 * 执行Handler完成后执行(统一异常处理，统一日志处理)
	 * @param request 	HttpServletRequest
	 * @param response 	HttpServletResponse
	 * @param handler 	handler
	 * @param e 		异常信息
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {
		System.out.println("after");
	}

}
