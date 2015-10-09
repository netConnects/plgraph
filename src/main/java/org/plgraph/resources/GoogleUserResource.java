package org.plgraph.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/oauth2callback")
@Produces(MediaType.APPLICATION_JSON)
public class GoogleUserResource {
	
	@GET
	public Response getGoogleCode(@QueryParam("code") String code) {
		System.out.println(code);
		return Response.status(Status.OK).entity(code).build();
	}
}
