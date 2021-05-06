package ADNArus;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
//import javax.faces.convert.FacesConverter;
//import javax.inject.Inject;

//@FacesConverter
public class AdministradoraSaludConverter implements Converter{

	
	private AdministradoraSaludService administradoraSaludService = new AdministradoraSaludService();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		// TODO Auto-generated method stub
		if (value == null || value.isEmpty()) {
            return null;
        }
		
		//Object obj = administradoraSaludService.encontrarPorCodigo(value);
		
		return new Object();
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		// TODO Auto-generated method stub
		if (!(value instanceof AdministradoraSalud)) {
            return null;
        }
		return ((AdministradoraSalud) value).getCodigo();
	}

}
