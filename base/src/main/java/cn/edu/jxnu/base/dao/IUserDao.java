package cn.edu.jxnu.base.dao;

import org.springframework.stereotype.Repository;

import cn.edu.jxnu.base.dao.IBaseDao;
import cn.edu.jxnu.base.entity.User;

/**
 * Copyright©2018 梦境迷离.All rights reserved.
 * 
 * @description:用户管理dao
 * @Package: cn.edu.jxnu.base.dao
 * @author: 梦境迷离
 * @date:2018年3月19日11:11:11
 */
@Repository
public interface IUserDao extends IBaseDao<User, Integer> {

	User findByUserName(String username);

	User findByUserCode(String usercode);

}
