package com.ceiba.usuario;

import java.text.SimpleDateFormat;

import javax.ejb.Stateless;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

@Stateless
public class UsuarioServiceRestClient {

	
	private static final String REST_URI 
    = "http://localhost:8090/adnarus/api/usuarios";

	private Client client = Client.create();
	
	private SimpleDateFormat formatoFecha;
	
	
	public void registrarUsuario(Usuario usuario) throws Exception {
		
		formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		
		String input = "{\"documentType\":\""+usuario.getTipoDocumento()+"\","
		+"\"documentNumber\":\""+usuario.getNumeroDocumento()+"\","
		+"\"firstName\":\""+usuario.getPrimerNombre()+"\","
		+"\"secondName\":\""+usuario.getSegundoNombre()+"\","
		+"\"surname\":\""+usuario.getPrimerApellido()+"\","
		+"\"secondSurname\":\""+usuario.getSegundoApellido()+"\","
		+"\"healthAdministrator\":\""+usuario.getAdministradoraSalud()+"\","
		+"\"healthAffiliationDate\":\""+formatoFecha.format(usuario.getFechaAfiliacionSalud())+"\","
		+"\"pensionAdministrator\":\""+usuario.getAdministradoraPension()+"\","
		+"\"pensionAffiliationDate\":\""+formatoFecha.format(usuario.getFechaAfiliacionPension())+"\"}";
		
		
		ClientResponse respuesta = client
				.resource(REST_URI)
				.type("application/json")
				.post(ClientResponse.class, input);


		if(respuesta.getStatus() != 200 )
			throw new Exception("Ocurrió un error al momento de crear el usuario en el servicio web");
	}
}
