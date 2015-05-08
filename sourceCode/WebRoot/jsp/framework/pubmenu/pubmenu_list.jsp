
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--引用CW标签  -->
<%@ taglib uri="/WEB-INF/taglib/cw" prefix="CW" %>
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
    
    <title><s:text name="菜单管理" /></title>
    
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!--通用js  -->
	<script type="text/javascript" src="<%=basePath%>js/commons/common.js"></script>
	<!--artDialog-->
	<script type="text/javascript" src="<%=basePath%>artDialog/artDialog.source.js?skin=twitter"></script>
	<script type="text/javascript" src="<%=basePath%>artDialog/plugins/iframeTools.source.js"></script>
	<script type="text/javascript">
	 $(document).ready(function() {
		 //排序
		 $(".tablesorter").tablesorter({
			  headers:{ 
		          0:{sorter:false},	//不排序
		          1:{sorter:'digit'}, //按数字排序
		          2:{sorter:'text'}, //按字符排序
		          3:{sorter:'text'}, 
		          4:{sorter:'text'},
		          5:{sorter:false},	
		          6:{sorter:false}	
		       }	
		 });
	});
	//删除
	function _deleteOne(id){
		var NavId = $("#NavId",parent.document).val();
		art.dialog.confirm('<s:text name="operation_confirm"><s:param><s:text name="operation_delete"></s:text></s:param></s:text>', 
		function(){
			document.rightFrame.action="<%=path%>/pubmenu/deleteBatch.action?ids="+id+"&NavId="+NavId;
			document.rightFrame.submit();
		}, function(){
		    art.dialog.tips('<s:text name="operation_cancel"></s:text>');
		});
	}
	//批量删除
	function _delete(){
		var NavId = $("#NavId",parent.document).val();
		var ids = getCheckedValue(); 
		if(ids==""||ids==null){
			art.dialog.alert('<s:text name="operation_no_select"></s:text>');
		}else{
			art.dialog.confirm('<s:text name="operation_confirm"><s:param><s:text name="operation_delete"></s:text></s:param></s:text>', 
			function(){
				document.rightFrame.action="<%=basePath%>pubmenu/deleteBatch.action?ids="+ids+"&NavId="+NavId;
				document.rightFrame.submit();
			}, function(){
			    art.dialog.tips('<s:text name="operation_cancel"></s:text>');
			});
		}
	}
	//新增
	function _add() {
		var doc= parent.frames['leftFrame'].document;
		var PID = doc.getElementById("PID").value;
		var NavId = $("#NavId",parent.document).val();
		 //设置请求的url 
		var _url = "<%=basePath%>pubmenu/gotoAdd.action?NavId="+NavId+"&PID="+PID;
		document.rightFrame.action=_url;
		document.rightFrame.submit();
	}
	//修改
	function _modify(id) {
		 //设置请求的url 
		var _url = "<%=basePath%>pubmenu/gotoModify.action?id="+id;
		document.rightFrame.action=_url;
		document.rightFrame.submit();
	}
	//查询一条数据
	function _detail(id){
		var NavId = $("#NavId",parent.document).val();
	 	//设置请求的url 
		var _url = "<%=basePath%>pubmenu/detail.action?id="+id+"&NavId="+NavId;
		document.rightFrame.action=_url;
		document.rightFrame.submit();
	}
	//刷新页面
	function _resresh(){
		var NavId = $("#NavId",parent.document).val();
		document.rightFrame.action="<%=path%>/pubmenu/findAllList.action?NavId="+NavId;
		document.rightFrame.submit();
	}

</script>
  </head>
  
<body>
	<form action="pubmenu/findList.action" id="rightFrame" name="rightFrame" method="post" target="mainFrame">
		<s:hidden name="actionResult" id="actionResult"  value=""></s:hidden>
		<!-- 模块名称 -->
		<h2 class="page-header"><s:text name="菜单管理" /></h2>
		<!-- 工具条 -->
		<div class="row">
			 <div class="col-xs-6">
			</div>
			 <div class="col-xs-6" style="text-align: right">
				<input name="add" type="button" value="<s:text name="operation_add" />" class="btn btn-default" onclick="_add();return false;">
				<input name="delete" type="button" value="<s:text name="operation_delete" />" class="btn btn-default" onclick="_delete();return false;">
				<input name="refresh" type="button" value="<s:text name="operation_resresh" />" class="btn btn-default" onclick="_resresh();return false;">
			 </div>
		</div>
		<!-- 列表内容 -->
		<table class="table table-striped tablesorter table-hover table-condensed">
			<thead>
				<tr class="gridview_color">
				<th width="5%"><CW:checkAll childName="chk_list" name="checkAll" id="checkAll" />
				</th>
				<th><s:text name="序号" /></th>
				<th><s:text name="菜单名称" /></th>
				<th><s:text name="菜单类型" /></th>
				<th><s:text name="菜单地址" /></th>
				<th><s:text name="排序号" /></th>
				<th><s:text name="状态" /></th>
				<th><s:text name="操作" /></th>
				</tr>
			</thead>
			<tbody>
				<s:if test="listPubMenu.size()>0">
					<s:iterator value="listPubMenu" var="list" status="status"  > 
						<tr>
						<td>
						<s:if test="childCoutn!=0">
						<input type="checkbox"  name="chk_list" id="chk_list_<s:property value="#status.count" />" value="<s:property value="UUID" />" />
						</s:if>
						<s:else>
						<input type="checkbox" name="chk_list" id="chk_list_<s:property value="#status.count" />" value="<s:property value="UUID" />"/>
						</s:else>
						</td>
						<td><s:property value="#status.count" /></td>
						<td><s:property value="MenuName" /></td>
						<td><CW:dataValue property="MenuType" field="MenuType" codeName="MenuType"></CW:dataValue></td>
						<td><s:property value="MenuUrl" /></td>
						<td><s:property value="SeqNum" /></td>
						<td><CW:status url="pubmenuAjax/changeStatus.action" field="Status" ><s:hidden value="%{#list.UUID}" /></CW:status></td>
						<td>
						<div class="btn-group">
						<a class="btn btn-default dropdown-toggle btn-sm" data-toggle="dropdown" href="#" onclick="return false;">
						<s:text name="operation" />
						<span class="caret"></span>
						</a>
						<ul class="dropdown-menu pull-right">
						<li><a href="#" onclick="_detail('<s:property value="UUID" />');return false;">
						<s:text name="operation_detail" /></a> 
						</li> 
						<li><a href="#" onclick="_modify('<s:property value="UUID" />');return false;">
						<s:text name="operation_modify" /></a> 
						</li> 
						<li><a href="#" onclick="_deleteOne('<s:property value="UUID" />');return false;">
						<s:text name="operation_delete" /></a> 
						</li>
						</ul>
						</div>
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
		<!--分页条-->
		<CW:page property="page" url="pubmenu/findList.action" formName="rightFrame" target="rightFrame"></CW:page>	
	</form>
</body>
</html>
