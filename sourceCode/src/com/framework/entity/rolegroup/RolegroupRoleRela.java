/**
 * @Title: RolegroupRoleRela 
 * @Copyright 2010 -2013 CreativeWise 
 * @Package: com.framework.entity.rolegroup
 * @Description: 
 * @author: gaogc 
 * @date: 2014-08-29 16:46:03
 * @version V1.0  
 */ 
package com.framework.entity.rolegroup;

import javax.persistence.Entity;
import com.orm.BaseEntity;

/**
 * @ClassName: RolegroupRoleRela 
 * @Description: 
 * @author: gaogc 
 * @date: 2014-08-29 16:46:03
 */ 
@Entity
public class RolegroupRoleRela extends BaseEntity {

    private String UUID;

    private String RoleId;

    private String RoleGroupId;

    
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
    public void setRoleId(String RoleId) {
    	this.RoleId = RoleId;
    }

    /**  
    * @Description: 
    */
    public String getRoleId() {
    	return this.RoleId;
    }

    /**  
    * @Description: 
    */
    public void setRoleGroupId(String RoleGroupId) {
    	this.RoleGroupId = RoleGroupId;
    }

    /**  
    * @Description: 
    */
    public String getRoleGroupId() {
    	return this.RoleGroupId;
    }

}
