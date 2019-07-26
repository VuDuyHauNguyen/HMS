package Controller;

import java.util.ArrayList;
import java.util.Date;
import Boundary.DAO.AppointmentDAOImpl;
import Boundary.Helpers.DateTimeHelper;
import Entity.Appointment;
import Entity.CheckUpRecord;
import Entity.Employee;

public class AppointmentController {

	public static final String SUCCESS = "Success";
	
	private static AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl(); 

	//validate and add appointment to database
	public static String addAppointment(Appointment appointment) {

		String result = SUCCESS;
		
		//check patient is valid
		if(appointment.getPatient() == null)
			//error message
			return "Patient Id is not existed\nPlease try again!";
		
		//check Appointment Date in future
		if( appointment.getAppointmentTime().getTime() < 
				(new Date()).getTime())
			//error message
			return "Appointment cannot in the past\nPlease try again!";
		
		//set default new appointment status
		appointment.setStatus(Appointment.STATUS_BOOK);
		
		//add
		int newAppointmentId = appointmentDAO.addAppointment(appointment);
		
		//check
		if(newAppointmentId < 0) {
			result = "Cannot create an appointment.\nPlease try again!";//error message
		}
		
		return result;
	}
	
	//get appointment by id
	public static Appointment getAppointmentById(int id) {
		
		return appointmentDAO.getAppointmentById(id);
	}
	
	//get all appointments by role
	public static ArrayList<Appointment> getAllAppointments() {
		
		Employee loggedInUser = Authentication.getLoggedInEmployee();
		
		switch(loggedInUser.getRole()) {
			case Employee.ADMIN_ROLE://show all
				return appointmentDAO.getAllAppointments();
			default:
				return appointmentDAO.getFromTodayAppointments();
		}
	}
	
	//cancel an appointment
	public static String cancelAppointment(Appointment appointment) {
		
		//set state to cancel
		appointment.setStatus(Appointment.STATUS_CANCEL);
		
		//update and return result
		return updateAppointment(appointment);
	}
	
	//check in an appointment to enter check up
	public static String checkInAppointment(Appointment appointment, String medicalProblem) {
		
		if(appointment.getStatus() == null ||
				appointment.getStatus().equals(Appointment.STATUS_CANCEL) ||
				appointment.getStatus().equals(Appointment.STATUS_DONE) ||
				appointment.getAppointmentTime() == null ||
				DateTimeHelper.getDisplayDateFromDate(appointment.getAppointmentTime())
				.equals(DateTimeHelper.getDisplayDateFromDate(new Date())) == false)
			return "Cannot check in with not today appointments\nPlease try again!";
		
		if(medicalProblem == null ||
				medicalProblem.equals(""))
			return "Cannot check in  without medical problems\nPlease try again!";
		
		//update appointment status to 'done'
		appointment.setStatus(Appointment.STATUS_DONE);
		
		String result = updateAppointment(appointment);
		
		//check update success
		if(result.equals(SUCCESS)) {
			
			//create a new check up record
			CheckUpRecord checkUpRecord = new CheckUpRecord();
			
			checkUpRecord.setMedicalProblem(medicalProblem);
			
			//set default data for the record
			checkUpRecord.setId(appointment.getId());
			checkUpRecord.setPatient(appointment.getPatient());
			checkUpRecord.setCheckUpRecordTime(new Date());
			checkUpRecord.setStatus(CheckUpRecord.STATUS_QUEUE);
			
			result = CheckUpRecordController.addCheckUpRecord(checkUpRecord);
			
		}else {
			result = "Cannot check in\nPlease try again!";
		}
		
		return result;
	}
	
	//validate and update appointment to database
	public static String updateAppointment(Appointment appointment) {

		String result = SUCCESS;
		
		if(appointment == null)
			return "Appointment must be selected\nPlease try again!";
		
		//check patient is valid
		if(appointment.getPatient() == null)
			//error message
			return "Patient Id is not existed\nPlease try again!";
		
		//check Appointment Date in future for 'book' appointment only
		if(appointment.getStatus().equals(Appointment.STATUS_BOOK) &&
				appointment.getAppointmentTime().getTime() < 
				(new Date()).getTime())
			//error message
			return "Appointment cannot in the past\nPlease try again!";
		
		//update and check
		if(appointmentDAO.updateAppointment(appointment) == false) {
			result = "Cannot update an appointment.\nPlease try again!";//error message
		}
		
		return result;
	}
	
}
