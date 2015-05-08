/**
* @Title: IDictService.java   
* @Copyright 2010 -2013 BIS
* @Package com.framework.service.dict   
* @Description: TODO(用一句话描述该文件做什么)   
* @author user1    
* @date 2014-1-3 上午10:23:28   
* @version V1.0 
*/

package com.framework.service.dict;

import com.framework.entity.dict.DictList;
import com.orm.BaseService;

/**
 * @ClassName: IDictService
 * @Description: 字典集管理
 * @author user1
 * @date 2014-1-3 上午10:23:28
 *
 */

public interface IDictService extends BaseService<DictList>{
	/*public Pagination<DictList> findAllDictList(PageTemp page,DictList dict) ;
	public Pagination<Dictmanager> getDictManagerByCode(String Code,PageTemp page,Dictmanager dict,String locale);
	public boolean insertDictList(DictList dictList);
	public boolean insertDictManager(Dictmanager dictmanager);
	public int getDictByCode(DictList dictList);
	public Object getDictListByCode(String dictListcode);
	public Object getDictmanagerByCode(Dictmanager dictmanager);
	public boolean delDictListForDictListCode(String scode);
	public boolean delDictmanagerForDictId(String dictID,String updatePerson,Date UpdateTime);
	public int getListmanagerCount(String dictID);
	public boolean updateDictListForDictListCode(DictList dictList);
	public boolean updateDictmanagerForID(Dictmanager dictmanager);
	public boolean updateDictListStatus(DictList dictList);
	public boolean updateDictmanagerStatus(Dictmanager dictmanager);
	public List<Dictmanager> getDictManagerByDictCode(String dictCode);
	*//**
	 * 
	* @Title: getDictManagerByName   
	* @Description: 同字典集下是否有重名  
	* @param @param dictmanager
	* @param @return      
	* @return int   
	* @author Lenovo
	* @date 2014-1-13 下午1:07:45 
	* @throws
	 *//*
	public int getDictManagerByName(Dictmanager dictmanager);
	*//**
	 * 
	* @Title: getDictManagerByDictData   
	* @Description: 同字典集下是否有同样的数据
	* @param @param dictmanager
	* @param @return      
	* @return int   
	* @author Lenovo
	* @date 2014-1-13 下午1:08:04 
	* @throws
	 *//*
	public int  getDictManagerByDictData(Dictmanager dictmanager);
	*//**
	 * 
	* @Title: getDictManagerByNameID   
	* @Description: 修改界面检测同字典集下是否有同名
	* @param @param dictmanager
	* @param @return      
	* @return int   
	* @author Lenovo
	* @date 2014-1-13 下午2:41:51 
	* @throws
	 *//*
	public int getDictManagerByNameID(Dictmanager dictmanager);
	*//**
	 * 
	* @Title: getDictManagerByDictDataID   
	* @Description: 修改界面检测同字典集下是否有同样的数据
	* @param @param dictmanager
	* @param @return      
	* @return int   
	* @author Lenovo
	* @date 2014-1-13 下午2:41:55 
	* @throws
	 *//*
	public int  getDictManagerByDictDataID(Dictmanager dictmanager);*/
}
