/**
 * @Title: DictDaoImpl.java   
 * @Copyright 2010 -2013 BIS
 * @Package com.framework.dao.dict   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author user1    
 * @date 2014-1-2 下午5:52:39   
 * @version V1.0 
 */

package com.framework.dao.dict;


import org.springframework.stereotype.Repository;

import com.framework.entity.dict.DictList;
import com.orm.BaseDaoImpl;

/**
 * @ClassName: DictDaoImpl
 * @Description: 字典管理
 * @author user1
 * @date 2014-1-2 下午5:52:39
 * 
 */
@Repository("dictDaoImpl")
public class DictDaoImpl extends BaseDaoImpl<DictList> implements DictDao{
	@Override
	public String getNameSpace() {
		
		return "com.framework.maps.dict";
	}
}
