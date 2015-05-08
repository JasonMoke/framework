<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--引用CW标签  -->
<%@ taglib uri="/WEB-INF/taglib/cw" prefix="CW" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title><s:text name="pubmenu_detail_page_title" /></title>
    
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/jquery-ui.css"> 
	<link href="<%=basePath%>css/en/edit.css" rel="stylesheet" type="text/css">
	<link href="<%=basePath%>css/en/list.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=basePath%>js/commons/common.js"></script>
	<script type="text/javascript" src="<%=basePath%>artDialog/artDialog.source.js?skin=twitter"></script>
	<script type="text/javascript" src="<%=basePath%>artDialog/plugins/iframeTools.source.js"></script>
<script type="text/javascript">
$(document).ready(
		function() {
			_initType();
	});
function __back(){
	 //设置请求的url 
	 var NavId = $("#NavId").val();
		var _url = "<%=path%>/pubmenu/findAllList.action?NavId="+NavId;
		location.href=_url;
}

//初始化菜单类型
function _initType(){
	/* 1:模块菜单   2：资源菜单 3：外部菜单 */
	var types= $("#MenuType").val();
	if(types==1){
		$("#moduleUrl").show();
		$("#resourceUrl").hide();
	}else if(types==2){
		$("#moduleUrl").hide();
		$("#resourceUrl").show();
	}else if(types==3){
		$("#moduleUrl").hide();
		$("#resourceUrl").hide();
	}
}
</script>
  </head>
  <body>
 <form action="" name="addpubmenuForm" id="addpubmenuForm" method="post">
 <input type="hidden" id="NavId" name="pubmenu.NavId" value="<s:property value="NavId"/>">
  <h2 class="page-header"><s:text name="查看" /></h2>
<div class="form-actions text-right">  
	<input class="btn  " name="_submit" type="button" value="<s:text name="operation_back" />" onclick="__back()">
</div>
<div class="controls">  
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="菜单名称" />：
    </label>
    <div class="col-sm-8">
      <input class="form-control" name="pubmenu.MenuName" type="text" id="pubmenu.MenuName" disabled value="<s:property value="pubmenu.MenuName"/>">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	<s:text name="上级" />：
    </label>
    <div class="col-sm-8">
    <input class="form-control" name="pubmenu.PID" id="pubmenu.PID"  type="text" disabled value="<s:property value="pubmenu.PIDName"/>">
    </div>
</div>
<div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	<s:text name="菜单类型" />：
    </label>
    <div class="col-sm-8">
		<CW:dataValue className="form-control" property="pubmenu.MenuType" field="MenuType" codeName="MenuType" title=""></CW:dataValue>
		<input id="MenuType" type="hidden" value="<s:property value="pubmenu.MenuType"/>">
    </div>
</div>
<div id="moduleUrl" class="form-group col-sm-6" style="display: none">
    <label class="col-sm-4 control-label text-right">
	<s:text name="模块路径" />：
    </label>
    <div class="col-sm-8">
    <input class="form-control"  type="text"  disabled value="<s:property value="pubmenu.ModuleName"/>">
    </div>
</div>
<div id="resourceUrl" class="form-group col-sm-6" style="display: none">
    <label class="col-sm-4 control-label text-right">
	<s:text name="资源路径" />：
    </label>
    <div class="col-sm-8">
    <input class="form-control"  type="text" disabled value="<s:property value="pubmenu.ResourcesName"/>">
    </div>
</div>
<div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	<s:text name="菜单路径" />：
    </label>
    <div class="col-sm-8">
    <input class="form-control" name="pubmenu.MenuUrl" id="pubmenu.MenuUrl" type="text" disabled value="<s:property value="pubmenu.MenuUrl"/>">
    </div>
</div>
<div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	<s:text name="帮助文档路径" />：
    </label>
    <div class="col-sm-8">
    <input class="form-control" name="pubmenu.HelpFilePath" id="pubmenu.HelpFilePath" type="text" disabled value="<s:property value="pubmenu.SeqNum"/>">
    </div>
</div>
<div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	<s:text name="排序号" />
    </label>
    <div class="col-sm-8">
    <input class="form-control" name="pubmenu.SeqNum" id="pubmenu.SeqNum" type="text" disabled value="<s:property value="pubmenu.SeqNum"/>">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	<s:text name="状态" />：
    </label>
    <div class="col-sm-8">
    <CW:switch name="pubmenu.Status" id="Status" type="checkbox" checked="true" disabled="disabled"/>
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
    <s:text name="是否打开新页面" />：
    </label>
    <div class="col-sm-8">
    <CW:switch name="pubmenu.Target" id="Target" type="checkbox" checked="true" disabled="disabled"/>
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	<s:text name="是否默认入口" />：
    </label>
    <div class="col-sm-8">
    <CW:switch name="pubmenu.IsMenuEntr" id="IsMenuEntr" type="checkbox" checked="true" disabled="disabled"/>
    </div>
</div>
<div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	<s:text name="是否首选项" />：
    </label>
    <div class="col-sm-8">
    <CW:switch name="pubmenu.isPreferences" id="isPreferences" type="checkbox" checked="true"/>
    </div>
</div>
</div>
</form>
</body>
</html>
