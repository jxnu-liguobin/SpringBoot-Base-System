package cn.edu.jxnu.base.controller.admin;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.jxnu.base.controller.BaseController;

/**
 * Copyright©2018 梦境迷离.All rights reserved.
 * 
 * @description:登录控制类
 * @Package: cn.edu.jxnu.base.controller
 * @author: 梦境迷离
 * @date:2018年3月19日11:03:23
 */
@Controller
public class LoginController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = { "/admin/login" }, method = RequestMethod.GET)
	public String login() {

		return "admin/login";
	}

	@RequestMapping(value = { "/admin/login" }, method = RequestMethod.POST)
	public String login(@RequestParam("usercode") String usercode, @RequestParam("password") String password,
			ModelMap model) {
		try {

			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(usercode, password);
			subject.login(token);// 调用主体的login方法进行认证登录
			logger.info("password:" + password);
			return redirect("/admin/index");
		} catch (AuthenticationException e) {

			model.put("message", e.getMessage());// 认证登录失败就进入登录页面
		}
		return "admin/login";
	}

	@RequestMapping(value = { "/admin/logout" }, method = RequestMethod.GET)
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();// 调用主体的logout方法进行登出
		return redirect("admin/login");
	}

}
