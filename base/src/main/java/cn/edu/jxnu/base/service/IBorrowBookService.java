package cn.edu.jxnu.base.service;

import cn.edu.jxnu.base.entity.BorrowBook;
import cn.edu.jxnu.base.service.impl.IBaseService;

/**
 * Copyright©2018 梦境迷离.All rights reserved.
 * 
 * @description:借阅图书服务类
 * @Package: cn.edu.jxnu.base.service
 * @author: 梦境迷离
 * @date:2018年3月19日11:23:33
 */
public interface IBorrowBookService extends IBaseService<BorrowBook, String> {

	/**
	 * 通过用户id查询借阅信息
	 * 
	 * @parameter userId 用户id
	 * @return BorrowBook[]
	 */
	BorrowBook[] findByUserId(int userId);

	/**
	 * 通过图书id查询借阅信息
	 * 
	 * @parameter bookId 图书id
	 * @return BorrowBook[]
	 */
	BorrowBook[] findByBookId(String bookId);

	/**
	 * 通过用户id和图书id查询借阅信息
	 * 
	 * @parameter userId 用户id bookId 图书id
	 * @return BorrowBook
	 */
	BorrowBook findByUserIdAndBookId(int userId, String bookId);

	/**
	 * 保存或修改借阅信息
	 * 
	 * @parameter borrowBook 借阅信息
	 * @return void
	 */
	void saveOrUpdate(BorrowBook borrowBook);

	/**
	 * 通过用户id和图书id联合主键删除借阅信息
	 * 
	 * @parameter userId 用户id bookId 图书id
	 * @return void
	 */
	void deletByUserIdAndBookId(int userId, String bookId);

}
