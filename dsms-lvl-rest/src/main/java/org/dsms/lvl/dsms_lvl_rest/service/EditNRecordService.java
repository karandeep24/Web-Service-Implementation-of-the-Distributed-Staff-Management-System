package org.dsms.lvl.dsms_lvl_rest.service;


import org.dsms.lvl.dsms_lvl_rest.utility.ServerUtility;

import rest.metadata.CreateNurse_Metadata;
import rest.metadata.NurseRecord;

public class EditNRecordService {

	public String editNurse_Service(CreateNurse_Metadata nurse)
	{
		ServerUtility utility = new ServerUtility();
		
		String RecordID = nurse.getID();
		
		NurseRecord.Status Status = (nurse.getStatus().equals("ACTIVE")) ? NurseRecord.Status.ACTIVE : NurseRecord.Status.TERMINATED;
		NurseRecord.Designation Designation =  (nurse.getDesignation().equals("JUNIOR")) ? NurseRecord.Designation.JUNIOR : NurseRecord.Designation.SENIOR;
		
		
		NurseRecord NRecord = new NurseRecord(nurse.getFirstName(), nurse.getLastName(), 
				Designation, Status, nurse.getStatusDate());
		
		Object dRecord = (Object)NRecord;
		
		return utility.editRecord(RecordID, dRecord);
	}
	
}
