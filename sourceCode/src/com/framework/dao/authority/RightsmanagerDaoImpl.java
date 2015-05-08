/**
 * @Title: RightsmanagerDaoImpl.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.dao.rightsmanager
 * @Description: DAO实现类。
 * @author zhaojie
 * @date 2014-01-20 15:23:11
 * @version V1.0
 */
package com.framework.dao.authority;

import org.springframework.stereotype.Repository;

import com.framework.entity.authority.RightsManager;
import com.orm.BaseDaoImpl;

/**
 * @ClassName: RightsmanagerDaoImpl
 * @Description: DAO实现类。
 * @author zhaojie
 * @date 2014-01-20 15:23:11
 * 
 */
@Repository("rightsmanagerDao")
public class RightsmanagerDaoImpl extends BaseDaoImpl<RightsManager> implements RightsmanagerDao {

    @Override
    public String getNameSpace() {
        return "com.framework.maps.Rightsmanager";
    }
    
}
