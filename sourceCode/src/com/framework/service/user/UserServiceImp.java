package com.framework.service.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.core.ehcache.EHCacheHelper;
import com.framework.dao.user.UserImp;
import com.framework.entity.resources.Resources;
import com.framework.entity.user.UserInfoAndDataManager;
import com.framework.service.resources.ResourcesService;
import com.framework.service.module.IModuleService;
import com.orm.BaseServiceImpl;
import com.orm.BaseService;
import com.util.Util;

@Service("userService")
@Transactional
public class UserServiceImp extends BaseServiceImpl<UserInfoAndDataManager> implements IUserService {
	@Resource(name = "userImp")
	private UserImp userImp;
	@Resource(name = "moduleServiceImp")
	private IModuleService moduleServiceImp;
	@Resource(name = "ResourcesServiceImp")
	private ResourcesService ResourcesService;
	
	
   @Override
    public BaseService<UserInfoAndDataManager> getDao() {
        return userImp;
    }

	@Override
	public void addObject(Object t) {
		
	}

	
	/**
	 * 获取当前人的权限集
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAuthorities(String userid) {
		List<Resources> resouercesList = new ArrayList<Resources>();
		List<String> authorities = new ArrayList<String>();
		Map<String,Object> m  = (Map<String, Object>) EHCacheHelper.getElementValue("authorizationCache", userid);
		if(m.isEmpty()){
			Map<String, Object> params = new HashMap<String, Object>();
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			if(request==null){
				return authorities;
			}
			params.put("UserId", userid);
			resouercesList = ResourcesService.findByCondition("getAllResourcesForUser", params);
		}else{
			resouercesList = (List<Resources>) m.get("resouercesList");
		}
		for(Resources resource:resouercesList){
			String Permission = resource.getPermissionSet();
			String[] array = Util.stringToArray(Permission, ",");
			for(String str:array){
				authorities.add(str);
			}
		}
		return authorities;
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
    public void addEntity(String sql,UserInfoAndDataManager t) {
    	getDao().addEntity(sql,t);
    }
}
