package cn.edu.jxnu.base.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cn.edu.jxnu.base.dao.IBaseDao;
import cn.edu.jxnu.base.dao.IRoleDao;
import cn.edu.jxnu.base.entity.Resource;
import cn.edu.jxnu.base.entity.Role;
import cn.edu.jxnu.base.service.IResourceService;
import cn.edu.jxnu.base.service.IRoleService;

/**
 * Copyright©2018 梦境迷离.All rights reserved.
 * 
 * @description: 角色服务实现类
 * @Package: cn.edu.jxnu.base.service.Impl
 * @author: 梦境迷离
 * @date:2018年3月19日11:25:50
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, Integer> implements IRoleService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private IResourceService resourceService;

	@Override
	public IBaseDao<Role, Integer> getBaseDao() {
		return this.roleDao;
	}

	@Override
	public void saveOrUpdate(Role role) {
		logger.info("saveOrUpdate:" + role);
		if (role.getId() != null) {
			Role dbRole = find(role.getId());
			dbRole.setUpdateTime(new Date());
			dbRole.setName(role.getName());
			dbRole.setDescription(role.getDescription());
			dbRole.setUpdateTime(new Date());
			dbRole.setStatus(role.getStatus());
			update(dbRole);
		} else {
			role.setCreateTime(new Date());
			role.setUpdateTime(new Date());
			save(role);
		}
	}

	@Override
	public void delete(Integer id) {
		logger.info("delete:" + id);
		Role role = find(id);
		Assert.state(!"administrator".equals(role.getRoleKey()), "超级管理员角色不能删除");
		super.delete(id);
	}

	@Override
	public void grant(Integer id, String[] resourceIds) {
		logger.info("grant:" + id);
		Role role = find(id);
		Assert.notNull(role, "角色不存在");

		Assert.state(!"administrator".equals(role.getRoleKey()), "超级管理员角色不能进行资源分配");
		Resource resource;
		Set<Resource> resources = new HashSet<Resource>();
		if (resourceIds != null) {
			for (int i = 0; i < resourceIds.length; i++) {
				if (StringUtils.isBlank(resourceIds[i]) || "0".equals(resourceIds[i])) {
					continue;
				}
				Integer rid = Integer.parseInt(resourceIds[i]);
				resource = resourceService.find(rid);
				resources.add(resource);
			}
		}
		role.setResources(resources);
		update(role);
	}

	@Override
	public Role find(String id) {
		return null;
	}

}
