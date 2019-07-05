package Boundary.DAO;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Entity.Appointment;
import Entity.Employee;

public class AppointmentDAOImpl {
	
	//CREATE
	public int addAppointment(Appointment appointment) {
		//Initialize variables
		SessionFactory fx = null;
		Session sx = null;
		Transaction tx = null;
		
		Integer appInteger = -1;
		
		try {
			fx = HibernateFactory.getFactory();
			sx = fx.openSession();
			tx = sx.beginTransaction();
			
			//create an appointment
			appInteger = (Integer) sx.save(appointment);
			
			tx.commit();
			
		}catch(HibernateException hx) {
			if(tx != null) tx.rollback();
			System.err.println(hx.getMessage());
		}finally {
			sx.close();
			fx.close();
		}
		
		return appInteger;
	}
	
	//GET an appointment by id
	public Appointment getAppointmentById(int id) {
		//Initialize variables
		SessionFactory fx = null;
		Session sx = null;
		
		Appointment appointment = null;
		
		try {
			fx = HibernateFactory.getFactory();
			sx = fx.openSession();
			
			//get appointment
			appointment = sx.get(Appointment.class, id);
			
		}catch(HibernateException hx) {
			System.err.println(hx.getMessage());
		}finally {
			sx.close();
			fx.close();
		}
		
		return appointment;
	}
	
	//GET all appointments
	public ArrayList<Appointment> getAllAppointments() {
		//Initialize variables
		SessionFactory fx = null;
		Session sx = null;
		Transaction tx = null;
		
		ArrayList<Appointment> appointments = null;
		
		try {
			fx = HibernateFactory.getFactory();
			sx = fx.openSession();
			tx = sx.beginTransaction();
			
			//get all appointments
			appointments = (ArrayList<Appointment>) 
					sx.createQuery("FROM Appointment").list(); 
			
		}catch(HibernateException hx) {
			System.err.println(hx.getMessage());
		}finally {
			sx.close();
			fx.close();
		}
		
		//return appointments
		return appointments;
	}
	
	//UPDATE 
	public boolean updateAppointment(Appointment app) {
		//Initialize variables
		SessionFactory fx = null;
		Session sx = null;
		Transaction tx = null;
		
		boolean result = true;
		
		try {
			fx = HibernateFactory.getFactory();
			sx = fx.openSession();
			tx = sx.beginTransaction();
			
			//update an appointment
			sx.update(app);
			
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
	public boolean deleteAppointment(Appointment app) {
		//Initialize variables
		SessionFactory fx = null;
		Session sx = null;
		Transaction tx = null;
		
		boolean result = true;
		
		try {
			fx = HibernateFactory.getFactory();
			sx = fx.openSession();
			tx = sx.beginTransaction();
			
			//delete an appointment
			sx.delete(app);
			
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
