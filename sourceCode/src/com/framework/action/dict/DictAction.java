/**
 * @Title: DictAction.java   
 * @Copyright 2010 -2013 BIS
 * @Package com.framework.action.dict   
 * @Description: 字典集管理  
 * @author user1    
 * @date 2014-1-3 上午9:58:00   
 * @version V1.0 
 */

package com.framework.action.dict;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.core.base.BaseAction;
import com.framework.entity.dict.DictList;
import com.framework.entity.dict.Dictmanager;
import com.framework.service.dict.IDictManagerService;
import com.framework.service.dict.IDictService;
import com.orm.Page;
import com.orm.Sort;
import com.util.Util;

/**
 * @ClassName: DictAction
 * @Description: 字典集管理
 * @author user1
 * @date 2014-1-3 上午9:58:00
 * 
 */
@Controller
public class DictAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8827981077343515107L;
	@Resource(name = "dictServiceImp")
	private IDictService dictServiceImp;
	@Resource(name = "dictManagerServiceImp")
	private IDictManagerService dictManagerServiceImp;
	private String start;
	private String actionResult;
	private String codeParam;
	List<DictList> list = null;
	List<Dictmanager> listManager = null;
	/* 当前页号及每页数 */
	Logger log = Logger.getLogger(this.getClass());
	private int currentPage;
	private DictList dictList;
	private Dictmanager dictmanager;
	private String dictListCode;
	private DictList dict;
	private Page<DictList> page;
	private Page<Dictmanager> pageDictmanager;
	private int pageSize;

	/**
	 * 
	 * @Title: findAllDict
	 * @Description: 分页查询全部字典集
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author user1
	 * @date 2014-1-3 上午11:14:32
	 * @throws
	 */
	public String findAllDict() throws Exception {
	    page = new Page<DictList>();
        page.setPageSize(pageSize);
        //设置排序
        page.addOrder(Sort.asc("DictListNumber"));
        page.addOrder(Sort.asc("UpdateTime"));
        page.setCurrentPage(currentPage);
		page = dictServiceImp.findByCondition("findAllDictList",dictList,page);
		list=page.getResultList();
		String actionResult = getActionResult();
		setActionResult(actionResult);
		return "success";
	}
	/**
	 * 
	* @Title: getDictManager   
	* @Description: 按字典集查询字典
	* @param @return
	* @param @throws Exception      
	* @return String   
	* @author Lenovo
	* @date 2014-1-9 下午2:57:45 
	* @throws
	 */
	public String getDictManager() throws Exception {
		Locale locale = getLocale();
		String code = getParameter("code");
		if(Util.isNull(code) && Util.isNotNull(dict)){
			code = dict.getDictListCode();
		}
		if(Util.isNull(code)){
			code = codeParam;
		}
		pageDictmanager = new Page<Dictmanager>();
		pageDictmanager.setPageSize(pageSize);
		pageDictmanager.setCurrentPage(currentPage);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sCode",  code );
		map.put("Code",  "'"+code+"'" );
		map.put("locale", locale.toString());
		if(dictmanager!=null){
			map.put("DictData1", dictmanager.getDictData1());
			map.put("DictListName", dictmanager.getDictName());
			map.put("Status", dictmanager.getStatus());
		}
		pageDictmanager = dictManagerServiceImp.findByCondition("getDictManager", map, pageDictmanager);
		listManager = pageDictmanager.getResultList();
		String actionResult = getActionResult();
		setActionResult(actionResult);
		setDictListCode(code);
		return "success";
	}
	/**
	 * 
	* @Title: detailDictList   
	* @Description: 查看单个字典集  
	* @param @return
	* @param @throws Exception      
	* @return String   
	* @author Lenovo
	* @date 2014-1-8 下午3:02:57 
	* @throws
	 */
	public String detailDictList() throws Exception {
		String dictListcode = getParameter("scode");
		DictList dict=dictServiceImp.findEntityByCondition("getDictListByCode", dictListcode);
		setDictList(dict);
		return "success";
	}
	/**
	 * 
	* @Title: detailDictmanager   
	* @Description: 查看单个字典 
	* @param @return
	* @param @throws Exception      
	* @return String   
	* @author Lenovo
	* @date 2014-1-8 下午4:02:14 
	* @throws
	 */
	public String detailDictmanager() throws Exception {
		Locale locale = getLocale();
		String dictId = getParameter("dictId");
		Dictmanager dictmanager=new Dictmanager();
		dictmanager.setDictId(dictId);
		dictmanager.setLocale(locale.toString());
		Dictmanager dict=dictManagerServiceImp.findEntityByCondition("getDictmanagerByCode", dictmanager);
		setDictmanager(dict);
		return "success";
	}
	/**
	 * 
	 * @Title: insertDictList
	 * @Description: 新增字典集
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author Lenovo
	 * @date 2014-1-6 下午5:31:23
	 * @throws
	 */
	public String insertDictList() throws Exception {
	    String CreatePerson = getCurUserId();	
		String flag = "success";
		dict.setCreateTime(new Date());
		dict.setCreatePerson(CreatePerson);
		dict.setUpdateTime(new Date());
		dict.setUpdatePerson(CreatePerson);
		try {
			dictServiceImp.addEntity("insertDictList", dict);
			addLog(true,"新增字典集成功");
		} catch (Exception e) {
			log.error(e);
			addLog(false, e);
			flag = "error";
		}
		setActionResult(flag);
		return flag;
	}
	/**
	 * 
	* @Title: insertDictManager   
	* @Description: 新增字典   
	* @param @return
	* @param @throws Exception      
	* @return String   
	* @author Lenovo
	* @date 2014-1-7 下午3:38:30 
	* @throws
	 */
	public String insertDictManager() throws Exception {
	    String CreatePerson = getCurUserId();	
		String flag = "success";
		dictmanager.setCreateTime(new Date());
		dictmanager.setCreatePerson(CreatePerson);
		dictmanager.setUpdateTime(new Date());
		dictmanager.setUpdatePerson(CreatePerson);
		String id=UUID.randomUUID().toString();
		dictmanager.setDictId(id);
		try {
			dictManagerServiceImp.addEntity("insertDictManager", dictmanager);
			addLog(true,"新增字典成功");
		} catch (Exception e) {
			log.error(e);
			addLog(false, e);
			flag = "error";
		}
		setActionResult(flag);
		setCodeParam(dictmanager.getDictListCode());
		return flag;
	}
	/**
	 * 
	* @Title: deleteDictList   
	* @Description: 根据字典集编码删除字典集 
	* @param @return
	* @param @throws Exception      
	* @return String   
	* @author Lenovo
	* @date 2014-1-8 下午4:57:09 
	* @throws
	 */
	public String deleteDictList() throws Exception {
		String scode = getParameter("scode");
		String flag = "success";
		String[] array ;
		Map<String,String[]> map = new HashMap<String,String[]>();
		if(Util.isNotNull(scode)){
			array = scode.split(",");
			map.put("listCodes", array);
		}
		try {
			dictServiceImp.deleteByConditions("delDictListForDictListCode", map);
			addLog(true,"删除字典集成功");
		} catch (Exception e) {
			log.error(e);
			addLog(false, e);
			flag = "error";
		}
		setActionResult(flag);
		return flag;
	}
	/**
	 * 
	* @Title: getListmanagerCount   
	* @Description: 查询字典集下是否存在字典 
	* @param @throws Exception      
	* @return void   
	* @author Lenovo
	* @date 2014-1-9 下午2:58:46 
	* @throws
	 */
	public void getListmanagerCount() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String scode = request.getParameter("scode");
		Boolean result = true;
		String[] array ;
		Map<String,String[]> map = new HashMap<String,String[]>();
		if(Util.isNotNull(scode)){
			array = scode.split(",");
			map.put("listCodes", array);
		}
		int number=dictManagerServiceImp.getCountBySqlAndParam("getDictManagerCountNumber", map);
		if(number>0){
			result= false;
			}
		ServletActionContext.getResponse().getWriter().write(result.toString());
	}
	/**
	 * 
	* @Title: getDictManagerByName   
	* @Description: 同字典集下是否有同名字典   
	* @param @throws Exception      
	* @return void   
	* @author Lenovo
	* @date 2014-1-13 下午1:11:32 
	* @throws
	 */
	public void getDictManagerByName() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String DictListCode = request.getParameter("DictListCode");
		Boolean result = true;
		dictmanager.setDictListCode(DictListCode);
		int number= dictManagerServiceImp.getCountBySqlAndParam("getDictManagerByName", dictmanager);
		if(number>0){
			result= false;
		}
		ServletActionContext.getResponse().getWriter().write(result.toString());
	}
	/**
	 * 
	* @Title: getDictManagerByNameID   
	* @Description: 在修改界面检测是否同名 
	* @param @throws Exception      
	* @return void   
	* @author Lenovo
	* @date 2014-1-13 下午2:46:46 
	* @throws
	 */
	public void getDictManagerByNameID() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String DictListCode = request.getParameter("DictListCode");
		String id=request.getParameter("DictID");
		Boolean result = true;
		dictmanager.setDictListCode(DictListCode);
		dictmanager.setDictId(id);
		int number= dictManagerServiceImp.getCountBySqlAndParam("getDictManagerByNameID", dictmanager);
		if(number>0){
			result= false;
		}
		ServletActionContext.getResponse().getWriter().write(result.toString());
	}
	/**
	 * 
	* @Title: getDictManagerByDictDataID   
	* @Description: 在修改界面检测是否有同数据  
	* @param @throws Exception      
	* @return void   
	* @author Lenovo
	* @date 2014-1-13 下午2:47:14 
	* @throws
	 */
	public void getDictManagerByDictDataID() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String DictListCode = request.getParameter("DictListCode");
		String id=request.getParameter("DictID");
		Boolean result = true;
		dictmanager.setDictListCode(DictListCode);
		dictmanager.setDictId(id);
		int number= dictManagerServiceImp.getCountBySqlAndParam("getDictManagerByDictDataID", dictmanager);
		if(number>0){
			result= false;
		}
		ServletActionContext.getResponse().getWriter().write(result.toString());
	}
	/**
	 * 
	* @Title: getDictManagerByDictData   
	* @Description: 同字典集下是否有同数据字典 
	* @param @throws Exception      
	* @return void   
	* @author Lenovo
	* @date 2014-1-13 下午1:11:37 
	* @throws
	 */
	public void getDictManagerByDictData() throws Exception {
		String DictListCode = getParameter("DictListCode");
		Boolean result = true;
		dictmanager.setDictListCode(DictListCode);
		int number= dictManagerServiceImp.getCountBySqlAndParam("getDictManagerByDictDataID", dictmanager);
		if(number>0){
			result= false;
		}
		ServletActionContext.getResponse().getWriter().write(result.toString());
	}
	/**
	 * 
	* @Title: deleteDictmanager   
	* @Description: 根据字典id删除字典 
	* @param @return
	* @param @throws Exception      
	* @return String   
	* @author Lenovo
	* @date 2014-1-8 下午4:57:44 
	* @throws
	 */
	public String deleteDictmanager() throws Exception {
	    String updatePerson = getCurUserId();	
	    Date UpdateTime=new Date();
		String dictId = getParameter("dictId");
		String scode = getParameter("scode");
		String[] array ;
		Map<String, Object> map = new HashMap<String, Object>();
		if(Util.isNotNull(dictId)){
			array = dictId.split(",");
			map.put("dictID", array);
			map.put("updatePerson", updatePerson);
			map.put("UpdateTime", UpdateTime);
		}
		String flag = "success";
		try {
			dictManagerServiceImp.deleteByConditions("delDictmanagerForDictId", map);
			addLog(true,"删除字典成功");
		} catch (Exception e) {
			log.error(e);
			addLog(false,e);
			flag = "error";
		}
		setActionResult(flag);
		setCodeParam(scode);
		return flag;
	}
	/**
	 * 
	* @Title: getDictByCode   
	* @Description: 检测字典集编码是否存在
	* @param @throws Exception      
	* @return void   
	* @author Lenovo
	* @date 2014-1-7 下午4:31:10 
	* @throws
	 */
	public void getDictByCode() throws Exception {
		int count = dictServiceImp.getCountBySqlAndParam("getDictByCode",dictList);
		Boolean result = true;
		if ( count > 0) {
			result = false;
		}
		ServletActionContext.getResponse().getWriter().write(result.toString());
	}
	/**
	 * 
	* @Title: updateDictList   
	* @Description: 修改字典集
	* @param @return
	* @param @throws Exception      
	* @return String   
	* @author Lenovo
	* @date 2014-1-9 下午2:27:13 
	* @throws
	 */
	public String updateDictList() throws Exception {
	    String updatePerson = getCurUserId();	
		dictList.setCreateTime(new Date());
		dictList.setCreatePerson(updatePerson);
	    dictList.setUpdateTime(new Date());
	    dictList.setUpdatePerson(updatePerson);
		String flag = "success";
		try {
			dictServiceImp.updateByCondition("updateDictListForDictListCode", dictList);
			addLog(true,"修改字典集成功");
		} catch (Exception e) {
			flag = "error";
			addLog(false,e);
			log.error(e);
		}
		setActionResult(flag);
		return flag;
	}
	/**
	 * 
	* @Title: changeStatus   
	* @Description: 修改字典集状态  
	* @param @throws Exception      
	* @return void   
	* @author Lenovo
	* @date 2014-1-10 上午10:25:56 
	* @throws
	 */
	public void changeStatus() throws Exception {
		String sCode = getParameter("id");
		String dictStatus = getParameter("status");
		Boolean result = true;
		DictList list=new DictList();
		list.setDictListCode(sCode);
		list.setStatus(Integer.parseInt(dictStatus));
	    String updatePerson = getCurUserId();	
	    Date updateTime=new Date();
	    list.setUpdateTime(updateTime);
	    list.setUpdatePerson(updatePerson);
		try {
			dictServiceImp.updateByCondition("updatedictListStatus", list);
			addLog(true,"变更字典集状态成功");
		} catch (Exception e) {
			log.error(e);
			addLog(false,e);
			result = false;
		}
		ServletActionContext.getResponse().getWriter().write(result.toString());
	}
	/**
	 * 
	* @Title: managerchangeStatus   
	* @Description: 修改字典状态
	* @param @throws Exception      
	* @return void   
	* @author Lenovo
	* @date 2014-1-10 上午11:42:28 
	* @throws
	 */
	public void managerchangeStatus() throws Exception {
		String dictID = getParameter("id");
		String dictmanagerStatus = getParameter("status");
		Boolean result = true;
		Dictmanager manager=new Dictmanager();
	    String updatePerson = getCurUserId();	
	    manager.setUpdateTime(new Date());
	    manager.setUpdatePerson(updatePerson);
		manager.setDictId(dictID);
		manager.setStatus(Integer.parseInt(dictmanagerStatus));
		
		try {
			dictManagerServiceImp.updateByCondition("updateDictmanagerStatus", manager);
			addLog(true,"变更字典状态成功");
		} catch (Exception e) {
			log.error(e);
			addLog(false,e);
			result = false;
		}
		ServletActionContext.getResponse().getWriter().write(result.toString());
	}

	/**
	 * 
	* @Title: updateDictmanager   
	* @Description: 根据id修改字典   
	* @param @return
	* @param @throws Exception      
	* @return String   
	* @author Lenovo
	* @date 2014-1-9 下午4:27:45 
	* @throws
	 */
	public String updateDictmanager() throws Exception {
	    String updatePerson = getCurUserId();	
	    dictmanager.setUpdateTime(new Date());
	    dictmanager.setUpdatePerson(updatePerson);
		String flag = "success";
		try {
			dictServiceImp.updateByCondition("updateDictmanagerForID",dictmanager);
			addLog(true,"修改字典成功");
		} catch (Exception e) {
			log.error(e);
			flag = "error";
			addLog(false,e);
		}
		setActionResult(flag);
		setCodeParam(dictmanager.getDictListCode());
		return flag;
	}
	/**
	 * 
	* @Title: toUpdateDictmanager   
	* @Description: 跳转修改字典   
	* @param @return
	* @param @throws Exception      
	* @return String   
	* @author Lenovo
	* @date 2014-1-9 下午4:33:41 
	* @throws
	 */
	public String toUpdateDictmanager() throws Exception {
		Locale locale = getLocale();
		String dictId = getParameter("dictId");
		Dictmanager dictmanager=new Dictmanager();
		dictmanager.setDictId(dictId);
		dictmanager.setLocale(locale.toString());
		Dictmanager dict = dictManagerServiceImp.findEntityByCondition("getDictmanagerByCode", dictmanager);
		setDictmanager(dict);
		return "success";
	}
	/**
	 * 
	* @Title: toAddDictList   
	* @Description: 跳转增加字典集
	* @param @return
	* @param @throws Exception      
	* @return String   
	* @author Lenovo
	* @date 2014-1-8 上午11:16:09 
	* @throws
	 */
	public String toAddDictList() throws Exception {
		return "success";
	}
	/**
	 * 
	* @Title: toUpdateDictList   
	* @Description: 跳转修改字典集 
	* @param @return
	* @param @throws Exception      
	* @return String   
	* @author Lenovo
	* @date 2014-1-9 下午2:33:11 
	* @throws
	 */
	public String toUpdateDictList() throws Exception {
		String dictListcode = getParameter("scode");
		DictList dict=dictServiceImp.findEntityByCondition("getDictListByCode", dictListcode);
		setDictList(dict);
		return "success";
	}
	/**
	 * 
	* @Title: toAddDictManager   
	* @Description: 跳转增加字典 
	* @param @return
	* @param @throws Exception      
	* @return String   
	* @author Lenovo
	* @date 2014-1-8 上午11:16:23 
	* @throws
	 */
	public String toAddDictManager() throws Exception {
		return "success";
	}
	

	/**
	 * @return the dictServiceImp
	 */
	public IDictService getDictServiceImp() {
		return dictServiceImp;
	}

	/**
	 * @param dictServiceImp
	 *            the dictServiceImp to set
	 */
	public void setDictServiceImp(IDictService dictServiceImp) {
		this.dictServiceImp = dictServiceImp;
	}

	/**
	 * @return the start
	 */
	public String getStart() {
		return start;
	}

	/**
	 * @param start
	 *            the start to set
	 */
	public void setStart(String start) {
		this.start = start;
	}

	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage
	 *            the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the list
	 */
	public List<DictList> getList() {
		return list;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<DictList> list) {
		this.list = list;
	}

	/**
	 * @return the actionResult
	 */
	public String getActionResult() {
		return actionResult;
	}

	/**
	 * @param actionResult
	 *            the actionResult to set
	 */
	public void setActionResult(String actionResult) {
		this.actionResult = actionResult;
	}

	/**
	 * @return the dictList
	 */
	public DictList getDictList() {
		return dictList;
	}

	/**
	 * @param dictList
	 *            the dictList to set
	 */
	public void setDictList(DictList dictList) {
		this.dictList = dictList;
	}

	/**
	 * @return the listManager
	 */
	public List<Dictmanager> getListManager() {
		return listManager;
	}

	/**
	 * @param listManager
	 *            the listManager to set
	 */
	public void setListManager(List<Dictmanager> listManager) {
		this.listManager = listManager;
	}

	/**
	 * @return the dictmanager
	 */
	public Dictmanager getDictmanager() {
		return dictmanager;
	}

	/**
	 * @param dictmanager the dictmanager to set
	 */
	public void setDictmanager(Dictmanager dictmanager) {
		this.dictmanager = dictmanager;
	}

	/**
	 * @return the dictListCode
	 */
	public String getDictListCode() {
		return dictListCode;
	}

	/**
	 * @param dictListCode the dictListCode to set
	 */
	public void setDictListCode(String dictListCode) {
		this.dictListCode = dictListCode;
	}
	/**
	 * @return the codeParam
	 */
	public String getCodeParam() {
		return codeParam;
	}
	/**
	 * @param codeParam the codeParam to set
	 */
	public void setCodeParam(String codeParam) {
		this.codeParam = codeParam;
	}
	/**
	 * @return the dict
	 */
	public DictList getDict() {
		return dict;
	}
	/**
	 * @param dict the dict to set
	 */
	public void setDict(DictList dict) {
		this.dict = dict;
	}
	public Page<DictList> getPage() {
		return page;
	}
	public void setPage(Page<DictList> page) {
		this.page = page;
	}
	public Page<Dictmanager> getPageDictmanager() {
		return pageDictmanager;
	}
	public void setPageDictmanager(Page<Dictmanager> pageDictmanager) {
		this.pageDictmanager = pageDictmanager;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	
}
