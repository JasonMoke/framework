<%@page import="com.framework.entity.user.UserInfoAndDataManager"%>
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
    
    <title><s:text name="user_list_page_title" /></title>
    
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
	<script type="text/javascript" src="<%=basePath %>js/distpicker/distpicker.data.js"></script>
<script type="text/javascript" src="<%=basePath %>js/distpicker/distpicker.js"></script>
<style type="text/css">
tr{
height:30px;
}
</style>	
<script type="text/javascript">
$(function() {
   	/*初始化默认显示的至，或者默认选中的值*/
  	$(".distpicker1").distpicker({
     	  province:"---所在省---",
     	  city:"---所在市---",
     	 });
});
	function __back(){
		document.addUserForm.action="<%=path%>/user/getAllUser.action?isBack=1";
		document.addUserForm.submit();
	}
</script>
  </head>
  <body>
    <form action="user/getAllUser.action" name="addUserForm" id="addUserForm" method="post" >
    
	<!-- 页面标题 -->
	<h2 class="page-header"><s:text name="detail_user" /></h2>
	<!-- 操作按钮 -->
	<div class="form-actions text-right">  
		<input class="btn btn-default  " name="_back" type="button" value="<s:text name="operation_tip_cancel" />" onclick="__back()">
	</div>	
<!-- 编辑 -->
<div class="controls">  
<h3 class="page-header">基本信息</h3>
<div class="row"> 
	<div class="form-group col-sm-6">
		<label class="col-sm-4 control-label text-right">
		  <s:text name="user_photo" />：
	    </label>
	    <div class="col-sm-8">
	    <div class="col-sm-12 text-center">
    		<img style="width: 100px;height:100px;text-align: center;" class="img-thumbnail" alt='' src="images/untitled.png" class="writetblimg" id="imgid">
	    </div>
	    <div class="center-block">&nbsp;</div>
    </div>
	</div>
	<div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="user_add_fullname" />：
    </label>
    <div class="col-sm-8">
      <input class="form-control" name="userData.FullName" type="text" id="userData.FullName" value="<s:property value="userInfoAndData.FullName" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="user_add_enname" />：
    </label>
    <div class="col-sm-8">
      <input class="form-control" name="userData.EnName" type="text" id="userData.EnName" value="<s:property value="userInfoAndData.EnName" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="last_name" />：
    </label>
    <div class="col-sm-8">
      <input class="form-control" name="userData.LastName" type="text" id="userData.LastName" value="<s:property value="userInfoAndData.LastName" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="first_name" />：
    </label>
    <div class="col-sm-8">
      <input class="form-control" name="userData.FirstName" type="text" id="userData.FirstName" value="<s:property value="userInfoAndData.FirstName" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="cname_short_spell" />：
    </label>
    <div class="col-sm-8">
      <input class="form-control" name="userData.CnameShortSpell" type="text" id="userData.CnameShortSpell" value="<s:property value="userInfoAndData.CnameShortSpell" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="cname_full_spell" />：
    </label>
    <div class="col-sm-8">
      <input class="form-control" name="userData.CnameFullSpell" type="text" id="userData.CnameFullSpell" value="<s:property value="userInfoAndData.CnameFullSpell" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="seq_num" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" type="text" name="userData.SeqNum" id="userData.SeqNum" value="<s:property value="userInfoAndData.SeqNum" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="sex" />：
    </label>
    <div class="col-sm-8">
    <CW:select name="userData.Sex" id="Sex" codeName="Sex" className="form-control" isCode="true" field="Sex" property="userInfoAndData.Sex" > 
	<CW:option value=""><s:text name="user_add_select"/></CW:option>
	</CW:select>
    </div>
</div>
<div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="birthday" />：
    </label>
    <div class="col-sm-8">
    <input name="userData.Birthday" id="userData.Birthday" type="text" class="Wdate form-control" onclick="WdatePicker();" />
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="nation" />：
    </label>
    <div class="col-sm-8">
	    <CW:select name="userData.Nation" className="form-control" id="Nation" codeName="Nation" isCode="true" field="Nation" property="userInfoAndDate.Nation"> 
			<CW:option value=""><s:text name="user_add_select"/></CW:option>
		</CW:select>
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="native_piace" />：
    </label>
    <div class="col-sm-8">
		<input class="form-control" type="text" name="userData.NativePiace" id="userData.NativePiace" value="<s:property value="userInfoAndData.NativePiace" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="politic_code" />：
    </label>
    <div class="col-sm-8">
    	<CW:select className="form-control" name="userData.PoliticCode" id="PoliticCode" codeName="PoliticCode" isCode="true" field="PoliticCode" property="userInfoAndData.PoliticCode"> 
		<CW:option value=""><s:text name="user_add_select"/></CW:option>
		</CW:select>
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="marry_code" />：
    </label>
    <div class="col-sm-8">
		<CW:select className="form-control" name="userData.MarryCode" id="MarryCode" codeName="MarryCode" isCode="true" field="MarryCode" property="userInfoAndData.MarryCode"> 
		<CW:option value=""><s:text name="user_add_select"/></CW:option>
		</CW:select>
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="country" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" type="text" name="userData.Country" id="userData.Country" value="<s:property value="userInfoAndData.Country" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="user_add_zipcode" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" type="text" name="userData.ZipCode" id="userData.ZipCode" value="<s:property value="userInfoAndData.ZipCode" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="provinceAndcity" />：
    </label>
    <div class="col-sm-8">
    	<div class="distpicker1">
		    <select style="width: 45%" name="userData.ProvinceId" id="userData.ProvinceId"></select>
		    <select style="width: 45%" name="userData.CityId" id="userData.CityId"></select>
		</div>
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="card_num" />：
    </label>
    <div class="col-sm-8">
        <input class="form-control" type="text" name="userData.CardNum" id="userData.CardNum" value="<s:property value="userInfoAndData.CardNum" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="card_code" />：
    </label>
    <div class="col-sm-8">
    	<CW:select className="form-control"  name="userData.CardCode" id="CardCode" codeName="CardCode" isCode="true" field="CardCode" property="userInfoAndData.CardCode"> 
		<CW:option value=""><s:text name="user_add_select"/></CW:option>
		</CW:select>
    </div>
</div>
</div>
<h3 class="page-header"><s:text name="educateInfo" /></h3>
<div class="row">  
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="edu_code" />：
    </label>
    <div class="col-sm-8">
    	<CW:select className="form-control" name="userData.EduCode" id="EduCode" codeName="EduCode" isCode="true" field="EduCode" property="userInfoAndData.EduCode" > 
		<CW:option value=""><s:text name="user_add_select"/></CW:option>
		</CW:select>
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="degree_code" />：
    </label>
    <div class="col-sm-8">
    	<CW:select className="form-control" name="userData.DegreeCode" id="DegreeCode" codeName="DegreeCode" isCode="true" field="DegreeCode" property="userInfoAndData.DegreeCode" > 
		<CW:option value=""><s:text name="user_add_select"/></CW:option>
		</CW:select>
    </div>
</div>
</div>
</div>
<h3 class="page-header"><s:text name="workInfo" /></h3>
<div class="row">  
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="person_code" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" type="text" name="userData.PersonCode"	id="userData.PersonCode"  value="<s:property value="userInfoAndData.PersonCode" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="room_rum" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" type="text" name="userData.RoomNum"	id="userData.RoomNum"  value="<s:property value="userInfoAndData.RoomNum" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="security_level" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" type="text" name="userData.SecurityLevel"	id="userData.SecurityLevel"  value="<s:property value="userInfoAndData.SecurityLevel" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="resp_dept" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" type="text" name="userData.OrganId"	id="userData.OrganId"  value="<s:property value="userInfoAndData.OrganId" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="user_add_status" />：
    </label>
    <div class="col-sm-8">
    	<CW:switch name="userInfo.Status" id="Status" type="checkbox" checked="true"/>
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="is_imp_contact" />：
    </label>
    <div class="col-sm-8">
    	<CW:switch name="userData.IsImpContact" id="IsImpContact" type="checkbox" checked="true"/>
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="postlevel" />：
    </label>
    <div class="col-sm-8">
    	<CW:select className="form-control" name="userData.PostLevel" id="PostLevel" codeName="PostLevel" isCode="true" field="PostLevel" property="userInfoAndData.PostLevel" > 
		<CW:option value=""><s:text name="user_add_select"/></CW:option>
		</CW:select>
    </div>
</div>
</div>
<h3 class="page-header"><s:text name="modify_user" /></h3>
<div class="row">  
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="user_add_contactphone" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" type="text" name="userData.ContactPhone" id="userData.ContactPhone"  value="<s:property value="userInfoAndData.ContactPhone" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="user_add_contactaddress" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" type="text" name="userData.ContactAddress" id="userData.ContactAddress"  value="<s:property value="userInfoAndData.ContactAddress" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="user_add_officephone" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" type="text" name="userData.OfficePhone" id="userData.OfficePhone"  value="<s:property value="userInfoAndData.OfficePhone" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="user_add_faxnumber" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" type="text" name="userData.FaxNumber" id="userData.FaxNumber"  value="<s:property value="userInfoAndData.FaxNumber" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="home_tel" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" type="text" name="userData.HomeTel" id="userData.HomeTel"  value="<s:property value="userInfoAndData.HomeTel" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="home_fax" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" type="text" name="userData.HomeFax" id="userData.HomeFax"  value="<s:property value="userInfoAndData.HomeFax" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="msn" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" type="text" name="userData.MSN" id="userData.MSN"  value="<s:property value="userInfoAndData.MSN" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="qq" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" type="text" name="userData.QQ" id="userData.QQ"  value="<s:property value="userInfoAndData.QQ" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="user_add_email" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" type="text" name="userData.EmailAddress" id="userData.EmailAddress"  value="<s:property value="userInfoAndData.EmailAddress" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="home_page" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" type="text" name="userData.HomePage" id="userData.HomePage"  value="<s:property value="userInfoAndData.HomePage" />">
    </div>
</div>
 <div class="form-group col-sm-6">
    <label class="col-sm-4 control-label text-right">
	  <s:text name="signature" />：
    </label>
    <div class="col-sm-8">
    	<input class="form-control" type="text" name="userData.Signature" id="userData.Signature"  value="<s:property value="userInfoAndData.Signature" />">
    </div>
</div>
</div>
<h3 class="page-header"><s:text name="otherInfo" /></h3>
<div class="row">  
 <div class="form-group col-sm-12">
	    <label class="col-sm-2 control-label text-right">
		  <s:text name="otherInfo" />：
	    </label>
	    <div class="col-sm-10">
			<textarea class="form-control" cols="20" name="userData.OtherInfo" rows="4"><s:property value="userInfoAndData.OtherInfo" /></textarea>
		</div>
</div>
 <div class="form-group col-sm-12">
	    <label class="col-sm-2 control-label text-right">
		  <s:text name="user_add_remark" />：
	    </label>
	    <div class="col-sm-10">
			<textarea class="form-control" cols="20" name="userData.UserRemark" rows="4"><s:property value="userInfoAndData.UserRemark" /></textarea>
		</div>
</div>
</div>
    </form>
  </body>
</html>
