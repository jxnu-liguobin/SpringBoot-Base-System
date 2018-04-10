package cn.edu.jxnu.base.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cn.edu.jxnu.base.common.MD5Utils;
import cn.edu.jxnu.base.dao.IBaseDao;
import cn.edu.jxnu.base.dao.IUserDao;
import cn.edu.jxnu.base.entity.Role;
import cn.edu.jxnu.base.entity.User;
import cn.edu.jxnu.base.service.IRoleService;
import cn.edu.jxnu.base.service.IUserService;

/**
 * Copyright©2018 梦境迷离.All rights reserved.
 * 
 * @description: 用户服务实现类
 * @Package: cn.edu.jxnu.base.service.Impl
 * @author: 梦境迷离
 * @date:2018年3月19日11:26:27
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements IUserService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IUserDao userDao;

	@Autowired
	private IRoleService roleService;

	@Override
	public IBaseDao<User, Integer> getBaseDao() {
		return this.userDao;
	}

	@Override
	public User findByUserName(String username) {
		logger.info("findByUserName:" + username);
		return userDao.findByUserName(username);
	}

	@Override
	public User findByUserCode(String usercode) {
		logger.info("findByUserCode:" + usercode);
		return userDao.findByUserCode(usercode);
	}

	@Override
	public void saveOrUpdate(User user) {
		logger.info("saveOrUpdate:" + user);
		if (user.getId() != null) {
			User dbUser = find(user.getId());
			dbUser.setUserName(user.getUserName());
			dbUser.setPassword(MD5Utils.md5(user.getPassword()));
			dbUser.setTelephone(user.getTelephone());
			dbUser.setLocked(user.getLocked());
			dbUser.setUpdateTime(new Date());
			logger.info("userinfo:" + user.toString());
			update(dbUser);
		} else {

			user.setCreateTime(new Date());
			user.setUpdateTime(new Date());
			user.setDeleteStatus(0);
			user.setPassword(MD5Utils.md5(user.getPassword()));
			// 设定默认分权为学生权限
			Role role;
			Set<Role> roles = new HashSet<Role>();
			role = roleService.find(2);
			roles.add(role);
			user.setRoles(roles);
			logger.info("userinfo:" + user.toString());
			save(user);
		}
	}

	/**
	 * @description 删除分两种：真正删除和转换为设置一个删除标志位，数据仍然保存
	 */
	@Override
	public void delete(Integer id) {
		logger.info("delete:" + id);
		User user = find(id);
		// false抛异常
		// Assert.state(user.getDeleteStatus() == 0, "已删除用户不可重复删除");
		Assert.state(!"admin".equals(user.getUserName()), "超级管理员用户不能删除");
		// super.delete(id);
		if (user.getDeleteStatus() == 1) {
			// 已被删除的，此次将真的删除。
			delete(user);
		} else {
			//置位1
			user.setDeleteStatus(1);
			update(user);
		}

	}

	// 授权管理
	@Override
	public void grant(Integer id, String[] roleIds) {
		logger.info("grant" + id);
		User user = find(id);
		Assert.notNull(user, "用户不存在");
		Assert.state(!"admin".equals(user.getUserName()), "超级管理员用户不能修改管理角色");
		Role role;
		Set<Role> roles = new HashSet<Role>();
		if (roleIds != null) {
			for (int i = 0; i < roleIds.length; i++) {
				Integer rid = Integer.parseInt(roleIds[i]);
				role = roleService.find(rid);
				roles.add(role);
			}
		}
		user.setRoles(roles);
		update(user);
	}

	@Override
	public User find(String id) {
		return null;
	}

}
