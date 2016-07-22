package org.dsms.lvl.dsms_lvl_rest.utility;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class NetworkUtility implements Runnable {
	
	public static final int BUFFER_SIZE = 1500;
	public static int local_port;
	public static String locationName;
	public static boolean bExitThread = false;
	public static ServerUtility svr = null;
	String msg_recv;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		DatagramSocket udpSock = null;
		try {
			udpSock = new DatagramSocket(local_port);
			byte[] messageToReceive = new byte[1000];
			
			byte[] messageToSend = null;
			while (!bExitThread) 
			{
				System.out.println(locationName + " now listening at PORT : " + local_port);
				 
				 DatagramPacket request = new DatagramPacket(messageToReceive, messageToReceive.length);
				 udpSock.receive(request);
			
				 System.out.println(" Message Recieved via UDP PAcket - " + (new String(request.getData(),0,request.getLength())));
				 msg_recv = (new String(request.getData(),0,request.getLength()));
				 
				 if(!msg_recv.contains(";"))
				 {
				 
					 if(msg_recv.equals("DR"))
					 {
						 System.out.println(" Message to Send : " + locationName + ":" + ServerUtility.drRecordCount);
						 messageToSend = (locationName + ":" + ServerUtility.drRecordCount).getBytes("UTF-8");
					 }
					 else if(msg_recv.equals("NR"))
					 {
						 System.out.println(" Message to Send : " + locationName + ":" + ServerUtility.nrRecordCount);
						 messageToSend = (locationName + ":" + ServerUtility.nrRecordCount).getBytes("UTF-8");
					 }
					 else
					 {
						 System.out.println(" Message to Send : " + locationName + ":" + (ServerUtility.nrRecordCount + ServerUtility.drRecordCount));
						 messageToSend = (locationName + ":" + (ServerUtility.nrRecordCount + ServerUtility.drRecordCount) ).getBytes("UTF-8");
					 }
				 }
				 else
				 {
					 String tempResponse = svr.parser(msg_recv);
					 System.out.println("\n\n Message to send to UDPSender : " + tempResponse);
					 messageToSend = tempResponse.getBytes("UTF-8");
					 
				 }
				 DatagramPacket reply = new DatagramPacket(messageToSend, messageToSend.length, request.getAddress(), request.getPort());
				 udpSock.send(reply);
				 System.out.println(" Message Sent to Port : " + request.getPort() + "\n\n");
				 
			}
			udpSock.close();
		} catch (Exception e) {
			StringWriter err = new StringWriter();
			e.printStackTrace(new PrintWriter(err));
		}
		
	}

	public void udpServerStart() {
		System.out.println("Starting to listen for UDP...");
		(new Thread(new NetworkUtility())).start();
	}
}
