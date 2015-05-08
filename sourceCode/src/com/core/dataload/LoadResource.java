package com.core.dataload;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.core.db.ILoadData;
import com.framework.entity.resources.Resources;


/**
 * 
* @ClassName: LoadRole
* @Description: 资源
* @author guangchao
* @date 2014-3-26 下午4:01:14
*
 */
public class LoadResource  implements ILoadData{
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T loadData(ResultSet rs) throws SQLException {
		Resources entity = new Resources();
		entity.setResourcesId(rs.getString("resourcesId"));
		entity.setResourcesName(rs.getString("resourcesName"));
		entity.setResourcesUrl(rs.getString("resourcesUrl"));
		entity.setIsMenu(rs.getInt("isMenu"));
		entity.setStatus(rs.getInt("Status"));
		entity.setCreatePerson(rs.getNString("CreatePerson"));
		entity.setCreateTime(rs.getDate("CreateTime"));
		entity.setUpdatePerson(rs.getNString("UpdatePerson"));
		entity.setUpdateTime(rs.getDate("UpdateTime"));
		return (T)entity;
	}
}