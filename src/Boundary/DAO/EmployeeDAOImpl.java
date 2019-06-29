package Boundary.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Entity.Employee;

public class EmployeeDAOImpl {
	
	//CREATE
	public int addEmployee(Employee e) {
		//Initialize variables
		SessionFactory fx = null;
		Session sx = null;
		Transaction tx = null;
		
		Integer employeeId = -1;
		
		try {
			fx = HibernateFactory.getFactory();
			sx = fx.openSession();
			tx = sx.beginTransaction();
			
			//create an employee
			employeeId = (Integer) sx.save(e);
			
			tx.commit();
			
		}catch(HibernateException hx) {
			if(tx != null) tx.rollback();
			System.err.println(hx.getMessage());
		}finally {
			sx.close();
			fx.close();
		}
		
		return employeeId;
	}
	
	//GET an employee by id
	public Employee getEmployeeById(int id) {
		//Initialize variables
		SessionFactory fx = null;
		Session sx = null;
		
		Employee employee = null;
		
		try {
			fx = HibernateFactory.getFactory();
			sx = fx.openSession();
			
			//get employee
			employee = sx.get(Employee.class, id);
			
		}catch(HibernateException hx) {
			System.err.println(hx.getMessage());
		}finally {
			sx.close();
			fx.close();
		}
		
		return employee;
	}
	
	//GET an employee by email
	public Employee getEmployeeByEmail(String email) {
		//Initialize variables
		SessionFactory fx = null;
		Session sx = null;
		Transaction tx = null;
		
		List<Employee> employees = null;
		
		try {
			fx = HibernateFactory.getFactory();
			sx = fx.openSession();
			tx = sx.beginTransaction();
			
			//get employees have matched email
			//assume email is unique before creating an employee
			employees = (ArrayList<Employee>) 
					sx.createQuery("FROM Employee E WHERE E.email = '" + email+"'").list(); 
			
		}catch(HibernateException hx) {
			System.err.println(hx.getMessage());
		}finally {
			sx.close();
			fx.close();
		}
		
		//return the matched employee, else return null
		return employees.size() == 1? employees.get(0) : null;
	}
	
	//GET all employees
	public ArrayList<Employee> getAllEmployees() {
		//Initialize variables
		SessionFactory fx = null;
		Session sx = null;
		Transaction tx = null;
		
		ArrayList<Employee> employees = null;
		
		try {
			fx = HibernateFactory.getFactory();
			sx = fx.openSession();
			tx = sx.beginTransaction();
			
			//get all employees
			employees = (ArrayList<Employee>) 
					sx.createQuery("FROM Employee").list(); 
			
		}catch(HibernateException hx) {
			System.err.println(hx.getMessage());
		}finally {
			sx.close();
			fx.close();
		}
		
		//return employees
		return employees;
	}
	
	//UPDATE 
	public boolean updateEmployee(Employee e) {
		//Initialize variables
		SessionFactory fx = null;
		Session sx = null;
		Transaction tx = null;
		
		boolean result = true;
		
		try {
			fx = HibernateFactory.getFactory();
			sx = fx.openSession();
			tx = sx.beginTransaction();
			
			//update an employee
			sx.update(e);
			
			tx.commit();
			
		}catch(HibernateException hx) {
			if(tx != null) tx.rollback();
			result = false;
			System.err.println(hx.getMessage());
		}finally {
			sx.close();
			fx.close();
		}
		
		return result;
	}
	
	//DELETE 
	public boolean deleteEmployee(Employee e) {
		//Initialize variables
		SessionFactory fx = null;
		Session sx = null;
		Transaction tx = null;
		
		boolean result = true;
		
		try {
			fx = HibernateFactory.getFactory();
			sx = fx.openSession();
			tx = sx.beginTransaction();
			
			//delete an employee
			sx.delete(e);
			
			tx.commit();
			
		}catch(HibernateException hx) {
			if(tx != null) tx.rollback();
			result = false;
			System.err.println(hx.getMessage());
		}finally {
			sx.close();
			fx.close();
		}
		
		return result;
	}
	
}
