<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/WEB-INF/taglib/cw" prefix="CW"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title><s:text name="user_list_page_title" /></title>
    
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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
tr{
height:30px;
}
</style>
<script type="text/javascript">
//返回
  	function __back(){
  		var code = $("#dictListCode").val();
		document.srcForm.action="<%=path%>/dict/getDictManager.action?code="+code;
		document.srcForm.submit();
		}
</script>
  </head>
  <body>
  <form action="" name="srcForm" method="post">
  <h2 class="page-header"><s:text name="detail_dict" /></h2>
	<div class="form-actions text-right">  
		<input class="btn btn-default  " name="_back" type="button" value="<s:text name="operation_back" />" onclick="__back()">
	</div>	
	<div class="controls">  
		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="dict_manager_th_DictListCode" />：
			    </label>
			    <div class="col-sm-8">
				  <input class="form-control" name="Dictmanager.dictListCode" id="dictListCode" type="text" disabled value="<s:property value="Dictmanager.dictListCode" />">
			    </div>
		</div>	
		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="dict_manager_th_DictName" />：
			    </label>
			    <div class="col-sm-8">
				  <input class="form-control" name="Dictmanager.dictName" id="dictName" type="text" disabled value="<s:property value="Dictmanager.dictName" />">
			    </div>
		</div>

		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="dict_manager_th_ParentDictId" />：
			    </label>
			    <div class="col-sm-8">
				  <input class="form-control" name="Dictmanager.parentDictId" id="parentDictId" type="text" disabled value="<s:property value="Dictmanager.parentDictId" />">
			    </div>
		</div>	
		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="dict_manager_th_DictData1" />：
			    </label>
			    <div class="col-sm-8">
				  <input class="form-control" name="Dictmanager.dictData1" id="dictData1" type="text" disabled value="<s:property value="Dictmanager.dictData1" />">
			    </div>
		</div>
		
		<div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="dict_manager_th_DictData2" />：
			    </label>
			    <div class="col-sm-8">
				  <input class="form-control" name="Dictmanager.dictData2" id="dictData2" type="text" disabled value="<s:property value="Dictmanager.dictData2" />">
			    </div>
		</div>	
		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="dict_manager_th_DictData3" />：
			    </label>
			    <div class="col-sm-8">
				  <input class="form-control" name="Dictmanager.dictData3" id="dictData3" type="text" disabled value="<s:property value="Dictmanager.dictData3" />">
			    </div>
		</div>
		
		<div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="dict_manager_th_DictData4" />：
			    </label>
			    <div class="col-sm-8">
				  <input class="form-control" name="Dictmanager.dictData4" id="dictData4" type="text" disabled value="<s:property value="Dictmanager.dictData4" />">
			    </div>
		</div>	
		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="dict_manager_th_DictData5" />：
			    </label>
			    <div class="col-sm-8">
				  <input class="form-control" name="Dictmanager.dictData5" id="dictData5" type="text" disabled value="<s:property value="Dictmanager.dictData5" />">
			    </div>
		</div>	
		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="dict_manager_th_DictStatus" />：
			    </label>
			    <div class="col-sm-8">
			     <CW:switch name="Dictmanager.Status" id="Status" type="checkbox" checked="true" disabled="true"/>
			    </div>   
		</div>	
		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="dict_manager_th_DictNumber" />：
			    </label>
			    <div class="col-sm-8">
				  <input class="form-control" name="Dictmanager.dictNumber" id="dictNumber" type="text" disabled value="<s:property value="Dictmanager.dictNumber" />">
			    </div>
		</div>	
		 <div class="form-group col-sm-12">
			    <label class="col-sm-2 control-label text-right">
				  <s:text name="dict_manager_th_DictRemark" />：
			    </label>
			    <div class="col-sm-10">
					<textarea class="form-control" cols="20" name="Dictmanager.dictRemark" rows="4" disabled><s:property value="Dictmanager.dictRemark" /></textarea>
				</div>
		</div>
	</div>	
    </form>
  </body>
</html>
