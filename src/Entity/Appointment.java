package Entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * This class will define an appointment.
 * This will range from the an appointment
 * id number, to the status of an appointment.
 * 
 * @author Beau Lee (300010635)
 *
 */

@Entity
@Table(name="appointments")
public class Appointment implements Vectorable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name="patientId")
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name="receptionistId")
	private Employee receptionist;
	
	public Employee getReceptionist() {
		return receptionist;
	}

	public void setReceptionist(Employee receptionist) {
		this.receptionist = receptionist;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date appointmentTime;
	
	private String status;
	
	public int getId() {
		return id;
	}

	public Date getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(Date appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public Vector getVector() {
		Vector v = new Vector<>();
		
		SimpleDateFormat appDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat appTime = new SimpleDateFormat("HH:mm");
		
		v.add(id);
		v.add(receptionist.getId());
		v.add(patient.getId());
		v.add(appDate.format(appointmentTime));
		v.add(appTime.format(appointmentTime));
		v.add(status);
		
		return v;
	}
	
	
}
