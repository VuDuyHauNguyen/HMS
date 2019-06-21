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
	private static JPanel panelManageEmployees, panelManagePatient, panelManageAppointment,  panelManageReceptionist;
	
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
		panelManageEmployees = new JPanel();
		tabbedPanes.addTab("Employees", null, panelManageEmployees, null);
		panelManageEmployees.setLayout(null);
		
		tableEmployees = new JTable();
		tableEmployees.setBounds(10, 11, 512, 354);
		panelManageEmployees.add(tableEmployees);
		
		JLabel lblEmpFirstName = new JLabel("First Name:");
		lblEmpFirstName.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblEmpFirstName.setBounds(554, 30, 67, 16);
		panelManageEmployees.add(lblEmpFirstName);
		
		JLabel lblEmpLastName = new JLabel("Last Name:");
		lblEmpLastName.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblEmpLastName.setBounds(556, 74, 65, 16);
		panelManageEmployees.add(lblEmpLastName);
		
		empFirstNameTxtBox = new JTextField();
		empFirstNameTxtBox.setColumns(10);
		empFirstNameTxtBox.setBounds(631, 30, 116, 22);
		panelManageEmployees.add(empFirstNameTxtBox);
		
		empLastNameTxtBox = new JTextField();
		empLastNameTxtBox.setColumns(10);
		empLastNameTxtBox.setBounds(631, 74, 116, 22);
		panelManageEmployees.add(empLastNameTxtBox);
		
		empGenderTxtBox = new JTextField();
		empGenderTxtBox.setColumns(10);
		empGenderTxtBox.setBounds(631, 119, 116, 22);
		panelManageEmployees.add(empGenderTxtBox);
		
		JLabel lblEmpGender = new JLabel("Gender:");
		lblEmpGender.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblEmpGender.setBounds(567, 122, 54, 16);
		panelManageEmployees.add(lblEmpGender);
		
		JLabel lblEmpDOB = new JLabel("Date of Birth:");
		lblEmpDOB.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblEmpDOB.setBounds(555, 168, 65, 16);
		panelManageEmployees.add(lblEmpDOB);
		
		JLabel lblEmpPhoneNum = new JLabel("Phone Number:");
		lblEmpPhoneNum.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblEmpPhoneNum.setBounds(545, 207, 82, 16);
		panelManageEmployees.add(lblEmpPhoneNum);
		
		empPhoneNumberTxtBox = new JTextField();
		empPhoneNumberTxtBox.setColumns(10);
		empPhoneNumberTxtBox.setBounds(631, 204, 116, 22);
		panelManageEmployees.add(empPhoneNumberTxtBox);
		
		JLabel lblEmpEmail = new JLabel("E-Mail:");
		lblEmpEmail.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblEmpEmail.setBounds(569, 243, 32, 16);
		panelManageEmployees.add(lblEmpEmail);
		
		empEmailTxtBox = new JTextField();
		empEmailTxtBox.setColumns(10);
		empEmailTxtBox.setBounds(620, 240, 116, 22);
		panelManageEmployees.add(empEmailTxtBox);
		
		JLabel lblEmpAddress = new JLabel("Address:");
		lblEmpAddress.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblEmpAddress.setBounds(556, 286, 52, 16);
		panelManageEmployees.add(lblEmpAddress);
		
		empAddrTxtBox = new JTextField();
		empAddrTxtBox.setColumns(10);
		empAddrTxtBox.setBounds(620, 283, 116, 22);
		panelManageEmployees.add(empAddrTxtBox);
		
		JDateChooser empCal = new JDateChooser();
		empCal.setBounds(631, 168, 100, 22);
		panelManageEmployees.add(empCal);
		
		//Patients GUI
		panelManagePatient = new JPanel();
		tabbedPanes.addTab("Patients", null, panelManagePatient, null);
		panelManagePatient.setLayout(null);
		
		tablePatients = new JTable();
		tablePatients.setBounds(10, 11, 512, 354);
		panelManagePatient.add(tablePatients);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(554, 30, 55, 16);
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panelManagePatient.add(lblFirstName);
		
		firstNameTxtBox = new JTextField();
		firstNameTxtBox.setBounds(631, 30, 116, 22);
		firstNameTxtBox.setColumns(10);
		PatientValidation.validateFirstName(firstNameTxtBox.toString());
		panelManagePatient.add(firstNameTxtBox);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(556, 74, 65, 16);
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 10));
		PatientValidation.validateLastName(lblLastName.toString());
		panelManagePatient.add(lblLastName);
		
		lastNameTxtBox = new JTextField();
		lastNameTxtBox.setBounds(631, 74, 116, 22);
		lastNameTxtBox.setColumns(10);
 		PatientValidation.validateLastName(lastNameTxtBox.toString());
		panelManagePatient.add(lastNameTxtBox);		
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setBounds(567, 122, 45, 16);
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panelManagePatient.add(lblGender);
		
		genderTxtBox = new JTextField();
		genderTxtBox.setBounds(631, 119, 116, 22);
		genderTxtBox.setColumns(10);
		PatientValidation.validateGender(genderTxtBox.toString());
		panelManagePatient.add(genderTxtBox);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth:");
		lblDateOfBirth.setBounds(555, 168, 65, 14);
		lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panelManagePatient.add(lblDateOfBirth);
		
		JLabel lblPhoneNum = new JLabel("Phone Number:");
		lblPhoneNum.setBounds(545, 218, 82, 16);
		lblPhoneNum.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panelManagePatient.add(lblPhoneNum);
		
		phoneNumTxtBox = new JTextField();
		phoneNumTxtBox.setBounds(631, 214, 116, 22);
		phoneNumTxtBox.setColumns(10);
		PatientValidation.validatePhone(phoneNumTxtBox.toString());
		panelManagePatient.add(phoneNumTxtBox);
		
		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setBounds(570, 260, 32, 16);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panelManagePatient.add(lblEmail);
		
		emailTxtBox = new JTextField();
		emailTxtBox.setBounds(631, 254, 116, 22);
		emailTxtBox.setColumns(10);
		PatientValidation.validateEmail(emailTxtBox);
		panelManagePatient.add(emailTxtBox);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(557, 303, 52, 16);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panelManagePatient.add(lblAddress);
		
		addrTxtBox = new JTextField();
		addrTxtBox.setBounds(631, 297, 116, 22);
		addrTxtBox.setColumns(10);
		PatientValidation.validateAddress(addrTxtBox.toString());
		panelManagePatient.add(addrTxtBox);
		
		JDateChooser patCal = new JDateChooser();
		patCal.setBounds(631, 168, 100, 22);
		panelManagePatient.add(patCal);
		
		//Appointment GUI
		panelManageAppointment = new JPanel();
		tabbedPanes.addTab("Appointment", null, panelManageAppointment, null);
		panelManageAppointment.setLayout(null);
		
		tableAppointment = new JTable();
		tableAppointment.setBounds(12, 13, 512, 354);
		panelManageAppointment.add(tableAppointment);
		
		JLabel lblManageAppointment = new JLabel("Manage Appointment");
		lblManageAppointment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblManageAppointment.setBounds(567, 10, 159, 20);
		panelManageAppointment.add(lblManageAppointment);
		
		JLabel label_1 = new JLabel("(This section is filled out by the receptionist only.)");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 8));
		label_1.setBounds(551, 36, 193, 13);
		panelManageAppointment.add(label_1);
		
		JLabel label_2 = new JLabel("Receptionist ID:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label_2.setBounds(536, 62, 78, 16);
		panelManageAppointment.add(label_2);
		
		JLabel label_3 = new JLabel("Appointment Date");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label_3.setBounds(538, 119, 86, 13);
		panelManageAppointment.add(label_3);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(636, 119, 100, 22);
		panelManageAppointment.add(dateChooser);
		
		JLabel label_4 = new JLabel("Status:");
		label_4.setBounds(558, 199, 56, 16);
		panelManageAppointment.add(label_4);
		
		JComboBox<String> appmntRecptStatusCbox = new JComboBox<String>();
		appmntRecptStatusCbox.setBounds(636, 196, 96, 22);
		panelManageAppointment.add(appmntRecptStatusCbox);
		
		JLabel lblAppmntTime = new JLabel("Time:");
		lblAppmntTime.setBounds(558, 158, 56, 16);
		panelManageAppointment.add(lblAppmntTime);
		
		JComboBox appmntRecptTimeCbox = new JComboBox();
		appmntRecptTimeCbox.setModel(new DefaultComboBoxModel(new String[] {"00:00", "00:15", "00:30", "00:45", "01:00", "01:15", "01:30", "01:45", "02:00", "02:15", "02:30", "02:45", "03:00", "03:15", "03:30", "03:45", "04:00", "04:15", "04:30", "04:45", "05:00", "05:15", "05:30", "05:45", "06:00", "06:15", "06:30", "06:45", "07:00", "07:15", "07:30", "07:45", "08:00", "08:15", "08:30", "08:45", "09:00", "09:15", "09:30", "09:45", "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45", "12:00", "12:15", "12:30", "12:45", "13:00", "13:15", "13:30", "13:45", "14:00", "14:15", "14:30", "14:45", "15:00", "15:15", "15:30", "15:45", "16:00", "16:15", "16:30", "16:45", "17:00", "17:15", "17:30", "17:45", "18:00", "18:15", "18:30", "18:45", "19:00", "19:15", "19:30", "19:45", "20:00", "20:15", "20:30", "20:45", "21:00", "21:15", "21:30", "21:45", "22:00", "22:15", "22:30", "22:45", "23:00", "23:15", "23:30", "23:45", "24:00"}));
		appmntRecptTimeCbox.setBounds(635, 157, 91, 22);
		panelManageAppointment.add(appmntRecptTimeCbox);
		
		appmntRecptID = new JTextField();
		appmntRecptID.setBounds(636, 62, 90, 22);
		panelManageAppointment.add(appmntRecptID);
		appmntRecptID.setColumns(10);
		
		Panel panelManagePayment = new Panel();
		tabbedPanes.addTab("Payment", null, panelManagePayment, null);
		panelManagePayment.setLayout(null);
		
		table = new JTable();
		table.setBounds(12, 13, 512, 354);
		panelManagePayment.add(table);
		
		JLabel lblDetails = new JLabel("Details:");
		lblDetails.setBounds(537, 20, 56, 16);
		panelManagePayment.add(lblDetails);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"This patient used Interac(R).", "This patient used Visa(TM).", "This patient used American Express(TM).", "This patient used Cash."}));
		comboBox.setBounds(536, 47, 202, 22);
		panelManagePayment.add(comboBox);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(536, 82, 56, 16);
		panelManagePayment.add(lblAmount);
		
		textField = new JTextField();
		textField.setBounds(631, 101, 116, 22);
		panelManagePayment.add(textField);
		textField.setColumns(10);
		
		JLabel lblPaymentDate = new JLabel("Payment Date:");
		lblPaymentDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPaymentDate.setBounds(536, 146, 86, 13);
		panelManagePayment.add(lblPaymentDate);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(647, 166, 100, 22);
		panelManagePayment.add(dateChooser_1);
		
		JLabel paymentTime = new JLabel("Time of Payment:");
		paymentTime.setBounds(536, 201, 116, 16);
		panelManagePayment.add(paymentTime);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(647, 230, 91, 22);
		panelManagePayment.add(comboBox_1);
		
		// Receptionist GUI
		panelManageReceptionist = new JPanel();
		tabbedPanes.addTab("Receptionist", null, panelManageReceptionist, null);
		panelManageReceptionist.setLayout(null);
		
		tableReceptionist = new JTable();
		tableReceptionist.setBounds(12, 13, 512, 354);
		panelManageReceptionist.add(tableReceptionist);
		
		lblRecpDispID = new JLabel("");
		lblRecpDispID.setBounds(636, 89, 55, 16);
		lblRecpDispID.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panelManageReceptionist.add(lblRecpDispID);
		
		JLabel lblReceptionistManagement = new JLabel("Receptionist Management");
		lblReceptionistManagement.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblReceptionistManagement.setBounds(556, 16, 192, 16);
		panelManageReceptionist.add(lblReceptionistManagement);
		
		JButton recptAddBtn = new JButton("Add");
		recptAddBtn.setBounds(556, 276, 85, 25);
		panelManageReceptionist.add(recptAddBtn);
		
		JButton recptRmBtn = new JButton("Remove");
		recptRmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		recptRmBtn.setBounds(661, 276, 85, 25);
		panelManageReceptionist.add(recptRmBtn);
		
		JButton recptCrteBtn = new JButton("Create");
		recptCrteBtn.setBounds(556, 314, 85, 25);
		panelManageReceptionist.add(recptCrteBtn);
		
		JButton recptUpdBtn = new JButton("Update");
		recptUpdBtn.setBounds(661, 314, 85, 25);
		panelManageReceptionist.add(recptUpdBtn);
		
		JLabel lalRcptID = new JLabel("Receptionist ID:");
		lalRcptID.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lalRcptID.setBounds(548, 70, 78, 16);
		panelManageReceptionist.add(lalRcptID);
		
		recptIDTxtBox = new JTextField();
		recptIDTxtBox.setColumns(10);
		recptIDTxtBox.setBounds(648, 70, 90, 22);
		panelManageReceptionist.add(recptIDTxtBox);
		
		JDateChooser recptDateChooser = new JDateChooser();
		recptDateChooser.setBounds(648, 127, 100, 22);
		panelManageReceptionist.add(recptDateChooser);
		
		JLabel lblRecptDate = new JLabel("Appointment Date");
		lblRecptDate.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblRecptDate.setBounds(550, 127, 86, 13);
		panelManageReceptionist.add(lblRecptDate);
		
		JLabel lblRecptTime = new JLabel("Time:");
		lblRecptTime.setBounds(570, 166, 56, 16);
		panelManageReceptionist.add(lblRecptTime);
		
		JComboBox recptTimeCBox = new JComboBox();
		recptTimeCBox.setBounds(647, 165, 91, 22);
		panelManageReceptionist.add(recptTimeCBox);
		
		JComboBox<String> comboBox_3 = new JComboBox<String>();
		comboBox_3.setBounds(648, 204, 96, 22);
		panelManageReceptionist.add(comboBox_3);
		
		JLabel lblRecptStatsCBox = new JLabel("Status:");
		lblRecptStatsCBox.setBounds(570, 207, 56, 16);
		panelManageReceptionist.add(lblRecptStatsCBox);
		
		JLabel lblClickTheButtons = new JLabel("<html><div style='text-align:center'>Click the buttons below to<br>" + 
											   "modify an appointment.</div></html>");
		lblClickTheButtons.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblClickTheButtons.setBounds(588, 238, 126, 27);
		panelManageReceptionist.add(lblClickTheButtons);
		
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
				addTabs(new JPanel[] {panelManageEmployees, panelManagePatient}, new String[] {"Employees", "Patients"});
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
