package edu.mum.cs545.ws;

import javax.inject.Named;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Named
@Path("hello")
@Api( value="/")
public class HelloRest {

	@GET
	@ApiOperation(value="/hello?name=some_name", notes="hello method")	
	public String helloWorld(@DefaultValue("Gorgeous") @QueryParam("name") String name) {
		return "Hello " + name + "!";
	}

}
