package com.core.sftps.ftp;

/**
 * 
* @ClassName: Upload
* @Description: 上传upload实体类
* @author guangchao
* @date 2014-1-20 上午10:41:20
*
 */
	public class Upload {
		private long totalSize;										
		private long startTime = System.currentTimeMillis();		
		private long uploadSize;									
		public long getTotalSize() {
			return totalSize;
		}
		public void setTotalSize(long totalSize) {
			this.totalSize = totalSize;
		}
		public long getStartTime() {
			return startTime;
		}
		public void setStartTime(long startTime) {
			this.startTime = startTime;
		}
		public long getUploadSize() {
			return uploadSize;
		}
		public void setUploadSize(long uploadSize) {
			this.uploadSize = uploadSize;
		}
	}
