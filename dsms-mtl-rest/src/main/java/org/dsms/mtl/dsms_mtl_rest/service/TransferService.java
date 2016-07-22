package org.dsms.mtl.dsms_mtl_rest.service;


import org.dsms.mtl.dsms_mtl_rest.utility.ServerUtility;

import rest.metadata.TransferRecord_Metadata;

public class TransferService {

	public String tranferDoctor_Service(TransferRecord_Metadata transfer)
	{
		ServerUtility utility = new ServerUtility();
		return utility.transferRecord(transfer);
	}
}
