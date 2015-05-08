package com.core.dataload;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.core.db.ILoadData;
import com.framework.entity.systemparam.Systemparam;


/**
 * @ClassName: LoadSystemParam 
 * @Description: 系统参数数据加载
 * @author:  tanyi
 * @date: 2014-01-16 14:11:04
 */ 
public class LoadSystemParam implements ILoadData{

	/**
	 * @ClassName: loadData 
	 * @Description: 系统参数数据加载
	 * @param rs 记录集
	 * @return 返回加载后的系统参数实体对象
	 * @author:  tanyi
	 * @date: 2014-01-16 14:11:04
	 */ 
	@SuppressWarnings("unchecked")
	@Override
	public <T> T loadData(ResultSet rs) throws SQLException {
		Systemparam entity = new Systemparam();
		entity.setParamId(rs.getNString("ParamId"));
		entity.setParamName(rs.getNString("ParamName"));
		entity.setParamValue(rs.getNString("ParamValue"));
		entity.setSystemCode(rs.getNString("SystemCode"));
		entity.setStatus(rs.getInt("Status"));
		entity.setParamNumber(rs.getInt("ParamNumber"));
		entity.setParamRemark(rs.getNString("ParamRemark"));
		return (T)entity;
	}
}