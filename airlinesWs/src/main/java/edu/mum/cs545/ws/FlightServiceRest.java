package edu.mum.cs545.ws;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cs545.airline.model.Airline;
import cs545.airline.model.Airplane;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.service.FlightService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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

	@Path("/find/{flightId}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get a flight", notes = "Get a flight")
	public Response findById(
			@ApiParam(value = "to find a flight by Id", required = true) @PathParam("flightId") Long flightId) {
		Flight flight = flightService.findById(flightId);
		return Response.ok().entity(flight).build();
	}

	@Path("/findByNumber")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get all flights by number", notes = "Get all flights by number")
	public List<Flight> findByNumber(
			@ApiParam(value = "Flight by number to find", required = true) @QueryParam("number") String number) {
		return flightService.findByNumber(number);
	}

	@Path("/findByAirlineId")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get all flights by the airline", notes = "Get all flights by the airline")
	public List<Flight> findByAirline(
			@ApiParam(value = "An Airline object", required = true) @QueryParam("byAirlineId") Long byAirlineId) {
		return flightService.findByAirlineId(byAirlineId);
	}

	@Path("/byOriginAirportName")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get flights by their origin", notes = "Get flights by their origin")
	public Response findByOrigin(
			@ApiParam(value = "An airport name", required = true) @QueryParam("name") String name) {
		List<Flight> flights = null;

		try {
			flights = flightService.findByOriginName(name);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return Response.ok().entity(flights).build();
	}

	//
	// @Path("/byDestination")
	// @GET
	// @Consumes(MediaType.APPLICATION_JSON)
	// @ApiOperation(value = "Get all flights by destination", notes = "Get all
	// flights by destination")
	// public List<Flight> findByDestination(@ApiParam(value = "An airport object",
	// required = true) Airport airport) {
	// return flightService.findByDestination(airport);
	// }
	//
	// @Path("/byAirplane")
	// @GET
	// @Consumes(MediaType.APPLICATION_JSON)
	// @ApiOperation(value = "Get all flights by airplane", notes = "Get all flights
	// by airplane")
	// public List<Flight> findByAirplane(@ApiParam(value = "An airplane object",
	// required = true) Airplane airplane) {
	// return flightService.findByAirplane(airplane);
	// }
	//
	// @Path("/byArrival")
	// @GET
	// @Consumes(MediaType.APPLICATION_JSON)
	// @ApiOperation(value = "Get all flights by arrival in a specific date", notes
	// = "Get all flights by arrival in a specific date")
	// public List<Flight> findByArrival(@ApiParam(value = "Give a date", required =
	// true) Date datetime) {
	// return flightService.findByArrival(datetime);
	// }
	//
	// @Path("/byArrivalBetween")
	// @GET
	// @Consumes(MediaType.APPLICATION_JSON)
	// @ApiOperation(value = "byArrivalBetween", notes = "Get all flights by arrival
	// betweet two dates")
	// public List<Flight> findByArrivalBetween(@ApiParam(value = "Input two dates",
	// required = true) Date datetimeFrom,
	// Date datetimeTo) {
	// return flightService.findByArrivalBetween(datetimeFrom, datetimeTo);
	// }
	//
	// @Path("/byDeparture")
	// @GET
	// @Consumes(MediaType.APPLICATION_JSON)
	// @ApiOperation(value = "byDeparture", notes = "Get all flights by departure in
	// a specific date")
	// public List<Flight> findByDeparture(@ApiParam(value = "Give a date ",
	// required = true) Date datetime) {
	// return flightService.findByDeparture(datetime);
	// }
	//
	// @Path("/byDepartureBetween")
	// @GET
	// @Consumes(MediaType.APPLICATION_JSON)
	// @ApiOperation(value = "byDepartureBetween", notes = "Get all flights by
	// departure between two dates")
	// public List<Flight> findByDepartureBetween(@ApiParam(value = "input two dates
	// ", required = true) Date datetimeFrom,
	// Date datetimeTo) {
	// return flightService.findByDepartureBetween(datetimeFrom, datetimeTo);
	// }
}
