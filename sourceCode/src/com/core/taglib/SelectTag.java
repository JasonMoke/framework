
package com.core.taglib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts2.views.jsp.TagUtils;

import com.framework.entity.dict.Dictmanager;
import com.opensymphony.xwork2.util.ValueStack;


public class SelectTag extends TagSupport
{
	
	private static final long serialVersionUID = 0x28fb0bdeeab05ff3L;
	private String field;
	private String name;
	private String id;
	private String defaultValue;
	private String className;
	private String codeName;
	private String onchange;
	private String value;
	private String width;
	private int size;
	private String title;
	private boolean notData;
	private boolean isCode;
	private String disabled;
	private String property;


	


	public SelectTag()
	{
		size = 0;
		notData = false;
	}

	public int doStartTag()
		throws JspException
	{
		String dataValue = "";
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
		setValue(dataValue);
		if ("".equals(value) && defaultValue != null)
			setValue(defaultValue);
		
		
		StringBuffer buf = new StringBuffer();
		String selectName = (new StringBuilder()).append(getName()).toString();
		StringBuffer ext = new StringBuffer();
		if (getOnchange() != null)
			ext.append(" onchange=\"").append(getOnchange()).append("\"");
		buf.append("<select name=\"");
		buf.append(selectName).append("\"");
		if (getSelectClassName() != null)
		buf.append(" class=\"").append(getSelectClassName()).append("\"");
		if (width != null)
			buf.append(" style=\"width:").append(width).append("\"");
		if (size != 0)
			buf.append(" size=\"").append(size).append("\"");
		else
			buf.append(" size=\"1\"");
		if (title != null)
		buf.append(" title=\"").append(title).append("\"");
		if (disabled != null)
			buf.append(" disabled=\"").append(disabled).append("\"");
		if (id != null)
			buf.append(" id=\"").append(id).append("\"");
		else
			buf.append(" id=\"").append(selectName).append("\"");
		buf.append(ext).append(">");
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
		List<?> codeList = new ArrayList();
		if(getIsCode()){
			try {
				//从缓存中获取
				codeList = TagTools.getCodeListFromCache(getCodeName());
//				codeList = TagTools.getCodeList(getCodeName());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else{
			codeList =  (List<?>) pageContext.getAttribute(getCodeName());
			if (codeList == null)
				codeList = (List<?>) pageContext.getRequest().getAttribute(getCodeName());
		}
		if (codeList == null)
			return 6;
		int index = codeList.size();
		String sValue = getValue();
		for (int i = 0; i < index; i++)
		{
			Dictmanager code = (Dictmanager)codeList.get(i);
			String name = code.getDictName();
			String DictData = code.getDictData1();
			String rem = code.getDictRemark();
			if (rem == null || "".equals(rem))
				rem = name;
			buf.append("<option value=\"").append(DictData).append("\"");
			if (DictData.equals(sValue))
				buf.append(" selected");
			buf.append(">");
			buf.append(name).append("</option>");
		}

		buf.append("</select>");
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	public String getDefaultValue()
	{
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue)
	{
		this.defaultValue = defaultValue;
	}

	public String getCodeName()
	{
		return codeName;
	}

	public void setCodeName(String codeName)
	{
		this.codeName = codeName;
	}


	public void setField(String field)
	{
		this.field = field;
	}

	public String getClassName()
	{
		if (className == null)
			className = "txt";
		return className;
	}

	public String getSelectClassName()
	{
		return className;
	}

	public void setClassName(String className)
	{
		this.className = className;
	}


	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		if (value != null)
			this.value = value.trim();
	}

	public String getWidth()
	{
		return width;
	}

	public void setWidth(String width)
	{
		this.width = width;
	}


	public int getSize()
	{
		return size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}

	public boolean isNotData()
	{
		return notData;
	}

	public void setNotData(boolean notData)
	{
		this.notData = notData;
	}


	public String getOnchange()
	{
		return onchange;
	}

	public void setOnchange(String onchange)
	{
		this.onchange = onchange;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getDisabled()
	{
		return disabled;
	}

	public void setDisabled(String disabled)
	{
		this.disabled = disabled;
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
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the isCode
	 */
	public boolean getIsCode() {
		return isCode;
	}

	/**
	 * @param isCode the isCode to set
	 */
	public void setIsCode(boolean isCode) {
		this.isCode = isCode;
	}
}
