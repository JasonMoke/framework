package com.core.sftps.ftp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
* @ClassName: AjaxServlet
* @Description: 获取上传进度
* @author guangchao
* @date 2014-1-20 上午10:42:46
*
 */
public class AjaxServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4637208022273753323L;

	/**
	 * Constructor of the object.
	 */
	public AjaxServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragrma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Upload upload = null;
		upload = (Upload)request.getSession().getAttribute("upload");
		if(null == upload)
			return;
		String str = "";
			long currentTime = System.currentTimeMillis();
			long fileTotalSize = upload.getTotalSize();
			long fileUploadSize = upload.getUploadSize();
			long time = (currentTime - upload.getStartTime()) / 1000 + 1;
			long speed = (long)fileUploadSize / 1024 / time;
			int percent =(int)((double)fileUploadSize / (double)fileTotalSize * 100);
			int b = 0;
			int total = 0;
			String unit = "B";
//			如果大于1M则以M为单位
			if(fileTotalSize>1024*1024){
				unit = "MB";
				total = (int)(fileTotalSize / 1024 / 1024);
			}else {
				unit = "KB";
				total = (int)(fileTotalSize / 1024 );
			}
//			如果大于1M则以M为单位
			if(fileUploadSize>1024*1024){
				b = (int) (fileUploadSize / 1024 / 1024);
			}else{
				b = (int) (fileUploadSize / 1024);
				
			}
			String shenYustr = "";
			if(speed!=0){
				int shenYu =  (int)((fileTotalSize - fileUploadSize) / 1024 / speed);
				shenYustr = Integer.toString(shenYu);
			}
			str = time+"-"+speed+"-"+percent+"-"+b+unit+"-"+total+unit+"-"+shenYustr;
		out.print(str);
		out.flush();
		out.close();
	}
	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
