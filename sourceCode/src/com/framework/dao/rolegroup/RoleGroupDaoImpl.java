/**
 * @Title: RoleGroupDaoImpl.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.dao.rolegroup
 * @Description: DAO实现类。
 * @author gaogc
 * @date 2014-08-29 13:55:02
 * @version V1.0
 */
package com.framework.dao.rolegroup;

import org.springframework.stereotype.Repository;

import com.framework.entity.rolegroup.RoleGroup;
import com.orm.BaseDaoImpl;

/**
 * @ClassName: RoleGroupDaoImpl
 * @Description: DAO实现类。
 * @author gaogc
 * @date 2014-08-29 13:55:02
 * 
 */
@Repository("rolegroupDao")
public class RoleGroupDaoImpl extends BaseDaoImpl<RoleGroup> implements RoleGroupDao {

    @Override
    public String getNameSpace() {
        return "com.framework.maps.rolegroup";
    }
    
}
