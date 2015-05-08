// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   UtilDateTime.java

package com.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class UtilDateTime
{

	public static final String datePartition = "-";
	@SuppressWarnings("unused")
	private static int imm = 0;
	@SuppressWarnings("unused")
	private static long countTime = 0L;

	public UtilDateTime()
	{
	}

	public static String nowMillisTime()
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String s = df.format(Long.valueOf(System.currentTimeMillis()));
		return s;
	}

	public static String nowDateTimeString()
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date());
	}

	public static String nowDateString()
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(new Date());
	}

	public static String getNextDay(String time)
	{
		int year = Integer.parseInt(time.substring(0, time.indexOf("-")));
		int month = Integer.parseInt(time.substring(time.indexOf("-") + 1, time.lastIndexOf("-")));
		int day = Integer.parseInt(time.substring(time.lastIndexOf("-") + 1));
		SimpleDateFormat f2 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar nextDate = Calendar.getInstance();
		nextDate.set(year, month - 1, day);
		nextDate.add(5, 1);
		Date date = nextDate.getTime();
		String nextdaystr = f2.format(date);
		return nextdaystr;
	}

	public static String formatCNYear(Date d)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy��");
		return df.format(d);
	}

	public static String formatCNMonth(Date d)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy��MM��");
		return df.format(d);
	}

	public static String formatCNDate(Date d)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy��MM��dd��");
		return df.format(d);
	}

	public static String formatCNTime(Date d)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy��MM��dd�� HHʱmm��ss��");
		return df.format(d);
	}

	public static String formatDate(Date d, String format)
	{
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(d);
	}

	public static Timestamp nowTimestamp()
	{
		return new Timestamp(System.currentTimeMillis());
	}

	public static Date nowDate()
	{
		return new Date();
	}

	public static Date nowSqlDate()
	{
		return new java.sql.Date(nowDate().getTime());
	}

	public static Timestamp getDayStart(Timestamp stamp)
	{
		return getDayStart(stamp, 0);
	}

	public static Timestamp getDayStart(Timestamp stamp, int daysLater)
	{
		Calendar tempCal = Calendar.getInstance();
		tempCal.setTime(new Date(stamp.getTime()));
		tempCal.set(tempCal.get(1), tempCal.get(2), tempCal.get(5), 0, 0, 0);
		tempCal.add(5, daysLater);
		return new Timestamp(tempCal.getTime().getTime());
	}

	public static Timestamp getNextDayStart(Timestamp stamp)
	{
		return getDayStart(stamp, 1);
	}

	public static Timestamp getDayEnd(Timestamp stamp)
	{
		return getDayEnd(stamp, 0);
	}

	public static Timestamp getDayEnd(Timestamp stamp, int daysLater)
	{
		Calendar tempCal = Calendar.getInstance();
		tempCal.setTime(new Date(stamp.getTime()));
		tempCal.set(tempCal.get(1), tempCal.get(2), tempCal.get(5), 23, 59, 59);
		tempCal.add(5, daysLater);
		return new Timestamp(tempCal.getTime().getTime());
	}

	public static java.sql.Date toSqlDate(String date)
	{
		Date newDate = toDate(date, "00:00:00");
		if (newDate != null)
			return new java.sql.Date(newDate.getTime());
		else
			return null;
	}

	public static java.sql.Date toSqlDateForDateTime(String dateTime)
	{
		Date newDate = toDate(dateTime);
		if (newDate != null)
			return new java.sql.Date(newDate.getTime());
		else
			return null;
	}

	public static java.sql.Date toSqlDate(String monthStr, String dayStr, String yearStr)
	{
		Date newDate = toDate(monthStr, dayStr, yearStr, "0", "0", "0");
		if (newDate != null)
			return new java.sql.Date(newDate.getTime());
		else
			return null;
	}

	public static java.sql.Date toSqlDate(int month, int day, int year)
	{
		Date newDate = toDate(month, day, year, 0, 0, 0);
		if (newDate != null)
			return new java.sql.Date(newDate.getTime());
		else
			return null;
	}

	public static Time toSqlTime(String time)
	{
		Date newDate = toDate("1970-1-1", time);
		if (newDate != null)
			return new Time(newDate.getTime());
		else
			return null;
	}

	public static Time toSqlTime(String hourStr, String minuteStr, String secondStr)
	{
		Date newDate = toDate("0", "0", "0", hourStr, minuteStr, secondStr);
		if (newDate != null)
			return new Time(newDate.getTime());
		else
			return null;
	}

	public static Time toSqlTime(int hour, int minute, int second)
	{
		Date newDate = toDate(0, 0, 0, hour, minute, second);
		if (newDate != null)
			return new Time(newDate.getTime());
		else
			return null;
	}

	public static Timestamp toTimestamp(String dateTime)
	{
		Date newDate = toDate(dateTime);
		if (newDate != null)
			return new Timestamp(newDate.getTime());
		else
			return null;
	}

	public static Timestamp toTimestamp(String date, String time)
	{
		if (date == null || time == null)
			return null;
		else
			return toTimestamp((new StringBuilder(String.valueOf(date))).append(" ").append(time).toString());
	}

	public static Timestamp toTimestamp(String monthStr, String dayStr, String yearStr, String hourStr, String minuteStr, String secondStr)
	{
		Date newDate = toDate(monthStr, dayStr, yearStr, hourStr, minuteStr, secondStr);
		if (newDate != null)
			return new Timestamp(newDate.getTime());
		else
			return null;
	}

	public static Timestamp toTimestamp(int month, int day, int year, int hour, int minute, int second)
	{
		Date newDate = toDate(month, day, year, hour, minute, second);
		if (newDate != null)
			return new Timestamp(newDate.getTime());
		else
			return null;
	}

	public static Date toDateFormat(String dateTime, String format) throws ParseException
	{
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.parse(dateTime);
	}

	public static Date toDate(String dateTime)
	{
		try {
			return new Date(DateFormat.getDateTimeInstance(2, 2, Locale.CHINA).parse(dateTime).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date toDate(String date, String time)
	{
		if (date == null || time == null)
			return null;
		else
			return toDate((new StringBuilder(String.valueOf(date))).append(" ").append(time).toString());
	}

	public static Date toDate(String monthStr, String dayStr, String yearStr, String hourStr, String minuteStr, String secondStr)
	{
		int month;
		int day;
		int year;
		int hour;
		int minute;
		int second;
		try
		{
			month = Integer.parseInt(monthStr);
			day = Integer.parseInt(dayStr);
			year = Integer.parseInt(yearStr);
			hour = Integer.parseInt(hourStr);
			minute = Integer.parseInt(minuteStr);
			second = Integer.parseInt(secondStr);
		}
		catch (Exception e)
		{
			return null;
		}
		return toDate(month, day, year, hour, minute, second);
	}

	public static Date toDate(int month, int day, int year, int hour, int minute, int second)
	{
		Calendar calendar = Calendar.getInstance();
		try
		{
			calendar.set(year, month - 1, day, hour, minute, second);
		}
		catch (Exception e)
		{
			return null;
		}
		return new Date(calendar.getTime().getTime());
	}

	public static String toDateString(Date date)
	{
		if (date == null)
			return "";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.get(2) + 1;
		int day = calendar.get(5);
		int year = calendar.get(1);
		String monthStr;
		if (month < 10)
			monthStr = (new StringBuilder("0")).append(month).toString();
		else
			monthStr = (new StringBuilder()).append(month).toString();
		String dayStr;
		if (day < 10)
			dayStr = (new StringBuilder("0")).append(day).toString();
		else
			dayStr = (new StringBuilder()).append(day).toString();
		String yearStr = (new StringBuilder()).append(year).toString();
		return (new StringBuilder(String.valueOf(yearStr))).append("-").append(monthStr).append("-").append(dayStr).toString();
	}

	public static String toTimeString(Date date)
	{
		if (date == null)
		{
			return "";
		} else
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return toTimeString(calendar.get(11), calendar.get(12), calendar.get(13));
		}
	}

	public static String toTimeString(int hour, int minute, int second)
	{
		String hourStr;
		if (hour < 10)
			hourStr = (new StringBuilder("0")).append(hour).toString();
		else
			hourStr = (new StringBuilder()).append(hour).toString();
		String minuteStr;
		if (minute < 10)
			minuteStr = (new StringBuilder("0")).append(minute).toString();
		else
			minuteStr = (new StringBuilder()).append(minute).toString();
		String secondStr;
		if (second < 10)
			secondStr = (new StringBuilder("0")).append(second).toString();
		else
			secondStr = (new StringBuilder()).append(second).toString();
		if (second == 0)
			return (new StringBuilder(String.valueOf(hourStr))).append(":").append(minuteStr).append(":").append(secondStr).toString();
		else
			return (new StringBuilder(String.valueOf(hourStr))).append(":").append(minuteStr).append(":").append(secondStr).toString();
	}

	public static String toDateTimeString(Date date)
	{
		if (date == null)
			return "";
		String dateString = toDateString(date);
		String timeString = toTimeString(date);
		if (dateString != null && timeString != null)
			return (new StringBuilder(String.valueOf(dateString))).append(" ").append(timeString).toString();
		else
			return "";
	}

	public static String toDateTimeString(java.sql.Date date)
	{
		if (date == null)
			return "";
		else
			return toDateTimeString(new Date(date.getTime()));
	}

	public static Timestamp monthBegin()
	{
		Calendar mth = Calendar.getInstance();
		mth.set(5, 1);
		mth.set(11, 0);
		mth.set(12, 0);
		mth.set(13, 0);
		mth.set(9, 0);
		return new Timestamp(mth.getTime().getTime());
	}

	public static Timestamp monthEnd()
	{
		Calendar mth = Calendar.getInstance();
		mth.set(5, mth.getActualMaximum(5));
		mth.set(11, 23);
		mth.set(12, 59);
		mth.set(13, 59);
		return new Timestamp(mth.getTime().getTime());
	}

	public static String getMonth(String date)
	{
		if (date == null || date.equals(""))
		{
			return "";
		} else
		{
			int dateSlash1 = date.indexOf("-");
			int dateSlash2 = date.lastIndexOf("-");
			return date.substring(dateSlash1 + 1, dateSlash2);
		}
	}

	public static String getQuarter(String date)
	{
		int month = Integer.parseInt(getMonth(date));
		if (month < 4)
			return "1";
		if (month < 7)
			return "2";
		if (month < 10)
			return "3";
		if (month < 13)
			return "4";
		else
			return null;
	}

	public static int getQuarterInt(String date)
	{
		int month = Integer.parseInt(getMonth(date));
		if (month < 4)
			return 1;
		if (month < 7)
			return 2;
		if (month < 10)
			return 3;
		return month >= 13 ? 0 : 4;
	}

	public static String getYear(String date)
	{
		if (date == null || date.equals(""))
		{
			return "";
		} else
		{
			int dateSlash1 = date.indexOf("-");
			return date.substring(0, dateSlash1);
		}
	}

	public static String getDay(String date)
	{
		if (date == null || date.equals(""))
		{
			return "";
		} else
		{
			int dateSlash2 = date.lastIndexOf("-");
			return date.substring(dateSlash2 + 1, date.length() >= dateSlash2 + 3 ? dateSlash2 + 3 : date.length());
		}
	}

	public static String getHour(String date)
	{
		if (date == null || date.equals(""))
		{
			return "";
		} else
		{
			int dateSlash1 = date.indexOf(":");
			return date.substring(dateSlash1 - 2 >= 0 ? dateSlash1 - 2 : 0, dateSlash1).trim();
		}
	}

	public static String getMinute(String date)
	{
		if (date == null || date.equals(""))
			return "";
		int dateSlash1 = date.indexOf(":");
		int dateSlash2 = date.lastIndexOf(":");
		if (dateSlash1 == dateSlash2)
			return date.substring(dateSlash1 + 1, date.length() >= dateSlash2 + 3 ? dateSlash2 + 3 : date.length()).trim();
		else
			return date.substring(dateSlash1 + 1, dateSlash2).trim();
	}

	public static String getSecond(String date)
	{
		if (date == null || date.equals(""))
			return "";
		int dateSlash1 = date.indexOf(":");
		int dateSlash2 = date.lastIndexOf(":");
		if (dateSlash1 == dateSlash2)
			return "0";
		else
			return date.substring(dateSlash2 + 1, date.length() >= dateSlash2 + 3 ? dateSlash2 + 3 : date.length()).trim();
	}

	public static String getYear(java.sql.Date date)
	{
		String str = toDateTimeString(date);
		return getYear(str);
	}

	public static int getQuarterInt(java.sql.Date date)
	{
		String str = toDateTimeString(date);
		return getQuarterInt(str);
	}

	public static String getQuarter(java.sql.Date date)
	{
		String str = toDateTimeString(date);
		return getQuarter(str);
	}

	public static String getYear(Date date)
	{
		String str = toDateTimeString(date);
		return getYear(str);
	}

	public static String getMonth(java.sql.Date date)
	{
		String str = toDateTimeString(date);
		return getMonth(str);
	}

	public static String getMonth(Date date)
	{
		String str = toDateTimeString(date);
		return getMonth(str);
	}

	public static int getQuarterInt(Date date)
	{
		String str = toDateTimeString(date);
		return getQuarterInt(str);
	}

	public static String getQuarter(Date date)
	{
		String str = toDateTimeString(date);
		return getQuarter(str);
	}

	public static String getQuarter(String year, String month, boolean isNow)
	{
		if (year == null || month == null || "".equals(year) || "".equals(month))
		{
			if (isNow)
				return getQuarter(nowDate());
			else
				return null;
		} else
		{
			String str = year + "-" + month + "-" + "01";
			return getQuarter(str);
		}
	}

	public static int getQuarterInt(String year, String month, boolean isNow)
	{
		if (year == null || month == null || "".equals(year) || "".equals(month))
		{
			if (isNow)
				return getQuarterInt(nowDate());
			else
				return 0;
		} else
		{
			String str = year + "-" + month + "-" + "01";
			return getQuarterInt(str);
		}
	}

	public static String getDay(java.sql.Date date)
	{
		String str = toDateTimeString(date);
		return getDay(str);
	}

	public static String getDay(Date date)
	{
		String str = toDateTimeString(date);
		return getDay(str);
	}

	public static String getHour(java.sql.Date date)
	{
		String str = toDateTimeString(date);
		return getHour(str);
	}

	public static String getHour(Date date)
	{
		String str = toDateTimeString(date);
		return getHour(str);
	}

	public static String getMinute(java.sql.Date date)
	{
		String str = toDateTimeString(date);
		return getMinute(str);
	}

	public static String getMinute(Date date)
	{
		String str = toDateTimeString(date);
		return getMinute(str);
	}

	public static String getSecond(java.sql.Date date)
	{
		String str = toDateTimeString(date);
		return getSecond(str);
	}

	public static String getSecond(Date date)
	{
		String str = toDateTimeString(date);
		return getSecond(str);
	}

	public static String getWeek(Date date)
	{
		if (date == null)
			return "";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int weekNum = calendar.get(7) - 1;
		if (weekNum == 0)
			weekNum = 7;
		return String.valueOf(weekNum);
	}

	public static String getWeek(java.sql.Date date)
	{
		if (date == null)
			return "";
		else
			return getWeek(new Date(date.getTime()));
	}

	public static String getWeek(String date)
	{
		return getWeek(toSqlDate(date));
	}

	public static String getWeek()
	{
		Calendar calendar = Calendar.getInstance();
		int weekNum = calendar.get(7) - 1;
		if (weekNum == 0)
			weekNum = 7;
		return String.valueOf(weekNum);
	}

	public static Timestamp getTimestamp(long time)
	{
		return new Timestamp(time);
	}

	public static String getTimeStr(long time)
	{
		return (new Timestamp(time)).toString();
	}

	public static String getNowTime()
	{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String xzsj = sdf.format(date);
		return xzsj;
	}

	public static long getExpendTime(String startTime, String endTime)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		long time = 0L;
		try
		{
			Date start = sdf.parse(startTime);
			Date end = sdf.parse(endTime);
			time = start.getTime() - end.getTime();
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		return time;
	}

	public static void countCalc()
	{
		imm = 0;
		countTime = 0L;
	}

	public static long countStart(String desc)
	{
		long time = (new Date()).getTime();
		return time;
	}

	public static long countStartInfo(String desc)
	{
		long time = (new Date()).getTime();
		return time;
	}

	public static void countEnd(String desc, long startTime)
	{
			long time = (new Date()).getTime();
//			StringBuffer endS = new StringBuffer();
			long t = time - startTime;
//			long sec = t / 60000L;
//			long miao = (t % 60000L) / 1000L;
//			long haomiao = t % 60000L % 1000L;
//			endS = new StringBuffer();
			imm++;
			countTime += t;
	}

	public static void countEndInfo(String desc, long startTime)
	{
			long time = (new Date()).getTime();
//			StringBuffer endS = new StringBuffer();
			long t = time - startTime;
//			long sec = t / 60000L;
//			long miao = (t % 60000L) / 1000L;
//			long haomiao = t % 60000L % 1000L;
//			endS = new StringBuffer();
			imm++;
			countTime += t;
	}

	public static Timestamp getWestWeekStart(Timestamp stamp)
	{
		Calendar tempCal = Calendar.getInstance();
		tempCal.setTime(new Date(stamp.getTime()));
		tempCal.set(7, tempCal.getActualMinimum(7));
		tempCal.set(11, 0);
		tempCal.set(12, 0);
		tempCal.set(13, 0);
		return new Timestamp(tempCal.getTime().getTime());
	}

	public static Timestamp getWestWeekEnd(Timestamp stamp)
	{
		Calendar tempCal = Calendar.getInstance();
		tempCal.setTime(new Date(stamp.getTime()));
		tempCal.set(7, tempCal.getActualMaximum(7));
		tempCal.set(11, 23);
		tempCal.set(12, 59);
		tempCal.set(13, 59);
		return new Timestamp(tempCal.getTime().getTime());
	}

	public static Timestamp getWeekStart(Timestamp stamp)
	{
		if ("7".equals(getWeek(stamp.toString())))
			return getNextDayStart(getWestWeekStart(getDayStart(stamp, -1)));
		else
			return getNextDayStart(getWestWeekStart(stamp));
	}

	public static Timestamp getWeekEnd(Timestamp stamp)
	{
		if ("7".equals(getWeek(stamp.toString())))
			return getDayEnd(getNextDayStart(getWestWeekEnd(getDayStart(stamp, -1))));
		else
			return getDayEnd(getNextDayStart(getWestWeekEnd(stamp)));
	}

	public static Timestamp getMonthStart(Timestamp stamp)
	{
		Calendar tempCal = Calendar.getInstance();
		tempCal.setTime(new Date(stamp.getTime()));
		tempCal.set(5, tempCal.getActualMinimum(5));
		tempCal.set(11, 0);
		tempCal.set(12, 0);
		tempCal.set(13, 0);
		return new Timestamp(tempCal.getTime().getTime());
	}

	public static Timestamp getMonthEnd(Timestamp stamp)
	{
		Calendar tempCal = Calendar.getInstance();
		tempCal.setTime(new Date(stamp.getTime()));
		tempCal.set(5, tempCal.getActualMaximum(5));
		tempCal.set(11, 23);
		tempCal.set(12, 59);
		tempCal.set(13, 59);
		return new Timestamp(tempCal.getTime().getTime());
	}

	public static Timestamp getYearStart(Timestamp stamp)
	{
		Calendar tempCal = Calendar.getInstance();
		tempCal.setTime(new Date(stamp.getTime()));
		tempCal.set(6, tempCal.getActualMinimum(6));
		tempCal.set(11, 0);
		tempCal.set(12, 0);
		tempCal.set(13, 0);
		return new Timestamp(tempCal.getTime().getTime());
	}

	public static Timestamp getYearEnd(Timestamp stamp)
	{
		Calendar tempCal = Calendar.getInstance();
		tempCal.setTime(new Date(stamp.getTime()));
		tempCal.set(6, tempCal.getActualMaximum(6));
		tempCal.set(11, 23);
		tempCal.set(12, 59);
		tempCal.set(13, 59);
		return new Timestamp(tempCal.getTime().getTime());
	}

	public static int getWeekOfMonth(Date d)
	{
		Calendar calendar = Calendar.getInstance();
		if (d == null)
			d = new Date();
		calendar.setTime(d);
		int weekNum = calendar.get(7) - 1;
		if (weekNum == 0)
			weekNum = 7;
		int v = Integer.parseInt(getDay(d)) - weekNum;
		if (v < 0)
			return 1;
		else
			return ((Integer.parseInt(getDay(d)) - weekNum) + 1) / 7 + 2;
	}

	public static int getWeekOfMonthFormDate(String dateString)
	{
		return getWeekOfMonth(toDate((new StringBuilder(String.valueOf(dateString))).append(" 00:00:01").toString()));
	}

	public static int getWeekOfMonthFormDateTime(String dateTimeString)
	{
		return getWeekOfMonth(toDate(dateTimeString));
	}

	public static int getWeekOfMonth()
	{
		return getWeekOfMonth(null);
	}

	public static void main(String args[])
	{
		System.out.println(getWeekOfMonthFormDate("2007-11-18"));
		System.out.println(nowMillisTime());
	}

}
