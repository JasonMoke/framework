package com.core.dataload;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.core.db.ILoadData;
import com.framework.entity.user.UserDataManager;


/**
 * 
* @ClassName: LoadUserData
* @Description: 用户实体
* @author guangchao
* @date 2014-3-26 下午4:01:14
*
 */
public class LoadUserData  implements ILoadData{
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T loadData(ResultSet rs) throws SQLException {
		UserDataManager entity = new UserDataManager();
		entity.setUserId(rs.getNString("UserId"));
		entity.setFullName(rs.getNString("FullName"));
		entity.setContactPhone(rs.getNString("ContactPhone"));
		entity.setOfficePhone(rs.getNString("OfficePhone"));
		entity.setFaxNumber(rs.getNString("FaxNumber"));
		entity.setEmailAddress(rs.getNString("EmailAddress"));
		entity.setContactAddress(rs.getNString("ContactAddress"));
		entity.setZipCode(rs.getNString("ZipCode"));
		entity.setOrganId(rs.getNString("OrganId"));
		entity.setDetpId(rs.getNString("DetpId"));
		entity.setUserRemark(rs.getNString("UserRemark"));
		entity.setStatus(rs.getInt("Status"));
		entity.setCreatePerson(rs.getNString("CreatePerson"));
		entity.setCreateTime(rs.getDate("CreateTime"));
		entity.setUpdatePerson(rs.getNString("UpdatePerson"));
		entity.setUpdateTime(rs.getDate("UpdateTime"));
		return (T)entity;
	}
}