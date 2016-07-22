package org.dsms.ddo.dsms_ddo_rest.service;

import org.dsms.ddo.dsms_ddo_rest.utility.ServerUtility;

import rest.metadata.CreateDR_Metadata;

public class DoctorService {
	
	public String addDoctor_Service(CreateDR_Metadata doctor)
	{
		ServerUtility utility = new ServerUtility();
		return utility.createDRecord(doctor);
	}

}
