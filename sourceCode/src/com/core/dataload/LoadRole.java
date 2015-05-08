package com.core.dataload;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.core.db.ILoadData;
import com.framework.entity.role.RoleManager;


/**
 * 
* @ClassName: LoadRole
* @Description: 用户角色
* @author guangchao
* @date 2014-3-26 下午4:01:14
*
 */
public class LoadRole  implements ILoadData{
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T loadData(ResultSet rs) throws SQLException {
		RoleManager entity = new RoleManager();
		entity.setRoleName(rs.getNString("RoleName"));
		entity.setRoleRemark(rs.getNString("RoleRemark"));
		entity.setRoleNumber(rs.getInt("RoleNumber"));
		entity.setStatus(rs.getInt("Status"));
		entity.setCreatePerson(rs.getNString("CreatePerson"));
		entity.setCreateTime(rs.getDate("CreateTime"));
		entity.setUpdatePerson(rs.getNString("UpdatePerson"));
		entity.setUpdateTime(rs.getDate("UpdateTime"));
		return (T)entity;
	}
}