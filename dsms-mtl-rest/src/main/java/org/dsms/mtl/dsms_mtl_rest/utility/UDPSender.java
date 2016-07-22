package org.dsms.mtl.dsms_mtl_rest.utility;



import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPSender extends Thread 
{

	private Thread t;
	private String serverName;
	private int port;
	public String recordType;
	public String msg_recv;
	int threadID;
	
	UDPSender( String serverName, int port, String recordType, int threadID)
	   {
		   this.serverName = serverName;
		   this.port = port;
		   this.recordType = recordType;
		   this.threadID =threadID;
	   }
	   public void run() 
	   {
	      
	      DatagramSocket aSocket = null;
	      try 
	      {
				Thread portno = Thread.currentThread();
				aSocket = new DatagramSocket();
				byte [] messageToSend = (recordType).getBytes("UTF-8");
				byte[] messageToReceive = new byte[1000];
				
				System.out.println("\n\n UDP Sender preparing to send at Port: " + port);
				
				InetAddress aHost = InetAddress.getByName(serverName);
				DatagramPacket requestserver =new DatagramPacket(messageToSend, messageToSend.length, aHost, port );
				aSocket.send(requestserver);
				
				System.out.println("Messege SENT !");
				
				
				DatagramPacket reply = new DatagramPacket(messageToReceive, messageToReceive.length);
				aSocket.receive(reply);
				
				msg_recv = new String(reply.getData(),0,reply.getLength());
				System.out.println("\n\n UDP Sender Recieved a Message : " + msg_recv);
				
				ServerUtility.getCountResponse_udp.add(msg_recv);
				System.out.println("\n\n Obtained Count from Port : " + port);
				ServerUtility.udp_end[threadID] = false;
			}
			catch (Exception e)
			{
				System.out.println("Socket: " + e.getMessage());
			} 
			 
			finally 
			{
				if(aSocket != null) aSocket.close();
			}
	   
	   }
	   
	   public void start ()
	   {
	      
	      if (t == null)
	      {
	         t = new Thread (this, serverName);
	         t.start ();
	      }
	   }
}
