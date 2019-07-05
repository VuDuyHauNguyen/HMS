package Boundary.GUIComponents;

import java.awt.Font;
import java.awt.Panel;

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

import Boundary.DAO.AppointmentDAOImpl;
import Boundary.Helpers.GUIHelper;
import Entity.Appointment;
import Entity.Employee;

import javax.swing.JScrollPane;
import javax.swing.JButton;

public class AppointmentTabGUI extends JPanel {
	
	private JTable tableAppointment;
	private JTextField receptionistIdTxtBox;	
	private JTextField textField;
	private JTextField patientIdTxtBox;
	private JTextField appointmentIdTxtBox;
	
	private DefaultTableModel tm;
	private ListSelectionListener lsl;
	private AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl();
	

	private void updateTable() {
		//remove listener
		tableAppointment.getSelectionModel().removeListSelectionListener(lsl);
		
		//array of column names in the table
		String[] columnNames = {"Id", "Receptionist Id", "Patient Id", "Date", "Time", "Status"};
		
		//create a DefaultTableModel object
		tm = GUIHelper.populateTableModel(columnNames, appointmentDAO.getAllAppointments());
		
		tableAppointment.setModel(tm);
		
		tableAppointment.setRowSorter(new TableRowSorter(tm));
		
		//add listener
		tableAppointment.getSelectionModel().addListSelectionListener(lsl);
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
				
//				updateCurrentAppointmentInfo(app);
			}
		};
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 512, 354);
		add(scrollPane);
		tableAppointment = new JTable();
		scrollPane.setViewportView(tableAppointment);
			
		JLabel lblManageAppointment = new JLabel("Manage Appointment");
		lblManageAppointment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblManageAppointment.setBounds(567, 13, 159, 20);
		add(lblManageAppointment);
			
		JLabel lblRecptTitle = new JLabel("(This section is filled out by the receptionist only.)");
		lblRecptTitle.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblRecptTitle.setBounds(551, 36, 193, 13);
		add(lblRecptTitle);
			
		JLabel lblPatientID = new JLabel("Receptionist ID:");
		lblPatientID.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblPatientID.setBounds(536, 85, 78, 16);
		add(lblPatientID);
			
		JLabel lblApptDate = new JLabel("Appointment Date:");
		lblApptDate.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblApptDate.setBounds(536, 150, 86, 13);
		add(lblApptDate);
			
		JDateChooser appointmentDate = new JDateChooser();
		appointmentDate.setBounds(628, 141, 116, 22);
		add(appointmentDate);
			
		JComboBox<String> appmntRecptStatusCbox = new JComboBox<String>();
		appmntRecptStatusCbox.setBounds(628, 210, 116, 22);
		add(appmntRecptStatusCbox);
			
		JComboBox appmntRecptTimeCbox = new JComboBox();
		appmntRecptTimeCbox.setModel(new DefaultComboBoxModel(new String[] {"08:00", "08:15", "08:30", "08:45", "09:00", "09:15", "09:30", "09:45", "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45", "12:00", "12:15", "12:30", "12:45", "13:00", "13:15", "13:30", "13:45", "14:00", "14:15", "14:30", "14:45", "15:00", "15:15", "15:30", "15:45", "16:00"}));
		appmntRecptTimeCbox.setBounds(628, 175, 116, 22);
		add(appmntRecptTimeCbox);
			
		receptionistIdTxtBox = new JTextField();
		receptionistIdTxtBox.setEditable(false);
		receptionistIdTxtBox.setBounds(628, 79, 116, 22);
		add(receptionistIdTxtBox);
		receptionistIdTxtBox.setColumns(10);			
		
		JLabel patientIdLbl = new JLabel("Patient ID:");
		patientIdLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		patientIdLbl.setBounds(536, 116, 78, 16);
		add(patientIdLbl);
		
		JLabel appointmentTimeLbl = new JLabel("Appointment Time:");
		appointmentTimeLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		appointmentTimeLbl.setBounds(536, 184, 86, 13);
		add(appointmentTimeLbl);
		
		patientIdTxtBox = new JTextField();
		patientIdTxtBox.setColumns(10);
		patientIdTxtBox.setBounds(628, 110, 116, 22);
		add(patientIdTxtBox);
		
		JLabel appointmentIdLbl = new JLabel("Appointment ID:");
		appointmentIdLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		appointmentIdLbl.setBounds(536, 56, 78, 16);
		add(appointmentIdLbl);
		
		appointmentIdTxtBox = new JTextField();
		appointmentIdTxtBox.setEditable(false);
		appointmentIdTxtBox.setColumns(10);
		appointmentIdTxtBox.setBounds(628, 50, 116, 22);
		add(appointmentIdTxtBox);
		
		JLabel statusLbl = new JLabel("Status:");
		statusLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		statusLbl.setBounds(536, 219, 86, 13);
		add(statusLbl);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(658, 338, 86, 29);
		add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(628, 244, 116, 29);
		add(btnUpdate);
		
		JButton btnClear = new JButton("Clear Form");
		btnClear.setBounds(536, 338, 115, 29);
		add(btnClear);
		
		JLabel lblDetails = new JLabel("Details:");
		lblDetails.setBounds(537, 20, 56, 16);		
			
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"This patient used Interac(R).", "This patient used Visa(TM).", "This patient used American Express(TM).", "This patient used Cash."}));
		comboBox.setBounds(536, 47, 202, 22);		
			
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(536, 82, 56, 16);		
			
		textField = new JTextField();
		textField.setBounds(631, 101, 116, 22);		
		textField.setColumns(10);
			
		JLabel lblPaymentDate = new JLabel("Payment Date:");
		lblPaymentDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPaymentDate.setBounds(536, 146, 86, 13);		
			
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(647, 166, 100, 22);		
			
		JLabel paymentTime = new JLabel("Time of Payment:");
		paymentTime.setBounds(536, 201, 116, 16);		
			
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(647, 230, 91, 22);		
		
		updateTable();
	}
}
