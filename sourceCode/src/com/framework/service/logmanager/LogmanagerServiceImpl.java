/**
 * @Title: LogmanagerServiceImpl.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.service.logmanager
 * @Description: 接口实现类。
 * @author gaoguangchao
 * @date 2014-01-14 14:16:14
 * @version V1.0
 */
package com.framework.service.logmanager;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.framework.dao.logmanager.LogmanagerDao;
import com.framework.entity.logmanager.Logmanager;
import com.orm.BaseServiceImpl;
import com.orm.BaseService;

/**
 * @ClassName: LogmanagerServiceImpl
 * @Description: 接口实现类。
 * @author gaoguangchao
 * @date 2014-01-14 14:16:14
 * 
 */
@Service("logmanagerService")
@Transactional
public class LogmanagerServiceImpl extends BaseServiceImpl<Logmanager> implements LogmanagerService {

    @Resource(name = "logmanagerDao")
    private LogmanagerDao dao;

    @Override
    public BaseService<Logmanager> getDao() {
        return dao;
    }

	@Override
	public void addObject(Object t) {
		
	}
}
