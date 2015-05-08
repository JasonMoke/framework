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
<style type="text/css">
tr{
height:30px;
}
</style>
<script type="text/javascript">
$(function() {
	/*初始化radio  启用 停用  */
    $( "#radio" ).buttonset();
});
/*!
 * 
 * 绑定验证
 * 
 * 注意1：所有的验证规则统一在此处进行定义
 * 注意2：当input中name使用pentity.name这样的命名方式时,其中"."与JQUERY冲突, 则rules: {"pentity.name": {}} 要加上双引号 
 * 
 */
$(document).ready(
	function() {
	var UserId=$("#UserId").val();
		$('#addUserForm').validate(
		{
			messages : {
				"userInfo.UserName" : {
					required :  '<s:text name="tip_is_not_null"></s:text>',
					rangelength : '<s:text name="tip_rangelength"><s:param>3</s:param><s:param>32</s:param></s:text>',
					remote:'<s:text name="operation_Alreadyexists"></s:text>'
				},
				"userInfo.UserPwd" : {
					required : '<s:text name="tip_is_not_null"></s:text>',
					rangelength : '<s:text name="tip_rangelength"><s:param>6</s:param><s:param>32</s:param></s:text>'
				},
		        "userData.FullName" : {
					required : '<s:text name="tip_is_not_null"></s:text>',
		        	maxlength: '<s:text name="tip_rangelength"><s:param>1</s:param><s:param>32</s:param></s:text>'
		        } 
			} ,
			rules : {
		        "userInfo.UserName" : {
					required : true,
					rangelength:[3,32],
					/* 需要做ajax校验的字段 */
					remote: {
						
					    url: "<%=basePath%>/userAjax/validateUser.action?UserId="+UserId,     //后台处理程序
				    type: "post",               //数据发送方式
				    dataType: "json",           //接受数据格式   
				    data: {                     //要传递的数据
				        "UserName": function() {
				            return $("#UserName").val();
				        }
				    }
				}
			},
	        "userInfo.UserPwd" : {
				required : true,
				rangelength:[6,32]
			},
	        "userData.FullName" : {
				required : true,
				maxlength : 32
			},
	        "userData.EmailAddress" : {
				email : true,
				rangelength:[3,32]
			},
	        "userData.ContactPhone" : {
				number:true , 
				rangelength:[3,16]
			},
	        "userData.OfficePhone" : {
				number:true , 
				rangelength:[3,16]
			},
	        "userData.FaxNumber" : {
				number:true , 
				rangelength:[3,16]
			},
	        "userData.ContactPhone" : {
				number:true , 
				rangelength:[3,16]
			},
	        "userData.ZipCode" : {
				number:true 
			}
		}
	});
});
function _validateUser(){
	$.ajax({  
	    type: "post",  
	    async: false,
	    url: "<%=basePath%>/userValidate/validateUser.action",  
		    data: "UserName=" + $("#userInfo.UserName").val() ,  
		    success: function(data){ 
		    	var isReapeat = data[0].isReapeat;
		    	if(isReapeat=='1'){//重复
				    art.dialog.alert('该用户名已经存在，请重新输入');
		    	}else if(result=='0'){//未重复
		    		art.dialog.tips('用户名可用');
		    	}else{
		    		//art.dialog.tips('新增用户成功');
		    	}
		    }
	});
}
function __submit(){
	if($("#addUserForm").valid()){
		document.addUserForm.action="<%=basePath%>user/updateHomeUser.action";
		document.addUserForm.submit();
	}
}

	function upload(){
		over = false;
		uploading = true;
		$("#state"+uuid).html("");
		$("#noPreImg"+uuid).hide();
		$("#info"+uuid).html("");
		$("#imgid"+uuid).hide();
		$("#info"+uuid).attr("title","");
		$("#progress"+uuid).css("width","0");
		$("input[type=submit]").attr("disabled",true);
		$("#progress"+uuid).css("width","0%");
	}
//选择完文件后 出发上传操作
	function fileupload(tagName){
	//art.dialog.tips('<s:text name="solution_jpgtishi" />');
	uuid="111111111";
	//预设的支持的图片类型
	var imgFileType= "*.BMP;*.JPG;*.JPEG;*.PNG;*.GIF;*.PCX;*.TIFF;*.TGA;*.EXIF;*.FPX;*.SVG;*.PSD;*.CDR;*.PCD;*.DXF;*.UFO;*.EPS;*.HDRI;*.AI;*.RAW;";
	upload();
	 var filepath=$("#uploadFile").val(); 
				 if(filepath==undefined||$.trim(filepath)==""){ 
                       art.dialog.tips('<s:text name="user_notjpg" />');
    			  }else{ 
				       var fileArr=filepath.split("//"); 
				       var fileTArr=fileArr[fileArr.length-1].toUpperCase().split("."); 

				       var filetype=fileTArr[fileTArr.length-1];
				       //如果不为图片类型
				       if(imgFileType.indexOf(filetype) == -1){
				        art.dialog.tips('<s:text name="user_notjpgbnm" />');
				                       return false;  
				       }
				  } 
	$.ajaxFileUpload({
			url:"<%=basePath %>/SwfFileUpload",
			method:"post",
			secureuri:false,
			fileElementId : tagName,
			dataType: 'text/xml',			
			success: function (data) {
				var result = data.split("#");
				var path = result[1];//在临时文件库中的相对路径
				var wideth = result[2];//图片的宽
				var height = result[3];//图片的高
				var MD5 = result[4];//MD5值
				var fileName = result[5];//文件实际名称
				var fileSize = result[6];//文件大小，自带单位如：50M 10000M 3K 300K
				var fileType = result[7];//文件类型 如：.PNG	.TXT .DOC
				var fileSizeBytes = result[8];//文件大小 单位byte  
				if(fileSizeBytes>66000){
				art.dialog.tips('<s:text name="user_notsize" />');
				}else{
				
				var url = "<%=path%>/upload/getPriviewInpByPath.action?path="+path;
				fileName = decodeURI(fileName);
				//如果为图片类型显示分辨率
				if(imgFileType.indexOf(fileType) != -1){
					$("#imgid").attr("src",url);
					$("#imgid").show();
				}else{
					//非图片类型显示“无预览图”
					$("#noPreImg"+uuid).show();
				}
				//为path返回临时文件库中的相对路径
				$("#fileFileName").val(path);
				}
			},
			error: function (data){
			}
		}
	);
}

</script>
  </head>
  
  <body>
<form action="user/updateHomeUser.action" name="addUserForm" id="addUserForm" method="post">
<input type="hidden" name="userInfo.UserId" id="UserId"   value="<s:property value="userInfoAndData.UserId" />"/>
<!-- 页面头 -->
<input type="hidden" id="radio1"  name="userInfo.Status" value="<s:property value="userInfoAndData.Status" />">
<div class="pageheader">
	<!-- 页面标题 -->
	<div class="pagetitle">
		<s:text name="modify_user" />
	</div>
	<!-- 操作按钮 -->
	<div class="pageheadercontent">
		<div class="toolbar">
			<div class="toolbarright">
				<input class="okbtn" name="_submit" type="button" value="OK" onclick="__submit()">
			</div>
		</div>
	</div>
</div>
<!-- 编辑 -->
 <input name="fileFileName" id="fileFileName" type="hidden" />
<div class="writetblbox" style="width: 750px">
	<table class="writetbl" style="width:100%">
		<tr>
		<th style="width: 15%"><s:text name="user_add_username" />：</th>
		<td style="width: 35%">
		<input name="userInfo.UserName" id="userInfo.UserName"  readonly="readonly" type="text" value="<s:property value="userInfoAndData.UserName" />">
		</td>
		<th style="width: 15%" rowspan="4"><s:text name="user_photo" />：</th>
	 	 <td style="width: 35%"  rowspan="3">      
		    <div style="display: block:left;width:100%;height:100%;float: left;margin:0 auto;text-align: center;">
		    <div class="file-box">
		    <input style="top: 300px;position: fixed;left: 700px" type='button' class='ATTACHMENTbtn' value='<s:text name="operation_upload" />' />
		    <input style="top: 300px;position: fixed;left: 700px" name="uploadFile" type="file" class="file" id="uploadFile" accept="image/*" size="28" onChange="return fileupload('uploadFile');"  />
 	 		</div>
    	 	 <img style="width: 100px;height: 100px;text-align: center;"  id="imgid" alt="" src="<%=path%>/upload/getPriviewInpByCondition.action?conditionName=UserId&conditionKey=<s:property value="userInfoAndData.UserId" />&table=t_userdata&InputStreamName=UserLogo" class="writetblimg">
   			<p>
    	</div>
 		 </td>
		</tr>
		<tr>
		<th><s:text name="user_add_fullname" />：</th>
		<td>
		<input name="userData.FullName" id="userData.FullName" type="text" value="<s:property value="userInfoAndData.FullName" />">
		</td>
		</tr>
		<tr>
		<th><s:text name="user_add_contactphone" />：</th>
		<td>
		<input name="userData.ContactPhone" id="userData.ContactPhone" type="text" value="<s:property value="userInfoAndData.ContactPhone" />">
		</td>
		</tr>
		<tr>
		<th><s:text name="user_add_officephone" />：</th>
		<td>
		<input name="userData.OfficePhone" id="userData.OfficePhone" type="text" value="<s:property value="userInfoAndData.OfficePhone" />">
		</td>
		
		</tr>
		<tr>
		<th><s:text name="user_add_email" />：</th>
		<td>
		<input name="userData.EmailAddress" id="userData.EmailAddress" type="text" value="<s:property value="userInfoAndData.EmailAddress" />">
		</td>
		<th><s:text name="user_add_contactaddress" />：</th>
		<td>
		<input name="userData.ContactAddress" id="userData.ContactAddress" type="text" value="<s:property value="userInfoAndData.ContactAddress" />">
		</td>
		</tr>
		<tr>
		<th><s:text name="user_add_zipcode" />：</th>
		<td>
		<input name="userData.ZipCode" id="userData.ZipCode" type="text" value="<s:property value="userInfoAndData.ZipCode" />">
		</td>
		<th><s:text name="user_add_faxnumber" />：</th>
		<td>
		<input name="userData.FaxNumber" id="userData.FaxNumber" type="text" value="<s:property value="userInfoAndData.FaxNumber" />">
		</td>

		</tr>
		<tr>
		<th><s:text name="user_add_remark" />：</th>
		<td colspan="3"><textarea cols="20" name="userData.UserRemark" id="userData.UserRemark" rows="6" ><s:property value="userInfoAndData.UserRemark" /></textarea></td>
		</tr>
		</table>
</div>
    </form>
    <span class="_height"></span>
  </body>
</html>
