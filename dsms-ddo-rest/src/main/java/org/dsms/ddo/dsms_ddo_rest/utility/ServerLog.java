package org.dsms.ddo.dsms_ddo_rest.utility;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;


public class ServerLog {
	
	public static String PATH_Logger = "/Users/karandsingh/Desktop/ServerLog/";
	static PrintWriter writer = null;
	static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
	static Date date = new Date();
	static BufferedReader reader;
	
	public static void init(String serverName)
	{
		String filePath = PATH_Logger + serverName + ".txt";
		
		try 
		{
			writer = new PrintWriter(new FileWriter(filePath, true));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static synchronized void enterLog(String serverName, String managerID, String action, String action_reqID, String response)
	{
		String filePath = PATH_Logger + serverName + ".txt";
		
		try 
		{
			writer = new PrintWriter(new FileWriter(filePath, true));
			writer.write(managerID + " | " + action + " | " + action_reqID + " | " + response + " | " + dateFormat.format(date).toString() + "\n");
			writer.close();
			
			System.out.println(" Server Log added...");
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
	}

}
