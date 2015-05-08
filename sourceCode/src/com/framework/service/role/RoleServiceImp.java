/**
* @Title: ModuleServiceImp.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.framework.service.module   
* @Description: 模块管理  
* @author guangchao    
* @date 2013-12-23 下午1:16:17   
* @version V1.0 
*/
package com.framework.service.role;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.framework.dao.role.RoleDao;
import com.framework.entity.role.RoleManager;
import com.framework.entity.role.UserRoleManager;
import com.orm.BaseServiceImpl;
import com.orm.BaseService;

/**
 * @ClassName: ModuleServiceImp
 * @Description: 模块管理
 * @author guangchao
 * @date 2013-12-23 下午1:16:17
 *
 */
@Service("roleServiceImp")
@Transactional
public class RoleServiceImp extends BaseServiceImpl<RoleManager> implements IRoleService{
	
	@Resource(name = "roleDaoImpl")
	private RoleDao dao;
	
	
	/**
	 * 
	* @Title: getRoleByUserId   
	* @Description:    根据用户id获取其角色 
	* @param @param userId
	* @param @return    
	* @return List<RoleManager>      
	* @author guangchao
	* @date 2013-12-24 下午1:48:02 
	* @throws
	 */
	public List<RoleManager> getRoleByUserId(String userId){
		return dao.getRoleByUserId(userId);
		
	}
	/**
	 * 
	* @Title: getUserRoleByUserId   
	* @Description:    根据用户id获取其角色id  
	* @param @param userId
	* @param @return    
	* @return List<UserRoleManager>      
	* @author guangchao
	* @date 2013-12-24 下午1:49:34 
	* @throws
	 */
	public List<UserRoleManager> getUserRoleByUserId(String userId){
		return dao.getUserRoleByUserId(userId);
		
	}
	@Override
	public BaseService<RoleManager> getDao() {
		return dao;
	}
	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public boolean updateRoleStatus(RoleManager manager) {
		return dao.updateRoleStatus(manager);
	}
	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
    public void updateEntity(RoleManager t) {
        getDao().updateEntity(t);
    }
	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
    public void addEntity(RoleManager t) {
        getDao().addEntity(t);
    }
	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
    public void updateByCondition(String sqlKey, Object params){
    	getDao().updateByCondition(sqlKey,params);
    }
}
