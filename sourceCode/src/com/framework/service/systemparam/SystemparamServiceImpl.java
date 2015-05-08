/**
 * @Title: SystemparamServiceImpl.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.service.systemparam
 * @Description: 接口实现类。
 * @author gaoguangchao
 * @date 2014-01-21 15:24:48
 * @version V1.0
 */
package com.framework.service.systemparam;


import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.framework.dao.systemparam.SystemparamDao;
import com.framework.entity.systemparam.Systemparam;
import com.orm.BaseServiceImpl;
import com.orm.BaseService;

/**
 * @ClassName: SystemparamServiceImpl
 * @Description: 接口实现类。
 * @author gaoguangchao
 * @date 2014-01-21 15:24:48
 * 
 */
@Service("systemparamService")
@Transactional
public class SystemparamServiceImpl extends BaseServiceImpl<Systemparam> implements SystemparamService {

    @Resource(name = "systemparamDao")
    private SystemparamDao dao;

    @Override
    public BaseService<Systemparam> getDao() {
        return dao;
    }
    
    @Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
    public void updateByCondition(String sqlKey, Object params){
    	getDao().updateByCondition(sqlKey,params);
    }
    @Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public void deleteByConditions(String sqlKey, Object params) {
		getDao().deleteByConditions(sqlKey, params);
	};
	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public void addEntity(String sql, Systemparam t) {
    	getDao().addEntity(sql,t);
    }
}
