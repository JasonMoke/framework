/**
* @Title: BaseAction.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.util   
* @Description:  工具类
* @author guangchao    
* @date 2013-12-26 上午11:05:57   
* @version V1.0 
*/
package com.core.base;

import com.core.database.databean.Data;
import com.framework.entity.user.UserInfoManager;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.util.SessionInfo;
import com.util.Util;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;

/**
 * 
* @ClassName: BaseAction
* @Description: action 基类
* @author gaoguangchao
* @date 2014年5月30日 上午10:34:18
*
 */
public abstract class BaseAction extends ActionSupport	implements Action
	
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8737142656681094642L;
	protected Data data = new Data();
	
	
	public BaseAction()
	{
	}
	/**
	 * 
	* @Title: getRequest   
	* @Description:获取ServletActionContext.getRequest()
	* @return
	* @return HttpServletRequest        
	* @author gaoguangchao
	* @date 2014年6月10日 上午11:36:59 
	*
	 */
	protected HttpServletRequest getRequest()
	{
		return ServletActionContext.getRequest();
	}
	/**
	 * 
	* @Title: getResponse   
	* @Description:获取ServletActionContext.getResponse()
	* @return
	* @return HttpServletResponse        
	* @author gaoguangchao
	* @date 2014年6月10日 上午11:37:19 
	*
	 */
	protected HttpServletResponse getResponse()
	{
		return ServletActionContext.getResponse();
	}
	/**
	 * 
	* @Title: getSession   
	* @Description:获取ActionContext.getContext().getSession()
	* @return
	* @return Map        
	* @author gaoguangchao
	* @date 2014年6月10日 上午11:37:31 
	*
	 */
	protected Map<?, ?> getSession()
	{
		return ActionContext.getContext().getSession();
	}
	/**
	 * 
	* @Title: getHttpSession   
	* @Description:获取HttpSession
	* @param flag	除非我们确认session一定存在或者sesson不存在时明确有创建session的需要，否则 尽量使用false
	* @return
	* @return HttpSession        
	* @author gaoguangchao
	* @date 2014年6月10日 上午11:37:45 
	*
	 */
	protected HttpSession getHttpSession(boolean flag)
	{
		return getRequest().getSession(flag);
	}
	/**
	 * 
	* @Title: getHttpSession   
	* @Description:获取HttpSession	sesson不存在时不创建session
	* @return
	* @return HttpSession        
	* @author gaoguangchao
	* @date 2014年6月10日 上午11:38:37 
	*
	 */
	protected HttpSession getHttpSession()
	{
		return getRequest().getSession(false);
	}
	/**
	 * 
	* @Title: getRequestObject   
	* @Description:
	* @param request
	* @return
	* @return Map        
	* @author gaoguangchao
	* @date 2014年6月10日 上午11:39:04 
	*
	 */
	protected Map<?, ?> getRequestObject(HttpServletRequest request)
	{
		return ServletTools.getRequestObject(getRequest());
	}
	protected int getNowPage()
	{
		return ServletTools.getNowPage(getRequest());
	}
	protected int getPageSize(int defaultValue)
	{
		return ServletTools.getPageSize(getRequest(), defaultValue);
	}
	/**
	 * 
	* @Title: getParameter   
	* @Description:getParameter
	* @param name 参数名称
	* @param defaultValue	如果为空，返回默认值
	* @return String        
	* @author gaoguangchao
	* @date 2014年6月10日 上午11:39:25 
	*
	 */
	protected String getParameter(String name, String defaultValue)
	{
		return ServletTools.getParameter(name, getRequest(), defaultValue);
	}
	/**
	 * 
	* @Title: getParameterInt   
	* @Description:
	* @param name 参数名称
	* @param defaultValue	如果为空，返回默认值
	* @return int        
	* @author gaoguangchao
	* @date 2014年6月10日 上午11:39:55 
	*
	 */
	protected int getParameterInt(String name, String defaultValue)
	{
		return ServletTools.getParameterInt(name, getRequest(), defaultValue);
	}
	/**
	 * 
	* @Title: getParameterValues   
	* @Description:
	* @param name 参数名称
	* @return String[]        
	* @author gaoguangchao
	* @date 2014年6月10日 上午11:40:19 
	*
	 */
	protected String[] getParameterValues(String name)
	{
		return ServletTools.getParameterValues(name, getRequest());
	}
	/**
	 * 
	* @Title: getParameter   
	* @Description:getParameter 比较常用的方法
	* @param name
	* @return String        
	* @author gaoguangchao
	* @date 2014年6月10日 上午11:40:33 
	*
	 */
	protected String getParameter(String name)
	{
		return ServletTools.getParameter(name, getRequest());
	}
	/**
	 * 
	* @Title: getAttribute   
	* @Description:
	* @param name
	* @return
	* @return Object        
	* @author gaoguangchao
	* @date 2014年6月10日 上午11:41:01 
	*
	 */
	protected Object getAttribute(String name)
	{
		return ServletTools.getAttribute(name, getRequest());
	}
	/**
	 * 
	* @Title: setAttribute   
	* @Description:
	* @param name
	* @param obj
	* @return void        
	* @author gaoguangchao
	* @date 2014年6月10日 上午11:41:09 
	*
	 */
	protected void setAttribute(String name, Object obj)
	{
		getRequest().setAttribute(name, obj);
	}
	/**
	 * 
	* @Title: getSession   
	* @Description:
	* @param flag
	* @return
	* @return HttpSession        
	* @author gaoguangchao
	* @date 2014年6月10日 上午11:41:12 
	*
	 */
	protected HttpSession getSession(boolean flag)
	{
		return getRequest().getSession(flag);
	}
	/**
	 * 
	* @Title: getCurUser   
	* @Description:获取当前登录人信息
	* @return
	* @return UserInfoManager        
	* @author gaoguangchao
	* @date 2014年6月10日 上午11:42:05 
	*
	 */
	protected UserInfoManager getCurUser()
	{
		//获取当前登陆人
        UserInfoManager userInfoManager = SessionInfo.getCurUser();
        return userInfoManager;
	}
	/**
	 * 
	* @Title: getCurUserId   
	* @Description:获取当前登陆他人ID
	* @return
	* @return String        
	* @author gaoguangchao
	* @date 2014年6月10日 上午11:42:18 
	*
	 */
	protected String getCurUserId()
	{
		//获取当前登陆人
		UserInfoManager userInfoManager = SessionInfo.getCurUser();
		
		return userInfoManager.getUserId();
	}
	/**
	 * 
	* @Title: isPermitted   
	* @Description:判断是否有权限
	* @param perms	String ...perms
	* @return boolean        
	* @author gaoguangchao
	* @date 2014年8月28日 下午2:06:29 
	*
	 */
	protected boolean isPermitted(String ...perms )
	{
		Subject currentUser = SecurityUtils.getSubject();
		boolean[] flag = currentUser.isPermitted(perms);
		for(boolean b :flag){
			if(!b){
				return false;
			}
		}
		return true;
	}
	/**
	 * 
	* @Title: addLog   
	* @Description:日志管理里添加操作结果
	* @param result
	* @param message
	* @return void        
	* @author gaoguangchao
	* @date 2014年9月5日 下午1:42:14 
	*
	 */
	protected void addLog(boolean result,Object message )
	{
		String m = "";
		if(Util.isNotNull(message)){
			m = String.valueOf(message);
		}
		if(result){
			getResponse().addHeader("flag", "success");
			getResponse().addHeader("message", m);
		}else{
			getResponse().addHeader("flag", "error");
			getResponse().addHeader("message", m);
		}
	}
	/**
	 * 
	* @Title: addLog   
	* @Description:日志管理里添加操作结果
	* @param result
	* @param message
	* @return void        
	* @author gaoguangchao
	* @date 2014年9月5日 下午1:55:23 
	*
	 */
	protected void addLog(String result,Object message )
	{
		String m = "";
		if(Util.isNotNull(message)){
			m = String.valueOf(message);
		}
		if("success".equalsIgnoreCase(result)||"true".equalsIgnoreCase(result)){
			getResponse().addHeader("flag", "success");
			getResponse().addHeader("message", m);
		}else if("error".equalsIgnoreCase(result)||"false".equalsIgnoreCase(result)){
			getResponse().addHeader("flag", "error");
			getResponse().addHeader("message", m);
		}
	}
	/**
	 * 
	* @Title: addLog   
	* @Description:日志管理里添加操作结果
	* @param result
	* @param message
	* @return void        
	* @author gaoguangchao
	* @date 2014年9月5日 下午1:43:13 
	*
	 */
	protected void addLog(boolean result,Exception message )
	{
		String m = "";
		if(Util.isNotNull(message)){
			m = message.getMessage();
		}
		if(result){
			getResponse().addHeader("flag", "success");
			getResponse().addHeader("message", m);
		}else{
			getResponse().addHeader("flag", "error");
			getResponse().addHeader("message", m);
		}
	}
	/**
	 * 
	* @Title: addLog   
	* @Description:日志管理里添加操作结果
	* @param result
	* @param message
	* @return void        
	* @author gaoguangchao
	* @date 2014年9月5日 下午1:56:02 
	*
	 */
	protected void addLog(String result,Exception message )
	{
		String m = "";
		if(Util.isNotNull(message)){
			m = message.getMessage();
		}
		if("success".equalsIgnoreCase(result)||"true".equalsIgnoreCase(result)){
			getResponse().addHeader("flag", "success");
			getResponse().addHeader("message", m);
		}else if("error".equalsIgnoreCase(result)||"false".equalsIgnoreCase(result)){
			getResponse().addHeader("flag", "error");
			getResponse().addHeader("message", m);
		}
	}
	/**
	 * 
	* @Title: addLog   
	* @Description:日志管理里添加操作结果
	* @param result
	* @return void        
	* @author gaoguangchao
	* @date 2014年9月5日 下午1:42:41 
	*
	 */
	protected void addLog(boolean result)
	{
		if(result){
			getResponse().addHeader("flag", "success");
		}else{
			getResponse().addHeader("flag", "error");
		}
	}
	/**
	 * 
	* @Title: addLog   
	* @Description:
	* @param result
	* @return void        
	* @author gaoguangchao
	* @date 2014年9月5日 下午1:56:33 
	*
	 */
	protected void addLog(String result)
	{
		if("success".equalsIgnoreCase(result)||"true".equalsIgnoreCase(result)){
			getResponse().addHeader("flag", "success");
		}else if("error".equalsIgnoreCase(result)||"false".equalsIgnoreCase(result)){
			getResponse().addHeader("flag", "error");
		}
	}
	/**
	 * @Title: getLocale   
	* @Description:获取当前语言
	* @return
	* @return Locale        
	* @author gaoguangchao
	* @date 2014年6月10日 上午11:42:18 
	 */
	public Locale getLocale()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(true);
		Locale locale = (Locale) session.getAttribute("WW_TRANS_I18N_LOCALE");
		if (locale == null) {
			locale = Locale.CHINA;
		}
		return locale;
	}
	/**
	 * @return the data
	 */
	public Data getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Data data) {
		this.data = data;
	}

}
