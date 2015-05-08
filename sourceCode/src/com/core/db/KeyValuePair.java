package com.core.db;

/**
 * @ClassName: KeyValuePair 
 * @Description: 键值对
 * @author:  tanyi
 * @date: 2014-01-16 14:11:04
 */ 
public class KeyValuePair {
	
	private Object Key;
	private Object Value;
	
	/*
	 * @Description: 获得键
	 */
	public Object getKey() {
		return Key;
	}
	
	/*
	 * @Description: 设置键
	 */
	public void setKey(Object Key) {
		this.Key = Key;
	}

	/*
	 * @Description: 获得值
	 */
	public Object getValue() {
		return Value;
	}
	
	/*
	 * @Description: 设置值
	 */
	public void setValue(Object Value) {
		this.Value = Value;
	}
	
	public KeyValuePair(){
		
	}
	
	public KeyValuePair(Object key,Object Value){
		setKey(key);
		setValue(Value);
	}
}
