/**
* @Title: SysParamHelper.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.oneworld.core.helper.sysparam   
* @Description:    
* @author guangchao    
* @date 2014-3-26 下午6:08:54   
* @version V1.0 
*/
package com.core.helper.sysparam;

import com.core.dataload.LoadSysParam;
import com.core.db.DBHelper;
import com.core.db.ILoadData;
import com.framework.entity.systemparam.Systemparam;

/**
 * @ClassName: SysParamHelper
 * @Description: 系统参数设置 hepler工具类
 * @author guangchao
 * @date 2014-3-26 下午6:08:54
 *
 */
public class SysParamHelper {

	/**
	 * @throws Exception 
	 * 
	* @Title: getSystemparamByName   
	* @Description:    根据系统参数name获取实体
	* @param @param ParamName
	* @param @return    
	* @return Systemparam      
	* @author guangchao
	* @date 2014-3-27 上午10:03:08 
	* @throws
	 */
	public static Systemparam getSysParamByName(String ParamName) throws Exception{
		Systemparam systemparam=new Systemparam();
		ILoadData sysParamData=new LoadSysParam();
		String sql="SELECT * FROM t_system_param where ParamName=? AND Status !=99";
		systemparam=DBHelper.getEntity(sql, sysParamData,ParamName);
		return systemparam;
	}
	
	/**
	 * @throws Exception 
	 * 
	* @Title: getSysParamByValue   
	* @Description:   根据系统参数值获取实体 
	* @param @param ParamValue
	* @param @return    
	* @return Systemparam      
	* @author guangchao
	* @date 2014-3-27 上午10:05:44 
	* @throws
	 */
	public static Systemparam getSysParamByValue(String ParamValue) throws Exception{
		Systemparam systemparam=new Systemparam();
		ILoadData sysParamData=new LoadSysParam();
		String sql="SELECT * FROM t_system_param where ParamValue=? AND Status !=99";
		systemparam=DBHelper.getEntity(sql, sysParamData,ParamValue);
		return systemparam;
	}
	
	/**
	 * @throws Exception 
	 * 
	* @Title: getSysParamValueByName   
	* @Description:     根据系统参数name获取参数值
	* @param @param ParamName
	* @param @return    
	* @return String      
	* @author guangchao
	* @date 2014-3-27 上午10:04:29 
	* @throws
	 */
	public static String getSysParamValueByName(String ParamName) throws Exception{
		
		String sql="SELECT ParamValue FROM t_system_param where ParamName=? AND Status !=99";
		String ParamValue=(String) DBHelper.executeScalar(sql,ParamName);	
		return ParamValue;
	}
}
