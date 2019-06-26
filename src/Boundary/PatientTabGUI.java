package Boundary;

import java.awt.Container;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import Controller.PatientValidation;

public class PatientTabGUI extends JPanel {
	
	private JTable tablePatients;
	private Container panelManagePatient;
	private JTextField firstNameTxtBox;
	private JTextField lastNameTxtBox;
	private JTextField genderTxtBox;
	private JTextField phoneNumTxtBox;
	private JTextField emailTxtBox;
	private JTextField addrTxtBox;

	public PatientTabGUI() {
		
		setLayout(null);
		tablePatients = new JTable();
		tablePatients.setBounds(10, 11, 512, 354);
		this.add(tablePatients);
			
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(554, 30, 55, 16);
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(lblFirstName);
			
		firstNameTxtBox = new JTextField();
		firstNameTxtBox.setBounds(631, 30, 116, 22);
		firstNameTxtBox.setColumns(10);
		PatientValidation.validateFirstName(firstNameTxtBox.toString());
		add(firstNameTxtBox);
			
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(556, 74, 65, 16);
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 10));
		PatientValidation.validateLastName(lblLastName.toString());
		add(lblLastName);
			
		lastNameTxtBox = new JTextField();
		lastNameTxtBox.setBounds(631, 74, 116, 22);
		lastNameTxtBox.setColumns(10);
 		PatientValidation.validateLastName(lastNameTxtBox.toString());
		add(lastNameTxtBox);		
			
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setBounds(567, 122, 45, 16);
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(lblGender);
			
		genderTxtBox = new JTextField();
		genderTxtBox.setBounds(631, 119, 116, 22);
		genderTxtBox.setColumns(10);
		PatientValidation.validateGender(genderTxtBox.toString());
		add(genderTxtBox);
			
		JLabel lblDateOfBirth = new JLabel("Date of Birth:");
		lblDateOfBirth.setBounds(555, 168, 65, 14);
		lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(lblDateOfBirth);
			
		JLabel lblPhoneNum = new JLabel("Phone Number:");
		lblPhoneNum.setBounds(545, 218, 82, 16);
		lblPhoneNum.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(lblPhoneNum);
			
		phoneNumTxtBox = new JTextField();
		phoneNumTxtBox.setBounds(631, 214, 116, 22);
		phoneNumTxtBox.setColumns(10);
		PatientValidation.validatePhone(phoneNumTxtBox.toString());
		add(phoneNumTxtBox);
			
		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setBounds(570, 260, 32, 16);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(lblEmail);
			
		emailTxtBox = new JTextField();
		emailTxtBox.setBounds(631, 254, 116, 22);
		emailTxtBox.setColumns(10);
		PatientValidation.validateEmail(emailTxtBox);
		add(emailTxtBox);
			
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(557, 303, 52, 16);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(lblAddress);
			
		addrTxtBox = new JTextField();
		addrTxtBox.setBounds(631, 297, 116, 22);
		addrTxtBox.setColumns(10);
		PatientValidation.validateAddress(addrTxtBox.toString());
		add(addrTxtBox);
			
		JDateChooser patCal = new JDateChooser();
		patCal.setBounds(631, 168, 100, 22);
		add(patCal);
	}
}
