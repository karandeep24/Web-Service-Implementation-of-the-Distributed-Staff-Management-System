package user_metadata;

public class CreateDoctor_Metadata {

	public String  Address,Phone;
	public String Specialization;
	public String Location ;  
	public String managerID;
	public String FirstName, LastName;
	
	
	
	public CreateDoctor_Metadata(String address, String phone, String specialization, String location, String managerID,
			String firstName, String lastName) {
		super();
		Address = address;
		Phone = phone;
		Specialization = specialization;
		Location = location;
		this.managerID = managerID;
		FirstName = firstName;
		LastName = lastName;
	}
	
	
	public CreateDoctor_Metadata() {
		
	}


	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getSpecialization() {
		return Specialization;
	}
	public void setSpecialization(String specialization) {
		Specialization = specialization;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getManagerID() {
		return managerID;
	}
	public void setManagerID(String managerID) {
		this.managerID = managerID;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String FirstName) {
		this.FirstName = FirstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String LastName) {
		this.LastName = LastName;
	}
	
	
	@Override
	public String toString() {
		String Return="{\n\t";
		Return = Return + "\"ID\":"      + "\""+ managerID       +"\",";
		Return=  Return + "\n\t";
		Return = Return + "\"FirstName\":"      + "\""+ FirstName       +"\",";
		Return=  Return + "\n\t";
		Return = Return + "\"LastName\":"       + "\""+ LastName        +"\",";
		Return=  Return + "\n\t";
		Return = Return + "\"Address\":"        + "\""+ Address  	    +"\",";
		Return=  Return + "\n\t";
		Return = Return + "\"Phone\":"          + "\""+ Phone           +"\",";
		Return=  Return + "\n\t";
		Return = Return + "\"Specialization\":" + "\""+ Specialization  +"\",";
		Return=  Return + "\n\t";
		Return = Return + "\"Location\":"       + "\""+ Location        +"\"";
		Return=  Return + "\n}";	
		
		return Return;
	}
	
	
}
