package cs545.airline.view.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import cs545.airline.model.Airplane;
import cs545.airline.service.AirplaneService;

@ManagedBean(name = "airplaneBean")
@RequestScoped
public class AirplaneBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Airplane newAirplane;
	private List<Airplane> planes;
	private List<Airplane> filterredPlanes;
	
	@Inject
	private AirplaneService airplaneService;
	
	@ManagedProperty(value="#{customTemplateController}")
	private CustomTemplateController templateController;	
	
	@PostConstruct
	public void init() {
		newAirplane = new Airplane();
		planes = airplaneService.findAll();
	}
	
	public void addAirplane(){
		airplaneService.create(newAirplane);
		newAirplane = new Airplane();
		templateController.toAirplanesView();
	}	

	public List<Airplane> getPlanes() {
		return planes;
	}

	public void setPlanes(List<Airplane> planes) {
		this.planes = planes;
	}

	public List<Airplane> getFilterredPlanes() {
		return filterredPlanes;
	}

	public void setFilterredPlanes(List<Airplane> filterredPlanes) {
		this.filterredPlanes = filterredPlanes;
	}

	public Airplane getNewAirplane() {
		return newAirplane;
	}

	public void setNewAirplane(Airplane newAirplane) {
		this.newAirplane = newAirplane;
	}

	public void setTemplateController(CustomTemplateController templateController) {
		this.templateController = templateController;
	}
	
}
