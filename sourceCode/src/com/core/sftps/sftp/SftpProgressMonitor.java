/**
* @Title: SftpProgressMonitor.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.core.sftps.sftp   
* @Description:    
* @author guangchao    
* @date 2014-3-25 上午11:00:12   
* @version V1.0 
*/
package com.core.sftps.sftp;

/**
 * @ClassName: SftpProgressMonitor
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author guangchao
 * @date 2014-3-25 上午11:00:12
 *
 */
public interface SftpProgressMonitor {
	  public static final int PUT=0;
	  public static final int GET=1;
	  void init(int op, String src, String dest, long max);
	  boolean count(long count);
	  void end();
}
