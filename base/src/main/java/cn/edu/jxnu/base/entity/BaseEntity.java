package cn.edu.jxnu.base.entity;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

/**
 * Copyright©2018 梦境迷离.All rights reserved.
 * 
 * @description:基类-----属性类型需要为引用类型，判断条件为null,原生类型为0
 * @Package: cn.edu.jxnu.base.entity.support
 * @author: 梦境迷离
 * @date:2018年3月19日11:17:59
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -250118731239275742L;

}