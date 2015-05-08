/**
* @Title: LoadDicList.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.oneworld.core.dataload   
* @Description:    
* @author guangchao    
* @date 2014-3-27 上午11:35:55   
* @version V1.0 
*/
package com.core.dataload;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.core.db.ILoadData;
import com.framework.entity.dict.DictList;


/**
 * @ClassName: LoadDicList
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author guangchao
 * @date 2014-3-27 上午11:35:55
 *
 */
public class LoadDicList implements ILoadData{

	@SuppressWarnings("unchecked")
	@Override
	public <T> T loadData(ResultSet rs) throws SQLException {
		
		DictList entity=new DictList();
		
		entity.setDictListCode(rs.getNString("DictListCode"));
		entity.setDictListName(rs.getNString("DictListName"));
		entity.setDictListRemark(rs.getNString("DictListRemark"));
		entity.setDictListNumber(rs.getInt("DictListNumber"));
		entity.setStatus(rs.getInt("Status"));
		entity.setCreateTime(rs.getDate("CreateTime"));
		entity.setCreatePerson(rs.getNString("CreatePerson"));
		entity.setUpdatePerson(rs.getNString("UpdatePerson"));
		entity.setUpdateTime(rs.getDate("UpdateTime"));
		
		return (T)entity;
	}

}
