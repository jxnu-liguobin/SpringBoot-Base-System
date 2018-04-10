package cn.edu.jxnu.base.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.edu.jxnu.base.entity.BaseEntity;

/**
 * Copyright©2018 梦境迷离.All rights reserved.
 * 
 * @description:图书表
 * @Package: cn.edu.jxnu.base.entity
 * @author: 梦境迷离
 * @date:2018年3月19日11:09:34
 */
@Entity
@Table(name = "tb_books")
public class Book extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3275387890061613371L;

	/**
	 * 图书id
	 */
	@Id
	@Column(name = "book_id", nullable = false)
	private String bookId;

	/**
	 * 图书名
	 */
	private String bookName;

	/**
	 * 作者
	 */
	private String bookAuthor;

	/**
	 * 出版社
	 */
	private String bookPress;

	/**
	 * 图书总库存
	 */
	private Integer bookInventory;

	/**
	 * 图书现有库存
	 */
	private Integer currentInventory;

	public Integer getCurrentInventory() {
		return currentInventory;
	}

	public void setCurrentInventory(Integer currentInventory) {
		this.currentInventory = currentInventory;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public void setBookInventory(Integer bookInventory) {
		this.bookInventory = bookInventory;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookPress() {
		return bookPress;
	}

	public void setBookPress(String bookPress) {
		this.bookPress = bookPress;
	}

	public Integer getBookInventory() {
		return bookInventory;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", bookAuthor=" + bookAuthor + ", bookPress="
				+ bookPress + ", bookInventory=" + bookInventory + ", currentInventory=" + currentInventory + "]";
	}

}
