package SystemTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Date;
import Boundary.DAO.EmployeeDAOImpl;
import Boundary.Helpers.DateTimeHelper;
import Entity.Employee;

/**
 * 
 * @author Beau (10635)
 * This class will test out the CRUD Operations for
 * the employees.
 * @version 0.3 - Re-did the class, based it on Hau Nguyen's
 * design (with permission).
 */
class EmployeeCRUDTest {	
	private EmployeeDAOImpl empDAO = new EmployeeDAOImpl();
	// Creating the fields for testing.
	private final String 	firstName ="Bob",
							lastName = "Smith",
							phone = "111-222-333-4444",							
							email = "bobsmith@somewhereawesome.com",
							address = "11111 Someplace Way,\n"
									   + "Somewhere, Nowhere, Canada (Up North";
	private final Date dob = DateTimeHelper.getDateFromString("1977-01-01 14:30:00");	
		
	@Test
	void addEmployee() {
		Employee e = new Employee();
		System.out.println("Beginning CREATE an employee test.");		
		e.setFirstName(firstName);
		e.setLastName(lastName);
		e.setPhone(phone);							
		e.setEmail(email);
		e.setAddress(address);
		e.setGender(Employee.GENDER_MALE);
		e.setDob(dob);
		// Add an employee to the database.
		int recentEmpID = empDAO.addEmployee(e);
		assertTrue(recentEmpID != EmployeeDAOImpl.ERROR_ADD);		
	}
	
	@Test
	void readEmployee() {
		Employee e = new Employee();
		System.out.println("Begginning test sequence to READ an employee.");
		e.setFirstName(firstName);
		e.setLastName(lastName);
		e.setPhone(phone);							
		e.setEmail(email);
		e.setAddress(address);
		e.setGender(e.GENDER_MALE);
		e.setDob(dob);
		
		// Add Employee to the database.
		int recentEmpID = empDAO.addEmployee(e);
		assertTrue(recentEmpID != EmployeeDAOImpl.ERROR_ADD);
		
		// Read recent added Employee from the database.
		e = empDAO.getEmployeeById(recentEmpID);
		
		// Asserts fields to see if the current
		// employee is on the database.
		assertTrue(e.getFirstName().equals(firstName));
		assertTrue(e.getLastName().equals(lastName));
		assertTrue(e.getPhone().equals(phone));
		assertTrue(e.getEmail().equals(email));
		assertTrue(e.getAddress().equals(address));
		assertTrue(e.getGender() == e.GENDER_MALE);
		assertTrue(e.getDob().equals(dob));		
	}
	
	@Test
	void updateEmployee() {
		Employee e = new Employee();
		System.out.println("Testing an UPDATE for an employee.");
		// Create an employee.
		e.setFirstName(firstName);
		e.setLastName(lastName);
		e.setPhone(phone);							
		e.setEmail(email);
		e.setAddress(address);
		e.setGender(e.GENDER_MALE);
		e.setDob(dob);
		
		// Add an employee to the database.
		int recentEmpID = empDAO.addEmployee(e);
		assertTrue(recentEmpID != EmployeeDAOImpl.ERROR_ADD);
		
		// Read the recent employee from the database. 
		e = empDAO.getEmployeeById(recentEmpID);
		
		// Set some new values for the Employee object
		// 'e'
		e.setFirstName(firstName + "new");
		e.setLastName(lastName + "new");
		e.setPhone(phone + "new");							
		e.setEmail(email + "new");
		e.setAddress(address + "new");
		e.setGender(e.GENDER_MALE);
		e.setDob(DateTimeHelper.getDateFromString("1985-01-31 15:00:00"));
		// Create a boolean variable to varify the result.
		boolean empResult = empDAO.updateEmployee(e);
		// Verify if the condition is true.
		assertTrue(empResult);
		
		// Read recently updated employee.
		e = empDAO.getEmployeeById(recentEmpID);
		
		// Assert to see the recently updated object
		// is updated from the previous Employee object.
		assertTrue(e.getFirstName().equals(firstName + "new"));
		assertTrue(e.getLastName().equals(lastName + "new"));
		assertTrue(e.getPhone().equals(phone + "new"));
		assertTrue(e.getEmail().equals(email + "new"));
		assertTrue(e.getAddress().equals(address + "new"));
		assertTrue(e.getGender() == e.GENDER_FEMALE);
		assertTrue(e.getDob().equals(DateTimeHelper.getDateFromString("1985-01-31 15:00:00")));	
	}
	
	@Test
	void deleteEmployee() {
		Employee e = new Employee();
		System.out.println("Beginning delete test for employee.");
		// Create an employee.
		e.setFirstName(firstName);
		e.setLastName(lastName);
		e.setPhone(phone);							
		e.setEmail(email);
		e.setAddress(address);
		e.setDob(dob);
		
		// Add an employee to database.
		int recentEmpID = empDAO.addEmployee(e);
		assertTrue(recentEmpID != EmployeeDAOImpl.ERROR_ADD);
		
		// Read a recently added employee to database.		
		Employee recentEmp = empDAO.getEmployeeById(recentEmpID);
		assertTrue(recentEmp != null, "should get Employee object.");
		
		// Delete recent added Employee
		boolean delEmpResult = empDAO.deleteEmployee(e);
		assertTrue(delEmpResult);
		
		// Read recently deleted Employee.
		Employee deletedEmp = empDAO.getEmployeeById(recentEmpID);
		assertTrue(deletedEmp == null, "Employee object should not be present.");
	}	
}
