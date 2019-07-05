package Boundary.Helpers;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeHelper {
	//get Time with format "HH:mm" from a Date
	public static String getDisplayTimeFromDate(Date date) {
		if(date == null) return "";
		
		SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
		return sf.format(date);
	}
	
	//get Date with format "yyyy-MM-dd" from a Date
	public static String getDisplayDateFromDate(Date date) {
		if(date == null) return "";
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(date);
	}
	
	//get a Date from string "yyyy-MM-dd HH:mm:ss"
	public static Date getDateFromString(String dateStr) {
		return new Date(Timestamp.valueOf(dateStr).getTime());
	}
}
