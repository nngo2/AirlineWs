package cs545.airline.view.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import cs545.airline.model.Airline;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;
import cs545.airline.service.FlightService;


@ManagedBean(name = "airlineBean")
@RequestScoped
public class AirlineBean implements Serializable {

	private static final long serialVersionUID = 1L;


	private Airline newAirline;
	@Inject
	private AirlineService airlineService;
	@Inject
	private FlightService flightService;
	
	@ManagedProperty(value="#{customTemplateController}")
	private CustomTemplateController templateController;

	private List<String> selectedFlights;
	
	private Long selectedFlightId;
	@PostConstruct
	public void init() {
		newAirline = new Airline();
		selectedFlights = new ArrayList<>();
	}

	public List<Airline> getAirlines() {
		return airlineService.findAll();
	}

	public Airline getNewAirline() {
		return newAirline;
	}

	public void setNewAirline(Airline newAirline) {
		this.newAirline = newAirline;
	}

	public void addAirline(){
		System.out.println("event fired");
		for(Flight f : newAirline.getFlights()) {
			newAirline.removeFlight(f);
		}
		
		for(String f : selectedFlights) {
			Flight fl = flightService.findById(Long.valueOf(f));
			newAirline.addFlight(fl);
		}
		airlineService.create(newAirline);
		this.newAirline = new Airline();
		selectedFlights.clear();
		templateController.toAirlinesView();
	}
	
	public void removeAirline(Long id) {
		Airline airline = airlineService.findById(id);
		if(airline!= null) {
			airlineService.delete(airline);
		}
	}

	public List<String> getSelectedFlights() {
		return selectedFlights;
	}

	public void setSelectedFlights(List<String> selectedFlights) {
		this.selectedFlights = selectedFlights;
	}

	public void setTemplateController(CustomTemplateController templateController) {
		this.templateController = templateController;
	}
	
	public void removeAirlineFlight(Long airlineId, Long flightId) {
		Airline airline = airlineService.findById(airlineId);
		Flight fl = flightService.findById(flightId);
		airline.removeFlight(fl);
		flightService.update(fl);
		airlineService.update(airline);
	}
	
	public List<Flight> getAvaiableFights() {
		return flightService.findAll().stream()
				.filter(fl -> fl.getAirline() == null)
				.collect(Collectors.toList());
	}
	
	public void addFlight(Long airlineId) {
		if(this.selectedFlightId!=null) {
			Airline airline = airlineService.findById(airlineId);
			Flight fl = flightService.findById(selectedFlightId);
			airline.addFlight(fl);
			airlineService.update(airline);
			this.setSelectedFlightId(null);
		}
	}

	public Long getSelectedFlightId() {
		return selectedFlightId;
	}

	public void setSelectedFlightId(Long selectedFlightId) {
		this.selectedFlightId = selectedFlightId;
	}

	
}
