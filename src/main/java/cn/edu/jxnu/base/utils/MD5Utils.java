/* 梦境迷离 (C)2020 */
package cn.edu.jxnu.base.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.util.StringUtils;

/**
 * Md5加密
 *
 * @author 梦境迷离
 * @version V2.0 2020年11月20日
 */
public class MD5Utils {
  /**
   * 对字符串进行Md5加密
   *
   * @param input 原文
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
   * @param input 原文
   * @param salt 随机数
   * @return string
   */
  public static String generatePasswordMD5(String input, String salt) {
    if (StringUtils.isEmpty(salt)) {
      salt = "";
    }
    return md5(salt + md5(input));
  }

  /**
   * 修改密码
   *
   * @param args args
   */
  public static void main(String[] args) {
    String passw = "111111";
    String r = MD5Utils.md5(passw);
    System.out.println(r);
  }
}
