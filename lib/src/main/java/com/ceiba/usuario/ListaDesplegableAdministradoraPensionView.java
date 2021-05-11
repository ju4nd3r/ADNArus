package com.ceiba.usuario;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class ListaDesplegableAdministradoraPensionView {

	private Map<String, String> administradorasPension; 
	
	@PostConstruct
	public void init() {
		administradorasPension = new HashMap<String , String>();
		
		administradorasPension.put("AFP001", "Protecci�n");
		administradorasPension.put("AFP002", "Porvenir");
		administradorasPension.put("AFP003", "Colpensiones");
		
	}

	public Map<String, String> getAdministradorasPension() {
		return administradorasPension;
	}

	public void setAdministradorasPension(Map<String, String> administradorasPension) {
		this.administradorasPension = administradorasPension;
	}


	
}
