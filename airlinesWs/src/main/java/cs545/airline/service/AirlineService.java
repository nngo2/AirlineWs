package cs545.airline.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import cs545.airline.dao.AirlineDao;
import cs545.airline.model.Airline;
import cs545.airline.model.Airplane;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.model.FlightDto;

@Named
@ApplicationScoped
@Transactional
public class AirlineService {

	// These services should be evaluated to reconsider which methods should be
	// public

	@Inject
	private AirlineDao airlineDao;
	
	@Inject 
	private AirplaneService airplaneService;
	
	@Inject 
	private AirportService airportService;	
	
	public void createFlight(Long airlineId, FlightDto flight) {
		Airline airline = airlineDao.findOne(airlineId);		
		Airplane airplane = airplaneService.findById(flight.getAirplaneId());
		Airport orgAirport = airportService.findById(flight.getOrgAirportId());
		Airport destAirport = airportService.findById(flight.getDestAirportId());		
		
		if (airline != null && airplane != null && orgAirport != null && destAirport != null) {
			Flight flightEntity = new Flight(flight.getFlightnr(), flight.getDepartureDate(), 
					flight.getDepartureTime(), flight.getArrivalDate(), flight.getArrivalTime(),
					airline, orgAirport, destAirport, airplane);
			airplane.addFlight(flightEntity);
			airlineDao.create(airline);
		}
	}
	
	public Airline updateFlight(Long airlineId, FlightDto flight) {
		Airline airline = airlineDao.findOne(airlineId);		
		Airplane airplane = airplaneService.findById(flight.getAirplaneId());
		Airport orgAirport = airportService.findById(flight.getOrgAirportId());
		Airport destAirport = airportService.findById(flight.getDestAirportId());		
		
		if (airline != null && airplane != null && orgAirport != null && destAirport != null) {
			Flight exist = flightDao.findOne(flight.getId());
			if (exist == null) {
				Flight flightEntity = new Flight(flight.getFlightnr(), flight.getDepartureDate(), 
						flight.getDepartureTime(), flight.getArrivalDate(), flight.getArrivalTime(),
						airline, orgAirport, destAirport, airplane);
				airplane.addFlight(flightEntity);
			} else {
				exist.setArrivalDate(flight.getArrivalDate());
				exist.setArrivalTime(flight.getArrivalTime());
				exist.setDepartureDate(flight.getDepartureDate());
				exist.setDepartureTime(flight.getDepartureTime());
				exist.setFlightnr(flight.getFlightnr());	
				if (airplane != null) {
					exist.setAirplane(airplane);
				}
				if (orgAirport != null) {
					exist.setOrigin(orgAirport);
				}
				if (destAirport != null) {
					exist.setDestination(destAirport);
				}
			}

			return airlineDao.update(airline);
		}
		
		return airline;
	}

	public void create(Airline airline) {
		airlineDao.create(airline);
	}

	public void delete(Airline airline) {
		airlineDao.delete(airline);
	}

	public Airline update(Airline airline) {
		return airlineDao.update(airline);
	}

	public Airline find(Airline airline) {
		return airlineDao.findOne(airline.getId());
	}
	
	public Airline findById(Long id) {
		return airlineDao.findOne(id);
	}

	public Airline findByName(String name) {
		return airlineDao.findOneByName(name);
	}

	public List<Airline> findByFlight(Flight flight) {
		return airlineDao.findByFlight(flight.getId());
	}

	public List<Airline> findAll() {
		return airlineDao.findAll();
	}
}
