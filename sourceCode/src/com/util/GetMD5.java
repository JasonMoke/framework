package com.util;

import java.io.File;
import java.io.FileInputStream;

/**
 *  
 */


import java.io.IOException;

import java.security.MessageDigest;

 
/**
 * 
* @ClassName: GetMD5
* @Description: 
* @author gaoguangchao
* @date 2015年3月6日 下午2:24:55
*
 */
public class GetMD5 {

 

   /**
    * 对文件全文生成MD5摘要
    * @param file要加密的文件
    * @return MD5摘要码
    */
   static char hexdigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

   public static String getMD5(File file) {

      FileInputStream fis = null;

      try {

        MessageDigest md = MessageDigest.getInstance("MD5");

        fis = new FileInputStream(file);

        byte[] buffer = new byte[2048];

        int length = -1;

        while ((length = fis.read(buffer)) != -1) {

           md.update(buffer, 0, length);

        }
        
        byte[] b = md.digest();

        return byteToHexString(b);

      } catch (Exception ex) {

	        ex.printStackTrace();
	
	        return null;

      } finally {

        try {

           fis.close();

        } catch (IOException ex) {

           ex.printStackTrace();

        }

      }

   }

   /**
    * 把byte[]数组转换成十六进制字符串表示形式
    * @param tmp    要转换的byte[]
    * @return 十六进制字符串表示形式
    */

   private static String byteToHexString(byte[] tmp) {

      String s;

      // 用字节表示就是 16 个字节

      char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，

      // 所以表示成 16 进制需要 32 个字符

      int k = 0; // 表示转换结果中对应的字符位置

      for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节

        // 转换成 16 进制字符的转换

        byte byte0 = tmp[i]; // 取第 i 个字节

        str[k++] = hexdigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,

        // >>> 为逻辑右移，将符号位一起右移

        str[k++] = hexdigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换

      }

      s = new String(str); // 换后的结果转换为字符串

      return s;

   }

//   public static void main(String arg[]) {
//	   long now1 = new Date().getTime();
//	   
//      System.out.println(getMD5(new File("D:/Media/卑鄙的我[QMV]QMV高清.qmv")));
//      long now2 = new Date().getTime();
//      System.out.println("**************"+(now2-now1));
//
//   }

}
