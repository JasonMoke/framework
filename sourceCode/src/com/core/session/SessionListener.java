package com.core.session;


import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


public class SessionListener implements HttpSessionListener {
	private SessionContext myc = SessionContext.getInstance();
	
	public void sessionCreated(HttpSessionEvent event) {
		myc.AddSession(event.getSession());  
	}
	
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		myc.DelSession(session);  
	}
}
