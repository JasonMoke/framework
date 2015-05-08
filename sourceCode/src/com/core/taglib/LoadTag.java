
package com.core.taglib;

import java.io.IOException;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.TagSupport;


import com.orm.Page;
import com.util.Util;

/**
 * 
* @ClassName: PageTag
* @Description: 分页自定义标签
* @author gaoguangchao
* @date 2014年5月20日 上午10:02:17
*
 */
public class LoadTag extends TagSupport
{
	public int doStartTag()
		throws JspException
	{
		StringBuffer buf = new StringBuffer();
		buf.append("<style type=\"text/css\">.loadingdiv{position: absolute; left: 0; top: 0; background: #F1F1F1;display:none;filter: alpha(opacity=35);opacity: 0.5;font-weight: bold;     color: Red;width: 100%;height: 4000px;z-index: 3333;font-size: 14px;}.loadingdiv .child{position: absolute; visibility:visible;z-index:3332;width:100%;text-align:center;margin-top:250px;</style>");
		buf.append("<div id=\"loadingdiv\" class=\"loadingdiv\">\n");
		buf.append("<center>\n");
		buf.append("<div class=\"child\">\n");
		buf.append("<img src=\"./images/loading.gif\" style=\"width:160px\" />\n");
		buf.append("</div>\n");
		buf.append("</center>\n");
		buf.append("</div>\n");
		try
		{
			pageContext.getOut().print(buf.toString());
		}
		catch (IOException e)
		{
			throw new JspTagException((new StringBuilder("Select Tag ERROR:")).append(e.getMessage()).toString());
		}
		return 1;
	}

	public int doEndTag()
		throws JspException
	{
		StringBuffer buf = new StringBuffer();

		try
		{
			pageContext.getOut().print(buf.toString());
		}
		catch (IOException e)
		{
			throw new JspTagException((new StringBuilder("Select Tag ERROR:")).append(e.getMessage()).toString());
		}
		return 6;
	}

}
