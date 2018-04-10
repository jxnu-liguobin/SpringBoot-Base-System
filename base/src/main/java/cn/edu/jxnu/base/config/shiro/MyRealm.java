package cn.edu.jxnu.base.config.shiro;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.edu.jxnu.base.common.Constats;
import cn.edu.jxnu.base.common.MD5Utils;
import cn.edu.jxnu.base.entity.Resource;
import cn.edu.jxnu.base.entity.Role;
import cn.edu.jxnu.base.entity.User;
import cn.edu.jxnu.base.service.IUserService;

/**
 * Copyright©2018 梦境迷离.All rights reserved.
 * 
 * @description:Shiro Realm
 * @Package: cn.edu.jxnu.base.config.shiro
 * @author: 梦境迷离
 * @date:2018年3月19日10:56:16
 */
@Component
public class MyRealm extends AuthorizingRealm {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private IUserService userService;

	// 集群中可能会导致出现验证多过5次的现象，因为AtomicInteger只能保证单节点并发
	private Cache<String, AtomicInteger> passwordRetryCache;

	public MyRealm(CacheManager cacheManager) {
		super(new AllowAllCredentialsMatcher());
		setAuthenticationTokenClass(UsernamePasswordToken.class);
		setPasswordRetryCache(cacheManager.getCache("passwordRetryCache"));
		logger.info("生成passwordRetryCache");
		// FIXME: 暂时禁用Cache
		setCachingEnabled(false);
	}

	/**
	 * 根据用户名来添加相应的权限和角色
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		User user = (User) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		User dbUser = userService.findByUserName(user.getUserName());
		Set<String> shiroPermissions = new HashSet<>();// 存放用户权限
		Set<String> roleSet = new HashSet<String>();// 存放用户角色名
		Set<Role> roles = dbUser.getRoles();// 从用户信息中获取用户拥有的角色
		for (Role role : roles) {
			// 对于用户的每个角色，获取角色拥有的资源
			Set<Resource> resources = role.getResources();
			for (Resource resource : resources) {
				// 添加角色拥有的资源的全部id到集合中
				shiroPermissions.add(resource.getSourceKey());

			}
			// 保存用户的角色的id
			roleSet.add(role.getRoleKey());
		}
		authorizationInfo.setRoles(roleSet);
		authorizationInfo.setStringPermissions(shiroPermissions);
		return authorizationInfo;
	}

	/**
	 * 密码验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userCode = (String) token.getPrincipal();
		User user = userService.findByUserCode(userCode);
		boolean matches = false;
		AtomicInteger retryCount = getPasswordRetryCache().get(userCode);
		// 用户名存在，且缓到期了。
		if (user != null && user.getDeleteStatus() == 1) {
			throw new UnknownAccountException("账号已注销");
		}
		if (user != null && getPasswordRetryCache().get(userCode) == null) {
			// 恢复标志位
			if (user.getLocked() == 1) {
				user.setLocked(0);
				userService.saveOrUpdate(user);
			}
		}

		if (null == retryCount) {
			retryCount = new AtomicInteger(0);
			getPasswordRetryCache().put(userCode, retryCount);
		}
		if (retryCount.incrementAndGet() > 5) {
			logger.warn("username: " + userCode + " 密码输入错误超过5次，请10分钟后尝试");
			// 修改锁定标志位
			User us = userService.findByUserCode(userCode);
			user.setLocked(1);
			userService.saveOrUpdate(us);
			throw new ExcessiveAttemptsException("密码输入错误超过5次，请10分钟后尝试");
		}
		String password = new String((char[]) token.getCredentials());
		SimpleAuthenticationInfo info = null;
		try {
			// 账号不存在
			if (user == null) {
				matches = true;
				throw new UnknownAccountException("账号或密码不正确");
			}
			// 密码错误
			if (!MD5Utils.md5(password).equals(user.getPassword())) {
				throw new IncorrectCredentialsException("账号或密码不正确");
			}
			// 账号锁定
			if (user.getLocked() == 1) {
				throw new LockedAccountException("账号已被锁定,请联系管理员");
			}
			info = new SimpleAuthenticationInfo(user, password, getName());
			matches = true;
			// 添加到session中
			SecurityUtils.getSubject().getSession().setTimeout(1000 * 60 * 10);
			SecurityUtils.getSubject().getSession().setAttribute(Constats.CURRENTUSER, user);

		} finally {
			// 账号不存在，用户名错误，正常登录都需要去掉缓存
			if (matches) {
				getPasswordRetryCache().remove(userCode);
			}
		}

		return info;
	}

	public Cache<String, AtomicInteger> getPasswordRetryCache() {
		return passwordRetryCache;
	}

	public void setPasswordRetryCache(Cache<String, AtomicInteger> passwordRetryCache) {
		this.passwordRetryCache = passwordRetryCache;
	}

}
