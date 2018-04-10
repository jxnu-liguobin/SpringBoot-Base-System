package cn.edu.jxnu.base.service;

import cn.edu.jxnu.base.entity.User;
import cn.edu.jxnu.base.service.impl.IBaseService;

/**
 * Copyright©2018 梦境迷离.All rights reserved.
 * 
 * @description:用户服务类
 * @Package: cn.edu.jxnu.base.service
 * @author: 梦境迷离
 * @date:2018年3月19日11:24:21
 */
public interface IUserService extends IBaseService<User, Integer> {

	/**
	 * 根据用户名查找用户
	 * 
	 * @param username
	 * @return
	 */
	User findByUserName(String username);

	User findByUserCode(String userCode);

	/**
	 * 增加或者修改用户
	 * 
	 * @param user
	 */
	void saveOrUpdate(User user);

	/**
	 * 给用户分配角色
	 * 
	 * @param id
	 *            用户ID
	 * @param roleIds
	 *            角色Ids
	 */
	void grant(Integer id, String[] roleIds);

}
