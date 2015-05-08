<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--引用CW标签  -->
<%@ taglib uri="/WEB-INF/taglib/cw" prefix="CW"%>
<!--引用struts标签  -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>会话管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--通用js  -->
<script type="text/javascript" src="<%=basePath%>js/commons/common.js"></script>
<!--artDialog-->
<script type="text/javascript"
	src="<%=basePath%>artDialog/artDialog.source.js?skin=twitter"></script>
<script type="text/javascript"
	src="<%=basePath%>artDialog/plugins/iframeTools.source.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		/*  $(".tablesorter").tablesorter({
		headers:{ 
		    0:{sorter:false},	
		    1:{sorter:'digit'}, 
		    2:{sorter:'text'}, 
		    3:{sorter:'text'}, 
		    4:{sorter:'text'}, 
		    5:{sorter:'text'}, 
		    6:{sorter:false},	
		    7:{sorter:false}	
		 }	
		}); */
	});
	//刷新页面
	function _resresh() {
		document.srcForm.action="<%=path%>/session/findOnLineList.action";
		document.srcForm.submit();
	}
	//强制踢人
	function _forcelogout(sessionId) {
		document.srcForm.action="<%=path%>/session/forceLogout.action?sessionId="+sessionId;
		document.srcForm.submit();
	}
</script>
</head>
<body>
	<form action="/session/findOnLineList.action" name="srcForm" id="srcForm" method="post">
		<s:hidden name="actionResult" id="actionResult"></s:hidden>
		<!-- 模块名称 -->
		<h2 class="page-header">会话管理</h2>
		<!-- 工具条 -->
		<input name="refresh" type="button"  value="<s:text name="operation_resresh" />" class="btn btn-default"	onclick="_resresh();return false;">
		<!-- 列表内容 -->
		<table class="table table-striped tablesorter table-hover table-condensed">
			<thead>
				<tr>
					<th>序号</th>
					<th>SessionId</th>
					<th>账户</th>
					<th>组织</th>
					<th>IP</th>
					<th>创建时间</th>
					<th>最后交互时间</th>
					<th>总时间</th>
					<th width="10%">操作</th>
				</tr>
			</thead>
				<s:if test="sessionlist.size()>0">
				<s:iterator value="sessionlist" var="list" status="status"  > 
				<tr>
					<td><s:property value="#status.count" /></td>
					<td><s:property value="SessionId" /></td>
					<td><s:property value="UserName" /></td>
					<td><CW:dataValue property="OrganId" field="OrganId" organ="true"></CW:dataValue></td>
					<td><s:property value="IP" /></td>
					<td><s:date name="CreateTime" format="yyyy-MM-dd HH:mm:ss" /></td>
					<td><s:date name="LastAccessedTime" format="yyyy-MM-dd HH:mm:ss" /></td>
					<td><s:date name="TotalTime" format="mm:ss" /></td>
					<td>
						<div class="btn-group">
						<a class="btn btn-default dropdown-toggle btn-sm" data-toggle="dropdown" href="#" onclick="return false;">
							<s:text name="operation" />
						<span class="caret"></span>
						</a>
						<ul class="dropdown-menu pull-right">
						<li><a href="#" onclick="_forcelogout('<s:property value="SessionId" />');return false;">强制下线</a></li>
						</ul>
						</div>
					 </td>
					 </tr>
				</s:iterator>
				</s:if>
			<tbody>
			</tbody>
		</table>
		<s:else>
			<div class="container">
				<div class="jumbotron">
					<div class="row text-center">
						<img alt="" src="images/errorc.png">
						<h3>
							<s:text name="list_nodata_to_load" />
						</h3>
					</div>
				</div>
			</div>
		</s:else>
		<!--分页条-->
		<CW:page property="page" url="session/findOnLineList.action"></CW:page>
	</form>
</body>
</html>
