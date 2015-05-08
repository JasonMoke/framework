/**
 * @Title: NestedSetTreeAction.java   
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.action.tree   
 * @Description:    
 * @author guangchao    
 * @date 2014-1-14 下午12:00:34   
 * @version V1.0 
 */
package com.framework.action.tree;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.framework.entity.tree.TreeEntity;
import com.framework.service.tree.TreeService;
import com.opensymphony.xwork2.ActionSupport;
import com.util.GsonUtil;
import com.util.Util;

/**
 * @ClassName: NestedSetTreeAction
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author guangchao
 * @date 2014-1-14 下午12:00:34
 * 
 */
public class TreeAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6763898450957208924L;
	Logger log = Logger.getLogger(this.getClass());
	@Resource(name = "treeService")
	private TreeService treeService;
	private String jsonData;
	private String roleName;
	private String UserId;
	private String ModuleID;
	private String ResourcesId;
	private String RoleId;
	private String PublisherId;
	private String OrganId;
	private String DictName;
	/**
	 * 
	 * @Title: moduleTree
	 * @Description: 模块树
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author zhaojie
	 * @date 2014-1-21 下午5:48:22
	 * @throws
	 */
	/*public String moduleTree() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(true);
		Locale locale = (Locale) session.getAttribute("WW_TRANS_I18N_LOCALE");
		String systemCode = request.getParameter("id");
		String pid = request.getParameter("pid");
		List<TreeEntity> trList =new ArrayList<TreeEntity>();
		Map mapQuery=new HashMap();
		mapQuery.put("locale", locale.toString());
		
		if(systemCode==null||systemCode.equals("")){
			 trList = treeService.findByCondition("findSystemList",locale.toString());// 鑾峰彇鎵�湁鑺傜偣淇℃伅
		}else{
			 mapQuery.put("systemCode", systemCode);
			 mapQuery.put("pid", pid);
			 trList = treeService.findByCondition("findModuleList",mapQuery);// 获取所有节点信息

		}
		List<TreeEntity> trListChil=new ArrayList<TreeEntity>();
		if (locale == null) {
			locale = Locale.CHINA;
		}

		for (int i = 0; i < trList.size(); i++) {
			TreeEntity resultTreeEntity = trList.get(i);
			resultTreeEntity.setIsParent(true);
			resultTreeEntity.setNocheck(true);
			trListChil.add(resultTreeEntity);
		}
		JSONArray jarray=new JSONArray();  
		jarray.addAll(trListChil);  
	        HttpServletResponse response = ServletActionContext.getResponse();  
	        response.setCharacterEncoding("utf-8");  
	        PrintWriter pw = null;  
	        try {  
	            pw = response.getWriter();  
	            pw.write(jarray.toString());  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	        pw.flush();  
	        pw.close();  
		return SUCCESS;
	}*/
	
	/**  
	* @Title: moduleTree  
	* @Description: 模块树 
	* @param @return
	* @param @throws Exception      
	* @return String  
	* @author lyc
	* @date 2014年8月29日 下午2:45:54
	* @throws  
	*/
	@SuppressWarnings("unchecked")
	public String moduleTree() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		// 转换为ztree需要数据格式 id name pid open checked isParent
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		List<TreeEntity> resultList = new ArrayList<TreeEntity>();
		List<TreeEntity> trList = treeService.findByCondition("findAddModuleList",null);
		String UserId = request.getParameter("UserId");
		if (UserId != null && !"".equals(UserId)) {
			List<TreeEntity> trListById = treeService.findByCondition("findUserByRoleByOrganId", UserId);
			OrganId="";
			for(int j = 0; j < trListById.size(); j++){
				TreeEntity t = null;
				t = trListById.get(j);
				String td = t.getId();
				OrganId += td + ","; 
			}
			for (int i = 0; i < trList.size(); i++) {
				TreeEntity resultTreeEntity = null;
				resultTreeEntity = trList.get(i);
				String parentModuleId = resultTreeEntity.getId();
				for (int j = 0; j < trListById.size(); j++) {
					TreeEntity moduleByIdTreeEntity = null;
					moduleByIdTreeEntity = trListById.get(j);
					String childModuleId = moduleByIdTreeEntity.getId();
					if (childModuleId.equals(parentModuleId)|| childModuleId == parentModuleId) {
						resultTreeEntity.setChecked(true);
					}
				}
				resultList.add(resultTreeEntity);

			}
		} else {
			resultList = trList;
		}
		
		for (int i = 0; i < resultList.size(); i++) {
			TreeEntity resultTreeEntity = resultList.get(i);
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("id", resultTreeEntity.getId());
			if ("".equals(resultTreeEntity.getName())|| resultTreeEntity.getName() == null) {
				map.put("name", resultTreeEntity.getNameDefult());
			} else {
				map.put("name", resultTreeEntity.getName());
			}
			if (resultTreeEntity.isChecked()) {
				map.put("checked", true);
			}
			map.put("pid", resultTreeEntity.getPid());
			map.put("open", true);
			map.put("iconSkin", "app");
			// 判断是否为父节点
			if (!"0".equals(resultTreeEntity.getPid())) {
				map.put("isParent", false);
			} else {
				map.put("isParent", true);
			}
			// 是否可用
			if (resultTreeEntity.isChkDisabled()) {
				map.put("chkDisabled", true);
			}
			maps.add(map);
		}
		setRoleId(OrganId);
		setUserId(UserId);
		jsonData = GsonUtil.toJson(maps);// 生成json数据
		return SUCCESS;
	}


	@SuppressWarnings("unchecked")
	public String regionTree() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(true);
		Locale locale = (Locale) session.getAttribute("WW_TRANS_I18N_LOCALE");
		if (locale == null) {
			locale = Locale.CHINA;
		}
		String checkedId = request.getParameter("checkedId");
		// 转换为ztree需要数据格式 id name pid open checked isParent
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapfanxuan = new HashMap<String, Object>();
		mapfanxuan.put("id", "fanxuan");
		if(locale.toString()=="en_US"||locale.toString().equals("en_US")){
			mapfanxuan.put("name","Reverse selection");
		}else{
			mapfanxuan.put("name","反选");
		}
		mapfanxuan.put("pid", "fanxuan");
		mapfanxuan.put("open", true);
		maps.add(mapfanxuan);
		List<TreeEntity> trList = treeService.findByCondition("findRegionList",
				null);// 获取所有节点信息

		for (int i = 0; i < trList.size(); i++) {
			TreeEntity resultTreeEntity = trList.get(i);
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("id", resultTreeEntity.getId());
			map.put("name", resultTreeEntity.getName()+"("+resultTreeEntity.getId()+")");
			map.put("pid", resultTreeEntity.getPid());
			map.put("open", true);
			// 根据选中的资源id串进行回显
			if (Util.isNotNull(checkedId)) {
				String[] checkedIds = checkedId.split(",");
				for (int a = 0; a < checkedIds.length; a++) {
					if (Util.isNotNull(checkedIds[a])) {
						if (checkedIds[a] == resultTreeEntity.getId()
								|| checkedIds[a].equals(resultTreeEntity
										.getId())) {
							map.put("checked", true);
						}
					}
				}
			}
			map.put("isParent", false);
			/*
			 * if (!"地区".equals(resultTreeEntity.getPid())) {
			 * map.put("isParent", false); } else { map.put("isParent", true); }
			 */
			// 是否可用
			if (resultTreeEntity.isChkDisabled()) {
				map.put("chkDisabled", true);
			}

			maps.add(map);
		}
		
		jsonData = GsonUtil.toJson(maps);// 生成json数据
		return SUCCESS;
	}
	/**
	 * 
	* @Title: RegionTree   
	* @Description: 出版商要求地区树 
	* @param @return
	* @param @throws Exception      
	* @return String   
	* @author zhaojie
	* @date 2014-2-19 下午2:30:08 
	* @throws
	 */
	@SuppressWarnings("unchecked")
	public String RegionTree() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String checkedId = request.getParameter("region");
		// 转换为ztree需要数据格式 id name pid open checked isParent
		HttpSession session = request.getSession(true);
		Locale locale = (Locale) session.getAttribute("WW_TRANS_I18N_LOCALE");
		if(locale==null){
			locale = Locale.CHINA;
		}
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();

		List<TreeEntity> trList = treeService.findByCondition("findRegionList",
				null);// 获取所有节点信息
		Map<String, Object> mapfanxuan = new HashMap<String, Object>();
		mapfanxuan.put("id", "fanxuan");
		if(locale.toString()=="en_US"||locale.toString().equals("en_US")){
			mapfanxuan.put("name","Reverse selection");
		}else{
			mapfanxuan.put("name","反选");
		}
		
		mapfanxuan.put("pid", "fanxuan");
		mapfanxuan.put("open", true);
		maps.add(mapfanxuan);
		for (int i = 0; i < trList.size(); i++) {
			TreeEntity resultTreeEntity = trList.get(i);
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("id", resultTreeEntity.getId());
			map.put("name", resultTreeEntity.getName());
			map.put("pid", resultTreeEntity.getPid());
			map.put("open", true);
			// 根据选中的资源id串进行回显
			if (Util.isNotNull(checkedId)) {
				String[] checkedIds = checkedId.split(",");
				for (int a = 0; a < checkedIds.length; a++) {
					if (Util.isNotNull(checkedIds[a])) {
						if (checkedIds[a] == resultTreeEntity.getId()
								|| checkedIds[a].equals(resultTreeEntity
										.getId())) {
							map.put("checked", true);
						}
					}
				}
			}
			map.put("isParent", false);
			if (resultTreeEntity.isChkDisabled()) {
				map.put("chkDisabled", true);
			}

			maps.add(map);
		}
		
		jsonData = GsonUtil.toJson(maps);// 生成json数据
		return SUCCESS;
	}
	/**
	 * 
	* @Title: OrganTree   
	* @Description:组织机构树
	* @param @return
	* @param @throws Exception      
	* @return String   
	* @author gaoguangchao
	* @date 2014年4月9日 下午12:50:12 
	* @throws   
	* @return
	* @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String OrganTree() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		// 转换为ztree需要数据格式 id name pid open checked isParent
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		List<TreeEntity> resultList = new ArrayList<TreeEntity>();
		List<TreeEntity> trList = treeService.findByCondition("findOrganList",null);
		String UserId = request.getParameter("UserId");
		if (UserId != null && !"".equals(UserId)) {
			List<TreeEntity> trListById = treeService.findByCondition("findUserByRoleByOrganId", UserId);
			OrganId="";
			for(int j = 0; j < trListById.size(); j++){
				TreeEntity t = null;
				t = trListById.get(j);
				String td = t.getId();
				OrganId += td + ","; 
			}
			for (int i = 0; i < trList.size(); i++) {
				TreeEntity resultTreeEntity = null;
				resultTreeEntity = trList.get(i);
				String parentModuleId = resultTreeEntity.getId();
				for (int j = 0; j < trListById.size(); j++) {
					TreeEntity moduleByIdTreeEntity = null;
					moduleByIdTreeEntity = trListById.get(j);
					String childModuleId = moduleByIdTreeEntity.getId();
					if (childModuleId.equals(parentModuleId)|| childModuleId == parentModuleId) {
						resultTreeEntity.setChecked(true);
					}
				}
				resultList.add(resultTreeEntity);

			}
		} else {
			resultList = trList;
		}
		
		for (int i = 0; i < resultList.size(); i++) {
			TreeEntity resultTreeEntity = resultList.get(i);
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("id", resultTreeEntity.getId());
			if ("".equals(resultTreeEntity.getName())|| resultTreeEntity.getName() == null) {
				map.put("name", resultTreeEntity.getNameDefult());
			} else {
				map.put("name", resultTreeEntity.getName());
			}
			if (resultTreeEntity.isChecked()) {
				map.put("checked", true);
			}
			map.put("pid", resultTreeEntity.getPid());
			map.put("open", false);
			map.put("iconSkin", "organ");
			// 判断是否为父节点
			if (!"0".equals(resultTreeEntity.getPid())) {
				map.put("isParent", false);
			} else {
				map.put("isParent", true);
			}
			// 是否可用
			if (resultTreeEntity.isChkDisabled()) {
				map.put("chkDisabled", true);
			}
			maps.add(map);
		}
		setRoleId(OrganId);
		setUserId(UserId);
		jsonData = GsonUtil.toJson(maps);// 生成json数据
		return SUCCESS;
	}
	/**
	 * 给用户选择组织
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String selOrganTree() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		// 转换为ztree需要数据格式 id name pid open checked isParent
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		List<TreeEntity> resultList = new ArrayList<TreeEntity>();
		List<TreeEntity> trList = treeService.findByCondition("findOrganList",null);
		String checkedId = request.getParameter("checkedId");
		String Id = request.getParameter("UserId");
		
		if (Id != null && !"".equals(Id)) {
			Map<String, String> mapQuery=new HashMap<String, String>();
			mapQuery.put("userId", Id);
			List<TreeEntity> trListById = treeService.findByCondition("findOrganIdByUser", mapQuery);

				for (int i = 0; i < trList.size(); i++) {
					TreeEntity resultTreeEntity = null;
					resultTreeEntity = trList.get(i);
					String parentModuleId = resultTreeEntity.getId();
					for (int j = 0; j < trListById.size(); j++) {
						TreeEntity moduleByIdTreeEntity = null;
						moduleByIdTreeEntity = trListById.get(j);
						if(moduleByIdTreeEntity!=null){
							String childModuleId = moduleByIdTreeEntity.getId();
							if (childModuleId.equals(parentModuleId)|| childModuleId == parentModuleId) {
								resultTreeEntity.setChecked(true);
							}
						}
						
					}
					resultList.add(resultTreeEntity);

				}

			
		} else {
			resultList = trList;
		}
		
		for (int i = 0; i < resultList.size(); i++) {
			TreeEntity resultTreeEntity = resultList.get(i);
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("id", resultTreeEntity.getId());
			if ("".equals(resultTreeEntity.getName())|| resultTreeEntity.getName() == null) {
				map.put("name", resultTreeEntity.getNameDefult());
			} else {
				map.put("name", resultTreeEntity.getName());
			}
			if (resultTreeEntity.isChecked()) {
				map.put("checked", true);
			}
			if (Util.isNotNull(checkedId)) {
				String[] checkedIds = checkedId.split(",");
				for (int a = 0; a < checkedIds.length; a++) {
					if (Util.isNotNull(checkedIds[a])) {
						if (checkedIds[a] == resultTreeEntity.getId()|| checkedIds[a].equals(resultTreeEntity.getId())) {
							map.put("checked", true);
						}
					}
				}
			}
			map.put("pid", resultTreeEntity.getPid());
			map.put("open", true);
			map.put("iconSkin", "organ");

			// 判断是否为父节点
			if (!"0".equals(resultTreeEntity.getPid())) {
				map.put("isParent", false);
			} else {
				map.put("isParent", true);
			}
			// 是否可用
			if (resultTreeEntity.isChkDisabled()) {
				map.put("chkDisabled", true);
			}
			maps.add(map);
		}
		setRoleId(OrganId);
		setUserId(UserId);
		jsonData = GsonUtil.toJson(maps);// 生成json数据
		return SUCCESS;
	}
	/**
	 * 
	* @Title: OrganTreeForUserList   
	* @Description:组织机构树 专门的用户列表左侧的树
	* @param @return
	* @param @throws Exception      
	* @return String   
	* @author lishanhe
	* @date 2014年4月9日 下午12:50:12 
	* @throws   
	* @return
	* @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String OrganTreeForUserList() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		// 转换为ztree需要数据格式 id name pid open checked isParent
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		List<TreeEntity> resultList = new ArrayList<TreeEntity>();
		List<TreeEntity> trList = treeService.findByCondition("findOrganList",null);
		String UserId = request.getParameter("UserId");
		if (UserId != null && !"".equals(UserId)) {
			List<TreeEntity> trListById = treeService.findByCondition("findOrganByUserID", UserId);
			OrganId="";
			for(int j = 0; j < trListById.size(); j++){
				TreeEntity t = null;
				t = trListById.get(j);
				String td = t.getId();
				OrganId += td + ",";
			}
			for (int i = 0; i < trList.size(); i++) {
				TreeEntity resultTreeEntity = null;
				resultTreeEntity = trList.get(i);
				String parentModuleId = resultTreeEntity.getId();
				for (int j = 0; j < trListById.size(); j++) {
					TreeEntity moduleByIdTreeEntity = null;
					moduleByIdTreeEntity = trListById.get(j);
					String childModuleId = moduleByIdTreeEntity.getId();
					if (childModuleId.equals(parentModuleId)
							|| childModuleId == parentModuleId) {
						resultTreeEntity.setChecked(true);
					}
				}
				resultList.add(resultTreeEntity);

			}
		} else {
			resultList = trList;
		}
		Map<String, Object> mapNeizhi = new HashMap<String, Object>();
		mapNeizhi.put("id", "neizhi");
		mapNeizhi.put("name", "内置");
		mapNeizhi.put("open", true);
		mapNeizhi.put("isParent", true);
		mapNeizhi.put("iconSkin", "home");
		mapNeizhi.put("chkDisabled", true);
		maps.add(mapNeizhi);
		for (int i = 0; i < resultList.size(); i++) {
			TreeEntity resultTreeEntity = resultList.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("id", resultTreeEntity.getId());
			if ("".equals(resultTreeEntity.getName())
					|| resultTreeEntity.getName() == null) {
				map.put("name", resultTreeEntity.getNameDefult());
			} else {
				map.put("name", resultTreeEntity.getName());
			}
			if (resultTreeEntity.isChecked()) {
				map.put("checked", true);
			}
			map.put("pid", resultTreeEntity.getPid());
			map.put("open", true);
			map.put("iconSkin", "app");
			// 判断是否为父节点
			if (!"0".equals(resultTreeEntity.getPid())) {
				map.put("isParent", false);
			} else {
				map.put("isParent", true);
			}
			// 是否可用
			if (resultTreeEntity.isChkDisabled()) {
				map.put("chkDisabled", true);
			}
			maps.add(map);
		}
		setRoleId(OrganId);
		setUserId(UserId);
		jsonData = GsonUtil.toJson(maps);// 生成json数据
		return SUCCESS;
	}
	/**
	 * 
	* @Title: organUserTree   
	* @Description:组织人员树
	* @return
	* @throws Exception
	* @return String        
	* @author gaoguangchao
	* @date 2014年9月2日 下午3:16:14 
	*
	 */
	@SuppressWarnings("unchecked")
	public String organUserTree() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		List<TreeEntity> trList =new ArrayList<TreeEntity>();
		List<TreeEntity> userList =new ArrayList<TreeEntity>();
		//查询条件
		Map<String, String> mapQuery=new HashMap<String, String>();
		// 转换为ztree需要数据格式 id name pid open checked isParent
		List<TreeEntity> trListChil=new ArrayList<TreeEntity>();
		if(id==null||id.equals("")){
			id="0";
		}
		TreeEntity treeEntity=new TreeEntity();
		treeEntity.setId("neizhi");
		treeEntity.setName("内置");
		treeEntity.setPid("0");
		treeEntity.setIsParent(true);
		treeEntity.setNocheck(true);
		treeEntity.setIconSkin("organ");
		mapQuery.put("id", id);
		trList = treeService.findByCondition("findOrganList",mapQuery);
		if(!id.equals("0")){
			userList = treeService.findByCondition("findUserOrganId",mapQuery);// 获取所有节点信息
		}else{
			trList.add(treeEntity);
		}
		
		
		for (int i = 0; i < trList.size(); i++) {
			TreeEntity resultTreeEntity = trList.get(i);
			resultTreeEntity.setIsParent(true);
			resultTreeEntity.setNocheck(true);
			resultTreeEntity.setIconSkin("organ");
			trListChil.add(resultTreeEntity);
		}
		for (int i = 0; i < userList.size(); i++) {
			TreeEntity resultTreeEntity = userList.get(i);
			resultTreeEntity.setIconSkin("user");
			trListChil.add(resultTreeEntity);
		}
		
		JSONArray jarray=new JSONArray();  
		jarray.addAll(trListChil);  
        HttpServletResponse response = ServletActionContext.getResponse();  
        response.setCharacterEncoding("utf-8");  
        PrintWriter pw = null;  
        try {  
            pw = response.getWriter();  
            pw.write(jarray.toString());  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        pw.flush();  
        pw.close();  
		
		return SUCCESS;
	}
	
	
	
	/**
	 * 资源树
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String resourcesTree() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String checkedId = request.getParameter("checkedId");
		String type = request.getParameter("type");
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		List<TreeEntity> trList = treeService.findByCondition("findResources",null);// 获取所有节点信息
		List<TreeEntity> trListById = treeService.findByCondition("findModuleResource", checkedId);
		for (int i = 0; i < trList.size(); i++) {
			TreeEntity resultTreeEntity = trList.get(i);
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("id", resultTreeEntity.getId());
			map.put("name", resultTreeEntity.getName());
			map.put("pid", resultTreeEntity.getPid());
			map.put("open", true);
			map.put("iconSkin", "app");
			// 根据选中的资源id串进行回显
			if(type==null||type.equals("")){
				for (int a = 0; a < trListById.size(); a++) {
					TreeEntity resultcheckedId=trListById.get(a);
						if (resultcheckedId.getId().equals(resultTreeEntity.getId())) {
							map.put("checked", true);
						}
				}
			}else{
				if (checkedId.equals(resultTreeEntity.getId())) {
					map.put("checked", true);
				}
			}
			map.put("treevalue", resultTreeEntity.getTreevalue());
		
			map.put("isParent", false);
			// 是否可用
			if (resultTreeEntity.isChkDisabled()) {
				map.put("chkDisabled", true);
			}

			maps.add(map);
		}
		jsonData = GsonUtil.toJson(maps);// 生成json数据
		if(type==null||type.equals("")){
			return SUCCESS;
		}else{
			return "meun";
		}
		
	}
	
	/**
	 * 菜单树
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String pumenuTree() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String checkedId = request.getParameter("checkedId");
		String NavId= request.getParameter("NavId");
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		List<TreeEntity> trList = treeService.findByCondition("findMeun",NavId);// 获取所有节点信息
		for (int i = 0; i < trList.size(); i++) {
			TreeEntity resultTreeEntity = trList.get(i);
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("id", resultTreeEntity.getId());
			map.put("name", resultTreeEntity.getName());
			map.put("pid", resultTreeEntity.getPid());
			map.put("open", true);
			// 根据选中的资源id串进行回显

			if (Util.isNotNull(checkedId) && checkedId.equals(resultTreeEntity.getId())) {
				map.put("checked", true);
			}
			// 是否可用
			if (resultTreeEntity.isChkDisabled()) {
				map.put("chkDisabled", true);
			}
			if ("0".equals(resultTreeEntity.getPid())||resultTreeEntity.getPid()==null||"".equals(resultTreeEntity.getPid())) {
				map.put("isParent", true);
			} else {
				map.put("isParent", false);
			}

			maps.add(map);
		}
		jsonData = GsonUtil.toJson(maps);// 生成json数据
		return SUCCESS;
		
		
	}
	
	/**  
	* @Title: moduleTree  
	* @Description: 左侧菜单树 
	* @param @return
	* @param @throws Exception      
	* @return String  
	* @author lyc
	* @date 2014年9月9日 上午11:19:06
	* @throws  
	*/
	@SuppressWarnings("unchecked")
	public String leftPubMenuTree() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String NavId= request.getParameter("NavId");
				if(Util.isNotNull(NavId)){
					NavId =	NavId.trim();
				}
		// 转换为ztree需要数据格式 id name pid open checked isParent
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		List<TreeEntity> resultList = new ArrayList<TreeEntity>();
		List<TreeEntity> trList = treeService.findByCondition("findLeftMeun",NavId);
		String UserId = request.getParameter("UserId");
		if (UserId != null && !"".equals(UserId)) {
			List<TreeEntity> trListById = treeService.findByCondition("findUserByRoleByOrganId", UserId);
			OrganId="";
			for(int j = 0; j < trListById.size(); j++){
				TreeEntity t = null;
				t = trListById.get(j);
				String td = t.getId();
				OrganId += td + ","; 
			}
			for (int i = 0; i < trList.size(); i++) {
				TreeEntity resultTreeEntity = null;
				resultTreeEntity = trList.get(i);
				String parentModuleId = resultTreeEntity.getId();
				for (int j = 0; j < trListById.size(); j++) {
					TreeEntity moduleByIdTreeEntity = null;
					moduleByIdTreeEntity = trListById.get(j);
					String childModuleId = moduleByIdTreeEntity.getId();
					if (childModuleId.equals(parentModuleId)|| childModuleId == parentModuleId) {
						resultTreeEntity.setChecked(true);
					}
				}
				resultList.add(resultTreeEntity);

			}
		} else {
			resultList = trList;
		}
		
		for (int i = 0; i < resultList.size(); i++) {
			TreeEntity resultTreeEntity = resultList.get(i);
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("id", resultTreeEntity.getId());
			if ("".equals(resultTreeEntity.getName())|| resultTreeEntity.getName() == null) {
				map.put("name", resultTreeEntity.getNameDefult());
			} else {
				map.put("name", resultTreeEntity.getName());
			}
			if (resultTreeEntity.isChecked()) {
				map.put("checked", true);
			}
			map.put("pid", resultTreeEntity.getPid());
			map.put("open", true);
			map.put("iconSkin", "app");
			// 判断是否为父节点
			if (!"0".equals(resultTreeEntity.getPid())) {
				map.put("isParent", false);
			} else {
				map.put("isParent", true);
			}
			// 是否可用
			if (resultTreeEntity.isChkDisabled()) {
				map.put("chkDisabled", true);
			}
			maps.add(map);
		}
		setRoleId(OrganId);
		setUserId(UserId);
		jsonData = GsonUtil.toJson(maps);// 生成json数据
		return SUCCESS;
	}
	/**
	 * 
	* @Title: languageTree   
	* @Description: 语言树  
	* @param @return
	* @param @throws Exception      
	* @return String   
	* @author zhaojie
	* @date 2014-1-24 下午2:41:11 
	* @throws
	 */
	@SuppressWarnings("unchecked")
	public String languageTree() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String checkedId="";
		if(request.getParameter("checkedId")!=null&&!"".equals(request.getParameter("checkedId"))){
			 checkedId = new String(request.getParameter("checkedId").getBytes("iso8859-1"),"utf-8");
		}
			
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		List<TreeEntity> trList = treeService.findByCondition("findLanguage",
				null);// 获取所有节点信息

		for (int i = 0; i < trList.size(); i++) {
			TreeEntity resultTreeEntity = trList.get(i);
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("id", resultTreeEntity.getId());
			map.put("name", resultTreeEntity.getName());
			map.put("pid", resultTreeEntity.getPid());
			map.put("open", true);
			// 根据选中的资源id串进行回显
			if (Util.isNotNull(checkedId)) {
				String[] checkedIds = checkedId.split(",");
				for (int a = 0; a < checkedIds.length; a++) {
					if (Util.isNotNull(checkedIds[a])) {
						if (checkedIds[a] == resultTreeEntity.getId()
								|| checkedIds[a].equals(resultTreeEntity
										.getId())) {
							map.put("checked", true);
						}
					}
				}
			}
			map.put("isParent", false);
			if (resultTreeEntity.isChkDisabled()) {
				map.put("chkDisabled", true);
			}

			maps.add(map);
		}
		jsonData = GsonUtil.toJson(maps);// 生成json数据
		return SUCCESS;
	}
	/**
	 * 
	 * @Title: RoleTree
	 * @Description: 角色树
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author zhaojie
	 * @date 2014-1-21 下午1:23:18
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public String RoleTree() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		// 转换为ztree需要数据格式 id name pid open checked isParent
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		List<TreeEntity> resultList = new ArrayList<TreeEntity>();
		List<TreeEntity> trList = treeService.findByCondition("findRoleList",null);
		String UserId = request.getParameter("UserId");
		String roleGroupId = request.getParameter("roleGroupId");
		String useType = request.getParameter("useType");
		String param = "";
		String flag = "";
		String sql = "";
		if("roleGroup".equals(useType)){
			param = roleGroupId;
			sql = "findRoleByRoleGroupId";
			flag = "roleGroupTree";
		}else{
			param = UserId;
			sql = "findRoleByUserID";
			flag = "roleUserTree";
		}
		if (Util.isNotNull(param)) {
			//回显
			List<TreeEntity> trListById = treeService.findByCondition(sql, param);
			RoleId="";
			for(int j = 0; j < trListById.size(); j++){
				TreeEntity t = null;
				t = trListById.get(j);
				String td = t.getId();
				RoleId += td + ",";
			}
			for (int i = 0; i < trList.size(); i++) {
				TreeEntity resultTreeEntity = null;
				resultTreeEntity = trList.get(i);
				String parentModuleId = resultTreeEntity.getId();
				for (int j = 0; j < trListById.size(); j++) {
					TreeEntity moduleByIdTreeEntity = null;
					moduleByIdTreeEntity = trListById.get(j);
					String childModuleId = moduleByIdTreeEntity.getId();
					if (childModuleId.equals(parentModuleId)
							|| childModuleId == parentModuleId) {
						resultTreeEntity.setChecked(true);
					}
				}
				resultList.add(resultTreeEntity);

			}
		} else {
			resultList = trList;
		}
		for (int i = 0; i < resultList.size(); i++) {
			TreeEntity resultTreeEntity = resultList.get(i);
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("id", resultTreeEntity.getId());
			if ("".equals(resultTreeEntity.getName())
					|| resultTreeEntity.getName() == null) {
				map.put("name", resultTreeEntity.getNameDefult());
			} else {
				map.put("name", resultTreeEntity.getName()+"（"+resultTreeEntity.getId()+"）");
			}
			if (resultTreeEntity.isChecked()) {
				map.put("checked", true);
			}
			map.put("pid", resultTreeEntity.getPid());
			map.put("open", true);

			// 判断是否为父节点
			if (!"0".equals(resultTreeEntity.getPid())) {
				map.put("isParent", false);
			} else {
				map.put("isParent", true);
			}
			// 是否可用
			if (resultTreeEntity.isChkDisabled()) {
				map.put("chkDisabled", true);
			}
			map.put("iconSkin", "user");
			maps.add(map);
		}
		setRoleId(RoleId);
		setUserId(param);
		jsonData = GsonUtil.toJson(maps);// 生成json数据
		return flag;
	}
	/**
	 * 
	* @Title: roleGroupRoleTree   
	* @Description:角色组与角色树
	* @return
	* @throws Exception
	* @return String        
	* @author gaoguangchao
	* @date 2014年9月2日 下午3:40:56 
	*
	 */
	@SuppressWarnings("unchecked")
	public String roleGroupRoleTree() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		// 转换为ztree需要数据格式 id name pid open checked isParent
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		List<TreeEntity> resultList = new ArrayList<TreeEntity>();
		List<TreeEntity> trList = treeService.findByCondition("findRoleGroupRoleList",null);
		String UserId = request.getParameter("UserId");
		String UserName = request.getParameter("UserName");
		if(UserName != null && !"".equals(UserName)){
			UserName = new String(UserName.getBytes("iso8859-1"),"utf-8"); 
		}
		setDictName(UserName);
		//回显
		List<TreeEntity> trListById = treeService.findByCondition("findRoleGroupRoleListForUser", UserId);
		RoleId="";
		for(int j = 0; j < trListById.size(); j++){
			TreeEntity t = null;
			t = trListById.get(j);
			String td = t.getId();
			RoleId += td + ",";
		}
		for (int i = 0; i < trList.size(); i++) {
			TreeEntity resultTreeEntity = null;
			resultTreeEntity = trList.get(i);
			String parentModuleId = resultTreeEntity.getId();
			for (int j = 0; j < trListById.size(); j++) {
				TreeEntity moduleByIdTreeEntity = null;
				moduleByIdTreeEntity = trListById.get(j);
				String childModuleId = moduleByIdTreeEntity.getId();
				if (childModuleId.equals(parentModuleId)
						|| childModuleId == parentModuleId) {
					resultTreeEntity.setChecked(true);
				}
			}
			resultList.add(resultTreeEntity);
			
		}
		for (int i = 0; i < resultList.size(); i++) {
			TreeEntity resultTreeEntity = resultList.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("id", resultTreeEntity.getId());
			if ("".equals(resultTreeEntity.getName())
					|| resultTreeEntity.getName() == null) {
				map.put("name", resultTreeEntity.getNameDefult());
			} else {
				map.put("name", resultTreeEntity.getName());
			}
			if (resultTreeEntity.isChecked()) {
				map.put("checked", true);
			}
			map.put("pid", resultTreeEntity.getPid());
			map.put("open", true);
			map.put("iconSkin", "role");
			
			// 判断是否为父节点
			if (!"0".equals(resultTreeEntity.getPid())) {
				map.put("isParent", false);
			} else {
				map.put("isParent", true);
				map.put("chkDisabled", true);
			}

			maps.add(map);
		}
		setRoleId(RoleId);
		setUserId(UserId);
		jsonData = GsonUtil.toJson(maps);// 生成json数据
		return "success";
	}

	/**
	 * 
	 * @Title: rightModuleTree
	 * @Description: 模块数
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author zhaojie
	 * @date 2014-1-21 下午12:46:08
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public String rightModuleTree() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(true);
		Locale locale = (Locale) session.getAttribute("WW_TRANS_I18N_LOCALE");
		String systemCode = request.getParameter("id");
		String pid = request.getParameter("pid");
		String RoleName = request.getParameter("RoleName");
		List<TreeEntity> trList =new ArrayList<TreeEntity>();
		if (locale == null) {
			locale = Locale.CHINA;
		}
		//查询条件
		Map<String, String> mapQuery=new HashMap<String, String>();
		mapQuery.put("locale", locale.toString());
		// 转换为ztree需要数据格式 id name pid open checked isParent
		List<TreeEntity> trListChil=new ArrayList<TreeEntity>();
		if(systemCode==null||systemCode.equals("")){
			 trList = treeService.findByCondition("findSystemList",locale.toString());
		}else{
			 mapQuery.put("systemCode", systemCode);
			 mapQuery.put("pid", pid);
			if(pid==null||pid.equals("")){
				trList = treeService.findByCondition("findModuleList",mapQuery);// 获取所有节点信息
			}else{
				trList = treeService.findByCondition("findModuleList",mapQuery);// 获取所有节点信息
			}
		}
		if (RoleName != null && !"".equals(RoleName)) {
			RoleName = new String(RoleName.getBytes("iso8859-1"),"utf-8"); 
			List<TreeEntity> trListById = treeService.findByCondition(
					"findModuleByModuleID", RoleName);
			ModuleID="";
			for(int j = 0; j < trListById.size(); j++){
				TreeEntity t = null;
				t = trListById.get(j);
				String td = t.getId();
				ModuleID += td + ",";
			}
			// 获取所有节点信息
			
			for (int i = 0; i < trList.size(); i++) {
				TreeEntity resultTreeEntity = trList.get(i);
				resultTreeEntity.setIsParent(true);
				if(systemCode==null||systemCode.equals("")){
					resultTreeEntity.setNocheck(true);
				}
				resultTreeEntity.setTreevalue(ModuleID);
				String parentModuleId = resultTreeEntity.getId();
				for (int j = 0; j < trListById.size(); j++) {
					TreeEntity moduleByIdTreeEntity = null;
					moduleByIdTreeEntity = trListById.get(j);
					String childModuleId = moduleByIdTreeEntity.getId();
					
					if (childModuleId.equals(parentModuleId)
							|| childModuleId == parentModuleId) {
						resultTreeEntity.setChecked(true);
					}
				}
				trListChil.add(resultTreeEntity);
			}
		} else {
			for (int i = 0; i < trList.size(); i++) {
				TreeEntity resultTreeEntity = trList.get(i);
				resultTreeEntity.setIsParent(true);
				if(systemCode==null||systemCode.equals("")){
					resultTreeEntity.setNocheck(true);
				}
				resultTreeEntity.setTreevalue(ModuleID);
				trListChil.add(resultTreeEntity);
			}
		}
		JSONArray jarray=new JSONArray();  
		jarray.addAll(trListChil);  
        HttpServletResponse response = ServletActionContext.getResponse();  
        response.setCharacterEncoding("utf-8");  
        PrintWriter pw = null;  
        try {  
            pw = response.getWriter();  
            pw.write(jarray.toString());  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        pw.flush();  
        pw.close();  
		setModuleID(ModuleID);
		setRoleName(RoleName);
		
		return SUCCESS;
	}
	/**
	 * 分配资源树
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String allocateResourceTree() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(true);
		Locale locale = (Locale) session.getAttribute("WW_TRANS_I18N_LOCALE");
		String systemCode = request.getParameter("id");
		String RoleName = request.getParameter("RoleName");
		List<TreeEntity> trList =new ArrayList<TreeEntity>();
		List<TreeEntity> reList =new ArrayList<TreeEntity>();
		if (locale == null) {
			locale = Locale.CHINA;
		}
		//查询条件
		Map<String, String> mapQuery=new HashMap<String, String>();
		// 转换为ztree需要数据格式 id name pid open checked isParent
		List<TreeEntity> trListChil=new ArrayList<TreeEntity>();
		if(systemCode==null||systemCode.equals("")){
			 trList = treeService.findByCondition("findSystemList",locale.toString());
		}else{
			 mapQuery.put("systemCode", systemCode);
			 trList = treeService.findByCondition("findModuleAllocateResource",mapQuery);// 获取所有节点信息
			 reList=treeService.findByCondition("findResourceAllocate",mapQuery);// 获取资源信息	 
		}
		if (RoleName != null && !"".equals(RoleName)) {
			RoleName = new String(RoleName.getBytes("iso8859-1"),"utf-8"); 
			List<TreeEntity> trListById = treeService.findByCondition("findModuleByModuleID", RoleName);
			List<TreeEntity> reListById = treeService.findByCondition("findResourceByRoleID", RoleName);
			ModuleID="";
			ResourcesId="";
			for(int j = 0; j < trListById.size(); j++){
				TreeEntity t = null;
				t = trListById.get(j);
				String td = t.getId();
				ModuleID += td + ",";
			}
			for(int j = 0; j < reListById.size(); j++){
				TreeEntity t = null;
				t = reListById.get(j);
				String td = t.getId();
				ResourcesId += td + ",";
			}
			// 获取所有节点信息
			
			for (int i = 0; i < trList.size(); i++) {
				TreeEntity resultTreeEntity = trList.get(i);
				resultTreeEntity.setIsParent(true);
				if(systemCode==null||systemCode.equals("")){
					resultTreeEntity.setNocheck(true);
				}
				resultTreeEntity.setTreevalue(ModuleID+"***"+ResourcesId);
				String parentModuleId = resultTreeEntity.getId();
				for (int j = 0; j < trListById.size(); j++) {
					TreeEntity moduleByIdTreeEntity = null;
					moduleByIdTreeEntity = trListById.get(j);
					String childModuleId = moduleByIdTreeEntity.getId();
					
					if (childModuleId.equals(parentModuleId)
							|| childModuleId == parentModuleId) {
						resultTreeEntity.setChecked(true);
						resultTreeEntity.setTreeDivision("selected");
					}
				}
				resultTreeEntity.setIconSkin("app");
				resultTreeEntity.setTreeDistinguish("module");
				trListChil.add(resultTreeEntity);
			}
			
			for (int i = 0; i < reList.size(); i++) {
				TreeEntity resultTreeEntity = reList.get(i);
				resultTreeEntity.setIsParent(false);
				if(systemCode==null||systemCode.equals("")){
					resultTreeEntity.setNocheck(true);
				}
				String parentModuleId = resultTreeEntity.getId();
				for (int j = 0; j < reListById.size(); j++) {
					TreeEntity moduleByIdTreeEntity = null;
					moduleByIdTreeEntity = reListById.get(j);
					String childModuleId = moduleByIdTreeEntity.getId();
					
					if (childModuleId.equals(parentModuleId)
							|| childModuleId == parentModuleId) {
						resultTreeEntity.setChecked(true);
						resultTreeEntity.setTreeDivision("selected");
					}
				}
				resultTreeEntity.setIconSkin("app");
				resultTreeEntity.setTreeDistinguish("resources");
				trListChil.add(resultTreeEntity);
			}
		} else {
			for (int i = 0; i < trList.size(); i++) {
				TreeEntity resultTreeEntity = trList.get(i);
				resultTreeEntity.setIsParent(true);
				if(systemCode==null||systemCode.equals("")){
					resultTreeEntity.setNocheck(true);
				}
				resultTreeEntity.setTreevalue(ModuleID+"***"+ResourcesId);
				resultTreeEntity.setTreeDistinguish("module");
				trListChil.add(resultTreeEntity);
			}
			for (int i = 0; i < reList.size(); i++) {
				TreeEntity resultTreeEntity = reList.get(i);
				resultTreeEntity.setIsParent(true);
				if(systemCode==null||systemCode.equals("")){
					resultTreeEntity.setNocheck(true);
				}
				resultTreeEntity.setTreeDistinguish("resources");
				trListChil.add(resultTreeEntity);
			}
		}
		JSONArray jarray=new JSONArray();  
		jarray.addAll(trListChil);  
        HttpServletResponse response = ServletActionContext.getResponse();  
        response.setCharacterEncoding("utf-8");  
        PrintWriter pw = null;  
        try {  
            pw = response.getWriter();  
            pw.write(jarray.toString());  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        pw.flush();  
        pw.close();  
		setModuleID(ModuleID);
		setResourcesId(ResourcesId);
		setRoleName(RoleName);
		
		return SUCCESS;
	}
	/**
	 * 应用模块树
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String moduleApplicationTree() throws Exception {
		Locale locale = getLocale();
		HttpServletRequest request = ServletActionContext.getRequest();
		String checkedId = request.getParameter("checkedId");
		String type = request.getParameter("type");
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		List<TreeEntity> trListById = treeService.findByCondition("findSystemModule", checkedId);
		List<TreeEntity> trList = null;
		if(type==null||type.equals("")){
			 trList = treeService.findByCondition("moduleApplicationTree",locale.toString());// 获取所有节点信息
		}else{
			 trList = treeService.findByCondition("moduleMeunTree",null);// 获取所有节点信息
		}
		
		//Set<TreeEntity> trListByIds = new HashSet<TreeEntity>(trListById);
		Map<String, String> trListByIds = new HashMap<String, String>();
		for (TreeEntity treeEntity : trListById) {
			trListByIds.put(treeEntity.getId(), treeEntity.getId());
		}
		for (int i = 0; i < trList.size(); i++) {
			TreeEntity resultTreeEntity = trList.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", resultTreeEntity.getId());
			if ("".equals(resultTreeEntity.getName()) || resultTreeEntity.getName() == null) {
				map.put("name", resultTreeEntity.getNameDefult());
			} else {
				map.put("name", resultTreeEntity.getName());
			}
			map.put("pid", resultTreeEntity.getPid());
			map.put("open", true);
			map.put("treevalue", resultTreeEntity.getTreevalue());
			if(type==null||type.equals("")){
				if(trListByIds.containsKey(resultTreeEntity.getId())){
					map.put("checked", true);
				}
			}else{
				if(checkedId.equals(resultTreeEntity.getId())){
					map.put("checked", true);
				}
			}
				
			if (!"0".equals(resultTreeEntity.getPid())) {
				map.put("isParent", false);
			} else {
				map.put("isParent", true);
			}
			if (resultTreeEntity.isChkDisabled()) {
				map.put("chkDisabled", true);
			}
			map.put("iconSkin", "app");
			maps.add(map);
		}
		jsonData = GsonUtil.toJson(maps);// 生成json数据
		if(type==null||type.equals("")){
			return SUCCESS;
		}else{
			return "meun";
		}
		
	}
	/**  
	* @Title: selModuleTree  
	* @Description: 模块添加，修改树  
	* @param @return
	* @param @throws Exception      
	* @return String  
	* @author lyc
	* @date 2014年8月29日 上午11:18:12
	* @throws  
	*/
	@SuppressWarnings("unchecked")
	public String selModuleTree() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String checkedId="";
		String types= request.getParameter("type");
		if(request.getParameter("checkedId")!=null&&!"".equals(request.getParameter("checkedId"))){
			 checkedId = new String(request.getParameter("checkedId").getBytes("iso8859-1"),"utf-8");
		}
			
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		List<TreeEntity> trList = treeService.findByCondition("findAddModuleList",null);// 获取所有节点信息

		for (int i = 0; i < trList.size(); i++) {
			TreeEntity resultTreeEntity = trList.get(i);
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("id", resultTreeEntity.getId());
			map.put("name", resultTreeEntity.getName());
			map.put("pid", resultTreeEntity.getPid());
			if("1".equals(types)){
				map.put("iconSkin", "organ");
				map.put("open", false);
			}else{
				map.put("open", true);
			}
			// 根据选中的资源id串进行回显
			if (Util.isNotNull(checkedId)) {
				String[] checkedIds = checkedId.split(",");
				for (int a = 0; a < checkedIds.length; a++) {
					if (Util.isNotNull(checkedIds[a])) {
						if (checkedIds[a] == resultTreeEntity.getId()
								|| checkedIds[a].equals(resultTreeEntity
										.getId())) {
							map.put("checked", true);
						}
					}
				}
			}
			map.put("isParent", false);
			if (resultTreeEntity.isChkDisabled()) {
				map.put("chkDisabled", true);
			}
			map.put("iconSkin", "app");
			maps.add(map);
		}
		jsonData = GsonUtil.toJson(maps);// 生成json数据
		return SUCCESS;
	}
	/**
	 * 
	* @Title: rightUserTree   
	* @Description: 用户树  
	* @param @return
	* @param @throws Exception      
	* @return String   
	* @author zhaojie
	* @date 2014-1-22 下午1:21:12 
	* @throws
	 */
	@SuppressWarnings("unchecked")
	public String rightUserTree() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(true);
		Locale locale = (Locale) session.getAttribute("WW_TRANS_I18N_LOCALE");
		String OrganId = request.getParameter("PublisherId");
		if (locale == null) {
			locale = Locale.CHINA;
		}
		List<TreeEntity> resultList = new ArrayList<TreeEntity>();
		// 转换为ztree需要数据格式 id name pid open checked isParent
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		List<TreeEntity> trList = treeService.findByCondition("findUserByPuXin",
				locale.toString());// 获取所有节点信息
		if (OrganId != null && !"".equals(OrganId)) {
			List<TreeEntity> trListById = treeService.findByCondition(
					"findUserByRoleByOrganId", OrganId);
			UserId="";
			for(int j = 0; j < trListById.size(); j++){
				TreeEntity t = null;
				t = trListById.get(j);
				String td = t.getId();
				UserId += td + ",";
			}
			for (int i = 0; i < trList.size(); i++) {
				TreeEntity resultTreeEntity = null;
				resultTreeEntity = trList.get(i);
				String parentModuleId = resultTreeEntity.getId();
				for (int j = 0; j < trListById.size(); j++) {
					TreeEntity moduleByIdTreeEntity = null;
					moduleByIdTreeEntity = trListById.get(j);
					String childModuleId = moduleByIdTreeEntity.getId();
					ModuleID += childModuleId + ",";
					if (childModuleId.equals(parentModuleId)
							|| childModuleId == parentModuleId) {
						resultTreeEntity.setChecked(true);
						
					}
				}
				resultList.add(resultTreeEntity);

			}
		} else {
			resultList = trList;
		}
		
		// 获取所有节点信息
		for (int i = 0; i < resultList.size(); i++) {
			TreeEntity resultTreeEntity = resultList.get(i);
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("id", resultTreeEntity.getId());
			if ("".equals(resultTreeEntity.getPid())
					|| resultTreeEntity.getPid() == null) {
				map.put("name", resultTreeEntity.getName());
			} else {
				if(locale.toString()=="en_US"||locale.toString().equals("en_US")){
					resultTreeEntity.setName(resultTreeEntity.getName()+"（Assigned to"+resultTreeEntity.getPid()+"）");
				}else{
					resultTreeEntity.setName(resultTreeEntity.getName()+"（已分配给"+resultTreeEntity.getPid()+"）");
				}
				map.put("name", resultTreeEntity.getName());
			}
			if (resultTreeEntity.isChecked()) {
				map.put("checked", true);
			}
			map.put("pid", resultTreeEntity.getPid());
			map.put("open", true);

			// 判断是否为父节点
			if (!"0".equals(resultTreeEntity.getPid())) {
				map.put("isParent", false);
			} else {
				map.put("isParent", true);
			}
			// 是否可用
			if (resultTreeEntity.isChkDisabled()) {
				map.put("chkDisabled", true);
			}
			maps.add(map);
		}
		setPublisherId(OrganId);
		setUserId(UserId);
		jsonData = GsonUtil.toJson(maps);// 生成json数据
		return SUCCESS;
	}

	/**
	 * @return the jsonData
	 */
	public String getJsonData() {
		return jsonData;
	}

	/**
	 * @param jsonData
	 *            the jsonData to set
	 */
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName
	 *            the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return UserId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		UserId = userId;
	}

	/**
	 * @return the moduleID
	 */
	public String getModuleID() {
		return ModuleID;
	}

	/**
	 * @param moduleID
	 *            the moduleID to set
	 */
	public void setModuleID(String moduleID) {
		this.ModuleID = moduleID;
	}

	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return RoleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		RoleId = roleId;
	}
	

	

	public String getOrganId() {
		return OrganId;
	}


	public void setOrganId(String organId) {
		OrganId = organId;
	}


	/**
	 * @return the publisherId
	 */
	public String getPublisherId() {
		return PublisherId;
	}

	/**
	 * @param publisherId the publisherId to set
	 */
	public void setPublisherId(String publisherId) {
		PublisherId = publisherId;
	}

	/**
	 * @return the dictName
	 */
	public String getDictName() {
		return DictName;
	}

	/**
	 * @param dictName the dictName to set
	 */
	public void setDictName(String dictName) {
		DictName = dictName;
	}


	public String getResourcesId() {
		return ResourcesId;
	}


	public void setResourcesId(String resourcesId) {
		ResourcesId = resourcesId;
	}
}
