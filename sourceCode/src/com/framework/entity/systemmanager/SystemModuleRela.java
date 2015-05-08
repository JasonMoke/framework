/**
 * @Title: Systemmanager 
 * @Copyright 2010 -2013 CreativeWise 
 * @Package: com.framework.entity.systemmanager
 * @Description: 
 * @author: lixiaoguang 
 * @date: 2014-04-03 13:41:58
 * @version V1.0  
 */ 
package com.framework.entity.systemmanager;

import java.util.Date;

import javax.persistence.Entity;

import com.orm.BaseEntity;


/**
 * @ClassName: Systemmanager 
 * @Description: 
 * @author: lixiaoguang 
 * @date: 2014-04-03 13:41:58
 */ 
@Entity
public class SystemModuleRela extends BaseEntity {

    private String UUID;

    private String SystemId;

    private String ModuleId;

    private Integer Status;

    private String CreatePerson;

    private Date CreateTime;

    private String UpdatePerson;

    private Date UpdateTime;

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public String getSystemId() {
		return SystemId;
	}

	public void setSystemId(String systemId) {
		SystemId = systemId;
	}

	public String getModuleId() {
		return ModuleId;
	}

	public void setModuleId(String moduleId) {
		ModuleId = moduleId;
	}

	public Integer getStatus() {
		return Status;
	}

	public void setStatus(Integer status) {
		Status = status;
	}

	public String getCreatePerson() {
		return CreatePerson;
	}

	public void setCreatePerson(String createPerson) {
		CreatePerson = createPerson;
	}

	public Date getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}

	public String getUpdatePerson() {
		return UpdatePerson;
	}

	public void setUpdatePerson(String updatePerson) {
		UpdatePerson = updatePerson;
	}

	public Date getUpdateTime() {
		return UpdateTime;
	}

	public void setUpdateTime(Date updateTime) {
		UpdateTime = updateTime;
	}

    
 
	
}
