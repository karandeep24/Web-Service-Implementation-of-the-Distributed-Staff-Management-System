package org.dsms.mtl.dsms_mtl_rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.dsms.mtl.dsms_mtl_rest.service.GetCountService;

@Path("getCount")
public class GetCountResource {
	
	@GET
	@Path("/{data}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getDoctor(@PathParam("data") String data) {
        
		GetCountService service = new GetCountService();
		return service.getCount_Service(data);
    }

}
