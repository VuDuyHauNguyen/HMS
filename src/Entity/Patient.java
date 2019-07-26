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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
* This is the patient class, where a new, or existing patient, will enter
 * their information and then it will be saved to the database.
 * 
 * 06/12/19 - Removed a few unnecessary variables, then added the variables 
 * Date of Birth (dob), gender, and telephone (phone).
 * 
 * @author Beau (10635)
 * @version 0.2
 *
 */

//+-----------+--------------+------+-----+---------+----------------+
//| Field     | Type         | Null | Key | Default | Extra          |
//+-----------+--------------+------+-----+---------+----------------+
//| id 		  | int(11)      | NO   | PRI | NULL    | auto_increment |
//| firstName | varchar(50)  | YES  |     | NULL    |                |
//| lastName  | varchar(50)  | YES  |     | NULL    |                |
//| gender    | char(1)      | YES  |     | NULL    |                |
//| dob       | date         | YES  |     | NULL    |                |
//| phone     | varchar(20)  | YES  |     | NULL    |                |
//| email     | varchar(255) | YES  |     | NULL    |                |
//| address   | varchar(255) | YES  |     | NULL    |                |
//+-----------+--------------+------+-----+---------+----------------+

@Entity
@Table(name="patients")
public class Patient implements Vectorable{
	
	public final static char GENDER_MALE 		= 'm';
	public final static char GENDER_FEMALE 		= 'f';
	public final static char GENDER_UNKNOWN 	= 'u';
	
	public final static HashMap<String, Character> GENDER_MAP = new HashMap<String, Character>()
	{{
	     put("Female", GENDER_FEMALE);
	     put("Male", GENDER_MALE);
	     put("Unknown", GENDER_UNKNOWN);
	}};
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	private char gender;
	
	@Temporal(TemporalType.DATE)
	private Date dob;
	private String phone;
	private String email;
	private String address;
	
	//patient variable in Appointment class
	@OneToMany(fetch = FetchType.EAGER, mappedBy="patient")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Appointment> appointments;
	
	//patient variable in CheckUpRecord class
	@OneToMany(mappedBy="patient")
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

	@Override
	public Vector getVector() {
		Vector v = new Vector();
		v.add(id);
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
		
		return v;
	}
	
}