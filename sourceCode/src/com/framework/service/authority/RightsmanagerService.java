/**
 * @Title: RightsmanagerService.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.service.rightsmanager
 * @Description: 
 * @author zhaojie
 * @date 2014-01-20 15:23:11
 * @version V1.0
 */

package com.framework.service.authority;


import java.util.List;

import com.framework.entity.authority.RightsManager;
import com.orm.BaseService;

/**
 * @ClassName: RightsmanagerService
 * @Description: 接口。
 * @author zhaojie
 * @date 2014-01-20 15:23:11
 * 
 */
public interface RightsmanagerService extends BaseService<RightsManager> {
	public boolean selRightManager(List<RightsManager> list,String RoleName);
}
