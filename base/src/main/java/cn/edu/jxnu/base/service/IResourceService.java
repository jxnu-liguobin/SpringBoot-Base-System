package cn.edu.jxnu.base.service;

import java.util.List;

import cn.edu.jxnu.base.entity.Resource;
import cn.edu.jxnu.base.entity.ZtreeView;
import cn.edu.jxnu.base.service.impl.IBaseService;

/**
 * Copyright©2018 梦境迷离.All rights reserved.
 * 
 * @description:资源服务类
 * @Package: cn.edu.jxnu.base.service
 * @author: 梦境迷离
 * @date:2018年3月19日11:23:46
 */
public interface IResourceService extends IBaseService<Resource, Integer> {

	/**
	 * 获取角色的权限树
	 * @param roleId
	 * @return
	 */
	List<ZtreeView> tree(int roleId);

	/**
	 * 修改或者新增资源
	 * @param resource
	 */
	void saveOrUpdate(Resource resource);

}
