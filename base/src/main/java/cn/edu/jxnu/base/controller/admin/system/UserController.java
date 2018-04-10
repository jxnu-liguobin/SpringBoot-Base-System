package cn.edu.jxnu.base.controller.admin.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.jxnu.base.common.JsonResult;
import cn.edu.jxnu.base.config.shiro.MyRealm;
import cn.edu.jxnu.base.controller.BaseController;
import cn.edu.jxnu.base.entity.Role;
import cn.edu.jxnu.base.entity.User;
import cn.edu.jxnu.base.service.IRoleService;
import cn.edu.jxnu.base.service.IUserService;
import cn.edu.jxnu.base.service.specification.SimpleSpecificationBuilder;
import cn.edu.jxnu.base.service.specification.SpecificationOperator.Operator;

/**
 * Copyright©2018 梦境迷离.All rights reserved.
 * 
 * @description:用户管理控制类
 * @Package: cn.edu.jxnu.base.controller.admin.system
 * @author: 梦境迷离
 * @date:2018年3月19日11:06:23
 */
@Controller
@RequestMapping("/admin/user")
public class UserController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	// 使用cache的密码缓存
	@Autowired
	private MyRealm myReal;
	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleService roleService;

	@RequestMapping(value = { "/", "/index" })
	public String index() {
		return "admin/user/index";
	}

	@RequestMapping(value = { "/list" })
	@ResponseBody
	public Page<User> list() {
		SimpleSpecificationBuilder<User> builder = new SimpleSpecificationBuilder<User>();
		String searchText = request.getParameter("searchText");
		if (StringUtils.isNotBlank(searchText)) {
			builder.add("userName", Operator.likeAll.name(), searchText);
		}

		Page<User> page = userService.findAll(builder.generateSpecification(), getPageRequest());
		return page;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap map) {
		return "admin/user/form";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Integer id, ModelMap map) {
		User user = userService.find(id);
		map.put("user", user);
		map.put("edit", "noCheck");
		return "admin/user/form";
	}

	@RequestMapping(value = { "/edit" }, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult edit(User user, ModelMap map) {
		try {
			logger.info("inputuser:" + user.toString());
			userService.saveOrUpdate(user);
		} catch (Exception e) {
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(@PathVariable Integer id, ModelMap map) {
		String res = "";
		try {
			userService.delete(id);
			User user = userService.find(id);
			if (user != null && user.getDeleteStatus() == 1) {
				res = "已注销";
			} else if (user == null) {
				res = "已删除";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success(res);
	}

	@RequestMapping(value = "/grant/{id}", method = RequestMethod.GET)
	public String grant(@PathVariable Integer id, ModelMap map) {
		User user = userService.find(id);
		map.put("user", user);

		Set<Role> set = user.getRoles();
		List<Integer> roleIds = new ArrayList<Integer>();
		for (Role role : set) {
			roleIds.add(role.getId());
		}
		map.put("roleIds", roleIds);

		List<Role> roles = roleService.findAll();
		map.put("roles", roles);
		return "admin/user/grant";
	}

	@ResponseBody
	@RequestMapping(value = "/grant/{id}", method = RequestMethod.POST)
	public JsonResult grant(@PathVariable Integer id, String[] roleIds, ModelMap map) {
		try {
			userService.grant(id, roleIds);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}

	/**
	 * @description:恢复账号
	 */
	@ResponseBody
	@RequestMapping(value = "/resume/{id}", method = RequestMethod.POST)
	public JsonResult resume(@PathVariable Integer id) {
		Cache<String, AtomicInteger> passwordRetryCache = myReal.getPasswordRetryCache();
		try {
			User user = userService.find(id);
			String userCode = user.getUserCode();
			// 恢复账号或者恢复锁定
			if (user.getDeleteStatus() == 1 || user.getLocked() == 1) {
				user.setDeleteStatus(0);
				user.setLocked(0);
				userService.saveOrUpdate(user);
				// 存在缓存，去除
				if (passwordRetryCache.get(userCode) != null) {
					passwordRetryCache.remove(userCode);
				}

			} else {
				// 不需要恢复
				return JsonResult.failure("当前账号不可执行此操作");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}

	/**
	 * @description:验证用户名【学号】是否已经被注册
	 */
	@ResponseBody
	@RequestMapping(value = "/isExist", method = RequestMethod.GET)
	public boolean isExist(String userCode) {
		logger.info("UserCode:" + userCode);
		boolean result = true;
		if (userCode != null) {
			User user = userService.findByUserCode(userCode);
			if (user != null) {
				result = false;
			}
		}
		return result;
	}

	/**
	 * @description:修改时，验证永真
	 */
	@ResponseBody
	@RequestMapping(value = "/isAllTrue", method = RequestMethod.GET)
	public boolean isAllTrue(String userCode) {
		return true;
	}

	/**
	 * @description:验证用户名【学号】是否已经被注册,代理前端的账户验证
	 */
	@ResponseBody
	@RequestMapping(value = "/isAvailable/{userCode}")
	public boolean isAvailable(@PathVariable("userCode") String userCode) {
		logger.info("前台验证账户可用代理:" + userCode);
		boolean result = true;
		if (userCode != null) {
			User user = userService.findByUserCode(userCode);
			if (user != null) {
				result = false;
			}
		}
		return result;
	}
}
