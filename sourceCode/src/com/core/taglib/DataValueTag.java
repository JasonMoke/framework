/**
* @Title: DataValueTag.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.framework.taglib   
* @Description:    
* @author guangchao    
* @date 2014-1-13 上午11:41:04   
* @version V1.0 
*/
package com.core.taglib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.views.jsp.TagUtils;

import com.framework.entity.organ.Organ;
import com.framework.entity.dict.Dictmanager;
import com.framework.entity.user.UserInfoManager;
import com.opensymphony.xwork2.util.ValueStack;
import com.util.Util;

/**
 * @ClassName: DataValueTag
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author guangchao
 * @date 2014-1-13 上午11:41:04
 *
 */
public class DataValueTag extends TagSupport{
	private static final long serialVersionUID = 0x28fb0bdeeab05ff3L;
	private String field;
	private String value;
	private String title;
	private String defaultValue;
	private String codeName;
	private String className;
	private String property;
	private String person;
	private String organ;
	private String name;
	private String isValue;
	private String isCode;
	
	
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpSession session = request.getSession(true);
	Locale locale = (Locale) session.getAttribute("WW_TRANS_I18N_LOCALE");
	
	public DataValueTag(){
		
	}
	protected void doStart()
			throws JspException
		{
		}

		public int doEndTag()
			throws JspException
		{
			StringBuffer buf = new StringBuffer();
			String name = "";
			String title = "";
			String rem = "";
			String dValue = getDefaultValue();
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
			if(dataValue==null){
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
			if (dataValue != null&&!"".equals(dataValue))
			{
				if (codeName != null&&!"".equals(codeName))
				{
					Dictmanager code = new Dictmanager();
//					从字典集中查询
					if("true".equals(getIsCode())){
						try {
							code = TagTools.getCodeValueFromCache(dataValue,codeName);
//							code = TagTools.getCodeValue(dataValue,codeName);
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (code == null)
						{
							if(locale==null||locale == Locale.CHINA){
								name = (new StringBuilder("页面中没有存放名称为“")).append(codeName).append("”的字典集").toString();
							}else{
								name = (new StringBuilder("Page not stored with the name '")).append(codeName).append("' set of dictionaries").toString();
							}
							title = name;
						}
						else
						{
							 name = code.getDictName();
							 value = code.getDictData1();
							 rem = code.getDictRemark();
							if (name == null)
							{
								if(locale==null||locale == Locale.CHINA){
									name = (new StringBuilder("字典集中没有值为“")).append(dataValue).append("”的字典值").toString();
								}else{
									name = (new StringBuilder("Dictionary concentration has no value for '")).append(codeName).append("' in the dictionary values").toString();
								}
							}
							title = name;
						}
//						从后台传过来的值查询
					}else{
						List<?> codeList = new ArrayList();
						codeList =  (List<?>) pageContext.getAttribute(getCodeName());
						if (codeList == null)
							codeList = (List<?>) pageContext.getRequest().getAttribute(getCodeName());
						if(codeList!=null){
//						判断是否为多个值
							if(dataValue.contains(",")){
								String[] dataValueArray = dataValue.split(",");
								StringBuffer CodeNameSb  = new StringBuffer();
								for(int j =0;j<dataValueArray.length;j++){
									String arrayDataValue = dataValueArray[j];
									for (int i = 0; i < codeList.size(); i++)
									{
										Dictmanager code0 = (Dictmanager)codeList.get(i);
										String name0 = code0.getDictName();
										String value0 = code0.getDictData1();
										if(Util.isNotNull(value0)){
											if (value0.equals(arrayDataValue)){
												CodeNameSb.append(name0).append(",");
												break;
											}
										}
									}
								}
								name = Util.subStringByCondition(CodeNameSb.toString(), ",");
							}else{
								
								for (int i = 0; i < codeList.size(); i++)
								{
									Dictmanager code0 = (Dictmanager)codeList.get(i);
									String name0 = code0.getDictName();
									String value0 = code0.getDictData1();
									if (value0.equals(dataValue)){
										name = name0;
										break;
									}
								}
								
							}
						}
					}
				} else
				if ("true".equalsIgnoreCase(getPerson()))
				{
					UserInfoManager userInfo = new UserInfoManager();
					try {
//						userInfo = TagTools.getUserInfo(dataValue);
						userInfo = TagTools.getUserInfoFromCache(dataValue);
					} catch (Exception e) {
						e.printStackTrace();
					}
					name = userInfo.getUserName();
					if(name==null){
						if(locale==null||locale == Locale.CHINA){
							name = (new StringBuilder("没有id为“")).append(dataValue).append("”的人员信息").toString();
						}else{
							name = (new StringBuilder("No ID is '")).append(codeName).append("' personnel information").toString();
						}
					}
					title = name;
				}else
					if ("true".equalsIgnoreCase(getOrgan()))
					{
						Organ organ = new Organ();
							try {
//								userInfo = TagTools.getUserInfo(dataValue);
								organ = TagTools.getOrganFromCache(dataValue);
							} catch (Exception e) {
								e.printStackTrace();
							}
							name = organ.getCname();
							if(name==null){
								if(locale==null||locale == Locale.CHINA){
									name = (new StringBuilder("没有id为“")).append(dataValue).append("”的组织信息").toString();
								}else{
									name = (new StringBuilder("No ID is '")).append(codeName).append("' organ information").toString();
								}
							}
							title = name;
					}
			}else
			{
				name = dValue;
				title = name;
			}
			if(isValue=="true"){
				buf.append(Util.getString(name));
			}else{
				if("".equals(getTitle())){
					buf.append("<span  class=\"").append(getClassName()).append("\"").append(">");
					buf.append(Util.getString(name));
					buf.append("</span >");
				}else if("isvalue".equals(getTitle())){
					buf.append("<span ").append(" title=\"").append(Util.getString(dValue)).append("\"").append("  class=\"").append(getClassName()).append("\"").append(">");
					buf.append(Util.getString(name));
					buf.append("</span >");
				}else{
					buf.append("<span ").append(" title=\"").append(Util.getString(title)).append("\"").append("  class=\"").append(getClassName()).append("\"").append(">");
					buf.append(Util.getString(name));
					buf.append("</span >");
					
				}
				
			}
				
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
	 * @return the defaultValue
	 */
	public String getDefaultValue() {
		return defaultValue;
	}
	/**
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	/**
	 * @return the codeName
	 */
	public String getCodeName() {
		return codeName;
	}
	/**
	 * @param codeName the codeName to set
	 */
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}
	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
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
	 * @return the person
	 */
	public String getPerson() {
		return person;
	}


	/**
	 * @param person the person to set
	 */
	public void setPerson(String person) {
		this.person = person;
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
	 * @return the isValue
	 */
	public String getIsValue() {
		return isValue;
	}
	/**
	 * @param isValue the isValue to set
	 */
	public void setIsValue(String isValue) {
		this.isValue = isValue;
	}
	/**
	 * @return the isCode
	 */
	public String getIsCode() {
		if(isCode==null||"null".equals(isCode)){
			return "true";
		}
		return isCode;
	}
	/**
	 * @param isCode the isCode to set
	 */
	public void setIsCode(String isCode) {
		this.isCode = isCode;
	}
	public String getOrgan() {
		return organ;
	}
	public void setOrgan(String organ) {
		this.organ = organ;
	}

}
