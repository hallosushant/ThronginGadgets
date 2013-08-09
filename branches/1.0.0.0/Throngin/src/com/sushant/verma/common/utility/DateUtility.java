package com.sushant.verma.common.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sushant.verma.device.dao.DeviceDao;

	public class DateUtility {
	
	private static Logger log = LogManager.getLogger(DeviceDao.class);
	public static final int NINTY_DAYS_BACK=-90;
	public static final int ZERO_DAYS_BACK=0;
	public static final String YYYY_MM_DD_FORMAT="yyyy-MM-dd";
	public static final String MMM_yyyy_FORMAT="MMM yyyy";
	public static final String RFC_822_FORMAT="EEE, dd MMM yyyy HH:mm:ss Z";
	
	public static String getDateAfter(Date fromDate,int noOfDays,String dateFormat){
		Calendar now = Calendar.getInstance();
		log.debug("Current date : " + now.get(Calendar.DATE) + "/" +(now.get(Calendar.MONTH) + 1) + "/"+  now.get(Calendar.YEAR));

	    // add days to current date using Calendar.add method
	    now.add(Calendar.DATE, noOfDays);

	    log.debug("date after "+noOfDays+" day : " + now.get(Calendar.DATE) + "/" +(now.get(Calendar.MONTH) + 1) + "/"+  now.get(Calendar.YEAR));
		Date afterDate= now.getTime();
		SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);
		String afterDateString=sdf.format(afterDate);
		log.debug("afterDateString="+afterDateString);
		return afterDateString;
	}
	
	public static String getRFC822Date(Date date){
		String rfc822Date = new SimpleDateFormat(RFC_822_FORMAT).format(date);
		return rfc822Date;
	}
	
	public static String getMonthYear(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat(MMM_yyyy_FORMAT);
		String monthYear=sdf.format(date);
		log.debug("monthYear="+monthYear);
		return monthYear;
	}
	public static void main(String[] args){
//		String toDate=getDateAfter(new Date(), ZERO_DAYS_BACK,YYYY_MM_DD_FORMAT);
//		log.debug("toDate="+toDate);

//		String[] launchDateParts="2013-3-28".split("-");
//		Calendar cal=Calendar.getInstance();
//		cal.set(Calendar.YEAR, Integer.valueOf(launchDateParts[0]));
//		cal.set(Calendar.MONTH, Integer.valueOf(launchDateParts[1]));
//		cal.set(Calendar.DATE, Integer.valueOf(launchDateParts[2]));
//		getMonthYear(cal.getTime());
		
	}
}
