package Controller;

import java.util.ArrayList;
import Boundary.DAO.EmployeeDAOImpl;
import Entity.Employee;

public class EmployeeController {

	public static final String SUCCESS = "Success";
	
	private static EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl(); 
	
	//validate and add employee to database
	public static String addEmployee(Employee emp) {
		
		String result = SUCCESS;
		
		//check email is not used
		if(employeeDAO.getEmployeeByEmail(emp.getEmail()) != null) {
			return "Email is used.\nPlease use another email!";//error message
		}
		
		//else add
		int newEmpId = employeeDAO.addEmployee(emp);
		
		//check
		if(newEmpId < 0) {
			result = "Cannot create an employee.\nPlease try again!";//error message
		}
		
		return result;
	}
	
	//get an employee by Id
	public static Employee getEmployeeById(int id) {
		
		return employeeDAO.getEmployeeById(id);
	}
	
	//get all employees
	public static ArrayList<Employee> getAllEmployees(){
		
		return employeeDAO.getAllEmployees();
	}
	
	//validate and update employee
	public static String updateEmployee(Employee emp) {
		
		String result = SUCCESS;
		
		//check email is not used and email is different with current email of emp
		Employee tmpEmp = employeeDAO.getEmployeeByEmail(emp.getEmail());
		if(tmpEmp != null && tmpEmp.getId() != emp.getId()) {
				return "Email is used.\nPlease use another email!";//error message
		}

		if(employeeDAO.updateEmployee(emp) == false)
			result = "Cannot update the employee.\nPlease try again!";
		
		return result;
	}
}
