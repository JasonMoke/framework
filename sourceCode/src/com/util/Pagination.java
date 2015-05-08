
package com.util;

import java.util.List;

/**
 * 
* @ClassName: Pagination
* @Description: 分页
* @author guangchao
* @date 2013-12-25 上午10:26:26
*
* @param <T>
 */
public class Pagination<T> {

	private List<T> list;
	private int pageNum = 10;// 页码显示条目数
	// 总共有多少数据
	private int total;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
