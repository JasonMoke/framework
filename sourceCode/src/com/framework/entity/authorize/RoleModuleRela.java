/**
 * @Title: RoleModuleRela 
 * @Copyright 2010 -2013 CreativeWise 
 * @Package: com.framework.entity.authorize
 * @Description: 
 * @author: gaogc 
 * @date: 2014-08-29 17:36:53
 * @version V1.0  
 */ 
package com.framework.entity.authorize;

import java.util.Date;
import javax.persistence.Entity;
import com.orm.BaseEntity;

/**
 * @ClassName: RoleModuleRela 
 * @Description: 
 * @author: gaogc 
 * @date: 2014-08-29 17:36:53
 */ 
@Entity
public class RoleModuleRela extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4993960326920357433L;

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
