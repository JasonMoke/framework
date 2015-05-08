/**
* @Title: RoleDao.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.framework.dao.role   
* @Description:    
* @author guangchao    
* @date 2014-1-1 下午7:13:21   
* @version V1.0 
*/
package com.framework.dao.role;

import java.util.List;

import com.framework.entity.role.RoleManager;
import com.framework.entity.role.UserRoleManager;
import com.orm.BaseService;

/**
 * @ClassName: RoleDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author guangchao
 * @date 2014-1-1 下午7:13:21
 *
 */
public interface RoleDao extends BaseService<RoleManager>{

	/**   
	* @Title: getRoleByUserId   
	* @Description:    
	* @param @param userId
	* @param @return    
	* @return List<RoleManager>      
	* @author guangchao
	* @date 2014-1-1 下午7:19:49 
	* @throws   
	*/ 
	
	List<RoleManager> getRoleByUserId(String userId);

	/**   
	* @Title: getUserRoleByUserId   
	* @Description:    
	* @param @param userId
	* @param @return    
	* @return List<UserRoleManager>      
	* @author guangchao
	* @date 2014-1-1 下午7:19:59 
	* @throws   
	*/ 
	
	List<UserRoleManager> getUserRoleByUserId(String userId);
	boolean updateRoleStatus(Object o);
}
