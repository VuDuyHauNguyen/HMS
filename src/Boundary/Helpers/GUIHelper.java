package Boundary.Helpers;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import Entity.Vectorable;

public class GUIHelper {
	
	//create a DefaultTableModel object
	public static <T extends Vectorable> DefaultTableModel populateTableModel(String[] columns, 
			ArrayList<T> rows) {
	
		DefaultTableModel tm = new DefaultTableModel();
		
		//add column names
		for(String columnName : columns)
			tm.addColumn(columnName);
		
		//add rows
		for(T row : rows)
			tm.addRow(row.getVector());
		
		return tm;
	}
	
	//disable all buttons
	public static void disableButtons(JButton[] buttons) {
		
		for(JButton button : buttons)
			button.setEnabled(false);
	}
	
	//enable all buttons
	public static void enableButtons(JButton[] buttons) {
		
		for(JButton button : buttons)
			button.setEnabled(true);
	}
}
