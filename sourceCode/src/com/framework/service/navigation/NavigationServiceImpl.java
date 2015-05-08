/**
 * @Title: TPubNavServiceImpl.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.service.navigation
 * @Description: 接口实现类。
 * @author gaojie
 * @date 2014-09-04 16:06:17
 * @version V1.0
 */
package com.framework.service.navigation;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.framework.dao.navigation.NavigationDao;
import com.framework.entity.navigation.Navigation;
import com.orm.BaseServiceImpl;
import com.orm.BaseService;

/**
 * @ClassName: NavigationServiceImpl
 * @Description: 接口实现类。
 * @author gaojie
 * @date 2014-09-04 16:06:17
 * 
 */
@Service("navigationService")
@Transactional
public class NavigationServiceImpl extends BaseServiceImpl<Navigation> implements NavigationService {

    @Resource(name = "navigationDao")
    private NavigationDao dao;

    @Override
    public BaseService<Navigation> getDao() {
        return dao;
    }
}
