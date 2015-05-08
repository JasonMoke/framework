
package com.core.taglib;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts2.views.jsp.TagUtils;

import com.opensymphony.xwork2.util.ValueStack;
import com.util.Util;


public class StatusTag extends TagSupport
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7175800023255371857L;
	
	private String id;
	private String name;
	private String field;
	private String property;
	private String url;
	private String disabled;
	private String dataValue = "";

	


	public StatusTag()
	{
	}

	public int doStartTag()
		throws JspException
	{
		if (getProperty() != null)
		{
			dataValue = (String) pageContext.getAttribute(getProperty());
			if (dataValue == null)
				dataValue = (String) pageContext.getRequest().getAttribute(getProperty());
		} else
		{
			dataValue =(String) pageContext.getAttribute("_DEAFULTDATA");
			if (dataValue == null)
				dataValue =(String) pageContext.getRequest().getAttribute("_DEAFULTDATA");
		}
		if (dataValue != null)
		{
			dataValue = (String) dataValue;
		} else
		{
			ValueStack stack = TagUtils.getStack(pageContext);  
	        if(stack!=null)  
	        {  
	             Object obj=stack.findValue(getField());
	             if(obj!=null)  
	             {  
	            	 dataValue = obj.toString();
	             }
	        }
        }
		
		StringBuffer buf = new StringBuffer();
		buf.append("<a class=\"btn btn-sm");
		if("0".equals(dataValue)){
			buf.append(" btn-danger \" ");
		}
		if("1".equals(dataValue)){
			buf.append(" btn-success \" ");
		}
		if("0".equals(dataValue)){
			buf.append("data-status=\"1\"");
		}
		if("1".equals(dataValue)){
			buf.append("data-status=\"0\"");
		}
		if(Util.isNotNull(getName())){
			buf.append("name=\""+getName()+"\"");
		}
		if(Util.isNotNull(getId())){
			buf.append("id=\""+getId()+"\"");
		}
		if (disabled != null)
			buf.append(" disabled=\"").append(disabled).append("\"");
		buf.append("onclick=\"_changeStatus(this);return false;\"");
		buf.append(">&nbsp;</a>");
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
    	ServletRequest request = pageContext.getRequest();
		String cp = request.getServletContext().getContextPath();
		//更改状态
		buf.append("<script type=\"text/javascript\">\n");
		buf.append("function _changeStatus(obj){\n");
		buf.append("var Id =  $(obj).next().val();\n");
		buf.append("var Status = $(obj).attr(\"data-status\");\n");
		buf.append("var Name;\n");
		buf.append("var css = \"\";\n");
		buf.append("var cssRemove = \"\";\n");
		buf.append("if(Status==1){\n");
		buf.append("css=\"btn btn-success\";\n");
		buf.append("cssRemove = \"btn-danger\";\n");
		buf.append("Name = \"0\";\n");
		buf.append("}else{\n");
		buf.append("css = \"btn btn-danger\";\n");
		buf.append("cssRemove=\"btn-success\";\n");
		buf.append("Name = \"1\";\n");
		buf.append("}\n");
		buf.append("$.ajax({ \n"); 
		buf.append("type: \"post\", \n"); 
		buf.append("async: true,\n");
		buf.append("url: \""+cp+"/"+getUrl()+"\", \n"); 
		buf.append("data: \"id=\" + Id +\"&status=\"+Status,  \n");
		buf.append("success: function(data){ \n");
		buf.append("if(data=='true'){\n");
		buf.append("art.dialog.tips(\"操作成功\");\n");
		buf.append("$(obj).attr(\"data-status\",Name);\n");
		buf.append("$(obj).removeClass(cssRemove);\n");
		buf.append("$(obj).addClass(css);\n");
		buf.append("}else{\n");
		buf.append("art.dialog.tips('操作失败');\n");
		buf.append("}\n");
		buf.append("}\n");
		buf.append("});\n");
		buf.append("}\n");
		buf.append("</script>\n");
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

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param field the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * @return the property
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * @param property the property to set
	 */
	public void setProperty(String property) {
		this.property = property;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the disabled
	 */
	public String getDisabled() {
		return disabled;
	}

	/**
	 * @param disabled the disabled to set
	 */
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	
}
