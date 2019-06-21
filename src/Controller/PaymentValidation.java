package Controller;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

/**
 * This class is for patient validation.  Here,
 * if the user were to input their fields, it
 * will return false.
 * @author Beau Lee (300010635)
 * @version 0.1
 *
 */
public class PaymentValidation {
	
	/**
	 * This method will validate the payment details.
	 * @param details
	 * @return
	 */
	public static boolean validatePaymentDetails(String details) {
		if(details.equals("")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * This method will validate the payment amount.
	 * @param amount
	 * @return
	 */
	public static boolean validatePaymentAmount(double amount) {
		if(amount == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * This method will validate the payment's date. 
	 * @param payDate
	 * @return
	 */
	public static boolean validatePaymentPayDate(JDateChooser payDate) {
		if(payDate.equals("")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * This method will validate the time of payment 
	 * @param time
	 * @return
	 */
	public static boolean validatePaymentPayTime(JComboBox<String> time) {
		if(time.equals("")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Thid method will validate the status of the
	 * payment.
	 * @param status
	 * @return
	 */
	public static boolean validatePaymentPayStatus(JComboBox<String> status) {
		if(status.equals(null)) {
			return true;
		}
		else {
			return false;
		}
	}
}
