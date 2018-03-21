package cs545.airline.model;

import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "A flight object")
@XmlRootElement
public class FlightDto {
	private long id;
	private long airlineId;
	private long orgAirportId;
	private long destAirportId;	
	private long airplaneId;	
	private String flightnr;
	private String departureDate;
	private String departureTime;
	private String arrivalDate;
	private String arrivalTime;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFlightnr() {
		return flightnr;
	}
	public void setFlightnr(String flightnr) {
		this.flightnr = flightnr;
	}
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public long getAirlineId() {
		return airlineId;
	}
	public void setAirlineId(long airlineId) {
		this.airlineId = airlineId;
	}
	public long getAirplaneId() {
		return airplaneId;
	}
	public void setAirplaneId(long airplaneId) {
		this.airplaneId = airplaneId;
	}
	public long getDestAirportId() {
		return destAirportId;
	}
	public void setDestAirportId(long destAirportId) {
		this.destAirportId = destAirportId;
	}
	public long getOrgAirportId() {
		return orgAirportId;
	}
	public void setOrgAirportId(long orgAirportId) {
		this.orgAirportId = orgAirportId;
	}
}
