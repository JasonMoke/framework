package com.framework.aop.log;



import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.framework.entity.logmanager.Logmanager;
import com.framework.service.logmanager.LogmanagerService;
@Component
public class AopLogPersistence {

	@Resource(name = "logmanagerService")
	private LogmanagerService logService;
	public void aopLogPersistence(Logmanager log) {
		logService.addEntity(log);
	}
	
}
