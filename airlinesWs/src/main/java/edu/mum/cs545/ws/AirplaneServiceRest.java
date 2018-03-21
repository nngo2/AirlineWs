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

import cs545.airline.model.Airplane;
import cs545.airline.service.AirplaneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Named
@Path("airplane")
@Api(value = "/airplane")
@Produces({ MediaType.APPLICATION_JSON })
public class AirplaneServiceRest {

	@Inject
	private AirplaneService airplaneService;
	
	@Path("/")
	@GET
	@ApiOperation(value = "Get all airplane", notes = "Get all airplanes")
	public List<Airplane> getAllAirplaness() {
		return airplaneService.findAll();
	}

	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create an airplane", notes = "Create an airplane")
	public void createAirplane(@ApiParam(value = "An plane object", required = true) Airplane plane) {
		airplaneService.create(plane);
	}

	@Path("/")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Update an airplane", notes = "Update an airplane")
	public Response editAirplane(@ApiParam(value = "An airplane object", required = true) Airplane airplane) {
		Airplane update = airplaneService.update(airplane);
		return Response.ok().entity(update).build();
	}

	@Path("/{airplaneId}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Delete an airplane", notes = "Delete an airplane")
	public void deleteAirplane(
			@ApiParam(value = "Airplane id to delete", required = true) @PathParam("airplaneId") Long airplaneId) {
		Airplane airplane = airplaneService.findById(airplaneId);
		if (airplane != null) {
			airplaneService.delete(airplane);
		}
	}
	
	@Path("/findByModel")
	@GET
	@ApiOperation(value = "Find airplane by mode", notes = "Find airplane by model")
	public Response findByCode (
			@ApiParam(value = "Airplane model to find", required = true) @QueryParam("model") String model) {
		List<Airplane> airplanes = null;
		try {
			airplanes = airplaneService.findByModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok().entity(airplanes).build();		
	}
	
	@Path("/findBySerialNo")
	@GET
	@ApiOperation(value = "Find airplane by serial number", notes = "Find airplane by serial number")
	public Response findBySerialNo(
			@ApiParam(value = "Airplane serial number to find", required = true) @QueryParam("serialno") String serialno) {
		Airplane airplane = null;
		try {
			airplane = airplaneService.findBySrlnr(serialno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok().entity(airplane).build();		
	}
}
