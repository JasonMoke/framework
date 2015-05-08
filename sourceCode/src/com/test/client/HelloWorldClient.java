/**
* @Title: HelloWorldClient.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.test.client   
* @Description: 
* @author gaoguangchao    
* @date 2014年5月19日 下午12:26:14   
* @version V1.0 
*/

package com.test.client;
import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.interfaces.ListObject;
import com.test.interfaces.UserDTO;
import com.test.server.IHelloWorldServer;  

/**
 * @ClassName: HelloWorldClient
 * @Description: 
 * @author gaoguangchao
 * @date 2014年5月19日 下午12:26:14
 *
 */


public class HelloWorldClient {  
    public static void main(String[] args) {  
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-client.xml");  
        IHelloWorldServer helloService = (IHelloWorldServer) context.getBean("client");  
        String response = helloService.sayHello("gaogc"); 
        String response1 = helloService.say("everyone");
        UserDTO user = new UserDTO();
        user.setId(123);
        user.setName("gaogc");
        String response2 = helloService.sayUserName(user);
        ListObject list = (ListObject) helloService.findUsers();
        for(int i =0;i<list.getList().size();i++){
        	UserDTO userdto = (UserDTO) list.getList().get(i);
        	System.out.println("Name:"+userdto.getName());
        }
//        System.out.println(response);  
//        System.out.println(response1);  
//        System.out.println(response2);  
//        
        
        
        //调用WebService，该调用方式不依赖xml配置
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(IHelloWorldServer.class);
        factory.setAddress("http://localhost:8888/framework/ws/helloService");
        
        IHelloWorldServer service = (IHelloWorldServer) factory.create();
        System.out.println(service.sayHello("gaogc"));
    }  
  
} 
