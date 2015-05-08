/**
 * @Title: SystemmanagerServiceImpl.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.service.systemmanager
 * @Description: 接口实现类。
 * @author lixiaoguang
 * @date 2014-04-03 13:41:59
 * @version V1.0
 */
package com.framework.service.systemmanager;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.framework.dao.systemmanager.SystemmanagerDao;
import com.framework.entity.systemmanager.Systemmanager;
import com.orm.BaseServiceImpl;
import com.orm.BaseService;

/**
 * @ClassName: SystemmanagerServiceImpl
 * @Description: 接口实现类。
 * @author lixiaoguang
 * @date 2014-04-03 13:41:59
 * 
 */
@Service("systemmanagerService")
@Transactional
public class SystemmanagerServiceImpl extends BaseServiceImpl<Systemmanager> implements SystemmanagerService {

    @Resource(name = "systemmanagerDao")
    private SystemmanagerDao dao;

    @Override
    public BaseService<Systemmanager> getDao() {
        return dao;
    }
}
