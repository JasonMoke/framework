/**
 * @Title: BaseEntity.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.cnrsteel.orm
 * @Description: 基类。
 * @author hankaibo
 * @date 2013-12-19 下午1:21:17
 * @version V1.0
 */
package com.orm;

import java.io.Serializable;

import com.util.Util;

/**
 * @ClassName: BaseEntity
 * @Description: 基础类。我们所有实体域对象都要继承它
 * @author hankaibo
 * @date 2013-12-19 下午1:21:17
 * 
 */
public class BaseEntity implements Serializable , Cloneable {

    private static final long serialVersionUID = -2913220082793071417L;
    
    public Object clone(){  
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {      
			return null;
		}      
	} 
    
    public String getString(String param){
    	if(Util.isNotNull(param)){
    		param = param.replaceAll("'", "''");
    	}
    	return param;
    }
}