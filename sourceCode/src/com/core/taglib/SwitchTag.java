
package com.core.taglib;

import java.io.IOException;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.TagSupport;


import org.apache.struts2.views.jsp.TagUtils;

import com.opensymphony.xwork2.util.ValueStack;
import com.util.Util;

/**
 * 
* @ClassName: SwitchTag
* @Description: 
* @author gaoguangchao
* @date 2014年7月9日 下午1:55:23
*
 */

public class SwitchTag extends TagSupport
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2877636443471096840L;
	private String id;
	private String name;
	private String checked;
	private String size;
	private String animate;
	private String disabled;
	private String readonly;
	private String indeterminate;
	private String onColor;
	private String offColor;
	private String onText;
	private String offText;
	private String labelText;
	private String radioAllOff;
	private String type;
	private String onchange;
	private String onclick;
	private String value;
	private String property;


	public SwitchTag()
	{
	}
	public int doStartTag()
		throws JspException
	{
		String dataValue = "";
		String isChecked = "false";
		if("true".equalsIgnoreCase(getChecked())||"checked".equalsIgnoreCase(getChecked())){
			value = "1";
			isChecked = getChecked();
		}else if("false".equalsIgnoreCase(getChecked())){
			value = "0";
			isChecked = getChecked();
		}else{
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
		             Object obj=stack.findValue(getName());
		             if(obj!=null)  
		             {  
		            	 dataValue = obj.toString();
		             }
		        }
	        }
			if(Util.isNotNull(dataValue)&&!"null".equals(dataValue)){
				value = dataValue;
			}
			if("1".equals(value)||"true".equals(value)){
				isChecked="true";
			}else if("0".equals(value)||"false".equals(value)){
				isChecked="false";
			}
		}
		
		/*if(Util.isNull(getType())){
			type="checkbox";
		}
		if(Util.isNull(getAnimate())){
			animate="true";
		}
		if(Util.isNull(getDisabled())){
			disabled="false";
		}
		if(Util.isNull(getReadonly())){
			readonly="false";
		}
		if(Util.isNull(getIndeterminate())){
			indeterminate="false";
		}
		if(Util.isNull(getOnColor())){
			onColor="primary";
		}
		if(Util.isNull(getOffColor())){
			offColor="default";
		}
		if(Util.isNull(getOnText())){
			onText="ON";
		}
		if(Util.isNull(getOffText())){
			offText="OFF";
		}
		if(Util.isNull(getLabelText())){
			labelText="&nbsp;";
		}
		if(Util.isNull(getRadioAllOff())){
			radioAllOff="false";
		}
		
		if(Util.isNull(getSize())){
			size="normal";
		}*/
		
		
		StringBuffer buf = new StringBuffer();
		buf.append("<input type=\""+type+"\" name=\""+getName()+"\" id=\""+getId()+"\"");
		buf.append(" checked=\""+checked+"\"");
		if(Util.isNotNull(getAnimate())){
			buf.append(" data-animate=\""+animate+"\"");
		}
		if(Util.isNotNull(getDisabled())){
			buf.append(" disabled=\""+disabled+"\"");
		}
		if(Util.isNotNull(getReadonly())){
			buf.append(" readonly=\""+readonly+"\"");
		}
		if(Util.isNotNull(getOnColor())){
			buf.append(" data-on-color=\""+onColor+"\"");
		}
		if(Util.isNotNull(getOffColor())){
			buf.append(" data-off-color=\""+offColor+"\"");
		}
		if(Util.isNotNull(getOnText())){
			buf.append(" data-on-text=\""+onText+"\"");
		}
		if(Util.isNotNull(getOffText())){
			buf.append(" data-off-text=\""+offText+"\"");
		}
		if(Util.isNotNull(getLabelText())){
			buf.append(" data-label-text=\""+labelText+"\"");
		}
		if(Util.isNotNull(getSize())){
			buf.append(" data-size=\""+size+"\"");
		}
		if("radio".equals(type)){
			if(Util.isNull(getRadioAllOff())){
				radioAllOff="false";
			}
			buf.append(" data-radio-all-off=\""+radioAllOff+"\"");
		}
		
		buf.append(" value=\""+value+"\"");
		
		if(Util.isNotNull(getOnchange())){
			buf.append(" onchange=\""+getOnchange()+"\"");
		}
		if(Util.isNotNull(getOnclick())){
			buf.append(" onclick=\""+getOnclick()+"\"");
		}
		buf.append(">");
		
		
		buf.append("<script type=\"text/javascript\">\n");
		buf.append("$(document).ready(function() {\n");
		buf.append("$(\"#"+getId()+"\").bootstrapSwitch({");
		StringBuffer bufsb = new StringBuffer();
		if("true".equals(isChecked)){
			bufsb.append("'state': true,");
		}else if("false".equals(isChecked)){
			bufsb.append("'state': false,");
		}
		if(Util.isNotNull(getDisabled())&&("disabled".equalsIgnoreCase(getDisabled())||"true".equalsIgnoreCase(getDisabled()))){
			bufsb.append("'disabled':'disabled',");
		}
		if(Util.isNotNull(getReadonly())&&("readonly".equalsIgnoreCase(getReadonly())||"true".equalsIgnoreCase(getReadonly()))){
			bufsb.append("'readonly':'readonly',");
		}
		buf.append(Util.subLastIndex(bufsb.toString(), ","));
		buf.append(" });\n");
		buf.append("$(\"#"+getId()+"\").on('switchChange.bootstrapSwitch', function(event, state) {\n");
		buf.append("if(state){\n");
		buf.append("$(this).val(1);\n");
		buf.append("}else{\n");
		buf.append("$(this).val(0);\n");
		buf.append("}\n");
		buf.append("});\n");
		buf.append("\n");
		buf.append("}); \n");
		buf.append("</script>\n");
		
		
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
	 * @return the size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * @return the animate
	 */
	public String getAnimate() {
		return animate;
	}

	/**
	 * @param animate the animate to set
	 */
	public void setAnimate(String animate) {
		this.animate = animate;
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

	/**
	 * @return the readonly
	 */
	public String getReadonly() {
		return readonly;
	}

	/**
	 * @param readonly the readonly to set
	 */
	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}

	/**
	 * @return the indeterminate
	 */
	public String getIndeterminate() {
		return indeterminate;
	}

	/**
	 * @param indeterminate the indeterminate to set
	 */
	public void setIndeterminate(String indeterminate) {
		this.indeterminate = indeterminate;
	}

	/**
	 * @return the onColor
	 */
	public String getOnColor() {
		return onColor;
	}

	/**
	 * @param onColor the onColor to set
	 */
	public void setOnColor(String onColor) {
		this.onColor = onColor;
	}

	/**
	 * @return the offColor
	 */
	public String getOffColor() {
		return offColor;
	}

	/**
	 * @param offColor the offColor to set
	 */
	public void setOffColor(String offColor) {
		this.offColor = offColor;
	}

	/**
	 * @return the onText
	 */
	public String getOnText() {
		return onText;
	}

	/**
	 * @param onText the onText to set
	 */
	public void setOnText(String onText) {
		this.onText = onText;
	}

	/**
	 * @return the offText
	 */
	public String getOffText() {
		return offText;
	}

	/**
	 * @param offText the offText to set
	 */
	public void setOffText(String offText) {
		this.offText = offText;
	}

	/**
	 * @return the labelText
	 */
	public String getLabelText() {
		return labelText;
	}

	/**
	 * @param labelText the labelText to set
	 */
	public void setLabelText(String labelText) {
		this.labelText = labelText;
	}

	/**
	 * @return the radioAllOff
	 */
	public String getRadioAllOff() {
		return radioAllOff;
	}

	/**
	 * @param radioAllOff the radioAllOff to set
	 */
	public void setRadioAllOff(String radioAllOff) {
		this.radioAllOff = radioAllOff;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the onchange
	 */
	public String getOnchange() {
		return onchange;
	}

	/**
	 * @param onchange the onchange to set
	 */
	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}

	/**
	 * @return the onclick
	 */
	public String getOnclick() {
		return onclick;
	}

	/**
	 * @param onclick the onclick to set
	 */
	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
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
	 * @return the checked
	 */
	public String getChecked() {
		return checked;
	}

	/**
	 * @param checked the checked to set
	 */
	public void setChecked(String checked) {
		this.checked = checked;
	}



}
