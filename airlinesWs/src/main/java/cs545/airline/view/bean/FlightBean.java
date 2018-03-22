package cs545.airline.view.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.time.DateUtils;

import cs545.airline.model.Airline;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.service.AirportService;
import cs545.airline.service.FlightService;
@Named(value="flightBean")
@ViewScoped
public class FlightBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT,
			Locale.US);
	private static DateFormat tf = DateFormat.getTimeInstance(DateFormat.SHORT,
			Locale.US);

	private Flight newFlight;
	private FlightTime flightTime;
	private List<Flight> flights;
	private List<Flight> filteredFlights;
	@Inject
	private FlightService flightService;
	@Inject
	private AirportService airportService;
	

	@PostConstruct
	public void init() {
		flights = flightService.findAll();
		initNewFlight();
	}
	
	public List<Airport> getAirports(){
		return airportService.findAll();
	}

	
	public boolean filterByDate(Object value, Object filter, Locale locale) throws ParseException {
		Date dateToFilered = null;
		if (filter == null) {
			return true;
		}

		if (value == null) {
			return false;
		}
		dateToFilered = df.parse(value.toString());
		return DateUtils.truncatedEquals(dateToFilered, (Date) filter, Calendar.DATE);
	}
	
	public boolean filterByTime(Object value, Object filter, Locale locale) throws ParseException {
		Date dateToFilered = null;
		if (filter == null) {
			return true;
		}

		if (value == null) {
			return false;
		}
		dateToFilered = tf.parse(value.toString());
		return DateUtils.truncatedEquals(dateToFilered, (Date) filter, Calendar.MINUTE);
	}
	
	public void addFlight() {
		String arrivalDate = df.format(flightTime.getArrivalDate());
		String arrivalTime = tf.format(flightTime.getArrivalTime());
		String departureDate = df.format(flightTime.getDepartureDate());
		String departureTime = tf.format(flightTime.getDepartureTime());
		newFlight.setDepartureDate(departureDate);
		newFlight.setDepartureTime(departureTime);
		newFlight.setArrivalDate(arrivalDate);
		newFlight.setArrivalTime(arrivalTime);
		Airport des = airportService.findById(newFlight.getDestination().getId());
		Airport org = airportService.findById(newFlight.getOrigin().getId());
		newFlight.setOrigin(null);
		newFlight.setDestination(null);
		flightService.create(newFlight);
		des.addArrival(newFlight);
		org.addDeparture(newFlight);
		airportService.update(des);
		airportService.update(org);
		initNewFlight();
	}
	

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public List<Flight> getFilteredFlights() {
		return filteredFlights;
	}

	public void setFilteredFlights(List<Flight> filteredFlights) {
		this.filteredFlights = filteredFlights;
	}
	
	

	public Flight getNewFlight() {
		return newFlight;
	}

	public void setNewFlight(Flight newFlight) {
		this.newFlight = newFlight;
	}
	
	
	public FlightTime getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(FlightTime flightTime) {
		this.flightTime = flightTime;
	}
	

	private void initNewFlight() {
		Flight newFlight = new Flight();
		newFlight.setOrigin(new Airport());
		newFlight.setDestination(new Airport());
		this.newFlight = newFlight;
		this.flightTime = new FlightTime();
	}
}
