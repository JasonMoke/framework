/**
 * @Title: DictServiceImp.java   
 * @Copyright 2010 -2013 BIS
 * @Package com.framework.service.dict   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author user1    
 * @date 2014-1-3 上午10:29:36   
 * @version V1.0 
 */

package com.framework.service.dict;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.framework.dao.dict.DictDaoImpl;
import com.framework.entity.dict.DictList;
import com.orm.BaseServiceImpl;
import com.orm.BaseService;

/**
 * @ClassName: DictServiceImp
 * @Description: 字典集管理
 * @author user1
 * @date 2014-1-3 上午10:29:36
 * 
 */
@Service("dictServiceImp")
@Transactional
public class DictServiceImp extends BaseServiceImpl<DictList> implements  IDictService {
	@Resource(name = "dictDaoImpl")
	private DictDaoImpl dictDaoImpl;
	@Override
	public BaseService<DictList> getDao() {
		return dictDaoImpl;
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
    public void addEntity(String sql, DictList t) {
    	getDao().addEntity(sql,t);
    }
}
