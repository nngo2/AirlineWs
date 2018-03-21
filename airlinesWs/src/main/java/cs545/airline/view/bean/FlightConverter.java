package cs545.airline.view.bean;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import cs545.airline.model.Flight;
import cs545.airline.service.FlightService;

@Named("flightConverter")
public class FlightConverter implements Converter{
	@Inject
	private FlightService flightService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return flightService.findById(Long.valueOf(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if(object != null) {
            return String.valueOf(((Flight) object).getId());
        }
        else {
            return null;
        }
	}

}
