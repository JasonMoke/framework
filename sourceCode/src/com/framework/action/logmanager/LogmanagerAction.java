/**
 * @Title: LogmanagerAction.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.action.logmanager
 * @Description: 。
 * @author gaoguangchao
 * @date 2014-01-14 14:16:14
 * @version V1.0
 */
package com.framework.action.logmanager;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.orm.Page;
import com.orm.Sort;
import com.core.base.BaseAction;
import com.framework.entity.logmanager.Logmanager;
import com.framework.service.logmanager.LogmanagerService;
/**
 * @ClassName: LogmanagerAction
 * @Description: 表现层控制类。
 * @author gaoguangchao
 * @date 2014-01-14 14:16:14
 */
@Controller
public class LogmanagerAction extends BaseAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6298115459161452922L;
	Logger log = Logger.getLogger(this.getClass());
    @Resource(name = "logmanagerService")
    private LogmanagerService logmanagerService;
    private Logmanager logmanager;
    private List<Logmanager> listLogmanager;
    private Page<Logmanager> page;
    
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
     * @author gaoguangchao
     * @date 2014-01-14 14:16:14
     * @throws
     */
    public String findList() throws Exception {
    	
        page = new Page<Logmanager>();
        //设置每页条数
        page.setPageSize(pageSize);
        //设置排序
        page.addOrder(Sort.desc("UpdateTime"));
        //设置当前页
        page.setCurrentPage(currentPage);
        //对应map中findAll
        page = logmanagerService.findByCondition("findAll", logmanager, page);
        listLogmanager = page.getResultList();
        return "success";
    }

    /**
     * @Title: gotoAdd
     * @Description: 跳转到添加页面.
     * @return
     * @return String
     * @author gaoguangchao
     * @date 2014-01-14 14:16:14
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
     * @author gaoguangchao
     * @date 2014-01-14 14:16:14
     * @throws
     */
    public String insert() {
    	String  flag = "success";
        //获取当前登陆人
        String userId = getCurUserId();
        // 主键
        logmanager.setLogId(UUID.randomUUID().toString());
        //设置创建人
        logmanager.setCreatePerson(userId);
        //设置创建时间
        logmanager.setCreateTime(new Date());
        //设置修改人
      	logmanager.setUpdatePerson(userId);
      	//设置修改时间
    	logmanager.setUpdateTime(new Date());
        //设置状态
        logmanager.setStatus((byte) 1);
                
        try {
            //使用泛型新增实体
            //对应map中add
            logmanagerService.addEntity(logmanager);
            addLog(true,"新增日志成功");
        } catch (Exception e) {
        	 flag = "error";
            log.error(e);
            addLog(false,e);
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
    * @author gaoguangchao
    * @date 2014-01-14 14:16:14
    * @throws
     */
    public String detail() throws Exception {
        //获取前台传递的id  get方式传值
        String LogId = getParameter("LogId");
        //查询实体
        //对应map中findById
        Logmanager logmanager = (Logmanager) logmanagerService.findById(LogId);
        setLogmanager(logmanager);
        return "success";
    } 
    
    /**
    * @Title: modify  
    * @Description:    进入修改页面
    * @param @return
    * @param @throws Exception    
    * @return String      
    * @author gaoguangchao
    * @date 2014-01-14 14:16:14
    * @throws
     */
    public String goToModify() throws Exception {
    	//获取前台传递的id  get方式传值
    	String LogId = getParameter("LogId");
    	//查询实体
    	//对应map中findById
    	Logmanager logmanager = (Logmanager) logmanagerService.findById(LogId);
    	setLogmanager(logmanager);
    	return "success";
    } 
    
    /**
    * @Title: update
    * @Description:修改
    * @param @return
    * @param @throws Exception    
    * @return String      
    * @author gaoguangchao
    * @date 2014-01-14 14:16:14
    * @throws
     */
    public String update() throws Exception {
    	String flag = "success";
        try {
            //对应map中update
            logmanagerService.updateEntity(logmanager);
            addLog(true,"修改日志成功");
        } catch (Exception e) {
        	flag = "error";
        	addLog(false,e);
            log.error(e);
        }//返回操作结果
        setActionResult(flag);
        return "success";
    }
    
    /**
     * @Title: delete
     * @Description: 删除
     * @return
     * @return String
     * @author gaoguangchao
     * @date 2014-01-14 14:16:14
     * @throws
     */
    public String delete() {
    	String flag = "success";
    	String LogId = getParameter("LogId");
    	try {
            //获取前台传递的id  get方式传值
            //对应map中deleteBatch
            logmanagerService.deleteEntityOfLogical(LogId);
            addLog(true,"删除日志成功");
        } catch (Exception e) {
            flag = "error";
            addLog(false,e);
            log.error(e);
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
     * @author gaoguangchao
     * @date 2014-01-14 14:16:14
     * @throws
     */
    public String deleteBatch() {
        String flag = "success";
        String LogIds = getParameter("LogIds");
        if(LogIds!=null){
            try {
                String[] ids = LogIds.split(",");
                //对应map中deleteBatch
                logmanagerService.bulkDeleteOfLogical(ids);
                addLog(true,"批量删除日志成功");
            } catch (Exception e) {
                flag = "error";
                addLog(false,e);
                log.error(e);
            }
        }
        //返回操作结果
        setActionResult(flag);
        return "success";
    }

    /**
     * @Title: changeStatus   
     * @Description:  更改状态  
     * @param @throws Exception    
     * @return void      
     * @author gaoguangchao
     * @date 2014-01-14 14:16:14
     * @throws
     */
    public void changeStatus() throws Exception {
    	Boolean result = true;
    	//获取当前登陆人
        String userId = getCurUserId();
    	Logmanager logmanager =  new Logmanager();
    	logmanager.setLogId(getParameter("LogId"));
    	logmanager.setStatus((byte) Integer.parseInt(getParameter("Status")));
    	logmanager.setUpdatePerson(userId);
    	logmanager.setUpdateTime(new Date());
    	try {
            //根据自定义sql进行更新
            logmanagerService.updateByCondition("changeStatus", logmanager);
            addLog(true,"修改日志状态成功");
    	} catch (Exception e) {
    		addLog(false,e);
    		 result = false;
            log.error(e);
    	}
    	getResponse().getWriter().write(result.toString());
    }

    /**
     * @return the listLogmanager
     */
    public List<Logmanager> getListLogmanager() {
        return listLogmanager;
    }

    /**
     * @param listLogmanager the listLogmanager to set
     */
    public void setListLogmanager(List<Logmanager> listLogmanager) {
        this.listLogmanager = listLogmanager;
    }

    /**
     * @return the logmanager
     */
    public Logmanager getLogmanager() {
        return logmanager;
    }

    /**
     * @param logmanager the logmanager to set
     */
    public void setLogmanager(Logmanager logmanager) {
        this.logmanager = logmanager;
    }

    /**
     * @return 返回分页
     */
    public Page<Logmanager> getPage() {
        return page;
    }

    /**
     * @param 设置分页
     */
    public void setPage(Page<Logmanager> page) {
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
}
