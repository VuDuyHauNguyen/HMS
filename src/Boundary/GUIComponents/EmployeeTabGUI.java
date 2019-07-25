package Boundary.GUIComponents;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.JTextComponent;

import com.toedter.calendar.JDateChooser;

import Boundary.MainForm;
import Boundary.DAO.EmployeeDAOImpl;
import Boundary.Helpers.GUIHelper;
import Controller.ValidationUserInput;
import Entity.Employee;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import java.awt.Color;

public class EmployeeTabGUI extends JPanel {

	private JTable tableEmployees;
	
	private JTextField empFirstNameTxtBox, empLastNameTxtBox, 
		empPhoneNumberTxtBox, empEmailTxtBox, empIdTxtBox, empPasswordTxtBox;   
	
	private JTextArea empAddressTxtArea;
	
	private JDateChooser empDob;
	
	private JComboBox comboBoxGender, comboBoxRole;
	
	private JCheckBox chckbxDisable;
	
	private JButton btnAdd, btnUpdate, btnClear;
	
	private DefaultTableModel tm;
	
	private ListSelectionListener lsl;
	
	private Map<String, JTextComponent> requiredTextFields = new HashMap<String, JTextComponent>();
	
	private EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
	
	private void updateTable() {
		//remove listener
		tableEmployees.getSelectionModel().removeListSelectionListener(lsl);
		
		//array of column names in the table
		String[] columnNames = {"Id", "Role", "Status", "First Name", "Last Name",
				"DOB", "Gender", "Email", "Phone", "Address", "Password"};
		
		//create a DefaultTableModel object
		tm = GUIHelper.populateTableModel(columnNames, employeeDAO.getAllEmployees());
		
		tableEmployees.setModel(tm);
		
		tableEmployees.setRowSorter(new TableRowSorter(tm));
		
		//add listener
		tableEmployees.getSelectionModel().addListSelectionListener(lsl);
	}
	
	private void updateCurrentEmployeeInfo(Employee emp) {
		
		empIdTxtBox.setText(emp.getId() + "");
		empFirstNameTxtBox.setText(emp.getFirstName());
		empLastNameTxtBox.setText(emp.getLastName());
		empDob.setDate(emp.getDob());
		empPhoneNumberTxtBox.setText(emp.getPhone());
		empAddressTxtArea.setText(emp.getAddress());
		empEmailTxtBox.setText(emp.getEmail());
		empPasswordTxtBox.setText(emp.getPassword());
		
		//update role
		for(String role : Employee.ROLE_MAP.keySet()) {
			if(Employee.ROLE_MAP.get(role) == emp.getRole()) {
				comboBoxRole.setSelectedItem(role);
				break;
			}
		}
		
		//update gender
		for(String gender : Employee.GENDER_MAP.keySet()) {
			if(Employee.GENDER_MAP.get(gender) == emp.getGender()) {
				comboBoxGender.setSelectedItem(gender);
				break;
			}
		}
		
		//update status
		if(emp.getStatus() == Employee.STATUS_ENABLE)
			chckbxDisable.setSelected(false);
		else
			chckbxDisable.setSelected(true);
	}
	
	private void clearForm() {
		empIdTxtBox.setText("");
		empFirstNameTxtBox.setText("");
		empLastNameTxtBox.setText("");
		empDob.setDate(null);
		empPhoneNumberTxtBox.setText("");
		empAddressTxtArea.setText("");
		empEmailTxtBox.setText("");
		empPasswordTxtBox.setText("");
		comboBoxRole.setSelectedIndex(0);
		comboBoxGender.setSelectedIndex(0);
		chckbxDisable.setSelected(false);
		
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
	
	//set data from UI to an an employee
	private Employee setUserInputDataToEmployee(Employee emp) {
		
		emp.setFirstName(empFirstNameTxtBox.getText());
		emp.setLastName(empLastNameTxtBox.getText());
		emp.setDob(empDob.getDate());
		emp.setPhone(empPhoneNumberTxtBox.getText());
		emp.setAddress(empAddressTxtArea.getText());
		emp.setEmail(empEmailTxtBox.getText());
		emp.setPassword(empPasswordTxtBox.getText());
		emp.setStatus(chckbxDisable.isSelected()? Employee.STATUS_DISABLE : Employee.STATUS_ENABLE);
		emp.setRole(Employee.ROLE_MAP.get(comboBoxRole.getSelectedItem()));
		emp.setGender(Employee.GENDER_MAP.get(comboBoxGender.getSelectedItem()));

		return emp;
	}
	
	/**
	 * Create the panel.
	 */
	public EmployeeTabGUI() {
		setLayout(null);
		
		//create lsl
		lsl = new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				tableEmployees.setEnabled(false);//disable table to prevent further click
				
				int currId = (int) tableEmployees.getValueAt(tableEmployees.getSelectedRow(), 0);//1st column
				
				//get the employee
				Employee emp = employeeDAO.getEmployeeById(currId);
				
				updateCurrentEmployeeInfo(emp);
				
				//setup buttons
				GUIHelper.disableButtons(new JButton[] {btnAdd});
				GUIHelper.enableButtons(new JButton[] {btnUpdate});
				
				tableEmployees.setEnabled(true);//enable table
			}
		};
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 733, 620);
		add(scrollPane);
				
		tableEmployees = new JTable();
		tableEmployees.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableEmployees.setDefaultEditor(Object.class, null);//cannot edit table
		scrollPane.setViewportView(tableEmployees);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Manage Employees", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(747, 3, 215, 410);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblEmpId = new JLabel("Id:");
		lblEmpId.setBounds(18, 27, 74, 16);
		panel.add(lblEmpId);
		lblEmpId.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		JLabel lblEmpLastName = new JLabel("Last Name:");
		lblEmpLastName.setBounds(18, 109, 65, 16);
		panel.add(lblEmpLastName);
		lblEmpLastName.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		empFirstNameTxtBox = new JTextField();
		empFirstNameTxtBox.setBounds(93, 75, 116, 22);
		panel.add(empFirstNameTxtBox);
		empFirstNameTxtBox.setColumns(10);
		
		empLastNameTxtBox = new JTextField();
		empLastNameTxtBox.setBounds(93, 103, 116, 22);
		panel.add(empLastNameTxtBox);
		empLastNameTxtBox.setColumns(10);
		
		JLabel lblEmpGender = new JLabel("Gender:");
		lblEmpGender.setBounds(18, 137, 54, 16);
		panel.add(lblEmpGender);
		lblEmpGender.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		JLabel lblEmpDOB = new JLabel("Date of Birth:");
		lblEmpDOB.setBounds(18, 165, 65, 16);
		panel.add(lblEmpDOB);
		lblEmpDOB.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		JLabel lblEmpPhoneNum = new JLabel("Phone:");
		lblEmpPhoneNum.setBounds(18, 189, 54, 16);
		panel.add(lblEmpPhoneNum);
		lblEmpPhoneNum.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		empPhoneNumberTxtBox = new JTextField();
		empPhoneNumberTxtBox.setBounds(93, 183, 116, 22);
		panel.add(empPhoneNumberTxtBox);
		empPhoneNumberTxtBox.setColumns(10);
		
		JLabel lblEmpEmail = new JLabel("E-Mail:");
		lblEmpEmail.setBounds(18, 271, 32, 16);
		panel.add(lblEmpEmail);
		lblEmpEmail.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		empEmailTxtBox = new JTextField();
		empEmailTxtBox.setBounds(93, 265, 116, 22);
		panel.add(empEmailTxtBox);
		empEmailTxtBox.setColumns(10);
		
		JLabel lblEmpAddress = new JLabel("Address:");
		lblEmpAddress.setBounds(18, 210, 52, 16);
		panel.add(lblEmpAddress);
		lblEmpAddress.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		empDob = new JDateChooser();
		empDob.setBounds(93, 159, 116, 22);
		panel.add(empDob);
		
		comboBoxGender = new JComboBox();
		comboBoxGender.setBounds(93, 133, 116, 20);
		panel.add(comboBoxGender);
		comboBoxGender.addItem("Unknown");
		comboBoxGender.addItem("Female");
		comboBoxGender.addItem("Male");
		
		empIdTxtBox = new JTextField();
		empIdTxtBox.setBounds(93, 23, 44, 20);
		panel.add(empIdTxtBox);
		empIdTxtBox.setEditable(false);
		empIdTxtBox.setColumns(10);
		
		chckbxDisable = new JCheckBox("Disable");
		chckbxDisable.setBounds(142, 22, 67, 23);
		panel.add(chckbxDisable);
		
		JLabel lblEmpFirstName = new JLabel("First Name:");
		lblEmpFirstName.setBounds(18, 81, 67, 16);
		panel.add(lblEmpFirstName);
		lblEmpFirstName.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		JLabel lblEmpRole = new JLabel("Role:");
		lblEmpRole.setBounds(18, 54, 74, 16);
		panel.add(lblEmpRole);
		lblEmpRole.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		comboBoxRole = new JComboBox();
		comboBoxRole.setBounds(93, 50, 116, 20);
		panel.add(comboBoxRole);
		comboBoxRole.addItem("Receptionist");
		comboBoxRole.addItem("Doctor");
		comboBoxRole.addItem("Technologist");
		comboBoxRole.addItem("Admin");
		
		btnAdd = new JButton("Add");
		btnAdd.setForeground(Color.GREEN);
		btnAdd.setBounds(125, 365, 84, 29);
		panel.add(btnAdd);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setForeground(Color.BLUE);
		btnUpdate.setBounds(93, 317, 116, 29);
		panel.add(btnUpdate);
		
		empAddressTxtArea = new JTextArea();
		empAddressTxtArea.setBounds(93, 210, 116, 50);
		panel.add(empAddressTxtArea);
		empAddressTxtArea.setLineWrap(true);
		
		JLabel lblEmpPassword = new JLabel("Password:");
		lblEmpPassword.setBounds(18, 296, 54, 16);
		panel.add(lblEmpPassword);
		lblEmpPassword.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		empPasswordTxtBox = new JTextField();
		empPasswordTxtBox.setBounds(93, 290, 116, 22);
		panel.add(empPasswordTxtBox);
		empPasswordTxtBox.setColumns(10);
		
		btnClear = new JButton("Clear Form");
		btnClear.setBounds(6, 365, 116, 29);
		panel.add(btnClear);
		
		//clear form
		btnClear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				clearForm();
			}
		});
		
		//update an employee
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//check required fields, stop if fail
				if(validateRequiredFields() == false) return;
				
				
				Employee emp = employeeDAO.getEmployeeById(Integer.parseInt(empIdTxtBox.getText()));
				
				if(emp ==  null) return;//can not get employee
				
				//update user input data to emp
				emp = setUserInputDataToEmployee(emp);
				
				if(employeeDAO.updateEmployee(emp))
					updateTable();
				else
					MainForm.showMessage("Cannot update the employee.\nPlease try again!");
			}
		});
		
		//add new employee
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//check required fields, stop if fail
				if(validateRequiredFields() == false) return;
				
				Employee emp =  new Employee();
				
				//set user input data to emp
				emp = setUserInputDataToEmployee(emp);
	
				int newEmpId = employeeDAO.addEmployee(emp);
				
				if(newEmpId < 0) {
					MainForm.showMessage("Cannot create an employee.\nPlease check email existence and try again!");
					empEmailTxtBox.requestFocus();//focus email field
					empIdTxtBox.setText("");//clear Id
				}else {
					updateTable();
					clearForm();
				}
			}
		});
		
		//create required fields hash map
		requiredTextFields.put("First Name", empFirstNameTxtBox);
		requiredTextFields.put("Last Name", empLastNameTxtBox);
		requiredTextFields.put("Phone", empPhoneNumberTxtBox);
		requiredTextFields.put("Address", empAddressTxtArea);
		requiredTextFields.put("Email", empEmailTxtBox);
		requiredTextFields.put("Password", empPasswordTxtBox);
		
		//update table
		clearForm();
		updateTable();
	}
}
