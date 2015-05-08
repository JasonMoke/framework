
package com.framework.entity.role;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

import com.framework.entity.authority.RightsManager;
import com.orm.BaseEntity;

/**
 * @ClassName: Rolemanager 
 * @Description: 
 * @author: zhaojie 
 * @date: 2014-01-17 14:14:24
 */ 
@Entity
public class RoleManager extends BaseEntity {

	private String RoleName;

	private String RoleRemark;

	private int RoleNumber;

	private int Status;

	private String CreatePerson;

	private Date CreateTime;

	private String UpdatePerson;

	private Date UpdateTime;
	
	private String Locale;
	
	private Set<RightsManager> RightsManagers = new HashSet<RightsManager>();
	
	
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
	public void setRoleRemark(String RoleRemark) {
		this.RoleRemark = RoleRemark;
	}

	/**
	 * @Description:
	 */
	public String getRoleRemark() {
		return this.RoleRemark;
	}

	/**
	 * @Description:
	 */
	public void setRoleNumber(int RoleNumber) {
		this.RoleNumber = RoleNumber;
	}

	/**
	 * @Description:
	 */
	public int getRoleNumber() {
		return this.RoleNumber;
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

	/**
	 * @return the locale
	 */
	public String getLocale() {
		return Locale;
	}

	/**
	 * @param locale the locale to set
	 */
	public void setLocale(String locale) {
		Locale = locale;
	}

	public Set<RightsManager> getRightsManagers() {
		return RightsManagers;
	}

	public void setRightsManagers(Set<RightsManager> rightsManagers) {
		RightsManagers = rightsManagers;
	}

}
