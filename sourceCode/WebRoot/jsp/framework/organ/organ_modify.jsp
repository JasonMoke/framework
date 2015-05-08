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
    
    <title><s:text name="user_list_page_title" /></title>

    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <meta http-equiv="description" content="This is my page">
	 <!--通用js  -->
    <script type="text/javascript" src="<%=basePath%>js/commons/common.js"></script>
    <!--artDialog-->
	<script type="text/javascript" src="<%=basePath%>artDialog/artDialog.source.js?skin=twitter"></script>
	<script type="text/javascript" src="<%=basePath%>artDialog/plugins/iframeTools.source.js"></script>
        <script type="text/javascript">
    $(document).ready(
    	function() {
            $('#updateForm').validate({
                rules : {
                    "organ.Cname" : {required : false, rangelength : [3,254], maxlength : 254},
                    "organ.ShortCname" : {required : true, rangelength : [3,100], maxlength : 100},
                    "organ.OrgCode" : {required : true, rangelength : [3,254], maxlength : 254},
                    "organ.Enname" : {required : true, rangelength : [3,254], maxlength : 254},
                    "organ.OrgGrade" : {required : true, rangelength : [3,20], maxlength : 20},
                    "organ.ParentId" : {required : false, rangelength : [3,64], maxlength : 64},
                    "organ.OrgPhone" : {required : true, number:true ,rangelength : [3,21], maxlength : 21},
                    "organ.OrgEmail" : {required : true, email : true,rangelength : [3,50], maxlength : 50},
                    "organ.OrgDoorNum" : {required : true, number:true ,rangelength : [3,100], maxlength : 100},
                    "organ.SeqNum" : {required : false, number:true ,number : true, maxlength : 8},
                    "organ.Memo" : {required : true, rangelength : [3,254], maxlength : 254},
                    "organ.RespPerson" : {required : true, rangelength : [3,64], maxlength : 64},
                    "organ.LinkMan" : {required : true, rangelength : [3,200], maxlength : 200},
                    "organ.IsReserved" : {required : true, rangelength : [3,2], maxlength : 2}
            }
    	});
    });
      //返回  
    function __back(){
	 //设置请求的url 
		var _url = "<%=path%>/organ/findList.action";
		location.href=_url;
	}
    //修改
    function __submit(){
      	 if(!$("#updateForm").valid()){
    		$("#loadingdiv").show();
    		document.updateForm.action="<%=basePath%>organ/update.action";
    		document.updateForm.submit();
    	} 
     }
    //组织树
    function _organTree() {
    	var checkedId = $("#ParentId").val();
    	//设置请求的url 
    	var _url = "<%=basePath%>/tree/selOrganTree.action?checkedId="+checkedId;
    	//调用art.dialog组件弹出窗口 采用iframe方式
    	art.dialog.open(_url,
    		{ 
    		 	lock: true,//背景锁定
    	    	background: '#BFBFBF', // 背景色
    	    	opacity: 0.5,	// 透明度
    			title : '选择组织',
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
    				var checkedNames = art.dialog.data('checkedNames');
    				$("#ParentId").val(checkedIds);
    				$("#ParentIdName").val(checkedNames);
    			},
    			cancel : true
    	}); 
    }
    </script>
</head>

<body>
<!-- target属性指向index.jsp的iframe -->
<form action="organ/update.action" name="updateForm" id="updateForm" method="post" target="mainFrame">
<input type="hidden" name="organ.OrganId" id="organ.OrganId" value="<s:property value="organ.OrganId" />"/>
<h2 class="page-header"><s:text name="modify_organ" /></h2>
<div class="form-actions text-right">  
	<input class="btn btn-primary  " name="_submit" type="button" value="<s:text name="operation_submit" />" onclick="__submit()">
	<input class="btn btn-default  " name="_back" type="button" value="<s:text name="operation_tip_cancel" />" onclick="__back()">
</div>
 
 <div class="controls">  
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_cname" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" name="organ.Cname" type="text" id="Cname" value="<s:property value="organ.Cname" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_short_cname" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" name="organ.ShortCname" type="text" id="ShortCname" value="<s:property value="organ.ShortCname" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_enname" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" name="organ.Enname" type="text" id="Enname" value="<s:property value="organ.Enname" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_org_code" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" name="organ.OrgCode" type="text" id="OrgCode" value="<s:property value="organ.OrgCode" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_resp_person" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" name="organ.RespPerson" type="text" id="RespPerson" value="<s:property value="organ.RespPerson" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_link_man" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" name="organ.LinkMan" type="text" id="LinkMan" value="<s:property value="organ.LinkMan" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_org_phone" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" name="organ.OrgPhone" type="text" id="OrgPhone" value="<s:property value="organ.OrgPhone" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_org_email" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" name="organ.OrgEmail" type="text" id="OrgEmail" value="<s:property value="organ.OrgEmail" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_org_door_num" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" name="organ.OrgDoorNum" type="text" id="OrgDoorNum" value="<s:property value="organ.OrgDoorNum" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_seq_num" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" name="organ.SeqNum" type="text" id="SeqNum" value="<s:property value="organ.SeqNum" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_status" />：
    </label>
    <div class="col-sm-8">
    <CW:switch name="organ.Status" id="Status" type="checkbox" checked="true"/>
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_is_reserved" />：
    </label>
    <div class="col-sm-8">
    <CW:switch name="organ.IsReserved" id="IsReserved" type="checkbox" checked="true"/>
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_org_grade" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" name="organ.OrgGrade" type="text" id="OrgGrade" value="<s:property value="organ.OrgGrade" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_parent_id" />：
    </label>
     	<div class="input-group col-sm-7 col-sm-offset-7" >
    	<input class="form-control" name="organ.ParentId" type="hidden" id="ParentId" value="<s:property value="organ.ParentId" />">
    	<input class="form-control" type="text" id="ParentIdName" onclick="_organTree()" value="<s:property value="organs.Cname"/>">
    	 <span class="input-group-addon" onclick="_organTree()">+</span>
    </div>
</div>
 <div class="form-group col-sm-12">
	    <label class="col-sm-2 control-label text-right">
		  <s:text name="organ_add_memo" />：
	    </label>
	    <div class="col-sm-10">
	    <textarea class="form-control" cols="20" name="organ.Memo" rows="4"><s:property value="organ.Memo" /></textarea>
	    </div>
</div>
</div>
</form>
</body>
</html>
