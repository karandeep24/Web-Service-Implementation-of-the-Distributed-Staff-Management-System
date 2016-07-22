package org.dsms.ddo.dsms_ddo_rest.service;


import org.dsms.ddo.dsms_ddo_rest.utility.ServerUtility;

import rest.metadata.CreateNR_Metadata;

public class NurseService {

	public String addNurse_Service(CreateNR_Metadata nurse)
	{
		ServerUtility utility = new ServerUtility();
		
		return utility.createNRecord(nurse);
	}
	
}
