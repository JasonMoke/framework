/**
 * @Title: RoleGroupServiceImpl.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.service.rolegroup
 * @Description: 接口实现类。
 * @author gaogc
 * @date 2014-08-29 13:55:02
 * @version V1.0
 */
package com.framework.service.rolegroup;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.framework.dao.rolegroup.RoleGroupDao;
import com.framework.entity.rolegroup.RoleGroup;
import com.framework.entity.rolegroup.RolegroupRoleRela;
import com.orm.BaseServiceImpl;
import com.orm.BaseService;

/**
 * @ClassName: RoleGroupServiceImpl
 * @Description: 接口实现类。
 * @author gaogc
 * @date 2014-08-29 13:55:02
 * 
 */
@Service("rolegroupService")
@Transactional
public class RoleGroupServiceImpl extends BaseServiceImpl<RoleGroup> implements RoleGroupService {

    @Resource(name = "rolegroupDao")
    private RoleGroupDao dao;

    @Override
    public BaseService<RoleGroup> getDao() {
        return dao;
    }

	@Override
	@Transactional
	public void addRolegroupRoleRela(List<RolegroupRoleRela> list,
			String roleGroupId) {
		dao.deleteByConditions("deleteRolegroupRoleRela", roleGroupId);
		dao.addObject("addListOfRolegroupRoleRela", list);
		
	}

}
