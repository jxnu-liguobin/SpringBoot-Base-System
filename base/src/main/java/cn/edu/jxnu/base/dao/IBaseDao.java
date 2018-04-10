package cn.edu.jxnu.base.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import cn.edu.jxnu.base.entity.BaseEntity;

/**
 * Copyright©2018 梦境迷离.All rights reserved.
 * 
 * @description:Dao层数据全部使用Spring Data JPA
 * @Package: cn.edu.jxnu.base.dao.support
 * @author: 梦境迷离
 * @date:2018年3月19日11:08:41
 */
@NoRepositoryBean // 启动时不实例化
public interface IBaseDao<T extends BaseEntity, ID extends Serializable>
		// JpaRepository支持基本的CRUD，JpaSpecificationExecutor用在复杂查询
		extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

}
