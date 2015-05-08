package com.framework.entity.session;

import java.util.Date;

public class SessionsManager {
	
	//sessionId
	private String SessionId;
	//session创建时间
	private Date CreateTime;
	//session最后活跃时间
	private Date LastAccessedTime;
	//session存在总时间
	private Date TotalTime;
	//在线人员部门
	private String OrganId;
	//账号
	private String UserName;
	//IP
	private String IP;
	

	public String getSessionId() {
		return SessionId;
	}

	public void setSessionId(String sessionId) {
		SessionId = sessionId;
	}

	

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}



	public Date getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}

	public Date getLastAccessedTime() {
		return LastAccessedTime;
	}

	public void setLastAccessedTime(Date lastAccessedTime) {
		LastAccessedTime = lastAccessedTime;
	}

	public Date getTotalTime() {
		return TotalTime;
	}

	public void setTotalTime(Date totalTime) {
		TotalTime = totalTime;
	}

	public String getOrganId() {
		return OrganId;
	}

	public void setOrganId(String organId) {
		OrganId = organId;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}
	
	
	
}
