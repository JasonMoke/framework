/**
 * @Title: NavigationAction.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.action.navigation
 * @Description: 。
 * @author gaojie
 * @date 2014-09-04 16:06:17
 * @version V1.0
 */
package com.framework.action.navigation;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.core.base.BaseAction;
import com.framework.entity.navigation.Navigation;
import com.framework.entity.dict.Dictmanager;
import com.framework.service.navigation.NavigationService;
import com.orm.Page;
import com.orm.Sort;
/**
 * @ClassName: NavigationAction
 * @Description: 表现层控制类。
 * @author gaojie
 * @date 2014-09-04 16:06:17
 */
@Controller
public class NavigationAction extends BaseAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8152297916767729552L;
	Logger log = Logger.getLogger(this.getClass());
    @Resource(name = "navigationService")
    private NavigationService navigationService;

    //操作类型：add 添加 ；detail 查看； modify 修改
    private String opType;
    private Navigation navigation;
    private List<Navigation> listNavigation;
    private List<Dictmanager> listSystemparam;
    private Page<Navigation> page;
    
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
     * @author gaojie
     * @date 2014-09-04 16:06:17
     * @throws
     */
    public String findList() throws Exception {
        page = new Page<Navigation>();
        //设置每页条数
        page.setPageSize(10);
        page.addOrder(Sort.asc("SeqNum"));
        page.addOrder(Sort.asc("UpdateTime"));
        //设置当前页
        page.setCurrentPage(currentPage);
        //对应map中findAll
        page = navigationService.findByCondition("findAll", navigation, page);
        listNavigation = page.getResultList();
        return "success";
    }

    /**
     * @Title: gotoAdd
     * @Description: 跳转到添加页面.
     * @return
     * @return String
     * @author gaojie
     * @date 2014-09-04 16:06:17
     * @throws
     */
    public String gotoAdd() {
    	setSelectList();
        return "success";
    }
    /**
     * @Title:setSelectList
     * @Description:给select标签添加数据
     * @param:
     * @return:void
     * @author:gaojie
     * @date:2014年9月11日上午11:10:08
     * @throws
     */
    public void setSelectList() {
        @SuppressWarnings("unchecked")
		List<Dictmanager> systemparamList = navigationService.findByCondition("selectSystemName", null);
        setListSystemparam(systemparamList);
    }
        
    /**
     * @Title: insert
     * @Description: 添加
     * @return
     * @return String
     * @author gaojie
     * @date 2014-09-04 16:06:17
     * @throws
     */
    public String insert() {
    	String flag = "error";
    	boolean result = true;
        //获取当前登陆人
        String userId = getCurUserId();
        // 主键
        navigation.setUUID(UUID.randomUUID().toString());
        //设置创建人
        navigation.setCreatePerson(userId);
        //设置创建时间
        navigation.setCreateTime(new Date());
        //设置修改人
        navigation.setUpdatePerson(userId);
      	//设置修改时间
        navigation.setUpdateTime(new Date());
        try {
            //使用泛型新增实体
            //对应map中add
        	navigationService.addEntity(navigation);
        } catch (Exception e) {
            result = false;
            addLog(true,e);
		}
		if(result){
            flag = "success";
            addLog(true,"添加成功");
		}else{
            flag = "error";
		}
        //返回操作结果
        setActionResult(flag);
        return "success";
    }
    
    /**
    * @Title: detail   
    * @Description: 查询单条信息
    * @param @return
    * @param @throws Exception    
    * @return String      
    * @author gaojie
    * @date 2014-09-04 16:06:17
    * @throws
     */
    public String detail() throws Exception {
        
        //获取前台传递的id  get方式传值
        String UUID = getParameter("id");
        //查询实体
        //对应map中findById
        Navigation navigation = (Navigation) navigationService.findById(UUID);
//        String systemCode=navigation.getSystemCode();
    	setSelectList();
        setNavigation(navigation);
        //设置操作类型
        setOpType("detail");
        return "success";
    } 
    
    /**
    * @Title: modify  
    * @Description:    进入修改页面
    * @param @return
    * @param @throws Exception    
    * @return String      
    * @author gaojie
    * @date 2014-09-04 16:06:17
    * @throws
     */
    public String gotoModify() throws Exception {
    	//获取前台传递的id  get方式传值
    	String UUID = getParameter("id");
    	//查询实体
    	//对应map中findById
    	Navigation navigation = (Navigation) navigationService.findById(UUID);
    	setSelectList();
    	setNavigation(navigation);
    	//设置操作类型
    	setOpType("modify");
    	return "success";
    } 
    
    /**
    * @Title: update
    * @Description:修改
    * @param @return
    * @param @throws Exception    
    * @return String      
    * @author gaojie
    * @date 2014-09-04 16:06:17
    * @throws
     */
    public String update() throws Exception {
    	String flag = "error";
    	boolean result = true;
         //设置修改人
    	navigation.setUpdatePerson(getCurUserId());
      	//设置修改时间
      	navigation.setUpdateTime(new Date());
        try {
            //对应map中update
        	navigationService.updateEntity(navigation);
        } catch (Exception e) {
            result = false;
            addLog(false,e);
        }
        if(result){
            flag = "success";
            addLog(true,"修改成功"); 
        }else{
            flag = "error";
        }
        //返回操作结果
        setActionResult(flag);
        return "success";
    }
    
    /**
     * @Title: delete
     * @Description: 删除
     * @return
     * @return String
     * @author gaojie
     * @date 2014-09-04 16:06:17
     * @throws
     */
    public String delete() {
    	String flag = "error";
    	String UUID = getParameter("id");
    	try {
            //获取前台传递的id  get方式传值
            //对应map中deleteBatch
    		navigationService.deleteEntityOfLogical(UUID);
            flag = "success";
            addLog(true,"删除成功");
        } catch (Exception e) {
            flag = "error";
            addLog(false,e);
        }
        //返回操作结果
        setActionResult(flag);
        return flag;
    }

    /**
     * @Title: deleteBatch
     * @Description: 批量删除
     * @return
     * @return String
     * @author gaojie
     * @date 2014-09-04 16:06:17
     * @throws
     */
    public String deleteBatch() {
    	String flag = "error";
        String UUIDs = getParameter("ids");
        if(UUIDs!=null){
            try {
                String[] ids = UUIDs.split(",");
                //对应map中deleteBatch
                navigationService.bulkDeleteOfLogical(ids);
                flag = "success";
                addLog(true,"删除成功");
            } catch (Exception e) {
                flag = "error";
                addLog(false,e);
            }
        }
        //添加日志
        
        //返回操作结果
        setActionResult(flag);
        return "success";
    }

    /**
     * @Title: changeStatus   
     * @Description:  更改状态  
     * @param @throws Exception    
     * @return void      
     * @author gaojie
     * @date 2014-09-04 16:06:17
     * @throws
     */
    public void changeStatus() throws Exception {
    	Boolean result = true;
    	
    	Navigation tpubnav =  new Navigation();
    	tpubnav.setUUID(getParameter("id"));
    	tpubnav.setStatus((byte) Integer.parseInt(getParameter("status")));
    	tpubnav.setUpdatePerson(getCurUserId());
    	tpubnav.setUpdateTime(new Date());
    	try {
            //根据自定义sql进行更新
    		navigationService.updateByCondition("updateStatus", tpubnav);
    		addLog(true,"状态更改成功");
    	} catch (Exception e) {
    		addLog(false,e);
    	}
    	//添加日志
    	
    	ServletActionContext.getResponse().getWriter().write(result.toString());
    }
    /**
     * @return 返回分页
     */
    public Page<Navigation> getPage() {
        return page;
    }

    /**
     * @param 设置分页
     */
    public void setPage(Page<Navigation> page) {
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

    /**
     * @return 返回操作类型
     */
    public String getOpType() {
        return opType;
    }

    /**
     * @param 设置操作类型
     */
    public void setOpType(String opType) {
        this.opType = opType;
    }

	public Navigation getNavigation() {
		return navigation;
	}

	public void setNavigation(Navigation navigation) {
		this.navigation = navigation;
	}

	public List<Navigation> getListNavigation() {
		return listNavigation;
	}

	public void setListNavigation(List<Navigation> listNavigation) {
		this.listNavigation = listNavigation;
	}

	public List<Dictmanager> getListSystemparam() {
		return listSystemparam;
	}

	public void setListSystemparam(List<Dictmanager> listSystemparam) {
		this.listSystemparam = listSystemparam;
	}
}
