package cn.edu.jxnu.base.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.edu.jxnu.base.dao.IBaseDao;
import cn.edu.jxnu.base.entity.Resource;

/**
 * Copyright©2018 梦境迷离.All rights reserved.
 * 
 * @description:资源管理dao
 * @Package: cn.edu.jxnu.base.dao
 * @author: 梦境迷离
 * @date:2018年3月19日11:10:45
 */
@Repository
public interface IResourceDao extends IBaseDao<Resource, Integer> {

	@Modifying
	@Query(nativeQuery = true, value = "DELETE FROM tb_role_resource WHERE resource_id = :id")
	// :param 匹配@Param参数名
	void deleteGrant(@Param("id") Integer id);

}
