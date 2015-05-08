/**
* @Title: uploadUtil.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.framework.ftp   
* @Description:    
* @author guangchao    
* @date 2014-1-20 下午4:23:20   
* @version V1.0 
*/
package com.core.sftps.ftp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;



import com.core.sftps.ftps.FTPSImpl;
import com.core.sftps.interfaces.SFTPSInterface;
import com.core.sftps.sftp.SFTPImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.util.GetConfig;
import com.util.Util;

/**
 * @ClassName: uploadUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author guangchao
 * @date 2014-1-20 下午4:23:20
 *
 */
public class uploadUtil extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2540940899923561831L;
	Logger log = Logger.getLogger(this.getClass());
	 /**
	 * @throws IOException 
     * 
    * @Title: getPriviewInp   
    * @Description:    根据文件id获取 文件库中的文件
    * @param @param request
    * @param @param response    
    * @return void      
    * @author guangchao
    * @date 2014-1-20 下午4:21:31 
    * @throws
     */
	public void getPriviewInp() throws IOException{
		/*HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = null;
		boolean flag = false;
		InputStream input=null;
		String ResourcesId = request.getParameter("ResourcesId");
		String AssetsId = request.getParameter("AssetsId");
		Resources resourcesOfCover = new Resources();
//		根据AssetsId获取资源的详细信息
		if(Util.isNotNull(AssetsId)){
			List<Resources> listResources = new ArrayList();
			listResources = ResourcesService.findByCondition("findResourcesListByAssetsId", AssetsId);
			for(int i=0;i<listResources.size();i++){
				Resources Resources = listResources.get(i);
				//为资源类型赋值：电子书 ：1  	封页设计： 2 	 屏幕截图 ：3 	元数据 ：4 		其它 ：5        缩略图6：
				String resourceClassify = Resources.getResourcesClassify();
				if("6".equals(resourceClassify)){
					resourcesOfCover = Resources;
					flag = true;
					break;
				}
			}
//			根据ResourcesId查询
		}else if(Util.isNotNull(ResourcesId)){
			Resources Resources = ResourcesService.findEntityByCondition("findResourcesByResourcesId", ResourcesId);
			String ResourcesSuffix = Resources.getResourcesSuffix();
			String imgType = "*.BMP;*.JPG;*.JPEG;*.PNG;*.GIF;*.PCX;*.TIFF;*.TGA;*.EXIF;*.FPX;*.SVG;*.PSD;*.CDR;*.PCD;*.DXF;*.UFO;*.EPS;*.HDRI;*.AI;*.RAW;";
			if(Util.isNotNull(ResourcesSuffix)){
				if(imgType.indexOf(ResourcesSuffix)>-1){
					resourcesOfCover = Resources;
					flag = true;	
				}
			}
			
		}
//		如果没有图片类型，则显示默认图片
		if(!flag){
	    	ActionContext ac = ActionContext.getContext();   
	    	ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);   
	    	String RealPath = sc.getRealPath("/"); 
			StringBuffer sb = new StringBuffer();
			sb.append(RealPath).append(File.separator).append("images").append(File.separator).append("untitled.png");
			input= new FileInputStream(sb.toString());
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
		}else{
//			从FTP中获取资源的inputstream 
			String ftpType = resourcesOfCover.getFTPType();//字典集管理	 FTPType	FTP:1	SFTP:3	FTPS:2
			String hostname = resourcesOfCover.getFTPAddress();
			int port = 21;
			port = resourcesOfCover.getFTPPort();
			String username = resourcesOfCover.getFTPUserName();
			String password = resourcesOfCover.getFTPPwd();
			String pathPrefix = resourcesOfCover.getFTPPath();
			String ResourcesPath = resourcesOfCover.getResourcesPath();
			SFTPSInterface ftpInterface = null;
			boolean isCon = false;
			if("1".equals(ftpType)){
				ftpInterface = new FtpImpl();
				isCon = ftpInterface.connect(hostname, port, username, password);
			}else if("2".equals(ftpType)){
				ftpInterface = new FTPSImpl();
				isCon = ftpInterface.connect(hostname, port, username, password);
			}else if("3".equals(ftpType)){
				ftpInterface = new SFTPImpl();
				isCon = ftpInterface.connect(hostname, port, username, password);
			}
			if(isCon){
				try {
//					****************获取FTP中的文件流****************
					ftpInterface.download(pathPrefix+File.separator+ResourcesPath);
				} catch (Exception e) {
					log.error(e);
				}
//					关闭连接
				ftpInterface.disconnect();
//					连接失败
				}else{
					String nowDate0=Util.changeDateToString(new Date(),"yyyy-MM-dd HH:mm:ss");
					System.out.println("**["+nowDate0+"]获取图片-连接FTP失败(CONNECT TO FTP FAIL)**");
				}
		}*/
		
	    
	}
	/**
	 * 
	* @Title: getPriviewInpForOtherFTP   
	* @Description:  获取给定的FTP中的图片  
	* @param @throws IOException    
	* @return void      
	* @author guangchao
	* @date 2014-2-25 下午4:19:04 
	* @throws
	 */
	public void getPriviewInpForOtherFTP() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = null;
		boolean error = false;
		InputStream input=null;
		String ftpType = request.getParameter("ftpType");
		String hostname = request.getParameter("hostname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		SFTPSInterface ftpInterface = null;
		/*if(Util.isNotNull(password)){
			password = URLDecoder.decode(password, "UTF-8");
		}
		if(Util.isNotNull(username)){
			username = URLDecoder.decode(username, "UTF-8");
		}
		if(Util.isNotNull(hostname)){
			hostname = URLDecoder.decode(hostname, "UTF-8");
		}*/
		
		String imgPath = request.getParameter("imgPath");
//		从FTP中获取资源的inputstream 
		boolean isCon = false;
		if(Util.isNotNull(imgPath)){
			if("1".equals(ftpType)){
				int port = 21;
				if(Util.isNull(request.getParameter("port"))){
					port = Integer.parseInt(request.getParameter("port"));
				}
				ftpInterface = new FtpImpl();
				isCon = ftpInterface.connect(hostname, port, username, password);
			}else if("2".equals(ftpType)){
				int port = 990;
				if(Util.isNull(request.getParameter("port"))){
					port = Integer.parseInt(request.getParameter("port"));
				}
				ftpInterface = new FTPSImpl();
				isCon = ftpInterface.connect(hostname, port, username, password);
			}else if("3".equals(ftpType)){
				int port = 22;
				if(Util.isNull(request.getParameter("port"))){
					port = Integer.parseInt(request.getParameter("port"));
				}
				ftpInterface = new SFTPImpl();
				isCon = ftpInterface.connect(hostname, port, username, password);
			}
			if(isCon){
				try {
//					 检查远程文件是否存在   
					boolean fileIsExit = ftpInterface.fileIsExit(imgPath);
					if(fileIsExit){
//			****************获取FTP中的文件流****************
						ftpInterface.downloadInputStream(imgPath);
					}else{
						error = true;
					}
				} catch (Exception e) {
					log.error(e);
				}
//			关闭连接
				ftpInterface.disconnect();
//			连接失败
			}else{
				String nowDate0=Util.changeDateToString(new Date(),"yyyy-MM-dd HH:mm:ss");
				System.out.println("**["+nowDate0+"]获取图片-连接SFTPS失败(CONNECT TO SFTPS FAIL)**");
				error = true;
			}
			if(error){
				ActionContext ac = ActionContext.getContext();   
		    	ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);   
		    	String RealPath = sc.getRealPath("/"); 
				StringBuffer sb = new StringBuffer();
				sb.append(RealPath).append(File.separator).append("images").append(File.separator).append("errord.png");
				input= new FileInputStream(sb.toString());
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
			}
		}
	}
    /**
     * @throws IOException 
     * 
    * @Title: getPriviewInpByPath   
    * @Description:    根据路径获取临时文件库中的文件
    * @param @param request
    * @param @param response
    * @param @throws FileNotFoundException    
    * @return void      
    * @author guangchao
    * @date 2014-1-20 下午4:21:55 
    * @throws
     */
	public String getPriviewInpByPath() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = null;
		String path = request.getParameter("path");
		String isAbsolute = request.getParameter("isAbsolute");
		String absolutePath = "";
		if("1".equals(isAbsolute)){
			absolutePath = path;
		}else{
			String base =  GetConfig.getFileoutputpath();
			absolutePath = base+File.separator+path;
		}
		InputStream input= new FileInputStream(absolutePath);
        ServletOutputStream out = null;
        BufferedOutputStream buffOut = null;
        byte[] byteBuff=new byte[1024];
        try {
            response = ServletActionContext.getResponse();
            response.setContentType("multipart/form-data");
            out = response.getOutputStream();
			buffOut = new BufferedOutputStream(out);
			int validLength = 0;
			// carry btye arrary
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
	/**
	 * 
	
	* @Title: getPriviewInpByCondition  
	
	* @Description: 根据inputStream获取图片信息
	
	* @param @return
	* @param @throws IOException      
	
	* @return String  
	
	* @author guangchao
	
	* @date 2014-1-22 上午10:48:39
	
	* @throws
	 */
	public String getPriviewInpByCondition() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = null;
		String conditionName = request.getParameter("conditionName");
		String conditionKey = request.getParameter("conditionKey");
		String table = request.getParameter("table");
		String InputStreamName = request.getParameter("InputStreamName");
		
//		查询流
		Connection conn=null;
		try {
			ResourceBundle p = ResourceBundle.getBundle("db");
	        String driverClassName = p.getString("jdbc.driverClassName");
	        String url = p.getString("jdbc.url");
	        String jdbcusername = p.getString("jdbc.username");
	        String jdbcpassword = p.getString("jdbc.password");
			Class.forName(driverClassName);
			conn=(Connection) DriverManager.getConnection(url,jdbcusername,jdbcpassword); 
        	PreparedStatement pstmtCaption = null;	
 			String sql="select "+InputStreamName+" from "+table+"	WHERE "+conditionName+" ='"+conditionKey+"' ";
 			pstmtCaption=conn.prepareStatement(sql);
 			
 			ResultSet result =pstmtCaption.executeQuery();
// 			ResultSetMetaData md = result.getMetaData(); //得到结果集(rs)的结构信息，比如字段数、字段名等   
 			while (result.next()) {
 				Blob blob = result.getBlob(1);
 				InputStream input= blob.getBinaryStream();
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
 			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
            if (conn != null) {
                try {
                    if (!conn.isClosed()) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
          }
		
		return null;
	}
	/**
	 * 
	* @Title: getFileStreamByPath   
	* @Description:   根据path路径，下载临时文件库中的文件（绝对路径时传递一个isAbsolute=1的参数） 
	* @param @return
	* @param @throws IOException    
	* @return String      
	* @author guangchao
	* @date 2014-3-19 下午4:40:41 
	* @throws
	 */
	public String getFileStreamByPath() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String path = request.getParameter("path");
		String isAbsolute = request.getParameter("isAbsolute");
		String absolutePath = "";
		if("1".equals(isAbsolute)){
			absolutePath = path;
		}else{
			String base =  GetConfig.getFileoutputpath();
			absolutePath = base+File.separator+path;
		}
//		获取文件名称
		//对远程目录的处理   
    	String remoteFileName = absolutePath;   
    	absolutePath = absolutePath.replaceAll("\\\\", "/");
    	if(absolutePath.contains("/")){   
    		remoteFileName = absolutePath.substring(absolutePath.lastIndexOf("/")+1);   
    	}
		@SuppressWarnings("resource")
		InputStream input= new FileInputStream(absolutePath);
		OutputStream os = null;    
	    FileInputStream fis = null;    
	        try {    
	             os = response.getOutputStream();    
	             response.setHeader("content-disposition", "attachment;filename=" + new String(remoteFileName.getBytes("GBK"), "ISO-8859-1"));      
	             response.setContentType("application/octet-stream");//八进制流 与文件类型无关  
	             byte temp[] = new byte[1024];    
	             fis = (FileInputStream) input;    
	             int n = 0;    
	             while ((n = fis.read(temp)) != -1) {    
	                 os.write(temp, 0, n);    
	             }    
	         } catch (Exception e) {    
	             System.out.print("出错了");    
	         } finally {    
	            if (os != null)    
	                 os.close();    
	            if (fis != null)    
	                 fis.close();    
	         }    		
		return null;
	}
}
