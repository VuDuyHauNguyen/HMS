package Controller;

import javax.swing.JComboBox;

import com.toedter.calendar.JDateChooser;
/**
 * This class will help validate the
 * receptionist's input.
 * 
 * @author Beau Lee (300010635)
 *
 */
public class ReceptionistValidation {
	public static boolean validateRecptID(int id) {
		if(id == 0) {
			return false;
		}
		else {
			return true;
		}
	}
	/**
	 * This method will return false if the date is
	 * not entered.
	 * 
	 * @param date
	 * @return
	 */
	public static boolean validateRecptCal(JDateChooser cbDate) {
		if(cbDate.equals("")) {
			return true;			
		}
		else {
			return false;
		}		
	}
	
	/**
	 * This method will return false if 
	 * the receptionist entered in nothing.
	 * 
	 * @param cb
	 */
	public static boolean validateRecptTime(JComboBox<String> cbTime) {
		if(cbTime.equals("")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * This will return null if the receptionist chose
	 * not to enter in anything for the status of the
	 * patient.
	 * @param cbStatus
	 * @return
	 */
	public static boolean validateRecptStat(JComboBox<String> cbStatus) {
		if(cbStatus.equals(null)) {
			return true;
		}
		else {
			return false;
		}
	}	
}
