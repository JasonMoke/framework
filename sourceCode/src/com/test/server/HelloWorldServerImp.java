/**
* @Title: HelloWorldServerImp.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.test   
* @Description: 
* @author gaoguangchao    
* @date 2014年5月19日 上午11:40:36   
* @version V1.0 
*/

package com.test.server;

import java.util.ArrayList;

import javax.jws.WebService;

import com.test.interfaces.ListObject;
import com.test.interfaces.UserDTO;

/**
 * @ClassName: HelloWorldServerImp
 * @Description: 
 * @author gaoguangchao
 * @date 2014年5月19日 上午11:40:36
 *
 */

@WebService(endpointInterface = "com.test.server.IHelloWorldServer", serviceName = "HelloService")  
public class HelloWorldServerImp implements IHelloWorldServer{  
  
    @Override  
    public String sayHello(String username) {  
          
        return username+" : HelloWorld";  
    }  
    public String sayUserName(UserDTO user)   
    {   
        return "hello " + user.getName();   
    }   
  
    public String say(String hello)   
    {   
        return "hello " + hello;   
    }   
  
    public ListObject findUsers()   
    {   
        ArrayList<Object> list = new ArrayList<Object>();   
  
        list.add(instancUser(1, "lib"));   
        list.add(instancUser(2, "mld"));   
        list.add(instancUser(3, "lq"));   
        list.add(instancUser(4, "gj"));   
        ListObject o = new ListObject();   
        o.setList(list);   
        return o;   
    }   
  
    private UserDTO instancUser(Integer id, String name)   
    {   
        UserDTO user = new UserDTO();   
        user.setId(id);   
        user.setName(name);   
        return user;   
    }   

} 
