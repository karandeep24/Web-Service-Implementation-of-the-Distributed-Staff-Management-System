package org.dsms.lvl.dsms_lvl_rest.service;

import org.dsms.lvl.dsms_lvl_rest.utility.ServerUtility;

import rest.metadata.CreateNR_Metadata;
import rest.metadata.CreateNurse_Metadata;

public class NurseService {

	public String addNurse_Service(CreateNR_Metadata nurse)
	{
		ServerUtility utility = new ServerUtility();
		
		return utility.createNRecord(nurse);
	}
	
}
