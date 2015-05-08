package com.framework.interceptor;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.framework.entity.logmanager.LogConfig;
import com.framework.entity.logmanager.Logmanager;
import com.framework.entity.user.UserInfoManager;
import com.framework.service.logconfig.LogConfigService;
import com.framework.service.logmanager.LogmanagerService;
import com.util.SessionInfo;



/**
 * Interceptor - 日志记录
 * 
 * @author CreativeWises Team
 * @version 3.0
 */
public class LogInterceptor extends HandlerInterceptorAdapter {

	/** 默认忽略参数 */
	private static final String[] DEFAULT_IGNORE_PARAMETERS = new String[] { "password", "rePassword", "currentPassword" };

	/** antPathMatcher */
	private static AntPathMatcher antPathMatcher = new AntPathMatcher();

	/** 忽略参数 */
	private String[] ignoreParameters = DEFAULT_IGNORE_PARAMETERS;

	@Resource(name = "logConfigServiceImpl")
	private LogConfigService logConfigService;
	@Resource(name = "logmanagerService")
	private LogmanagerService logService;
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		List<LogConfig> logConfigs = logConfigService.getAll();
		System.out.println("===================="+logConfigs);
		if (logConfigs != null) {
			String path = request.getServletPath();
			System.out.println("===================="+path);
			for (LogConfig logConfig : logConfigs) {
				System.out.println("===================="+logConfig.getUrlPattern());
				if (antPathMatcher.match(logConfig.getUrlPattern(), path)) {
					String LogType = logConfig.getLogType();
					int LogLevel = logConfig.getLogLevel();
					String operatObject=logConfig.getOperatObject();
					String content = (String) request.getAttribute(Logmanager.LOG_CONTENT_ATTRIBUTE_NAME);
					String ip = request.getRemoteAddr();
					request.removeAttribute(Logmanager.LOG_CONTENT_ATTRIBUTE_NAME);
					StringBuffer parameter = new StringBuffer();
					Map<String, String[]> parameterMap = request.getParameterMap();
					if (parameterMap != null) {
						for (Entry<String, String[]> entry : parameterMap.entrySet()) {
							String parameterName = entry.getKey();
							if (!ArrayUtils.contains(ignoreParameters, parameterName)) {
								String[] parameterValues = entry.getValue();
								if (parameterValues != null) {
									for (String parameterValue : parameterValues) {
										parameter.append(parameterName + " = " + parameterValue + "\n");
									}
								}
							}
						}
					}
					Logmanager log = new Logmanager();
					
					log.setContent(content);
					
					UserInfoManager userInfoManager = SessionInfo.getCurUser();
			        String userId = userInfoManager.getUserId();
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
			    	log.setParameter(parameter.toString());
			    	//设置操作人
			    	log.setOperatPerson(userId);
			    	//设置操作类型
			    	log.setLogType(LogType);
			    	//设置等级
			    	log.setLogLevel(LogLevel);
			    	//设置等级
			    	log.setOperatObject(operatObject);
			    	
					logService.addEntity(log);
					break;
				}
			}
		}
	}

	/**
	 * 设置忽略参数
	 * 
	 * @return 忽略参数
	 */
	public String[] getIgnoreParameters() {
		return ignoreParameters;
	}

	/**
	 * 设置忽略参数
	 * 
	 * @param ignoreParameters
	 *            忽略参数
	 */
	public void setIgnoreParameters(String[] ignoreParameters) {
		this.ignoreParameters = ignoreParameters;
	}

}