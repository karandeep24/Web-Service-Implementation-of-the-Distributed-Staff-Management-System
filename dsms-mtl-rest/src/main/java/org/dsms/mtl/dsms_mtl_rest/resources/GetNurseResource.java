package org.dsms.mtl.dsms_mtl_rest.resources;

import java.util.Vector;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.dsms.mtl.dsms_mtl_rest.service.GetNurseService;
import rest.metadata.GetNurse_Metadata;

@Path("getNR")
public class GetNurseResource {

	@GET
	@Path("/{nurseID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Vector<GetNurse_Metadata> getNurse(@PathParam("nurseID") String nurseID) {
        
		GetNurseService service = new GetNurseService();
		return service.getNurse_Service(nurseID);
    }
}
