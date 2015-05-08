document.write("<scri"+"pt id='jquery.validation.minjs' type='text/javascript' src='./js/validate/jquery.validation.min.js'></scr"+"ipt>"); 
document.write("<scri"+"pt id='jquery.validation-additional-methodsjs' type='text/javascript' src='./js/validate/jquery.validation-additional-methods.js'></scr"+"ipt>"); 
document.write("<scri"+"pt id='messages_cnjs' type='text/javascript' src='./js/validate/messages_cn.js'></scr"+"ipt>"); 

String.prototype.trim= function(){  
    // 用正则表达式将前后空格  
    // 用空字符串替代。  
    return this.replace(/(^\s*)|(\s*$)/g, "");  
    
}
