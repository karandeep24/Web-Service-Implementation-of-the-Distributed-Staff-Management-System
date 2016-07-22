package client;
import parsers.*;
import user_metadata.CreateDoctor_Metadata;
import user_metadata.CreateNurse_Metadata;
import user_metadata.GetDoctor_MetaData;
import user_metadata.GetNurse_MetaData;
import user_metadata.TransferRecord_Metadata;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import Logger.Log;
import Logger.LogMetadata;


public class ManagerClient {
	
	JTable table;
	String header[] = {"Action","RecordID","Response","Log Date" };
	DefaultTableModel model;
	
	JFrame frame;
	static JPanel JPanel_Nurse;
	static JPanel JPanel_Doctor;
	static JPanel JPanel_Transfer;
	static JPanel JPanel_RecordCount;
	static JPanel JPanel_EditRecord;
	static JLayeredPane  layeredPane;
	private JTextField tf_Dr_FirstName;
	private JTextField tf_Remote_Record_ID;
	private JTextField tf_Remote_Server_ID;
	private JTextField tf_Dr_LastName;
	private JTextField tf_Dr_Address;
	private JTextField tf_Dr_Phone;
	private JRadioButton rdbtn_Dr_Sugeon;
	private JRadioButton rdbtn_Dr_Ortho;
	private JRadioButton rdbtn_Laval;
	private JRadioButton rdbtn_Montreal;
	private JRadioButton rdbtn_Dollard;
	
	private JDateChooser date_NR_statusDate;
	private JTextField tf_Nr_FirstName;
	private JTextField tf_Nr_LastName;
	private JRadioButton rdbtn_NR_statusTerminated;
	private JRadioButton rdbtn_NR_statusActive;
	private JRadioButton rdbtn_NR_DesignationJunior;
	private JRadioButton rdbtn_NR_DesignationSenior;
	
	private JDateChooser Edit_StatusDate;
	private JRadioButton Edit_StatusTerminated;
	private JTextField Edit_FirstName;
	private JTextField Edit_LastName;
	private JTextField Edit_Address;
	private JTextField Edit_Phone;
	private JTextField Edit_RecordID;
	private JRadioButton Edit_SpecializationSurgeon;
	private JRadioButton Edit_SpecializationOrthopedic;
	private JRadioButton Edit_LocationLaval;
	private JRadioButton Edit_LocationMontreal;
	private JRadioButton Edit_LocationDollard;
	private JRadioButton Edit_DesignationJunior;
	private JRadioButton Edit_DesignationSenior;
	private JRadioButton Edit_StatusActive;
	
	private JRadioButton rdbtn_NrCount;
	private JRadioButton rdbtn_DrCount;
	private JRadioButton rdbtn_AllCount;
	private JButton btn_ClearFields;
	private JButton btn_ViewLog;
	private JButton btn_TestData;
	private JButton btn_TransferRecords;
	//public static String serverArena = ServerSpot.SERVER_MTL;
	public static String serverURL;
	//public static int port = ServerSpot.PORT_SERVER_MTL;
	static Vector<LogMetadata> recordVector = new Vector<LogMetadata>();
	static Vector<CreateDoctor_Metadata> testVector = new Vector<CreateDoctor_Metadata>();
	public static boolean operation_done = false;
	CreateDoctor_Metadata createDoctor;
	CreateNurse_Metadata createNurse;
	TransferRecord_Metadata transfer;
	Object editRecord;
	String RecordID, RecordType;
	String response;
	Object getRecord_obj;
	String ManagerID;
	String recordString;
	
	private String empCounts[];
	
	public ManagerClient(String ManagerID) {
		this.ManagerID = ManagerID;
		bindManager_Serv(this.ManagerID);
		initialize();
	}
	

	
	/**
	 * onSubmit : Invoked after Manager fills the form and select SUBMIT for a specific action
	 * Input : choice<Integer>
	 * Action : Based upon the choice respective thread is started from the ManagerUtility class
	 */
	public void onSubmit(int choice)
	{
		try
		{
			switch(choice)
			{
				case 0 : response = null;
						 ManagerUtility.createDRecord_connectionThread(serverURL +Constants.CREATEDOCTOR, createDoctor.toString(), ManagerID, choice);
	   					 while(ConnectionThread.flag)
						 {
							response = ConnectionThread.response;
						 }
	   					 System.out.println("RESPONSE recieved from the Server - "+ response);
	   					 
					break;
					
				case 1 : response = null;
						 ManagerUtility.createNRecord_connectionThread(serverURL + Constants.CREATENURSE, createNurse.toString(), ManagerID, choice);	
						 while(ConnectionThread.flag)
						 {
							 response = ConnectionThread.response;
						 }
	   					 System.out.println("RESPONSE recieved from the Server - "+ response);
					break;
					
				case 2 : getRecord_obj = null;
				
						 ManagerUtility.getRecordbyID_connectionThread(serverURL + Constants.GETDOCTOR +RecordID, RecordID, 3);	
						 while(ConnectionThread.flag)
						 {
						 	getRecord_obj = ConnectionThread.getRecord_obj;
						 }
				break;
				
						 
				case 3 : getRecord_obj = null;
						 ManagerUtility.getRecordbyID_connectionThread(serverURL + Constants.GETNURSE +RecordID, RecordID, choice);		
						 while(ConnectionThread.flag)
						 {
						 	getRecord_obj = ConnectionThread.getRecord_obj;
						 }
					break;	
					
				case 4 : response = null;
						 ManagerUtility.createDRecord_connectionThread(serverURL +Constants.EDITDR, createDoctor.toString(),ManagerID, choice);
							 while(ConnectionThread.flag)
						 {
							response = ConnectionThread.response;
						 }
							 System.out.println("RESPONSE recieved from the Server - "+ response);
							 
				case 5 : response = null;
				 ManagerUtility.createDRecord_connectionThread(serverURL +Constants.EDITNR, createNurse.toString(), ManagerID,4 );
					 while(ConnectionThread.flag)
				 {
					response = ConnectionThread.response;
				 }
					 System.out.println("RESPONSE recieved from the Server - "+ response);
					 			 
							 
				case 6 : response = null;
				
					 ManagerUtility.getCount_connectionThread(serverURL +Constants.GETCOUNT ,RecordType ,ManagerID, 2);	
					 while(ConnectionThread.flag)
					 {
						 response = ConnectionThread.response;
					 }
					 empCounts = response.split("#");
						 System.out.println("RESPONSE recieved from the Server - "+ empCounts[0] + " " + empCounts[1] + " " + empCounts[2]);
				break;
				
				case 7 : response = null;
				
						 ManagerUtility.createDRecord_connectionThread(serverURL +Constants.TRANSFER ,transfer.toString() ,ManagerID, 5);	
						 while(ConnectionThread.flag)
						 {
							 response = ConnectionThread.response;
						 }
						
						 System.out.println("RESPONSE recieved from the Server - "+ response);
					break;
			}
			
		}
		catch(Exception ex)
		{
			System.out.println("ERROR : " + ex.toString());
		}
	}
	
	/**
	 *  Binds the server based upon the location of the Manager.
	 * @param managerID
	 */
	public void bindManager_Serv(String managerID)
	{
		String temp = managerID.substring(0,3);
		
		if(temp.equals("MTL"))
			serverURL = Constants.MTLURL;//serverArena = ServerSpot.SERVER_MTL;
		else if(temp.equals("LVL"))
			serverURL = Constants.LVLURL;//serverArena = ServerSpot.SERVER_LVL;
		else 
			serverURL = Constants.DDOURL;//serverArena = ServerSpot.SERVER_DDO;
	}
	
	/**
	 * Initialize the contents of the frame(DoctorPanel, NursePanel, GetRecordCount, EditRecordPanel).
	 * Also binds the action events on the Submit buttons.
	 * Performs Validations on the form.
	 *
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle(" STAFF MANAGEMENT SYSTEM");
		frame.setBounds(100, 100, 1190, 827);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//////// Main Panel ///////
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btn_CreateDoctor = new JButton("Create Doctor");
		btn_CreateDoctor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clearFields();
				JPanel_Doctor.setVisible(true);
				layeredPane.setPosition(JPanel_Doctor, 0);
				JPanel_Nurse.setVisible(false);
				JPanel_RecordCount.setVisible(false);
				JPanel_EditRecord.setVisible(false);
				JPanel_Transfer.setVisible(false);
				
			}
		});
		btn_CreateDoctor.setBounds(12, 191, 145, 25);
		panel.add(btn_CreateDoctor);
		
		JButton btn_CreateNurse = new JButton("Create Nurse");
		btn_CreateNurse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clearFields();
				JPanel_Doctor.setVisible(false);
				JPanel_Nurse.setVisible(true);
				layeredPane.setPosition(JPanel_Nurse, 0);
				JPanel_RecordCount.setVisible(false);
				JPanel_EditRecord.setVisible(false);
				JPanel_Transfer.setVisible(false);
			}
		});
		btn_CreateNurse.setBounds(12, 229, 145, 25);
		panel.add(btn_CreateNurse);
		
		JButton btn_ShowRecordCount = new JButton("Get Count");
		btn_ShowRecordCount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clearFields();
				JPanel_Doctor.setVisible(false);
				JPanel_Nurse.setVisible(false);
				JPanel_RecordCount.setVisible(true);
				layeredPane.moveToFront(JPanel_RecordCount);
				JPanel_EditRecord.setVisible(false);
				JPanel_Transfer.setVisible(false);
			}
		});
		btn_ShowRecordCount.setBounds(12, 267, 145, 25);
		panel.add(btn_ShowRecordCount);
		
		JButton btn_EditRecord = new JButton("Edit Record");
		btn_EditRecord.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			Edit_FirstName.setEnabled(false);
				Edit_LastName.setEnabled(false);
				Edit_Address.setEnabled(false);
				Edit_Phone.setEnabled(false);
				JPanel_Doctor.setVisible(false);
				JPanel_Nurse.setVisible(false);
				JPanel_RecordCount.setVisible(false);
				JPanel_EditRecord.setVisible(true);
				JPanel_Transfer.setVisible(false);
			//	layeredPane.moveToFront();
			}
		});
		btn_EditRecord.setBounds(12, 305, 145, 25);
		panel.add(btn_EditRecord);
		
		btn_TransferRecords = new JButton("TransFer Record");
		btn_TransferRecords.setBounds(12, 450, 145, 25);
		panel.add(btn_TransferRecords);
		btn_TransferRecords.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				
				clearFields();
				JPanel_Doctor.setVisible(false);
				JPanel_Nurse.setVisible(false);
				JPanel_RecordCount.setVisible(false);
				JPanel_EditRecord.setVisible(false);
				JPanel_Transfer.setVisible(true);	
				layeredPane.moveToFront(JPanel_Transfer);	
			}
		});
		


		layeredPane = new JLayeredPane();
		layeredPane.setBounds(866, 98, 1, 1);
		panel.add(layeredPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(881, 182, -31, -59);
		panel.add(desktopPane);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBounds(203, 116, 483, 578);
		panel.add(layeredPane_1);
		
		
		/////// Transfer Panel //////
		JPanel_Transfer = new JPanel();
		JPanel_Transfer.setBounds(0, 70, 420, 302);
		layeredPane_1.add(JPanel_Transfer);
		JPanel_Transfer.setVisible(false);
		JPanel_Transfer.setLayout(null);
		
		JLabel lbl_recordID = new JLabel("Record ID");
		lbl_recordID.setBounds(28, 8, 80, 16);
		JPanel_Transfer.add(lbl_recordID);
		
		JLabel lbl_remoteServerName = new JLabel("Remote Server ID");
		lbl_remoteServerName.setBounds(28, 48, 110, 16);
		JPanel_Transfer.add(lbl_remoteServerName);
		
		tf_Remote_Record_ID = new JTextField();
		tf_Remote_Record_ID.setBounds(139, 5, 116, 22);
		JPanel_Transfer.add(tf_Remote_Record_ID);
		tf_Remote_Record_ID.setColumns(10);
		
		
		tf_Remote_Server_ID = new JTextField();
		tf_Remote_Server_ID.setBounds(139,45 , 116, 22);
		JPanel_Transfer.add(tf_Remote_Server_ID);
		tf_Remote_Server_ID.setColumns(10);
		
		
		
		JButton btn_Submit_Transfer = new JButton("Transfer");
		btn_Submit_Transfer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				String recordID = tf_Remote_Record_ID.getText();
				String remoteServer  = tf_Remote_Server_ID.getText();
				transfer = new TransferRecord_Metadata(ManagerID, recordID, remoteServer);
				
				onSubmit(7);
				
				if(response.compareTo("-1")!=0)
				{
					JOptionPane.showMessageDialog(frame,"Record Tranferred !", "Transfer Record",JOptionPane.PLAIN_MESSAGE);
					clearFields();
				}
				
				else
					JOptionPane.showMessageDialog(frame,"Woops Something Went Wrong", "Transfer Record",JOptionPane.ERROR_MESSAGE);

				
			}});
		
		btn_Submit_Transfer.setBounds(139, 100, 116, 25);
		JPanel_Transfer.add(btn_Submit_Transfer);
		
		//////// Doctor Panel ///////
		JPanel_Doctor = new JPanel();
		JPanel_Doctor.setBounds(0, 70, 420, 302);
		layeredPane_1.add(JPanel_Doctor);
		JPanel_Doctor.setVisible(false);
		JPanel_Doctor.setLayout(null);
		JLabel lbl_Dr_FirstName = new JLabel("First Name");
		lbl_Dr_FirstName.setBounds(28, 8, 80, 16);
		JPanel_Doctor.add(lbl_Dr_FirstName);
		
		tf_Dr_FirstName = new JTextField();
		tf_Dr_FirstName.setBounds(139, 5, 116, 22);
		JPanel_Doctor.add(tf_Dr_FirstName);
		tf_Dr_FirstName.setColumns(10);
		
		JLabel lbl_Dr_LastName = new JLabel("Last Name");
		lbl_Dr_LastName.setBounds(28, 48, 80, 16);
		JPanel_Doctor.add(lbl_Dr_LastName);
		
		JLabel lbl_Dr_Address = new JLabel("Address");
		lbl_Dr_Address.setBounds(28, 90, 80, 16);
		JPanel_Doctor.add(lbl_Dr_Address);
		
		tf_Dr_LastName = new JTextField();
		tf_Dr_LastName.setBounds(139, 45, 116, 22);
		JPanel_Doctor.add(tf_Dr_LastName);
		tf_Dr_LastName.setColumns(10);
		
		tf_Dr_Address = new JTextField();
		tf_Dr_Address.setBounds(139, 87, 116, 22);
		JPanel_Doctor.add(tf_Dr_Address);
		tf_Dr_Address.setColumns(10);
		
		JLabel lbl_Dr_Phone = new JLabel("Phone");
		lbl_Dr_Phone.setBounds(28, 139, 80, 16);
		JPanel_Doctor.add(lbl_Dr_Phone);
		
		tf_Dr_Phone = new JTextField();
		tf_Dr_Phone.setBounds(139, 136, 116, 22);
		JPanel_Doctor.add(tf_Dr_Phone);
		tf_Dr_Phone.setColumns(10);
		
		JLabel lbl_Dr_pecialization = new JLabel("Specialization");
		lbl_Dr_pecialization.setBounds(28, 189, 88, 16);
		JPanel_Doctor.add(lbl_Dr_pecialization);
		
		JLabel lbl_Dr_Location = new JLabel("Location");
		lbl_Dr_Location.setBounds(28, 236, 78, 16);
		JPanel_Doctor.add(lbl_Dr_Location);
		
		JButton btn_Submit_CreateDoctor = new JButton("Create  Doctor");
		btn_Submit_CreateDoctor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				String FirstName = tf_Dr_FirstName.getText();
				String LastName  = tf_Dr_LastName.getText();
				String Address = tf_Dr_Address.getText();
				String Phone = tf_Dr_Phone.getText();
				String Specialization =  (rdbtn_Dr_Sugeon.isSelected()) ? "SURGEON" : "ORTHOPAEDIC" ;
				String Location = (rdbtn_Dollard.isSelected()) ? "DDO" :
					                   ((rdbtn_Montreal.isSelected() ?  "MTL" : "LVL"));
				
				
				CheckUps frontEndCheck = new CheckUps();
				String buttonOutput = frontEndCheck.isValid(FirstName, LastName, Phone);
				
				if(buttonOutput.length()>1)
					JOptionPane.showMessageDialog(frame,buttonOutput, "Add New Doctor",JOptionPane.ERROR_MESSAGE);
				else
				{
					createDoctor = new CreateDoctor_Metadata(Address, Phone, Specialization, Location, ManagerID, FirstName, LastName);
					
					onSubmit(0);
					
					
					System.out.println(createDoctor.toString());
					if(response.compareTo("-1")!=0)
					{
						JOptionPane.showMessageDialog(frame,"Doctor Added!", "Add New Nurse",JOptionPane.PLAIN_MESSAGE);
						clearFields();
					}
					
					else
						JOptionPane.showMessageDialog(frame,"Woops Something Went Wrong", "Add New Doctor",JOptionPane.ERROR_MESSAGE);
						
				}
				
				
						   
						    
				
			}
		});
		btn_Submit_CreateDoctor.setBounds(139, 264, 116, 25);
		JPanel_Doctor.add(btn_Submit_CreateDoctor);
		rdbtn_Dr_Sugeon = new JRadioButton("Surgeon");
		rdbtn_Dr_Sugeon.setSelected(true);
		rdbtn_Dr_Sugeon.setBounds(139, 185, 85, 25);
		
		ButtonGroup btn_Group_Specialization = new ButtonGroup();
		btn_Group_Specialization.add(rdbtn_Dr_Sugeon);
		JPanel_Doctor.add(rdbtn_Dr_Sugeon);
		
		rdbtn_Dr_Ortho = new JRadioButton("Orthopedic");
		rdbtn_Dr_Ortho.setBounds(229, 185, 120, 25);
		btn_Group_Specialization.add(rdbtn_Dr_Ortho);
		JPanel_Doctor.add(rdbtn_Dr_Ortho);
		
		ButtonGroup btn_Group_Location = new ButtonGroup();
		rdbtn_Laval = new JRadioButton("Laval");
		rdbtn_Laval.setSelected(true);
		rdbtn_Laval.setBounds(139, 232, 85, 25);
		btn_Group_Location.add(rdbtn_Laval);
		JPanel_Doctor.add(rdbtn_Laval);
		
		rdbtn_Montreal = new JRadioButton("Montreal");
		rdbtn_Montreal.setBounds(229, 232, 95, 25);
		btn_Group_Location.add(rdbtn_Montreal);
		JPanel_Doctor.add(rdbtn_Montreal);
		
		rdbtn_Dollard = new JRadioButton("Dollard");
		rdbtn_Dollard.setBounds(319, 232, 85, 25);
		btn_Group_Location.add(rdbtn_Dollard);
		JPanel_Doctor.add(rdbtn_Dollard);
		
		//////// Record Count Panel ///////
		
		JPanel_RecordCount = new JPanel();
		JPanel_RecordCount.setBounds(0, 70, 290, 175);
		layeredPane_1.add(JPanel_RecordCount);
		JPanel_RecordCount.setVisible(false);
		JPanel_RecordCount.setLayout(null);
		JLabel lblRecordTypr = new JLabel("Record Type");
		lblRecordTypr.setBounds(12, 13, 94, 16);
		JPanel_RecordCount.add(lblRecordTypr);
		rdbtn_DrCount = new JRadioButton("Doctor");
		rdbtn_DrCount.setBounds(8, 45, 127, 25);
		JPanel_RecordCount.add(rdbtn_DrCount);
		
		rdbtn_NrCount = new JRadioButton("Nurse");
		rdbtn_NrCount.setBounds(8, 75, 127, 25);
		JPanel_RecordCount.add(rdbtn_NrCount);
		
		rdbtn_AllCount = new JRadioButton("All");
		rdbtn_AllCount.setBounds(8, 105, 127, 25);
		JPanel_RecordCount.add(rdbtn_AllCount);
		
		JButton btn_ShowCount = new JButton("Show ");
		btn_ShowCount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				RecordType = (rdbtn_AllCount.isSelected()) ? "ALL" : (rdbtn_NrCount.isSelected() ? "NR" : "DR");
				onSubmit(6);
				
				JOptionPane.showMessageDialog(frame, empCounts[0] +"\n"+ empCounts[1] +"\n"+ empCounts[2],"Employee Count",JOptionPane.INFORMATION_MESSAGE );
			}
		});
		btn_ShowCount.setBounds(28, 139, 145, 25);
		JPanel_RecordCount.add(btn_ShowCount);
		
		
		//////// Nurse Panel ///////
		
		JPanel_Nurse = new JPanel();
		JPanel_Nurse.setBounds(0, 70, 372, 302);
		layeredPane_1.add(JPanel_Nurse);
		JPanel_Nurse.setVisible(false);
		JPanel_Nurse.setLayout(null);
		JLabel label_2 = new JLabel("First Name");
		label_2.setBounds(28, 8, 80, 16);
		JPanel_Nurse.add(label_2);
		
		tf_Nr_FirstName = new JTextField();
		tf_Nr_FirstName.setColumns(10);
		tf_Nr_FirstName.setBounds(139, 5, 116, 22);
		JPanel_Nurse.add(tf_Nr_FirstName);
		
		JLabel label_3 = new JLabel("Last Name");
		label_3.setBounds(28, 48, 80, 16);
		JPanel_Nurse.add(label_3);
		
		JLabel lblDesignation_1 = new JLabel("Designation");
		lblDesignation_1.setBounds(28, 90, 80, 16);
		JPanel_Nurse.add(lblDesignation_1);
		
		tf_Nr_LastName = new JTextField();
		tf_Nr_LastName.setColumns(10);
		tf_Nr_LastName.setBounds(139, 45, 116, 22);
		JPanel_Nurse.add(tf_Nr_LastName);
		
		JLabel lblStatus_1 = new JLabel("Status");
		lblStatus_1.setBounds(28, 139, 56, 16);
		JPanel_Nurse.add(lblStatus_1);
		
		JLabel lblStatusDate_1 = new JLabel("Status Date");
		lblStatusDate_1.setBounds(28, 189, 88, 16);
		JPanel_Nurse.add(lblStatusDate_1);
		
		JButton btn_Submit_CreateNurse = new JButton("Create ");
		btn_Submit_CreateNurse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				String FirstName = tf_Nr_FirstName.getText();
				String LastName = tf_Nr_LastName.getText();
				String Status = (rdbtn_NR_statusActive.isSelected()) ? "ACTIVE" : "TERMINATED";
				String Designation =  (rdbtn_NR_DesignationJunior.isSelected()) ? "JUNIOR" : "SENIOR";
			
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				String  Date = dateFormat.format(date_NR_statusDate.getDate());
				CheckUps frontEndCheck = new CheckUps();
				String buttonOutput = frontEndCheck.isValid(FirstName, LastName, "111");
				if(buttonOutput.length()>1)
					JOptionPane.showMessageDialog(frame,buttonOutput, "Add New Nurse",JOptionPane.ERROR_MESSAGE);
				else
				{
					createNurse = new CreateNurse_Metadata(FirstName, FirstName, Designation, Status, Date, ManagerID);
					
					
					
					onSubmit(1);
					if(response.compareTo("-1")!=0)
					{
						JOptionPane.showMessageDialog(frame,"Nurse Added!", "Add New Nurse",JOptionPane.PLAIN_MESSAGE);
						clearFields();
					}
					else
						JOptionPane.showMessageDialog(frame,"Woops Something Went Wrong", "Add New Nurse",JOptionPane.ERROR_MESSAGE);
						
				}
				
				
				
			}
		});
		btn_Submit_CreateNurse.setBounds(60, 264, 145, 25);
		JPanel_Nurse.add(btn_Submit_CreateNurse);
		rdbtn_NR_DesignationJunior = new JRadioButton("Junior");
		rdbtn_NR_DesignationJunior.setSelected(true);
		rdbtn_NR_DesignationJunior.setBounds(139, 86, 80, 25);
		JPanel_Nurse.add(rdbtn_NR_DesignationJunior);
		
		rdbtn_NR_DesignationSenior = new JRadioButton("Senior");
		rdbtn_NR_DesignationSenior.setBounds(208, 86, 80, 25);
		JPanel_Nurse.add(rdbtn_NR_DesignationSenior);
		rdbtn_NR_statusActive = new JRadioButton("Active");
		rdbtn_NR_statusActive.setSelected(true);
		rdbtn_NR_statusActive.setBounds(139, 135, 80, 25);
		JPanel_Nurse.add(rdbtn_NR_statusActive);
		
		rdbtn_NR_statusTerminated = new JRadioButton("Terminated");

		rdbtn_NR_statusTerminated.setBounds(208, 135, 120, 25);
		JPanel_Nurse.add(rdbtn_NR_statusTerminated);
		
		date_NR_statusDate = new JDateChooser();
		date_NR_statusDate.setVisible(true);
		date_NR_statusDate.setBounds(139, 183, 150, 22);
		JPanel_Nurse.add(date_NR_statusDate);
		
		JPanel_EditRecord = new JPanel();
		JPanel_EditRecord.setVisible(false);
		JPanel_EditRecord.setBounds(0, 0, 420, 541);
		layeredPane_1.add(JPanel_EditRecord);
		JPanel_EditRecord.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Record ID");
		lblNewLabel.setBounds(28, 8, 62, 16);
		JPanel_EditRecord.add(lblNewLabel);
		
		JButton btn_Edit_ShowRecord = new JButton("Show Record");
		btn_Edit_ShowRecord.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				RecordID = Edit_RecordID.getText();
				
				
				
				String TwoBitsID = RecordID.charAt(0)+""+RecordID.charAt(1)+"";
				if(TwoBitsID.toUpperCase().compareTo("DR")==0)
				{
					
					onSubmit(2);
					GetDoctor_MetaData doctor =(GetDoctor_MetaData) getRecord_obj;
					
					if(doctor != null)
					{
						Edit_FirstName.setEnabled(true);
						Edit_LastName.setEnabled(true);
						
						Edit_Address.setEnabled(true);
						Edit_Phone.setEnabled(true);
						Edit_SpecializationOrthopedic.setEnabled(true);
						Edit_SpecializationSurgeon.setEnabled(true);
						Edit_LocationDollard.setEnabled(true);
						Edit_LocationLaval.setEnabled(true);
						Edit_LocationMontreal.setEnabled(true);
						
						Edit_DesignationJunior.setEnabled(false);
						Edit_DesignationSenior.setEnabled(false);
						Edit_StatusActive.setEnabled(false);
						Edit_StatusTerminated.setEnabled(false);
						Edit_StatusDate.setEnabled(false);
						Edit_StatusDate.setVisible(false);
						
						Edit_FirstName.setText(doctor.getFirstName());
						Edit_LastName.setText(doctor.getLastName());
						Edit_Address.setText(doctor.getAddress());
						Edit_Phone.setText(doctor.getPhone());
						
						if(doctor.getSpecialization().equals("ORTHOPAEDIC"))
						{
								Edit_SpecializationOrthopedic.setSelected(true);
								Edit_SpecializationSurgeon.setSelected(false);
						}
						else
						{
							Edit_SpecializationOrthopedic.setSelected(false);
							Edit_SpecializationSurgeon.setSelected(true);
						}
						
						if(doctor.getLocation().equals("LVL"))
						{
								
							Edit_LocationLaval.setSelected(true);	
						}
						else if(doctor.getLocation().equals("DDO"))
						{
							Edit_LocationDollard.setSelected(true);
						}
						else
							Edit_LocationMontreal.setSelected(true);
					}
					else
					{
						JOptionPane.showMessageDialog(frame,"Doctor Record does not exist !", "Get Count ",JOptionPane.ERROR_MESSAGE);
					}
				}
				else if(TwoBitsID.toUpperCase().compareTo("NR")==0)
				{
					onSubmit(3);
					GetNurse_MetaData nurse =(GetNurse_MetaData) getRecord_obj;
					if(nurse != null)
					{
						Edit_FirstName.setEnabled(true);
						Edit_LastName.setEnabled(true);
						
						Edit_Address.setEnabled(false);
						Edit_Phone.setEnabled(false);
						Edit_SpecializationOrthopedic.setEnabled(false);
						Edit_SpecializationSurgeon.setEnabled(false);
						Edit_LocationDollard.setEnabled(false);
						Edit_LocationLaval.setEnabled(false);
						Edit_LocationMontreal.setEnabled(false);
						
						Edit_DesignationJunior.setEnabled(true);
						Edit_DesignationSenior.setEnabled(true);
						Edit_StatusActive.setEnabled(true);
						Edit_StatusTerminated.setEnabled(true);
						Edit_StatusDate.setEnabled(true);
						Edit_StatusDate.setVisible(true);
						
						Edit_FirstName.setText(nurse.getFirstName());
						Edit_LastName.setText(nurse.getLastName());
						
						
						if(nurse.getDesignation().equals("JUNIOR"))
							Edit_DesignationJunior.setSelected(true);
						else
						{
							Edit_DesignationSenior.setSelected(false);
						}
						
						if(nurse.getStatus().equals("TERMINATED"))
						{
							Edit_StatusTerminated.setSelected(true);
							Edit_StatusDate.setToolTipText(nurse.getStatusDate());
						}
						else
						{
							Edit_StatusActive.setSelected(true);
						}
						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						try {
							Edit_StatusDate.setDate(dateFormat.parse(nurse.getStatusDate()));
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else
					{
						JOptionPane.showMessageDialog(frame,"Nurse Record does not exist !", "Get Count ",JOptionPane.ERROR_MESSAGE);
					}
					
				}
				else
					JOptionPane.showMessageDialog(frame,"Invalid ID, ID should start with NR or DR", "Add New Doctor",JOptionPane.ERROR_MESSAGE);
			}
		});
		
		
		btn_Edit_ShowRecord.setBounds(259, 5, 116, 22);
		JPanel_EditRecord.add(btn_Edit_ShowRecord);
		
		JLabel label = new JLabel("First Name");
		label.setBounds(28, 45, 80, 16);
		JPanel_EditRecord.add(label);
		
		Edit_FirstName = new JTextField();
		Edit_FirstName.setColumns(10);
		Edit_FirstName.setBounds(139, 40, 116, 22);
		JPanel_EditRecord.add(Edit_FirstName);
		
		JLabel label_1 = new JLabel("Last Name");
		label_1.setBounds(28, 86, 80, 16);
		JPanel_EditRecord.add(label_1);
		
		Edit_LastName = new JTextField();
		Edit_LastName.setColumns(10);
		Edit_LastName.setBounds(139, 83, 116, 22);
		JPanel_EditRecord.add(Edit_LastName);
		
		JLabel label_4 = new JLabel("Address");
		label_4.setBounds(28, 129, 80, 16);
		JPanel_EditRecord.add(label_4);
		
		Edit_Address = new JTextField();
		
		Edit_Address.setColumns(10);
		Edit_Address.setBounds(139, 126, 116, 22);
		JPanel_EditRecord.add(Edit_Address);
		
		JLabel label_5 = new JLabel("Phone");
		label_5.setBounds(28, 172, 80, 16);
		JPanel_EditRecord.add(label_5);
		
		Edit_Phone = new JTextField();
		Edit_Phone.setColumns(10);
		Edit_Phone.setBounds(139, 169, 116, 22);
		JPanel_EditRecord.add(Edit_Phone);
		
		JLabel label_6 = new JLabel("Specialization");
		label_6.setBounds(28, 217, 100, 16);
		JPanel_EditRecord.add(label_6);
		
		ButtonGroup EditGroupSpecialization = new ButtonGroup();
		Edit_SpecializationSurgeon = new JRadioButton("Surgeon");
		Edit_SpecializationSurgeon.setEnabled(false);
		Edit_SpecializationSurgeon.setSelected(true);
		Edit_SpecializationSurgeon.setBounds(139, 213, 90, 25);
		EditGroupSpecialization.add(Edit_SpecializationSurgeon);
		JPanel_EditRecord.add(Edit_SpecializationSurgeon);
		
		Edit_SpecializationOrthopedic = new JRadioButton("Orthopedic");
		Edit_SpecializationOrthopedic.setEnabled(false);
		Edit_SpecializationOrthopedic.setBounds(237, 213, 120, 25);
		EditGroupSpecialization.add(Edit_SpecializationOrthopedic);
		JPanel_EditRecord.add(Edit_SpecializationOrthopedic);
		
		JLabel label_7 = new JLabel("Location");
		label_7.setBounds(28, 255, 85, 16);
		JPanel_EditRecord.add(label_7);
		
		ButtonGroup EditGroupLocation = new ButtonGroup();
		Edit_LocationLaval = new JRadioButton("Laval");
		Edit_LocationLaval.setEnabled(false);
		Edit_LocationLaval.setSelected(true);
		Edit_LocationLaval.setBounds(139, 251, 80, 25);
		EditGroupLocation.add(Edit_LocationLaval);
		JPanel_EditRecord.add(Edit_LocationLaval);
		
		Edit_LocationMontreal = new JRadioButton("Montreal");
		Edit_LocationMontreal.setEnabled(false);
		Edit_LocationMontreal.setBounds(237, 251, 95, 25);
		EditGroupLocation.add(Edit_LocationMontreal);
		JPanel_EditRecord.add(Edit_LocationMontreal);
		
		Edit_LocationDollard = new JRadioButton("Dollard");
		Edit_LocationDollard.setEnabled(false);
		Edit_LocationDollard.setBounds(319, 251, 80, 25);
		EditGroupLocation.add(Edit_LocationDollard);
		JPanel_EditRecord.add(Edit_LocationDollard);
		
		JLabel label_8 = new JLabel("Designation");
		label_8.setBounds(28, 304, 100, 16);
		JPanel_EditRecord.add(label_8);
		
		Edit_DesignationJunior = new JRadioButton("Junior");
		Edit_DesignationJunior.setEnabled(false);
		Edit_DesignationJunior.setSelected(true);
		Edit_DesignationJunior.setBounds(139, 300, 80, 25);
		JPanel_EditRecord.add(Edit_DesignationJunior);
		
		
		ButtonGroup EditGroupDesignation = new ButtonGroup();
		Edit_DesignationSenior = new JRadioButton("Senior");
		Edit_DesignationSenior.setEnabled(false);
		Edit_DesignationSenior.setBounds(237, 300, 80, 25);
		EditGroupDesignation.add(Edit_DesignationSenior);
		JPanel_EditRecord.add(Edit_DesignationSenior);
		EditGroupDesignation.add(Edit_DesignationJunior);
		JLabel label_9 = new JLabel("Status");
		label_9.setBounds(28, 353, 80, 16);
		JPanel_EditRecord.add(label_9);
		
		ButtonGroup EditGroupStatus = new ButtonGroup(); 
		Edit_StatusActive = new JRadioButton("Active");
		Edit_StatusActive.setEnabled(false);
		Edit_StatusActive.setSelected(true);
		EditGroupStatus.add(Edit_StatusActive);
		Edit_StatusActive.setBounds(139, 349, 100, 25);
		JPanel_EditRecord.add(Edit_StatusActive);
		
		Edit_StatusTerminated = new JRadioButton("Terminated");
	
		
		Edit_StatusTerminated.setEnabled(false);
		EditGroupStatus.add(Edit_StatusTerminated);
		
		Edit_StatusTerminated.setBounds(237, 349, 125, 25);
		JPanel_EditRecord.add(Edit_StatusTerminated);
		
		JLabel label_10 = new JLabel("Status Date");
		label_10.setBounds(28, 402, 88, 16);
		JPanel_EditRecord.add(label_10);
		
		Edit_StatusDate = new JDateChooser();
		Edit_StatusDate.setVisible(true);
		Edit_StatusDate.setBounds(139, 396, 150, 22);
		JPanel_EditRecord.add(Edit_StatusDate);
		
		Edit_StatusTerminated.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				if(Edit_StatusTerminated.isSelected())
					Edit_StatusDate.setVisible(true);
				
				if(!Edit_StatusTerminated.isSelected())
					Edit_StatusDate.setVisible(true);	
				
			}
		});
		JButton btn_UpdateRecord = new JButton("Update Record");
		btn_UpdateRecord.setBounds(139, 490, 116, 25);
		
		JPanel_EditRecord.add(btn_UpdateRecord);
		btn_UpdateRecord.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0)
			{
				
				RecordID = Edit_RecordID.getText();
				String TwoBitsID = RecordID.charAt(0)+""+RecordID.charAt(1)+"";
				if(TwoBitsID.toUpperCase().compareTo("DR")==0)
				{
					String FirstName = Edit_FirstName.getText();
					String LastName  = Edit_LastName.getText();
					String Address = Edit_Address.getText();
					String Phone = Edit_Phone.getText();
					String Specialization =  (Edit_SpecializationSurgeon.isSelected()) ? "SURGEON" : "ORTHOPAEDIC" ;
					String Location = (Edit_LocationDollard.isSelected()) ? "Location.DDO" :
						                   ((Edit_LocationMontreal.isSelected() ?  "MTL" : "LVL"));
					
					
					CheckUps frontEndCheck = new CheckUps();
					String buttonOutput = frontEndCheck.isValid(FirstName, LastName, Phone);
					
					if(buttonOutput.length()>1)
						JOptionPane.showMessageDialog(frame,buttonOutput, "Add New Doctor",JOptionPane.ERROR_MESSAGE);
					else
					{
						createDoctor = new CreateDoctor_Metadata(Address, Phone, Specialization, Location, RecordID, FirstName, LastName);
						onSubmit(4);
						System.out.println(createDoctor.toString());
						if(response.compareTo("-1")!=0)
						{
							JOptionPane.showMessageDialog(frame,"Doctor Record Edited!", "EditDoctor",JOptionPane.PLAIN_MESSAGE);
							clearFields();
						}
						else
							JOptionPane.showMessageDialog(frame,"Woops something went wrong", " Edit Doctor",JOptionPane.ERROR_MESSAGE);
					}
				}
				
				else if(TwoBitsID.toUpperCase().compareTo("NR")==0)
				{
					
					
					String FirstName = Edit_FirstName.getText();
					String LastName = Edit_LastName.getText();
					String Status = (Edit_StatusActive.isSelected()) ? "ACTIVE" : "TERMINATED";
					String Designation =  (Edit_DesignationJunior.isSelected()) ? "JUNIOR" : "SENIOR";
					 
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					String  Date = dateFormat.format(Edit_StatusDate.getDate());
					CheckUps frontEndCheck = new CheckUps();
					String buttonOutput = frontEndCheck.isValid(FirstName, LastName, "111");
					if(buttonOutput.length()>1)
						JOptionPane.showMessageDialog(frame,buttonOutput, " Edit Nurse",JOptionPane.ERROR_MESSAGE);
					else
					{
						createNurse = new  CreateNurse_Metadata(FirstName, LastName, Designation, Status, Date, RecordID);
						onSubmit(5);
						System.out.println(createNurse.toString());
						if(response.compareTo("-1")!=0)
						{
							JOptionPane.showMessageDialog(frame,"Nurse Edited!", "Edit Nurse",JOptionPane.PLAIN_MESSAGE);
							clearFields();
						}else
							JOptionPane.showMessageDialog(frame,"Woops something went wrong", " Edit Nurse",JOptionPane.ERROR_MESSAGE);
					}
				}	
				else
				{
					JOptionPane.showMessageDialog(frame,"ID should start with either NR or DR , if\n this doesnt work ask Karan or Yeghia ji", " Wrong ID",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		
		
		Edit_RecordID = new JTextField();
		Edit_RecordID.setBounds(139, 5, 116, 22);
		JPanel_EditRecord.add(Edit_RecordID);
		Edit_RecordID.setColumns(10);
		
	
		
		ButtonGroup  grouprdbtn_EmployeeCount = new ButtonGroup();
		ButtonGroup  grouprdbtn_NR_Designation = new ButtonGroup();
		ButtonGroup  grouprdbtn_NR_Status = new ButtonGroup();
		
		
		grouprdbtn_EmployeeCount.add(rdbtn_DrCount);
		grouprdbtn_EmployeeCount.add(rdbtn_NrCount);
		grouprdbtn_EmployeeCount.add(rdbtn_AllCount);
		grouprdbtn_NR_Designation.add(rdbtn_NR_DesignationJunior);
		grouprdbtn_NR_Designation.add(rdbtn_NR_DesignationSenior);
		grouprdbtn_NR_Status.add(rdbtn_NR_statusActive);
		grouprdbtn_NR_Status.add(rdbtn_NR_statusTerminated);
		
		btn_ViewLog = new JButton("View Log");
		btn_ViewLog.setBounds(12, 380, 145, 25);
		panel.add(btn_ViewLog);
		btn_ViewLog.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
			
				recordVector.clear();
				recordVector = Log.readLog(ManagerID);
				if(recordVector != null)
				{
				
					final JFrame LogFrame = new JFrame("Log");
					LogFrame.setVisible(true);
					
					JPanel JPanel_Grid = new JPanel();
					LogFrame.add(JPanel_Grid);
					
					model = new DefaultTableModel(0,0);	
					model.setColumnIdentifiers(header);
					table=new JTable(model);
					JPanel_Grid.add(table);
						/*{
							@Override 
							public boolean isCellEditable(int arg0, int arg1) 
							{ return false; }
						}; */
						
						
					JScrollPane	pane = new JScrollPane(table); 
					LogFrame.add(pane); 
					LogFrame.setVisible(true); 
					LogFrame.setSize(500,600); 
					LogFrame.setLayout(new FlowLayout()); 
					
					
					
					for(int i = 0; i < recordVector.size(); i++)
					{
						model.addRow(new Object[]
							{
								recordVector.get(i).getAction(),
								recordVector.get(i).getAction_requested(),
								recordVector.get(i).getResponse(),
								recordVector.get(i).getLog_date()
							}
						);
					}
					
					JButton btn_Close = new JButton("Close");
					btn_Close.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							LogFrame.dispose();
						}
	
						
					});
					LogFrame.add(btn_Close);
				}
			
				else
				{
					JOptionPane.showMessageDialog(null, "No Logs !", "Log", JOptionPane.INFORMATION_MESSAGE );
				}
			}
		});
		
		btn_TestData = new JButton("Test Data");
		btn_TestData.setBounds(12, 415, 145, 25);
		panel.add(btn_TestData);
		btn_TestData.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				
				test_func("Test");
				JOptionPane.showMessageDialog(null, "Data Imported, view Log for more information !", "Data Import", JOptionPane.INFORMATION_MESSAGE );			
			}
		});
		
		
		
		
		
		btn_ClearFields = new JButton("Clear Fields");
		btn_ClearFields.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clearFields();
			}

			
		});
		btn_ClearFields.setBounds(12, 343, 145, 25);
		panel.add(btn_ClearFields);
		

	}
	
	public void test_func(String filename)
	{
		testVector = Log.testData(filename);
	
		
		
		for(int i=0; i< testVector.size(); i++)
		{
			 response = null;
			 
			 ManagerUtility.createDRecord_connectionThread(serverURL +Constants.CREATEDOCTOR, testVector.get(i).toString(), ManagerID, 0);
				 while(ConnectionThread.flag)
			 {
				response = ConnectionThread.response;
			 }
			System.out.println("RESPONSE recieved from the Server - "+ response);
			
		}
		if (JOptionPane.showConfirmDialog(null, "Records Created !\n Transfer ?", "Test Data",
		        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			
			for(int i=0; i< 20; i++)
			{
				
				String recordID = "DR"+String.format("%05d",i+1);
				String remoteServer  = "SERVER_DDO";
				transfer = new TransferRecord_Metadata(ManagerID, recordID, remoteServer);
				
				response = null;
				 
				 ManagerUtility.createDRecord_connectionThread(serverURL +Constants.TRANSFER ,transfer.toString() ,ManagerID, 5);	
				 while(ConnectionThread.flag)
				 {
					 response = ConnectionThread.response;
				 }
				
				 System.out.println("RESPONSE recieved from the Server - "+ response);
				
			}
			
		} else {
			
		}
	}

	public  void  clearFields()
	{
		tf_Dr_FirstName.setText("");
		tf_Dr_LastName.setText("");
		tf_Dr_Address.setText("");
		tf_Dr_Phone.setText("");
		rdbtn_Dr_Sugeon.setSelected(true);
		//rdbtn_Dr_Ortho.setSelected(false);
		rdbtn_Laval.setSelected(true);
		//rdbtn_Montreal.setSelected(false);
		//rdbtn_Dollard.setSelected(false);
		
		tf_Nr_FirstName.setText("");
		tf_Nr_LastName.setText("");
		rdbtn_NR_DesignationJunior.setSelected(true);
		//rdbtn_NR_DesignationSenior.setSelected(false);
		rdbtn_NR_statusActive.setSelected(true);
		//rdbtn_NR_statusTerminated.setSelected(false);
		
		
		/*Edit_FirstName.setText("");
		Edit_LastName.setText("");
		Edit_Phone.setText("");
		Edit_Address.setText("");
		
	    Edit_StatusTerminated.setSelected(false);
	    Edit_RecordID.setText("");
	    Edit_SpecializationSurgeon.setSelected(false);
	    Edit_SpecializationOrthopedic.setSelected(false);
	    Edit_LocationLaval.setSelected(false);
	    Edit_LocationMontreal.setSelected(false);
	    Edit_LocationDollard.setSelected(false);
	    Edit_DesignationJunior.setSelected(false);
	    Edit_DesignationSenior.setSelected(false);
	    Edit_StatusActive.setSelected(false);*/
		
		Edit_FirstName.setEnabled(false);
		Edit_LastName.setEnabled(false);
		Edit_Address.setEnabled(false);
		Edit_Phone.setEnabled(false);
		Edit_SpecializationOrthopedic.setEnabled(false);
		Edit_SpecializationSurgeon.setEnabled(false);
		Edit_LocationDollard.setEnabled(false);
		Edit_LocationLaval.setEnabled(false);
		Edit_LocationMontreal.setEnabled(false);
		Edit_DesignationJunior.setEnabled(false);
		Edit_DesignationSenior.setEnabled(false);
		Edit_StatusActive.setEnabled(false);
		Edit_StatusTerminated.setEnabled(false);
		Edit_StatusDate.setEnabled(false);
		Edit_StatusDate.setVisible(false);
		
	}
}

	


