package com.orm;

/**
 * 
* @ClassName: DBType
* @Description: 数据库类型
* @author gaoguangchao
* @date 2015年2月12日 下午1:00:38
*
 */
public enum DBTYPE {
	ORACLE("oracle"), MYSQL("mysql"), SQLSERVER("sqlserver"), DEFAULT("mysql");
	 
	private final String type;
	 
	private DBTYPE(String type) {
	     this.type = type;
	}
	
	public String getType() {
	     return type;
	}
}
