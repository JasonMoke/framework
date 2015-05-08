/**
* @Title: demo.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.core.sftps.ftps   
* @Description:    
* @author guangchao    
* @date 2014-3-27 下午2:32:02   
* @version V1.0 
*/
package com.core.sftps.ftps;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyStore;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPSClient;
/**
 * @ClassName: demo
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author guangchao
 * @date 2014-3-27 下午2:32:02
 *
 */
	
	public class ConnectFTPS
	{
		private static final String trust_path = "F:/FTP/ftpserver-1.0.5/apache-ftpserver-1.0.5/res/kclient.keystore";
//		private static final String trust_path = "C:\\Users\\guangchao\\Desktop\\glyphicons_003_user.png";
		private static final String key_path = "F:/FTP/ftpserver-1.0.5/apache-ftpserver-1.0.5/res/tclient.keystore";
		private static final String trust_pw = "123456";
		private static FTPSClient ftpsClient;
		private static final String key_pw = "123456";
		private static final String serverIP = "210.73.60.249";
		private static final int serverPort = 990;
		private static final int defaultTimeout = 10000;
		private static final int soTimeout = 900000;
		private static final int dataTimeout = 5000;
		/**
		 * 测试连接FTP With SSL，以Apache FTPServer为例
		 * @param args
		 * @throws Exception
		 */
		public static void main(String[] args) throws Exception
		{
			if (!connect("active"))
			{
				connect("passive");
			}
			//FileInputStream fs = new FileInputStream(trust_path);
			//System.out.println("storeFile: " + ftpsClient.storeFile("test_file", fs));
			//fs.close();
			ftpsClient.disconnect();
		}
		 /**
		  * 登陆FTP
		  * @param active
		  * @return
		  * @throws Exception
		  */
		private static boolean connect(String active) throws Exception
		{
			ftpsClient = new FTPSClient(true);
			ftpsClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
//			ftpsClient.setKeyManager(getKeyManager());
			//ftpsClient.setTrustManager(getTrustManager());
			ftpsClient.setDefaultTimeout(defaultTimeout);
			ftpsClient.connect(serverIP, serverPort);
			System.out.println("已经连接FTP");
			ftpsClient.setSoTimeout(soTimeout);
			ftpsClient.getReplyCode();
			ftpsClient.execPBSZ(0);
			ftpsClient.execPROT("P");
			ftpsClient.login("framework", "0new0rld2014;");
			ftpsClient.changeWorkingDirectory("/");
			ftpsClient.setDataTimeout(dataTimeout);
			if (active.equalsIgnoreCase("active"))
			{
				ftpsClient.enterLocalActiveMode();
			} else
			{
				ftpsClient.enterLocalPassiveMode();
			}
			
			System.out.println("已经登陆FTP");
			return testLink();
		}
		/**
		 * 遍历FTP文件
		 * @return
		 */
		private static boolean testLink()
		{
			long t1 = System.currentTimeMillis();
			try
			{
//				System.out.println("List file length:" + ftpsClient.listFiles("/test/ecm"));
				FTPFile[] files = ftpsClient.listFiles("/test/ecm/9781629785950.mobi"); 
				for(int i=0;i<files.length;i++){
					System.out.println(files[i].getName());
				}
			} catch (IOException e)
			{
				System.out.println(e.getMessage());
				long t2 = System.currentTimeMillis();
				long t = (t2 - t1) / 1000;
				System.out.println("t: " + t);
				try
				{
					ftpsClient.disconnect();
				} catch (IOException e1)
				{
					e1.printStackTrace();
				}
				return false;
			}
			return true;
		}
		private static KeyManager getKeyManager() throws Exception
		{
			KeyStore key_ks = KeyStore.getInstance("JKS");
			key_ks.load(new FileInputStream(key_path), key_pw.toCharArray());
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(key_ks, key_pw.toCharArray());
			KeyManager[] km = kmf.getKeyManagers();
			System.out.println("km len: " + km.length);
			return km[0];
		}
		private static TrustManager getTrustManager() throws Exception
		{
			KeyStore trust_ks = KeyStore.getInstance("JKS");
			trust_ks.load(new FileInputStream(trust_path), trust_pw.toCharArray());
			TrustManagerFactory tf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tf.init(trust_ks);
			TrustManager[] tm = tf.getTrustManagers();
			System.out.println("tm len: " + tm.length);
			return tm[0];
		}
	}

