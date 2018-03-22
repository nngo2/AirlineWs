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

import cs545.airline.model.Airport;
import cs545.airline.service.AirportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Named
@Path("airport")
@Api(value = "/airport")
@Produces({ MediaType.APPLICATION_JSON })
public class AirportServiceRest {
	@Inject
	private AirportService airportService;

	@Path("/")
	@GET
	@ApiOperation(value = "Get all airports", notes = "Get all airports")
	public List<Airport> getAllAirports() {
		return airportService.findAll();
	}

	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create an airport", notes = "Create an airport")
	public void createAirport(@ApiParam(value = "An airport object", required = true) Airport airport) {
		airportService.create(airport);
	}

	@Path("/")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Update an airport", notes = "Update an airport")
	public Response editAirport(@ApiParam(value = "An airport object", required = true) Airport airport) {
		Airport update = airportService.update(airport);
		return Response.ok().entity(update).build();
	}

	@Path("/{airportId}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Delete an airport", notes = "Delete an airport")
	public void deleteAirport(
			@ApiParam(value = "Airport id to delete", required = true) @PathParam("airportId") Long airportId) {
		Airport airport = airportService.findById(airportId);
		if (airport != null) {
			airportService.delete(airport);
		}
	}

	@Path("/findByCode")
	@GET
	@ApiOperation(value = "Find airport by code", notes = "Find airport by code")
	public Response findByCode (
			@ApiParam(value = "Airport code to find", required = true) @QueryParam("code") String airportcode) {
		Airport airport = null;
		try {
			airport = airportService.findByCode(airportcode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok().entity(airport).build();		
	}


	@Path("/findByCountry")
	@GET
	@ApiOperation(value = "Find airport by country", notes = "Find airport by country")
	public List<Airport> findByCountry(
			@ApiParam(value = "Airport country to find", required = true) @QueryParam("country") String country) {
		List<Airport> airports = null;
		try {
			airports = airportService.findByCountry(country);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return airports;
	}


	@Path("/findByCity")
	@GET
	@ApiOperation(value = "Find airport by city", notes = "Find airport by city")
	public List<Airport> findByCity(
			@ApiParam(value = "Airport city to find", required = true) @QueryParam("city") String city) {
		List<Airport> airports = null;
		try {
			airports = airportService.findByCity(city);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return airports;		
	}

	@Path("/findByName")
	@GET
	@ApiOperation(value = "Find airport by name", notes = "Find airport by name")
	public List<Airport> findByName(
			@ApiParam(value = "Airport name to find", required = true) @QueryParam("name") String name) {
		List<Airport> airports = null;
		try {
			airports = airportService.findByName(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return airports;				
	}

}
