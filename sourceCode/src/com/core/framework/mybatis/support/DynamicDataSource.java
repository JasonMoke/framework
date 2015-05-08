package com.core.framework.mybatis.support;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 
* @ClassName: DynamicDataSource
* @Description: 
* @author gaoguangchao
* @date 2015年2月12日 下午1:20:23
*
 */
public class DynamicDataSource extends AbstractRoutingDataSource{  
	  
    @Override  
    protected Object determineCurrentLookupKey() {  
        return CustomerContextHolder.getContextType();  
    }  
    
}  