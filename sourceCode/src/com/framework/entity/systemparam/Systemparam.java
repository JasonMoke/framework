/**
 * @Title: Systemparam 
 * @Copyright 2010 -2013 CreativeWise 
 * @Package: com.framework.entity.systemparam
 * @Description: 
 * @author: gaoguangchao 
 * @date: 2014-01-21 15:24:47
 * @version V1.0  
 */ 
package com.framework.entity.systemparam;

import java.util.Date;
import javax.persistence.Entity;
import com.orm.BaseEntity;

/**
 * @ClassName: Systemparam 
 * @Description: 
 * @author: gaoguangchao 
 * @date: 2014-01-21 15:24:47
 */ 
@Entity
public class Systemparam extends BaseEntity {

    private String ParamId;

    private String ParamName;

    private String ParamValue;

    private String ParamRemark;

    private int ParamNumber;

    private String SystemCode;

    private int Status;

    private String CreatePerson;

    private Date CreateTime;

    private String UpdatePerson;

    private Date UpdateTime;

    
    /**  
    * @Description: 
    */
    public void setParamId(String ParamId) {
    	this.ParamId = ParamId;
    }

    /**  
    * @Description: 
    */
    public String getParamId() {
    	return this.ParamId;
    }

    /**  
    * @Description: 
    */
    public void setParamName(String ParamName) {
    	this.ParamName = ParamName;
    }

    /**  
    * @Description: 
    */
    public String getParamName() {
    	return this.ParamName;
    }

    /**  
    * @Description: 
    */
    public void setParamValue(String ParamValue) {
    	this.ParamValue = ParamValue;
    }

    /**  
    * @Description: 
    */
    public String getParamValue() {
    	return this.ParamValue;
    }

    /**  
    * @Description: 
    */
    public void setParamRemark(String ParamRemark) {
    	this.ParamRemark = ParamRemark;
    }

    /**  
    * @Description: 
    */
    public String getParamRemark() {
    	return this.ParamRemark;
    }

    /**  
    * @Description: 
    */
    public void setParamNumber(int ParamNumber) {
    	this.ParamNumber = ParamNumber;
    }

    /**  
    * @Description: 
    */
    public int getParamNumber() {
    	return this.ParamNumber;
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
