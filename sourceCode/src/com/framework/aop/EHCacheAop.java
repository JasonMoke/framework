/**
* @Title: EHCacheAop.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.framework.aop   
* @Description: 
* @author gaoguangchao    
* @date 2014年6月27日 下午4:15:54   
* @version V1.0 
*/

package com.framework.aop;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.core.ehcache.EHCacheHelper;
import com.core.ehcache.EHCacheInit;
import com.framework.entity.user.UserInfoAndDataManager;
import com.util.Util;


/**
 * @ClassName: EHCacheAop
 * @Description: 
 * @author gaoguangchao
 * @date 2014年6月27日 下午4:15:54
 *
 */
@Component
@Aspect
public class EHCacheAop {
	
	/**
	 * 用户
	 */
	@Pointcut("execution (* com.framework.service.user.UserServiceImp.update*(..))")
	public void updateUser() {
	}
	@Pointcut("execution (* com.framework.service.user.UserServiceImp.add*(..))")
	public void addUser() {
	}
	@SuppressWarnings("unchecked")
	@After(value = "updateUser()")
	public void updateUserAfter(JoinPoint point) throws Exception {
		List<UserInfoAndDataManager> list = new ArrayList<UserInfoAndDataManager>();
		list = (List<UserInfoAndDataManager>) EHCacheHelper.getElementValue("userCache", "allUserCache_zh_CN");
		List<UserInfoAndDataManager> listEng = new ArrayList<UserInfoAndDataManager>();
		listEng = (List<UserInfoAndDataManager>) EHCacheHelper.getElementValue("userCache", "allUserCache_en_US");
		if(Util.isNullOfList(list)||Util.isNullOfList(listEng)){
			EHCacheInit.initUser();
		}
	}
	@SuppressWarnings("unchecked")
	@After("addUser()")
	public void addUserAfter(JoinPoint point) throws Exception {
		List<UserInfoAndDataManager> list = new ArrayList<UserInfoAndDataManager>();
		list = (List<UserInfoAndDataManager>) EHCacheHelper.getElementValue("userCache", "allUserCache_zh_CN");
		List<UserInfoAndDataManager> listEng = new ArrayList<UserInfoAndDataManager>();
		listEng = (List<UserInfoAndDataManager>) EHCacheHelper.getElementValue("userCache", "allUserCache_en_US");
		if(Util.isNullOfList(list)||Util.isNullOfList(listEng)){
			EHCacheInit.initUser();
		}
	}
	
	/**
	 * 系统参数
	 */
	@Pointcut("execution (* com.framework.service.systemparam.SystemparamServiceImpl.update*(..))")
	public void updateSystemParam() {
	}
	@Pointcut("execution (* com.framework.service.systemparam.SystemparamServiceImpl.add*(..))")
	public void addSystemParam() {
	}
	@Pointcut("execution (* com.framework.service.systemparam.SystemparamServiceImpl.delete*(..))")
	public void deleteSystemParam() {
	}
	@After(value = "updateSystemParam()")
	public void addSystemParamAfter(JoinPoint point) throws Exception {
		EHCacheInit.initSysParam();
	}
	@After(value = "addSystemParam()")
	public void updateSystemParamAfter(JoinPoint point) throws Exception {
		EHCacheInit.initSysParam();
	}
	@After(value = "deleteSystemParam()")
	public void deleteSystemParamAfter(JoinPoint point) throws Exception {
		EHCacheInit.initSysParam();
	}
	
	/**
	 * 字典集
	 */
	@Pointcut("execution (* com.framework.service.dict.DictServiceImp.insert*(..))")
	public void addDict() {
	}
	@Pointcut("execution (* com.framework.service.dict.DictServiceImp.update*(..))")
	public void updateDict() {
	}
	@Pointcut("execution (* com.framework.service.dict.DictServiceImp.delete*(..))")
	public void deleteDict() {
	}
	@After("addDict()")
	public void addDictAfter(JoinPoint point) throws Exception {
			EHCacheInit.initCode();
	}
	@After("updateDict()")
	public void updateDictAfter(JoinPoint point) throws Exception {
		EHCacheInit.initCode();
	}
	@After("deleteDict()")
	public void deleteDictAfter(JoinPoint point) throws Exception {
		EHCacheInit.initCode();
	}

	/**
	 * 模板
	 */
	@Pointcut("execution (* com.framework.service.module.ModuleServiceImp.update*(..))")
	public void updateModule() {
	}
	@Pointcut("execution (* com.framework.service.module.ModuleServiceImp.add*(..))")
	public void addModule() {
	}
	@Pointcut("execution (* com.framework.service.module.ModuleServiceImp.delete*(..))")
	public void deleteModule() {
	}
	@After(value = "updateModule()")
	public void addModuleAfter(JoinPoint point) throws Exception {
		EHCacheInit.initModule();
	}
	@After(value = "addModule()")
	public void updateModuleAfter(JoinPoint point) throws Exception {
		EHCacheInit.initModule();
	}
	@After(value = "deleteModule()")
	public void deleteModuleAfter(JoinPoint point) throws Exception {
		EHCacheInit.initModule();
	}
	
	/**
	 * 资源
	 */
	@Pointcut("execution (* com.framework.service.resources.ResourcesServiceImp.update*(..))")
	public void updateResource() {
	}
	@Pointcut("execution (* com.framework.service.resources.ResourcesServiceImp.add*(..))")
	public void addResource() {
	}
	@Pointcut("execution (* com.framework.service.resources.ResourcesServiceImp.delete*(..))")
	public void deleteResource() {
	}
	@After(value = "updateResource()")
	public void addResourceAfter(JoinPoint point) throws Exception {
		EHCacheInit.initResource();
	}
	@After(value = "addResource()")
	public void updateResourceAfter(JoinPoint point) throws Exception {
		EHCacheInit.initResource();
	}
	@After(value = "deleteResource()")
	public void deleteResourceAfter(JoinPoint point) throws Exception {
		EHCacheInit.initResource();
	}
	/**
	 * 组织
	 */
	@Pointcut("execution (* com.framework.service.organ.OrganServiceImpl.update*(..))")
	public void updateOrgan() {
	}
	@Pointcut("execution (* com.framework.service.organ.OrganServiceImpl.add*(..))")
	public void addOrgan() {
	}
	@Pointcut("execution (* com.framework.service.organ.OrganServiceImpl.delete*(..))")
	public void deleteOrgan() {
	}
	@After(value = "updateOrgan()")
	public void addOrganAfter(JoinPoint point) throws Exception {
		EHCacheInit.initOrgan();
	}
	@After(value = "addOrgan()")
	public void updateOrganAfter(JoinPoint point) throws Exception {
		EHCacheInit.initOrgan();
	}
	@After(value = "deleteOrgan()")
	public void deleteOrganAfter(JoinPoint point) throws Exception {
		EHCacheInit.initOrgan();
	}
	
}
