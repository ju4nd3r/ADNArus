package com.ceiba.usuario.servicio;


import java.text.ParseException;


import org.apache.commons.lang3.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;

import com.ceiba.usuario.TipoDocumento;
import com.ceiba.usuario.Usuario;
import com.ceiba.usuario.UsuarioService;
import com.ceiba.usuario.servicio.testdatabuilder.UsuarioTestDataBuilder;

public class ServicioCrearUsuarioTest {

	
	/**
	 * Se valida Criterio 1:
		Cuando
		Se registre una persona indicando la administradora de pensión AFP003 Colpensiones y la fecha de afiliación es menor a 2021/01/01
		Espero
		Que no se permite el registro, y se muestre en la vista el siguiente mensaje “Para la administradora de pensión AFP003 solo es posible afiliarse después de  2020/12/31”.
	
	 */
	@Test
	public void validarCriterio1() {
		
		try {
			
			// arrange
			Usuario usuario = new UsuarioTestDataBuilder( ).
					conAdministradoraPension( "AFP003" ).
					conFechaAfiliacionPension( DateUtils.parseDate("30/11/2020","dd/MM/yyyy") ).build( );
			UsuarioService usuarioService = new UsuarioService();
			
			// act
			usuarioService.registrarUsuario(usuario);
		} catch (ParseException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			
			// assert
			Assert.assertTrue(e.getMessage().equals("Para la administradora de pensión AFP003 solo es posible afiliarse después de 2020/12/31"));
			
		}

	}
	
	/**
	 * Se valida Criterio 2
		Cuando
		Se registre una persona con tipo de documento RC Registro civil, y con las administradoras de EPS003 Coomeva ó EPS004 Medimas
		Espero
		Que no se permite el registro, y se muestre en la vista el siguiente mensaje “Para una persona con tipo de documento RC Registro civil no es permitido afiliarse a las EPS: EPS003, EPS004”.

	 */
	@Test
	public void validarCriterio2() {
		try {
			
			// arrange
			Usuario usuario = new UsuarioTestDataBuilder( ).
					conTipodocumento(TipoDocumento.RC).
					conAdministradoraSalud("EPS003").build( );
			UsuarioService usuarioService = new UsuarioService();
			
			// act
			usuarioService.registrarUsuario(usuario);
		} catch (ParseException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			
			// assert
			Assert.assertTrue(e.getMessage().equals("Para una persona con tipo de documento RC Registro civil no es permitido afiliarse a las EPS: EPS003, EPS004"));
			
		}
	}
	
	/**
	 * Se valida Criterio 3
		Cuando
		Se registre una persona con la administradora de salud EPS002  Sura EPS y la administradora de pensión es diferente de AFP001 Protección
		Espero
		Que no se permite el registro, y se muestre en la vista el siguiente mensaje “Las personas que se afilien a la administradora EPS002 Sura EPS solo pueden afiliarse a la administradora de pensión AFP001 Protección”.

	 */
	@Test
	public void validarCriterio3() {
		try {
			
			// arrange
			Usuario usuario = new UsuarioTestDataBuilder( )
					.conAdministradoraSalud( "EPS002" )
					.conAdministradoraPension("AFP003")
					.build( );
			
			UsuarioService usuarioService = new UsuarioService();
			
			// act
			usuarioService.registrarUsuario(usuario);
		} catch (ParseException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			
			// assert
			Assert.assertTrue(e.getMessage().equals("Las personas que se afilien a la administradora EPS002 Sura EPS solo pueden afiliarse a la administradora de pensión AFP001 Protección"));
			
		}
	}
}
