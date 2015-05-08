
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
public class PageTag extends TagSupport
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6613149754167063695L;
	private String property;
	private String url;
	private String className;
	private String formName;
	private String target;
	private boolean Multilanguage;



	@SuppressWarnings("unchecked")
	public int doStartTag()
		throws JspException
	{
		Page<Object> page = new Page<Object>();
		StringBuffer buf = new StringBuffer();
		String total = "";
		String currentPage = "";
		int pageSize = 10;
		String totalPage = "";
		String ContextPath =pageContext.getServletContext().getContextPath();
		if (getProperty() != null)
		{
			page = (Page<Object>) pageContext.getAttribute(getProperty());
			if (page == null)
				page = (Page<Object>) pageContext.getRequest().getAttribute(getProperty());
		} else
		{
			page =(Page<Object>) pageContext.getAttribute("_DEAFULTDATA");
			if (page == null)
				page =(Page<Object>) pageContext.getRequest().getAttribute("_DEAFULTDATA");
		}
		if(Util.isNotNull(page)){
			total = String.valueOf(page.getTotal());
			currentPage = String.valueOf(page.getCurrentPage());
			totalPage = String.valueOf(page.getTotalPage());
			pageSize = page.getPageSize();
		}
		//不支持多语言
		if(!Multilanguage){
			buf.append("<style type=\"text/css\">");
			buf.append(".pagination{ width:100%; height:29px; padding-top:50px; text-align: center;}.pagination a { display:block; width:26px; height:29px; margin-left:5px;}.first, .previous, .next, .last{ background-image:url(./images/Page.gif);}.first, .previous, .next, .last, .textbox{ float:left; cursor:pointer;}.pagination_l{ height:29px; float:left; width: 50%;}.first{ background-position:0 0;}.first:hover{ background-position:0 29px; }.previous{ background-position:-26px 0;}.previous:hover{ background-position:-26px 29px; }.next{ background-position:-52px 0;}.next:hover{ background-position:-52px 29px; }.last{ background-position:-78px 0;}.last:hover{ background-position:-78px 29px; }.textbox{ height:22px; width:120px; color:#CCC; text-align:center; background-position:0 24px; margin-left:5px;}.pagination_r span{ font-weight: bold; font-size: 15px; color:#640F12;}.pagination_r{ float:left; font-size:15px; display:block; height:29px; line-height:29px; width:40%; text-align: right; margin-right: 10px;}");
			buf.append("</style>");
			buf.append("<script type=\"text/javascript\">\n");
			buf.append("$(document).ready(function() {\n");
			buf.append("$('.pagination').jqPagination({\n");
			buf.append("current_page: "+currentPage+",\n");
			buf.append("page_string	: '第{current_page}页，共{max_page}页',\n");
			buf.append("max_page	: "+totalPage+",\n");
			buf.append("paged		: function(pageNum) {\n");
			buf.append("$('.log').prepend('<li>Requested page ' + pageNum + '</li>');\n");
			buf.append("document."+getFormName()+".action=\""+ContextPath+"/"+getUrl()+"?currentPage=\"+pageNum;\n");
			if(Util.isNotNull(getTarget())){
				buf.append("document."+getFormName()+".target=\""+getTarget()+"\";\n");
			}
			buf.append("document."+getFormName()+".submit();\n");
			buf.append("}\n");
			buf.append("});\n ");
			buf.append("}); \n");
			buf.append("function _changePageSize(){\n");
			buf.append("document."+getFormName()+".action=\""+ContextPath+"/"+getUrl()+"?currentPage=1\";\n");
			buf.append("document."+getFormName()+".submit();\n");
			buf.append("}\n");
			buf.append("</script>\n");
			
			buf.append("<div id=\"pageTlg\" class=\"pagination\">");
			buf.append("<div class=\"pagination_r\">");
			buf.append('共');
			buf.append("<span>").append(total).append("</span>条");  
			buf.append("记录,每页");  
			buf.append("<select name=\"pageSize\" id=\"pageSize\" style=\"width:55px;height:25px;\" onchange=\"_changePageSize()\">");
			buf.append("<option value=\"10\" ");
			if(pageSize==10){
				buf.append(" selected ");
			}
			buf.append(">10</option>");
			buf.append("<option value=\"15\" ");
			if(pageSize==15){
				buf.append(" selected ");
			}
			buf.append(">15</option>");
			buf.append("<option value=\"20\" ");
			if(pageSize==20){
				buf.append(" selected ");
			}
			buf.append(">20</option>");
			buf.append("<option value=\"50\" ");
			if(pageSize==50){
				buf.append(" selected ");
			}
			buf.append(">50</option>");
			buf.append("</select>");
			buf.append("条");
			buf.append("</div>");
			buf.append("<div class=\"pagination_l\">");
			buf.append("<a href=\"#\" class=\"first\" data-action=\"first\" title=\"首页\"></a>"); 
			buf.append("<a href=\"#\" class=\"previous\" data-action=\"previous\" title=\"上一页\"></a>"); 
			buf.append("<input type=\"text\" class=\"textbox\" readonly=\"readonly\" style=\"height: 29px;\"/>"); 
			buf.append("<a href=\"#\" class=\"next\" data-action=\"next\" title=\"下一页\"></a>"); 
			buf.append("<a href=\"#\" class=\"last\" data-action=\"last\" title=\"末页\"></a>");
		}
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

		buf.append("</div>");
		buf.append("</div>");
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
	 * @return the className
	 */
	public String getClassName() {
		if(Util.isNull(className)){
			className ="default";
		}
		return className;
	}

	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return the multilanguage
	 */
	public boolean isMultilanguage() {
		return Multilanguage;
	}

	/**
	 * @param multilanguage the multilanguage to set
	 */
	public void setMultilanguage(boolean multilanguage) {
		Multilanguage = multilanguage;
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

	public String getFormName() {
		if(formName==null){
			return "srcForm";
		}
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

}
