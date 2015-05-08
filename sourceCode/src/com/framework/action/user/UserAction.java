package com.framework.action.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.core.base.BaseAction;
import com.framework.entity.role.UserRoleManager;
import com.framework.entity.user.UserDataManager;
import com.framework.entity.user.UserInfoAndDataManager;
import com.framework.entity.user.UserInfoManager;
import com.framework.service.organ.OrganService;
import com.framework.service.module.IModuleService;
import com.framework.service.role.IRoleService;
import com.framework.service.user.IUserService;
import com.framework.service.user.UserrolemanagerService;
import com.orm.Page;
import com.orm.Sort;
import com.util.GetConfig;
import com.util.Global;
import com.util.SessionInfo;
import com.util.Util;

@Controller
public class UserAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 55188743203873945L;
	@Resource(name = "userService")
	private IUserService userService;
	@Resource(name = "roleServiceImp")
	private IRoleService roleServiceImp;
	@Resource(name = "moduleServiceImp")
	private IModuleService moduleServiceImp;
	@Resource(name = "userrolemanagerService")
	private UserrolemanagerService userrolemanagerService;
	@Resource(name = "organService")
	private OrganService organService;
	private String fileFileName;
	String outPath;
	private String isReapeat;// 用户名是否存在 1存在，0不存在
	private String actionResult;
	List<UserInfoAndDataManager> list = null;
	private Page<UserInfoAndDataManager> page;

	/* 当前页号及每页数 */
	private int currentPage;
	private int pageSize;
	private UserInfoManager userInfo;
	private UserDataManager userData;
	private UserInfoAndDataManager userInfoAndData;
	Logger log = Logger.getLogger(this.getClass());

	
	/**
	 * 
	 * @Title: getAllUser
	 * @Description: 获取用户列表 查询date表
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author guangchao
	 * @date 2013-12-24 下午2:38:59
	 * @throws
	 */
	public String getAllUser() throws Exception {
		String isBack = getParameter("isBack");
		page = new Page<UserInfoAndDataManager>();
		page.setPageSize(pageSize);
        //设置排序
        page.addOrder(Sort.asc("UpdateTime"));
		page.setCurrentPage(currentPage);
		if("1".equals(isBack)){
			userInfo = null;
		}
		page = userService.findByCondition("getAllUserList", userInfoAndData, page);
		list = page.getResultList();
		String actionResult = getActionResult();
		setActionResult(actionResult);
		return "success";
	}
	
	/**
	 * 
	 * @Title: toUserAccount
	 * @Description: 跳转到分配账号页面,先查询，如果有数据就是更新操作
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author lishanhe
	 * @date 2013-12-31 下午4:16:34
	 * @throws
	 */
	public String toUserAccount() throws Exception {
		String UserId = getParameter("UserId");
		setAttribute("UserId", UserId);
		userInfo = userService.findObjectByCondition("getUserInfoByUserId", UserId);
		return "success";
	}
	
	
	/**
	 * 
	 * @Title: addUserAccount
	 * @Description: 人员分配账号,向userinfo表里面添加
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author lishanhe
	 * @date 2013-12-31 下午4:16:34
	 * @throws
	 */

	public String addUserAccount() throws Exception {
		boolean result = true;
		String flag = "error";
		UserInfoManager userInfoManager = SessionInfo.getCurUser();
		String CreatePerson = userInfoManager.getUserId();
		String passWordMd5 = Util.MD5(userInfo.getUserPwd());
		userInfo.setUserPwd(passWordMd5);
		userInfo.setCreatePerson(CreatePerson);
		userInfo.setUpdatePerson(CreatePerson);
		userInfo.setCreateTime(new Date());
		userInfo.setUpdateTime(new Date());
		try {
			UserInfoAndDataManager u = userService.findObjectByCondition("detailUser", userInfo.getUserId());
			if(u.getUserName().equals(userInfo.getUserName())){
				u.setStatus(userInfo.getStatus());
				userService.updateByCondition("updateUserInfo", u);
			}else{
				userService.addObject("insertUserInfo", userInfo);
			}
		} catch (Exception e) {
			result=false;
			log.error(e);
		}
		if (result) {
			flag = "success";
			getResponse().addHeader("flag", "success");
		} else {
			flag = "error";
			getResponse().addHeader("flag", "error");
		}
		setActionResult(flag);
		return flag;
	}
	
	
	/**
	 * Title:logoutAccount
	 * 注销账号 将info表status变为0
	 * 删除账号 将info表status变为99
	 * @return
	 */
	public String logoutAccount(){
		boolean result = true;
		String flag = "error";
		UserInfoManager userInfoManager = SessionInfo.getCurUser();
		String UpdatePerson = userInfoManager.getUserId();
		String userId = getParameter("UserId");
		String tip = getParameter("tip");
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("UserId", userId);
		param.put("UpdatePerson",UpdatePerson);
		param.put("UpdateTime",new Date());
		if(Util.isNotNull(tip) && tip.equals("del")){
			int status = 99;
			param.put("Status", status);
		}else if(Util.isNotNull(tip) && tip.equals("logout")){
			int status = 0;
			param.put("Status", status);
		}
		try {
			userService.updateByCondition("updateAccountById", param);
		} catch (Exception e) {
			result=false;
			log.error(e);
		}
		if (result) {
			flag = "success";
		} else {
			flag = "error";
		}
		setActionResult(flag);
		return flag;
	}
	
	
	
	/**
	 * 
	 * @Title: getUserById
	 * @Description: 根据用户ID获取用户信息
	 * @param @param UserId
	 * @param @return
	 * @return UserInfoManager
	 * @author guangchao
	 * @date 2014-1-2 下午12:14:48
	 * @throws
	 */
	public UserInfoManager getUserById(String userId) {
		
		HttpSession session = getSession(true);
		Locale locale = (Locale) session.getAttribute("WW_TRANS_I18N_LOCALE");
		if (locale == null) {
			locale = Locale.CHINA;
		}
		UserInfoManager user_r = new UserInfoManager();
//		UserDataManager user_data_r = new UserDataManager();
//		List<RoleManager> roleList = new ArrayList<RoleManager>();
//		List<ModuleManager> moduleList = new ArrayList<ModuleManager>();
		user_r = userService.findObjectByCondition("getUserInfoByUserId", userId);
//		user_data_r = userService.findObjectByCondition("getUserDataByUserId", userId);
//		roleList = roleServiceImp.getRoleByUserId(userId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("locale", locale);
		//map.put("SystemCode", SystemCode);
//		moduleList = moduleServiceImp.findByCondition("getAllModulesForUser", map);
//		user_r.setModuleList(moduleList);
//		user_r.setUserDataManager(user_data_r);
//		user_r.setRoleList(roleList);
		return user_r;

	}

	/**
	 * 
	 * @Title: AddUser
	 * @Description: 新增用户
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author guangchao
	 * @date 2013-12-24 下午2:39:17
	 * @throws
	 */
	public String addUser() throws Exception {
		String base =  GetConfig.getFileoutputpath();
		boolean result = true;
		String flag = "error";
		String userId = UUID.randomUUID().toString();
		UserInfoManager userInfoManager = SessionInfo.getCurUser();
		String CreatePerson = userInfoManager.getUserId();
		
//		对密码MD5加密
		//具体字段需要处理
		outPath=base+fileFileName;
		if(fileFileName !="" && fileFileName.length()>=1)
		{
		File imga=new File(outPath);
		FileInputStream fin = new FileInputStream(outPath);
		byte[] nbf = new byte[(int) imga.length()];
		fin.read(nbf);          
		fin.close();  
		userData.setUserLogo(nbf);
		 }
		if(userData.getOrganId()==null || "".equals(userData.getOrganId()) ){
			userData.setOrganId("neizhi");
		}
		userData.setUserId(userId);
		userData.setStatus(1);
		userData.setUpdatePerson(CreatePerson);
		userData.setUpdateTime(new Date());
		userData.setCreatePerson(CreatePerson);
		userData.setCreateTime(new Date());
		try {
			userService.addObject("insertUserData", userData);
			addLog(true,"新增用户成功");
		} catch (Exception e) {
			result=false;
			log.error(e);
			addLog(false,e);
		}
		if (result) {
			flag = "success";
		} else {
			flag = "error";
		}
		setActionResult(flag);
		return flag;
	}

	/**
	 * 
	 * @Title: updateUser
	 * @Description: 修改用户
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author guangchao
	 * @date 2013-12-31 上午11:35:43
	 * @throws
	 */
	public String updateUser() throws Exception {
		String base =  GetConfig.getFileoutputpath();
		boolean result = true;
		String flag = "error";
		UserInfoManager userInfoManager = SessionInfo.getCurUser();
		String UpdatePerson = userInfoManager.getUserId();
		String UserId = userInfo.getUserId();
		String newFileName=getParameter("fileFileName");
		UserInfoAndDataManager userInfoAndDataManager = (UserInfoAndDataManager) userService
				.findObjectByCondition("findUserDataById", userInfo.getUserId());
		if(Util.isNotNull(newFileName)){	
			outPath=base+newFileName;
			File imga=new File(outPath);
			FileInputStream fin = new FileInputStream(outPath);
			byte[] nbf = new byte[(int) imga.length()];
			fin.read(nbf);          
			fin.close();  
			userData.setUserLogo(nbf);
		}else if(userInfoAndDataManager.getUserLogo()!=null){
			userData.setUserLogo(userInfoAndDataManager.getUserLogo());
		}
		userData.setUpdatePerson(UpdatePerson);
		userData.setUpdateTime(new Date());
		userData.setStatus(1);
		userData.setUserId(UserId);
		try {
			userService.updateEntity("updateUserData", userData);
			addLog(true,"修改用户成功");
		} catch (Exception e) {
			result = false;
			log.error(e);
			addLog(false,e);
		}
		if (result) {
			flag = "success";
		} else {
			flag = "error";
		}
		setActionResult(flag);
		return flag;
	}
	/**
	 * 
	* @Title: updateHomeUser   
	* @Description: 首页修改用户信息 
	* @param @return
	* @param @throws Exception      
	* @return String   
	* @author zhaojie
	* @date 2014-3-27 下午3:25:52 
	* @throws
	 */
	public String updateHomeUser() throws Exception {
		String base =  GetConfig.getFileoutputpath();
		boolean result = true;
		String flag = "error";
		UserInfoManager userInfoManager = SessionInfo.getCurUser();
		String UpdatePerson = userInfoManager.getUserId();
		String UserId = userInfo.getUserId();
		
		String newFileName=getParameter("fileFileName");
//		对密码MD5加密
		UserInfoAndDataManager userInfoAndDataManager = userService
				.findEntityByCondition("detailUser", userInfo.getUserId());
		if(Util.isNotNull(newFileName)){	
			outPath=base+newFileName;
			File imga=new File(outPath);
			FileInputStream fin = new FileInputStream(outPath);
			byte[] nbf = new byte[(int) imga.length()];
			fin.read(nbf);          
			fin.close();  
			userData.setUserLogo(nbf);
		}else if(userInfoAndDataManager.getUserLogo()!=null){
			userData.setUserLogo(userInfoAndDataManager.getUserLogo());
		}
		
		String passWordMd5 = Util.MD5(userInfo.getUserPwd());
		userInfo.setUserPwd(passWordMd5);
		userData.setUpdatePerson(UpdatePerson);
		userData.setUpdateTime(new Date());
		userInfo.setUpdatePerson(UpdatePerson);
		userInfo.setUpdateTime(new Date());
		userData.setUserId(UserId);
		try {
			userService.updateEntity("updateUserInfo", userInfo);
			userService.updateEntity("updateUserData", userData);
			addLog(true,"修改用户成功");
		} catch (Exception e) {
			result = false;
			log.error(e);
			addLog(false,e);
		}
		if (result) {
			flag = "success";
		} else {
			flag = "error";
		}
		HttpSession session = getSession(true);
		session.setAttribute(Global.SESSION_USER, userInfo);
		SessionInfo.setCurUser(userInfo);
		setActionResult(flag);
		return flag;
	}
	

	/**
	 * 
	 * @Title: detailUser
	 * @Description: 查看用户
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author guangchao
	 * @date 2013-12-25 上午11:49:19
	 * @throws
	 */
	public String detailUser() throws Exception {
		String UserId = getParameter("UserId");
		UserInfoAndDataManager userInfoAndDataManager = userService.findEntityByCondition("findUserDataById", UserId);
		setUserInfoAndData(userInfoAndDataManager);
		return "success";
	}

	/**
	 * 
	 * @Title: modifyUser
	 * @Description:修改用户
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author guangchao
	 * @date 2013-12-25 下午1:00:55
	 * @throws
	 */
	public String modifyUser() throws Exception {
		String UserId = getParameter("UserId");
		UserInfoAndDataManager userInfoAndDataManager = userService.findEntityByCondition("findUserDataById", UserId);
		setUserInfoAndData(userInfoAndDataManager);
		return "success";
	}
	/**
	 * 
	* @Title: modifyUser   
	* @Description: 首页修改用户
	* @param @return
	* @param @throws Exception      
	* @return String   
	* @author zhaojie
	* @date 2014-3-27 下午3:07:40 
	* @throws
	 */
	public String modifyHomeUser() throws Exception {;
		UserInfoManager userInfoManager = SessionInfo.getCurUser();
//		对密码MD5加密
		UserInfoAndDataManager userInfoAndDataManager = userService.findEntityByCondition("detailUser", userInfoManager.getUserId());
		setUserInfoAndData(userInfoAndDataManager);
		return "success";
	}
	/**
	 * 
	 * @Title: deleteUser
	 * @Description: 删除用户
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author guangchao
	 * @date 2013-12-31 下午1:27:05
	 * @throws
	 */
	public String deleteUser() throws Exception {
		String UserId = getParameter("UserId");
		boolean result = true;
		String flag = "error";
		UserInfoManager userInfoManager = SessionInfo.getCurUser();
		String[] UserIds = Util.stringToArray(UserId);
		Map<String,Object> t = new HashMap<String, Object>();
		t.put("UserIds",UserIds);
		t.put("Status","99");
		t.put("UpdateTime",new Date());
		t.put("UpdatePerson",userInfoManager.getUserName());
		try {
			userService.updateEntity("updateUserStatus", t);
			addLog(true,"删除用户成功");
		} catch (Exception e) {
			result = false;
			log.error(e);
			addLog(false,e);
		}
		if (result) {
			flag = "success";
		} else {
			flag = "error";
		}
		setActionResult(flag);
		return flag;
	}

	/**
	 * 
	 * @Title: validateUser
	 * @Description: 校验用户是否存在
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author guangchao
	 * @date 2013-12-26 下午4:23:59
	 * @throws
	 */
	public void validateUser() throws Exception {
		
		String UserId = getParameter("UserId");
		String UserName = getParameter("UserName");
		if(userInfo!=null){
			userInfo.setUserId(UserId);
		}else{
			userInfo = new UserInfoManager();
			userInfo.setUserName(UserName);
			userInfo.setUserId(UserId);
			
		}	
		int count = userService.findObjectByCondition("getUserInfoForValidate", userInfo);
		Boolean result = true;
		if (count > 0) {
			result = false;
		}
		ServletActionContext.getResponse().getWriter().write(result.toString());
	}

	/**
	 * 
	 * @Title: validateUserPwd
	 * @Description: 修改密码时 验证旧密码是否正确
	 * @param @throws Exception
	 * @return void
	 * @author guangchao
	 * @date 2013-12-31 下午4:39:36
	 * @throws
	 */

	public void validateUserPwd() throws Exception {
		
		String UserId = getParameter("UserId");
		String UserName = getParameter("UserName");
		String UserPwd = getParameter("UserPwd");
//		对密码MD5加密
		String passWordMd5 = Util.MD5(UserPwd);
		UserInfoManager userInfo = new UserInfoManager();
		userInfo.setUserId(UserId);
		userInfo.setUserPwd(passWordMd5);
		userInfo.setUserName(UserName);
		int count = userService.getCountBySqlAndParam("getUserInfoForValidatePwd", userInfo);
		Boolean result = false;
		if (count > 0) {
			result = true;
		}
		ServletActionContext.getResponse().getWriter().write(result.toString());
	}

	/**
	 * 
	 * @Title: validateUserStatus
	 * @Description: 校验用户是否停用
	 * @param @throws Exception
	 * @return void
	 * @author guangchao
	 * @date 2014-1-1 下午6:12:57
	 * @throws
	 */
	public void validateUserStatus() throws Exception {
		
		String UserName = getParameter("UserName");
		String UserPwd = getParameter("UserPwd");
//		对密码MD5加密
		String passWordMd5 = Util.MD5(UserPwd);
		UserInfoManager userInfo = new UserInfoManager();
		userInfo.setUserPwd(passWordMd5);
		userInfo.setUserName(UserName);
		int count = userService.getCountBySqlAndParam("getUserInfoForValidateUserStatus", userInfo);
		Boolean result = false;
		if (count > 0) {
			result = true;
		}
		ServletActionContext.getResponse().getWriter().write(result.toString());
	}

	/**
	 * 
	 * @Title: resetPwd
	 * @Description: 重置密码
	 * @param @throws Exception
	 * @return void
	 * @author guangchao
	 * @date 2013-12-31 下午3:43:57
	 * @throws
	 */
	public void resetPwd() throws Exception {
		
		String UserId = getParameter("UserId");
		String UserPwd = getParameter("UserPwd");
		Boolean result = true;
		UserInfoManager userInfoManager = SessionInfo.getCurUser();
		UserInfoManager userInfo = new UserInfoManager();
		String UpdatePerson = userInfoManager.getUserId();
		userInfo.setUpdatePerson(UpdatePerson);
		userInfo.setUpdateTime(new Date());
		userInfo.setUserId(UserId);
//		对密码MD5加密
		String passWordMd5 = Util.MD5(UserPwd);
		userInfo.setUserPwd(passWordMd5);
		try {
			userService.updateByCondition("updateUserPwd", userInfo);
			addLog(true,"重置密码成功");
		} catch (Exception e) {
			result = false;
			addLog(false,e);
			log.error(e);
		}
		ServletActionContext.getResponse().getWriter().write(result.toString());
	}

	/**
	 * 
	 * @Title: changeStatus
	 * @Description: 更换状态  更改date表中status  0--》1
	 * @param @throws Exception
	 * @return void
	 * @author guangchao
	 * @date 2014-1-1 下午5:02:08
	 * @throws
	 */
	public void changeStatus() throws Exception {
		String UserId = getParameter("id");
		Boolean result = true;
		UserInfoManager userInfoManager = SessionInfo.getCurUser();
		userInfoAndData = new UserInfoAndDataManager();
		userInfoAndData.setUpdatePerson(userInfoManager.getUserId());
		userInfoAndData.setUpdateTime(new Date());
		userInfoAndData.setUserId(UserId);
		userInfoAndData.setStatus(Integer.parseInt(getParameter("status")));
		try {
			userService.updateByCondition("changeStatus", userInfoAndData);
			addLog(true,"变更用户状态成功");
		} catch (Exception e) {
			result = false;
			addLog(false,e);
			log.error(e);
		}
		ServletActionContext.getResponse().getWriter().write(result.toString());
	}

	/**
	 * 
	 * @Title: selRole
	 * @Description: 用户权限
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author zhaojie
	 * @date 2014-1-22 上午10:03:28
	 * @throws
	 */
	public String selRole() throws Exception {
		Boolean result = true;
		String flag = "error";
		List<UserRoleManager> list = new ArrayList<UserRoleManager>();
		String UserId = getParameter("UserId");
		UserInfoManager userInfoManager = SessionInfo.getCurUser();
		String updatePerson = userInfoManager.getUserId();
		String[] RoleIdarray = Util.stringToArray(getParameter("RoleId")) ;
		for (int i = 0; i < RoleIdarray.length; i++) {
			UserRoleManager userRoleManager = new UserRoleManager();
			userRoleManager.setUserRoleId(UUID.randomUUID().toString());
			userRoleManager.setStatus(1);
			userRoleManager.setCreateTime(new Date());
			userRoleManager.setUpdateTime(new Date());
			userRoleManager.setUserId(UserId);
			userRoleManager.setUpdatePerson(updatePerson);
			userRoleManager.setCreatePerson(updatePerson);
			userRoleManager.setRoleName(RoleIdarray[i]);
			list.add(userRoleManager);
		}
		try {
			userrolemanagerService.selRoleManager(list, UserId);
		} catch (Exception e) {
			result = false;
			log.error(e);
		}
		if(result){
			flag = "success";
		}else{
			flag = "error";
		}
		setActionResult(flag);
		return flag;
	}
	/**
	 * 授权
	 * @return
	 * @throws Exception
	 */
	public String selRoleByUsers() throws Exception {
		Boolean result = true;
		String flag = "error";
		List<UserRoleManager> list = new ArrayList<UserRoleManager>();
		String[] UserIdarray = Util.stringToArray(getParameter("UserId")) ;
		UserInfoManager userInfoManager = SessionInfo.getCurUser();
		String updatePerson = userInfoManager.getUserId();
		String[] RoleIdarray = Util.stringToArray(getParameter("RoleId")) ;
		for (int i = 0; i < RoleIdarray.length; i++) {
			for(int j=0;j<UserIdarray.length;j++){
				UserRoleManager userRoleManager = new UserRoleManager();
				userRoleManager.setUserRoleId(UUID.randomUUID().toString());
				userRoleManager.setStatus(1);
				userRoleManager.setCreateTime(new Date());
				userRoleManager.setUpdateTime(new Date());
				userRoleManager.setUserId(UserIdarray[j]);
				userRoleManager.setUpdatePerson(updatePerson);
				userRoleManager.setCreatePerson(updatePerson);
				userRoleManager.setRoleName(RoleIdarray[i]);
				list.add(userRoleManager);
			}
		}
		try {
			userrolemanagerService.selRoleByUsers(list, UserIdarray);
		} catch (Exception e) {
			result = false;
			log.error(e);
		}
		if(result){
			flag = "success";
		}else{
			flag = "error";
		}
		setActionResult(flag);
		return flag;
	}
	
	/**
	 * 
	 * @Title: selOrgan
	 * @Description: 分配组织
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author lishanhe
	 * @date 2014-5-26 
	 * @throws
	 */
	public String selOrgan() throws Exception {
		
		String UserId = getParameter("UserId");
		String OrganId = getParameter("OrganId");
		UserInfoManager userInfoManager = SessionInfo.getCurUser();
		String updatePerson = userInfoManager.getUserId();
		UserDataManager userdata = new UserDataManager();
		userdata.setUserId(UserId);
		userdata.setOrganId(OrganId);
		userdata.setUpdateTime(new Date());
		userdata.setUpdatePerson(updatePerson);
		userdata.setStatus(1);
		userService.updateEntity("updateUserData", userdata);
		return "success";
	}
	
	
	 /**
     * 
    * @Title: findUserListByOrganId   
    * @Description:通过OrganId来查找所有用户
    * @param @return      
    * @return String   
    * @author lishanhe
    * @date 2014年4月9日 下午1:51:44 
    * @throws   
    * @return
     */
    public String findUserListByOrganId() {
    	String OrganId = getParameter("OrganId");
		page = new Page<UserInfoAndDataManager>();
		page.setPageSize(pageSize);
		page.setCurrentPage(currentPage);
		userInfoAndData = new UserInfoAndDataManager();
		userInfoAndData.setOrganId(OrganId);
		page = userService.findByCondition("getUserListByOrganId", userInfoAndData, page);
		list = page.getResultList();
		String actionResult = getActionResult();
		setActionResult(actionResult);
		return "success";
    }
	
    
    /**
     * 
    * @Title: getUserByCondition   
    * @Description:按照fullname username status进行查询 
    * @param @return      
    * @return String   
    * @author lishanhe
    * @date 2014年4月9日 下午1:51:44 
    * @throws   
    * @return
     */
    public String getUserByCondition() {
    	String UserName = getParameter("UserName");
    	String FullName=getParameter("FullName");
    	Integer status=null;
    	if(Util.isNotNull(FullName)){
    		try {
				FullName=new String(FullName.getBytes("iso8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
    	}
    	if (Util.isNotNull(getParameter("Status"))) {
    		status = Integer.parseInt(getParameter("Status"));
		}
		page = new Page<UserInfoAndDataManager>();
		page.setPageSize(pageSize);
		page.setCurrentPage(currentPage);
		userInfoAndData = new UserInfoAndDataManager();
		userInfoAndData.setFullName(FullName);
		userInfoAndData.setUserName(UserName);
		userInfoAndData.setStatus(status);
		page = userService.findByCondition("getUserByCondition", userInfoAndData, page);
		list = page.getResultList();
		String actionResult = getActionResult();
		setActionResult(actionResult);
		return "success";
    }
    
    
	 /**
     * 
    * @Title: userManager   
    * @Description:进入用户管理
    * @param @return      
    * @return String   
    * @author lishanhe
    * @date 2014年4月9日 下午1:51:44 
    * @throws   
    * @return
     */
    public String userManager() {
    	return "success";
    }

	
	/**
	 * 
	 * @Title: toAddUser
	 * @Description: 跳转到添加用户页面
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author guangchao
	 * @date 2013-12-24 下午2:38:33
	 * @throws
	 */
	public String toAddUser() throws Exception {
		return "success";
	}

	/**
	 * 
	 * @Title: toUpdatePwd
	 * @Description: 打开修改密码
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author guangchao
	 * @date 2013-12-31 下午4:16:34
	 * @throws
	 */
	public String toUpdatePwd() throws Exception {
		return "success";
	}

	/**
	 * @return the fileFileName
	 */
	public String getFileFileName() {
		return fileFileName;
	}

	/**
	 * @param fileFileName the fileFileName to set
	 */
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	/**
	 * @return the outPath
	 */
	public String getOutPath() {
		return outPath;
	}

	/**
	 * @param outPath the outPath to set
	 */
	public void setOutPath(String outPath) {
		this.outPath = outPath;
	}


	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage
	 *            the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the isReapeat
	 */
	public String getIsReapeat() {
		return isReapeat;
	}

	/**
	 * @param isReapeat
	 *            the isReapeat to set
	 */
	public void setIsReapeat(String isReapeat) {
		this.isReapeat = isReapeat;
	}

	/**
	 * @return the actionResult
	 */
	public String getActionResult() {
		return actionResult;
	}

	/**
	 * @param actionResult
	 *            the actionResult to set
	 */
	public void setActionResult(String actionResult) {
		this.actionResult = actionResult;
	}


	/**
	 * @return the userInfoAndData
	 */
	public UserInfoAndDataManager getUserInfoAndData() {
		return userInfoAndData;
	}

	/**
	 * @param userInfoAndData
	 *            the userInfoAndData to set
	 */
	public void setUserInfoAndData(UserInfoAndDataManager userInfoAndData) {
		this.userInfoAndData = userInfoAndData;
	}

	/**
	 * @return the userInfo
	 */
	public UserInfoManager getUserInfo() {
		return userInfo;
	}

	/**
	 * @param userInfo
	 *            the userInfo to set
	 */
	public void setUserInfo(UserInfoManager userInfo) {
		this.userInfo = userInfo;
	}

	/**
	 * @return the userData
	 */
	public UserDataManager getUserData() {
		return userData;
	}

	/**
	 * @param userData
	 *            the userData to set
	 */
	public void setUserData(UserDataManager userData) {
		this.userData = userData;
	}

	public List<UserInfoAndDataManager> getList() {
		return list;
	}

	public void setList(List<UserInfoAndDataManager> list) {
		this.list = list;
	}

	public Page<UserInfoAndDataManager> getPage() {
		return page;
	}

	public void setPage(Page<UserInfoAndDataManager> page) {
		this.page = page;
	}


	

}
