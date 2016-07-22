package org.dsms.lvl.dsms_lvl_rest.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.dsms.lvl.dsms_lvl_rest.service.NurseService;

import rest.metadata.CreateNR_Metadata;
import rest.metadata.CreateNurse_Metadata;

@Path("createNR")
public class CreateNurseResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addResource(CreateNR_Metadata nurse)
	{
		NurseService utility = new NurseService();
		
		return utility.addNurse_Service(nurse);
	}
	
}
