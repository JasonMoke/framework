<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--引用CW标签  -->
<%@ taglib prefix="CW" uri="/WEB-INF/taglib/cw" %>
<!--引用struts标签  -->
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    
    <title><s:text name="systemparam_list_page_title" /></title>
    
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=basePath%>js/commons/common.js"></script>
	<!--artDialog-->
	<script type="text/javascript" src="<%=basePath%>artDialog/artDialog.source.js?skin=twitter"></script>
    <script type="text/javascript">
	function __back(){
	 //设置请求的url 
		var _url = "<%=path%>/systemparam/findList.action";
		location.href=_url;
	}
	
    </script>
</head>
  
<body>
<form action="">
<h2 class="page-header"><s:text name="detail_systemparam" /></h2>
 
  <div class="form-actions text-right">  
	<input class="btn btn-default  " name="_back" type="button" value="<s:text name="operation_back" />" onclick="__back()">
</div>
<div class="controls">  
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="systemparam_add_paramname" />：
	    </label>
	    <div class="col-sm-8">
		  <input class="form-control" name="systemparam.ParamName" type="text" id="ParamName" disabled value="<s:property value="systemparam.ParamName"/>">
	    </div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="systemparam_add_paramvalue" />：
	    </label>
	    <div class="col-sm-8">
	    <input class="form-control" name="systemparam.ParamValue" type="text" id="ParamValue" disabled value="<s:property value="systemparam.ParamValue"/>">
	    </div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="systemparam_add_paramnumber" />：
	    </label>
	    <div class="col-sm-8">
	    <input class="form-control" name="systemparam.ParamNumber" type="text" id="ParamNumber" disabled value="<s:property value="systemparam.ParamNumber"/>">
	    </div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="systemparam_add_systemcode" />：
	    </label>
	    <div class="col-sm-8">
	    <input class="form-control" name="systemparam.SystemCode" type="text" id="SystemCode" disabled value="<s:property value="systemparam.SystemCode"/>">
	    </div>
</div>
 <div class="form-group col-sm-12">
	    <label class="col-sm-2 control-label text-right">
		  <s:text name="systemparam_add_status" />：
	    </label>
	    <div class="col-sm-10">
	    <CW:switch name="systemparam.Status" id="Status" type="checkbox" checked="true" disabled="true"/>
	    </div>
</div>
 <div class="form-group col-sm-12">
	    <label class="col-sm-2 control-label text-right">
		  <s:text name="systemparam_add_paramremark" />：
	    </label>
	    <div class="col-sm-10">
	    <textarea class="form-control" cols="20" name="systemparam.ParamRemark" rows="4" disabled><s:property value="systemparam.ParamRemark"/></textarea>
	    </div>
</div>
</div>
</form>
</body>
</html>
