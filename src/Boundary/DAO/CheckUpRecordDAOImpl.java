package Boundary.DAO;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Entity.CheckUpRecord;

public class CheckUpRecordDAOImpl {
	
	//CREATE 
	public int addCheckUpRecord(CheckUpRecord checkUpRecord) {
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
			appInteger = (Integer) sx.save(checkUpRecord);
			
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
	
	//GET all records
	public ArrayList<CheckUpRecord> getAllCheckUpRecords() {
		//Initialize variables
		SessionFactory fx = null;
		Session sx = null;
		Transaction tx = null;
		
		ArrayList<CheckUpRecord> checkUpRecords = null;
		
		try {
			fx = HibernateFactory.getFactory();
			sx = fx.openSession();
			tx = sx.beginTransaction();
			
			//get all checkUpRecords
			checkUpRecords = (ArrayList<CheckUpRecord>) 
					sx.createQuery("FROM CheckUpRecord ORDER BY checkUpRecordTime").list(); 
			
		}catch(HibernateException hx) {
			System.err.println(hx.getMessage());
		}finally {
			sx.close();
			fx.close();
		}
		
		//return all checkUpRecords
		return checkUpRecords;
	}
	
}
