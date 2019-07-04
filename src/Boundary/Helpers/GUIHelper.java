package Boundary.Helpers;

import java.util.ArrayList;

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

}
