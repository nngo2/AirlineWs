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

import cs545.airline.model.Flight;
import cs545.airline.service.FlightService;
@Named(value="flightBean")
@ViewScoped
public class FlightBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT,
			Locale.US);
	private static DateFormat tf = DateFormat.getTimeInstance(DateFormat.SHORT,
			Locale.US);

	private List<Flight> flights;
	private List<Flight> filteredFlights;
	@Inject
	private FlightService flightService;

	@PostConstruct
	public void init() {
		flights = flightService.findAll();
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

}
