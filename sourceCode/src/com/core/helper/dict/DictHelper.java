/**
* @Title: DictHelper.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.oneworld.core.helper.dict   
* @Description:    
* @author guangchao    
* @date 2014-3-26 下午6:08:37   
* @version V1.0 
*/
package com.core.helper.dict;

import java.util.ArrayList;
import java.util.List;

import com.core.dataload.LoadDicList;
import com.core.dataload.LoadDict;
import com.core.db.DBHelper;
import com.core.db.ILoadData;
import com.framework.entity.dict.DictList;
import com.framework.entity.dict.Dictmanager;

/**
 * @ClassName: DictHelper
 * @Description: 字典集Helper工具类
 * @author guangchao
 * @date 2014-3-26 下午6:08:37
 *
 */
public class DictHelper {

	
	/**
	 * @throws Exception 
	 * 
	* @Title: getDictList   
	* @Description:    根据字典集code值获取字典集实体
	* @param @param Code
	* @param @return    
	* @return DictList      
	* @author guangchao
	* @date 2014-3-27 上午10:32:42 
	* @throws
	 */
	public static DictList getDictList(String DictListCode) throws Exception{
		
		DictList dictList=new DictList();
		ILoadData dicListData=new LoadDicList(); 
		String sql="select * from t_dictList where DictListCode=? AND Status!=99";
		dictList=DBHelper.getEntity(sql,dicListData, DictListCode);
		
		return dictList;
		
	}
	
	
	/**
	 * @throws Exception 
	 * 
	* @Title: getDictManagerList   
	* @Description:   根据字典集code获取其下的字典列表 
	* @param @param DictListCode
	* @param @return    
	* @return List<Dictmanager>      
	* @author guangchao
	* @date 2014-3-27 上午10:34:03 
	* @throws
	 */
	public static List<Dictmanager> getDictManagerList(String DictListCode) throws Exception{
		
		List<Dictmanager> dictManagerList=new ArrayList<Dictmanager>();
		ILoadData dictData=new LoadDict();
		String sql="select * from t_dict where DictListCode=? AND Status!=99";
		dictManagerList=DBHelper.getList(sql,dictData,DictListCode);
		
		return dictManagerList;
		
	}
	
	/**
	 * @throws Exception 
	 * 
	* @Title: getDictmanager   
	* @Description:    根据字典id获取字典实体
	* @param @param DictId
	* @param @return    
	* @return Dictmanager      
	* @author guangchao
	* @date 2014-3-27 上午10:35:55 
	* @throws
	 */
	public static Dictmanager getDictmanager(String DictId) throws Exception{
		
		Dictmanager dictManager=new Dictmanager();
		ILoadData dictData=new LoadDict();
		String sql="select * from t_dict where DictId=? AND Status!=99";
		dictManager=DBHelper.getEntity(sql, dictData,DictId);
		
		return dictManager;
			
	}
	
	
	/**
	 * @throws Exception 
	 * 
	* @Title: getDictmanagerByCode   
	* @Description:    根据字典code获取字典实体
	* @param @param DictCode
	* @param @return    
	* @return Dictmanager      
	* @author guangchao
	* @date 2014-3-27 上午11:27:02 
	* @throws
	 */
	public static Dictmanager getDictmanagerByCode(String DictCode) throws Exception{
		
		Dictmanager dictManager=new Dictmanager();
		ILoadData dictData=new LoadDict();
		String sql="select * from t_dict where DictCode=? AND Status!=99";
		dictManager=DBHelper.getEntity(sql, dictData,DictCode);
		
		return dictManager;
		
	}
}
