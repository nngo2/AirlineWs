package cs545.airline.view.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import cs545.airline.model.Airline;
import cs545.airline.service.AirlineService;


@ManagedBean(name = "airlineBean")
@RequestScoped
public class AirlineBean implements Serializable {

	private static final long serialVersionUID = 1L;


	private Airline newAirline;
	@Inject
	private AirlineService airlineService;

	@PostConstruct
	public void init() {
		newAirline = new Airline();
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
		airlineService.create(newAirline);
		this.newAirline = new Airline();
	}
	

}
