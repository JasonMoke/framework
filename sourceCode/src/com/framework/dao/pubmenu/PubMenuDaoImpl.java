/**
 * @Title: PubMenuDaoImpl.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.dao.pubmenu
 * @Description: DAO实现类。
 * @author lyc
 * @date 2014-09-05 11:03:47
 * @version V1.0
 */
package com.framework.dao.pubmenu;

import org.springframework.stereotype.Repository;

import com.framework.entity.pubmenu.PubMenu;
import com.orm.BaseDaoImpl;

/**
 * @ClassName: PubMenuDaoImpl
 * @Description: DAO实现类。
 * @author lyc
 * @date 2014-09-05 11:03:47
 * 
 */
@Repository("pubmenuDao")
public class PubMenuDaoImpl extends BaseDaoImpl<PubMenu> implements PubMenuDao {

    @Override
    public String getNameSpace() {
        return "com.framework.maps.PubMenu";
    }
    
}
