package rest.metadata;

public class NurseRecord extends StaffRecord 
{
	public enum Designation {JUNIOR,SENIOR};
	public enum Status {ACTIVE,TERMINATED};
	public String StatusDate;
	
	Designation EDesignation;
	Status EStatus;

	public NurseRecord(String FirstName,String LastName,Designation EDesignation,
					   Status EStatus,String statusDate)
	{
		super(FirstName,LastName);
		this.EDesignation = EDesignation;
		this.EStatus = EStatus;
		this.StatusDate = statusDate;
	}

	
	
	public Designation getEDesignation() 
	{
		return EDesignation;
	}



	public void setEDesignation(Designation eDesignation) 
	{
		EDesignation = eDesignation;
	}



	public Status getEStatus() 
	{
		return EStatus;
	}



	public void setEStatus(Status eStatus) 
	{
		EStatus = eStatus;
	}



	public String getEStatusDate() 
	{
		return StatusDate;
	}



	public void setEStatusDate(String statusDate) 
	{
		StatusDate = statusDate;
	}



	@Override
	public String toString() 
	{
		return  "NurseRecord  ["+     super.toString()+" EDesignation=" + EDesignation + ", EStatus=" + EStatus + ", EStatusDate=" + StatusDate
				+ "]";
	}
}
