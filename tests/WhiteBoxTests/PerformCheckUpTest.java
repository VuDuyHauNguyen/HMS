package WhiteBoxTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Boundary.DAO.HibernateFactory;
import Controller.AppointmentController;
import Controller.CheckUpRecordController;
import Controller.EmployeeController;
import Entity.Appointment;
import Entity.CheckUpRecord;

class PerformCheckUpTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		//generate mock-up database
		HibernateFactory.initializeDatabase();
	}

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	void testDoctorPerformCheckUp() {
		
		//get today appointment (ID 101-110)
		int appointmentId = 101;
		Appointment appointment = AppointmentController.getAppointmentById(appointmentId);
		
		assertTrue(appointment != null); 
		
		//check-in appointment
		AppointmentController.checkInAppointment(appointment, "headache");
		
		//should change status to 'done'
		assertTrue(AppointmentController.getAppointmentById(appointmentId).getStatus().equals(Appointment.STATUS_DONE));
		
		//should create a record
		CheckUpRecord checkUpRecord = CheckUpRecordController.getCheckUpRecordById(appointmentId);
		assertTrue(checkUpRecord != null);
		
		//select then change status to 'checkup'
		checkUpRecord = CheckUpRecordController.getCheckUpRecordInQueueById(appointmentId);
		assertTrue(checkUpRecord != null);
		assertTrue(checkUpRecord.getStatus().equals(CheckUpRecord.STATUS_IN_PROGRESS) == true);
		
		//perform check-up, fill data to record
		checkUpRecord.setDoctor(EmployeeController.getEmployeeById(2));
		checkUpRecord.setCheckUpRecordTime(new Date());
		checkUpRecord.setCheckupResult("abc");
		checkUpRecord.setPrescriptions("xyz");
		
		//before should not have 
		assertTrue(checkUpRecord.getStatus().equals(CheckUpRecord.STATUS_DONE) == false);
		
		//finish
		CheckUpRecordController.finishCheckUpRecord(checkUpRecord);
		
		assertTrue(checkUpRecord.getStatus().equals(CheckUpRecord.STATUS_DONE) == true);
		
	}
	
	@Test
	void testDoctorCancelCheckUp() {
		
		//get today appointment (ID 101-110)
		int appointmentId = 102;
		Appointment appointment = AppointmentController.getAppointmentById(appointmentId);
		
		assertTrue(appointment != null); 
		
		//check-in appointment
		AppointmentController.checkInAppointment(appointment, "back pain");
		
		//should change status to 'done'
		assertTrue(AppointmentController.getAppointmentById(appointmentId).getStatus().equals(Appointment.STATUS_DONE));
		
		//should create a record
		CheckUpRecord checkUpRecord = CheckUpRecordController.getCheckUpRecordById(appointmentId);
		assertTrue(checkUpRecord != null);
		assertTrue(checkUpRecord.getStatus().equals(CheckUpRecord.STATUS_QUEUE) == true);
		
		//select then change status to 'checkup'
		checkUpRecord = CheckUpRecordController.getCheckUpRecordInQueueById(appointmentId);
		assertTrue(checkUpRecord != null);
		assertTrue(checkUpRecord.getStatus().equals(CheckUpRecord.STATUS_IN_PROGRESS) == true);
		
		//cancel
		CheckUpRecordController.cancelCheckUpRecord(checkUpRecord);
		
		//status change to queue again
		assertTrue(CheckUpRecordController.getCheckUpRecordById(appointmentId).getStatus()
				.equals(CheckUpRecord.STATUS_QUEUE) == true);
		
	}

}
