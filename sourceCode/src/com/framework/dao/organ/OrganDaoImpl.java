/**
 * @Title: OrganDaoImpl.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.dao.organ
 * @Description: DAO实现类。
 * @author gaoguangchao
 * @date 2014-04-08 17:17:55
 * @version V1.0
 */
package com.framework.dao.organ;

import org.springframework.stereotype.Repository;

import com.framework.entity.organ.Organ;
import com.orm.BaseDaoImpl;

/**
 * @ClassName: OrganDaoImpl
 * @Description: DAO实现类。
 * @author gaoguangchao
 * @date 2014-04-08 17:17:55
 * 
 */
@Repository("organDao")
public class OrganDaoImpl extends BaseDaoImpl<Organ> implements OrganDao {

    @Override
    public String getNameSpace() {
        return "com.framework.maps.Organ";
    }
    
}
