package com.framework.aop.log;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class AopLogEntity {
	private String LogId;	//VARCHAR(36)

	private String LogType;	//VARCHAR(20)

	private int LogLevel;	//INT(11)
	
	private int OperatResult;	//INT(11)

	private String LogMessage;	//VARCHAR(2000)

	private String OperatPerson;	//VARCHAR(36)

	private String OperatIp;	//VARCHAR(20)

	private String OperatObject;	//VARCHAR(256)

	private Date OperatTime;	//DATETIME(19)
	
	private String CreatePerson;

    private Date CreateTime;

    private String UpdatePerson;

    private Date UpdateTime;

    private int Status;
    
	public void setLogId(String LogId){
		this.LogId=LogId;
	}
	public String getLogId(){
		return LogId;
	}

	public void setLogType(String LogType){
		this.LogType=LogType;
	}
	public String getLogType(){
		return LogType;
	}

	public void setLogLevel(int LogLevel){
		this.LogLevel=LogLevel;
	}
	public int getLogLevel(){
		return LogLevel;
	}

	public void setLogMessage(String LogMessage){
		this.LogMessage=LogMessage;
	}
	public String getLogMessage(){
		return LogMessage;
	}

	public void setOperatPerson(String OperatPerson){
		this.OperatPerson=OperatPerson;
	}
	public String getOperatPerson(){
		return OperatPerson;
	}

	public void setOperatIp(String OperatIp){
		this.OperatIp=OperatIp;
	}
	public String getOperatIp(){
		return OperatIp;
	}

	public void setOperatObject(String OperatObject){
		this.OperatObject=OperatObject;
	}
	public String getOperatObject(){
		return OperatObject;
	}

	public void setOperatTime(Date OperatTime){
		this.OperatTime=OperatTime;
	}
	public Date getOperatTime(){
		return OperatTime;
	}
	/**
	 * @return the operatResult
	 */
	public int getOperatResult() {
		return OperatResult;
	}
	/**
	 * @param operatResult the operatResult to set
	 */
	public void setOperatResult(int operatResult) {
		OperatResult = operatResult;
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
	 * @return the status
	 */
	public int getStatus() {
		return Status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		Status = status;
	}
	
}
