/**
 * @Title: RoleResourceRela 
 * @Copyright 2010 -2013 CreativeWise 
 * @Package: com.framework.entity.authorize
 * @Description: 
 * @author: gaogc 
 * @date: 2014-08-29 17:37:07
 * @version V1.0  
 */ 
package com.framework.entity.authorize;

import java.util.Date;
import javax.persistence.Entity;
import com.orm.BaseEntity;

/**
 * @ClassName: RoleResourceRela 
 * @Description: 
 * @author: gaogc 
 * @date: 2014-08-29 17:37:07
 */ 
@Entity
public class RoleResourceRela extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 9057247700161291378L;

	private String UUID;

    private String RoleName;

    private String ResourceId;

    private int Status;

    private String CreatePerson;

    private Date CreateTime;

    private String UpdatePerson;

    private Date UpdateTime;

    
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
    public void setResourceId(String ResourceId) {
    	this.ResourceId = ResourceId;
    }

    /**  
    * @Description: 
    */
    public String getResourceId() {
    	return this.ResourceId;
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
