package UnitAndIntegrationTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Boundary.DAO.CheckUpRecordDAOImpl;
import Boundary.DAO.AppointmentDAOImpl;
import Entity.Appointment;
import Entity.CheckUpRecord;

/**
 * This class will test out the "Check up" record
 * method for the Hospital Management System app.
 * 
 * @author Beau (10635)
 * @version 0.1
 * 
 * @see [Line 42] To demonstrate this class, please change
 * the value from 1030 to a higher vlaue
 */
class CheckInAnAppointmentTest {
	CheckUpRecordDAOImpl chkUpAppt;
	CheckUpRecord checkRecord;
	Appointment appt;
	
	@BeforeEach
	void setup() {
		chkUpAppt = new CheckUpRecordDAOImpl();
		checkRecord = new CheckUpRecord();
		appt = new Appointment();
	}
	
	@Test
	void addRecord() {		
		
		checkRecord.setMedicalProblem("Strep Throat");
		checkRecord.setCheckupResult("Confirmed");
		
		// Change the value to help further demonstrate
		// this method and the "checkupRecord()" method.
		// as well.
		checkRecord.setId(1030);
		
		chkUpAppt.addCheckUpRecord(checkRecord);		
	}
	
	@Test
	void checkRecord() {		
		chkUpAppt.getAllCheckUpRecords();
	}
}
