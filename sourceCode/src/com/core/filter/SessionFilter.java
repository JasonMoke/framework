package com.core.filter;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.framework.entity.user.UserInfoManager;
import com.util.Global;
import com.util.SessionInfo;
import com.util.Util;


public class SessionFilter implements Filter {
	 /** 需要排除（不拦截）的URL的正则表达式 */
    private String excepUrl;
    /** 需要排除（不拦截）的URL的正则表达式 */
    private String excepUrl2;
    /** 需要排除（不拦截）的URL的正则表达式 */
    private String excepUrl3;
    
    /** 检查不通过时，转发的URL */
    private String forwardUrl;
    
    /** 要检查的 session 的名称 */
    private String sessionKey;
    
    
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
			HttpServletRequest httpReq=(HttpServletRequest)request;  
	        HttpServletResponse httpRes=(HttpServletResponse)response;  
	        HttpSession httpSession=httpReq.getSession(); 
//        	UserInfoManager user = (UserInfoManager) s.getAttribute(Global.SESSION_USER);
        	UserInfoManager user = (UserInfoManager) httpSession.getAttribute(Global.SESSION_USER);
        	if(user==null){
        		System.out.println();
        	}
        	SessionInfo.setCurUser(user);
	        String servletPath = ((HttpServletRequest) request).getServletPath();
	        String contextPath = ((HttpServletRequest) request).getContextPath();
//	                        获取当前浏览器语言
			Locale localeReq = (Locale) httpSession.getAttribute("WW_TRANS_I18N_LOCALE");
			if(Util.isNull(localeReq)){
				httpSession.setAttribute("WW_TRANS_I18N_LOCALE", request.getLocale());
			}
	        
	        // 如果 sessionKey 为空，则直接放行
	        if (StringUtils.isBlank(sessionKey)) {
	            chain.doFilter(request, response);
	            return;
	        }
	     // 如果请求的路径与forwardUrl相同，或请求的路径是排除的URL时，则直接放行
	        if (servletPath.equals(forwardUrl) || servletPath.equals(excepUrl)|| servletPath.equals(excepUrl2)||servletPath.contains(excepUrl3)) {
	            chain.doFilter(request, response);
	            return;
	        }
	        if (servletPath.contains("html")) {
	        	chain.doFilter(request, response);
	        	return;
	        }
	        if (servletPath.contains("userAjax")) {
	        	chain.doFilter(request, response);
	        	return;
	        }
	        if(httpSession.getAttribute(Global.SESSION_USER)==null){  
	            httpRes.sendRedirect(contextPath + StringUtils.defaultIfEmpty(forwardUrl, "/"));
	        }else{  
	            chain.doFilter(request, response);  
	        }  
	}

	public void init(FilterConfig cfg) throws ServletException {
			excepUrl = cfg.getInitParameter("excepUrl");
			excepUrl2 = cfg.getInitParameter("excepUrl2");
			excepUrl3 = cfg.getInitParameter("excepUrl3");
			forwardUrl = cfg.getInitParameter("forwardUrl");
     		sessionKey = cfg.getInitParameter("sessionKey");
	}

}
