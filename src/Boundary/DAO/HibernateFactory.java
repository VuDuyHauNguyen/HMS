package Boundary.DAO;

import java.util.Date;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import Boundary.MainForm;
import Boundary.Helpers.DateTimeHelper;
import Boundary.Helpers.FileHelper;
import Controller.Authentication;
import Entity.Appointment;
import Entity.Employee;
import Entity.Patient;

public class HibernateFactory {
	public static SessionFactory getFactory() {
		//Initialize variables
		StandardServiceRegistry ssr = null;
		Metadata meta = null;
		SessionFactory factory = null;
		
		//Start up and return the session factory
		try {
			ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
			meta = new MetadataSources(ssr).getMetadataBuilder().build();
			factory = meta.getSessionFactoryBuilder().build();
		}catch(Throwable ex) {
			System.err.println("Error: " + ex.getMessage());
		}
		
		return factory;
	}
	
	//Initialize database by execute sql files in database folder
	public static void initializeDatabase() {
		//Initialize variables
		SessionFactory fx = null;
		Session sx = null;
		Transaction tx = null;
		
		//create SQL files
		//create some today and upcoming appointments
		createTodayAndUpcomingAppointmentsSQLFile(
				"./database/todayAndUpcomingAppointments.sql");
		//create mock-up history check up records for past 'done' appointments
		createCheckUpRecordsSQLFile("./database/historyCheckUpRecords.sql");
		
		//list of SQL files need to execute
		String[] sqlFilePaths = {
				"./database/appointment.sql",
				"./database/todayAndUpcomingAppointments.sql",
				"./database/checkUpRecord.sql",
				"./database/historyCheckUpRecords.sql",
				"./database/employee.sql",
				"./database/patient.sql"};
		
		try {
			fx = getFactory();
			sx = fx.openSession();
			
			for(String path : sqlFilePaths) {
				tx = sx.beginTransaction();
				
				String sql = FileHelper.readFile(path);
				sql = sql.replace("\n", "").replace("\r", "");//remove new line
				
				//execute each SQL statement
				for(String command : sql.split(";")) 
					sx.createNativeQuery(command + ";").executeUpdate();
				
				tx.commit();
			}
			
		}catch(HibernateException hx) {
			if(tx != null) tx.rollback();
			System.err.println(hx.getMessage());
		}finally {
			sx.close();
			fx.close();
		}
		
	}
	
	private static void createCheckUpRecordsSQLFile(String filePath) {
		
		//create sample of common drugs, problems and diagnosis
		//https://www.medicinenet.com/top_drugs_prescribed_in_the_us/views.htm
		String[] drugs = {"Vicodin", "Simvastatin", "Lisinopril", "Levothyroxine", "Azithromycin", 
				"Metformin", "Lipitor", "Amlodipine", "Amoxicillin", "Hydrochlorothiazide"};
		String[] medicalProblems = {"body pain", "soft, yellowish skin growths", 
				"fatigue, shortness of breath", "depression, weight gain, memory loss",
				"cough, sore throat", "fatigue, blurred vision, and headaches", 
				"nausea, irregular heartbeat", "dizziness, squeezing sensation",
				"redness or inflammation of skin", "difficulty breathing, headache"
				};
		String[] diagnosis = {"acute or chronic moderate to moderately severe pain", 
				"high cholesterol", "congestive heart failure", "hypothyroidism", 
				"throat infection", "type 2 diabetes", "coronary artery disease", 
				"chest pain", "skin infections", "high blood pressure"};
		
		//patient Ids of 80 'done' appointments (Id: 21-100) in database/appointment.sql
		int[] patientIds = {19,19,6,1,10,10,16,12,5,17,14,19,15,15,19,17,13,13,
				10,6,11,1,6,14,1,12,3,9,18,4,19,15,11,10,9,12,4,18,1,11,4,4,8,11,
				6,10,17,13,20,15,17,1,8,13,11,19,5,5,6,4,10,5,17,16,18,20,17,18,4,
				14,16,7,20,10,4,18,19,7,8,4};//total 80
		
		//appointment times
		String[] appointmentTimes = {"2019-06-23 12:30:00","2019-04-12 12:30:00",
				"2018-12-13 12:30:00","2019-03-22 12:30:00","2019-06-05 12:30:00",
				"2018-08-01 12:30:00","2018-08-08 12:30:00","2019-03-16 12:30:00",
				"2019-03-02 12:30:00","2019-02-22 12:30:00","2018-08-26 12:30:00",
				"2019-04-25 12:30:00","2019-01-30 12:30:00","2018-07-29 12:30:00",
				"2019-07-19 12:30:00","2019-03-11 12:30:00","2018-12-06 12:30:00",
				"2018-08-20 12:30:00","2018-09-02 12:30:00","2019-01-20 12:30:00",
				"2018-12-08 12:30:00","2018-09-02 12:30:00","2018-10-17 12:30:00",
				"2019-07-06 12:30:00","2018-12-17 12:30:00","2018-10-11 12:30:00",
				"2019-04-05 12:30:00","2019-05-19 12:30:00","2019-02-20 12:30:00",
				"2019-06-04 12:30:00","2018-08-25 12:30:00","2019-06-03 12:30:00",
				"2018-09-04 12:30:00","2018-09-16 12:30:00","2018-10-07 12:30:00",
				"2019-06-13 12:30:00","2019-03-25 12:30:00","2019-07-18 12:30:00",
				"2018-11-06 12:30:00","2019-05-15 12:30:00","2019-03-03 12:30:00",
				"2019-03-16 12:30:00","2019-05-20 12:30:00","2019-07-01 12:30:00",
				"2019-02-27 12:30:00","2018-11-08 12:30:00","2018-12-17 12:30:00",
				"2019-02-08 12:30:00","2019-06-05 12:30:00","2019-06-15 12:30:00",
				"2019-05-26 12:30:00","2019-06-15 12:30:00","2018-10-22 12:30:00",
				"2018-11-19 12:30:00","2019-06-17 12:30:00","2018-08-28 12:30:00",
				"2019-04-23 12:30:00","2019-06-02 12:30:00","2019-01-04 12:30:00",
				"2019-06-22 12:30:00","2018-08-12 12:30:00","2018-10-21 12:30:00",
				"2019-07-13 12:30:00","2018-08-15 12:30:00","2019-07-07 12:30:00",
				"2019-04-26 12:30:00","2019-04-22 12:30:00","2019-01-22 12:30:00",
				"2019-05-10 12:30:00","2019-01-17 12:30:00","2018-12-13 12:30:00",
				"2018-09-02 12:30:00","2018-08-16 12:30:00","2018-10-16 12:30:00",
				"2018-09-21 12:30:00","2018-11-04 12:30:00","2019-02-18 12:30:00",
				"2019-01-08 12:30:00","2018-09-10 12:30:00","2018-12-14 12:30:00"};
		
		//doctor Ids in database/employee.sql
		int[] doctorIds = {1,5,6,8,9,10,15,19};//total 8
				
		//use appointment id as id of checkUpRecord
		//Ratio of checkUpRecord 10% cancel, 90% done
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for(int id=21; id<=100; id++) {
			
			//random checkUpRecord index from 0-9 predefined drugs, medicalProblems, diagnosis
			int recordIndex = rand.nextInt(10);
			
			if(rand.nextInt(100) > 9) {//90%
				sb.append("insert into checkuprecords (id, patientId, doctorId, checkUpRecordTime, medicalProblem, checkupResult, prescriptions, status) values ("
						+ id + ", "
						+ patientIds[id-21] + ","
						+ doctorIds[rand.nextInt(8)] + ",'"
						+ appointmentTimes[id-21].substring(0, 10) //date part
						+ " 13:00:00'"//time part (adding 30mins to appointmentTimes)
						+ ",'" + medicalProblems[recordIndex] + "'"
						+ ",'" + diagnosis[recordIndex] + "'"
						+ ",'" + drugs[recordIndex] + "'"
						+ ", 'done');");
			}else {//10%
				sb.append("insert into checkuprecords (id, patientId, medicalProblem, status) values ("
						+ id + ", "
						+ patientIds[id-21] + ", '"
						+ medicalProblems[rand.nextInt(10)]
						+ "', 'cancel');");
			}
			
			if(id<100)
				sb.append(System.getProperty("line.separator"));
		}
		
		FileHelper.saveFile(filePath, sb.toString());
	}
	
	//generate mock-up data for today and upcoming appointments and write to a file
	private static void createTodayAndUpcomingAppointmentsSQLFile(String filePath) {
		StringBuilder sb = new StringBuilder();
		
		//Patient ID range 1-20
		//Receptionist ID [7, 12, 13, 17, 18]
		
		int[] receptionistIds = {7, 12, 13, 17, 18};
		Random rand = new Random();
		
		//CREATE 10 TODAY APPOINTMENTS for patient Ids 1 to 10
		for(int i=0; i<10; i++) {
			sb.append("insert into appointments (patientId, receptionistId, appointmentTime, status) values ("
					+ (i + 1) + ", "
					+ receptionistIds[rand.nextInt(5)] + ", '"
					+ DateTimeHelper.getDisplayDateFromDate(new Date()) + " 13:00:00"
					+ "', 'book');");
			sb.append(System.getProperty("line.separator"));
		}
	
		//CREATE 10 UPCOMING APPOINTMENTS for patient Ids 11 to 20
		for(int i=0; i<10; i++) {
			sb.append("insert into appointments (patientId, receptionistId, appointmentTime, status) values ("
					+ (i + 11) + ", "
					+ receptionistIds[rand.nextInt(5)] + ", '"
					+ DateTimeHelper.getDisplayDateFromDate(new Date(
									(new Date()).getTime() 
										+ 86400000 // + 1 day  
										+ rand.nextInt(1209600000)// + max 14 days
									))  + " 14:00:00"
					+ "', 'book');");
			
			//do not add new line at the last line
			if(i < 9)
				sb.append(System.getProperty("line.separator"));
		}
		
		FileHelper.saveFile(filePath, sb.toString());
	}
}
