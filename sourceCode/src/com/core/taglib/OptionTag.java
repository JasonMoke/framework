
package com.core.taglib;

import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.TagSupport;


public class OptionTag extends TagSupport
{

	private static final long serialVersionUID = 0x81f5b1991eae70eaL;
	private String value;
	private String title;
	private boolean selected;

	public OptionTag()
	{
	}

	public int doStartTag()
		throws JspException
	{
		SelectTag parent = (SelectTag)getParent();
		if (parent == null)
			throw new JspException("not found SelectTag!");
		String d = parent.getValue();
		StringBuffer buf = new StringBuffer();
		String title = getTitle();
		if (title != null)
			title = (new StringBuilder(" title=\"")).append(title).append("\"").toString();
		else
			title = "";
		buf.append("<option value=\"").append(value).append("\"").append(title);
		if (value.equals(d))
			buf.append(" selected>");
		else
			buf.append(">");
		try
		{
			pageContext.getOut().print(buf.toString());
		}
		catch (IOException e)
		{
			throw new JspTagException((new StringBuilder("Data Tag ERROR:")).append(e.getMessage()).toString());
		}
		return 1;
	}

	public int doEndTag()
		throws JspException
	{
		try
		{
			pageContext.getOut().print("</option>");
		}
		catch (IOException e)
		{
			throw new JspTagException((new StringBuilder("Data Tag ERROR:")).append(e.getMessage()).toString());
		}
		return 6;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public boolean isSelected()
	{
		return selected;
	}

	public void setSelected(boolean selected)
	{
		this.selected = selected;
	}
}
