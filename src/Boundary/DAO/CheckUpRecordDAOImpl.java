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
	
	//GET all records in queue or in progress
	public ArrayList<CheckUpRecord> getAllCheckUpRecordsInQueueOrInProgress() {
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
					sx.createQuery("FROM CheckUpRecord WHERE status IN (:state)")
					.setParameterList("state", new String[] {
							CheckUpRecord.STATUS_QUEUE,
							CheckUpRecord.STATUS_IN_PROGRESS
					})
					.list(); 
			
		}catch(HibernateException hx) {
			System.err.println(hx.getMessage());
		}finally {
			sx.close();
			fx.close();
		}
		
		//return all checkUpRecords
		return checkUpRecords;
	}
	
	//GET all history records
	public ArrayList<CheckUpRecord> getAllHistoryCheckUpRecords() {
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
					sx.createQuery("FROM CheckUpRecord WHERE status IN (:state)")
					.setParameterList("state", new String[] {
							CheckUpRecord.STATUS_DONE,
							CheckUpRecord.STATUS_CANCEL
					})
					.list(); 
			
		}catch(HibernateException hx) {
			System.err.println(hx.getMessage());
		}finally {
			sx.close();
			fx.close();
		}
		
		//return all checkUpRecords
		return checkUpRecords;
	}
	
	//GET all records of a patient 
	public ArrayList<CheckUpRecord> getAllCheckUpRecordsByPatientId(int patientId) {
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
					sx.createQuery("FROM CheckUpRecord WHERE patientId = :patientId AND status IN (:state)")
					.setParameter("patientId", patientId)
					.setParameterList("state", new String[] {
							CheckUpRecord.STATUS_DONE
					})
					.list(); 
			
		}catch(HibernateException hx) {
			System.err.println(hx.getMessage());
		}finally {
			sx.close();
			fx.close();
		}
		
		//return all checkUpRecords
		return checkUpRecords;
	}
	
	//GET a record
	public CheckUpRecord getCheckUpRecordById(int id) {
		//Initialize variables
		SessionFactory fx = null;
		Session sx = null;
		
		CheckUpRecord checkUpRecord = null;
		
		try {
			fx = HibernateFactory.getFactory();
			sx = fx.openSession();
			
			//get checkUpRecord
			checkUpRecord = sx.get(CheckUpRecord.class, id);
			
		}catch(HibernateException hx) {
			System.err.println(hx.getMessage());
		}finally {
			sx.close();
			fx.close();
		}
		
		return checkUpRecord;
	}
	
	//GET a record in queue
	public CheckUpRecord getCheckUpRecordInQueueById(int id) {
		//Initialize variables
		SessionFactory fx = null;
		Session sx = null;
		Transaction tx = null;
		
		CheckUpRecord checkUpRecord = null;
		
		try {
			fx = HibernateFactory.getFactory();
			sx = fx.openSession();
			tx = sx.beginTransaction();
			
			//get a checkUpRecord and lock the row
			checkUpRecord = sx.get(CheckUpRecord.class, id);
			
			//check if status = 'queue'
			if(checkUpRecord.getStatus().equals(CheckUpRecord.STATUS_QUEUE)) {
				
				//change status to 'checkup'
				checkUpRecord.setStatus(CheckUpRecord.STATUS_IN_PROGRESS);
				
				//update database to prevent other doctor see it 
				sx.save(checkUpRecord);
			}else//return null
				checkUpRecord = null;
			
			tx.commit();
		}catch(HibernateException hx) {
			if(tx != null) tx.rollback();
			System.err.println(hx.getMessage());
		}finally {
			sx.close();
			fx.close();
		}
		
		return checkUpRecord;
	}
	
	//UPDATE 
	public boolean updateCheckUpRecord(CheckUpRecord checkUpRecord) {
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
			sx.update(checkUpRecord);
			
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
