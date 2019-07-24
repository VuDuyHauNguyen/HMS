package SystemTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Boundary.DAO.EmployeeDAOImpl;
import Entity.Employee;

/**
 * 
 * @author Beau (10635)
 * This class will test out the CRUD Operations for
 * the employees.
 * @version 0.2 - Added the appropriate assertTrue methods for testing.
 */
class EmployeeCRUDTest {
	private Employee e = new Employee();
	private EmployeeDAOImpl empDAO = new EmployeeDAOImpl();
	
	@BeforeEach
	void setUp() throws Exception {
		empDAO = new EmployeeDAOImpl();		
	}
	
	@Test
	void addEmployee() {
		int newEmpID = empDAO.addEmployee(e);
		Employee recentAddedEmp = new Employee();
		recentAddedEmp = empDAO.getEmployeeById(newEmpID);
		
		e.setFirstName("Bob");
		assertTrue(recentAddedEmp.getFirstName() == "Bob");
		e.setLastName("Smith");
		assertTrue(recentAddedEmp.getLastName().equals("Smith"));
		e.setAddress("11111 Someplace Way,\n"
				   + "Somewhere, Nowhere, Canada (Up North");
		assertTrue(recentAddedEmp.getAddress()
				.equals("11111 Someplace Way,\n"
				+ "Somewhere, Nowhere, Canada (Up North"));
		e.setDob(null);
		assertTrue(recentAddedEmp.getDob().equals(null));
		e.setAppointments(null);
		assertTrue(recentAddedEmp.getAppointments().equals(null));
		e.setEmail("bobsmith@somewhereawesome.com");
		assertTrue(recentAddedEmp.getEmail().equals("bobsmith@somewhereawesome.com"));
		e.setGender('m');
		assertTrue(recentAddedEmp.getGender() == 'm');
		e.setPhone("111-222-333-4444");
		assertTrue(recentAddedEmp.getPhone().equals("111-222-333-4444"));
		empDAO.addEmployee(e);
		assertEquals(empDAO.addEmployee(recentAddedEmp), true);
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
		assertTrue(empDAO.deleteEmployee(e) == true);
	}
	
	@Test
	void addEmployeeForUpdating() {
		int newEmpID = empDAO.addEmployee(e);
		Employee recentAddedEmp = 
		empDAO.getEmployeeById(newEmpID);
		
		e.setFirstName("Bill");
		assertTrue(recentAddedEmp.getFirstName() == "Bill");
		e.setLastName("Swanson");
		assertTrue(recentAddedEmp.getLastName() == "Swanson");
		e.setAddress("11111 Area 51,\n"
				   + "Somewhere, Nowhere, United States");
		assertTrue(recentAddedEmp.getAddress() == "11111 Area 51,\n"
				   + "Somewhere, Nowhere, United States");
		e.setDob(null);
		assertTrue(recentAddedEmp.getDob() == null);
		e.setAppointments(null);
		assertTrue(recentAddedEmp.getAppointments() == null);
		e.setEmail("bswanson@someemail.org");
		assertTrue(recentAddedEmp.getEmail() == "bswanson@someemail.org");
		e.setGender('m');
		assertTrue(recentAddedEmp.getGender() == 'm');
		e.setPhone("555-666-777-888-9999");
		assertTrue(recentAddedEmp.getPhone() == "555-666-777-888-9999");		
		
		// Add the employee for updating.
		empDAO.addEmployee(e);
	}
	@Test
	void updateEmployee() {		
		int newEmpID = empDAO.addEmployee(e);
		Employee recentAddedEmp = 
		empDAO.getEmployeeById(newEmpID);
		e.setPhone("505-616-727-838-9449");		
		assertTrue(recentAddedEmp.getPhone() == "505-616-727-838-9449");
	}	
}
