package Controller;

import javax.swing.JOptionPane;

import Boundary.EmployeeDAOImpl;
import Entity.Employee;

public final class Authentication {
	
	public final static int FAIL = 0;
	
	private static Employee employee = null;

	
	private static EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
	
	
	public static int login(String email, String password) {
		
		employee = employeeDAO.getEmployeeByEmail(email);
		
		if(employee ==  null)
			return FAIL;
		else {//found employee 
			if(password.equals(employee.getPassword()))
				return employee.getRole();
			else {
				employee = null;//set employee to null
				return FAIL;
			}
		}
	}
	
	public static Employee getLoggedInEmployee() {
		return employee;
	}

	public static void logout() {
		employee = null;//set employee reference to null
	}
}
