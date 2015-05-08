package com.framework.aop.log;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("AopService")
@Transactional
public class AopService {
	@Resource(name="AopServiceDao")
	private AopServiceDaoImpl aopServiceDao;

	public void logAdd(AopLogEntity aopLogEntity) {
		aopServiceDao.logAdd(aopLogEntity);
	}

	/**   
	* @Title: logAdd   
	* @Description:    
	* @param @param 操作对象
	* @param @param 操作类型
	* @param @param 操作级别
	* @param @param 日志描述   
	* @return void      
	* @author guangchao
	* @date 2014-1-6 下午2:07:20 
	* @throws   
	*/ 
	
	public void logAdd(String OperatObject,String  LogType,int  LogLevel,String  LogMessage,int OperatResult) {
		aopServiceDao.logAdd(OperatObject,LogType,LogLevel,LogMessage,OperatResult);
	}

}
