package com.core.db;

import java.util.*;
import java.sql.*;

import com.orm.DBTYPE;
import com.util.Util;

/**
 * @ClassName: DBHelper 
 * @Description: 数据库工具类
 * @author:  tanyi
 * @date: 2014-01-16 14:11:04
 */ 
public class DBHelper {
	
	private static ResourceBundle rb;
	private static String jdbcurl;
	private static String jdbcusername;
	private static String jdbcpassword;
	
	/**
     * @Description: 默认自动初始化
     */
	static { 
		try {
			init(DBTYPE.DEFAULT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * @Title: init
     * @Description: 初始化
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	private static void init(DBTYPE value) throws Exception { 
		try{
			rb = ResourceBundle.getBundle("db");
			if(value.equals(DBTYPE.MYSQL)){
				jdbcurl = rb.getString("mysql.jdbc.url");
				jdbcusername = rb.getString("mysql.jdbc.username");
				jdbcpassword = rb.getString("mysql.jdbc.password");
			}else if(value.equals(DBTYPE.ORACLE)){
				jdbcurl = rb.getString("oracle.jdbc.url");
				jdbcusername = rb.getString("oracle.jdbc.username");
				jdbcpassword = rb.getString("oracle.jdbc.password");
				
			}else if(value.equals(DBTYPE.SQLSERVER)){
				jdbcurl = rb.getString("sqlserver.jdbc.url");
				jdbcusername = rb.getString("sqlserver.jdbc.username");
				jdbcpassword = rb.getString("sqlserver.jdbc.password");
				
			}else {
				jdbcurl = rb.getString("mysql.jdbc.url");
				jdbcusername = rb.getString("mysql.jdbc.username");
				jdbcpassword = rb.getString("mysql.jdbc.password");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	/**
     * @Title: getConnection
     * @Description: 获得一个连接对象
     * @return 返回一个连接对象
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static Connection getConnection() throws Exception{
        return DriverManager.getConnection(jdbcurl,jdbcusername,jdbcpassword); 
	}
	
	/**
     * @Title: executeUpdate
     * @Description: 执行一个不是查询语句的SQL，如：添加、删除、更新语句
     * @param sql(一个添加、删除、更新语句)
     * @return 返回影响的行数
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static int executeNonQuery(String sql) throws Exception{
		return executeNonQuery(sql, (Object[])null);
	}
	
	/**
     * @Title: executeUpdate
     * @Description: 执行一个不是查询语句的SQL，如：添加、删除、更新语句
     * @param sql(一个添加、删除、更新语句)
     * @return 返回影响的行数
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static int executeNonQuery(String sql,Object ...parameters) throws Exception{
		
		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection(); 
			stmt = conn.prepareStatement(sql);
			stmt = buildParameters(stmt, parameters);
        	result = stmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(stmt,conn);
		}	
		return result;
	}
	
	/**
     * @Title: executeUpdate
     * @Description: 执行一个不是查询语句的SQL，如：添加、删除、更新语句
     * @param sql(一个添加、删除、更新语句)
     * @return 返回影响的行数
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static int executeNonQuery(String sql,Map<Integer, Object> parameters) throws Exception{
		
		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection(); 
			stmt = conn.prepareStatement(sql);
			stmt = buildParameters(stmt, parameters);
        	result = stmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(stmt,conn);
		}	
		return result;
	}
	
	/**
     * @Title: executeFunc
     * @Description: 执行一个函数	="{? = call sp_add_emp2(?,?,?,?,?)}";  
     * @param sql(一个函数名称)
     * @param parameters(参数列表)
     * @return 返回输出的参数
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static Object executeFunc(String sql) throws Exception{
		return executeFunc(sql, (Object[])null);
	}
	
	/**
     * @Title: executeFunc
     * @Description: 执行一个函数	="{? = call sp_add_emp2(?,?,?,?,?)}";  
     * @param sql(一个函数名称)
     * @param parameters(参数列表)
     * @return 返回输出的参数
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static Object executeFunc(String sql, Map<Integer,Object> parameters) throws Exception{
		
		Object result = 0;
		CallableStatement cstmt = null;
		Connection conn = null;
		
		try {
			conn = getConnection(); 
			conn.setAutoCommit(false);  
			cstmt = conn.prepareCall(sql);
			cstmt = buildParameters(cstmt, parameters);
			cstmt.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(cstmt,conn);
		}	
		return result;
	}
	
	/**
     * @Title: executeFunc
     * @Description: 执行一个函数	="{? = call sp_add_emp2(?,?,?,?,?)}";  
     * @param sql(一个函数名称)
     * @param parameters(参数列表)
     * @return 返回输出的参数
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static Object executeFunc(String sql, Object ...parameters) throws Exception{
		
		Object result = 0;
		CallableStatement cstmt = null;
		Connection conn = null;
		
		try {
			conn = getConnection(); 
			conn.setAutoCommit(false);  
			cstmt = conn.prepareCall(sql);
			cstmt = buildParameters(cstmt, parameters);
			cstmt.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(cstmt,conn);
		}	
		return result;
	}

	/**
     * @Title: executeProc
     * @Description: 执行一个存储过程	="{call sp_add_emp2(?,?,?,?,?)}";  
     * @param sql(一个存储过程名称)
     * @param parameters(参数列表)
     * @param outParamName(输出参数名称，可以为空。)
     * @param outParamType(输出参数类型，参考Types类中的类型。)
     * @return 返回输出的参数
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static Object executeProc(String sql,Map<Integer,Object> parameters,
			int outParamIndex,int outParamType) throws Exception{
		
		Object result = 0;
		CallableStatement cstmt = null;
		Connection conn = null;
		
		try {
			conn = getConnection(); 
			conn.setAutoCommit(false);  
			cstmt = conn.prepareCall(sql);
			Set<Integer> keys = parameters.keySet();
			for(Integer key : keys){
				cstmt.setObject (key, parameters.get(key));
			}
			if(outParamIndex >= 1 && outParamIndex <= keys.size()){
				cstmt.registerOutParameter(outParamIndex, outParamType);
			}
			cstmt.execute();
			if(outParamIndex >= 1 && outParamIndex <= keys.size()){
				result = cstmt.getObject(outParamIndex);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(cstmt,conn);
		}	
		return result;
	}
	
	/**
     * @Title: executeProc
     * @Description: 执行一个存储过程	="{call sp_add_emp2(?,?,?,?,?)}";  
     * @param sql(一个存储过程名称)
     * @param parameters(参数列表)
     * @param outParamName(输出参数名称，可以为空。)
     * @param outParamType(输出参数类型，参考Types类中的类型。)
     * @return 返回输出的参数
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static Object executeProc(String sql,List<TKey<Integer,Object>> parameters,
			int outParamIndex,int outParamType) throws Exception{
		
		Object result = 0;
		CallableStatement cstmt = null;
		Connection conn = null;
		
		try {
			conn = getConnection(); 
			conn.setAutoCommit(false);  
			cstmt = conn.prepareCall(sql);
			for(TKey<Integer,Object> param : parameters){
				cstmt.setObject(param.getKey(), param.getValue());
			}
			if(outParamIndex >= 1 && outParamIndex <= parameters.size()){
				cstmt.registerOutParameter(outParamIndex, outParamType);
			}
			cstmt.execute();
			if(outParamIndex >= 1 && outParamIndex <= parameters.size()){
				result = cstmt.getObject(outParamIndex);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(cstmt,conn);
		}	
		return result;
	}
	
	/**
     * @Title: executeUpdate
     * @Description: 批量执行一个不是查询语句的SQL，如：添加、删除、更新语句
     * @param list(一个添加、删除、更新语句)
     * @return 批量返回影响的行数
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static boolean executeNonQuery(List<String> list) throws Exception{
		return executeNonQuery(list,true);
	}
	
	/**
     * @Title: executeUpdate
     * @Description: 批量执行一个不是查询语句的SQL，如：添加、删除、更新语句
     * @param list(一个添加、删除、更新语句)	transCommit(事物提交)
     * @return 批量返回影响的行数
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static boolean executeNonQuery(List<String> list,boolean transCommit) throws Exception{

		String tempsql = "";
		boolean result = false;
		Statement stmt = null;
		Connection conn = null;
		
		try {
			conn = getConnection(); 
			
			if(transCommit){
				conn.setAutoCommit(false);
			}
			
			for(String sql : list) {
				tempsql = sql;
				stmt = conn.prepareStatement(sql);
				stmt.executeUpdate(sql);
			}
			
        	if(transCommit){
        		conn.commit();
        		result = true;
        	}
		}catch (Exception e) {
			if(transCommit){
				conn.rollback();
			}
			e.printStackTrace();
			System.out.print(tempsql);
		}finally{
			closeAll(stmt,conn);
		}	
		return result;
	}
	
	/**
     * @Title: executeScalar
     * @Description: 获得单个数据
     * @param sql
     * @return 返回单个数据
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static Object executeScalar(String sql) throws Exception{
		return executeScalar(sql, (Object[])null);
	}
	
	/**
     * @Title: executeScalar
     * @Description: 获得单个数据
     * @param sql
     * @return 返回单个数据
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static Object executeScalar(String sql, Object ...parameters) throws Exception{

		Object result = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = null;
		 
		try {
			conn = getConnection(); 
			stmt = conn.prepareStatement(sql);
			stmt = buildParameters(stmt, parameters);
			rs = stmt.executeQuery();
			
			if(rs.next()){
				result = rs.getObject(1);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(rs,stmt,conn);
        }
		return result;
	}
	
	/**
     * @Title: executeScalar
     * @Description: 获得单个数据
     * @param sql
     * @return 返回单个数据
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static Object executeScalar(String sql, Map<Integer, Object> parameters) throws Exception{

		Object result = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = null;
		 
		try {
			conn = getConnection(); 
			stmt = conn.prepareStatement(sql);
			stmt = buildParameters(stmt, parameters);
			rs = stmt.executeQuery();
			
			if(rs.next()){
				result = rs.getObject(1);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(rs,stmt,conn);
        }
		return result;
	}
	
	/**
     * @Title: getMetadata
     * @Description: 获得表结构
     * @param sql
     * @return 返回表结构
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static List<KeyValuePair> getMetadata(String sql) throws Exception{

		ResultSet rs = null;
		PreparedStatement stmt = null;
		ResultSetMetaData rsmd = null;
		Connection conn = null;
		List<KeyValuePair> list = new ArrayList<KeyValuePair>();
		 
		try {
			conn = getConnection(); 
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			rsmd = rs.getMetaData();
			for(int i = 0; i < rsmd.getColumnCount(); i++){
				KeyValuePair kv = new KeyValuePair();
				kv.setKey(String.valueOf(i));
				kv.setValue(rsmd.getColumnName(i));
				list.add(kv);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(rs,stmt,conn);
        }
		return list;
	}
	
	
	/**
     * @Title: getEntity
     * @Description: 获得单条记录
     * @param sql(一个添加、删除、更新语句)	action(加载数据接口)
     * @return 返回单条记录的实体对象
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static<T> T getEntity(String sql,ILoadData action) throws Exception{
		return getEntity(sql, action, (Object)null);
	}
	
	/**
     * @Title: getEntity
     * @Description: 获得单条记录
     * @param sql(一个添加、删除、更新语句)	action(加载数据接口)
     * @return 返回单条记录的实体对象
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static<T> T getEntity(String sql,ILoadData action, Object ...parameters) throws Exception{

		T entity = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = null;
		 
		try {
			conn = getConnection(); 
			stmt = conn.prepareStatement(sql);
			stmt = buildParameters(stmt, parameters);
			rs = stmt.executeQuery();
			
			if(rs.next()){
				entity = action.loadData(rs);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(rs,stmt,conn);
        }
		return entity;
	}
	
	/**
     * @Title: getEntity
     * @Description: 获得单条记录
     * @param sql(一个添加、删除、更新语句)	action(加载数据接口)
     * @return 返回单条记录的实体对象
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static<T> T getEntity(String sql,ILoadData action, Map<Integer, Object> parameters) throws Exception{

		T entity = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = null;
		 
		try {
			conn = getConnection(); 
			stmt = conn.prepareStatement(sql);
			stmt = buildParameters(stmt, parameters);
			rs = stmt.executeQuery();
			
			if(rs.next()){
				entity = action.loadData(rs);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(rs,stmt,conn);
        }
		return entity;
	}
	
	/**
     * @Title: getList
     * @Description: 执行多条记录的对象集合
     * @param sql(一个添加、删除、更新语句)	action(加载数据接口)
     * @return 返回多条记录的对象集合
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static<T> List<T> getList(String sql,ILoadData action) throws Exception{
		return getList(sql, action, (Object[])null);
	}
	
	/**
     * @Title: getList
     * @Description: 执行多条记录的对象集合
     * @param sql(一个添加、删除、更新语句)	action(加载数据接口)
     * @return 返回多条记录的对象集合
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static<T> List<T> getList(String sql,ILoadData action, Object ...parameters) throws Exception{

		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = null;
		List<T> list = new ArrayList<T>();
		 
		try {
			conn = getConnection(); 
			stmt = conn.prepareStatement(sql);
			stmt = buildParameters(stmt, parameters);
			rs = stmt.executeQuery();
			while(rs.next()){
				T entity = action.loadData(rs);
				list.add(entity);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(rs,stmt,conn);
        }
		return list;
	}
	
	/**
     * @Title: getList
     * @Description: 执行多条记录的对象集合
     * @param sql(一个添加、删除、更新语句)	action(加载数据接口)
     * @return 返回多条记录的对象集合
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static<T> List<T> getList(String sql,ILoadData action, Map<Integer, Object> parameters) throws Exception{

		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = null;
		List<T> list = new ArrayList<T>();
		 
		try {
			conn = getConnection(); 
			stmt = conn.prepareStatement(sql);
			stmt = buildParameters(stmt, parameters);
			rs = stmt.executeQuery();
			while(rs.next()){
				T entity = action.loadData(rs);
				list.add(entity);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(rs,stmt,conn);
        }
		return list;
	}
	
	/**
     * @Title: getList
     * @Description: 执行多条记录的对象集合
     * @param sql(一个添加、删除、更新语句)	action(加载数据接口)
     * @return 返回多条记录的对象集合
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static<T> Map<String,List<T>> getMapList(String sql, String keyName,ILoadData action) throws Exception{

		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = null;
		List<T> list = null;
		Map<String,List<T>> maplist = new HashMap<String, List<T>>();
		 
		try {
			conn = getConnection(); 
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				T entity = action.loadData(rs);
				if(maplist.containsKey(rs.getObject(keyName))){
					list = maplist.get(rs.getObject(keyName));
					list.add(entity);
					
				}else{
					list = new ArrayList<T>();
					list.add(entity);
				}
				maplist.put(rs.getObject(keyName).toString(), list);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(rs,stmt,conn);
        }
		return maplist;
	}
	
	/**
     * @Title: getMap
     * @Description: 将查询出来的记录以map的方式进行存放
     * @param sql(一个查询的SQL语句)
     * @return 返回一条以map方式存储的记录
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static Map<String,Object> getMapByHorizontal(String sql) throws Exception{

		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		PreparedStatement stmt = null;
		Connection conn = null;
		Map<String,Object> map = new HashMap<String,Object>();
		 
		try {
			conn = getConnection(); 
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			rsmd = rs.getMetaData();
			while(rs.next()){
 				for (int i = 2; i <= rsmd.getColumnCount(); i++) {
 					if(Util.isNull(rs.getObject(i))) continue;
 					map.put(rs.getObject(i).toString(),rs.getObject(1));
 				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(rs,stmt,conn);
        }
		return map;
	}
	
	/**
     * @Title: getMapBySeparator
     * @Description: 将查询出来的记录以map的方式进行存放
     * @param sql(一个查询的SQL语句)
     * @return 返回一条以map方式存储的记录
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static Map<String,Object> getMapByByFuzzyMatching(String sql,String keyName,String valueName,String separator) throws Exception{

		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = null;
		Map<String,Object> map = new HashMap<String,Object>();
		 
		try {
			conn = getConnection(); 
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
 				if(Util.isNull(rs.getObject(valueName))) continue;
 				String[] keys = rs.getObject(valueName).toString().split(separator);
 				for(String key : keys){
 					if(Util.isNull(key)) continue;
 					map.put(key, rs.getObject(keyName));
 				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(rs,stmt,conn);
        }
		return map;
	}
	
	/**
     * @Title: getMap
     * @Description: 将查询出来的记录以map的方式进行存放
     * @param sql(一个查询的SQL语句)
     * @return 返回一条以map方式存储的记录
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static Map<String,Object> getMap(String sql) throws Exception{

		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		PreparedStatement stmt = null;
		Map<String,Object> map = null;
		Connection conn = null;
		 
		try {
			conn = getConnection(); 
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			rsmd = rs.getMetaData();
			if(rs.next()){
				map = new HashMap<String,Object>();
 				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
 					map.put(rsmd.getColumnName(i), rs.getObject(i));
 				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(rs,stmt,conn);
        }
		return map;
	}
	
	/**
     * @Title: getMaps
     * @Description: 执行多条记录的对象集合
     * @param sql(一个查询语句)	action(加载数据接口)
     * @return 返回多条记录的对象集合
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static<T> Map<String,T> getMaps(String sql,String keyName,ILoadData action) throws Exception{

		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = null;
		Map<String,T> maps = new HashMap<String, T>();
		 
		try {
			conn = getConnection(); 
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				T entity = action.loadData(rs);
				maps.put(rs.getObject(keyName).toString(), entity);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(rs,stmt,conn);
        }
		return maps;
	}
	
	/**
	 * 
	* @Title: getEISBNMaps   
	* @Description: 检测验证EISBN 
	* @param @param sql
	* @param @return
	* @param @throws Exception      
	* @return Map<String,String>   
	* @author zhaojie
	* @date 2014-4-17 上午11:25:39 
	* @throws
	 */
	public static<T> Map<String,String> getEISBNMaps(String sql) throws Exception{

		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = null;
		Map<String,String> maps = new HashMap<String, String>();
		 
		try {
			conn = getConnection(); 
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				maps.put(rs.getObject("RowIndex").toString(), rs.getObject("EISBN").toString());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(rs,stmt,conn);
        }
		return maps;
	}
	
	/*public static<T> Map<String,String> getMaps(String sql,String keyName,String FieldName) throws Exception{

		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = null;
		Map<String,String> maps = new HashMap<String, String>();
		 
		try {
			conn = getConnection(); 
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				maps.put(rs.getObject(keyName).toString(), rs.getObject(FieldName).toString());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(conn,stmt,rs);
        }
		return maps;
	}*/
	
	/**
     * @Title: getMapsMap
     * @Description: 执行多条记录的对象集合
     * @param sql(一个查询语句)	action(加载数据接口)
     * @return 返回多条记录的对象集合
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static Map<String,Map<String,Object>> getMapsMap(String sql) throws Exception{

		ResultSet rs = null;
		PreparedStatement stmt = null;
		ResultSetMetaData rsmd = null;
		Connection conn = null;
		Map<String,Map<String,Object>> maps = new HashMap<String, Map<String,Object>>();
		 
		try {
			conn = getConnection(); 
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			rsmd = rs.getMetaData();
			while(rs.next()){
				Map<String,Object> map = new HashMap<String,Object>();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
 					map.put(rsmd.getColumnName(i), rs.getObject(i));
 				}
				maps.put(rs.getString(1), map);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(rs,stmt,conn);
        }
		return maps;
	}
	
	/**
     * @Title: getListMap
     * @Description: 将查询出来的记录以List<Map>的方式进行存放
     * @param sql(一个查询的SQL语句)
     * @return 返回多条以List<Map>方式存储的记录集
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static List<Map<String,Object>> getListMap(String sql) throws Exception{

		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		PreparedStatement stmt = null;
		Connection conn = null;
		
		List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
		 
		try {
			conn = getConnection(); 
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			rsmd = rs.getMetaData();
			while(rs.next()){
				Map<String,Object> map = new HashMap<String,Object>();
 				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
 					map.put(rsmd.getColumnName(i), rs.getObject(i));
 				}
 				listMap.add(map);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(rs,stmt,conn);
        }
		return listMap;
	}
	
	/**
     * @Title: getListMap
     * @Description: 将查询出来的记录以List<Map>的方式进行存放
     * @param sql(一个查询的SQL语句)
     * @return 返回多条以List<Map>方式存储的记录集
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static List<Map<String,Object>> getListMap(String sql, List<String> keyList, String KeyName) throws Exception{

		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		PreparedStatement stmt = null;
		Connection conn = null;
		
		List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
		 
		try {
			conn = getConnection(); 
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			rsmd = rs.getMetaData();
			while(rs.next()){
				Map<String,Object> map = new HashMap<String,Object>();
 				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
 					map.put(rsmd.getColumnName(i), rs.getObject(i));
 				}
 				listMap.add(map);
 				keyList.add(rs.getNString(KeyName));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(rs,stmt,conn);
        }
		return listMap;
	}
	
	/**
     * @Title: buildParameters
     * @Description: 构建参数列表
     * @param stmt
     * @param parameters 参数列表
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static CallableStatement buildParameters(CallableStatement cstms, Map<Integer,Object> parameters) throws Exception{
		if(Util.isNull(parameters)) return cstms;
		Set<Integer> keys = parameters.keySet();
		for(int key : keys){
			cstms.setObject(key, parameters.get(key));
		}
		return cstms;
	}
	
	/**
     * @Title: buildParameters
     * @Description: 构建参数列表
     * @param stmt
     * @param parameters 参数列表
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static CallableStatement buildParameters(CallableStatement cstms, Object... parameters) throws Exception{
		if(Util.isNull(parameters)) return cstms;
		if(parameters.length == 1 && parameters[0] == null) return cstms;
		int parameterIndex = 1;
		for(Object param : parameters){
			cstms.setObject(parameterIndex, param);
			parameterIndex++;
		}
		return cstms;
	}
	
	/**
     * @Title: buildParameters
     * @Description: 构建参数列表
     * @param stmt
     * @param parameters 参数列表
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static PreparedStatement buildParameters(PreparedStatement stmt, Map<Integer,Object> parameters) throws Exception{
		if(Util.isNull(parameters)) return stmt;
		Set<Integer> keys = parameters.keySet();
		for(int key : keys){
			stmt.setObject(key, parameters.get(key));
		}
		return stmt;
	}
	
	/**
     * @Title: buildParameters
     * @Description: 构建参数列表
     * @param stmt
     * @param parameters 参数列表
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static PreparedStatement buildParameters(PreparedStatement stmt, Object... parameters) throws Exception{
		if(Util.isNull(parameters)) return stmt;
		if(parameters.length == 1 && parameters[0] == null) return stmt;
		int parameterIndex = 1;
		for(Object param : parameters){
			stmt.setObject(parameterIndex, param);
			parameterIndex++;
		}
		return stmt;
	}
	
	/**
     * @Title: close
     * @Description: 关闭Connection对象
     * @param conn(Connection对象)
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static void close(Connection conn) throws Exception{ 
        if (conn == null || conn.isClosed()) return;
        
        try { 
            conn.close(); 
        } catch (SQLException e) { 
        	e.printStackTrace();
        } 
	} 

	/**
     * @Title: close
     * @Description: 关闭ResultSet对象
     * @param rs(ResultSet对象)
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static void close(ResultSet rs) throws Exception{ 
        if (rs == null || rs.isClosed()) return;
        
        try { 
            rs.close(); 
        } catch (SQLException e) { 
        	e.printStackTrace();
        } 
	} 

	/**
     * @Title: close
     * @Description: 关闭Statement对象
     * @param stmt(Statement对象)
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static void close(Statement stmt) throws Exception{ 
        if (stmt == null || stmt.isClosed()) return;
        
        try { 
            stmt.close(); 
        } catch (SQLException e) { 
        	e.printStackTrace();
        } 
	}
	
	/**
     * @Title: close
     * @Description: 关闭CallableStatement对象
     * @param stmt(Statement对象)
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static void close(CallableStatement  cstmt) throws Exception{ 
        if (cstmt == null || cstmt.isClosed()) return;
        
        try { 
        	cstmt.close(); 
        } catch (SQLException e) { 
        	e.printStackTrace();
        } 
	}
	
	/**
	 * @throws Exception 
     * @Title: closeAll
     * @Description: 关闭所有对象对象
     * @param objects(Connection、Statement、ResultSet)
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static void closeAll(Object ...objects) throws Exception{
		for (Object o : objects) { 
            if (o instanceof Connection) close((Connection) o); 
            if (o instanceof Statement) close((Statement) o); 
            if (o instanceof ResultSet) close((ResultSet) o); 
            if (o instanceof CallableStatement ) close((CallableStatement) o); 
		}
	}
	
	/**
     * @Title: rebuildInParamsByString
     * @Description: 重新构建SQL的IN查询参数
     * @param str 字符串
     * @return 返回符合SQL的参数值
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static String rebuildInParamsByString(String str){
		return rebuildInParamsByString(str, ",");
	}
	
	/**
     * @Title: rebuildInParamsByString
     * @Description: 重新构建SQL的IN查询参数
     * @param str(字符串)	separator(字符串的分隔符)
     * @return 返回符合SQL的参数值
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static String rebuildInParamsByString(String str,String separator){
		if(Util.isNull(str)) return str;
		if(Util.isNull(separator)) separator = ",";
		
		String[] arys = str.split(separator);
		
		if(arys.length <= 0) return str;
		
		StringBuffer strBufTemp = new StringBuffer();
		
		for(int i = 0; i < arys.length; i++){
			if(Util.isNull(arys[i])) continue;
			strBufTemp.append("'");
			strBufTemp.append(arys[i]);
			strBufTemp.append("'");
			if(i < arys.length -1){
				strBufTemp.append(",");
			}
		}
		if(false == strBufTemp.toString().endsWith(",")){
			return strBufTemp.toString();
		} else{
			return strBufTemp.toString().substring(0, strBufTemp.toString().length()-1);
		}
	}

	/**
     * @Title: rebuildInParamsByList
     * @Description: 重新构建SQL的IN查询参数
     * @return 返回已经构建好的IN查询参数值
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static String rebuildInParamsByList(List<String> list){
		
		String conditions = "";
		
		if(list.size() <= 0)
			return conditions;
		
		if(list.size() == 1){
			conditions =  "'" + list.get(0) + "'";
			return conditions;
		}
		
		StringBuffer strBufTemp = new StringBuffer();
		
		for(int i = 0; i < list.size(); i++){
			if(Util.isNull(list.get(i))) continue;
			strBufTemp.append("'");
			strBufTemp.append(list.get(i));
			strBufTemp.append("'");
			if(i < list.size() -1){
				strBufTemp.append(',');
			}
		}
		if(false == strBufTemp.toString().endsWith(",")){
			return strBufTemp.toString();
		} else{
			return strBufTemp.toString().substring(0, strBufTemp.toString().length()-1);
		}
	}

	/**
     * @Title: getSafetyParam
     * @Description: 获得安全的参数
     * @return 返回已经构建好的安全参数
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static String getSafetyParam(String value){
		if(value == null) return "";
		String param = value.trim();
		if(param.isEmpty()) return "";
		return param.replace("'", "''");
	}
	
	/**
     * @Title: getSafetyParam
     * @Description: 获得安全的参数
     * @return 返回已经构建好的安全参数
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static String getSafetyParam(Object value){
		if(value == null) return "";
		String param = value.toString().trim();
		if(param.isEmpty()) return "";
		return param.replace("'", "''");
	}
}