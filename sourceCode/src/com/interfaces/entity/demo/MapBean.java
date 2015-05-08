/**
* @Title: MapBean.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.interfaces.entity.demo   
* @Description: 
* @author gaoguangchao    
* @date 2014年7月3日 上午11:26:25   
* @version V1.0 
*/

package com.interfaces.entity.demo;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

/**
 * @ClassName: MapBean
 * @Description: 
 * @author gaoguangchao
 * @date 2014年7月3日 上午11:26:25
 *
 */
@XmlRootElement
public class MapBean {
	 private Map<String, User> map;
	    
    //@XmlElement(type = User.class)
    public Map<String, User> getMap() {
        return map;
    }
    public void setMap(Map<String, User> map) {
        this.map = map;
    }
}
