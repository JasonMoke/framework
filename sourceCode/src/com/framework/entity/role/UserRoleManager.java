
package com.framework.entity.role;

import java.util.Date;

import javax.persistence.Entity;

import com.orm.BaseEntity;

/**
 * @ClassName: Userrolemanager 
 * @Description: 
 * @author: zhaojie 
 * @date: 2014-01-17 14:11:47
 */ 
@Entity
public class UserRoleManager extends BaseEntity {

	private String UserRoleId;

	private String UserId;

	private String RoleName;

	private int Status;

	private String CreatePerson;

	private Date CreateTime;

	private String UpdatePerson;

	private Date UpdateTime;

	/**
	 * @Description:
	 */
	public void setUserRoleId(String UserRoleId) {
		this.UserRoleId = UserRoleId;
	}

	/**
	 * @Description:
	 */
	public String getUserRoleId() {
		return this.UserRoleId;
	}

	/**
	 * @Description:
	 */
	public void setUserId(String UserId) {
		this.UserId = UserId;
	}

	/**
	 * @Description:
	 */
	public String getUserId() {
		return this.UserId;
	}

	/**
	 * @Description:
	 */
	public void setRoleName(String RoleName) {
		this.RoleName = RoleName;
	}

	/**
	 * @Description:
	 */
	public String getRoleName() {
		return this.RoleName;
	}

	/**
	 * @Description:
	 */
	public void setStatus(int Status) {
		this.Status = Status;
	}

	/**
	 * @Description:
	 */
	public int getStatus() {
		return this.Status;
	}

	/**
	 * @Description:
	 */
	public void setCreatePerson(String CreatePerson) {
		this.CreatePerson = CreatePerson;
	}

	/**
	 * @Description:
	 */
	public String getCreatePerson() {
		return this.CreatePerson;
	}

	/**
	 * @Description:
	 */
	public void setCreateTime(Date CreateTime) {
		this.CreateTime = CreateTime;
	}

	/**
	 * @Description:
	 */
	public Date getCreateTime() {
		return this.CreateTime;
	}

	/**
	 * @Description:
	 */
	public void setUpdatePerson(String UpdatePerson) {
		this.UpdatePerson = UpdatePerson;
	}

	/**
	 * @Description:
	 */
	public String getUpdatePerson() {
		return this.UpdatePerson;
	}

	/**
	 * @Description:
	 */
	public void setUpdateTime(Date UpdateTime) {
		this.UpdateTime = UpdateTime;
	}

	/**
	 * @Description:
	 */
	public Date getUpdateTime() {
		return this.UpdateTime;
	}

}
