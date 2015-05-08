/**
* @Title: FtpInterface.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.core.sftps.ftp   
* @Description:    
* @author guangchao    
* @date 2014-1-21 下午5:21:38   
* @version V1.0 
*/
package com.core.sftps.interfaces;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.net.ftp.FTPClient;

import com.jcraft.jsch.SftpException;
import com.core.sftps.ftp.DownloadStatus;
import com.core.sftps.ftp.UploadStatus;

/**
 * @ClassName: FtpInterface
 * @Description: FtpInterface
 * @author guangchao
 * @date 2014-1-21 下午5:21:38
 *
 */
public interface SFTPSInterface {

		boolean connect(String hostname,int port,String username,String password) throws IOException;
		public DownloadStatus download(String remote,String local) throws IOException;
		public UploadStatus upload(String local,String remote) throws IOException;
		public UploadStatus upload(InputStream input,String remote) throws IOException;
		public InputStream downloadInputStream(String remote) throws IOException;
		public void disconnect() throws IOException;
		public void logout() throws IOException;
		public boolean isCompletePendingCommand() throws IOException;
		public DownloadStatus download(String remote) throws IOException;
		public UploadStatus CreateDirecroty(String remote,FTPClient ftpClient) throws IOException;
		public UploadStatus uploadFile(String remoteFile,File localFile,FTPClient ftpClient,long remoteSize) throws IOException;
		public UploadStatus uploadFile(String remoteFile,InputStream input,FTPClient ftpClient,long remoteSize) throws IOException;
		public void completePendingCommand() throws IOException;
		public Map getFolders(String remote) throws UnsupportedEncodingException, IOException;
		public String[] getFiles(String remote) throws UnsupportedEncodingException, IOException;
		public boolean fileIsExit(String remote) throws UnsupportedEncodingException, IOException;
		public void connect();
		public boolean batchDownLoadFile(String remotPath, String localPath,String fileFormat, boolean del);
		public boolean downloadFile(String remotePath, String remoteFileName,String localPath, String localFileName);
		public boolean uploadFile(String remotePath, String remoteFileName,String localPath, String localFileName) ;
		public boolean bacthUploadFile(String remotePath, String localPath,boolean del);
		public boolean deleteFile(String filePath);
		public boolean createDir(String createpath);
		public boolean isDirExist(String directory);
		public void deleteSFTP(String directory, String deleteFile);
		public void mkdirs(String path);
		public Vector listFiles(String directory)throws SftpException ;
} 
