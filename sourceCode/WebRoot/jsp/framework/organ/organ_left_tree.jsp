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
<title>组织机构树视图</title>
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
<style type="text/css">
 .ztree li span.button.switch.level0 {visibility:hidden; width:1px;}
.ztree li ul.level0 {padding:0; background:none;} 
</style>
</head>
<body>
	<form action="" name=""
		id="" method="post"  target="mainFrame">
		<!-- 树容器   UL-->
		<ul id="treeDemo" class="ztree"></ul>
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
				enable : false
			},
			data : {
				simpleData : {
					idKey : "id",
					pIdKey : "pid",
					rootPId : "组织",
					enable : true
				}
			},
			callback : {//事件方法绑定   onClick点击事件   OnCheck选中事件
					onClick : zTreeOnlick
			}
		};
		//-----------json数据------------
		var zNodes = <s:property value="jsonData" escape="false"/>;
		var rootNode = { id:'0', pId:'-1', name:'组织', open:true,iconSkin:"home"};
		zNodes.push(rootNode);
		
		//-----------初始化树并且同时展开所有节点------------
		$(document).ready(function() {
			treeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
			//treeObj.expandAll(false);
			var nodes = treeObj.getSelectedNodes();
			for(var i=0;i<nodes.length;i++){
				if(nodes[i].level==0){
					treeObj.expandNode(nodes[i], true, true, true);
				}
			}
		});
		function zTreeOnlick(event, treeId, treeNode) {
			var nodes = treeObj.getSelectedNodes(true);
			var id = "";
			for ( var i = 0, l = nodes.length; i < l; i++) {
				id += nodes[i].id + ",";
			}
			if (id.length > 0) {
				id = id.substring(0, id.length - 1);
			}
			var cid = nodes[0].id;
			//var pid = nodes[0].pid;
			parent.rightFrame.location = "<%=request.getContextPath() %>/organ/findList.action?ParentId="+cid;
			};
	</script>
	
</body>
</html>