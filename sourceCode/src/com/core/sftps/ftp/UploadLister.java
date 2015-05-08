/**
 * 
 */
package com.core.sftps.ftp;

/**
 * 
 */
import org.apache.commons.fileupload.ProgressListener;


public class UploadLister implements ProgressListener{
	private Upload upload = null;
	
	public UploadLister(Upload upload){
		this.upload = upload;
	}
	
	public void update(long uploadSize,long totalSize,int items) {
		upload.setUploadSize(uploadSize);
		upload.setTotalSize(totalSize);
//		upload.setTotalSize(4553598*1024);
	}
}

