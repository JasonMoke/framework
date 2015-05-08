/**
* @Title: Util.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.util   
* @Description:    
* @author guangchao    
* @date 2013-12-27 上午11:32:20   
* @version V1.0 
*/
package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.cglib.beans.BeanGenerator;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;


/**
 * 工具类
 * @version V1.0
 */
public class Util {

	/**
	 * 
	 *<br/>方法描述: 根据类路径创建实例
	 *@param url  类路径
	 *@return Object
	 *@throws Exception  Object
	 */
	public static Object createObject(String url) throws Exception {
		Object o = (Object) Class.forName(url).newInstance();
		return o;
	}

	/**
	 * 
	 *方法功能描述:将double类型转换为指定格式
	 *@param pattern pattern:两位小数"0.00" <br> 三位小数"0.000"
	 *@param source
	 *@return  String
	 */
	public static String formatData(String pattern, double source) {
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(source);
	}
	/**
	 * 
	* @Title: MD5   
	* @Description:    MD5加密方法，返回加密后的字符串
	* @param @param s
	* @param @return    
	* @return String      
	* @author guangchao
	* @date 2014-3-7 上午10:24:54 
	* @throws
	 */
	public static String MD5(String s) {
		if(Util.isNotNull(s)){
			try {
				byte[] btInput = s.getBytes();
				MessageDigest mdInst = MessageDigest.getInstance("MD5");
				mdInst.update(btInput);
				byte[] md = mdInst.digest();
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < md.length; i++) {
					int val = ((int) md[i]) & 0xff;
					if (val < 16)
						sb.append("0");
					sb.append(Integer.toHexString(val));
				}
				return sb.toString();
			} catch (Exception e) {
				return "";
			}
		}else{
			return "";
		}
	}
	/**
	 * 
	* @Title: checkGBK   
	* @Description: 判断java 字符串是否有中文
	* @param str
	* @return
	* @return boolean        
	* @author gaoguangchao
	* @date 2014年6月18日 下午2:49:58 
	*
	 */
	public static boolean checkGBK(String str) {  
        String test = "[\\u4E00-\\u9FA5]+";  
        Pattern p = Pattern.compile(test);  
        Matcher m = p.matcher(str);  
        if (m.find()) {  
            return true;  
        }  
        return false;  
    } 
	/**
	 * 
	 *<br/>方法描述:将字符串转化为double类型 （默认值为 0.0）
	 *@param o 需要转化的字符串对象
	 *@return  double 数字
	 */
	public static double todouble(Object o) {
		try {
			if (o != null) {
				return Double.parseDouble(o.toString());
			} else {
				return 0.0;
			}
		} catch (NumberFormatException e) {
			return 0.0;
		}
	}

	/**
	 * 
	 *<br/>方法描述:将字符串转化为long类型 （默认值为 0）
	 *@param o  需要转化的字符串对象
	 *@return  long数字
	 */ 
	public static long tolong(Object o) {
		try {
			if (o != null) {
				return Long.parseLong(o.toString());
			} else {
				return 0;
			}
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	/**
	 * 
	 *<br/>方法描述:将字符串转化为int类型 （默认值为 0）
	 *@param o   需要转化的字符串对象
	 *@return  int 数字
	 */
	public static int toint(Object o) {
		try {
			if (o != null) {
				return Integer.parseInt(o.toString());
			} else {
				return 0;
			}
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	/**
	 * 
	* @Title: getNowDateStr   
	* @Description:获取当前时间的字符串 格式（yyyy-MM-dd HH:mm:ss）
	* @return
	* @return String        
	* @author gaoguangchao
	* @date 2014年6月26日 下午6:00:29 
	*
	 */
	public static String getNowDateStr() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(new Date());
	}

	/**
	 *  
	 *<br/>方法描述:判断对象是否为空
	 *@param o  检测的对象
	 *@return  boolean 表示为空 false 表示不为空
	 */
	public static boolean isNull(Object o) {
		if (o == null || "".equals(o) || "null".equals(o)) {
			return true;
		}else if(isList(o)){
			return isNullOfList((List<?>) o);
		}else {
			return false;
		}
	}
	/**
	 * 
	* @Title: isNullOfArray   
	* @Description:   判断数字里的所有元素是否为空 ;如果为空数组（null）返回true
	* @param @param o	如 String[] o ={,,}; 
	* @param @return    
	* @return boolean      返回true
	* @author guangchao
	* @date 2014-2-28 上午11:24:30 
	* @throws
	 */
	public static boolean isNullOfArray(String[] o) {
		boolean f = false;
		if(Util.isNull(o)){
			return true;
		}
		for(int i=0;i<o.length;i++){
			if(Util.isNotNull(o[i])){
				f = true;
				break;
			}
		}
//		该数组为空
		if(!f){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 
	* @Title: isNotNullOfArray   
	* @Description: 判断数字里的所有元素是否不为空 ;
	* @param o
	* @return
	* @return boolean        
	* @author gaoguangchao
	* @date 2014年6月24日 下午3:43:59 
	*
	 */
	public static boolean isNotNullOfArray(String[] o) {
		return !isNullOfArray(o);
	}
	/**
	 * @param <T>
	 * 
	* @Title: isNullOfList   
	* @Description:    判断List<String>中所有元素是否为空
	* @param @param o
	* @param @return    true：空
	* @return boolean      
	* @author guangchao
	* @date 2014-3-19 下午3:49:41 
	* @throws
	 */
	public static <T> boolean isNullOfList(List<T> o) {
		boolean f = false;
		if(o==null){
			return true;
		}
		if(o.size()==0){
			return true;
		}
		for(int i=0;i<o.size();i++){
			if(Util.isNotNull(o.get(i))){
				f = true;
				break;
			}
		}
//		该数组为空
		if(!f){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 
	* @Title: isNotNullOfList   
	* @Description:判断List<String>中所有元素是否不为空
	* @param o
	* @return
	* @return boolean        
	* @author gaoguangchao
	* @date 2014年6月24日 下午3:44:52 
	*
	 */
	public static <T> boolean isNotNullOfList(List<T> o) {
		return !isNullOfList(o);
	}
	/**
	 *  
	 *<br/>方法描述:判断对象是否不为空
	 *@param o  检测的对象
	 *@return  boolean 表示为不为空  false 表示为空
	 */	
	public static boolean isNotNull(Object o) {
		return !isNull(o);
	}
	/**
	 * 
	* @Title: getFileSuffixByPath   
	* @Description:  传一个文件的路径或者文件的名称的String，截取该 String的后缀
	* @param @param source（D:\SVN\Design\DataBaseDesign\视图.txt）
	* @param @return    
	* @return String  如（.txt）    
	* @author guangchao
	* @date 2014-2-27 上午11:41:27 
	* @throws
	 */
	public static String getFileSuffixByPath(String source) {
		if(Util.isNull(source)){
			return "";
		}else{
			return source.lastIndexOf(".") == -1 ? "" : source.substring(source.lastIndexOf("."));
		}
	}
	/**
	 * 
	* @Title: subStringByCondition   
	* @Description:    判断source最后一位是否为sItem，如果是则删除掉最后一位,否则返回原source（用于将“123,123,342,”格式化为“123,123,342”）
	* @param @param source
	* @param @param sItem
	* @param @return    
	* @return String      
	* @author guangchao
	* @date 2014-2-27 下午4:40:51 
	* @throws
	 */
	public static String subStringByCondition(String source, String sItem) {
		if(Util.isNull(source)||Util.isNull(sItem)){
			return "";
		}else{
			source = source.trim();
			if(source.length()>0){
				if(source.lastIndexOf(sItem)==source.length()-1){
					source = source.substring(0, source.length()-1);
				}
	    	}
			return source;
		}
	}
	/**
	 * 
	* @Title: subLastIndex   
	* @Description:判断source最后一位是否为sItem，如果是则删除掉最后一位,否则返回原source（用于将“123,123,342,”格式化为“123,123,342”）
	* @param source
	* @param sItem
	* @return
	* @return String        
	* @author gaoguangchao
	* @date 2014年6月24日 下午3:53:04 
	*
	 */
	public static String subLastIndex(String source, String sItem) {
		if(Util.isNull(source)||Util.isNull(sItem)){
			return "";
		}else{
			if(source.length()>0){
				if(source.lastIndexOf(sItem)==source.length()-1){
					source = source.substring(0, source.length()-1);
				}
			}
			return source;
		}
	}
	/**
	 * 
	 *<br/>方法描述:字符编码转换：从iso转化成Utf8
	 *@param source  源字符串
	 *@return  String 编码后的字符串
	 */
	public static String isoToUtf8(String source) {
		String target = "";
		try {
			target = new String(source.getBytes("iso-8859-5"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return target;
	}

	/**
	 * 
	 *<br/>方法描述:字符编码转换：从iso转化成GBK
	 *@param source 源字符串
	 *@return  String 编码后的字符串
	 */
	public static String isoToGBK(String source) {
		String target = "";
		try {
			target = new String(source.getBytes("iso-8859-5"), "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return target;
	}
	/**
	 * 
	* @Title: utf8ToGBK   
	* @Description:utf8 转成GBK
	* @param source
	* @return
	* @return String        
	* @author gaoguangchao
	* @date 2014年6月18日 下午2:46:26 
	*
	 */
	public static String utf8ToGBK(String source) {
		String target = "";
		try {
			target = new String(source.getBytes("utf-8"), "gbk");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return target;
	}

	/**
	 * 
	 *<br/>方法描述:字符编码转换：从iso转化成GB2312
	 *@param source 源字符串
	 *@return  String 编码后的字符串
	 */
	public static String isoToGB2312(String source) {
		String target = "";
		try {
			target = new String(source.getBytes("iso-8859-5"), "gb2312");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return target;
	}
	/**
	 * 
	* @Title: GBKtoISO   
	* @Description:  转码：GBK ----> iso-8859-1  
	* @param @param s
	* @param @return    
	* @return String      
	* @author guangchao
	* @date 2014-2-17 下午12:33:19 
	* @throws
	 */
    public static String GBKtoISO(String s) {
        try {
            s = new String(s.getBytes("GBK"), "iso-8859-1");
        } catch (Exception e) {
        }
        return s;
    }
    /**
     * 
    * @Title: GBKtoUTF8   
    * @Description:转码：GBK ----> UTF8 
    * @param s
    * @return
    * @return String        
    * @author gaoguangchao
    * @date 2014年6月19日 下午7:13:42 
    *
     */
    public static String GBKtoUTF8(String s) {
    	String fullStr = "";
    	try {
            byte[] fullByte = gbk2utf8(s);
            fullStr = new String(fullByte, "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
        }
    	return fullStr;
    }
    /**
     * 
    * @Title: gbk2utf8   
    * @Description:
    * @param chenese
    * @return
    * @return byte[]        
    * @author gaoguangchao
    * @date 2014年6月19日 下午7:14:15 
    *
     */
    public static byte[] gbk2utf8(String gbkStr) {
        
    	int n = gbkStr.length();  
        byte[] utfBytes = new byte[3 * n];  
        int k = 0;  
        for (int i = 0; i < n; i++) {  
            int m = gbkStr.charAt(i);  
            if (m < 128 && m >= 0) {  
                utfBytes[k++] = (byte) m;  
                continue;  
            }  
            utfBytes[k++] = (byte) (0xe0 | (m >> 12));  
            utfBytes[k++] = (byte) (0x80 | ((m >> 6) & 0x3f));  
            utfBytes[k++] = (byte) (0x80 | (m & 0x3f));  
        }  
        if (k < utfBytes.length) {  
            byte[] tmp = new byte[k];  
            System.arraycopy(utfBytes, 0, tmp, 0, k);  
            return tmp;  
        }  
        return utfBytes;  
    }
    /**
     * 
    * @Title: getString   
    * @Description:  判断参数是否为null，为空则返回""，否则返回其值  
    * @param @param sSource
    * @param @return    
    * @return String      
    * @author guangchao
    * @date 2014-2-17 下午12:34:10 
    * @throws
     */
    public static String getString(String sSource) {
        String sReturn = "";
        if (sSource != null) {
            sReturn = sSource;
        }
        return sReturn;
    }
    /**
     * 
    * @Title: getOSName   
    * @Description:   获取服务器 操作系统名称 	
    * @param @return    如：Windows 7 ,Linux
    * @return String      
    * @author guangchao
    * @date 2014-2-18 下午3:48:13 
    * @throws
     */
    public static String getOSName() {
    	Properties props=System.getProperties(); //获得系统属性集    
		String osName = props.getProperty("os.name"); //操作系统名称    
    	return osName;
    }
    /**
     * @Title: isContain   
     * @Description:  查找以逗号分隔的源字符串是否包含给定字符串
     * @param sSource :源字符串
     * @param sItem :子串
     * @return 是否包含
     * @author guangchao
     * @date 2014-2-17 下午12:34:10  
     */
    public static boolean isContain(String sSource, String sItem) {
        boolean isReturn = false;
    	if(Util.isNull(sSource)||Util.isNull(sItem)){
    		return isReturn;
    	}else{
    		StringTokenizer st = null;
            st = new StringTokenizer(sSource, ",");
            while (st.hasMoreTokens()) {
                if (sItem.equals(st.nextToken())) {
                    isReturn = true;
                    break;
                }
            }
    	}
        return isReturn;
    }
    /**
     * @Title: isContain   
     * @Description:   查找源字符串数组中是否包含给定字符串
     * @param aSource :源字符串数组
     * @param sItem :子串
     * @return 是否包含
     * @author guangchao
     * @date 2014-2-17 下午12:34:10 
     */
    public static boolean isContain(String[] aSource, String sItem) {
    	boolean isReturn = false;
    	if(Util.isNull(aSource)||Util.isNull(sItem)){
    		return isReturn;
    	}else{
    		for (int i = 0; i < aSource.length; i++) {
    			if (sItem.equals(aSource[i])) {
    				isReturn = true;
    				break;
    			}
    		}
    	}
        return isReturn;
    }
    /**
     * 
    * @Title: isContainRepeatString   
    * @Description:    判断字符串数组里是否有重复的元素（注意若数组里有空值，则返回false）
    * @param @param aSource
    * @param @return    
    * @return boolean   存在重复返回true   
    * @author guangchao
    * @date 2014-2-27 下午5:21:54 
    * @throws
     */
    public static boolean isContainRepeatString(String[] aSource) {
    	boolean isReturn = false;
    	if(Util.isNull(aSource)){
    		return false;
    	}
    	for (int i = 0; i < aSource.length; i++) {
    		String item = aSource[i];
    		if(Util.isNull(item)){
    			return false;
    		}
    		int num = 0;
    		for(int j=0;j<aSource.length; j++){
    			String item0 = aSource[j];
    			if(item.equals(item0)){
    				num = num+1;
    			}
    		}
    		if(num>1){
    			isReturn = true;
    			break;
    		}
    	}
    	return isReturn;
    }
    /**
     * @Title: replaceString   
     * @Description:   此函数有三个输入参数，源字符串(将被操作的字符串),原字符串中被替换的字符串(旧字符串)
     * 替换的字符串(新字符串)，函数接收源字符串、旧字符串、新字符串三个值后，
     * 用新字符串代替源字符串中的旧字符串并返回结果
     * @param source 源字符串
     * @param oldString 旧字符串
     * @param newString 新字符串
     * @return 替换后的字符串
     * @author guangchao
     * @date 2014-2-17 下午12:34:10 
     */
    public static String replaceString(String source, String oldString,
            String newString) {
        StringBuffer output = new StringBuffer();
        int lengthOfSource = source.length(); // 源字符串长度
        int lengthOfOld = oldString.length(); // 老字符串长度
        int posStart = 0; // 开始搜索位置
        int pos; // 搜索到老字符串的位置
        while ((pos = source.indexOf(oldString, posStart)) >= 0) {
            output.append(source.substring(posStart, pos));
            output.append(newString);
            posStart = pos + lengthOfOld;
        }
        if (posStart < lengthOfSource) {
            output.append(source.substring(posStart));
        }
        return output.toString();
    }
    /**
     * @Title: replaceAllString   
     * @Description:   此函数有三个输入参数，源字符串(将被操作的字符串),原字符串中被替换的字符串(旧字符串)
     * 替换的字符串(新字符串)，函数接收源字符串、旧字符串、新字符串三个值后，
     * 用新字符串代替源字符串中的旧字符串并返回结果,并且会重复到全部替换为止
     * @param source 源字符串
     * @param oldString 旧字符串
     * @param newString 新字符串
     * @return 替换后的字符串
     * @author guangchao
     * @date 2014-2-17 下午12:34:10 
     */
    public static String replaceAllString(String source, String oldString,String newString) {
    	StringBuffer output = new StringBuffer();
    	if(!source.contains(oldString)){
    		return source;
    	}
    	int lengthOfSource = source.length(); // 源字符串长度
    	int lengthOfOld = oldString.length(); // 老字符串长度
    	int posStart = 0; // 开始搜索位置
    	int pos; // 搜索到老字符串的位置
    	while ((pos = source.indexOf(oldString, posStart)) >= 0) {
    		output.append(source.substring(posStart, pos));
    		output.append(newString);
    		posStart = pos + lengthOfOld;
    	}
    	if (posStart < lengthOfSource) {
    		output.append(source.substring(posStart));
    	}
    	String out = output.toString();
    	while(out.contains(oldString)){
    		out = replaceString(output.toString(), oldString, newString);
		}
    	return out;
    }
    
    /**
     * @Title: arrayToString   
     * @Description:  将数组中的元素连成一个以逗号分隔的字符串
     * @param aSource 源数组
     * @return 字符串
     * @author guangchao
     * @date 2014-2-17 下午12:34:10 
     */
    public static String arrayToString(String[] aSource) {
        String sReturn = "";
        for (int i = 0; i < aSource.length; i++) {
            if (i > 0) {
                sReturn += ",";
            }
            sReturn += aSource[i];
        }
        return sReturn;
    }
    /**
     * @Title: arrayToString   
     * @Description: 将数组中的元素连成一个以逗号分隔的字符串
     * @param aSource 源数组
     * @return 字符串
     * @author guangchao
     * @date 2014-2-17 下午12:34:10  
     */
    public static String arrayToString(int[] aSource) {
        String sReturn = "";
        for (int i = 0; i < aSource.length; i++) {
            if (i > 0) {
                sReturn += ",";
            }
            sReturn += aSource[i];
        }
        return sReturn;
    }
    /**
     * @Title: arrayToString   
     * @Description:  将数组中的元素连成一个以给定字符分隔的字符串
     * @param aSource 源数组
     * @param sChar 分隔符
     * @return 字符串
     * @author guangchao
     * @date 2014-2-17 下午12:34:10  
     */
    public static String arrayToString(String[] aSource, String sChar) {
        String sReturn = "";
        for (int i = 0; i < aSource.length; i++) {
            if (i > 0) {
                sReturn += sChar;
            }
            sReturn += aSource[i];
        }
        return sReturn;
    }
    /**
     * 
    * @Title: stringToArray   
    * @Description:将String字符串根据Char参数转换成数组，且去除每个值的前后空格
    * @param aSource
    * @param sChar
    * @return
    * @return String[]        
    * @author gaoguangchao
    * @date 2014年6月24日 下午4:50:59 
    *
     */
    public static String[] stringToArray(String aSource, String sChar){
    	if(Util.isNull(aSource)||Util.isNull(sChar)){
    		return new String[0];
    	}
    	String[] array = aSource.split(sChar);
    	String[] returnArray = new String[array.length];
    	for(int i=0;i<array.length;i++){
    		String s = array[i].trim();
    		returnArray[i] = s;
    	}
    	return returnArray;
    	
    }
    /**
     * 
    * @Title: stringToArray   
    * @Description:将String字符串根据","转换成数组，且去除每个值的前后空格
    * @param aSource
    * @return
    * @return String[]        
    * @author gaoguangchao
    * @date 2014年6月24日 下午4:52:00 
    *
     */
    public static String[] stringToArray(String aSource){
    	if(Util.isNull(aSource)){
    		return new String[0];
    	}
    	String[] array = aSource.split(",");
    	String[] returnArray = new String[array.length];
    	for(int i=0;i<array.length;i++){
    		String s = array[i].trim();
    		returnArray[i] = s;
    	}
    	return returnArray;
    	
    }
    
    /**
     * @Title: addMark   
     * @Description:将以逗号分隔的字符串的每个元素加上单引号 如： 1000,1001,1002 --> '1000','1001','1002'
     * @param sSource 源串
     * @return String
     * @author guangchao
     * @date 2014-2-17 下午12:34:10  
     */
    public static String addMark(String sSource) {
        String sReturn = "";
        StringTokenizer st = null;
        st = new StringTokenizer(sSource, ",");
        if (st.hasMoreTokens()) {
            sReturn += "'" + st.nextToken() + "'";
        }
        while (st.hasMoreTokens()) {
            sReturn += "," + "'" + st.nextToken() + "'";
        }
        return sReturn;
    }
    /**
     * 
    * @Title: addMark   
    * @Description:将以逗号分隔的字符串的每个元素加上Char参数
    *  如Char为单引号： 1000,1001,1002 --> '1000','1001','1002'
    *  如Char为双引号： 1000,1001,1002 --> "1000","1001","1002"
    * @param sSource
    * @param Char
    * @return
    * @return String        
    * @author gaoguangchao
    * @date 2014年6月24日 下午3:47:56 
    *
     */
    public static String addMark(String sSource,String Char) {
    	String sReturn = "";
    	if(Util.isNull(sSource)||Util.isNull(Char)){
    		return sReturn;
    	}
    	StringTokenizer st = null;
    	st = new StringTokenizer(sSource, ",");
    	if (st.hasMoreTokens()) {
    		sReturn += Char + st.nextToken() + Char;
    	}
    	while (st.hasMoreTokens()) {
    		sReturn += "," + Char + st.nextToken() + Char;
    	}
    	return sReturn;
    }
	/**
	 * 
	 *<br/>方法描述: 基于map 动态创建bean
	 *@param queryMap  查询属性封装 eg ： {eqName :int}
	 *@return  Object
	 */
	public static Object createDynamicBean(Map<?, ?> queryMap) {
		Iterator<?> iterQuery = queryMap.keySet().iterator();// 初始化bean 主要是处理int
		// double的默认值
		BeanGenerator bg = new BeanGenerator(); // 基于配置信息，利用cglib，动态创建类
		Map<String, Integer> defaltSetMap = new HashMap<String, Integer>();// 处理int double
		// 因为基于cglib，创建bean的时候，会自动赋值为0
		while (iterQuery.hasNext()) {
			String key = (String) iterQuery.next();
			String name = key;
			String type = ((String) queryMap.get(key)).toLowerCase();
			if ("int".equals(type)) {
				bg.addProperty(name, Integer.TYPE);
				defaltSetMap.put(name, new Integer(-111111));// 设置-111111，
				// 作为整型的默认值
				continue;
			}
			if ("double".equals(type)) {
				bg.addProperty(name, Double.TYPE);
				defaltSetMap.put(name, new Integer(-222222));// 设置-222222，
				// 作为浮点型的默认值
				continue;
			}
			if ("string".equals(type)) {
				bg.addProperty(name, String.class);
				continue;
			}
			if ("list".equals(type)) {
				bg.addProperty(name, Collection.class);
				continue;
			}
		}
		Object bean = bg.create();// 动态创建查询对象实例
		Iterator<String> iter = defaltSetMap.keySet().iterator();// 初始化bean 主要是处理int
		// double的默认值
		while (iter.hasNext()) {
			String key = (String) iter.next();
			try {
				PropertyUtils.setProperty(bean, key, defaltSetMap.get(key));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		return bean;
	}

	/**
	 * 
	 *<br/>方法描述: 依照指定属性集合排序
	 *@param collection  需要排序的集合
	 *@param sortCol 排序字段
	 *@param isAsc true 升序 false 降序
	 *@return  List
	 */
	public static List<Object> sortCollection(List<Object> collection, String sortCol,boolean isAsc) {
		for (int i = 0; i < collection.size(); i++) {
			for (int j = i + 1; j < collection.size(); j++) {
				BeanWrapper bwi = new BeanWrapperImpl(collection.get(i));
				BeanWrapper bwj = new BeanWrapperImpl(collection.get(j));
				int leftI = (Integer) bwi.getPropertyValue(sortCol);
				int leftJ = (Integer) bwj.getPropertyValue(sortCol);
				if (isAsc) {
					if (leftI > leftJ) {
						Object obj = collection.get(j);
						collection.set(j, collection.get(i));
						collection.set(i, obj);
					}
				} else {
					if (leftI < leftJ) {
						Object obj = collection.get(j);
						collection.set(j, collection.get(i));
						collection.set(i, obj);
					}
				}
			}
		}
		return collection;
	}

	/**
	 * 
	 *<br/>方法描述: 解析配置文件 ，得到的是一个Map 配置文件的写法如下: priceRule.subject.type.size=3
	 * priceRule.subject.type.0=0,\u5206\u652f\u79d1\u76ee
	 * priceRule.subject.type.1=1,\u586b\u62a5\u79d1\u76ee
	 * priceRule.subject.type.2=2,\u5206\u644a\u79d1\u76ee
	 *@param fileName  要解析的文件全名，没有后缀名
	 *@param key 表示大小的key
	 *@return  Map<String,String>
	 */
	public static Map<String, String> parsePropertiesReturnMap(String fileName,	String key) {
		Map<String, String> map = new TreeMap<String, String>();
		ResourceBundle rb = ResourceBundle.getBundle(fileName);
		String value = rb.getString(key);
		int count = 0;
		if (null != value) {
			count = Integer.parseInt(value);
		}
		for (int i = 0; i < count; i++) {
			String subKey = key.replaceAll("size", (i + ""));
			map.put(rb.getString(subKey).split(",")[0], rb.getString(subKey)
					.split(",")[1]);
		}
		return map;
	}

	/**
	 *<br/>方法描述: 删除list中空的对象
	 *@param list<T>  
	 *@return list<T>
	 */
	public static <T> List<T> removeNullList(List<T> list){
		List<T> newList = new ArrayList<T>();
		if(Util.isNull(list)) return newList;
		for(T entity : list){
			if(Util.isNull(entity)) continue;
			newList.add(entity);
		}
		return newList;
	}

	/**
	 * 
	 *<br/>方法描述: 解析出配置文件。返回一个str
	 *@param fileName  要解析的文件全名，没有后缀名
	 *@param key  key
	 *@return  String
	 */
	public static String parsePropertiesReturnStr(String fileName, String key) {
		ResourceBundle rb = ResourceBundle.getBundle(fileName);
		return rb.getString(key);
	}

	/**
	 * 
	 *<br/>方法描述: 解析出配置文件。返回一个str,逗号右边的
	 *@param fileName 要解析的文件全名，没有后缀名
	 *@param key  key
	 *@return  String
	 */
	public static String parsePropertiesReturnStrAfterComma(String fileName,
			String key) {
		ResourceBundle rb = ResourceBundle.getBundle(fileName);
		return rb.getString(key).split(",")[1];
	}

	/**
	 *  
	 *<br/>方法描述: 解析出配置文件。返回一个str,逗号左边的
	 *@param fileName 要解析的文件全名，没有后缀名
	 *@param key key
	 *@return  String
	 */
	public static String parsePropertiesReturnStrBeforeComma(String fileName,
			String key) {
		ResourceBundle rb = ResourceBundle.getBundle(fileName);
		return rb.getString(key).split(",")[0];
	}

	/**
	 * 
	 *<br/>方法描述:获取uuid去掉“-” <br/>xlp
	 *@return  String
	 */
	public static String getUuid() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}
	/**
	 * 
	* @Title: isNumber   
	* @Description:    判断是否为number	不包含小数点
	* @param @param numStr
	* @param @return    
	* @return boolean      
	* @author guangchao
	* @date 2014-1-14 下午3:43:31 
	* @throws
	 */
	public static boolean isNumber(String numStr) {
		Pattern pattern = Pattern.compile("[0-9]*");  //Character.isDigit(char); or ascii码
		return pattern.matcher(numStr).matches();
	}
	/**
	 * 
	* @Title: isNumberDecimal   
	* @Description:    判断是否为number	包含0-4位小数点	
	* @param @param numStr
	* @param @return    
	* @return boolean      
	* @author guangchao
	* @date 2014-2-17 上午10:18:54 
	* @throws
	 */
	public static boolean isNumberDecimal(String numStr) {
		Pattern pattern = Pattern.compile("^[0-9]\\d*\\.\\d*|[0-9]\\d*$");  //Character.isDigit(char); or ascii码
		return pattern.matcher(numStr).matches();
	}
	/**
	 * 
	* @Title: isZeroNumberDecimal   
	* @Description:判断是否为小与1的正整数  如0.01 ，0.5
	* @param numStr
	* @return
	* @return boolean        
	* @author gaoguangchao
	* @date 2014年6月25日 下午6:20:30 
	*
	 */
	public static boolean isZeroNumberDecimal(String numStr) {
		Pattern pattern = Pattern.compile("^0\\.\\d*|[0-9]\\d*$");  //Character.isDigit(char); or ascii码
		return pattern.matcher(numStr).matches();
	}
	/**
	 * 
	* @Title: isDateString   
	* @Description:    判断是否为时间格式的字符串	
	* 					支持2004_04_30
	*					2004/04/30
	*					2004-04-30
	*					2004-04-30 01:11:0
	*					2004-04-30 0:0:0等,支持闰年
	* @param @param numStr
	* @param @return    
	* @return boolean      
	* @author guangchao
	* @date 2014-2-17 上午10:38:30 
	* @throws
	 */
	public static boolean isDateString(String numStr) {
		 Pattern pattern = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\_\\/\\s]?((((0?[13578])|(1[02]))[\\-\\_\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\_\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
		return pattern.matcher(numStr).matches();
	}

	/**
	 * 
	* @Title: isInteger   
	* @Description:   判断是否为 Integer 
	* @param @param value
	* @param @return    
	* @return boolean      
	* @author guangchao
	* @date 2014-1-14 下午3:43:49 
	* @throws
	 */
	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	/**
	 * 
	* @Title: isDouble   
	* @Description:   判断是否为 Double 
	* @param @param value
	* @param @return    
	* @return boolean      
	* @author guangchao
	* @date 2014-1-14 下午3:43:55 
	* @throws
	 */
	public static boolean isDouble(String value) {
		try {
			Double.parseDouble(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	/**
	 * 
	* @Title: isInteger4Reg   
	* @Description:  判断是否为 Integer4Reg    
	* @param @param value
	* @param @return    
	* @return boolean      
	* @author guangchao
	* @date 2014-1-14 下午3:44:15 
	* @throws
	 */
	public static boolean isInteger4Reg(String value) {
		//"^[0-9]*(\.[0])?$";
		Pattern pattern = Pattern.compile("^[0-9]*(\\.[0])?$");  //Character.isDigit(char); or ascii码
		return pattern.matcher(value).matches();
	}
	
	
	

	/**
	 * 
	 *<br/>方法描述: 按数据库总记录数与每页记录数计算出总页数
	 *@param logCount  数据库的总页数
	 *@param pageSize 页面显示时的第页的记录数
	 *@return  int 返回此次查询的总页数
	 */
	public static int calculateTotalPage(int logCount, int pageSize) {
		if (logCount % pageSize == 0) {
			return logCount / pageSize;
		} else {
			return (logCount / pageSize) + 1;
		}
	}
	/**
	 * 
	* @Title: changeDateToString   
	* @Description:    将Date转换为指定格式的Sting类型
	* @param @param date 时间		new Date() 等
	* @param @param format	yyyy-MM-dd HH:mm:ss 等
	* @param @return    
	* @return String      
	* @author guangchao
	* @date 2014-2-13 下午2:59:23 
	* @throws
	 */
	public static String changeDateToString(Date date,String format){
		String nowDate = "";
		if(isNotNull(format)){
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			nowDate=formatter.format(date);
		}
		return nowDate;
	}
	/**
	 * 
	* @Title: changeLongToDateString   
	* @Description:    将long类型转换为制定类型的String
	* @param @param currentTime
	* @param @param formatType
	* @param @return
	* @param @throws ParseException
	* @param @throws java.text.ParseException    
	* @return String      
	* @author guangchao
	* @date 2014-2-17 上午10:58:55 
	* @throws
	 */
	public static String changeLongToDateString(long currentTime, String formatType) throws ParseException
 			{
 		Date date = changeLongToDate(currentTime, formatType); // long类型转成Date类型
 		String strTime = changeDateToString(date, formatType); // date类型转成String
 		return strTime;
 	}
	/**
	 * 
	* @Title: changeLongToDate   
	* @Description:     将long类型转换为制定类型的Date
	* @param @param currentTime
	* @param @param formatType
	* @param @return
	* @param @throws ParseException    
	* @return Date      
	* @author guangchao
	* @date 2014-2-17 上午10:59:04 
	* @throws
	 */
	public static Date changeLongToDate(long currentTime, String formatType)
 			throws ParseException {
 		Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
 		String sDateTime = changeDateToString(dateOld, formatType); // 把date类型的时间转换为string
 		Date date = changeStringToDate(sDateTime, formatType); // 把String类型转换为Date类型
 		return date;
 	}
	/**
	 * 
	* @Title: changeStringToDate   
	* @Description:    将String类型转换为制定类型的Date
	* @param @param strTime
	* @param @param formatType
	* @param @return
	* @param @throws ParseException
	* @param @throws java.text.ParseException    
	* @return Date      
	* @author guangchao
	* @date 2014-2-17 上午10:59:10 
	* @throws
	 */
	public static Date changeStringToDate(String strTime, String formatType)
 			throws ParseException {
 		SimpleDateFormat formatter = new SimpleDateFormat(formatType);
 		Date date = null;
 		date = formatter.parse(strTime);
 		return date;
 	}
	/**
	 * @throws ParseException 
	 * 
	* @Title: changeStringToDateString   
	* @Description:    将日期格式的string 转换为新的格式的 String
	* @param @param dateStr
	* @param @param format
	* @param @return    
	* @return String      
	* @author guangchao
	* @date 2014-2-17 上午10:48:38 
	* @throws
	 */
	public static String changeDateStringToDateString(String strTime,String formatType) throws ParseException{
		String DateString = "";
		if(isNotNull(formatType)&&isNotNull(strTime)){
				SimpleDateFormat formatter = new SimpleDateFormat(formatType);
		 		Date date = null;
		 		date = formatter.parse(strTime);
		 		DateString = changeDateToString(date,formatType);
		}else{
			return "false";
		}
		return DateString;
	}
	/**
	 * 
	* @Title: changeNum   
	* @Description:    将一整型数值转化为大写字符串,如将"123"转化为"一百二十三"
	* @param @param oldNumber
	* @param @return    
	* @return String      
	* @author guangchao
	* @date 2014-1-14 下午3:45:08 
	* @throws
	 */
	public static String changeNum(int oldNumber) {
		String newNumber = "";
		char[] temp = String.valueOf(oldNumber).toCharArray();

		for (int i = 0; i < temp.length; i++) {
			boolean sign = true;
			switch (temp[i]) {
			case '1':
				newNumber += "一";
				break;
			case '2':
				newNumber += "二";
				break;
			case '3':
				newNumber += "三";
				break;
			case '4':
				newNumber += "四";
				break;
			case '5':
				newNumber += "五";
				break;
			case '6':
				newNumber += "六";
				break;
			case '7':
				newNumber += "七";
				break;
			case '8':
				newNumber += "八";
				break;
			case '9':
				newNumber += "九";
				break;
			case '0':
				if (i != temp.length - 1) {
					if (temp[i + 1] == '0') {
						sign = false;
						break;
					} else {
						newNumber += "零";
						break;
					}
				} else
					break;
			}
			if (!newNumber.endsWith("零") && sign == true) {

				switch (temp.length - i) {
				case 12:
					newNumber += "千";
					break;
				case 11:
					newNumber += "百";
					break;
				case 10:
					newNumber += "十";
					break;

				case 8:
					newNumber += "千";
					break;
				case 7:
					newNumber += "百";
					break;
				case 6:
					newNumber += "十";
					break;

				case 4:
					newNumber += "千";
					break;
				case 3:
					newNumber += "百";
					break;
				case 2:
					newNumber += "十";
					break;
				}
			}
			switch (temp.length - i) {
			case 9:
				newNumber += "亿";
				break;
			case 5:
				newNumber += "万";
				break;
			}
		}
		if (newNumber.startsWith("一十")) {
			newNumber = newNumber.replaceFirst("一十", "十");
		}
		newNumber = newNumber.replaceAll("亿万", "亿");
		newNumber = newNumber.replaceAll("零万", "万");
		return newNumber;
	}
	/**
	 * 
	* @Title: join   
	* @Description:    类似js中的join使用   比如说 ： List(^)  --->>  xxx^yyy^
	* @param @param s
	* @param @param delimiter
	* @param @return    
	* @return String      
	* @author guangchao
	* @date 2014-1-14 下午3:45:22 
	* @throws
	 */
	public static String join(Collection<String> s, String delimiter) {
	    if (s.isEmpty()) return "";
	    Iterator<String> iter = s.iterator();
	    StringBuffer buffer = new StringBuffer(iter.next());
	    while (iter.hasNext()) buffer.append(delimiter).append(iter.next());
	    return buffer.toString();
	}
	/**
	 * 
	* @Title: ConvertObjToMap   
	* @Description:实体类转为Map
	* @param obj
	* @return
	* @return Map        
	* @author gaoguangchao
	* @date 2014年9月10日 下午4:23:48 
	*
	 */
	public static Map<String,Object> ConvertObjToMap(Object obj){
        Map<String,Object> reMap = new HashMap<String,Object>();
        if (obj == null) 
        	return null;
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
         for(int i=0;i<fields.length;i++){
          try {
           Field f = obj.getClass().getDeclaredField(fields[i].getName());
           f.setAccessible(true);
                 Object o = f.get(obj);
                 reMap.put(fields[i].getName(), o);
          } catch (NoSuchFieldException e) {
           e.printStackTrace();
          } catch (IllegalArgumentException e) {
           e.printStackTrace();
          } catch (IllegalAccessException e) {
           e.printStackTrace();
          }
         }
        } catch (SecurityException e) {
         e.printStackTrace();
        } 
        return reMap;
       }
	/**
	 * 
	* @Title: isPrimitive  
	* @Description:  判断是否为基本类型 
	* @param @param o
	* @param @return      
	* @return boolean  
	* @author lyc
	* @date 2014年9月11日 下午5:47:22
	* @throws
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isPrimitive(Object o){
		Class objClass = o.getClass();
		if(objClass.isPrimitive()){
			return true;
		}
		if (objClass.getPackage().getName().equals("java.lang")) {  
			return true;  
        }  
        if (objClass.getPackage().getName().equals("java.util")) {  
        	return true;  
        }  
		return false;
		
	}
	/**
	 * 
	* @Title: isMap  
	* @Description: 判断是否为Map  
	* @param @param o
	* @param @return      
	* @return boolean  
	* @author lyc
	* @date 2014年9月11日 下午5:57:19
	* @throws
	 */
	public static boolean isMap(Object o){
		if(o==null){
			return false;
		}
		if (o.getClass().getName().equals("java.util.HashMap")) {  
			return true;  
		}  
		if (o.getClass().getName().equals("java.util.Map")) {  
			return true;  
		}  
		return false;
		
	}
	/**
	 * 
	* @Title: isList   
	* @Description:判断是否为List/ArrayList
	* @param o
	* @return
	* @return boolean        
	* @author MyPC
	* @date 2014年9月23日 下午3:59:22 
	*
	 */
	public static boolean isList(Object o){
		if(o==null){
			return false;
		}
		if (o.getClass().getName().equals("java.util.ArrayList")) {  
			return true;  
		}  
		if (o.getClass().getName().equals("java.util.List")) {  
			return true;  
		}  
		return false;
		
	}
	/**
	 * 
	* @Title: isString   
	* @Description:判断是否为String类型
	* @param o
	* @return
	* @return boolean        
	* @author gaoguangchao
	* @date 2014年12月16日 下午3:00:32 
	*
	 */
	public static boolean isString(Object o){
		if(o==null){
			return false;
		}
		if (o.getClass().getName().equals("java.util.String")) {  
			return true;  
		}
		if(o instanceof java.lang.String){
			return true;
		}
		return false;
	}
	/**
	 * 
	* @Title: isArray   
	* @Description:判断是否为String[]类型
	* @param o
	* @return
	* @return boolean        
	* @author gaoguangchao
	* @date 2014年12月16日 下午3:12:47 
	*
	 */
	public static boolean isArray(Object o){
		if(o==null){
			return false;
		}
		if (o.getClass().getName().equals("java.util.String[]")) {  
			return true;  
		}
		if(o instanceof java.lang.String[]){
			return true;
		}
		return false;
	}
	/**
	 * 将输入流转为字符串
	 * 
	 * @param is
	 * @return
	 */
	public  static String convertStreamToString(InputStream is) {
		BufferedReader reader=null;
		try {
			reader = new BufferedReader(new InputStreamReader(is,"utf-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line );
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}	
	/**
	 * 去除集合中的Null对象
	 * 
	 * @param collection  原集合对象 
	 * @return
	 */
	public  static Collection<?> removeNullObject4Collection(Collection<?> collection) {
		if(collection==null)  return collection;
		Iterator<?>  iterator=collection.iterator();
		while(iterator.hasNext()){
			Object o=iterator.next();
			if(o==null) iterator.remove();
		}
		return collection;
	}	
	/**
	 * 
	* @Title: formatSplitStr   
	* @Description:    
	* @param @param src
	* @param @param step
	* @param @param format
	* @param @return    
	* @return String      
	* @author guangchao
	* @date 2014-1-14 下午3:45:52 
	* @throws
	 */
    public  static String formatSplitStr(String src,int  step, String  format) {
        if(isNull(src))  return  null;
        StringBuffer  sb=new StringBuffer();
        for (int i = 0; i < src.length(); i++) {
            if(i%step==0){
                int  end=(i+step)>src.length()?src.length():(i+step);
                sb.append(src.substring(i, end)+format);
            }
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
    
    /**
     * @Title: DateStringConvertFormat
     * @Description: 日期字符串转换格式
     * @param dateString 日期字符串
     * @param afterFormat 转换后的格式
     * @return 返回转换后的格式
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static String DateStringConvertFormat(String dateString,String afterFormat){
		
		String beforeFormat = "yyyy-MM-dd HH:mm:ss";
		int length = dateString.length(); // 字符的长度
		int h = dateString.split("-").length - 1; // 横线出现的次数
		int m = dateString.split(":").length - 1; // 冒号出现的次数
		int x = dateString.split("/").length - 1; // 斜线出现的次数
		
		if(h == 2 && length == 10)
			beforeFormat = "yyyy-MM-dd";
		else if(h == 2 && m == 2 && length == 19)
			beforeFormat = "yyyy-MM-dd HH:mm:ss";
		else if(x == 2 && length == 10)
			beforeFormat = "yyyy/MM/dd";
		else if(x == 2 && m == 2 && length == 19)
			beforeFormat = "yyyy/MM/dd HH:mm:ss";
		else if(length == 8)
			beforeFormat = "yyyyMMdd";
		else if(length == 14)
			beforeFormat = "yyyyMMddHHmmss";
		
		return DateStringConvertFormat(dateString,beforeFormat,afterFormat);
	}
	
	/**
     * @Title: DateStringConvertFormat
     * @Description: 日期字符串转换格式
     * @param dateString 日期字符串
     * @param beforeFormat 日期转换前的格式
     * @param afterFormat 转换后的格式
     * @return 返回转换后的格式
     * @throws Exception
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static String DateStringConvertFormat(String dateString,String beforeFormat,String afterFormat){
		Date date = null;
		DateFormat dateBeforeFormat = new SimpleDateFormat(beforeFormat);
		try {
			date = dateBeforeFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat afterDateFormat = new SimpleDateFormat(afterFormat);
		return afterDateFormat.format(date);
	}
}
