package org.dsms.ddo.dsms_ddo_rest.service;

import java.util.Vector;

import org.dsms.ddo.dsms_ddo_rest.utility.ServerUtility;

import rest.metadata.DoctorRecord;
import rest.metadata.GetDoctor_Metadata;

public class GetDoctorService {
	
	ServerUtility utility = new ServerUtility();
	
	public Vector<GetDoctor_Metadata> getDoctor_Service(String doctorID)
	{
		Vector<GetDoctor_Metadata> dVector = new Vector<GetDoctor_Metadata>();
		GetDoctor_Metadata dMetadata = new GetDoctor_Metadata();
		DoctorRecord dRecord = utility.getDRecordbyID(doctorID);
		
		dMetadata.setFirstName(dRecord.getFirstName());
		dMetadata.setLastName(dRecord.getLastName());
		dMetadata.setAddress(dRecord.getAddress());
		dMetadata.setPhone(dRecord.getPhone());
		dMetadata.setSpecialization(dRecord.getESpecialization().toString());
		dMetadata.setLocation(dRecord.getELocation().toString());
		
		
		dVector.add(dMetadata);
		
		return dVector;
	}

}
