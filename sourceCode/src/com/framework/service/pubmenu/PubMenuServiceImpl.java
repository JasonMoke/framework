/**
 * @Title: PubMenuServiceImpl.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.service.pubmenu
 * @Description: 接口实现类。
 * @author lyc
 * @date 2014-09-05 11:03:47
 * @version V1.0
 */
package com.framework.service.pubmenu;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.framework.dao.pubmenu.PubMenuDao;
import com.framework.entity.pubmenu.PubMenu;
import com.orm.BaseServiceImpl;
import com.orm.BaseService;

/**
 * @ClassName: PubMenuServiceImpl
 * @Description: 接口实现类。
 * @author lyc
 * @date 2014-09-05 11:03:47
 * 
 */
@Service("pubmenuService")
@Transactional
public class PubMenuServiceImpl extends BaseServiceImpl<PubMenu> implements PubMenuService {

    @Resource(name = "pubmenuDao")
    private PubMenuDao dao;

    @Override
    public BaseService<PubMenu> getDao() {
        return dao;
    }
}
