package edu.mum.cs545.ws;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import cs545.airline.model.Flight;
import cs545.airline.service.FlightService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Named
@Path("flight")
@Api(value = "/flight")
@Produces({ MediaType.APPLICATION_JSON })
public class FlightServiceRest {

	@Inject
	private FlightService flightService;
	
	@Path("/")
	@GET
	@ApiOperation(value = "Get all flights", notes = "Get all flights")
	public List<Flight> getAllFlights() {
		return flightService.findAll();
	}
}
