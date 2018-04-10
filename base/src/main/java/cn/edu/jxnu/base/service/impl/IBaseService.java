package cn.edu.jxnu.base.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright©2018 梦境迷离.All rights reserved.
 * 
 * @description:系统服务层基接口
 * @Package: cn.edu.jxnu.base.service.support
 * @author: 梦境迷离
 * @date:2018年3月19日11:33:03
 */
public interface IBaseService<T, ID extends Serializable> {
	public abstract T find(ID id);

	public abstract T find(String id);

	public abstract List<T> findAll();

	public abstract List<T> findList(ID[] ids);

	public abstract List<T> findList(Iterable<ID> ids);

	public abstract Page<T> findAll(Pageable pageable);

	public abstract Page<T> findAll(Specification<T> spec, Pageable pageable);

	public abstract long count();

	public abstract long count(Specification<T> spec);

	public abstract boolean exists(ID id);

	public abstract void save(T entity);

	public abstract T update(T entity);

	public abstract void delete(ID id);

	public abstract void deleteByIds(@SuppressWarnings("unchecked") ID... ids);

	public abstract void delete(T[] entitys);

	public void delete(Iterable<T> entitys);

	public abstract void delete(T entity);

	// List<T>是ArrayList类的泛型等效类，该类使用大小可按需动态增加的数组实现IList<T>泛型接口
	public List<T> findList(Specification<T> spec, Sort sort);
}