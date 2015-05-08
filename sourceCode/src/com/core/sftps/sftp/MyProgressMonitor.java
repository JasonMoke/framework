/**
* @Title: MyProgressMonitor.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.core.sftps.sftp   
* @Description:    
* @author guangchao    
* @date 2014-3-25 上午11:00:32   
* @version V1.0 
*/
package com.core.sftps.sftp;
import com.jcraft.jsch.SftpProgressMonitor;
/**
 * @ClassName: MyProgressMonitor
 * @Description:监控传输进度JSch每次传输一个数据块，就会调用count方法来实现主动进度通知。
 * @author guangchao
 * @date 2014-3-25 上午11:00:32
 *
 */
public class MyProgressMonitor implements SftpProgressMonitor {
	private long transfered;

    @Override
    public boolean count(long count) {
        transfered = transfered + count;
        System.out.println("Currently transferred total size: " + transfered + " bytes");
        return true;
    }

    @Override
    public void end() {
        System.out.println("Transferring done.");
    }

    @Override
    public void init(int op, String src, String dest, long max) {
        System.out.println("Transferring begin.");
    }
}
