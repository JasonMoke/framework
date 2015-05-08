/**
 * @Title: UserrolemanagerDaoImpl.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.dao.userrolemanager
 * @Description: DAO实现类。
 * @author zhaojie
 * @date 2014-01-22 10:29:15
 * @version V1.0
 */
package com.framework.dao.user;
import org.springframework.stereotype.Repository;

import com.framework.entity.role.UserRoleManager;
import com.orm.BaseDaoImpl;

/**
 * @ClassName: UserrolemanagerDaoImpl
 * @Description: DAO实现类。
 * @author zhaojie
 * @date 2014-01-22 10:29:15
 * 
 */
@Repository("userrolemanagerDao")
public class UserrolemanagerDaoImpl extends BaseDaoImpl<UserRoleManager> implements UserrolemanagerDao {
	
    @Override
    public String getNameSpace() {
        return "com.framework.maps.Userrolemanager";
    }
    
}
