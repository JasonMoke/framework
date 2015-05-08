package com.core.db;

/**
 * @ClassName: KeyValuePair 
 * @Description: 键值对
 * @author:  tanyi
 * @date: 2014-01-16 14:11:04
 */ 
public class TKey<T1,T2> {
	
	private T1 Key;
	private T2 Value;
	
	/*
	 * @Description: 获得键
	 */
	public T1 getKey() {
		return Key;
	}
	
	/*
	 * @Description: 设置键
	 */
	public void setKey(T1 Key) {
		this.Key = Key;
	}

	/*
	 * @Description: 获得值
	 */
	public T2 getValue() {
		return Value;
	}
	
	/*
	 * @Description: 设置值
	 */
	public void setValue(T2 Value) {
		this.Value = Value;
	}
	
	public TKey(){
		
	}
	
	public TKey(T1 key,T2 Value){
		setKey(key);
		setValue(Value);
	}
}
