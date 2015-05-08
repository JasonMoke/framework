/**
 * @Title: UserrolemanagerServiceImpl.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.service.userrolemanager
 * @Description: 接口实现类。
 * @author zhaojie
 * @date 2014-01-22 10:29:14
 * @version V1.0
 */
package com.framework.service.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.framework.dao.user.UserrolemanagerDao;
import com.framework.entity.role.UserRoleManager;
import com.framework.entity.user.UserDataManager;
import com.orm.BaseServiceImpl;
import com.orm.BaseService;

/**
 * @ClassName: UserrolemanagerServiceImpl
 * @Description: 接口实现类。
 * @author zhaojie
 * @date 2014-01-22 10:29:14
 * 
 */
@Service("userrolemanagerService")
@Transactional
public class UserrolemanagerServiceImpl extends BaseServiceImpl<UserRoleManager> implements UserrolemanagerService {

    @Resource(name = "userrolemanagerDao")
    private UserrolemanagerDao dao;

    @Override
    public BaseService<UserRoleManager> getDao() {
        return dao;
    }
    @Transactional
	public boolean selRoleManager(List<UserRoleManager> list,String UserId) {
		boolean flag=false;
		boolean result=false;
		try {
			if(list==null||list.size()<1){
				dao.deleteByConditions("deleteUserRole", UserId);
			}else{
				dao.deleteByConditions("deleteUserRole", UserId);
				dao.addListOfEntity("addList",list);
				
			}
			result = true;
		} catch (Exception e) {
			result = false;
		}
		if (result) {
			flag = true;
		} else {
			flag = false;
		}
		
		return flag;
	}
	@Transactional
	public boolean selRoleByUsers(List<UserRoleManager> list,String[] UserIdarray) {
		boolean flag=false;
		boolean result=false;
		try {
			Map<String,Object> map=new HashMap<String, Object>();
	    	map.put("ids", UserIdarray);
			dao.deleteByConditions("deleteRoleByUsers", map);
			dao.addListOfEntity("addList",list);
			result = true;
		} catch (Exception e) {
			result = false;
		}
		if (result) {
			flag = true;
		} else {
			flag = false;
		}
		
		return flag;
	}
	public boolean selUser(List<UserDataManager> list) {
		boolean flag=false;
		boolean result=false;
		UserDataManager manager=list.get(0);
		try {
			dao.updateByCondition("updateUserByOrganIdisNull",manager.getOrganId());
			dao.updateByCondition("updateUserByPuid",list);
			
			result = true;
		} catch (Exception e) {
			result = false;
		}
		if (result) {
			flag = true;
		} else {
			flag = false;
		}
		
		return flag;
	}
}
