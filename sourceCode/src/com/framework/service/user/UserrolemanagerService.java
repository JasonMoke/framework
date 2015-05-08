/**
 * @Title: UserrolemanagerService.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.service.userrolemanager
 * @Description: 
 * @author zhaojie
 * @date 2014-01-22 10:29:14
 * @version V1.0
 */
package com.framework.service.user;


import java.util.List;

import com.framework.entity.role.UserRoleManager;
import com.framework.entity.user.UserDataManager;
import com.orm.BaseService;

/**
 * @ClassName: UserrolemanagerService
 * @Description: 接口。
 * @author zhaojie
 * @date 2014-01-22 10:29:14
 * 
 */
public interface UserrolemanagerService extends BaseService<UserRoleManager> {
	public boolean selRoleManager(List<UserRoleManager> list,String RoleId);
	public boolean selRoleByUsers(List<UserRoleManager> list,String[] UserIdarray) ;
	public boolean selUser(List<UserDataManager> list);
}
