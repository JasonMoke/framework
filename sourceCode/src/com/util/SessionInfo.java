/**
* @Title: SessionInfo.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.framework.util   
* @Description:  session工具类
* @author guangchao    
* @date 2013-12-20 上午11:05:57   
* @version V1.0 
*/
package com.util;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import com.framework.entity.user.UserInfoManager;


public class SessionInfo
{

	private static final ThreadLocal<HashMap<String, Object>> threadLocal = new ThreadLocal<HashMap<String, Object>>();

	public SessionInfo()
	{
	}

	public static void setCurUser(UserInfoManager user)
	{
		if (user == null)
			removeCurThreadAttribute(Global.SESSION_USER);
		else
			setCurThreadAttribute(Global.SESSION_USER, user);
	}

	public static UserInfoManager getCurUser()
	{
		return (UserInfoManager)getCurThreadAttribute(Global.SESSION_USER);
	}

	public static UserInfoManager getUserInfo(HttpSession session)
	{
		if (session == null){
			
			return null;
		}else{
			return (UserInfoManager)session.getAttribute(Global.SESSION_USER);
		}
	}

	public static synchronized void setCurThreadAttribute(String attName, Object attValue)
	{
		HashMap<String, Object> atts = (HashMap<String, Object>)threadLocal.get();
		if (atts == null)
		{
			atts = new HashMap<String, Object>();
			threadLocal.set(atts);
		}
		atts.put(attName, attValue);
	}

	public static void removeCurThreadAllAttribute()
	{
		threadLocal.set(null);
	}

	public static Object getCurThreadAttribute(String attName)
	{
		HashMap<?, ?> atts = (HashMap<?, ?>)threadLocal.get();
		if (atts == null)
			return null;
		else
			return atts.get(attName);
	}

	public static Object removeCurThreadAttribute(String attName)
	{
		HashMap<?, ?> atts = (HashMap<?, ?>)threadLocal.get();
		if (atts == null)
			return null;
		else
			return atts.remove(attName);
	}

}
