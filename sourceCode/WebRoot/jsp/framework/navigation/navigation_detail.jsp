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
    
    <title>navigation_detail</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/jquery-ui.css"> 
	<!--系统css  -->
	<link href="<%=basePath%>css/en/edit.css" rel="stylesheet" type="text/css">
	<!--通用js  -->
	<script type="text/javascript" src="<%=basePath%>js/commons/common.js"></script>
	<!--artDialog-->
	<script type="text/javascript" src="<%=basePath%>artDialog/artDialog.source.js?skin=twitter"></script>

<style type="text/css">
tr{
height:30px;
}
</style>
<script type="text/javascript">
	function __back(){
	 //设置请求的url 
		var _url = "<%=path%>/navigation/findList.action";
		location.href=_url;
	}
	
    </script>
</head>
  
<body>
<form action="">
    <h2 class="page-header"><s:text name="查看" /></h2>
	<div class="form-actions text-right">  
		<input class="btn btn-default  " name="_back" type="button" value="<s:text name="operation_back" />" onclick="__back()">
	</div>
<div class="controls">  
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="navigation_list_th_systemcode" />：
	    </label>
	    <div class="col-sm-8">
		<CW:select name="navigation.SystemCode" id="SystemCode" codeName="listSystemparam" className="form-control" isCode="false" field="navigation.SystemCode" disabled="true"> 
		</CW:select>
	    </div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="navigation_list_th_navname" />：
	    </label>
	    <div class="col-sm-8">
	    <input class="form-control" name="navigation.NavName" type="text" id="NavName" disabled value="<s:property value="navigation.NavName" />">
	    </div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="navigation_list_th_navurl" />：
	    </label>
	    <div class="col-sm-8">
	    <input class="form-control" name="navigation.NavUrl" type="text" id="NavUrl" disabled value="<s:property value="navigation.NavUrl" />">
	    </div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="navigation_list_th_urlprefix" />：
	    </label>
	    <div class="col-sm-8">
	    <input class="form-control" name="navigation.UrlPrefix" type="text" id="UrlPrefix" disabled value="<s:property value="navigation.UrlPrefix" />">
	    </div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="navigation_list_th_seqnum" />：
	    </label>
	    <div class="col-sm-8">
	    <input class="form-control" name="navigation.SeqNum" type="text" id="SeqNum" disabled value="<s:property value="navigation.SeqNum" />">
	    </div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="navigation_list_th_status" />：
	    </label>
	    <div class="col-sm-8">
	    <CW:switch name="navigation.Status" id="Status" type="checkbox"  disabled="true"/>
	    </div>
</div>
 <div class="form-group col-sm-12">
	    <label class="col-sm-2 control-label text-right">
		  <s:text name="navigation_list_th_helpfilepath" />：
	    </label>
	    <div class="col-sm-10">
	    <input class="form-control" name="navigation.HelpFilePath" type="text" id="HelpFilePath" disabled value="<s:property value="navigation.HelpFilePath" />">
	    </div>
</div>
</div>
</form>
</body>
</html>
