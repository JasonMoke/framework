/*
 * Copyright 2005-2013 compass.com. All rights reserved.
 * Support: http://www.compass.com
 * License: http://www.compass.com/license
 */
package com.framework.service.logconfig;

import java.util.List;

import com.framework.entity.logmanager.LogConfig;


/**
 * Service - 日志配置
 * 
 * @author CreativeWises Team
 * @version 3.0
 */
public interface LogConfigService {

	/**
	 * 获取所有日志配置
	 * 
	 * @return 所有日志配置
	 */
	List<LogConfig> getAll();
	

}