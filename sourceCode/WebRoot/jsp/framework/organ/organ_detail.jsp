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
    
    <title><s:text name="organ_list_page_title" /></title>
    
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <!--通用js  -->
    <script type="text/javascript" src="<%=basePath%>js/commons/common.js"></script>
<script type="text/javascript">
	function __back(){
	 //设置请求的url 
		var _url = "<%=path%>/organ/findList.action";
		location.href=_url;
	}
    </script>
</head>
  
<body>
<form action="">
<h2 class="page-header"><s:text name="detail_organ" /></h2>
<div class="form-actions text-right">  
	<input class="btn" name="_back" type="button" value="<s:text name="operation_back" />" onclick="__back()">
</div>
 
 <div class="controls">  
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_cname" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" name="organ.Cname" type="text" id="Cname" disabled value="<s:property value="organ.Cname"/>">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_short_cname" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" name="organ.ShortCname" type="text" id="ShortCname" disabled value="<s:property value="organ.ShortCname"/>">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_enname" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" name="organ.Enname" type="text" id="Enname" disabled value="<s:property value="organ.Enname"/>">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_org_code" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" name="organ.OrgCode" type="text" id="OrgCode" disabled value="<s:property value="organ.OrgCode"/>">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_org_grade" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" name="organ.OrgGrade" type="text" id="OrgGrade" disabled value="<s:property value="organ.OrgGrade"/>">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_parent_id" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" name="organ.ParentId" type="text" id="ParentId" disabled value="<s:property value="organ.ParentId"/>">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_resp_person" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" name="organ.RespPerson" type="text" id="RespPerson" disabled value="<s:property value="organ.RespPerson"/>">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_link_man" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" name="organ.LinkMan" type="text" id="LinkMan" disabled value="<s:property value="organ.LinkMan"/>">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_org_phone" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" name="organ.OrgPhone" type="text" id="OrgPhone" disabled value="<s:property value="organ.OrgPhone"/>">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_org_email" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" name="organ.OrgEmail" type="text" id="OrgEmail" disabled value="<s:property value="organ.OrgEmail"/>">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_org_door_num" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" name="organ.OrgDoorNum" type="text" id="OrgDoorNum" disabled value="<s:property value="organ.OrgDoorNum"/>">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_seq_num" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" name="organ.SeqNum" type="text" id="SeqNum"  disabled value="<s:property value="organ.SeqNum"/>">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_status" />：
    </label>
    <div class="col-sm-8">
    <CW:switch name="organ.Status" id="Status" type="checkbox" checked="true" disabled="true"/>
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_is_reserved" />：
    </label>
    <div class="col-sm-8">
    <CW:switch name="organ.IsReserved" id="IsReserved" type="checkbox" checked="true" disabled="true"/>
    </div>
</div>
 <div class="form-group col-sm-12">
    <label class="col-sm-2 control-label text-right">
	  <s:text name="organ_add_memo" />：
    </label>
    <div class="col-sm-10">
    	<textarea class="form-control" cols="20" name="organ.Memo" rows="4" disabled="disabled"><s:property value="organ.Memo"/></textarea>
    </div>
</div>
</div>
</form>
</body>
</html>
