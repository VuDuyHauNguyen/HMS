package Boundary.GUIComponents;


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.JTextComponent;

import com.toedter.calendar.JDateChooser;

import Boundary.MainForm;
import Boundary.Helpers.GUIHelper;
import Controller.PatientController;
import Controller.ValidationUserInput;
import Entity.Patient;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.ListSelectionModel;

public class PatientTabGUI extends JPanel {
	
	private JTable tablePatients;
	private JTextField patientFirstNameTxtBox, patientLastNameTxtBox, patientPhoneNumTxtBox,
		patientIdTxtBox, patientEmailTxtBox;
	private JDateChooser patientDob;
	private JTextArea patientAddressTextArea;
	private JComboBox<String> comboBoxGender;
	private JButton btnAdd, btnUpdate, btnClear;
	
	private DefaultTableModel tm;
	private ListSelectionListener lsl;
	
	private Map<String, JTextComponent> requiredTextFields = new LinkedHashMap<String, JTextComponent>();
	
	//update patient table
	private void updateTable() {
		//remove listener
		tablePatients.getSelectionModel().removeListSelectionListener(lsl);
		
		//array of column names in the table
		String[] columnNames = {"Id", "First Name", "Last Name", "DOB", "Gender", "Email", "Phone", "Address"};
		
		//create a DefaultTableModel object
		tm = GUIHelper.populateTableModel(columnNames, PatientController.getAllPatients());
		
		tablePatients.setModel(tm);
		
		tablePatients.setRowSorter(new TableRowSorter<DefaultTableModel>(tm));
		
		//add listener
		tablePatients.getSelectionModel().addListSelectionListener(lsl);
	}
	
	private void updateCurrentPatientInfo(Patient patient) {
		
		patientIdTxtBox.setText(patient.getId() + "");
		patientFirstNameTxtBox.setText(patient.getFirstName());
		patientLastNameTxtBox.setText(patient.getLastName());
		patientDob.setDate(patient.getDob());
		patientPhoneNumTxtBox.setText(patient.getPhone());
		patientAddressTextArea.setText(patient.getAddress());
		patientEmailTxtBox.setText(patient.getEmail());
		
		//update gender
		for(String gender : Patient.GENDER_MAP.keySet()) {
			if(Patient.GENDER_MAP.get(gender) == patient.getGender()) {
				comboBoxGender.setSelectedItem(gender);
				break;
			}
		}
	}
	
	private void clearForm() {
		patientIdTxtBox.setText("");
		patientFirstNameTxtBox.setText("");
		patientLastNameTxtBox.setText("");
		patientDob.setDate(null);
		patientPhoneNumTxtBox.setText("");
		patientAddressTextArea.setText("");
		patientEmailTxtBox.setText("");
		comboBoxGender.setSelectedIndex(0);
		
		//setup buttons
		GUIHelper.enableButtons(new JButton[] {btnAdd});
		GUIHelper.disableButtons(new JButton[] {btnUpdate});
	}
	
	//validate required fields
	private boolean validateRequiredFields() {
		
		//check required fields
		String validationResult = ValidationUserInput.validateRequiredTextFields(requiredTextFields);
		if(validationResult.equals(ValidationUserInput.VALID) == false) {
			
			//notify error and stop
			MainForm.showMessage(validationResult + "\nPlease try again!");
			return false;
		}
		
		return true;
	}
	
	//set data from UI to an patient
	private Patient setUserInputDataToPatient(Patient patient) {
		
		patient.setFirstName(patientFirstNameTxtBox.getText());
		patient.setLastName(patientLastNameTxtBox.getText());
		patient.setDob(patientDob.getDate());
		patient.setPhone(patientPhoneNumTxtBox.getText());
		patient.setAddress(patientAddressTextArea.getText());
		patient.setEmail(patientEmailTxtBox.getText());
		patient.setGender(Patient.GENDER_MAP.get(comboBoxGender.getSelectedItem()));

		return patient;
	}

	public PatientTabGUI() {
		
		setLayout(null);
		
		//create lsl
		lsl = new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				tablePatients.setEnabled(false);//disable table
				
				int currId = (int) tablePatients.getValueAt(tablePatients.getSelectedRow(), 0);//1st column
				
				//get the patient
				Patient patient = PatientController.getPatientById(currId);
				
				updateCurrentPatientInfo(patient);
				
				//setup buttons
				GUIHelper.disableButtons(new JButton[] {btnAdd});
				GUIHelper.enableButtons(new JButton[] {btnUpdate});
				
				tablePatients.setEnabled(true);//enable table
			}
		};
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 733, 620);
		add(scrollPane);
		
		tablePatients = new JTable();
		tablePatients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePatients.setDefaultEditor(Object.class, null);//cannot edit table
		scrollPane.setViewportView(tablePatients);
		
		JPanel patientFormPanel = new JPanel();
		patientFormPanel.setBorder(new TitledBorder(null, "Manage Patients", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		patientFormPanel.setBounds(747, 3, 215, 383);
		add(patientFormPanel);
		patientFormPanel.setLayout(null);

		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(10, 57, 55, 16);
		patientFormPanel.add(lblFirstName);
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 10));
			
		patientFirstNameTxtBox = new JTextField();
		patientFirstNameTxtBox.setBounds(83, 51, 116, 22);
		patientFormPanel.add(patientFirstNameTxtBox);
		patientFirstNameTxtBox.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(10, 86, 65, 16);
		patientFormPanel.add(lblLastName);
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		patientLastNameTxtBox = new JTextField();
		patientLastNameTxtBox.setBounds(83, 80, 116, 22);
		patientFormPanel.add(patientLastNameTxtBox);
		patientLastNameTxtBox.setColumns(10);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setBounds(10, 113, 45, 16);
		patientFormPanel.add(lblGender);
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth:");
		lblDateOfBirth.setBounds(10, 144, 65, 14);
		patientFormPanel.add(lblDateOfBirth);
		lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		JLabel lblPhoneNum = new JLabel("Phone:");
		lblPhoneNum.setBounds(10, 171, 65, 16);
		patientFormPanel.add(lblPhoneNum);
		lblPhoneNum.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		patientPhoneNumTxtBox = new JTextField();
		patientPhoneNumTxtBox.setBounds(83, 165, 116, 22);
		patientFormPanel.add(patientPhoneNumTxtBox);
		patientPhoneNumTxtBox.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setBounds(10, 198, 32, 16);
		patientFormPanel.add(lblEmail);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		patientIdTxtBox = new JTextField();
		patientIdTxtBox.setBounds(83, 22, 116, 22);
		patientFormPanel.add(patientIdTxtBox);
		patientIdTxtBox.setEnabled(false);
		patientIdTxtBox.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(10, 221, 52, 16);
		patientFormPanel.add(lblAddress);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		patientEmailTxtBox = new JTextField();
		patientEmailTxtBox.setBounds(83, 192, 116, 22);
		patientFormPanel.add(patientEmailTxtBox);
		patientEmailTxtBox.setColumns(10);
		
		patientDob = new JDateChooser();
		patientDob.setBounds(83, 136, 116, 22);
		patientFormPanel.add(patientDob);
		
		comboBoxGender = new JComboBox<String>();
		comboBoxGender.setBounds(83, 109, 116, 20);
		patientFormPanel.add(comboBoxGender);
		comboBoxGender.addItem("Unknown");
		comboBoxGender.addItem("Female");
		comboBoxGender.addItem("Male");
		
		patientAddressTextArea = new JTextArea();
		patientAddressTextArea.setBounds(83, 221, 116, 50);
		patientFormPanel.add(patientAddressTextArea);
		patientAddressTextArea.setLineWrap(true);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setForeground(Color.BLUE);
		btnUpdate.setBounds(83, 276, 116, 29);
		patientFormPanel.add(btnUpdate);
		
		btnClear = new JButton("Clear Form");
		btnClear.setBounds(6, 347, 116, 29);
		patientFormPanel.add(btnClear);
		
		btnAdd = new JButton("Add");
		btnAdd.setForeground(Color.GREEN);
		btnAdd.setBounds(125, 347, 74, 29);
		patientFormPanel.add(btnAdd);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(10, 28, 55, 16);
		patientFormPanel.add(lblId);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		//add new patient
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//check required fields, stop if fail
				if(validateRequiredFields() == false) return;
				
				//add a patient
				Patient patient = new Patient();
				
				//update new data to patient
				patient = setUserInputDataToPatient(patient);
				
				String result = PatientController.addPatient(patient);
				
				if(result.equals(PatientController.SUCCESS)) {
					MainForm.showMessage("Added successfully!");
					
					//update UI
					updateTable();
					clearForm();
				}else {
					MainForm.showMessage(result);
				}
			}
		});
		
		//clear form
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//clear patient form
				clearForm();
			}
		});
		
		//update patient
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//check required fields, stop if fail
				if(validateRequiredFields() == false) return;
				
				Patient patient = PatientController.getPatientById(Integer.parseInt(patientIdTxtBox.getText()));
				
				//update new data to patient
				patient = setUserInputDataToPatient(patient);
				
				//update to database
				String result = PatientController.updatePatient(patient);
				
				if(result.equals(PatientController.SUCCESS)) {
					MainForm.showMessage("Updated successfully");
					updateTable();
				}
				else
					MainForm.showMessage(result);
			}
		});
		
		//create required fields map
		requiredTextFields.put("First Name", patientFirstNameTxtBox);
		requiredTextFields.put("Last Name", patientLastNameTxtBox);
		requiredTextFields.put("Phone", patientPhoneNumTxtBox);
		requiredTextFields.put("Email", patientEmailTxtBox);
		requiredTextFields.put("Address", patientAddressTextArea);
		
		//update patients table
		updateTable();
		clearForm();
	}
}
