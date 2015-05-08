/**
* @Title: ModuleDaoImpl.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.framework.dao.module   
* @Description: 模块管理   
* @author guangchao    
* @date 2013-12-23 下午1:14:21   
* @version V1.0 
*/
package com.framework.dao.module;



import org.springframework.stereotype.Repository;

import com.framework.entity.module.ModuleManager;
import com.orm.BaseDaoImpl;

/**
 * @ClassName: ModuleDaoImpl
 * @Description: 模块管理
 * @author guangchao
 * @date 2013-12-23 下午1:14:21
 *
 */
@Repository("moduleDaoImpl")
public class ModuleDaoImpl extends BaseDaoImpl<ModuleManager> implements ModuleDao{

	public String getNameSpace() {
		return "com.framework.module";
	}

}
