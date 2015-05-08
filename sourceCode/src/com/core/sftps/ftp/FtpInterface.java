/**
* @Title: FtpInterface.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.framework.ftp   
* @Description:    
* @author guangchao    
* @date 2014-1-21 下午5:21:38   
* @version V1.0 
*/
package com.core.sftps.ftp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.commons.net.ftp.FTPClient;

/**
 * @ClassName: FtpInterface
 * @Description: FtpInterface
 * @author guangchao
 * @date 2014-1-21 下午5:21:38
 *
 */
public interface FtpInterface {

	 boolean connect(String hostname,int port,String username,String password) throws IOException;
	 DownloadStatus download(String remote,String local) throws IOException;
	 UploadStatus upload(String local,String remote) throws IOException;
	 UploadStatus upload(InputStream input,String remote) throws IOException;
	 InputStream downloadInputStream(String remote) throws IOException;
	 void disconnect() throws IOException;
	 void logout() throws IOException;
	 boolean isCompletePendingCommand() throws IOException;
	 DownloadStatus download(String remote) throws IOException;
	 UploadStatus CreateDirecroty(String remote,FTPClient ftpClient) throws IOException;
	 UploadStatus uploadFile(String remoteFile,File localFile,FTPClient ftpClient,long remoteSize) throws IOException;
	 UploadStatus uploadFile(String remoteFile,InputStream input,FTPClient ftpClient,long remoteSize) throws IOException;
	 void completePendingCommand() throws IOException;
	 public Map getFolders(String remote) throws UnsupportedEncodingException, IOException;
	 public String[] getFiles(String remote) throws UnsupportedEncodingException, IOException;
	 public boolean fileIsExit(String remote) throws UnsupportedEncodingException, IOException;
} 
