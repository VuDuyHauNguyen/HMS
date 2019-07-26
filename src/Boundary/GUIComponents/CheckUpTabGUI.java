package Boundary.GUIComponents;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


import Boundary.MainForm;
import Boundary.Helpers.GUIHelper;
import Controller.Authentication;
import Controller.CheckUpRecordController;
import Entity.CheckUpRecord;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import java.awt.Color;

public class CheckUpTabGUI extends JPanel {
	
	private JTable tableCheckUpInQueue;
	private JTextField doctorIdTxtBox;	
	private JTextField patientIdTxtBox;
	private JTextField checkUpIdTxtBox;
	private JTextArea medicalProblemsTextArea, checkUpResultTextArea, prescriptionsTextArea;
	
	private DefaultTableModel tm;
	private ListSelectionListener lsl;
	
	private CheckUpRecord currentCheckUpRecord = null;
	private JTextField statusTxtBox;
	private JButton btnCancel, btnDone;
	
	public void updateTable() {
		//remove listener
		tableCheckUpInQueue.getSelectionModel().removeListSelectionListener(lsl);
		
		//array of column names in the table
		String[] columnNames = {"Id", "Patient Id", "Doctor Id", "Medical Problems", "Status"};
		
		//create a DefaultTableModel object
		tm = GUIHelper.populateTableModel(columnNames, 
				CheckUpRecordController.getAllCheckUpRecordsInQueueOrInProgress());
		
		tableCheckUpInQueue.setModel(tm);
		
		tableCheckUpInQueue.setRowSorter(new TableRowSorter<DefaultTableModel>(tm));
		
		//add listener
		tableCheckUpInQueue.getSelectionModel().addListSelectionListener(lsl);
	}
	
	private void updateCurrentCheckUpInfo(CheckUpRecord checkUpRecord) {
		checkUpIdTxtBox.setText(checkUpRecord.getId() + "");
		doctorIdTxtBox.setText((checkUpRecord.getDoctor() != null) ?
				checkUpRecord.getDoctor().getId() + "" : "");
		patientIdTxtBox.setText(checkUpRecord.getPatient().getId() + "");
		medicalProblemsTextArea.setText(checkUpRecord.getMedicalProblem());
		statusTxtBox.setText(checkUpRecord.getStatus());
	}
	
	//clear all check up form
	private void clearForm(){
		//reset UIs
		checkUpIdTxtBox.setText("");
		doctorIdTxtBox.setText("");
		patientIdTxtBox.setText("");
		statusTxtBox.setText("");
		medicalProblemsTextArea.setText("");
		checkUpResultTextArea.setText("");
		prescriptionsTextArea.setText("");
		
		//setup buttons
		GUIHelper.disableButtons(new JButton[] {btnCancel, btnDone});
		
		currentCheckUpRecord = null;//reset currentCheckUpRecord
	}
	
	public CheckUpTabGUI() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(10, 11, 733, 620);
		add(scrollPane);
		tableCheckUpInQueue = new JTable();
		tableCheckUpInQueue.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCheckUpInQueue.setDefaultEditor(Object.class, null);//cannot edit table
		scrollPane.setViewportView(tableCheckUpInQueue);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Perform Check Up", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(747, 3, 220, 422);
		add(panel);
		panel.setLayout(null);
			
		JLabel lblPatientID = new JLabel("Doctor ID:");
		lblPatientID.setBounds(6, 54, 78, 16);
		panel.add(lblPatientID);
		lblPatientID.setFont(new Font("Tahoma", Font.PLAIN, 10));
			
		doctorIdTxtBox = new JTextField();
		doctorIdTxtBox.setBounds(98, 48, 116, 22);
		panel.add(doctorIdTxtBox);
		doctorIdTxtBox.setEditable(false);
		doctorIdTxtBox.setColumns(10);			
		
		JLabel patientIdLbl = new JLabel("Patient ID:");
		patientIdLbl.setBounds(6, 81, 78, 16);
		panel.add(patientIdLbl);
		patientIdLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		patientIdTxtBox = new JTextField();
		patientIdTxtBox.setBounds(98, 75, 116, 22);
		panel.add(patientIdTxtBox);
		patientIdTxtBox.setEditable(false);
		patientIdTxtBox.setColumns(10);
		
		JLabel checkUpIdLbl = new JLabel("Check Up ID:");
		checkUpIdLbl.setBounds(6, 28, 78, 16);
		panel.add(checkUpIdLbl);
		checkUpIdLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		checkUpIdTxtBox = new JTextField();
		checkUpIdTxtBox.setBounds(98, 22, 116, 22);
		panel.add(checkUpIdTxtBox);
		checkUpIdTxtBox.setEditable(false);
		checkUpIdTxtBox.setColumns(10);
		
		btnDone = new JButton("Done");
		btnDone.setForeground(Color.GREEN);
		btnDone.setBounds(114, 377, 100, 29);
		panel.add(btnDone);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setForeground(Color.RED);
		btnCancel.setBounds(6, 377, 100, 29);
		panel.add(btnCancel);
		
		JLabel medicalProblemslbl = new JLabel("Medical Problems:");
		medicalProblemslbl.setBounds(6, 125, 86, 16);
		panel.add(medicalProblemslbl);
		medicalProblemslbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		JLabel checkUpResultlbl = new JLabel("Diagnostics:");
		checkUpResultlbl.setBounds(6, 195, 86, 16);
		panel.add(checkUpResultlbl);
		checkUpResultlbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		JLabel prescriptionsLbl = new JLabel("Prescriptions:");
		prescriptionsLbl.setBounds(6, 268, 86, 16);
		panel.add(prescriptionsLbl);
		prescriptionsLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 142, 206, 48);
		panel.add(scrollPane_1);
		
		medicalProblemsTextArea = new JTextArea();
		scrollPane_1.setViewportView(medicalProblemsTextArea);
		medicalProblemsTextArea.setLineWrap(true);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(6, 215, 206, 48);
		panel.add(scrollPane_2);
		
		checkUpResultTextArea = new JTextArea();
		scrollPane_2.setViewportView(checkUpResultTextArea);
		checkUpResultTextArea.setLineWrap(true);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(8, 285, 204, 48);
		panel.add(scrollPane_3);
		
		prescriptionsTextArea = new JTextArea();
		scrollPane_3.setViewportView(prescriptionsTextArea);
		prescriptionsTextArea.setLineWrap(true);
		
		
		JLabel statusLbl = new JLabel("Status:");
		statusLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		statusLbl.setBounds(6, 108, 78, 16);
		panel.add(statusLbl);
		
		statusTxtBox = new JTextField();
		statusTxtBox.setEditable(false);
		statusTxtBox.setColumns(10);
		statusTxtBox.setBounds(98, 102, 116, 22);
		panel.add(statusTxtBox);
		
		//create lsl
		lsl = new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				tableCheckUpInQueue.setEnabled(false);//disable table
				
				//check if the doctor has selected a patient
				//or checkUpRecordId text box has value
				if(checkUpIdTxtBox.getText().toString().equals("") == false) {
					MainForm.showMessage("Please finish the check up before moving on another one!"
							+ "\nOr click Cancel button to return the patient to the Check Up Queue.");
					tableCheckUpInQueue.setEnabled(true);//enable table
					return;
				}
				
				//else try to find the selected checkUpRecord	
				int currId = (int) tableCheckUpInQueue.getValueAt(tableCheckUpInQueue.getSelectedRow(), 0);//1st column
				
				//get the checkUpRecord
				currentCheckUpRecord = CheckUpRecordController.getCheckUpRecordInQueueById(currId);
				
				//check if currenCheckUp is available
				if(currentCheckUpRecord == null) 
					MainForm.showMessage("The patient is checked-up by other doctor!");
				else {
					//setup buttons
					GUIHelper.enableButtons(new JButton[] {btnCancel, btnDone});
					updateCurrentCheckUpInfo(currentCheckUpRecord);
				}
				
				updateTable();
				tableCheckUpInQueue.setEnabled(true);//enable table
			}
		};
		
		JButton btnRefreshCheckUp = new JButton("Refresh Check Up Queue");
		btnRefreshCheckUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				updateTable();//update check up queue
			}
		});
		btnRefreshCheckUp.setForeground(Color.BLUE);
		btnRefreshCheckUp.setBounds(6, 342, 208, 29);
		panel.add(btnRefreshCheckUp);

		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//if there is no patient 
				if(currentCheckUpRecord == null) {
					return;
				}
				
				//return the patient to the check-up table
				//set status to 'queue'
				currentCheckUpRecord.setStatus(CheckUpRecord.STATUS_QUEUE);
				
				//save to database
				String result = CheckUpRecordController.cancelCheckUpRecord(currentCheckUpRecord);
				
				if(result.equals(CheckUpRecordController.SUCCESS)) {
					
					MainForm.showMessage("Canceled successfully!");
					
					//reset UIs
					clearForm();
					updateTable();
				}else {
					MainForm.showMessage(result);
				}
					
			}
		});
		
		//done check up
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//if there is no patient 
				if(currentCheckUpRecord == null) {
					return;
				}
				
				//fill data to currentCheckUpRecord
				currentCheckUpRecord.setMedicalProblem(medicalProblemsTextArea.getText().toString());
				currentCheckUpRecord.setCheckupResult(checkUpResultTextArea.getText().toString());
				currentCheckUpRecord.setPrescriptions(prescriptionsTextArea.getText().toString());
				currentCheckUpRecord.setStatus(CheckUpRecord.STATUS_DONE);
				currentCheckUpRecord.setDoctor(Authentication.getLoggedInEmployee());
				currentCheckUpRecord.setCheckUpRecordTime(new Date());
				
				//save to database
				String result = CheckUpRecordController.finishCheckUpRecord(currentCheckUpRecord);
				
				if(result.equals(CheckUpRecordController.SUCCESS)) {
					
					MainForm.showMessage("Finished successfully!");
					
					//reset UIs
					clearForm();
					updateTable();
				}else {
					MainForm.showMessage(result);
				}
					
			}
		});

		clearForm();
		updateTable();
	}
}
