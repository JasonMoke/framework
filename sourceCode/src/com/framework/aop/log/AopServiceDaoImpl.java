package com.framework.aop.log;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.framework.entity.user.UserInfoManager;
import com.util.GetConfig;
import com.util.SessionInfo;

@Repository("AopServiceDao")
public class AopServiceDaoImpl {
	@Autowired
    private SqlSession sqlSession;
	
	String logLevel = GetConfig.getLogLevel();
	String logEnabled = GetConfig.getLogEnabled();
	/**
	 * 
	* @Title: logAdd   
	* @Description:    
	* @param @param aopLogEntity    
	* @return void      
	* @author guangchao
	* @date 2014-1-6 下午4:03:13 
	* @throws
	 */
	public void logAdd(AopLogEntity aopLogEntity) {
		if("true".equals(logEnabled)){
			if(!"".equals(aopLogEntity.getLogLevel())&&!"".equals(logLevel)){
				if(aopLogEntity.getLogLevel()>=Integer.parseInt(logLevel)){
					HttpServletRequest request = ServletActionContext.getRequest();
					UserInfoManager userInfoManager = SessionInfo.getCurUser();
					String OperatPerson = userInfoManager.getUserId();
					String ip=request.getRemoteAddr();
					Date date=new Date();
					aopLogEntity.setLogId(UUID.randomUUID().toString());
					aopLogEntity.setOperatIp(ip);
					aopLogEntity.setOperatPerson(OperatPerson);
					aopLogEntity.setOperatTime(date);
					sqlSession.insert("addLog",userInfoManager);
				}
			}
		}
	}
	/**
	 * 
	* @Title: logAdd   
	* @Description:    
	* @param @param OperatObject 操作对象
	* @param @param LogType 日志类型
	* @param @param LogLevel 日志登记
	* @param @param LogMessage    日志描述
	* @return void      
	* @author guangchao
	* @date 2014-1-6 下午2:03:35 
	* @throws
	 */
	public void logAdd(String OperatObject,String  LogType,int  LogLevel,String  LogMessage,int OperatResult) {
		if("true".equals(logEnabled)){
			if(!"".equals(LogLevel)&&!"".equals(logLevel)){
				if(LogLevel>=Integer.parseInt(logLevel)){
					AopLogEntity aopLogEntity = new AopLogEntity();
					HttpServletRequest request = ServletActionContext.getRequest();
					UserInfoManager userInfoManager = SessionInfo.getCurUser();
					String OperatPerson = userInfoManager.getUserId();
					String ip=request.getRemoteAddr();
					Date date=new Date();
					aopLogEntity.setLogId(UUID.randomUUID().toString());
					aopLogEntity.setLogLevel(LogLevel);
					aopLogEntity.setLogMessage(LogMessage);
					aopLogEntity.setLogType(LogType);
					aopLogEntity.setOperatIp(ip);
					aopLogEntity.setOperatObject(OperatObject);
					aopLogEntity.setOperatPerson(OperatPerson);
					aopLogEntity.setOperatResult(OperatResult);
					aopLogEntity.setOperatTime(date);
					aopLogEntity.setCreateTime(date);
					aopLogEntity.setCreatePerson(OperatPerson);
					aopLogEntity.setUpdatePerson(OperatPerson);
					aopLogEntity.setUpdateTime(date);
					aopLogEntity.setStatus(1);
					sqlSession.insert("addLog",aopLogEntity);
				}
			}
		}
	}



}
