package cn.edu.jxnu.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Copyright © 2018 梦境迷离. All rights reserved.
 * 
 * @description:主启动类
 * @Package: cn.edu.jxnu.base
 * @author: 梦境迷离
 * @date: 2018年3月19日10:46:46
 */
@SpringBootApplication
@EnableCaching
public class Application {

	private static Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		logger.debug("启动成功");
	}

}
