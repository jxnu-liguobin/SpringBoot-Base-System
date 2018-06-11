package cn.edu.jxnu.base.controller.admin.system;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 梦境迷离.
 * @time 2018年6月11日
 * @version v1.0
 */
@Slf4j
@Controller
public class Vcodecontroller {

	@ResponseBody
	@RequestMapping(value = "/isTrue")
	public boolean isTrue(@RequestParam("vcode") String vcode) {
		boolean result = false;
		log.info("前台验证码:" + vcode);
		if (vcode == null || vcode == "") {
			return result;

		}
		Session session = SecurityUtils.getSubject().getSession();
		// 转化成小写字母
		vcode = vcode.toLowerCase();
		String v = (String) session.getAttribute("_code");
		// 还可以读取一次后把验证码清空，这样每次登录都必须获取验证码
		session.removeAttribute("_code");
		if (!vcode.equals(v)) {
			return result;
		}
		result = true;
		return result;

	}
}
