/**
 * @Title: SystemparamAction.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.action.systemparam
 * @Description: 。
 * @author gaoguangchao
 * @date 2014-03-07 17:29:35
 * @version V1.0
 */
package com.framework.action.systemparam;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.orm.Page;
import com.orm.Sort;
import com.core.base.BaseAction;
import com.core.database.databean.Data;
import com.framework.entity.systemparam.Systemparam;
import com.framework.service.systemparam.SystemparamService;
/**
 * @ClassName: SystemparamAction
 * @Description: 表现层控制类。
 * @author gaoguangchao
 * @date 2014-03-07 17:29:35
 */
/**
* @ClassName: SystemparamAction
* @Description: TODO(这里用一句话描述这个类的作用)
* @author lyc
* @date 2014年9月1日 下午12:24:03
*
*/
@Controller
public class SystemparamAction extends BaseAction {
	
	private static final long serialVersionUID = 7863998537771116600L;
	
	Logger log = Logger.getLogger(this.getClass());
    @Resource(name = "systemparamService")
    private SystemparamService systemparamService;

    //操作类型：add 添加 ；detail 查看； modify 修改
    private String opType;
    private Systemparam systemparam;
    private List<Systemparam> listSystemparam;
    private Page<Systemparam> page;
    
    /* 当前页号及每页数 */
    private int currentPage;
    private int pageSize;
    //操作结果
    private String actionResult;
    //列表 显示模式（列表、缩略图、详细）
    private String switchFlag = "det";

    /**
     * @Title: findList
     * @Description: 页面。
     * @return
     * @throws Exception
     * @return String
     * @author gaoguangchao
     * @date 2014-03-07 17:29:35
     * @throws
     */
    public String findList() throws Exception {
        page = new Page<Systemparam>();
        //设置每页条数
        page.setPageSize(pageSize);
        //设置排序
        page.addOrder(Sort.asc("ParamNumber"));
        page.addOrder(Sort.asc("UpdateTime"));
        //设置当前页
        page.setCurrentPage(currentPage);
        //对应map中findAll
        page = systemparamService.findByCondition("findAll", systemparam, page);
        listSystemparam = page.getResultList();
        //设置列表显示模式
        setSwitchFlag(switchFlag);
        return "success";
    }

    /**
     * @Title: gotoAdd
     * @Description: 跳转到添加页面.
     * @return
     * @return String
     * @author gaoguangchao
     * @date 2014-03-07 17:29:35
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
     * @date 2014-03-07 17:29:35
     * @throws
     */
    public String insert() {
    	/*if(!isPermitted("systemparam:insert")){
    		  return "unauthorized";
    	}*/
    	String flag = "error";
    	boolean result = true;
        
        String userId = getCurUserId();
        
        // 主键
        systemparam.setParamId(UUID.randomUUID().toString());
        //设置创建人
        systemparam.setCreatePerson(userId);
        //设置创建时间
        systemparam.setCreateTime(new Date());
        //设置修改人
      	systemparam.setUpdatePerson(userId);
      	//设置修改时间
    	systemparam.setUpdateTime(new Date());
                
        try {
            //使用泛型新增实体
            //对应map中add
            systemparamService.addEntity(systemparam);
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
    * @author gaoguangchao
    * @date 2014-03-07 17:29:35
    * @throws
     */
    public String detail() throws Exception {
        //获取前台传递的id  get方式传值
        String ParamId = getParameter("id");
        //查询实体
        //对应map中findById
        Systemparam systemparam = (Systemparam) systemparamService.findById(ParamId);
        data = new Data();
        
        setSystemparam(systemparam);
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
    * @author gaoguangchao
    * @date 2014-03-07 17:29:35
    * @throws
     */
    public String gotoModify() throws Exception {
    	//获取前台传递的id  get方式传值
    	String ParamId = getParameter("id");
    	//查询实体
    	//对应map中findById
    	Systemparam systemparam = (Systemparam) systemparamService.findById(ParamId);
    	setSystemparam(systemparam);
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
    * @author gaoguangchao
    * @date 2014-03-07 17:29:35
    * @throws
     */
    public String update() throws Exception {
    	String flag = "error";
    	boolean result = true;
        String userId = getCurUserId();
        //设置修改人
      	systemparam.setUpdatePerson(userId);
      	//设置修改时间
    	systemparam.setUpdateTime(new Date());
        try {
            //对应map中update
            systemparamService.updateEntity(systemparam);
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
     * @author gaoguangchao
     * @date 2014-03-07 17:29:35
     * @throws
     */
    public String delete() {
    	String flag = "error";
    	String ParamId = getParameter("id");
    	try {
            //获取前台传递的id  get方式传值
            //对应map中deleteBatch
            systemparamService.deleteEntityOfLogical(ParamId);
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
     * @author gaoguangchao
     * @date 2014-03-07 17:29:35
     * @throws
     */
    public String deleteBatch() {
        String flag = "error";
        String ParamIds = getParameter("ids");
        if(ParamIds!=null){
            try {
                String[] ids = ParamIds.split(",");
                //对应map中deleteBatch
                systemparamService.bulkDeleteOfLogical(ids);
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
     * @author gaoguangchao
     * @date 2014-03-07 17:29:35
     * @throws
     */
    public void changeStatus() throws Exception {
    	Boolean result = true;
    	//获取当前登陆人
        String userId = getCurUserId();
    	Systemparam systemparam =  new Systemparam();
    	systemparam.setParamId(getParameter("id"));
    	systemparam.setStatus((byte) Integer.parseInt(getParameter("status")));
    	systemparam.setUpdatePerson(userId);
    	systemparam.setUpdateTime(new Date());
    	try {
            //根据自定义sql进行更新
            systemparamService.updateByCondition("updateStatus", systemparam);
    	} catch (Exception e) {
            log.error(e);
    	}
    	ServletActionContext.getResponse().getWriter().write(result.toString());
    }

    /**
     * @return the listSystemparam
     */
    public List<Systemparam> getListSystemparam() {
        return listSystemparam;
    }

    /**
     * @param listSystemparam the listSystemparam to set
     */
    public void setListSystemparam(List<Systemparam> listSystemparam) {
        this.listSystemparam = listSystemparam;
    }

    /**
     * @return the systemparam
     */
    public Systemparam getSystemparam() {
        return systemparam;
    }

    /**
     * @param systemparam the systemparam to set
     */
    public void setSystemparam(Systemparam systemparam) {
        this.systemparam = systemparam;
    }

    /**
     * @return 返回分页
     */
    public Page<Systemparam> getPage() {
        return page;
    }

    /**
     * @param 设置分页
     */
    public void setPage(Page<Systemparam> page) {
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
}
