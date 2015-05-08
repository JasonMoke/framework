package com.core.framework.mybatis.support;

/**
 * 
* @ClassName: CustomerContextHolder
* @Description: 多数据源
* @author gaoguangchao
* @date 2015年2月12日 下午1:19:58
*
 */
public abstract class CustomerContextHolder {
 
    public final static String SESSION_FACTORY_MYSQL = "mysql";
    public final static String SESSION_FACTORY_ORACLE = "oracle";
    public final static String SESSION_FACTORY_SQLSERVER = "sqlserver";
    
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();  
    
    public static void setContextType(String contextType) {  
        contextHolder.set(contextType);  
    }  
      
    public static String getContextType() {  
        return contextHolder.get();  
    }  
      
    public static void clearContextType() {  
        contextHolder.remove();  
    }  
}