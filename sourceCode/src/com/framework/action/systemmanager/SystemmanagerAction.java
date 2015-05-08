/**
 * @Title: SystemmanagerAction.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.action.systemmanager
 * @Description: 。
 * @author lixiaoguang
 * @date 2014-04-03 13:41:59
 * @version V1.0
 */
package com.framework.action.systemmanager;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.util.GetConfig;
import com.util.Util;
import com.core.base.BaseAction;
import com.orm.Page;
import com.orm.Sort;
import com.framework.entity.module.ModuleManager;
import com.framework.entity.systemmanager.SystemModuleRela;
import com.framework.entity.systemmanager.Systemmanager;
import com.framework.service.module.IModuleService;
import com.framework.service.systemmanager.SystemmanagerService;
/**
 * @ClassName: SystemmanagerAction
 * @Description: 表现层控制类。
 * @author lixiaoguang
 * @date 2014-04-03 13:41:59
 */
@Controller
public class SystemmanagerAction extends BaseAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8600078314108387292L;
	Logger log = Logger.getLogger(this.getClass());
    @Resource(name = "systemmanagerService")
    private SystemmanagerService systemmanagerService;
    //模块管理
    @Resource(name = "moduleServiceImp")
	private IModuleService moduleServiceImp;

    //操作类型：add 添加 ；detail 查看； modify 修改
    private String opType;
    private Systemmanager systemmanager;
    private List<Systemmanager> listSystemmanager;
    private Page<Systemmanager> page;
    private Page<ModuleManager> pageModuleManager;
    private List<ModuleManager> moduleList=null; //模块列表
    
    /* 当前页号及每页数 */
    private int currentPage;
    private int pageSize;
    //操作结果
    private String actionResult;
	String outPath;
	private String fileFileName;
	
	private String SystemCode; //应用ID
	
    /**
     * @Title: findList
     * @Description: 页面。
     * @return
     * @throws Exception
     * @return String
     * @author lixiaoguang
     * @date 2014-04-03 13:41:59
     * @throws
     */
    public String findList() throws Exception {
        page = new Page<Systemmanager>();
        //设置每页条数
        page.setPageSize(pageSize);
        //设置排序
        page.addOrder(Sort.asc("SystemNumber"));
        page.addOrder(Sort.asc("UpdateTime"));
        //设置当前页
        page.setCurrentPage(currentPage);
        //对应map中findAll
        page = systemmanagerService.findByCondition("findAll", systemmanager, page);
        listSystemmanager = page.getResultList();
        return "success";
    }

    /**
     * @Title: gotoAdd
     * @Description: 跳转到添加页面.
     * @return
     * @return String
     * @author lixiaoguang
     * @date 2014-04-03 13:41:59
     * @throws
     */
    public String gotoAdd() {
        return "success";
    }

    /**
     * @throws Exception 
     * @Title: insert
     * @Description: 添加
     * @return
     * @return String
     * @author lixiaoguang
     * @date 2014-04-03 13:41:59
     * @throws
     */
    public String insert() throws Exception {
    	String  flag = "success";
        //获取当前登陆人
        String userId = getCurUserId();
        String types = getParameter("type"); //是否替换首选项 1为替换
        // 主键
        systemmanager.setSystemCode(UUID.randomUUID().toString());
        //设置创建人
        systemmanager.setCreatePerson(userId);
        //设置创建时间
        systemmanager.setCreateTime(new Date());
        //设置修改人
      	systemmanager.setUpdatePerson(userId);
      	//设置修改时间
    	systemmanager.setUpdateTime(new Date());
        //设置状态
//        systemmanager.setStatus(1);
		String base =  GetConfig.getFileoutputpath();
//		对密码MD5加密
		outPath=base+fileFileName;
		if(fileFileName !="" && fileFileName.length()>=1){
			File imga=new File(outPath);
			FileInputStream fin = new FileInputStream(outPath);
			byte[] nbf = new byte[(int) imga.length()];
			fin.read(nbf);          
			fin.close();  
			systemmanager.setBigLogo(nbf);
		}     
    	
    	
        try {
            //使用泛型新增实体
            //对应map中add
        	if("1".equals(types)){
        		systemmanagerService.updateByCondition("changePreferences", systemmanager);
        	}
        		systemmanagerService.addEntity(systemmanager);
        	
            
        } catch (Exception e) {
        	flag = "error";
            log.error(e);
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
    * @author lixiaoguang
    * @date 2014-04-03 13:41:59
    * @throws
     */
    public String detail() throws Exception {
        //获取前台传递的id  get方式传值
        String SystemCode = getParameter("id");
        //查询实体
        //对应map中findById
        Systemmanager systemmanager = (Systemmanager) systemmanagerService.findById(SystemCode);
        setSystemmanager(systemmanager);
        return "success";
    } 
    
    /**
    * @Title: modify  
    * @Description:    进入修改页面
    * @param @return
    * @param @throws Exception    
    * @return String      
    * @author lixiaoguang
    * @date 2014-04-03 13:41:59
    * @throws
     */
    public String gotoModify() throws Exception {
    	//获取前台传递的id  get方式传值
    	String SystemCode = getParameter("id");
    	//查询实体
    	//对应map中findById
    	Systemmanager systemmanager = (Systemmanager) systemmanagerService.findById(SystemCode);
    	setSystemmanager(systemmanager);
    	return "success";
    } 
    
    /**
    * @Title: update
    * @Description:修改
    * @param @return
    * @param @throws Exception    
    * @return String      
    * @author lixiaoguang
    * @date 2014-04-03 13:41:59
    * @throws
     */
    public String update() throws Exception {
    	String flag = "success";
        //获取当前登陆人
        String userId = getCurUserId();
        String types = getParameter("type");
        //设置修改人
      	systemmanager.setUpdatePerson(userId);
      	//设置修改时间
    	systemmanager.setUpdateTime(new Date());
    	systemmanager.setCreatePerson(userId);
    	systemmanager.setCreateTime(new Date());
        try {
            //对应map中update
        	if("1".equals(types)){
        		systemmanagerService.updateByCondition("changePreferences", systemmanager);
        	}
            systemmanagerService.updateEntity(systemmanager);
        } catch (Exception e) {
        	flag = "error";
            log.error(e);
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
     * @author lixiaoguang
     * @date 2014-04-03 13:41:59
     * @throws
     */
    public String delete() {
    	String flag = "success";
    	String SystemCode = getParameter("id");
    	try {
            //获取前台传递的id  get方式传值
            //对应map中deleteBatch
            systemmanagerService.deleteEntityOfLogical(SystemCode);
        } catch (Exception e) {
            flag = "error";
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
     * @author lixiaoguang
     * @date 2014-04-03 13:41:59
     * @throws
     */
    public String deleteBatch() {
        String  flag = "success";
        String SystemCodes = getParameter("ids");
        if(SystemCodes!=null){
            try {
                String[] ids = SystemCodes.split(",");
                //对应map中deleteBatch
                systemmanagerService.bulkDeleteOfLogical(ids);
            } catch (Exception e) {
                flag = "error";
                log.error(e);
            }
        }
        //返回操作结果
        setActionResult(flag);
        return "success";
    }
    
    
    /**
     * 判断是否已存在首选项
     * @throws Exception
     */
    public void selectIsPreferences() throws Exception {
    	String result = null;
    	List<?> list=systemmanagerService.findByCondition("selectIsPreferences",null);
    	int  conut =list.size();
    	result = String.valueOf(conut);
    	getResponse().getWriter().write(result);	
    }
    /**
     * @Title: changeStatus   
     * @Description:  更改状态  
     * @param @throws Exception    
     * @return void      
     * @author lixiaoguang
     * @date 2014-04-03 13:41:59
     * @throws
     */
    public void changeStatus() throws Exception {
    	Boolean result = true;
    	//获取当前登陆人
        String userId = getCurUserId();
    	Systemmanager systemmanager =  new Systemmanager();
    	systemmanager.setSystemCode(getParameter("id"));
    	systemmanager.setStatus(Integer.parseInt(getParameter("status")));
    	systemmanager.setUpdatePerson(userId);
    	systemmanager.setUpdateTime(new Date());
    	try {
            //根据自定义sql进行更新
            systemmanagerService.updateByCondition("updateStatus", systemmanager);
    	} catch (Exception e) {
    		result = false;
            log.error(e);
    	}
    	getResponse().getWriter().write(result.toString());
    }
    
    /**  
     * @Title: setModule  
     * @Description: 给应用设置模块  
     * @param @return      
     * @return String  
     * @author lyc
     * @date 2014年9月1日 下午12:24:09
     * @throws  
     */
     public String setModuleManager() {
    	 String userId = getCurUserId();
 		String SystemCode = getParameter("SystemCode");
 		String ids = getParameter("ids");
 		String [] arrayIds=null;
 		List<SystemModuleRela> listSystemManager=new  ArrayList<SystemModuleRela>();
 		if(Util.isNotNull(ids)){
 			arrayIds=ids.split(",");
 			for(int i=0 ; i<arrayIds.length ; i++){
 				SystemModuleRela  systemModuleRela = new SystemModuleRela();
 				systemModuleRela.setSystemId(SystemCode.trim());
 				systemModuleRela.setUUID(UUID.randomUUID().toString());
 				systemModuleRela.setModuleId(arrayIds[i].trim());
 				systemModuleRela.setStatus(1);
 				systemModuleRela.setCreatePerson(userId);
 				systemModuleRela.setCreateTime(new Date());
 				systemModuleRela.setUpdatePerson(userId);
 				systemModuleRela.setUpdateTime(new Date());
 				listSystemManager.add(systemModuleRela);
 			}
 			try {
 				systemmanagerService.deleteByConditions("systemDelModule", SystemCode);
 				systemmanagerService.addListOfEntity("systemAddModule", listSystemManager);
 			} catch (Exception e) {
 				log.error(e);
 			}
 			
 		}
 		return "success";
 	}
     
     /**  
     * @Title: detailModule  
     * @Description: 查看模块下的应用
     * @param @return      
     * @return String  
     * @author lyc
     * @date 2014年9月1日 下午12:47:13
     * @throws  
     */
     public String detailModule() {
    	 if(Util.isNull(systemmanager.getSystemCode())){
    		 SystemCode = getParameter("SystemCode");
    		 systemmanager.setSystemCode(SystemCode);
    	 }else{
    		 SystemCode =  systemmanager.getSystemCode();
    	 }
 			pageModuleManager = new Page<ModuleManager>();
 	        //设置每页条数
 			pageModuleManager.setPageSize(pageSize);
 	        //设置当前页
 			pageModuleManager.setCurrentPage(currentPage);
 	        //对应map中findAll1
 			Map<String,String> param = new HashMap<String,String>();
 			param.put("SystemCode", SystemCode);
 			pageModuleManager = moduleServiceImp.findByCondition("detailModuleList",param , pageModuleManager);
 	        moduleList = pageModuleManager.getResultList();
 			setModuleList(moduleList);
 			setSystemCode(SystemCode);
 		return "success";
 	}


    /**
     * @return the listSystemmanager
     */
    public List<Systemmanager> getListSystemmanager() {
        return listSystemmanager;
    }

    /**
     * @param listSystemmanager the listSystemmanager to set
     */
    public void setListSystemmanager(List<Systemmanager> listSystemmanager) {
        this.listSystemmanager = listSystemmanager;
    }

    /**
     * @return the systemmanager
     */
    public Systemmanager getSystemmanager() {
        return systemmanager;
    }

    /**
     * @param systemmanager the systemmanager to set
     */
    public void setSystemmanager(Systemmanager systemmanager) {
        this.systemmanager = systemmanager;
    }

    /**
     * @return 返回分页
     */
    public Page<Systemmanager> getPage() {
        return page;
    }

    /**
     * @param 设置分页
     */
    public void setPage(Page<Systemmanager> page) {
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

	public String getOutPath() {
		return outPath;
	}

	public void setOutPath(String outPath) {
		this.outPath = outPath;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public List<ModuleManager> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<ModuleManager> moduleList) {
		this.moduleList = moduleList;
	}

	public Page<ModuleManager> getPageModuleManager() {
		return pageModuleManager;
	}

	public void setPageModuleManager(Page<ModuleManager> pageModuleManager) {
		this.pageModuleManager = pageModuleManager;
	}

	public String getSystemCode() {
		return SystemCode;
	}

	public void setSystemCode(String systemCode) {
		SystemCode = systemCode;
	}
	
	
    
    
    
}
