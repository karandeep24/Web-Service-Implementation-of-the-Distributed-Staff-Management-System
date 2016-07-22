package user_metadata;

public class CreateNurse_Metadata {

	public String FirstName, LastName;
	public String Designation;
	public String Status;
	public String StatusDate;
	public String managerID;
	
	public String getManagerID() {
		return managerID;
	}
	public void setManagerID(String managerID) {
		this.managerID = managerID;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getDesignation() {
		return Designation;
	}
	public void setDesignation(String designation) {
		Designation = designation;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getStatusDate() {
		return StatusDate;
	}
	public void setStatusDate(String statusDate) {
		StatusDate = statusDate;
	}
	public CreateNurse_Metadata(String firstName, String lastName, String designation, String status, String statusDate,
			String managerID) {
		super();
		FirstName = firstName;
		LastName = lastName;
		Designation = designation;
		Status = status;
		StatusDate = statusDate;
		this.managerID = managerID;
	}
	@Override
	public String toString() {
		String Return="{\n\t";
		Return = Return + "\"ID\":"         + "\""+ managerID   +"\",";
		Return=  Return + "\n\t";
		Return = Return + "\"FirstName\":"  + "\""+ FirstName   +"\",";
		Return=  Return + "\n\t";
		Return = Return + "\"LastName\":"   + "\""+ LastName    +"\",";
		Return=  Return + "\n\t";
		Return = Return + "\"Status\":"     + "\""+ Status  	+"\",";
		Return=  Return + "\n\t";
		Return = Return + "\"Designation\":"+ "\""+ Designation +"\",";
		Return=  Return + "\n\t";
		Return = Return + "\"StatusDate\":" + "\""+ StatusDate  +"\"";
		Return=  Return + "\n}";	
		
		return Return;
	}
	
	
	
	
}