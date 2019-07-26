package Boundary.DAO;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Entity.Patient;

public class PatientDAOImpl {

	public static int ERROR_ADD = -1;
	
	//CREATE
	public int addPatient(Patient p) {
		//Initialize variables
		SessionFactory fx = null;
		Session sx = null;
		Transaction tx = null;
		
		Integer patientId = ERROR_ADD;
		
		try {
			fx = HibernateFactory.getFactory();
			sx = fx.openSession();
			tx = sx.beginTransaction();
			
			//create a patient
			patientId = (Integer) sx.save(p);
			
			tx.commit();
			
		}catch(Exception hx) {
			if(tx != null) tx.rollback();
			System.err.println(hx.getMessage());
		}finally {
			sx.close();
			fx.close();
		}
		
		return patientId;
	}
	
	//GET a patient by id
	public Patient getPatientById(int id) {
		//Initialize variables
		SessionFactory fx = null;
		Session sx = null;
		
		Patient patient = null;
		
		try {
			fx = HibernateFactory.getFactory();
			sx = fx.openSession();
			
			//get a patient
			patient = sx.get(Patient.class, id);
			
		}catch(Exception hx) {
			System.err.println(hx.getMessage());
		}finally {
			sx.close();
			fx.close();
		}
		
		return patient;
	}
	
	//GET all patients
	public ArrayList<Patient> getAllPatients() {
		//Initialize variables
		SessionFactory fx = null;
		Session sx = null;
		Transaction tx = null;
		
		ArrayList<Patient> patients = null;
		
		try {
			fx = HibernateFactory.getFactory();
			sx = fx.openSession();
			tx = sx.beginTransaction();
			
			//get all patients
			patients = (ArrayList<Patient>) 
					sx.createQuery("FROM Patient").list(); 
			
		}catch(Exception hx) {
			System.err.println(hx.getMessage());
		}finally {
			sx.close();
			fx.close();
		}
		
		//return patients
		return patients;
	}
	
	//UPDATE 
	public boolean updatePatient(Patient p) {
		//Initialize variables
		SessionFactory fx = null;
		Session sx = null;
		Transaction tx = null;
		
		boolean result = true;
		
		try {
			fx = HibernateFactory.getFactory();
			sx = fx.openSession();
			tx = sx.beginTransaction();
			
			//update a patient
			sx.update(p);
			
			tx.commit();
			
		}catch(Exception hx) {
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
	public boolean deletePatient(Patient p) {
		//Initialize variables
		SessionFactory fx = null;
		Session sx = null;
		Transaction tx = null;
		
		boolean result = true;
		
		try {
			fx = HibernateFactory.getFactory();
			sx = fx.openSession();
			tx = sx.beginTransaction();
			
			//delete a patient
			sx.delete(p);
			
			tx.commit();
			
		}catch(Exception hx) {
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
