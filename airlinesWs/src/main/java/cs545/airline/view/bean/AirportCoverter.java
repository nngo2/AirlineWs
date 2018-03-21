package cs545.airline.view.bean;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import cs545.airline.model.Airport;
import cs545.airline.service.AirportService;

@FacesConverter("airportConverter")
public class AirportCoverter implements Converter{
	private AirportService airportService;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		String[] tokens = value.split("-");
		if(tokens.length > 1) {
			String airportCode = tokens[1];
			return airportService.findByCode(airportCode);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value!=null && value instanceof Airport) {
			Airport airport = (Airport) value;
			return airport.getName() + "-" + airport.getAirportcode();
		}
		return "";
	}

}
