/**
* @Title: EHCacheInit.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.oneworld.core.ehcache   
* @Description: 
* @author gaoguangchao    
* @date 2014年6月26日 下午5:13:27   
* @version V1.0 
*/

package com.core.ehcache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.jfree.util.Log;

import com.core.dataload.LoadDicList;
import com.core.dataload.LoadDict;
import com.core.dataload.LoadModule;
import com.core.dataload.LoadOrgan;
import com.core.dataload.LoadResource;
import com.core.dataload.LoadSysParam;
import com.core.db.DBHelper;
import com.core.db.ILoadData;
import com.core.helper.module.ModuleHelper;
import com.core.helper.user.UserHelper;
import com.framework.entity.organ.Organ;
import com.framework.entity.resources.Resources;
import com.framework.entity.dict.DictList;
import com.framework.entity.dict.Dictmanager;
import com.framework.entity.module.ModuleManager;
import com.framework.entity.systemparam.Systemparam;
import com.framework.entity.user.UserInfoAndDataManager;
import com.util.Util;

/**
 * @ClassName: EHCacheCodeInit
 * @Description: 
 * @author gaoguangchao
 * @date 2014年6月26日 下午5:13:27
 *
 */

public class EHCacheInit{
	
	
	public static void initCode() throws Exception {
		System.out.println("***********"+Util.getNowDateStr()+"初始化字典集——开始*************");
		List<Dictmanager> dictManagerList=new ArrayList<Dictmanager>();
		List<Dictmanager> dictManagerListEng=new ArrayList<Dictmanager>();
		List<DictList> dictList=new ArrayList<DictList>();
		try {
        	StringBuffer sb = new StringBuffer();
 					
			sb.append("select a.DictId,a.DictListCode,");
			sb.append("REPLACE(REPLACE((CASE WHEN b.DictName IS NULL then a.DictName ELSE b.DictName END), CHAR(10), ''), CHAR(13), '') as DictName,");
			sb.append("a.ParentDictId,a.DictData1,a.DictData2,a.DictData3,a.DictData4,a.DictData5,");
			sb.append("a.BigImage,a.SmallImage,(CASE WHEN b.DictRemark IS NULL then a.DictRemark ELSE b.DictRemark END) as DictRemark");
			sb.append(",a.DictNumber,a.Status,a.CreatePerson,a.CreateTime,a.UpdatePerson,a.UpdateTime from t_dict a left JOIN ");
			sb.append("t_dictml b on (a.DictId=b.DictId and b.LanguageCode= ? )where a.Status=1");
 			
			String sql= sb.toString();
			
			StringBuffer sbl = new StringBuffer();
			
			sbl.append("select * from t_dictlist where Status!=99");
			
			
 			ILoadData dictData=new LoadDict();
 			ILoadData DicList=new LoadDicList();
 			
 			dictList=DBHelper.getList(sbl.toString(),DicList);
 			dictManagerList=DBHelper.getList(sql,dictData,Locale.CHINA.toString());
 			
 			for(DictList dictlist :dictList){
 				String DictListCode = dictlist.getDictListCode();
 				List<Dictmanager> list=new ArrayList<Dictmanager>();
 				for(Dictmanager dict :dictManagerList){
 					String DictListCodeOfDict = dict.getDictListCode();
 					if(Util.isNotNull(DictListCode)){
 						if(DictListCode.equals(DictListCodeOfDict)||DictListCode == DictListCodeOfDict){
 							list.add(dict);
 						}
 					}
 				}
// 				Map<String, String> map = new HashMap<String, String>();
// 				map.put(DictListCode, Locale.CHINA.toString());
 				EHCacheHelper.putElement("codeCache", DictListCode+"_"+Locale.CHINA.toString(), list);
 			}
 			//获取英文字典集
 			dictManagerListEng=DBHelper.getList(sql,dictData,Locale.US.toString());
 			
 			for(DictList dictlist :dictList){
 				String DictListCode = dictlist.getDictListCode();
 				List<Dictmanager> list=new ArrayList<Dictmanager>();
 				for(Dictmanager dict :dictManagerListEng){
 					String DictListCodeOfDict = dict.getDictListCode();
 					if(Util.isNotNull(DictListCode)){
 						if(DictListCode.equals(DictListCodeOfDict)||DictListCode == DictListCodeOfDict){
 							list.add(dict);
 						}
 					}
 				}
// 				Map<String, String> map = new HashMap<String, String>();
// 				map.put(DictListCode, Locale.US.toString());
 				EHCacheHelper.putElement("codeCache", DictListCode+"_"+Locale.US.toString(), list);
 			}
 			
		} catch (Exception e) {
			Log.error(e);
			System.out.println(e.getMessage());
			System.out.println("***********"+Util.getNowDateStr()+"初始化字典集——出错*************");
		}
		System.out.println("***********"+Util.getNowDateStr()+"初始化字典集——结束*************");
	}

	
	/**   
	* @Title: initUser   
	* @Description:人员信息加到缓存
	* @return void        
	* @author gaoguangchao
	 * @throws Exception 
	* @date 2014年6月27日 上午10:07:46 
	*   
	*/ 
	
	public static void initUser() throws Exception {
		
		System.out.println("***********"+Util.getNowDateStr()+"初始化人员——开始*************");
		
		String languageCode = "";
		List<UserInfoAndDataManager> list = new ArrayList<UserInfoAndDataManager>();
		List<UserInfoAndDataManager> listEng = new ArrayList<UserInfoAndDataManager>();
		//查询中文
		languageCode = Locale.CHINA.toString();
		list = UserHelper.getAllUserInfo(languageCode);
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("allUserCache", languageCode);
		EHCacheHelper.putElement("userCache", "allUserCache_zh_CN", list);
		for(UserInfoAndDataManager user :list){
			String userId = user.getUserId();
			Map<String, String> mapkey = new HashMap<String, String>();
			mapkey.put(userId, languageCode);
			EHCacheHelper.putElement("userCache", mapkey, user);
		}
		
		//查询英文
		languageCode = Locale.US.toString();
		listEng = UserHelper.getAllUserInfo(languageCode);
//		Map<String, String> mapEng = new HashMap<String, String>();
//		map.put("allUserCache", languageCode);
		EHCacheHelper.putElement("userCache", "allUserCache_en_US", listEng);
		for(UserInfoAndDataManager user :list){
			String userId = user.getUserId();
			Map<String, String> mapkey = new HashMap<String, String>();
			mapkey.put(userId, languageCode);
			EHCacheHelper.putElement("userCache", mapkey, user);
		}
		
		System.out.println("***********"+Util.getNowDateStr()+"初始化人员——结束*************");
		
	}


	
	/**   
	* @Title: initHome   
	* @Description:
	* @return void        
	* @author gaoguangchao
	* @date 2014年6月27日 下午12:29:10 
	*   
	*/ 
	
	public static void initHome() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 
	* @Title: initModule   
	* @Description:初始化模块信息
	* @return void        
	* @author gaoguangchao
	* @date 2014年8月28日 下午5:26:08 
	*
	 */
	public static void initModule() {
		System.out.println("***********"+Util.getNowDateStr()+"初始化模块——开始*************");
		List<ModuleManager> modulelist = new ArrayList<ModuleManager>();
		List<Resources> resourcelist = new ArrayList<Resources>();
		Map<String,Map<String,Object>> relamap = new HashMap<String,Map<String,Object>>();
		try{
			String sqlmodul= "SELECT * FROM t_module WHERE Status!=99";
			String sqlresource= "SELECT * FROM t_resources WHERE Status!=99";
			String sqlrela= "SELECT * FROM t_module_resource_rela ";
			ILoadData moduleData=new LoadModule();
			ILoadData resourceData = new LoadResource();
			modulelist = DBHelper.getList(sqlmodul,moduleData);
			resourcelist = DBHelper.getList(sqlresource,resourceData);
			relamap = DBHelper.getMapsMap(sqlrela);
			modulelist = ModuleHelper.iteratorMap(modulelist,resourcelist,relamap);
			EHCacheHelper.putElement("moduleCache", "allModule", modulelist);
			for(ModuleManager module:modulelist){
				String ModuleName = module.getModuleName();
				EHCacheHelper.putElement("moduleCache", ModuleName, module);
			}
		}catch(Exception e) {
			Log.error(e);
			System.out.println(e.getMessage());
			System.out.println("***********"+Util.getNowDateStr()+"初始模块——出错*************");
		}
		System.out.println("***********"+Util.getNowDateStr()+"初始化模块——结束*************");
		
	}

	
	/**   
	* @Title: initSysParam
	* @Description:
	* @return void        
	* @author lishanhe
	 * @throws Exception 
	* @date 2014年7月1日 下午2:44:25 
	*   
	*/ 
	
	public static void initSysParam() throws Exception {
		System.out.println("***********"+Util.getNowDateStr()+"初始化系统参数——开始*************");
		List<Systemparam> list = new ArrayList<Systemparam>();
		ILoadData systemParamLoad = new LoadSysParam();
		String sql = "SELECT * FROM t_system_param WHERE Status!=99";
		list = DBHelper.getList(sql, systemParamLoad);
		EHCacheHelper.putElement("sysParamCache", "allSystemParam", list);
		for(Systemparam i : list){
			String ParamName = i.getParamName();
			EHCacheHelper.putElement("sysParamCache", ParamName, i);
		}
		System.out.println("***********"+Util.getNowDateStr()+"初始化系统参数——结束*************");
	}


	public static void initResource() throws Exception {
		System.out.println("***********"+Util.getNowDateStr()+"初始化资源——开始*************");
		List<Resources> list = new ArrayList<Resources>();
		ILoadData resourcesLoad = new LoadResource();
		String sql = "SELECT * FROM t_resources WHERE Status!=99";
		list = DBHelper.getList(sql, resourcesLoad);
		EHCacheHelper.putElement("resourceCache", "allResources", list);
		for(Resources resource : list){
			String resourcesName = resource.getResourcesName();
			EHCacheHelper.putElement("resourceCache", resourcesName, resource);
		}
		System.out.println("***********"+Util.getNowDateStr()+"初始化资源——结束*************");
	}


	/**
	 * 
	* @Title: initOrgan   
	* @Description:初始化组织信息
	* @return void        
	* @author gaoguangchao
	 * @throws Exception 
	* @date 2014年9月9日 下午5:10:54 
	*
	 */
	public static void initOrgan() throws Exception {
		System.out.println("***********"+Util.getNowDateStr()+"初始化组织——开始*************");
		List<Organ> list = new ArrayList<Organ>();
		ILoadData LoadOrgan = new LoadOrgan();
		String sql = "SELECT * FROM t_organ WHERE Status!=99";
		list = DBHelper.getList(sql, LoadOrgan);
		EHCacheHelper.putElement("organCache", "allOrgans", list);
		for(Organ organ : list){
			String id = organ.getOrganId();
			EHCacheHelper.putElement("organCache", id, organ);
		}
		System.out.println("***********"+Util.getNowDateStr()+"初始化组织——结束*************");
		
	}
	
	
}
