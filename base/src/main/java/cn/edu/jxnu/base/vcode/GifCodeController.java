package cn.edu.jxnu.base.vcode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * Gif验证码请求处理
 * 
 * @author 梦境迷离
 * @time 2018年4月10日 下午5:53:59.
 * @version V1.0
 */
@Controller
@Slf4j
public class GifCodeController {
	/**
	 * 获取验证码（Gif版本）
	 * 
	 * @param response
	 */

	@RequestMapping("getGifCode")
	public void getGifCode(HttpServletResponse response, HttpServletRequest request) {
		try {

			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/gif");
			/**
			 * gif格式动画验证码 宽，高，位数。
			 */
			Captcha captcha = new GifCaptcha(130, 30, 4);
			// 输出
			captcha.out(response.getOutputStream());
			/**
			 * 第一次请求的时候没有session
			 */
			HttpSession session = request.getSession(true);
			// 存入shiro Session 10分钟，可以使用使用ehcache redis 来替代
			session.setAttribute("_code", captcha.text().toLowerCase());
		} catch (Exception e) {
			log.info("获取验证码异常：" + e.getMessage());
		}
	}
}