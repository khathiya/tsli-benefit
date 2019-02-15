package com.tsli.util;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import sun.util.BuddhistCalendar;

public class DateUtil {

    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String TIME_FORMAT = "HH:mm:ss";
    private static final String TIMESTAMP_FORMAT = DATE_FORMAT+" "+TIME_FORMAT;

    private static final String LONG_DATE_FORMAT = "EEEEE dd MMMMM yyyy";
    private static final String DB_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    //private static final String SHORT_DISPLAY_FORMAT = "dd-MMM-yyyy";

    private static final Locale LOCALE_ENG = new Locale("EN","en");
    public static final Locale LOCALE_THAI = new Locale("TH","th");

    public static java.sql.Date toDate(String date) throws Exception {
    	return toDate(date, DATE_FORMAT);
    }

    public static java.sql.Date toDate(String date, String format) throws Exception {
        java.sql.Date result = null;
        if (date!=null||!"".equals(date)){
            SimpleDateFormat sd = new SimpleDateFormat(format, LOCALE_THAI);
            sd.setCalendar(new BuddhistCalendar());
            result = new java.sql.Date(sd.parse(date).getTime());
        }
        return result;
    }

    public static java.sql.Time toTime(String time) throws Exception {
        return toTime(time, TIME_FORMAT);
    }

    public static java.sql.Time toTime(String time, String format) throws Exception {
        java.sql.Time result = null;
        if (time!=null||!"".equals(time)){
            SimpleDateFormat sd = new SimpleDateFormat(format, LOCALE_THAI);
            sd.setCalendar(new BuddhistCalendar());
            result = new java.sql.Time(sd.parse(time).getTime());
        }
        return result;
    }

    public static java.sql.Timestamp toTimestamp(String timestamp) throws Exception {
        return toTimestamp(timestamp, TIMESTAMP_FORMAT);
    }

    public static java.sql.Timestamp toTimestamp(String timestamp, String format) throws Exception {
        java.sql.Timestamp result = null;
        if (timestamp!=null||!"".equals(timestamp)){
            SimpleDateFormat sd = new SimpleDateFormat(format, LOCALE_THAI);
            sd.setCalendar(new BuddhistCalendar());
            result = new java.sql.Timestamp(sd.parse(timestamp).getTime());
        }
        return result;
    }

    public static String toString(java.sql.Date date) {
        return toString(date, DATE_FORMAT);
    }
    public static String toString(java.sql.Date date, String format) {
        String result = "";
        if (date!=null){
            SimpleDateFormat sd = new SimpleDateFormat(format,LOCALE_THAI);
            sd.setCalendar(new BuddhistCalendar());
            result = sd.format(date);
        }
        return result;
    }

    public static String toString(java.sql.Time time) {
        return toString(time, TIME_FORMAT);
    }
    public static String toString(java.sql.Time time, String format) {
        String result = "";
        if (time!=null){
            SimpleDateFormat sd = new SimpleDateFormat(format,LOCALE_THAI);
            sd.setCalendar(new BuddhistCalendar());
            result = sd.format(time);
        }
        return result;
    }

    public static String toString(java.sql.Timestamp timestamp) {
        return toString(timestamp, TIMESTAMP_FORMAT);
    }

    public static String toString(java.sql.Timestamp timestamp, String format) {
        String result = "";
        if (timestamp!=null){
            SimpleDateFormat sd = new SimpleDateFormat(format,LOCALE_THAI);
            sd.setCalendar(new BuddhistCalendar());
            result = sd.format(timestamp);
        }
        return result;
    }

    public static String getCurrentTimeStamp() {
        return toString(new java.sql.Timestamp(new java.util.Date().getTime()));
    }

    public static String getSystemShortDateFormatTH() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, LOCALE_THAI);
        return sdf.format(new Date());
    }
    
    public static String getSystemDateTH(String format) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(format, LOCALE_THAI);
        return sdf.format(new Date());
    }


    public static String getYearThai2Digits(){
        SimpleDateFormat ssdd = new SimpleDateFormat("yy", LOCALE_THAI);
        return ssdd.format(new Date());
    }

    public static String getYearThai4Digits(){
        SimpleDateFormat ssdd = new SimpleDateFormat("yyyy", LOCALE_THAI);
        return ssdd.format(new Date());
    }

    public static String convertBBYear2(String str){
        String strYear = str.substring(0,4).trim();
        int year = Integer.parseInt(str.substring(0,4).trim()) + 543;
        str = str.replaceAll(strYear, String.valueOf(year));
        return str;
    }
    
    public static String convertBBYear(String str){
        String strYear = str.substring(6,10).trim();
        int year = Integer.parseInt(str.substring(6,10).trim()) + 543;
        str = str.replaceAll(strYear, String.valueOf(year));
        return str;
    }

    public static String convertCCyear(String str){
        String strYear = str.substring(6, 10).trim();
        int year = Integer.parseInt(str.substring(6, 10).trim()) - 543;
        str = str.replaceAll(strYear, String.valueOf(year));
        return str;

    }
    
    public static String convertCCYear2(String str){
        String strYear = str.substring(0,4).trim();
        int year = Integer.parseInt(str.substring(0,4).trim()) - 543;
        str = str.replaceAll(strYear, String.valueOf(year));
        return str;
    }

    /**
     * 
     * Compare 2 string represent date in TH format using Calendar.
     * If string 1 and string 2 are equals the method return 0.
     * If string 1 represent date after string 2 the method return 1.
     * If string 1 represent date before string 2 the method return -1.
     * 
     */
    public static int compareDate(String dateStr1, String dateStr2)
	{
    	int result = 0;

    	java.util.Calendar cal1 = java.util.Calendar.getInstance();
    	java.util.Calendar cal2 = java.util.Calendar.getInstance();

    	String[] date1 = dateStr1.split("/");
    	String[] date2 = dateStr2.split("/");

        int day1 = Integer.parseInt(date1[0]);
        int month1 = Integer.parseInt(date1[1]);
        int year1 = Integer.parseInt(date1[2]);

        int day2 = Integer.parseInt(date2[0]);
        int month2 = Integer.parseInt(date2[1]);
        int year2 = Integer.parseInt(date2[2]);

        cal1.set(year1,month1,day1);
        cal2.set(year2,month2,day2);
        if(cal1.equals(cal2)) {
        	System.out.println(dateStr1+" is the same as " + dateStr2);
        	result = 0;
        } else if(cal1.after(cal2)) {
	    	System.out.println(dateStr1 + " is after "+dateStr2);
	    	result = 1;
	    } else if (cal1.before(cal2)) {
	    	System.out.println(dateStr1 + " is before "+dateStr2);
	    	result = -1;
	    }

	    return result;
	}
    
    public static String convertToDB2Date(String tmpDate){
        /* 05/04/2010*/
        String str = tmpDate.substring(6,10)+"-"+tmpDate.substring(3,5)+"-"+tmpDate.substring(0,2);
        return tmpDate.replaceAll(tmpDate.substring(0,10), str);
    }
    public static String convertTHDateToUSDB2Date(String tmpDate){
    	/* 05/04/2553*/
    	tmpDate = convertCCyear(tmpDate);
        String str = tmpDate.substring(6,10)+"-"+tmpDate.substring(3,5)+"-"+tmpDate.substring(0,2);
        return tmpDate.replaceAll(tmpDate.substring(0,10), str);
    }
    public static String convertUSDB2DateToTHDate(String tmpDate){
    	/* 2010-01-01*/
    	tmpDate = convertBBYear2(tmpDate);
        String str = tmpDate.substring(8,10)+"/"+tmpDate.substring(5,7)+"/"+tmpDate.substring(0,4);
        tmpDate = tmpDate.replaceAll(tmpDate.substring(0,10), str);
        return tmpDate;
    }
    
    public static String convertDateToTH(String date) {
      System.out.println("date="+date);
    	
    	int yyyy = 0;
    	String newDate = "";
        if(date!= null )
        {
        	if(date.length()>0 && date.length()<=10){
        	
        		yyyy = Integer.parseInt(date.substring(0,4))+543;
        		newDate+=date.substring(8,10);
            	newDate+="/";
            	newDate+=date.substring(5,7);
            	newDate+="/";
            	newDate+=""+ yyyy;
        	}
        	
        }
       
        return newDate;
    }
    
    public static long diffDay(String dateParam1,String dateParam2){
    	// Creates two calendars instances
    	Calendar cal1 = Calendar.getInstance();
    	Calendar cal2 = Calendar.getInstance();
    	
        String[] dateArr = dateParam1.split("/");
        int date = Integer.valueOf(dateArr[0]);
        int month = Integer.valueOf(dateArr[1]);
        int year = Integer.valueOf(dateArr[2]);
        
        String[] dateArr2 = dateParam2.split("/");
        int date2 = Integer.valueOf(dateArr2[0]);
        int month2 = Integer.valueOf(dateArr2[1]);
        int year2 = Integer.valueOf(dateArr2[2]);
        
    	// Set the date for both of the calendar instance
    	cal1.set(year, month, date);
    	cal2.set(year2, month2, date2);
    	
    	// Get the represented date in milliseconds
    	long milis1 = cal1.getTimeInMillis();
    	long milis2 = cal2.getTimeInMillis();
    	
    	// Calculate difference in milliseconds
    	long diff = milis1 - milis2;
    	// Calculate difference in seconds
    	long diffSeconds = diff / 1000;
    	// Calculate difference in minutes
    	long diffMinutes = diff / (60 * 1000);
    	// Calculate difference in hours
    	long diffHours = diff / (60 * 60 * 1000);
    	// Calculate difference in days
    	long diffDays = diff / (24 * 60 * 60 * 1000);

    	return diffDays;
    }
    
}
