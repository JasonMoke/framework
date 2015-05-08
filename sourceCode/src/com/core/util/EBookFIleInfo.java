package com.core.util;

/**
 * @ClassName: EBookFIleInfo 
 * @Description: 电子书信息
 * @author:  tanyi
 * @date: 2014-01-16 14:11:04
 */ 
public class EBookFIleInfo {
	private boolean ISSuccess;
	private String EISBN;
	private String FormatName;
	private String Language;
	
	/*
	 * @Description: 获得是否成功
	 */
	public boolean getISSuccess() {
		return ISSuccess;
	}
	
	/*
	 * @Description: 设置是否成功
	 */
	public void setISSuccess(boolean ISSuccess) {
		this.ISSuccess = ISSuccess;
	}

	/*
	 * @Description: 获得EISBN
	 */
	public String getEISBN() {
		return EISBN;
	}
	
	/*
	 * @Description: 设置EISBN
	 */
	public void setEISBN(String EISBN) {
		this.EISBN = EISBN;
	}
	
	/*
	 * @Description: 获得EISBN
	 */
	public String getFormatName() {
		return FormatName;
	}
	
	/*
	 * @Description: 设置EISBN
	 */
	public void setFormatName(String FormatName) {
		this.FormatName = FormatName;
	}
	
	/*
	 * @Description: 获得语言
	 */
	public String getLanguage() {
		return Language;
	}
	
	/*
	 * @Description: 设置语言
	 */
	public void setLanguage(String Language) {
		this.Language = Language;
	}
}
