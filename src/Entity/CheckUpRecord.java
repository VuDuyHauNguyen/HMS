package Entity;

import java.util.Date;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import Boundary.Helpers.DateTimeHelper;

@Entity
@Table(name="checkUpRecords")
public class CheckUpRecord implements Vectorable{

	public final static String STATUS_IN_PROGRESS	= "checkup";
	public final static String STATUS_CANCEL 		= "cancel";
	public final static String STATUS_QUEUE 		= "queue";
	public final static String STATUS_DONE 			= "done";
	
	@Id
	private int id;
	
	@ManyToOne
	@JoinColumn(name="patientId")
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name="doctorId")
	private Employee doctor;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date checkUpRecordTime;
	
	private String medicalProblem, checkupResult, prescriptions, status;

	//getters & setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Employee getDoctor() {
		return doctor;
	}

	public void setDoctor(Employee doctor) {
		this.doctor = doctor;
	}

	public Date getCheckUpRecordTime() {
		return checkUpRecordTime;
	}

	public void setCheckUpRecordTime(Date checkUpRecordTime) {
		this.checkUpRecordTime = checkUpRecordTime;
	}

	public String getMedicalProblem() {
		return medicalProblem;
	}

	public void setMedicalProblem(String medicalProblem) {
		this.medicalProblem = medicalProblem;
	}

	public String getCheckupResult() {
		return checkupResult;
	}

	public void setCheckupResult(String checkupResult) {
		this.checkupResult = checkupResult;
	}

	public String getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(String prescriptions) {
		this.prescriptions = prescriptions;
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
		
		v.add(id);
		v.add(patient.getId());
		v.add(doctor != null ? doctor.getId() : "");
		v.add(checkUpRecordTime != null? 
				DateTimeHelper.getDisplayDateFromDate(checkUpRecordTime) : "");
		v.add(checkUpRecordTime != null?
				DateTimeHelper.getDisplayTimeFromDate(checkUpRecordTime) : "");
		v.add(medicalProblem);
		v.add(checkupResult);
		v.add(prescriptions);
		v.add(status);
		
		return v;
	}
	
}
