
package com.core.database.databean;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.util.UtilDateTime;

import oracle.sql.TIMESTAMP;


@SuppressWarnings("rawtypes")
public class Data extends HashMap
{

	private static final long serialVersionUID = 0x7db06895168de43dL;
	private static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static String DATE_FORMAT = "yyyy-MM-dd";
	private String entityName;
	private Map dataType;

	public Data()
	{
		dataType = new HashMap();
		entityName = null;
	}


	public Data(Map dataMap)
	{
		this();
	}


	public void add(String key, Object value)
	{
		put(key.toUpperCase(), value);
	}

	public void addData(Map datas)
	{
		putAll(keyUpperCase(datas));
	}

	public void addDataString(Map datas)
	{
		putAll(keyUpperCaseString(datas));
	}

	private Map keyUpperCaseString(Map data)
	{
		Object keys[] = data.keySet().toArray();
		int n = keys.length;
		for (int i = 0; i < n; i++)
		{
			String v = (String)data.get(keys[i]);
			data.remove(keys[i]);
			data.put(((String)keys[i]).toUpperCase(), v);
		}

		return data;
	}

	public void addData(String fieldName, Object fieldValue)
	{
		put(fieldName.toUpperCase(), fieldValue);
	}

	public void addString(String key, String value)
	{
		add(key, value);
	}

	private Object checkNull(Object obj, String defaultString)
	{
		return obj != null ? obj : defaultString;
	}

	public Object[] fields()
	{
		return keySet().toArray();
	}

	public Object get(String fieldName)
	{
		fieldName = fieldName.toUpperCase();
		return super.get(fieldName);
	}

	public Object get(Object key)
	{
		return get(((String)key).toUpperCase());
	}

	public Object get(String fieldName, String defaultString)
	{
		return checkNull(super.get(fieldName), defaultString);
	}

	public byte[] getBlob(Object key)
		throws IOException, SQLException
	{
		Object b = super.get(key);
		if (b == null)
			return null;
		else
			return (byte[])b;
	}

	public InputStream getStream(Object key)
		throws IOException, SQLException
	{
		Object b = super.get(key);
		if (b == null)
			return null;
		else
			return ((Blob)b).getBinaryStream();
	}

	private byte[] blobToBytes(Blob blob)
	{
		BufferedInputStream is;
		Exception exception;
		if (blob == null)
			return null;
		is = null;
		byte abyte0[];
		try
		{
			is = new BufferedInputStream(blob.getBinaryStream());
			byte bytes[] = new byte[(int)blob.length()];
			int len = bytes.length;
			int offset = 0;
			for (int read = 0; offset < len && (read = is.read(bytes, offset, len - offset)) >= 0; offset += read);
			abyte0 = bytes;
		}
		catch (Exception e)
		{
			try
			{
				is.close();
				is = null;
			}
			catch (IOException e1)
			{
				return null;
			}
			return null;
		}
		finally { }
		try
		{
			is.close();
			is = null;
		}
		catch (IOException e)
		{
			return null;
		}
		return abyte0;
	}

	public String getClobString(Object key)
		throws SQLException, IOException
	{
		return getString(key);
	}

	public Map getData()
	{
		return this;
	}

	public String getDate(String fieldName)
	{
		return getDate(fieldName, DATE_FORMAT);
	}

	public String getDate(String fieldName, String formatString)
	{
		Object v;
		v = get(fieldName);
		if (v == null || "".equals(v))
			return "";
		if (formatString == null)
			formatString = DATE_FORMAT;
		SimpleDateFormat format;
		format = new SimpleDateFormat(formatString);
		return format.format(v);
	}

	public String getDateStringForLong(String fieldName)
	{
		long k;
		k = getLong(fieldName);
		if (k == 0L)
			return "";
		if (!"".equals(fieldName))
			return UtilDateTime.toDateString(new Date(k));
		else
			return "";
	}

	public String getDateTime(String fieldName)
	{
		return getDateTime(fieldName, null);
	}

	public String getDateTime(String fieldName, String formatString)
	{
		Object v;
		if (formatString == null)
			formatString = DATETIME_FORMAT;
		v = get(fieldName);
		if (v instanceof TIMESTAMP)
		{
			String strDate = "";
			try
			{
				Date TIMESTAMP = ((TIMESTAMP)v).dateValue();
				SimpleDateFormat format = new SimpleDateFormat(formatString);
				strDate = format.format(TIMESTAMP);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			return strDate;
		}
		if (v == null || "".equals(v))
			return "";
		SimpleDateFormat format = new SimpleDateFormat(DATETIME_FORMAT);
		return format.format(v);
	}

	public String getDateTimeStringForLong(String fieldName)
	{
		long k;
		k = getLong(fieldName);
		if (k == 0L)
			return "";
		if (!"".equals(fieldName))
			return UtilDateTime.toDateTimeString(new Date(k));
		else
			return "";
	}

	public double getDouble(String fieldName)
	{
		Object v = get(fieldName);
		if (v == null || "".equals(v))
			return 0.0D;
		if (v instanceof BigDecimal)
			return ((BigDecimal)v).doubleValue();
		else
			return Double.parseDouble(String.valueOf(v));
	}

	public String getEntityName()
	{
		return entityName;
	}

	public float getFloat(String fieldName)
	{
		Object v = get(fieldName);
		if (v == null || "".equals(v))
			return 0.0F;
		if (v instanceof BigDecimal)
			return ((BigDecimal)v).floatValue();
		else
			return Float.parseFloat((String)v);
	}

	public int getInt(String fieldName)
	{
		Object v;
		v = get(fieldName);
		if (v == null || "".equals(v))
			return 0;
		if (v instanceof BigDecimal)
			return ((BigDecimal)v).intValue();
		if (v instanceof Integer)
			return ((Integer)v).intValue();
		return Integer.parseInt((String)v, 10);
	}

	public long getLong(String fieldName)
	{
		Object v = get(fieldName);
		if (v == null || "".equals(v))
			return 0L;
		if (v instanceof BigDecimal)
			return ((BigDecimal)v).longValue();
		else
			return Long.parseLong((String)v);
	}


	public String getString(Object fieldName)
	{
		Object v = get(fieldName);
		if (v == null)
			return null;
		if (v instanceof String)
			return (String)v;
		if (v instanceof TIMESTAMP)
			return ((TIMESTAMP)v).stringValue();
		if (v instanceof Timestamp)
			return (new SimpleDateFormat(DATETIME_FORMAT)).format(v);
		if (v instanceof BigDecimal)
			return ((BigDecimal)v).toString();
		if (v instanceof java.sql.Date)
			return UtilDateTime.toDateTimeString((java.sql.Date)v);
		if (v instanceof Time)
			return UtilDateTime.toDateTimeString((Time)v);
		if (v instanceof Long)
			return ((Long)v).toString();
		if (v instanceof Integer)
			return ((Integer)v).toString();
		if (v instanceof Boolean)
			return ((Boolean)v).toString();
		if (v instanceof Double)
			return ((Double)v).toString();
		else
			return v.toString();
	}

	public String getString(Object fieldName, String defaultValue)
	{
		String v = getString(fieldName);
		if (v == null)
			return defaultValue;
		else
			return v;
	}

	public String getType(String fieldName)
	{
		String dt = (String)dataType.get(fieldName.toUpperCase());
		return dt;
	}

	public Map getTypes()
	{
		return dataType;
	}

	public boolean hasLob()
	{
		boolean b = dataType.containsValue("oracle.sql.BLOB");
		if (!b)
			b = dataType.containsValue("oracle.sql.CLOB");
		if (!b)
			b = dataType.containsValue("java.sql.Clob");
		if (!b)
			b = dataType.containsValue("java.sql.Blob");
		return b;
	}

	public boolean hasType(String type)
	{
		return dataType.containsValue(type);
	}

	private Map keyUpperCase(Map data)
	{
		Object keys[] = data.keySet().toArray();
		int n = keys.length;
		for (int i = 0; i < n; i++)
		{
			Object v = data.get(keys[i]);
			data.remove(keys[i]);
			data.put(((String)keys[i]).toUpperCase(), v);
		}

		return data;
	}

	public void removeData(List fieldNames)
	{
		for (int i = 0; i < fieldNames.size(); i++)
			remove(fieldNames.get(i));

	}

	public void removeData(String fieldName)
	{
		remove(fieldName.toUpperCase());
	}

	public void setData(Map map)
	{
		clear();
		putAll(keyUpperCase(map));
	}

	public void setEntityName(String entityName)
	{
		if (entityName != null)
			this.entityName = entityName.toUpperCase();
	}


	public void setType(Map map)
	{
		dataType.putAll(map);
	}

	public void setType(String fieldName, String type)
	{
		dataType.put(fieldName.toUpperCase(), type);
	}

	public boolean toDateLong(String fieldName)
	{
		try
		{
			String o = getString(fieldName);
			put(fieldName.toUpperCase(), new Long(UtilDateTime.toDate(o, "00:00:00").getTime()));
		}
		catch (Exception e)
		{
			return false;
		}
		return true;
	}

	public boolean toDateTimeLong(String fieldName)
	{
		try
		{
			String o = getString(fieldName);
			put(fieldName.toUpperCase(), new Long(UtilDateTime.toDate(o).getTime()));
		}
		catch (Exception e)
		{
			return false;
		}
		return true;
	}

}
