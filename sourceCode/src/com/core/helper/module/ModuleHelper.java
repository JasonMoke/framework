/**
* @Title: DictHelper.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.oneworld.core.helper.dict   
* @Description:    
* @author guangchao    
* @date 2014-3-26 下午6:08:37   
* @version V1.0 
*/
package com.core.helper.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jfree.util.Log;

import com.core.dataload.LoadModule;
import com.core.dataload.LoadResource;
import com.core.db.DBHelper;
import com.core.db.ILoadData;
import com.core.helper.user.UserHelper;
import com.framework.entity.resources.Resources;
import com.framework.entity.module.ModuleManager;

/**
 * 
* @ClassName: ModuleHelper
* @Description: 工具类
* @author gaoguangchao
* @date 2014年9月3日 上午11:23:25
*
 */
public class ModuleHelper {

	
	/**
	 * 
	* @Title: getModuleList   
	* @Description:获取所有ModuleList
	* @return
	* @throws Exception
	* @return List<ModuleManager>        
	* @author gaoguangchao
	* @date 2014年9月3日 上午10:51:47 
	*
	 */
	public static List<ModuleManager> getModuleList() throws Exception{
		
		List<ModuleManager> modulelist = new ArrayList<ModuleManager>();
		List<Resources> resourcelist = new ArrayList<Resources>();
		Map<String,Map<String,Object>> relamap = new HashMap<String,Map<String,Object>>();
		try{
			String sqlmodul= "SELECT * FROM t_module WHERE Status!=99";
			String sqlresource= "SELECT * FROM t_resources WHERE Status!=99";
			String sqlrela= "SELECT * FROM t_module_resource_rela ";
			ILoadData moduleData=new LoadModule();
			ILoadData resourceData = new LoadResource();
			modulelist = DBHelper.getList(sqlmodul,moduleData);
			resourcelist = DBHelper.getList(sqlresource,resourceData);
			relamap = DBHelper.getMapsMap(sqlrela);
			modulelist = iteratorMap(modulelist,resourcelist,relamap);
		}catch(Exception e) {
			Log.error(e);
			System.out.println(e.getMessage());
		}
		return modulelist;
	}
	/**
	 * 
	* @Title: getModuleResourceRela   
	* @Description:获取模块资源关系
	* @return
	* @throws Exception
	* @return Map<String,Map<String,Object>>        
	* @author gaoguangchao
	* @date 2014年9月3日 上午11:26:05 
	*
	 */
	public static Map<String,Map<String,Object>> getModuleResourceRela() throws Exception{
		
		Map<String,Map<String,Object>> relamap = new HashMap<String,Map<String,Object>>();
		try{
			String sqlrela= "SELECT * FROM t_module_resource_rela ";
			relamap = DBHelper.getMapsMap(sqlrela);
		}catch(Exception e) {
			Log.error(e);
			System.out.println(e.getMessage());
		}
		return relamap;
	}
	
	/**
	 * 
	* @Title: getModuleListForUser   
	* @Description:获取某个用户的module
	* @param UserId
	* @return
	* @throws Exception
	* @return List<ModuleManager>        
	* @author gaoguangchao
	* @date 2014年9月3日 上午10:52:05 
	*
	 */
	public static List<ModuleManager> getModuleListForUser(String UserId) throws Exception{
		List<ModuleManager> modulelist = new ArrayList<ModuleManager>();
		List<Resources> resourcelist = new ArrayList<Resources>();
		Map<String,Map<String,Object>> relamap = new HashMap<String,Map<String,Object>>();
		ILoadData moduleData=new LoadModule();
		ILoadData resourceData = new LoadResource();
		String getAllModulesForUser = "";
		StringBuffer s = new StringBuffer();
		s.append("select R.*,S.SystemCode from  (SELECT M.ModuleId, M.ModuleParent,");
		s.append(" M.ModuleAddress,");
		s.append(" M.Status,");
		s.append(" M.ModuleNumber,");
		s.append(" M.SmallLogo,");
		s.append(" M.PermissionSet,");
		s.append(" M.ModuleParam,");
		s.append(" M.ModuleName,");
		s.append(" M.ModuleRemark ");
		s.append(" FROM t_module M ");
		s.append(" where M.ModuleId IN");
		s.append(" (SELECT ModuleId FROM t_role_module_rela WHERE RoleName IN");
		s.append(" (SELECT RoleName FROM t_user_role WHERE  UserId = ? AND Status  = '1')");
		s.append(" AND Status  = '1')");
		s.append(" AND M.Status='1'");
		s.append(" ORDER BY M.ModuleNumber) AS R");
		s.append(" left join t_system S on S.SystemCode IN");
		s.append(" (select SystemId as SystemCode from t_system_module_rela where moduleId =R.ModuleId)");
		String getAllResourcesForUser = "SELECT M.*	 FROM t_resources M 	 where M.ResourcesId IN	(SELECT ResourceId FROM t_role_resource_rela WHERE RoleName IN"
				+ "	(SELECT RoleName FROM t_user_role WHERE  UserId = #{userId} AND Status  = '1') AND Status  = '1')"
				+ "	AND M.Status='1' ORDER BY M.ResourcesNumber";
		String sqlrela= "SELECT * FROM t_module_resource_rela ";
		getAllModulesForUser = s.toString();
		modulelist = DBHelper.getList(getAllModulesForUser,moduleData,UserId);
		resourcelist = DBHelper.getList(getAllResourcesForUser,resourceData,UserId);
		relamap = DBHelper.getMapsMap(sqlrela);
		modulelist = iteratorMap(modulelist,resourcelist,relamap);
		
		return modulelist;
		
	}
	/**
	 * 
	* @Title: getModuleListForCurUser   
	* @Description:获取当前登陆人的module
	* @return
	* @throws Exception
	* @return List<ModuleManager>        
	* @author gaoguangchao
	* @date 2014年9月3日 上午10:52:51 
	*
	 */
	public static List<ModuleManager> getModuleListForCurUser() throws Exception{
		String UserId = UserHelper.getCurUserId();
		return getModuleListForUser(UserId);
		
	}
	/**   
	* @Title: iteratorMap
	* @Description:遍历modulelist，resourcelist，relamap 将属于module的resourceSet放进去
	* @return modulelist        
	* @author lishanhe
	 * @throws Exception 
	* @date 2014年7月1日 下午2:44:25 
	*   
	*/ 
	public static List<ModuleManager> iteratorMap(List<ModuleManager> modulelist,
												  List<Resources> resourcelist,
												  Map<String,Map<String,Object>> relamap){
		Set<Resources> resourceSet;
		if(relamap==null){
			try {
				relamap = getModuleResourceRela();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		for(Map<String,Object> inmap : relamap.values()){
			String moduleId = (String)inmap.get("ModuleId");
			String resourceId = (String)inmap.get("ResourceId");
			for(ModuleManager module:modulelist){
				if(module.getModuleId().equals(moduleId)){
					for(Resources resource:resourcelist){
						if(resource.getResourcesId().equals(resourceId)){
							resourceSet = module.getResourcesSet();
							if(resourceSet!=null&&resourceSet.size()>0){
								resourceSet.add(resource);
								break;
							}else{
								resourceSet = new HashSet<Resources>();
								resourceSet.add(resource);
								module.setResourcesSet(resourceSet);
								break;
							}
						}
					}
				}
			}
		}
		return modulelist;
	}
	
}
