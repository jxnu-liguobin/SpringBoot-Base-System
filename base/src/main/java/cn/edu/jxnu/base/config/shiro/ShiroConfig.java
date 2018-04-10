package cn.edu.jxnu.base.config.shiro;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

/**
 * Copyright©2018 梦境迷离.All rights reserved.
 * 
 * @description:Shiro配置
 * @Package: cn.edu.jxnu.base.config.shiro
 * @author: 梦境迷离
 * @date:2018年3月19日10:56:53
 */
@Configuration
@Import(ShiroManager.class)
public class ShiroConfig {

	@Bean(name = "realm")
	@DependsOn("lifecycleBeanPostProcessor")
	@ConditionalOnMissingBean

	public Realm realm(@Qualifier("shiroCacheManager") CacheManager cacheManager) {
		return new MyRealm(cacheManager);
	}

	/**
	 * 用户授权信息Cache
	 */
	@Bean(name = "shiroCacheManager")
	@ConditionalOnMissingBean
	public CacheManager cacheManager() {
		return new MemoryConstrainedCacheManager();
	}

	@Bean(name = "securityManager")
	@ConditionalOnMissingBean
	public DefaultSecurityManager securityManager() {
		DefaultSecurityManager sm = new DefaultWebSecurityManager();
		sm.setCacheManager(cacheManager());
		return sm;
	}

	@Bean(name = "shiroFilter")
	@DependsOn("securityManager")
	@ConditionalOnMissingBean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultSecurityManager securityManager, Realm realm) {
		securityManager.setRealm(realm);

		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager);
		shiroFilter.setLoginUrl("/admin/login");
		shiroFilter.setSuccessUrl("/admin/index");
		shiroFilter.setUnauthorizedUrl("/assets/401.html");
		Map<String, String> filterChainDefinitionMap = new HashMap<String, String>();
		filterChainDefinitionMap.put("/assets/**", "anon");
		filterChainDefinitionMap.put("/admin/regist", "anon");// 添加
		filterChainDefinitionMap.put("/admin/login", "anon");
		// 个人信息
		filterChainDefinitionMap.put("/admin/info/**", "anon");
		// 自主还书
		filterChainDefinitionMap.put("/admin/borrow/**", "anon");
		filterChainDefinitionMap.put("/admin/user/index", "perms[system:user:index]");
		filterChainDefinitionMap.put("/admin/user/add", "perms[system:user:add]");
		filterChainDefinitionMap.put("/admin/user/edit*", "perms[system:user:edit]");
		filterChainDefinitionMap.put("/admin/user/deleteBatch", "perms[system:user:deleteBatch]");
		filterChainDefinitionMap.put("/admin/user/grant/**", "perms[system:user:grant]");
		filterChainDefinitionMap.put("/admin/user/resume/**", "perms[system:user:resume]");
		// 注册账号验证和添加账号验证
		filterChainDefinitionMap.put("/admin/user/isExist/**", "anon");
		filterChainDefinitionMap.put("/admin/user/isAvailable/**", "anon");
		filterChainDefinitionMap.put("/admin/user/isAllTrue/**", "anon");

		filterChainDefinitionMap.put("/admin/role/index", "perms[system:role:index]");
		filterChainDefinitionMap.put("/admin/role/add", "perms[system:role:add]");
		filterChainDefinitionMap.put("/admin/role/edit*", "perms[system:role:edit]");
		filterChainDefinitionMap.put("/admin/role/deleteBatch", "perms[system:role:deleteBatch]");
		filterChainDefinitionMap.put("/admin/role/grant/**", "perms[system:role:grant]");

		filterChainDefinitionMap.put("/admin/resource/index", "perms[system:resource:index]");
		filterChainDefinitionMap.put("/admin/resource/add", "perms[system:resource:add]");
		filterChainDefinitionMap.put("/admin/resource/edit*", "perms[system:resource:edit]");
		filterChainDefinitionMap.put("/admin/resource/deleteBatch", "perms[system:resource:deleteBatch]");

		filterChainDefinitionMap.put("/druid/", "perms[system:resource:druid]");// druid
		// 添加过滤条件
		filterChainDefinitionMap.put("/admin/books/book_management", "perms[system:books:book_management]");

		filterChainDefinitionMap.put("/admin/**", "authc");
		shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilter;
	}
}
