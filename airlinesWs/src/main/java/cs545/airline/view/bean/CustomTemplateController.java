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

	public void toViewFlight() {
		mainContent = MainView.VIEW_FLIGHT.getName();
	}

	public void toAirlinesView() {
		mainContent = MainView.VIEW_AIRLINES.getName();
	}
	
	public void toAddAirline() {
		mainContent = MainView.ADD_AIRLINE.getName();
	}

	public String getMainContent() {
		return mainContent;
	}

	static enum MainView {
		VIEW_FLIGHT("view-flights.xhtml"), 
		VIEW_AIRLINES("view-airlines.xhtml"),
		ADD_AIRLINE("add-airline.xhtml");
		private String name;

		public String getName() {
			return name;
		}

		private MainView(String viewName) {
			this.name = viewName;
		}
	}

}
