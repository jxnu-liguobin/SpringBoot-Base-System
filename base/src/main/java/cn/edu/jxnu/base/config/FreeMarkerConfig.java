package cn.edu.jxnu.base.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import cn.edu.jxnu.base.config.shiro.freemarker.ShiroTags;

/**
 * Copyright©2018 梦境迷离.All rights reserved.
 * 
 * @description:FreeMarker配置
 * @Package: cn.edu.jxnu.base.config
 * @author: 梦境迷离
 * @date:2018年3月19日10:52:30
 */
@Configuration
public class FreeMarkerConfig {

	@Autowired
	private freemarker.template.Configuration configuration;

	// @PostContruct是spring框架的注解，在方法上加该注解会在项目启动的时候执行该方法，也可以理解为在spring容器初始化的时候执行该方法。
	@PostConstruct
	public void setSharedVariable() {
		try {
			// 在FreeMarker页面中添加使用shiro标签的配置
			configuration.setSharedVariable("shiro", new ShiroTags());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
