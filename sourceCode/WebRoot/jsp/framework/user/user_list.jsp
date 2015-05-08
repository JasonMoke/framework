
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
    
    <title><s:text name="user_list_page_title" /></title>
    
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
	 
	 $(".tablesorter").tablesorter({
	 
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
	 
	 });

});
//删除
function _deleteOne(id){
	art.dialog.confirm('<s:text name="operation_confirm"><s:param><s:text name="operation_delete"></s:text></s:param></s:text>', 
	function(){
		document.srcForm.action="<%=path%>/user/deleteUser.action?UserId="+id;
		document.srcForm.submit();
	}, function(){
	    art.dialog.tips('<s:text name="operation_cancel"></s:text>');
	});
}

//批量删除用户
function _delete(){
	var ids = getCheckedValue(); 
	if(ids==""||ids==null){
		art.dialog.alert('<s:text name="operation_no_select"></s:text>');
	}else{
		art.dialog.confirm('<s:text name="operation_confirm"><s:param><s:text name="operation_delete"></s:text></s:param></s:text>', 
		function(){
			document.srcForm.action="<%=basePath%>user/deleteUser.action?UserId="+ids;
			document.srcForm.submit();
		}, function(){
		    art.dialog.tips('<s:text name="operation_cancel"></s:text>');
		});
	}
}
//新增
function _add() {
	 //设置请求的url 
	var _url = "<%=basePath%>user/toAddUser.action";
	document.srcForm.action=_url;
	document.srcForm.submit();
}
//修改用户
function _modify(UserId) {
	 //设置请求的url 
	var _url = "<%=basePath%>user/modifyUser.action?UserId="+UserId;
	document.srcForm.action=_url;
	document.srcForm.submit();
}
//查询一条数据
function _detail(UserId){
 	//设置请求的url 
	var _url = "<%=basePath%>user/detailUser.action?UserId="+UserId;
	document.srcForm.action=_url;
	document.srcForm.submit();
}
//刷新页面
function _resresh(){
	$("#UserName").val("");
	$("#FullName").val("");
	$("#Status").val("");
	document.srcForm.action="<%=path%>/user/getAllUser.action";
	document.srcForm.submit();
}
//重置密码  进行判断 如果有账号才能重置 没有账号不能重置
function _reserPwd(UserId,Account){
	if(Account==""){
		art.dialog.tips("该用户没有账号，不能重置密码！");
	}else{
		art.dialog.confirm('<s:text name="operation_resetpasswordok"></s:text>', function(){
			$.ajax({  
				    type: "post",  
				    async: false,
				    url: "<%=basePath%>/userAjax/resetPwd.action",  
				    data: "UserId=" + UserId +"&UserPwd=1234567890",  
				    success: function(data){ 
				    	if(data=='true'){
						    art.dialog.tips('<s:text name="operation_resetpassword"></s:text>:"1234567890"');
				    	}else{
				    		art.dialog.tips('<s:text name="list_operationfailed"></s:text>');
				    	}
				    }
			});
		}, function(){
		    art.dialog.tips('<s:text name="operation_cancel"></s:text>');
		});
	}
}
/* 选择角色 */
function _roleSel(name) {
	//设置请求的url 
	var _url = "<%=basePath%>/tree/RoleTree.action?UserId="+name;
	//调用art.dialog组件弹出窗口 采用iframe方式
	art.dialog.open(_url,
		{ 
		 	lock: true,//背景锁定
	    	background: '#BFBFBF', // 背景色
	    	opacity: 0.5,	// 透明度
			title : '选择角色',
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
				var form = iframe.document.getElementById("selUserRoleForm");
				form.submit();
			},
			cancel : true
	}); 
}
//分配账号
function _AccountSel(UserId){
	location.href="<%=path%>/user/toUserAccount.action?UserId="+UserId;
}
//注销账号||删除账号
function _logoutAccount(UserId,tip){
	art.dialog.confirm('<s:text name="operation_confirm"><s:param></s:param></s:text>', 
	function(){
		document.srcForm.action="<%=path%>/user/logoutAccount.action?UserId="+UserId+"&tip="+tip;
		document.srcForm.submit();
	}, function(){
	    art.dialog.tips('<s:text name="operation_cancel"></s:text>');
	});
	
}

//人员调动
function _organSel(name){
	//设置请求的url 
	var _url = "<%=basePath%>/tree/selOrganTree.action?UserId="+name;
	//调用art.dialog组件弹出窗口 采用iframe方式
	art.dialog.open(_url,
		{ 
		 	lock: true,//背景锁定
	    	background: '#BFBFBF', // 背景色
	    	opacity: 0.5,	// 透明度
			title : '人员调动',
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
				var form = iframe.document.getElementById("selUserOrganForm");
				form.submit();
			},
			cancel : true
	}); 
}
//带条件查询
function _search(){
	var username = $("#UserName").val();
	var fullname = $("#FullName").val();
	var status = $("#Status").val();
	var page = $("#pageNum").val();
	document.srcForm.action="<%=path%>/user/getUserByCondition.action?UserName="+username+"&FullName="+encodeURI(fullname)+"&Status="+status+"&pageNo="+page;
	document.srcForm.submit();
}
function _export(){
    window.location.href="<%=path%>/springUser/exportUser.do";
}

	function _reset(){
	$("#UserName").val("");
	$("#FullName").val("");
	$("#Status").val("");
}
</script>
  </head>
<body>
<form action="user/getAllUser.action" name="srcForm" id="UserListForm" method="post">
<s:hidden name="actionResult" id="actionResult"  ></s:hidden>
<!-- 模块名称 -->
<h2 class="page-header"><s:text name="user_list_page_title" /></h2>
<!-- 条件查询 -->
<div class="row">
<div class="col-sm-4">
	<div class="form-group">
	    <label class="col-sm-4 control-label">
	    <s:text name="user_list_th_username" />：
	    </label>
	    <div class="col-sm-8">
	    <input type="text" class="form-control" name="userInfoAndData.UserName" id="UserName" value="<s:property value="userInfoAndData.UserName" />">
	    </div>
	</div>
</div>
<div class="col-sm-4">
<div class="form-group">
      <label class="col-sm-4 control-label">
      <s:text name="user_add_fullname" />：
      </label>
      <div class="col-sm-8">
      <input type="text" class="form-control" name="userInfoAndData.FullName" id="FullName" value="<s:property value="userInfoAndData.FullName" />">
    </div>
   </div>
</div>
<div class="col-sm-4">
<div class="form-group">
      <label class="col-sm-4 control-label">
      <s:text name="user_list_th_userstatus" />：
      </label>
      <div class="col-sm-8">
     	<select name="userInfoAndData.Status" id="Status" class="form-control">
		<option value=""></option>
		  <s:if test="userInfoAndData.Status==0">
		  <option value="0" selected="selected"><s:text name="user_list_td_userstatus_disable" /></option>
		 </s:if>
		 <s:else>
		   <option value="0"><s:text name="user_list_td_userstatus_disable" /></option>
		 </s:else>
		  <s:if test="userInfoAndData.Status==1">
		 <option value="1" selected="selected"><s:text name="user_list_td_userstatus_enable" /></option>
		 </s:if>
		 <s:else>
		  <option value="1" ><s:text name="user_list_td_userstatus_enable" /></option>
		 </s:else>
		</select>
     </div>
   </div>
</div>
<div class="col-sm-12" style="text-align: center;">
	<input type="button" class="btn  btn-primary" value="<s:text name="operation_search" />" onclick="_search()">
	<input type="button" class="btn  btn-primary" value="<s:text name="operation_reset" />" onclick="_reset()">
</div>
</div>
<!-- 工具条 -->
<div class="row">
 <div class="col-xs-6">
</div>
 <div class="col-xs-6" style="text-align: right">
	<input name="add" type="button" value="<s:text name="operation_add" />" class="btn btn-default" onclick="_add();return false;">
	<input name="delete" type="button" value="<s:text name="operation_delete" />" class="btn btn-default" onclick="_delete();return false;">
	<input name="refresh" type="button" value="<s:text name="operation_resresh" />" class="btn btn-default" onclick="_resresh();return false;">
	<input name="refresh" type="button" value="导出" class="btn btn-default" onclick="_export();return false;">
 </div>
</div>
<!-- 列表内容 -->
<table class="table table-striped tablesorter table-hover table-condensed">
<thead>
<tr>
<th width="5%"><CW:checkAll childName="chk_list" name="checkAll" id="checkAll" />
</th>
<th width="5%"><s:text name="user_list_th_no" /></th>
<th><s:text name="user_list_th_username" /></th>
<th><s:text name="user_add_fullname" /></th>
<th><s:text name="user_list_th_creater" /></th>
<th><s:text name="user_list_th_createtime" /></th>
<th><s:text name="user_list_th_userstatus" /></th>
<th width="12%"><s:text name="user_list_th_operation" /></th>
</tr>
</thead>
<tbody>
<s:if test="list.size()>0">
<s:iterator value="list" var="list" status="status"  > 
<s:if test='InfoStatus=="0"'>
<tr title="账户已注销" class="danger">
</s:if>
<s:else>
<tr>
</s:else>
<td><input type="checkbox" name="chk_list" id="chk_list_<s:property value="#status.count" />" value="<s:property value="UserId" />"/>
</td>
<td><s:property value="#status.count" /></td>
<td><span title="<s:property value="Account" />"><s:property value="Account" /></span></td>
<td><span title="<s:property value="FullName" />"><s:property value="FullName" /></span></td>
<td><CW:dataValue property="CreatePerson" field="CreatePerson" person="true"></CW:dataValue></td>
<td><span title="<s:date name="CreateTime" format="yyyy/MM/dd hh:mm:ss" />"><s:date name="CreateTime" format="yyyy/MM/dd hh:mm:ss" /></span></td>
<td>
<CW:status url="userAjax/changeStatus.action" field="Status" ><s:hidden value="%{#list.UserId}" /></CW:status>
</td>
<td>
<div class="btn-group">
<a class="btn btn-default dropdown-toggle btn-sm" data-toggle="dropdown" href="#" onclick="return false;">
<s:text name="operation" />
<span class="caret"></span>
</a>
<ul class="dropdown-menu pull-right">
<li><a href="#" onclick="_detail('<s:property value="UserId" />');return false;">
<s:text name="operation_detail" /></a> 
</li> 
<li><a href="#" onclick="_modify('<s:property value="UserId" />');return false;">
<s:text name="operation_modify" /></a> 
</li> 
<li><a href="#" onclick="_deleteOne('<s:property value="UserId" />');return false;">
<s:text name="operation_delete" /></a> 
</li> 
<li><a href="#" onclick="_reserPwd('<s:property value="UserId" />','<s:property value="Account" />');return false;"><s:text name="operation_resetpassword" /></a>
</li>
<li><a href="#" onclick="_AccountSel('<s:property value="UserId" />');return false;">分配账号</a>
</li>
<li>
<s:if test='InfoStatus==null||InfoStatus=="0"'>
<a href="javascript:void(0)">注销账号</a>
</s:if>
<s:else>
<a href="#" onclick="_logoutAccount('<s:property value="UserId" />','logout');return false;">注销账号</a>
</s:else>
</li>
<li>
<s:if test='InfoStatus==null'>
<a href="javascript:void(0)">删除账号</a>
</s:if>
<s:else>
<a href="#" onclick="_logoutAccount('<s:property value="UserId" />','del');return false;">删除账号</a>
</s:else>
</li>
<li><a href="#" onclick="_organSel('<s:property value="UserId" />');return false;">人员调动</a>
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
<CW:page property="page" url="user/getAllUser.action"></CW:page>		
</form>
  </body>
</html>
