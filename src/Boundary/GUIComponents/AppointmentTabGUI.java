package Boundary.GUIComponents;

import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import Boundary.MainForm;
import Boundary.Helpers.DateTimeHelper;
import Boundary.Helpers.GUIHelper;
import Controller.AppointmentController;
import Controller.Authentication;
import Controller.PatientController;
import Controller.ValidationUserInput;
import Entity.Appointment;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import java.awt.Color;

/**
 * This class is the GUI Tab for Appointment.
 * @author Beau (10635)
 * @author Hau Ngyyen
 *
 */
public class AppointmentTabGUI extends JPanel {
	
	private JTable tableAppointment;
	private JTextField receptionistIdTxtBox;	
	private JTextField patientIdTxtBox;
	private JTextField appointmentIdTxtBox;
	private JComboBox<String> appmntRecptTimeCbox;
	private JDateChooser appointmentDate;
	
	private DefaultTableModel tm;
	private ListSelectionListener lsl;
	private JTextField statusTxtBox;
	JTextArea textAreaMedicalProblems;
	
	private JButton btnAdd, btnClear, btnUpdate, btnCancel, btnEnterCheckUp, btnViewPatient, btnViewReceptionist;
	
	private Appointment currentAppointment = null;

	private void updateTable() {
		//remove listener
		tableAppointment.getSelectionModel().removeListSelectionListener(lsl);
		
		//array of column names in the table
		String[] columnNames = {"Id", "Receptionist Id", "Patient Id", "Date", "Time", "Status"};
		
		//create a DefaultTableModel object
		tm = GUIHelper.populateTableModel(columnNames, 
				AppointmentController.getAllAppointments());
		
		tableAppointment.setModel(tm);
		
		tableAppointment.setRowSorter(new TableRowSorter<DefaultTableModel>(tm));
		
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
		statusTxtBox.setText(appointment.getStatus());
	}
	
	private void clearForm() {
		appointmentIdTxtBox.setText("");
		receptionistIdTxtBox.setText("");
		patientIdTxtBox.setText("");
		appointmentDate.setDate(null);
		appmntRecptTimeCbox.setSelectedIndex(0);
		statusTxtBox.setText("");
		textAreaMedicalProblems.setText("");
		
		//setup buttons
		GUIHelper.enableButtons(new JButton[] {btnAdd});
		GUIHelper.disableButtons(new JButton[] {btnUpdate, btnCancel, btnEnterCheckUp, 
				btnViewPatient, btnViewReceptionist});
		
		//reset currentAppointment
		currentAppointment = null;
	}
	
	//validate required fields
	private boolean validateRequiredFields() {
		
		//check required fields
		//Patient Id
		String validationResult = ValidationUserInput.validateRequiredPositiveNumberField(
				"Patient Id", patientIdTxtBox.getText().toString());
		
		if(validationResult.equals(ValidationUserInput.VALID) == false) {
			
			//notify error and stop
			MainForm.showMessage(validationResult + "\nPlease try again!");
			return false;
		}
		
		//Appointment Time
		validationResult = ValidationUserInput.validateRequiredDateField(
				"Appointment Date", appointmentDate.getDate());
		
		if(validationResult.equals(ValidationUserInput.VALID) == false) {
			
			//notify error and stop
			MainForm.showMessage(validationResult + "\nPlease try again!");
			return false;
		}
		
		return true;
	}
	
	//set user input data to appointment
	private Appointment setUserInputDataToAppointment(Appointment appointment) {
		
		appointment.setReceptionist(Authentication.getLoggedInEmployee());
		appointment.setPatient(PatientController.getPatientById(
				Integer.parseInt(patientIdTxtBox.getText().toString())));
		//build Date String with format "yyyy-MM-dd HH:mm:ss"
		appointment.setAppointmentTime(DateTimeHelper.getDateFromString(
				DateTimeHelper.getDisplayDateFromDate(appointmentDate.getDate()) +
				" " + appmntRecptTimeCbox.getSelectedItem() + ":00"
				));
		
		return appointment;
	}
	
	public AppointmentTabGUI() {
		
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 733, 620);
		add(scrollPane);
		tableAppointment = new JTable();
		tableAppointment.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableAppointment.setDefaultEditor(Object.class, null);//cannot edit table
		scrollPane.setViewportView(tableAppointment);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Manage Appointments", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(747, 3, 220, 375);
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
			
		appmntRecptTimeCbox = new JComboBox<String>();
		appmntRecptTimeCbox.setBounds(98, 147, 116, 22);
		panel.add(appmntRecptTimeCbox);
		appmntRecptTimeCbox.setModel(new DefaultComboBoxModel<String>(new String[] {"08:00", "08:15", "08:30", "08:45", "09:00", "09:15", "09:30", "09:45", "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45", "12:00", "12:15", "12:30", "12:45", "13:00", "13:15", "13:30", "13:45", "14:00", "14:15", "14:30", "14:45", "15:00", "15:15", "15:30", "15:45", "16:00"}));
			
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
		
		statusTxtBox = new JTextField();
		statusTxtBox.setEditable(false);
		statusTxtBox.setColumns(10);
		statusTxtBox.setBounds(98, 182, 116, 22);
		panel.add(statusTxtBox);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Check In", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(747, 380, 215, 136);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel labelMedicalProblems = new JLabel("Medical Problems:");
		labelMedicalProblems.setBounds(6, 22, 86, 16);
		panel_1.add(labelMedicalProblems);
		labelMedicalProblems.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 40, 201, 46);
		panel_1.add(scrollPane_1);
		
		textAreaMedicalProblems = new JTextArea();
		scrollPane_1.setViewportView(textAreaMedicalProblems);
		textAreaMedicalProblems.setLineWrap(true);
		
		//Buttons setup
		btnAdd = new JButton("Add");
		btnAdd.setForeground(Color.GREEN);
		btnAdd.setBounds(128, 335, 86, 29);
		panel.add(btnAdd);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setForeground(Color.BLUE);
		btnUpdate.setBounds(98, 216, 116, 29);
		panel.add(btnUpdate);
		
		btnClear = new JButton("Clear Form");
		btnClear.setBounds(6, 335, 115, 29);
		panel.add(btnClear);
		
		btnCancel = new JButton("Cancel");

		btnCancel.setForeground(Color.RED);
		btnCancel.setBounds(6, 216, 86, 29);
		panel.add(btnCancel);
		
		btnViewPatient = new JButton("Patient");
		btnViewPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(currentAppointment != null && currentAppointment.getPatient() != null)
					MainForm.showMessage(currentAppointment.getPatient().toString());
			}
		});
		btnViewPatient.setBounds(98, 285, 116, 29);
		panel.add(btnViewPatient);
		
		btnViewReceptionist = new JButton("Receptionist");
		btnViewReceptionist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(currentAppointment != null && currentAppointment.getReceptionist() != null)
					MainForm.showMessage(currentAppointment.getReceptionist().toString());
			}
		});
		btnViewReceptionist.setBounds(98, 250, 116, 29);
		panel.add(btnViewReceptionist);
		
		JLabel lblView = new JLabel("View:");
		lblView.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblView.setBounds(6, 266, 86, 13);
		panel.add(lblView);
		
		btnEnterCheckUp = new JButton("Enter Check Up");
		
		btnEnterCheckUp.setForeground(Color.BLUE);
		btnEnterCheckUp.setBounds(48, 100, 159, 29);
		panel_1.add(btnEnterCheckUp);
		
		//create lsl
		lsl = new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				tableAppointment.setEnabled(false);//disable table
				
				int currId = (int) tableAppointment.getValueAt(tableAppointment.getSelectedRow(), 0);//1st column
				
				//get the appointment
				currentAppointment = AppointmentController.getAppointmentById(currId);
				
				//setup buttons
				GUIHelper.disableButtons(new JButton[] {btnAdd});
				switch(currentAppointment.getStatus()) {
					case Appointment.STATUS_BOOK:
						GUIHelper.enableButtons(new JButton[] {btnUpdate, btnCancel});
						
						//if appointment date is today enable button Enter Check Up
						if(DateTimeHelper.getDisplayDateFromDate(currentAppointment.getAppointmentTime()).equals(
								DateTimeHelper.getDisplayDateFromDate(new Date())))
							GUIHelper.enableButtons(new JButton[] {btnEnterCheckUp});
						else
							GUIHelper.disableButtons(new JButton[] {btnEnterCheckUp});
						
						break;
					default://appointment reached end state (cancel or done) view only
						GUIHelper.disableButtons(new JButton[] {btnUpdate, btnCancel, btnEnterCheckUp});
						break;
				}
				
				GUIHelper.enableButtons(new JButton[] {btnViewPatient, btnViewReceptionist});
				
				updateCurrentAppointmentInfo(currentAppointment);
				tableAppointment.setEnabled(true);//enable table
			}
		};
		
		//reset UI
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//reset UIs
				clearForm();
			}
		});
		
		//update an appointment
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(currentAppointment == null) return;
				
				//check required fields, stop if fail
				if(validateRequiredFields() == false) return;
				
				//set user input data to appointment
				currentAppointment =  setUserInputDataToAppointment(currentAppointment);
				
				//update
				String result = AppointmentController.updateAppointment(currentAppointment);
				
				//check result
				if(result.equals(AppointmentController.SUCCESS)) {
					MainForm.showMessage("Updated successfully!");
					
					//update UI
					updateTable();
					clearForm();
				}else {
					MainForm.showMessage(result);
				}

			}
		});
		
		//add an appointment
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//check required fields, stop if fail
				if(validateRequiredFields() == false) return;
				
				//create new appointment
				Appointment appointment = new Appointment();

				//set user input data to appointment
				appointment =  setUserInputDataToAppointment(appointment);
				
				//add
				String result = AppointmentController.addAppointment(appointment);
				
				//check result
				if(result.equals(AppointmentController.SUCCESS)) {
					MainForm.showMessage("Added successfully!");
					
					//update UI
					updateTable();
					clearForm();
				}else {
					MainForm.showMessage(result);
				}
			}
		});
		
		//cancel an appointment
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//update database
				String result = AppointmentController.cancelAppointment(currentAppointment);
				
				//check
				if(result.equals(AppointmentController.SUCCESS)) { 
					MainForm.showMessage("Cancel successfully!");
					clearForm();
					updateTable();//update UI
				}
				else
					MainForm.showMessage(result);
			}
		});
		
		//patient check-in at hospital for a check up
		btnEnterCheckUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//check required fields
				String medicalProblems = textAreaMedicalProblems.getText().toString();
				String validateResult = ValidationUserInput.validateRequiredTextField(
						"Medical Problems", medicalProblems);
				
				if(validateResult.equals(ValidationUserInput.VALID) == false) {
					MainForm.showMessage(validateResult);
					return;
				}
				
				//check in
				String result = AppointmentController.checkInAppointment(
						currentAppointment, medicalProblems);
				
				if(result.equals(AppointmentController.SUCCESS)) {
					
					MainForm.showMessage("Check-in successfully");
					clearForm();
					updateTable();//update UI
				}else 
					MainForm.showMessage(result);
				
			}
		});
		
		clearForm();//reset UIs
		updateTable();//update table
	}
}
