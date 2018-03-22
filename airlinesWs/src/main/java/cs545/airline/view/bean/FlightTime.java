package cs545.airline.view.bean;

import java.util.Date;

public class FlightTime {
	private Date departureDate;
	private Date departureTime;
	private Date arrivalDate;
	private Date arrivalTime;
	
	

	public FlightTime() {
		this.departureDate = new Date();
		this.departureTime = new Date();
		this.arrivalDate = new Date();
		this.arrivalTime = new Date();
		
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

}
