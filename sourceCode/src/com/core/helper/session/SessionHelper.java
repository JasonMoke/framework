/**
* @Title: OrganHelper.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.core.helper.organ   
* @Description: 
* @author gaoguangchao    
* @date 2014年4月4日 上午11:32:47   
* @version V1.0 
*/

package com.core.helper.session;

import javax.servlet.http.HttpSession;

import com.util.Global;
import com.util.SessionInfo;

/**
 * 
* @ClassName: SessionHelper
* @Description: 
* @author gaoguangchao
* @date 2014年9月9日 下午2:02:18
*
 */

public class SessionHelper {
	
	public static void logout(HttpSession session){
		if(session!=null){
			session.removeAttribute(Global.SESSION_USER); 
			session.removeAttribute("sessionKey"); 
			session.removeAttribute("UserId"); 
			session.removeAttribute("UserFullName"); 
			session.removeAttribute("sessionKey"); 
			session.removeAttribute("systemFullName");
			session.setMaxInactiveInterval(-1);
			SessionInfo.setCurUser(null);
			session.setAttribute(Global.SESSION_FORCE_LOGOUT_KEY, true);
		}
	}

}
