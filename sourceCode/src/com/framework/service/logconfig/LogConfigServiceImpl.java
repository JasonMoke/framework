/*
 * Copyright 2005-2013 compass.com. All rights reserved.
 * Support: http://www.compass.com
 * License: http://www.compass.com/license
 */
package com.framework.service.logconfig;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.framework.entity.logmanager.LogConfig;

/**
 * Service - 日志配置
 * 
 * @author CreativeWises Team
 * @version 3.0
 */
@Service("logConfigServiceImpl")
public class LogConfigServiceImpl implements LogConfigService {

	@SuppressWarnings("unchecked")
	@Cacheable("logConfig")
	public List<LogConfig> getAll() {
		try {
			File shopxxXmlFile = new ClassPathResource("/framework.xml").getFile();
			Document document = new SAXReader().read(shopxxXmlFile);
			List<org.dom4j.Element> elements = document.selectNodes("/framework/logConfig");
			List<LogConfig> logConfigs = new ArrayList<LogConfig>();
			for (org.dom4j.Element element : elements) {
				String logType = element.attributeValue("logType");
				int logLevel = Integer.parseInt((String)element.attributeValue("logLevel"));
				String operatObject = element.attributeValue("operatObject");
				String urlPattern = element.attributeValue("urlPattern");
				LogConfig logConfig = new LogConfig();
				logConfig.setLogType(logType);
				logConfig.setLogLevel(logLevel);
				logConfig.setUrlPattern(urlPattern);
				logConfig.setOperatObject(operatObject);
				logConfigs.add(logConfig);
			}
			return logConfigs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}