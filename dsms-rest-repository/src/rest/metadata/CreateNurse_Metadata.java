package rest.metadata;

public class CreateNurse_Metadata {

	public String FirstName, LastName;
	public String Designation;
	public String Status;
	public String StatusDate;
	public String ID;
	
	
	
	public CreateNurse_Metadata(String firstName, String lastName, String designation, String status, String statusDate,
			String iD) 
	{
		super();
		FirstName = firstName;
		LastName = lastName;
		Designation = designation;
		Status = status;
		StatusDate = statusDate;
		ID = iD;
	}
	public String getID() {
		return ID;
	}
	public void setID(String ID) {
		this.ID = ID;
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
	
	
}
