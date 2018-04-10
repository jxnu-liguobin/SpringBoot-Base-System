package cn.edu.jxnu.base.dao;

import org.springframework.stereotype.Repository;

import cn.edu.jxnu.base.dao.IBaseDao;
import cn.edu.jxnu.base.entity.Role;

/**
 * Copyright©2018 梦境迷离.All rights reserved.
 * 
 * @description:角色管理dao
 * @Package: cn.edu.jxnu.base.dao
 * @author: 梦境迷离
 * @date:2018年3月19日11:10:59
 */
@Repository
public interface IRoleDao extends IBaseDao<Role, Integer> {

}
