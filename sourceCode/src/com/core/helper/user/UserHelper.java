/**
* @Title: UserHelper.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.oneworld.core.helper.user   
* @Description:    
* @author guangchao    
* @date 2014-3-26 下午3:12:36   
* @version V1.0 
*/
package com.core.helper.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.ehcache.CacheException;

import org.apache.struts2.ServletActionContext;
import org.jfree.util.Log;

import com.core.dataload.LoadModule;
import com.core.dataload.LoadRole;
import com.core.dataload.LoadUserData;
import com.core.dataload.LoadUserInfo;
import com.core.dataload.LoadUserInfoAndData;
import com.core.db.DBHelper;
import com.core.db.ILoadData;
import com.framework.entity.module.ModuleManager;
import com.framework.entity.role.RoleManager;
import com.framework.entity.user.UserDataManager;
import com.framework.entity.user.UserInfoAndDataManager;
import com.framework.entity.user.UserInfoManager;
import com.util.SessionInfo;
import com.util.Util;

/**
 * @ClassName: UserHelper
 * @Description: 用户工具类
 * @author guangchao
 * @date 2014-3-26 下午3:12:36
 *
 */
public class UserHelper {
	
	public static String GetLanguageCode()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(true);
		Locale locale = (Locale) session.getAttribute("WW_TRANS_I18N_LOCALE");
		if(locale==null){
			locale = Locale.CHINA;
		}
		return locale.toString();
	}
	
	public static String languageCode;  
	 
    static{  
        try {  
            if(languageCode==null)
            	try {
            		languageCode = GetLanguageCode(); 
				} catch (Exception e) {
					languageCode = Locale.CHINA.toString();
				}
        } catch (CacheException e) {  
        	Log.error(e);
        }  
    }  
	
	/**
	 * 
	* @Title: getUserInfoByUserId   
	* @Description:根据用户id 获取所有用户信息    
	* @param @param UserId
	* @param @return UserInfoManager
	* @param @throws Exception    
	* @return UserInfoManager      
	* @author guangchao
	* @date 2014-3-26 下午4:35:28 
	* @throws
	 */
	public static UserInfoManager getUserInfoByUserId(String UserId) throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(true);
		Locale locale = (Locale) session.getAttribute("WW_TRANS_I18N_LOCALE");
		if(locale==null){
			locale = Locale.CHINA;
		}
		
//		UserInfoManager
		UserInfoManager userInfoManager = new UserInfoManager();
		String sql = "SELECT * FROM t_userinfo WHERE UserId=? AND Status !=99";
		ILoadData userInfo = new LoadUserInfo();
		userInfoManager = DBHelper.getEntity(sql, userInfo,UserId);
		
//		UserDataManager
		UserDataManager userDataManager = new UserDataManager();
		String sql0 = "SELECT * FROM t_userdata WHERE UserId=?";
		ILoadData userData = new LoadUserData();
		userDataManager = DBHelper.getEntity(sql0, userData,UserId);
		
//		角色列表
		List<RoleManager> roleList = new ArrayList<RoleManager>();
		ILoadData role = new LoadRole();
		String sql1 = "SELECT * FROM t_role WHERE RoleName IN (SELECT  RoleName FROM t_user_role WHERE UserId=? AND Status = 1) ";
		roleList = DBHelper.getList(sql1, role,UserId);
		
//		模块列表
		List<ModuleManager> moduleList = new ArrayList<ModuleManager>();
		ILoadData module = new LoadModule();
		String sqlModule = "select ModuleId,ModuleName,ModuleParent,ModuleAddress,Status,ModuleNumber,SmallLogo,ModuleRemark from v_moduleml where LanguageCode=? AND Status=1 AND ModuleId in ((SELECT ModuleId FROM t_rightsmanager WHERE RoleName=(SELECT RoleName FROM t_user_role WHERE UserId=? AND Status=1) AND Status=1)) order by ModuleNumber";
		moduleList = DBHelper.getList(sqlModule, module, locale.toString(), UserId);
		
		userInfoManager.setUserDataManager(userDataManager);
		userInfoManager.setRoleList(roleList);
		userInfoManager.setModuleList(moduleList);
		return userInfoManager;
	}
	/**
	 * 
	* @Title: getAllUserInfo   
	* @Description:查询所有用户信息
	* @return
	* @throws Exception
	* @return List<UserInfoAndDataManager>        
	* @author gaoguangchao
	* @date 2014年6月27日 上午10:26:00 
	*
	 */
	public static List<UserInfoAndDataManager> getAllUserInfo() throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append(" select i.*,d.FullName as FullName,");
		sb.append(" (CASE WHEN c.DictName IS NULL then b.DictName ELSE c.DictName END) as UserTypeName,");
		sb.append(" (case d.UserType when 1 then ");
		sb.append(" (select FullName from v_systemml where LanguageCode='"+languageCode+"' limit 1) when 2 then ");
		sb.append(" (select FullName from v_publisherml where PublisherId=d.OrganId and LanguageCode='"+languageCode+"' limit 1) when 3 then ");
		sb.append(" (select FullName from v_channelml where ChannelId=d.OrganId and LanguageCode='"+languageCode+"' limit 1) when 4 then 'Other' end )as OrganIdName");
		sb.append(" from t_userinfo i LEFT JOIN t_userdata d on i.UserId=d.UserId");
		sb.append(" LEFT JOIN t_dict b on(d.UserType=b.DictData1 and b.DictListCode='FTPHome' and b.Status=1)");
		sb.append(" LEFT JOIN t_dictml c on(b.DictId=c.DictId and c.LanguageCode='"+languageCode+"')");
		sb.append(" WHERE");
		sb.append(" i.Status != '99'");
		List<UserInfoAndDataManager> list = new ArrayList<UserInfoAndDataManager>();
		ILoadData LoadUserInfoAndData = new LoadUserInfoAndData();
		list = DBHelper.getList(sb.toString(),LoadUserInfoAndData);
		
		return list;
	}
	/**
	 * 
	* @Title: getAllUserInfo   
	* @Description:查询所有用户信息
	* @param languageCode
	* @return
	* @throws Exception
	* @return List<UserInfoAndDataManager>        
	* @author gaoguangchao
	* @date 2014年6月27日 上午10:27:17 
	*
	 */
	public static List<UserInfoAndDataManager> getAllUserInfo(String languageCode) throws Exception{
		if(Util.isNull(languageCode)){
			languageCode = GetLanguageCode();
		}
		StringBuffer sb = new StringBuffer();
		sb.append(" select i.*,d.FullName as FullName,d.ContactPhone,d.OfficePhone,d.FaxNumber,d.EmailAddress,d.ContactAddress,d.ZipCode,");
		sb.append(" d.OrganId,d.DetpId, d.UserRemark");
		sb.append(" from t_userinfo i LEFT JOIN t_userdata d on i.UserId=d.UserId");
		sb.append(" WHERE");
		sb.append(" i.Status != '99'");
		List<UserInfoAndDataManager> list = new ArrayList<UserInfoAndDataManager>();
		ILoadData LoadUserInfoAndData = new LoadUserInfoAndData();
		list = DBHelper.getList(sb.toString(),LoadUserInfoAndData);
		
		return list;
	}
	
	/**
	 * 
	* @Title: getUserInfoByUserName   
	* @Description:    根据用户账号，获取用户全部信息
	* @param @param UserName
	* @param @return
	* @param @throws Exception    
	* @return UserInfoManager      
	* @author guangchao
	* @date 2014-3-26 下午4:38:16 
	* @throws
	 */
	public static UserInfoManager getUserInfoByUserName(String UserName) throws Exception{
		
//		UserInfoManager
		UserInfoManager userInfoManager = new UserInfoManager();
		String sql = "SELECT * FROM t_userinfo WHERE UserName=? AND Status !=99";
		ILoadData userInfo = new LoadUserInfo();
		userInfoManager = DBHelper.getEntity(sql, userInfo, UserName);
		
		String UserId = userInfoManager.getUserId();
		
//		UserDataManager
		UserDataManager userDataManager = new UserDataManager();
		String sql0 = "SELECT * FROM t_userdata WHERE UserId=?";
		ILoadData userData = new LoadUserData();
		userDataManager = DBHelper.getEntity(sql0, userData, UserId);
		
//		角色列表
		List<RoleManager> roleList = new ArrayList<RoleManager>();
		ILoadData role = new LoadRole();
		String sql1 = "SELECT * FROM t_role WHERE RoleName IN (SELECT  RoleName FROM t_user_role WHERE UserId=?  AND Status = 1) ";
		roleList = DBHelper.getList(sql1, role, UserId);
		
//		模块列表
		List<ModuleManager> moduleList = new ArrayList<ModuleManager>();
		ILoadData module = new LoadModule();
		String sqlModule = "select ModuleId,ModuleName,ModuleParent,ModuleAddress,Status,ModuleNumber,SmallLogo,ModuleRemark from v_moduleml where LanguageCode=? AND Status=1 AND ModuleId in ((SELECT ModuleId FROM t_rightsmanager WHERE RoleName=(SELECT RoleName FROM t_user_role WHERE UserId=? AND Status=1) AND Status=1)) order by ModuleNumber";
		moduleList = DBHelper.getList(sqlModule, module, languageCode, UserId);
		userInfoManager.setUserDataManager(userDataManager);
		userInfoManager.setRoleList(roleList);
		userInfoManager.setModuleList(moduleList);
		return userInfoManager;
	}
	/**
	 * 
	* @Title: getCurUser   
	* @Description:  获取当前登陆人  
	* @param @return    
	* @return UserInfoManager      
	* @author guangchao
	* @date 2014-3-26 下午5:46:53 
	* @throws
	 */
	public static UserInfoManager getCurUser(){
		UserInfoManager userInfoManager = SessionInfo.getCurUser();
		return userInfoManager;
	}
	/**
	 * 
	* @Title: getCurUserId   
	* @Description: 获取当前登陆人ID   
	* @param @return    
	* @return String      
	* @author guangchao
	* @date 2014-3-26 下午5:47:01 
	* @throws
	 */
	public static String getCurUserId(){
		UserInfoManager userInfoManager = SessionInfo.getCurUser();
		String UserId = userInfoManager.getUserId();	
		return UserId;
	}
	
	/**
	 * @throws Exception 
	 * 
	* @Title: isAdminByUserName   
	* @Description:   根据用户账号，判断用户是否属于admin角色组 
	* @param @param UserName
	* @param @return    
	* @return boolean      
	* @author guangchao
	* @date 2014-3-26 下午4:36:54 
	* @throws
	 */
	public static boolean isAdminByUserName(String UserName) throws Exception{
		boolean flag = false;
		UserInfoManager userInfoManager = new UserInfoManager();
		userInfoManager = getUserInfoByUserName(UserName);
		List<RoleManager> roleList = new ArrayList<RoleManager>();
		roleList = userInfoManager.getRoleList();
		for(int i=0;i<roleList.size();i++){
			RoleManager roleManager = new RoleManager();
			roleManager = roleList.get(i);
			String roleName = roleManager.getRoleName();
			if(RoleGroup.ADMIN.getRole().equals(roleName)){
				flag = true;
				break;
			}
		}
		return flag;
		
	}
	/**
	 * @throws Exception 
	 * 
	* @Title: isAdminByUserId   
	* @Description:   根据用户Id，判断用户是否属于admin角色组  
	* @param @param UserId
	* @param @return    
	* @return boolean      
	* @author guangchao
	* @date 2014-3-26 下午4:43:16 
	* @throws
	 */
	public static boolean isAdmin(String UserId) throws Exception{
		boolean flag = false;
		UserInfoManager userInfoManager = new UserInfoManager();
		userInfoManager = getUserInfoByUserId(UserId);
		List<RoleManager> roleList = new ArrayList<RoleManager>();
		roleList = userInfoManager.getRoleList();
		for(int i=0;i<roleList.size();i++){
			RoleManager roleManager = new RoleManager();
			roleManager = roleList.get(i);
			String roleName = roleManager.getRoleName();
			if(RoleGroup.ADMIN.getRole().equals(roleName)){
				flag = true;
				break;
			}
		}
		return flag;
	}
	/**
	 * 
	* @Title: isAuthor   
	* @Description:   根据用户Id，判断用户是否属于author角色组   
	* @param @param UserId
	* @param @return
	* @param @throws Exception    
	* @return boolean      
	* @author guangchao
	* @date 2014-3-26 下午5:33:51 
	* @throws
	 */
	public static boolean isAuthor(String UserId) throws Exception{
		boolean flag = false;
		UserInfoManager userInfoManager = new UserInfoManager();
		userInfoManager = getUserInfoByUserId(UserId);
		List<RoleManager> roleList = new ArrayList<RoleManager>();
		roleList = userInfoManager.getRoleList();
		for(int i=0;i<roleList.size();i++){
			RoleManager roleManager = new RoleManager();
			roleManager = roleList.get(i);
			String roleName = roleManager.getRoleName();
			if(RoleGroup.AUTHOR.getRole().equals(roleName)){
				flag = true;
				break;
			}
		}
		return flag;
	}
	/**
	 * 
	* @Title: isAuthorByUserName   
	* @Description: 根据用户name，判断用户是否属于author角色组      
	* @param @param UserName
	* @param @return
	* @param @throws Exception    
	* @return boolean      
	* @author guangchao
	* @date 2014-3-26 下午5:34:43 
	* @throws
	 */
	public static boolean isAuthorByUserName(String UserName) throws Exception{
		boolean flag = false;
		UserInfoManager userInfoManager = new UserInfoManager();
		userInfoManager = getUserInfoByUserName(UserName);
		List<RoleManager> roleList = new ArrayList<RoleManager>();
		roleList = userInfoManager.getRoleList();
		for(int i=0;i<roleList.size();i++){
			RoleManager roleManager = new RoleManager();
			roleManager = roleList.get(i);
			String roleName = roleManager.getRoleName();
			if(RoleGroup.AUTHOR.getRole().equals(roleName)){
				flag = true;
				break;
			}
		}
		return flag;
	}
	/**
	 * 
	 * @Title: isPublihser   
	 * @Description:   根据用户Id，判断用户是否属于Publihser角色组   
	 * @param @param UserId
	 * @param @return
	 * @param @throws Exception    
	 * @return boolean      
	 * @author guangchao
	 * @date 2014-3-26 下午5:33:51 
	 * @throws
	 */
	public static boolean isPublihser(String UserId) throws Exception{
		boolean flag = false;
		UserInfoManager userInfoManager = new UserInfoManager();
		userInfoManager = getUserInfoByUserId(UserId);
		List<RoleManager> roleList = new ArrayList<RoleManager>();
		roleList = userInfoManager.getRoleList();
		for(int i=0;i<roleList.size();i++){
			RoleManager roleManager = new RoleManager();
			roleManager = roleList.get(i);
			String roleName = roleManager.getRoleName();
			if(RoleGroup.PUBLISHER.getRole().equals(roleName)){
				flag = true;
				break;
			}
		}
		return flag;
	}
	/**
	 * 
	 * @Title: isPublihserByUserName   
	 * @Description: 根据用户name，判断用户是否属于Publihser角色组      
	 * @param @param UserName
	 * @param @return
	 * @param @throws Exception    
	 * @return boolean      
	 * @author guangchao
	 * @date 2014-3-26 下午5:34:43 
	 * @throws
	 */
	public static boolean isPublihserByUserName(String UserName) throws Exception{
		boolean flag = false;
		UserInfoManager userInfoManager = new UserInfoManager();
		userInfoManager = getUserInfoByUserName(UserName);
		List<RoleManager> roleList = new ArrayList<RoleManager>();
		roleList = userInfoManager.getRoleList();
		for(int i=0;i<roleList.size();i++){
			RoleManager roleManager = new RoleManager();
			roleManager = roleList.get(i);
			String roleName = roleManager.getRoleName();
			if(RoleGroup.PUBLISHER.getRole().equals(roleName)){
				flag = true;
				break;
			}
		}
		return flag;
	}
	/**
	 * 
	 * @Title: isPublihserManager   
	 * @Description:   根据用户Id，判断用户是否属于PublihserManager角色组   
	 * @param @param UserId
	 * @param @return
	 * @param @throws Exception    
	 * @return boolean      
	 * @author guangchao
	 * @date 2014-3-26 下午5:33:51 
	 * @throws
	 */
	public static boolean isPublihserManager(String UserId) throws Exception{
		boolean flag = false;
		UserInfoManager userInfoManager = new UserInfoManager();
		userInfoManager = getUserInfoByUserId(UserId);
		List<RoleManager> roleList = new ArrayList<RoleManager>();
		roleList = userInfoManager.getRoleList();
		for(int i=0;i<roleList.size();i++){
			RoleManager roleManager = new RoleManager();
			roleManager = roleList.get(i);
			String roleName = roleManager.getRoleName();
			if(RoleGroup.PUBLISHERMANAGER.getRole().equals(roleName)){
				flag = true;
				break;
			}
		}
		return flag;
	}
	/**
	 * 
	 * @Title: isPublihserManagerByUserName   
	 * @Description: 根据用户name，判断用户是否属于PublihserManager角色组      
	 * @param @param UserName
	 * @param @return
	 * @param @throws Exception    
	 * @return boolean      
	 * @author guangchao
	 * @date 2014-3-26 下午5:34:43 
	 * @throws
	 */
	public static boolean isPublihserManagerByUserName(String UserName) throws Exception{
		boolean flag = false;
		UserInfoManager userInfoManager = new UserInfoManager();
		userInfoManager = getUserInfoByUserName(UserName);
		List<RoleManager> roleList = new ArrayList<RoleManager>();
		roleList = userInfoManager.getRoleList();
		for(int i=0;i<roleList.size();i++){
			RoleManager roleManager = new RoleManager();
			roleManager = roleList.get(i);
			String roleName = roleManager.getRoleName();
			if(RoleGroup.PUBLISHERMANAGER.getRole().equals(roleName)){
				flag = true;
				break;
			}
		}
		return flag;
	}
	/**
	 * 
	 * @Title: isDev   
	 * @Description:   根据用户Id，判断用户是否属于DEV角色组   
	 * @param @param UserId
	 * @param @return
	 * @param @throws Exception    
	 * @return boolean      
	 * @author guangchao
	 * @date 2014-3-26 下午5:33:51 
	 * @throws
	 */
	public static boolean isDev(String UserId) throws Exception{
		boolean flag = false;
		UserInfoManager userInfoManager = new UserInfoManager();
		userInfoManager = getUserInfoByUserId(UserId);
		List<RoleManager> roleList = new ArrayList<RoleManager>();
		roleList = userInfoManager.getRoleList();
		for(int i=0;i<roleList.size();i++){
			RoleManager roleManager = new RoleManager();
			roleManager = roleList.get(i);
			String roleName = roleManager.getRoleName();
			if(RoleGroup.DEV.getRole().equals(roleName)){
				flag = true;
				break;
			}
		}
		return flag;
	}
	/**
	 * 
	 * @Title: isDevByUserName   
	 * @Description: 根据用户name，判断用户是否属于Dev角色组      
	 * @param @param UserName
	 * @param @return
	 * @param @throws Exception    
	 * @return boolean      
	 * @author guangchao
	 * @date 2014-3-26 下午5:34:43 
	 * @throws
	 */
	public static boolean isDevByUserName(String UserName) throws Exception{
		boolean flag = false;
		UserInfoManager userInfoManager = new UserInfoManager();
		userInfoManager = getUserInfoByUserName(UserName);
		List<RoleManager> roleList = new ArrayList<RoleManager>();
		roleList = userInfoManager.getRoleList();
		for(int i=0;i<roleList.size();i++){
			RoleManager roleManager = new RoleManager();
			roleManager = roleList.get(i);
			String roleName = roleManager.getRoleName();
			if(RoleGroup.DEV.getRole().equals(roleName)){
				flag = true;
				break;
			}
		}
		return flag;
	}
}
