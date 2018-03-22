package cs545.airline.view.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "customTemplateController")
@SessionScoped
public class CustomTemplateController implements Serializable {
	private static final long serialVersionUID = 1L;
	private String mainContent;

	@PostConstruct
	public void init() {
		toViewFlight();
	}

	public void toAirplanesView() {
		mainContent = MainView.VIEW_AIRPLANES.getName();
	}
	
	public void toAddAirplaneView() {
		mainContent = MainView.VIEW_ADD_AIRPLANE.getName();
	}
	
	public void toViewFlight() {
		mainContent = MainView.VIEW_FLIGHT.getName();
	}

	public void toAirlinesView() {
		mainContent = MainView.VIEW_AIRLINES.getName();
	}
	
	public void toAddAirline() {
		mainContent = MainView.ADD_AIRLINE.getName();
	}
	
	public void toAddFlight() {
		mainContent = MainView.ADD_FLIGHT.getName();
	}

	public String getMainContent() {
		return mainContent;
	}

	static enum MainView {
		VIEW_AIRPLANES("plane/view-planes.xhtml"), 
		VIEW_ADD_AIRPLANE("plane/create-plane.xhtml"), 		
		VIEW_FLIGHT("flight/view-flights.xhtml"), 
		ADD_FLIGHT("flight/add-flight.xhtml"), 
		VIEW_AIRLINES("airline/view-airlines.xhtml"),
		ADD_AIRLINE("airline/add-airline.xhtml");
		private String name;

		public String getName() {
			return name;
		}

		private MainView(String viewName) {
			this.name = viewName;
		}
	}

}
