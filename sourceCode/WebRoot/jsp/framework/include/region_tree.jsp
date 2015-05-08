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
	<script type="text/javascript" src="<%=basePath%>artDialog/artDialog.source.js?skin=twitter"></script>
	<script type="text/javascript" src="<%=basePath%>artDialog/plugins/iframeTools.source.js"></script>
</head>
<body>
	<!-- 树容器   UL-->
	<ul id="treeDemo" class="ztree"></ul>
	<!-- 业务数据：角色id串-->
	<input name="roleCache" id="roleCache" type="hidden"
		value='<s:property value="roleId"/>' />


	<script type="text/javascript">
		var treeObj;
		//-----------配置信息------------
		var setting = {
			check : { //复选框
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
					rootPId : "地区",
					enable : true
				}
			},
			callback : {//事件方法绑定   onClick点击事件   OnCheck选中事件
				onClick  : zTreeOnlick,
				onCheck  : zTreeOnCheck
			}
		};
		//-----------json数据------------
		var zNodes = <s:property value="jsonData" escape="false"/>;
		//-----------初始化树并且同时展开所有节点------------
		$(document).ready(function() {
			treeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
			treeObj.expandAll(true);
		});
		//-----------复选框的选中事件-增加业务处理rescCache 追加选中的所有节点id串------------
		function zTreeOnCheck(event, treeId, treeNode) {
			var nodes = treeObj.getCheckedNodes(true);
			var id = "";
			var kong=" ";
			var name = "";
			var fnodes = treeObj.getCheckedNodes(false);
			var num="0";
			for ( var i = 0, l = nodes.length; i < l; i++) {
				if(nodes[i].id=="fanxuan"){
				num="1";
				}
			}
			if(num=="1"){
				for ( var i = 0, l = fnodes.length; i < l; i++) {
				treeObj.checkNode(fnodes[i], true, true);
			/* 		nodes[i].checked=false;
					fnodes[i].checked=true; */
				}
				for ( var i = 0, l = nodes.length; i < l; i++) {
					treeObj.checkNode(nodes[i], false, false);
			/* 		nodes[i].checked=false;
					fnodes[i].checked=true; */
				}
				for ( var i = 0, l = fnodes.length; i < l; i++) {
				name += fnodes[i].name + ",";
				}
				if (id.length > 0) {
					id = id.substring(0, id.length - 1);
				}
				for ( var i = 0, l = fnodes.length; i < l; i++) {
				id += fnodes[i].id + ",";
				}
				if (name.length > 0) {
					name = name.substring(0, name.length - 1);
				}
			}else{
			for ( var i = 0, l = nodes.length; i < l; i++) {
				name += nodes[i].name + ",";
				}
				if (id.length > 0) {
					id = id.substring(0, id.length - 1);
				}
				for ( var i = 0, l = nodes.length; i < l; i++) {
				id += nodes[i].id + ",";
				}
				if (name.length > 0) {
					name = name.substring(0, name.length - 1);
				}
			}
		
			
			
			if(nodes.length > 0){
				art.dialog.data('checkedIds',id);
				art.dialog.data('checkedNames',name);
			}else{
				art.dialog.data('checkedIds',kong);
				art.dialog.data('checkedNames',kong);
			}
			
				//获取未选中的节点信息
			var nodesUnchecked = treeObj.getCheckedNodes(false);
			var idUnchecked  = "";
			for ( var i = 0, l = nodesUnchecked.length; i < l; i++) {
				idUnchecked  += nodesUnchecked[i].id  + ",";
			}
			if (idUnchecked.length > 0) {
				idUnchecked  = idUnchecked.substring(0, idUnchecked.length - 1);
			}
			var nameUnchecked  = "";
			for ( var i = 0, l = nodesUnchecked.length; i < l; i++) {
				nameUnchecked  += nodesUnchecked[i].name + ",";
			}
			if (nameUnchecked.length > 0) {
				nameUnchecked = nameUnchecked.substring(0, nameUnchecked.length - 1);
			}
			art.dialog.data('UncheckedIds',idUnchecked );
			art.dialog.data('UncheckedNames',nameUnchecked );
		};
		//单击事件
		function zTreeOnlick(event, treeId, treeNode) {
			var nodes = treeObj.getSelectedNodes(true);
			var id = "";
			for ( var i = 0, l = nodes.length; i < l; i++) {
				id += nodes[i].id + ",";
			}
			if (id.length > 0) {
				id = id.substring(0, id.length - 1);
			}
			art.dialog.data('checkedIds',id);
			//返回值是数组  如nodes[0].id nodes[0].name 显示的都是name值  id是隐藏的
		};
	</script>
</body>
</html>