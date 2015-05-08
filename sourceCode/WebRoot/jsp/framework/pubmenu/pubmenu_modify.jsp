<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--引用CW标签  -->
<%@ taglib uri="/WEB-INF/taglib/cw" prefix="CW" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title><s:text name="pubmenu_modify_page_title" /></title>
    
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/jquery-ui.css"> 
	<link href="<%=basePath%>css/en/edit.css" rel="stylesheet" type="text/css">
	<link href="<%=basePath%>css/en/list.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=basePath%>js/commons/common.js"></script>
	<script type="text/javascript" src="<%=basePath%>artDialog/artDialog.source.js?skin=twitter"></script>
	<script type="text/javascript" src="<%=basePath%>artDialog/plugins/iframeTools.source.js"></script>
<style type="text/css">
tr{
height:30px;
}
</style>
<script type="text/javascript">
	$(document).ready(
		function() {
			_initType();
			$('#addpubmenuForm').validate(
			{
				rules : {
				      "pubmenu.MenuName" : {
							required : true,
							rangelength:[2,32]
						},
						 "pubmenu.SeqNum" : {
								required : true,
								number : true,
								maxlength : 32
							},
						 "pubmenu.MenuType" : {
								required : true
							}
			}
		});
	});
	//修改
	function __submit(){
	if($("#addpubmenuForm").valid()){
		var NavId = $("#NavId").val(); //应用导航Id
		var isPreferences=$("#isPreferences").val();
		var PID= $("#PID").val();
			$.ajax({  
			    type: "post",  
			    async: false,
			    url: "<%=basePath%>pubmenuAjax/selectIsPreferences.action?PID="+PID+"&NavId="+NavId,  
			    success: function(data){ 
			    	if(data=='0'){
			    		if(isPreferences==0){
			    			alert("必须有一个为首选项");
			    		}else{
			    			document.addpubmenuForm.action="<%=basePath%>pubmenu/update.action";
				    		document.addpubmenuForm.submit();
			    		}
			    	}else{
			    			art.dialog.confirm('<s:text name="已有首选项，是否更改首选项？"><s:param><s:text name="确认"></s:text></s:param></s:text>', 
			    			function(){
			    				document.addpubmenuForm.action="<%=basePath%>pubmenu/update.action?type=1";
					    		document.addpubmenuForm.submit();
			    			}, function(){
			    			    art.dialog.tips('<s:text name="取消"></s:text>');
			    			});
			    	}
			    }
			});
		}
	}
	//返回
	function __back(){
		 //设置请求的url 
		 var NavId = $("#NavId").val(); //应用导航Id
			var _url = "<%=path%>/pubmenu/findAllList.action?NavId="+NavId;
			location.href=_url;
	}
	
	//初始化菜单类型
	function _initType(){
		/* 1:模块菜单   2：资源菜单 3：外部菜单 */
		var types= $("#MenuType").val();
		if(types==1){
			$("#moduleUrl").show();
			$("#resourceUrl").hide();
		}else if(types==2){
			$("#moduleUrl").hide();
			$("#resourceUrl").show();
		}else if(types==3){
			$("#moduleUrl").hide();
			$("#resourceUrl").hide();
		}
	}
	
	//显示所选类型的
	function _changeType(){
		/* 1:模块菜单   2：资源菜单 3：外部菜单 */
		var types= $("#MenuType").val();
		if(types==1){
			$("#moduleUrl").show();
			$("#resourceUrl").hide();
			$("#MenuUrl").val("");
		}else if(types==2){
			$("#moduleUrl").hide();
			$("#resourceUrl").show();
			$("#MenuUrl").val("");
		}else if(types==3){
			$("#moduleUrl").hide();
			$("#resourceUrl").hide();
			$("#MenuUrl").val("");
		}
	}
	
	
	//选择模块
	function _selectModule(){
		var id = $("#ModuleId").val();
		//设置请求的url 
		var _url = "<%=basePath%>/tree/moduleApplicationTree.action?checkedId="+id+"&type=menu";
		//调用art.dialog组件弹出窗口 采用iframe方式
		art.dialog.open(_url,
			{ 
			 	lock: true,//背景锁定
		    	background: '#BFBFBF', // 背景色
		    	opacity: 0.5,	// 透明度
				title : '选择模块',
				width: '400px',
				height: '400px',
				drag : false,//禁止拖动
				resize: false,//禁止改变大小
				ok : function() {
					var iframe = this.iframe.contentWindow;
					if (!iframe.document.body) {
						/* 页面没有加载完毕！ */
						alert('页面没有加载完毕！');
						return false;
					}
					var checkedIds = art.dialog.data('checkedIds');
					var checkedName = art.dialog.data('checkedNames');
					var checkedUrl = art.dialog.data('checkedUrl');
					$("#ModuleId").val(checkedIds);
					$("#ModuleName").val(checkedName);
					$("#MenuUrl").val(checkedUrl);
				},
				cancel : true
		}); 
	}
	//选择资源
	function _selectResource(){
		var id = $("#ResourcesId").val();
		//设置请求的url 
		var _url = "<%=basePath%>/tree/resourcesTree.action?checkedId="+id+"&type=menu";
		//调用art.dialog组件弹出窗口 采用iframe方式
		art.dialog.open(_url,
			{ 
			 	lock: true,//背景锁定
		    	background: '#BFBFBF', // 背景色
		    	opacity: 0.5,	// 透明度
				title : '选择资源',
				width: '400px',
				height: '400px',
				drag : false,//禁止拖动
				resize: false,//禁止改变大小
				ok : function() {
					var iframe = this.iframe.contentWindow;
					if (!iframe.document.body) {
						/* 页面没有加载完毕！ */
						alert('页面没有加载完毕！');
						return false;
					}
					var checkedIds = art.dialog.data('checkedIds');
					var checkedName = art.dialog.data('checkedNames');
					var checkedUrl = art.dialog.data('checkedUrl');
					$("#ResourcesId").val(checkedIds);
					$("#ResourcesName").val(checkedName);
					$("#MenuUrl").val(checkedUrl);
				},
				cancel : true
		}); 
	}
	//选择上级
	function _selectPid(){
		var NavId = $("#NavId").val(); //应用导航Id
		var id = $("#PID").val();
		//设置请求的url 
		var _url = "<%=basePath%>/tree/pumenuTree.action?checkedId="+id+"&NavId="+NavId;
		//调用art.dialog组件弹出窗口 采用iframe方式
		art.dialog.open(_url,
			{ 
			 	lock: true,//背景锁定
		    	background: '#BFBFBF', // 背景色
		    	opacity: 0.5,	// 透明度
				title : '选择上级',
				width: '400px',
				height: '400px',
				drag : false,//禁止拖动
				resize: false,//禁止改变大小
				ok : function() {
					var iframe = this.iframe.contentWindow;
					if (!iframe.document.body) {
						/* 页面没有加载完毕！ */
						alert('页面没有加载完毕！');
						return false;
					}
					var checkedIds = art.dialog.data('checkedIds');
					var checkedName = art.dialog.data('checkedNames');
					$("#PID").val(checkedIds);
					$("#PIDName").val(checkedName);
				},
				cancel : true
		}); 
	}

</script>
  </head>
  
  <body>
 	<form action="" name="addpubmenuForm" id="addpubmenuForm" method="post">
 	<input type="hidden" id="NavId" name="pubmenu.NavId" value="<s:property value="pubmenu.NavId"/>">
 	<input type="hidden" name="pubmenu.UUID" value="<s:property value="pubmenu.UUID"/>">
 	<h2 class="page-header"><s:text name="修改" /></h2>
	<div class="form-actions text-right">  
		<input class="btn btn-primary  " name="_submit" type="button" value="<s:text name="operation_submit" />" onclick="__submit()">
		<input class="btn btn-default  " name="_back" type="button" value="<s:text name="operation_tip_cancel" />" onclick="__back()">
	</div>
	 <div class="controls">  
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="菜单名称" />：
    </label>
    <div class="col-sm-8">
      <input class="form-control" name="pubmenu.MenuName" type="text" id="pubmenu.MenuName"  value="<s:property value="pubmenu.MenuName"/>">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	<s:text name="上级" />：
    </label>
    <div class="col-sm-8">
    <input class="form-control" name="pubmenu.PID" id="PID"  type="hidden" value="<s:property value="pubmenu.PID"/>">
    <input class="form-control" name="PIDName" id="PIDName"  type="text" onclick="_selectPid()" value="<s:property value="pubmenu.PIDName"/>">
    </div>
</div>

<div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	<s:text name="帮助文档路径" />：
    </label>
    <div class="col-sm-8">
    <input class="form-control" name="pubmenu.HelpFilePath" id="pubmenu.HelpFilePath" type="text" value="<s:property value="pubmenu.HelpFilePath"/>">
    </div>
</div>
<div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	<s:text name="排序号" />
    </label>
    <div class="col-sm-8">
    <input class="form-control" name="pubmenu.SeqNum" id="pubmenu.SeqNum" type="text" value="<s:property value="pubmenu.SeqNum"/>">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	<s:text name="状态" />：
    </label>
    <div class="col-sm-8">
    <CW:switch name="pubmenu.Status" id="Status" type="checkbox" checked="true"/>
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
    <s:text name="是否打开新页面" />：
    </label>
    <div class="col-sm-8">
    <CW:switch name="pubmenu.Target" id="Target" type="checkbox" checked="true" />
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	<s:text name="是否默认入口" />：
    </label>
    <div class="col-sm-8">
    <CW:switch name="pubmenu.IsMenuEntr" id="IsMenuEntr" type="checkbox" checked="true"/>
    </div>
</div>
<div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	<s:text name="是否首选项" />：
    </label>
    <div class="col-sm-8">
    <CW:switch name="pubmenu.isPreferences" id="isPreferences" type="checkbox" checked="true"/>
    </div>
</div>
<div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	<s:text name="菜单类型" />：
    </label>
    <div class="col-sm-8">
    <CW:select onchange="_changeType()" className="form-control" name="pubmenu.MenuType" id="MenuType" codeName="MenuType" isCode="true" field="MenuType" property="pubmenu.MenuType"> 
		<CW:option value=""><s:text name="--菜单类型--"/></CW:option>
		</CW:select>
    </div>
</div>
<div id="moduleUrl" class="form-group col-sm-6" style="display: none">
    <label class="col-sm-4 control-label text-right">
	<s:text name="模块路径" />：
    </label>
    <div class="input-group col-sm-7 col-sm-offset-7" >
    <input class="form-control" name="pubmenu.ModuleId" id="ModuleId" type="hidden" value="<s:property value="pubmenu.ModuleId"/>">
    <input class="form-control" name="ModuleName" id="ModuleName" type="text" onclick="_selectModule()" value="<s:property value="pubmenu.ModuleName"/>">
    <span class="input-group-addon" onclick="_selectModule()">+</span>
    </div>
</div>
<div id="resourceUrl" class="form-group col-sm-6" style="display: none">
    <label class="col-sm-4 control-label text-right">
	<s:text name="资源路径" />：
    </label>
   <div class="input-group col-sm-7 col-sm-offset-7" >
    <input class="form-control" name="pubmenu.ResourcesId" id="ResourcesId" type="hidden" value="<s:property value="pubmenu.ResourcesId"/>">
    <input class="form-control" name="ResourcesName" id="ResourcesName" type="text" onclick="_selectResource()" value="<s:property value="pubmenu.ResourcesName"/>">
    <span class="input-group-addon" onclick="_selectResource()">+</span>
    </div>
</div>
<div class="form-group col-sm-6" >
    <label class="col-sm-4 control-label text-right">
	<s:text name="菜单路径" />：
    </label>
    <div class="col-sm-8">
    <input class="form-control" name="pubmenu.MenuUrl" id="MenuUrl" type="text" value="<s:property value="pubmenu.MenuUrl"/>">
    </div>
</div>
</div>
</form>
</body>
</html>
