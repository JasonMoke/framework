/**
* @Title: Users.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.interfaces.entity.demo   
* @Description: 
* @author gaoguangchao    
* @date 2014年7月3日 上午11:27:07   
* @version V1.0 
*/

package com.interfaces.entity.demo;
import java.util.HashMap;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * @ClassName: Users
 * @Description: 
 * @author gaoguangchao
 * @date 2014年7月3日 上午11:27:07
 *
 */
@XmlRootElement(name = "UserInfos")
public class Users {
	
	private List<User> users;
    
    private User[] userArr;
    
    private HashMap<String, User> maps;

	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	/**
	 * @return the userArr
	 */
	public User[] getUserArr() {
		return userArr;
	}

	/**
	 * @param userArr the userArr to set
	 */
	public void setUserArr(User[] userArr) {
		this.userArr = userArr;
	}

	/**
	 * @return the maps
	 */
	public HashMap<String, User> getMaps() {
		return maps;
	}

	/**
	 * @param maps the maps to set
	 */
	public void setMaps(HashMap<String, User> maps) {
		this.maps = maps;
	}
    
}
