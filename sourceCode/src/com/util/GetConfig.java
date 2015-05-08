
package com.util;

import java.util.ResourceBundle;


/**
 * 
* @ClassName: GetConfig
* @Description: 获取配置文件参数
* @author guangchao
* @date 2013-12-17 下午5:41:18
*
 */
public class GetConfig {
    
    
    private static boolean notLoad = false;
    
    private static String logPath=""; 
    private static String ftpPath=""; 
    private static String fileoutputpath="";
    private static String logLevel="";
    private static String logEnabled="";
    private static String templatefile="";
    private static String MetadataGeneralPath="";
    private static String FormaxECM="";
    private static String logAsynchronous="AOP";
    private static int CachePeriod = 480;
    
	static{
        init();
    }

    private static void init() {
        try{
        	String configName = "config.system_config";
        	String OSName = Util.getOSName();
        	if(!OSName.contains("Windows")){
        		configName = "config.system_config_linux";
        	}
        	ResourceBundle p = ResourceBundle.getBundle(configName);
            notLoad=true;
            logPath=p.getString("logPath");
            logAsynchronous=p.getString("logAsynchronous");
            fileoutputpath = p.getString("fileoutPath");
            logLevel = p.getString("logLevel");
            logEnabled = p.getString("logEnabled");
            ftpPath = p.getString("ftpPath");
            templatefile = p.getString("templatefile");
            MetadataGeneralPath = p.getString("MetadataGeneralPath");
            CachePeriod = Integer.valueOf(p.getString("CachePeriod"));
            FormaxECM=p.getString("FormaxECM");
            
        }catch(Exception e){
        	
        }
        
    }
    
    public static String getLogLevel() {
    	if (notLoad) {
    		init();
    	}
    	
    	return logLevel;
    }
    public static String getLogEnabled() {
    	if (notLoad) {
    		init();
    	}
    	
    	return logEnabled;
    }
    public static String getLogAsynchronous() {
    	if (notLoad) {
    		init();
    	}
    	
    	return logAsynchronous;
    }
    public static String getLogPath() {
    	if (notLoad) {
    		init();
    	}
    	
    	return logPath;
    }
    
    public static String getFileoutputpath() {
      	 if (notLoad) {
               init();
           }
   		return fileoutputpath;
   	}
    public static String getFtpPath() {
    	if (notLoad) {
    		init();
    	}
    	return ftpPath;
    }
    
    public static String getTemplatefile() {
     	 if (notLoad) {
              init();
          }
  		return templatefile;
  	}
    
    public static String getMetadataGeneralPath() {
    	 if (notLoad) {
             init();
         }
 		return MetadataGeneralPath;
 	}
    
    public static int getCachePeriod() {
   	 if (notLoad) {
            init();
        }
		return CachePeriod;
	}
    
    public static String getFormaxECM() {
   	 if (notLoad) {
            init();
        }
		return FormaxECM;
	}
}
