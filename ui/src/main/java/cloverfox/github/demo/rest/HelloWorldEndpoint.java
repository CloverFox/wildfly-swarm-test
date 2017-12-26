package cloverfox.github.demo.rest;

import cloverfox.github.demo.Hello;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


@Singleton
@Path("/hello")
@Startup
@Slf4j
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