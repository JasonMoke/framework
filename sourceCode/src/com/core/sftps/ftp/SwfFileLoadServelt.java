package com.core.sftps.ftp;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

import com.core.util.EBookFIleInfo;
import com.core.util.EBookFileInfoHelper;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.util.GetConfig;
import com.util.GetMD5;

/**
 * @author huangshuang
 * @version 2011-5-1
 */
public class SwfFileLoadServelt extends HttpServlet {

	private static final long serialVersionUID = -3096800116651263134L;

	private String fileSizeLimit;
	private File oldFile;

	public void init(ServletConfig config) throws ServletException {
		this.fileSizeLimit = config.getInitParameter("fileSizeLimit");
	}

	public void destroy() {
		this.fileSizeLimit = null;
		super.destroy();
	}

	class MyFileRenamePolicy implements FileRenamePolicy {
		public File rename(File file) {
			oldFile = file;
			String fileSaveName = StringUtils.join(new String[] { java.util.UUID.randomUUID().toString(), ".",
					FilenameUtils.getExtension(file.getName()) });
			File result = new File(file.getParentFile(), fileSaveName);
			return result;
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int wideth = 0;
		int height = 0;
		String md5 = "";
//		String flag = request.getParameter("flag");
		// 设置临时文件存储位置
		String base =  GetConfig.getFileoutputpath();
		String nowDateFoler = new SimpleDateFormat("yyyyMMdd").format(new Date());
		File file = new File(base+nowDateFoler);//创建文件夹
		if(!file.exists()){
			file.mkdirs();
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置内存缓冲区，超过后写入临时文件
		factory.setSizeThreshold(10240000); 
		//factory.setRepository(file);
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置单个文件的最大上传值
		long maxSize = new Long(107374182400l);
		upload.setFileSizeMax(maxSize);//最大10G
		// 设置整个request的最大值
		upload.setSizeMax(107374182400l);
		upload.setHeaderEncoding("UTF-8");
		Upload uploadEntity = new Upload();
		UploadLister lister = new UploadLister(uploadEntity);
		upload.setProgressListener(lister);
		request.getSession().setAttribute("upload", uploadEntity);
		StringBuffer tempFilePath = new StringBuffer();
		FileItem item = null;
		String fileName = null;
		String filetype = null;
		String fileFormat = null;
		String unit = null;
		long fileSizeBytes = 0;
		int fileSize = 0;
		try {
			List<?> items = upload.parseRequest(request);
			String filenameUUID = UUID.randomUUID().toString();  //服务器创建的文件名称
//			ContinueFTP myFtp = new ContinueFTP();
//			myFtp.connect("192.168.25.253", 21, "formax", "123456"); 
			for (int i = 0 ;i < items.size(); i++){
				item = (FileItem) items.get(i);
				// 保存文件
				if (!item.isFormField() && item.getName().length() > 0) {
					fileName = item.getName();
					int m =fileName.lastIndexOf("."); 
					fileName = URLEncoder.encode(fileName, "UTF-8");
					filetype = item.getName().substring(m);
					filetype = filetype.toUpperCase();
					fileSizeBytes = item.getSize();
					if(fileSizeBytes>1024*1024){
						unit = "MB";
						fileSize = (int)(fileSizeBytes / 1024 / 1024);
					}else {
						unit = "KB";
						fileSize = (int)(fileSizeBytes / 1024 );
					}
//					上传到FTP
//					InputStream input = item.getInputStream();  
//					myFtp.upload(input, "/Upload/test/"+fileName);
					tempFilePath.append(base).append(File.separator).append(nowDateFoler).append(File.separator).append(filenameUUID).append(filetype);
					item.write(new File(tempFilePath.toString()));
					
				}
			}
//			  myFtp.disconnect(); 
			File _file = new File(tempFilePath.toString()); //读入文件
			//获取MD5值
			md5 = GetMD5.getMD5(_file);
			//获取图片文件分辨率
			if("*.BMP;*.JPG;*.JPEG;*.PNG;*.GIF;*.PCX;*.TIFF;*.TGA;*.EXIF;*.FPX;*.SVG;*.PSD;*.CDR;*.PCD;*.DXF;*.UFO;*.EPS;*.HDRI;*.AI;*.RAW;".contains(filetype)){
				Image src = javax.imageio.ImageIO.read(_file); //构造Image对象
				 wideth=src.getWidth(null); //得到源图宽
				 height=src.getHeight(null); //得到源图长
			}
//			获取资源文件 格式
			EBookFIleInfo eBookFIleInfo = new EBookFIleInfo();
			eBookFIleInfo = EBookFileInfoHelper.getEBookFileInfo(tempFilePath.toString());
			fileFormat = eBookFIleInfo.getFormatName().toUpperCase();
			StringBuffer sb = new StringBuffer();
			sb.append("#").append(nowDateFoler).append( File.separator ).append(filenameUUID+filetype);
			sb.append("#").append(wideth).append("#").
			append(height).append("#").append(md5).append("#").append(fileName).append("#").append(fileSize).
			append(unit).append("#").append(filetype).append("#").append(fileSizeBytes).append("#").append(fileFormat).append("#");
			response.getWriter().print(sb.toString());//返回临时文件名到前台和文件大小
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public String getFileSizeLimit() {
		return fileSizeLimit;
	}

	public void setFileSizeLimit(String fileSizeLimit) {
		this.fileSizeLimit = fileSizeLimit;
	}

}
