package Controller;

import java.sql.Date;
import javax.swing.JTextField;

/**
 * This class will validate the patient's input within the main form.
 * @author Beau Lee (300010635)
 * @version 0.2 - I have used regex as a validation process
 * for each textfield.
 *  
 */
public class PatientValidation {
	
	/*
	 * This method validates a user's first name.
	 */
	public static boolean validateFirstName(String firstName) {	
		if(firstName.matches("[A-Z][a-z]*")) {
			return true;
		}
		else {
			return false;
		}		
	}
	
	/*
	 * This method validates a user's last name.
	 */
	public static boolean validateLastName(String lastName) {
		if(lastName.matches("[A-Z][a-z]*")) {
			return true;			
		}
		else {
			return false;
		}
	}
	
	/*
	 * This method validates a user's Gender type.
	 */
	public static boolean validateGender(String gender) {
		if(gender.matches("male") || gender.matches("female")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/*
	 * This method validates a user's Date of Birth (dob).
	 */
	public static boolean validateDob(Date dob) {
		// If it equals within the date range, return true.
		if(dob.equals(dob)) {
			return true;
		}
		// Otherwise if not, return false.
		else if(!dob.equals(dob)){
			return false;
		}
		// Otherwise if nothing is entered (empty values), return false again.
		else {
			return false;
		}
	}
	
	/*
	 * This method validates a user's telephone number.
	 */
	public static boolean validatePhone(String phone) {
		if(phone.matches("[0-9]")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/*
	 * This method validates a user's e-mail address.
	 */	
	public static boolean validateEmail(JTextField emailTxtBox) {		
		if(emailTxtBox.equals("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/*
	 * This method validates a user's address of residence.
	 */
	public static boolean validateAddress(String address) {
		if(address == "") {
			return true;
		}
		else {
			return false;
		}
	}
}
