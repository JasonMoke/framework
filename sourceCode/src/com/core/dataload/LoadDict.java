/**
* @Title: LoadDict.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.oneworld.core.dataload   
* @Description:    
* @author guangchao    
* @date 2014-3-27 上午11:36:03   
* @version V1.0 
*/
package com.core.dataload;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.core.db.ILoadData;
import com.framework.entity.dict.Dictmanager;


/**
 * @ClassName: LoadDict
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author guangchao
 * @date 2014-3-27 上午11:36:03
 *
 */
public class LoadDict implements ILoadData {

	@SuppressWarnings("unchecked")
	@Override
	public <T> T loadData(ResultSet rs) throws SQLException {
		
		Dictmanager entity=new Dictmanager();
		
		entity.setDictId(rs.getNString("DictId"));
		entity.setDictListCode(rs.getNString("DictListCode"));
		entity.setDictName(rs.getNString("DictName"));
		entity.setParentDictId(rs.getNString("ParentDictId"));
		entity.setDictData1(rs.getNString("DictData1"));
		entity.setDictData2(rs.getNString("DictData2"));
		entity.setDictData3(rs.getNString("DictData3"));		
		entity.setDictData4(rs.getNString("DictData4"));		
		entity.setDictData5(rs.getNString("DictData5"));
		entity.setBigImage(rs.getNString("BigImage"));
		entity.setSmallImage(rs.getNString("SmallImage"));
		entity.setDictRemark(rs.getNString("DictRemark"));
		entity.setStatus(rs.getInt("Status"));
		entity.setCreateTime(rs.getDate("CreateTime"));
		entity.setCreatePerson(rs.getNString("CreatePerson"));
		entity.setUpdateTime(rs.getDate("UpdateTime"));
		entity.setUpdatePerson(rs.getNString("UpdatePerson"));
		
		return (T)entity;
	}

}
