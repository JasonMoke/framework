<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html >
<html>
<head>
<title>角色树视图</title>
	<!-- 追加ztree的js资源-->
	<script type="text/javascript" src="<%=basePath%>js/ztree/js/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/ztree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/ztree/js/jquery.ztree.excheck-3.5.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/ztree/css/zTreeStyle/zTreeStyle.css"> 
</head>
<body>
<!-- 树容器   UL-->
<ul id="treeDemo" class="ztree"></ul>

<!-- 业务数据：角色id串-->
<input name="roleCache" id="roleCache" type="hidden" value='<s:property value="roleId"/>' />


<script type="text/javascript" >
    var  treeObj;
    //-----------配置信息------------
    var setting = {
			check: {
				enable: true,
				chkboxType: { "Y": "ps", "N": "sp" }
			},
			data: {
				simpleData: {
					idKey: "id",
					pIdKey: "pid",
					rootPId: "0",				
					enable: true
				}
			},
			callback: {
				onCheck: zTreeOnCheck
			}			
    };
    //-----------json数据------------
	var zNodes=<s:property value="jsonData" escape="false"/>;
	//-----------初始化树并且同时展开所有节点------------
	$(document).ready(function(){
	
	    treeObj=$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	    treeObj.expandAll(true);
	});
	//-----------复选框的选中事件-增加业务处理rescCache 追加选中的所有节点id串------------
	function zTreeOnCheck(event, treeId, treeNode) {
	   var nodes = treeObj.getCheckedNodes(true);
	   var id = "";
	   for (var i=0, l=nodes.length; i<l; i++) {
		  id += nodes[i].id+",";
	   }
	   if (id.length > 0 ) {
		  id = id.substring(0, id.length-1);
	   }
	   $("#roleCache").val(id);
	};	
</script>
</body>
</html>