
package com.util;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

public class ServletTools
{


	public ServletTools()
	{
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static final Map getRequestObject(HttpServletRequest request)
	{
		Map therequestdata = new HashMap();
		String name;
		for (Enumeration names = request.getParameterNames(); names.hasMoreElements(); therequestdata.put(name, getParameter(name, request)))
			name = names.nextElement().toString();

		return therequestdata;
	}

	public static final int getNowPage(HttpServletRequest request)
	{
		return getParameterInt("page", request, "0");
	}

	public static final int getPageSize(HttpServletRequest request, int defaultValue)
	{
		return getParameterInt("pageSize", request, defaultValue);
	}


	public static String getParameter(String name, HttpServletRequest request, String defaultValue)
	{
		String v = getParameter(name, request);
		if (v == null)
			v = defaultValue;
		return v;
	}

	public static int getParameterInt(String name, HttpServletRequest request, String defaultValue)
	{
		String v = getParameter(name, request, defaultValue);
		if ("".equals(v) || v == null)
			v = defaultValue;
		int r = 0;
		try
		{
			r = Integer.parseInt(v);
		}
		catch (NumberFormatException e)
		{
		}
		return r;
	}

	public static int getParameterInt(String name, HttpServletRequest request, int defaultValue)
	{
		return getParameterInt(name, request, Integer.toString(defaultValue));
	}

	public static String[] getParameterValues(String name, HttpServletRequest request)
	{
			return request.getParameterValues(name);
	}

	public static String getParameter(String name, HttpServletRequest request)
	{
			return request.getParameter(name);
	}

	public static Object getAttribute(String name, HttpServletRequest request)
	{
		return request.getAttribute(name);
	}


}
