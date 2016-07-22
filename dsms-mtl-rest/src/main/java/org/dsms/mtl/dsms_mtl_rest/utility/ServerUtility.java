package org.dsms.mtl.dsms_mtl_rest.utility;


	import java.io.PrintWriter;
	import java.io.StringWriter;
	import java.rmi.RemoteException;
	import java.text.DateFormat;
	import java.text.SimpleDateFormat;
	import java.util.ArrayList;
	import java.util.Collections;
	import java.util.Date;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Random;
	import java.util.Vector;
	import java.util.concurrent.ConcurrentHashMap;


import rest.metadata.CreateDR_Metadata;
import rest.metadata.CreateDoctor_Metadata;
import rest.metadata.CreateNR_Metadata;
import rest.metadata.CreateNurse_Metadata;
import rest.metadata.DoctorRecord;
import rest.metadata.NurseRecord;
import rest.metadata.TransferRecord_Metadata;


	public class ServerUtility {

		private static String svrName;
		private static NetworkUtility ntwUtility = new NetworkUtility();
		private static ConcurrentHashMap<String , ConcurrentHashMap<String , Object>> Server = new ConcurrentHashMap<String, ConcurrentHashMap<String , Object>>();
		private static ConcurrentHashMap<String, String> denseIndex = new ConcurrentHashMap<String,String>();
		public static int drRecordCount = 0;
		public static int nrRecordCount = 0;
		private static int udp_ports[] = {
									ServerSpot.PORT_UDP_SERVER_MTL, 
									ServerSpot.PORT_UDP_SERVER_LVL,
									ServerSpot.PORT_UDP_SERVER_DDO
								  };
		public static HashMap<Integer,String> portToName = new HashMap<Integer,String>();
		public static HashMap<String,Integer> NameToPort = new HashMap<String,Integer>();
		private static int localSvrPort;
		public static Vector<String> getCountResponse_udp = new Vector<String>();
		static volatile boolean udp_end[] = {true, true, true};

		public int init(String svrName, int localSvrPort) {
				portToName.put(udp_ports[0], ServerSpot.MTL_NAME);
				portToName.put(udp_ports[1],ServerSpot.LVL_NAME);
				portToName.put(udp_ports[2],ServerSpot.DDO_NAME);
				this.localSvrPort = localSvrPort;
				this.svrName = svrName;

				// start UDP server
				ntwUtility.svr = this;
				ntwUtility.locationName = svrName;
				ntwUtility.local_port = localSvrPort;
				ntwUtility.udpServerStart();
				
				// TODO: use semaphore to check if the thread has already started
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					StringWriter err = new StringWriter();
					e.printStackTrace(new PrintWriter(err));
					//SysLogger.err(err.toString());
				}
				return 0;
			}

			public void exit() {
				NetworkUtility.bExitThread = true;
				// TODO: send a packet
			}

			public ConcurrentHashMap<String, ConcurrentHashMap<String, Object>> getServer() 
			{
				return Server;
			}

			public void setServer(ConcurrentHashMap<String, ConcurrentHashMap<String, Object>> server) 
			{
				Server = server;
			}

			@Override
			public String toString() 
			{
				return "DSMSServerUtility [svrName=" + svrName + ",\nServer=" + Server + "\n DenseIndex"+ denseIndex+"]";
			}
			
			
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					/////////////////////////////// INTERFACE IMPLEMENTATIONS ////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
			/**
			 * Method to create Doctor Record invoked remotely by ManagerClient
			 * @param managerID
			 * @param createDoctor<DoctorRecord>
			 * @return Response will be the Record Count of Doctor with the RecordID as needed at 
			 *		   the Client end for the Log.
			 */
			public String createDRecord (CreateDR_Metadata createDoctor)
			{
				String response = "-1";
				
				DoctorRecord.Specialization Specialization =  (createDoctor.getSpecialization().equals("SURGEON")) ? DoctorRecord.Specialization.SURGEON : DoctorRecord.Specialization.ORTHOPAEDIC ;
				DoctorRecord.Location Location = (createDoctor.getLocation().equals("DDO")) ? DoctorRecord.Location.DDO :
					                   ((createDoctor.getLocation().equals("MTL") ?  DoctorRecord.Location.MTL : DoctorRecord.Location.LVL));
				
				DoctorRecord DRecord = new DoctorRecord(createDoctor.getFirstName(), createDoctor.getLastName(), createDoctor.getAddress(),
						createDoctor.getPhone(), Specialization, Location);
				try
				{
					incrementRecordCount("DR");

					System.out.println(" Generating new DoctorId for the record...");
					System.out.println("\n\n Manager with ID: "+ createDoctor.getID() + "requested for Doctor Record Creation...");
					
					DRecord.setRecordID(generateRecord("DR"));
					
					System.out.println(" New DoctorId for the record generated..." + DRecord.getRecordID());
					
					denseIndex.put(DRecord.getRecordID(), DRecord.getLastName().charAt(0)+"");
					
					System.out.println("\n\n Dense Index for the DoctorID created..." + denseIndex.size() + Server.size());
					
					if(Server.containsKey(DRecord.getLastName().charAt(0) +""))
					{
						System.out.println("\n\n Inner Hash Map with key:" + DRecord.getLastName().charAt(0));
						(Server.get(DRecord.getLastName().charAt(0) +"")).put(DRecord.getRecordID(), DRecord);
						System.out.println("\n\n Record ADDED..." + Server.size());
					}
					else
					{
						System.out.println("\n\n Creating a new Inner Hash Map with key:" + DRecord.getLastName().charAt(0));
						ConcurrentHashMap<String , Object> temp = new ConcurrentHashMap<String,Object>();
						temp.put(DRecord.getRecordID(), DRecord);
						Server.put(DRecord.getLastName().charAt(0) +"",temp );
						System.out.println("\n\n New Inner Hash Map created...");
						System.out.println("\n\n Record ADDED...");
					}
					
					response  = drRecordCount + ":" + DRecord.getRecordID();
					
					System.out.println("\n\n Replying back to the client...");
					
					ServerLog.enterLog(svrName, DRecord.getManagerID(), "CreateDRecord", DRecord.getRecordID(), "SUCCESS");
				}
				catch(Exception ex)
				{
					ServerLog.enterLog(svrName, DRecord.getManagerID(), "CreateDRecord", DRecord.getRecordID(), "ERROR");
				}
				
				return response;
			}
			
			/**
			 * Method to create Nurse Record invoked remotely by ManagerClient
			 * @param managerID
			 * @param createNurse<NurseRecord>
			 * @return Response will be the Record Count of Nurse with the RecordID as needed at 
			 * 		   the Client end for the Log.
			 */
			public String createNRecord(CreateNR_Metadata createNurse)
			{
				String response = "-1";
				NurseRecord NRecord = null;
				try
				{
					incrementRecordCount("NR");
					
					System.out.println(" Generating new NurseId for the record...");
					System.out.println("\n\n Manager with ID: "+ createNurse.getID() + "requested for Nurse Record Creation...");
					
					NurseRecord.Status Status = (createNurse.getStatus().equals("ACTIVE")) ? NurseRecord.Status.ACTIVE : NurseRecord.Status.TERMINATED;
					NurseRecord.Designation Designation =  (createNurse.getDesignation().equals("JUNIOR")) ? NurseRecord.Designation.JUNIOR : NurseRecord.Designation.SENIOR;
					
					
					NRecord = new NurseRecord(createNurse.getFirstName(), createNurse.getLastName(), 
							Designation, Status, createNurse.getStatusDate()); 
					NRecord.setRecordID(generateRecord("NR"));
					
					System.out.println(" New DoctorId for the record generated...");
					denseIndex.put(NRecord.getRecordID(), NRecord.getLastName().charAt(0)+"");	
					
					System.out.println("\n\n Dense Index for the NurseID created...");
					if(Server.containsKey(NRecord.getLastName().charAt(0) +""))
					{
						System.out.println("\n\n Inner Hash Map with key:" + NRecord.getLastName().charAt(0));
						(Server.get(NRecord.getLastName().charAt(0) +"")).put(NRecord.getRecordID(), NRecord);
						System.out.println("\n\n Record ADDED...");
					}
					else
					{
						System.out.println("\n\n Creating a new Inner Hash Map with key:" + NRecord.getLastName().charAt(0));
						ConcurrentHashMap<String , Object> temp = new ConcurrentHashMap<String,Object>();
						temp.put(NRecord.getRecordID(), NRecord);
						Server.put(NRecord.getLastName().charAt(0) +"",temp );
						System.out.println("\n\n New Inner Hash Map created...");
						System.out.println("\n\n Record ADDED...");
					}
					
					response  = nrRecordCount + ":" + NRecord.getRecordID();
					
					System.out.println("\n\n Replying back to the client...");
					
					ServerLog.enterLog(svrName, createNurse.getID(), "CreateNRecord", NRecord.getRecordID(), "SUCCESS");
				}
				catch(Exception ex)
				{
					ServerLog.enterLog(svrName, createNurse.getID(), "CreateNRecord", NRecord.getRecordID(), "ERROR");
				}
				return response;
					
			}
			
			/**
			 * Returns the total count of the requested record from the Server by making UDP calls
			 * @param managerID
			 * @param RecordType
			 * @return
			 */
			public String getRecordCounts(String managerID, String RecordType)
			{
				String response = "-1";
				getCountResponse_udp.clear();
				udp_end[0]=true;
				udp_end[1]=true;
				udp_end[2]=true;
				try
				{
					System.out.println("Get Record Count : " + RecordType);
					
					{
						for(int i =0; i < udp_ports.length ; i++)
						{
							System.out.println(udp_ports[i] +":"+localSvrPort );
							if(udp_ports[i] != localSvrPort)
							{
								
								 System.out.println("\n\n Initiate the UDP Sender for Port: " + udp_ports[i]);
								 UDPSender remoteServer = new UDPSender(portToName.get(udp_ports[i]),udp_ports[i], RecordType, i);
								 remoteServer.start();
								 System.out.println(" Thread for UDP Sender started...");
							}
							else
							{
								System.out.println("\n\n Obtained Count from Port: " + udp_ports[i]);
								getCountResponse_udp.add(getLocalCount(RecordType));
								udp_end[i] = false;
							}
					}

					
					System.out.println("\n\n Waiting for the Reply from all the Ports");

					
					while(udp_end[0] || udp_end[1] || udp_end[2])
					{
						System.out.println("Waiting ....");
					}
					
					
					
					response  = getCountResponse_udp.get(0) +"#"+ getCountResponse_udp.get(1) +"#"+ getCountResponse_udp.get(2);
					System.out.println("\n\n Replying back to the client...");

					ServerLog.enterLog(svrName, managerID, "GetRecordCount", RecordType, "SUCCESS");
					return response;
					}
				}
				catch(Exception ex)
				{
					ServerLog.enterLog(svrName, managerID, "GetRecordCount", RecordType, "ERROR");
					return response;
				}
			}
			
			/**
			 * Returns the local count of the requested record
			 * @param RecordType
			 * @return
			 */
			public String getLocalCount(String RecordType)
			{
				String response = "";
				try
				{
					if(RecordType.equals("NR"))
						response = svrName + ":" + String.valueOf(nrRecordCount);
					else if(RecordType.equals("DR"))
						response = svrName + ":" + String.valueOf(drRecordCount);
					else
						response = svrName + ":" + String.valueOf(nrRecordCount+drRecordCount);
					
					
					return response;
					
				}
				catch(Exception ex)
				{
					return response;
				}
			}
			
			/**
			 * Method to find the record from the hash map based on the RecordID
			 * @param RecordID
			 * @return <Object> DoctorRecor/NurseRecord
			 */
			public DoctorRecord getDRecordbyID(String RecordID)
			{
				System.out.println("\n\n Request for the Record with RecordID : " + RecordID);
				
				try
				{
					ConcurrentHashMap<String , Object> editCache = new ConcurrentHashMap<String,Object>();
					String last_name = denseIndex.get(RecordID);
					
					editCache = Server.get(last_name);
					
					if(Server.size() != 0)
					{
				
							DoctorRecord dRecord = (DoctorRecord)editCache.get(RecordID);
							System.out.println("\n\n Replying back to the client...");
							return dRecord;
						
					}
					else
					{
						return null;
					}
				}
				catch(Exception ex)
				{
					System.out.println("ERROR :" + ex.toString());
					return null;
				}
				
			}
			
			/**
			 * Method to find the record from the hash map based on the RecordID
			 * @param RecordID
			 * @return <Object> DoctorRecor/NurseRecord
			 */
			public NurseRecord getNRecordbyID(String RecordID)
			{
				System.out.println("\n\n Request for the Record with RecordID : " + RecordID);
				
				try
				{
					ConcurrentHashMap<String , Object> editCache = new ConcurrentHashMap<String,Object>();
					String last_name = denseIndex.get(RecordID);
					
					editCache = Server.get(last_name);
					if(Server.size() != 0)
					{
						NurseRecord nRecord = (NurseRecord)editCache.get(RecordID);
						System.out.println("\n\n Replying back to the client...");
						return nRecord;
					
					}
					else
					{
						return null;
					}
				}
				catch(Exception ex)
				{
					System.out.println("ERROR :" + ex.toString());
					return null;
				}
				
			}
			
			/**
			 * Updates the old record with the Object of new Record.
			 * @param RecordID
			 * @param editVal
			 * @return
			 * @throws RemoteException
			 */
			
			public String editRecord(String RecordID, Object editVal){
				// TODO Auto-generated method stub
				try
				{
					System.out.println("\n\n Editing the Record with RecordID : "+ RecordID);
					(Server.get(denseIndex.get(RecordID))).replace(RecordID, editVal);
					System.out.println(" Record Editted...");
					return "0";
				}
				catch(Exception ex)
				{
					return "-1";
				}
			}
			
			
			/**
			 * 
			 * @param managerID
			 * @param recordID
			 * @param remoteClinicServerName
			 * @return
			 */
			public String transferRecord(TransferRecord_Metadata transfer) {
				String response = "";
				
				String recordID = transfer.getRecordID();
				String managerID = transfer.getManagerID();
				String remoteClinicServerName = transfer.getRemoteServer();
				
				getCountResponse_udp.clear();
				udp_end[0]=true;
				NameToPort.put(ServerSpot.SERVER_MTL,udp_ports[0] );
				NameToPort.put(ServerSpot.SERVER_LVL,udp_ports[1] );
				NameToPort.put(ServerSpot.SERVER_DDO,udp_ports[2] );
				DoctorRecord Doctor;
				NurseRecord Nurse;
				//StaffRecord temp= (StaffRecord) getRecordbyID(recordID);
 				StringBuilder Message = new StringBuilder();
				if(recordID.charAt(0)=='D')
				{
					System.out.println("Marshalling  DR Record");
					Doctor = getDRecordbyID(recordID);
					Message.append("D;").append(Doctor.getFirstName()).append(";").append(Doctor.getLastName()).append(";");
					Message.append(Doctor.getAddress()).append(";").append(Doctor.getPhone()).append(";");
					Message.append(Doctor.getESpecialization()).append(";").append(Doctor.getELocation()).append(";");
					Message.append(managerID).append(";").append(remoteClinicServerName);
					String lastname = denseIndex.get(recordID);
					ConcurrentHashMap<String, Object> tempMap = Server.get(lastname);
					tempMap.remove(Doctor.getRecordID());
					drRecordCount--;
					Server.put(lastname, tempMap);
					
					System.out.println("Message is ready !");
				}
				else
				{
					System.out.println("Marshalling  NR Record");
					Nurse = getNRecordbyID(recordID);
					Message.append("N;").append(Nurse.getFirstName()).append(";").append(Nurse.getLastName()).append(";");
					Message.append(Nurse.getEDesignation()).append(";").append(Nurse.getEStatus()).append(";");
					Message.append(Nurse.StatusDate).append(";").append(managerID).append(";").append(remoteClinicServerName);
					String lastname = denseIndex.get(recordID);
					ConcurrentHashMap<String, Object> tempMap = Server.get(lastname);
					tempMap.remove(Nurse.getRecordID());
					nrRecordCount--;
					Server.put(lastname, tempMap);
				}
				
				int port = NameToPort.get(remoteClinicServerName);
				
				System.out.println("Preparing to  send to" + remoteClinicServerName  );
				UDPSender remoteServer = new UDPSender("localhost", port,Message.append(managerID).toString(), 0);
				remoteServer.start();
				 
				System.out.println("\n\n Waiting for the Reply !");

					
				while(udp_end[0])
				{
						//System.out.println("Waiting ....");
				}
				 
				response = getCountResponse_udp.get(0);
				System.out.println("\n\n Record Tranfered to : " + remoteClinicServerName);
				
				return response;
			}

			public String parser(String msg_recv) {
			
				String response = "-1";
				
				System.out.println("transfer almost complete...");
				String[] temp = msg_recv.split(";");
				String recordType= temp[0];
				String server = "";
				
				if(temp[temp.length-1].contains("SERVER_MTL"))
					 server = "MTL";
				else if(temp[temp.length-1].contains("SERVER_LVL"))
					 server = "LVL";
				else 
					 server = "DDO";
				 
				 
				 
				if(recordType.charAt(0)=='D')
				{
					/* doctor.setFirstName(temp[0]);
					 doctor.setLastName(temp[1]);
					 doctor.setAddress(temp[2]);
					 doctor.setPhone(temp[3]);
					 if(temp[4].equals("ORTHOPAEDIC"))
					 
					 if(temp[5].equals("LVL"))*/
					
//					CreateDoctor_Metadata createDoctor = new CreateDoctor_Metadata(temp[0], temp[1], temp[2], temp[3], 
//							temp[4], temp[5], temp[6]);
//					
//					 response = createDRecord(createDoctor);
					
					CreateDR_Metadata doctor = new CreateDR_Metadata();
					doctor.setFirstName(temp[1]);
					doctor.setLastName(temp[2]);
					doctor.setAddress(temp[3]);
					doctor.setPhone(temp[4]);
					doctor.setSpecialization(temp[5]);
					doctor.setLocation(temp[6]);
					doctor.setID(temp[7]);
					
					response = createDRecord(doctor);
				}
				else
				{
					 /*
					  *  nurse.setFirstName(temp[0]);
							 nurse.setLastName(temp[1]);
							 if(temp[2].equals("JUNIOR"))
								 nurse.setEDesignation(Designation.JUNIOR);
						     else if(temp[2].equals("SENIOR"))
						    	 nurse.setEDesignation(Designation.SENIOR);	
							 if(temp[3].equals("ACTIVE"))
								 nurse.setEStatus(Status.ACTIVE);
							 else  if(temp[3].equals("TERMINATED"))
								 nurse.setEStatus(Status.TERMINATED);
							 nurse.setEStatusDate(temp[3]);
					  */
					
					CreateNR_Metadata createNurse = new CreateNR_Metadata();
					
					createNurse.setFirstName(temp[1]);
					createNurse.setLastName(temp[2]);
					createNurse.setDesignation(temp[3]);
					createNurse.setStatus(temp[4]);
					createNurse.setStatusDate(temp[5]);
					createNurse.setID(temp[6]);
							
					response = createNRecord(createNurse);
				}
					
				 
				 
				 return response;
				
			}
			
			
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
							////////////// ENDS ///////////////////// ENDS ///////////////// ENDS	//////////
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			
			public ConcurrentHashMap<String, String> getDenseIndex() 
			{
				return denseIndex;
			}

			public void setDenseIndex(ConcurrentHashMap<String, String> denseIndex) 
			{
				this.denseIndex = denseIndex;
			}

			public int getDrRecordCount() 
			{
				return drRecordCount;
			}

			public void setDrRecordCount(int drRecordCount) 
			{
				this.drRecordCount = drRecordCount;
			}

			public int getNrRecordCount() 
			{
				return nrRecordCount;
			}

			public void setNrRecordCount(int nrRecordCount) 
			{
				this.nrRecordCount = nrRecordCount;
			}

			public String generateRecord(String recordType)
			{
				
				if(recordType== "DR")
					return "DR"+String.format("%05d", getDrRecordCount());
				
				else
					return "NR"+String.format("%05d", getNrRecordCount());
			}

			public void incrementRecordCount(String recordType)
			{
				if(recordType== "DR")
				{
					int oldRecordCount =getDrRecordCount();
					setDrRecordCount(++oldRecordCount);
					
				}
				else
				{
					int oldRecordCount =getNrRecordCount();
					setNrRecordCount(++oldRecordCount);
				}
			}
			
	}

