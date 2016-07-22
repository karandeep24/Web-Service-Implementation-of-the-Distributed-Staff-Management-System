package org.dsms.ddo.dsms_ddo_rest.service;

import java.util.Vector;

import org.dsms.ddo.dsms_ddo_rest.utility.ServerUtility;

import rest.metadata.GetNurse_Metadata;
import rest.metadata.NurseRecord;

public class GetNurseService {
	
ServerUtility utility = new ServerUtility();
	
	public Vector<GetNurse_Metadata> getNurse_Service(String nurseID)
	{
		Vector<GetNurse_Metadata> nVector = new Vector<GetNurse_Metadata>();
		GetNurse_Metadata nMetadata = new GetNurse_Metadata();
		NurseRecord nRecord = utility.getNRecordbyID(nurseID);
		
		nMetadata.setFirstName(nRecord.getFirstName());
		nMetadata.setLastName(nRecord.getLastName());
		nMetadata.setDesignation(nRecord.getEDesignation().toString());
		nMetadata.setStatus(nRecord.getEStatus().toString());
		nMetadata.setStatusDate(nRecord.getEStatusDate());
		
		
		nVector.add(nMetadata);
		
		return nVector;
	}

}
