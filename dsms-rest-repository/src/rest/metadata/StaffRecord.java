package rest.metadata;

import java.io.Serializable;

public class StaffRecord implements Serializable 
{
	private String FirstName,LastName, RecordID = "";

	
	public StaffRecord(String FirstName,String LastName)
	{
		this.FirstName = FirstName;
		this.LastName  = LastName;
	}
	
	public String getRecordID() 
	{
		return RecordID;
	}

	public void setRecordID(String recordID) 
	{
		RecordID = recordID;
	}

	public String getFirstName() 
	{
		return FirstName;
	}

	public void setFirstName(String firstName) 
	{
		FirstName = firstName;
	}

	public String getLastName() 
	{
		return LastName;
	}

	public void setLastName(String lastname) 
	{
		LastName = lastname;
	}

	@Override
	public String toString() {
		return "FirstName=" + FirstName + ", LastName=" + LastName + ", RecordID=" + RecordID ;
	}
	
	
	
}
