/**
 * @Title: t_module 
 * @Copyright 2010 -2013 CreativeWise 
 * @Package: com.framework.entity.module 
 * @Description: t_module的实体类 
 * @author: gaoguangchao 
 * @date: 2013-12-23 13:02:29 
 * @version V1.0  
 */ 
package com.framework.entity.module;



import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;

import com.framework.entity.resources.Resources;
import com.orm.BaseEntity;


/**
 * @ClassName: t_module 
 * @Description: t_module的实体类 
 * @author: gaoguangchao 
 * @date: 2013-12-23 13:02:29 
 */ 
@Entity
public class ModuleManager extends BaseEntity{

	private String ModuleId;	//VARCHAR(36)

	private String ModuleName;	//VARCHAR(64)

	public String ModuleParent;	//VARCHAR(36)
	
	private String ModuleNameML;	//VARCHAR(64)
	
	private String ModuleNameDefult;	//VARCHAR(64)

	private String ModuleAddress;	//VARCHAR(512)

	private String ModuleParam;	//VARCHAR(512)

	private String ModulePrompt;	//VARCHAR(256)

	private String ModuleTarger;	//VARCHAR(64)

	private String HelpPath;	//VARCHAR(512)

	private int IsEntrance;	//BIT(1)

	private int IsMenu;	//BIT(1)

	private int IsRightSet;	//BIT(1)

	private String BigLogo;	//VARCHAR(512)

	private String SmallLogo;	//VARCHAR(512)

	private int Status;	//BIT(1)

	private String ModuleRemark;	//VARCHAR(256)

	private int ModuleNumber;	//INT(11)

	private String SystemCode;	//VARCHAR(64)

	private Date CreateTime;	//DATETIME(19)

	private String CreatePerson;	//VARCHAR(32)
	
	private String UpdatePerson;
	
	private Date UpdateTime;
	
	private String Locale;
	
	private String HasChild;
	
	private String PermissionSet;
	
	private Set<Resources> resourcesSet;

	public void setModuleId(String ModuleId){
		this.ModuleId=ModuleId;
	}
	public String getModuleId(){
		return ModuleId;
	}

	public void setModuleName(String ModuleName){
		this.ModuleName=ModuleName;
	}
	public String getModuleName(){
		return ModuleName;
	}

	public void setModuleParent(String ModuleParent){
		this.ModuleParent=ModuleParent;
	}
	public String getModuleParent(){
		return ModuleParent;
	}

	public void setModuleAddress(String ModuleAddress){
		this.ModuleAddress=ModuleAddress;
	}
	public String getModuleAddress(){
		return ModuleAddress;
	}

	public void setModuleParam(String ModuleParam){
		this.ModuleParam=ModuleParam;
	}
	public String getModuleParam(){
		return ModuleParam;
	}

	public void setModulePrompt(String ModulePrompt){
		this.ModulePrompt=ModulePrompt;
	}
	public String getModulePrompt(){
		return ModulePrompt;
	}

	public void setModuleTarger(String ModuleTarger){
		this.ModuleTarger=ModuleTarger;
	}
	public String getModuleTarger(){
		return ModuleTarger;
	}

	public void setHelpPath(String HelpPath){
		this.HelpPath=HelpPath;
	}
	public String getHelpPath(){
		return HelpPath;
	}


	public void setBigLogo(String BigLogo){
		this.BigLogo=BigLogo;
	}
	public String getBigLogo(){
		return BigLogo;
	}

	public void setSmallLogo(String SmallLogo){
		this.SmallLogo=SmallLogo;
	}
	public String getSmallLogo(){
		return SmallLogo;
	}
	public void setModuleRemark(String ModuleRemark){
		this.ModuleRemark=ModuleRemark;
	}
	public String getModuleRemark(){
		return ModuleRemark;
	}

	public void setModuleNumber(int ModuleNumber){
		this.ModuleNumber=ModuleNumber;
	}
	public int getModuleNumber(){
		return ModuleNumber;
	}

	public void setSystemCode(String SystemCode){
		this.SystemCode=SystemCode;
	}
	public String getSystemCode(){
		return SystemCode;
	}

	public void setCreateTime(Date CreateTime){
		this.CreateTime=CreateTime;
	}
	public Date getCreateTime(){
		return CreateTime;
	}

	public void setCreatePerson(String CreatePerson){
		this.CreatePerson=CreatePerson;
	}
	public String getCreatePerson(){
		return CreatePerson;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return Status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		Status = status;
	}
	/**
	 * @return the updatePerson
	 */
	public String getUpdatePerson() {
		return UpdatePerson;
	}
	/**
	 * @param updatePerson the updatePerson to set
	 */
	public void setUpdatePerson(String updatePerson) {
		UpdatePerson = updatePerson;
	}
	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return UpdateTime;
	}
	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		UpdateTime = updateTime;
	}
	/**
	 * @return the moduleNameML
	 */
	public String getModuleNameML() {
		return ModuleNameML;
	}
	/**
	 * @param moduleNameML the moduleNameML to set
	 */
	public void setModuleNameML(String moduleNameML) {
		ModuleNameML = moduleNameML;
	}
	/**
	 * @return the moduleNameDefult
	 */
	public String getModuleNameDefult() {
		return ModuleNameDefult;
	}
	/**
	 * @param moduleNameDefult the moduleNameDefult to set
	 */
	public void setModuleNameDefult(String moduleNameDefult) {
		ModuleNameDefult = moduleNameDefult;
	}
	/**
	 * @return the locale
	 */
	public String getLocale() {
		return Locale;
	}
	/**
	 * @param locale the locale to set
	 */
	public void setLocale(String locale) {
		Locale = locale;
	}
	/**
	 * @return the hasChild
	 */
	public String getHasChild() {
		return HasChild;
	}
	/**
	 * @param hasChild the hasChild to set
	 */
	public void setHasChild(String hasChild) {
		HasChild = hasChild;
	}
	/**
	 * @return the isEntrance
	 */
	public int getIsEntrance() {
		return IsEntrance;
	}
	/**
	 * @param isEntrance the isEntrance to set
	 */
	public void setIsEntrance(int isEntrance) {
		IsEntrance = isEntrance;
	}
	/**
	 * @return the isMenu
	 */
	public int getIsMenu() {
		return IsMenu;
	}
	/**
	 * @param isMenu the isMenu to set
	 */
	public void setIsMenu(int isMenu) {
		IsMenu = isMenu;
	}
	/**
	 * @return the isRightSet
	 */
	public int getIsRightSet() {
		return IsRightSet;
	}
	/**
	 * @param isRightSet the isRightSet to set
	 */
	public void setIsRightSet(int isRightSet) {
		IsRightSet = isRightSet;
	}
	public String getPermissionSet() {
		return PermissionSet;
	}
	public void setPermissionSet(String permissionSet) {
		PermissionSet = permissionSet;
	}
	
	
	public Set<Resources> getResourcesSet() {
		return resourcesSet;
	}
	public void setResourcesSet(Set<Resources> resourcesSet) {
		this.resourcesSet = resourcesSet;
	}
	
}

