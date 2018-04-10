package cn.edu.jxnu.base.entity;

import java.io.Serializable;

/**
 * Copyright©2018 梦境迷离.All rights reserved.
 * 
 * @description:ztree树
 * @Package: cn.edu.jxnu.base.vo
 * @author: 梦境迷离
 * @date:2018年3月19日11:35:03
 */
public class ZtreeView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6237809780035784312L;

	private Long id;

	private Long pId;

	private String name;

	private boolean open;

	private boolean checked = false;

	public ZtreeView() {
	}

	public ZtreeView(Long id, Long pId, String name, boolean open) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.open = open;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
