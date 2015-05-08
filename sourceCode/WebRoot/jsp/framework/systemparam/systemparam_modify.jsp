<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/cw" prefix="CW"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

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
	<script type="text/javascript" src="<%=basePath%>js/commons/common.js"></script>
	<!--artDialog-->
	<script type="text/javascript" src="<%=basePath%>artDialog/artDialog.source.js?skin=twitter"></script>
	
  <script type="text/javascript">
    
    /*
    * 绑定验证
    * 注意1：所有的验证规则统一在此处进行定义
    * 注意2：当input中name使用pentity.name这样的命名方式时,其中"."与JQUERY冲突, 则rules: {"pentity.name": {}} 要加上双引号 
    */
    $(document).ready(
    	function() {
            $('#updateForm').validate({
                rules : {
                    "systemparam.ParamName" : {required : true, rangelength : [1,64], maxlength : 64},
                    "systemparam.ParamValue" : {required : true, rangelength : [1,128], maxlength : 128},
                    "systemparam.ParamRemark" : {required : false, rangelength : [1,256], maxlength : 256},
                    "systemparam.ParamNumber" : {required : false, number : true, maxlength : 8},
                    "systemparam.SystemCode" : {required : true, rangelength : [1,64], maxlength : 64},
            }
    	});
    });
        
    
   	function __back(){
	 //设置请求的url 
		var _url = "<%=path%>/systemparam/findList.action";
		location.href=_url;
	}
function __submit(){
  	if($("#updateForm").valid()){
		$("#loadingdiv").show();
		document.updateForm.action="<%=basePath%>systemparam/update.action";
		document.updateForm.submit();
	} 
}
    </script>
</head>

<body>
	<form action="systemparam/update.action" name="updateForm" id="updateForm" method="post">
	<input type="hidden" name="systemparam.ParamId" id="systemparam.ParamId" value="<s:property value="systemparam.ParamId" />"/>
		<h2 class="page-header"><s:text name="modify_tosystemparam" /></h2>
		
	<div class="form-actions text-right">  
		<input class="btn btn-primary  " name="_submit" type="button" value="<s:text name="operation_submit" />" onclick="__submit()">
		<input class="btn btn-default  " name="_back" type="button" value="<s:text name="operation_tip_cancel" />" onclick="__back()">
	</div>
	
<%-- <form action="systemparam/update.action" name="updateForm" id="updateForm" method="post">
<input type="hidden" name="systemparam.ParamId" id="systemparam.ParamId" value="<s:property value="systemparam.ParamId" />"/>
<div class="pageheader" >
	<!-- 页面标题 -->
	<div class="pagetitle">
		<s:text name="modify_tosystemparam" />
	</div>
	<!-- 操作按钮 -->
	<div class="pageheadercontent">
		<div class="toolbar">
			<div class="toolbarright">
				<input class="okbtn" name="_submit" type="button" value="OK" onclick="__submit()">
				<input class="calbtn" name="_back" type="button" value="Cancel" onclick="__back()">
			</div>
		</div>
	</div>
</div> --%>

<div class="controls">  
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="systemparam_add_paramname" />：
	    </label>
	    <div class="col-sm-8">
	    <input class="form-control" name="systemparam.ParamName" type="text" id="ParamName" value="<s:property value="systemparam.ParamName" />">
   		</div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="systemparam_add_paramvalue" />：
	    </label>
	    <div class="col-sm-8">
	    <input  class="form-control"  name="systemparam.ParamValue" type="text" id="ParamValue" value="<s:property value="systemparam.ParamValue" />">
   		</div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="systemparam_add_paramnumber" />：
	    </label>
	    <div class="col-sm-8">
	    <input  class="form-control"  name="systemparam.ParamNumber" type="text" id="ParamNumber" value="<s:property value="systemparam.ParamNumber" />">
   		</div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="systemparam_add_systemcode" />：
	    </label>
	    <div class="col-sm-8">
	      <input  class="form-control"  name="systemparam.SystemCode" type="text" id="SystemCode" value="<s:property value="systemparam.SystemCode" />">
   		</div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="systemparam_add_status" />：
	    </label>
	    <div class="col-sm-8">
	     <CW:switch name="systemparam.Status" id="Status" type="checkbox" checked="true" />
	    </div>
</div>
 <div class="form-group col-sm-12">
	    <label class="col-sm-2 control-label text-right">
		  <s:text name="systemparam_add_paramremark" />：
	    </label>
	    <div class="col-sm-10">
			<textarea class="form-control" name="systemparam.ParamRemark" id="ParamRemark" cols="20" rows="4"><s:property value="systemparam.ParamRemark"/></textarea>
		</div>
</div>

</div>


<%--  <div class="writetblbox" style="width: 700px">
	<table class="writetbl" style="width: 100%">
    <tr>
    	<th><s:text name="systemparam_add_paramname" />:</th>
    	<td><input name="systemparam.ParamName" type="text" id="ParamName" value="<s:property value="systemparam.ParamName" />"></td>
    	<th><s:text name="systemparam_add_paramvalue" />:</th>
    	<td><input name="systemparam.ParamValue" type="text" id="ParamValue" value="<s:property value="systemparam.ParamValue" />"></td>
    </tr>
    <tr>
    	<th><s:text name="systemparam_add_paramnumber" />:</th>
    	<td><input name="systemparam.ParamNumber" type="text" id="ParamNumber" value="<s:property value="systemparam.ParamNumber" />"></td>
    	<th><s:text name="systemparam_add_systemcode" />:</th>
    	<td><input name="systemparam.SystemCode" type="text" id="SystemCode" value="<s:property value="systemparam.SystemCode" />"></td>
    </tr>
    <tr>
        <th><s:text name="systemparam_add_status" />：</th>
        <!--启用、停用  -->
        <td>
            <div id="radio">
                <input type="radio" id="radio1" name="systemparam.Status" <s:if test="%{systemparam.Status == 1}">checked="checked"</s:if> value="1"><label for="radio1"><s:text name="systemparam_list_td_status_enable" /></label>
                <input type="radio" id="radio2" name="systemparam.Status" <s:if test="%{systemparam.Status == 0}">checked="checked"</s:if> value="0"><label for="radio2"><s:text name="systemparam_list_td_status_disable" /></label>
            </div>
        </td>
    </tr>
        <tr>
    	<th><s:text name="systemparam_add_paramremark" />:</th>
    	<td colspan="3">
    	<textarea name="systemparam.ParamRemark" id="systemparam.ParamRemark" cols="20" rows="4" ><s:property value="systemparam.ParamRemark"/></textarea>
    	</td>
    </tr>
</table>
</div>  --%>
</form>

	<div id="loadingdiv" class="loadingdiv">
	   <center>
	        <div class="child">
				<img src="<%=path%>/images/loading.gif" style="width:160px" />
			</div>
	   </center>
	</div>

</body>
</html>
