/**
* @Title: SFTPImpl.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.core.sftps.sftp   
* @Description:    
* @author guangchao    
* @date 2014-3-25 上午11:28:22   
* @version V1.0 
*/
package com.core.sftps.sftp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.struts2.ServletActionContext;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.core.sftps.ftp.DownloadStatus;
import com.core.sftps.ftp.UploadStatus;
import com.core.sftps.interfaces.SFTPSInterface;

/**
 * @ClassName: SFTPImpl
 * @Description: SFTP 公共类实现
 * @author guangchao
 * @date 2014-3-25 上午11:28:22
 *
 */
public class SFTPImpl implements SFTPSInterface{
	 private String host;
	    private String username;
	    private String password;
	    private int port = 22;
	    private ChannelSftp sftp = null;
	    private Session sshSession = null;
	 
	    public SFTPImpl(){   
	        //设置将过程中使用到的命令输出到控制台   
	    } 
	 
	    public SFTPImpl(String host, String username, String password, int port) {
	        this.host = host;
	        this.username = username;
	        this.password = password;
	        this.port = port;
	    }
	 
	    public SFTPImpl(String host, String username, String password) {
	        this.host = host;
	        this.username = username;
	        this.password = password;
	    }
	 
	    /**
	     * connect server via sftp
	     */
	    public void connect() {
	        try {
	 
	            JSch jsch = new JSch();
	            jsch.getSession(username, host, port);
	            sshSession = jsch.getSession(username, host, port);
	            System.out.println("Session created.");
	            sshSession.setPassword(password);
	            Properties sshConfig = new Properties();
	            sshConfig.put("StrictHostKeyChecking", "no");
	            sshSession.setConfig(sshConfig);
	            sshSession.connect();
	            System.out.println("Session connected.");
	            System.out.println("Opening Channel.");
	            Channel channel = sshSession.openChannel("sftp");
	            channel.connect();
	            sftp = (ChannelSftp) channel;
	            System.out.println("Connected to " + host + ".");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
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
	        try {
	       	 
	            JSch jsch = new JSch();
	            jsch.getSession(username, hostname, port);
	            sshSession = jsch.getSession(username, hostname, port);
	            System.out.println("Session created.");
	            sshSession.setPassword(password);
	            Properties sshConfig = new Properties();
	            sshConfig.put("StrictHostKeyChecking", "no");
	            sshSession.setConfig(sshConfig);
	            sshSession.connect();
	            System.out.println("Session connected.");
	            System.out.println("Opening Channel.");
	            Channel channel = sshSession.openChannel("sftp");
	            channel.connect();
	            sftp = (ChannelSftp) channel;
	            System.out.println("Connected to " + hostname + ".");
	            return true;   
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return false;   
	    } 
	    /**
	     * 关闭资源
	     */
	    public void disconnect() {
	        if (this.sftp != null) {
	            if (this.sftp.isConnected()) {
	                this.sftp.disconnect();
	                System.out.println("sftp is closed already");
	            }
	        }
	 
	        if (this.sshSession != null) {
	            if (this.sshSession.isConnected()) {
	                this.sshSession.disconnect();
	                System.out.println("sshSession is closed already");
	            }
	 
	        }
	 
	    }
	 
	    /**
	     * 批量下载文件
	     * 
	     * @param remotPath
	     *            远程下载目录(以路径符号结束)
	     * @param localPath
	     *            本地保存目录(以路径符号结束)
	     * @param fileFormat
	     *            下载文件格式(以特定字符开头,为空不做检验)
	     * @param del
	     *            下载后是否删除sftp文件
	     * @return
	     */
	    public boolean batchDownLoadFile(String remotPath, String localPath,
	            String fileFormat, boolean del) {
	        try {
	            connect();
	            Vector<?> v = listFiles(remotPath);
	            if (v.size() > 0) {
	 
	                Iterator<?> it = v.iterator();
	                while (it.hasNext()) {
	                    LsEntry entry = (LsEntry) it.next();
	                    String filename = entry.getFilename();
	                    SftpATTRS attrs = entry.getAttrs();
	                    if (!attrs.isDir()) {
	                        if (fileFormat != null && !"".equals(fileFormat.trim())) {
	                            if (filename.startsWith(fileFormat)) {
	                                if (this.downloadFile(remotPath, filename,
	                                        localPath, filename)
	                                        && del) {
	                                    deleteSFTP(remotPath, filename);
	                                }
	                            }
	                        } else {
	                            if (this.downloadFile(remotPath, filename,
	                                    localPath, filename)
	                                    && del) {
	                                deleteSFTP(remotPath, filename);
	                            }
	                        }
	                    }
	                }
	            }
	        } catch (SftpException e) {
	            e.printStackTrace();
	        } finally {
	            this.disconnect();
	        }
	        return false;
	    }
	 
	    /**
	     * 下载单个文件
	     * 
	     * @param remotPath
	     *            远程下载目录(以路径符号结束)
	     * @param remoteFileName
	     *            下载文件名
	     * @param localPath
	     *            本地保存目录(以路径符号结束)
	     * @param localFileName
	     *            保存文件名
	     * @return
	     */
	    public boolean downloadFile(String remotePath, String remoteFileName,
	            String localPath, String localFileName) {
	        try {
	            sftp.cd(remotePath);
	            File file = new File(localPath + localFileName);
	            mkdirs(localPath + localFileName);
	            sftp.get(remoteFileName, new FileOutputStream(file));
	            return true;
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (SftpException e) {
	            e.printStackTrace();
	        }
	 
	        return false;
	    }
	    /**
	     * 下载单个文件
	     * @throws UnsupportedEncodingException 
	     */
		public DownloadStatus download(String remote, String local) throws UnsupportedEncodingException {
			DownloadStatus result;   
			boolean upNewStatus = true;
	        remote = remote.replaceAll("\\\\", "/");
	        remote = new String(remote.getBytes("GBK"),"iso-8859-1");
	        local = local.replaceAll("\\\\", "/");
	        String remoteFileName = "";
	        String remotePath = "";
	        if(remote.contains("/")){   
	            remoteFileName = remote.substring(remote.lastIndexOf("/")+1);   
	            remotePath = remote.substring(0,remote.lastIndexOf("/"));   
	        }  
	        try {
	            sftp.cd(remotePath);
	            File file = new File(local);
	            mkdirs(local);
	            sftp.get(remoteFileName, new FileOutputStream(file),new FileProgressMonitor(file.length()));
	            upNewStatus = true;
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	            upNewStatus = false;
	        } catch (SftpException e) {
	            e.printStackTrace();
	            upNewStatus = false;
	        }
            if(upNewStatus){   
                result = DownloadStatus.Download_New_Success;   
            }else {   
                result = DownloadStatus.Download_New_Failed;   
            }   
	        return result;  
			
		}
	    /**
	     * 上传单个文件
	     * 
	     * @param remotePath
	     *            远程保存目录
	     * @param remoteFileName
	     *            保存文件名
	     * @param localPath
	     *            本地上传目录(以路径符号结束)
	     * @param localFileName
	     *            上传的文件名
	     * @return
	     */
	    public boolean uploadFile(String remotePath, String remoteFileName,
	            String localPath, String localFileName) {
	        FileInputStream in = null;
	        try {
	            createDir(remotePath);
	            File file = new File(localPath + localFileName);
	            in = new FileInputStream(file);
	            sftp.put(in, remoteFileName);
	            return true;
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (SftpException e) {
	            e.printStackTrace();
	        } finally {
	            if (in != null) {
	                try {
	                    in.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        return false;
	    }
	    /**  
	     * 上传文件到FTP服务器
	     * @param local 本地文件名称，绝对路径  
	     * @param remote 远程文件路径，使用/home/directory1/subdirectory/file.ext 按照Linux上的路径指定方式，支持多级目录嵌套，支持递归创建不存在的目录结构  
	     * @return 上传结果  
	     * @throws IOException  
	     */  
	    public UploadStatus upload(String local,String remote) throws IOException{   
	        UploadStatus result;
	        local = local.replaceAll("\\\\", "/");
	        remote = remote.replaceAll("\\\\", "/");
	        //对远程目录的处理   
	        String remoteFileName = remote;   
	        if(remote.contains("/")){   
	            remoteFileName = remote.substring(remote.lastIndexOf("/")+1);   
	            //创建服务器远程目录结构，创建失败直接返回   
	            if(CreateDirecroty(remote)==UploadStatus.Create_Directory_Fail){   
	                return UploadStatus.Create_Directory_Fail;   
	            }   
	        }   
	        result = uploadFile(remoteFileName, new File(local));   
	        return result;   
	    }
	    
	    /**
	     * @throws UnsupportedEncodingException    
		* @Title: uploadFile   
		* @Description:    
		* @param @param remoteFileName
		* @param @param file
		* @param @return    
		* @return UploadStatus      
		* @author guangchao
		* @date 2014-3-25 下午2:03:35 
		* @throws   
		*/ 
	    private UploadStatus uploadFile(String remoteFileName, File localFile) throws UnsupportedEncodingException {
			UploadStatus status; 
			boolean flag = true;
	        FileInputStream in = null;
	        try {
	            in = new FileInputStream(localFile);
	            sftp.put(in, new String(remoteFileName.getBytes("GBK"),"iso-8859-1"),new FileProgressMonitor(localFile.length()));
	            flag = true;
	        } catch (FileNotFoundException e) {
	        	flag = false;
	            e.printStackTrace();
	        } catch (SftpException e) {
	        	flag = false;
	            e.printStackTrace();
	        } finally {
	            if (in != null) {
	                try {
	                    in.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        status = flag?UploadStatus.Upload_New_File_Success:UploadStatus.Upload_New_File_Failed;   
	        return status;  
		}

		/**  
	     * 递归创建远程服务器目录  
	     * @param remote 远程服务器文件绝对路径  
	     * @param ftpClient FTPClient对象  
	     * @return 目录创建是否成功  
	     * @throws IOException  
	     */  
	    public UploadStatus CreateDirecroty(String remote) throws IOException{   
	        UploadStatus status = UploadStatus.Create_Directory_Success;   
	        remote = remote.replaceAll("\\\\", "/");
	        String directory = remote.substring(0,remote.lastIndexOf("/")+1);   
	        try {
	            if (isDirExist(directory)) {
	                this.sftp.cd(directory);
	                return status;
	            }
	            String pathArry[] = directory.split("/");
	            StringBuffer filePath = new StringBuffer("/");
	            for (String path : pathArry) {
	                if (path.equals("")) {
	                    continue;
	                }
	                filePath.append(path + "/");
	                if (isDirExist(filePath.toString())) {
	                    sftp.cd(filePath.toString());
	                } else {
	                    // 建立目录
	                    sftp.mkdir(filePath.toString());
	                    // 进入并设置为当前目录
	                    sftp.cd(filePath.toString());
	                }
	 
	            }
	            this.sftp.cd(directory);
	            return status;
	        } catch (SftpException e) {
	            e.printStackTrace();
	        }
	        return status;   
	    }   
	    /**
	     * 批量上传文件
	     * 
	     * @param remotePath
	     *            远程保存目录
	     * @param localPath
	     *            本地上传目录(以路径符号结束)
	     * @param del
	     *            上传后是否删除本地文件
	     * @return
	     */
	    public boolean bacthUploadFile(String remotePath, String localPath,
	            boolean del) {
	        try {
	            connect();
	            File file = new File(localPath);
	            File[] files = file.listFiles();
	            for (int i = 0; i < files.length; i++) {
	                if (files[i].isFile()
	                        && files[i].getName().indexOf("bak") == -1) {
	                    if (this.uploadFile(remotePath, files[i].getName(),
	                            localPath, files[i].getName())
	                            && del) {
	                        deleteFile(localPath + files[i].getName());
	 
	                    }
	                }
	            }
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            this.disconnect();
	        }
	        return false;
	 
	    }
	 
	    /**
	     * 删除本地文件
	     * 
	     * @param filePath
	     * @return
	     */
	    public boolean deleteFile(String filePath) {
	        File file = new File(filePath);
	        if (!file.exists()) {
	            return false;
	        }
	 
	        if (!file.isFile()) {
	            return false;
	        }
	 
	        return file.delete();
	    }
	 
	    /**
	     * 创建目录
	     * 
	     * @param createpath
	     * @return
	     */
	    public boolean createDir(String createpath) {
	        try {
	            if (isDirExist(createpath)) {
	                this.sftp.cd(createpath);
	                return true;
	            }
	            String pathArry[] = createpath.split("/");
	            StringBuffer filePath = new StringBuffer("/");
	            for (String path : pathArry) {
	                if (path.equals("")) {
	                    continue;
	                }
	                filePath.append(path + "/");
	                if (isDirExist(filePath.toString())) {
	                    sftp.cd(filePath.toString());
	                } else {
	                    // 建立目录
	                    sftp.mkdir(filePath.toString());
	                    // 进入并设置为当前目录
	                    sftp.cd(filePath.toString());
	                }
	 
	            }
	            this.sftp.cd(createpath);
	            return true;
	        } catch (SftpException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	 
	    /**
	     * 判断目录是否存在
	     * 
	     * @param directory
	     * @return
	     */
	    public boolean isDirExist(String directory) {
	        boolean isDirExistFlag = false;
	        try {
	            SftpATTRS sftpATTRS = sftp.lstat(directory);
	            isDirExistFlag = true;
	            return sftpATTRS.isDir();
	        } catch (Exception e) {
	            if (e.getMessage().toLowerCase().equals("no such file")) {
	                isDirExistFlag = false;
	            }
	        }
	        return isDirExistFlag;
	    }
	    /**
	     * 判断文件是否存在
	     * 
	     * @param filePath
	     * @return
	     */
	    public boolean fileIsExit(String filePath) {
	    	boolean isDirExistFlag = false;
	    	try {
	    		SftpATTRS sftpATTRS = sftp.lstat(filePath);
	    		isDirExistFlag = true;
	    		return !sftpATTRS.isDir();
	    	} catch (Exception e) {
	    		if (e.getMessage().toLowerCase().equals("no such file")) {
	    			isDirExistFlag = false;
	    		}
	    	}
	    	return isDirExistFlag;
	    }
	 
	    /**
	     * 删除stfp文件
	     * 
	     * @param directory
	     *            要删除文件所在目录
	     * @param deleteFile
	     *            要删除的文件
	     * @param sftp
	     */
	    public void deleteSFTP(String directory, String deleteFile) {
	        try {
	            sftp.cd(directory);
	            sftp.rm(deleteFile);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	 
	    /**
	     * 如果目录不存在就创建目录
	     * 
	     * @param path
	     */
	    public void mkdirs(String path) {
	        File f = new File(path);
	 
	        String fs = f.getParent();
	 
	        f = new File(fs);
	 
	        if (!f.exists()) {
	            f.mkdirs();
	        }
	    }
	 
	    /**
	     * 列出目录下的文件
	     * 
	     * @param directory
	     *            要列出的目录
	     * @param sftp
	     * @return
	     * @throws SftpException
	     */
	    public Vector<?> listFiles(String directory) throws SftpException {
	        return sftp.ls(directory);
	    }
	    
	    /**  
	     * 获取FTP中文件夹目录  
	     * @param remote 路径，过滤文件类型为excel的文件
	     * @return String[]  
	     * @throws SftpException 
	     */
	     public Map getFolders(String remote) throws UnsupportedEncodingException, IOException{
	    	//设置被动模式   
	        remote = remote.replaceAll("\\\\", "/");
	    	Vector<?> files = null;
			try {
				files = sftp.ls(new String(remote.getBytes("GBK"),"iso-8859-1"));
			} catch (SftpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	List<String> list = new ArrayList<String>();
	    	List<String> fileList = new ArrayList<String>();
	    	if(files.size() > 0){ 
	    		for(int i=0;i<files.size();i++){
	    			LsEntry LsEntry = (com.jcraft.jsch.ChannelSftp.LsEntry) files.get(i);
	    			
	    			if(LsEntry.getAttrs().isDir()){
	    				String folderName = LsEntry.getFilename();
	    				if(".".equals(folderName)){
	    					continue;
	    				}
	    				if("..".equals(folderName)){
	    					continue;
	    				}
	    				list.add(folderName);
	    			}else{
	    				String fileName = LsEntry.getFilename();
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
	    	
	    	Map<String, String[]> map = new HashMap<String, String[]>();
	    	map.put("folderList", arr);
	    	map.put("fileList", filearr);
	    	return map;
	    }
	    
	    
	     /**
	     * @throws SftpException 
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
		    public InputStream downloadInputStream(String remote) throws IOException{   
		    	HttpServletResponse response = null;
		    	remote = remote.replaceAll("\\\\", "/");
		    	//检查远程文件是否存在   
		    	Vector<?> files = null;
				try {
					files = listFiles(new String(remote.getBytes("GBK"),"iso-8859-1"));
				} catch (SftpException e2) {
					e2.printStackTrace();
				}   
		    	if(files.size() != 1){   
		    		System.out.println("远程文件不存在");   
		    	}   
		    	
		    		InputStream input = null;
					try {
						input = sftp.get(new String(remote.getBytes("GBK"),"iso-8859-1"));
					} catch (SftpException e1) {
						e1.printStackTrace();
					}   
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
			        return null;
		    }  
	    
	 
	    public String getHost() {
	        return host;
	    }
	 
	    public void setHost(String host) {
	        this.host = host;
	    }
	 
	    public String getUsername() {
	        return username;
	    }
	 
	    public void setUsername(String username) {
	        this.username = username;
	    }
	 
	    public String getPassword() {
	        return password;
	    }
	 
	    public void setPassword(String password) {
	        this.password = password;
	    }
	 
	    public int getPort() {
	        return port;
	    }
	 
	    public void setPort(int port) {
	        this.port = port;
	    }
	 
	    public ChannelSftp getSftp() {
	        return sftp;
	    }
	 
	    public void setSftp(ChannelSftp sftp) {
	        this.sftp = sftp;
	    }
	 
	    public static void main(String[] args) {
	    	SFTPImpl ftp = new SFTPImpl("192.168.25.175", "ty", "123456");
	        String remotePath = "/home/ty/";
	        ftp.connect();
	 
	        try {
	        	Vector v  = ftp.listFiles(remotePath);
	        	for(Iterator it = v.iterator();it.hasNext();){
	        		LsEntry LsEntry = (com.jcraft.jsch.ChannelSftp.LsEntry) it.next();
	        		
	                System.out.println("fileName:"+LsEntry.getFilename());
	                System.out.println("longName:"+LsEntry.getLongname());
	                System.out.println("Attrs:"+LsEntry.getAttrs());
	                System.out.println("******************");
	            }
				System.out.println();
			} catch (SftpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//	        ftp.bacthUploadFile(remotePath,localPath,true);
//	        ftp.downloadFile(remotePath, "test.txt", localPath, "test.txt");
//	        ftp.batchDownLoadFile(remotePath, localPath, null, true);
	 
	        ftp.disconnect();
	        System.exit(0);
	    }

		/* (non-Javadoc)
		 * @see com.core.sftps.interfaces.SFTPSInterface#upload(java.io.InputStream, java.lang.String)
		 */
		@Override
		public UploadStatus upload(InputStream input, String remote)
				throws IOException {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see com.core.sftps.interfaces.SFTPSInterface#logout()
		 */
		@Override
		public void logout() throws IOException {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see com.core.sftps.interfaces.SFTPSInterface#isCompletePendingCommand()
		 */
		@Override
		public boolean isCompletePendingCommand() throws IOException {
			// TODO Auto-generated method stub
			return false;
		}

		/* (non-Javadoc)
		 * @see com.core.sftps.interfaces.SFTPSInterface#download(java.lang.String)
		 */
		@Override
		public DownloadStatus download(String remote) throws IOException {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see com.core.sftps.interfaces.SFTPSInterface#CreateDirecroty(java.lang.String, org.apache.commons.net.ftp.FTPClient)
		 */
		@Override
		public UploadStatus CreateDirecroty(String remote, FTPClient ftpClient)
				throws IOException {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see com.core.sftps.interfaces.SFTPSInterface#uploadFile(java.lang.String, java.io.File, org.apache.commons.net.ftp.FTPClient, long)
		 */
		@Override
		public UploadStatus uploadFile(String remoteFile, File localFile,
				FTPClient ftpClient, long remoteSize) throws IOException {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see com.core.sftps.interfaces.SFTPSInterface#uploadFile(java.lang.String, java.io.InputStream, org.apache.commons.net.ftp.FTPClient, long)
		 */
		@Override
		public UploadStatus uploadFile(String remoteFile, InputStream input,
				FTPClient ftpClient, long remoteSize) throws IOException {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see com.core.sftps.interfaces.SFTPSInterface#completePendingCommand()
		 */
		@Override
		public void completePendingCommand() throws IOException {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see com.core.sftps.interfaces.SFTPSInterface#getFiles(java.lang.String)
		 */
		@Override
		public String[] getFiles(String remote)
				throws UnsupportedEncodingException, IOException {
			// TODO Auto-generated method stub
			return null;
		}
	    
}
