package cs545.airline.service;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import cs545.airline.dao.FlightDao;
import cs545.airline.model.Airline;
import cs545.airline.model.Airplane;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;

@Named
@ApplicationScoped
@Transactional
public class FlightService {

	@Inject
	private FlightDao flightDao;
	
	// These services should be evaluated to reconsider which methods should be
	// public

	public void create(Flight flight) {
		flightDao.create(flight);
	}

	// DELETE MUST BE DONE THROUGH UPDATE ON RELATED OBJECT
	//public void delete(Flight flight) {
	//	flightDao.delete(flight);
	//}

	public Flight update(Flight flight) {
		return flightDao.update(flight);
	}

	public Flight find(Flight flight) {
		return flightDao.findOne(flight.getId());
	}
	
	public Flight findById(Long id) {
		return flightDao.findOne(id);
	}	

	public List<Flight> findByNumber(String flightnr) {
		return flightDao.findByFlightnr(flightnr);
	}

	public List<Flight> findByAirline(Airline airline) {
		return flightDao.findByAirline(airline.getId());
	}
	
	public List<Flight> findByAirlineId(long airlineId) {
		return flightDao.findByAirline(airlineId);
	}	

	public List<Flight> findByOrigin(Airport airport) {
		return flightDao.findByOrigin(airport.getId());
	}
	
	public List<Flight> findByOriginName(String name) {
		return flightDao.findByOriginName(name);
	}	

	public List<Flight> findByDestination(Airport airport) {
		return flightDao.findByDestination(airport.getId());
	}
	
	public List<Flight> findByDestinationName(String name) {
		return flightDao.findByDestinationName(name);
	}	

	public List<Flight> findByAirplane(Airplane airplane) {
		return flightDao.findByAirplane(airplane.getId());
	}
	
	public List<Flight> findByAirlineSerialNo(String airplaneSerialNo) {
		return flightDao.findByAirplaneSerialNo(airplaneSerialNo);
	}		
	
	public List<Flight> findByArrival(Date date) {
		return flightDao.findByArrival(date, date);
	}

	public List<Flight> findByArrival(Date date, Date time) {
		return flightDao.findByArrival(date, time);
	}

	public List<Flight> findByArrivalBetween(Date datetimeFrom, Date datetimeTo) {
		return flightDao.findByArrivalBetween(datetimeFrom, datetimeTo);
	}

	public List<Flight> findByDeparture(Date date) {
		return flightDao.findByDeparture(date, date);
	}	
	
	public List<Flight> findByDeparture(Date date, Date time) {
		return flightDao.findByDeparture(date, time);
	}

	public List<Flight> findByDepartureBetween(Date datetimeFrom, Date datetimeTo) {
		return flightDao.findByDepartureBetween(datetimeFrom, datetimeTo);
	}

	public List<Flight> findAll() {
		return flightDao.findAll();
	}
}
