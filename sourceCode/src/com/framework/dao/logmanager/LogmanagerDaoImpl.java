/**
 * @Title: LogmanagerDaoImpl.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.dao.logmanager
 * @Description: DAO实现类。
 * @author gaoguangchao
 * @date 2014-01-14 14:16:14
 * @version V1.0
 */
package com.framework.dao.logmanager;

import org.springframework.stereotype.Repository;

import com.framework.entity.logmanager.Logmanager;
import com.orm.BaseDaoImpl;

/**
 * @ClassName: LogmanagerDaoImpl
 * @Description: DAO实现类。
 * @author gaoguangchao
 * @date 2014-01-14 14:16:14
 * 
 */
@Repository("logmanagerDao")
public class LogmanagerDaoImpl extends BaseDaoImpl<Logmanager> implements LogmanagerDao {

    @Override
    public String getNameSpace() {
        return "com.framework.maps.Logmanager";
    }
    
}
