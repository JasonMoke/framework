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
<script type="text/javascript"
	src="<%=basePath%>artDialog/artDialog.source.js?skin=twitter"></script>
<script type="text/javascript"
	src="<%=basePath%>artDialog/plugins/iframeTools.source.js"></script>
</head>
<body>
	<form action="<%=path%>/role/selRight.action" name="selRoleForm"
		id="selRoleForm" method="post"  target="mainFrame">
		<!-- 树容器   UL-->
		<ul id="treeDemo" class="ztree"></ul>

		<input type="hidden" name="RoleName" id="RoleName"  /> 
		<input type="hidden" name="ModuleID" id="ModuleID" value='<s:property value="ModuleID"/>' />
	</form>
	<script type="text/javascript">
		var treeObj;
		var RoleName = art.dialog.data('name');
		$("#RoleName").val(RoleName);
		
		//-----------配置信息------------
		var setting = {
			async:{
				enable:true,
				url:"<%=basePath%>/treeJSON/rightModuleTree.action?RoleName="+RoleName,
       			autoParam:["id","pid"]
			},
			view: {
				selectedMulti: false,  
				dblClickExpand: false,
				showLine: false
			},	
			check : {
				enable : true,
				chkboxType : {
					"Y" : "ps",
					"N" : "sp"
				},
			},
			data : {
				simpleData : {
					/* idKey : "id",
					pIdKey : "pid",
					rootPId : "格式", */
					enable : true
				}
			},
			callback : {
				onCheck : zTreeOnCheck,
				onClick : zTreeOnlick
			}
		};

		//-----------复选框的选中事件-增加业务处理rescCache 追加选中的所有节点id串------------
		function zTreeOnCheck(event, treeId, treeNode) {
		     treeObj=$.fn.zTree.getZTreeObj("treeDemo");
			var nodes = treeObj.getCheckedNodes(true);
			var id = "";
			for ( var i = 0, l = nodes.length; i < l; i++) {
				id += nodes[i].id + ",";
			}
			if (id.length > 0) {
				id = id.substring(0, id.length - 1);
			}
			$("#ModuleID").val("");
			$("#ModuleID").val(id);
		};
		//单击事件
		function zTreeOnlick(event, treeId, treeNode) {
		   treeObj=$.fn.zTree.getZTreeObj("treeDemo");
			treeObj.expandNode(treeNode);
			//返回值是数组  如nodes[0].id nodes[0].name 显示的都是name值  id是隐藏的
		};
		 $(document).ready(function(){
            //异步提交表单
             $.ajax({ 
	             type: "POST",       
	             dataType: "json", 
                 url: '<%=basePath%>/treeJSON/rightModuleTree.action?RoleName='+RoleName,
                 data:{
                	 },
                 success: function(data) {
				 zNodes=eval("("+data.jsonData+")");
				 $("#ModuleID").val(data[0].treevalue);
                 $.fn.zTree.init($("#treeDemo"), setting, zNodes);//实例化后直接返回树对象
                }
            });
        });
	</script>

</body>
</html>