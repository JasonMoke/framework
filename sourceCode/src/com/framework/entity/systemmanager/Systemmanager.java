/**
 * @Title: Systemmanager 
 * @Copyright 2010 -2013 CreativeWise 
 * @Package: com.framework.entity.systemmanager
 * @Description: 
 * @author: lixiaoguang 
 * @date: 2014-04-03 13:41:58
 * @version V1.0  
 */ 
package com.framework.entity.systemmanager;

import java.util.Date;

import javax.persistence.Entity;

import com.orm.BaseEntity;


/**
 * @ClassName: Systemmanager 
 * @Description: 
 * @author: lixiaoguang 
 * @date: 2014-04-03 13:41:58
 */ 
@Entity
public class Systemmanager extends BaseEntity {

    private String SystemCode;

    private String ShortName;

    private String FullName;

    private String SystemAddress;

    private byte[] BigLogo;

    private byte[] SmallLogo;

    private String SystemRemark;

    private int SystemNumber;
    
    private int isPreferences;

    private Integer Status;

    private String CreatePerson;

    private Date CreateTime;

    private String UpdatePerson;

    private Date UpdateTime;
    
    private int childCoutn;

    
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
    public void setShortName(String ShortName) {
    	this.ShortName = ShortName;
    }

    /**  
    * @Description: 
    */
    public String getShortName() {
    	return this.ShortName;
    }

    /**  
    * @Description: 
    */
    public void setFullName(String FullName) {
    	this.FullName = FullName;
    }

    /**  
    * @Description: 
    */
    public String getFullName() {
    	return this.FullName;
    }

    /**  
    * @Description: 
    */
    public void setSystemAddress(String SystemAddress) {
    	this.SystemAddress = SystemAddress;
    }

    /**  
    * @Description: 
    */
    public String getSystemAddress() {
    	return this.SystemAddress;
    }



    /**  
    * @Description: 
    */
    public void setSystemRemark(String SystemRemark) {
    	this.SystemRemark = SystemRemark;
    }

    /**  
    * @Description: 
    */
    public String getSystemRemark() {
    	return this.SystemRemark;
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
	public int getSystemNumber() {
		return SystemNumber;
	}

    /**  
     * @Description: 
     */
	public void setSystemNumber(int systemNumber) {
		SystemNumber = systemNumber;
	}

	public Integer getStatus() {
		return Status;
	}

	public void setStatus(Integer status) {
		Status = status;
	}

	public byte[] getBigLogo() {
		return BigLogo;
	}

	public void setBigLogo(byte[] bigLogo) {
		BigLogo = bigLogo;
	}

	public byte[] getSmallLogo() {
		return SmallLogo;
	}

	public void setSmallLogo(byte[] smallLogo) {
		SmallLogo = smallLogo;
	}

	/**
	 * @return the isPreferences
	 */
	public int getIsPreferences() {
		return isPreferences;
	}

	/**
	 * @param isPreferences the isPreferences to set
	 */
	public void setIsPreferences(int isPreferences) {
		this.isPreferences = isPreferences;
	}

	public int getChildCoutn() {
		return childCoutn;
	}

	public void setChildCoutn(int childCoutn) {
		this.childCoutn = childCoutn;
	}
	
}
