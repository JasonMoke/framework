/**
 * @Title: PubMenuAction.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.action.pubmenu
 * @Description: 。
 * @author lyc
 * @date 2014-09-05 11:03:47
 * @version V1.0
 */
package com.framework.action.pubmenu;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.core.base.BaseAction;
import com.orm.Page;
import com.orm.Sort;
import com.util.Util;
import com.framework.entity.pubmenu.PubMenu;
import com.framework.service.pubmenu.PubMenuService;
/**
 * @ClassName: PubMenuAction
 * @Description: 表现层控制类。
 * @author lyc
 * @date 2014-09-05 11:03:47
 */
@Controller
public class PubMenuAction extends BaseAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2494113784315350292L;
	Logger log = Logger.getLogger(this.getClass());
    @Resource(name = "pubmenuService")
    private PubMenuService pubmenuService;
    private PubMenu pubmenu;
    private List<PubMenu> listPubMenu;
    private Page<PubMenu> page;
    
    private String NavId; //应用id
    
    /* 当前页号及每页数 */
    private int currentPage;
    private int pageSize;
    //操作结果
    private String actionResult;
    /**
     * @Title: findList
     * @Description: 页面。
     * @return
     * @throws Exception
     * @return String
     * @author lyc
     * @date 2014-09-05 11:03:47
     * @throws
     */
    public String findList() throws Exception {
    	String 	ParentId = getParameter("ParentId");
    	String 	NavId = getParameter("NavId").trim();
    	if(Util.isNotNull(ParentId)){
    		if(Util.isNull(pubmenu)){
    			pubmenu = new PubMenu();
    			pubmenu.setPID(ParentId);
    		}else{
    			pubmenu.setPID(ParentId);
    		}
    	}else{
    		if(Util.isNull(pubmenu)){
    			pubmenu = new PubMenu();
    			pubmenu.setPID("0");
    		}
    		
    	}
    	pubmenu.setNavId(NavId);
        page = new Page<PubMenu>();
        //设置每页条数
        page.setPageSize(10);
        //设置排序
        page.addOrder(Sort.asc("SeqNum"));
        page.addOrder(Sort.asc("UpdateTime"));
        //设置当前页
        page.setCurrentPage(currentPage);
        //对应map中findAll
        page = pubmenuService.findByCondition("findAll", pubmenu, page);
        listPubMenu = page.getResultList();
        setNavId(NavId);
        return "success";
    }
    
    /**  
    * @Title: findAllList  
    * @Description: 同时刷新左侧树和右侧的列表  
    * @param @return      
    * @return String  
    * @author lyc
    * @date 2014年9月9日 上午10:02:28
    * @throws  
    */
    public String findAllList(){
    	String 	NavId = getParameter("NavId");
    	if(Util.isNotNull(NavId)){
    		setNavId(NavId);
    	}
    	return "success";
    }

    /**
     * @Title: gotoAdd
     * @Description: 跳转到添加页面.
     * @return
     * @return String
     * @author lyc
     * @date 2014-09-05 11:03:47
     * @throws
     */
    public String gotoAdd() {
    	String PID= getParameter("PID");
    	String 	NavId = getParameter("NavId");
    	if(!"0".equals(PID)){
    		PubMenu pubmenu = (PubMenu) pubmenuService.findEntityByCondition("findByPID", PID);
    		setPubmenu(pubmenu);
    	}
    	setNavId(NavId);
        return "success";
    }

    /**
     * @Title: insert
     * @Description: 添加
     * @return
     * @return String
     * @author lyc
     * @date 2014-09-05 11:03:47
     * @throws
     */
    public String insert() {
    	String flag = "success";
        //获取当前登陆人
        String userId = getCurUserId();
        // 主键
        pubmenu.setUUID(UUID.randomUUID().toString());
        //设置创建人
        pubmenu.setCreatePerson(userId);
        //设置创建时间
        pubmenu.setCreateTime(new Date());
        //设置修改人
      	pubmenu.setUpdatePerson(userId);
      	//设置修改时间
    	pubmenu.setUpdateTime(new Date());
    	if(Util.isNull(pubmenu.getPID())){
    		pubmenu.setPID("0");
    	}
        try {
        	//更改首选项
        	if("1".equals(String.valueOf(pubmenu.getIsPreferences()))){
        		pubmenuService.updateByCondition("changeIsPreferences", pubmenu);
        	}
        	//使用泛型新增实体
            //对应map中add
            pubmenuService.addEntity(pubmenu);
        } catch (Exception e) {
        	flag = "error";
        	 addLog(flag, e);
            log.error(e);
		}
        addLog(flag, "添加");
        //返回操作结果
        setNavId(pubmenu.getNavId());
        setActionResult(flag);
        return "success";
    }
    
    /**
    * @Title: detail   
    * @Description: 查询单条信息
    * @param @return
    * @param @throws Exception    
    * @return String      
    * @author lyc
    * @date 2014-09-05 11:03:47
    * @throws
     */
    public String detail() throws Exception {
        //获取前台传递的id  get方式传值
        String UUID = getParameter("id");
        //查询实体
        //对应map中findById
        PubMenu pubmenu = (PubMenu) pubmenuService.findById(UUID);
        PubMenu pubmenus = (PubMenu) pubmenuService.findEntityByCondition("findByPID", pubmenu.getPID());
    	pubmenu.setPIDName(pubmenus.getMenuName());
        setPubMenu(pubmenu);
        return "success";
    } 
    
    /**
    * @Title: modify  
    * @Description:    进入修改页面
    * @param @return
    * @param @throws Exception    
    * @return String      
    * @author lyc
    * @date 2014-09-05 11:03:47
    * @throws
     */
    public String gotoModify() throws Exception {
    	//获取前台传递的id  get方式传值
    	String UUID = getParameter("id");
    	//查询实体
    	//对应map中findById
    	PubMenu pubmenu = (PubMenu) pubmenuService.findById(UUID);
    	String PID =pubmenu.getPID();
    	PubMenu pubmenus = (PubMenu) pubmenuService.findEntityByCondition("findByPID", PID);
    	if(Util.isNotNull(pubmenus)){
    		pubmenu.setPIDName(pubmenus.getMenuName());
    	}
    	setPubMenu(pubmenu);
    	return "success";
    } 
    
    /**
    * @Title: update
    * @Description:修改
    * @param @return
    * @param @throws Exception    
    * @return String      
    * @author lyc
    * @date 2014-09-05 11:03:47
    * @throws
     */
    public String update() throws Exception {
    	String flag = "success";
        //获取当前登陆人
        String userId = getCurUserId();
        //设置修改人
      	pubmenu.setUpdatePerson(userId);
      	//设置修改时间
    	pubmenu.setUpdateTime(new Date());
    	//根据菜单类型判断ModuleId、ResourcesId的值，1：模块菜单  2：资源菜单 3: 外部菜单
    	if("1".equals(pubmenu.getMenuType())){
    		pubmenu.setResourcesId("");
    	} else if("2".equals(pubmenu.getMenuType())){
    		pubmenu.setModuleId("");
    	}else if("3".equals(pubmenu.getMenuType())){
    		pubmenu.setModuleId("");
    		pubmenu.setResourcesId("");
    	}
        try {
            //对应map中update
        	//更改首选项
        	if("1".equals(String.valueOf(pubmenu.getIsPreferences()))){
        		pubmenuService.updateByCondition("changeIsPreferences", pubmenu);
        	}
            pubmenuService.updateEntity(pubmenu);
        } catch (Exception e) {
        	flag = "error";
        	 addLog(flag, e);
            log.error(e);
        }
        //返回操作结果
        setActionResult(flag);
        addLog(flag, "修改");
        setNavId(pubmenu.getNavId());
        return "success";
    }
    
    /**
     * @Title: delete
     * @Description: 删除
     * @return
     * @return String
     * @author lyc
     * @date 2014-09-05 11:03:47
     * @throws
     */
    public String delete() {
    	String flag = "success";
    	String UUID = getParameter("id");
    	String NavId = getParameter("NavId");
    	try {
            //获取前台传递的id  get方式传值
            //对应map中deleteBatch
            pubmenuService.deleteEntityOfLogical(UUID);
        } catch (Exception e) {
            flag = "error";
            addLog(flag, e);
            log.error(e);
        }
        //返回操作结果
        setActionResult(flag);
        addLog(flag, "删除");
        setNavId(NavId);
        return flag;
    }

    /**
     * @Title: deleteBatch
     * @Description: 批量删除
     * @return
     * @return String
     * @author lyc
     * @date 2014-09-05 11:03:47
     * @throws
     */
    public String deleteBatch() {
        String flag = "success";
        String UUIDs = getParameter("ids");
        String NavId = getParameter("NavId");
        if(UUIDs!=null){
            try {
                String[] ids = UUIDs.split(",");
                //对应map中deleteBatch
                pubmenuService.bulkDeleteOfLogical(ids);
            } catch (Exception e) {
                flag = "error";
                addLog(flag, e);
                log.error(e);
            }
        }
        //返回操作结果
        setActionResult(flag);
        addLog(flag,"批量删除");
        setNavId(NavId);
        return "success";
    }

    /**
     * @Title: changeStatus   
     * @Description:  更改状态  
     * @param @throws Exception    
     * @return void      
     * @author lyc
     * @date 2014-09-05 11:03:47
     * @throws
     */
    public void changeStatus() throws Exception {
    	Boolean result = true;
    	//获取当前登陆人
        String userId = getCurUserId();
    	PubMenu pubmenu =  new PubMenu();
    	pubmenu.setUUID(getParameter("id"));
    	pubmenu.setStatus((byte) Integer.parseInt(getParameter("status")));
    	pubmenu.setUpdatePerson(userId);
    	pubmenu.setUpdateTime(new Date());
    	try {
            //根据自定义sql进行更新
            pubmenuService.updateByCondition("updateStatus", pubmenu);
    	} catch (Exception e) {
    		result = false;
    		addLog(result, e);
            log.error(e);
    	}
    	//添加日志
    	addLog(result, "更改状态");
    	getResponse().getWriter().write(result.toString());
    }
    
   
    /**  
    * @Title: selectIsPreferences  
    * @Description: 是否存在首选项 
    * @param @throws Exception      
    * @return void  
    * @author lyc
    * @date 2014年9月10日 下午3:42:52
    * @throws  
    */
    public void selectIsPreferences() throws Exception {
    	String PID = getParameter("PID");
    	String NavId =getParameter("NavId");
    	if(Util.isNull(PID)){
    		PID =  "0";
    	}
    	String result = null;
    	pubmenu = new PubMenu();
    	pubmenu.setPID(PID);
    	pubmenu.setNavId(NavId);
    	int  conut = pubmenuService.getCountBySqlAndParam("selectIsPreferences", pubmenu);
    	result = String.valueOf(conut);
    	getResponse().getWriter().write(result);	
    }

    /**
     * @return the listPubMenu
     */
    public List<PubMenu> getListPubMenu() {
        return listPubMenu;
    }

    /**
     * @param listPubMenu the listPubMenu to set
     */
    public void setListPubMenu(List<PubMenu> listPubMenu) {
        this.listPubMenu = listPubMenu;
    }

    /**
     * @return the pubmenu
     */
    public PubMenu getPubMenu() {
        return pubmenu;
    }

    /**
     * @param pubmenu the pubmenu to set
     */
    public void setPubMenu(PubMenu pubmenu) {
        this.pubmenu = pubmenu;
    }

    /**
     * @return 返回分页
     */
    public Page<PubMenu> getPage() {
        return page;
    }

    /**
     * @param 设置分页
     */
    public void setPage(Page<PubMenu> page) {
        this.page = page;
    }

    /**
     * @return 返回当前页
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * @param 设置当前页
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * @return 返回每页显示的条数
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param 设置每页显示的条数
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }  
    /**
     * @return 返回操作的结果
     */
    public String getActionResult() {
        return actionResult;
    }

    /**
     * @param 设置返回结果
     */
    public void setActionResult(String actionResult) {
        this.actionResult = actionResult;
    }

	public PubMenu getPubmenu() {
		return pubmenu;
	}

	public void setPubmenu(PubMenu pubmenu) {
		this.pubmenu = pubmenu;
	}

	public String getNavId() {
		return NavId;
	}

	public void setNavId(String navId) {
		NavId = navId;
	}
	
	
    
}
