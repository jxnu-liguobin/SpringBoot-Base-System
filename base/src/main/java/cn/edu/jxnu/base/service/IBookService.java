package cn.edu.jxnu.base.service;

import cn.edu.jxnu.base.entity.Book;
import cn.edu.jxnu.base.service.impl.IBaseService;

/**
 * Copyright©2018 梦境迷离.All rights reserved.
 * 
 * @description:图书服务类
 * @Package: cn.edu.jxnu.base.service
 * @author: 梦境迷离
 * @date:2018年3月19日11:23:12
 */
public interface IBookService extends IBaseService<Book, String> {

	/**
	 * 根据图书id查找图书
	 * 
	 * @parameter id
	 * @return Book
	 */
	Book findByBookId(String id);

	/**
	 * 根据书名查图书
	 * 
	 * @parameter bookName
	 * @return Book
	 */
	Book findByBookName(String bookName);

	/**
	 * 根据出版社查图书
	 * 
	 * @parameter bookPress
	 * @return Book
	 */
	Book findByBookPress(String bookPress);

	/**
	 * 保存或更新图书信息
	 * 
	 * @parameter book
	 * @return void
	 */
	void saveOrUpdate(Book book);

}
