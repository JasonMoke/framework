package com.util;


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.jgroups.util.UUID;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.util.GetConfig;

/**
 * 
* @ClassName: FileIO
* @Description: IO工具类
* @author guangchao
* @date 2013-12-17 下午5:40:15
*
 */
 
public class FileIO {
	
	public static void main(String args[]) {
//		fileinput();
	    String outputPath = GetConfig.getFileoutputpath();
	    delFolder(outputPath);
//		newFolder();
		}
	/**
	 * 
	* @Title: newFolder   
	* @Description:  创建文件夹  
	* @param @return    
	* @return String      
	* @author guangchao
	* @date 2014-2-18 上午10:14:31 
	* @throws
	 */
	public static String newFolder() {  
		String flag = "false";
		try {   
		    String outputPath =GetConfig.getFileoutputpath();
		    File myFilePath = new File(outputPath);
			if (!myFilePath.exists()) {          
					if(myFilePath.mkdirs()){
						flag = "success";
					}        
				}else{
					flag = "success";
				}      
			} catch (Exception e) {
				}  
		return flag;
		}
	/**
	 * 
	* @Title: createFolder   
	* @Description:创建目录结构
	* @param FolderPath
	* @return
	* @return boolean        
	* @author gaoguangchao
	* @date 2014年6月9日 下午6:25:39 
	*
	 */
	public static boolean createFolder(String FolderPath) {  
		boolean flag = false;
		try {   
			
			File myFilePath = new File(FolderPath);
			if (!myFilePath.exists()) {          
				if(myFilePath.mkdirs()){
					flag = true;
				}        
			}else{
				flag = true;
			}      
		} catch (Exception e) {
			flag = false;
		}  
		return flag;
	}
	/**
	 * 
	* @Title: createFile   
	* @Description:创建新文件
	* @param FolderPath
	* @return
	* @return boolean        
	* @author gaoguangchao
	* @date 2014年6月10日 下午8:05:49 
	*
	 */
	public static boolean createFile(String FilePath) {  
		boolean flag = false;
		try {   
			
			File myFilePath = new File(FilePath);
			if (!myFilePath.exists()) { 
				myFilePath.createNewFile();
			}else{
				flag = true;
			}      
		} catch (Exception e) {
			flag = false;
		}  
		return flag;
	}
/**
 * 
* @Title: delFolder   
* @Description:    删除文件夹
* @param @param path
* @param @return    
* @return boolean      
* @author guangchao
* @date 2014-2-18 上午10:14:16 
* @throws
 */
	public static boolean delFolder(String path) {
		boolean flag = false;
		   File file = new File(path);
		   if (!file.exists()) {
		    return flag;
		   }else{
			   file.delete();
			   flag =true;
		   }
		return flag;
	}
	/**
	 * 
	* @Title: fileExist   
	* @Description:    判断文件是否存在
	* @param @param path
	* @param @return    
	* @return boolean      
	* @author guangchao
	* @date 2014-2-18 上午10:14:07 
	* @throws
	 */
	public static boolean fileExist(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}else{
			flag =true;
		}
		return flag;
	}
	/**
	 * 
	* @Title: getFilePathByStrs   
	* @Description: 依序将多个文件目录块及文件名称，组合成一个完整的文件目录
	* @param @param paths 
	* @param @return      
	* @return String   
	* @author gaoguangchao
	* @date 2014年4月3日 下午5:44:42 
	* @throws   
	* @param paths
	* @return
	 */
	public static String getFilePathByStrs(String ...args) {
		StringBuffer sb = new StringBuffer();
		String filePath = "";
		if(Util.isNotNull(args)){
			for(int i=0;i<args.length;i++){
				String path = args[i];
				if(Util.isNotNull(path)){
					path = path.replaceAll("\\\\", "/");
					//如果最后一位为“/”，则删掉
					if(path.lastIndexOf("/")==path.length()-1){
						path = path.substring(0,path.length()-1);
					}
					//如果第一位为“/”，则删掉
					if(path.lastIndexOf("/")==0){
						path = path.substring(1,path.length());
					}
					sb.append(path).append("/");
				}
				continue;
			}
			filePath = sb.toString();
			if(Util.isNotNull(filePath)){
				//如果最后一位为“/”，则删掉
				if(filePath.lastIndexOf("/")==filePath.length()-1){
					filePath = filePath.substring(0,filePath.length()-1);
				}
			}
		}
		//由于window平台中有些路径中包括空格被转义后需要重新转义回来
		filePath = filePath.replaceAll("%20", " ");
		return filePath;
	}
	/**
	 * 
	* @Title: delExistFile   
	* @Description:    删除指定路径下指定文件名称的文件
	* @param @param path
	* @param @param fileNames
	* @param @return    
	* @return boolean      
	* @author guangchao
	* @date 2014-2-18 上午10:13:14 
	* @throws
	 */
	public static boolean delExistFile(String path,String fileNames) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
		    return flag;
		}
		 String[] tempList = file.list();
		   File temp = null;
		   for (int i = 0; i < tempList.length; i++) {
			   //如果临时文件库中的该文件在fileNames，说明该文件没有上传完，不删除，反之亦然
		    if (path.endsWith(File.separator)) {
		    	if(tempList[i].substring(0, tempList[i].length()-1).indexOf(fileNames)==-1){
		    		temp = new File(path + tempList[i]);
		    	}else{
		    		continue;
		    	}
		    } else {
		    	if(tempList[i].indexOf(fileNames)==-1){
		    		temp = new File(path + File.separator + tempList[i]);
		    	}else{
		    		continue;
		    	}
		    }
		    if (temp.isFile()) {
		     temp.delete();
		     flag = true;
		    }
		    if (temp.isDirectory()) {
		       delAllFile(path +File.separator + tempList[i]);// ��ɾ���ļ���������ļ�
		     flag = true;
		    }
		   }
		return flag;
	}
	/**
	 * 
	* @Title: delSpecFiles   
	* @Description:  删除特定后缀的文件  
	* @param @param path
	* @param @param ends
	* @param @return    
	* @return String      
	* @author guangchao
	* @date 2014-2-18 上午10:12:07 
	* @throws
	 */
	public static String delSpecFiles(String path,final String ends) {
		String flag = "false";
		try {
			File  file=new File(path);
			  
			  File file2[]=file.listFiles(new FileFilter() {
			  
			  public boolean accept(File file) {
			   return file.getName().endsWith(ends);
			  }
			 });
			  if(file2!=null){
				  if(file2.length==0){
					  flag = "none";
				  }else{
					  for (int i = 0; i < file2.length; i++) {
						  file2[i].delete();
					  }
					  flag = "true";
				  }
			  }else{
				  flag = "none";
			  }
		} catch (Exception e) {
			  flag = "false";
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * 
	* @Title: getSpecFiles   
	* @Description:    获取指定路径下所有以ends为后缀的文件数组，为空时返回null
	* @param @param path
	* @param @param ends
	* @param @return    
	* @return File[]      
	* @author guangchao
	* @date 2014-2-18 上午10:18:07 
	* @throws
	 */
	public static File[] getSpecFiles(String path,final String ends) {
		File file2[]= null;
		try {
//			创建file
			File  file=new File(path);
//			获取file下的以ends为后缀的所有文件
			file2=file.listFiles(new FileFilter() {
				public boolean accept(File file) {
					return file.getName().endsWith(ends);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file2;
	}
/**
 * 
* @Title: delAllFile   
* @Description:    删除指定路径下的所有文件和文件夹
* @param @param path
* @param @return    
* @return boolean      
* @author guangchao
* @date 2014-2-18 上午10:12:30 
* @throws
 */
	public static boolean delAllFile(String path) {
	   boolean flag = false;
	   File file = new File(path);
	   if (!file.exists()) {
	    return flag;
	   }
	   if (!file.isDirectory()) {
	    return flag;
	   }
	   String[] tempList = file.list();
	   File temp = null;
	   for (int i = 0; i < tempList.length; i++) {
	    if (path.endsWith(File.separator)) {
	     temp = new File(path + tempList[i]);
	    } else {
	     temp = new File(path + File.separator + tempList[i]);
	    }
	    if (temp.isFile()) {
	     temp.delete();
	    }
	    if (temp.isDirectory()) {
	       delAllFile(path + File.separator + tempList[i]);// ��ɾ���ļ���������ļ�
	     flag = true;
	    }
	   }
	   flag = delFolder(path);
	   return flag;
	}
/**
 * 
* @Title: delFile   
* @Description:    删除指定路径的文件
* @param @param path
* @param @return    
* @return boolean      
* @author guangchao
* @date 2014-2-18 上午10:12:53 
* @throws
 */
	public static boolean delFile(String path) {
		   boolean flag = false;
		   File file = new File(path);
		   if (!file.exists()) {
		    return flag;
		   }else{
			   file.delete();
			   flag =true;
		   }
		return flag;
	}
	/**
	 * 
	* @Title: zoomImg   
	* @Description:压缩图片（等比例缩放）
	* @param path
	* @param outputWidth 压缩后的宽度像素
	* @return
	* @return String        
	* @author gaoguangchao
	* @date 2014年6月10日 下午7:53:01 
	*
	 */
	public static String zoomImg(String localpath,double outputWidth){
		// 设置临时文件存储位置
		String base = GetConfig.getFileoutputpath();
		String nowDateFoler = new SimpleDateFormat("yyyyMMdd").format(new Date());
		StringBuffer sb = new StringBuffer();
		sb.append(base).append(File.separator).append(nowDateFoler).append(UUID.randomUUID()).append(".jpeg");
		String returnPath = sb.toString();
		FileOutputStream out = null;
		  try { 
              //获得源文件 
              File file = new File(localpath); 
              if (!file.exists()) { 
                      return ""; 
              } 
              Image img = ImageIO.read(file); 
              // 判断图片格式是否正确 
              if (img.getWidth(null) == -1) {
                      System.out.println(" can't read,retry!" + "<BR>");
              } else { 
                      int newWidth; 
                      int newHeight; 
                      // 判断是否是等比缩放 
//                      if (this.proportion == true) { 
                          // 为等比缩放计算输出的图片宽度及高度 
			            float zoom =(float) (((outputWidth/(double) img.getWidth(null))*100)/100.00);// 你要方缩的比例
//                          double rate1 = ((double) img.getWidth(null)) / (double) outputWidth + 0.1;
//                          double rate2 = ((double) img.getHeight(null)) / (double) outputHeight + 0.1;
                          // 根据缩放比率大的进行缩放控制 
//                          double rate = rate1 > rate2 ? rate1 : rate2;
                          newWidth = (int) (((double) img.getWidth(null)) * zoom);
                          newHeight = (int) (((double) img.getHeight(null)) * zoom);
//                      } else { 
//                              newWidth = img.getWidth(null); // 输出的图片宽度
//                              newHeight = img.getHeight(null); // 输出的图片高度
//                      } 
                      BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);

                      /*
                      * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的
                      * 优先级比速度高 生成的图片质量比较好 但速度慢
                      */ 
                      tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
                      out = new FileOutputStream(returnPath);
                      // JPEGImageEncoder可适用于其他图片类型的转换 
                      JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                      encoder.encode(tag); 
                      out.close(); 
              } 
      } catch (IOException ex) { 
              ex.printStackTrace(); 
              returnPath = null;
      } finally{
    	  if(out!=null){
    		  try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
				returnPath = null;
			}
    	  }
      }
		return returnPath;
	}
	/**
	 * 
	* @Title: zoomImg   
	* @Description:	压缩图片（等比例缩放）
	* @param localpath 传入文件绝对路径
	* @param returnPath  传出文件绝对路径
	* @param outputWidth	压缩后的宽度像素
	* @return
	* @return String        
	* @author gaoguangchao
	* @date 2014年6月10日 下午7:56:29 
	*
	 */
	public static String zoomImg(String localpath,String returnPath,double outputWidth){
		FileOutputStream out = null;
		try { 
			//获得源文件 
			File file = new File(localpath); 
			if (!file.exists()) { 
				return ""; 
			} 
			Image img = ImageIO.read(file); 
			// 判断图片格式是否正确 
			if (img.getWidth(null) == -1) {
				System.out.println(" can't read,retry!" + "<BR>");
			} else { 
				int newWidth; 
				int newHeight; 
				// 判断是否是等比缩放 
//                      if (this.proportion == true) { 
				// 为等比缩放计算输出的图片宽度及高度 
				float zoom =(float) (((outputWidth/(double) img.getWidth(null))*100)/100.00);// 你要方缩的比例
//                          double rate1 = ((double) img.getWidth(null)) / (double) outputWidth + 0.1;
//                          double rate2 = ((double) img.getHeight(null)) / (double) outputHeight + 0.1;
				// 根据缩放比率大的进行缩放控制 
//                          double rate = rate1 > rate2 ? rate1 : rate2;
				newWidth = (int) (((double) img.getWidth(null)) * zoom);
				newHeight = (int) (((double) img.getHeight(null)) * zoom);
//                      } else { 
//                              newWidth = img.getWidth(null); // 输出的图片宽度
//                              newHeight = img.getHeight(null); // 输出的图片高度
//                      } 
				BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);
				
				/*
				 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的
				 * 优先级比速度高 生成的图片质量比较好 但速度慢
				 * image.SCALE_SMOOTH //平滑优先
					image.SCALE_FAST//速度优先
					image.SCALE_AREA_AVERAGING //区域均值
					image.SCALE_REPLICATE //像素复制型缩放
					image.SCALE_DEFAULT //默认缩放模式
				 */ 
				tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_FAST), 0, 0, null);
				out = new FileOutputStream(returnPath);
				// JPEGImageEncoder可适用于其他图片类型的转换 
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				encoder.encode(tag); 
				out.close(); 
			} 
		} catch (IOException ex) { 
			ex.printStackTrace(); 
			returnPath = null;
		} finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
					returnPath = null;
				}
			}
		}
		return returnPath;
	}
	/**
	 * 
	* @Title: zoomImg   
	* @Description:
	* @param localpath
	* @param returnPath
	* @param outputWidth
	* @param outputHeight
	* @return
	* @return String        
	* @author gaoguangchao
	* @date 2014年6月10日 下午9:01:40 
	*
	 */
	public static Map<String,Object> zoomImg(String localpath,String returnPath,double outputWidth,double outputHeight){
		FileOutputStream out = null;
		try { 
			//获得源文件 
			File file = new File(localpath); 
			if (file.exists()) { 
				Image img = ImageIO.read(file); 
				// 判断图片格式是否正确 
				if (img.getWidth(null) == -1) {
					System.out.println(" can't read,retry!" + "<BR>");
				} else { 
					int newWidth; 
					int newHeight; 
					// 判断是否是等比缩放 
//                      if (this.proportion == true) { 
					// 为等比缩放计算输出的图片宽度及高度 
					float zoom =(float) (((outputWidth/(double) img.getWidth(null))*100)/100.00);// 你要方缩的比例
//                          double rate1 = ((double) img.getWidth(null)) / (double) outputWidth + 0.1;
//                          double rate2 = ((double) img.getHeight(null)) / (double) outputHeight + 0.1;
					// 根据缩放比率大的进行缩放控制 
//                          double rate = rate1 > rate2 ? rate1 : rate2;
					newWidth = (int) (((double) img.getWidth(null)) * zoom);
					newHeight = (int) (((double) img.getHeight(null)) * zoom);
					outputHeight= Util.todouble(newHeight);
//                      } else { 
//                              newWidth = img.getWidth(null); // 输出的图片宽度
//                              newHeight = img.getHeight(null); // 输出的图片高度
//                      } 
					BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);
					
					/*
					 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的
					 * 优先级比速度高 生成的图片质量比较好 但速度慢
					 * image.SCALE_SMOOTH //平滑优先
					image.SCALE_FAST//速度优先
					image.SCALE_AREA_AVERAGING //区域均值
					image.SCALE_REPLICATE //像素复制型缩放
					image.SCALE_DEFAULT //默认缩放模式
					 */ 
					tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_FAST), 0, 0, null);
					out = new FileOutputStream(returnPath);
					// JPEGImageEncoder可适用于其他图片类型的转换 
					JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
					encoder.encode(tag); 
					out.close(); 
				} 
			} else{
				returnPath = null;
			}
		} catch (IOException ex) { 
			ex.printStackTrace(); 
			returnPath = null;
		} finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
					returnPath = null;
				}
			}
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("returnPah", returnPath);
		map.put("outputHeight", outputHeight);
		return map;
	}

}
