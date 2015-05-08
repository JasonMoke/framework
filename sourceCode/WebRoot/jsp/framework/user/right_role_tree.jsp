<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>模块树视图</title>
<!-- 追加ztree的js资源-->
<script type="text/javascript"
	src="<%=basePath%>js/ztree/js/jquery-1.7.2.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>js/ztree/css/zTreeStyle/zTreeStyle.css">
<!--artDialog-->
<script type="text/javascript"
	src="<%=basePath%>artDialog/artDialog.source.js?skin=twitter"></script>
<script type="text/javascript"
	src="<%=basePath%>artDialog/plugins/iframeTools.source.js"></script>
</head>
<body>
	<form action="<%=path%>/user/selRole.action" name="selUserRoleForm"
		id="selUserRoleForm" method="post"  target="mainFrame">
		<!-- 树容器   UL-->
		<ul id="treeDemo" class="ztree"></ul>
		<input type="hidden" name="UserId" id="UserId" value='<s:property value="UserId"/>' /> 
		<input type="hidden" name="RoleId" id="RoleId"  value='<s:property value="RoleId"/>' />
	</form>
	<script type="text/javascript">
		var treeObj;
		//-----------配置信息------------
		var setting = {
			view: {
				dblClickExpand: false,
				showLine: false
			},	
			check : {
				enable : true,
				chkboxType : {
					"Y" : "ps",
					"N" : "sp"
				}
			},
			data : {
				simpleData : {
					idKey : "id",
					pIdKey : "pid",
					rootPId : "角色",
					enable : true
				}
			},
			callback : {//事件方法绑定   onClick点击事件   OnCheck选中事件
					onCheck : zTreeOnCheck,
					onClick : zTreeOnlick
			}
		};
		//-----------json数据------------
		var zNodes = <s:property value="jsonData" escape="false"/>;
		//-----------初始化树并且同时展开所有节点------------
		$(document).ready(function() {
			treeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
			treeObj.expandAll(true);
		});
	function zTreeOnCheck(event, treeId, treeNode) {
			var nodes = treeObj.getCheckedNodes(true);
			var id = "";
			for ( var i = 0, l = nodes.length; i < l; i++) {
				id += nodes[i].id + ",";
			}
			if (id.length > 0) {
				id = id.substring(0, id.length - 1);
			}
			$("#RoleId").val("");
			$("#RoleId").val(id);
		};
				//单击事件
		function zTreeOnlick(event, treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            zTree.checkNode(treeNode, !treeNode.checked, null, true);
		};
	</script>
	
</body>
</html>