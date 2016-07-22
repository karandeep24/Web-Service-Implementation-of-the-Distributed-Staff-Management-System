package rest.metadata;

import java.io.Serializable;

public class DoctorRecord extends StaffRecord implements Serializable
{
	public String  Address,Phone;
	public enum Specialization {SURGEON,ORTHOPAEDIC};
	public enum Location {LVL,MTL,DDO};  
	public Specialization ESpecialization;
	public Location ELocation;
	String managerID;
	
	public String getManagerID() {
		return managerID;
	}


	public void setManagerID(String managerID) {
		this.managerID = managerID;
	}

	
	public DoctorRecord(String FirstName,String LastName,String  Address,String Phone, 
			            Specialization ESpecialization,Location ELocation)
	{
		super(FirstName,LastName);
		this.Address = Address;
		this.Phone = Phone;
		this.ESpecialization = ESpecialization;
		this.ELocation = ELocation;
		
	}


	public String getAddress() 
	{
		return Address;
	}



	public void setAddress(String address) 
	{
		Address = address;
	}



	public String getPhone() 
	{
		return Phone;
	}



	public void setPhone(String phone) 
	{
		Phone = phone;
	}



	public Specialization getESpecialization() 
	{
		return ESpecialization;
	}



	public void setESpecialization(Specialization eSpecialization) 
	{
		ESpecialization = eSpecialization;
	}



	public Location getELocation() 
	{
		return ELocation;
	}



	public void setELocation(Location eLocation) 
	{
		ELocation = eLocation;
	}



	@Override
	public String toString() {
		return  "DoctorRecord ["+     super.toString()+" Address=" + Address + ", Phone=" + Phone + ", ESpecialization=" + ESpecialization
				+ ", ELocation=" + ELocation + "]";
		
		
	}
	
	
}




