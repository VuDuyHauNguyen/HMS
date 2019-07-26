package Controller;

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
}
