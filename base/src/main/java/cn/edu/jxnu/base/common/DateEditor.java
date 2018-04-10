package cn.edu.jxnu.base.common;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

/**
 * Copyright©2018 梦境迷离.All rights reserved.
 * 
 * @description:通过继承JDK 中的 java.beans.PropertyEditorSupport 类来实现自己的编辑器类
 *                      ，该类用于实现将String 类型转换成自己需要的数据类型
 * @Package: cn.edu.jxnu.base.controller
 * @author: 梦境迷离
 * @date:2018年3月19日10:49:41
 */
public class DateEditor extends PropertyEditorSupport {

	private boolean emptyAsNull;
	private String dateFormat = "yyyy-MM-dd HH:mm:ss";
	public static final String[] DATE_PATTERNS = { "yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd", "yyyyMMdd",
			"yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss" };

	public DateEditor(boolean emptyAsNull) {
		this.emptyAsNull = emptyAsNull;
	}

	public DateEditor(boolean emptyAsNull, String dateFormat) {
		this.emptyAsNull = emptyAsNull;
		this.dateFormat = dateFormat;
	}

	public String getAsText() {
		Date date = (Date) getValue();
		return date != null ? new SimpleDateFormat(this.dateFormat).format(date) : "";
	}

	public void setAsText(String text) {
		if (text == null) {
			setValue(null);
		} else {
			String str = text.trim();
			// true 则将空字符串转换为null
			if ((this.emptyAsNull) && ("".equals(str)))
				setValue(null);
			else
				try {
					setValue(DateUtils.parseDate(str, DATE_PATTERNS));
				} catch (ParseException e) {
					setValue(null);
				}
		}
	}
}
