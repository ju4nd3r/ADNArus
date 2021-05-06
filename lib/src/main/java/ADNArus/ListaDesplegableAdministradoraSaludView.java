package ADNArus;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class ListaDesplegableAdministradoraSaludView {

	private Map<String, String> administradorasSalud; 
	
//	@EJB
//	@Inject
//	private AdministradoraSaludService administradoraSaludService;
//	
	@PostConstruct
	public void init() {
		administradorasSalud = new HashMap<String, String>();
		
		administradorasSalud.put("EPS001","Sanitas EPS");
		administradorasSalud.put("EPS002","Sura EPS");
		administradorasSalud.put("EPS003","Coomeva");
		administradorasSalud.put("EPS004","Medimas");
		
		
	}

	public Map<String, String> getAdministradorasSalud() {
		return administradorasSalud;
	}

	public void setAdministradorasSalud(Map<String, String> administradorasSalud) {
		this.administradorasSalud = administradorasSalud;
	}

	
	
	
}
