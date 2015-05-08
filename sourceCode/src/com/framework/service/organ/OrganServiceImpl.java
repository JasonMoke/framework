/**
 * @Title: OrganServiceImpl.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.service.organ
 * @Description: 接口实现类。
 * @author gaoguangchao
 * @date 2014-04-08 17:17:55
 * @version V1.0
 */
package com.framework.service.organ;


import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.framework.dao.organ.OrganDao;
import com.framework.entity.organ.Organ;
import com.orm.BaseServiceImpl;
import com.orm.BaseService;

/**
 * @ClassName: OrganServiceImpl
 * @Description: 接口实现类。
 * @author gaoguangchao
 * @date 2014-04-08 17:17:55
 * 
 */
@Service("organService")
@Transactional
public class OrganServiceImpl extends BaseServiceImpl<Organ> implements OrganService {

    @Resource(name = "organDao")
    private OrganDao dao;

    @Override
    public BaseService<Organ> getDao() {
        return dao;
    }
    @Override
   	@Transactional
   	@CacheEvict(value = "organCache", allEntries = true)
    public void updateByCondition(String sqlKey, Object params){
    	getDao().updateByCondition(sqlKey,params);
    }
    @Override
    @Transactional
    @CacheEvict(value = "organCache", allEntries = true)
    public void updateEntity(Organ t){
    	getDao().updateEntity(t);
    }
    @Override
   	@Transactional
   	@CacheEvict(value = "organCache", allEntries = true)
   	public void deleteEntityOfLogical( String id) {
    	getDao().deleteEntityOfLogical(id);
   	};
   	@Override
   	@Transactional
   	@CacheEvict(value = "organCache", allEntries = true)
   	public void bulkDeleteOfLogical( String[] ids) {
   		getDao().bulkDeleteOfLogical(ids);
   	};
   	@Override
   	@Transactional
   	@CacheEvict(value = "organCache", allEntries = true)
   	public void addEntity(String sql, Organ t) {
       	getDao().addEntity(sql,t);
    }
}
