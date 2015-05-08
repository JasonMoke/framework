
package com.framework.service.resources;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.framework.dao.resources.ResourcesDaoImpl;
import com.framework.entity.resources.Resources;
import com.orm.BaseServiceImpl;
import com.orm.BaseService;


@Service("ResourcesServiceImp")
@Transactional
public class ResourcesServiceImp extends BaseServiceImpl<Resources> implements  ResourcesService {
	@Resource(name = "ResourcesDaoImpl")
	private ResourcesDaoImpl ResourcesDaoImpl;
	@Override
	public BaseService<Resources> getDao() {
		return ResourcesDaoImpl;
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
    public void addEntity(String sql,Resources t) {
    	getDao().addEntity(sql,t);
    }
}
