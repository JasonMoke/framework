/**
* @Title: SFTPTest.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.core.sftps.sftp   
* @Description:    
* @author guangchao    
* @date 2014-3-25 上午10:59:37   
* @version V1.0 
*/
package com.core.sftps.sftp;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.jcraft.jsch.ChannelSftp;
/**
 * @ClassName: SFTPTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author guangchao
 * @date 2014-3-25 上午10:59:37
 *
 */
public class SFTPTest {
	public SFTPChannel getSFTPChannel() {
        return new SFTPChannel();
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        SFTPTest test = new SFTPTest();

        Map<String, String> sftpDetails = new HashMap<String, String>();
        // 设置主机ip，端口，用户名，密码
        sftpDetails.put(SFTPConstants.SFTP_REQ_HOST, "192.168.25.175");
        sftpDetails.put(SFTPConstants.SFTP_REQ_USERNAME, "ty");
        sftpDetails.put(SFTPConstants.SFTP_REQ_PASSWORD, "123456 ");
        sftpDetails.put(SFTPConstants.SFTP_REQ_PORT, "22");
        
        String src = "C:\\Users\\guangchao\\Desktop\\untitled.png"; // 本地文件名
        String dst = "/home/ty/test/untitled.png"; // 目标文件名
              
        SFTPChannel channel = test.getSFTPChannel();
        ChannelSftp chSftp = channel.getChannel(sftpDetails, 60000);
        
        File file = new File(src);
        long fileSize = file.length();
        
        /**
         * 代码段1
        OutputStream out = chSftp.put(dst, new FileProgressMonitor(fileSize), ChannelSftp.OVERWRITE); // 使用OVERWRITE模式
        byte[] buff = new byte[1024 * 256]; // 设定每次传输的数据块大小为256KB
        int read;
        if (out != null) {
            System.out.println("Start to read input stream");
            InputStream is = new FileInputStream(src);
            do {
                read = is.read(buff, 0, buff.length);
                if (read > 0) {
                    out.write(buff, 0, read);
                }
                out.flush();
            } while (read >= 0);
            System.out.println("input stream read done.");
        }
        **/
        
        chSftp.put(src, dst, new FileProgressMonitor(fileSize), ChannelSftp.OVERWRITE); // 代码段2
        
        // chSftp.put(new FileInputStream(src), dst, new FileProgressMonitor(fileSize), ChannelSftp.OVERWRITE); // 代码段3
        
        chSftp.quit();
        channel.closeChannel();
    }
}
