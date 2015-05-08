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
    
    <title><s:text name="organ_list_page_title" /></title>
    
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
    <script type="text/javascript" src="<%=basePath%>artDialog/artDialog.source.js?skin=blue"></script>
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
                7:{sorter: false}
 	       }	
 		});	
	 /*显示操作结果*/
	var actionResult = $("#actionResult").val();
	if(actionResult=="success"){
		art.dialog.tips('<s:text name="list_successfuloperation" />');
	}else if(actionResult=="error"){
		art.dialog.tips('<s:text name="list_operationfailed" />');
	}
    });
    //删除
    function _deleteOne(id){
    	art.dialog.confirm('<s:text name="operation_confirm"><s:param><s:text name="operation_delete"></s:text></s:param></s:text>', 
    	function(){
    		document.srcForm.action="<%=path%>/organ/delete.action?id="+id;
    		document.srcForm.submit();
    	}, function(){
    	    art.dialog.tips('<s:text name="operation_cancel"></s:text>');
    	});
    }
    
    //批量删除
    function _deleteBatch(){
    	var ids = getCheckedValue(); 
    	if(ids==""||ids==null){
    		art.dialog.alert('<s:text name="operation_no_select"></s:text>');
    	}else{
    		art.dialog.confirm('<s:text name="operation_confirm"><s:param><s:text name="operation_delete"></s:text></s:param></s:text>', function(){
    			document.srcForm.action="<%=basePath%>organ/deleteBatch.action?ids="+ids;
    			document.srcForm.submit();
    		}, function(){
    		    art.dialog.tips('<s:text name="operation_cancel"></s:text>');
    		});
    	}
    }
      //新增
    function _add() {
    	//设置请求的url 
    	var _url = "<%=basePath%>organ/gotoAdd.action";
    	location.href=_url;
    }
    //修改
    function _modify(id) {
    	 //设置请求的url 
    	var _url = "<%=basePath%>organ/gotoModify.action?id="+id;
    	location.href=_url;
    }
   //查询一条数据
    function _detail(id){
     	//设置请求的url 
    	var _url = "<%=basePath%>organ/detail.action?id="+id;
		location.href=_url;
    }   
       
    //刷新页面
    function _resresh(){
        _reset();
    	document.srcForm.action="<%=path%>/organ/findList.action";
    	document.srcForm.submit();
    }
        //条件查询
    function _search(){
    	$("#loadingdiv").show();
    	document.srcForm.action="<%=path%>/organ/findList.action";
        document.srcForm.submit();
    }
    //重置查询条件
	function _reset(){
         $("#Cname").val("");
         $("#ShortCname").val("");
         $("#OrgCode").val("");
	}
    </script>
</head>

<body>
	<form action="organ/findList.action" name="srcForm" method="post">
		<!--操作结果  -->
		<s:hidden name="actionResult" id="actionResult"></s:hidden>
		<input type="hidden" name="organ.ParentId" id="ParentId" value="<s:property value="organ.ParentId" />">
		<!-- 模块名称 -->
		<h2 class="page-header"><s:text name="organ_list_page_title" /></h2>
		<!-- 条件查询 -->
		<div class="row">
			<div class="col-sm-4">
				<div class="form-group">
				    <label class="col-sm-6 control-label">
				    	<s:text name="organ_list_th_cname" />：
				    </label>
				    <div class="col-sm-6">
				    	<input class="form-control" type="text" name="organ.Cname" id="Cname" value="<s:property value="organ.Cname" />">
				    </div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
			      <label class="col-sm-5 control-label">
			      	<s:text name="organ_list_th_short_cname" />：
			      </label>
			      <div class="col-sm-7">
			      	<input class="form-control" type="text" name="organ.ShortCname" id="ShortCname" value="<s:property value="organ.ShortCname" />">
		    	  </div>
			   </div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
				      <label class="col-sm-5 control-label">
				      	<s:text name="organ_list_th_org_code" />：
				      </label>
				      <div class="col-sm-7">
				      	<input class="form-control" type="text" name="organ.OrgCode" id="OrgCode" value="<s:property value="organ.OrgCode" />">
			    	  </div>
			    </div>
			</div>
			<div class="col-sm-12 text-center">
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
		<table class="table table-striped tablesorter table-hover table-condensed">
			<thead>
				<tr>
					<th width="5%"><CW:checkAll childName="chk_list" name="checkAll" id="checkAll" /></th>
					<!--序号  -->
					<th width="5%"><s:text name="organ_list_th_no" /></th>
					<th><s:text name="organ_list_th_cname" /></th>
					<th><s:text name="organ_list_th_short_cname" /></th>
					<th><s:text name="organ_list_th_org_code" /></th>
					<th><s:text name="organ_list_th_createtime" /></th>
					<th><s:text name="organ_list_th_status" /></th>
					<th width="5%"><s:text name="organ_list_th_operation" /></th>
				</tr>
			</thead>
			<s:if test="page.resultList.size()>0">
			<tbody> 
				<s:iterator value="page.resultList" var="list" status="status"> 
					<tr>
						<td><input type="checkbox" name="chk_list" id="chk_list_<s:property value="#status.count" />" value="<s:property value="OrganId" />"/></td>
						<td><s:property value="#status.count" /></td>
						<td><span title="<s:property value="Cname" />"><s:property value="Cname" /></span></td>
						<td><span title="<s:property value="ShortCname" />"><s:property value="ShortCname" /></span></td>
						<td><span title="<s:property value="OrgCode" />"><s:property value="OrgCode" /></span></td>
						<td><span title="<s:date name="CreateTime" format="yyyy/MM/dd hh:mm:ss" />"><s:date name="CreateTime" format="yyyy/MM/dd hh:mm:ss" /></span></td>
						<td>
							<CW:status url="organAjax/changeStatus.action" field="Status" ><s:hidden value="%{#list.OrganId}" /></CW:status>
						</td>
						<td>
							<div class="btn-group">
								<a class="btn btn-default dropdown-toggle btn-sm" data-toggle="dropdown" href="#" onclick="return false;">
									<s:text name="operation" />
									<span class="caret"></span>
								</a>
								<ul class="dropdown-menu pull-right">
									<li><a href="#" onclick="_detail('<s:property value="OrganId" />');return false;">
										<s:text name="operation_detail" /></a> 
									</li> 
									<li><a href="#" onclick="_modify('<s:property value="OrganId" />');return false;">
										<s:text name="operation_modify" /></a> 
									</li> 
									<li><a href="#" onclick="_deleteOne('<s:property value="OrganId" />');return false;">
										<s:text name="operation_delete" /></a> 
									</li> 
								</ul>
							</div>
						</td>
					</tr>
				</s:iterator>
			</s:if>
		<tbody>
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
		<CW:page property="page" url="organ/findList.action"></CW:page>	
</form>
</body>
</html>
