package edu.mum.cs545.ws;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import cs545.airline.model.Airline;
import cs545.airline.service.AirlineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Named
@Path("hello")
@Api( value="/")
public class HelloRest {

	@Inject
	private AirlineService airlineService;

	@GET
	@ApiOperation(value="/hello?name=some_name", notes="hello method")	
	public String helloWorld(@DefaultValue("Gorgeous") @QueryParam("name") String name) {
		return "Hello " + name + "!";
	}

	@Path("airline")
	@GET
	@ApiOperation(value="/hello/airline", notes="Get all airlines method")		
	public String getAirlineTest() {
		String result = "Nil!";
		Airline airline = airlineService.findByName("oneworld");
		if (airline != null) {
			result = "This is an airline: " + airline.getName();
		}
		return result;
	}

}
