<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Update PassWord</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=basePath%>js/commons/common.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/jquery-ui.css"> 
	<link href="<%=basePath%>css/en/edit.css" rel="stylesheet" type="text/css">
	<link href="<%=basePath%>css/en/list.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=basePath%>artDialog/artDialog.source.js?skin=twitter"></script>
	<script type="text/javascript" src="<%=basePath%>artDialog/plugins/iframeTools.source.js"></script> 
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	$(document).ready(
			function() {
				$('#srcForm').validate(
				{
				messages : {
						 "oldPassword" : {
							required : '<s:text name="tip_is_not_null"></s:text>',
							rangelength : '<s:text name="tip_rangelength"><s:param>4</s:param><s:param>32</s:param></s:text>',
							remote:'<s:text name="password_error"></s:text>'
						},
						"newPassword" : {
							required : '<s:text name="tip_is_not_null"></s:text>',
							rangelength : '<s:text name="tip_rangelength"><s:param>6</s:param><s:param>32</s:param></s:text>'
						},
				        "repeatPassword" : {
							required : '<s:text name="tip_is_not_null"></s:text>',
				        	maxlength: '<s:text name="tip_rangelength"><s:param>6</s:param><s:param>32</s:param></s:text>',
				        	equalTo:'<s:text name="password_notmatch"></s:text>'
				        } 
					} ,
					rules : {
				        "oldPassword" : {
							required : true,
							rangelength:[4,32],
							remote: {
							    url: "<%=basePath%>/userAjax/validateUserPwd.action",     //后台处理程序
							    type: "post",               //数据发送方式
							    dataType: "json",           //接受数据格式   
							    data: {                     //要传递的数据
							        "UserPwd": function() {
							            return $("#oldPassword").val();
							        },
							        "UserId": function() {
							            return $("#UserId").val();
							        }
							    }
							}
						},
				        "newPassword" : {
							required : true,
							rangelength:[6,32],
						},
				        "repeatPassword" : {
							required : true,
							rangelength:[6,32],
							equalTo:"#newPassword"
						}
				        
					},
				});
			});
			//重置密码
	function _updatePwd(){
		if( $("#srcForm").valid()){
			var UserId = $("#UserId").val();
			var UserPwd = $("#newPassword").val();
			$.ajax({  
			    type: "post",  
			    async: false,
			    url: "<%=basePath%>/userAjax/resetPwd.action",  
			    data: "UserId=" + UserId +"&UserPwd="+UserPwd,  
			    success: function(data){ 
			    	if(data=='true'){
					    art.dialog({
						    follow: document.getElementById('_submit'),
						    icon: 'succeed',
						    init: function () {
					    	var that = this, i = 3;
					        var fn = function () {
					            that.title(i + '<s:text name="operation_seconds"></s:text>');
					            !i && that.close();
					            i --;
					        };
					        timer = setInterval(fn, 1000);
					        fn();
					    },
						    //lock: true,//背景锁定
					    	//background: '#BFBFBF', // 背景色
					    	//opacity: 0.5,	// 透明度
						    content: '<s:text name="list_successfuloperation"></s:text>'
						});
						window.setTimeout(function(){top.window.location="<%=path%>/loginAction/logout.action";},3000);
			    	}else{
			    		art.dialog.tips('<s:text name="list_operationfailed"></s:text>');
			    	}
			    }
			});
		}else{
			return;
		}
	}
	function _validate(){
	 	$("#srcForm").valid();
	 }
	 function _reset(){
	 	$("#oldPassword").val("");
	 	$("#repeatPassword").val("");
	 	$("#newPassword").val("");
	 }
	</script>
  </head>
  
  <body style="background: #E8E8E8">
  <form action="userAjax/resetPwd.action" name="srcForm" id="srcForm" method="post">
  <input type="hidden" name="UserId" id="UserId" value='<s:property value="#session.UserId"/>' >
  <table class="writetbl" style="width: 500px">
	<tr>
	<th style="width: 30%"><s:text name="user_modify_password" />：</th>
	<td style="width: 70%"><input name="oldPassword" id="oldPassword" type="password" onchange=" _validate()"></td>
	</tr>
	<tr>
	<th><s:text name="user_new_password" />：</th>
	<td><input name="newPassword" id="newPassword" type="password" onchange=" _validate()"></td>
	</tr>
	<tr>
	<th><s:text name="user_repeat_password" />：</th>
	<td><input name="repeatPassword" id="repeatPassword" type="password" onchange=" _validate()"></td>
	</tr>
	<tr>
	<td colspan="2" style="text-align: center;">
		<input name="Button1" id="_submit" class="searchbtn" type="button" value="<s:text name="user_commit" />" onclick="_updatePwd()">&nbsp;&nbsp;&nbsp;&nbsp;
		<input name="Button1" id="_close" class="resetbtn" type="button" value="<s:text name="user_reset" />" onclick="_reset()">
	</td>
	</tr>
	</table>
  </form>
  </body>
</html>
