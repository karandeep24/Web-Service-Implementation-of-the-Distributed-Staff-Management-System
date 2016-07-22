package org.dsms.mtl.dsms_mtl_rest.service;

import org.dsms.mtl.dsms_mtl_rest.utility.ServerUtility;

import rest.metadata.CreateNR_Metadata;
import rest.metadata.CreateNurse_Metadata;

public class NurseService {

	public String addNurse_Service(CreateNR_Metadata nurse)
	{
		ServerUtility utility = new ServerUtility();
		
		return utility.createNRecord(nurse);
	}
	
}
