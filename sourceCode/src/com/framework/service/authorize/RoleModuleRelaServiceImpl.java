/**
 * @Title: RoleModuleRelaServiceImpl.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.service.authorize
 * @Description: 接口实现类。
 * @author gaogc
 * @date 2014-08-29 17:36:54
 * @version V1.0
 */
package com.framework.service.authorize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.framework.dao.authorize.RoleModuleRelaDao;
import com.framework.entity.authorize.RoleModuleRela;
import com.orm.BaseServiceImpl;
import com.orm.BaseService;

/**
 * @ClassName: RoleModuleRelaServiceImpl
 * @Description: 接口实现类。
 * @author gaogc
 * @date 2014-08-29 17:36:54
 * 
 */
@Service("rolemodulerelaService")
@Transactional
public class RoleModuleRelaServiceImpl extends BaseServiceImpl<RoleModuleRela> implements RoleModuleRelaService {

    @Resource(name = "rolemodulerelaDao")
    private RoleModuleRelaDao dao;

    @Override
    public BaseService<RoleModuleRela> getDao() {
        return dao;
    }

    @Transactional
	public boolean SelModule(List<RoleModuleRela> listModule,String[] arrayNoModule, String roleName) {
		// TODO Auto-generated method stub
    	Map<String,Object> map=new HashMap<String, Object>();
    	map.put("ids", arrayNoModule);
    	map.put("roleName", roleName);
		dao.bulkDelete("deleteByRoleName", map);
		dao.addListOfEntity(listModule);
		return true;
	}

	
}
