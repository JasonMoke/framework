/**
* @Title: FtpImpl.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.core.sftps.ftp   
* @Description:    
* @author guangchao    
* @date 2014-1-21 下午5:25:37   
* @version V1.0 
*/
package com.core.sftps.ftps;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;
import org.apache.struts2.ServletActionContext;

import com.jcraft.jsch.SftpException;
import com.core.sftps.ftp.DownloadStatus;
import com.core.sftps.ftp.UploadStatus;
import com.core.sftps.interfaces.SFTPSInterface;

/**
 * @ClassName: FtpImpl
 * @Description: FtpImpl
 * @author guangchao
 * @date 2014-1-21 下午5:25:37
 *
 */
public class FTPSImpl implements SFTPSInterface{
	
	public FTPSClient ftpClient = new FTPSClient(true);   
	
	 public FTPSImpl(){   
	        //设置将过程中使用到的命令输出到控制台   
	        this.ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));   
	    } 
	    /**  
	     * 连接到FTP服务器  
	     * @param hostname 主机名  
	     * @param port 端口  
	     * @param username 用户名  
	     * @param password 密码  
	     * @return 是否连接成功  
	     * @throws IOException  
	     */  
	    public boolean connect(String hostname,int port,String username,String password) throws IOException{   
	    	ftpClient.setDefaultTimeout(60000);// 超时60秒
	        ftpClient.connect(hostname, port);   
	        ftpClient.setControlEncoding("GBK");   
	        if(FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){  
	        	ftpClient.execPBSZ(0);
	        	ftpClient.execPROT("P");
	            if(ftpClient.login(username, password)){   
	                return true;   
	            }   
	        }   
	        disconnect();   
	        return false;   
	    }   
	    /**  
	     * 获取FTP中文件夹目录  
	     * @param remote 路径
	     * @return String[]  
	     */
	    public Map getFolders(String remote) throws UnsupportedEncodingException, IOException{
	    	//设置被动模式   
	        ftpClient.enterLocalPassiveMode();  
	        remote = remote.replaceAll("\\\\", "/");
	    	FTPFile[] files = ftpClient.listFiles(new String(remote.getBytes("GBK"),"iso-8859-1"));
	    	List list = new ArrayList();
	    	List fileList = new ArrayList();
	    	if(files.length > 0){ 
	    		for(int i=0;i<files.length;i++){
	    			if(files[i].isDirectory()){
	    				String folderName = files[i].getName();
	    				if(".".equals(folderName)){
	    					continue;
	    				}
	    				if("..".equals(folderName)){
	    					continue;
	    				}
	    				list.add(folderName);
	    			}else{
	    				String fileName = files[i].getName();
	    				String fileType = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
//	    				if("xlsx".equals(fileType)||"xls".equals(fileType)){
	    				if("xlsx".equals(fileType)){
	    					fileList.add(fileName);
	    				}
	    			}
	    		}
	    	}
	    	final int size = list.size();
	    	String[] arr = (String[])list.toArray(new String[size]);
	    	final int filesize = fileList.size();
	    	String[] filearr = (String[])fileList.toArray(new String[filesize]);
	    	
	    	Map map = new HashMap();
	    	map.put("folderList", arr);
	    	map.put("fileList", filearr);
	    	return map;
	    }
	    /**  
	     * 获取FTP中文件目录  
	     * @param remote 路径
	     * @return String[]  
	     */
	    public String[] getFiles(String remote) throws UnsupportedEncodingException, IOException{
	    	//设置被动模式   
	    	ftpClient.enterLocalPassiveMode();  
	    	remote = remote.replaceAll("\\\\", "/");
	    	FTPFile[] files = ftpClient.listFiles(new String(remote.getBytes("GBK"),"iso-8859-1"));
	    	List list = new ArrayList();
	    	if(files.length > 0){ 
	    		for(int i=0;i<files.length;i++){
	    			if(!files[i].isDirectory()){
	    				String fileName = files[i].getName();
	    				list.add(fileName);
	    			}
	    		}
	    	}
	    	final int size = list.size();
	    	String[] arr = (String[])list.toArray(new String[size]);
	    	return arr;
	    }
	    /**
	     * 
	    * @Title: fileIsExit   
	    * @Description:    检查远程文件是否存在   
	    * @param @param remote
	    * @param @return
	    * @param @throws UnsupportedEncodingException
	    * @param @throws IOException    
	    * @return boolean      
	    * @author guangchao
	    * @date 2014-2-25 下午4:46:11 
	    * @throws
	     */
	    public boolean fileIsExit(String remote) throws UnsupportedEncodingException, IOException{
	    	//设置被动模式   
	    	ftpClient.enterLocalPassiveMode();  
	    	remote = remote.replaceAll("\\\\", "/");
	    	//检查远程文件是否存在   
	        FTPFile[] files = ftpClient.listFiles(new String(remote.getBytes("GBK"),"iso-8859-1"));   
	        if(files.length != 1){   
	            return false;   
	        }else{
	        	return true;
	        }  
	    }
	    /**  
	     * 从FTP服务器上下载文件,支持断点续传，上传百分比汇报  
	     * @param remote 远程文件路径  
	     * @param local 本地文件路径  
	     * @return 上传的状态  
	     * @throws IOException  
	     */  
	    public DownloadStatus download(String remote,String local) throws IOException{   
	        //设置被动模式   
	        ftpClient.enterLocalPassiveMode();   
	        //设置以二进制方式传输   
	        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);   
	        DownloadStatus result;   
	        remote = remote.replaceAll("\\\\", "/");
	        local = local.replaceAll("\\\\", "/");
	        //检查远程文件是否存在   
	        FTPFile[] files = ftpClient.listFiles(new String(remote.getBytes("GBK"),"iso-8859-1"));   
	        if(files.length != 1){   
	            System.out.println("远程文件不存在");   
	            return DownloadStatus.Remote_File_Noexist;   
	        }   
	           
	        long lRemoteSize = files[0].getSize();   
	        File f = new File(local);   
	        //本地存在文件，进行断点下载   
	        if(f.exists()){   
	            long localSize = f.length();   
	            //判断本地文件大小是否大于远程文件大小   
	            if(localSize >= lRemoteSize){   
	                System.out.println("本地文件大于远程文件，下载中止");   
	                return DownloadStatus.Local_Bigger_Remote;   
	            }   
	               
	            //进行断点续传，并记录状态   
	            FileOutputStream out = new FileOutputStream(f,true);   
	            ftpClient.setRestartOffset(localSize);   
	            InputStream in = ftpClient.retrieveFileStream(new String(remote.getBytes("GBK"),"iso-8859-1"));   
	            byte[] bytes = new byte[1024];   
	            long step = lRemoteSize /100;   
	            long process=localSize /step;   
	            int c;   
	            while((c = in.read(bytes))!= -1){   
	                out.write(bytes,0,c);   
	                localSize+=c;   
	                long nowProcess = localSize /step;   
	                if(nowProcess > process){   
	                    process = nowProcess;   
	                    if(process % 10 == 0)   
	                        System.out.println("下载进度："+process);   
	                    //TODO 更新文件下载进度,值存放在process变量中   
	                }   
	            }   
	            in.close();   
	            out.close();   
	            boolean isDo = ftpClient.completePendingCommand();   
	            if(isDo){   
	                result = DownloadStatus.Download_From_Break_Success;   
	            }else {   
	                result = DownloadStatus.Download_From_Break_Failed;   
	            }   
	        }else {   
	            OutputStream out = new FileOutputStream(f);   
	            InputStream in= ftpClient.retrieveFileStream(new String(remote.getBytes("GBK"),"iso-8859-1"));   
	            byte[] bytes = new byte[1024];   
	            long step = lRemoteSize /100;   
	            long process=0;   
	            long localSize = 0L;   
	            int c;   
	            while((c = in.read(bytes))!= -1){   
	                out.write(bytes, 0, c);   
	                localSize+=c;   
	                long nowProcess = localSize /step;   
	                if(nowProcess > process){   
	                    process = nowProcess;   
	                    if(process % 10 == 0)   
	                        System.out.println("下载进度："+process);   
	                    //TODO 更新文件下载进度,值存放在process变量中   
	                }   
	            }   
	            in.close();   
	            out.close();   
	            boolean upNewStatus = ftpClient.completePendingCommand();   
	            if(upNewStatus){   
	                result = DownloadStatus.Download_New_Success;   
	            }else {   
	                result = DownloadStatus.Download_New_Failed;   
	            }   
	        }   
	        return result;   
	    }   
		/**
		 * 	    
		* @Title: download   
		* @Description:    将FTP中的文件以流的方式传送的客户端
		* @param @param remote
		* @param @return
		* @param @throws IOException    
		* @return DownloadStatus      
		* @author guangchao
		* @date 2014-2-18 上午9:56:04 
		* @throws
		 */
	    public DownloadStatus download(String remote) throws IOException{   
	    	HttpServletResponse response = null;
	    	//设置被动模式   
	    	ftpClient.enterLocalPassiveMode();   
	    	//设置以二进制方式传输   
	    	ftpClient.setFileType(FTP.BINARY_FILE_TYPE);   
	    	DownloadStatus result;   
	    	remote = remote.replaceAll("\\\\", "/");
	    	//检查远程文件是否存在   
	    	FTPFile[] files = ftpClient.listFiles(new String(remote.getBytes("GBK"),"iso-8859-1"));   
	    	if(files.length != 1){   
	    		System.out.println("远程文件不存在");   
	    		return DownloadStatus.Remote_File_Noexist;   
	    	}   
	    	
	    	long lRemoteSize = files[0].getSize();   
	    	//本地存在文件，进行断点下载   
	    		InputStream input= ftpClient.retrieveFileStream(new String(remote.getBytes("GBK"),"iso-8859-1"));   
	    		ServletOutputStream out = null;
		        BufferedOutputStream buffOut = null;
		        byte[] byteBuff=new byte[1024];
		        try {
		            response = ServletActionContext.getResponse();
		            response.setContentType("multipart/form-data");
		            out = response.getOutputStream();
					buffOut = new BufferedOutputStream(out);
					int validLength = 0;
					while ((validLength = input.read(byteBuff)) != -1) {
						buffOut.write(byteBuff, 0, validLength);
					}
		            out.flush();
		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		        	if (buffOut != null) {
						buffOut.flush();
						buffOut.close();
					}
					if (input != null) {
						input.close();
					}
		            if (out != null) {
		                try {
		                    out.close();
		                } catch (Exception e) {
		                    e.printStackTrace();
		                }
		            }
		        }  
	    		boolean upNewStatus = ftpClient.completePendingCommand();   
	    		if(upNewStatus){   
	    			result = DownloadStatus.Download_New_Success;   
	    		}else {   
	    			result = DownloadStatus.Download_New_Failed;   
	    		}   
	    	return result;   
	    }   
	    /**
	     * 
	    * @Title: downloadInputStream   
	    * @Description:    下载stream流
	    * @param @param remote
	    * @param @param local
	    * @param @return
	    * @param @throws IOException    
	    * @return DownloadStatus      
	    * @author guangchao
	    * @date 2014-2-13 下午3:09:31 
	    * @throws
	     */
	    public InputStream downloadInputStream(String remote) throws IOException{
	    	HttpServletResponse response = null;
	    	//设置被动模式   
	    	ftpClient.enterLocalPassiveMode();   
	    	//设置以二进制方式传输   
	    	ftpClient.setFileType(FTP.BINARY_FILE_TYPE);   
	    	DownloadStatus result;   
	    	remote = remote.replaceAll("\\\\", "/");
	    	//检查远程文件是否存在   
	    	FTPFile[] files = ftpClient.listFiles(new String(remote.getBytes("GBK"),"iso-8859-1"));   
	    	if(files.length != 1){   
	    		System.out.println("远程文件不存在");   
	    	}   
	    	
	    	long lRemoteSize = files[0].getSize();   
	    	//本地存在文件，进行断点下载   
	    		InputStream input= ftpClient.retrieveFileStream(new String(remote.getBytes("GBK"),"iso-8859-1"));   
	    		ServletOutputStream out = null;
		        BufferedOutputStream buffOut = null;
		        byte[] byteBuff=new byte[1024];
		        try {
		            response = ServletActionContext.getResponse();
		            response.setContentType("multipart/form-data");
		            out = response.getOutputStream();
					buffOut = new BufferedOutputStream(out);
					int validLength = 0;
					while ((validLength = input.read(byteBuff)) != -1) {
						buffOut.write(byteBuff, 0, validLength);
					}
		            out.flush();
		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		        	if (buffOut != null) {
						buffOut.flush();
						buffOut.close();
					}
					if (input != null) {
						input.close();
					}
		            if (out != null) {
		                try {
		                    out.close();
		                } catch (Exception e) {
		                    e.printStackTrace();
		                }
		            }
		        }  
	    		boolean upNewStatus = ftpClient.completePendingCommand();   
	    		if(upNewStatus){   
	    			result = DownloadStatus.Download_New_Success;   
	    		}else {   
	    			result = DownloadStatus.Download_New_Failed;   
	    		}   
			return null;
	    	
	    }
//	    public InputStream downloadInputStream(String remote) throws IOException{
//	    	InputStream inputStream = null;
//	    	remote = remote.replaceAll("\\\\", "/");
//	    	remote = remote.replaceAll("//", "/");
//	    	//设置被动模式   
//	    	ftpClient.enterLocalPassiveMode();   
//	    	//设置以二进制方式传输   
//	    	ftpClient.setFileType(FTP.BINARY_FILE_TYPE);   
//	    	
//	    	ftpClient.setRestartOffset(0);
//	    	DownloadStatus result;   
//	    	//检查远程文件是否存在   
//	    	FTPFile[] files = ftpClient.listFiles(new String(remote.getBytes("GBK"),"iso-8859-1"));   
//	    	if(files.length != 1){   
//	    		System.out.println("远程文件不存在");   
////	            return DownloadStatus.Remote_File_Noexist;   
//	    	}else{
//	    		inputStream = ftpClient.retrieveFileStream(new String(remote.getBytes("GBK"),"iso-8859-1"));  
//	    		System.out.println(inputStream.available());
//	    	}   
//	    	
//	    	return inputStream;
//	    	
//	    }
	    /**  
	     * 上传文件到FTP服务器，支持断点续传  
	     * @param local 本地文件名称，绝对路径  
	     * @param remote 远程文件路径，使用/home/directory1/subdirectory/file.ext 按照Linux上的路径指定方式，支持多级目录嵌套，支持递归创建不存在的目录结构  
	     * @return 上传结果  
	     * @throws IOException  
	     */  
	    public UploadStatus upload(String local,String remote) throws IOException{   
	        //设置PassiveMode传输   
	        ftpClient.enterLocalPassiveMode();   
	        //设置以二进制流的方式传输   
	        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);   
	        ftpClient.setControlEncoding("GBK");   
	        UploadStatus result;
	        local = local.replaceAll("\\\\", "/");
	        remote = remote.replaceAll("\\\\", "/");
	        //对远程目录的处理   
	        String remoteFileName = remote;   
	        if(remote.contains("/")){   
	            remoteFileName = remote.substring(remote.lastIndexOf("/")+1);   
	            //创建服务器远程目录结构，创建失败直接返回   
	            if(CreateDirecroty(remote, ftpClient)==UploadStatus.Create_Directory_Fail){   
	                return UploadStatus.Create_Directory_Fail;   
	            }   
	        }   
	           
	        //检查远程是否存在文件   
	        FTPFile[] files = ftpClient.listFiles(new String(remoteFileName.getBytes("GBK"),"iso-8859-1"));   
	        if(files.length == 1){   
	            long remoteSize = files[0].getSize();   
	            File f = new File(local);   
	            long localSize = f.length();   
	            if(remoteSize==localSize){   
	                return UploadStatus.File_Exits;   
	            }else if(remoteSize > localSize){   
	                return UploadStatus.Remote_Bigger_Local;   
	            }   
	               
	            //尝试移动文件内读取指针,实现断点续传   
	            result = uploadFile(remoteFileName, f, ftpClient, remoteSize);   
	               
	            //如果断点续传没有成功，则删除服务器上文件，重新上传   
	            if(result == UploadStatus.Upload_From_Break_Failed){   
	                if(!ftpClient.deleteFile(remoteFileName)){   
	                    return UploadStatus.Delete_Remote_Faild;   
	                }   
	                result = uploadFile(remoteFileName, f, ftpClient, 0);   
	            }   
	        }else {   
	            result = uploadFile(remoteFileName, new File(local), ftpClient, 0);   
	        }   
	        return result;   
	    }   
	    /**
	     * 
	    * @Title: upload   
	    * @Description:    上传InputStream
	    * @param @param input
	    * @param @param remote
	    * @param @return
	    * @param @throws IOException    
	    * @return UploadStatus      
	    * @author guangchao
	    * @date 2014-1-18 下午4:24:17 
	    * @throws
	     */
	    public UploadStatus upload(InputStream input,String remote) throws IOException{   
	    	//设置PassiveMode传输   
	    	ftpClient.enterLocalPassiveMode();   
	    	//设置以二进制流的方式传输   
	    	ftpClient.setFileType(FTP.BINARY_FILE_TYPE);   
	    	ftpClient.setControlEncoding("GBK");   
	    	UploadStatus result;   
	    	//对远程目录的处理   
	    	String remoteFileName = remote;   
	    	remote = remote.replaceAll("\\\\", "/");
	    	if(remote.contains("/")){   
	    		remoteFileName = remote.substring(remote.lastIndexOf("/")+1);   
	    		//创建服务器远程目录结构，创建失败直接返回   
	    		if(CreateDirecroty(remote, ftpClient)==UploadStatus.Create_Directory_Fail){   
	    			return UploadStatus.Create_Directory_Fail;   
	    		}   
	    	}   
	    	
	    	//检查远程是否存在文件   
	    	FTPFile[] files = ftpClient.listFiles(new String(remoteFileName.getBytes("GBK"),"iso-8859-1"));   
	    	if(files.length == 1){   
	    		long remoteSize = files[0].getSize();   
//	    		File f = new File(local);   
	    		long localSize = input.available();   
	    		if(remoteSize==localSize){   
	    			return UploadStatus.File_Exits;   
	    		}else if(remoteSize > localSize){   
	    			return UploadStatus.Remote_Bigger_Local;   
	    		}   
	    		
	    		//尝试移动文件内读取指针,实现断点续传   
	    		result = uploadFile(remoteFileName, input, ftpClient, remoteSize);   
	    		
	    		//如果断点续传没有成功，则删除服务器上文件，重新上传   
	    		if(result == UploadStatus.Upload_From_Break_Failed){   
	    			if(!ftpClient.deleteFile(remoteFileName)){   
	    				return UploadStatus.Delete_Remote_Faild;   
	    			}   
	    			result = uploadFile(remoteFileName,input, ftpClient, 0);   
	    		}   
	    	}else {   
	    		result = uploadFile(remoteFileName, input, ftpClient, 0);   
	    	}   
	    	return result;   
	    }   
	    /**  
	     * 断开与远程服务器的连接  
	     * @throws IOException  
	     */  
	    public void disconnect() throws IOException{   
	        if(ftpClient.isConnected()){   
	            ftpClient.disconnect();   
	        }   
	    }   
	    /**
	     * 
	    * @Title: logout   
	    * @Description:    logout
	    * @param @throws IOException    
	    * @return void      
	    * @author guangchao
	    * @date 2014-2-17 下午6:10:36 
	    * @throws
	     */
	    public void logout() throws IOException{   
	    		ftpClient.logout();   
	    }   
	       /**
	     * @return 
	        * 
	       * @Title: isCompletePendingCommand   
	       * @Description:    
	       * @param @throws IOException    
	       * @return void      
	       * @author guangchao
	       * @date 2014-2-17 下午6:11:27 
	       * @throws
	        */
	    public boolean isCompletePendingCommand() throws IOException{   
	    	return ftpClient.completePendingCommand();   
	    }   
	    
	    /**  
	     * 递归创建远程服务器目录  
	     * @param remote 远程服务器文件绝对路径  
	     * @param ftpClient FTPClient对象  
	     * @return 目录创建是否成功  
	     * @throws IOException  
	     */  
	    public UploadStatus CreateDirecroty(String remote,FTPClient ftpClient) throws IOException{   
	        UploadStatus status = UploadStatus.Create_Directory_Success;   
	        remote = remote.replaceAll("\\\\", "/");
	        String directory = remote.substring(0,remote.lastIndexOf("/")+1);   
	        if(!directory.equalsIgnoreCase("/")&&!ftpClient.changeWorkingDirectory(new String(directory.getBytes("GBK"),"iso-8859-1"))){   
	            //如果远程目录不存在，则递归创建远程服务器目录   
	            int start=0;   
	            int end = 0;   
	            if(directory.startsWith("/")){   
	                start = 1;   
	            }else{   
	                start = 0;   
	            }   
	            end = directory.indexOf("/",start);   
	            while(true){   
	                String subDirectory = new String(remote.substring(start,end).getBytes("GBK"),"iso-8859-1");   
	                if(!ftpClient.changeWorkingDirectory(subDirectory)){   
	                    if(ftpClient.makeDirectory(subDirectory)){   
	                        ftpClient.changeWorkingDirectory(subDirectory);   
	                    }else {   
	                        System.out.println("创建目录失败");   
	                        return UploadStatus.Create_Directory_Fail;   
	                    }   
	                }   
	                   
	                start = end + 1;   
	                end = directory.indexOf("/",start);   
	                   
	                //检查所有目录是否创建完毕   
	                if(end <= start){   
	                    break;   
	                }   
	            }   
	        }   
	        return status;   
	    }   
	       
	    /**  
	     * 上传文件到服务器,新上传和断点续传  
	     * @param remoteFile 远程文件名，在上传之前已经将服务器工作目录做了改变  
	     * @param localFile 本地文件File句柄，绝对路径  
	     * @param processStep 需要显示的处理进度步进值  
	     * @param ftpClient FTPClient引用  
	     * @return  
	     * @throws IOException  
	     */  
	    public UploadStatus uploadFile(String remoteFile,File localFile,FTPClient ftpClient,long remoteSize) throws IOException{   
	        UploadStatus status;   
	        //显示进度的上传   
	        long step = localFile.length() / 100;   
	        long process = 0;   
	        long localreadbytes = 0L;   
	        RandomAccessFile raf = new RandomAccessFile(localFile,"r");   
	        OutputStream out = ftpClient.appendFileStream(new String(remoteFile.getBytes("GBK"),"iso-8859-1"));   
	        //断点续传   
	        if(remoteSize>0){   
	            ftpClient.setRestartOffset(remoteSize);   
	            process = remoteSize /step;   
	            raf.seek(remoteSize);   
	            localreadbytes = remoteSize;   
	        }   
	        byte[] bytes = new byte[1024];   
	        int c;   
	        while((c = raf.read(bytes))!= -1){   
	            out.write(bytes,0,c);   
	            localreadbytes+=c;   
	            if(localreadbytes / step != process){   
	                process = localreadbytes / step;   
	                System.out.println("上传进度:" + process);   
	                //TODO 汇报上传状态   
	            }   
	        }   
	        out.flush();   
	        raf.close();   
	        out.close();   
	        boolean result =ftpClient.completePendingCommand();   
	        if(remoteSize > 0){   
	            status = result?UploadStatus.Upload_From_Break_Success:UploadStatus.Upload_From_Break_Failed;   
	        }else {   
	            status = result?UploadStatus.Upload_New_File_Success:UploadStatus.Upload_New_File_Failed;   
	        }   
	        return status;   
	    }   
	    /**
	     * 
	    * @Title: uploadFile   
	    * @Description:    上传InputStream
	    * @param @param remoteFile
	    * @param @param input
	    * @param @param ftpClient
	    * @param @param remoteSize
	    * @param @return
	    * @param @throws IOException    
	    * @return UploadStatus      
	    * @author guangchao
	    * @date 2014-1-18 下午4:27:39 
	    * @throws
	     */
	    public UploadStatus uploadFile(String remoteFile,InputStream input,FTPClient ftpClient,long remoteSize) throws IOException{   
	    	UploadStatus status;   
	    	//显示进度的上传   
	    	long step = input.available() / 100;   
	    	long process = 0;   
	    	long localreadbytes = 0L;   
//	    	RandomAccessFile raf = new RandomAccessFile(localFile,"r");   
	    	OutputStream out = ftpClient.appendFileStream(new String(remoteFile.getBytes("GBK"),"iso-8859-1"));   
	    	//断点续传   
	    	if(remoteSize>0){   
	    		ftpClient.setRestartOffset(remoteSize);   
	    		process = remoteSize /step; 
	    		input.skip(remoteSize);
	    		localreadbytes = remoteSize;   
	    	}   
	    	byte[] bytes = new byte[1024];   
	    	int c;   
	    	while((c = input.read(bytes))!= -1){   
	    		out.write(bytes,0,c);   
	    		localreadbytes+=c;   
	    		if(localreadbytes / step != process){   
	    			process = localreadbytes / step;   
	    			System.out.println("上传进度:" + process);   
	    			//TODO 汇报上传状态   
	    		}   
	    	}   
	    	out.flush();   
//	    	raf.close();   
	    	input.close();   
	    	out.close();   
	    	boolean result =ftpClient.completePendingCommand();   
	    	if(remoteSize > 0){   
	    		status = result?UploadStatus.Upload_From_Break_Success:UploadStatus.Upload_From_Break_Failed;   
	    	}else {   
	    		status = result?UploadStatus.Upload_New_File_Success:UploadStatus.Upload_New_File_Failed;   
	    	}   
	    	return status;   
	    }

		/* (non-Javadoc)
		 * @see com.core.sftps.ftp.FtpInterface#completePendingCommand()
		 */
		public void completePendingCommand() throws IOException {
			ftpClient.completePendingCommand();
			
		}
		/* (non-Javadoc)
		 * @see com.core.sftps.interfaces.SFTPSInterface#connect()
		 */
		@Override
		public void connect() {
			// TODO Auto-generated method stub
			
		}
		/* (non-Javadoc)
		 * @see com.core.sftps.interfaces.SFTPSInterface#batchDownLoadFile(java.lang.String, java.lang.String, java.lang.String, boolean)
		 */
		@Override
		public boolean batchDownLoadFile(String remotPath, String localPath,
				String fileFormat, boolean del) {
			// TODO Auto-generated method stub
			return false;
		}
		/* (non-Javadoc)
		 * @see com.core.sftps.interfaces.SFTPSInterface#downloadFile(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
		 */
		@Override
		public boolean downloadFile(String remotePath, String remoteFileName,
				String localPath, String localFileName) {
			// TODO Auto-generated method stub
			return false;
		}
		/* (non-Javadoc)
		 * @see com.core.sftps.interfaces.SFTPSInterface#uploadFile(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
		 */
		@Override
		public boolean uploadFile(String remotePath, String remoteFileName,
				String localPath, String localFileName) {
			// TODO Auto-generated method stub
			return false;
		}
		/* (non-Javadoc)
		 * @see com.core.sftps.interfaces.SFTPSInterface#bacthUploadFile(java.lang.String, java.lang.String, boolean)
		 */
		@Override
		public boolean bacthUploadFile(String remotePath, String localPath,
				boolean del) {
			// TODO Auto-generated method stub
			return false;
		}
		/* (non-Javadoc)
		 * @see com.core.sftps.interfaces.SFTPSInterface#deleteFile(java.lang.String)
		 */
		@Override
		public boolean deleteFile(String filePath) {
			// TODO Auto-generated method stub
			return false;
		}
		/* (non-Javadoc)
		 * @see com.core.sftps.interfaces.SFTPSInterface#createDir(java.lang.String)
		 */
		@Override
		public boolean createDir(String createpath) {
			// TODO Auto-generated method stub
			return false;
		}
		/* (non-Javadoc)
		 * @see com.core.sftps.interfaces.SFTPSInterface#isDirExist(java.lang.String)
		 */
		@Override
		public boolean isDirExist(String directory) {
			// TODO Auto-generated method stub
			return false;
		}
		/* (non-Javadoc)
		 * @see com.core.sftps.interfaces.SFTPSInterface#deleteSFTP(java.lang.String, java.lang.String)
		 */
		@Override
		public void deleteSFTP(String directory, String deleteFile) {
			// TODO Auto-generated method stub
			
		}
		/* (non-Javadoc)
		 * @see com.core.sftps.interfaces.SFTPSInterface#mkdirs(java.lang.String)
		 */
		@Override
		public void mkdirs(String path) {
			// TODO Auto-generated method stub
			
		}
		/* (non-Javadoc)
		 * @see com.core.sftps.interfaces.SFTPSInterface#listFiles(java.lang.String)
		 */
		@Override
		public Vector listFiles(String directory) throws SftpException {
			// TODO Auto-generated method stub
			return null;
		}   
		
		 public static void main(String[] args) {   
			 FTPSImpl myFtp = new FTPSImpl();   
		        try {   
		            myFtp.connect("210.73.60.249", 990, "framework", "0new0rld2014;");   
//		          myFtp.ftpClient.makeDirectory(new String("电视剧".getBytes("GBK"),"iso-8859-1"));   
//		          myFtp.ftpClient.changeWorkingDirectory(new String("电视剧".getBytes("GBK"),"iso-8859-1"));   
//		          myFtp.ftpClient.makeDirectory(new String("走西口".getBytes("GBK"),"iso-8859-1"));   
//		          System.out.println(myFtp.upload("E:\\yw.flv", "/yw.flv",5));   
//		          System.out.println(myFtp.upload("E:\\test.mkv","/Upload/test/test.mkv"));   
//		            System.out.println(myFtp.upload("C:\\Users\\guangchao\\Desktop\\9781629785974111111111111.mobi", "/test/ecm/9781123123629785950.mobi"));   
		            System.out.println(myFtp.fileIsExit("/test/batch/test1/9781629785950.jpg")+"#########################");
//		            System.out.println(myFtp.getFolders("/test/ecm"));
		            myFtp.disconnect();   
		        } catch (IOException e) {   
		            System.out.println("连接FTP出错："+e.getMessage());   
		        }   
		    }   
	       
}
