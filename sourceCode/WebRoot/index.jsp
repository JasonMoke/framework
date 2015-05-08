<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			HttpSession sessions=request.getSession();
    String I18N =sessions.getAttribute("WW_TRANS_I18N_LOCALE").toString();
    String language = "";
    if("zh_CN".equals(I18N)){
    	language = "cn";
    }else{
    	language = "en";
    }
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title><s:text name="home_page_title" />
</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--jqueryUI  -->
<script type="text/javascript" src="<%=basePath%>js/commons/common.js"></script>
<script type="text/javascript" src="<%=basePath%>artDialog/artDialog.source.js?skin=twitter"></script>
<script type="text/javascript" src="<%=basePath%>artDialog/plugins/iframeTools.source.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/bootstrap/dashboard.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/bootstrap/font-awesome/css/font-awesome.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/bootstrap/stylesheets/theme.css">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	var ExplorerType = getExplorerType();
 $(document).ready(function(){
 		var locale = $("#locale").val();
 			var okVal = '<s:text name="operation_tip_ok" />';
 		if(locale=='zh_CN'){
 			okVal = '<s:text name="operation_tip_ok" />';
 		}else{
 			okVal = '&nbsp;&nbsp;<s:text name="operation_tip_ok" />&nbsp;&nbsp;';
 		}
 		
	   (function (config) {
	    config['title'] = '<s:text name="operation_tip_title" />';
	    config['okVal'] = okVal;
	    config['cancelVal'] = '<s:text name="operation_tip_cancel" />';
	    config['background'] = '#BFBFBF';
	    config['opacity'] = '0.5';
	    config['drag'] = false;
	    config['resize'] = false;
	    config['lock'] = true;
	    // [more..]
	})(art.dialog.defaults);
	   
	   
	  /*  $("#mainFrame").load(function(){
		   var mainheight = $(this).contents().find("body").height()+50;
		   alert(mainheight)
		   $(this).height(mainheight);
		   }); */ 
	});
//调整iframe高度
var isLoadAuto = false;
function iframeAuto() 
{ 
try 
	{ 
		//定位需要调整的frame框架
		var a = document.getElementsByName("mainFrame"); 
		for(var i=0; i<a.length; i++)
		{ 
			var h = 0;
			$(window.frames["mainFrame"].document).find("._height").each(function(i){
		 		if($(this).offset().top>h){
		 		 	h = $(this).offset().top;
		 		}
			});
			//var h=$(window.frames["mainFrame"].document).find("#_height").offset().top;
			//var h1 = $("#side").css("height").replace("px","");
			//var h1 = $(window).height();
			//var h2=Math.max(h1, h);      //取两者中的最大值
			/* //调整框架的大小
			var ExplorerType = getExplorerType();
			if(ExplorerType=="IE"){
				//h2 = h2-48;
			}else if(ExplorerType=="chrome"){
				//h2 = h2-180;
			}else{
				//h2 = h2-46;
			} */
			if(ExplorerType=="IE"&&isLoadAuto==true){
				h = h - 54;
			}
			//如果为首页
			a[i].style.height =  h +"px"; 
			isLoadAuto = true;
		} 
	} 
catch (ex){} 
} 
window.setInterval(iframeAuto, 200); 
	//点击菜单后显示跳转
function forward(obj,obj1,obj2){
	if(obj==""||obj==null){
		 return false;
	}else{
		$('html,body').animate({
            'scrollTop': 0
          }, 300);
		$(".active").removeClass("active");
		$(obj1).addClass("active");
		if(obj1.target=='_blank'){
			window.open("<%=path%>/"+obj);
		}else{
			mainFrame.location.href ="<%=path%>/"+obj;
		}
		isLoadAuto = false;
	}
}
	//切换系统
function _location(obj,SystemCode){
	if(obj==""||obj==null){
		 return false;
	}else{
		$("#SystemCode").val(SystemCode);
		document.srcForm.action="<%=path%>/"+obj;
		document.srcForm.submit();
	}
}
//logout
function _logout(){
	top.window.location="<%=path%>/loginAction/logout.action";
}
//切换语言
function _changeLuang(value){
	if(value==1){
		document.srcForm.action="<%=path%>/loginAction/changeLanguage.action?request_locale=en_US";
		document.srcForm.submit();
	}else{
		document.srcForm.action="<%=path%>/loginAction/changeLanguage.action?request_locale=zh_CN";
		document.srcForm.submit();
	}
}
//打开修改密码
function _updatePwd(){
    var _url = "<%=basePath%>user/toUpdatePwd.action";
    	//调用art.dialog组件弹出窗口 采用iframe方式
    	art.dialog.open(_url,
		{ 
		 	lock: true,//背景锁定
	    	background: '#BFBFBF', // 背景色
	    	opacity: 0.5,	// 透明度
			title : '<s:text name="changepassword"></s:text>',
			width: 600,
			height: 300,
			drag: false,//禁止拖动
			resize: false,//禁止改变大小
    	}); 
}
function _modifyUser(){
    var _url = "<%=basePath%>user/modifyHomeUser.action";
    	//调用art.dialog组件弹出窗口 采用iframe方式
    	art.dialog.open(_url,
		{ 
		 	lock: true,//背景锁定
	    	background: '#BFBFBF', // 背景色
	    	opacity: 0.5,	// 透明度
			title : '<s:text name="changepassword"></s:text>',
			width: 1000,
			height: 710,
			drag: false,//禁止拖动
			resize: false,//禁止改变大小

    	}); 
}
/* function iFrameHeight() {

    var ifm= document.getElementById("mainFrame");

    var subWeb = document.frames ? document.frames["mainFrame"].document:ifm.contentDocument;

    if(ifm != null && subWeb != null) {
		if(subWeb.body!=null){
		    ifm.height = subWeb.body.scrollHeight;
		}
    }
}  */
//window.setInterval(iFrameHeight, 200); 
	</script>
	<%-- <script type="text/javascript">
        $(function() {
            var match = document.cookie.match(new RegExp('color=([^;]+)'));
            if(match) var color = match[1];
            if(color) {
                $('body').removeClass(function (index, css) {
                    return (css.match (/\btheme-\S+/g) || []).join(' ')
                })
                $('body').addClass('theme-' + color);
            }

            $('[data-popover="true"]').popover({html: true});
            
        });
    </script>
    <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .navbar-default .navbar-brand, .navbar-default .navbar-brand:hover { 
            color: #fff;
        }
    </style>
--%>
<style type="text/css">
.navbar-inverse .navbar-nav > li > a{
	color:#D6D4D4;
}
body{font-family:"Microsoft YaHei",微软雅黑,"Microsoft JhengHei",华文细黑,STHeiti,MingLiu}
#side a{font-family:"Microsoft YaHei",微软雅黑,"Microsoft JhengHei",华文细黑,STHeiti,MingLiu}
</style>
    <script type="text/javascript">
        $(function() {
            var uls = $('.sidebar-nav > ul > *').clone();
            uls.addClass('visible-xs');
            $('#main-menu').append(uls.clone());
        });
    </script> 
	
</head>

<body>
<form method="post" id="srcForm" name="srcForm" action="">
<input type="hidden" name="SystemCode" id="SystemCode" value="">
<input type="hidden" name="locale" id="locale" value="<s:property value="#session['WW_TRANS_I18N_LOCALE'].toString()" />">
   <div class="navbar navbar-inverse" role="navigation" >
      <div class="container-fluid">
        <div class="navbar-header">
          <a class="navbar-brand" href="#">
          <s:if test="%{#session.systemFullName == null||#session.systemFullName==''}">
			Framework
          </s:if>
          <s:else>
          <s:property value="#session.systemFullName" />
          </s:else>
          </a>
        </div>
        <div class="navbar-collapse collapse" style="font-size: 16px;">
          <ul class="nav navbar-nav navbar-right">
          	<s:if test="listSystemManager.size()>1">
	          <li class="dropdown"> 
	               <a data-toggle="dropdown" class="dropdown-toggle" href="#">
	               	应用
				   <b class="caret"></b></a>
	                 <ul class="dropdown-menu">
	                 <s:iterator value="navigationList" var="navigation" status="navigation">
						<li	onClick="_location('<s:property value="NavUrl" />','<s:property value="SystemCode" />');return false">
							<a href="#" > 
							<s:property value="NavName" />
							</a>
						</li>
						</s:iterator>
	                 </ul>
	               </li>
               </s:if>
	           	<!-- <li><a href="#">Dashboard</a></li>
	            <li><a href="#">Settings</a></li>
	            <li><a href="#">Profile</a></li>
	            <li><a href="#">Help</a></li> -->
               <li class="dropdown"> 
               <a data-toggle="dropdown" class="dropdown-toggle" href="#">
               <s:text name="change_the_language" />
			   <b class="caret"></b></a>
                 <ul class="dropdown-menu">
                   <li><a href="#" onclick="_changeLuang(2);return false;"><s:text name="language_zh" /></a>
                   </li>
                   <li><a href="#" onclick="_changeLuang(1);return false;"><s:text name="language_en" /></a></li>
                 </ul>
               </li>
               <li class="divider-vertical"></li>
               <li class="dropdown"> 
               <a data-toggle="dropdown" class="dropdown-toggle" href="#">
				<img alt="" class="img-rounded" style="height:50px;margin-top: -10px;margin-bottom: -10px;" src="<%=path%>/upload/getPriviewInpByCondition.action?conditionName=UserId&conditionKey=<s:property value="#session.UserId" />&table=t_userdata&InputStreamName=UserLogo">
			   <b class="caret"></b>
			   </a>
                 <ul class="dropdown-menu">
                   <li><a href="#" onclick="_modifyUser();return false;">用户信息</a></li>
                   <li><a href="#" onclick="_updatePwd();return false;">修改密码</a></li>
                   <li class="divider"></li>
                   <li><a href="#" onclick="_logout();return false;">注销</a></li>
                 </ul>
               </li>
             </ul>
          <!-- <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form> -->
        </div>
      </div>
    </div>
	<!-- main -->
    <div class="container-fluid">
    <div class="row">
    <div id="side" class="sidebar-nav col-sm-3 col-md-2" style="padding: 0px;">
    <ul>
    <s:iterator var="mep" value="pubmenuList" status="status">
		<s:if test="%{#mep.PID == 0}">
			<!-- 没有下级 -->
			<s:if test="%{#mep.HasChild != 1}">
			<li id="pNav<s:property value="#status.count" />" 
			>
			<a href="#"  
			<s:if test="%{#mep.isPreferences == 1}">
			class="nav-header active"
			</s:if>
			<s:else>
			class="nav-header"
			</s:else>
			onClick="forward('<s:property value="#mep.MenuUrl" />',this);return false"
			<s:if test="%{#mep.Target == 1}">target="_blank"</s:if>>
				<!-- <i class="fa fa-fw fa-question-circle"></i> -->
				<s:property value="#mep.MenuName" />
			</a>
			</li>
			</s:if>
			<!-- 有下级 -->
			<s:if test="%{#mep.HasChild == 1}">
			<li>
			<a href="#" class="nav-header" data-target=".pNav<s:property value="#status.count" />" class="nav-header" data-toggle="collapse" id="pNav<s:property value="#status.count" />" 
			<s:if test="%{#mep.isPreferences == 1}">
			class="active"
			</s:if>
			onClick="forward('<s:property value="#mep.MenuUrl" />',this);return false"
			>
			<!-- <i class="fa fa-fw fa-dashboard"></i>  -->
			<s:property value="#mep.MenuName" /> 
			<i class="fa fa-collapse"></i></a>
			</li>
			<li><ul class="pNav<s:property value="#status.count" /> nav nav-list collapse in" id="mNav<s:property value="#status.count" />">
			<s:iterator var="mec" value="#request.pubmenuList" status="status2">
				<s:if test="%{#mec.PID == #mep.UUID}">
					<li	onClick="forward('<s:property value="#mec.MenuUrl" />',this,'pNav<s:property value="#status.count" />');return false" >
						<a href="#" <s:if test="%{#mec.Target == 1}">target="_blank"</s:if>> 
						<span class="fa fa-caret-right"></span>
						<s:property value="#mec.MenuName" />
						 </a>
					</li>
				</s:if>
			</s:iterator>
    		</ul>
    		</li>
    		</s:if>
    		</s:if>
    		</s:iterator>
       </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        <div id="leamain">
		<iframe 
        <s:if test="%{data.IsPreferencesMenuUrl !=null}">src="<%=basePath%>/<s:property value="data.IsPreferencesMenuUrl" />"</s:if> 
        <s:if test="%{data.IsPreferencesMenuUrl ==null}">src="<%=basePath%>/loginAction/home.action"</s:if>
        id="mainFrame" align="top" name="mainFrame" marginwidth="0" marginheight="0" frameborder="0" scrolling="no" style="overflow-x:hidden;overflow-y:hidden;min-height:100%;width:100%;"></iframe>

     </div>
         </div>
      </div>
    </div>
    </form>
</body>
</html>
