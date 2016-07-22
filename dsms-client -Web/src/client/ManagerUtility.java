package client;

public class ManagerUtility {

	
	public static void createDRecord_connectionThread(String URL, String doctorParam, String managerID, int choice){
		(new Thread(new ConnectionThread(URL,doctorParam, managerID, choice))).start();
	}
	

	public static void createNRecord_connectionThread(String URL, String nurseParam, String managerID, int choice){
		(new Thread(new ConnectionThread(URL,nurseParam, managerID, choice))).start();
	}


	public static void getRecordbyID_connectionThread(String URL,String recordID, int choice) {
		(new Thread(new ConnectionThread(URL,recordID, choice))).start();
		
	}
	
	public static void getCount_connectionThread(String URL,String recordType,String managerID, int choice) {
		(new Thread(new ConnectionThread(URL,recordType,managerID, choice))).start();
		
	}
	
	
	

	

}
