/**
 * @Title: RoleModuleRelaDaoImpl.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.dao.authorize
 * @Description: DAO实现类。
 * @author gaogc
 * @date 2014-08-29 17:36:54
 * @version V1.0
 */
package com.framework.dao.authorize;

import org.springframework.stereotype.Repository;

import com.framework.entity.authorize.RoleModuleRela;
import com.orm.BaseDaoImpl;

/**
 * @ClassName: RoleModuleRelaDaoImpl
 * @Description: DAO实现类。
 * @author gaogc
 * @date 2014-08-29 17:36:54
 * 
 */
@Repository("rolemodulerelaDao")
public class RoleModuleRelaDaoImpl extends BaseDaoImpl<RoleModuleRela> implements RoleModuleRelaDao {

    @Override
    public String getNameSpace() {
        return "com.framework.maps.rolemodulerela";
    }
    
}
