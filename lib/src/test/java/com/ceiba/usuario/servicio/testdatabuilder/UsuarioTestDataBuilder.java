package com.ceiba.usuario.servicio.testdatabuilder;

import java.util.Date;

import com.ceiba.usuario.TipoDocumento;
import com.ceiba.usuario.Usuario;

public class UsuarioTestDataBuilder {

	
	private TipoDocumento tipoDocumento;
	private String numeroDocumento;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private String administradoraSalud;
	private Date fechaAfiliacionSalud;
	private String administradoraPension;
	private Date fechaAfiliacionPension;
	 
	 



    public UsuarioTestDataBuilder() {
        //nombreUsuario = "1234";

    }

    public UsuarioTestDataBuilder conTipodocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
        return this;
    }
    
    public UsuarioTestDataBuilder conNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
        return this;
    }
    
    public UsuarioTestDataBuilder conPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
        return this;
    }
    
    public UsuarioTestDataBuilder conSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
        return this;
    }

    public UsuarioTestDataBuilder conPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
        return this;
    }
    
    public UsuarioTestDataBuilder conSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
        return this;
    }
    
    public UsuarioTestDataBuilder conAdministradoraSalud(String administradoraSalud) {
        this.administradoraSalud = administradoraSalud;
        return this;
    }
    
    public UsuarioTestDataBuilder conFechaAfiliacionSalud(Date fechaAfiliacionSalud) {
        this.fechaAfiliacionSalud = fechaAfiliacionSalud;
        return this;
    }
    
    public UsuarioTestDataBuilder conAdministradoraPension(String administradoraPension) {
        this.administradoraPension = administradoraPension;
        return this;
    }
    
    public UsuarioTestDataBuilder conFechaAfiliacionPension(Date fechaAfiliacionPension) {
        this.fechaAfiliacionPension = fechaAfiliacionPension;
        return this;
    }
    
    public Usuario build() {
    	Usuario usuario = new Usuario();
    	usuario.setTipoDocumento(this.tipoDocumento);
    	usuario.setNumeroDocumento(this.numeroDocumento);
    	usuario.setPrimerNombre(this.primerNombre);
    	usuario.setSegundoNombre(this.segundoNombre);
    	usuario.setPrimerApellido(this.primerApellido);
    	usuario.setSegundoApellido(this.segundoApellido);
    	usuario.setAdministradoraSalud(this.administradoraSalud);
    	usuario.setFechaAfiliacionSalud(this.fechaAfiliacionSalud);
    	usuario.setAdministradoraPension(this.administradoraPension);
    	usuario.setFechaAfiliacionPension(this.fechaAfiliacionPension);
        return new Usuario();
    }
}
