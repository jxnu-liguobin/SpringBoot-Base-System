package cn.edu.jxnu.base.entity;

import java.util.Arrays;

import cn.edu.jxnu.base.entity.BaseEntity;

/**
 * Copyright©2018 梦境迷离.All rights reserved.
 * 
 * @description:借阅列表
 * @Package: cn.edu.jxnu.base.entity
 * @author: 梦境迷离
 * @date:2018年3月19日11:12:54
 */
public class BorrowList extends BaseEntity {

	private static final long serialVersionUID = -1894163644285296223L;

	private Integer id;
	private String[] booklist;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String[] getBooklist() {
		return booklist;
	}

	public void setBooklist(String[] booklist) {
		this.booklist = booklist;
	}

	@Override
	public String toString() {
		return "BorrowList [id=" + id + ", booklist=" + Arrays.toString(booklist) + "]";
	}

}
