/**
 * @Title: RoleResourceRelaServiceImpl.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.service.authorize
 * @Description: 接口实现类。
 * @author gaogc
 * @date 2014-08-29 17:37:08
 * @version V1.0
 */
package com.framework.service.authorize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.framework.dao.authorize.RoleResourceRelaDao;
import com.framework.entity.authorize.RoleResourceRela;
import com.orm.BaseServiceImpl;
import com.orm.BaseService;

/**
 * @ClassName: RoleResourceRelaServiceImpl
 * @Description: 接口实现类。
 * @author gaogc
 * @date 2014-08-29 17:37:08
 * 
 */
@Service("roleresourcerelaService")
@Transactional
public class RoleResourceRelaServiceImpl extends BaseServiceImpl<RoleResourceRela> implements RoleResourceRelaService {

    @Resource(name = "roleresourcerelaDao")
    private RoleResourceRelaDao dao;

    @Override
    public BaseService<RoleResourceRela> getDao() {
        return dao;
    }
    @Transactional
	public boolean SelResources(List<RoleResourceRela> listResources,
			String[] arrayNoResources,String roleName) {
    	Map<String,Object> map=new HashMap<String, Object>();
    	map.put("ids", arrayNoResources);
    	map.put("roleName", roleName);
    	dao.bulkDelete("deleteByRoleName", map);
		dao.addListOfEntity(listResources);
		return true;
	}
}
