package UnitAndIntegrationTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import Boundary.DAO.HibernateFactory;
import Boundary.Helpers.DateTimeHelper;
import Controller.AppointmentController;
import Controller.CheckUpRecordController;
import Entity.Appointment;
import Entity.CheckUpRecord;

class CheckInMockUpDataAppointmentsTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		//generate mock-up database
		HibernateFactory.initializeDatabase();
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testCheckInTodayAppointments() {
		//past appointments have ID from 1-100 
		//today appointments have ID from 101-110
		//future appointments have ID from 111-120
		
		//test all today appointments
		for(int appointmentId = 101; appointmentId < 111; appointmentId++) {
			
			//check-in today appointment with medicalProblems
			//get today appointment
			Appointment todayAppointment = 
					AppointmentController.getAppointmentById(appointmentId);
			
			assertTrue(todayAppointment != null, "should get an appointment");
			assertTrue(DateTimeHelper.getDisplayDateFromDate(todayAppointment.getAppointmentTime())
					.equals(DateTimeHelper.getDisplayDateFromDate(new Date())), 
					"appointment date should equals today");
			assertTrue(todayAppointment.getStatus().equals(Appointment.STATUS_BOOK)||
					todayAppointment.getStatus().equals(Appointment.STATUS_CANCEL), 
					"status must be 'book' or 'cancel'");
			
			//check-in today appointment with null medical problem 
			String result = AppointmentController.checkInAppointment(todayAppointment, null);
			
			assertTrue(result.equals(AppointmentController.SUCCESS) == false);
			assertTrue(AppointmentController.getAppointmentById(appointmentId).getStatus()
					.equals(Appointment.STATUS_DONE) == false);
			//no new record
			assertTrue(CheckUpRecordController.getCheckUpRecordById(appointmentId) == null);
			
			//check-in today appointment with empty medical problem 
			result = AppointmentController.checkInAppointment(todayAppointment, "");
			
			assertTrue(result.equals(AppointmentController.SUCCESS) == false);
			assertTrue(AppointmentController.getAppointmentById(appointmentId).getStatus()
					.equals(Appointment.STATUS_DONE) == false);
			//no new record 
			assertTrue(CheckUpRecordController.getCheckUpRecordById(appointmentId) == null);
			
			//check-in today appointment with a medical problem 
			result = AppointmentController.checkInAppointment(todayAppointment, "high blood pressure");
			
			assertTrue(result.equals(AppointmentController.SUCCESS) == true);
			assertTrue(AppointmentController.getAppointmentById(appointmentId).getStatus()
					.equals(Appointment.STATUS_DONE) == true);
			//new record with status 'queue'
			assertTrue(CheckUpRecordController.getCheckUpRecordById(appointmentId) != null);
			assertTrue(CheckUpRecordController.getCheckUpRecordById(appointmentId).getStatus()
					.equals(CheckUpRecord.STATUS_QUEUE) == true);
			assertTrue(CheckUpRecordController.getCheckUpRecordById(appointmentId).getMedicalProblem()
					.equals("high blood pressure") == true);
		}
	}
	
	@Test
	void testCheckInPastAppointments() {
		//past appointments have ID from 1-100 
		//today appointments have ID from 101-110
		//future appointments have ID from 111-120
		
		//test all past appointments
		for(int appointmentId = 1; appointmentId < 101; appointmentId++) {
			
			//check-in past appointment with medicalProblems
			//get past appointment
			Appointment pastAppointment = 
					AppointmentController.getAppointmentById(appointmentId);
			
			assertTrue(pastAppointment != null, "should get an appointment");
			assertTrue(pastAppointment.getAppointmentTime().getTime() < (new Date()).getTime(), 
					"appointment date should less than today");
			assertTrue(pastAppointment.getStatus().equals(Appointment.STATUS_BOOK) == false, 
					"status cannot be 'book'");
			
			//check-in past appointment with null medical problem 
			String result = AppointmentController.checkInAppointment(pastAppointment, null);
			
			System.out.println("ID: " + appointmentId);
			
			assertTrue(result.equals(AppointmentController.SUCCESS) == false);
			//past appointment should not have status 'book'
			assertTrue(AppointmentController.getAppointmentById(appointmentId).getStatus()
					.equals(Appointment.STATUS_BOOK) == false);
			
			if(AppointmentController.getAppointmentById(appointmentId).getStatus()
					.equals(Appointment.STATUS_CANCEL))
				//no new record
				assertTrue(CheckUpRecordController.getCheckUpRecordById(appointmentId) == null);
			else if (AppointmentController.getAppointmentById(appointmentId).getStatus()
			.equals(Appointment.STATUS_DONE))
				//should have record
				assertTrue(CheckUpRecordController.getCheckUpRecordById(appointmentId) != null);
			
			//check-in past appointment with empty medical problem 
			result = AppointmentController.checkInAppointment(pastAppointment, "");
			
			assertTrue(result.equals(AppointmentController.SUCCESS) == false);
			assertTrue(AppointmentController.getAppointmentById(appointmentId).getStatus()
					.equals(Appointment.STATUS_BOOK) == false);
			if(AppointmentController.getAppointmentById(appointmentId).getStatus()
					.equals(Appointment.STATUS_CANCEL))
				//no new record
				assertTrue(CheckUpRecordController.getCheckUpRecordById(appointmentId) == null);
			else if (AppointmentController.getAppointmentById(appointmentId).getStatus()
			.equals(Appointment.STATUS_DONE))
				//should have record
				assertTrue(CheckUpRecordController.getCheckUpRecordById(appointmentId) != null);
			
			//check-in past appointment with a medical problem 
			result = AppointmentController.checkInAppointment(pastAppointment, "high blood pressure");
			
			assertTrue(result.equals(AppointmentController.SUCCESS) == false);
			assertTrue(AppointmentController.getAppointmentById(appointmentId).getStatus()
					.equals(Appointment.STATUS_BOOK) == false);
			if(AppointmentController.getAppointmentById(appointmentId).getStatus()
					.equals(Appointment.STATUS_CANCEL))
				//no new record
				assertTrue(CheckUpRecordController.getCheckUpRecordById(appointmentId) == null);
			else if (AppointmentController.getAppointmentById(appointmentId).getStatus()
			.equals(Appointment.STATUS_DONE))
				//should have record with status 'cancel' or 'done'
				assertTrue(CheckUpRecordController.getCheckUpRecordById(appointmentId) != null
						&& (CheckUpRecordController.getCheckUpRecordById(appointmentId).getStatus()
								.equals(CheckUpRecord.STATUS_CANCEL) || 
								CheckUpRecordController.getCheckUpRecordById(appointmentId).getStatus()
								.equals(CheckUpRecord.STATUS_DONE)
								));
		}
	}
	
	@Test
	void testCheckInFutureAppointments() {
		//past appointments have ID from 1-100 
		//today appointments have ID from 101-110
		//future appointments have ID from 111-120
		
		//test all future appointments
		for(int appointmentId = 111; appointmentId < 121; appointmentId++) {
			
			//check-in future appointment with medicalProblems
			//get future appointment
			Appointment futureAppointment = 
					AppointmentController.getAppointmentById(appointmentId);
			
			assertTrue(futureAppointment != null, "should get an appointment");
			assertTrue(futureAppointment.getAppointmentTime().getTime() > (new Date()).getTime(), 
					"appointment date should larger than today");
			assertTrue(futureAppointment.getStatus().equals(Appointment.STATUS_BOOK) ||
					futureAppointment.getStatus().equals(Appointment.STATUS_CANCEL), 
					"status must be 'book' or 'cancel'");
			
			//check-in future appointment with null medical problem 
			String result = AppointmentController.checkInAppointment(futureAppointment, null);
			
			assertTrue(result.equals(AppointmentController.SUCCESS) == false);
			assertTrue(AppointmentController.getAppointmentById(appointmentId).getStatus()
					.equals(Appointment.STATUS_DONE) == false);
			//no new record
			assertTrue(CheckUpRecordController.getCheckUpRecordById(appointmentId) == null);
			
			//check-in future appointment with empty medical problem 
			result = AppointmentController.checkInAppointment(futureAppointment, "");
			
			assertTrue(result.equals(AppointmentController.SUCCESS) == false);
			assertTrue(AppointmentController.getAppointmentById(appointmentId).getStatus()
					.equals(Appointment.STATUS_DONE) == false);
			//no new record 
			assertTrue(CheckUpRecordController.getCheckUpRecordById(appointmentId) == null);
			
			//check-in future appointment with a medical problem 
			result = AppointmentController.checkInAppointment(futureAppointment, "high blood pressure");
			
			assertTrue(result.equals(AppointmentController.SUCCESS) == false);
			assertTrue(AppointmentController.getAppointmentById(appointmentId).getStatus()
					.equals(Appointment.STATUS_DONE) == false);
			//no new record 
			assertTrue(CheckUpRecordController.getCheckUpRecordById(appointmentId) == null);
		}
	}

}
