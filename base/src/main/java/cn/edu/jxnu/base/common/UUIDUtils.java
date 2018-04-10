package cn.edu.jxnu.base.common;

import java.util.UUID;

/**
 * Copyright©2018 梦境迷离.All rights reserved.
 * 
 * @description:生成唯一id工具
 * @Package: cn.edu.jxnu.base.common.utils
 * @author: 梦境迷离
 * @date:2018年3月19日10:49:41
 */
public class UUIDUtils {

	public static String makeID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
