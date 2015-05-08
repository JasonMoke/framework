package com.core.util;
import java.io.File;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.util.FileIO;
import com.util.Util;

/**
 * @ClassName: EBookFileInfoHelper 
 * @Description: 电子书文件信息类
 * @author:  tanyi
 * @date: 2014-01-16 14:11:04
 */ 
public class EBookFileInfoHelper {
	
	private static ZipFile zf;

	/**
     * @Title: getEBookFileInfo
     * @Description: 获得电子书文件信息。
     * @param 文件名
     * @return 返回电子书文件信息
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static EBookFIleInfo getEBookFileInfo(String fileName) throws Exception{
		
		String suffixName = "";	
		EBookFIleInfo eBookFIleInfo = new EBookFIleInfo();
		
		if(Util.isNull(fileName)) return eBookFIleInfo;
		if(false == FileIO.fileExist(fileName)) return eBookFIleInfo;
		
		int index = fileName.lastIndexOf(".");
		if(index <= 0) return eBookFIleInfo;
		
		suffixName = fileName.substring(index + 1, fileName.length());
		suffixName = suffixName.toLowerCase();
		
		if(suffixName.equals("epub")){
			return analysisEpub(fileName);
		}
		else{
			eBookFIleInfo.setISSuccess(true);
			eBookFIleInfo.setFormatName(suffixName);
		}
		return eBookFIleInfo;
	}
	
	/**
     * @Title: getEBookFileInfo
     * @Description: 获得电子书文件信息。
     * @param 文件
     * @return 返回电子书文件信息
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static EBookFIleInfo getEBookFileInfo(File file) throws Exception{
		return getEBookFileInfo(file.getName());
	}
	
	/**  
	
	* @Title: GetFormat  
	* @Description: TODO(根据文件获得文件格式)  
	* @param @param epubFile
	* @param @return
	* @param @throws Exception      
	* @return Map  
	* @author lyc
	* @date 2014-2-17 上午10:04:46
	* @throws  
	*/
	public static EBookFIleInfo analysisEpub(String epubFileName) throws Exception{
		
		ZipEntry entry;
		zf = new ZipFile(epubFileName);
		Enumeration<?> enu = zf.entries();
		EBookFIleInfo eBookFIleInfo = new EBookFIleInfo();
		
		while(enu.hasMoreElements()){
		    entry = (ZipEntry)enu.nextElement();
		    if(false == entry.getName().endsWith(".opf"))continue;
		    
    		SAXReader saxReader = new SAXReader();
    		Document document = saxReader.read(zf.getInputStream(entry));  	
    		Element element=document.getRootElement();
    		eBookFIleInfo.setFormatName(element.attributeValue("version"));
    		if(Util.isNull(eBookFIleInfo.getFormatName())){
    			eBookFIleInfo.setISSuccess(false);
    		}
    		else{
    			eBookFIleInfo.setISSuccess(true);
    		}
    		break;
		}
		return eBookFIleInfo;
	}
}
