/**
* @Title: User.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.interfaces.entity.demo   
* @Description: 
* @author gaoguangchao    
* @date 2014年7月3日 上午11:28:14   
* @version V1.0 
*/

package com.interfaces.entity.demo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @ClassName: User
 * @Description: 
 * @author gaoguangchao
 * @date 2014年7月3日 上午11:28:14
 *
 */
@XmlRootElement(name = "UserInfo")
public class User  implements Serializable {
	private static final long serialVersionUID = 677484458789332877L;
    private int id;
    private String name;
    private String email;
    private String address;
    
    
    @Override
    public String toString() {
        return this.id + "#" + this.name + "#" + this.email + "#" + this.address;
    }

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
}
