package Entity;


import java.util.Date;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//+------------+--------------+------+-----+---------+----------------+
//| Field      | Type         | Null | Key | Default | Extra          |
//+------------+--------------+------+-----+---------+----------------+
//| id         | int(11)      | NO   | PRI | NULL    | auto_increment |
//| firstName  | varchar(50)  | YES  |     | NULL    |                |
//| lastName   | varchar(50)  | YES  |     | NULL    |                |
//| gender     | char(1)      | YES  |     | NULL    |                |
//| dob        | date         | YES  |     | NULL    |                |
//| phone      | varchar(20)  | YES  |     | NULL    |                |
//| email      | varchar(255) | YES  | UNI | NULL    |                |
//| address    | varchar(255) | YES  |     | NULL    |                |
//| role       | tinyint(4)   | YES  |     | NULL    |                |
//| status     | tinyint(4)   | YES  |     | NULL    |                |
//| password   | varchar(50)  | YES  |     | NULL    |                |
//+------------+--------------+------+-----+---------+----------------+

@Entity
@Table(name="employees")
public class Employee {
	public final static int ADMIN_ROLE 			= 1;
	public final static int RECEPTIONIST_ROLE 	= 2;
	public final static int DOCTOR_ROLE 		= 3;
	public final static int TECHNOLOGIST_ROLE 	= 4;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private byte role, status;
	private String firstName, lastName, phone, email, address, password;
	private char gender;
	
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public byte getRole() {
		return role;
	}
	public void setRole(byte role) {
		this.role = role;
	}
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public Vector getVector() {
		Vector v = new Vector();
		v.add(id);
		v.add(role);
		v.add(status);
		v.add(firstName);
		v.add(lastName);
		v.add(dob);
		v.add(gender);
		v.add(email);
		v.add(phone);
		v.add(address);
		v.add(password);
		
		return v;
	}
}
