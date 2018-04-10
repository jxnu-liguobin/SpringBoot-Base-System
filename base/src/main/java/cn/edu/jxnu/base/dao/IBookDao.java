package cn.edu.jxnu.base.dao;

import org.springframework.stereotype.Repository;

import cn.edu.jxnu.base.dao.IBaseDao;
import cn.edu.jxnu.base.entity.Book;

/**
 * Copyright©2018 梦境迷离.All rights reserved.
 * 
 * @description:图书管理dao
 * @Package: cn.edu.jxnu.base.dao
 * @author: 梦境迷离
 * @date:2018年3月19日11:09:34
 */
@Repository
public interface IBookDao extends IBaseDao<Book, String> {

	Book findByBookId(String bookId);

	Book findByBookName(String bookName);

	Book findByBookPress(String bookPress);

}
