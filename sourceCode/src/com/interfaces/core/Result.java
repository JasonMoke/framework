/**
* @Title: Result.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.interfaces.core   
* @Description: 
* @author gaoguangchao    
* @date 2014年7月3日 下午3:46:02   
* @version V1.0 
*/

package com.interfaces.core;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @ClassName: Result
 * @Description: 
 * @author gaoguangchao
 * @date 2014年7月3日 下午3:46:02
 *
 */
@XmlRootElement(name = "Result")
public class Result {
	
	private String Code;
	
	private String Message;
	
	
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return Code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		Code = code;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return Message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		Message = message;
	}
}
