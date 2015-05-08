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
    
    <title><s:text name="systemmanager_modify_page_title" /></title>
    
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
		//var UserId=$("#UserId").val();
			$('#addSystemmanagerForm').validate(
			{
	
				messages : {
				} ,
				rules : {
			}
		});
	});
	//修改
	function __submit(){
		if($("#addSystemmanagerForm").valid()){
			var isPreferences=$("#isPreferences").val();
			if(isPreferences==1){
				$.ajax({  
				    type: "post",  
				    async: false,
				    url: "<%=basePath%>systemmanagerAjax/selectIsPreferences.action",  
				    success: function(data){ 
				    	if(data=='true'){
							document.addSystemmanagerForm.action="<%=basePath%>systemmanager/update.action";
							document.addSystemmanagerForm.submit();
				    	}else{
				    		art.dialog.confirm('<s:text name="已有首选项，是否取消其它已选首选项？"><s:param><s:text name="确认"></s:text></s:param></s:text>', 
					    			function(){
					    				document.addSystemmanagerForm.action="<%=basePath%>systemmanager/update.action?type=1";
							    		document.addSystemmanagerForm.submit();
					    			}, function(){
					    			    art.dialog.tips('<s:text name="取消"></s:text>');
					    			});
				    	}
				    }
				});
			}else{
				document.addSystemmanagerForm.action="<%=basePath%>systemmanager/update.action";
				document.addSystemmanagerForm.submit();
			}
		 	$("#loadingdiv").hide();
		}
	}
	//返回
	function __back(){
		 //设置请求的url 
			var _url = "<%=path%>/systemmanager/findList.action";
			location.href=_url;
	}
	//上传图片
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
//选择完文件后 触发上传操作
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
 	<form action="" name="addSystemmanagerForm" id="addSystemmanagerForm" method="post">
 	<h2 class="page-header"><s:text name="systemmanager_modify_page_title" /></h2>
	<div class="form-actions text-right">  
		<input class="btn btn-primary  " name="_submit" type="button" value="<s:text name="operation_submit" />" onclick="__submit()">
		<input class="btn btn-default  " name="_back" type="button" value="<s:text name="operation_tip_cancel" />" onclick="__back()">
	</div>
	 <div class="controls">  
		 <div class="form-group col-sm-12">
		    <label class="col-sm-2 control-label text-right">
			  <s:text name="systemmanager_list_th_biglogo" />：
		    </label>
		    <div class="col-sm-4">
			    <div class="col-sm-12 text-center">
		    		<img style="width: 100px;height:100px;text-align: center;" class="img-thumbnail" alt='' src="images/untitled.png" class="writetblimg" id="imgid">
			    </div>
			    <div class="center-block">&nbsp;</div>
			    <div class="col-sm-12 text-center">
				    <div class="col-sm-6">
					    <button type="button" type='button' class='btn btn-block btn-primary ' ><s:text name="operation_upload" /></button>
					    <input name="uploadFile" type="file" class='btn btn-block ' style="filter: alpha(opacity:0);opacity: 0;margin-top:-30px;" id="uploadFile" accept="image/*" onChange="return fileupload('uploadFile');"  />
				    </div>
				    <div class="col-sm-6">
					    <button type="button" type='button' class='btn btn-block ' >清除</button>
				    </div>
			    </div>
		    </div>
		</div>
		 <div class="form-group col-sm-6">
		    <label class="col-sm-4 control-label text-right">
			  <s:text name="systemmanager_list_th_systemcode" />：
		    </label>
		    <div class="col-sm-8">
		      <input class="form-control" name="systemmanager.SystemCode" type="text" id="systemmanager.SystemCode" value="<s:property value="systemmanager.SystemCode" />">
		    </div>
		</div>
		 <div class="form-group col-sm-6">
		    <label class="col-sm-4 control-label text-right">
			<s:text name="systemmanager_list_th_fullname" />：
		    </label>
		    <div class="col-sm-8">
		    <input class="form-control" name="systemmanager.FullName" id="systemmanager.FullName" type="text" value="<s:property value="systemmanager.FullName" />">
		    </div>
		</div>
		 <div class="form-group col-sm-6">
		    <label class="col-sm-4 control-label text-right">
			<s:text name="systemmanager_list_th_shortname" />：
		    </label>
		    <div class="col-sm-8">
		    <input class="form-control" name="systemmanager.ShortName" id="systemmanager.ShortName"  type="text" value="<s:property value="systemmanager.ShortName" />">
		    </div>
		</div>
		 <div class="form-group col-sm-6">
		    <label class="col-sm-4 control-label text-right">
			<s:text name="systemmanager_list_th_systemnumber" />：
		    </label>
		    <div class="col-sm-8">
		    <input class="form-control" name="systemmanager.SystemNumber" id="systemmanager.SystemNumber" type="text" value="<s:property value="systemmanager.SystemNumber" />">
		    </div>
		</div>
		 <div class="form-group col-sm-6">
		    <label class="col-sm-4 control-label text-right">
			<s:text name="systemmanager_list_th_status" />：
		    </label>
		    <div class="col-sm-8">
		    <CW:switch name="systemmanager.Status" id="Status" type="checkbox" />
		    </div>
		</div>
		 <div class="form-group col-sm-6">
	    	<label class="col-sm-4 control-label text-right">
			首选项：
	    	</label>
	   		 <div class="col-sm-8">
	    	<CW:switch name="systemmanager.isPreferences" id="isPreferences" type="checkbox" />
	    	</div>
		</div>
		 <div class="form-group col-sm-6">
		    <label class="col-sm-4 control-label text-right">
			<s:text name="systemmanager_list_th_systemaddress" />：
		    </label>
		    <div class="col-sm-8">
		    <input class="form-control" name="systemmanager.SystemAddress" id="systemmanager.SystemAddress" type="text" value="<s:property value="systemmanager.SystemAddress" />">
		    </div>
		</div>
		 <div class="form-group col-sm-12">
		    <label class="col-sm-2 control-label text-right">
			<s:text name="systemmanager_list_th_systemremark" />：
		    </label>
		    <div class="col-sm-10">
		    <textarea class="form-control" cols="20" name="systemmanager.SystemRemark" rows="4"><s:property value="systemmanager.SystemRemark" /></textarea>
		    </div>
		</div>
	</div>
</form>
</body>
</html>
