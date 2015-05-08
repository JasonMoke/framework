/**
 * @Title: RolemanagerAction.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.action.rolemanager
 * @Description: 角色管理
 * @author zhaojie
 * @date 2014-01-17 14:14:24
 * @version V1.0
 */
package com.framework.action.role;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;
import org.apache.log4j.Logger;

import com.core.base.BaseAction;
import com.framework.entity.authority.RightsManager;
import com.framework.entity.role.RoleManager;
import com.framework.entity.user.UserInfoManager;
import com.orm.Page;
import com.orm.Sort;
import com.framework.service.authority.RightsmanagerService;
import com.framework.service.role.IRoleService;
import com.util.SessionInfo;

@Controller
public class RoleAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6902517075719447743L;
	@Resource(name = "roleServiceImp")
	private IRoleService roleServiceImp;
	@Resource(name = "rightsmanagerService")
	private RightsmanagerService rightsmanagerService;
	private List<RoleManager> listRoleManager;
	private Page<RoleManager> page;
	private RoleManager rolemanager;
	private RightsManager rightsManager;
	/* 当前页号及每页数 */
	private int currentPage;
	private int pageSize;

	private String switchFlag = "det";
	Logger log = Logger.getLogger(this.getClass());
	private String roleId;
	private String jsonData;
	private String actionResult;

	public String findAllRoles() throws Exception {
		
		HttpSession session = getSession(true);
		Locale locale = (Locale) session.getAttribute("WW_TRANS_I18N_LOCALE");
		if (locale == null) {
			locale = Locale.CHINA;
		}
		page = new Page<RoleManager>();
		page.setPageSize(pageSize);
        //设置排序
        page.addOrder(Sort.asc("RoleNumber"));
        page.addOrder(Sort.asc("UpdateTime"));
		page.setCurrentPage(currentPage);
		page = roleServiceImp.findByCondition("findAll", locale.toString(), page);
		listRoleManager = page.getResultList();

		return "success";
	}

	/**
	 * @Title: detail
	 * @Description: 查询单条信息
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author zhaojie
	 * @date 2014-01-17 14:14:24
	 * @throws
	 */
	public String detailRole() throws Exception {
		
		HttpSession session = getSession(true);
		Locale locale = (Locale) session.getAttribute("WW_TRANS_I18N_LOCALE");
		if (locale == null) {
			locale = Locale.CHINA;
		}
		// 获取前台传递的id get方式传值
		String RoleName = getParameter("RoleName");
		// 查询实体
		// 对应map中findById
		RoleName = new String(RoleName.getBytes("iso8859-1"),"utf-8"); 
		RoleManager role=new RoleManager();
		role.setRoleName(RoleName);
		role.setLocale(locale.toString());
		RoleManager rolemanager = (RoleManager) roleServiceImp
				.findObjectByCondition("findById",role);
		setRolemanager(rolemanager);
		// 设置操作类型
		return "success";
	}

	/**
	 * 
	 * @Title: toModifyRole
	 * @Description: 跳转修改界面
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author Lenovo
	 * @date 2014-1-17 下午6:07:02
	 * @throws
	 */
	public String toModifyRole() throws Exception {
		
		HttpSession session = getSession(true);
		Locale locale = (Locale) session.getAttribute("WW_TRANS_I18N_LOCALE");
		if (locale == null) {
			locale = Locale.CHINA;
		}
		// 获取前台传递的id get方式传值
		String RoleName = getParameter("RoleName");
		RoleName = new String(RoleName.getBytes("iso8859-1"),"utf-8"); 
		RoleManager role=new RoleManager();
		role.setRoleName(RoleName);
		role.setLocale(locale.toString());
		RoleManager rolemanager = (RoleManager) roleServiceImp
				.findObjectByCondition("findById",role);
		setRolemanager(rolemanager);
		// 设置操作类型
		return "success";
	}

	/**
	 * @Title: update
	 * @Description:修改
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author zhaojie
	 * @date 2014-01-17 14:14:24
	 * @throws
	 */
	public String ModifyRole() throws Exception {
		String flag = "error";
		System.out.println(rolemanager.getRoleName());
		boolean result = true;
		// 获取当前登陆人
		UserInfoManager userInfoManager = SessionInfo.getCurUser();
		String userId = userInfoManager.getUserId();
		// 设置修改人
		rolemanager.setUpdatePerson(userId);
		// 设置修改时间
		rolemanager.setUpdateTime(new Date());

		try {
			// 对应map中update
			roleServiceImp.updateEntity(rolemanager);
			addLog(true,"修改角色成功");
		} catch (Exception e) {
			result = false;
			addLog(false,e);
			log.error(e);
		}
		if (result) {
			flag = "success";
		} else {
			flag = "error";
		}
		setActionResult(flag);
		return "success";
	}

	/**
	 * @Title: gotoAdd
	 * @Description: 跳转到添加页面.
	 * @return
	 * @return String
	 * @author zhaojie
	 * @date 2014-01-17 14:14:24
	 * @throws
	 */
	public String gotoAdd() {
		return "success";
	}

	/**
	 * @Title: insert
	 * @Description: 添加
	 * @return
	 * @return String
	 * @author zhaojie
	 * @date 2014-01-17 14:14:24
	 * @throws
	 */
	public String insertRole() {
		String flag = "error";
		boolean result = true;
		// 获取当前登陆人
		UserInfoManager userInfoManager = SessionInfo.getCurUser();
		String userId = userInfoManager.getUserId();

		// 设置创建人
		rolemanager.setCreatePerson(userId);
		// 设置创建时间
		rolemanager.setCreateTime(new Date());
		// 设置修改人
		rolemanager.setUpdatePerson(userId);
		// 设置修改时间
		rolemanager.setUpdateTime(new Date());
		RoleManager role=roleServiceImp.findEntityByCondition("getRoleByRoleName",rolemanager.getRoleName());
		
		try {
			// 使用泛型新增实体
			// 对应map中add
			if(role==null){
				roleServiceImp.addEntity(rolemanager);
			}else{
				role=rolemanager;
				roleServiceImp.updateEntity(role);
			}
			addLog(true,"新增角色成功");
		} catch (Exception e) {
			result = false;
			addLog(false,e);
			log.error(e);
		}
		if (result) {
			flag = "success";
		} else {
			flag = "error";
		}
		// 返回操作结果
		setActionResult(flag);
		return "success";
	}

	/**
	 * 
	 * @Title: getRoleCount
	 * @Description: 检测是否有同名角色
	 * @param @throws Exception
	 * @return void
	 * @author Lenovo
	 * @date 2014-1-17 下午4:26:26
	 * @throws
	 */
	public void getRoleCount() throws Exception {
		
		String RoleName = getParameter("RoleName");
		RoleName = new String(RoleName.getBytes("iso8859-1"),"utf-8"); 
		Boolean result = true;
		RoleManager rolemanager = (RoleManager) roleServiceImp
				.findById(RoleName);
		if (rolemanager != null)
			result = false;
		ServletActionContext.getResponse().getWriter().write(result.toString());
	}

	/**
	 * 
	 * @Title: deleteRole
	 * @Description: 逻辑删除角色并将该角色的权限与被赋予该角色的用户的权限删除
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author Lenovo
	 * @date 2014-1-18 上午11:57:34
	 * @throws
	 */
	public String deleteRole() throws Exception {
		
		String RoleName = getParameter("RoleName");
		RoleName = new String(RoleName.getBytes("iso8859-1"),"utf-8"); 
		UserInfoManager userInfoManager = SessionInfo.getCurUser();
		String userId = userInfoManager.getUserId();
		boolean result = true;
		String flag = "error";
		String[] array;
		array = RoleName.split(",");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("array", array);
		map.put("UpdatePerson", userId);
		map.put("UpdateTime", new Date());
		try {
			roleServiceImp.updateByCondition("delRole", map);
/*			roleServiceImp.updateByCondition("delRole2", map);
			roleServiceImp.updateByCondition("delRole3", map);*/
			addLog(true,"删除角色成功");
		} catch (Exception e) {
			log.error(e);
			result = false;
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
	 * @Title: getRoleCount
	 * @Description:批量查看该角色是否被使用
	 * @param @throws Exception
	 * @return void
	 * @author Lenovo
	 * @date 2014-1-18 下午12:01:05
	 * @throws
	 */
	public void getRoleManagerCount() throws Exception {
		
		String RoleName = getParameter("RoleName");
		RoleName = new String(RoleName.getBytes("iso8859-1"),"utf-8"); 
		String[] array;
		array = RoleName.split(",");
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("array", array);
		Boolean result = true;
		int num = roleServiceImp.getCountBySqlAndParam("findByRoleName", array);

		if (num > 0)
			result = false;
		ServletActionContext.getResponse().getWriter().write(result.toString());
	}
	
	/**
	 * @Title:getRoleManagerCountOne
	 * @Description:查看该角色是否被哪个资源使用
	 * @throws Exception
	 * void
	 * @author gaojie
	 * @date 2014年9月3日下午5:45:43
	 */
	public void getRoleManagerCountOne() throws Exception {
		
		String RoleName = getParameter("RoleName");
		RoleName = new String(RoleName.getBytes("iso8859-1"),"utf-8"); 
		String result = "true";
		int num1 = roleServiceImp.getCountBySqlAndParam("findByRoleNameFromUser", RoleName);
		if (num1 > 0){
			result = "1";
		}
		if(result == "true"){
			int num2 = roleServiceImp.getCountBySqlAndParam("findByRoleNameFromModule", RoleName);
			if(num2>0){
				result = "2";
			}
		}
		ServletActionContext.getResponse().getWriter().write(result.toString());
	}
	/**
	 * 
	* @Title: 角色权限   
	* @Description:  角色权限   
	* @param @return
	* @param @throws Exception      
	* @return String   
	* @author zhaojie
	* @date 2014-1-22 上午10:02:34 
	* @throws
	 */
	public String selRight() throws Exception {
		List<RightsManager> list= new ArrayList<RightsManager>();
		String RoleName = getParameter("RoleName");
		String ModuleID = getParameter("ModuleID");
		UserInfoManager userInfoManager = SessionInfo.getCurUser();
		String updatePerson = userInfoManager.getUserId();
		String[] array;
		array = ModuleID.split(",");
		for (int i = 0; i < array.length; i++) {
			RightsManager rightsManager=new RightsManager();
			rightsManager.setRightsId(UUID.randomUUID().toString());
			rightsManager.setStatus(1);
			rightsManager.setCreateTime(new Date());
			rightsManager.setUpdateTime(new Date());
			rightsManager.setModuleId(array[i]);
			rightsManager.setUpdatePerson(updatePerson);
			rightsManager.setCreatePerson(updatePerson);
			rightsManager.setRoleName(RoleName);
			list.add(rightsManager);
		}
		if(ModuleID != null && !"".equals(ModuleID)){
			rightsmanagerService.selRightManager(list, RoleName);
		}else{
			rightsmanagerService.selRightManager(null, RoleName);
		}
		
		return "success";

	}
	/**
	 * 
	* @Title: changeRoleStatus   
	* @Description:修改角色状态
	* @param @throws Exception      
	* @return void   
	* @author zhaojie
	* @date 2014-3-21 下午3:39:19 
	* @throws
	 */
	public void changeRoleStatus() throws Exception {
		String sCode = getParameter("id");
		String dictStatus = getParameter("status");
		Boolean result = true;
		RoleManager manager=new RoleManager();
		manager.setRoleName(sCode);
		manager.setStatus(Integer.parseInt(dictStatus));
		UserInfoManager userInfoManager = SessionInfo.getCurUser();
	    String updatePerson = userInfoManager.getUserId();	
	    Date updateTime=new Date();
	    manager.setUpdateTime(updateTime);
	    manager.setUpdatePerson(updatePerson);
		try {
			result = roleServiceImp.updateRoleStatus(manager);
			addLog(true,"变更角色状态成功");
		} catch (Exception e) {
			log.error(e);
			addLog(false,e);
		}
		ServletActionContext.getResponse().getWriter().write(result.toString());
	}
	/**
	 * @return the listRoleManager
	 */
	public List<RoleManager> getListRoleManager() {
		return listRoleManager;
	}

	/**
	 * @param listRoleManager
	 *            the listRoleManager to set
	 */
	public void setListRoleManager(List<RoleManager> listRoleManager) {
		this.listRoleManager = listRoleManager;
	}

	/**
	 * @return the page
	 */
	public Page<RoleManager> getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(Page<RoleManager> page) {
		this.page = page;
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
	 * @return the switchFlag
	 */
	public String getSwitchFlag() {
		return switchFlag;
	}

	/**
	 * @param switchFlag
	 *            the switchFlag to set
	 */
	public void setSwitchFlag(String switchFlag) {
		this.switchFlag = switchFlag;
	}

	/**
	 * @return the jsonData
	 */
	public String getJsonData() {
		return jsonData;
	}

	/**
	 * @param jsonData
	 *            the jsonData to set
	 */
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the rolemanager
	 */
	public RoleManager getRolemanager() {
		return rolemanager;
	}

	/**
	 * @param rolemanager
	 *            the rolemanager to set
	 */
	public void setRolemanager(RoleManager rolemanager) {
		this.rolemanager = rolemanager;
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
	 * @return the rightsManager
	 */
	public RightsManager getRightsManager() {
		return rightsManager;
	}

	/**
	 * @param rightsManager
	 *            the rightsManager to set
	 */
	public void setRightsManager(RightsManager rightsManager) {
		this.rightsManager = rightsManager;
	}

}
