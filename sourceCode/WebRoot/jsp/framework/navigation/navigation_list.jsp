<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--引用CW标签  -->
<%@ taglib prefix="CW" uri="/WEB-INF/taglib/cw" %>
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
    
    <title>navigation_list</title>
    
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

	<!--通用js  -->
	<script type="text/javascript" src="<%=basePath%>js/commons/common.js"></script>
	<!--artDialog-->
	<script type="text/javascript" src="<%=basePath%>artDialog/artDialog.source.js?skin=twitter"></script>
	<script type="text/javascript" src="<%=basePath%>artDialog/plugins/iframeTools.source.js"></script>
    <script type="text/javascript">
	$(document).ready(function() {
    	//页面排序
 	    $(".tablesorter").tablesorter({
 		  headers:{ 
 	          0:{sorter: false},	
 	          1:{sorter: 'digit'}, 
 	          2:{sorter: 'text'}, 
 	          3:{sorter: 'text'}, 
 	          4:{sorter: 'text'}, 
 	          5:{sorter: 'text'}, 
 	          6:{sorter: 'text'},
 	          7:{sorter: false},
 	          8:{sorter: false}
 	       }	
 		});
	        
		 /*显示操作结果*/
		var actionResult = $("#actionResult").val();
		if(actionResult=="success"){
			art.dialog.tips('操作成功');
		}else if(actionResult=="error"){
			art.dialog.tips('操作失败');
		}
	  	});
	  	
	    //删除
	    function _deleteOne(id){
	    	art.dialog.confirm('确定执行删除操作？', 
	    	function(){
	    		document.srcForm.action="<%=path%>/navigation/delete.action?id="+id;
	    		document.srcForm.submit();
	    	}, function(){
	    	    art.dialog.tips('取消了操作');
	    	});
	    }
	    
	    //批量删除
	    function _deleteBatch(){
	    	var ids = getCheckedValue(); 
	    	if(ids==""||ids==null){
	    		art.dialog.alert('<s:text name="operation_no_select"></s:text>');
	    	}else{
	    		art.dialog.confirm('确定执行删除操作？', function(){
	    			document.srcForm.action="<%=basePath%>navigation/deleteBatch.action?ids="+ids;
	    			document.srcForm.submit();
	    		}, function(){
	    		    art.dialog.tips('取消了操作');
	    		});
	    	}
	    }
	      //新增
	    function _add() {
	    	//设置请求的url 
	    	var _url = "<%=basePath%>navigation/gotoAdd.action";
	        document.srcForm.action=_url;
	        document.srcForm.submit();
	    	
	    }
	    //修改
	    function _modify(id) {
	    	 //设置请求的url 
	    	var _url = "<%=basePath%>navigation/gotoModify.action?id="+id;
	        document.srcForm.action=_url;
	        document.srcForm.submit();
	    	
	    }
	   //查询一条数据
	    function _detail(id){
	     	//设置请求的url 
	    	var _url = "<%=basePath%>navigation/detail.action?id="+id;
	        document.srcForm.action=_url;
	        document.srcForm.submit();
			
	    }   
	       
	    //刷新页面
	    function _resresh(){
	        _reset();
	    	document.srcForm.action="<%=path%>/navigation/findList.action";
	    	document.srcForm.submit();
	    }
	    
	    //更改状态
	    function _changeStatus(obj){
	    	var Id = obj.id;
	    	var Status = obj.name;
	    	var Name;
	    	var css = "";
	    	if(Status==1){
	    		css="statusEnabled";
	    		Name = "0";
	    	}else{
	    		css = "statusDisabled";
	    		Name = "1";
	    	}
	    	$.ajax({  
	    	    type: "post",  
	    	    async: false,
	    	    url: "<%=basePath%>/navigationAjax/changeStatus.action",  
	    	    data: "id=" + Id +"&status="+Status,  
	    	    success: function(data){ 
	    	    	if(data=='true'){
	    			    art.dialog.tips("操作成功");
	    			    $("#"+UserId).attr("class",css);
	    			    $("#"+UserId).attr("name",Name);
	    	    	}else{
	    	    		art.dialog.tips('取消了操作');
	    	    	}
	    	    }
	    	});
	    }
	        //条件查询
	    function _search(){
	    	$("#loadingdiv").show();
	    	document.srcForm.action="<%=path%>/navigation/findList.action";
	        document.srcForm.submit();
	    }
	    //重置查询条件
	    function _reset(){
	           $("#SystemCode").val("");
	           $("#NavName").val("");
	           $("#NavUrl").val("");
	    }
	    //查看下级
	    function _seeSubordinate(id){
	    	$("#loadingdiv").show();
	    	document.srcForm.action="<%=path%>/pubmenu/findAllList.action?NavId="+id;
	        document.srcForm.submit();
	    }
    </script>
</head>

<body id="body">
<form action="navigation/findList.action" name="srcForm" method="post">
<!--操作结果  -->
<s:hidden name="actionResult" id="actionResult"></s:hidden>
<!-- 模块名称 -->
<h2 class="page-header"><s:text name="navigation_list_page_title" /></h2>
<!-- 操作提示 -->
<div class="panel panel-default">
	<div class="panel-heading">
		<h4 class="panel-title tip-title">
			<a data-toggle="collapse" data-parent="#accordion" href="#collapse-content" >
			操作提示
			</a>
		</h4>
	</div>
	<div id="collapse-content" class="panel-collapse collapse">
		<div class="panel-body">
		注册到Framework中的应用、模块、资源等，在界面上可以统一展现，展现管理就是设置这些应用、模块、资源如何在界面上显示；可以为应用创建应用导航栏，将应用下的模块和资源生成对应的菜单，菜单可以在整个窗口的顶部或者左侧显示；可以直接增加一个菜单，菜单指向一个未在Framework中注册的资源，比如一个外部URL，该类菜单不会受权限控制
		</div>
	</div>
</div>
<!-- 条件查询 -->
<div class="row">
<div class="col-sm-4">
	<div class="form-group">
	    <label class="col-sm-4 control-label">
	    <s:text name="navigation_list_th_systemcode" />：
	    </label>
	    <div class="col-sm-8">
	    <input type="text" class="form-control" name="navigation.SystemCode" id="SystemCode" value="<s:property value="navigation.SystemCode" />">
	    </div>
	</div>
</div>
<div class="col-sm-4">
<div class="form-group">
      <label class="col-sm-4 control-label">
      <s:text name="navigation_list_th_navname" />：
      </label>
      <div class="col-sm-8">
      <input type="text" class="form-control" name="navigation.NavName" id="NavName" value="<s:property value="navigation.NavName" />">
    </div>
   </div>
</div>
<div class="col-sm-4">
<div class="form-group">
      <label class="col-sm-4 control-label">
      <s:text name="navigation_list_th_navurl" />：
      </label>
      <div class="col-sm-8">
      <input type="text" class="form-control" name="navigation.NavUrl" id="NavUrl" value="<s:property value="navigation.NavUrl" />">
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
	<input name="delete" type="button" value="<s:text name="operation_delete" />" class="btn btn-default" onclick="_deleteBatch();return false;">
	<input name="refresh" type="button" value="<s:text name="operation_resresh" />" class="btn btn-default" onclick="_resresh();return false;">
 </div>
</div>
    <!--数据表单  -->
    <table class="tablesorter table table-striped table-hover table-condensed">
    <thead>
	<tr>
	<th width="5%"><CW:checkAll childName="chk_list" name="checkAll" id="checkAll" /></th>
	<th width="5%"><s:text name="navigation_list_th_seqnum" /></th>
	<th><s:text name="navigation_list_th_navname" /></th>
	<th><s:text name="navigation_list_th_navurl" /></th>
	<th><s:text name="navigation_list_th_systemcode" /></th>
	<th><s:text name="navigation_list_th_creater" /></th>
	<th><s:text name="navigation_list_th_createtime" /></th>
	<th width="5%"><s:text name="navigation_list_th_status" /></th>
	<th width="10%"><s:text name="navigation_list_th_operation" /></th>
	</tr>  
	</thead>
	<tbody>  
	<s:if test="page.resultList.size()>0">
	<s:iterator value="page.resultList" var="list" status="status"> 
	<tr>
	<td><input type="checkbox" name="chk_list" id="chk_list_<s:property value="#status.count" />" value="<s:property value="UUID" />"/></td>
	<td><s:property value="#status.count" /></td>
	<td><span title="<s:property value="NavName" />"><s:property value="NavName" /></span></td>
	<td><span title="<s:property value="NavUrl" />"><s:property value="NavUrl" /></span></td>
	<td><span title="<s:property value="SystemCode" />"><s:property value="SystemCode" /></span></td>
	<td><CW:dataValue field="CreatePerson" property="CreatePerson" person="true"></CW:dataValue></td>
	<td><span title="<s:date name="CreateTime" format="yyyy/MM/dd HH:mm:ss" />"><s:date name="CreateTime" format="yyyy/MM/dd HH:mm:ss" /></span></td>
	<td><CW:status url="/navigationAjax/changeStatus.action" field="Status" ><s:hidden value="%{#list.UUID}" /></CW:status>
	</td>
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
	<li><a href="#" onclick="_seeSubordinate('<s:property value="UUID" />');return false;">
	<s:text name="查看菜单" /></a> 
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
<CW:page property="page" url="navigation/findList.action"></CW:page>
</form>
<CW:load/>
</body>
</html>
