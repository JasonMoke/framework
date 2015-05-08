/**
* @Title: LoadSysParam.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.oneworld.core.dataload   
* @Description:    
* @author guangchao    
* @date 2014-3-27 上午11:35:41   
* @version V1.0 
*/
package com.core.dataload;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.core.db.ILoadData;
import com.framework.entity.systemparam.Systemparam;


/**
 * @ClassName: LoadSysParam
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author guangchao
 * @date 2014-3-27 上午11:35:41
 *
 */
public class LoadSysParam implements ILoadData{

	@SuppressWarnings("unchecked")
	@Override
	public <T> T loadData(ResultSet rs) throws SQLException {
		
		Systemparam entity=new Systemparam();
		entity.setParamId(rs.getNString("ParamId"));
		entity.setParamName(rs.getNString("ParamName"));
		entity.setParamValue(rs.getNString("ParamValue"));
		entity.setParamRemark(rs.getNString("ParamRemark"));
		entity.setParamNumber(rs.getInt("ParamNumber"));
		entity.setSystemCode(rs.getNString("SystemCode"));
		entity.setStatus(rs.getInt("Status"));
		entity.setCreatePerson(rs.getNString("CreatePerson"));
		entity.setCreateTime(rs.getDate("CreateTime"));
		entity.setUpdatePerson(rs.getNString("UpdatePerson"));
		entity.setUpdateTime(rs.getDate("UpdateTime"));
		
		return (T)entity;
		
	}

}
