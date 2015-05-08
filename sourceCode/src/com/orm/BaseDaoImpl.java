/**
 * @Title: BaseDaoImpl.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.orm
 * @Description: DAO实现类。
 * @author hankaibo
 * @date 2013-12-19 下午1:34:09
 * @version V1.0
 */
package com.orm;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.framework.entity.user.UserInfoManager;
import com.core.framework.mybatis.support.CustomerContextHolder;
import com.orm.BaseEntity;
import com.util.SessionInfo;
import com.util.Util;

/**
 * @ClassName: BaseDaoImpl
 * @Description: DAO实现类。与业务无关的通用操作封装
 * @author hankaibo
 * @date 2013-12-19 下午1:34:09
 * 
 */
public abstract class BaseDaoImpl<T extends BaseEntity> implements BaseService<T> {
    Logger log = Logger.getLogger(BaseService.class);

    private static final String ADD = "add";
    private static final String ADDLIST = "addList";
    private static final String UPDATE = "update";
    private static final String DELETE = "delete";
    private static final String DELETE_LOGIC = "deleteBatch";
    private static final String FIND_BY_ID = "findById";
    private static final String FIND_ALL = "findAll";
    private static final String FIND_BY_SQL = "cn.framework.dao.base.dynaSql";
    private static final String SQL_KEY = "SQL Key <";
    private static final String SQL_KEY_END = ">";

    @Autowired
    private SqlSession sqlSession;

    /**
     * @Title: createSqlKeyName
     * @Description: 构造sql配置文件中的key,格式 nameSpace+'.'+sqlKey
     * @param sqlKey
     * @return
     * @return String
     * @author hankaibo
     * @date 2013-12-19 下午1:58:26
     * @throws
     */
    String createSqlKeyName(String sqlKey) {
    	String DatabaseId = sqlSession.getConfiguration().getDatabaseId().toUpperCase();
        String key = getNameSpace() + "." + sqlKey;
//        String key = getNameSpace() + "." + sqlKey+"_"+DatabaseId;
        if (log.isDebugEnabled()) {
            log.debug(SQL_KEY + key + SQL_KEY_END);
        }
        return key;
    }
    /**
     * 
    * @Title: getNameSpace   
    * @Description:
    * @return
    * @return String        
    * @author gaoguangchao
    * @date 2014年7月7日 下午2:34:17 
    *
     */
    public abstract String getNameSpace();
    /**
     * 
    * @Title: getSql   
    * @Description:获取sql
    * @param sqlKey
    * @param param
    * @return
    * @return String        
    * @author gaoguangchao
    * @date 2014年7月7日 下午2:34:22 
    *
     */
    public String getSql(String sqlKey, Object param) {
        String fullSqlKey = createSqlKeyName(sqlKey);
        return getMyBatisSql(fullSqlKey, param).toString();
    }
   
    /**
     * @Title: findBySql
     * @Description: 根据sql语句查询
     * @param sql
     * @return
     * @return List<Map<String,Object>>
     * @author hankaibo
     * @date 2013-12-19 下午2:36:25
     * @throws
     */
    public List<Map<String, Object>> findBySql(String sql) {
        return sqlSession.selectList(FIND_BY_SQL, sql);
    }
    /**
     * 
    * @Title: findMapBySql   
    * @Description:    根绝sql分页查询
    * @param @param sql
    * @param @param page
    * @param @return    
    * @return Page<Map<String,Object>>      
    * @author guangchao
    * @date 2014-1-10 上午10:04:05 
    * @throws
     */
    public Page<Map<String, Object>> findMapBySql(String sql, Page<Map<String, Object>> page) {
        if (page == null) {
            page = new Page<Map<String, Object>>();
            List<Map<String, Object>> list = sqlSession.selectList(FIND_BY_SQL, sql);
            page.setResultList(list);
            return page;
        }

        int offset = (page.getCurrentPage() - 1) * page.getPageSize();
        List<Map<String, Object>> list = sqlSession.selectList(FIND_BY_SQL, sql,
                new RowBounds(offset, page.getPageSize()));
        page.setResultList(list);
        page.setTotal(countBySql(sql));

        return page;

    }

    /**
     * 
    * @Title: addEntity   
    * @Description:    添加dao继承的实体
    * @param @param T t
    * @param @return    
    * @author guangchao
    * @date 2014-1-10 上午10:04:05 
    * @throws
     */
//    @RequiresPermissions("account:create")
    public void addEntity(T t) {
        sqlSession.insert(createSqlKeyName(ADD), t);
    }
    /**
      * @Title: addObject   
    * @Description:    添加对象
    * @param @param Object t
    * @param @return    
    * @author guangchao
    * @date 2014-1-10 上午10:04:05 
    * @throws
     */
    public void addObject(Object t) {
    	sqlSession.insert(createSqlKeyName(ADD), t);
    }
    /**
     * 
    * @Title: addListOfEntity   
    * @Description:    批量添加继承的实体
    * @param @param sql
    * @param @param list    
    * @return void      
    * @author guangchao
    * @date 2014-1-8 下午2:15:19 
    * @throws
     */
    public void addListOfEntity(List list) {
    	sqlSession.insert(createSqlKeyName(ADDLIST), list);
    }
    /**
     * 
    * @Title: addListOfEntity   
    * @Description:    根据自定义sql批量添加实体
    * @param @param sql
    * @param @param list    
    * @return void      
    * @author guangchao
    * @date 2014-1-8 下午2:15:19 
    * @throws
     */
    public void addListOfEntity(String sql,List list) {
    	sqlSession.insert(createSqlKeyName(sql), list);
    }

    /**
     * @Title: addEntity
     * @Description: 添加自定义实体。
     * @param sqlKey
     * @param t
     * @return void
     * @author hankaibo
     * @date 2013-12-23 下午1:17:27
     * @throws
     */
    public <O> void addEntity(String sqlKey, O t) {
        sqlSession.insert(createSqlKeyName(sqlKey), t);
    }
    /**
     * 
    * @Title: addEntity   
    * @Description:    按照自定义的sql添加继承的实体
    * @param @param sqlKey
    * @param @param T    
    * @return void      
    * @author guangchao
    * @date 2014-1-8 下午2:15:19 
    * @throws
     */
    public  void addEntity(String sqlKey, T t) {
    	sqlSession.insert(createSqlKeyName(sqlKey), t);
    }
    /**
     * @Title: addObject   
    * @Description:    按照自定义的sql添加对象
    * @param @param sqlKey
    * @param @param Object    
    * @return void      
    * @author guangchao
    * @date 2014-1-8 下午2:15:19 
    * @throws
     */
    public void addObject(String sqlKey, Object t) {
    	sqlSession.insert(createSqlKeyName(sqlKey), t);
    }
    /**
     * 
    * @Title: insertByCondetion   
    * @Description:    按照自定义的sql添加自定义对象
    * @param @param sqlKey
    * @param @param params    
    * @return void      
    * @author guangchao
    * @date 2014-1-8 下午2:15:19 
    * @throws
     */
    public void insertByCondetion(String sqlKey, Object params) {
        sqlSession.insert(createSqlKeyName(sqlKey), params);
    }
    /**
     * 
    * @Title: updateEntity   
    * @Description:    修改继承的实体
    * @param @param T t
    * @return void      
    * @author guangchao
    * @date 2014-1-8 下午2:15:19 
    * @throws
     */
    public void updateEntity(T t) {
        sqlSession.update(createSqlKeyName(UPDATE), t);
    }

    /**
     * @Title: updateEntity
     * @Description: 根据自定义sql更新自定义实体。
     * @param sqlKey
     * @param t
     * @return void
     * @author hankaibo
     * @date 2013-12-23 下午1:18:48
     * @throws
     */
    public <O> void updateEntity(String sqlKey, O t) {
        sqlSession.update(createSqlKeyName(sqlKey), t);
    }

    /**
     * @Title: updateByCondition
     * @Description: 根据条件修改实体。
     * @param sqlKey
     * @param params
     * @return void
     * @author hankaibo
     * @date 2013-12-19 下午3:03:21
     * @throws
     */
    public void updateByCondition(String sqlKey, Object params) {
        sqlSession.update(createSqlKeyName(sqlKey), params);
    }
    /**
     * @Title: updateByCondition
     * @Description: 根据条件修改实体。
     * @param sqlKey
     * @param params
     * @return void
     * @author hankaibo
     * @date 2013-12-19 下午3:03:21
     * @throws
     */
    public void updateBatchStatus(String sqlKey, String[] ids) {
    	Map map = new HashMap();
    	map.put("ids", ids);
    	sqlSession.update(createSqlKeyName(sqlKey), map);
    }
    /**
     * 
    * @Title: updateEntity   
    * @Description:    物理删除实体
    * @param @param T t
    * @return void      
    * @author guangchao
    * @date 2014-1-8 下午2:15:19 
    * @throws
     */
    public void deleteEntity(Serializable id) {
        sqlSession.delete(createSqlKeyName(DELETE), id);
    }
    /**
     * 
    * @Title: deleteEntityOfLogical   
    * @Description: 逻辑删除   
    * @param @param id    
    * @return void      
    * @author guangchao
    * @date 2014-1-8 下午3:48:12 
    * @throws
     */
    public void deleteEntityOfLogical(String id) {
    	UserInfoManager userInfoManager = SessionInfo.getCurUser();
    	String UserId = userInfoManager.getUserId();
    	String[] ids = new String[1];
    	ids[0] = id;
    	Map map = new HashMap();
    	map.put("ids", ids);
    	map.put("UpdatePerson", UserId);
    	map.put("UpdateTime", new Date());
    	sqlSession.update(createSqlKeyName(DELETE_LOGIC), map);
    }
    /**
     * 
    * @Title: deleteEntityOfLogical   
    * @Description:    根据自定义的sql逻辑删除 
    * @param @param sql
    * @param @param id    
    * @return void      
    * @author guangchao
    * @date 2014-1-8 下午3:56:10 
    * @throws
     */
    public void deleteEntityOfLogical(String sql,String id) {
		UserInfoManager userInfoManager = SessionInfo.getCurUser();
	    String UserId = userInfoManager.getUserId();
    	String[] ids = new String[1] ;
    	ids[0] = id;
    	Map map = new HashMap();
    	map.put("ids", ids);
    	map.put("UpdatePerson", UserId);
    	map.put("UpdateTime", new Date());
    	sqlSession.update(createSqlKeyName(sql), map);
    }
    /**
     * 
    * @Title: bulkDelete   
    * @Description:    批量物理删除 
    * @param @param ids
    * @return void      
    * @author guangchao
    * @date 2014-1-8 下午3:56:10 
    * @throws
     */
    public void bulkDelete(Serializable[] ids) {
        for (Serializable id : ids) {
            deleteEntity(id);
        }
    }
    /**
     * 
    * @Title: bulkDelete   
    * @Description:     根据自定义的sql批量物理删除
    * @param @param ids    
    * @return void      
    * @author guangchao
    * @date 2014-1-8 下午3:48:26 
    * @throws
     */
    public void bulkDelete(String sql,Object params) {
    	sqlSession.delete(createSqlKeyName(sql), params);
    }
    /**
     * 
    * @Title: bulkDeleteOfLogical   
    * @Description:    批量逻辑删除
    * @param @param ids    
    * @return void      
    * @author guangchao
    * @date 2014-1-8 下午3:48:26 
    * @throws
     */
    public void bulkDeleteOfLogical(String[] ids) {
    	UserInfoManager userInfoManager = SessionInfo.getCurUser();
    	String UserId = userInfoManager.getUserId();
    	Map map = new HashMap();
    	map.put("ids", ids);
    	map.put("UpdatePerson", UserId);
    	map.put("UpdateTime", new Date());
    	sqlSession.update(createSqlKeyName(DELETE_LOGIC), map);
    }
    /**
     * 
    * @Title: bulkDeleteOfLogical   
    * @Description:     根据自定义的sql批量逻辑删除 
    * @param @param sql
    * @param @param ids    
    * @return void      
    * @author guangchao
    * @date 2014-1-8 下午3:56:36 
    * @throws
     */
    public void bulkDeleteOfLogical(String sql,String[] ids) {
    	UserInfoManager userInfoManager = SessionInfo.getCurUser();
    	String UserId = userInfoManager.getUserId();
    	Map map = new HashMap();
    	map.put("ids", ids);
    	map.put("UpdatePerson", UserId);
    	map.put("UpdateTime", new Date());
    	sqlSession.update(createSqlKeyName(sql), map);
    }

    /**
     * @Title: deleteByConditions
     * @Description: 根据条件删除实体。
     * @param sqlKey
     * @param params
     * @return void
     * @author hankaibo
     * @date 2013-12-19 下午3:01:25
     * @throws
     */
    public void deleteByConditions(String sqlKey, Object params) {
        sqlSession.delete(createSqlKeyName(sqlKey), params);
    }

    /**
     * 
    * @Title: bulkDeleteOfLogical   
    * @Description:     根据主键查找一个实体。 
    * @param @param id
    * @return T     继承的实体 
    * @author guangchao
    * @date 2014-1-8 下午3:56:36 
    * @throws
     */
    public T findById(Serializable id) {
        return sqlSession.selectOne(createSqlKeyName(FIND_BY_ID), id);
    }
    /**
     * @Title: findEntityByCondition
     * @Description: 根据条件获取一个实体。
     * @param sqlKey
     * @param params
     * @return
     * @return T	继承的实体
     * @author hankaibo
     * @date 2013-12-19 下午3:07:22
     * @throws
     */
    public T findEntityByCondition(String sqlKey, Object params) {
        return sqlSession.selectOne(createSqlKeyName(sqlKey), getParam(params));
    }

    /**
     * @Title: findObjectByCondition
     * @Description: 根据条件返回单个实体（用于自定义实体的转换，如UserVO，而非与数据库对应的实体UserEntity）
     * @param sqlKey
     * @param params
     * @return
     * @return O
     * @author hankaibo
     * @date 2013-12-19 下午6:05:32
     * @throws
     */
    public <O> O findObjectByCondition(String sqlKey, Object params) {
        return sqlSession.selectOne(createSqlKeyName(sqlKey), getParam(params));
    }

    /**
     * @Title: findObjectByCondition
     * @Description: 查询并返回所有实体。
     * @param sqlKey
     * @param params
     * @return T	继承的实体
     * @author guangchao
     * @date 2013-1-9 下午6:05:32
     * @throws
     */
    public List<T> findAll() {
        return sqlSession.selectList(createSqlKeyName(FIND_ALL));
    }

    /**
     * @Title: findByCondition
     * @Description: 根据条件返回实体列表。
     * @param sqlKey
     * @param params
     * @return
     * @return List
     * @author hankaibo
     * @date 2013-12-19 下午3:10:31
     * @throws
     */
    public List findByCondition(String sqlKey, Object params) {
        return sqlSession.selectList(createSqlKeyName(sqlKey), getParam(params));
    }

    /**
     * @Title: findObjectListByCondition
     * @Description: 根据条件返回实体列表（用于自定义实体的转换，如UserVO，而非与数据库对应的实体UserEntity）
     * @param sqlKey
     * @param params
     * @return
     * @return List<O>
     * @author hankaibo
     * @date 2013-12-19 下午3:12:07
     * @throws
     */
    public <O> List<O> findObjectListByCondition(String sqlKey, Object params) {
        return sqlSession.selectList(createSqlKeyName(sqlKey), getParam(params));
    }

    /**
     * @Title: findOneByCondition
     * @Description: 查询一条记录。
     * @param sqlKey
     * @param params
     * @return
     * @return Map<String,Object>
     * @author hankaibo
     * @date 2013-12-19 下午3:32:13
     * @throws
     */
    public Map<String, Object> findOneByCondition(String sqlKey, Object params) {
        return sqlSession.selectOne(createSqlKeyName(sqlKey), getParam(params));
    }

    /**
     * @Title: findMapByCondition
     * @Description: 返回Map.
     * @param sqlKey
     * @param params
     * @return
     * @return List<Map<String,Object>>
     * @author hankaibo
     * @date 2013-12-19 下午3:18:28
     * @throws
     */
    public List<Map<String, Object>> findMapByCondition(String sqlKey, Object params) {
        return sqlSession.selectList(createSqlKeyName(sqlKey), getParam(params));
    }

    /**
     * @Title: findMapByConditionPage
     * @Description: 根据自定义sql，返回分页Map.
     * @param sqlKey
     * @param params
     * @param page
     * @return
     * @return Page<Map<String,Object>>
     * @author hankaibo
     * @date 2013-12-19 下午6:07:30
     * @throws
     */
    public Page<Map<String, Object>> findMapByConditionPage(String sqlKey, Object params, Page<Map<String, Object>> page) {
        if (page == null) {
            page = new Page<Map<String, Object>>();
            List<Map<String, Object>> list = sqlSession.selectList(createSqlKeyName(sqlKey), getParam(params));
            page.setResultList(list);
            return page;
        }
        int offset = (page.getCurrentPage() - 1) * page.getPageSize();
        List<Map<String, Object>> list = sqlSession.selectList(createSqlKeyName(sqlKey), getParam(params), new RowBounds(offset,
                page.getPageSize()));
        page.setResultList(list);
        if(params instanceof BaseEntity){
        	page.setTotal(count(sqlKey, getParam(params)));
        }else{
        	page.setTotal(count(sqlKey, params));
        }
        return page;
    }

    /**
     * @Title: findByCondition
     * @Description: 根据自定义sql，返回分页实体
     * @param sqlKey
     * @param params
     * @param page
     * @return
     * @return Page<T>
     * @author hankaibo
     * @date 2013-12-19 下午6:07:30
     * @throws
     */
	@SuppressWarnings("unchecked")
	public Page<T> findByCondition(String sqlKey, Object params, Page<T> page) {
        if (page == null) {
            page = new Page<T>();
            List<T> list = sqlSession.selectList(createSqlKeyName(sqlKey), getParam(params));
            page.setResultList(list);
            return page;
        }
        int offset = (page.getCurrentPage() - 1) * page.getPageSize();
        Map<String, Object> map = new HashMap<String, Object>();
        if(params!=null){
        	params = getParam(params);
        	if(Util.isMap(params)){
            	map = (Map<String, Object>) params;
            }else{
            	map = Util.ConvertObjToMap(params);
            }
        }
        map.put("orders", page.getOrders());
        List<T> list = sqlSession.selectList(createSqlKeyName(sqlKey), map,new RowBounds(offset, page.getPageSize()));
        page.setResultList(list);
        if(params instanceof BaseEntity){
        	page.setTotal(count(sqlKey, getParam(params)));
        }else{
        	page.setTotal(count(sqlKey, params));
        }
        return page;
    }

    /**
     * @Title: findByCondition
     * @Description: 根据自定义sql，返回自定义的实体。
     * @param sqlKey
     * @param params
     * @param pageSize
     * @param currentPage
     * @return
     * @return List<O>
     * @author hankaibo
     * @date 2013-12-23 下午1:29:07
     * @throws
     */
    public <O> List<O> findByCondition(String sqlKey, Object params, int pageSize, int currentPage) {
        int offset = (currentPage - 1) * pageSize;
        List<O> list = sqlSession.selectList(createSqlKeyName(sqlKey), getParam(params), new RowBounds(offset, pageSize));
        return list;

    }

    /**
     * @Title: findObjectPageByCondition
     * @Description: 分页查询（用于关联查询自定义实体）
     * @param sqlKey
     * @param params
     * @param page
     * @return
     * @return Page<O>
     * @author hankaibo
     * @date 2013-12-19 下午3:44:30
     * @throws
     */
    public <O> Page<O> findObjectPageByCondition(String sqlKey, Object params, Page<O> page) {
        if (page == null) {
            page = new Page<O>();
            List<O> list = sqlSession.selectList(createSqlKeyName(sqlKey), getParam(params));
            page.setResultList(list);
            return page;
        }
        int offset = (page.getCurrentPage() - 1) * page.getPageSize();
        List<O> list = sqlSession.selectList(createSqlKeyName(sqlKey), getParam(params),
                new RowBounds(offset, page.getPageSize()));
        page.setResultList(list);
        if(params instanceof BaseEntity){
        	page.setTotal(count(sqlKey, getParam(params)));
        }else{
        	page.setTotal(count(sqlKey, params));
        }
        return page;
    }

    /**
     * @Title: findOPageByCondition
     * @Description: 分页查询（传入实体，count+sqlKey 为 count一次统计key值，对数据新增操作少的查询）
     *               使用两个查询,查询数据使用sqlKey,查询统统sqlKey为 "count"+sqlKey
     * @param sqlKey
     * @param params
     * @param page
     * @return
     * @return Page<O>
     * @author hankaibo
     * @date 2013-12-23 下午1:30:18
     * @throws
     */
    @SuppressWarnings("unchecked")
    public <O> Page<O> findOPageByCondition(String sqlKey, Object params, Page<O> page) {
        if (page == null) {
            page = new Page<O>();
            List<O> list = sqlSession.selectList(createSqlKeyName(sqlKey), getParam(params));
            page.setResultList(list);
            return page;
        }
        int offset = (page.getCurrentPage() - 1) * page.getPageSize();
        page.setResultList((List<O>) sqlSession.selectList(createSqlKeyName(sqlKey), getParam(params),
                new RowBounds(offset, page.getPageSize())));
        if (page.getTotal() == -1) {
            page.setTotal(countSimple(sqlKey, getParam(params)));
        }
        return page;
    }

    /**
     * @Title: countBySql
     * @Description: 查询条数
     * @param sql
     * @return
     * @return long
     * @author hankaibo
     * @date 2013-12-19 下午2:47:39
     * @throws
     */
    private int countBySql(String sql) {
        String fromHql = getMyBatisSql(FIND_BY_SQL, sql).toString();
        String countSql = "select count(1) from (" + fromHql + ") count_sql_alias";

        return ((Number) sqlSession.selectOne("cn.framework.dao.base.countSql", countSql)).intValue();
    }
    /**
     * 
    * @Title: getCountBySqlAndParam   
    * @Description:    根据自定义sql与参数查询条数
    * @param @param sql
    * @param @param params
    * @param @return    
    * @return int      
    * @author guangchao
    * @date 2014-1-18 下午12:12:03 
    * @throws
     */
    public int getCountBySqlAndParam(String sql,Object params) {
    	return  sqlSession.selectOne(createSqlKeyName(sql), params);
    }

    /**
     * @Title: getMyBatisSql
     * @Description:	
     * @param fullSqlKey
     * @param param
     * @return
     * @return Object
     * @author hankaibo
     * @date 2013-12-19 下午2:07:44
     * @throws
     */
    private MyBatisSql getMyBatisSql(String id, Object param) {
        MyBatisSql myBatisSql = new MyBatisSql();
        MappedStatement ms = sqlSession.getConfiguration().getMappedStatement(id);
        BoundSql boundSql = ms.getBoundSql(param);
        List<ResultMap> resultMaps = ms.getResultMaps();
        if (resultMaps != null && resultMaps.size() > 0) {
            ResultMap resultMap = ms.getResultMaps().get(0);
            myBatisSql.setResultClass(resultMap.getType());
        }
        myBatisSql.setSql(boundSql.getSql());
        Configuration configuration = ms.getConfiguration();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (parameterMappings != null) {
            Object[] parameterArray = new Object[parameterMappings.size()];
            MetaObject metaObject = param == null ? null : configuration.newMetaObject(param);
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    PropertyTokenizer prop = new PropertyTokenizer(propertyName);
                    if (param == null) {
                        value = null;
                    } else if (ms.getConfiguration().getTypeHandlerRegistry().hasTypeHandler(param.getClass())) {
                        value = param;
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)
                            && boundSql.hasAdditionalParameter(prop.getName())) {
                        value = boundSql.getAdditionalParameter(prop.getName());
                        if (value != null) {
                            // TODO
                            value = MetaObject.forObject(value, null, null).getValue(
                                    propertyName.substring(prop.getName().length()));
                        }
                    } else {
                        value = metaObject == null ? null : metaObject.getValue(propertyName);
                    }
                    parameterArray[i] = value;
                }
            }
            myBatisSql.setParameters(parameterArray);
        }
        return myBatisSql;
    }

    /**
     * @Title: countSimple
     * @Description: 分页查询时，查询总数。
     * @param sqlKey
     * @param params
     * @return
     * @return long
     * @author hankaibo
     * @date 2013-12-19 下午4:09:52
     * @throws
     */
    private int countSimple(String sqlKey, Object params) {
        String fromHql = "";
        fromHql = getMyBatisSql(createSqlKeyName("count" + sqlKey), params).toString();
        String countSql = "select count(1) from (" + fromHql + ") count_sql_alias";
        return ((Number) sqlSession.selectOne("cn.framework.dao.base.countSql", countSql)).intValue();
    }

    /**
     * @Title: count
     * @Description: 分页查询时，查询总数。
     * @param sqlKey
     * @param params
     * @return
     * @return int
     * @author hankaibo
     * @date 2013-12-19 下午3:37:53
     * @throws
     */
    private int count(String sqlKey, Object params) {
        String fromHql = getMyBatisSql(createSqlKeyName(sqlKey), params).toString();
        fromHql = StringUtils.substringBefore(fromHql, "order by");
        String countSql = "select count(1) from (" + fromHql + ") count_sql_alias";

        return ((Number) sqlSession.selectOne("cn.framework.dao.base.countSql", countSql)).intValue();

    }
    /**
     * 
    * @Title: getParam   
    * @Description:(谨慎使用)对参数中的需要转义的字符，进行预处理;注意，一个方法中多次引用param时，只需要调用一次该方法即可(保证运行一次)
    * @param param
    * @return
    * @return Object        
    * @author gaoguangchao
    * @date 2014年12月16日 下午3:13:36 
    *
     */
    private Object getParam(Object param){
    	if(param==null){
    		return param;
    	}
    	if(Util.isMap(param)){
    		Map paramMap = (HashMap)param;
    		Set<String> key = paramMap.keySet();
            for (Iterator it = key.iterator(); it.hasNext();) {
                String s = (String) it.next();
                //加了对String的判断，目前只处理简单泛型
                if(Util.isString(paramMap.get(s))){
                	String value = (String) paramMap.get(s);
                	if(value!=null){
                		value = value.replaceAll("'", "''");
                	}
                	paramMap.put(s, value);
                }
            }
            return paramMap;
    	}
    	
    	if(Util.isList(param)){
			List paramList = (ArrayList)param;
    		for(Object o:paramList){
    			if(Util.isMap(o)){
    				Map paramMap = (HashMap)o;
    	    		Set<String> key = paramMap.keySet();
    	            for (Iterator it = key.iterator(); it.hasNext();) {
    	                String s = (String) it.next();
    	                if(Util.isString(paramMap.get(s))){
    	                	String value = (String) paramMap.get(s);
    	                	if(value!=null){
    	                		value = value.replaceAll("'", "''");
    	                	}
    	                	paramMap.put(s, value);
    	                }
    	            }
    			}else if(Util.isString(o)){
    				o = o.toString().replaceAll("'", "''");
    			}else if(Util.isArray(o)){
    				String [] arr = ( String[] ) o ;  
    	            for ( int i = 0 ; i < arr.length ; i++ ){
    	            	if(arr[i]!=null){
    	            		arr[i] = arr[i].replaceAll("'", "''");
    	            	}
    	            }  
    			}
    			
    		}
    		return paramList;
    	}
    	
    	if(Util.isString(param)){
    		return param.toString().replace("'", "''");
    	}
    	
    	if (Util.isArray(param)){
    		String [] arr = ( String[] ) param ;  
            for ( int i = 0 ; i < arr.length ; i++ ){
            	if(arr[i]!=null){
            		arr[i] = arr[i].replaceAll("'", "''");
            	}
            }  
            return arr;
    	}
    	if(param instanceof Number){
    		return param;
    	}
    	if(param instanceof BaseEntity){
    		Object model = ((BaseEntity) param).clone();
    		// 获取实体类的所有属性，返回Field数组  
            Field[] field = model.getClass().getDeclaredFields();  
            // 遍历所有属性  
            for (int j = 0; j < field.length; j++) {  
                // 获取属性的名字  
                String name = field[j].getName();  
                // 将属性的首字符大写，方便构造get，set方法  
                name = name.substring(0, 1).toUpperCase() + name.substring(1);  
                // 获取属性的类型  
                String type = field[j].getGenericType().toString();  
                // 如果type是类类型，则前面包含"class "，后面跟类名  
                try {
                	if (type.equals("class java.lang.String")) {  
                        Method m = model.getClass().getMethod("get" + name);  
                        // 调用getter方法获取属性值  
                        String value = (String) m.invoke(model);  
                        if (Util.isNotNull(value)) {  
                        	value = value.replaceAll("'", "''");
                        	Method s = model.getClass().getDeclaredMethod("set" + name,String.class); 
                        	s.invoke(model,value);
                        } 
                    }  
				} catch (Exception e) {
					System.out.println(e);
					return param;
				}
            }  
    		return model;
    	}
    	
		return param;
    	
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
