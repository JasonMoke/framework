/**
 * @Title: RightsmanagerAction.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.action.rightsmanager
 * @Description: 。
 * @author zhaojie
 * @date 2014-01-20 15:23:11
 * @version V1.0
 */
package com.framework.action.authority;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.util.SessionInfo;
import com.orm.Page;
import com.orm.Sort;
import com.framework.service.authority.RightsmanagerService;
import com.opensymphony.xwork2.ActionSupport;
import com.framework.entity.authority.RightsManager;
import com.framework.entity.user.UserInfoManager;
/**
 * @ClassName: RightsmanagerAction
 * @Description: 表现层控制类。
 * @author zhaojie
 * @date 2014-01-20 15:23:11
 */
@Controller
public class RightsmanagerAction extends ActionSupport {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1423068271727676641L;
	Logger log = Logger.getLogger(this.getClass());
    @Resource(name = "rightsmanagerService")
    private RightsmanagerService rightsmanagerService;

    //操作类型：add 添加 ；detail 查看； modify 修改
    private String opType;
    private RightsManager rightsmanager;
    private List<RightsManager> listRightsmanager;
    private Page<RightsManager> page;
    
    /* 当前页号及每页数 */
    private int currentPage;
    private int pageSize;
    //操作结果
    private String actionResult;
    //列表 显示模式（列表、缩略图、详细）
    private String switchFlag = "det";
    //由列表进入缩略图时，前台传递的页面高度
    private String bodyheight = "600";

    /**
     * @Title: findList
     * @Description: 页面。
     * @return
     * @throws Exception
     * @return String
     * @author zhaojie
     * @date 2014-01-20 15:23:11
     * @throws
     */
    public String findList() throws Exception {
        page = new Page<RightsManager>();
        //设置每页条数
        page.setPageSize(pageSize);
        //设置排序
        page.addOrder(Sort.asc("UpdateTime"));
        //设置当前页
        page.setCurrentPage(currentPage);
        //对应map中findAll
        page = rightsmanagerService.findByCondition("findAll", null, page);
        listRightsmanager = page.getResultList();
        //设置列表显示模式
        setSwitchFlag(switchFlag);
        //设置页面高度
        setBodyheight(bodyheight);
        return "success";
    }
    /**
     * 
    * @Title: gotoRight   
    * @Description: 跳转权限管理   
    * @param @return      
    * @return String   
    * @author Lenovo
    * @date 2014-1-20 下午4:12:53 
    * @throws
     */
    public String gotoRight() {
        return "success";
    }

    /**
     * @Title: gotoAdd
     * @Description: 跳转到添加页面.
     * @return
     * @return String
     * @author zhaojie
     * @date 2014-01-20 15:23:11
     * @throws
     */
    public String gotoAdd() {
        return "success";
    }

    /**
     * @Title: insert
     * @Description: 添加
     * @return
     * @return String
     * @author zhaojie
     * @date 2014-01-20 15:23:11
     * @throws
     */
    public String insert() {
    	String flag = "error";
    	boolean result = true;
        
        //获取当前登陆人
        UserInfoManager userInfoManager = SessionInfo.getCurUser();
        String userId = userInfoManager.getUserId();
        
        // 主键
        rightsmanager.setRightsId(UUID.randomUUID().toString());
        //设置创建人
        rightsmanager.setCreatePerson(userId);
        //设置创建时间
        rightsmanager.setCreateTime(new Date());
        //设置修改人
      	rightsmanager.setUpdatePerson(userId);
      	//设置修改时间
    	rightsmanager.setUpdateTime(new Date());
        //设置状态
        rightsmanager.setStatus((byte) 1);
                
        try {
            //使用泛型新增实体
            //对应map中add
            rightsmanagerService.addEntity(rightsmanager);
        } catch (Exception e) {
            result = false;
            log.error(e);
		}
		if(result){
            flag = "success";
		}else{
            flag = "error";
		}
        //返回操作结果
        setActionResult(flag);
        //设置列表显示模式
        setSwitchFlag(switchFlag);
        return "success";
    }
    
    /**
    * @Title: detail   
    * @Description: 查询单条信息
    * @param @return
    * @param @throws Exception    
    * @return String      
    * @author zhaojie
    * @date 2014-01-20 15:23:11
    * @throws
     */
    public String detail() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        //获取前台传递的id  get方式传值
        String RightsId = request.getParameter("id");
        //查询实体
        //对应map中findById
        RightsManager rightsmanager = (RightsManager) rightsmanagerService.findById(RightsId);
        setRightsmanager(rightsmanager);
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
    * @author zhaojie
    * @date 2014-01-20 15:23:11
    * @throws
     */
    public String goToModify() throws Exception {
    	HttpServletRequest request = ServletActionContext.getRequest();
    	//获取前台传递的id  get方式传值
    	String RightsId = request.getParameter("id");
    	//查询实体
    	//对应map中findById
    	RightsManager rightsmanager = (RightsManager) rightsmanagerService.findById(RightsId);
    	setRightsmanager(rightsmanager);
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
    * @author zhaojie
    * @date 2014-01-20 15:23:11
    * @throws
     */
    public String update() throws Exception {
    	String flag = "error";
    	boolean result = true;
        try {
            //对应map中update
            rightsmanagerService.updateEntity(rightsmanager);
        } catch (Exception e) {
            result = false;
            log.error(e);
        }
        if(result){
            flag = "success";
        }else{
            flag = "error";
        }
        //返回操作结果
        setActionResult(flag);
        //设置列表显示模式  
        setSwitchFlag(switchFlag);
        return "success";
    }
    
    /**
     * @Title: delete
     * @Description: 删除
     * @return
     * @return String
     * @author zhaojie
     * @date 2014-01-20 15:23:11
     * @throws
     */
    public String delete() {
    	HttpServletRequest request = ServletActionContext.getRequest();
    	String flag = "error";
    	String RightsId = request.getParameter("id");
    	try {
            //获取前台传递的id  get方式传值
            //对应map中deleteBatch
            rightsmanagerService.deleteEntityOfLogical(RightsId);
            flag = "success";
        } catch (Exception e) {
            flag = "error";
            log.error(e);
        }
        //返回操作结果
        setActionResult(flag);
     	//设置列表显示模式  
        setSwitchFlag(switchFlag);
        return flag;
    }

    /**
     * @Title: deleteBatch
     * @Description: 批量删除
     * @return
     * @return String
     * @author zhaojie
     * @date 2014-01-20 15:23:11
     * @throws
     */
    public String deleteBatch() {
    	HttpServletRequest request = ServletActionContext.getRequest();
        String flag = "error";
        String RightsIds = request.getParameter("ids");
        if(RightsIds!=null){
            try {
                String[] ids = RightsIds.split(",");
                //对应map中deleteBatch
                rightsmanagerService.bulkDeleteOfLogical(ids);
                flag = "success";
            } catch (Exception e) {
                flag = "error";
                log.error(e);
            }
        }
        //返回操作结果
        setActionResult(flag);
     	//设置列表显示模式  
        setSwitchFlag(switchFlag);
        return "success";
    }

    /**
     * @Title: changeStatus   
     * @Description:  更改状态  
     * @param @throws Exception    
     * @return void      
     * @author zhaojie
     * @date 2014-01-20 15:23:11
     * @throws
     */
    public void changeStatus() throws Exception {
    	Boolean result = true;
    	HttpServletRequest request = ServletActionContext.getRequest();

    	//获取当前登陆人
        UserInfoManager userInfoManager = SessionInfo.getCurUser();
        String userId = userInfoManager.getUserId();
        RightsManager rightsmanager =  new RightsManager();
    	rightsmanager.setRightsId(request.getParameter("id"));
    	rightsmanager.setStatus((byte) Integer.parseInt(request.getParameter("status")));
    	rightsmanager.setUpdatePerson(userId);
    	rightsmanager.setUpdateTime(new Date());
    	try {
            //根据自定义sql进行更新
            rightsmanagerService.updateByCondition("changeStatus", rightsmanager);
    	} catch (Exception e) {
            log.error(e);
    	}
    	ServletActionContext.getResponse().getWriter().write(result.toString());
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
     * @return 返回开关标签
     */
    public String getSwitchFlag() {
        return switchFlag;
    }

    /**
     * @param 设置开个标签
     */
    public void setSwitchFlag(String switchFlag) {
        this.switchFlag = switchFlag;
    }

    /**
     * @return 返回body高度
     */
    public String getBodyheight() {
        return bodyheight;
    }

    /**
     * @param 设置body高度
     */
    public void setBodyheight(String bodyheight) {
        this.bodyheight = bodyheight;
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
	/**
	 * @return the log
	 */
	public Logger getLog() {
		return log;
	}
	/**
	 * @param log the log to set
	 */
	public void setLog(Logger log) {
		this.log = log;
	}
	/**
	 * @return the rightsmanagerService
	 */
	public RightsmanagerService getRightsmanagerService() {
		return rightsmanagerService;
	}
	/**
	 * @param rightsmanagerService the rightsmanagerService to set
	 */
	public void setRightsmanagerService(RightsmanagerService rightsmanagerService) {
		this.rightsmanagerService = rightsmanagerService;
	}
	/**
	 * @return the rightsmanager
	 */
	public RightsManager getRightsmanager() {
		return rightsmanager;
	}
	/**
	 * @param rightsmanager the rightsmanager to set
	 */
	public void setRightsmanager(RightsManager rightsmanager) {
		this.rightsmanager = rightsmanager;
	}
	/**
	 * @return the listRightsmanager
	 */
	public List<RightsManager> getListRightsmanager() {
		return listRightsmanager;
	}
	/**
	 * @param listRightsmanager the listRightsmanager to set
	 */
	public void setListRightsmanager(List<RightsManager> listRightsmanager) {
		this.listRightsmanager = listRightsmanager;
	}
	/**
	 * @return the page
	 */
	public Page<RightsManager> getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(Page<RightsManager> page) {
		this.page = page;
	}
}
