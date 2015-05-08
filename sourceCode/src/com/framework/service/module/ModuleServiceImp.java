/**
* @Title: ModuleServiceImp.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.framework.service.module   
* @Description: 模块管理  
* @author guangchao    
* @date 2013-12-23 下午1:16:17   
* @version V1.0 
*/
package com.framework.service.module;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.core.ehcache.EHCacheHelper;
import com.core.helper.module.ModuleHelper;
import com.framework.dao.navigation.NavigationDao;
import com.framework.dao.pubmenu.PubMenuDao;
import com.framework.dao.resources.ResourcesDaoImpl;
import com.framework.dao.module.ModuleDaoImpl;
import com.framework.dao.systemmanager.SystemmanagerDao;
import com.framework.entity.navigation.Navigation;
import com.framework.entity.pubmenu.PubMenu;
import com.framework.entity.resources.Resources;
import com.framework.entity.module.ModuleManager;
import com.framework.entity.module.ModuleResourceRela;
import com.framework.entity.systemmanager.Systemmanager;
import com.orm.BaseServiceImpl;
import com.orm.BaseService;
import com.util.SessionInfo;
import com.util.Util;

/**
 * @ClassName: ModuleServiceImp
 * @Description: 模块管理
 * @author guangchao
 * @date 2013-12-23 下午1:16:17
 *
 */
@Service("moduleServiceImp")
@Transactional
public class ModuleServiceImp  extends BaseServiceImpl<ModuleManager> implements IModuleService{
	
	@Resource(name = "moduleDaoImpl")
	private ModuleDaoImpl dao;
    @Resource(name = "systemmanagerDao")
    private SystemmanagerDao systemmanagerDao;
    @Resource(name = "ResourcesDaoImpl")
	private ResourcesDaoImpl ResourcesDaoImpl;
    @Resource(name = "navigationDao")
    private NavigationDao navigationDao;
    @Resource(name = "pubmenuDao")
    private PubMenuDao pubmenuDao;
	@Override
    public BaseService<ModuleManager> getDao() {
        return dao;
    }

	@SuppressWarnings("unchecked")
	@Override
	public Map<String,Object> getModuleByUserId(Map<?, ?> param) {
		String UserId = (String) param.get("UserId");
		//当前默认的应用ID
		String SystemCode = (String) param.get("SystemCode");
		//当前默认的应用导航ID
		String NavId = "";
		HttpSession session = (HttpSession) param.get("session");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", UserId);
		//map.put("SystemCode", SystemCode);
		List<ModuleManager> moduleList = dao.findByCondition("getAllModulesForUser", map);
		List<Navigation> navigationList = navigationDao.findByCondition("getAllNavigationsForUser", map);
		//获取该用户的应用
		List<Systemmanager> SystemManagerlist = systemmanagerDao.findByCondition("getAllSystemsForUser", map);
		List<Resources> resouercesList = ResourcesDaoImpl.findByCondition("getAllResourcesForUser", map);
		//获取默认应用
		String SystemFullName = "";
		boolean hasPreSys = false;
		for(Systemmanager systemmanager:SystemManagerlist){
			String systemFullName = systemmanager.getFullName();
			String systemId = systemmanager.getSystemCode();
			if(Util.isNull(SystemCode)){
				int isPreferences = systemmanager.getIsPreferences();
				if(isPreferences==1){
					SystemCode = systemId;//当前默认被选中的应用
					SystemFullName = systemFullName;
					hasPreSys = true;
				}
			}else{
				if(SystemCode.equalsIgnoreCase(systemId)){
					SystemFullName = systemFullName;
					hasPreSys = true;
				}
			}
		}
		if(!hasPreSys){
			Systemmanager systemmanager = SystemManagerlist.get(0);
			String systemFullName = systemmanager.getFullName();
			String systemId = systemmanager.getSystemCode();
			SystemCode = systemId;//当前默认被选中的应用
			SystemFullName = systemFullName;
		}
		if(session!=null){
			session.setAttribute("systemFullName", SystemFullName);
		}
		//获取默认应用导航的ID
		for(Navigation n: navigationList){
			if(Util.isNotNull(SystemCode)&&SystemCode.equalsIgnoreCase(n.getSystemCode())){
				NavId = n.getUUID();
				break;
			}
		}
		//获取当前应用导航下的所有菜单
		map.put("NavId", NavId);
		List<PubMenu> pubmenuList = pubmenuDao.findByCondition("getAllPubMenusForUser", map);
		//判断菜单下是否有子菜单
		Map<String, PubMenu> menuMap = new HashMap<String, PubMenu>();
		for(PubMenu menu:pubmenuList){
			String PID = menu.getPID();
			if(Util.isNull(PID)){
				continue;
			}
			menuMap.put(PID, menu);
		}
		for(PubMenu menu0:pubmenuList){
			String ID = menu0.getUUID();
			if(menuMap.containsKey(ID)){
				menu0.setHasChild("1");
			}
		}
		//获取首选项的一级菜单
		String IsPreferencesMenuUrl = "";
		for(PubMenu menu0:pubmenuList){
			int IsPreferences = menu0.getIsPreferences();
			String PID = menu0.getPID();
			if(IsPreferences==1&&"0".equals(PID)){
				IsPreferencesMenuUrl = menu0.getMenuUrl();
			}
		}
		//获取默认应用其下的模块
		List<ModuleManager> ModuleList = new ArrayList<ModuleManager>();
		for(ModuleManager module:moduleList){
			String syscode = module.getSystemCode();
			if(Util.isNotNull(syscode)&&syscode.equals(SystemCode)){
				ModuleList.add(module);
			}
		}
		//获取模块与资源关联
		ModuleList = ModuleHelper.iteratorMap(ModuleList, resouercesList, null);
		//将首选项资源的URL赋值给模块
		/*for(ModuleManager m:ModuleList){
			Set<Resources> ss = m.getResourcesSet();
			if(ss==null){
				continue;
			}
			List<Resources> rs = new ArrayList<Resources>(ss);
			if(rs.size()==0){
				continue;
			}
			boolean flag = false;
			for(Resources r :rs){
				if(r.getIsMenu()==1){
					m.setModuleAddress(r.getResourcesUrl());
					flag = true;
					break;
				}
			}
			if(!flag){
				m.setModuleAddress( rs.get(0).getResourcesUrl());
			}
		}*/
		SessionInfo.getCurUser().setModuleList(ModuleList);
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("IsPreferencesMenuUrl", IsPreferencesMenuUrl);
		m.put("moduleList", ModuleList);
		m.put("pubmenuList", pubmenuList);
		m.put("resouercesList", resouercesList);
		m.put("navigationList", navigationList);
		m.put("listSystemManager", SystemManagerlist);
		EHCacheHelper.putElement("authorizationCache", UserId, m);
		return m;
	}

	/**
	 *  查询module列表把其下的resource也查出来,存到Set<Resources>
	 */
	@Override
	public List<ModuleManager> findAll() {
		@SuppressWarnings("unchecked")
		List<ModuleManager> modulelist = dao.findByCondition("findAll", null);
		List<Resources> resourceslist = ResourcesDaoImpl.findAll();
		List<ModuleResourceRela> relamap = ResourcesDaoImpl.findObjectListByCondition("findModuleResourceRela",null);
		Set<Resources> resourceSet;
		for(ModuleResourceRela rela : relamap){
			resourceSet = new HashSet<Resources>();
			String moduleId = rela.getModuleId();
			String resourceId = rela.getResourceId();
			for(ModuleManager module:modulelist){
				if(module.getModuleId().equals(moduleId)){
					for(Resources resource:resourceslist){
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
		
		
	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
    public void updateByCondition(String sqlKey, Object params){
    	getDao().updateByCondition(sqlKey,params);
    }
	
	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public void deleteByConditions(String sqlKey, Object params) {
		getDao().deleteByConditions(sqlKey, params);
	};
	
	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
    public void addEntity(String sql,ModuleManager t) {
    	getDao().addEntity(sql,t);
    }
}
