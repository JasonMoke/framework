package com.core.dataload;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.core.db.ILoadData;
import com.framework.entity.user.UserInfoManager;


/**
 * 
* @ClassName: LoadUserInfo
* @Description: 用户实体
* @author guangchao
* @date 2014-3-26 下午4:01:14
*
 */
public class LoadUserInfo  implements ILoadData{
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T loadData(ResultSet rs) throws SQLException {
		UserInfoManager entity = new UserInfoManager();
		entity.setUserId(rs.getNString("UserId"));
		entity.setUserKey(rs.getNString("UserKey"));
		entity.setUserName(rs.getNString("UserName"));
		entity.setUserPwd(rs.getNString("UserPwd"));
		entity.setStatus(rs.getInt("Status"));
		entity.setCreatePerson(rs.getNString("CreatePerson"));
		entity.setCreateTime(rs.getDate("CreateTime"));
		entity.setUpdatePerson(rs.getNString("UpdatePerson"));
		entity.setUpdateTime(rs.getDate("UpdateTime"));
		return (T)entity;
	}
}