package ADNArus;

import javax.ejb.Stateless;

@Stateless
public class UsuarioService {

	
	public void registrarUsuario( Usuario usuario  ) {
		String resultado = "Se crea el usuario identificado con: " + 
	usuario.getTipoDocumento() + ": " + 
				usuario.getNumeroDocumento();
		System.out.println(resultado);
	}
}
