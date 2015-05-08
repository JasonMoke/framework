/**
* @Title: BaseMap.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.interfaces.core   
* @Description: 
* @author gaoguangchao    
* @date 2014年7月3日 下午3:43:55   
* @version V1.0 
*/

package com.interfaces.core;


import javax.xml.bind.annotation.XmlRootElement;



/**
 * @ClassName: BaseMap
 * @Description: 
 * @author gaoguangchao
 * @date 2014年7月3日 下午3:43:55
 *
 */
@XmlRootElement(name = "Data")
public class Data {
	
	private Result Result;
	
	/**
	 * @return the result
	 */
	public Result getResult() {
		return Result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(Result result) {
		Result = result;
	}
}
