package com.core.dataload;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.core.db.ILoadData;
import com.framework.entity.module.ModuleManager;


/**
 * 
* @ClassName: LoadModule
* @Description: 模块
* @author guangchao
* @date 2014-3-26 下午4:01:14
*
 */
public class LoadModule  implements ILoadData{
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T loadData(ResultSet rs) throws SQLException {
		ModuleManager entity = new ModuleManager();
		entity.setModuleId(rs.getNString("ModuleId"));
		entity.setModuleName(rs.getNString("ModuleName"));
		entity.setModuleParent(rs.getNString("ModuleParent"));
		entity.setModuleAddress(rs.getNString("ModuleAddress"));
		entity.setModuleRemark(rs.getNString("ModuleRemark"));
		entity.setModuleNumber(rs.getInt("ModuleNumber"));
		entity.setStatus(rs.getInt("Status"));
		return (T)entity;
	}
}