package com.vup.tess.global.util;

import java.util.Calendar;

public class DateUtils {
	public static int getMonthMinDay(String dateStr){
		int year = Integer.parseInt(dateStr.substring(0, 4));
		int month = Integer.parseInt(dateStr.substring(4, 6));
		int date = Integer.parseInt(dateStr.substring(6, 8));
        
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month-1, date);
     
		return calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
	}
	
	public static int getMonthMaxDay(String dateStr){
		int year = Integer.parseInt(dateStr.substring(0, 4));
		int month = Integer.parseInt(dateStr.substring(4, 6));
		int date = Integer.parseInt(dateStr.substring(6, 8));
        
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month-1, date);
     
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
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
