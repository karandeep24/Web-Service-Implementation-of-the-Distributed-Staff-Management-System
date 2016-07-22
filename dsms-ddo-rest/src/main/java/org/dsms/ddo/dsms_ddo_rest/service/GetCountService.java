package org.dsms.ddo.dsms_ddo_rest.service;

import org.dsms.ddo.dsms_ddo_rest.utility.ServerUtility;

public class GetCountService {

	ServerUtility utility = new ServerUtility();
	
	public String getCount_Service(String data)
	{
		String temp[] = data.split(":");
		
		return utility.getRecordCounts(temp[0], temp[1]);
	}
}
