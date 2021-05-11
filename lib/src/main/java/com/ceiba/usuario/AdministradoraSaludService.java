package com.ceiba.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;

@ApplicationScoped
public class AdministradoraSaludService {
	
	private List< AdministradoraSalud > administradorasSalud;
	
	@PostConstruct
    public void init() {
		
		administradorasSalud = new ArrayList<AdministradoraSalud>();
		
		administradorasSalud.add(new AdministradoraSalud("EPS001","Sanitas EPS"));
		administradorasSalud.add(new AdministradoraSalud("EPS002","Sura EPS"));
		administradorasSalud.add(new AdministradoraSalud("EPS003","Coomeva"));
		administradorasSalud.add(new AdministradoraSalud("EPS004","Medimas"));
		
	}
	
	public AdministradoraSalud encontrarPorCodigo(String codigo) {
		
		for (AdministradoraSalud administradoraSalud : administradorasSalud) {
			if( administradoraSalud.getCodigo() == codigo )
				return administradoraSalud;
		}
		
		return null;
		
	}

	public List<AdministradoraSalud> getAdministradorasSalud() {
		return administradorasSalud;
	}

	public void setAdministradorasSalud(List<AdministradoraSalud> administradorasSalud) {
		this.administradorasSalud = administradorasSalud;
	}
	
	
}
