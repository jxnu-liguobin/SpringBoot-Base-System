package cn.edu.jxnu.base.common;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.util.StringUtils;

/**
 * Copyright©2018 梦境迷离.All rights reserved.
 * 
 * @description:Md5加密
 * @Package: cn.edu.jxnu.base.common.utils
 * @author: 梦境迷离
 * @date:2018年3月19日10:49:41
 */
public class MD5Utils {
	/**
	 * 对字符串进行Md5加密
	 * 
	 * @param input
	 *            原文
	 * @return md5后的密文
	 */
	public static String md5(String input) {
		byte[] code = null;
		try {
			code = MessageDigest.getInstance("md5").digest(input.getBytes());
		} catch (NoSuchAlgorithmException e) {
			code = input.getBytes();
		}
		BigInteger bi = new BigInteger(code);
		return bi.abs().toString(32).toUpperCase();
	}

	/**
	 * 对用户名密码进行Md5加密
	 * 
	 * @param input
	 *            原文
	 * @param salt
	 *            随机数
	 * @return string
	 */
	public static String generatePasswordMD5(String input, String salt) {
		if (StringUtils.isEmpty(salt)) {
			salt = "";
		}
		return md5(salt + md5(input));
	}

	/**
	 * @description:修改密码
	 */
	public static void main(String[] args) {
		String passw = "111111";
		String r = MD5Utils.md5(passw);
		System.out.println(r);
	}

}
