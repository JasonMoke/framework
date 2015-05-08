/**
* @Title: UserDTO.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.test.interfaces   
* @Description: 
* @author gaoguangchao    
* @date 2014年5月19日 下午1:24:22   
* @version V1.0 
*/

package com.test.interfaces;
import javax.xml.bind.annotation.XmlAccessType;   
import javax.xml.bind.annotation.XmlAccessorType;   
import javax.xml.bind.annotation.XmlType;  

/**
 * @ClassName: UserDTO
 * @Description: 
 * @author gaoguangchao
 * @date 2014年5月19日 下午1:24:22
 *
 */

/**  
 * Web Service传输User信息的DTO.  
 *   
 * 分离entity类与web service接口间的耦合，隔绝entity类的修改对接口的影响. 
 * 使用JAXB 2.0的annotation标注JAVA-XML映射，尽量使用默认约定.  
 *   
 */  
@XmlAccessorType(XmlAccessType.FIELD)   
@XmlType(name = "User")   
public class UserDTO   
{   
  
    protected Integer id;   
  
    protected String name;   
  
    public Integer getId()   
    {   
        return id;   
    }   
  
    public void setId(Integer value)   
    {   
        id = value;   
    }   
  
    public String getName()   
    {   
        return name;   
    }   
  
    public void setName(String value)   
    {   
        name = value;   
    }   
}

