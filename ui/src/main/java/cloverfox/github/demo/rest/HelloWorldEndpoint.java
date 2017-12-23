package cloverfox.github.demo.rest;


import cloverfox.github.demo.Hello;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

@Singleton
@Path("/hello")
public class HelloWorldEndpoint {

	@EJB
	private Hello helloBean;

	@GET
	@Produces("text/plain")
	public Response doGet() {
		String returnHi = helloBean.returnHi();

		return Response.ok(returnHi + " from WildFly Swarm!").build();
	}
}