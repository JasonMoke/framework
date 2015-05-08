/**
 * @Title: t_module 
 * @Copyright 2010 -2013 CreativeWise 
 * @Package: com.framework.entity.module 
 * @Description: t_module的实体类 
 * @author: gaoguangchao 
 * @date: 2013-12-23 13:02:29 
 * @version V1.0  
 */ 
package com.framework.entity.module;



import java.util.Date;

import javax.persistence.Entity;

import com.orm.BaseEntity;


/**
 * @ClassName: t_module 
 * @Description: t_module的实体类 
 * @author: gaoguangchao 
 * @date: 2013-12-23 13:02:29 
 */ 
@Entity
public class ModuleResourceRela extends BaseEntity{

	private String UUID;	//VARCHAR(36)
	
	private String ModuleId;	//VARCHAR(64)
	
	private String ResourceId;	//VARCHAR(36)

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public String getModuleId() {
		return ModuleId;
	}

	public void setModuleId(String moduleId) {
		ModuleId = moduleId;
	}

	public String getResourceId() {
		return ResourceId;
	}

	public void setResourceId(String resourceId) {
		ResourceId = resourceId;
	}

 

}

