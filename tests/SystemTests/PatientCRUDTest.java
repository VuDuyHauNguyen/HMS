package SystemTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

import Boundary.DAO.PatientDAOImpl;
import Boundary.Helpers.DateTimeHelper;
import Entity.Patient;

class PatientCRUDTest {

	private PatientDAOImpl patientDAO = new PatientDAOImpl();
	
	//testings fields values
	private final String 	firstName = "John",
							lastName = "Doe",
							phone = "777-888-9999",
							email = "patient@hms.com",
							address = "7788 Douglas Ave, Vancouver, BC";
	private final Date dob = DateTimeHelper.getDateFromString("2000-08-07 00:00:00");	
	
    
    @Test
	void testCreatePatient() {
    	System.out.println("Executing CREATE a patient test");
		//create a patient
		Patient patient = new Patient();
		patient.setFirstName(firstName);
		patient.setLastName(lastName);
		patient.setPhone(phone);
		patient.setEmail(email);
		patient.setAddress(address);
		patient.setGender(Patient.GENDER_MALE);
		patient.setDob(dob);
		
		//add a patient to database
		int  recentPatientId = patientDAO.addPatient(patient);
		assertTrue(recentPatientId != PatientDAOImpl.ERROR_ADD);
	}
    
    @Test
    void testReadPatient() {
    	System.out.println("Executing READ a patient test");
    	//create a patient
		Patient patient = new Patient();
		patient.setFirstName(firstName);
		patient.setLastName(lastName);
		patient.setPhone(phone);
		patient.setEmail(email);
		patient.setAddress(address);
		patient.setGender(Patient.GENDER_MALE);
		patient.setDob(dob);
		
		//add a patient to database
		int  recentPatientId = patientDAO.addPatient(patient);
		assertTrue(recentPatientId != PatientDAOImpl.ERROR_ADD);
		
		//read recent added patient from database
		Patient recentPatient = patientDAO.getPatientById(recentPatientId);
		
		//asserts all fields are matched with set fields
		assertTrue(recentPatient.getFirstName().equals(firstName));
		assertTrue(recentPatient.getLastName().equals(lastName));
		assertTrue(recentPatient.getPhone().equals(phone));
		assertTrue(recentPatient.getEmail().equals(email));
		assertTrue(recentPatient.getAddress().equals(address));
		assertTrue(recentPatient.getGender() == Patient.GENDER_MALE);
		assertTrue(recentPatient.getDob().equals(dob));
    }
    
    @Test
    void testUpdatePatient() {
    	System.out.println("Executing UPDATE a patient test");
    	//create a patient
		Patient patient = new Patient();
		patient.setFirstName(firstName);
		patient.setLastName(lastName);
		patient.setPhone(phone);
		patient.setEmail(email);
		patient.setAddress(address);
		patient.setGender(Patient.GENDER_MALE);
		patient.setDob(dob);
		
		//add a patient to database
		int  recentPatientId = patientDAO.addPatient(patient);
		assertTrue(recentPatientId != PatientDAOImpl.ERROR_ADD);
		
		//read recent added patient from database
		Patient recentPatient = patientDAO.getPatientById(recentPatientId);
		
		//set new values to the recentPatient
		recentPatient.setFirstName(firstName + "new");
		recentPatient.setLastName(lastName + "new");
		recentPatient.setPhone(phone + "new");
		recentPatient.setEmail(email + "new");
		recentPatient.setAddress(address + "new");
		recentPatient.setGender(Patient.GENDER_FEMALE);
		recentPatient.setDob(DateTimeHelper.getDateFromString("2020-09-09 00:00:00"));
		
		boolean updateResult = patientDAO.updatePatient(recentPatient);
		
		//update should return true if success
		assertTrue(updateResult);
		
		//read recent updated patient from database
		Patient recentUpdatedPatient = patientDAO.getPatientById(recentPatientId);
		
		//asserts all fields are matched with set fields
		assertTrue(recentUpdatedPatient.getFirstName().equals(firstName + "new"));
		assertTrue(recentUpdatedPatient.getLastName().equals(lastName + "new"));
		assertTrue(recentUpdatedPatient.getPhone().equals(phone + "new"));
		assertTrue(recentUpdatedPatient.getEmail().equals(email + "new"));
		assertTrue(recentUpdatedPatient.getAddress().equals(address + "new"));
		assertTrue(recentUpdatedPatient.getGender() == Patient.GENDER_FEMALE);
		assertTrue(recentUpdatedPatient.getDob().equals(DateTimeHelper.getDateFromString("2020-09-09 00:00:00")));
    }
    
    @Test
    void testDeletePatient() {
    	System.out.println("Executing DELETE a patient test");
    	//create a patient
		Patient patient = new Patient();
		patient.setFirstName(firstName);
		patient.setLastName(lastName);
		patient.setPhone(phone);
		patient.setEmail(email);
		patient.setAddress(address);
		patient.setGender(Patient.GENDER_MALE);
		patient.setDob(dob);
		
		//add a patient to database
		int  recentPatientId = patientDAO.addPatient(patient);
		assertTrue(recentPatientId != PatientDAOImpl.ERROR_ADD);
		
		//read recent added patient from database
		Patient recentPatient = patientDAO.getPatientById(recentPatientId);
		assertTrue(recentPatient != null, "should get a patient object");
		
		//delete recent added patient from database
		boolean deleteResult = patientDAO.deletePatient(recentPatient);
		assertTrue(deleteResult);
		
		//read recent deleted patient from database
		Patient deletedPatient = patientDAO.getPatientById(recentPatientId);
		assertTrue(deletedPatient == null, "should not get a patient object");
    }
    

}
