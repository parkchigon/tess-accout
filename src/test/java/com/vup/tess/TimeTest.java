package com.vup.tess;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeTest {
	public static void main(String[] args) throws ParseException {
		/*
		 * Calendar calendar = Calendar.getInstance(); calendar.add(calendar.MONTH, -1);
		 * String lastDate = getCurrentTime("YYYY") +
		 * calendar.getActualMaximum(Calendar.DATE); System.out.println(lastDate);
		 */
		 SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd"); 
		    Calendar time = Calendar.getInstance(); 
		    time.add(Calendar.MONTH, -1);
		    String beforeMonth = format.format(time.getTime()); 
		int year = Integer.parseInt(beforeMonth.substring(0, 4));
		int month = Integer.parseInt(beforeMonth.substring(4, 6));
		int date = Integer.parseInt(beforeMonth.substring(6, 8));
        
		//Calendar calendar = Calendar.getInstance();
		time.set(year, month-1, date);
		//calendar.add(calendar.MONTH, -1);
		String nextYearMonthLastDay = "";
		//time.getActualMinimum(date)
		nextYearMonthLastDay = String.valueOf(year) + getDayToString(month) + getDayToString(time.getActualMinimum(Calendar.DAY_OF_MONTH));
		System.out.println(nextYearMonthLastDay);
	}
	public static String getCurrentTime(String datePattern) {
		/*
		long sysTime = System.currentTimeMillis();

		Date date = new Date(sysTime);
		return getFormattedTime(date, datePattern);
		*/

		Calendar calendar = Calendar.getInstance();
        java.util.Date date = calendar.getTime();
        String today = (new SimpleDateFormat(datePattern).format(date));

        return today;
	}
	public static String getDayToString(int day){
		String dayStr = "";
		
		if(day < 10){
			dayStr = "0" + day;
		}else{
			dayStr = String.valueOf(day);
		}
		return dayStr;
	}
}
