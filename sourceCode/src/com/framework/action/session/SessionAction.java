/**
 * @Title: RoleGroupAction.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.action.rolegroup
 * @Description: 。
 * @author gaogc
 * @date 2014-08-29 13:55:02
 * @version V1.0
 */
package com.framework.action.session;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.util.Global;
import com.util.Util;
import com.core.base.BaseAction;
import com.core.session.SessionContext;
import com.framework.entity.user.UserInfoManager;
import com.framework.entity.session.SessionsManager;
/**
 * @ClassName: SessionAction
 * @Description: 会话管理控制。
 * @author lishanhe
 * @date 2014-08-29 13:55:02
 */
@Controller
public class SessionAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 232763179032132321L;
	List<SessionsManager> sessionlist = null;
	/**
	* @Title: findOnLineList   
    * @Description:获取所有会话列表
    * @param @return
    * @param @throws Exception    
    * @return String      
    * @author lishanhe
    * @date 2014-9-4
    * @throws
	 */
	public String findOnLineList(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HashMap<String, HttpSession> sessions = SessionContext.getInstance().getAllSession();
        sessionlist = new ArrayList<SessionsManager>();
        if(sessions!=null){
        	SessionsManager sessionManager;
        	for(HttpSession session:sessions.values()){
        		Object sessionKey = session.getAttribute("sessionKey");
        		if(Util.isNotNull(sessionKey)){
        			sessionManager = new SessionsManager();
        			Date CreateTime = new Date(session.getCreationTime());
            		Date LastAccessedTime = new Date(session.getLastAccessedTime());
            		Date TotalTime = new Date(session.getLastAccessedTime()-session.getCreationTime());
            		sessionManager.setSessionId((String)session.getId());
            		sessionManager.setCreateTime(CreateTime);
            		sessionManager.setLastAccessedTime(LastAccessedTime);
            		sessionManager.setTotalTime(TotalTime);
            		UserInfoManager user_r = (UserInfoManager) session.getAttribute(Global.SESSION_USER);
            		sessionManager.setUserName(user_r.getUserName());
            		sessionManager.setOrganId(user_r.getUserDataManager().getOrganId());
            		sessionManager.setIP(request.getRemoteAddr());
            		sessionlist.add(sessionManager);
        		}
        	}
        }
        return "success";
	}
	/**
	* @Title: forceLogout   
    * @Description:强制下线
    * @param @return
    * @param @throws Exception    
    * @author lishanhe
    * @date 2014-9-4
    * @throws
	 */
	
	public String forceLogout(){
		String sessionId = getParameter("sessionId");
		SessionContext myc= SessionContext.getInstance();  
		HttpSession session = myc.getSession(sessionId);  
		if(session!=null){
			Session sessionShiro = (Session) session.getAttribute("sessionShiro");  
			if(sessionShiro!=null){
				sessionShiro.setAttribute(Global.SESSION_FORCE_LOGOUT_KEY, true);
			}
			myc.DelSession(session);
		}
    	return "success";
	}
	
	
	
	public List<SessionsManager> getSessionlist() {
		return sessionlist;
	}
	public void setSessionlist(List<SessionsManager> sessionlist) {
		this.sessionlist = sessionlist;
	}

	
}
