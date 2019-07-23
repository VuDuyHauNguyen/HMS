package WhiteBoxTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Boundary.DAO.AppointmentDAOImpl;
import Entity.Appointment;

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
	}
	
	@Test
	void checkAppoitnment() {
		
	}
}
