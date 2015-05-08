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
<script type="text/javascript" src="<%=basePath%>js/commons/common.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
-->
<%-- <script type="text/javascript">
$(document).ready(function(){
	var h = $("._height").offset().top;
	var h1 = $(".container").css("height");
	if(h!=null&&h!=h1){
		$("._height").offset().top = h1;
	}
});
</script> --%>
</head>
<body>
<input type="hidden" name="locale" id="locale" value="<s:property value="#session['WW_TRANS_I18N_LOCALE'].toString()" />">
<div class="container">
<div class="jumbotron">
	<div class="row">
	<h1>Framework&nbsp;<small>&nbsp;&nbsp;应用支撑平台</small></h1>
	<!-- <img alt="" src="images/cw_logo.jpg" style="margin-top: 50px"> -->
	</div>
	<br>
	<div class="row">
        <div class="col-sm-6">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h4 class="panel-title">Features <span class="badge">1</span></h4>
            </div>
            <div class="panel-body" style="height:110px">
             	拥有强大的应用和资源管理能力，帮助用户提升IT资产的价值
             	<br>
             	具有良好的开放性和松散耦合性，可以非常方便快速的进行用户界面集成
            </div>
          </div>
          <div class="panel panel-primary">
            <div class="panel-heading">
              <h4 class="panel-title">Features <span class="badge">2</span></h4>
            </div>
            <div class="panel-body" style="height:110px">
              	强大的组织机构管理、用户管理、权限管理能力，完善的组织管理模型，更加贴近业务需求的组织管理，满足复杂的企业管理需求，帮助开发商快速开发业务系统。
            </div>
          </div>
        </div><!-- /.col-sm-6 -->
        <div class="col-sm-6">
          <div class="panel panel-success">
            <div class="panel-heading">
              <h4 class="panel-title">Features <span class="badge">3</span></h4>
            </div>
            <div class="panel-body" style="height:110px">
             	具有良好的开放性和松散耦合性，可以非常方便快速的进行用户界面集成
            </div>
          </div>
          <div class="panel panel-info">
            <div class="panel-heading">
              <h4 class="panel-title">Features <span class="badge">4</span></h4>
            </div>
            <div class="panel-body" style="height:110px">
              	安全可见的权限操作，规避复杂业务权限处理的风险
              	<br>
              	贴心的用户体验设计，非常方便用户使用和学习
            </div>
          </div>
        </div>
      </div>
</div>
</div>
</body>
</html>
