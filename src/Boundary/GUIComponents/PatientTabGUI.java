package Boundary.GUIComponents;


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.toedter.calendar.JDateChooser;

import Controller.PatientValidation;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PatientTabGUI extends JPanel {
	
	private JTable tablePatients;
	private JTextField patientFirstNameTxtBox, patientLastNameTxtBox, patientPhoneNumTxtBox,
		patientIdTxtBox, patientEmailTxtBox;

	public PatientTabGUI() {
		
		setLayout(null);
		
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
			
		JDateChooser patientDob = new JDateChooser();
		patientDob.setBounds(631, 125, 116, 22);
		add(patientDob);
		
		JComboBox comboBoxGender = new JComboBox();
		comboBoxGender.setBounds(631, 98, 116, 20);
		add(comboBoxGender);
		
		JTextArea patientAddressTextArea = new JTextArea();
		patientAddressTextArea.setLineWrap(true);
		patientAddressTextArea.setBounds(631, 210, 116, 50);
		add(patientAddressTextArea);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//update a patient
			}
		});
		btnUpdate.setBounds(631, 265, 116, 29);
		add(btnUpdate);
		
		JButton btnClearForm = new JButton("Clear Form");
		btnClearForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//clear patient form
			}
		});
		btnClearForm.setBounds(554, 336, 116, 29);
		add(btnClearForm);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//add a patient
			}
		});
		btnAdd.setBounds(673, 336, 74, 29);
		add(btnAdd);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblId.setBounds(554, 17, 55, 16);
		add(lblId);
	}
}
