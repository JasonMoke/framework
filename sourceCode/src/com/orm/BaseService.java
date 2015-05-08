/**
 * @Title: BaseService.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.cnrsteel.orm
 * @Description: 
 * @author hankaibo
 * @date 2013-12-19 下午1:24:51
 * @version V1.0
 */
package com.orm;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: BaseService
 * @Description:泛型接口，定义了最常用的功能，例如：增、删、改、查等。我们自定义的接口只要继承它就可以了
 * @author hankaibo
 * @date 2013-12-19 下午1:24:51
 * 
 */
public interface BaseService<T extends BaseEntity> {

    /**
     * @Title: addEntity
     * @Description: 添加实体。
     * @param t
     * @return void
     * @author hankaibo
     * @date 2013-12-19 下午1:26:55
     * @throws
     */
    void addEntity(T t);
    /**
     * @Description:添加对象
     * @param t
     */
    void addObject(Object t);
    /**
     * 
    * @Title: addEntity   
    * @Description:    按照自定义的sql添加
    * @param @param sql
    * @param @param t    
    * @return void      
    * @author guangchao
    * @date 2014-1-8 下午2:13:33 
    * @throws
     */
    void addEntity(String sql,T t);
    /**
     * @Description:添加对象
     * @param sql
     * @param t
     */
    void addObject(String sql,Object t);
    /**
     * @Title: updateEntity
     * @Description: 修改实体。
     * @param t
     * @return void
     * @author hankaibo
     * @date 2013-12-19 下午1:27:33
     * @throws
     */
    void updateEntity(T t);
    /**
     * 
    * @Title: updateEntity   
    * @Description:    
    * @param @param sqlKey
    * @param @param t    
    * @return void      
    * @author guangchao
    * @date 2014-1-17 上午9:54:15 
    * @throws
     */
    <O> void updateEntity(String sqlKey, O t);

    /**
     * @Title: deleteEntity
     * @Description: 物理删除实体。
     * @param id
     * @return void
     * @author hankaibo
     * @date 2013-12-19 下午1:28:33
     * @throws
     */
    void deleteEntity(Serializable id);
    /**
     * 
    * @Title: deleteEntityOfLogical   
    * @Description:    逻辑删除
    * @param @param id    
    * @return void      
    * @author guangchao
    * @date 2014-1-8 下午3:37:00 
    * @throws
     */
    void deleteEntityOfLogical(String id);
    /**
     * 
    * @Title: deleteEntityOfLogical   
    * @Description:    根据自定义的sql逻辑删除 
    * @param @param sql
    * @param @param id    
    * @return void      
    * @author guangchao
    * @date 2014-1-8 下午3:52:31 
    * @throws
     */
    void deleteEntityOfLogical(String sql,String id);

    /**
     * @Title: bulkDelete
     * @Description: 批量物理删除。
     * @param ids
     * @return void
     * @author hankaibo
     * @date 2013-12-19 下午1:29:21
     * @throws
     */
    void bulkDelete(Serializable[] ids);
    /**
     * 
    * @Title: bulkDelete   
    * @Description:    根据自定义sql和参数进行批量物理删除
    * @param @param sql
    * @param @param params    
    * @return void      
    * @author guangchao
    * @date 2014-1-9 下午3:25:42 
    * @throws
     */
    void bulkDelete(String sql,Object params);
    /**
     * 
    * @Title: bulkDeleteOfLogical   
    * @Description:   逻辑批量删除 
    * @param @param ids    
    * @return void      
    * @author guangchao
    * @date 2014-1-8 下午3:37:25 
    * @throws
     */
    void bulkDeleteOfLogical(String[] ids);
    /**
     * 
    * @Title: bulkDeleteOfLogical   
    * @Description:   根据自定义的sql批量逻辑删除 
    * @param @param sql
    * @param @param ids    
    * @return void      
    * @author guangchao
    * @date 2014-1-8 下午3:51:57 
    * @throws
     */
    void bulkDeleteOfLogical(String sql,String[] ids);

    /**
     * @Title: findById
     * @Description: 根据id获取实体。
     * @param id
     * @return
     * @return T
     * @author hankaibo
     * @date 2013-12-19 下午1:29:49
     * @throws
     */
    T findById(Serializable id);

    /**
     * @Title: findAll
     * @Description: 查询所有数据。
     * @return
     * @return List<T>
     * @author hankaibo
     * @date 2013-12-19 下午1:30:19
     * @throws
     */
    List<T> findAll();

    /**
     * @Title: findByCondition
     * @Description: 分页查询。
     * @param sqlKey
     * @param params
     * @param page
     * @return
     * @return Page<T>
     * @author hankaibo
     * @date 2013-12-19 下午1:32:36
     * @throws
     */
    Page<T> findByCondition(String sqlKey, Object params, Page<T> page);
    /**
     * 
    * @Title: findByCondition   
    * @Description:    根据条件查询所有列表
    * @param @param sqlKey
    * @param @param params
    * @param @return    
    * @return Page<T>      
    * @author guangchao
    * @date 2014-1-9 上午10:35:49 
    * @throws
     */
    List findByCondition(String sqlKey, Object params);

	/**   
	* @Title: addListOfEntity   
	* @Description:    
	* @param @param list    
	* @return void      
	* @author guangchao
	* @date 2014-1-8 下午2:13:00 
	* @throws   
	*/ 
	
	void addListOfEntity(List list);
	/**
	 * 
	* @Title: addListOfEntity   
	* @Description:    
	* @param @param sql
	* @param @param list    
	* @return void      
	* @author guangchao
	* @date 2014-1-8 下午2:16:00 
	* @throws
	 */
	void addListOfEntity(String sql,List list);
	/**   
	* @Title: updateByCondition   
	* @Description:    根据条件与自定义sql修改
	* @param @param sqlKey
	* @param @param params    
	* @return void      
	* @author guangchao
	* @date 2014-1-14 下午2:23:22 
	* @throws   
	*/ 
	
	void updateByCondition(String sqlKey, Object params);
	/**   
	* @Title: findEntityByCondition   
	* @Description:    
	* @param @param sqlKey
	* @param @param params
	* @param @return    
	* @return T      
	* @author guangchao
	* @date 2014-1-15 上午10:27:05 
	* @throws   
	*/ 
	
	T findEntityByCondition(String sqlKey, Object params);
	/**   
	* @Title: getCountBySql   
	* @Description:    
	* @param @param sql
	* @param @param params
	* @param @return    
	* @return int      
	* @author guangchao
	* @date 2014-1-18 下午12:13:04 
	* @throws   
	*/ 
	
	int getCountBySqlAndParam(String sql, Object params);
	/**   
	* @Title: deleteByConditions   
	* @Description:    
	* @param @param sqlKey
	* @param @param params
	* @param @return    
	* @return Object      
	* @author guangchao
	* @date 2014-1-21 下午4:17:06 
	* @throws   
	*/ 
	
	void deleteByConditions(String sqlKey, Object params);
	/**
	 * 
	* @Title: findObjectByCondition   
	* @Description:    查询对象
	* @param @param sqlKey
	* @param @param params
	* @param @return    
	* @return O      
	* @author guangchao
	* @date 2014-2-20 上午11:24:50 
	* @throws
	 */
	<O> O findObjectByCondition(String sqlKey, Object params);
	
}
