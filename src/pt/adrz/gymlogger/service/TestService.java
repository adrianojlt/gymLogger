package pt.adrz.gymlogger.service;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/test")
public class TestService {
	
	@GET
	public Response getUserAgent(@HeaderParam("user-agent") String userAgent) {
		String resp = "user-agent" + userAgent;
		return Response.status(200).entity(resp).build();
	}
}
