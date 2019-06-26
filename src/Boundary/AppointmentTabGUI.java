package Boundary;

import java.awt.Font;
import java.awt.Panel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class AppointmentTabGUI extends JPanel {
	
	
	private JPanel panelManageAppointment;
	private Object tabbedPanes;
	private JTable tableAppointment;
	private JTextField appmntRecptID;	
	private JTextField textField;

	public AppointmentTabGUI() {
		
		setLayout(null);
		tableAppointment = new JTable();
		tableAppointment.setBounds(12, 13, 512, 354);
		this.add(tableAppointment);
			
		JLabel lblManageAppointment = new JLabel("Manage Appointment");
		lblManageAppointment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblManageAppointment.setBounds(567, 10, 159, 20);
		add(lblManageAppointment);
			
		JLabel lblRecptTitle = new JLabel("(This section is filled out by the receptionist only.)");
		lblRecptTitle.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblRecptTitle.setBounds(551, 36, 193, 13);
		add(lblRecptTitle);
			
		JLabel lblRecptID = new JLabel("Receptionist ID:");
		lblRecptID.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblRecptID.setBounds(536, 62, 78, 16);
		add(lblRecptID);
			
		JLabel lblApptDate = new JLabel("Appointment Date");
		lblApptDate.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblApptDate.setBounds(538, 119, 86, 13);
		add(lblApptDate);
			
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(636, 119, 100, 22);
		add(dateChooser);
			
		JLabel lblRecptStatus = new JLabel("Status:");
		lblRecptStatus.setBounds(558, 199, 56, 16);
		add(lblRecptStatus);
			
		JComboBox<String> appmntRecptStatusCbox = new JComboBox<String>();
		appmntRecptStatusCbox.setBounds(636, 196, 96, 22);
		add(appmntRecptStatusCbox);
			
		JLabel lblAppmntTime = new JLabel("Time:");
		lblAppmntTime.setBounds(558, 158, 56, 16);
		add(lblAppmntTime);
			
		JComboBox appmntRecptTimeCbox = new JComboBox();
		appmntRecptTimeCbox.setModel(new DefaultComboBoxModel(new String[] {"00:00", "00:15", "00:30", "00:45", "01:00", "01:15", "01:30", "01:45", "02:00", "02:15", "02:30", "02:45", "03:00", "03:15", "03:30", "03:45", "04:00", "04:15", "04:30", "04:45", "05:00", "05:15", "05:30", "05:45", "06:00", "06:15", "06:30", "06:45", "07:00", "07:15", "07:30", "07:45", "08:00", "08:15", "08:30", "08:45", "09:00", "09:15", "09:30", "09:45", "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45", "12:00", "12:15", "12:30", "12:45", "13:00", "13:15", "13:30", "13:45", "14:00", "14:15", "14:30", "14:45", "15:00", "15:15", "15:30", "15:45", "16:00", "16:15", "16:30", "16:45", "17:00", "17:15", "17:30", "17:45", "18:00", "18:15", "18:30", "18:45", "19:00", "19:15", "19:30", "19:45", "20:00", "20:15", "20:30", "20:45", "21:00", "21:15", "21:30", "21:45", "22:00", "22:15", "22:30", "22:45", "23:00", "23:15", "23:30", "23:45", "24:00"}));
		appmntRecptTimeCbox.setBounds(635, 157, 91, 22);
		add(appmntRecptTimeCbox);
			
		appmntRecptID = new JTextField();
		appmntRecptID.setBounds(636, 62, 90, 22);
		add(appmntRecptID);
		appmntRecptID.setColumns(10);			
		
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
	}
}
