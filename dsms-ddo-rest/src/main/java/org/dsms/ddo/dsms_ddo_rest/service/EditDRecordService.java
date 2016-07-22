package org.dsms.ddo.dsms_ddo_rest.service;



import org.dsms.ddo.dsms_ddo_rest.utility.ServerUtility;

import rest.metadata.CreateDoctor_Metadata;
import rest.metadata.DoctorRecord;

public class EditDRecordService {

	public String editDoctor_Service(CreateDoctor_Metadata doctor)
	{
		ServerUtility utility = new ServerUtility();
		
		String RecordID = doctor.getID();
		
		DoctorRecord.Specialization Specialization =  (doctor.getSpecialization().equals("SURGEON")) ? DoctorRecord.Specialization.SURGEON : DoctorRecord.Specialization.ORTHOPAEDIC ;
		DoctorRecord.Location Location = (doctor.getLocation().equals("DDO")) ? DoctorRecord.Location.DDO :
			                   ((doctor.getLocation().equals("MTL") ?  DoctorRecord.Location.MTL : DoctorRecord.Location.LVL));
		
		DoctorRecord DRecord = new DoctorRecord(doctor.getFirstName(), doctor.getLastName(), doctor.getAddress(),
				doctor.getPhone(), Specialization, Location);
		
		Object dRecord = (Object)DRecord;
		
		return utility.editRecord(RecordID, dRecord);
	}
	
}
