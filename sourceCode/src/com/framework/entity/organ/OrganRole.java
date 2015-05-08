package com.framework.entity.organ;

import java.util.Date;
import javax.persistence.Entity;

@Entity
public class OrganRole {
		
	private String OrganRoleId;
	private String ID;
	private String RoleName;
	private Integer Status;
	private String CreatePerson;
	private Date CreateTime;
	private String UpdatePerson;
	private Date UpdateTime;
	
	public String getOrganRoleId() {
		return OrganRoleId;
	}
	public void setOrganRoleId(String organRoleId) {
		OrganRoleId = organRoleId;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getRoleName() {
		return RoleName;
	}
	public void setRoleName(String roleName) {
		RoleName = roleName;
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
