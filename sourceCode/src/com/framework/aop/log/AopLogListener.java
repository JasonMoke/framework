package com.framework.aop.log;


import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.framework.entity.logmanager.LogConfig;
import com.framework.entity.logmanager.Logmanager;
import com.framework.entity.user.UserInfoManager;
import com.framework.service.logconfig.LogConfigService;
import com.framework.service.logmanager.LogmanagerService;
import com.opensymphony.xwork2.ActionContext;
import com.util.SessionInfo;

@Component("AopLogListener")
@Aspect
public class AopLogListener {

	@Resource(name = "logConfigServiceImpl")
	private LogConfigService logConfigService;
	@Resource(name = "logmanagerService")
	private LogmanagerService logService;
//	@Autowired
//	private QueueSender queueSender;
//	@Autowired
//	private MessageProducer queueProducer;
	// 通用查询调用
	@Pointcut("execution(* com.framework.action..*(..))")
	public void usedMeth() {
	}
	@After("usedMeth()")
	public void afterMethod(JoinPoint point) {
		
		String pathName=point.getTarget().getClass().getName();
		String methName=point.getSignature().getName(); 
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST); 
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
		
		String path = pathName+"."+methName;
		
//		System.out.println("_________________________________________"+path);
		
		String ip=request.getRemoteAddr();
		
		Logmanager log = new Logmanager();
		
		List<LogConfig> logConfigs = logConfigService.getAll();
		if (logConfigs != null) {
			
			for (LogConfig logConfig : logConfigs) {
				
				if (logConfig.getUrlPattern().equals(path)) {
					String LogType = logConfig.getLogType();
					int LogLevel = logConfig.getLogLevel();
					String operatObject=logConfig.getOperatObject();
					String userId = "";
					UserInfoManager userInfoManager = SessionInfo.getCurUser();
					userId = userInfoManager.getUserId();
					
					// 操作结果
					String flag=response.getHeader("flag");
					if("success".equals(flag)){
						log.setOperatResult(1);
					}else{
						log.setOperatResult(0);
					}
					
			        // 主键
			        log.setLogId(UUID.randomUUID().toString());
			       
			        //设置创建人
			        log.setCreatePerson(userId);
			        //设置创建时间
			        log.setCreateTime(new Date());
			        //设置修改人
			        log.setUpdatePerson(userId);
			      	//设置修改时间
			      	log.setUpdateTime(new Date());
			        //设置状态
			    	log.setStatus((byte) 1);
			    	//设置IP
			    	log.setOperatIp(ip);
			    	//设置传入参数
//			    	log.setParameter(parameter.toString());
			    	//设置操作人
			    	log.setOperatPerson(userId);
			    	//设置操作类型
			    	log.setLogType(LogType);
			    	//设置等级
			    	log.setLogLevel(LogLevel);
			    	//设置等级
			    	log.setOperatObject(operatObject);
			    	
			    	//设置备注
			    	String mes=response.getHeader("message");
			    	if(mes!=null){
			    		log.setLogMessage(mes);
			    	}else{
			    		log.setLogMessage("");
			    	}
			    	
			    	log.setOperatTime(new Date());
			    	
//			    	if("AOP".equals(GetConfig.getLogAsynchronous())){
			    		logService.addEntity(log);
//			    	}else{
//			    		//将日志对象插入消息队列，实现异步持久化
//			    		queueProducer.sendMessage(log);
//			    	}
				}
			}
		}
	}
	
}
