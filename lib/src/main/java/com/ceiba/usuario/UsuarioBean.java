package com.ceiba.usuario;

import java.text.ParseException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.validator.ValidatorException;

import org.primefaces.component.selectonemenu.SelectOneMenu;


@ManagedBean
@SessionScoped 
public class UsuarioBean {

	private static final String SOLO_SE_PERMITEN_LETRAS = "Solo se permiten letras";
	private static final String SOLO_SE_PERMITEN_NUMEROS_Y_MAXIMO_10_CARACTERES = "Solo se permiten numeros y m�ximo 10 caracteres";
	private static final String SOLO_SE_PERMITEN_NUMEROS_LETRAS_Y_MAXIMO_14_CARACTERES = "Solo se permiten numeros, letras y maximo 14 caracteres";
	private static final String SOLO_SE_PERMITEN_NUMEROS = "Solo se permiten numeros";
	private static final String EL_REGISTRO_HA_SIDO_EXITOSO = "El registro ha sido exitoso";
	
	
	
	@EJB
	private UsuarioService usuarioService;
	
	private Usuario usuario;
		
	private String resultado;
	
	@PostConstruct
	public void init() {
		this.usuario = new Usuario();
		
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public void tipoDocumentoSeleccionadoListener(AjaxBehaviorEvent evento) {
		SelectOneMenu componente = (SelectOneMenu) evento.getComponent();
		this.usuario.setTipoDocumento ( (TipoDocumento) componente.getValue() );
	}
	
	
	public void registrarUsuario() {
		this.resultado = "";
		
		try {

			
			usuarioService.registrarUsuario( this.usuario );
			
			agregarMensajeInformativoVista(EL_REGISTRO_HA_SIDO_EXITOSO);
		
		} catch (ParseException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			mostrarMensajeErrorVista(e.getMessage());
		}

	}
	
	
	public void validarNombre(FacesContext context, UIComponent component, Object valor)  
			throws ValidatorException{
		String nombre = (String) valor;
		nombre = nombre.replaceAll("\s","" );
		for (int i = 0; i < nombre.length(); i++) {
			if( !Character.isLetter( nombre.charAt( i ) ) ) {
//				context.addMessage(component.getClientId(context), 
//						new FacesMessage(FacesMessage.SEVERITY_ERROR,SOLO_SE_PERMITEN_LETRAS, null));
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,SOLO_SE_PERMITEN_LETRAS, null));
			}
		}
	}
		
	public void validarNumeroDocumento(FacesContext context, UIComponent component, Object valor)  
			throws ValidatorException{
		String numeroDocumento = (String) valor;
		
		//Si el numero de documento es cedula de ciudadania
		if(usuario.getTipoDocumento() == TipoDocumento.CC) {
			if(numeroDocumento.length() > 10) {
				 
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
								SOLO_SE_PERMITEN_NUMEROS_Y_MAXIMO_10_CARACTERES, 
								null));
			}
			
			for (int i = 0; i < numeroDocumento.length(); i++) {
				if( !Character.isDigit( numeroDocumento.charAt( i ) ) ) {
					throw new ValidatorException( 
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									SOLO_SE_PERMITEN_NUMEROS_Y_MAXIMO_10_CARACTERES, 
									null));
				}
			}
				
		}
		
		//Si el numero de documento es cedula de extranjeria
		if(usuario.getTipoDocumento() == TipoDocumento.CE) {
			if(numeroDocumento.length() > 14) {
				throw new ValidatorException( 
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								SOLO_SE_PERMITEN_NUMEROS_LETRAS_Y_MAXIMO_14_CARACTERES, 
								null));
			}
			
			for (int i = 0; i < numeroDocumento.length(); i++) {
				if( !Character.isDigit( numeroDocumento.charAt( i ) ) &&
						!Character.isLetter( numeroDocumento.charAt( i ) )) {
					throw new ValidatorException( 
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									SOLO_SE_PERMITEN_NUMEROS_LETRAS_Y_MAXIMO_14_CARACTERES, 
									null));
				}
			}
		}
		
		//Si el numero de documento es Registro Civil o tarjeta de identidad
		if(usuario.getTipoDocumento() == TipoDocumento.RC || 
				usuario.getTipoDocumento() == TipoDocumento.TI) {
			
			for (int i = 0; i < numeroDocumento.length(); i++) {
				if( !Character.isDigit( numeroDocumento.charAt( i ) ) ) {
					throw new ValidatorException( 
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									SOLO_SE_PERMITEN_NUMEROS, 
									null));
				}
			}
		}
		
		
		
	}
	

	
	public void agregarMensajeErrorVista(String mensaje) {
		this.resultado += mensaje + "\n";
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage( null , new FacesMessage(FacesMessage.SEVERITY_ERROR,mensaje, null));
	}
	
	public void agregarMensajeInformativoVista(String mensaje) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage( null , new FacesMessage(FacesMessage.SEVERITY_INFO,mensaje, null));
	}
	
	public void mostrarMensajeErrorVista(String mensajeExcepcion) {
		String[] mensajesExcepcion = mensajeExcepcion.split("\n");
		
		for (int i = 0; i < mensajesExcepcion.length; i++) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage( null , new FacesMessage(FacesMessage.SEVERITY_ERROR,mensajesExcepcion[i], null));
		}
	}
	

}
