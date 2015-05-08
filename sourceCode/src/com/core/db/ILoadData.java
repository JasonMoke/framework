package com.core.db;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName: ILoadData 
 * @Description: 加载数据的接口
 * @author:  tanyi
 * @date: 2014-01-16 14:11:04
 */ 
public interface ILoadData {
	
	/**
	 * @ClassName: ILoadData 
	 * @Description: 加载数据的接口方法
	 * @author:  tanyi
	 * @date: 2014-01-16 14:11:04
	 */ 
	abstract <T> T loadData(ResultSet rs) throws SQLException;
}
