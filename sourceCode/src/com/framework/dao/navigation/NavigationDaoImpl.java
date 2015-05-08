/**
 * @Title: TPubNavDaoImpl.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.dao.navigation
 * @Description: DAO实现类。
 * @author gaojie
 * @date 2014-09-04 16:06:17
 * @version V1.0
 */
package com.framework.dao.navigation;

import org.springframework.stereotype.Repository;

import com.framework.entity.navigation.Navigation;
import com.orm.BaseDaoImpl;

/**
 * @ClassName: TPubNavDaoImpl
 * @Description: DAO实现类。
 * @author gaojie
 * @date 2014-09-04 16:06:17
 * 
 */
@Repository("navigationDao")
public class NavigationDaoImpl extends BaseDaoImpl<Navigation> implements NavigationDao {

    @Override
    public String getNameSpace() {
        return "com.framework.maps.Navigation";
    }
    
}
