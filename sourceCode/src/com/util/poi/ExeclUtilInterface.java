/**
* @Title: ExeclUtilInterface.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.util.poi   
* @Description: 
* @author guangchao    
* @date 2014年3月28日 下午12:42:55   
* @version V1.0 
*/

package com.util.poi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;



/**
 * @ClassName: ExeclUtilInterface
 * @Description: 基于POI3.10的工具类
 * @author guangchao
 * @date 2014年3月28日 下午12:42:55
 *
 */

public interface ExeclUtilInterface {
//	创建execl2007
	public void create2007Excel()  throws FileNotFoundException,IOException ;
//	创建execl2003
	public void create2003Excel() throws FileNotFoundException,IOException;
//	读取execl，自动判断版本（根据后缀）
	public Map<String,List<Object>> readExcel(File file,String[] tables) throws Exception;
//	读取execl2007
	public Map<String,List<Object>> read2007Excel(File file,String[] tables) throws Exception;
//	读取execl2003
	public Map<String,List<Object>> read2003Excel(File file,String[] tables) throws Exception;
//	读取execl，自动判断版本（根据后缀）
	public List<List<Object>>  readExcel(File file) throws Exception;
//	读取execl2007
	public List<List<Object>>  read2007Excel(File file) throws Exception;
//	读取execl2003
	public List<List<Object>>  read2003Excel(File file) throws Exception;
	/**
	 * 
	* @Title: export03   
	* @Description: 报表导出2003
	* @param @param listSalesdetails
	* @param @return
	* @param @throws Exception      
	* @return HSSFWorkbook   
	* @author zhaojie
	* @date 2014-3-28 下午12:58:35 
	* @throws
	 */
	public void export07() throws FileNotFoundException,IOException;
}
