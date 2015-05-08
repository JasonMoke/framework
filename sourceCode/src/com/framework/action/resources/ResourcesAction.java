
package com.framework.action.resources;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.core.base.BaseAction;
import com.framework.entity.resources.Resources;
import com.framework.service.resources.ResourcesService;
import com.orm.Page;
import com.orm.Sort;
import com.util.Util;

/**
 * @ClassName: ResourcesAction
 * @Description: 资源管理
 * @author zhaojie
 * @date 2014-1-3 上午9:58:00
 * 
 */
@Controller
public class ResourcesAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3933095368139372742L;
	@Resource(name = "ResourcesServiceImp")
	private ResourcesService ResourcesService;
	private String actionResult;
	List<Resources> list = null;
	private int currentPage;
	private Page<Resources> page;
	private int pageSize;
	private Resources Resources;
	Logger log = Logger.getLogger(this.getClass());
	/**
	 * 
	 * @Title: findAll
	 * @Description: 分页查询全部资源
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author user1
	 * @date 2014-1-3 上午11:14:32
	 * @throws
	 */
	public String findAll() throws Exception {
	    page = new Page<Resources>();
        page.setPageSize(pageSize);
        //设置排序
        page.addOrder(Sort.asc("ResourcesNumber"));
        page.addOrder(Sort.asc("UpdateTime"));
        page.setCurrentPage(currentPage);
		page = ResourcesService.findByCondition("findAll",Resources,page);
		list=page.getResultList();
		String actionResult = getActionResult();
		setActionResult(actionResult);
		return "success";
	}
	/**
	 * 
	* @Title: detail
	* @Description: 查看单个
	* @param @return
	* @param @throws Exception      
	* @return String   
	* @author Lenovo
	* @date 2014-1-8 下午3:02:57 
	* @throws
	 */
	public String detail() throws Exception {
		String id = getParameter("id");
		Resources manager=ResourcesService.findEntityByCondition("detail", id);
		setResources(manager);
		return "success";
	}
	/**
	 * 
	* @Title: toAdd
	* @Description: 跳转到添加界面
	* @param @return
	* @param @throws Exception      
	* @return String   
	* @author zhaojie
	* @date 2014-1-8 下午3:02:57 
	* @throws
	 */
	public String toAdd() throws Exception {
		return "success";
	}
	/**
	 * 
	* @Title: toUpdate
	* @Description: 跳转到修改界面
	* @param @return
	* @param @throws Exception      
	* @return String   
	* @author zhaojie
	* @date 2014-1-8 下午3:02:57 
	* @throws
	 */
	public String toUpdate() throws Exception {
		String id = getParameter("id");
		Resources manager=ResourcesService.findEntityByCondition("detail", id);
		setResources(manager);
		return "success";
	}
	/**
	 * 
	* @Title: update   
	* @Description: 修改资源
	* @param @return
	* @param @throws Exception      
	* @return String   
	* @author zhaojie
	* @date 2014-1-9 下午2:27:13 
	* @throws
	 */
	public String update() throws Exception {
	    String updatePerson = getCurUserId();	
	    Resources.setCreateTime(new Date());
	    Resources.setCreatePerson(updatePerson);
	    Resources.setUpdateTime(new Date());
	    Resources.setUpdatePerson(updatePerson);
		String flag = "success";
		try {
			ResourcesService.updateByCondition("update", Resources);
			addLog(true,"修改资源成功");
		} catch (Exception e) {
			flag = "error";
			addLog(false, e);
			log.error(e);
		}
		setActionResult(flag);
		return flag;
	}
	/**
	 * 
	* @Title: changeStatus   
	* @Description: 修改资源状态  
	* @param @throws Exception      
	* @return void   
	* @author zhaojie
	* @date 2014-1-10 上午10:25:56 
	* @throws
	 */
	public void changeStatus() throws Exception {
		String id = getParameter("id");
		String Status = getParameter("status");
		Boolean result = true;
		Resources list=new Resources();
		list.setResourcesId(id);
		list.setStatus(Integer.parseInt(Status));
	    String updatePerson = getCurUserId();	
	    Date updateTime=new Date();
	    list.setUpdateTime(updateTime);
	    list.setUpdatePerson(updatePerson);
		try {
			ResourcesService.updateByCondition("updateStatus", list);
			addLog(true,"变更资源状态成功");
		} catch (Exception e) {
			log.error(e);
			addLog(false, e);
			result = false;
		}
		ServletActionContext.getResponse().getWriter().write(result.toString());
	}
	/**
	 * 
	* @Title: delete 
	* @Description: 删除资源
	* @param @return
	* @param @throws Exception      
	* @return String   
	* @author zhaojie
	* @date 2014-1-8 下午4:57:09 
	* @throws
	 */
	public String delete() throws Exception {
		String id = getParameter("id");
		String flag = "success";
		String[] array ;
		Map<String,String[]> map = new HashMap<String,String[]>();
		if(Util.isNotNull(id)){
			array = id.split(",");
			map.put("id", array);
		}
		try {
			ResourcesService.deleteByConditions("delete", map);
			addLog(true,"删除资源成功");
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
	 * @Title: insert
	 * @Description: 新增资源
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author zhaojie
	 * @date 2014-1-6 下午5:31:23
	 * @throws
	 */
	public String insert() throws Exception {
	    String CreatePerson = getCurUserId();	
		String flag = "success";
		String id=UUID.randomUUID().toString();
		Resources.setResourcesId(id);
		Resources.setCreateTime(new Date());
		Resources.setCreatePerson(CreatePerson);
		Resources.setUpdateTime(new Date());
		Resources.setUpdatePerson(CreatePerson);
		try {
			ResourcesService.addEntity("insert", Resources);
			addLog(true,"新增资源成功");
		} catch (Exception e) {
			log.error(e);
			addLog(false, e);
			flag = "error";
		}
		setActionResult(flag);
		return flag;
	}
	public String getActionResult() {
		return actionResult;
	}
	public void setActionResult(String actionResult) {
		this.actionResult = actionResult;
	}
	public Resources getResources() {
		return Resources;
	}
	public void setResources(Resources Resources) {
		this.Resources = Resources;
	}
	public List<Resources> getList() {
		return list;
	}
	public void setList(List<Resources> list) {
		this.list = list;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public Page<Resources> getPage() {
		return page;
	}
	public void setPage(Page<Resources> page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
