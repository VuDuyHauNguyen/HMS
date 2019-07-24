package WhiteBoxTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Boundary.DAO.AppointmentDAOImpl;
import Entity.Appointment;
/**
 * 
 * @author Beau (10635)
 * @version 0.2 - Added the appropriate assertTrue methods, plus an
 * additional assertEquals() method as well, for testing.
 *
 */
public class CreateAnAppointmentTest {
	Appointment appt;
	AppointmentDAOImpl apptDAO;

	@BeforeEach
	void setUp() throws Exception {
		apptDAO = new AppointmentDAOImpl();
		appt = new Appointment();
	}

	@Test
	void createAppoitnment() {		 
		apptDAO.addAppointment(appt);		
		assertEquals(apptDAO.addAppointment(appt), true);
	}
}
