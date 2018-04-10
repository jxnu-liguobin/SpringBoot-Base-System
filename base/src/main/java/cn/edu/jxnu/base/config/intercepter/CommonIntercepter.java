package cn.edu.jxnu.base.config.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright©2018 梦境迷离.All rights reserved.
 * 
 * @description:公共拦截器
 * @Package: cn.edu.jxnu.base.config.intercepter
 * @author: 梦境迷离
 * @date:2018年3月19日10:54:06
 */
@Component
public class CommonIntercepter implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return true;
	}

	/**
	 * 注册全局变量->应用路径
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		request.setAttribute("ctx", request.getContextPath());
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
