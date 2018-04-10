package cn.edu.jxnu.base.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.jxnu.base.dao.IBaseDao;
import cn.edu.jxnu.base.dao.IBorrowBookDao;
import cn.edu.jxnu.base.entity.BorrowBook;
import cn.edu.jxnu.base.service.IBorrowBookService;

/**
 * Copyright©2018 梦境迷离.All rights reserved.
 * 
 * @description:借阅图书服务实现类
 * @Package: cn.edu.jxnu.base.service.Impl
 * @author: 梦境迷离
 * @date:2018年3月19日11:25:13
 */
@Service
public class BorrowBookServiceImpl extends BaseServiceImpl<BorrowBook, String> implements IBorrowBookService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private IBorrowBookDao borrowBookDao;

	@Override
	public BorrowBook[] findByUserId(int userId) {
		logger.info("findByUserId:" + userId);
		return borrowBookDao.findByUserId(userId);
	}

	@Override
	public BorrowBook[] findByBookId(String bookId) {
		logger.info("findByBookId:" + bookId);
		return borrowBookDao.findByBookId(bookId);
	}

	@Override
	public void saveOrUpdate(BorrowBook borrowBook) {
		logger.info("saveOrUpdate:" + borrowBook.toString());
		save(borrowBook);

	}

	@Override
	public IBaseDao<BorrowBook, String> getBaseDao() {
		return this.borrowBookDao;
	}

	@Override
	public BorrowBook findByUserIdAndBookId(int userId, String bookId) {
		logger.info("findByUserIdAndBookId:" + userId + "-" + bookId);
		return borrowBookDao.findByUserIdAndBookId(userId, bookId);
	}

	@Override
	public void deletByUserIdAndBookId(int userId, String bookId) {
		logger.info("deletByUserIdAndBookId:" + userId + "-" + bookId);
		borrowBookDao.mDelet(userId, bookId);
	}

}
