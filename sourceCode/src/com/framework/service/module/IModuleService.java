/**
* @Title: IModuleService.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.framework.service.module   
* @Description:  模块管理   
* @author guangchao    
* @date 2013-12-23 下午1:15:58   
* @version V1.0 
*/
package com.framework.service.module;


import java.util.Map;

import com.framework.entity.module.ModuleManager;
import com.orm.BaseService;
/**
 * @ClassName: IModuleService
 * @Description: 模块管理
 * @author guangchao
 * @date 2013-12-23 下午1:15:58
 *
 */
public interface IModuleService extends BaseService<ModuleManager> {
	
	public Map<String,Object> getModuleByUserId(Map<?, ?> param);
	
	
}
