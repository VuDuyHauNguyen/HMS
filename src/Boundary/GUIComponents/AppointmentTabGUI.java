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
import Entity.CheckUpRecord;
import Entity.Employee;
import Entity.Patient;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

public class AppointmentTabGUI extends JPanel {
	
	private JTable tableAppointment;
	private JTextField receptionistIdTxtBox;	
	private JTextField patientIdTxtBox;
	private JTextField appointmentIdTxtBox;
	private JComboBox<String> appmntRecptStatusCbox, appmntRecptTimeCbox;
	private JDateChooser appointmentDate;
	
	private DefaultTableModel tm;
	private ListSelectionListener lsl;
	private AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl();
	private PatientDAOImpl patientDAO = new PatientDAOImpl(); 
	private CheckUpRecordDAOImpl checkUpRecordDAO = new CheckUpRecordDAOImpl(); 

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
		appointmentIdTxtBox.setText(appointment.getId() + "");
		receptionistIdTxtBox.setText(appointment.getReceptionist().getId() + "");
		patientIdTxtBox.setText(appointment.getPatient().getId() + "");
		appointmentDate.setDate(appointment.getAppointmentTime());
		appmntRecptTimeCbox.setSelectedItem(DateTimeHelper.getDisplayTimeFromDate(
				appointment.getAppointmentTime()));
		appmntRecptStatusCbox.setSelectedItem(appointment.getStatus());
	}
	
	private void clearForm() {
		appointmentIdTxtBox.setText("");
		receptionistIdTxtBox.setText("");
		patientIdTxtBox.setText("");
		appointmentDate.setDate(null);
		appmntRecptTimeCbox.setSelectedIndex(0);
		appmntRecptStatusCbox.setSelectedIndex(0);
	}
	
	public AppointmentTabGUI() {
		
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
		scrollPane.setBounds(10, 11, 733, 620);
		add(scrollPane);
		tableAppointment = new JTable();
		scrollPane.setViewportView(tableAppointment);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Manage Appointments", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(747, 3, 220, 346);
		add(panel);
		panel.setLayout(null);
			
		JLabel lblPatientID = new JLabel("Receptionist ID:");
		lblPatientID.setBounds(6, 57, 78, 16);
		panel.add(lblPatientID);
		lblPatientID.setFont(new Font("Tahoma", Font.PLAIN, 10));
			
		JLabel lblApptDate = new JLabel("Appointment Date:");
		lblApptDate.setBounds(6, 122, 86, 13);
		panel.add(lblApptDate);
		lblApptDate.setFont(new Font("Tahoma", Font.PLAIN, 10));
			
		appointmentDate = new JDateChooser();
		appointmentDate.setBounds(98, 113, 116, 22);
		panel.add(appointmentDate);
			
		appmntRecptStatusCbox = new JComboBox<String>();
		appmntRecptStatusCbox.setBounds(98, 182, 116, 22);
		panel.add(appmntRecptStatusCbox);
		appmntRecptStatusCbox.setModel(new DefaultComboBoxModel(new String[] {"book", "cancel","queue","done"}));
			
		appmntRecptTimeCbox = new JComboBox();
		appmntRecptTimeCbox.setBounds(98, 147, 116, 22);
		panel.add(appmntRecptTimeCbox);
		appmntRecptTimeCbox.setModel(new DefaultComboBoxModel(new String[] {"08:00", "08:15", "08:30", "08:45", "09:00", "09:15", "09:30", "09:45", "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45", "12:00", "12:15", "12:30", "12:45", "13:00", "13:15", "13:30", "13:45", "14:00", "14:15", "14:30", "14:45", "15:00", "15:15", "15:30", "15:45", "16:00"}));
			
		receptionistIdTxtBox = new JTextField();
		receptionistIdTxtBox.setBounds(98, 51, 116, 22);
		panel.add(receptionistIdTxtBox);
		receptionistIdTxtBox.setEditable(false);
		receptionistIdTxtBox.setColumns(10);			
		
		JLabel patientIdLbl = new JLabel("Patient ID:");
		patientIdLbl.setBounds(6, 88, 78, 16);
		panel.add(patientIdLbl);
		patientIdLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		JLabel appointmentTimeLbl = new JLabel("Appointment Time:");
		appointmentTimeLbl.setBounds(6, 156, 86, 13);
		panel.add(appointmentTimeLbl);
		appointmentTimeLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		patientIdTxtBox = new JTextField();
		patientIdTxtBox.setBounds(98, 82, 116, 22);
		panel.add(patientIdTxtBox);
		patientIdTxtBox.setColumns(10);
		
		JLabel appointmentIdLbl = new JLabel("Appointment ID:");
		appointmentIdLbl.setBounds(6, 28, 78, 16);
		panel.add(appointmentIdLbl);
		appointmentIdLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		appointmentIdTxtBox = new JTextField();
		appointmentIdTxtBox.setBounds(98, 22, 116, 22);
		panel.add(appointmentIdTxtBox);
		appointmentIdTxtBox.setEditable(false);
		appointmentIdTxtBox.setColumns(10);
		
		JLabel statusLbl = new JLabel("Status:");
		statusLbl.setBounds(6, 191, 86, 13);
		panel.add(statusLbl);
		statusLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(128, 310, 86, 29);
		panel.add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(98, 216, 116, 29);
		panel.add(btnUpdate);
		
		JButton btnClear = new JButton("Clear Form");
		btnClear.setBounds(6, 310, 115, 29);
		panel.add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//reset UIs
				clearForm();
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String appointmentIdStr = appointmentIdTxtBox.getText();
				String patientIdStr = patientIdTxtBox.getText();
				
				//check ids are available
				if(appointmentIdStr.equals("") ||
						patientIdStr.equals("") || 
						appointmentDate.getDate() == null) {
					MainForm.showMessage("Appointment Id, Patient Id and Appointment Date cannot be blank\nPlease select an appointment!");
					return;
				}
				
				//cannot update appointmentTime to the past 
				if(DateTimeHelper.getDateFromString(
						DateTimeHelper.getDisplayDateFromDate(appointmentDate.getDate()) +
						" " + appmntRecptTimeCbox.getSelectedItem() + ":00"
						).getTime() < (new Date()).getTime()) {
					MainForm.showMessage("Cannot update an appointment in the past. Please try again!");
					return;
				}
				
				//get an appointment
				Appointment appointment = appointmentDAO.getAppointmentById(
						Integer.parseInt(appointmentIdStr));
				
				if(appointment == null) return;//cannot get the appointment
				
				//check patient id is valid
				Patient patient = patientDAO.getPatientById(Integer.parseInt(patientIdStr)); 
				
				if(patient == null) {
					MainForm.showMessage("Patient Id is invalid. The patient may not exists.\nPlease try again!");
					return;
				}
				
				//update use the current logged in employee as a new receptionist
				appointment.setReceptionist(Authentication.getLoggedInEmployee());
				appointment.setPatient(patient);
				//build Date String with format "yyyy-MM-dd HH:mm:ss"
				appointment.setAppointmentTime(DateTimeHelper.getDateFromString(
						DateTimeHelper.getDisplayDateFromDate(appointmentDate.getDate()) +
						" " + appmntRecptTimeCbox.getSelectedItem() + ":00"
						));
				appointment.setStatus((String)appmntRecptStatusCbox.getSelectedItem());
				
				//update database
				if(appointmentDAO.updateAppointment(appointment)) {
					updateTable();//update UI
					switch((String)appmntRecptStatusCbox.getSelectedItem()) {
						case "cancel":
							clearForm(); break;
						case "done":
							//create a new checkUpRecord
							CheckUpRecord checkUpRecord = new CheckUpRecord();
							
							//set default data for the record
							checkUpRecord.setId(appointment.getId());
							checkUpRecord.setPatient(appointment.getPatient());
							checkUpRecord.setStatus(CheckUpRecord.STATUS_QUEUE);
							
							checkUpRecordDAO.addCheckUpRecord(checkUpRecord);
							
							//update Tab Check Up
							MainForm.updateTables();
							
							clearForm(); break;
					}
				}
				else
					MainForm.showMessage("Cannot update the appointment\nPlease try again!");
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String patientIdStr = patientIdTxtBox.getText();
				
				//check patient id is available
				if(patientIdStr.equals("") || 
						appointmentDate.getDate() == null) {
					MainForm.showMessage("Patient Id and Appointment Date cannot be blank\nPlease try again!");
					return;
				}
				
				//cannot create a new appointment in the past
				if(DateTimeHelper.getDateFromString(
						DateTimeHelper.getDisplayDateFromDate(appointmentDate.getDate()) +
						" " + appmntRecptTimeCbox.getSelectedItem() + ":00"
						).getTime() < (new Date()).getTime()) {
					MainForm.showMessage("Cannot create an appointment in the past. Please try again!");
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
				//build Date String with format "yyyy-MM-dd HH:mm:ss"
				appointment.setAppointmentTime(DateTimeHelper.getDateFromString(
						DateTimeHelper.getDisplayDateFromDate(appointmentDate.getDate()) +
						" " + appmntRecptTimeCbox.getSelectedItem() + ":00"
						));
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
