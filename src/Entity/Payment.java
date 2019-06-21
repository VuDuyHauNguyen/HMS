package Entity;

public class Payment {
	private int checkupID;
	private int receptionistID;
	private String details;
	private double amount;
	private String payDate;
	private String payTime;
	private String status;
	
	public int getCheckupID() {
		return checkupID;
	}
	public void setCheckupID(int checkupID) {
		this.checkupID = checkupID;
	}
	public int getReceptionistID() {
		return receptionistID;
	}
	public void setReceptionistID(int receptionistID) {
		this.receptionistID = receptionistID;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
