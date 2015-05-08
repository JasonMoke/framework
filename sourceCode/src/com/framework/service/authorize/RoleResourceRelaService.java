/**
 * @Title: RoleResourceRelaService.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.service.authorize
 * @Description: 
 * @author gaogc
 * @date 2014-08-29 17:37:08
 * @version V1.0
 */
package com.framework.service.authorize;

import java.util.List;

import com.framework.entity.authorize.RoleResourceRela;
import com.orm.BaseService;

/**
 * @ClassName: RoleResourceRelaService
 * @Description: 接口。
 * @author gaogc
 * @date 2014-08-29 17:37:08
 * 
 */
public interface RoleResourceRelaService extends BaseService<RoleResourceRela> {
	public boolean SelResources(List<RoleResourceRela> listResources,String[] arrayNoResources,String roleName);
}
