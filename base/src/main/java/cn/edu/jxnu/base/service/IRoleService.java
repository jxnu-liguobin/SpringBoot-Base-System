package cn.edu.jxnu.base.service;

import cn.edu.jxnu.base.entity.Role;
import cn.edu.jxnu.base.service.impl.IBaseService;

/**
 * Copyright©2018 梦境迷离.All rights reserved.
 * 
 * @description:角色服务类
 * @Package: cn.edu.jxnu.base.service
 * @author: 梦境迷离
 * @date:2018年3月19日11:24:03
 */
public interface IRoleService extends IBaseService<Role,Integer> {

	/**
	 * 添加或者修改角色
	 * @param role
	 */
	void saveOrUpdate(Role role);

	/**
	 * 给角色分配资源
	 * @param id 角色ID
	 * @param resourceIds 资源ids
	 */
	void grant(Integer id, String[] resourceIds);
	
}
