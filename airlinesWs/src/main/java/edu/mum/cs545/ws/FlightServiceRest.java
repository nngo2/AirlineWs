package edu.mum.cs545.ws;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
	private static DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT,Locale.US);	
	private static DateFormat tf = DateFormat.getTimeInstance(DateFormat.SHORT,Locale.US);	
	
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

	
	@Path("/byDestination")
	@GET
	@ApiOperation(value = "Get all flights by destination", notes = "Get all flights by destination")
	public Response findByDestination(@ApiParam(value = "An airport name", required = true) @QueryParam("name") String name) {
		
		List<Flight> flights = null;

		try {
			flights = flightService.findByDestinationName(name);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return Response.ok().entity(flights).build();
	}
	
	@Path("/byAirplane")
	@GET
	@ApiOperation(value = "Get all flights by airplane", notes = "Get all flights by airplane")
	public Response findByAirplane(
			@ApiParam(value = "An airplane serial nimber", required = true) @QueryParam("serialNo") String serialNo) {
		List<Flight> flights = null;

		try {
			flights = flightService.findByAirlineSerialNo(serialNo);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return Response.ok().entity(flights).build();
	}
	
	@Path("/byArrival")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get all flights by arrival in a specific date", notes = "Get all flights by arrival in a specific date")
	public Response findByArrival(
			@ApiParam(value = "Give a date ", required = true) @QueryParam("date") String date,
			@ApiParam(value = "Give a time ", required = true) @QueryParam("time") String time) {

		List<Flight> flights = null;

		try {
			Date newDate = df.parse(date);
			Date newTime = tf.parse(time);
			flights = flightService.findByArrival(newDate, newTime);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return Response.ok().entity(flights).build();
	}
	
	@Path("/byArrivalBetween")
	@GET
	@ApiOperation(value = "Get all flights by arrival betweet two dates", notes = "Get all flights by arrival betweet two dates")
	public Response findByArrivalBetween(
			@ApiParam(value = "From date", required = true) @QueryParam("from") String datetimeFrom,
			@ApiParam(value = "To date", required = true) @QueryParam("to") String datetimeTo) {
		
		List<Flight> flights = null;

		try {
			Date from = df.parse(datetimeFrom);
			Date to = df.parse(datetimeTo);
			flights = flightService.findByArrivalBetween(from, to);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return Response.ok().entity(flights).build();		
	}
	
	@Path("/byDeparture")
	@GET
	@ApiOperation(value = "Get all flights by departure in a specific date", notes = "Get all flights by departure in a specific date")
	public Response findByDeparture(
			@ApiParam(value = "Give a date ", required = true) @QueryParam("date") String date,
			@ApiParam(value = "Give a time ", required = true) @QueryParam("time") String time) {
		
		List<Flight> flights = null;

		try {
			Date newDate = df.parse(date);
			Date newTime = tf.parse(time);
			flights = flightService.findByDeparture(newDate, newTime);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return Response.ok().entity(flights).build();
	}

	@Path("/byDepartureBetween")
	@GET
	@ApiOperation(value = "Get all flights by departure between two dates", notes = "Get all flights by departure between two dates")
	public Response findByDepartureBetween(
			@ApiParam(value = "From date", required = true) @QueryParam("from") String datetimeFrom,
			@ApiParam(value = "To date", required = true) @QueryParam("to") String datetimeTo) {

		List<Flight> flights = null;

		try {
			Date from = df.parse(datetimeFrom);
			Date to = df.parse(datetimeTo);
			flights = flightService.findByDepartureBetween(from, to);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return Response.ok().entity(flights).build();
	}
}
