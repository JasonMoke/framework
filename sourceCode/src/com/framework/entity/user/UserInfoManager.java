/**
 * @Title: UserInfoManager 
 * @Copyright 2010 -2013 CreativeWise 
 * @Package: com.framework.entity.user 
 * @Description: UserInfoManager的实体类 
 * @author: gaoguangchao 
 * @date: 2013-12-20 12:51:55 
 * @version V1.0  
 */ 
package com.framework.entity.user;



import java.util.Date;
import java.util.List;

import com.framework.entity.module.ModuleManager;
import com.framework.entity.role.RoleManager;
import com.orm.BaseEntity;


/**
 * @ClassName: UserInfoManager 
 * @Description: UserInfoManager的实体类 
 * @author: gaoguangchao 
 * @date: 2013-12-20 12:51:55 
 */ 

public class UserInfoManager extends BaseEntity{

	    private String UserId;
 
	    private String UserName;

	    private String UserPwd;

	    private String UserKey;

	    private Integer Status;

	    private String CreatePerson;

	    private Date CreateTime;

	    private String UpdatePerson;

	    private Date UpdateTime;

	    private String IsReserved;
	    
	    private List<RoleManager> RoleList;

	    private List<ModuleManager> ModuleList;
	    
	    private UserDataManager UserDataManager;
	    private String Account;
	    

		public String getAccount() {
			return Account;
		}

		public void setAccount(String Account) {
			this.Account = Account;
		}
	    
		public String getUserId() {
			return UserId;
		}

		public void setUserId(String userId) {
			UserId = userId;
		}

		public String getUserName() {
			return UserName;
		}

		public void setUserName(String userName) {
			UserName = userName;
		}

		public String getUserPwd() {
			return UserPwd;
		}

		public void setUserPwd(String userPwd) {
			UserPwd = userPwd;
		}

		public String getUserKey() {
			return UserKey;
		}

		public void setUserKey(String userKey) {
			UserKey = userKey;
		}

		public Integer getStatus() {
			return Status;
		}

		public void setStatus(Integer status) {
			Status = status;
		}

		public String getCreatePerson() {
			return CreatePerson;
		}

		public void setCreatePerson(String createPerson) {
			CreatePerson = createPerson;
		}

		public Date getCreateTime() {
			return CreateTime;
		}

		public void setCreateTime(Date createTime) {
			CreateTime = createTime;
		}

		public String getUpdatePerson() {
			return UpdatePerson;
		}

		public void setUpdatePerson(String updatePerson) {
			UpdatePerson = updatePerson;
		}

		public Date getUpdateTime() {
			return UpdateTime;
		}

		public void setUpdateTime(Date updateTime) {
			UpdateTime = updateTime;
		}

		public String getIsReserved() {
			return IsReserved;
		}

		public void setIsReserved(String isReserved) {
			IsReserved = isReserved;
		}

		public List<RoleManager> getRoleList() {
			return RoleList;
		}

		public void setRoleList(List<RoleManager> roleList) {
			RoleList = roleList;
		}

		public List<ModuleManager> getModuleList() {
			return ModuleList;
		}

		public void setModuleList(List<ModuleManager> moduleList) {
			ModuleList = moduleList;
		}

		public UserDataManager getUserDataManager() {
			return UserDataManager;
		}

		public void setUserDataManager(UserDataManager userDataManager) {
			UserDataManager = userDataManager;
		}
	    
	    
}

