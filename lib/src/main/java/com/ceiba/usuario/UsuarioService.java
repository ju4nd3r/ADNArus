package com.ceiba.usuario;

import javax.ejb.Stateless;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

@Stateless
public class UsuarioService {

	private String mensajeExcepcion;
	
	public void registrarUsuario( Usuario usuario ) throws Exception{
		this.mensajeExcepcion ="";
		
		
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		//Get the session named kseesion-rule that we defined in kmodule.xml above.
		//Also by default the session returned is always stateful. 
		KieSession kSession = kContainer.newKieSession("ksession-rule");
		
		kSession.setGlobal("usuarioService", this);
		
		kSession.insert(usuario);
		
		kSession.fireAllRules();
		
		if(!this.mensajeExcepcion.trim().equals("")) throw new Exception(this.mensajeExcepcion);
		
		
		String resultado = "Se crea el usuario identificado con: " + 
				usuario.getTipoDocumento() + ": " + 
				usuario.getNumeroDocumento();
		System.out.println(resultado);
	}
	
	
	public void agregarMensajeExcepcion(String mensaje) {
		this.mensajeExcepcion += mensaje + "\n";
	}
	
}
