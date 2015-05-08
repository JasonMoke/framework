/**
 * @Title: Rightsmanager 
 * @Copyright 2010 -2013 CreativeWise 
 * @Package: com.framework.entity.rightsmanager
 * @Description: 
 * @author: zhaojie 
 * @date: 2014-01-20 15:23:11
 * @version V1.0  
 */ 
package com.framework.entity.authority;

import java.util.Date;
import javax.persistence.Entity;
import com.orm.BaseEntity;

/**
 * @ClassName: Rightsmanager 
 * @Description: 
 * @author: zhaojie 
 * @date: 2014-01-20 15:23:11
 */ 
@Entity
public class RightsManager extends BaseEntity {

    private String RightsId;

    private String RoleName;

    private String ModuleId;

    private int Status;

    private String CreatePerson;

    private Date CreateTime;

    private String UpdatePerson;

    private Date UpdateTime;

    
    /**  
    * @Description: 
    */
    public void setRightsId(String RightsId) {
    	this.RightsId = RightsId;
    }

    /**  
    * @Description: 
    */
    public String getRightsId() {
    	return this.RightsId;
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
    public void setModuleId(String ModuleId) {
    	this.ModuleId = ModuleId;
    }

    /**  
    * @Description: 
    */
    public String getModuleId() {
    	return this.ModuleId;
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