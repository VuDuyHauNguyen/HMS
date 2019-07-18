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
import Boundary.DAO.CheckUpRecordDAOImpl;
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
import javax.swing.border.TitledBorder;

public class CheckUpTabGUI extends JPanel {
	
	private JTable tableAppointment;
	private JTextField doctorIdTxtBox;	
	private JTextField patientIdTxtBox;
	private JTextField checkUpIdTxtBox;
	
	private DefaultTableModel tm;
	private ListSelectionListener lsl;
	private AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl();
	private PatientDAOImpl patientDAO = new PatientDAOImpl(); 
	private CheckUpRecordDAOImpl checkUpRecordDAO = new CheckUpRecordDAOImpl(); 
	
	public void updateTable() {
		//remove listener
		tableAppointment.getSelectionModel().removeListSelectionListener(lsl);
		
		//array of column names in the table
		String[] columnNames = {"Id", "Patient Id", "Doctor Id", "Date", "Time", 
				"Medical Problems", "Diagnoses", "Prescriptions", "Status"};
		
		//create a DefaultTableModel object
		Employee loggedInUser = Authentication.getLoggedInEmployee();
		if(loggedInUser.getRole() == Employee.ADMIN_ROLE)//display all appointments
			tm = GUIHelper.populateTableModel(columnNames, checkUpRecordDAO.getAllCheckUpRecords());
		else//receptionist: display in-progress from today appointments only
			tm = GUIHelper.populateTableModel(columnNames, checkUpRecordDAO.getAllCheckUpRecords());
		
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
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 733, 620);
		add(scrollPane);
		tableAppointment = new JTable();
		scrollPane.setViewportView(tableAppointment);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Perform Check Up", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(747, 3, 220, 369);
		add(panel);
		panel.setLayout(null);
			
		JLabel lblPatientID = new JLabel("Doctor ID:");
		lblPatientID.setBounds(6, 54, 78, 16);
		panel.add(lblPatientID);
		lblPatientID.setFont(new Font("Tahoma", Font.PLAIN, 10));
			
		doctorIdTxtBox = new JTextField();
		doctorIdTxtBox.setBounds(98, 48, 116, 22);
		panel.add(doctorIdTxtBox);
		doctorIdTxtBox.setEditable(false);
		doctorIdTxtBox.setColumns(10);			
		
		JLabel patientIdLbl = new JLabel("Patient ID:");
		patientIdLbl.setBounds(6, 81, 78, 16);
		panel.add(patientIdLbl);
		patientIdLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		patientIdTxtBox = new JTextField();
		patientIdTxtBox.setBounds(98, 75, 116, 22);
		panel.add(patientIdTxtBox);
		patientIdTxtBox.setEditable(false);
		patientIdTxtBox.setColumns(10);
		
		JLabel checkUpIdLbl = new JLabel("Check Up ID:");
		checkUpIdLbl.setBounds(6, 28, 78, 16);
		panel.add(checkUpIdLbl);
		checkUpIdLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		checkUpIdTxtBox = new JTextField();
		checkUpIdTxtBox.setBounds(98, 22, 116, 22);
		panel.add(checkUpIdTxtBox);
		checkUpIdTxtBox.setEditable(false);
		checkUpIdTxtBox.setColumns(10);
		
		JButton btnDone = new JButton("Done");
		btnDone.setBounds(114, 333, 100, 29);
		panel.add(btnDone);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(6, 333, 100, 29);
		panel.add(btnSave);
		
		JLabel medicalProblemslbl = new JLabel("Medical Problems:");
		medicalProblemslbl.setBounds(6, 100, 86, 16);
		panel.add(medicalProblemslbl);
		medicalProblemslbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		JLabel checkUpResultlbl = new JLabel("Diagnosises:");
		checkUpResultlbl.setBounds(6, 170, 86, 16);
		panel.add(checkUpResultlbl);
		checkUpResultlbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		JLabel prescriptionsLbl = new JLabel("Prescriptions:");
		prescriptionsLbl.setBounds(6, 243, 86, 16);
		panel.add(prescriptionsLbl);
		prescriptionsLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(8, 117, 206, 48);
		panel.add(scrollPane_1);
		
		JTextArea medicalProblemsTextArea = new JTextArea();
		scrollPane_1.setViewportView(medicalProblemsTextArea);
		medicalProblemsTextArea.setLineWrap(true);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(8, 189, 206, 48);
		panel.add(scrollPane_2);
		
		JTextArea checkUpResultTextArea = new JTextArea();
		scrollPane_2.setViewportView(checkUpResultTextArea);
		checkUpResultTextArea.setLineWrap(true);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 260, 204, 48);
		panel.add(scrollPane_3);
		
		JTextArea prescriptionsTextArea = new JTextArea();
		scrollPane_3.setViewportView(prescriptionsTextArea);
		prescriptionsTextArea.setLineWrap(true);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//reset UIs
				checkUpIdTxtBox.setText("");
				doctorIdTxtBox.setText("");
				patientIdTxtBox.setText("");
			}
		});
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
		
		updateTable();
	}
}
