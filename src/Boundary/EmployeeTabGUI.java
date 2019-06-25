package Boundary;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;

public class EmployeeTabGUI extends JPanel {

	private JTable tableEmployees;
	
	private JTextField empFirstNameTxtBox, empLastNameTxtBox, 
		empPhoneNumberTxtBox, empEmailTxtBox;   
	
	private JDateChooser empDob;
	private JTextField empIdTxtBox;
	private JTextField empPasswordTxtBox;
	
	/**
	 * Create the panel.
	 */
	public EmployeeTabGUI() {
		setLayout(null);
				
		tableEmployees = new JTable();
		tableEmployees.setBounds(10, 11, 512, 354);
		add(tableEmployees);
		
		JLabel lblEmpId = new JLabel("Id:");
		lblEmpId.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblEmpId.setBounds(549, 12, 74, 16);
		add(lblEmpId);
		
		JLabel lblEmpLastName = new JLabel("Last Name:");
		lblEmpLastName.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblEmpLastName.setBounds(549, 97, 65, 16);
		add(lblEmpLastName);
		
		empFirstNameTxtBox = new JTextField();
		empFirstNameTxtBox.setColumns(10);
		empFirstNameTxtBox.setBounds(624, 63, 116, 22);
		add(empFirstNameTxtBox);
		
		empLastNameTxtBox = new JTextField();
		empLastNameTxtBox.setColumns(10);
		empLastNameTxtBox.setBounds(624, 91, 116, 22);
		add(empLastNameTxtBox);
		
		JLabel lblEmpGender = new JLabel("Gender:");
		lblEmpGender.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblEmpGender.setBounds(549, 125, 54, 16);
		add(lblEmpGender);
		
		JLabel lblEmpDOB = new JLabel("Date of Birth:");
		lblEmpDOB.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblEmpDOB.setBounds(549, 152, 65, 16);
		add(lblEmpDOB);
		
		JLabel lblEmpPhoneNum = new JLabel("Phone:");
		lblEmpPhoneNum.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblEmpPhoneNum.setBounds(549, 179, 54, 16);
		add(lblEmpPhoneNum);
		
		empPhoneNumberTxtBox = new JTextField();
		empPhoneNumberTxtBox.setColumns(10);
		empPhoneNumberTxtBox.setBounds(624, 176, 116, 22);
		add(empPhoneNumberTxtBox);
		
		JLabel lblEmpEmail = new JLabel("E-Mail:");
		lblEmpEmail.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblEmpEmail.setBounds(549, 271, 32, 16);
		add(lblEmpEmail);
		
		empEmailTxtBox = new JTextField();
		empEmailTxtBox.setEditable(false);
		empEmailTxtBox.setEnabled(false);
		empEmailTxtBox.setColumns(10);
		empEmailTxtBox.setBounds(624, 268, 116, 22);
		add(empEmailTxtBox);
		
		JLabel lblEmpAddress = new JLabel("Address:");
		lblEmpAddress.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblEmpAddress.setBounds(549, 207, 52, 16);
		add(lblEmpAddress);
		
		empDob = new JDateChooser();
		empDob.setBounds(624, 146, 116, 22);
		add(empDob);
		
		JComboBox comboBoxGender = new JComboBox();
		comboBoxGender.setBounds(624, 121, 116, 20);
		add(comboBoxGender);
		
		empIdTxtBox = new JTextField();
		empIdTxtBox.setEditable(false);
		empIdTxtBox.setEnabled(false);
		empIdTxtBox.setBounds(624, 8, 44, 20);
		add(empIdTxtBox);
		empIdTxtBox.setColumns(10);
		
		JCheckBox chckbxDisable = new JCheckBox("Disable");
		chckbxDisable.setBounds(673, 7, 67, 23);
		add(chckbxDisable);
		
		JLabel lblEmpFirstName = new JLabel("First Name:");
		lblEmpFirstName.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblEmpFirstName.setBounds(549, 69, 67, 16);
		add(lblEmpFirstName);
		
		JLabel lblEmpRole = new JLabel("Role:");
		lblEmpRole.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblEmpRole.setBounds(549, 39, 74, 16);
		add(lblEmpRole);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(624, 35, 116, 20);
		add(comboBox);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(549, 342, 89, 23);
		add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(651, 342, 89, 23);
		add(btnUpdate);
		
		JTextArea empAddressTxtArea = new JTextArea();
		empAddressTxtArea.setLineWrap(true);
		empAddressTxtArea.setBounds(624, 207, 116, 50);
		add(empAddressTxtArea);
		
		JLabel lblEmpPassword = new JLabel("Password:");
		lblEmpPassword.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblEmpPassword.setBounds(549, 302, 54, 16);
		add(lblEmpPassword);
		
		empPasswordTxtBox = new JTextField();
		empPasswordTxtBox.setColumns(10);
		empPasswordTxtBox.setBounds(624, 296, 116, 22);
		add(empPasswordTxtBox);

	}
}
