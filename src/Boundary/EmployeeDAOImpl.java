package Boundary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Entity.Employee;

public class EmployeeDAOImpl {

	private String dsn = "jdbc:mysql://localhost/hms_db";
	private String username = "root";
	private String password = "";
	
	private Connection conn = null;
	private ResultSet rs = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	
	public void connectDB()	{
		//Connect to the Database
		try {
			
			//Connect to the database with the proper info
			this.conn = DriverManager.getConnection(dsn, username, password);
			
			if (this.conn.isClosed()) {
				System.out.println("Database connection was not establsiehd");
			} else {
				System.out.println("Database connection established");
			}
			
		} catch (SQLException sx) {
			System.out.println("Error Connecting to Database");
			System.out.println(sx.getMessage());
			System.out.println(sx.getErrorCode());
			System.out.println(sx.getSQLState());
			
		}
	}
	
	public void disconnectDB()	{
		//Disconnect
		try {
			//disconnect from the database
			this.conn.close();
			
			if (conn.isClosed()) {
				System.out.println("Database connection closed");
			} else {
				System.out.println("There was a problem disconnecting from the database!");
			}
		} catch (SQLException sx) {
			System.out.println("Error Connecting to Database");
			System.out.println(sx.getMessage());
			System.out.println(sx.getErrorCode());
			System.out.println(sx.getSQLState());
			
		}
		
	}
	
	
	public Employee getEmployeeById(int id) {
		
		Employee s = null; 
		
		String sql = "SELECT * FROM employees WHERE id = ?";
		
		try {
			
			//Connect to the database
			connectDB();
			
			//Create the statement
			pstmt = conn.prepareStatement(sql);
			
			//Declare the parameter (starting at 1)
			pstmt.setInt(1,id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next())	{
				//if found
				s = new Employee();
				
				s.setId(rs.getInt("employeeId"));
				s.setFirstName(rs.getString("firstName"));
				s.setLastName(rs.getString("lastName"));
				s.setGender((char)rs.getByte("gender"));
				s.setDob(rs.getDate("dob"));
				s.setPhone(rs.getString("phone"));
				s.setEmail(rs.getString("email"));
				s.setAddress(rs.getString("address"));
				s.setRole(rs.getByte("role"));
				s.setStatus(rs.getByte("status"));
				s.setPassword(rs.getString("password"));
				
				
			} 
			
			disconnectDB();
				
			} catch (SQLException sx) {
				System.out.println("Error Connecting to Database");
				System.out.println(sx.getMessage());
				System.out.println(sx.getErrorCode());
				System.out.println(sx.getSQLState());
				
			}
			
			return s;
			
		}
	
	public Employee getEmployeeByEmail(String email) {
		
		Employee s = null; 
		
		String sql = "SELECT * FROM employees WHERE email = ?";
		
		try {
			
			//Connect to the database
			connectDB();
			
			//Create the statement
			pstmt = conn.prepareStatement(sql);
			
			//Declare the parameter (starting at 1)
			pstmt.setString(1, email);
			
			rs = pstmt.executeQuery();
			
			while (rs.next())	{
				//if found
				s = new Employee();
				
				s.setId(rs.getInt("employeeId"));
				s.setFirstName(rs.getString("firstName"));
				s.setLastName(rs.getString("lastName"));
				s.setGender((char)rs.getByte("gender"));
				s.setDob(rs.getDate("dob"));
				s.setPhone(rs.getString("phone"));
				s.setEmail(rs.getString("email"));
				s.setAddress(rs.getString("address"));
				s.setRole(rs.getByte("role"));
				s.setStatus(rs.getByte("status"));
				s.setPassword(rs.getString("password"));
				
				
			} 
			
			disconnectDB();
				
			} catch (SQLException sx) {
				System.out.println("Error Connecting to Database");
				System.out.println(sx.getMessage());
				System.out.println(sx.getErrorCode());
				System.out.println(sx.getSQLState());
				
			}
			
			return s;
			
		}
	
}
