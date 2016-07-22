package user_metadata;

public class TransferRecord_Metadata {
	
	String managerID;
	String recordID;
	String remoteServer;
	
	
	
	public TransferRecord_Metadata(String managerID, String recordID, String remoteServer) {
		super();
		this.managerID = managerID;
		this.recordID = recordID;
		this.remoteServer = remoteServer;
	}
	
	public String getManagerID() {
		return managerID;
	}
	public void setManagerID(String managerID) {
		this.managerID = managerID;
	}
	public String getRecordID() {
		return recordID;
	}
	public void setRecordID(String recordID) {
		this.recordID = recordID;
	}
	public String getRemoteServer() {
		return remoteServer;
	}
	public void setRemoteServer(String remoteServer) {
		this.remoteServer = remoteServer;
	}
	
	@Override
	public String toString() {
		String Return="{\n\t";
		Return = Return + "\"managerID\":"      + "\""+ managerID       +"\",";
		Return=  Return + "\n\t";
		Return = Return + "\"recordID\":"      + "\""+ recordID       +"\",";
		Return=  Return + "\n\t";
		Return = Return + "\"remoteServer\":"       + "\""+ remoteServer        +"\"";
		Return=  Return + "\n}";	
		
		return Return;
	}

	
//	{
//	    "managerID" : "MTL0001",
//	    "recordID" : "DR00001",
//	    "remoteServer" : "SERVER_DDO"
//	}
}
