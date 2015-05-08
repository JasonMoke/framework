/**
 * @Title: SystemmanagerDaoImpl.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.dao.systemmanager
 * @Description: DAO实现类。
 * @author lixiaoguang
 * @date 2014-04-03 13:41:59
 * @version V1.0
 */
package com.framework.dao.systemmanager;

import org.springframework.stereotype.Repository;

import com.framework.entity.systemmanager.Systemmanager;
import com.orm.BaseDaoImpl;

/**
 * @ClassName: SystemmanagerDaoImpl
 * @Description: DAO实现类。
 * @author lixiaoguang
 * @date 2014-04-03 13:41:59
 * 
 */
@Repository("systemmanagerDao")
public class SystemmanagerDaoImpl extends BaseDaoImpl<Systemmanager> implements SystemmanagerDao {

    @Override
    public String getNameSpace() {
        return "com.framework.maps.Systemmanager";
    }
    
}
