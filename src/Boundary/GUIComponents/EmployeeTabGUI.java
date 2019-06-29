package Boundary.GUIComponents;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import Boundary.MainForm;
import Boundary.DAO.EmployeeDAOImpl;
import Entity.Employee;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class EmployeeTabGUI extends JPanel {

	private JTable tableEmployees;
	
	private JTextField empFirstNameTxtBox, empLastNameTxtBox, 
		empPhoneNumberTxtBox, empEmailTxtBox, empIdTxtBox, empPasswordTxtBox;   
	
	private JTextArea empAddressTxtArea;
	
	private JDateChooser empDob;
	
	private JComboBox comboBoxGender, comboBoxRole;
	
	private JCheckBox chckbxDisable;
	
	private DefaultTableModel tm;
	
	private ListSelectionListener lsl;
	
	private EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
	
	private void updateTable() {
		//remove listener
		tableEmployees.getSelectionModel().removeListSelectionListener(lsl);
		
		tm = new DefaultTableModel();
		
		//set the columns
		tm.addColumn("Id");
		tm.addColumn("Role");
		tm.addColumn("Status");
		tm.addColumn("First Name");
		tm.addColumn("Last Name");
		tm.addColumn("DOB");
		tm.addColumn("Gender");
		tm.addColumn("Email");
		tm.addColumn("Phone");
		tm.addColumn("Address");
		tm.addColumn("Password");

		//get all employees
		ArrayList<Employee> employees = employeeDAO.getAllEmployees();
		
		for(Employee e : employees)
			tm.addRow(e.getVector());
		
		tableEmployees.setModel(tm);
		
		//add listener
		tableEmployees.getSelectionModel().addListSelectionListener(lsl);
		
		tableEmployees.setRowSorter(new TableRowSorter(tm));
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
	
	/**
	 * Create the panel.
	 */
	public EmployeeTabGUI() {
		setLayout(null);
		
		//create lsl
		lsl = new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				int currId = (int) tableEmployees.getValueAt(tableEmployees.getSelectedRow(), 0);//1st column
				
				//get the employee
				Employee emp = employeeDAO.getEmployeeById(currId);
				
				updateCurrentEmployeeInfo(emp);
			}
		};
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 512, 354);
		add(scrollPane);
				
		tableEmployees = new JTable();
		scrollPane.setViewportView(tableEmployees);
		
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
		
		comboBoxGender = new JComboBox();
		comboBoxGender.setBounds(624, 121, 116, 20);
		comboBoxGender.addItem("Unknown");
		comboBoxGender.addItem("Female");
		comboBoxGender.addItem("Male");
		add(comboBoxGender);
		
		empIdTxtBox = new JTextField();
		empIdTxtBox.setEditable(false);
		empIdTxtBox.setBounds(624, 8, 44, 20);
		add(empIdTxtBox);
		empIdTxtBox.setColumns(10);
		
		chckbxDisable = new JCheckBox("Disable");
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
		
		comboBoxRole = new JComboBox();
		comboBoxRole.setBounds(624, 35, 116, 20);
		comboBoxRole.addItem("Admin");
		comboBoxRole.addItem("Receptionist");
		comboBoxRole.addItem("Doctor");
		comboBoxRole.addItem("Technologist");
		add(comboBoxRole);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(549, 342, 89, 23);
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Employee emp =  new Employee();
				
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
	
				int newEmpId = employeeDAO.addEmployee(emp);
				
				if(newEmpId < 0) {
					MainForm.showMessage("Cannot create an employee.\nPlease check email existence and try again!");
					empEmailTxtBox.requestFocus();//focus email field
					empIdTxtBox.setText("");//clear Id
				}else {
					updateCurrentEmployeeInfo(employeeDAO.getEmployeeById(newEmpId));
				}
				
				updateTable();
			}
		});
		add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//check id is available
				if(empIdTxtBox.getText().equals("")) {
					MainForm.showMessage("Employee Id cannot be blank\nPlease select an employee!");
					return;
				}
				
				Employee emp = employeeDAO.getEmployeeById(Integer.parseInt(empIdTxtBox.getText()));
				
				if(emp ==  null) return;//can not get employee
				
				//update
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
				
				employeeDAO.updateEmployee(emp);
				
				updateTable();
			}
		});
		btnUpdate.setBounds(651, 342, 89, 23);
		add(btnUpdate);
		
		empAddressTxtArea = new JTextArea();
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

		//update table
		updateTable();
	}
}
