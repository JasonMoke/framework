/**
 * @Title: PubMenu 
 * @Copyright 2010 -2013 CreativeWise 
 * @Package: com.framework.entity.pubmenu
 * @Description: 
 * @author: lyc 
 * @date: 2014-09-05 11:03:46
 * @version V1.0  
 */ 
package com.framework.entity.pubmenu;

import java.util.Date;

import javax.persistence.Entity;

import com.orm.BaseEntity;

/**
 * @ClassName: PubMenu 
 * @Description: 
 * @author: lyc 
 * @date: 2014-09-05 11:03:46
 */ 
@Entity
public class PubMenu extends BaseEntity {

    private String UUID;

    private String NavId;

    private String MenuName;

    private String MenuType;

    private String ModuleId;

    private String ResourcesId;

    private String PID;

    private int SeqNum;

    private String MenuUrl;

    private int Target;

    private String IsMenuEntr;

    private String HelpFilePath;

    private int Status;

    private String CreatePerson;
    
    private String HasChild;

    private String UpdatePerson;

    private Date UpdateTime;

    private Date CreateTime;
    
    private String ModuleName; //模块名称

    private String ResourcesName;//资源名称
    
    private String PIDName; //父类名称
    
    private int isPreferences;

    
    public int getIsPreferences() {
		return isPreferences;
	}

	public void setIsPreferences(int isPreferences) {
		this.isPreferences = isPreferences;
	}

	public String getPIDName() {
		return PIDName;
	}

	public void setPIDName(String pIDName) {
		PIDName = pIDName;
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

    /**  
    * @Description: 
    */
    public void setNavId(String NavId) {
    	this.NavId = NavId;
    }

    /**  
    * @Description: 
    */
    public String getNavId() {
    	return this.NavId;
    }

    /**  
    * @Description: 
    */
    public void setMenuName(String MenuName) {
    	this.MenuName = MenuName;
    }

    /**  
    * @Description: 
    */
    public String getMenuName() {
    	return this.MenuName;
    }

    /**  
    * @Description: 
    */
    public void setMenuType(String MenuType) {
    	this.MenuType = MenuType;
    }

    /**  
    * @Description: 
    */
    public String getMenuType() {
    	return this.MenuType;
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
    public void setResourcesId(String ResourcesId) {
    	this.ResourcesId = ResourcesId;
    }

    /**  
    * @Description: 
    */
    public String getResourcesId() {
    	return this.ResourcesId;
    }

    /**  
    * @Description: 
    */
    public void setPID(String PID) {
    	this.PID = PID;
    }

    /**  
    * @Description: 
    */
    public String getPID() {
    	return this.PID;
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
    public void setMenuUrl(String MenuUrl) {
    	this.MenuUrl = MenuUrl;
    }

    /**  
    * @Description: 
    */
    public String getMenuUrl() {
    	return this.MenuUrl;
    }

    /**  
    * @Description: 
    */
    public void setTarget(int Target) {
    	this.Target = Target;
    }

    /**  
    * @Description: 
    */
    public int getTarget() {
    	return this.Target;
    }

    /**  
    * @Description: 
    */
    public void setIsMenuEntr(String IsMenuEntr) {
    	this.IsMenuEntr = IsMenuEntr;
    }

    /**  
    * @Description: 
    */
    public String getIsMenuEntr() {
    	return this.IsMenuEntr;
    }

    /**  
    * @Description: 
    */
    public void setHelpFilePath(String HelpFilePath) {
    	this.HelpFilePath = HelpFilePath;
    }

    /**  
    * @Description: 
    */
    public String getHelpFilePath() {
    	return this.HelpFilePath;
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
    public void setCreateTime(Date CreateTime) {
    	this.CreateTime = CreateTime;
    }

    /**  
    * @Description: 
    */
    public Date getCreateTime() {
    	return this.CreateTime;
    }

	public String getModuleName() {
		return ModuleName;
	}

	public void setModuleName(String moduleName) {
		ModuleName = moduleName;
	}

	public String getResourcesName() {
		return ResourcesName;
	}

	public void setResourcesName(String resourcesName) {
		ResourcesName = resourcesName;
	}

	public String getHasChild() {
		return HasChild;
	}

	public void setHasChild(String hasChild) {
		HasChild = hasChild;
	}
    
    

}
