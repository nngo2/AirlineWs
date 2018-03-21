package cs545.airline.view.bean;

import java.util.TimeZone;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

@FacesConverter("defaultDateConverter")
public class DefaultDateConverter extends DateTimeConverter {

	public DefaultDateConverter() {
		setPattern("MM/dd/yyyy");
		this.setTimeZone(TimeZone.getDefault());
	}

	@Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.length() != getPattern().length()) {
            throw new ConverterException("Invalid format");
        }

        return super.getAsObject(context, component, value);
    }
}
