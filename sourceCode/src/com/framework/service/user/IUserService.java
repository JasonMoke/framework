package com.framework.service.user;

import java.util.List;

import com.framework.entity.user.UserInfoAndDataManager;
import com.orm.BaseService;

public interface IUserService extends BaseService<UserInfoAndDataManager>{

	List<String> findAuthorities(String userid);
//	
//    public List getAllUser();
//   
//    public Pagination<UserInfoAndDataManager> getAllUserList(Page page,UserInfoAndDataManager userInfoAndData);	
//	public Object detailUser(String UserId);
//	
//	public boolean delete(String id);
//	
//	public boolean updateUserPwd(UserInfoManager userInfoManager);
//	
////	public int getUserInfoForValidate(UserInfoManager userInfoManager);
//
//	/**
//	 * @return    
//	* @Title: getUserInfoForLogin   
//	* @Description:    
//	* @param @param username
//	* @param @param password    
//	* @return void      
//	* @author guangchao
//	* @date 2013-12-23 下午1:47:42 
//	* @throws   
//	*/ 
//	
//	public UserInfoManager getUserInfoForLogin(String username, String password);
//	
//	
//	public Pagination<UserInfoAndDataManager> getUserListByOrganId(Page page,UserInfoAndDataManager userInfoAndData);
//	
//	public Pagination<UserInfoAndDataManager> getUserByCondition(Page page,UserInfoAndDataManager userInfoAndData);
//	
//	public UserInfoManager getUserById(String userId);
//	/**
//	 * 
//	* @Title: getUserDataByUserId   
//	* @Description:    
//	* @param @param userId
//	* @param @return    
//	* @return UserInfoManager      
//	* @author guangchao
//	* @date 2013-12-24 下午1:27:37 
//	* @throws
//	 */
//	public UserDataManager getUserDataByUserId(String userId);
//	public boolean addUserInfoAndData(UserInfoManager userInfoManager,UserDataManager userDataManager);
//	public boolean addUserInfo(UserInfoManager userInfoManager);
//	public boolean addUserData(UserDataManager userDataManager);
//
//	/**   
//	* @Title: updateUserInfo  
//	* @Description:    
//	* @param @param userInfo
//	* @param @return    
//	* @return boolean      
//	* @author guangchao
//	* @date 2013-12-31 上午11:43:53 
//	* @throws   
//	*/ 
//	
//	public boolean updateUserInfo(UserInfoManager userInfo);
//	/**   
//	 * @Title: updateUserData   
//	 * @Description:    
//	 * @param @param userData
//	 * @param @return    
//	 * @return boolean      
//	 * @author guangchao
//	 * @date 2013-12-31 上午11:43:53 
//	 * @throws   
//	 */ 
//	
//	public boolean updateUserData(UserDataManager userData);
//
//	/**   
//	* @Title: updateUserStatus   
//	* @Description:    
//	* @param @param userIds
//	* @param @return    
//	* @return boolean      
//	* @author guangchao
//	* @date 2013-12-31 下午1:40:12 
//	* @throws   
//	*/ 
//	
//	public boolean updateUserStatus(String userIds,String userStatus);
//
//	/**   
//	* @Title: getUserInfoForUpdatePwd   
//	* @Description:    
//	* @param @param userInfo
//	* @param @return    
//	* @return int      
//	* @author guangchao
//	* @date 2013-12-31 下午4:44:43 
//	* @throws   
//	*/ 
//	
////	public int getUserInfoForUpdatePwd(UserInfoManager userInfo);
//
//	/**   
//	* @Title: changeStatus   
//	* @Description:    
//	* @param @param userInfo
//	* @param @return    
//	* @return Boolean      
//	* @author guangchao
//	* @date 2014-1-1 下午5:05:54 
//	* @throws   
//	*/ 
//	
//	public Boolean changeStatus(UserInfoManager userInfo);
//
//	/**   
//	* @Title: getUserInfoForValidateUserStatus   
//	* @Description:    
//	* @param @param userInfo
//	* @param @return    
//	* @return int      
//	* @author guangchao
//	* @date 2014-1-1 下午6:44:34 
//	* @throws   
//	*/ 
//	
////	public int getUserInfoForValidateUserStatus(UserInfoManager userInfo);
//
//	/**   
//	* @Title: getUserInfoByUserId   
//	* @Description:    
//	* @param @param userId
//	* @param @return    
//	* @return UserInfoManager      
//	* @author guangchao
//	* @date 2014-1-2 下午12:19:44 
//	* @throws   
//	*/ 
//	
//	public UserInfoManager getUserInfoByUserId(String userId);

}
