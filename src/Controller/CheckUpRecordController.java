package Controller;

import java.util.ArrayList;
import java.util.Date;

import Boundary.DAO.CheckUpRecordDAOImpl;
import Entity.CheckUpRecord;

public class CheckUpRecordController {

	public static final String SUCCESS = "Success";
	
	private static CheckUpRecordDAOImpl checkUpRecordDAO = new CheckUpRecordDAOImpl(); 

	
	//validate and add a check up record
	public static String addCheckUpRecord(CheckUpRecord checkUpRecord) {
		
		String result = SUCCESS;
		
		//validate minimum required fields
		if(checkUpRecord.getPatient() == null) 
			//error message
			return "Cannot create a check up record without a patient.\nPlease try again!";
		
		if(checkUpRecord.getId() == 0)
			//error message
			return "Cannot create a check up record without an Check Up ID.\nPlease try again!";
		
		if(checkUpRecord.getMedicalProblem() == null)
			//error message
			return "Cannot create a check up record without medical problem.\nPlease try again!";
		
		checkUpRecordDAO.addCheckUpRecord(checkUpRecord);
		
		return result;
	}
	
	//get all check up records in queue and in-progress
	public static ArrayList<CheckUpRecord> getAllCheckUpRecordsInQueueOrInProgress(){
		
		return checkUpRecordDAO.getAllCheckUpRecordsInQueueOrInProgress();
	}
	
	//get all check up records by patient id
	public static ArrayList<CheckUpRecord> getAllCheckUpRecordsByPatientId(int id){
		
		return checkUpRecordDAO.getAllCheckUpRecordsByPatientId(id);
	}
	
	//get all check up records by patient id
	public static ArrayList<CheckUpRecord> getAllHistoryCheckUpRecords(){
		
		return checkUpRecordDAO.getAllHistoryCheckUpRecords();
	}
	
	//get check up record in queue by id
	public static CheckUpRecord getCheckUpRecordInQueueById(int id) {
		
		return checkUpRecordDAO.getCheckUpRecordInQueueById(id);
	}
	
	//get check up record by id
	public static CheckUpRecord getCheckUpRecordById(int id){
		
		return checkUpRecordDAO.getCheckUpRecordById(id);
	}
	
	//finish check up record
	public static String finishCheckUpRecord(CheckUpRecord checkUpRecord) {
		
		String result = SUCCESS;
		
		if(checkUpRecord == null)
			return "Please select an check up!";
		
		//validate required fields
		if(checkUpRecord.getPatient() == null) 
			//error message
			return "Cannot done a check up record without a patient.\nPlease try again!";
		
		if(checkUpRecord.getDoctor() == null) 
			//error message
			return "Cannot done a check up record without a doctor.\nPlease try again!";
		
		if(checkUpRecord.getId() == 0)
			//error message
			return "Cannot done a check up record without a Check Up ID.\nPlease try again!";

		if(checkUpRecord.getMedicalProblem() == null ||
				checkUpRecord.getMedicalProblem().equals(""))
			//error message
			return "Cannot done a check up record without Medical Problem.\nPlease try again!";
		
		if(checkUpRecord.getCheckupResult() == null ||
				checkUpRecord.getCheckupResult().equals(""))
			//error message
			return "Cannot done a check up record without Diagnosis.\nPlease try again!";
		
		if(checkUpRecord.getPrescriptions() == null ||
				checkUpRecord.getPrescriptions().equals(""))
			//error message
			return "Cannot done a check up record without Prescriptions.\nPlease try again!";
		
		//set status to 'done'
		checkUpRecord.setStatus(CheckUpRecord.STATUS_DONE);
		
		//update final time
		checkUpRecord.setCheckUpRecordTime(new Date());
		
		if(checkUpRecordDAO.updateCheckUpRecord(checkUpRecord) == false)
			return "Cannot done a check up record\nPlease try again!";
		
		return result;
	}
	
	//cancel check up record
	public static String cancelCheckUpRecord(CheckUpRecord checkUpRecord) {
		
		String result = SUCCESS;
		
		if(checkUpRecord == null)
			return "Please select an check up!";
		
		//set status to 'queue'
		checkUpRecord.setStatus(CheckUpRecord.STATUS_QUEUE);
		
		if(checkUpRecordDAO.updateCheckUpRecord(checkUpRecord) == false)
			return "Cannot cancel a check up record\nPlease try again!";
		
		return result;
	}
}
