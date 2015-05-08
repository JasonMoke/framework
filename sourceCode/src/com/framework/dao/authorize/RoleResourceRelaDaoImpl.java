/**
 * @Title: RoleResourceRelaDaoImpl.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.dao.authorize
 * @Description: DAO实现类。
 * @author gaogc
 * @date 2014-08-29 17:37:08
 * @version V1.0
 */
package com.framework.dao.authorize;

import org.springframework.stereotype.Repository;

import com.framework.entity.authorize.RoleResourceRela;
import com.orm.BaseDaoImpl;

/**
 * @ClassName: RoleResourceRelaDaoImpl
 * @Description: DAO实现类。
 * @author gaogc
 * @date 2014-08-29 17:37:08
 * 
 */
@Repository("roleresourcerelaDao")
public class RoleResourceRelaDaoImpl extends BaseDaoImpl<RoleResourceRela> implements RoleResourceRelaDao {

    @Override
    public String getNameSpace() {
        return "com.framework.maps.roleresourcerela";
    }
    
}
