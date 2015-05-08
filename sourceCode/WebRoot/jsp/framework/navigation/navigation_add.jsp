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
    
    <title>navigation_add</title>
    
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
                    "navigation.SystemCode" : {required : true, rangelength : [1,64], maxlength : 64},
                    "navigation.NavName" : {required : true, rangelength : [1,100], maxlength : 100},
                    "navigation.NavUrl" : {required : false, rangelength : [1,1000], maxlength : 1000},
                    "navigation.UrlPrefix" : {required : false, rangelength : [1,1000], maxlength : 1000},
                    "navigation.SeqNum" : {required : true, number : true, maxlength : 8},
                    "Navigation.HelpFilePath" : {required : false, rangelength : [1,200], maxlength : 200},
            }
    	});
    });
        
    function __back(){
	 	var _url = "<%=path%>/navigation/findList.action";
		location.href=_url;
	}
    function __submit(){
        if($("#addForm").valid()){
    		$("#loadingdiv").show();
    		document.addForm.action="<%=basePath%>navigation/insert.action";
    		document.addForm.submit();
            }
	}
    </script>
</head>
  
<body>
<!-- target属性指向index.jsp的iframe -->
<form action="navigation/insert.action" name="addForm" id="addForm" method="post" target="mainFrame">
 <h2 class="page-header"><s:text name="navigation_list_page_add" /></h2>
   <div class="form-actions text-right">  
 	<input class="btn btn-primary  " name="_submit" type="button" value="<s:text name="operation_submit" />" onclick="__submit()">
	<input class="btn btn-default  " name="_back" type="button" value="<s:text name="operation_tip_cancel" />" onclick="__back()">
</div>
<div class="controls">  
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="navigation_list_th_systemcode" />：
	    </label>
	    <div class="col-sm-8">
		<CW:select name="navigation.SystemCode" id="SystemCode" codeName="listSystemparam" className="form-control" isCode="false" field="navigation.SystemCode" > 
		<CW:option value="">--请选择--</CW:option>
		</CW:select>
	    </div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="navigation_list_th_navname" />：
	    </label>
	    <div class="col-sm-8">
	    <input class="form-control" name="navigation.NavName" type="text" id="NavName">
	    </div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="navigation_list_th_navurl" />：
	    </label>
	    <div class="col-sm-8">
	    <input class="form-control" name="navigation.NavUrl" type="text" id="NavUrl">
	    </div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="navigation_list_th_urlprefix" />：
	    </label>
	    <div class="col-sm-8">
	    <input class="form-control" name="navigation.UrlPrefix" type="text" id="UrlPrefix">
	    </div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="navigation_list_th_seqnum" />：
	    </label>
	    <div class="col-sm-8">
	    <input class="form-control" name="navigation.SeqNum" type="text" id="SeqNum">
	    </div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="navigation_list_th_status" />：
	    </label>
	    <div class="col-sm-8">
	    <CW:switch name="navigation.Status" id="Status" type="checkbox" checked="true"/>
	    </div>
</div>
 <div class="form-group col-sm-12">
	    <label class="col-sm-2 control-label text-right">
		  <s:text name="navigation_list_th_helpfilepath" />：
	    </label>
	    <div class="col-sm-10">
	    <input class="form-control" name="navigation.HelpFilePath" type="text" id="HelpFilePath">
	    </div>
</div>
</div>
</form>
<span class="_height"></span>
<div id="loadingdiv" class="loadingdiv">
	   <center>
	        <div class="child">
				<img src="<%=path%>/images/loading.gif" style="width:160px" />
			</div>
	   </center>
	</div>
</body>
</html>
