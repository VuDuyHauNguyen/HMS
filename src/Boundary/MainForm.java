package Boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import Controller.Authentication;
import Entity.Employee;
import Controller.PatientValidation;

import java.sql.Date;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenu;
import java.awt.Choice;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextArea;

public class MainForm {
	private static MainForm window;
	
	private JFrame frmHospitalManagementSystem;
	private JTable tableEmployees, tablePatients, tableReceptionist, tableAppointment;
	
	private static JTabbedPane tabbedPanes;
	private static JPanel panelManagePatient, panelManageAppointment,  panelManageReceptionist;
	
	private static EmployeeTabGUI panelManageEmployees;
	
	private Employee loggedinEmployee;
	
	//UI references
	private JLabel lbUser;
	private JTextField firstNameTxtBox;
	private JTextField lastNameTxtBox;
	private JTextField addrTxtBox;
	private JTextField genderTxtBox;
	private JTextField phoneNumTxtBox;
	private JTextField emailTxtBox;
	
	private JTextField empFirstNameTxtBox;
	private JTextField empLastNameTxtBox;
	private JTextField empGenderTxtBox;
	private JTextField empPhoneNumberTxtBox;
	private JTextField empEmailTxtBox;
	private JTextField empAddrTxtBox;	
	private JLabel lblRecpDispID;
	private JTextField appmntRecptID;
	private JTable table;
	private JTextField textField;
	private JTextField recptIDTxtBox;


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

					//login dialog
					showLoginDialog();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		frmHospitalManagementSystem.setBounds(100, 100, 800, 500);
		frmHospitalManagementSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHospitalManagementSystem.getContentPane().setLayout(null);
		
		lbUser = new JLabel("admin");
		lbUser.setHorizontalAlignment(SwingConstants.TRAILING);
		lbUser.setBounds(388, 15, 300, 14);
		frmHospitalManagementSystem.getContentPane().add(lbUser);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Authentication.logout();
				showLoginDialog();
			}
		});
		btnLogout.setBounds(698, 11, 76, 23);
		frmHospitalManagementSystem.getContentPane().add(btnLogout);
		
		tabbedPanes = new JTabbedPane(JTabbedPane.TOP);
		tabbedPanes.setBounds(10, 46, 764, 404);
		frmHospitalManagementSystem.getContentPane().add(tabbedPanes);
		
		//Employee GUI
		panelManageEmployees = new EmployeeTabGUI();
		
		//Appointment GUI
		panelManageAppointment = new AppointmentTabGUI();
		
		//Patient GUI
		panelManagePatient = new PatientTabGUI();
	}
	
	//show login dialog
	private static void showLoginDialog() {
		//hide main window
		window.frmHospitalManagementSystem.setVisible(false);
		//remove all tabs
		tabbedPanes.removeAll();
		
		LoginDialog loginDialog = new LoginDialog(window.frmHospitalManagementSystem, true);
		loginDialog.showDialog();
		
		window.loggedinEmployee = Authentication.getLoggedInEmployee();//get logged in employee
		
		if(window.loggedinEmployee == null)//if null then
		{
			window.frmHospitalManagementSystem.dispose();//destroy main form
			System.exit(0);//close application
		}
		
		//prepare UI based on role		
		switch(window.loggedinEmployee.getRole()) {
			case Employee.ADMIN_ROLE:
				window.lbUser.setText(window.loggedinEmployee.getFirstName() + " " + window.loggedinEmployee.getLastName());
				//add UI based on roles
				addTabs(new JPanel[] {panelManageEmployees, panelManagePatient, panelManageAppointment}, new String[] {"Employees", "Patients", "Appointment"});
				break;
			case Employee.RECEPTIONIST_ROLE:
				//add UI based on roles
				addTabs(new JPanel[] {panelManagePatient}, new String[] {"Patients"});
				
				window.lbUser.setText(window.loggedinEmployee.getFirstName() + " " + window.loggedinEmployee.getLastName());
				break;
			case Employee.DOCTOR_ROLE:
				//add UI based on roles
				addTabs(new JPanel[] {panelManagePatient}, new String[] {"Patients"});
				
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
