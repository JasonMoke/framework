/**
* @Title: DownloadStatus.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.oneworld.ftp   
* @Description:    
* @author guangchao    
* @date 2014-1-18 下午2:01:38   
* @version V1.0 
*/
package com.core.sftps.ftp;

/**
 * @ClassName: DownloadStatus
 * @Description:枚举类DownloadStatus代码
 * @author guangchao
 * @date 2014-1-18 下午2:01:38
 *
 */
public enum DownloadStatus {
	Remote_File_Noexist,	//远程文件不存在
	Local_Bigger_Remote,	//本地文件大于远程文件
	Download_From_Break_Success,	//断点下载文件成功
	Download_From_Break_Failed,		//断点下载文件失败
	Download_New_Success,			//全新下载文件成功
	Download_New_Failed;			//全新下载文件失败
}