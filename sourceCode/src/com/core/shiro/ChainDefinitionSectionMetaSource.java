package com.core.shiro;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;

import com.core.ehcache.EHCacheHelper;
import com.framework.entity.resources.Resources;
import com.framework.service.resources.ResourcesService;
import com.framework.service.module.IModuleService;

public class ChainDefinitionSectionMetaSource implements FactoryBean<Ini.Section>{

//	 	@Autowired  
//	    private ResourceDao resourceDao;  
	 	@Resource(name = "moduleServiceImp")
		private IModuleService moduleServiceImp;
		@Resource(name = "ResourcesServiceImp")
		private ResourcesService ResourcesService;
		
	    private String filterChainDefinitions;  
	  
	    /** 
	     * 默认premission字符串 
	     */  
	    public static final String PREMISSION_STRING="perms[\"{0}\"]";  
	  
	    @SuppressWarnings("unchecked")
		public Section getObject() throws BeansException {  
	    	List<Resources> list = new ArrayList<Resources>();
			list = (List<Resources>) EHCacheHelper.getElementValue("resourceCache","allResources");
			if(list==null||list.size()==0){
				list = ResourcesService.findAll();
			}
	        Ini ini = new Ini();  
	        //加载默认的url  
	        ini.load(filterChainDefinitions);  
	        Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);  
	        //循环Resource的url,逐个添加到section中。section就是filterChainDefinitionMap,  
	        //里面的键就是链接URL,值就是存在什么条件才能访问该链接  
	        for (Iterator<Resources> it = list.iterator(); it.hasNext();) {  
	  
	        	Resources resource = it.next();  
	            //如果不为空值添加到section中  
	            if(StringUtils.isNotEmpty(resource.getResourcesUrl()) && StringUtils.isNotEmpty(resource.getPermissionSet())) {  
	                section.put(resource.getResourcesUrl(),  "authc,"+MessageFormat.format(PREMISSION_STRING,resource.getPermissionSet()));  
	            }  
	            if(StringUtils.isNotEmpty(resource.getResourcesUrl()) && StringUtils.isEmpty(resource.getPermissionSet())) {  
	            	section.put(resource.getResourcesUrl(),  "authc");  
	            }  
	        }  
	        return section;  
	    }  
	  
	    /** 
	     * 通过filterChainDefinitions对默认的url过滤定义 
	     *  
	     * @param filterChainDefinitions 默认的url过滤定义 
	     */  
	    public void setFilterChainDefinitions(String filterChainDefinitions) {  
	        this.filterChainDefinitions = filterChainDefinitions;  
	    }  
	  
	    public Class<?> getObjectType() {  
	        return this.getClass();  
	    }  
	  
	    public boolean isSingleton() {  
	        return false;  
	    }  
	    
}
