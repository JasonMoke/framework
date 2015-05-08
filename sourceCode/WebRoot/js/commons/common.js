document.write('<meta http-equiv="X-UA-Compatible" content="ie=edge">'); 
document.write("<link rel='stylesheet' type='text/css' href='./bootstrap/bootstrap.css'> "); 
/*document.write("<link rel='stylesheet' type='text/css' href='./bootstrap/bootstrap-theme.css'> ");*/ 
document.write("<link rel='stylesheet' type='text/css' href='./bootstrap/bootstrap-switch.css'> "); 
document.write("<link rel='stylesheet' type='text/css' href='./jquery.tablesorter/themes/blue/style.css'> "); 
/*document.write("<link rel='stylesheet' type='text/css' href='./css/jquery-ui.css'> "); */
/*document.write("<link rel='stylesheet' type='text/css' href='./bootstrap/jquery-ui-1.10.0.custom.css'> "); */
document.write("<scri"+"pt type='text/javascript' src='./js/commons/jquery-1.9.1.js'></scr"+"ipt>"); 
document.write("<scri"+"pt type='text/javascript' src='./jquery.tablesorter/jquery.tablesorter.js'></scr"+"ipt>"); 
document.write("<scri"+"pt type='text/javascript' src='./js/fileupload/ajaxfileupload.js'></scr"+"ipt>"); 
document.write("<scri"+"pt type='text/javascript' src='./js/commons/jquery.json-2.4.js'></scr"+"ipt>"); 
/*document.write("<scri"+"pt type='text/javascript' src='./js/commons/jquery-ui.js'></scr"+"ipt>");*/ 
/*document.write("<scri"+"pt type='text/javascript' src='./js/commons/jquery-ui-1.9.2.custom.min.js'></scr"+"ipt>");*/ 
document.write("<scri"+"pt type='text/javascript' src='./bootstrap/bootstrap.js'></scr"+"ipt>"); 
document.write("<scri"+"pt type='text/javascript' src='./bootstrap/bootstrap-switch.js'></scr"+"ipt>"); 
/*document.write("<scri"+"pt type='text/javascript' src='artDialog/artDialog.source.js?skin=twitter'></scr"+"ipt>"); 
document.write("<scri"+"pt type='text/javascript' src='artDialog/plugins/iframeTools.source.js'></scr"+"ipt>"); */
document.write("<scri"+"pt type='text/javascript' src='./js/commons/jquery.jqpagination.js'></scr"+"ipt>");
document.write("<scri"+"pt type='text/javascript' src='./js/validate/jquery.validate.js'></scr"+"ipt>"); 
document.write("<scri"+"pt type='text/javascript' src='./js/validate/jquery.metadata.js'></scr"+"ipt>"); 
document.write("<scri"+"pt type='text/javascript' src='./js/My97DatePicker/WdatePicker.js'></scr"+"ipt>"); 
document.write("<style type=\"text/css\">body{font-family:\"Microsoft YaHei\",微软雅黑,\"Microsoft JhengHei\",华文细黑,STHeiti,MingLiu !important}.tip-title{background-image: url(./bootstrap/images/bg.gif);background-repeat: no-repeat;background-position: center left 65;cursor: pointer;}.dropdown-menu{min-width:100px;}._height{display:block;clear:both;}.table{margin-top: 10px;}body{font-size: 14px !important;background: #fff !important;}a{text-decoration:none !important;}table{font-size: 14px !important;}.loadingdiv{position: absolute; left: 0; top: 0; background: #F1F1F1;display:none;filter: alpha(opacity=35);opacity: 0.5;font-weight: bold;     color: Red;width: 100%;height: 4000px;z-index: 3333;font-size: 14px;}.loadingdiv .child{position: absolute; visibility:visible;z-index:3332;width:100%;text-align:center;margin-top:250px;</style>"); 
document.write("<script type=\"text/javascript\">$(function(){$(document).tooltip()	});</scr"+"ipt>"); 
document.write("<script type=\"text/javascript\">$(function(){$(\"body\").after(\"<span class='_height'></span>\");});</scr"+"ipt>"); 

//根据浏览器语言 
if (window != top){
	if(window.parent.document.getElementById("locale")!=null){
		var locale = window.parent.document.getElementById("locale").value;
	}else{
		var locale = "en_US";
	}
}else{
	if(document.getElementById("locale")!=null){
		var locale = document.getElementById("locale").value;
	}else{
		var locale = "en_US";
	}
}
if(locale=='zh_CN'){
		document.write("<scri"+"pt id='messages_cnjs' type='text/javascript' src='./js/validate/messages_cn.js'></scr"+"ipt>"); 
	}else{
		document.write("<scri"+"pt id='messages_cnjs' type='text/javascript' src='./js/validate/messages_en.js'></scr"+"ipt>"); 
}
String.prototype.trim= function(){  
    // 用正则表达式将前后空格  
    // 用空字符串替代。  
    return this.replace(/(^\s*)|(\s*$)/g, "");  
    
}
//获取访问路径
var curwwwpath=window.document.location.href;
var pathname = window.document.location.pathname;    
var pos = curwwwpath.indexOf(pathname);    //获取主机地址
var localhostpath = curwwwpath.substring(0,pos);    //获取带"/"的项目名，
var projectname=pathname.substring(0,pathname.substr(1).indexOf('/')+1);
var baseUrl = localhostpath + projectname;

//动态为dilog高度宽度赋值
function _commonCloseMainFrame(){
	window.parent._closeMainFrame();
}
function getDialogHeight(){
	if(getExplorerType()=="IE")
		return document.documentElement.clientHeight;
	else
		return document.body.clientHeight;
}

 function getDialogWidth (){
	 if(getExplorerType()=="IE")
		return document.documentElement.clientWidth;
	else
		return document.body.clientWidth;
}
 
 
 
 /*!
 Math.uuid.js (v1.4)
 http://www.broofa.com
 mailto:robert@broofa.com
  
 Copyright (c) 2010 Robert Kieffer
 Dual licensed under the MIT and GPL licenses.
 */
  
 /*
  * Generate a random uuid.
  *
  * USAGE: Math.uuid(length, radix)
  *   length - the desired number of characters
  *   radix  - the number of allowable values for each character.
  *
  * EXAMPLES:
  *   // No arguments  - returns RFC4122, version 4 ID
  *   >>> Math.uuid()
  *   "92329D39-6F5C-4520-ABFC-AAB64544E172"
  *
  *   // One argument - returns ID of the specified length
  *   >>> Math.uuid(15)     // 15 character ID (default base=62)
  *   "VcydxgltxrVZSTV"
  *
  *   // Two arguments - returns ID of the specified length, and radix. (Radix must be <= 62)
  *   >>> Math.uuid(8, 2)  // 8 character ID (base=2)
  *   "01001010"
  *   >>> Math.uuid(8, 10) // 8 character ID (base=10)
  *   "47473046"
  *   >>> Math.uuid(8, 16) // 8 character ID (base=16)
  *   "098F4D35"
  */
 (function() {
   // Private array of chars to use
   var CHARS = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
  
   Math.uuid = function (len, radix) {
     var chars = CHARS, uuid = [], i;
     radix = radix || chars.length;
  
     if (len) {
       // Compact form
       for (i = 0; i < len; i++) uuid[i] = chars[0 | Math.random()*radix];
     } else {
       // rfc4122, version 4 form
       var r;
  
       // rfc4122 requires these characters
       uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
       uuid[14] = '4';
  
       // Fill in random data.  At i==19 set the high bits of clock sequence as
       // per rfc4122, sec. 4.1.5
       for (i = 0; i < 36; i++) {
         if (!uuid[i]) {
           r = 0 | Math.random()*16;
           uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
         }
       }
     }
  
     return uuid.join('');
   };
  
   // A more performant, but slightly bulkier, RFC4122v4 solution.  We boost performance
   // by minimizing calls to random()
   Math.uuidFast = function() {
     var chars = CHARS, uuid = new Array(36), rnd=0, r;
     for (var i = 0; i < 36; i++) {
       if (i==8 || i==13 ||  i==18 || i==23) {
         uuid[i] = '-';
       } else if (i==14) {
         uuid[i] = '4';
       } else {
         if (rnd <= 0x02) rnd = 0x2000000 + (Math.random()*0x1000000)|0;
         r = rnd & 0xf;
         rnd = rnd >> 4;
         uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
       }
     }
     return uuid.join('');
   };
  
   // A more compact, but less performant, RFC4122v4 solution:
   Math.uuidCompact = function() {
     return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
       var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
       return v.toString(16);
     });
   };
 })();
 
 
 
//获取浏览器版本
 var userAgent = navigator.userAgent,   
 rMsie = /(msie\s|trident.*rv:)([\w.]+)/,   
 rFirefox = /(firefox)\/([\w.]+)/,   
 rOpera = /(opera).+version\/([\w.]+)/,   
 rChrome = /(chrome)\/([\w.]+)/,   
 rSafari = /version\/([\w.]+).*(safari)/;  
 var browser;  
 var version;  
 var ua = userAgent.toLowerCase();  
 function uaMatch(ua) {  
     var match = rMsie.exec(ua);  
     if (match != null) {  
         return { browser : "IE", version : match[2] || "0" };  
     }  
     var match = rFirefox.exec(ua);  
     if (match != null) {  
         return { browser : match[1] || "", version : match[2] || "0" };  
     }  
     var match = rOpera.exec(ua);  
     if (match != null) {  
         return { browser : match[1] || "", version : match[2] || "0" };  
     }  
     var match = rChrome.exec(ua);  
     if (match != null) {  
         return { browser : match[1] || "", version : match[2] || "0" };  
     }  
     var match = rSafari.exec(ua);  
     if (match != null) {  
         return { browser : match[2] || "", version : match[1] || "0" };  
     }  
     if (match != null) {  
         return { browser : "", version : "0" };  
     }  
 }  
 var browserMatch = uaMatch(userAgent.toLowerCase());  
 if (browserMatch.browser) {  
     browser = browserMatch.browser;  
     version = browserMatch.version;  
 }
 //document.write(browser+version);
 function getExplorerType(){
 	return browser;
 }
 function getExplorerVersion(){
 	return version;
 }
