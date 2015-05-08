/**
 * @Title: RoleGroup 
 * @Copyright 2010 -2013 CreativeWise 
 * @Package: com.framework.entity.rolegroup
 * @Description: 
 * @author: gaogc 
 * @date: 2014-08-29 13:55:01
 * @version V1.0  
 */ 
package com.framework.entity.rolegroup;

import java.util.Date;
import javax.persistence.Entity;
import com.orm.BaseEntity;

/**
 * @ClassName: RoleGroup 
 * @Description: 
 * @author: gaogc 
 * @date: 2014-08-29 13:55:01
 */ 
@Entity
public class RoleGroup extends BaseEntity {

    private String RoleGroupName;

    private String RoleGroupRemark;

    private int SeqNum;

    private int Status;

    private String CreatePerson;

    private Date CreateTime;

    private String UpdatePerson;

    private Date UpdateTime;

    private String UUID;
    private int childCoutn;

    
    /**  
    * @Description: 
    */
    public void setRoleGroupName(String RoleGroupName) {
    	this.RoleGroupName = RoleGroupName;
    }

    /**  
    * @Description: 
    */
    public String getRoleGroupName() {
    	return this.RoleGroupName;
    }

    /**  
    * @Description: 
    */
    public void setRoleGroupRemark(String RoleGroupRemark) {
    	this.RoleGroupRemark = RoleGroupRemark;
    }

    /**  
    * @Description: 
    */
    public String getRoleGroupRemark() {
    	return this.RoleGroupRemark;
    }

    /**  
    * @Description: 
    */
    public void setSeqNum(int SeqNum) {
    	this.SeqNum = SeqNum;
    }

    /**  
    * @Description: 
    */
    public int getSeqNum() {
    	return this.SeqNum;
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
    * @Description: 
    */
    public void setUUID(String UUID) {
    	this.UUID = UUID;
    }

    /**  
    * @Description: 
    */
    public String getUUID() {
    	return this.UUID;
    }

	public int getChildCoutn() {
		return childCoutn;
	}

	public void setChildCoutn(int childCoutn) {
		this.childCoutn = childCoutn;
	}
    

}
