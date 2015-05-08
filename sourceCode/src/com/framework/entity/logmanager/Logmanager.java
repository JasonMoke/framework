/**
 * @Title: Logmanager 
 * @Copyright 2010 -2013 CreativeWise 
 * @Package: com.framework.entity.logmanager
 * @Description: 
 * @author: gaoguangchao 
 * @date: 2014-01-14 14:16:14
 * @version V1.0  
 */ 
package com.framework.entity.logmanager;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

import com.orm.BaseEntity;

/**
 * @ClassName: Logmanager 
 * @Description: 
 * @author: gaoguangchao 
 * @date: 2014-01-14 14:16:14
 */ 
@Entity
public class Logmanager extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7327313637146887549L;

	private String LogId;

    private String LogType;

    private Integer LogLevel;

    private String LogMessage;

    private String OperatPerson;

    private String OperatIp;

    private String OperatObject;

    private Date OperatTime;
    
    private String OperatTimeStart;
    
    private String OperatTimeEnd;

    private Integer OperatResult;

    private Integer Status;

    private String CreatePerson;

    private Date CreateTime;

    private String UpdatePerson;

    private Date UpdateTime;
    
    
    /** "日志内容"属性名称 */
	public static final String LOG_CONTENT_ATTRIBUTE_NAME = Logmanager.class.getName() + ".CONTENT";

	/** 操作 */
	private String operation;

	/** 内容 */
	private String content;

	/** 请求参数 */
	private String parameter;


	/**
	 * 获取操作
	 * 
	 * @return 操作
	 */
	@Column(nullable = false, updatable = false)
	public String getOperation() {
		return operation;
	}

	/**
	 * 设置操作
	 * 
	 * @param operation
	 *            操作
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}


	/**
	 * 获取内容
	 * 
	 * @return 内容
	 */
	@Column(length = 3000, updatable = false)
	public String getContent() {
		return content;
	}

	/**
	 * 设置内容
	 * 
	 * @param content
	 *            内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取请求参数
	 * 
	 * @return 请求参数
	 */
	@Lob
	@Column(updatable = false)
	public String getParameter() {
		return parameter;
	}

	/**
	 * 设置请求参数
	 * 
	 * @param parameter
	 *            请求参数
	 */
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

    /**  
    * @Description: 
    */
    public void setLogId(String LogId) {
    	this.LogId = LogId;
    }

    /**  
    * @Description: 
    */
    public String getLogId() {
    	return this.LogId;
    }

    /**  
    * @Description: 
    */
    public void setLogType(String LogType) {
    	this.LogType = LogType;
    }

    /**  
    * @Description: 
    */
    public String getLogType() {
    	return this.LogType;
    }

    /**  
    * @Description: 
    */
    public void setLogLevel(int LogLevel) {
    	this.LogLevel = LogLevel;
    }

    /**  
    * @Description: 
    */
    public int getLogLevel() {
    	return this.LogLevel;
    }

    /**  
    * @Description: 
    */
    public void setLogMessage(String LogMessage) {
    	this.LogMessage = LogMessage;
    }

    /**  
    * @Description: 
    */
    public String getLogMessage() {
    	return this.LogMessage;
    }

    /**  
    * @Description: 
    */
    public void setOperatPerson(String OperatPerson) {
    	this.OperatPerson = OperatPerson;
    }

    /**  
    * @Description: 
    */
    public String getOperatPerson() {
    	return this.OperatPerson;
    }

    /**  
    * @Description: 
    */
    public void setOperatIp(String OperatIp) {
    	this.OperatIp = OperatIp;
    }

    /**  
    * @Description: 
    */
    public String getOperatIp() {
    	return this.OperatIp;
    }

    /**  
    * @Description: 
    */
    public void setOperatObject(String OperatObject) {
    	this.OperatObject = OperatObject;
    }

    /**  
    * @Description: 
    */
    public String getOperatObject() {
    	return this.OperatObject;
    }

    /**  
    * @Description: 
    */
    public void setOperatTime(Date OperatTime) {
    	this.OperatTime = OperatTime;
    }

    /**  
    * @Description: 
    */
    public Date getOperatTime() {
    	return this.OperatTime;
    }

    /**  
    * @Description: 
    */
    public void setOperatResult(int OperatResult) {
    	this.OperatResult = OperatResult;
    }

    /**  
    * @Description: 
    */
    public int getOperatResult() {
    	return this.OperatResult;
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

	/**
	 * @return the operatTimeStart
	 */
	public String getOperatTimeStart() {
		return OperatTimeStart;
	}

	/**
	 * @param operatTimeStart the operatTimeStart to set
	 */
	public void setOperatTimeStart(String operatTimeStart) {
		OperatTimeStart = operatTimeStart;
	}

	/**
	 * @return the operatTimeEnd
	 */
	public String getOperatTimeEnd() {
		return OperatTimeEnd;
	}

	/**
	 * @param operatTimeEnd the operatTimeEnd to set
	 */
	public void setOperatTimeEnd(String operatTimeEnd) {
		OperatTimeEnd = operatTimeEnd;
	}

}
