/**
 * @Title: BaseServiceImpl.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.cnrsteel.orm
 * @Description: TODO(用一句话描述该文件做什么)
 * @author hankaibo
 * @date 2013-12-20 下午12:28:42
 * @version V1.0
 */
package com.orm;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.core.framework.mybatis.support.CustomerContextHolder;

/**
 * @ClassName: BaseServiceImpl
 * @Description: 抽象类，实现BaseService中接口定义的方法并定义了一个要实现的抽象方法，我们的接口实现类继承它，并实现覆盖方法即可；
 * @author hankaibo
 * @date 2013-12-20 下午12:28:42
 * 
 */
public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    public abstract BaseService<T> getDao();

    
    public void addEntity(T t) {
        getDao().addEntity(t);
    }
    
    public void addEntity(String sql,T t) {
    	getDao().addEntity(sql,t);
    }
    
    public void addObject(Object t) {
    	getDao().addObject(t);
    }
    
    public void addObject(String sql,Object t) {
    	getDao().addObject(sql,t);
    }
    
    public void addListOfEntity(List list) {
    	getDao().addListOfEntity(list);
    }
    
    public void addListOfEntity(String sql,List list) {
    	getDao().addListOfEntity(sql,list);
    }

    
    public void updateEntity(T t) {
        getDao().updateEntity(t);
    }
    
    public <O> void updateEntity(String sqlKey, O t) {
    	getDao().updateEntity(sqlKey,t);
    }
    
    public void updateByCondition(String sqlKey, Object params){
    	getDao().updateByCondition(sqlKey,params);
    }

    
    public void deleteEntity(Serializable id) {
        getDao().deleteEntity(id);
    }

    
    public void bulkDelete(Serializable[] ids) {
        getDao().bulkDelete(ids);
    }
    
    public void bulkDelete(String sql,Object params) {
    	getDao().bulkDelete(sql,params);
    }
    
    public void deleteEntityOfLogical(String id) {
    	getDao().deleteEntityOfLogical(id);
    }
    
    public void deleteEntityOfLogical(String sql,String id) {
    	getDao().deleteEntityOfLogical(sql,id);
    }
    
    
    public void bulkDeleteOfLogical(String[] ids) {
    	getDao().bulkDeleteOfLogical(ids);
    }
    
    public void bulkDeleteOfLogical(String sql,String[] ids) {
    	getDao().bulkDeleteOfLogical(sql,ids);
    }

    public List<T> findAll() {
        return getDao().findAll();
    }

    public T findById(Serializable id) {
        return getDao().findById(id);
    }

    public Page<T> findByCondition(String sqlKey, Object params, Page<T> page) {
        return getDao().findByCondition(sqlKey, params, page);
    }
    public List findByCondition(String sqlKey, Object params) {
    	return getDao().findByCondition(sqlKey, params);
    }
    public  T findEntityByCondition(String sqlKey, Object params) {
    	return getDao().findEntityByCondition(sqlKey, params);
    }
    public int getCountBySqlAndParam(String sql,Object params){
    	return getDao().getCountBySqlAndParam(sql,params);
    }
    public  void deleteByConditions(String sqlKey, Object params){
    	getDao().deleteByConditions(sqlKey,params);
    }
    public <O> O findObjectByCondition(String sqlKey, Object params) {
    	return getDao().findObjectByCondition(sqlKey,params);
    }
    /**
     * 
    * @Title: DBType   
    * @Description:切换数据源
    * @param value
    * @return void        
    * @author gaoguangchao
    * @date 2015年2月12日 下午1:07:48 
    *
     */
    public void DBType(DBTYPE value){
    	if(value.equals(DBTYPE.MYSQL)){
    		CustomerContextHolder.setContextType(CustomerContextHolder.SESSION_FACTORY_MYSQL);
    	}else if(value.equals(DBTYPE.ORACLE)){
    		CustomerContextHolder.setContextType(CustomerContextHolder.SESSION_FACTORY_ORACLE);
    	}else if(value.equals(DBTYPE.SQLSERVER)){
    		CustomerContextHolder.setContextType(CustomerContextHolder.SESSION_FACTORY_SQLSERVER);
    	}
    }
}
