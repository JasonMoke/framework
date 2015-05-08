/**
 * @Title: RoleGroupAction.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.action.rolegroup
 * @Description: 。
 * @author gaogc
 * @date 2014-08-29 13:55:02
 * @version V1.0
 */
package com.framework.action.rolegroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.util.Util;
import com.orm.Page;
import com.orm.Sort;
import com.core.base.BaseAction;
import com.framework.entity.rolegroup.RoleGroup;
import com.framework.entity.rolegroup.RolegroupRoleRela;
import com.framework.service.rolegroup.RoleGroupService;
/**
 * @ClassName: RoleGroupAction
 * @Description: 表现层控制类。
 * @author gaogc
 * @date 2014-08-29 13:55:02
 */
@Controller
public class RoleGroupAction extends BaseAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2918133198700726250L;
	Logger log = Logger.getLogger(this.getClass());
    @Resource(name = "rolegroupService")
    private RoleGroupService rolegroupService;

    //操作类型：add 添加 ；detail 查看； modify 修改
    private String opType;
    private RoleGroup rolegroup;
    private List<RoleGroup> listRoleGroup;
    private Page<RoleGroup> page;
    
    /* 当前页号及每页数 */
    private int currentPage;
    private int pageSize;
    //操作结果
    private String actionResult;
    private String switchFlag;
   

    /**
     * @Title: findList
     * @Description: 页面。
     * @return
     * @throws Exception
     * @return String
     * @author gaogc
     * @date 2014-08-29 13:55:02
     * @throws
     */
    public String findList() throws Exception {
        page = new Page<RoleGroup>();
        //设置每页条数
        page.setPageSize(pageSize);
        //设置排序
        page.addOrder(Sort.asc("SeqNum"));
        page.addOrder(Sort.asc("UpdateTime"));
        //设置当前页
        page.setCurrentPage(currentPage);
        //对应map中findAll
        page = rolegroupService.findByCondition("findAll", rolegroup, page);
        listRoleGroup = page.getResultList();
        //设置列表显示模式
        setSwitchFlag(switchFlag);
        return "success";
    }

    /**
     * @Title: gotoAdd
     * @Description: 跳转到添加页面.
     * @return
     * @return String
     * @author gaogc
     * @date 2014-08-29 13:55:02
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
     * @author gaogc
     * @date 2014-08-29 13:55:02
     * @throws
     */
    public String insert() {
    	String flag = "error";
    	boolean result = true;
        // 主键
        rolegroup.setUUID(UUID.randomUUID().toString());
        //设置创建人
        rolegroup.setCreatePerson(getCurUserId());
        //设置创建时间
        rolegroup.setCreateTime(new Date());
        //设置修改人
      	rolegroup.setUpdatePerson(getCurUserId());
      	//设置修改时间
    	rolegroup.setUpdateTime(new Date());
                
        try {
            //使用泛型新增实体
            //对应map中add
            rolegroupService.addEntity(rolegroup);
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
    * @author gaogc
    * @date 2014-08-29 13:55:02
    * @throws
     */
    public String detail() throws Exception {
        
        //获取前台传递的id  get方式传值
        String UUID = getParameter("id");
        //查询实体
        //对应map中findById
        RoleGroup rolegroup = (RoleGroup) rolegroupService.findById(UUID);
        setRolegroup(rolegroup);
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
    * @author gaogc
    * @date 2014-08-29 13:55:02
    * @throws
     */
    public String gotoModify() throws Exception {
    	//获取前台传递的id  get方式传值
    	String UUID = getParameter("id");
    	//查询实体
    	//对应map中findById
    	RoleGroup rolegroup = (RoleGroup) rolegroupService.findById(UUID);
    	setRolegroup(rolegroup);
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
    * @author gaogc
    * @date 2014-08-29 13:55:02
    * @throws
     */
    public String update() throws Exception {
    	String flag = "error";
    	boolean result = true;
         //设置修改人
      	rolegroup.setUpdatePerson(getCurUserId());
      	//设置修改时间
    	rolegroup.setUpdateTime(new Date());
        try {
            //对应map中update
            rolegroupService.updateEntity(rolegroup);
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
     * @author gaogc
     * @date 2014-08-29 13:55:02
     * @throws
     */
    public String delete() {
    	String flag = "error";
    	String RoleGroupName = getParameter("id");
    	try {
            //获取前台传递的id  get方式传值
            //对应map中deleteBatch
            rolegroupService.deleteEntityOfLogical(RoleGroupName);
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
     * @author gaogc
     * @date 2014-08-29 13:55:02
     * @throws
     */
    public String deleteBatch() {
    	String flag = "error";
        String RoleGroupNames = getParameter("RoleName");
        if(RoleGroupNames!=null){
            try {
                String[] ids = RoleGroupNames.split(",");
                //对应map中deleteBatch
                rolegroupService.bulkDeleteOfLogical(ids);
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
     * @author gaogc
     * @date 2014-08-29 13:55:02
     * @throws
     */
    public void changeStatus() throws Exception {
    	Boolean result = true;
    	
    	RoleGroup rolegroup =  new RoleGroup();
    	rolegroup.setRoleGroupName(getParameter("id"));
    	rolegroup.setStatus((byte) Integer.parseInt(getParameter("status")));
    	rolegroup.setUpdatePerson(getCurUserId());
    	rolegroup.setUpdateTime(new Date());
    	try {
            //根据自定义sql进行更新
            rolegroupService.updateByCondition("updateStatus", rolegroup);
    	} catch (Exception e) {
            log.error(e);
    	}
    	ServletActionContext.getResponse().getWriter().write(result.toString());
    }
    /**
     * 
    * @Title: selRole   
    * @Description:
    * @return
    * @throws Exception
    * @return String        
    * @author gaoguangchao
    * @date 2014年8月29日 下午4:44:10 
    *
     */
    public String selRole() throws Exception {
		Boolean result = true;
		String flag = "error";
		List<RolegroupRoleRela> list = new ArrayList<RolegroupRoleRela>();
		String roleGroupId = getParameter("UserId");
		String[] RoleIdarray = Util.stringToArray(getParameter("RoleId")) ;
		for (int i = 0; i < RoleIdarray.length; i++) {
			RolegroupRoleRela rrr = new RolegroupRoleRela();
			rrr.setRoleGroupId(roleGroupId);
			rrr.setUUID(UUID.randomUUID().toString());
			rrr.setRoleId(RoleIdarray[i]);
			list.add(rrr);
		}
		try {
			rolegroupService.addRolegroupRoleRela(list, roleGroupId);
		} catch (Exception e) {
			result = false;
			log.error(e);
		}
		if(result){
			flag = "success";
		}else{
			flag = "error";
		}
		setActionResult(flag);
		return flag;
	}
    /**
     * @return the listRoleGroup
     */
    public List<RoleGroup> getListRoleGroup() {
        return listRoleGroup;
    }

    /**
     * @param listRoleGroup the listRoleGroup to set
     */
    public void setListRoleGroup(List<RoleGroup> listRoleGroup) {
        this.listRoleGroup = listRoleGroup;
    }


    /**
     * @return 返回分页
     */
    public Page<RoleGroup> getPage() {
        return page;
    }

    /**
     * @param 设置分页
     */
    public void setPage(Page<RoleGroup> page) {
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

	public String getSwitchFlag() {
		return switchFlag;
	}

	public void setSwitchFlag(String switchFlag) {
		this.switchFlag = switchFlag;
	}

	public RoleGroup getRolegroup() {
		return rolegroup;
	}

	public void setRolegroup(RoleGroup rolegroup) {
		this.rolegroup = rolegroup;
	}
}
