/**
 * @Title: moduleAction.java   
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.action.module   
 * @Description: 模块管理  
 * @author guangchao    
 * @date 2013-12-23 下午1:00:02   
 * @version V1.0 
 */

package com.framework.action.module;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.core.base.BaseAction;
import com.framework.entity.navigation.Navigation;
import com.framework.entity.pubmenu.PubMenu;
import com.framework.entity.module.ModuleManager;
import com.framework.entity.module.ModuleResourceRela;
import com.framework.entity.systemmanager.Systemmanager;
import com.framework.entity.user.UserInfoManager;
import com.orm.Page;
import com.orm.Sort;
import com.framework.service.module.IModuleService;
import com.framework.service.systemmanager.SystemmanagerService;
import com.util.Util;

/**
 * @ClassName: moduleAction
 * @Description: 模块管理
 * @author guangchao
 * @date 2013-12-23 下午1:00:02
 * 
 */
@Controller
public class ModuleAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 261863904877939333L;
	@Resource(name = "moduleServiceImp")
	private IModuleService moduleServiceImp;
//	应用管理
	@Resource(name = "systemmanagerService")
    private SystemmanagerService systemmanagerService;
	
	private Page<ModuleManager> page;
	/* 当前页号及每页数 */
	private int currentPage;
	private int pageSize;
	// 操作结果
	private String actionResult;
	private List<ModuleManager> moduleList = null;
	private UserInfoManager userInfo;
	private ModuleManager moduleManager;
	private List<Systemmanager> listSystemManager=null;
	private List<Navigation> navigationList=null;
	private List<PubMenu> pubmenuList=null;
	private Map<String,Object> cache = new HashMap<String,Object>();
	
	
	Logger log = Logger.getLogger(this.getClass());
	
	
	/**  
	* @Title: getModuleByCondition  
	* @Description: 查询列表 
	* @param @return
	* @param @throws Exception      
	* @return String  
	* @author lyc
	* @date 2014年8月29日 下午3:21:40
	* @throws  
	*/
	public String getModuleByCondition() throws Exception {
		 //获取前台传递的id  get方式传值
        String ParentId = getParameter("ParentId");
        if(Util.isNull(ParentId)){
        	ParentId = "0";
        }
        ModuleManager manager=new ModuleManager();
        if(Util.isNull(moduleManager)){
        	manager.setModuleParent(ParentId);
        }else{
        	manager = moduleManager;
        	ParentId = manager.getModuleParent();
            if(Util.isNull(ParentId)){
            	ParentId = "0";
            }
            manager.setModuleParent(ParentId);
        }
		page = new Page<ModuleManager>();
		page.setPageSize(pageSize);
        //设置排序
        page.addOrder(Sort.asc("ModuleNumber"));
        page.addOrder(Sort.asc("UpdateTime"));
		page.setCurrentPage(currentPage);
		page = moduleServiceImp.findByCondition("findModuleByParent",manager, page);
		moduleList = page.getResultList();
		return "success";
	}
	/**
	 * 
	* @Title: detailModule   
	* @Description: 查看模块详细信息  
	* @param @return
	* @param @throws Exception      
	* @return String   
	* @author Lenovo
	* @date 2014-1-15 上午10:02:57 
	* @throws
	 */
	public String detailModule() throws Exception {
		String ModuleId = getParameter("ModuleId");
		ModuleManager module=new ModuleManager();
		ModuleManager manager=new ModuleManager();
		manager.setModuleId(ModuleId);
		module=(ModuleManager)moduleServiceImp.findEntityByCondition("findModuleByID",manager);
		setModuleManager(module);
		return "success";
	}
	/**
	 * 
	* @Title: toFind   
	* @Description: 跳转模块管理
	* @param @return
	* @param @throws Exception      
	* @return String   
	* @author Lenovo
	* @date 2014-1-15 上午10:03:43 
	* @throws
	 */
	public String toFind() throws Exception {
		return "success";
	}
	/**
	 * 
	* @Title: toAddModule   
	* @Description: 跳转添加   
	* @param @return
	* @param @throws Exception      
	* @return String   
	* @author Lenovo
	* @date 2014-1-15 下午1:51:18 
	* @throws
	 */
	public String toAddModule() throws Exception {
		String ModuleParent = getParameter("ModuleParent");
		ModuleManager module=new ModuleManager();
		List<Systemmanager> list=new ArrayList<Systemmanager>();
		module=(ModuleManager)moduleServiceImp.findEntityByCondition("findModuleNameByModuleParent",ModuleParent);
		list = systemmanagerService.findAll();
		setModuleManager(module);
		setListSystemManager(list);
		return "success";
	}
	/**
	 * 
	* @Title: insertModule   
	* @Description: 添加模块
	* @param @return
	* @param @throws Exception      
	* @return String   
	* @author Lenovo
	* @date 2014-1-15 下午3:20:34 
	* @throws
	 */
	public String insertModule() throws Exception {
	    String CreatePerson = getCurUserId();	
		boolean result = true;
		String flag = "error";
		moduleManager.setCreateTime(new Date());
		moduleManager.setCreatePerson(CreatePerson);
		moduleManager.setUpdateTime(new Date());
		moduleManager.setUpdatePerson(CreatePerson);
		String id=UUID.randomUUID().toString();
		moduleManager.setModuleId(id);
		moduleManager.setSystemCode("framework");
		if(moduleManager.getModuleParent()==null||moduleManager.getModuleParent().equals("")){
			moduleManager.setModuleParent("0");
		}
		try {
			 moduleServiceImp.addEntity(moduleManager);
			 addLog(true,"新增模块成功");
		} catch (Exception e) {
			log.error(e);
			addLog(false,e);
			result = false;
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
	* @Title: toUpdateModoule   
	* @Description: 跳转修改
	* @param @return
	* @param @throws Exception      
	* @return String   
	* @author Lenovo
	* @date 2014-1-15 下午6:43:57 
	* @throws
	 */
	public String toUpdateModoule() throws Exception {
		String ModuleId = getParameter("ModuleId");
		ModuleManager module=new ModuleManager();
		ModuleManager manager=new ModuleManager();
		manager.setModuleId(ModuleId);
		module=(ModuleManager)moduleServiceImp.findEntityByCondition("findModuleByID",manager);
		setModuleManager(module);
		return "success";
	}
	/**
	 * 
	* @Title: updateModule   
	* @Description: 修改模块
	* @param @return
	* @param @throws Exception      
	* @return String   
	* @author Lenovo
	* @date 2014-1-16 上午11:21:16 
	* @throws
	 */
	public String updateModule() throws Exception {
	    String CreatePerson = getCurUserId();	
		boolean result = true;
		String flag = "error";
		moduleManager.setUpdatePerson(CreatePerson);
		moduleManager.setUpdateTime(new Date());
		try {
			 moduleServiceImp.updateEntity(moduleManager);
			 addLog(true,"修改模块成功");
		} catch (Exception e) {
			log.error(e);
			addLog(false,e);
			result = false;
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
	* @Title: deleteModule   
	* @Description: 逻辑删除模块
	* @param @return
	* @param @throws Exception      
	* @return String   
	* @author Lenovo
	* @date 2014-1-16 上午11:18:51 
	* @throws
	 */
	public String deleteModule() throws Exception {
		String ModuleId = getParameter("ModuleId");
		boolean result = true;
		String flag = "error";
		String[] array ;
		array = ModuleId.split(",");
		try {
			 moduleServiceImp.bulkDeleteOfLogical(array);
			 addLog(true,"删除模块成功");
		} catch (Exception e) {
			log.error(e);
			addLog(false,e);
			result = false;
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
	* @Title: getModuleCount   
	* @Description: 查询模块下是否有子模块和资源
	* @param @throws Exception      
	* @return void   
	* @author Lenovo
	* @date 2014-1-16 上午11:37:33 
	* @throws
	 */
	@SuppressWarnings("unchecked")
	public void getModuleCount() throws Exception {
		String ModuleId = getParameter("ModuleId");
		String[] array ;
		array = ModuleId.split(",");
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("array", array);
		Boolean result = true;
		moduleList=moduleServiceImp.findByCondition("findModuleParentByID",map);
		int count = moduleServiceImp.getCountBySqlAndParam("findResourcesCount", map);
		if(moduleList.size()!=0 && count !=0)
			result= false;
		ServletActionContext.getResponse().getWriter().write(result.toString());
	}
	/**
	 * @Title:getModuleManagerCountOne
	 * @Description:查询模块是否与资源、角色、应用有关联。
	 * @throws Exception
	 * void
	 * @author gaojie
	 * @date 2014年9月3日下午5:29:43
	 */
	public void getModuleManagerCountOne() throws Exception {
		String ModuleId = getParameter("ModuleId");
		ModuleId = new String(ModuleId.getBytes("iso8859-1"),"utf-8"); 
		String result = "true";
		int num1 = moduleServiceImp.getCountBySqlAndParam("findByModulResource", ModuleId);
		if (num1 > 0){
			result = "1";
		}
		if(result == "true"){
			int num2 = moduleServiceImp.getCountBySqlAndParam("findByModulRole", ModuleId);
			if(num2>0){
				result = "2";
			}
		}
		if(result == "true"){
			int num3 = moduleServiceImp.getCountBySqlAndParam("findByModulSystem", ModuleId);
			if(num3>0){
				result = "3";
			}
		}
		ServletActionContext.getResponse().getWriter().write(result.toString());
	}
	/**
	 * @Title:getModuleManagerCount
	 * @Description:批量查询模块是否与资源、角色、应用有关联。
	 * @throws Exception
	 * void
	 * @author gaojie
	 * @date 2014年9月3日下午5:28:07
	 */
	public void getModuleManagerCount() throws Exception {
		String ModuleId = getParameter("ModuleId");
		ModuleId = new String(ModuleId.getBytes("iso8859-1"),"utf-8"); 
		String[] array;
		array = ModuleId.split(",");
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("array", array);
		Boolean result = true;
		int num = moduleServiceImp.getCountBySqlAndParam("findByModulId", array);
		if (num > 0)
			result = false;
		ServletActionContext.getResponse().getWriter().write(result.toString());
	}
	/**
	 * 
	* @Title: changeModuleStatus   
	* @Description: 更改模块状态
	* @param @throws Exception      
	* @return void   
	* @author Lenovo
	* @date 2014-1-16 下午6:34:35 
	* @throws
	 */
	public void changeModuleStatus() throws Exception {
		String moduleId = getParameter("id");
		String moduleStatus = getParameter("status");
		Boolean result = true;
	    String updatePerson = getCurUserId();
	    ModuleManager manager =new ModuleManager();
	    manager.setUpdateTime(new Date());
	    manager.setUpdatePerson(updatePerson);
	    manager.setModuleId(moduleId);
	    manager.setStatus(Byte.parseByte(moduleStatus));
		try {
			moduleServiceImp.updateEntity("updateModuleStatus",manager);
			addLog(true,"变更模块状态成功");
		} catch (Exception e) {
			log.error(e);
			addLog(false,e);
			result=false;
		}
		ServletActionContext.getResponse().getWriter().write(result.toString());
	}

	/**
	 * 获取当前登陆人的菜单
	 * 
	 * @Title: getAllModulesForUser
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return
	 * @return String
	 * @author guangchao
	 * @date 2013-12-23 下午1:08:05
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public String getAllModulesForUser() {
		String userId = getCurUserId();
		String SystemCode = getParameter("SystemCode");
		if(Util.isNull(SystemCode)){
			SystemCode = (String) getSession(true).getAttribute("systemFullName");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("UserId", userId);
		params.put("SystemCode", SystemCode);
		params.put("session", getSession(true));
		Map<String, Object> res = moduleServiceImp.getModuleByUserId(params);
		moduleList = (List<ModuleManager>) res.get("moduleList");
		pubmenuList = (List<PubMenu>) res.get("pubmenuList");
		listSystemManager = (List<Systemmanager>) res.get("listSystemManager");
		navigationList = (List<Navigation>) res.get("navigationList");
		String IsPreferencesMenuUrl = (String) res.get("IsPreferencesMenuUrl");
		data.add("IsPreferencesMenuUrl", IsPreferencesMenuUrl);
		return "success";
	}
	
	/**  
	* @Title: setResources  
	* @Description: 设置资源 
	* @param @return      
	* @return String  
	* @author lyc
	* @date 2014年8月29日 下午5:48:40
	* @throws  
	*/
	public String setResources() {
		String ModuleId = getParameter("ModuleId");
		String ids = getParameter("ids");
		String [] arrayIds=null;
		List<ModuleResourceRela> listSystemManager=new  ArrayList<ModuleResourceRela>();
		if(Util.isNotNull(ids)){
			arrayIds=ids.split(",");
			for(int i=0 ; i<arrayIds.length ; i++){
				ModuleResourceRela  moduleResourceRela = new ModuleResourceRela();
				moduleResourceRela.setModuleId(ModuleId.trim());
				moduleResourceRela.setUUID(UUID.randomUUID().toString());
				moduleResourceRela.setResourceId(arrayIds[i].trim());
				listSystemManager.add(moduleResourceRela);
			}
			try {
				moduleServiceImp.deleteByConditions("moduleDelResource", ModuleId);
				moduleServiceImp.addListOfEntity("moduleAddResource", listSystemManager);
				addLog(true,"设置模块资源成功");
			} catch (Exception e) {
				addLog(false,e);
				log.error(e);
			}
			
		}
		return "success";
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
	 * @return the moduleList
	 */
	public List<ModuleManager> getModuleList() {
		return moduleList;
	}

	/**
	 * @param moduleList
	 *            the moduleList to set
	 */
	public void setModuleList(List<ModuleManager> moduleList) {
		this.moduleList = moduleList;
	}
	/**
	 * @return the moduleServiceImp
	 */
	public IModuleService getModuleServiceImp() {
		return moduleServiceImp;
	}

	/**
	 * @param moduleServiceImp the moduleServiceImp to set
	 */
	public void setModuleServiceImp(IModuleService moduleServiceImp) {
		this.moduleServiceImp = moduleServiceImp;
	}

	/**
	 * @return the page
	 */
	public Page<ModuleManager> getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Page<ModuleManager> page) {
		this.page = page;
	}

	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage the currentPage to set
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
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the actionResult
	 */
	public String getActionResult() {
		return actionResult;
	}

	/**
	 * @param actionResult the actionResult to set
	 */
	public void setActionResult(String actionResult) {
		this.actionResult = actionResult;
	}
	/**
	 * @return the moduleManager
	 */
	public ModuleManager getModuleManager() {
		return moduleManager;
	}
	/**
	 * @param moduleManager the moduleManager to set
	 */
	public void setModuleManager(ModuleManager moduleManager) {
		this.moduleManager = moduleManager;
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
	public Map<String, Object> getCache() {
		return cache;
	}
	public void setCache(Map<String, Object> cache) {
		this.cache = cache;
	}
	public List<Navigation> getNavigationList() {
		return navigationList;
	}
	public void setNavigationList(List<Navigation> navigationList) {
		this.navigationList = navigationList;
	}
	public List<PubMenu> getPubmenuList() {
		return pubmenuList;
	}
	public void setPubmenuList(List<PubMenu> pubmenuList) {
		this.pubmenuList = pubmenuList;
	}
	

}
