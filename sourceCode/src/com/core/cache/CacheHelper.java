package com.core.cache;

import java.util.*;

import com.util.GetConfig;
import com.util.Util;

/**
 * @ClassName: CacheHelper 
 * @Description: 缓存类
 * @author:  tanyi
 * @date: 2014-01-16 14:11:04
 */ 
public class CacheHelper {
	
	/**
	 * @Description: // 1000 * 60 * 60 * 8; 默认8小时清理一次。
	 */ 
	private static long period = 28800000;
	
	/**
	 * @Description: 缓存时间计量器
	 */ 
	private static Map<String, Date> timeCache = 
			new HashMap<String, Date>();
	
	/**
	 * @Description: 缓存对象
	 */ 
	private static Map<String,Map<String, Object>> dataCache =
			new HashMap<String,Map<String, Object>>();
	
	/**
	 * @Description: 初始化
	 */ 
	static{
		// 获得配置文件中设置的缓存最大时间
		int cachePeriod = GetConfig.getCachePeriod();
		period = 1000 * 60 * cachePeriod;
		
		Timer time = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				
				if(dataCache.size() <= 0) return;
				
				Set<String> timeCacheKeys = timeCache.keySet();
				List<String> timekeys = new ArrayList<String>();
				
				// 清理数据缓存缓存
				for(String key : timeCacheKeys){
					Date currentDate = new Date();
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(timeCache.get(key));
					calendar.add(Calendar.SECOND, (int)(period / 1000));
					// 如果缓存到期，则开始进行清理操作。
					if(calendar.getTime().before(currentDate)){
						dataCache.remove(key);
						timekeys.add(key);
						System.out.println("dataCache:" + key);
					}
				}
				
				if(timekeys.size() <= 0) return;
				// 清理记录缓存的时间
				for(String key : timekeys){
					timeCache.remove(key);
					System.out.println("timeCache:" + key);
				}
			}
		};
		
		// 设置10秒后开始清理缓存，并每隔指定的周期进行定期清理操作。
		time.schedule(task, 1000, period);
	}
	
	/**
	 * @ClassName: add 
	 * @Description: 将一个map对象添加到缓存中
	 * @param map(需要缓存的map对象)
	 * @return 返回缓存的编号
	 * @author:  tanyi
	 * @date: 2014-01-16 14:11:04
	 */ 
	public static String add(Map<String,Object> map){
		String cacheId = UUID.randomUUID().toString();
		add(cacheId,map);
		return cacheId;
	}
	
	/**
	 * @ClassName: add 
	 * @Description: 将一个map对象添加到缓存中
	 * @param cacheId(指定缓存编号) map(需要缓存的map对象)
	 * @author:  tanyi
	 * @date: 2014-01-16 14:11:04
	 */ 
	public static void add(final String cacheId ,Map<String,Object> map){
		dataCache.put(cacheId,map);
		timeCache.put(cacheId, new Date());
	}
	
	/**
	 * @ClassName: add 
	 * @Description: 将一个对象添加到一个缓存的map对象中
	 * @param cacheId(指定缓存编号)	objId(对象编号) obj(对象)
	 * @author:  tanyi
	 * @date: 2014-01-16 14:11:04
	 */ 
	public static void add(String cacheId,String objId,Object obj){
		Map<String,Object> map;
		if(false == dataCache.containsKey(cacheId)){
			map = new HashMap<String, Object>();
			dataCache.put(cacheId, map);
		}else{
			map = dataCache.get(cacheId);
		}
		map.put(objId, obj);
	}
	
	/**
	 * @ClassName: remove 
	 * @Description: 移除一个缓存map对象
	 * @param cacheId(指定缓存编号)
	 * @author:  tanyi
	 * @date: 2014-01-16 14:11:04
	 */ 
	public static void remove(String cacheId){
		if(dataCache.containsKey(cacheId)){
			dataCache.remove(cacheId);
		}
		if(timeCache.containsKey(cacheId)){
			timeCache.remove(cacheId);
		}
	}
	
	/**
	 * @ClassName: remove 
	 * @Description: 移除一个缓存map中的一个对象
	 * @param cacheId(指定缓存编号)	objId(对象编号)
	 * @author:  tanyi
	 * @date: 2014-01-16 14:11:04
	 */ 
	public static void remove(String cacheId,String objId){
		if(false == dataCache.containsKey(cacheId)) return;
		Map<String,Object> map = dataCache.get(cacheId);
		if(map.containsKey(objId)){
			map.remove(objId);
		}
	}
	
	/**
	 * @ClassName: get 
	 * @Description: 得到一个缓存map对象
	 * @param cacheId(指定缓存编号)
	 * @author:  tanyi
	 * @date: 2014-01-16 14:11:04
	 */
	public static Map<String,Object> get(String cacheId){
		return get(cacheId,false);
	}
	
	/**
	 * @ClassName: get 
	 * @Description: 得到一个缓存map对象,并确定得到后是否删除这个缓存对象。
	 * @param cacheId(指定缓存编号)	isRemove(得到后是否删除这个缓存对象)
	 * @author:  tanyi
	 * @date: 2014-01-16 14:11:04
	 */
	public static Map<String,Object> get(String cacheId,boolean isRemove){
		Map<String, Object> map = new HashMap<String, Object>();
		if(Util.isNull(cacheId)) {
			return map;
		}
		if(dataCache.containsKey(cacheId)){
			map = dataCache.get(cacheId);
			if(isRemove){
				remove(cacheId);
			}
			return map;
		} else{
			return map;
		}
	}
	
	/**
	 * @ClassName: get 
	 * @Description: 得到一个缓存map中其中的一个对象
	 * @param cacheId(指定缓存编号)	objId(map对象中的以个对象)
	 * @author:  tanyi
	 * @date: 2014-01-16 14:11:04
	 */
	public static Object get(String cacheId,String objId){
		return get(cacheId,objId,false);
	}
	
	/**
	 * @ClassName: get 
	 * @Description: 得到一个缓存map中其中的一个对象
	 * @param cacheId(指定缓存编号)	objId(map对象中的以个对象) isRemove(得到后是否删除这个对象)
	 * @author:  tanyi
	 * @date: 2014-01-16 14:11:04
	 */
	public static Object get(String cacheId,String objId,boolean isRemove){
		if(Util.isNull(cacheId) || Util.isNull(objId)){
			return new Object();
		}
		if(false == dataCache.containsKey(cacheId)){
			return new Object();
		}
		Map<String, Object> map = dataCache.get(cacheId);
		if(false == map.containsKey(objId)){
			return new Object();
		}
		Object obj = map.get(objId);
		if(isRemove) map.remove(objId);
		return obj;
	}
}