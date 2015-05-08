/**
* @Title: demo.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.framework.ftps   
* @Description:    
* @author guangchao    
* @date 2014-3-27 下午2:44:23   
* @version V1.0 
*/
package com.core.sftps.ftps;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.SocketException;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;

/**
 * @ClassName: demo
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author guangchao
 * @date 2014-3-27 下午2:44:23
 *
 */
public class demo {

	/**   
	 * @Title: main   
	 * @Description:    
	 * @param @param args    
	 * @return void      
	 * @author guangchao
	 * @date 2014-3-27 下午2:44:23 
	 * @throws   
	 */

	public static void main(String[] args) {
		boolean error = false;
		FTPSClient ftp = null;
		try {
			
			ftp = new FTPSClient("TLS",true);
			ftp.setAuthValue("TLS");
			ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
			
			int reply;
			
			ftp.connect("210.73.60.249",990);
			System.out.println("Connected to ftp.wpmikz.com.cn");
			System.out.print(ftp.getReplyString());
			
			// After connection attempt, you should check the reply code to verify
			// success.
			reply = ftp.getReplyCode();
			
			if(!FTPReply.isPositiveCompletion(reply))
			{
				ftp.disconnect();
				System.err.println("FTP server refused connection.");
				System.exit(1);
			}
			ftp.login("framework", "0new0rld2014;");
			//  ... // transfer files
			ftp.setBufferSize(1000);
			ftp.enterLocalPassiveMode();
			//ftp.setControlEncoding("GB2312");
			ftp.changeWorkingDirectory("/");
			ftp.changeWorkingDirectory("/SunShine Life/SU001SH/Interface/KZ IN/Response");
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			System.out.println("Remote system is " + ftp.getSystemName());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}
//	public static void main(String[] args) {
//		ByteArrayOutputStream Log = new ByteArrayOutputStream();
//		PrintStream printLog = new PrintStream(Log);
//		PrintWriter LogPrinter = new PrintWriter(Log);
//		try  {
//			org.apache.commons.net.ftp.FTPSClient FTPs = new org.apache.commons.net.ftp.FTPSClient(false);
//			FTPs.addProtocolCommandListener(new org.apache.commons.net.PrintCommandListener(LogPrinter));
//			FTPs.setDefaultTimeout(10000);
//			FTPs.connect("210.73.60.249"); // ftp://ftpstest.forus.com 100MB, bandwidth limited, no MkDir, supports FTP Active, and FTPS Active & Passive. Please delete files
//			FTPs.setSoTimeout(900000); // 15 minutes, a massive file transfer.
//			FTPs.getReplyCode();
//			FTPs.execPBSZ(0); // RFC2228 requires that the PBSZ subcommand be issued prior to the PROT subcommand. However, TLS/SSL handles blocking of data, so '0' is the only value accepted.
//			FTPs.execPROT("P"); // P(rivate) data channel, which needs certs if "Active". E and S: '536 Requested PROT level not supported by mechanism.'. C is default, but has clear text data channel - http://www.nabble.com/TLS-for-FTP-td6645485.html
//			FTPs.login("framework", "0new0rld2014;");
//			FTPs.changeWorkingDirectory("/");
////			   java.io.FileInputStream fileStream = new java.io.FileInputStream("JavaTest.java");
//			FTPs.setDataTimeout(5000);
//			FTPs.enterLocalPassiveMode(); // Active is the default, which very few clients can suppart in SSL (firewalls can't detect "PORT" command, and thus cant open/map local port). Active will also require keys/certs.
//			printLog.println("(call store file...)");
////			   FTPs.storeFile("JavaTest.java", fileStream);
////			   fileStream.close();
//			FTPs.disconnect();
//			System.out.println("");
//			System.out.println("FTP COMMAND LOG:");
//			System.out.println(Log.toString());
//		} catch(Exception e) {
//			System.out.println(e);
//		}
//		
//	}
//	public static void main(String[] args) {
//		boolean canConnection = false;  
//		boolean isConnectionSuccess = false;  
//		FTPSClient ftpsClient = null;  
//		
//		try   
//		{  
//			ftpsClient = new FTPSClient("TLS",true);  
//			ftpsClient.setAuthValue("TLS");
//			//ftpsClient.setConnectTimeout(300000);   //连接超时为5分钟  
//			ftpsClient.setDataTimeout(3600000);  
//			
//			ftpsClient.connect("210.73.60.249");  
//			
////          loger.info(traceStr+"Connected to " + serverIP + ".");  
//			
//			int reply = ftpsClient.getReplyCode();  
//			
//			canConnection = FTPReply.isPositiveCompletion(reply);  //可以判断是否可以连接  
////          loger.info(""+"是否可以连接："+canConnection);  
//			
//			if(canConnection)  
//			{  
//				isConnectionSuccess = ftpsClient.login("framework", "0new0rld2014;");
//				if(!isConnectionSuccess)  
//				{  
//					System.out.println("服务器连接错误,请重新配置!");  
//				}  
//				else  
//				{  
//					System.out.println("连接服务器成功......");  
//				}  
//				
//			}  
//			
//		} catch (SocketException e) {  
//			e.printStackTrace();  
//			
//		} catch (IOException e) {  
//			e.printStackTrace();  
//			
//		} 
//		
//	}

}
