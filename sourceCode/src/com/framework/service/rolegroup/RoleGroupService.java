/**
 * @Title: RoleGroupService.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.service.rolegroup
 * @Description: 
 * @author gaogc
 * @date 2014-08-29 13:55:02
 * @version V1.0
 */
package com.framework.service.rolegroup;


import java.util.List;

import com.framework.entity.rolegroup.RoleGroup;
import com.framework.entity.rolegroup.RolegroupRoleRela;
import com.orm.BaseService;

/**
 * @ClassName: RoleGroupService
 * @Description: 接口。
 * @author gaogc
 * @date 2014-08-29 13:55:02
 * 
 */
public interface RoleGroupService extends BaseService<RoleGroup> {

	/**
	 * 
	* @Title: addRolegroupRoleRela   
	* @Description:角色组与角色关系
	* @param list
	* @param roleGroupId
	* @return void        
	* @author gaoguangchao
	* @date 2014年8月29日 下午4:55:36 
	*
	 */
	void addRolegroupRoleRela(List<RolegroupRoleRela> list, String roleGroupId);


}
