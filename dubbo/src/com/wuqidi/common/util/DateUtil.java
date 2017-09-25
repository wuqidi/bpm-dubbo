package com.wuqidi.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * <p>
 * 项目名称：bonc_ioc_oa
 * </p>
 * <p>
 * 类名称：DateUtil
 * </p>
 * <p>
 * 类描述：时间工具类
 * </p>
 * <p>
 * 创建人：泥猴桃
 * </p>
 * <p>
 * 创建时间：2017-7-5 上午10:27:53
 * </p>
 * <p>
 * 修改人：***
 * </p>
 * <p>
 * 修改时间：2017-7-5 上午10:27:53
 * </p>
 * <p>
 * 修改备注：
 * </p>
 * <p>
 * @version 1.0
 * </p>
 * 
 */
public class DateUtil {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private static SimpleDateFormat dateFormat2 = new SimpleDateFormat(
			"yyyy-MM-dd");

	// 获取当前时间
	public static String getNowTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(new Date());
	}

	public static String getNowLast7() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -7);
		Date monday = c.getTime();
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat2.format(monday) + " 00:00:00";
	}

	/**
	* <p>@Description: TODO(获取day天后的日期)</p>
	* <p>@author 泥猴桃 904778966@qq.com</p>
	* <p>@param OutTime
	* <p>@return    设定文件</p>
	* <p>@throws</p>
	*/
	public static Date getDateAddDay(int day) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DATE, day);
		return now.getTime();
	}

	/**
	 * 获取Date类型的时间
	 * 
	 * @param OutTime
	 * @return
	 */
	public static Date getDateAdd(int OutTime) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, OutTime);
		Date date = new Date(now.getTimeInMillis());
		return date;
	}

	// 当天的开始时间
	public static String getDayStart() {
		return dateFormat2.format(new Date()) + " 00:00:00";
	}

	// 当天的最后时间
	public static String getDayEnd() {
		return dateFormat2.format(new Date()) + " 23:59:59";
	}

	public static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1) {
			return -6;
		} else {
			return 2 - dayOfWeek;
		}
	}

	// 将字符换转为日期
	public static Date string2DateM(String str) {
		try {
			return dateFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * <p>	 * @Description: TODO(获取本周周一日期)	 * </p>
	 * <p>	 * @author 泥猴桃 904778966@qq.com	 * </p>
	 * <p>	 * @return 设定文件	 * </p>
	 * <p>	 * @throws	 * </p>
	 */
	public static Date getCurMonday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();
		// String preMonday = dateFormat2.format(monday);
		return monday;
	}

	// 获得当前周- 周日 的日期
	/**
	 * <p>	 * @Description: TODO(获取本周周五日期)	 * </p>
	 * <p>	 * @author 泥猴桃 904778966@qq.com	 * </p>
	 * <p>	 * @return 设定文件	 * </p>
	 * <p>	 * @throws	 * </p>
	 */
	public static Date getCurFirday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 4);
		Date firday = currentDate.getTime();
		// String preMonday = dateFormat.format(monday);
		return firday;
	}

	// 获得当前月--开始日期
	public static String getMinMonthDate() {
		Calendar cd = Calendar.getInstance();
		cd.set(Calendar.DAY_OF_MONTH,
				cd.getActualMinimum(Calendar.DAY_OF_MONTH));
		return dateFormat2.format(cd.getTime()) + " 00:00:00";
	}

	// 获得当前月--结束日期
	public static String getMaxMonthDate() {
		Calendar cd = Calendar.getInstance();
		cd.set(Calendar.DAY_OF_MONTH,
				cd.getActualMaximum(Calendar.DAY_OF_MONTH));
		return dateFormat.format(cd.getTime());
	}

	// 给定一个时间距离当前时间的时分秒数
	public static String Difference(String str) {

		Date endDate = new Date();
		Date startDate;
		try {
			startDate = dateFormat.parse(str);

			long different = endDate.getTime() - startDate.getTime();
			// 1 minute = 60 seconds
			// 1 hour = 60 x 60 = 3600
			// 1 day = 3600 x 24 = 86400
			long secondsInMilli = 1000;
			long minutesInMilli = secondsInMilli * 60;
			long hoursInMilli = minutesInMilli * 60;
			long daysInMilli = hoursInMilli * 24;

			long elapsedDays = different / daysInMilli;
			different = different % daysInMilli;

			long elapsedHours = different / hoursInMilli;
			different = different % hoursInMilli;

			long elapsedMinutes = different / minutesInMilli;
			different = different % minutesInMilli;

			long elapsedSeconds = different / secondsInMilli;

			if (elapsedDays > 0) {
				return elapsedDays + "天前";
			}
			if (elapsedHours > 0) {
				return elapsedHours + "小时前";
			}
			if (elapsedMinutes > 0) {
				return elapsedMinutes + "分钟前";
			}
			if (elapsedSeconds > 0) {
				return elapsedSeconds + "秒前";
			}

			/*
			 * System.out.printf("%d 天, %d 小时, %d 分钟, %d 秒%n", elapsedDays,
			 * elapsedHours, elapsedMinutes, elapsedSeconds);
			 */
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "未知时间";

	}

	/**
	 * 精确到秒的日期格式转换为字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String dateM2String(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = sdf.format(date);
		return str;
	}

	/**
	 * <p>
	 * @Description: TODO(获取前一天时间 yyyy-MM-dd)
	 * </p>
	 * <p>
	 * @author 泥猴桃 904778966@qq.com
	 * </p>
	 * <p>
	 * @return yyyy-MM-dd 设定文件
	 * </p>
	 * <p>
	 * @throws
	 * </p>
	 */
	public static String getYesterDay() {
		Calendar cal = Calendar.getInstance();// 此时打印它获取的是系统当前时间
		cal.add(Calendar.DATE, -1); // 得到前一天
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}

	/**
	 * <p>
	 * @Description: TODO(utilDate转sqlDate)
	 * </p>
	 * <p>
	 * @author 泥猴桃 904778966@qq.com
	 * </p>
	 * <p>
	 * @param createTime
	 * <p>
	 * @return 设定文件
	 * </p>
	 * <p>
	 * @throws
	 * </p>
	 */
	public static java.sql.Date convertSqlDate(Date createTime) {
		// TODO Auto-generated method stub
		return new java.sql.Date(createTime.getTime());
	}

	/**
	 * <p>
	 * @Description: TODO(获取现在时间处于本年第几周)
	 * </p>
	 * <p>
	 * @author 泥猴桃 904778966@qq.com
	 * </p>
	 * <p>
	 * @return 设定文件
	 * </p>
	 * <p>
	 * @throws
	 * </p>
	 */
	public static Integer getWeekOfYear() {
		Calendar cal = Calendar.getInstance();
		int week = cal.get(cal.WEEK_OF_YEAR);
		return week;
	}
}
