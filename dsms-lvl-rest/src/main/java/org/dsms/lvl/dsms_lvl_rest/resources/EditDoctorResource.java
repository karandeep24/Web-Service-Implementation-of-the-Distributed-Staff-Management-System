package org.dsms.lvl.dsms_lvl_rest.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.dsms.lvl.dsms_lvl_rest.service.EditDRecordService;

import rest.metadata.CreateDoctor_Metadata;

@Path("editDR")
public class EditDoctorResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String editDoctor_Resource(CreateDoctor_Metadata doctor)
	{
		System.out.println(":::::::::::::::IN :::::::::");
		EditDRecordService utility = new EditDRecordService();
		return utility.editDoctor_Service(doctor);
	}
}
