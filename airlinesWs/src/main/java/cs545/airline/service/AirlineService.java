package cs545.airline.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import cs545.airline.dao.AirlineDao;
import cs545.airline.dao.FlightDao;
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
	private FlightDao flightDao;
	
	@Inject
	private AirlineDao airlineDao;
	
	@Inject 
	private AirplaneService airplaneService;
	
	@Inject 
	private AirportService airportService;	
	
	@Inject 
	private FlightService flightService;		
	
	public Airline createFlight(Long airlineId, FlightDto flight) {
		if (airlineId == null || flight == null) {
			return null;
		}
		
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
			airline = airlineDao.findOne(airlineId);
		}
		
		return airline;	
	}
	
	public Airline updateFlight(Long airlineId, FlightDto flight) {
		if (airlineId == null || flight == null) {
			return null;
		}
		
		Airline airline = airlineDao.findOne(airlineId);		
		Airplane airplane = airplaneService.findById(flight.getAirplaneId());
		Airport orgAirport = airportService.findById(flight.getOrgAirportId());
		Airport destAirport = airportService.findById(flight.getDestAirportId());		
		
		if (airline != null && airplane != null && orgAirport != null && destAirport != null) {
			Flight flightEntity = new Flight(flight.getFlightnr(), flight.getDepartureDate(), 
					flight.getDepartureTime(), flight.getArrivalDate(), flight.getArrivalTime(),
					airline, orgAirport, destAirport, airplane);
			airplane.addFlight(flightEntity);
			return airlineDao.update(airline);
		}
		
		return airline;
	}
	
	public Airline deleteFlight(Long airlineId, Long flightId) {
		if (airlineId == null || flightId == null) {
			return null;
		}
		
		Flight flightEntity = flightService.findById(flightId);
		if (flightEntity == null) {
			return null;
		}
		
		Airline airline = null;
		Airplane airplane = null;
		Airport orgAirport = null;
		Airport destAirport = null;
		
		airline = airlineDao.findOne(airlineId);	
		
		if (flightEntity.getAirplane() != null) {
			airplane = airplaneService.findById(flightEntity.getAirplane().getId());
		}
	
		if (flightEntity.getOrigin() != null) {
			orgAirport = airportService.findById(flightEntity.getOrigin().getId());
		}
		
		if (flightEntity.getDestination() != null) {
			destAirport = airportService.findById(flightEntity.getDestination().getId());			
		}
		
		if (airplane != null) {
			airplane.removeFlight(flightEntity);	
			airplaneService.update(airplane);
		}
		
		if (orgAirport != null) {
			orgAirport.removeDeparture(flightEntity);	
			airportService.update(orgAirport);
		}	
		
		if (destAirport != null) {
			destAirport.removeArrival(flightEntity);			
			airportService.update(destAirport);
		}		
		
		if (airline != null) {
			airline.removeFlight(flightEntity);
			airline = airlineDao.update(airline);
		}
		
		flightDao.delete(flightEntity);
		
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
