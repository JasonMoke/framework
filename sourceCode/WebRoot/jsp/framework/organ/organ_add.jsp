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
    
    <title><s:text name="organ_add_page_title" /></title>
    
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
    /*
    * 绑定验证
    * 注意1：所有的验证规则统一在此处进行定义
    * 注意2：当input中name使用pentity.name这样的命名方式时,其中"."与JQUERY冲突, 则rules: {"pentity.name": {}} 要加上双引号 
    */
    $(document).ready(
    	function() {
            $('#addForm').validate({
                rules : {
                    "organ.Cname" : {required : true, rangelength : [1,254], maxlength : 254},
                    "organ.ShortCname" : {required : false, rangelength : [1,100], maxlength : 100},
                    "organ.OrgCode" : {required : false, rangelength : [1,254], maxlength : 254},
                    "organ.Enname" : {required : false, rangelength : [1,254], maxlength : 254},
                    "organ.OrgGrade" : {required : false, rangelength : [1,20], maxlength : 20},
                    "organ.ParentId" : {required : true, rangelength : [1,64], maxlength : 64},
                    "organ.OrgPhone" : {required : false, rangelength : [1,21], maxlength : 21,number : true},
                    "organ.OrgEmail" : {required : false, rangelength : [1,50], maxlength : 50,email:true },
                    "organ.OrgDoorNum" : {required : false, rangelength : [1,100], maxlength : 100,number : true},
                    "organ.SeqNum" : {required : true, number : true, maxlength : 8},
                    "organ.Memo" : {required : false, rangelength : [1,254], maxlength : 254},
                    "organ.RespPerson" : {required : false, rangelength : [1,64], maxlength : 64},
                    "organ.LinkMan" : {required : false, rangelength : [1,200], maxlength : 200},
            }
    	});
    });
        
    function __back(){
	 	var _url = "<%=path%>/organ/findList.action";
		location.href=_url;
	}
    function __submit(){
        if($("#addForm").valid()){
    		$("#loadingdiv").show();
    		document.addForm.action="<%=basePath%>organ/insert.action";
    		document.addForm.submit();
            }
	}
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
<form action="organ/insert.action" name="addForm" id="addForm" method="post" target="mainFrame">
<h2 class="page-header"><s:text name="add_organ" /></h2>
<div class="form-actions text-right">  
	<input class="btn btn-primary  " name="_submit" type="button" value="<s:text name="operation_submit" />" onclick="__submit()">
	<input class="btn btn-default  " name="_back" type="button" value="<s:text name="operation_tip_cancel" />" onclick="__back()">
</div>
 <p>
 <div class="controls">  
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_cname" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" name="organ.Cname" type="text" id="Cname">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_short_cname" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" name="organ.ShortCname" type="text" id="ShortCname">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_enname" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" name="organ.Enname" type="text" id="Enname">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_org_code" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" name="organ.OrgCode" type="text" id="OrgCode">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_resp_person" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" name="organ.RespPerson" type="text" id="RespPerson">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_link_man" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" name="organ.LinkMan" type="text" id="LinkMan">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_org_phone" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" name="organ.OrgPhone" type="text" id="OrgPhone">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_org_email" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" name="organ.OrgEmail" type="text" id="OrgEmail">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_org_door_num" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" name="organ.OrgDoorNum" type="text" id="OrgDoorNum">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_seq_num" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" name="organ.SeqNum" type="text" id="SeqNum" value="1">
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
    	<input class="form-control" name="organ.OrgGrade" type="text" id="OrgGrade">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	   <s:text name="organ_add_parent_id" />：
    </label>
     <div class="col-sm-8" >
	     <div class="input-group">
	      <input class="form-control" type="text" id="ParentIdName" onclick="_organTree()">
	      <div class="input-group-addon"  onclick="_organTree()">+</div>
	    </div>
    	<input name="organ.ParentId" type="hidden" id="ParentId">
    </div>
</div>
 <div class="form-group col-sm-12">
	    <label class="col-sm-2 control-label text-right">
		  <s:text name="organ_add_memo" />：
	    </label>
	    <div class="col-sm-10">
	    <textarea class="form-control" cols="20" name="organ.Memo" rows="4"></textarea>
	    </div>
</div>
</div>
</form>
<div id="loadingdiv" class="loadingdiv">
<center>
<div class="child">
<img src="images/loading.gif" style="width:160px" />
</div>
</center>
</div>
</body>
</html>
