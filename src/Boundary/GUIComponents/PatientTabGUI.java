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

import com.toedter.calendar.JDateChooser;

import Boundary.MainForm;
import Boundary.DAO.PatientDAOImpl;
import Boundary.Helpers.GUIHelper;
import Controller.PatientValidation;
import Entity.Patient;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PatientTabGUI extends JPanel {
	
	private JTable tablePatients;
	private JTextField patientFirstNameTxtBox, patientLastNameTxtBox, patientPhoneNumTxtBox,
		patientIdTxtBox, patientEmailTxtBox;
	private JDateChooser patientDob;
	private JTextArea patientAddressTextArea;
	private JComboBox comboBoxGender;
	
	private DefaultTableModel tm;
	private ListSelectionListener lsl;
	private PatientDAOImpl patientDAO = new PatientDAOImpl(); 
	
	//update patient table
	private void updateTable() {
		//remove listener
		tablePatients.getSelectionModel().removeListSelectionListener(lsl);
		
		//array of column names in the table
		String[] columnNames = {"Id", "First Name", "Last Name", "DOB", "Gender", "Email", "Phone", "Address"};
		
		//create a DefaultTableModel object
		tm = GUIHelper.populateTableModel(columnNames, patientDAO.getAllPatients());
		
		tablePatients.setModel(tm);
		
		tablePatients.setRowSorter(new TableRowSorter(tm));
		
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

	public PatientTabGUI() {
		
		setLayout(null);
		
		//create lsl
		lsl = new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				int currId = (int) tablePatients.getValueAt(tablePatients.getSelectedRow(), 0);//1st column
				
				//get the patient
				Patient patient = patientDAO.getPatientById(currId);
				
				updateCurrentPatientInfo(patient);
			}
		};
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 512, 354);
		add(scrollPane);
		
		tablePatients = new JTable();
		scrollPane.setViewportView(tablePatients);

		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(554, 46, 55, 16);
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(lblFirstName);
			
		patientFirstNameTxtBox = new JTextField();
		patientFirstNameTxtBox.setBounds(631, 40, 116, 22);
		patientFirstNameTxtBox.setColumns(10);
		PatientValidation.validateFirstName(patientFirstNameTxtBox.toString());
		add(patientFirstNameTxtBox);
			
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(554, 75, 65, 16);
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 10));
		PatientValidation.validateLastName(lblLastName.toString());
		add(lblLastName);
			
		patientLastNameTxtBox = new JTextField();
		patientLastNameTxtBox.setBounds(631, 69, 116, 22);
		patientLastNameTxtBox.setColumns(10);
 		PatientValidation.validateLastName(patientLastNameTxtBox.toString());
		add(patientLastNameTxtBox);		
			
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setBounds(554, 102, 45, 16);
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(lblGender);
			
		JLabel lblDateOfBirth = new JLabel("Date of Birth:");
		lblDateOfBirth.setBounds(554, 133, 65, 14);
		lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(lblDateOfBirth);
			
		JLabel lblPhoneNum = new JLabel("Phone:");
		lblPhoneNum.setBounds(554, 160, 65, 16);
		lblPhoneNum.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(lblPhoneNum);
			
		patientPhoneNumTxtBox = new JTextField();
		patientPhoneNumTxtBox.setBounds(631, 154, 116, 22);
		patientPhoneNumTxtBox.setColumns(10);
		PatientValidation.validatePhone(patientPhoneNumTxtBox.toString());
		add(patientPhoneNumTxtBox);
			
		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setBounds(554, 187, 32, 16);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(lblEmail);
			
		patientIdTxtBox = new JTextField();
		patientIdTxtBox.setEnabled(false);
		patientIdTxtBox.setBounds(631, 11, 116, 22);
		patientIdTxtBox.setColumns(10);
		PatientValidation.validateEmail(patientIdTxtBox);
		add(patientIdTxtBox);
			
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(554, 210, 52, 16);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(lblAddress);
			
		patientEmailTxtBox = new JTextField();
		patientEmailTxtBox.setBounds(631, 181, 116, 22);
		patientEmailTxtBox.setColumns(10);
		PatientValidation.validateAddress(patientEmailTxtBox.toString());
		add(patientEmailTxtBox);
			
		patientDob = new JDateChooser();
		patientDob.setBounds(631, 125, 116, 22);
		add(patientDob);
		
		comboBoxGender = new JComboBox();
		comboBoxGender.setBounds(631, 98, 116, 20);
		comboBoxGender.addItem("Unknown");
		comboBoxGender.addItem("Female");
		comboBoxGender.addItem("Male");
		add(comboBoxGender);
		
		patientAddressTextArea = new JTextArea();
		patientAddressTextArea.setLineWrap(true);
		patientAddressTextArea.setBounds(631, 210, 116, 50);
		add(patientAddressTextArea);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//update a patient
				//check id is available
				if(patientIdTxtBox.getText().equals("")) {
					MainForm.showMessage("Patient Id cannot be blank\nPlease select a patient!");
					return;
				}
				
				Patient patient = patientDAO.getPatientById(Integer.parseInt(patientIdTxtBox.getText()));
				
				//update
				patient.setFirstName(patientFirstNameTxtBox.getText());
				patient.setLastName(patientLastNameTxtBox.getText());
				patient.setDob(patientDob.getDate());
				patient.setPhone(patientPhoneNumTxtBox.getText());
				patient.setAddress(patientAddressTextArea.getText());
				patient.setEmail(patientEmailTxtBox.getText());
				patient.setGender(Patient.GENDER_MAP.get(comboBoxGender.getSelectedItem()));
				
				if(patientDAO.updatePatient(patient)) 
					updateTable();//update UIs
				else 
					MainForm.showMessage("Cannot update the patient\nPlease try again!");
			}
		});
		btnUpdate.setBounds(631, 265, 116, 29);
		add(btnUpdate);
		
		JButton btnClearForm = new JButton("Clear Form");
		btnClearForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//clear patient form
				patientIdTxtBox.setText("");
				patientFirstNameTxtBox.setText("");
				patientLastNameTxtBox.setText("");
				patientDob.setDate(null);
				patientPhoneNumTxtBox.setText("");
				patientAddressTextArea.setText("");
				patientEmailTxtBox.setText("");
				comboBoxGender.setSelectedIndex(0);
			}
		});
		btnClearForm.setBounds(554, 336, 116, 29);
		add(btnClearForm);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//add a patient
				Patient patient = new Patient();
				patient.setFirstName(patientFirstNameTxtBox.getText());
				patient.setLastName(patientLastNameTxtBox.getText());
				patient.setDob(patientDob.getDate());
				patient.setPhone(patientPhoneNumTxtBox.getText());
				patient.setAddress(patientAddressTextArea.getText());
				patient.setEmail(patientEmailTxtBox.getText());
				patient.setGender(Patient.GENDER_MAP.get(comboBoxGender.getSelectedItem()));
				
				int newPatientId = patientDAO.addPatient(patient);
				
				if(newPatientId < 0) {
					MainForm.showMessage("Cannot create a patient.\nPlease try again!");
				}else {
					updateCurrentPatientInfo(patientDAO.getPatientById(newPatientId));
					updateTable();
				}
			}
		});
		btnAdd.setBounds(673, 336, 74, 29);
		add(btnAdd);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblId.setBounds(554, 17, 55, 16);
		add(lblId);
		
		//update patients table
		updateTable();
	}
}
