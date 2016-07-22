package org.dsms.mtl.dsms_mtl_rest.resources;

import java.util.Vector;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.dsms.mtl.dsms_mtl_rest.service.GetDoctorService;
import rest.metadata.GetDoctor_Metadata;

@Path("getDR")
public class GetDoctorResource {

	@GET
	@Path("/{doctorID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Vector<GetDoctor_Metadata> getDoctor(@PathParam("doctorID") String doctorID) {
        
		GetDoctorService service = new GetDoctorService();
		return service.getDoctor_Service(doctorID);
    }
	
    
}
