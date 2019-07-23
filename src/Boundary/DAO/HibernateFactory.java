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
		
		//list of SQL files need to execute
		String[] sqlFilePaths = {
				"./database/appointment.sql",
				"./database/todayAndUpcomingAppointments.sql",
				"./database/checkUpRecord.sql",
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
