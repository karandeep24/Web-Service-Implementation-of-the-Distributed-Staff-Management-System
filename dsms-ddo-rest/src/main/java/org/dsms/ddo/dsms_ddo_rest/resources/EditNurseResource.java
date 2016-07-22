package org.dsms.ddo.dsms_ddo_rest.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.dsms.ddo.dsms_ddo_rest.service.EditNRecordService;

import rest.metadata.CreateNurse_Metadata;

@Path("editNR")
public class EditNurseResource {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String editNurse_Resource(CreateNurse_Metadata nurse)
	{
		EditNRecordService utility = new EditNRecordService();
		return utility.editNurse_Service(nurse);
	}

}
