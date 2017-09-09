package texus.app.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import texus.app.utils.AppConstance;
import texus.app.utils.AppUtility;

public class DateManager {

	public static final int EQUAL = 0;
	public static final int GREATER = 0;
	public static final int LESS = 0;

	Date date;
	Calendar cal = null;

	public DateManager(String dateString) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(date != null) {
			cal = Calendar.getInstance();
			cal.setTime(date);
		}
		
	}

	public DateManager() {
		cal = Calendar.getInstance();
	}



    public boolean ifMonthEnd() {
        if( cal == null) return false;
        Calendar nextNotifTime = Calendar.getInstance();
        nextNotifTime.add(Calendar.MONTH, 1);
        nextNotifTime.set(Calendar.DATE, 1);
        nextNotifTime.add(Calendar.DATE, -1);
        if( cal.get(Calendar.DAY_OF_MONTH) == nextNotifTime.get(Calendar.DAY_OF_MONTH)) {
            return true;
        }
        return false;
    }

    public long getMonthEndTimeMorning() {
		Calendar nextNotifTime = Calendar.getInstance();
		nextNotifTime.add(Calendar.MONTH, 1);
		nextNotifTime.set(Calendar.DATE, 1);
		nextNotifTime.add(Calendar.DATE, -1);
		nextNotifTime.set(Calendar.AM_PM, Calendar.AM);
		nextNotifTime.set(Calendar.HOUR_OF_DAY, 9);
		nextNotifTime.set(Calendar.MINUTE, 0);
		nextNotifTime.set(Calendar.SECOND,0);
		return nextNotifTime.getTimeInMillis();
	}

	public void addDay( int dayCount) {
		if( cal == null) return;
		cal.add(Calendar.DATE, dayCount);
	}





	public long getMonthHalfTimeMorning() {
		Calendar nextNotifTime = Calendar.getInstance();
		int currentDay = nextNotifTime.get(Calendar.DATE);
		if( currentDay > 15) {
			nextNotifTime.add(Calendar.MONTH, 1);
		}
		nextNotifTime.set(Calendar.DATE, 15);
		nextNotifTime.set(Calendar.AM_PM, Calendar.AM);
		nextNotifTime.set(Calendar.HOUR_OF_DAY, 9);
		nextNotifTime.set(Calendar.MINUTE, 0);
		nextNotifTime.set(Calendar.SECOND,0);
		return nextNotifTime.getTimeInMillis();
	}

	public long getNextAlarmTime() {
//		return  Calendar.getInstance().getTimeInMillis() + 2*60*1000;
		Calendar nextNotifTime = Calendar.getInstance();
		int currentDay = nextNotifTime.get(Calendar.DATE);
		int hour = nextNotifTime.get(Calendar.HOUR_OF_DAY);
		int minute = nextNotifTime.get(Calendar.MINUTE);
		int seconds = nextNotifTime.get(Calendar.SECOND);
		if( currentDay >= 15 && hour >= 9) {
			return getMonthEndTimeMorning();
		}
		return getMonthHalfTimeMorning();
	}

	public void setTime( int hour, int minute, int second) {
		if( cal == null) return;
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set( Calendar.MINUTE, minute);
		cal.set( Calendar.SECOND, second);
	}



	public int compare ( DateManager toCompare) {
		if( toCompare == null || cal == null)  return AppConstance.INVALID_VALUE;
		if(cal.getTimeInMillis() == toCompare.getTimeInMilliseconds())
			return EQUAL;
		if( cal.getTimeInMillis() > toCompare.getTimeInMilliseconds())
			return GREATER;
		if( cal.getTimeInMillis() < toCompare.getTimeInMilliseconds())
			return LESS;

		return AppConstance.INVALID_VALUE;
	}





	//eg:- 2017-03-30
	//xxxxx0123456789
	//here we use substring logics than split logic because
	// divider character may be vary
	public static DateManager parseYYYYMMDD( String date) {
		DateManager dateManager = null;
		try {
			String year = date.substring(0,4);
			String month = date.substring(5,7);
			String day = date.substring(8,10);
			int yearInt = AppUtility.parseInt(year);
			int monthInt = AppUtility.parseInt(month);
			int dayInt = AppUtility.parseInt(day);
			Calendar tempCal = Calendar.getInstance();
			tempCal.set(Calendar.YEAR, yearInt);
			tempCal.set(Calendar.MONTH, --monthInt);
			tempCal.set(Calendar.DAY_OF_MONTH, dayInt);
			dateManager = new DateManager(tempCal.getTimeInMillis());
		} catch ( Exception e) {
			return null;
		}

		return dateManager;
	}

	//eg:- 04/01/2017
	//xxxxx0123456789
	//here we use substring logics than split logic because
	// divider character may be vary
	public static DateManager parseDDMMYYYY( String date) {
		DateManager dateManager = null;
		try {
			String year = date.substring(6,10);
			String month = date.substring(3,5);
			String day = date.substring(0,2);
			int yearInt = AppUtility.parseInt(year);
			int monthInt = AppUtility.parseInt(month);
			int dayInt = AppUtility.parseInt(day);
			Calendar tempCal = Calendar.getInstance();
			tempCal.set(Calendar.YEAR, yearInt);
			tempCal.set(Calendar.MONTH, --monthInt);
			tempCal.set(Calendar.DAY_OF_MONTH, dayInt);
			dateManager = new DateManager(tempCal.getTimeInMillis());
		} catch ( Exception e) {
			return null;
		}

		return dateManager;
	}

	public String getQuarterName() {
		if(cal ==  null) return "Q1";
		// 0 1 2
		if(cal.get(Calendar.MONTH) <= 2)
			return "Q1";

		// 3 4 5
		if(cal.get(Calendar.MONTH) <= 5)
			return "Q2";

		// 6 7 8
		if(cal.get(Calendar.MONTH) <= 8)
			return "Q3";

		// 9 10 11
		if(cal.get(Calendar.MONTH) <= 11)
			return "Q4";

		return "Q1";
	}

	public boolean isToday() {
		if( cal == null) return false;
		Calendar today = Calendar.getInstance();
		if( cal.get(Calendar.YEAR) !=  today.get(Calendar.YEAR)) return false;
		if( cal.get(Calendar.MONTH) !=  today.get(Calendar.MONTH)) return false;
		if( cal.get(Calendar.DAY_OF_MONTH) !=  today.get(Calendar.DAY_OF_MONTH)) return false;
		return true;
	}

	//01/01/70 06:25:49 AM
	//01234567890123456789
	public static DateManager parseDDMMYYHHMMSSAMPM( String date) {
		DateManager dateManager = null;
		try {
			String day = date.substring(0,2);
			String month = date.substring(3,5);
			String year = date.substring(6,8);

			String hour = date.substring(9,11);
			String minute = date.substring(12,14);
			String second = date.substring(15,17);

			String amPM = date.substring(8,10);

			int yearInt = 2000 +  AppUtility.parseInt(year);
			int monthInt = AppUtility.parseInt(month);
			int dayInt = AppUtility.parseInt(day);

			int hourInt = AppUtility.parseInt(hour);
			int minuteInt = AppUtility.parseInt(minute);
			int secondInt = AppUtility.parseInt(second);

			Calendar tempCal = Calendar.getInstance();
			tempCal.set(Calendar.YEAR, yearInt);
			tempCal.set(Calendar.MONTH, --monthInt);
			tempCal.set(Calendar.DAY_OF_MONTH, dayInt);

			if( amPM.toLowerCase().equals("am")) {
				tempCal.set(Calendar.AM_PM, Calendar.AM);
			} else {
				tempCal.set(Calendar.AM_PM, Calendar.PM);
			}

			tempCal.set(Calendar.HOUR_OF_DAY, hourInt);
			tempCal.set(Calendar.MINUTE, minuteInt);
			tempCal.set(Calendar.SECOND, secondInt);



			dateManager = new DateManager(tempCal.getTimeInMillis());

		} catch ( Exception e) {
			return null;
		}

		return dateManager;
	}

	//26/04/2017 15.10.16 PM
	//01234567890123456789012
	public static DateManager parseDDMMYYYYHHMMSSAMPM( String date) {
		DateManager dateManager = null;
		try {
			String day = date.substring(0,2);
			String month = date.substring(3,5);
			String year = date.substring(6,10);

			String hour = date.substring(11,13);
			String minute = date.substring(14,16);
			String second = date.substring(17,19);

			String amPM = date.substring(20,22);


			int yearInt = AppUtility.parseInt(year);
			int monthInt = AppUtility.parseInt(month);
			int dayInt = AppUtility.parseInt(day);

			int hourInt = AppUtility.parseInt(hour);
			int minuteInt = AppUtility.parseInt(minute);
			int secondInt = AppUtility.parseInt(second);

			Calendar tempCal = Calendar.getInstance();
			tempCal.set(Calendar.YEAR, yearInt);
			tempCal.set(Calendar.MONTH, --monthInt);
			tempCal.set(Calendar.DAY_OF_MONTH, dayInt);

			if( amPM.toLowerCase().equals("am")) {
				tempCal.set(Calendar.AM_PM, Calendar.AM);
			} else {
				tempCal.set(Calendar.AM_PM, Calendar.PM);
			}

			tempCal.set(Calendar.HOUR_OF_DAY, hourInt);
			tempCal.set(Calendar.MINUTE, minuteInt);
			tempCal.set(Calendar.SECOND, secondInt);


			long timeInMilliseconds = tempCal.getTimeInMillis();
			dateManager = new DateManager(tempCal.getTimeInMillis());

		} catch ( Exception e) {
			return null;
		}

		return dateManager;
	}

	public DateManager(int year, int month, int day) {
		this.cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, day);
		String date = getDayNameDD_MM_YYY__HHMMAM();
		date = date;
	}

	public int getYear() {
		if( cal != null) {
			return cal.get(Calendar.YEAR);
		}
		return AppConstance.INVALID_VALUE;
	}

//	public boolean isToday() {
//		if( cal == null) return  false;
//		Calendar calendar = Calendar.getInstance();
//		if( cal.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)) {
//			return true;
//		}
//		return false;
//	}

	public long getYesterdayMidnightTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return  calendar.getTimeInMillis();
	}

	public int getMonth() {
		if( cal != null) {
			return cal.get(Calendar.MONTH);
		}
		return AppConstance.INVALID_VALUE;
	}

	public int getDay() {
		if( cal != null) {
			return cal.get(Calendar.DAY_OF_MONTH);
		}
		return AppConstance.INVALID_VALUE;
	}

	public DateManager(Calendar calendar) {
		this.cal = calendar;
	}

	public DateManager(long timeInMillisecond) {
		this.cal = Calendar.getInstance();
		cal.setTimeInMillis(timeInMillisecond);
	}

	public long getStartOfDate() {
		if( this.cal != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(cal.getTimeInMillis());
			calendar.set(Calendar.HOUR_OF_DAY,0);
			calendar.set(Calendar.MINUTE,0);
			calendar.set(Calendar.SECOND,1);
			return calendar.getTimeInMillis();
		} else {
			return this.cal.getTimeInMillis();
		}

	}

	public long getTimeInMilliseconds() {
		if( cal != null)
			return cal.getTimeInMillis();
		return Calendar.getInstance().getTimeInMillis();
	}

	public long getEndOfDate() {
		if( this.cal != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(cal.getTimeInMillis());
			calendar.set(Calendar.HOUR_OF_DAY,23);
			calendar.set(Calendar.MINUTE,59);
			calendar.set(Calendar.SECOND,59);
			return calendar.getTimeInMillis();
		}
		return this.cal.getTimeInMillis();
	}
	
	public String getDayNameDD_MM_YYY__HHMMAM() {
		if(cal == null) {
			return "";
		}
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		if(hour > 12) {
			hour = hour - 12;
		}
		int minute = cal.get(Calendar.MINUTE);
		int am_pm = cal.get(Calendar.AM_PM);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		int month = cal.get(Calendar.MONTH);
		int day_of_month = cal.get(Calendar.DAY_OF_MONTH);

		String am_pm_string = (am_pm == Calendar.AM) ? "AM" : "PM";
		
		return (getDayName(day) + " " + setPadding(day_of_month) 
				+"-" + setPadding(month + 1) + "-" + cal.get(Calendar.YEAR) + " "
				+ setPadding(hour) + ":" + setPadding(minute) + " " + am_pm_string);
	}

	public String get_HHMM() {
		if(cal == null) {
			return "";
		}
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		if(hour > 12) {
			hour = hour - 12;
		}
		int minute = cal.get(Calendar.MINUTE);
		int am_pm = cal.get(Calendar.AM_PM);
		String am_pm_string = (am_pm == Calendar.AM) ? "AM" : "PM";
		return setPadding(hour) + ":" + setPadding(minute) + " " + am_pm_string;
	}

	public String getDayNameDD_MM_YYYY() {
		if(cal == null) {
			return "";
		}
		int day = cal.get(Calendar.DAY_OF_WEEK);
		int month = cal.get(Calendar.MONTH);
		int day_of_month = cal.get(Calendar.DAY_OF_MONTH);

		return (getDayName(day) + " " + setPadding(day_of_month)
				+"-" + setPadding(month + 1) + "-" + cal.get(Calendar.YEAR));
	}

	public String getDD_MM_YYYY() {
		if(cal == null) {
			return "";
		}
		int day = cal.get(Calendar.DAY_OF_WEEK);
		int month = cal.get(Calendar.MONTH);
		int day_of_month = cal.get(Calendar.DAY_OF_MONTH);

		return  setPadding(day_of_month)
				+ "-" + setPadding(month + 1) + "-" + cal.get(Calendar.YEAR);
	}

	
	private String setPadding(int num) {

		return (num < 10) ? "0" + num : "" + num;
	}
	
	private String getDayName(int day) {

		switch (day) {
		case Calendar.SUNDAY:
			return "Sunday";
		case Calendar.MONDAY:
			return "Monday";
		case Calendar.TUESDAY:
			return "Tuseday";
		case Calendar.WEDNESDAY:
			return "Wednesday";
		case Calendar.THURSDAY:
			return "Thursday";
		case Calendar.FRIDAY:
			return "Friday";
		case Calendar.SATURDAY:
			return "Saturday";
		}

		return "Sunday";
	}

	private String getMonthName(int month) {

		switch (month) {
		case Calendar.JANUARY:
			return "January";
		case Calendar.FEBRUARY:
			return "February";
		case Calendar.MARCH:
			return "March";
		case Calendar.APRIL:
			return "April";
		case Calendar.MAY:
			return "May";
		case Calendar.JUNE:
			return "June";
		case Calendar.JULY:
			return "July";
		case Calendar.AUGUST:
			return "August";
		case Calendar.SEPTEMBER:
			return "September";
		case Calendar.OCTOBER:
			return "October";
		case Calendar.NOVEMBER:
			return "November";
		case Calendar.DECEMBER:
			return "December";
		}

		return "January";
	}
	
	
	
}
