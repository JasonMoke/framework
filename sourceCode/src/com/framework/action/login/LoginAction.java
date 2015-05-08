package com.framework.action.login;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;

import com.util.Global;
import com.util.SessionInfo;
import com.core.base.BaseAction;
import com.core.shiro.AuthenticationToken;
import com.core.shiro.Principal;
import com.framework.entity.module.ModuleManager;
import com.framework.entity.role.RoleManager;
import com.framework.entity.systemmanager.Systemmanager;
import com.framework.entity.user.UserDataManager;
import com.framework.entity.user.UserInfoManager;
import com.framework.service.module.IModuleService;
import com.framework.service.role.IRoleService;
import com.framework.service.systemmanager.SystemmanagerService;
import com.framework.service.user.IUserService;
import com.opensymphony.xwork2.ActionContext;




@Controller
public class LoginAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6230659541232145020L;
	@Resource(name="userService")
	private IUserService userService;
	@Resource(name="roleServiceImp")
	private IRoleService roleServiceImp;
	@Resource(name="moduleServiceImp")
	private IModuleService moduleServiceImp;
//	应用管理
	@Resource(name = "systemmanagerService")
    private SystemmanagerService systemmanagerService;
//	解决方案管理
	private String localeStr;
	private String username;
	private String password;
	private String UserNumber;
	private String EBookNumber;
	private String PublisherNumber;
	private String ChannelNumber;
	private UserInfoManager userInfo;
    private String loginResult  = "";
    private List<ModuleManager> moduleList=null;
    private List<Systemmanager> listSystemManager=null;
	


	
   
	/**
     * 
    * @Title: loginPage   
    * @Description: 打开登录页面
    * @param @return    
    * @return String      
    * @author guangchao
    * @date 2013-12-23 上午10:59:50 
    * @throws
     */
    public String loginPage() throws Exception {
    	return "success";
    }
    /**
     * 
    * @Title: home   
    * @Description: 进入首页   
    * @param @return
    * @param @throws Exception    
    * @return String      
    * @author guangchao
    * @date 2014-3-11 上午11:51:59 
    * @throws
     */
    public String home() throws Exception {
    	return "success";
    }
    /**
     * 
    * @Title: logout   
    * @Description:注销    
    * @param @return
    * @param @throws Exception    
    * @return String      
    * @author guangchao
    * @date 2013-12-24 上午10:18:15 
    * @throws
     */
    public String logout() throws Exception {
    	HttpSession session=getSession(true);
   		session.removeAttribute(Global.SESSION_USER); 
   		session.removeAttribute("sessionKey"); 
   		session.removeAttribute("UserId"); 
   		session.removeAttribute("UserFullName"); 
   		session.removeAttribute("systemFullName");
   		session.setMaxInactiveInterval(-1);
   		SessionInfo.setCurUser(null);
   		Subject currentUser = SecurityUtils.getSubject(); 
   		currentUser.logout();
    	return "success";
    }
    /**
     * 
    * @Title: submitLogin   
    * @Description: 提交登录，校验  
    * @param @return
    * @param @throws Exception    
    * @return String      
    * @author guangchao
    * @date 2013-12-23 上午11:55:02 
    * @throws
     */
    public String submitLogin() throws Exception {
    	String result = "";
    	boolean flag = true;
    	StringBuffer sb = new StringBuffer();
    	Subject currentUser = SecurityUtils.getSubject();  
    	 AuthenticationToken token = null;
    	 Session sessionShiro = null;
         if (!currentUser.isAuthenticated()) {  
        	 token = new AuthenticationToken(userInfo.getUserName(), userInfo.getUserPwd()); 
             try {  
            	 currentUser.login(token);  
            	 sessionShiro = currentUser.getSession();
             } catch (UnknownAccountException uae) {  
            	 sb.append(this.getText("user_not_exist"));
            	 flag = false;
//                 log.info("There is no user with username of " + token.getPrincipal());  
//                 return INPUT;  
             } catch (IncorrectCredentialsException ice) {  
            	 sb.append(this.getText("password_error"));
            	 flag = false;
//                 log.info("Password for account " + token.getPrincipal() + " was incorrect!");  
//                 return INPUT;  
             } catch (LockedAccountException lae) {  
            	 sb.append(this.getText("user_is_disabled"));
 				 flag = false;
//                 log.info("The account for username " + token.getPrincipal() + " is locked.  " +  
//                         "Please contact your administrator to unlock it.");  
//                 return INPUT;  
             }  
             // ... catch more exceptions here (maybe custom ones specific to your application?  
             catch (AuthenticationException ae) {  
                //...  
             }  
         }  
         
         if(currentUser.isAuthenticated()&&flag){
        	UserInfoManager user_r = new UserInfoManager();
         	UserDataManager user_data_r = new UserDataManager();
        	HttpSession session = getSession(true);
        	Principal p = (Principal) currentUser.getPrincipal();
     		String userId = p.getUserid();
     		List<RoleManager> roleList = new ArrayList<RoleManager>();
     		//根据userId获取用户扩展信息
     		user_r = SessionInfo.getCurUser();
     		user_data_r = userService.findObjectByCondition("getUserDataByUserId", userId);
     		String userFullName = user_data_r.getFullName();
     		//根据userId获取用户角色信息
     		roleList = roleServiceImp.getRoleByUserId(userId);
     		Locale locale = getLocale();
     		String SystemCode = getParameter("SystemCode");
     		Map<String, Object> map = new HashMap<String, Object>();
     		map.put("userId", userId);
     		map.put("locale", locale);
     		map.put("SystemCode", SystemCode);
     		user_r.setRoleList(roleList);
     		user_r.setUserDataManager(user_data_r);
     		session.setAttribute(Global.SESSION_USER, user_r);
     		SessionInfo.setCurUser(user_r);
     		session.setAttribute("sessionKey", "userInfo");
     		session.setAttribute("UserFullName", userFullName);
     		session.setAttribute("UserId", userId);
     		session.setAttribute("sessionShiro", sessionShiro);
     		setUserInfo(user_r);
     		result = "success";
     		addLog(true,"登录成功");
     	}else{
     		setLoginResult(sb.toString());
     		result = "error";
     		addLog(false,"登录失败");
     		this.addActionError( sb.toString());
     	}
    	return result;
    }
    /**
     * 
    * @Title: changeLanguage   
    * @Description:    更改语言环境
    * @param @return    
    * @return String      
    * @author guangchao
    * @date 2013-12-30 上午9:55:34 
    * @throws
     */
    public String changeLanguage(){
    	Locale localeReq = getLocale();
    	String locale = getParameter("request_locale");
    	if(locale!=null&&!"".equals(locale)){
    		if("zh_CN".equals(locale)){
    			localeReq = Locale.CHINA;
    		}else if("en_US".equals(locale)){
    			localeReq = Locale.US;
    		}
    	}
    	HttpSession session=getHttpSession(true);
		session.setAttribute("WW_TRANS_I18N_LOCALE", localeReq);
		ActionContext.getContext().setLocale( localeReq);
		setLocaleStr(locale);
    	return "success";
    }
    
    /**
	 * @return the moduleList
	 */
	public List<ModuleManager> getModuleList() {
		return moduleList;
	}

	/**
	 * @param moduleList the moduleList to set
	 */
	public void setModuleList(List<ModuleManager> moduleList) {
		this.moduleList = moduleList;
	}

	/**
	 * @return the localeStr
	 */
	public String getLocaleStr() {
		return localeStr;
	}

	/**
	 * @param localeStr the localeStr to set
	 */
	public void setLocaleStr(String localeStr) {
		this.localeStr = localeStr;
	}


	/**
	 * @return the userInfo
	 */
	public UserInfoManager getUserInfo() {
		return userInfo;
	}

	/**
	 * @param userInfo the userInfo to set
	 */
	public void setUserInfo(UserInfoManager userInfo) {
		this.userInfo = userInfo;
	}



//	public void setLogin(ILoginservice login) {
//		this.login = login;
//	}

	/**
	 * @return the loginResult
	 */
	public String getLoginResult() {
		return loginResult;
	}

	/**
	 * @param loginResult the loginResult to set
	 */
	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the userNumber
	 */
	public String getUserNumber() {
		return UserNumber;
	}
	/**
	 * @param userNumber the userNumber to set
	 */
	public void setUserNumber(String userNumber) {
		UserNumber = userNumber;
	}
	/**
	 * @return the eBookNumber
	 */
	public String getEBookNumber() {
		return EBookNumber;
	}
	/**
	 * @param eBookNumber the eBookNumber to set
	 */
	public void setEBookNumber(String eBookNumber) {
		EBookNumber = eBookNumber;
	}
	/**
	 * @return the publisherNumber
	 */
	public String getPublisherNumber() {
		return PublisherNumber;
	}
	/**
	 * @param publisherNumber the publisherNumber to set
	 */
	public void setPublisherNumber(String publisherNumber) {
		PublisherNumber = publisherNumber;
	}
	/**
	 * @return the channelNumber
	 */
	public String getChannelNumber() {
		return ChannelNumber;
	}
	/**
	 * @param channelNumber the channelNumber to set
	 */
	public void setChannelNumber(String channelNumber) {
		ChannelNumber = channelNumber;
	}
	 /**
	 * @return the listSystemManager
	 */
	public List<Systemmanager> getListSystemManager() {
		return listSystemManager;
	}
	/**
	 * @param listSystemManager the listSystemManager to set
	 */
	public void setListSystemManager(List<Systemmanager> listSystemManager) {
		this.listSystemManager = listSystemManager;
	}

}
