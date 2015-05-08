/**
* @Title: EHCacheHelper.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.oneworld.core.ehcache   
* @Description: 
* @author gaoguangchao    
* @date 2014年6月26日 下午2:48:59   
* @version V1.0 
*/

package com.core.ehcache;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.jgroups.util.UUID;

import com.util.Util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * @ClassName: EHCacheHelper
 * @Description: EHCacheHelper工具类
 * @author gaoguangchao
 * @date 2014年6月26日 下午2:48:59
 *
 */

public class EHCacheHelper {
	
	private static final Logger log = Logger.getLogger(EHCacheHelper.class);
	
	
	 public static CacheManager manager;  
     
	    static{  
	        try {  
	            manager = CacheManager.getInstance();  
	            if(manager==null)  
	                manager = CacheManager.create();  
	        } catch (CacheException e) {  
	            log.fatal("Initialize cache manager failed.", e);  
	        }  
	    }  
	/**
	 * 
	* @Title: createCacheManager   
	* @Description:创建CacheManager对象
	* @return
	* @return CacheManager        
	* @author gaoguangchao
	* @date 2014年6月26日 下午3:10:48 
	*
	 */
	public static CacheManager createCacheManager(){
		return manager;
	}
	/**
	 * 
	* @Title: caloseCacheManager   
	* @Description:手动关闭CacheManager对象
	* @return
	* @return Boolean        
	* @author gaoguangchao
	* @date 2014年6月26日 下午4:28:00 
	*
	 */
	public static Boolean caloseCacheManager(){
		//关闭对象
		if(manager!=null)  
            manager.shutdown();
		
		return true;
	}
	/**
	 * 
	* @Title: getCacheNames   
	* @Description:获取所有CacheNames
	* @return
	* @return String[]        
	* @author gaoguangchao
	* @date 2014年6月26日 下午4:41:52 
	*
	 */
	public static String[] getCacheNames(){
		return manager.getCacheNames();
	}
	/**
	 * 
	* @Title: isNullCache   
	* @Description:判断cache对象是否为空
	* @param cache
	* @return
	* @return boolean        
	* @author gaoguangchao
	* @date 2014年6月26日 下午3:20:29 
	*
	 */
	public static boolean isNullCache(Cache cache){
		if(Util.isNull(cache)){
			log.info("未找到Cache对象");
			return true;
		}
		return false;
	}
	/**
	 * 
	* @Title: isNullCache   
	* @Description:判断名称为cacheName的cache对象是否为空
	* @param cacheName
	* @return
	* @return boolean        
	* @author gaoguangchao
	* @date 2014年6月26日 下午3:20:35 
	*
	 */
	public static boolean isNullCache(String cacheName){
		Cache cache = manager.getCache(cacheName);
		if(Util.isNull(cache)){
			log.info("未找到cacheName为:"+cacheName+"的Cache对象");
			return true;
		}
		return false;
	}
	/**
	 * 
	* @Title: addCache   
	* @Description:添加名称为CacheName的Cache到CacheManager中
	* @param CacheName
	* @return
	* @return boolean        
	* @author gaoguangchao
	* @date 2014年6月26日 下午4:19:52 
	*
	 */
	public static boolean addCacheBoolean(String CacheName){
		try {
			if(Util.isNull(CacheName)){
				return false;
			}
			manager.addCache(CacheName);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	/**
	 * 
	* @Title: addCache   
	* @Description:添加名称为CacheName的Cache到CacheManager中,返回新创建的Cache
	* @param CacheName
	* @return
	* @return Cache        
	* @author gaoguangchao
	* @date 2014年6月26日 下午4:26:20 
	*
	 */
	public static Cache addCache(String CacheName){
		Cache cache = null;
		try {
			if(Util.isNull(CacheName)){
				return null;
			}
			manager.addCache(CacheName);
			cache = manager.getCache(CacheName);
		} catch (Exception e) {
			return null;
		}
		return cache;
	}
	/**
	 * 
	* @Title: getCache   
	* @Description:根据cache名称获取Cache对象
	* @param CacheName
	* @return
	* @return Cache        
	* @author gaoguangchao
	* @date 2014年6月26日 下午3:10:52 
	*
	 */
	public static Cache getCache(String CacheName){
		Cache cache = manager.getCache(CacheName);
		if(!isNullCache(cache)){
			return cache;
		}else{
			return null;
		}
	}
	/**
	 * 
	* @Title: getCacheKeys   
	* @Description:获取名称为cacheName的Cache的键值
	* @param CacheName
	* @return
	* @return List        
	* @author gaoguangchao
	* @date 2014年6月26日 下午4:43:53 
	*
	 */
	public static List<?> getCacheKeys(String CacheName){
		Cache cache = manager.getCache(CacheName);
		if(!isNullCache(cache)){
			return cache.getKeys();
		}else{
			return null;
		}
	}
	/**
	 * 
	* @Title: putCache   
	* @Description:将Cache存入CacheManager中
	* @param cache
	* @return
	* @return boolean        
	* @author gaoguangchao
	* @date 2014年6月26日 下午3:10:55 
	*
	 */
	public static boolean putCache(Cache cache){
		try {
			manager.addCache(cache);
		} catch (Exception e) {
			log.error(e);
			return false;
		}
		return true;
	}
	/**
	 * 
	* @Title: cacheExists   
	* @Description:判断名称为cacheName的Cache是否存在
	* @param cacheName
	* @return
	* @return boolean        
	* @author gaoguangchao
	* @date 2014年6月26日 下午4:36:28 
	*
	 */
	public static boolean cacheExists(String cacheName){
		try {
			return manager.cacheExists(cacheName);
		} catch (Exception e) {
			log.error(e);
			return false;
		}
	}
	/**
	 * 
	* @Title: putElement   
	* @Description:将Element存入Cache中
	* @param cache
	* @param element
	* @return
	* @return boolean        
	* @author gaoguangchao
	* @date 2014年6月26日 下午3:10:58 
	*
	 */
	public static boolean putElement(Cache cache,Element element){
		try {
			if(!isNullCache(cache)){
				cache.put(element);
			}else{
				return false;
			}
		} catch (Exception e) {
			log.error(e);
			return false;
		}
		return true;
	}
	/**
	 * 
	* @Title: putElement   
	* @Description:将Element存入名称为cacheName的Cache中
	* @param cacheName
	* @param element
	* @return
	* @return boolean        
	* @author gaoguangchao
	* @date 2014年6月26日 下午3:11:01 
	*
	 */
	public static boolean putElement(String cacheName,Element element){
		try {
			Cache cache = getCache(cacheName);
			if(Util.isNotNull(cache)){
				cache.put(element);
			}else{
				return false;
			}
		} catch (Exception e) {
			log.error(e);
		}
		return true;
	}
	/**
	 * 
	* @Title: putElement   
	* @Description:将key为elementKey，value为elementValue 的Element存入名称为cacheName的Cache中
	* @param cacheName
	* @param elementKey
	* @param elementValue
	* @return
	* @return boolean        
	* @author gaoguangchao
	* @date 2014年6月26日 下午3:11:03 
	*
	 */
	public static boolean putElement(String cacheName,Object elementKey,Object elementValue){
		try {
			if(Util.isNull(elementKey)&&Util.isNull(elementValue)){
				return false;
			}
			if(Util.isNull(elementKey)){
				elementKey = UUID.randomUUID().toString();
			}
			Element element = new Element(elementKey, elementValue);
			Cache cache = getCache(cacheName);
			if(Util.isNotNull(cache)){
				cache.put(element);
			}else{
				return false;
			}
		} catch (Exception e) {
			log.error(e);
			return false;
		}
		return true;
	}
	/**
	 * 
	* @Title: putElement   
	* @Description:将value为elementValue 的Element存入名称为cacheName的Cache中，返回elementKey（UUID）,如果操作失败，返回""  
	* @param cacheName
	* @param elementValue
	* @return String      如果操作失败，返回""  
	* @author gaoguangchao
	* @date 2014年6月26日 下午3:11:06 
	*
	 */
	public static String putElement(String cacheName,Object elementValue){
		String elementKey = UUID.randomUUID().toString();
		try {
			if(Util.isNull(cacheName)&&Util.isNull(elementValue)){
				return "";
			}
			Element element = new Element(elementKey, elementValue);
			Cache cache = getCache(cacheName);
			if(Util.isNotNull(cache)){
				cache.put(element);
			}else{
				return "";
			}
		} catch (Exception e) {
			log.error(e);
			return "";
		}
		return elementKey;
	}
	/**
	 * 
	* @Title: putElement   
	* @Description:将value为elementValue 的Element存入Cache中，返回elementKey（UUID）,如果操作失败，返回""  
	* @param cache
	* @param elementValue
	* @return String     如果操作失败，返回""   
	* @author gaoguangchao
	* @date 2014年6月26日 下午3:11:09 
	*
	 */
	public static String putElement(Cache cache,Object elementValue){
		String elementKey = UUID.randomUUID().toString();
		try {
			if(Util.isNull(cache)&&Util.isNull(elementValue)){
				return "";
			}
			Element element = new Element(elementKey, elementValue);
			if(!isNullCache(cache)){
				cache.put(element);
			}else{
				return "";
			}
		} catch (Exception e) {
			log.error(e);
			return "";
		}
		return elementKey;
	}
	/**
	 * 
	* @Title: putElement   
	* @Description:将key为elementKey，value为elementValue 的Element存入名称为cacheName的Cache中
	* @param cache
	* @param elementKey
	* @param elementValue
	* @return
	* @return boolean        
	* @author gaoguangchao
	* @date 2014年6月26日 下午3:11:12 
	*
	 */
	public static boolean putElement(Cache cache,Object elementKey,Object elementValue){
		try {
			if(Util.isNull(elementKey)&&Util.isNull(elementValue)){
				return false;
			}
			Element element = new Element(elementKey, elementValue);
			if(!isNullCache(cache)){
				cache.put(element);
			}else{
				return false;
			}
		} catch (Exception e) {
			log.error(e);
			return false;
		}
		return true;
	}
	/**
	 * 
	* @Title: getElement   
	* @Description:获取名称为cacheName的Cache中的，key为Key的Element
	* @param cacheName
	* @param Key
	* @return Element    如果为失败或者没有值 返回null    
	* @author gaoguangchao
	* @date 2014年6月26日 下午3:35:01 
	*
	 */
	public static Element getElement(String cacheName,Object Key){
		Cache cache = getCache(cacheName);
		Element element = null;
		if(Util.isNotNull(cache)){
			element = cache.get(Key);
		}
		return element;
	}
	/**
	 * 
	* @Title: getElementValue   
	* @Description:获取获取名称为cacheName的Cache中的，key为Key的Element中的Value值
	* @param cacheName
	* @param Key
	* @return
	* @return Object        
	* @author gaoguangchao
	* @date 2014年6月26日 下午4:03:09 
	*
	 */
	public static Object getElementValue(String cacheName,Object Key){
		Cache cache = getCache(cacheName);
		Element element = null;
		if(Util.isNotNull(cache)){
			element = cache.get(Key);
		}
		if(Util.isNotNull(element)){
			return cache.get(Key).getObjectValue();
		}
		return null;
	}
	/**
	 * 
	* @Title: removeElement   
	* @Description:删除名称为cacheName的Cache中的，key为Key的Element
	* @param cacheName
	* @param Key
	* @return
	* @return boolean        
	* @author gaoguangchao
	* @date 2014年6月26日 下午3:48:29 
	*
	 */
	public static boolean removeElement(String cacheName,Object Key){
		Cache cache = getCache(cacheName);
		try {
			if(Util.isNotNull(cache)){
				cache.remove(Key);
			}else{
				return false;
			}
		} catch (Exception e) {
			log.error(e);
			return false;
		}
		return true;
	}
	/**
	 * 
	* @Title: removeAllElement   
	* @Description:删除名称为cacheName的Cache中的所有Element
	* @param cacheName
	* @return
	* @return boolean        
	* @author gaoguangchao
	* @date 2014年6月26日 下午3:49:17 
	*
	 */
	public static boolean removeAllElement(String cacheName){
		Cache cache = getCache(cacheName);
		try {
			if(Util.isNotNull(cache)){
				cache.removeAll();
			}else{
				return false;
			}
		} catch (Exception e) {
			log.error(e);
			return false;
		}
		return true;
	}
	/**
	 * 
	* @Title: removeAllElement   
	* @Description:删除名称为cacheName的Cache中的所有keys的Element
	* @param cache
	* @param keys
	* @return
	* @return boolean        
	* @author gaoguangchao
	* @date 2014年6月26日 下午4:39:35 
	*
	 */
	public static boolean removeAllElement(Cache cache, Collection<String> keys) {
		if(isNullCache(cache)){
			return false;
		}
		try {
			cache.removeAll(keys);
		} catch (Exception e) {
			log.error(e);
			return false;
		}
		return true;
	}
	/**
	 * 
	* @Title: removeCaches   
	* @Description:删除CacheManager下的所有Cache（慎用）
	* @return
	* @return boolean        
	* @author gaoguangchao
	* @date 2014年6月26日 下午4:08:57 
	*
	 */
	public static boolean removeCaches(){
		try {
			manager.removeAllCaches();
		} catch (Exception e) {
			log.error(e);
			return false;
		}
		return true;
	}
	/**
	 * 
	* @Title: removeCaches   
	* @Description:删除CacheManager下的名称为cacheName的Cache
	* @param cacheName
	* @return
	* @return boolean        
	* @author gaoguangchao
	* @date 2014年6月26日 下午4:10:02 
	*
	 */
	public static boolean removeCache(String cacheName){
		try {
			manager.removeCache(cacheName);
		} catch (Exception e) {
			log.error(e);
			return false;
		}
		return true;
	}
	
}
