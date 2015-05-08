/**
* @Title: ListObject.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.test   
* @Description: 
* @author gaoguangchao    
* @date 2014年5月19日 下午1:22:44   
* @version V1.0 
*/

package com.test.interfaces;


import java.util.ArrayList;   
import java.util.List;   
  
import javax.xml.bind.annotation.XmlAccessType;   
import javax.xml.bind.annotation.XmlAccessorType;   
import javax.xml.bind.annotation.XmlElement;   
import javax.xml.bind.annotation.XmlType;  

/**
 * @ClassName: ListObject
 * @Description: 
 * @author gaoguangchao
 * @date 2014年5月19日 下午1:22:44
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)   
@XmlType(name = "listObject", propOrder ={ "list" })   
public class ListObject   
{   
    @XmlElement(nillable = true)   
    protected List<Object> list;   
  
    /**  
     * Gets the value of the list property.  
     *   
     * <p>  
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be  
     * present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the list property.  
     *   
     * <p>  
     * For example, to add a new item, do as follows:  
     *   
     * <pre>  
     * getList().add(newItem);  
     * </pre>  
     *   
     *   
     * <p>  
     * Objects of the following type(s) are allowed in the list {@link Object }  
     *   
     *   
     */  
    public List<Object> getList()   
    {   
        if (list == null)   
        {   
            list = new ArrayList<Object>();   
        }   
        return this.list;   
    }   
  
    public void setList(ArrayList<Object> list)   
    {   
        this.list = list;   
    }   
  
}

