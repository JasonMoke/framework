/**
* @Title: IHelloWorldServer.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.test   
* @Description: 
* @author gaoguangchao    
* @date 2014年5月19日 上午11:40:13   
* @version V1.0 
*/

package com.test.server;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.test.interfaces.ListObject;
import com.test.interfaces.UserDTO;

/**
 * @ClassName: IHelloWorldServer
 * @Description: 
 * @author gaoguangchao
 * @date 2014年5月19日 上午11:40:13
 *
 */
@WebService  
public interface IHelloWorldServer {
	//加入WebParam注解，以保证xml文件中参数名字的正确性  如果没有加注解，参数将被命名为arg0
	public String sayHello(@WebParam(name="userName") String username);  
	/*  
     * 一个简单的方法,返回一个字符串  
     *   
     * @param hello  
     *   
     * @return  
     */  
    String say(String hello);   
  
    /**  
     * 稍微复杂一些的方法,传递一个对象给服务端处理  
     *   
     * @param user  
     * @return  
     */  
    String sayUserName(@WebParam(name = "user") UserDTO user);   
  
    /**  
     * 最复杂的方法,返回一个List封装的对象集合  
     *   
     * @return  
     */  
    public @WebResult(partName = "o") ListObject findUsers();   

}
