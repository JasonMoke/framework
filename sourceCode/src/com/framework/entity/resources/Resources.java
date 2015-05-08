package com.framework.entity.resources;



import java.util.Date;


import javax.persistence.Entity;

import com.orm.BaseEntity;

 @Entity
public class Resources extends BaseEntity{
	private String resourcesId;
	private String resourcesName;
	private String resourcesUrl;
	private String permissionSet;
	private int isMenu;
	private int Status;	//TINYINT(3)
	private Integer resourcesNumber;
	private Date CreateTime;	//DATETIME(19)
	private String CreatePerson;	//VARCHAR(36)
	private String UpdatePerson;
	private Date UpdateTime;




	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return CreateTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}

	/**
	 * @return the createPerson
	 */
	public String getCreatePerson() {
		return CreatePerson;
	}

	/**
	 * @param createPerson the createPerson to set
	 */
	public void setCreatePerson(String createPerson) {
		CreatePerson = createPerson;
	}



	/**
	 * @return the updatePerson
	 */
	public String getUpdatePerson() {
		return UpdatePerson;
	}

	/**
	 * @param updatePerson the updatePerson to set
	 */
	public void setUpdatePerson(String updatePerson) {
		UpdatePerson = updatePerson;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return UpdateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		UpdateTime = updateTime;
	}

	public String getResourcesId() {
		return resourcesId;
	}

	public void setResourcesId(String resourcesId) {
		this.resourcesId = resourcesId;
	}

	public String getResourcesName() {
		return resourcesName;
	}

	public void setResourcesName(String resourcesName) {
		this.resourcesName = resourcesName;
	}

	public String getResourcesUrl() {
		return resourcesUrl;
	}

	public void setResourcesUrl(String resourcesUrl) {
		this.resourcesUrl = resourcesUrl;
	}


	public Integer getResourcesNumber() {
		return resourcesNumber;
	}

	public void setResourcesNumber(Integer resourcesNumber) {
		this.resourcesNumber = resourcesNumber;
	}

	public int getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(int isMenu) {
		this.isMenu = isMenu;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public String getPermissionSet() {
		return permissionSet;
	}

	public void setPermissionSet(String permissionSet) {
		this.permissionSet = permissionSet;
	}


	

}

