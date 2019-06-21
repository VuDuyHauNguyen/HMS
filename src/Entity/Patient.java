package Entity;

import java.sql.Date;

import javax.swing.JOptionPane;

/**
* This is the patient class, where a new, or existing patient, will enter
 * their information and then it will be saved to the database.
 * 
 * 06/12/19 - Removed a few unnecessary variables, then added the variables 
 * Date of Birth (dob), gender, and telephone (phone).
 * 
 * @author Beau 300010635
 * @version 0.2
 *
 */

//+-----------+--------------+------+-----+---------+----------------+
//| Field     | Type         | Null | Key | Default | Extra          |
//+-----------+--------------+------+-----+---------+----------------+
//| patientId | int(11)      | NO   | PRI | NULL    | auto_increment |
//| firstName | varchar(50)  | YES  |     | NULL    |                |
//| lastName  | varchar(50)  | YES  |     | NULL    |                |
//| gender    | char(1)      | YES  |     | NULL    |                |
//| dob       | date         | YES  |     | NULL    |                |
//| phone     | varchar(20)  | YES  |     | NULL    |                |
//| email     | varchar(255) | YES  | UNI | NULL    |                |
//| address   | varchar(255) | YES  |     | NULL    |                |
//+-----------+--------------+------+-----+---------+----------------+

public class Patient {
	private int id;
	private String firstName;
	private String lastName;
	private String gender;
	private Date dob;
	private String phone;
	private String email;
	private String address;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
}