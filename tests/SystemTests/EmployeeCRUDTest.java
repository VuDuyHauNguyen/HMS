package SystemTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Boundary.DAO.EmployeeDAOImpl;
import Entity.Employee;

class EmployeeCRUDTest {
	Employee e = new Employee();
	private EmployeeDAOImpl empDAO;
	
	@BeforeEach
	void setUp() throws Exception {
		empDAO = new EmployeeDAOImpl();
	}

	/*@Test
	void test() {
		fail("Not yet implemented");
	}*/
	@Test
	void addEmployee() {
	
		e.setFirstName("Bob");
		e.setLastName("Smith");
		e.setAddress("11111 Someplace Way,\n"
				   + "Somewhere, Nowhere, Canada (Up North");
		e.setDob(null);
		e.setAppointments(null);
		e.setEmail("bobsmith@somewhereawesome.com");
		e.setGender('m');
		e.setPhone("111-222-333-4444");
		
		empDAO.addEmployee(e);
	}
	
	@Test
	void deleteEmployee() {
		
		e.setFirstName("Rob");
		e.setLastName("Smythe");
		e.setAddress("11111 Someplace Way,\n"
				   + "Somewhere, Nowhere, Canada (Up North");
		e.setDob(null);
		e.setAppointments(null);
		e.setEmail("robsmythe@somewhereawesome.com");
		e.setGender('f');
		e.setPhone("998-777-796-5654");
		
		empDAO.deleteEmployee(e);
	}
	
	@Test
	void addEmployeeForUpdating() {
		
		e.setFirstName("Bill");
		e.setLastName("Swanson");
		e.setAddress("11111 Area 51,\n"
				   + "Somewhere, Nowhere, United States");
		e.setDob(null);
		e.setAppointments(null);
		e.setEmail("bswanson@someemail.org");
		e.setGender('m');
		e.setPhone("555-666-777-888-9999");
		// Add the employee for updating.
		empDAO.addEmployee(e);
	}
	@Test
	void updateEmployee() {		
		
		e.setFirstName("Bill");
		e.setLastName("Swanson");
		e.setAddress("11111 Area 51,\n"
				   + "Utah, TN, United States");
		e.setDob(null);
		e.setAppointments(null);
		e.setEmail("bswanson@someemail.org");
		e.setGender('m');
		e.setPhone("505-616-727-838-9449");
		// Add the employee for updating.
		empDAO.addEmployee(e);	
	}
}
