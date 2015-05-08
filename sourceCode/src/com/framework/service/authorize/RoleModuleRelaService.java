/**
 * @Title: RoleModuleRelaService.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.service.authorize
 * @Description: 
 * @author gaogc
 * @date 2014-08-29 17:36:54
 * @version V1.0
 */
package com.framework.service.authorize;

import java.util.List;

import com.framework.entity.authorize.RoleModuleRela;
import com.orm.BaseService;

/**
 * @ClassName: RoleModuleRelaService
 * @Description: 接口。
 * @author gaogc
 * @date 2014-08-29 17:36:54
 * 
 */
public interface RoleModuleRelaService extends BaseService<RoleModuleRela> {
	public boolean SelModule(List<RoleModuleRela> listModule,String[] arrayNoModule,String roleName);
}
