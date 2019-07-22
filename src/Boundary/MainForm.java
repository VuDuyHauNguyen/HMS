package Boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import Boundary.DAO.HibernateFactory;
import Boundary.GUIComponents.AppointmentTabGUI;
import Boundary.GUIComponents.CheckUpTabGUI;
import Boundary.GUIComponents.EmployeeTabGUI;
import Boundary.GUIComponents.LoginDialog;
import Boundary.GUIComponents.PatientRecordsTabGUI;
import Boundary.GUIComponents.PatientTabGUI;
import Controller.Authentication;
import Entity.Employee;

import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainForm {
	private static MainForm window;
	
	private JFrame frmHospitalManagementSystem;
	
	private static JTabbedPane tabbedPanes;
	private static JPanel panelManagePatient, panelManageAppointment;
	
	private static CheckUpTabGUI panelCheckUp;
	private static EmployeeTabGUI panelManageEmployees;
	private static PatientRecordsTabGUI panelViewPatientRecords;
	
	private Employee loggedinEmployee;
	
	//UI references
	private JLabel lbUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new MainForm();
					//set main form to center screen
					window.frmHospitalManagementSystem.setLocationRelativeTo(null);
					
					//Initialize database by execute SQL commands from database folder 
					HibernateFactory.initializeDatabase();
					
					//login dialog
					showLoginDialog();
					
					//build UI based on user role, then display the main form
					buildDynamicUIBasedOnUserRole();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Show a message
	 * @param message
	 */
	public static void showMessage(String message) {
		JOptionPane.showMessageDialog(window.frmHospitalManagementSystem, message);
	}
	
//	public static void updateTables() {
//		//update Check Up List
//		panelCheckUp.updateTable();
//	}

	/**
	 * Create the application.
	 */
	public MainForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHospitalManagementSystem = new JFrame();
		frmHospitalManagementSystem.setTitle("Hospital Management System");
		frmHospitalManagementSystem.setBounds(100, 100, 1024, 768);
		frmHospitalManagementSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHospitalManagementSystem.getContentPane().setLayout(null);
		
		lbUser = new JLabel("admin");
		lbUser.setHorizontalAlignment(SwingConstants.TRAILING);
		lbUser.setBounds(585, 15, 300, 14);
		frmHospitalManagementSystem.getContentPane().add(lbUser);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Authentication.logout();
				showLoginDialog();
				buildDynamicUIBasedOnUserRole();
			}
		});
		btnLogout.setBounds(895, 11, 91, 23);
		frmHospitalManagementSystem.getContentPane().add(btnLogout);
		
		tabbedPanes = new JTabbedPane(JTabbedPane.TOP);
		tabbedPanes.setBounds(10, 34, 977, 662);
		frmHospitalManagementSystem.getContentPane().add(tabbedPanes);
	}
	
	//show login dialog
	private static void showLoginDialog() {
		//hide main window
		window.frmHospitalManagementSystem.setVisible(false);
		//remove all tabs
		tabbedPanes.removeAll();
		
		LoginDialog loginDialog = new LoginDialog(window.frmHospitalManagementSystem, true);
		loginDialog.showDialog();
	}
	
	//build dynamic UI based on logged in user role
	private static void buildDynamicUIBasedOnUserRole() {
		window.loggedinEmployee = Authentication.getLoggedInEmployee();//get logged in employee
		
		if(window.loggedinEmployee == null)//if null then
		{
			window.frmHospitalManagementSystem.dispose();//destroy main form
			System.exit(0);//close application
		}
		
		//Initialized Tabs
		//Employee GUI
		panelManageEmployees = new EmployeeTabGUI();
		
		//Patient GUI
		panelManagePatient = new PatientTabGUI();
		
		//Appointment GUI
		panelManageAppointment = new AppointmentTabGUI();
		
		//Check Up GUI
		panelCheckUp = new CheckUpTabGUI();
		
		//Patient Records GUI
		panelViewPatientRecords = new PatientRecordsTabGUI();
		
		//prepare UI based on role		
		switch(window.loggedinEmployee.getRole()) {
			case Employee.ADMIN_ROLE:
				window.lbUser.setText(window.loggedinEmployee.getFirstName() + " " + window.loggedinEmployee.getLastName());
				//add UI based on roles
				addTabs(new JPanel[] {panelManageEmployees, panelManagePatient, panelManageAppointment, panelCheckUp, panelViewPatientRecords}, 
						new String[] {"Employees", "Patients", "Appointment", "Check Up", "Patient Records"});
				break;
			case Employee.RECEPTIONIST_ROLE:
				//add UI based on roles
				addTabs(new JPanel[] {panelManagePatient, panelManageAppointment}, new String[] {"Patients", "Appointment"});
				
				window.lbUser.setText(window.loggedinEmployee.getFirstName() + " " + window.loggedinEmployee.getLastName());
				break;
			case Employee.DOCTOR_ROLE:
				//add UI based on roles
				addTabs(new JPanel[] {panelManagePatient, panelCheckUp, panelViewPatientRecords}, 
						new String[] {"Patients", "Check Up", "Patient Records"});
				
				window.lbUser.setText(window.loggedinEmployee.getFirstName() + " " + window.loggedinEmployee.getLastName());
				break;
			case Employee.TECHNOLOGIST_ROLE:
				//add UI based on roles
				addTabs(new JPanel[] {panelManagePatient}, new String[] {"Patients"});
				
				window.lbUser.setText(window.loggedinEmployee.getFirstName() + " " + window.loggedinEmployee.getLastName());
				break;
		}
		
		//show main window
		window.frmHospitalManagementSystem.setVisible(true);
	}
	
	//add corresponding tabs and titles to main forms
	private static void addTabs(JPanel[] tabs, String[] titles) {
		
		for(int i=0; i<titles.length; i++) {
			tabbedPanes.addTab(titles[i], null, tabs[i], null);
		}
			
	}
}
