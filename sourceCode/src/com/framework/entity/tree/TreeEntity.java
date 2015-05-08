/**
* @Title: TreeEntity.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.framework.entity.tree   
* @Description:    
* @author guangchao    
* @date 2014-1-14 下午1:22:52   
* @version V1.0 
*/
package com.framework.entity.tree;

import javax.persistence.Entity;

import com.orm.BaseEntity;

/**
 * @ClassName: TreeEntity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author guangchao
 * @date 2014-1-14 下午1:22:52
 *
 */
@Entity
public class TreeEntity extends BaseEntity{
	
	private String id;
	
	private String name;
	
	private String nameDefult;
	
	private String pid;
	
	private String pid2;
	
	private boolean open;
	
	private boolean isParent;//是否父级
	
	private boolean chkDisabled;//不可用
	
	private boolean checked;//选中
	
	private boolean nocheck;
	private String treevalue;//另保存的值
	private String treeDistinguish;//分类
	private String treeDivision;//二次区分
	private String parentname;
	private String iconSkin;
	public void setPid2(String pid2) {
		this.pid2 = pid2;
	}
	/**
	 * @return the pid2
	 */
	public String getPid2() {
		return pid2;
	}
	/**
	 * @param pid2 the pid2 to set
	 */
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
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
	 * @return the pid
	 */
	public String getPid() {
		return pid;
	}
	/**
	 * @param pid the pid to set
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}
	/**
	 * @return the open
	 */
	public boolean isOpen() {
		return open;
	}
	/**
	 * @param open the open to set
	 */
	public void setOpen(boolean open) {
		this.open = open;
	}
	/**
	 * @return the isParent
	 */
	public boolean getIsParent() {
		return isParent;
	}

	/**
	 * @param isParent the isParent to set
	 */
	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}
	/**
	 * @return the chkDisabled
	 */
	public boolean isChkDisabled() {
		return chkDisabled;
	}
	/**
	 * @param chkDisabled the chkDisabled to set
	 */
	public void setChkDisabled(boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}
	/**
	 * @return the nameDefult
	 */
	public String getNameDefult() {
		return nameDefult;
	}
	/**
	 * @param nameDefult the nameDefult to set
	 */
	public void setNameDefult(String nameDefult) {
		this.nameDefult = nameDefult;
	}
	/**
	 * @return the checked
	 */
	public boolean isChecked() {
		return checked;
	}
	/**
	 * @param checked the checked to set
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	/**
	 * @return the nocheck
	 */
	public boolean isNocheck() {
		return nocheck;
	}
	/**
	 * @param nocheck the nocheck to set
	 */
	public void setNocheck(boolean nocheck) {
		this.nocheck = nocheck;
	}
	/**
	 * @return the treevalue
	 */
	public String getTreevalue() {
		return treevalue;
	}
	/**
	 * @param treevalue the treevalue to set
	 */
	public void setTreevalue(String treevalue) {
		this.treevalue = treevalue;
	}
	/**
	 * @return the parentname
	 */
	public String getParentname() {
		return parentname;
	}
	/**
	 * @param parentname the parentname to set
	 */
	public void setParentname(String parentname) {
		this.parentname = parentname;
	}
	public String getTreeDistinguish() {
		return treeDistinguish;
	}
	public void setTreeDistinguish(String treeDistinguish) {
		this.treeDistinguish = treeDistinguish;
	}
	public String getIconSkin() {
		return iconSkin;
	}
	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}
	public String getTreeDivision() {
		return treeDivision;
	}
	public void setTreeDivision(String treeDivision) {
		this.treeDivision = treeDivision;
	}



	
}
