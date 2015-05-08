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
import javax.servlet.http.HttpSession;


import com.util.Util;

/**
 * 
* @ClassName: LocaleFilter
* @Description: 获取当前浏览器的语言，保存到session中
* @author guangchao
* @date 2014-2-14 下午2:25:53
*
 */
public class LocaleFilter implements Filter {
    private String locale;
    
    
	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
			HttpServletRequest httpReq=(HttpServletRequest)request;  
	        HttpSession httpSession=httpReq.getSession();  
//	                        获取当前浏览器语言
			Locale localeReq = (Locale) httpSession.getAttribute("WW_TRANS_I18N_LOCALE");
			if(Util.isNull(localeReq)){
				httpSession.setAttribute("WW_TRANS_I18N_LOCALE", request.getLocale());
			}
			 chain.doFilter(request, response);  
	}

	public void init(FilterConfig cfg) throws ServletException {
			locale = cfg.getInitParameter("locale");
	}

}
