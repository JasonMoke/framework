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
    <title>分配账号</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/jquery-ui.css">
    <link href="<%=basePath%>css/en/edit.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=basePath%>js/commons/common.js"></script>
	<script type="text/javascript" src="<%=basePath%>artDialog/artDialog.source.js?skin=twitter"></script>
	<script type="text/javascript" src="<%=basePath%>artDialog/plugins/iframeTools.source.js"></script>
    <script type="text/javascript">
    $(document).ready(
    		function() {
    			$('#addForm').validate(
    			{
    				messages : {
    		        	"userInfo.UserPwd" : {
    					required : '<s:text name="tip_is_not_null"></s:text>',
    					rangelength : '<s:text name="tip_rangelength"><s:param>6</s:param><s:param>32</s:param></s:text>'
	    		        },
	    		        "Pwd2" : {
	    					required : '<s:text name="tip_is_not_null"></s:text>',
	    					rangelength : '<s:text name="tip_rangelength"><s:param>6</s:param><s:param>32</s:param></s:text>',
	    					equalTo:'<s:text name="password_notmatch"></s:text>'
	    		        },
	    			} ,
    				rules : {
    					"userInfo.UserName":{
    						required : true,
    					},
    			        "userInfo.UserPwd" : {//name
    						required : true,
    						rangelength:[6,32],
    					},
    					"Pwd2" : {
      						required : true,
      						rangelength:[6,32],
      						equalTo:"#userInfoUserPwd"//id
      					},
      					
      					
    				}
    		});
    	});  
    function _validateUserName(){
    	$.ajax({  
    		type: "post",  
		    async: false,
		    url: "<%=basePath%>/userAjax/validateUser.action",  
		    data: {                     //要传递的数据
		        "UserName": function() {
		            return $("#UserName").val();
		        }
			 }, 
			 success: function(data){ 
 		    	if(data.toString()=='false'){//重复
 				    art.dialog.alert('该用户名已经存在，请重新输入');
 		    	}else if(data.toString()=='true'){//未重复
 		    		art.dialog.tips('用户名可用');
 		    	}else{
 		    		//art.dialog.tips('新增用户成功');
 		    	}
 		    }
    	});
    }
    
    function __back(){
	 	var _url = "<%=path%>/user/getAllUser.action";
		location.href=_url;
	}
    function __submit(){
        if($("#addForm").valid()){ 
    		$("#loadingdiv").show();
	    		document.addForm.action="<%=basePath%>user/addUserAccount.action";
	    		document.addForm.submit();
        }
	}
    function __refresh(){
       location.reload();
    }
    
    </script>
</head>
  
<body>
<form action="user/addUserAccount.action" name="addForm" id="addForm" method="post" target="mainFrame">
<input type="hidden" name="userInfo.UserId" id="UserId" value=${UserId}> 
<input type="hidden" id="Status" name="Status" value="<s:property value="userInfo.Status"/>">

	<!-- 页面标题 -->
	<h2 class="page-header">账号信息</h2>
	<!-- 操作按钮 -->
	<div class="form-actions text-right">  
		<input class="btn btn-primary  " name="_submit" type="button" value="<s:text name="user_commit"/>"  onclick="__submit()">
		<input class="btn btn-default  " name="_refresh" type="button" value="刷新" onclick="__refresh()">
		<input class="btn btn-default  " name="_back" type="button" value="<s:text name="operation_tip_cancel" />" onclick="__back()">
	</div>	

<div class="controls"> 
<h3 class="page-header">基本信息</h3>
<div class="row"> 
	<div class="form-group col-sm-10">
		<label class="col-sm-4 control-label text-right">
		  账号:
	    </label>
	    <div class="col-sm-6 text-center">
    		<input class="form-control" type="text" id="UserName" name="userInfo.UserName"  value="<s:property value="userInfo.UserName" />" onchange="_validateUserName()"/>
	    </div>
	    <div class="center-block">&nbsp;</div>
    </div>
	<div class="form-group col-sm-10">
		<label class="col-sm-4 control-label text-right">
		 登陆密码:
	    </label>
	    <div class="col-sm-6 text-center">
    		<input class="form-control" type="password" id="userInfoUserPwd" name="userInfo.UserPwd" value="<s:property value="userInfo.UserPwd" />"/>	
		</div>
	    <div class="center-block">&nbsp;</div>
    </div>
	<div class="form-group col-sm-10">
		<label class="col-sm-4 control-label text-right">
		 重复密码:
	    </label>
	    <div class="col-sm-6 text-center">
    		<input class="form-control" type="password" id="Pwd2" name="Pwd2"/>	
		</div>
	    <div class="center-block">&nbsp;</div>
    </div>
	<div class="form-group col-sm-10">
		<label class="col-sm-4 control-label text-right">
		 账号状态:
	    </label>
	    <div class="col-sm-6 text-center">
    		<select class="form-control" name="userInfo.Status" id="userInfoStatus">
      		 <option value="1" <s:if test="userInfo.Status==1">selected = "true"</s:if>>启动</option>
      		 <option value="0" <s:if test="userInfo.Status==0">selected = "true"</s:if>>禁用</option>
         	 </select>
		</div>
	    <div class="center-block">&nbsp;</div>
   		</div>
	</div>

</div>
</form>
</body>
</html>
