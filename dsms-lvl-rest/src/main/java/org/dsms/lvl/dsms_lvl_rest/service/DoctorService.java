package org.dsms.lvl.dsms_lvl_rest.service;

import org.dsms.lvl.dsms_lvl_rest.utility.ServerUtility;

import rest.metadata.CreateDR_Metadata;
import rest.metadata.CreateDoctor_Metadata;

public class DoctorService {
	
	public String addDoctor_Service(CreateDR_Metadata doctor)
	{
		ServerUtility utility = new ServerUtility();
		
		return utility.createDRecord(doctor);
	}

}
