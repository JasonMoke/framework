/**
* @Title: LogHelper.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.oneworld.core.helper.log   
* @Description:    
* @author guangchao    
* @date 2014-3-26 下午6:09:20   
* @version V1.0 
*/
package com.core.helper.log;

import java.util.Date;

import org.jgroups.util.UUID;

import com.core.db.DBHelper;
import com.core.helper.user.UserHelper;
import com.framework.entity.logmanager.Logmanager;

/**
 * @ClassName: LogHelper
 * @Description: 日志管理Helper工具类
 * @author guangchao
 * @date 2014-3-26 下午6:09:20
 *
 */
public class LogHelper {
	
	/**
	 * @throws Exception 
	 * 
	* @Title: addLog   
	* @Description:   添加一个Logmanager实体 
	* @param @param logmanager
	* @param @return    
	* @return boolean      
	* @author guangchao
	* @date 2014-3-27 上午10:39:01 
	* @throws
	 */
	public static boolean logAdd(Logmanager logmanager) throws Exception{
		
		String LogId=logmanager.getLogId();
		String LogType=logmanager.getLogType();
		Integer LogLevel=logmanager.getLogLevel();
		String LogMessage=logmanager.getLogMessage();
		String OperatPerson=logmanager.getOperatPerson();
		String OperatIp=logmanager.getOperatIp();
		String OperatObject=logmanager.getOperatObject();
		Date OperatTime=logmanager.getOperatTime();
//		String OperatTimeStart=logmanager.getOperatTimeStart();
//		String OperatTimeEnd=logmanager.getOperatTimeEnd();
		Integer OperatResult=logmanager.getOperatResult();
		Integer Status=logmanager.getStatus();
		String CreatePerson=logmanager.getCreatePerson();
		Date CreateTime=logmanager.getCreateTime();
		String UpdatePerson=logmanager.getUpdatePerson();
		Date UpdateTime=logmanager.getUpdateTime();
		
		String sql="INSERT INTO t_log VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		int result=DBHelper.executeNonQuery(sql, LogId,LogType,LogLevel,LogMessage,OperatPerson,OperatIp,
				OperatObject,OperatTime,OperatResult,Status,CreatePerson,CreateTime,UpdatePerson,UpdateTime);
		
		if(result>=1){
			return true;
		}
		return false;
	}
	
	/**
	 * @throws Exception 
	 * 
	* @Title: logAdd   
	* @Description:    通过参数添加日志
	* @param @param OperatObject
	* @param @param LogType
	* @param @param LogLevel
	* @param @param LogMessage
	* @param @param OperatResult
	* @param @return    
	* @return boolean      
	* @author guangchao
	* @date 2014-3-27 上午10:41:36 
	* @throws
	 */
	public static boolean logAdd(String OperatObject,String  LogType,int  LogLevel,String  LogMessage,int OperatResult) throws Exception{
//		"渠道管理","新增",5,"addChannel","success".equals(flag)?1:0
		Logmanager logmanager = new Logmanager();
		logmanager.setCreatePerson(UserHelper.getCurUserId());
		logmanager.setLogId(UUID.randomUUID().toString());
		
		String sql="INSERT INTO t_log (OperatObject,LogType,LogLevel,LogMessage,OperatResult) VALUES(?,?,?,?,?)";
		
		int result=DBHelper.executeNonQuery(sql, OperatObject,LogType,LogLevel,LogMessage,OperatResult);
		
		if(result>=1){
			return true;
		}
	
		return false;	
	}
}
