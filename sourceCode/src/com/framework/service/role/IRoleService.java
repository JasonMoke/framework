/**
* @Title: IModuleService.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.framework.service.module   
* @Description:  角色管理   
* @author guangchao    
* @date 2013-12-23 下午1:15:58   
* @version V1.0 
*/
package com.framework.service.role;

import java.util.List;

import com.framework.entity.role.RoleManager;
import com.framework.entity.role.UserRoleManager;
import com.orm.BaseService;
/**
 * @ClassName: IModuleService
 * @Description: 角色管理
 * @author guangchao
 * @date 2013-12-23 下午1:15:58
 *
 */
public interface IRoleService extends BaseService<RoleManager> {

	public List<RoleManager> getRoleByUserId(String userId);	
	
	public List<UserRoleManager> getUserRoleByUserId(String userId);	
	public boolean updateRoleStatus(RoleManager manager);
}
