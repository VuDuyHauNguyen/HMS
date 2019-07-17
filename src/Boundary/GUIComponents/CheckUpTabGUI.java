package Boundary.GUIComponents;

import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import Boundary.MainForm;
import Boundary.DAO.AppointmentDAOImpl;
import Boundary.DAO.PatientDAOImpl;
import Boundary.Helpers.DateTimeHelper;
import Boundary.Helpers.GUIHelper;
import Controller.Authentication;
import Entity.Appointment;
import Entity.Employee;
import Entity.Patient;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class CheckUpTabGUI extends JPanel {
	
	private JTable tableAppointment;
	private JTextField doctorIdTxtBox;	
	private JTextField patientIdTxtBox;
	private JTextField checkUpIdTxtBox;
	
	private DefaultTableModel tm;
	private ListSelectionListener lsl;
	private AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl();
	private PatientDAOImpl patientDAO = new PatientDAOImpl(); 
	

	private void updateTable() {
		//remove listener
		tableAppointment.getSelectionModel().removeListSelectionListener(lsl);
		
		//array of column names in the table
		String[] columnNames = {"Id", "Receptionist Id", "Patient Id", "Date", "Time", "Status"};
		
		//create a DefaultTableModel object
		Employee loggedInUser = Authentication.getLoggedInEmployee();
		if(loggedInUser.getRole() == Employee.ADMIN_ROLE)//display all appointments
			tm = GUIHelper.populateTableModel(columnNames, appointmentDAO.getAllAppointments());
		else//receptionist: display in-progress from today appointments only
			tm = GUIHelper.populateTableModel(columnNames, appointmentDAO.getFromTodayAppointments());
		
		tableAppointment.setModel(tm);
		
		tableAppointment.setRowSorter(new TableRowSorter(tm));
		
		//add listener
		tableAppointment.getSelectionModel().addListSelectionListener(lsl);
	}
	
	private void updateCurrentAppointmentInfo(Appointment appointment) {
		checkUpIdTxtBox.setText(appointment.getId() + "");
		doctorIdTxtBox.setText(appointment.getReceptionist().getId() + "");
		patientIdTxtBox.setText(appointment.getPatient().getId() + "");

	}
	
	public CheckUpTabGUI() {
		
		setLayout(null);
		
		//create lsl
		lsl = new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				int currId = (int) tableAppointment.getValueAt(tableAppointment.getSelectedRow(), 0);//1st column
				
				//get the appointment
				Appointment app = appointmentDAO.getAppointmentById(currId);
				
				updateCurrentAppointmentInfo(app);
			}
		};
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 27, 512, 340);
		add(scrollPane);
		tableAppointment = new JTable();
		scrollPane.setViewportView(tableAppointment);
			
		JLabel lblCheckUp = new JLabel("Check Up");
		lblCheckUp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCheckUp.setBounds(12, 5, 116, 20);
		add(lblCheckUp);
			
		JLabel lblDoctorTitle = new JLabel("(This section is filled out by the doctor only.)");
		lblDoctorTitle.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblDoctorTitle.setBounds(95, 12, 180, 13);
		add(lblDoctorTitle);
			
		JLabel lblPatientID = new JLabel("Doctor ID:");
		lblPatientID.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblPatientID.setBounds(536, 59, 78, 16);
		add(lblPatientID);
			
		doctorIdTxtBox = new JTextField();
		doctorIdTxtBox.setEditable(false);
		doctorIdTxtBox.setBounds(628, 53, 116, 22);
		add(doctorIdTxtBox);
		doctorIdTxtBox.setColumns(10);			
		
		JLabel patientIdLbl = new JLabel("Patient ID:");
		patientIdLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		patientIdLbl.setBounds(536, 86, 78, 16);
		add(patientIdLbl);
		
		patientIdTxtBox = new JTextField();
		patientIdTxtBox.setEditable(false);
		patientIdTxtBox.setColumns(10);
		patientIdTxtBox.setBounds(628, 80, 116, 22);
		add(patientIdTxtBox);
		
		JLabel checkUpIdLbl = new JLabel("Check Up ID:");
		checkUpIdLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		checkUpIdLbl.setBounds(536, 33, 78, 16);
		add(checkUpIdLbl);
		
		checkUpIdTxtBox = new JTextField();
		checkUpIdTxtBox.setEditable(false);
		checkUpIdTxtBox.setColumns(10);
		checkUpIdTxtBox.setBounds(628, 27, 116, 22);
		add(checkUpIdTxtBox);
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String patientIdStr = patientIdTxtBox.getText();
				
				//check patient id is available
				if(patientIdStr.equals("")) {
					MainForm.showMessage("Patient Id and Appointment Date cannot be blank\nPlease try again!");
					return;
				}
				
				//check patient id is valid
				Patient patient = patientDAO.getPatientById(Integer.parseInt(patientIdStr)); 
				
				if(patient == null) {
					MainForm.showMessage("Patient Id is invalid. The patient may not exists.\nPlease try again!");
					return;
				}
				
				//create new appointment
				Appointment appointment = new Appointment();

				//set fields
				appointment.setReceptionist(Authentication.getLoggedInEmployee());
				appointment.setPatient(patient);
				
				appointment.setStatus(Appointment.STATUS_BOOK);//default new appointment status
				
				//add appointment to database
				int newAppointment = appointmentDAO.addAppointment(appointment);
				
				if(newAppointment < 0) {
					MainForm.showMessage("Cannot create an appointment.\nPlease try again!");
				}else {//update UI
					updateCurrentAppointmentInfo(appointment);
					updateTable();
				}		
			}
		});
		btnDone.setBounds(644, 338, 100, 29);
		add(btnDone);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//reset UIs
				checkUpIdTxtBox.setText("");
				doctorIdTxtBox.setText("");
				patientIdTxtBox.setText("");
			}
		});
		btnSave.setBounds(536, 338, 100, 29);
		add(btnSave);
		
		JLabel medicalProblemslbl = new JLabel("Medical Problems:");
		medicalProblemslbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		medicalProblemslbl.setBounds(536, 105, 86, 16);
		add(medicalProblemslbl);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(536, 123, 208, 50);
		add(scrollPane_1);
		
		JTextArea medicalProblemsTextArea = new JTextArea();
		medicalProblemsTextArea.setLocation(536, 0);
		scrollPane_1.setViewportView(medicalProblemsTextArea);
		medicalProblemsTextArea.setLineWrap(true);
		
		JLabel checkUpResultlbl = new JLabel("Diagnosises:");
		checkUpResultlbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		checkUpResultlbl.setBounds(536, 175, 86, 16);
		add(checkUpResultlbl);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(536, 193, 208, 50);
		add(scrollPane_2);
		
		JTextArea checkUpResultTextArea = new JTextArea();
		checkUpResultTextArea.setLocation(536, 0);
		scrollPane_2.setViewportView(checkUpResultTextArea);
		checkUpResultTextArea.setLineWrap(true);
		
		JLabel prescriptionsLbl = new JLabel("Prescriptions:");
		prescriptionsLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		prescriptionsLbl.setBounds(536, 248, 86, 16);
		add(prescriptionsLbl);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(536, 263, 206, 50);
		add(scrollPane_3);
		
		JTextArea prescriptionsTextArea = new JTextArea();
		prescriptionsTextArea.setLocation(536, 0);
		scrollPane_3.setViewportView(prescriptionsTextArea);
		prescriptionsTextArea.setLineWrap(true);
		
		updateTable();
	}
}
