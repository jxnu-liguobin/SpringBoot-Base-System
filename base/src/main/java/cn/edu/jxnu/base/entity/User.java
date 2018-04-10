package cn.edu.jxnu.base.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;

import cn.edu.jxnu.base.entity.BaseEntity;

/**
serialVersionUID的作用
简单来说，Java的序列化机制是通过在运行时判断类的serialVersionUID来验证版本一致性的。
在进行反序列化时，JVM会把传来的字节流中的serialVersionUID与本地相应
实体（类）的serialVersionUID进行比较，如果相同就认为是一致的，可以进行反序列化，
否则就会出现序列化版本不一致的异常。(InvalidCastException)
serialVersionUID有两种显示的生成方式：
一个是默认的1L，比如：private static final long serialVersionUID = 1L; 
一个是根据类名、接口名、成员方法及属性等来生成一个64位的哈希字段

注意：阿里电面问过序列化机制
最后补充：序列化关键一点在于远程方法调用（RMI）中可以隐藏底层细节
 */
/**
 * Copyright©2018 梦境迷离.All rights reserved.
 * 
 * @description:用户表
 * @Package: cn.edu.jxnu.base.entity
 * @author: 梦境迷离
 * @date:2018年3月19日11:17:04
 */
@Entity
@Table(name = "tb_user")
public class User extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2401834536131259473L;

	/**
	 * 用户id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	/**
	 * 学号
	 */
	private String userCode;
	/**
	 * 真实姓名
	 */
	private String userName;

	/**
	 * 用户密码
	 */
	private String password;

	/**
	 * 电话
	 */
	private String telephone;

	/**
	 * 逻辑删除状态 0 未删除 1 删除
	 */
	private Integer deleteStatus;

	/**
	 * 是否锁定
	 * 
	 * 0 未锁定 1 锁定
	 */
	private Integer locked;

	/**
	 * 创建时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 更新时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	/**
	 * 用户<->角色 多对多关系，设置级联删除，懒加载，中间表tb_user_role，[user_id<->role_id]
	 */
	@ManyToMany(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(name = "tb_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private java.util.Set<Role> roles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(Integer deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public java.util.Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(java.util.Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", userCode=" + userCode + ", password=" + password
				+ ", telephone=" + telephone + "]";
	}

}
