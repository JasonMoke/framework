/**
 * @Title: OrganAction.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.action.organ
 * @Description: 。
 * @author gaoguangchao
 * @date 2014-04-08 17:17:55
 * @version V1.0
 */
package com.framework.action.organ;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.util.SessionInfo;
import com.util.Util;
import com.orm.Page;
import com.orm.Sort;
import com.core.base.BaseAction;
import com.framework.entity.user.UserInfoManager;
import com.framework.entity.organ.Organ;
import com.framework.service.organ.OrganService;
/**
 * @ClassName: OrganAction
 * @Description: 表现层控制类。
 * @author gaoguangchao
 * @date 2014-04-08 17:17:55
 */
@Controller
public class OrganAction extends BaseAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2107534980258269537L;
	Logger log = Logger.getLogger(this.getClass());
    @Resource(name = "organService")
    private OrganService organService;

    //操作类型：add 添加 ；detail 查看； modify 修改
    private String opType;
    private Organ organ;
    private Organ organs;
    private List<Organ> listOrgan;
    private Page<Organ> page;
    
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
     * @date 2014-04-08 17:17:55
     * @throws
     */
    public String findList() throws Exception {
        //获取前台传递的id  get方式传值
        String ParentId = getParameter("ParentId");
        if(Util.isNull(ParentId)){
        	ParentId = "0";
        }
        Organ organparam = new Organ();
        if(Util.isNull(organ)){
        	organparam.setParentId(ParentId);
        }else{
        	organparam = organ;
        	ParentId = organ.getParentId();
            if(Util.isNull(ParentId)){
            	ParentId = "0";
            }
        	organparam.setParentId(ParentId);
        }
        page = new Page<Organ>();
        //设置每页条数
        page.setPageSize(pageSize);
        //设置排序
        page.addOrder(Sort.asc("SeqNum"));
        page.addOrder(Sort.asc("UpdateTime"));
        //设置当前页
        page.setCurrentPage(currentPage);
        //对应map中findAll
        page = organService.findByCondition("findAll", organparam, page);
        listOrgan = page.getResultList();
        setOrgan(organparam);
        return "success";
    }

    /**
     * @Title: gotoAdd
     * @Description: 跳转到添加页面.
     * @return
     * @return String
     * @author gaoguangchao
     * @date 2014-04-08 17:17:55
     * @throws
     */
    public String gotoAdd() {
        return "success";
    }
    /**
     * 
    * @Title: organManager   
    * @Description:进入组织机构管理
    * @param @return      
    * @return String   
    * @author gaoguangchao
    * @date 2014年4月9日 下午1:51:44 
    * @throws   
    * @return
     */
    public String organManager() {
    	return "success";
    }

    /**
     * @Title: insert
     * @Description: 添加
     * @return
     * @return String
     * @author gaoguangchao
     * @date 2014-04-08 17:17:55
     * @throws
     */
    public String insert() {
    	String flag = "success";
        //获取当前登陆人
        String userId = getCurUserId();
        // 主键
        organ.setOrganId(UUID.randomUUID().toString());
        //设置创建人
        organ.setCreatePerson(userId);
        //设置创建时间
        organ.setCreateTime(new Date());
        //设置修改人
      	organ.setUpdatePerson(userId);
      	//设置修改时间
    	organ.setUpdateTime(new Date());
        //设置状态
        organ.setStatus( 1);
        if(Util.isNull(organ.getParentId()) || "0".equals(organ.getParentId())){
        	organ.setParentId("0");
        	organ.setTreePath(organ.getOrganId());
        }else{
        	Organ organs = organService.findById(organ.getParentId());
        	organ.setTreePath(organs.getTreePath()+","+organ.getOrganId());
        }
        try {
             //使用泛型新增实体
             //对应map中add
             organService.addEntity(organ);
             addLog(true,"添加组织成功");
        } catch (Exception e) {
        	 flag = "error";
             log.error(e);
             addLog(false, e);
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
    * @date 2014-04-08 17:17:55
    * @throws
     */
    public String detail() throws Exception {
        //获取前台传递的id  get方式传值
        String Id = getParameter("id");
        //查询实体
        //对应map中findById
        Organ organ = (Organ) organService.findById(Id);
        setOrgan(organ);
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
    * @date 2014-04-08 17:17:55
    * @throws
     */
    public String gotoModify() throws Exception {
    	//获取前台传递的id  get方式传值
    	String Id = getParameter("id");
    	//查询实体
    	//对应map中findById
    	 organ = organService.findById(Id);
    	 organs =  organService.findById(organ.getParentId());
    	setOrgan(organ);
    	setOrgans(organs);
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
    * @date 2014-04-08 17:17:55
    * @throws
     */
    public String update() throws Exception {
    	String flag = "success";
        //获取当前登陆人
        String userId = getCurUserId();
        //设置修改人
      	organ.setUpdatePerson(userId);
      	//设置修改时间
    	organ.setUpdateTime(new Date());
    	if(Util.isNull(organ.getParentId()) || "0".equals(organ.getParentId())){
        	organ.setParentId("0");
        	organ.setTreePath(organ.getOrganId());
        }else{
        	Organ organs = organService.findById(organ.getParentId());
        	organ.setTreePath(organs.getTreePath()+","+organ.getOrganId());
        }
        try {
            //对应map中update
            organService.updateEntity(organ);
            addLog(true,"修改组织成功");
        } catch (Exception e) {
        	flag = "error";
            log.error(e);
            addLog(false, e);
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
     * @author gaoguangchao
     * @date 2014-04-08 17:17:55
     * @throws
     */
    public String delete() {
    	String flag = "success";
    	String Id =getParameter("id");
    	try {
            //获取前台传递的id  get方式传值
            //对应map中deleteBatch
            organService.deleteEntityOfLogical(Id);
            addLog(true,"删除组织成功");
        } catch (Exception e) {
            flag = "error";
            addLog(false, e);
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
     * @date 2014-04-08 17:17:55
     * @throws
     */
    public String deleteBatch() {
        String flag = "success";
        String Ids = getParameter("ids");
        if(Ids!=null){
            try {
                String[] ids = Ids.split(",");
                //对应map中deleteBatch
                organService.bulkDeleteOfLogical(ids);
                addLog(true,"批量删除组织成功");
            } catch (Exception e) {
                flag = "error";
                addLog(false, e);
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
     * @date 2014-04-08 17:17:55
     * @throws
     */
    public void changeStatus() throws Exception {
    	Boolean result = true;
    	//获取当前登陆人
        UserInfoManager userInfoManager = SessionInfo.getCurUser();
        String userId = userInfoManager.getUserId();
    	Organ organ =  new Organ();
    	organ.setOrganId(getParameter("id"));
    	organ.setStatus(Integer.parseInt(getParameter("status")));
    	organ.setUpdatePerson(userId);
    	organ.setUpdateTime(new Date());
    	try {
            //根据自定义sql进行更新
            organService.updateByCondition("updateStatus", organ);
            addLog(true,"更换组织状态成功");
    	} catch (Exception e) {
    		result = false;
    		addLog(false, e);
            log.error(e);
    	}
    	getResponse().getWriter().write(result.toString());
    }

    /**
     * @return the listOrgan
     */
    public List<Organ> getListOrgan() {
        return listOrgan;
    }

    /**
     * @param listOrgan the listOrgan to set
     */
    public void setListOrgan(List<Organ> listOrgan) {
        this.listOrgan = listOrgan;
    }

    /**
     * @return the organ
     */
    public Organ getOrgan() {
        return organ;
    }

    /**
     * @param organ the organ to set
     */
    public void setOrgan(Organ organ) {
        this.organ = organ;
    }

    /**
     * @return 返回分页
     */
    public Page<Organ> getPage() {
        return page;
    }

    /**
     * @param 设置分页
     */
    public void setPage(Page<Organ> page) {
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

	public Organ getOrgans() {
		return organs;
	}

	public void setOrgans(Organ organs) {
		this.organs = organs;
	}
    
    
}
