package Entity;

public class Receptionist {
	private int patientID;
	private int receptID;
	private String appmntDate;
	private String appmntTime;
	private String status;
	
	public int getPatientID() {
		return patientID;
	}
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}
	public int getReceptID() {
		return receptID;
	}
	public void setReceptID(int receptID) {
		this.receptID = receptID;
	}
	public String getAppmntDate() {
		return appmntDate;
	}
	public void setAppmntDate(String appmntDate) {
		this.appmntDate = appmntDate;
	}
	public String getAppmntTime() {
		return appmntTime;
	}
	public void setAppmntTime(String appmntTime) {
		this.appmntTime = appmntTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}	 
}
