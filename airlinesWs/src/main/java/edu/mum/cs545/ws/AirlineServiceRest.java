package edu.mum.cs545.ws;

import java.util.List;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import cs545.airline.model.Airline;
import cs545.airline.service.AirlineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Named
@Path("airline")
@Api( value="/airline")
public class AirlineServiceRest {
	@Inject
	private AirlineService airlineService;
	
	@Path("/")
	@GET
	@Produces
	@ApiOperation(value="/", notes="Get all airlines")		
	public List<Airline> getAirlineTest() {
		return airlineService.findAll();
	}
}
