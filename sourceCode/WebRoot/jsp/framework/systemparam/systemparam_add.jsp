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
    
    <title><s:text name="systemparam_add_page_title" /></title>
    
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
	<!--通用js  -->
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
            $('#addForm').validate({
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
	 	var _url = "<%=path%>/systemparam/findList.action";
		location.href=_url;
	}
function __submit(){
    if($("#addForm").valid()){
		$("#loadingdiv").show();
		document.addForm.action="<%=basePath%>systemparam/insert.action";
		document.addForm.submit();
	}
}
    </script>
</head>
  
<body>
<!-- target属性指向index.jsp的iframe -->
<form action="systemparam/insert.action" name="addForm" id="addForm" method="post" target="mainFrame">
 <h2 class="page-header"><s:text name="add_tosystemparam" /></h2>
 
  <div class="form-actions text-right">  
 	<input class="btn btn-primary  " name="_submit" type="button" value="<s:text name="operation_submit" />" onclick="__submit()">
	<input class="btn btn-default  " name="_back" type="button" value="<s:text name="operation_tip_cancel" />" onclick="__back()">
</div>
<div class="controls">  
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="systemparam_add_paramname" />：
	    </label>
	    <div class="col-sm-8">
		  <input class="form-control" name="systemparam.ParamName" type="text" id="ParamName">
	    </div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="systemparam_add_paramvalue" />：
	    </label>
	    <div class="col-sm-8">
	    <input class="form-control" name="systemparam.ParamValue" type="text" id="ParamValue">
	    </div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="systemparam_add_paramnumber" />：
	    </label>
	    <div class="col-sm-8">
	    <input class="form-control" name="systemparam.ParamNumber" type="text" id="ParamNumber">
	    </div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="systemparam_add_systemcode" />：
	    </label>
	    <div class="col-sm-8">
	    <input class="form-control" name="systemparam.SystemCode" type="text" id="SystemCode">
	    </div>
</div>
 <div class="form-group col-sm-12">
	    <label class="col-sm-2 control-label text-right">
		  <s:text name="systemparam_add_status" />：
	    </label>
	    <div class="col-sm-10">
	    <CW:switch name="systemparam.Status" id="Status" type="checkbox" checked="true"/>
	    </div>
</div>
 <div class="form-group col-sm-12">
	    <label class="col-sm-2 control-label text-right">
		  <s:text name="systemparam_add_paramremark" />：
	    </label>
	    <div class="col-sm-10">
	    <textarea class="form-control" cols="20" name="systemparam.ParamRemark" rows="4"></textarea>
	    </div>
</div>
</div>
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
