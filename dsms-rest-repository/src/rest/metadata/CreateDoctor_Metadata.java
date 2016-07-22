package rest.metadata;


public class CreateDoctor_Metadata {

	public String Address,Phone;
	public String Specialization;
	public String Location ;  
	public String ID;
	public String FirstName, LastName;
	
	
	
//	public CreateDoctor_Metadata(String firstName, String lastName, String address, String phone, String specialization, 
//			String location, String iD) 
//	{
//		super();
//		Address = address;
//		Phone = phone;
//		Specialization = specialization;
//		Location = location;
//		ID = iD;
//		FirstName = firstName;
//		LastName = lastName;
//	}
	
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
	public String getID() {
		return ID;
	}
	public void setID(String ID) {
		this.ID = ID;
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
	
}
