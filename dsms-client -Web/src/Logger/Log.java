package Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import user_metadata.CreateDoctor_Metadata;


public class Log {
	
	public static String PATH_Logger = "/Users/karandsingh/Desktop/ManagerLog/";
	public static String PATH_TestData = "/Users/karandsingh/Desktop/";
	static PrintWriter writer = null;
	static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	static Date date = new Date();
	static BufferedReader reader;
	static Vector<LogMetadata> recordVector = new Vector<LogMetadata>();
	static Vector<CreateDoctor_Metadata> testVector = new Vector<CreateDoctor_Metadata>();
	
	public static void init(String managerID)
	{
		
		String filePath = PATH_Logger + managerID + ".txt";
		
		try 
		{
			writer = new PrintWriter(new FileWriter(filePath, true));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
	}
	
	public static synchronized void enterLog(String action, String action_reqID, String response, String managerID)
	{
			
			String filePath = PATH_Logger + managerID + ".txt";
			try 
			{
				writer = new PrintWriter(new FileWriter(filePath, true));
				writer.write(action + "," + action_reqID + "," + response + "," + dateFormat.format(date).toString() + "\n");
				writer.close();
			}
			catch(Exception ex)
			{
				System.out.println(ex.toString());
			}
	}
	
	public  static void exitLog()
	{
		writer.close();
	}
	
	public static Vector<LogMetadata> readLog(String managerID)
	{
		String filePath = PATH_Logger + managerID + ".txt";
		String line;
		LogMetadata logMetadata;
		
		try 
		{
			reader = new BufferedReader(new FileReader(filePath));
			
			while ((line = reader.readLine()) != null) {

				logMetadata = new LogMetadata();
				
				String[] tempData = line.split(",");

				logMetadata.setAction(tempData[0]);
				logMetadata.setAction_requested(tempData[1]);
				logMetadata.setResponse(tempData[2]);
				logMetadata.setLog_date(tempData[3]);
				
				recordVector.addElement(logMetadata);
			}

		}
		catch(Exception ex)
		{
			return null;
		}

		return recordVector;	
	}
	
	
	
	public static Vector<CreateDoctor_Metadata> testData(String FileName)
	{
		String filePath = PATH_TestData + FileName + ".txt";
		String line;
		CreateDoctor_Metadata doctorMetadata;
		
		try 
		{
			reader = new BufferedReader(new FileReader(filePath));
			
			while ((line = reader.readLine()) != null) {
				
				String[] tempData = line.split(",");
//				(String address, String phone, String specialization, String location, String managerID,
//						String firstName, String lastName)
				doctorMetadata = new CreateDoctor_Metadata("","","","", "", "", "");
						
				doctorMetadata.setFirstName(tempData[0]);
				doctorMetadata.setLastName(tempData[1]);
			    doctorMetadata.setAddress(tempData[2]);
			    doctorMetadata.setPhone(tempData[3]);
				doctorMetadata.setSpecialization(tempData[4]);//.equals("SURGEON")) ? DoctorRecord.Specialization.SURGEON : DoctorRecord.Specialization.ORTHOPAEDIC);
				doctorMetadata.setLocation(tempData[5]);
				doctorMetadata.setManagerID(tempData[6]);
//				.equals("DDO")? DoctorRecord.Location.DDO :
//			                   				((tempData[5].equals("MTL") ?  DoctorRecord.Location.MTL : DoctorRecord.Location.LVL))));
				
				testVector.add(doctorMetadata);
			}

		}
		catch(Exception ex)
		{
			return null;
		}

		return testVector;	
	}
	
	
}
