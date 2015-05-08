
package com.core.taglib;

import java.io.IOException;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.TagSupport;


import com.util.Util;

/**
 * 
* @ClassName: CheckAllTag
* @Description: 全选标签
* @author gaoguangchao
* @date 2014年5月30日 下午4:03:59
*
 */
public class CheckAllTag extends TagSupport
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1890326798782493609L;
	private String name;
	private String id;
	private String className;
	private String childName;



	public int doStartTag()
		throws JspException
	{
		StringBuffer buf = new StringBuffer();
		//不支持多语言
			buf.append("<script type=\"text/javascript\">\n");
			buf.append("$(document).ready(function() {\n");
			buf.append("$('#"+getId()+"').click(function(){\n");
			buf.append("$('input[name=\""+getChildName()+"\"]').prop('checked', this.checked);\n");
			buf.append("});\n");
			buf.append("$('input[name=\""+getChildName()+"\"]').click(function() {\n");
			buf.append("var $subs = $('input[name=\""+getChildName()+"\"]');\n");
			buf.append("$('#"+getId()+"').prop('checked' , $subs.length == $subs.filter(':checked').length ? true :false);\n");
			buf.append("});\n");
			buf.append("});\n");
			buf.append("//获取被选中的checkbox的值\n");
			buf.append("function getCheckedValue(){\n");
			buf.append("var chk_list = document.getElementsByName('chk_list');\n");
			buf.append("var chkString = '';\n");
			buf.append("for(var i=0;i<chk_list.length;i++){\n");
			buf.append("if(chk_list[i].checked){\n");
			buf.append("chkString =chk_list[i].value+','+chkString; \n");
			buf.append("}\n");
			buf.append("}\n");
			buf.append("return chkString.substring(0,chkString.length-1);\n");
			buf.append("}\n");
			buf.append("</script>\n");
			
			buf.append("<input type='checkbox' name='"+getName()+"' id='"+getId()+"' ");  
			if(Util.isNotNull(className)){
				buf.append("class='"+getClassName()+"'");
			}
			buf.append("/>");
		try
		{
			pageContext.getOut().print(buf.toString());
		}
		catch (IOException e)
		{
			throw new JspTagException((new StringBuilder("CheckAllTag Tag ERROR:")).append(e.getMessage()).toString());
		}
		return 1;
	}

	public int doEndTag()
		throws JspException
	{
		StringBuffer buf = new StringBuffer();

		buf.append("\n");
		try
		{
			pageContext.getOut().print(buf.toString());
		}
		catch (IOException e)
		{
			throw new JspTagException((new StringBuilder("CheckAllTag Tag ERROR:")).append(e.getMessage()).toString());
		}
		return 6;
	}


	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		if(Util.isNull(name)){
			name ="checkAll";
		}
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		if(Util.isNull(id)){
			id ="checkAll";
		}
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the childName
	 */
	public String getChildName() {
		if(Util.isNull(childName)){
			childName ="chk_list";
		}
		return childName;
	}

	/**
	 * @param childName the childName to set
	 */
	public void setChildName(String childName) {
		this.childName = childName;
	}

	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}


}
