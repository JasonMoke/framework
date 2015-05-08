/**
 * @Title: dictmanager 
 * @Copyright 2010 -2013 CreativeWise 
 * @Package: com.framework.entity.dict 
 * @Description: t_dict的实体类 
 * @author: gaoguangchao 
 * @date: 2014-01-02 17:11:36 
 * @version V1.0  
 */ 
package com.framework.entity.dict;



import java.util.Date;


import javax.persistence.Entity;

import com.orm.BaseEntity;


/**
 * @ClassName: dictmanager 
 * @Description: t_dict的实体类 
 * @author: gaoguangchao 
 * @date: 2014-01-02 17:11:36 
 */ 
 @Entity
public class Dictmanager extends BaseEntity{

	private String DictId;	//VARCHAR(36)

	private String DictListCode;	//VARCHAR(36)

	private String DictName;	//VARCHAR(128)

	private String ParentDictId;	//VARCHAR(36)

	private String DictData1;	//VARCHAR(256)

	private String DictData2;	//VARCHAR(256)

	private String DictData3;	//VARCHAR(256)

	private String DictData4;	//VARCHAR(256)

	private String DictData5;	//VARCHAR(256)

	private String BigImage;	//VARCHAR(512)

	private String SmallImage;	//VARCHAR(512)

	private String DictRemark;	//VARCHAR(512)

	private int DictNumber;	//INT(11)
	private Integer Status;	//TINYINT(3)
	private String Locale;
	private Date CreateTime;	//DATETIME(19)
	private String CreatePerson;	//VARCHAR(36)
	private String UpdatePerson;
	private Date UpdateTime;
	/**
	 * @return the dictId
	 */
	public String getDictId() {
		return DictId;
	}

	/**
	 * @param dictId the dictId to set
	 */
	public void setDictId(String dictId) {
		DictId = dictId;
	}

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
	 * @return the dictName
	 */
	public String getDictName() {
		return DictName;
	}

	/**
	 * @param dictName the dictName to set
	 */
	public void setDictName(String dictName) {
		DictName = dictName;
	}


	/**
	 * @return the dictData1
	 */
	public String getDictData1() {
		return DictData1;
	}

	/**
	 * @param dictData1 the dictData1 to set
	 */
	public void setDictData1(String dictData1) {
		DictData1 = dictData1;
	}

	/**
	 * @return the dictData2
	 */
	public String getDictData2() {
		return DictData2;
	}

	/**
	 * @param dictData2 the dictData2 to set
	 */
	public void setDictData2(String dictData2) {
		DictData2 = dictData2;
	}

	/**
	 * @return the dictData3
	 */
	public String getDictData3() {
		return DictData3;
	}

	/**
	 * @param dictData3 the dictData3 to set
	 */
	public void setDictData3(String dictData3) {
		DictData3 = dictData3;
	}

	/**
	 * @return the dictData4
	 */
	public String getDictData4() {
		return DictData4;
	}

	/**
	 * @param dictData4 the dictData4 to set
	 */
	public void setDictData4(String dictData4) {
		DictData4 = dictData4;
	}

	/**
	 * @return the dictData5
	 */
	public String getDictData5() {
		return DictData5;
	}

	/**
	 * @param dictData5 the dictData5 to set
	 */
	public void setDictData5(String dictData5) {
		DictData5 = dictData5;
	}

	/**
	 * @return the bigImage
	 */
	public String getBigImage() {
		return BigImage;
	}

	/**
	 * @param bigImage the bigImage to set
	 */
	public void setBigImage(String bigImage) {
		BigImage = bigImage;
	}

	/**
	 * @return the smallImage
	 */
	public String getSmallImage() {
		return SmallImage;
	}

	/**
	 * @param smallImage the smallImage to set
	 */
	public void setSmallImage(String smallImage) {
		SmallImage = smallImage;
	}

	/**
	 * @return the dictRemark
	 */
	public String getDictRemark() {
		return DictRemark;
	}

	/**
	 * @param dictRemark the dictRemark to set
	 */
	public void setDictRemark(String dictRemark) {
		DictRemark = dictRemark;
	}

	/**
	 * @return the dictNumber
	 */
	public int getDictNumber() {
		return DictNumber;
	}

	/**
	 * @param dictNumber the dictNumber to set
	 */
	public void setDictNumber(int dictNumber) {
		DictNumber = dictNumber;
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
	 * @return the parentDictId
	 */
	public String getParentDictId() {
		return ParentDictId;
	}

	/**
	 * @param parentDictId the parentDictId to set
	 */
	public void setParentDictId(String parentDictId) {
		ParentDictId = parentDictId;
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

	/**
	 * @return the locale
	 */
	public String getLocale() {
		return Locale;
	}

	/**
	 * @param locale the locale to set
	 */
	public void setLocale(String locale) {
		Locale = locale;
	}


	

}

