/**
* @Title: EntityToolUtil.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.cnrsteel.util   
* @Description:  时间工具类
* @author guangchao    
* @date 2013-12-20 上午11:05:57   
* @version V1.0 
*/
package com.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 */
public class DateUtility
{

	public DateUtility()
	{
	}

	public static int compareDate(String sDate1, String sDate2)
	{
		DateFormat dateFormat = DateFormat.getDateInstance();
		Date date1 = null;
		Date date2 = null;
		try
		{
			date1 = dateFormat.parse(sDate1);
			date2 = dateFormat.parse(sDate2);
		}
		catch (ParseException e)
		{
			throw new IllegalArgumentException(e.getMessage());
		}
		long dif = 0L;
		if (date2.after(date1))
			dif = (date2.getTime() - date1.getTime()) / 1000L / 60L / 60L / 24L;
		else
			dif = (date1.getTime() - date2.getTime()) / 1000L / 60L / 60L / 24L;
		return (int)dif;
	}

	public static int compareTime(Long sTime1, Long sTime2)
	{
		long dif = 0L;
		if (sTime1.longValue() > sTime2.longValue())
			dif = (sTime1.longValue() - sTime2.longValue()) / 1000L / 60L / 60L / 24L;
		else
			dif = (sTime2.longValue() - sTime1.longValue()) / 1000L / 60L / 60L / 24L;
		return (int)dif;
	}

	public static String getCurrentDate()
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String s = df.format(new Date());
		return s;
	}

	public static String getCurrentDateTime()
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = df.format(new Date());
		return s;
	}

	public static String getCurrentTime()
	{
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		return df.format(new Date());
	}

	public static String getCurrentDay()
	{
		SimpleDateFormat df = new SimpleDateFormat("d");
		String day = df.format(new Date());
		return day;
	}

	public static String getCurrentMonth()
	{
		SimpleDateFormat df = new SimpleDateFormat("MM");
		String month = df.format(new Date());
		return month;
	}

	public static String getCurrentYear()
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		return df.format(new Date());
	}

	public static Long getLongDate(String strDate)
	{
		Date date = java.sql.Date.valueOf(strDate);
		Long lDate = new Long(date.getTime());
		return lDate;
	}

	public static Long getFindULongDate(String strDate)
	{
		return getDaysLater(strDate, 1);
	}

	@SuppressWarnings("deprecation")
	public static Long getDaysLater(String strDate, int days)
	{
		Date date = java.sql.Date.valueOf(strDate);
		date.setDate(date.getDate() + days);
		Long lDate = new Long(date.getTime());
		return lDate;
	}

	public static Long getLongDate(String strDate, int iType)
	{
		Long retDate = null;
		switch (iType)
		{
		default:
			break;

		case 0: // '\0'
			retDate = getLongDate(strDate);
			break;

		case 1: // '\001'
			retDate = new Long(Timestamp.valueOf(strDate).getTime());
			break;

		case 2: // '\002'
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddTHH:mm:ss");
			try
			{
				retDate = new Long(format.parse(strDate).getTime());
			}
			catch (ParseException e)
			{
				e.printStackTrace();
			}
			break;

		case 3: // '\003'
			SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
			try
			{
				retDate = new Long(format2.parse(strDate).getTime());
			}
			catch (ParseException e)
			{
				e.printStackTrace();
			}
			break;

		case 4: // '\004'
			SimpleDateFormat format3 = new SimpleDateFormat("YYYY-MM-DD hh:mm");
			try
			{
				retDate = new Long(format3.parse(strDate).getTime());
			}
			catch (ParseException e)
			{
				e.printStackTrace();
			}
			break;

		case 5: // '\005'
			SimpleDateFormat format4 = new SimpleDateFormat("yyyy-M-d");
			try
			{
				retDate = new Long(format4.parse(strDate).getTime());
			}
			catch (ParseException e)
			{
				e.printStackTrace();
			}
			break;

		case 6: // '\006'
			SimpleDateFormat format5 = new SimpleDateFormat("yyyy-MM");
			try
			{
				retDate = new Long(format5.parse(strDate).getTime());
			}
			catch (ParseException e)
			{
				e.printStackTrace();
			}
			break;

		case 7: // '\007'
			SimpleDateFormat format6 = new SimpleDateFormat("yyyy/MM/dd");
			try
			{
				retDate = new Long(format6.parse(strDate).getTime());
			}
			catch (ParseException e)
			{
				e.printStackTrace();
			}
			break;
		}
		return retDate;
	}

	public static String getStrYear(Long lDate)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		return df.format(lDate);
	}

	public static String getStrMonth(Long lDate)
	{
		SimpleDateFormat df = new SimpleDateFormat("MM");
		return df.format(lDate);
	}

	public static String getStrDay(Long lDate)
	{
		SimpleDateFormat df = new SimpleDateFormat("d");
		return df.format(lDate);
	}

	public static String getStrDate(Long lDate, int iType)
	{
		if (lDate == null || lDate.longValue() == 0L)
			return "";
		Date date = new Date(lDate.longValue());
		SimpleDateFormat simpleDateFormat = null;
		switch (iType)
		{
		case 0: // '\0'
			simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			break;

		case 1: // '\001'
			simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
			break;

		case 2: // '\002'
			simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			break;

		case 3: // '\003'
			simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			break;

		case 4: // '\004'
			simpleDateFormat = new SimpleDateFormat("yyyyMMddTHH:mm:ss");
			break;

		case 5: // '\005'
			simpleDateFormat = new SimpleDateFormat("MM-dd");
			break;

		case 6: // '\006'
			simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
			break;

		case 7: // '\007'
			simpleDateFormat = new SimpleDateFormat("HH:mm");
			break;

		case 8: // '\b'
			simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
			break;

		case 9: // '\t'
			simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			break;

		case 10: // '\n'
			simpleDateFormat = new SimpleDateFormat("yyyy-M-d");
			break;

		case 11: // '\013'
			simpleDateFormat = new SimpleDateFormat("yyyy-MM");
			break;
		}
		String strDate = simpleDateFormat.format(date);
		return strDate;
	}

	public static int getWeek(Date date)
	{
		if (date == null)
			return 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int weekNum = calendar.get(7) - 1;
		if (weekNum == 0)
			weekNum = 7;
		return weekNum;
	}

	public static String getWeekOfChinese(Date date)
	{
		String weekday[] = {
			"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天"
		};
		if (date == null)
			return "";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int weekNum = calendar.get(7) - 1;
		if (weekNum == 0)
			weekNum = 7;
		if (--weekNum >= 0 && weekNum <= 6)
			return weekday[weekNum];
		else
			return "";
	}

	public static int getWeek(Long datetime)
	{
		return getWeek(new Date(datetime.longValue()));
	}

	public static int getDaysOfMonth(int year, int month)
	{
		Calendar time = Calendar.getInstance();
		time.clear();
		time.set(1, year);
		time.set(2, month - 1);
		int day = time.getActualMaximum(5);
		return day;
	}

	public static String getJsDateString(String strDate, int iType)
	{
		Long time = getLongDate(strDate, iType);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-M-d");
		return df.format(time);
	}

	public static int getDaysOfM(int year, int month)
	{
		Calendar time = Calendar.getInstance();
		time.clear();
		time.set(1, year);
		time.set(2, month - 1);
		int day = time.getActualMaximum(5);
		return day;
	}

	public static void main(String args[])
	{
		System.out.println(getJsDateString("2009-02-04", 0));
	}
}
