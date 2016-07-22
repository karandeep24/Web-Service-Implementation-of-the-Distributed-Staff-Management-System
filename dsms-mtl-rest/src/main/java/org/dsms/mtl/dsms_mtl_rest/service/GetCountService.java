package org.dsms.mtl.dsms_mtl_rest.service;

import org.dsms.mtl.dsms_mtl_rest.utility.ServerUtility;

public class GetCountService {

	ServerUtility utility = new ServerUtility();
	
	public String getCount_Service(String data)
	{
		String temp[] = data.split(":");
		
		return utility.getRecordCounts(temp[0], temp[1]);
	}
}
