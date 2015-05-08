/**
 * @Title: dictList 
 * @Copyright 2010 -2013 CreativeWise 
 * @Package: com.framework.entity.dict 
 * @Description: t_dictList的实体类 
 * @author: gaoguangchao 
 * @date: 2014-01-02 17:07:01 
 * @version V1.0  
 */ 
package com.framework.entity.dict;



import java.util.Date;


import javax.persistence.Entity;

import com.orm.BaseEntity;


/**
 * @ClassName: dictList 
 * @Description: t_dictList的实体类 
 * @author: gaoguangchao 
 * @date: 2014-01-02 17:07:01 
 */ 
 @Entity
public class DictList extends BaseEntity{

	private String DictListCode;	//VARCHAR(36)

	private String DictListName;	//VARCHAR(128)

	private String DictListRemark;	//VARCHAR(256)

	private int DictListNumber;	//INT(11)

	private Integer Status;	//TINYINT(3)

	private Date CreateTime;	//DATETIME(19)

	private String CreatePerson;	//VARCHAR(36)
	private String UpdatePerson;
	private Date UpdateTime;
	/**
	 * @return the dictListCode
	 */
	public String getDictListCode() {
		return DictListCode;
	}

	/**
	 * @param dictListCode the dictListCode to set
	 */
	public void setDictListCode(String dictListCode) {
		DictListCode = dictListCode;
	}

	/**
	 * @return the dictListName
	 */
	public String getDictListName() {
		return DictListName;
	}

	/**
	 * @param dictListName the dictListName to set
	 */
	public void setDictListName(String dictListName) {
		DictListName = dictListName;
	}

	/**
	 * @return the dictListRemark
	 */
	public String getDictListRemark() {
		return DictListRemark;
	}

	/**
	 * @param dictListRemark the dictListRemark to set
	 */
	public void setDictListRemark(String dictListRemark) {
		DictListRemark = dictListRemark;
	}

	/**
	 * @return the dictListNumber
	 */
	public int getDictListNumber() {
		return DictListNumber;
	}

	/**
	 * @param dictListNumber the dictListNumber to set
	 */
	public void setDictListNumber(int dictListNumber) {
		DictListNumber = dictListNumber;
	}



	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return CreateTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}

	/**
	 * @return the createPerson
	 */
	public String getCreatePerson() {
		return CreatePerson;
	}

	/**
	 * @param createPerson the createPerson to set
	 */
	public void setCreatePerson(String createPerson) {
		CreatePerson = createPerson;
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

	/**
	 * @return the updatePerson
	 */
	public String getUpdatePerson() {
		return UpdatePerson;
	}

	/**
	 * @param updatePerson the updatePerson to set
	 */
	public void setUpdatePerson(String updatePerson) {
		UpdatePerson = updatePerson;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return UpdateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		UpdateTime = updateTime;
	}



}

