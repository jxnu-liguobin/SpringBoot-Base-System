package cn.edu.jxnu.base.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.edu.jxnu.base.dao.IBaseDao;
import cn.edu.jxnu.base.entity.BorrowBook;

/**
 * Copyright©2018 梦境迷离.All rights reserved.
 * 
 * @description:借书管理dao
 * @Package: cn.edu.jxnu.base.dao
 * @author: 梦境迷离
 * @date:2018年3月19日11:10:18
 */
@Repository
public interface IBorrowBookDao extends IBaseDao<BorrowBook, String> {

	BorrowBook[] findByUserId(int userId);

	BorrowBook[] findByBookId(String bookId);

	BorrowBook findByUserIdAndBookId(int userId, String bookId);

	// 事务删除或修改操作，不支持新增/插入
	@Modifying
	@Query("DELETE FROM BorrowBook b WHERE b.userId = ?1 and b.bookId= ?2")
	void mDelet(int userId, String bookId);

}
