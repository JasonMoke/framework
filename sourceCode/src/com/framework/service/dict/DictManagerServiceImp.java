/**
 * @Title: DictServiceImp.java   
 * @Copyright 2010 -2013 BIS
 * @Package com.framework.service.dict   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author user1    
 * @date 2014-1-3 上午10:29:36   
 * @version V1.0 
 */

package com.framework.service.dict;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.framework.dao.dict.DictManagerDaoImpl;
import com.framework.entity.dict.Dictmanager;
import com.orm.BaseServiceImpl;
import com.orm.BaseService;

/**
 * @ClassName: DictServiceImp
 * @Description: 字典集管理
 * @author user1
 * @date 2014-1-3 上午10:29:36
 * 
 */
@Service("dictManagerServiceImp")
@Transactional
public class DictManagerServiceImp extends BaseServiceImpl<Dictmanager> implements  IDictManagerService {
	@Resource(name = "dictManagerDaoImpl")
	private DictManagerDaoImpl dictManagerDaoImpl;
	@Override
	public BaseService<Dictmanager> getDao() {
		return dictManagerDaoImpl;
	}
	/*@Resource(name = "dictDaoImpl")
	private DictDaoImpl dictDaoImpl;

	public Pagination<DictList> findAllDictList(PageTemp page,DictList dict) {
		return dictDaoImpl.findAllDict(page,dict);
	}

	public Pagination<Dictmanager> getDictManagerByCode(String Code, PageTemp page,Dictmanager dict,String locale) {
		return dictDaoImpl.getDictManagerByCode(Code, page,dict,locale);
	}

	public boolean insertDictList(DictList dictList) {
		return dictDaoImpl.insertDictList(dictList);
	}

	public boolean insertDictManager(Dictmanager dictmanager) {
		return dictDaoImpl.insertDictManager(dictmanager);
	}

	public int getDictByCode(DictList dictList) {
		// TODO Auto-generated method stub
		return dictDaoImpl.getDictByCode(dictList);
	}

	public Object getDictListByCode(String dictListcode) {
		return dictDaoImpl.getDictListByCode(dictListcode);
	}

	public Object getDictmanagerByCode(Dictmanager dictmanager) {
		// TODO Auto-generated method stub
		return dictDaoImpl.getDictmanagerByCode(dictmanager);
	}

	public boolean delDictListForDictListCode(String scode) {
		return dictDaoImpl.delDictListForDictListCode(scode);
	}

	public boolean delDictmanagerForDictId(String dictID,String updatePerson,Date UpdateTime) {
		dictDaoImpl.delDictmanagerForDictId(dictID,updatePerson,UpdateTime);
		return true;
	}
	public int getListmanagerCount(String dictID) {
		return dictDaoImpl.getListmanagerCount(dictID);
	}

	public boolean updateDictListForDictListCode(DictList dictList) {
		return dictDaoImpl.updateDictListForDictListCode(dictList);
	}
	public boolean updateDictmanagerForID(Dictmanager dictmanager) {
		return dictDaoImpl.updateDictmanagerForID(dictmanager);
	}
	public boolean updateDictListStatus(DictList dictList) {
		return dictDaoImpl.updateDictListStatus(dictList);
	} 	
	public boolean updateDictmanagerStatus(Dictmanager dictmanager) {
		return dictDaoImpl.updateDictmanagerStatus(dictmanager);
	}
	public List<Dictmanager> getDictManagerByDictCode(String dictCode) {
		return dictDaoImpl.getDictManagerByDictCode(dictCode);
	}
	public int getDictManagerByName(Dictmanager dictmanager) {
		return dictDaoImpl.getDictManagerByName(dictmanager);
	}
	public int getDictManagerByDictData(Dictmanager dictmanager) {
		return dictDaoImpl.getDictManagerByDictData(dictmanager);
	}
	public int getDictManagerByNameID(Dictmanager dictmanager) {
		return dictDaoImpl.getDictManagerByNameID(dictmanager);
	}
	public int getDictManagerByDictDataID(Dictmanager dictmanager) {
		return dictDaoImpl.getDictManagerByDictDataID(dictmanager);
	}
*/
}
