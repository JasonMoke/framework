<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/cw" prefix="CW"%>
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
<title>模块管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<!--通用js  -->
	<script type="text/javascript" src="<%=basePath%>js/commons/common.js"></script>
	<!--artDialog-->
	<script type="text/javascript" src="<%=basePath%>artDialog/artDialog.source.js?skin=twitter"></script>
	<script type="text/javascript" src="<%=basePath%>artDialog/plugins/iframeTools.source.js"></script>
<script type="text/javascript">
$(document).ready(function(){
  //页面排序
	   $(".tablesorter").tablesorter({
		  headers:{ 
	          0:{sorter: false},
	          1:{sorter: 'digit'},
	          2:{sorter: 'text'}, 
	          3:{sorter: 'text'}, 
	          4:{sorter: 'text'}, 
	          5:{sorter: false}
	       }	
		});
  //显示操作结果
	 var actionResult = $("#actionResult").val();
	 if(actionResult=="success"){
	     parent.leftFrame.location = "<%=basePath%>/jsp/framework/module/module_tree.jsp";
	 	art.dialog.tips('<s:text name="list_successfuloperation"></s:text>');
	 }else if(actionResult=="error"){
	 	art.dialog.tips('<s:text name="list_operationfailed"></s:text>');
	 }
  
}); 
	//返回到应用页面
	function _back() {
	     //设置请求的url
	    var url = "<%=basePath%>/systemmanager/findList.action";
	    location.href=url;
	}

</script>
</head>
<body>
	<form method="post" id="srcForm" name="srcForm" action="systemmanager/detailModule.action" target="mainFrame" >
	<input type="hidden" name="systemmanager.SystemCode" id="SystemCode" value='<s:property value="SystemCode"/>'>
	<h2 class="page-header"><s:text name="模块管理" /></h2>
	<div class="row placeholders">
      <div class="col-xs-6 placeholder">
      </div>
      <div class="col-xs-6 placeholder" style="text-align: right">
		<input name="back" type="button" value="<s:text name="返回" />" class="btn btn-default" onclick="_back();return false;">
      </div>
    </div>
    <div class="table-responsive">
	<table class="table table-striped tablesorter table-hover" >
	<thead>
		<tr>
			<th width="5%"><CW:checkAll childName="chk_list" name="checkAll" id="checkAll" /></th>
			<th><s:text name="序号" />
			<th><s:text name="名称" />
			</th>
			<th><s:text name="创建人" />
			</th>
			<th><s:text name="创建时间" />
			</th>
			<th><s:text name="状态" />
		</tr>
		</thead>
		<tbody>  
		<s:if test="moduleList.size()>0">
			<s:iterator value="moduleList" var="list"	status="status">
					<tr>
					<td><input type="checkbox" name="chk_list"	id="chk_list_<s:property value="#status.count" />"	value="<s:property value="ModuleId" />" /></td>
					<td><span title="<s:property value="#status.count" />"><s:property value="#status.count" /></span></td>
					<td><span title="<s:property value="ModuleName" />"><s:property value="ModuleName" /></span>
					</td>
					<td><CW:dataValue property="CreatePerson" field="CreatePerson" person="true" title="isvalue"></CW:dataValue>
					</td>
					<td><span title="<s:date name="CreateTime" format="yyyy/MM/dd HH:mm:ss" />"><s:date name="CreateTime" format="yyyy/MM/dd HH:mm:ss" /></span>
					</td>
					<td><CW:status url="/moduleAjax/changeModuleStatus.action" field="Status"  disabled="false"><s:hidden value="%{#list.ModuleId}" /></CW:status>
					</td>
				</tr>
			</s:iterator>
		</s:if>
		</tbody>  
	</table>
	 <s:else>
       <div class="container">
		<div class="jumbotron">
		<div class="row text-center">
		<img alt="" src="images/errorc.png">
		<h3><s:text name="list_nodata_to_load" /></h3>
		</div>
		</div>
		</div>
	</s:else>
	<CW:page property="pageModuleManager" url="systemmanager/detailModule.action" ></CW:page>
</div>
</form>
</body>
</html>
