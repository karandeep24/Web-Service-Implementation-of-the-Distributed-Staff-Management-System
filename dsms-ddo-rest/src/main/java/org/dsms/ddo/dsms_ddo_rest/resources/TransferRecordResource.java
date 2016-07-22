package org.dsms.ddo.dsms_ddo_rest.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.dsms.ddo.dsms_ddo_rest.service.TransferService;
import rest.metadata.TransferRecord_Metadata;

@Path("transfer")
public class TransferRecordResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String transferResource(TransferRecord_Metadata transfer)
	{
		TransferService utility = new TransferService();
		
		return utility.tranferDoctor_Service(transfer);
	}
}
