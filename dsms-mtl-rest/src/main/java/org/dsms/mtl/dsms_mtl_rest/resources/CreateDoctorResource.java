package org.dsms.mtl.dsms_mtl_rest.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.dsms.mtl.dsms_mtl_rest.service.DoctorService;
import org.dsms.mtl.dsms_mtl_rest.utility.ServerUtility;

import rest.metadata.CreateDR_Metadata;
import rest.metadata.CreateDoctor_Metadata;

@Path("createDR")
public class CreateDoctorResource {
	
	
//		@GET
//		@Produces(MediaType.TEXT_PLAIN)
//		public String getResource()
//		{
//			return "Doctor Created";
//		}
		
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String addResource(CreateDR_Metadata doctor)
		{
			DoctorService utility = new DoctorService();
			
			return utility.addDoctor_Service(doctor);
		}
		
}
