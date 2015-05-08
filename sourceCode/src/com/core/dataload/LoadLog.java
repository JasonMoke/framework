/**
* @Title: LoadLog.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.oneworld.core.dataload   
* @Description:    
* @author guangchao    
* @date 2014-3-27 上午11:35:32   
* @version V1.0 
*/
package com.core.dataload;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.core.db.ILoadData;
import com.framework.entity.logmanager.Logmanager;


/**
 * @ClassName: LoadLog
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author guangchao
 * @date 2014-3-27 上午11:35:32
 *
 */
public class LoadLog implements ILoadData{

	@SuppressWarnings("unchecked")
	@Override
	public <T> T loadData(ResultSet rs) throws SQLException {
		
		Logmanager entity=new Logmanager();
		entity.setLogId(rs.getNString("LogId"));
		entity.setLogType(rs.getNString("LogType"));
		entity.setLogLevel(rs.getInt("LogLevel"));
		entity.setLogMessage(rs.getNString("LogMessage"));
		entity.setOperatPerson(rs.getNString("OperatPerson"));
		entity.setOperatIp(rs.getNString("OperatIp"));
		entity.setOperatObject(rs.getNString("OperatObject"));
		entity.setOperatTime(rs.getDate("OperatTime"));
//		entity.setOperatTimeStart(rs.getNString("OperatTimeStart"));
//		entity.setOperatTimeEnd(rs.getNString("OperatTimeEnd"));
		entity.setOperatResult(rs.getInt("OperatResult"));
		entity.setStatus(rs.getInt("Status"));
		entity.setCreatePerson(rs.getNString("CreatePerson"));
		entity.setCreateTime(rs.getDate("CreateTime"));
		entity.setUpdatePerson(rs.getNString("UpdatePerson"));
		entity.setUpdateTime(rs.getDate("UpdateTime"));		
		
		return (T)entity;
		
	}

}
