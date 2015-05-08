/**
 * @Title: SystemparamDaoImpl.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.dao.systemparam
 * @Description: DAO实现类。
 * @author gaoguangchao
 * @date 2014-01-21 15:24:48
 * @version V1.0
 */
package com.framework.dao.systemparam;

import org.springframework.stereotype.Repository;

import com.framework.entity.systemparam.Systemparam;
import com.orm.BaseDaoImpl;

/**
 * @ClassName: SystemparamDaoImpl
 * @Description: DAO实现类。
 * @author gaoguangchao
 * @date 2014-01-21 15:24:48
 * 
 */
@Repository("systemparamDao")
public class SystemparamDaoImpl extends BaseDaoImpl<Systemparam> implements SystemparamDao {

    @Override
    public String getNameSpace() {
        return "com.framework.maps.Systemparam";
    }
    
}
