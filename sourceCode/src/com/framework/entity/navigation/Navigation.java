/**
 * @Title: TPubNav 
 * @Copyright 2010 -2013 CreativeWise 
 * @Package: com.framework.entity.navigation
 * @Description: 
 * @author: gaojie 
 * @date: 2014-09-04 16:06:16
 * @version V1.0  
 */ 
package com.framework.entity.navigation;

import java.util.Date;
import javax.persistence.Entity;
import com.orm.BaseEntity;

/**
 * @ClassName: TPubNav 
 * @Description: 
 * @author: gaojie 
 * @date: 2014-09-04 16:06:16
 */ 
@Entity
public class Navigation extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3545233827537964766L;

	private String UUID;

    private String SystemCode;

    private String NavName;

    private String NavUrl;

    private String UrlPrefix;

    private int SeqNum;

    private int Status;

    private String HelpFilePath;

    private String CreatePerson;

    private String UpdatePerson;

    private Date UpdateTime;

    private Date CreateTime;

    
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
    public void setSystemCode(String SystemCode) {
    	this.SystemCode = SystemCode;
    }

    /**  
    * @Description: 
    */
    public String getSystemCode() {
    	return this.SystemCode;
    }

    /**  
    * @Description: 
    */
    public void setNavName(String NavName) {
    	this.NavName = NavName;
    }

    /**  
    * @Description: 
    */
    public String getNavName() {
    	return this.NavName;
    }

    /**  
    * @Description: 
    */
    public void setNavUrl(String NavUrl) {
    	this.NavUrl = NavUrl;
    }

    /**  
    * @Description: 
    */
    public String getNavUrl() {
    	return this.NavUrl;
    }

    /**  
    * @Description: 
    */
    public void setUrlPrefix(String UrlPrefix) {
    	this.UrlPrefix = UrlPrefix;
    }

    /**  
    * @Description: 
    */
    public String getUrlPrefix() {
    	return this.UrlPrefix;
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

}
