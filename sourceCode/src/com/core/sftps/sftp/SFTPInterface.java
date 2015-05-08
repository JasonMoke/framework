/**
* @Title: SFTPInterface.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.core.sftps.sftp   
* @Description:    
* @author guangchao    
* @date 2014-3-25 上午11:29:09   
* @version V1.0 
*/
package com.core.sftps.sftp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Vector;

import com.jcraft.jsch.SftpException;
import com.core.sftps.ftp.DownloadStatus;
import com.core.sftps.ftp.UploadStatus;

/**
 * @ClassName: SFTPInterface
 * @Description: SFTP 公共类接口
 * @author guangchao
 * @date 2014-3-25 上午11:29:09
 *
 */
public interface SFTPInterface {
	public void connect();
	public void disconnect();
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
	public boolean connect(String hostname,int port,String username,String password) throws IOException;
	public UploadStatus upload(String local,String remote) throws IOException;
	public Map getFolders(String remote) throws UnsupportedEncodingException, IOException, SftpException;
	public DownloadStatus download(String filePath, String string) throws UnsupportedEncodingException;
	public void downloadInputStream(String string) throws IOException, SftpException;
	public boolean fileIsExit(String imgPath);
}
