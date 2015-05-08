<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	HttpSession sessions=request.getSession();
	String I18N = "zh_CN";
	if(sessions!=null&&sessions.getAttribute("WW_TRANS_I18N_LOCALE")!=null){
    	I18N = sessions.getAttribute("WW_TRANS_I18N_LOCALE").toString();
	}
    String language = "";
    if("zh_CN".equals(I18N)){
    	language = "cn";
    }else{
    	language = "en";
    }
%>

<!DOCTYPE HTML">
<html>
<head>
<base href="<%=basePath%>">

<title><s:text name="home_page_title" />
</title>
<!--artdialog 控件 -->
<script type="text/javascript" src="<%=basePath%>js/commons/common.js"></script>
<script type="text/javascript" src="<%=basePath%>js/commons/holder.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/bootstrap/dashboard.css">
<script id='messages_cnjs' type='text/javascript' src='<%=basePath%>js/validate/messages_<%=language %>.js'></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	$(document).ready(function(){
	$("#loginbtn").click(
	  function toggle() {
		$(".loginform").animate({top:"75px"});
	  }
	);
	$("#close_window").click(
	  function toggle() {
		$(".loginform").animate({top:"-555px"});
	  }
	);
	//如果不是顶层，则跳转到顶层
	 if (window != top){
		top.location.href = location.href;
	} 
}); 
//切换语言
function _changeLuang(value){
	if(value==1){
		top.window.location="<%=path%>/loginAction/changeLanguageLogin.action?request_locale=en_US";
	}else{
		top.window.location="<%=path%>/loginAction/changeLanguageLogin.action?request_locale=zh_CN";
	}
}
//点击登录按钮
/* function login(){
 		 $(".sidebox").animate({'right':195},'fast');
         $("#sideframe").attr("src","loginAction/loginPage.action");
         $(".mask").fadeIn(300);
} */
function _submitLogin(){
	if(	$("#srcForm").valid()){
		if(_validateUserName()){
				if(_validateUserPwd()){
					if(_validateUserStatus()){
						document.srcForm.action="<%=path%>/loginAction/Login.action";
						document.srcForm.submit();
					}
				}
			}
		}
}
function _reset(){
	$(".loginInput").val("");
//	$("#user.UserPwd").val("");
	//$("#user.UserName").val("");
}
$(document).ready(
			function() {
				$('#srcForm').validate(
				{
					rules : {
				        "userInfo.UserName" : {
							required : true,
							rangelength:[3,32]
						},
				        "userInfo.UserPwd" : {
							required : true,
							rangelength:[6,32]
						}
					}
				});
			});
	function _validate(){
		 $("#error1").hide();
		 $("#error2").hide();
	 	 $("#srcForm").valid();
	 }
	 function _validateUserName(){
		var flag = false;
	 	$.ajax({  
				    type: "post",  
				    async: false,
				    url: "<%=basePath%>/userAjax/validateUser.action",  
				    data: {                     //要传递的数据
					        "userInfo.UserName": function() {
					            return $("#UserName").val();
					        }
						 },  
				    success: function(data){ 
				    	if(data=='true'){//用户名不存在
						    $("#error1").show();
						   // $("#UserName").addClass("error");
						    flag = false;
				    	}else{
						    $("#error1").hide();
						   // $("#UserName").removeClass("error");
				    		flag = true;
				    	}
				    }
			});
			return flag;
	 }
	 function _validateUserPwd(){
		var flag = false;
	 	$.ajax({  
				    type: "post",  
				    async: false,
				    url: "<%=basePath%>/userAjax/validateUserPwd.action",  
				    data: {                     //要传递的数据
					        "UserPwd": function() {
					            return $("#UserPwd").val();
					        },
					        "UserName": function() {
					            return $("#UserName").val();
					        }
					    },
				    success: function(data){ 
				    	if(data=='false'){//用户名不存在
						    $("#error2").show();
						    ///$("#UserPwd").addClass("error");
						    flag = false;
				    	}else{
						    $("#error2").hide();
						    //$("#UserPwd").removeClass("error");
				    		flag = true;
				    	}
				    }
			});
			return flag;
	 }
	 function _validateUserStatus(){
		var flag = false;
	 	$.ajax({  
				    type: "post",  
				    async: false,
				    url: "<%=basePath%>/userAjax/validateUserStatus.action",  
				    data: {                     //要传递的数据
					        "UserPwd": function() {
					            return $("#UserPwd").val();
					        },
					        "UserName": function() {
					            return $("#UserName").val();
					        }
					    },
				    success: function(data){ 
				    	if(data=='false'){//用户名不存在
						    $("#error3").show();
						    flag = false;
				    	}else{
						    $("#error3").hide();
				    		flag = true;
				    	}
				    }
			});
			return flag;
	 }
	 function keyLogin(){  
	   	if (event.keyCode==13)   //回车键的键值为13  
	   	document.getElementById("_submit").click(); //调用登录按钮的登录事件  
	} 
	</script>
	<style type="text/css">
	/*校验控件样式start*/
.tooltip {
  position: absolute;
  z-index: 1020;
  display: block;
  visibility: visible;
  padding: 5px;
  font-size: 14px;
  opacity: 0;
  filter: alpha(opacity=0);
}

.tooltip-inner {
  max-width: 600px;
  padding: 3px 8px 0px 8px;
  color: #ffffff;
  text-align: center;
  text-decoration: none;
  background-color: #524B4B;
  -webkit-border-radius: 4px;
  -moz-border-radius: 4px;
  border-radius: 4px;
  height: 25px;
  line-height: 25px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.navbar-inverse .navbar-nav > li > a{
	color:#D6D4D4;
}
.loginform {
	position: fixed;
	z-index: 999;
	left: 50%;
	top: -555px;
	width: 298px;
	height: 287px;
	margin-left: 180px;
	/* filter:alpha(opacity=85); */ /*IE滤镜，透明度50%*/
	/* -moz-opacity:0.85; */ /*Firefox私有，透明度50%*/
	/* opacity:0.85; *//*其他，透明度50%*/
}
.loginBox{
	width:420px;
	height:230px;
	padding:0 20px;
	border:1px solid #fff; 
	color:#000; 
	border-radius:8px;
	background: white;
	box-shadow:0 0 15px #222; 
	background: -moz-linear-gradient(top, #fff, #efefef 8%);
	background: -webkit-gradient(linear, 0 0, 0 100%, from(#f6f6f6), to(#f4f4f4));
	font:11px/1.5em 'Microsoft YaHei' ;
}
.loginBox h2{
	height:45px;
	font-size:20px;
	font-weight:normal;
}
body{
	padding-top: 60px;
	padding-bottom: 60px;
}
.glyphicon-chevron-right:before {
content: ">";
}
.glyphicon-chevron-left:before {
content: "<";
}
</style>

	
</head>
<body onkeydown="keyLogin()">
<input type="hidden" name="locale" id="locale" <s:if test="#session['WW_TRANS_I18N_LOCALE']!=null">value='<s:property value="#session['WW_TRANS_I18N_LOCALE'].toString()" />'</s:if> >

<!-- 导航 -->
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation" style="height:60px;">
   <div class="container">
     <div class="navbar-header">
       <a class="navbar-brand" href="#">
	Framework
       </a>
     </div>
     <div class="navbar-collapse collapse" style="font-size: 16px;">
      <ul class="nav navbar-nav navbar-right">
          <li class="dropdown"> 
          <a data-toggle="dropdown" class="dropdown-toggle navbar-brand" href="#">
          <s:text name="change_the_language" />
 			<b class="caret"></b></a>
            <ul class="dropdown-menu">
              <li><a href="#" onclick="_changeLuang(2);return false;"><s:text name="language_zh" /></a>
              </li>
              <li><a href="#" onclick="_changeLuang(1);return false;"><s:text name="language_en" /></a></li>
            </ul>
          </li>
          <li class="divider-vertical"></li>
          <li>
      		<input id="loginbtn" class="btn" name="Button1" style="margin-top: 8px;" type="button" value="<s:text name="login_sigin" />"> 
		  </li>
        </ul>
      <!-- <form class="navbar-form navbar-right">
        <input type="text" class="form-control" placeholder="Search...">
      </form> -->
    </div>
  </div>
</div>
<!-- main -->
<div class="container" role="main">
<br>
	<div class="jumbotron">
		<div class="row">
		<h1>Framework&nbsp;<small>&nbsp;&nbsp;应用支撑平台</small></h1>
		</div>
		<br>
		<div class="row">
		<div id="carousel-example-captions" class="carousel slide" data-ride="carousel">
		     <ol class="carousel-indicators">
		       <li data-target="#carousel-example-captions" data-slide-to="0" class="active"></li>
		       <li data-target="#carousel-example-captions" data-slide-to="1"></li>
		       <li data-target="#carousel-example-captions" data-slide-to="2"></li>
		       <li data-target="#carousel-example-captions" data-slide-to="3"></li>
		     </ol>
		     <div class="carousel-inner" role="listbox">
		       <div class="item active">
		         <img data-src="holder.js/1050x340/auto/#6E6E6E:#222/text:First Features" >
		         <div class="carousel-caption">
		           	<p>拥有强大的应用和资源管理能力，帮助用户提升IT资产的价值.具有良好的开放性和松散耦合性，可以非常方便快速的进行用户界面集成
				 	</p>
		         </div>
		       </div>
		       <div class="item">
		         <img data-src="holder.js/1050x340/auto/#666:#222/text:Second Features" >
		         <div class="carousel-caption">
		           <p>强大的组织机构管理、用户管理、权限管理能力，完善的组织管理模型，满足复杂的企业管理需求，帮助开发商快速开发业务系统</p>
		         </div>
		       </div>
		       <div class="item">
		         <img data-src="holder.js/1050x340/auto/#5E5E5E:#222/text:Third Features" >
		         <div class="carousel-caption">
		           <p>具有良好的开放性和松散耦合性，可以非常方便快速的进行用户界面集成</p>
		         </div>
		       </div>
		       <div class="item">
		         <img data-src="holder.js/1050x340/auto/#555555:#222/text:Fourth Features" >
		         <div class="carousel-caption">
		           <p>安全可见的权限操作，规避复杂业务权限处理的风险.贴心的用户体验设计，非常方便用户使用和学习
				</p>
		         </div>
		       </div>
		     </div>
		     <a class="left carousel-control" href="#carousel-example-captions" role="button" data-slide="prev">
		       <span class="glyphicon glyphicon-chevron-left"></span>
		       <span class="sr-only">Previous</span>
		     </a>
		     <a class="right carousel-control" href="#carousel-example-captions" role="button" data-slide="next">
		       <span class="glyphicon glyphicon-chevron-right"></span>
		       <span class="sr-only">Next</span>
		     </a>
		   </div>
    	</div>
	</div>
	</div>
	<!-- 底部导航 -->
<nav class="navbar navbar-default navbar-fixed-bottom" role="navigation">
  <div class="container">
  <br>
  	<p class="text-center">Copyright © 2012-2014 CreativeWises, Inc. All Rights Reserved.</p>
  </div>
</nav>
<!-- 登录框 -->
<div class="loginform">
<form action="loginAction/Login.action" method="post" name="srcForm" id="srcForm" >
    <div class="loginBox row-fluid">
        <h2>登录</h2>
     	<div class="form-group col-sm-12">
	    <label class="col-sm-3 control-label text-right">
		  <s:text name='username' />:
	    </label>
	    <div class="col-sm-9">
		  <input class="userinput form-control" name="userInfo.UserName" id="UserName" placeholder="<s:text name='input_username' />" value="" type="text"  onkeypress=" _validate()">
		  <div for="UserName" id="error1" class="tooltip tooltip-inner" style="display: none; opacity: 0.8;"><s:text name="user_not_exist"/></div>
	    </div>
		</div>
		<br>	
     	<div class="form-group col-sm-12">
	    <label class="col-sm-3 control-label text-right">
		  <s:text name='password' />:
	    </label>
	    <div class="col-sm-9">
	    <input class="passinput form-control" name="userInfo.UserPwd" id="UserPwd" placeholder="密码" type="password"  onkeypress=" _validate()">
		<div for="UserPwd" id="error2" class="tooltip tooltip-inner" style="display: none; opacity: 0.8;"><s:text name="password_error"/></div>
		<div for="UserPwd" id="error3" class="tooltip tooltip-inner" style="display: none; opacity: 0.8;"><s:text name="user_is_disabled"/></div>
	    </div>
		</div>	
		<div class="form-group col-sm-12">
		<div class="col-sm-6 text-right">
	 	  <input name="_submit" id="_submit" class="btn btn-primary" type="button" value="<s:text name="login_sigin" />" onclick="_submitLogin()"> 
		</div>	
		<div class="col-sm-6 text-left">
	 	  <input name="_cancle" id="close_window" class="close_window btn" type="button" value="<s:text name="operation_tip_cancel" />"> 
		</div>	
		</div>	
	 </div> 
 </form>
 </div> 
</body>
</html>
