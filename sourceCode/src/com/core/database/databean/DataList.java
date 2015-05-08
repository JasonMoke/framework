
package com.core.database.databean;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DataList extends ArrayList
{

	private static final long serialVersionUID = 0x8e10686031f27c97L;
	private ArrayList<Object> message;
	private int nowPage;
	private int pageSize;
	private int dataTotal;
	private String sql;

	public String getSql()
	{
		return sql;
	}

	void setSql(String sql)
	{
		this.sql = sql;
	}

	public DataList()
	{
		nowPage = 0;
		pageSize = 0;
		dataTotal = 0;
		message = new ArrayList<Object>();
	}

	public DataList(int index)
	{
		super(index);
		nowPage = 0;
		pageSize = 0;
		dataTotal = 0;
		message = new ArrayList<Object>();
	}

	public DataList(String urlStr)
	{
		nowPage = 0;
		pageSize = 0;
		dataTotal = 0;
		message = new ArrayList<Object>();
	}

	public DataList(URL url)
	{
		nowPage = 0;
		pageSize = 0;
		dataTotal = 0;
		message = new ArrayList<Object>();
	}

	public DataList(String xml, boolean b)
	{
		nowPage = 0;
		pageSize = 0;
		dataTotal = 0;
		message = new ArrayList<Object>();
	}

	public boolean addAll(DataList dataList)
	{
		super.addAll(dataList);
		initMessage();
		List<Object> l = dataList.getMessage();
		if (l != null)
			message.addAll(l);
		return true;
	}

	public void addMessage(Object message)
	{
		initMessage();
		this.message.add(message);
	}

	public List<Object> getMessage()
	{
		return message;
	}

	private void initMessage()
	{
		if (message == null)
			message = new ArrayList<Object>();
	}

	public void clearMessage()
	{
		initMessage();
		message.clear();
	}

	public void removeMessage(Object o)
	{
		initMessage();
		message.remove(o);
	}

	public void removeMessage(int index)
	{
		initMessage();
		message.remove(index);
	}

	public boolean add(Data data)
	{
		return super.add(data);
	}

	public boolean set(Data data)
	{
		clear();
		return super.add(data);
	}

	public Object remove(int index)
	{
		return super.remove(index);
	}

	public boolean remove(Object o)
	{
		return super.remove(o);
	}

	public Data getData(int i)
	{
		return (Data)get(i);
	}

	public int getNowPage()
	{
		if (pageSize != 0 && nowPage == 0)
			return -1;
		if (pageSize == 0)
			return 0;
		else
			return nowPage;
	}

	void setNowPage(int nowPage)
	{
		this.nowPage = nowPage;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public int getDataTotal()
	{
		if (pageSize != 0)
			return dataTotal;
		else
			return size();
	}

	void setDataTotal(int dataTotal)
	{
		this.dataTotal = dataTotal;
	}

	public int getPageTotal()
	{
		if (pageSize == 0)
			return 1;
		int pageTotal = dataTotal / pageSize;
		int y = dataTotal % pageSize;
		if (y > 0)
			pageTotal++;
		return pageTotal;
	}

	public int getPagetotal()
	{
		int pageTotal = 1;
		int dataTotal = getDataTotal();
		int nowPage = getNowPage();
		if (nowPage < 1)
			return 1;
		if (nowPage > 0 && pageSize > 0 && dataTotal > 0)
		{
			pageTotal = dataTotal / pageSize;
			if (dataTotal % pageSize > 0)
				pageTotal++;
		}
		return pageTotal;
	}
}
