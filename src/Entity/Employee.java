package Entity;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.swing.text.JTextComponent;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


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
public class Employee implements Vectorable{
	public final static byte ADMIN_ROLE 		= 1;
	public final static byte RECEPTIONIST_ROLE 	= 2;
	public final static byte DOCTOR_ROLE 		= 3;
	public final static byte TECHNOLOGIST_ROLE 	= 4;
	
	public final static char GENDER_MALE 		= 'm';
	public final static char GENDER_FEMALE 		= 'f';
	public final static char GENDER_UNKNOWN 	= 'u';
	
	public final static byte STATUS_ENABLE 		= 1;
	public final static byte STATUS_DISABLE 	= -1;
	
	public final static HashMap<String, Byte> ROLE_MAP = new HashMap<String, Byte>()
	{{
	     put("Admin", ADMIN_ROLE);
	     put("Receptionist", RECEPTIONIST_ROLE);
	     put("Doctor", DOCTOR_ROLE);
	     put("Technologist",TECHNOLOGIST_ROLE);
	}};
	
	public final static HashMap<String, Character> GENDER_MAP = new HashMap<String, Character>()
	{{
	     put("Female", GENDER_FEMALE);
	     put("Male", GENDER_MALE);
	     put("Unknown", GENDER_UNKNOWN);
	}};
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private byte role, status;
	private String firstName, lastName, phone, email, address, password;
	private char gender;
	
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	//receptionist variable in Appointment class
	@OneToMany(fetch = FetchType.EAGER, mappedBy="receptionist")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Appointment> appointments;
	
	//doctor variable in CheckUpRecord class
	@OneToMany(mappedBy="doctor")
	private List<CheckUpRecord> checkUpRecords;
	
	//getters & setters
	public List<Appointment> getAppointments() {
		return appointments;
	}
	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	
	public List<CheckUpRecord> getCheckUpRecords() {
		return checkUpRecords;
	}
	public void setCheckUpRecords(List<CheckUpRecord> checkUpRecords) {
		this.checkUpRecords = checkUpRecords;
	}
	
	public int getId() {
		return id;
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
		
		//display role as string
		for (Map.Entry<String, Byte> roleEntry : ROLE_MAP.entrySet()) {
			if(role == roleEntry.getValue()) {
				v.add(roleEntry.getKey());
				break;
			}
		}
		
		//display status as string
		v.add(status > 0 ? "Enable" : "Disable");
		v.add(firstName);
		v.add(lastName);
		v.add(dob);
		
		//display gender as string
		for (Map.Entry<String, Character> genderEntry : GENDER_MAP.entrySet()) {
			if(gender == genderEntry.getValue()) {
				v.add(genderEntry.getKey());
				break;
			}
		}
		v.add(email);
		v.add(phone);
		v.add(address);
		v.add(password);
		
		return v;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		
		String displayRole = "";
		
		//get role
		//display role as string
		for (Map.Entry<String, Byte> roleEntry : ROLE_MAP.entrySet()) {
			if(role == roleEntry.getValue()) {
				displayRole = roleEntry.getKey();
				break;
			}
		}
		
		sb.append(displayRole + " Info");
		sb.append(System.lineSeparator());
		sb.append("ID: " + id);
		sb.append(System.lineSeparator());
		sb.append("Name: " + firstName + ", " + lastName);
		sb.append(System.lineSeparator());
		
		for (Map.Entry<String, Character> genderEntry : GENDER_MAP.entrySet()) {
			if(gender == genderEntry.getValue()) {
				
				sb.append("Gender: " + genderEntry.getKey());
				break;
			}
		}
		sb.append(System.lineSeparator());
		sb.append("Phone: " + phone);
		sb.append(System.lineSeparator());
		sb.append("Email: " + email);
		sb.append(System.lineSeparator());
		sb.append("Address: " + address);
		
		return sb.toString();
	}

}
