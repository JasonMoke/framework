/**
 * @Title: Organ 
 * @Copyright 2010 -2013 CreativeWise 
 * @Package: com.framework.entity.organ
 * @Description: 
 * @author: gaoguangchao 
 * @date: 2014-04-09 13:33:04
 * @version V1.0  
 */ 
package com.framework.entity.organ;

import java.util.Date;
import javax.persistence.Entity;
import com.orm.BaseEntity;

/**
 * @ClassName: Organ 
 * @Description: 
 * @author: gaoguangchao 
 * @date: 2014-04-09 13:33:04
 */ 
@Entity
public class Organ extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5869648917890485762L;

	private String OrganId;

    private String Cname;

    private String ShortCname;

    private String OrgCode;

    private String Enname;

    private String OrgGrade;

    private String ParentId;

    private String OrgPhone;

    private String OrgAddr;

    private String OrgEmail;

    private String OrgDoorNum;

    private int SeqNum;

    private Integer Status;

    private String Memo;

    private String RespPerson;

    private String LinkMan;

    private String CreatePerson;

    private Date CreateTime;

    private String UpdatePerson;

    private Date UpdateTime;

    private String IsResrved;
    
    private String TreePath;

    
    /**  
    * @Description: 
    */
    public void setOrganId(String Id) {
    	this.OrganId = Id;
    }

    /**  
    * @Description: 
    */
    public String getOrganId() {
    	return this.OrganId;
    }

    /**  
    * @Description: 
    */
    public void setCname(String Cname) {
    	this.Cname = Cname;
    }

    /**  
    * @Description: 
    */
    public String getCname() {
    	return this.Cname;
    }

    /**  
    * @Description: 
    */
    public void setShortCname(String ShortCname) {
    	this.ShortCname = ShortCname;
    }

    /**  
    * @Description: 
    */
    public String getShortCname() {
    	return this.ShortCname;
    }

    /**  
    * @Description: 
    */
    public void setOrgCode(String OrgCode) {
    	this.OrgCode = OrgCode;
    }

    /**  
    * @Description: 
    */
    public String getOrgCode() {
    	return this.OrgCode;
    }

    /**  
    * @Description: 
    */
    public void setEnname(String Enname) {
    	this.Enname = Enname;
    }

    /**  
    * @Description: 
    */
    public String getEnname() {
    	return this.Enname;
    }

    /**  
    * @Description: 
    */
    public void setOrgGrade(String OrgGrade) {
    	this.OrgGrade = OrgGrade;
    }

    /**  
    * @Description: 
    */
    public String getOrgGrade() {
    	return this.OrgGrade;
    }

    /**  
    * @Description: 
    */
    public void setParentId(String ParentId) {
    	this.ParentId = ParentId;
    }

    /**  
    * @Description: 
    */
    public String getParentId() {
    	return this.ParentId;
    }

    /**  
    * @Description: 
    */
    public void setOrgPhone(String OrgPhone) {
    	this.OrgPhone = OrgPhone;
    }

    /**  
    * @Description: 
    */
    public String getOrgPhone() {
    	return this.OrgPhone;
    }

    /**  
    * @Description: 
    */
    public void setOrgAddr(String OrgAddr) {
    	this.OrgAddr = OrgAddr;
    }

    /**  
    * @Description: 
    */
    public String getOrgAddr() {
    	return this.OrgAddr;
    }

    /**  
    * @Description: 
    */
    public void setOrgEmail(String OrgEmail) {
    	this.OrgEmail = OrgEmail;
    }

    /**  
    * @Description: 
    */
    public String getOrgEmail() {
    	return this.OrgEmail;
    }

    /**  
    * @Description: 
    */
    public void setOrgDoorNum(String OrgDoorNum) {
    	this.OrgDoorNum = OrgDoorNum;
    }

    /**  
    * @Description: 
    */
    public String getOrgDoorNum() {
    	return this.OrgDoorNum;
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
    public void setMemo(String Memo) {
    	this.Memo = Memo;
    }

    /**  
    * @Description: 
    */
    public String getMemo() {
    	return this.Memo;
    }

    /**  
    * @Description: 
    */
    public void setRespPerson(String RespPerson) {
    	this.RespPerson = RespPerson;
    }

    /**  
    * @Description: 
    */
    public String getRespPerson() {
    	return this.RespPerson;
    }

    /**  
    * @Description: 
    */
    public void setLinkMan(String LinkMan) {
    	this.LinkMan = LinkMan;
    }

    /**  
    * @Description: 
    */
    public String getLinkMan() {
    	return this.LinkMan;
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
    public void setIsResrved(String IsResrved) {
    	this.IsResrved = IsResrved;
    }

    /**  
    * @Description: 
    */
    public String getIsResrved() {
    	return this.IsResrved;
    }

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return Status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		Status = status;
	}

	public String getTreePath() {
		return TreePath;
	}

	public void setTreePath(String treePath) {
		TreePath = treePath;
	}
	
	

}
