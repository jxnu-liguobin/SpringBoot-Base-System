package cn.edu.jxnu.base.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * Copyright©2018 梦境迷离.All rights reserved.
 * 
 * @description:借书表
 * @Package: cn.edu.jxnu.base.entity
 * @author: 梦境迷离
 * @date:2018年3月19日11:12:16
 */
@IdClass(BorrowBook.class) // 复合主键（user_id，book_id）
@Embeddable // 表示此类可被插入到其他类
@Entity
@Table(name = "tb_borrow_books")
public class BorrowBook extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7107955948085904241L;

	/**
	 * 用户id
	 */
	@Id
	@Column(name = "user_id", nullable = false)
	private Integer userId;

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
	 * 更新时间
	 */
	private Date updateTime;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookPress() {
		return bookPress;
	}

	public void setBookPress(String bookPress) {
		this.bookPress = bookPress;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "BorrowBook [userId=" + userId + ", bookId=" + bookId + ", bookName=" + bookName + ", bookAuthor="
				+ bookAuthor + ", bookPress=" + bookPress + ", updateTime=" + updateTime + "]";
	}

	/**
	 * 本来没有重写这两个方法，报了警告所以就重写了 重写equals必须重写hashCode，因为@IdClass-指明了复合主键
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BorrowBook) {
			BorrowBook b = (BorrowBook) obj;
			return b.bookId.equals(this.bookId) && b.updateTime.equals(this.updateTime) && b.userId.equals(this.userId)
					&& b.bookAuthor.equals(this.bookAuthor) && b.bookName.equals(this.bookName)
					&& b.bookPress.equals(this.bookPress);

		}
		return false;
	}

	@Override
	public int hashCode() {
		// 对于JDK7及更新版本
		return Objects.hash(this.bookId, this.userId, this.updateTime, this.bookAuthor, this.bookName, this.bookPress);
		// int result = 17;
		// result = 31 * result + this.bookId.hashCode();
		// result = 31 * result + this.userId;
		// result = 31 * result + this.updateTime.hashCode();
		// ...
		// return result;
	}

}
