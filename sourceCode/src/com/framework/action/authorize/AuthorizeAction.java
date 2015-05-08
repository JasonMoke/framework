/**
 * @Title: RolemanagerAction.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.action.rolemanager
 * @Description: 角色管理
 * @author zhaojie
 * @date 2014-01-17 14:14:24
 * @version V1.0
 */
package com.framework.action.authorize;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.core.base.BaseAction;
import com.framework.entity.authorize.RoleModuleRela;
import com.framework.entity.authorize.RoleResourceRela;
import com.framework.entity.user.UserInfoManager;
import com.framework.service.authorize.RoleModuleRelaService;
import com.framework.service.authorize.RoleResourceRelaService;
import com.util.SessionInfo;

@Controller
public class AuthorizeAction extends BaseAction {

	 /**
	 * 
	 */
	private static final long serialVersionUID = -6768183438673031963L;
	@Resource(name = "rolemodulerelaService")
	 private RoleModuleRelaService rolemodulerelaService;
	 @Resource(name = "roleresourcerelaService")
	 private RoleResourceRelaService roleresourcerelaService;
	/**
	 * 
	* @Title: 角色权限   
	* @Description:  角色权限   
	* @param @return
	* @param @throws Exception      
	* @return String   
	* @author zhaojie
	* @date 2014-1-22 上午10:02:34 
	* @throws
	 */
	public String SelResourcesAndModule() throws Exception {
		List<RoleModuleRela> listModule= new ArrayList<RoleModuleRela>();
		List<RoleResourceRela> listResources= new ArrayList<RoleResourceRela>();
		String RoleName = getParameter("RoleName");
		String ModuleID = getParameter("ModuleID");
		String ResourcesId = getParameter("ResourcesId");
		String noMoudleId = getParameter("noMoudleId");
		String noResourcesId = getParameter("noResourcesId");
		UserInfoManager userInfoManager = SessionInfo.getCurUser();
		String updatePerson = userInfoManager.getUserId();
		String[] arrayModule;
		String[] arrayResources;
		String[] arrayNoModule;
		String[] arrayNoResources;
		arrayModule = ModuleID.split(",");
		arrayResources = ResourcesId.split(",");
		arrayNoModule = noMoudleId.split(",");
		arrayNoResources = noResourcesId.split(",");
		for (int i = 0; i < arrayModule.length; i++) {
			RoleModuleRela roleModuleRela=new RoleModuleRela();
			roleModuleRela.setRightsId(UUID.randomUUID().toString());
			roleModuleRela.setCreatePerson(updatePerson);
			roleModuleRela.setUpdatePerson(updatePerson);
			roleModuleRela.setStatus(1);
			roleModuleRela.setCreateTime(new Date());
			roleModuleRela.setUpdateTime(new Date());
			roleModuleRela.setModuleId(arrayModule[i]);
			roleModuleRela.setRoleName(RoleName);
			listModule.add(roleModuleRela);
		}
		for (int i = 0; i < arrayResources.length; i++) {
			RoleResourceRela roleResourceRela=new RoleResourceRela();
			roleResourceRela.setUUID(UUID.randomUUID().toString());
			roleResourceRela.setCreatePerson(updatePerson);
			roleResourceRela.setUpdatePerson(updatePerson);
			roleResourceRela.setStatus(1);
			roleResourceRela.setCreateTime(new Date());
			roleResourceRela.setUpdateTime(new Date());
			roleResourceRela.setResourceId(arrayResources[i]);
			roleResourceRela.setRoleName(RoleName);
			listResources.add(roleResourceRela);
		}
		rolemodulerelaService.SelModule(listModule,arrayNoModule, RoleName);
		roleresourcerelaService.SelResources(listResources,arrayNoResources, RoleName);
		return "success";

	}
	/**
     * 
    * @Title: gotoAuthorize   
    * @Description:
    * @return
    * @return String        
    * @author gaoguangchao
    * @date 2014年9月2日 下午2:33:49 
    *
     */
    public String gotoAuthorize() {
        return "success";
    }

}
