package client;


import java.rmi.RemoteException;
import Logger.Log;
import parsers.DoctorParser;
import parsers.NurseParser;
import parsers.ParserUtility;
import user_metadata.GetDoctor_MetaData;
import user_metadata.GetNurse_MetaData;

public class ConnectionThread implements Runnable{
	
	String serverURL;
	
	int choice;
	String recordString;
	String Record;
	Object editVal;
	String recordType;
	static Object getRecord_obj;
	static String response;
	String recordID;
	static volatile boolean flag = true; 
	String managerID;
	
	public ConnectionThread(String serverURL, String recordString,String managerID, int choice) {
		this.serverURL = serverURL;
		this.recordString = recordString;
		this.choice = choice;
		this.managerID = managerID;
		flag = true;

	}
	
	
	public ConnectionThread(String URL,String recordID, int choice) {
		this.serverURL =URL;
		this.recordID = recordID;
		this.choice = choice;
		flag = true;

	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////// REMOTE FUNCTIONS CALLS ///////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	

	/**
	 * Invokes the remote function for creating the Doctor Record
	 * @param server
	 * @return <String> "0" : Success, "-1": Failure
	 * @throws RemoteException
	 */
	public String createDRecord() {

		String ret = "";
		String tempArr[];
		
		try
		{
			DoctorParser  parser = new DoctorParser();
			ret = parser.performPostonData(serverURL, recordString);
			System.out.println("Size : " + ret);
			
			if(ret.equals("-1"))
			{
				Log.enterLog("CreateDRecord", "-1", "ERROR", managerID);
				return ret;
			}
			else
			{
				tempArr = ret.split(":");
				Log.enterLog("CreateDRecord", tempArr[1], tempArr[0], managerID);
				return "0";
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
			return "-1";
		}
	}
	
	/**
	 * Invokes the remote function for creating the Nurse Record
	 * @param server
	 * @return <String> "0" : Success, "-1": Failure
	 * @throws RemoteException
	 */
	public String createNRecord()  {
		
		String ret = null;
		String tempArr[];
		
		try
		{
			NurseParser  parser = new NurseParser();
			ret = parser.performPostonData(serverURL, recordString);
			System.out.println("Size : " + ret);
			if(ret.equals("-1"))
			{
				Log.enterLog("CreateNRecord", ret, "ERROR", managerID);
				return ret;
			}
			else
			{
				tempArr = ret.split(":");
				Log.enterLog("CreateNRecord", tempArr[1], tempArr[0], managerID);
				return "0";				
			}

		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
			return ret;
		}
		
	}
	
	/**
	 * Invokes the remote function to get the count of the Record type Requested.
	 * @param server
	 * @return <Integer> Count requested
	 * @throws RemoteException
	 */
	public String getRecordCount( String managerID, String recordType)
	{
		String ret = null;
		try
		{
			ParserUtility utility = new ParserUtility();
			ret = utility.getCountFromURL(serverURL+managerID+":"+recordType);
			Log.enterLog("GetRecordCount", recordType, "SUCCESS", managerID);
			
			return ret;
			
			
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
			Log.enterLog("GetRecordCount", recordType, "ERROR", managerID);
			
			return ret;
		}
	}
	
	/**
	 * Invokes the remote function for fetching the record based on the Record ID
	 * @param server
	 * @param RecordID
	 * @return
	 */
	public String getRecordbyID(String serverURL,String  recordID)
	{
		String ret = null;
		getRecord_obj = null;
		try
		{
			if(recordID.substring(0,2).toUpperCase().compareTo("DR")==0){
				DoctorParser doctorParser = new  DoctorParser();
				GetDoctor_MetaData  doctor = doctorParser.getParsedListfromGetDoctor(serverURL).get(0);
				getRecord_obj = doctor;
			}
			else{
				NurseParser nurseParser = new NurseParser();
				GetNurse_MetaData nurse = nurseParser.getParsedListfromGetNurse(serverURL).get(0);
				getRecord_obj = nurse;
			}
			
			if(getRecord_obj == null)
			{
				ret ="-1";
			}
			else
			{
				ret ="0";
			}
			return ret;
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
			return "-1";
		}
	}
	
	/**
	 * Invokes the remote function to edit the Record.
	 * @param server
	 * @return <Integer> Count requested
	 * @throws RemoteException
	 */
	public String editRecord(){
		String ret = "-1";
		try{
			ParserUtility  parser = new ParserUtility();
			ret = parser.performPostonData(serverURL, recordString);
			System.out.println("Size : " + ret);
			if(ret.equals("-1")){
				Log.enterLog("EditRecord", "-1", "ERROR", managerID);
				return ret;
			}
			else
				Log.enterLog("EditRecord", ret, "SUCCESS", managerID);
		}
		catch(Exception ex){
			System.out.println(ex.toString());
			return ret;
		}
		return ret;
	}
	
	public String transferRecord(){
		String ret = "-1";
		try{
			ParserUtility  parser = new ParserUtility();
			ret = parser.performPostonData(serverURL, recordString);
			System.out.println("Size : " + ret);
			if(ret.equals("-1")){
				Log.enterLog("TransferRecord", "-1", "ERROR", managerID);
				return ret;
			}
			else
				Log.enterLog("TransferRecord", ret, "SUCCESS", managerID);
		}
		catch(Exception ex){
			System.out.println(ex.toString());
			return ret;
		}
		return ret;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try
		{
		

			switch(choice)
			{
				case 0: response = createDRecord(); 
						if(response != null)
						{
							flag = false;
						}	
					break;
					
				case 1: response = createNRecord();	
						if(response != null)
						{
							flag = false;
						}	
					break;
					//GetDoctor_MetaData  doctor = doctorParser.getParsedListfromGetDoctor("http://192.168.2.14:8080/dsms-mtl-rest/webapi/getDR/"+RecordID).get(0);
					
				case 2: response = getRecordCount(managerID, recordString);	
						if(response != null)
						{
							flag = false;
						}
					break;
					
				
			
					
				case 3: response = getRecordbyID(serverURL, recordID);
						if(response != null)
						{
							flag = false;
						}	
					break;
					
				case 4: response  = editRecord();
						if(response != null)	
						{
							flag = false;
						}
					break;
					
				case 5: response  = transferRecord();
						if(response != null)	
						{
							flag = false;
						}
					break;	
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
	}

}
