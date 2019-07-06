package Boundary.DAO;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import Boundary.Helpers.FileHelper;
import Entity.Appointment;

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
		
		//list of SQL files need to execute
		String[] sqlFilePaths = {
				"./database/appointment.sql",
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
}
