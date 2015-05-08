/**
 * @Title: RightsmanagerServiceImpl.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.service.rightsmanager
 * @Description: 接口实现类。
 * @author zhaojie
 * @date 2014-01-20 15:23:11
 * @version V1.0
 */

package com.framework.service.authority;

import java.util.List;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.framework.dao.authority.RightsmanagerDao;
import com.framework.entity.authority.RightsManager;
import com.orm.BaseServiceImpl;
import com.orm.BaseService;

/**
 * @ClassName: RightsmanagerServiceImpl
 * @Description: 接口实现类。
 * @author zhaojie
 * @date 2014-01-20 15:23:11
 * 
 */
@Service("rightsmanagerService")
@Transactional
public class RightsmanagerServiceImpl extends BaseServiceImpl<RightsManager> implements RightsmanagerService {

    @Resource(name = "rightsmanagerDao")
    private RightsmanagerDao dao;

    @Override
    public BaseService<RightsManager> getDao() {
        return dao;
    }
	/* (non-Javadoc)
	 * @see com.framework.service.authority.RightsmanagerService#selRightManager(com.framework.entity.authority.RightsManager)
	 */
	public boolean selRightManager(List<RightsManager> list,String RoleName) {
		boolean flag=false;
		boolean result=false;
		try {
			if(list==null||list.size()<1){
				dao.deleteByConditions("deleteBynMame", RoleName);
			}else{
		     	dao.deleteByConditions("deleteBynMame", RoleName);
		//		dao.addListOfEntity("addList",list);	
				for(int i=0;i<list.size();i++){
					RightsManager manager=list.get(i);
					dao.addEntity(manager);
				}
			}
			result = true;
		} catch (Exception e) {
			System.out.println(e);
			result = false;
		}
		if (result) {
			flag = true;
		} else {
			flag = false;
		}
		
		return flag;
	}
}
