package Controller;

import javax.swing.JComboBox;

import com.toedter.calendar.JDateChooser;

/**
 * This class will validate and see if the patient,
 * has entered in their fields correctly.
 * 
 * @author Beau Lee (300010635)
 *
 */
public class AppointmentValidation {
	/**
	 * This method demonstrates that if the receptionist
	 * enters in their ID number, it will return true.
	 * Otherwise, it will return false.
	 * false.
	 * @param id
	 * @return
	 */
	public static boolean validateAppmntRecptID(int id) {
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
	 * @param date
	 * @return
	 */
	public static boolean validateAppmntRecptCal(JDateChooser cbDate) {
		if(cbDate.equals("")) {
			return true;			
		}
		else {
			return false;
		}		
	}
	
	/**
	 * This method will return false if a
	 * time of appointment is not entered.
	 * 
	 * @param cb
	 */
	public static boolean validateAppmntRecptTime(JComboBox<String> cbTime) {
		if(cbTime.equals("")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean validateAppmntRecptStat(JComboBox<String> cbStatus) {
		if(cbStatus.equals(null)) {
			return true;
		}
		else {
			return false;
		}
	}	
}
