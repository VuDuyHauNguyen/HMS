package Controller;

import java.util.ArrayList;
import Boundary.DAO.PatientDAOImpl;
import Entity.Patient;

public class PatientController {
	
	public static final String SUCCESS = "Success";
	
	private static PatientDAOImpl patientDAO = new PatientDAOImpl(); 
	
	//validate and add patient to database
	public static String addPatient(Patient patient) {

		String result = SUCCESS;
		
		//add
		int newPatientId = patientDAO.addPatient(patient);
		
		//check
		if(newPatientId < 0) {
			result = "Cannot create a patient.\nPlease try again!";//error message
		}
		
		return result;
	}
	
	//get a patient by Id
	public static Patient getPatientById(int id) {
		
		return patientDAO.getPatientById(id);
	}
	
	//get all patients
	public static ArrayList<Patient> getAllPatients(){
		
		return patientDAO.getAllPatients();
	}
	
	//validate and update patient
	public static String updatePatient(Patient patient) {
		
		String result = SUCCESS;

		if(patientDAO.updatePatient(patient) == false)
			result = "Cannot update the patient.\nPlease try again!";
		
		return result;
	}
}
