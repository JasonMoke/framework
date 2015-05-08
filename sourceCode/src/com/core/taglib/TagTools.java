/**
* @Title: TagTools.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.framework.taglib   
* @Description:    
* @author guangchao    
* @date 2014-1-10 下午5:37:29   
* @version V1.0 
*/
package com.core.taglib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



import org.apache.struts2.ServletActionContext;



import org.jfree.util.Log;

import com.core.dataload.LoadDict;
import com.core.dataload.LoadUserInfo;
import com.core.db.DBHelper;
import com.core.db.ILoadData;
import com.core.ehcache.EHCacheHelper;
import com.framework.entity.organ.Organ;
import com.framework.entity.dict.Dictmanager;
import com.framework.entity.user.UserInfoAndDataManager;
import com.framework.entity.user.UserInfoManager;
import com.util.Util;

/**
 * @ClassName: TagTools
 * @Description: 
 * @author guangchao
 * @date 2014-1-10 下午5:37:29
 *
 */
public class TagTools {
	/**
	 * 
	* @Title: GetLanguageCode   
	* @Description:    获取当前语言环境
	* @param @return    
	* @return String      
	* @author guangchao
	* @date 2014-2-28 下午1:33:04 
	* @throws
	 */
	public static String GetLanguageCode()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(true);
		Locale locale = (Locale) session.getAttribute("WW_TRANS_I18N_LOCALE");
		if(locale==null){
			locale = Locale.CHINA;
		}
		return locale.toString();
	}
    
	/**
	 * 
	* @Title: getCodeList   
	* @Description:    获取codelist
	* @param @param codeName
	* @param @return
	* @param @throws Exception    
	* @return List<Dictmanager>      
	* @author guangchao
	* @date 2014-2-28 下午1:32:44 
	* @throws
	 */
	public static List<Dictmanager> getCodeList(String codeName) throws Exception {
		List<Dictmanager> dictManagerList=new ArrayList<Dictmanager>();
		try {
        	StringBuffer sb = new StringBuffer();
 					
			sb.append("select a.DictId,a.DictListCode,");
			sb.append("REPLACE(REPLACE((CASE WHEN b.DictName IS NULL then a.DictName ELSE b.DictName END), CHAR(10), ''), CHAR(13), '') as DictName,");
			sb.append("a.ParentDictId,a.DictData1,a.DictData2,a.DictData3,a.DictData4,a.DictData5,");
			sb.append("a.BigImage,a.SmallImage,(CASE WHEN b.DictRemark IS NULL then a.DictRemark ELSE b.DictRemark END) as DictRemark");
			sb.append(",a.DictNumber,a.Status,a.CreatePerson,a.CreateTime,a.UpdatePerson,a.UpdateTime from t_dict a left JOIN ");
			sb.append("t_dictml b on (a.DictId=b.DictId and b.LanguageCode= ? )where DictListCode= ? and a.Status=1");;
 			
			String sql= sb.toString();
 			ILoadData dictData=new LoadDict();
 			dictManagerList=DBHelper.getList(sql,dictData,GetLanguageCode(),codeName);
 			
		} catch (Exception e) {
			Log.error(e);
			System.out.println(e.getMessage());
		}
		return dictManagerList;
	}
	/**
	 * 
	* @Title: getCodeListFromCache   
	* @Description:从缓存中 获取codelist
	* @param codeName
	* @return
	* @throws Exception
	* @return List<Dictmanager>        
	* @author gaoguangchao
	* @date 2014年6月26日 下午6:19:22 
	*
	 */
	@SuppressWarnings("unchecked")
	public static List<Dictmanager> getCodeListFromCache(String codeName) throws Exception {
		List<Dictmanager> dictManagerList=new ArrayList<Dictmanager>();
		try {
//			Map<String, String> map = new HashMap<String, String>();
//			map.put(codeName, languageCode);
			dictManagerList = (List<Dictmanager>) EHCacheHelper.getElementValue("codeCache", codeName+"_"+GetLanguageCode());			
		} catch (Exception e) {
			Log.error(e);
			System.out.println(e.getMessage());
		}
		return dictManagerList;
	}
	/**
	 * 
	* @Title: getCodeValue   
	* @Description:    根据key获取value
	* @param @param codeValue
	* @param @param codeName
	* @param @return
	* @param @throws Exception    
	* @return Dictmanager      
	* @author guangchao
	* @date 2014-2-28 下午1:32:24 
	* @throws
	 */
	public static Dictmanager getCodeValue(String codeValue,String codeName) throws Exception {
		Connection conn=null;
		Dictmanager dictmanager = new Dictmanager();
		try {
			String sql="";
			if(codeValue.contains(",")){
				ResourceBundle p = ResourceBundle.getBundle("db");
				String driverClassName = p.getString("jdbc.driverClassName");
				String url = p.getString("jdbc.url");
				String jdbcusername = p.getString("jdbc.username");
				String jdbcpassword = p.getString("jdbc.password");
				Class.forName(driverClassName);
				conn=(Connection) DriverManager.getConnection(url,jdbcusername,jdbcpassword); 
				PreparedStatement pstmtCaption = null;	
				codeValue = Util.addMark(codeValue);
				sql = "select a.DictId,a.DictListCode,(CASE WHEN b.DictName IS NULL then a.DictName ELSE b.DictName END) as DictName,a.ParentDictId,a.DictData1,a.DictData2,a.DictData3,a.DictData4,a.DictData5,a.BigImage,a.SmallImage,(CASE WHEN b.DictRemark IS NULL then a.DictRemark ELSE b.DictRemark END) as DictRemark,a.DictNumber,a.Status,a.CreatePerson,a.CreateTime,a.UpdatePerson,a.UpdateTime from t_dict a left JOIN t_dictml b on (a.DictId=b.DictId and b.LanguageCode='"+ GetLanguageCode() +"')where a.DictListCode='"+ codeName +"' and a.DictData1 IN ("+ codeValue +") and a.Status=1 ";
				pstmtCaption=conn.prepareStatement(sql);
				
				ResultSet result =pstmtCaption.executeQuery();
				StringBuffer DictNameSb = new StringBuffer();
				StringBuffer DictData1Sb = new StringBuffer();
				StringBuffer DictRemarkSb = new StringBuffer();
				String DictName = "";
				String DictData1 = "";
				String DictRemark = "";
				while (result.next()) {
					DictNameSb.append(result.getString("DictName")).append(",");
					DictData1Sb.append(result.getString("DictData1")).append(",");
					DictRemarkSb.append(result.getString("DictRemark")).append(",");
				}
//				截取最后一个“，”
				DictName = DictNameSb.toString();
				if(DictName.length()>0){
					DictName = DictName.substring(0,DictName.length()-1);
				}
				DictData1 = DictData1Sb.toString();
				if(DictData1.length()>0){
					DictData1 = DictData1.substring(0,DictData1.length()-1);
				}
				DictRemark = DictRemarkSb.toString();
				if(DictRemark.length()>0){
					DictRemark = DictRemark.substring(0,DictRemark.length()-1);
				}
				dictmanager.setDictName(DictName);
				dictmanager.setDictData1(DictData1);
				dictmanager.setDictRemark(DictRemark);
			}else{
				sql="select a.DictId,a.DictListCode,(CASE WHEN b.DictName IS NULL then a.DictName ELSE b.DictName END) as DictName,a.ParentDictId,a.DictData1,a.DictData2,a.DictData3,a.DictData4,a.DictData5,a.BigImage,a.SmallImage,(CASE WHEN b.DictRemark IS NULL then a.DictRemark ELSE b.DictRemark END) as DictRemark,a.DictNumber,a.Status,a.CreatePerson,a.CreateTime,a.UpdatePerson,a.UpdateTime from t_dict a left JOIN t_dictml b on (a.DictId=b.DictId and b.LanguageCode= ?) where a.DictListCode= ? and a.DictData1= ? and a.Status=1 limit 1";
				ILoadData dictData=new LoadDict();
				dictmanager=DBHelper.getEntity(sql, dictData,GetLanguageCode(),codeName,codeValue);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Log.error(e);
		}
		return dictmanager;
	}
	/**
	 * 
	* @Title: getCodeValueFromCache   
	* @Description:从缓存中获取
	* @param codeValue
	* @param codeName
	* @return
	* @throws Exception
	* @return Dictmanager        
	* @author gaoguangchao
	* @date 2014年6月26日 下午6:19:44 
	*
	 */
	public static Dictmanager getCodeValueFromCache(String codeValue,String codeName) throws Exception {
		Dictmanager dictmanager = new Dictmanager();
		List<Dictmanager> CodeList =  getCodeListFromCache(codeName);
		if(codeValue.contains(",")){
			StringBuffer DictNameSb = new StringBuffer();
			StringBuffer DictData1Sb = new StringBuffer();
			StringBuffer DictRemarkSb = new StringBuffer();
			String DictNameStr = "";
			String DictData1Str = "";
			String DictRemarkStr = "";
			String[] codeValueArray = codeValue.split(",");
			for(String codeValueStr:codeValueArray){
				for(Dictmanager dict :CodeList){
					String DictData1 = dict.getDictData1();
					if(Util.isNotNull(codeValueStr)){
						if(codeValueStr.equals(DictData1)||codeValueStr==DictData1){
							DictNameSb.append(dict.getDictName()).append(",");
							DictData1Sb.append(dict.getDictData1()).append(",");
							DictRemarkSb.append(dict.getDictRemark()).append(",");
							break;
						}
					}
				}
			}
			DictNameStr = Util.subLastIndex(DictNameSb.toString(), ",");
			DictData1Str = Util.subLastIndex(DictData1Sb.toString(), ",");
			DictRemarkStr = Util.subLastIndex(DictRemarkSb.toString(), ",");
			dictmanager.setDictName(DictNameStr);
			dictmanager.setDictData1(DictData1Str);
			dictmanager.setDictRemark(DictRemarkStr);
		}else{
			for(Dictmanager dict :CodeList){
				String DictData1 = dict.getDictData1();
				if(Util.isNotNull(codeValue)){
					if(codeValue.equals(DictData1)||codeValue==DictData1){
						dictmanager = dict;
						break;
					}
				}
			}
		}
		return dictmanager;
	}
	/**
	 * 
	* @Title: getCodeNum   
	* @Description:    根据value值（DictData1）和DictListCode查询字典集里该记录的条数
	* @param @param codeValue
	* @param @param codeName
	* @param @return
	* @param @throws Exception    
	* @return int      
	* @author guangchao
	* @date 2014-2-28 下午1:47:35 
	* @throws
	 */
	public static int getCodeNum(String codeValue,String codeName) throws Exception {
		int rowCount = 0;
		try {
			String sql="";
			sql="select count(*) from (select a.DictId,a.DictListCode,(CASE WHEN b.DictName IS NULL then a.DictName ELSE b.DictName END) as DictName,a.ParentDictId,a.DictData1,a.DictData2,a.DictData3,a.DictData4,a.DictData5,a.BigImage,a.SmallImage,(CASE WHEN b.DictRemark IS NULL then a.DictRemark ELSE b.DictRemark END) as DictRemark,a.DictNumber,a.Status,a.CreatePerson,a.CreateTime,a.UpdatePerson,a.UpdateTime from t_dict a left JOIN t_dictml b on (a.DictId=b.DictId and b.LanguageCode= ? ))  t where t.DictListCode=?  and t.DictData1= ? and t.Status=1 ";
			rowCount = Util.toint(DBHelper.executeScalar(sql,GetLanguageCode(),codeName,codeValue));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return rowCount;
	}
	/**
	 * 
	* @Title: getCodeNumFromCache   
	* @Description:从缓存中 根据value值（DictData1）和DictListCode查询字典集里该记录的条数
	* @param codeValue
	* @param codeName
	* @return
	* @throws Exception
	* @return int        
	* @author gaoguangchao
	* @date 2014年6月27日 下午1:55:17 
	*
	 */
	public static int getCodeNumFromCache(String codeValue,String codeName) throws Exception {
		int rowCount = 0;
		List<Dictmanager> CodeList=new ArrayList<Dictmanager>();
		CodeList = getCodeListFromCache(codeName);			
		for(Dictmanager dict :CodeList){
			String DictData1 = dict.getDictData1();
			if(Util.isNotNull(codeValue)){
				if(codeValue.equals(DictData1)||codeValue==DictData1){
					rowCount = rowCount+1;
				}
			}
		}
		return rowCount;
	}
	/**
	 * 
	* @Title: getCodeNum   
	* @Description: 查询代码集匹配条数
	* @param codeValue
	* @param codeName
	* @return
	* @throws Exception
	* @return int        
	* @author gaoguangchao
	* @date 2014年6月13日 下午4:04:26 
	*
	 */
	public static int getCodeNum(String[] codeValue,String codeName) throws Exception {
		int rowCount = 0;
		String codeValueStr = Util.addMark(Util.arrayToString(codeValue));
		try {
			String sql="";
			sql="select count(*) from t_dict t  where t.DictListCode= ?  and t.DictData1 IN ("+codeValueStr+") ";
			rowCount = Util.toint(DBHelper.executeScalar(sql,codeName));
		} catch (Exception e) {
			Log.error(e);
		}
		return rowCount;
	}
	/**
	 * 
	* @Title: getCodeNumFromCache   
	* @Description:从缓存中查询代码集匹配条数
	* @param codeValue
	* @param codeName
	* @return
	* @throws Exception
	* @return int        
	* @author gaoguangchao
	* @date 2014年6月27日 下午1:50:57 
	*
	 */
	public static int getCodeNumFromCache(String[] codeValue,String codeName) throws Exception {
		int rowCount = 0;
		List<Dictmanager> CodeList=new ArrayList<Dictmanager>();
		CodeList = getCodeListFromCache(codeName);			
		for(String codeValueStr:codeValue){
			for(Dictmanager dict :CodeList){
				String DictData1 = dict.getDictData1();
				if(Util.isNotNull(codeValueStr)){
					if(codeValueStr.equals(DictData1)||codeValueStr==DictData1){
						rowCount = rowCount+1;
					}
				}
			}
		}
		return rowCount;
	}
	/**
	 * 
	* @Title: getUserInfo   
	* @Description:    根据用户id获取用户信息
	* @param @param userId
	* @param @return
	* @param @throws Exception    
	* @return UserInfoManager      
	* @author guangchao
	* @date 2014-2-28 下午1:33:28 
	* @throws
	 */
	public static UserInfoManager getUserInfo(String UserId) throws Exception {
		
//		UserInfoManager
		UserInfoManager userInfoManager = new UserInfoManager();
		String sql = "SELECT * FROM t_userinfo WHERE UserId = ? AND Status !=99";
		ILoadData userInfo = new LoadUserInfo();
		userInfoManager = DBHelper.getEntity(sql, userInfo,UserId);
		
//		UserDataManager
//		UserDataManager userDataManager = new UserDataManager();
//		String sql0 = "SELECT * FROM t_userdata WHERE UserId = '" + UserId + "'";
//		ILoadData userData = new LoadUserData();
//		userDataManager = DBHelper.getEntity(sql0, userData);
		
//		角色列表
//		List<RoleManager> roleList = new ArrayList<RoleManager>();
//		ILoadData role = new LoadRole();
//		String sql1 = "SELECT * FROM t_role WHERE RoleName IN (SELECT  RoleName FROM t_user_role WHERE UserId = '" + UserId + "'  AND Status = 1) ";
//		roleList = DBHelper.getList(sql1, role);
		
//		模块列表
//		List<ModuleManager> moduleList = new ArrayList<ModuleManager>();
//		ILoadData module = new LoadModule();
//		StringBuffer sb = new StringBuffer();
//		sb.append("SELECT M.ModuleId,M.ModuleParent,M.ModuleAddress,M.Status,M.ModuleNumber,M.SmallLogo, CASE L.ModuleName WHEN LENGTH(L.ModuleName)<=0 THEN L.ModuleName ELSE M.ModuleName END as ModuleName, ");
//		sb.append("CASE L.ModuleRemark WHEN LENGTH(L.ModuleRemark)<=0 THEN L.ModuleRemark ELSE M.ModuleRemark END as ModuleRemark FROM ");
//		sb.append("t_module M LEFT JOIN t_moduleml L ON (M.ModuleId=L.ModuleId AND L.LanguageCode='").append(locale.toString()).append("' ").append(") where M.ModuleId IN ");
//		sb.append("(SELECT ModuleId FROM t_rightsmanager WHERE RoleName IN (SELECT RoleName FROM t_user_role WHERE  UserId = '").append(UserId).append("' ");
//		sb.append(" AND Status  = '1') AND Status  = '1') AND M.Status='1' ORDER BY M.ModuleNumber");
//		moduleList = DBHelper.getList(sb.toString(), module);
		
//		userInfoManager.setUserDataManager(userDataManager);
//		userInfoManager.setRoleList(roleList);
//		userInfoManager.setModuleList(moduleList);
		return userInfoManager;
	}
	/**
	 * 
	* @Title: getUserInfoFromCache   
	* @Description:	根据用户id从缓存中获取用户信息
	* @param UserId
	* @return
	* @throws Exception
	* @return UserInfoManager        
	* @author gaoguangchao
	* @date 2014年6月27日 上午11:03:16 
	*
	 */
	public static UserInfoManager getUserInfoFromCache(String UserId) throws Exception {
		Map<String, String> mapkey = new HashMap<String, String>();
		mapkey.put(UserId, GetLanguageCode());
		UserInfoManager userInfoManager = new UserInfoManager();
		UserInfoAndDataManager UserInfoAndDataManager = (UserInfoAndDataManager) EHCacheHelper.getElementValue("userCache", mapkey);
		userInfoManager.setUserId(UserId);
		userInfoManager.setUserName(UserInfoAndDataManager.getUserName());
		return userInfoManager;
	}
	/**
	 * 
	* @Title: getOrganFromCache   
	* @Description:根据组织id从缓存中获取组织信息
	* @param dataValue
	* @return
	* @return Organ        
	* @author gaoguangchao
	* @date 2014年9月9日 下午5:06:59 
	*
	 */
	public static Organ getOrganFromCache(String organId) {
		Organ organ = new Organ();
		organ = (Organ) EHCacheHelper.getElementValue("organCache", organId);
		return organ;
	}

}
