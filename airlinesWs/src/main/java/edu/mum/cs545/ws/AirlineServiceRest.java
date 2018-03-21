
package edu.mum.cs545.ws;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cs545.airline.model.Airline;
import cs545.airline.model.FlightDto;
import cs545.airline.service.AirlineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Named
@Path("airline")
@Api(value = "/airline")
@Produces({ MediaType.APPLICATION_JSON })
public class AirlineServiceRest {
	@Inject
	private AirlineService airlineService;

	@Path("/")
	@GET
	@ApiOperation(value = "Get all airlines", notes = "Get all airlines")
	public List<Airline> getAllAirlines() {
		return airlineService.findAll();
	}

	@Path("/{airlineId}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get airline by id", notes = "Get airline by id")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Airline not found") })
	public Response getAirlineById(
			@ApiParam(value = "Airline id to find", required = true) @PathParam("airlineId") Long airlineId) {
		Airline ariline = airlineService.findById(airlineId);
		return Response.ok().entity(ariline).build();
	}

	@Path("/findByName")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get airline by name", notes = "Get airline by name")
	public Response getAirlineByName(
			@ApiParam(value = "Airline name to find", required = true) @QueryParam("name") String name) {
		Airline airline = null;
		try {
			airline = airlineService.findByName(name);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return Response.ok().entity(airline).build();
	}

	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create an airline", notes = "Create an airline")
	public void createAirline(@ApiParam(value = "An airline object", required = true) Airline airline) {
		airlineService.create(airline);
	}

	@Path("/")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Update an airline", notes = "Update an airline")
	public Response editAirline(@ApiParam(value = "An airline object", required = true) Airline airline) {
		Airline update = airlineService.update(airline);
		return Response.ok().entity(update).build();
	}

	@Path("/{airlineId}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Delete an airline", notes = "Delete an airline")
	public void deleteAirline(
			@ApiParam(value = "Airline id to delete", required = true) @PathParam("airlineId") Long airlineId) {
		Airline airline = airlineService.findById(airlineId);
		if (airline != null) {
			airlineService.delete(airline);
		}
	}
	
	@Path("/{airlineId}/flight")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create a flight", notes = "Create a flight")
	public Response createFlight(
			@ApiParam(value = "Airline id", required = true) @PathParam("airlineId") Long airlineId, 			
			@ApiParam(value = "Flight Information", required = true) FlightDto flight) {
		Airline airline = airlineService.createFlight(airlineId, flight);
		return Response.ok().entity(airline.getFlights()).build();		
	}
	
	@Path("/{airlineId}/flight")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Edit a flight", notes = "Edit a flight")
	public Response editFlight(
			@ApiParam(value = "Airline id", required = true) @PathParam("airlineId") Long airlineId, 			
			@ApiParam(value = "Flight Information", required = true) FlightDto flight) {
		Airline airline = airlineService.updateFlight(airlineId, flight);
		return Response.ok().entity(airline.getFlights()).build();
	}	
	
	@Path("/{airlineId}/flight")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Delete a flight", notes = "Delete a flight")
	public Response deleteFlight(
			@ApiParam(value = "Airline id", required = true) @PathParam("airlineId") Long airlineId, 			
			@ApiParam(value = "Flight id", required = true) @QueryParam("flightId") Long flightId) {
		Airline airline = airlineService.deleteFlight(airlineId, flightId);
		return Response.ok().entity(airline.getFlights()).build();
	}		
}
