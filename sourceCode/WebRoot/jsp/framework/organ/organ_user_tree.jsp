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
<h3 class="page-header">组织人员</h3>
	<form action="<%=path%>/authorize/SelResourcesAndModule.action" name="selResourcesForm"
		id="selResourcesForm" method="post"  target="mainFrame">
		<!-- 树容器   UL-->
		
		<ul id="treeDemo" class="ztree"></ul>

		<input type="hidden" name="UserId" id="UserId" value='' />
	</form>
	<script type="text/javascript">
		var treeObj;
		
		//-----------配置信息------------
		var setting = {
			async:{
				enable:true,
				url:"<%=basePath%>/treeJSON/organUserTree.action",
       			autoParam:["id"]
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
			var UserId = "";
			for ( var i = 0, l = nodes.length; i < l; i++) {
				UserId += nodes[i].id + ",";
			}
			if (UserId.length > 0) {
				UserId = UserId.substring(0, UserId.length - 1);
			}
			$("#UserId").val("");
			$("#UserId").val(UserId);
		};
		//单击事件
		function zTreeOnlick(event, treeId, treeNode) {
		   treeObj=$.fn.zTree.getZTreeObj("treeDemo");
		   	var nodes = treeObj.getSelectedNodes(true);
			var UserId = nodes[0].id;
			var UserName = nodes[0].name;
			//var pid = nodes[0].pid;
			parent.rightFrame.location = "<%=request.getContextPath() %>/tree/roleGroupRoleTree.action?UserId="+UserId+"&UserName="+UserName;
			//返回值是数组  如nodes[0].id nodes[0].name 显示的都是name值  id是隐藏的
		};
		 $(document).ready(function(){
            //异步提交表单
             $.ajax({ 
	             type: "POST",       
	             dataType: "json", 
                 url: '<%=basePath%>/treeJSON/organUserTree.action',
                 data:{
                	 },
                 success: function(data) {
				 zNodes=eval("("+data.jsonData+")");
                 $.fn.zTree.init($("#treeDemo"), setting, zNodes);//实例化后直接返回树对象
                }
            });
        });
	</script>

</body>
</html>