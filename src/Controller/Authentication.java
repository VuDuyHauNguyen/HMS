package Controller;

import Boundary.MainForm;
import Boundary.DAO.EmployeeDAOImpl;
import Entity.Employee;

public final class Authentication {
	
	public final static int FAIL = 0;
	public final static boolean FAIL_AUTHENTICATION = false;
	public final static boolean SUCCESS_AUTHENTICATION = true;
	
	private static Employee employee = null;

	
	private static EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
	
	
	public static boolean login(String email, String password) {
		
		employee = employeeDAO.getEmployeeByEmail(email);
		
		if(employee ==  null) {
			MainForm.showMessage("Email does not exist or wrong password.\nPlease try again!");
			return FAIL_AUTHENTICATION;
		}
		else{//found employee 
			//check employee status
			if(employee.getStatus() == Employee.STATUS_DISABLE) {
				logout();//log the employee out
				MainForm.showMessage("The user is disable.\nPlease contact admin for help!");
				return FAIL_AUTHENTICATION;
			}else if(password.equals(employee.getPassword()))//check password
				return SUCCESS_AUTHENTICATION;
			else {
				employee = null;//set employee to null
				return FAIL_AUTHENTICATION;
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
