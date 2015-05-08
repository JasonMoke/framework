/*
 * Copyright 2005-2013 compass.com. All rights reserved.
 * Support: http://www.compass.com
 * License: http://www.compass.com/license
 */
package com.framework.entity.logmanager;

import java.io.Serializable;

/**
 * 日志配置
 * 
 * @author CreativeWises Team
 * @version 3.0
 */
public class LogConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5474556561435098854L;

	/** 操作类型*/
    private String LogType;
    
    /** 操作类等级*/
    private Integer LogLevel;

	/** 请求URL */
	private String urlPattern;
	
	/** 操作模块 */
	private String OperatObject;


	public String getLogType() {
		return LogType;
	}

	public void setLogType(String logType) {
		LogType = logType;
	}

	public Integer getLogLevel() {
		return LogLevel;
	}

	public void setLogLevel(Integer logLevel) {
		LogLevel = logLevel;
	}

	/**
	 * 获取请求URL
	 * 
	 * @return 请求URL
	 */
	public String getUrlPattern() {
		return urlPattern;
	}

	/**
	 * 设置请求URL
	 * 
	 * @param urlPattern
	 *            请求URL
	 */
	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}

	public String getOperatObject() {
		return OperatObject;
	}

	public void setOperatObject(String operatObject) {
		OperatObject = operatObject;
	}

}