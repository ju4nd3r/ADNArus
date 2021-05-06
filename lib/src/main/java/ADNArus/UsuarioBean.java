package ADNArus;

import java.util.Date;

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
	
	private String resultado;
	
	
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getPrimerNombre() {
		return primerNombre;
	}
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}
	public String getSegundoNombre() {
		return segundoNombre;
	}
	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}
	public String getPrimerApellido() {
		return primerApellido;
	}
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	public String getSegundoApellido() {
		return segundoApellido;
	}
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getAdministradoraSalud() {
		return administradoraSalud;
	}
	public void setAdministradoraSalud(String administradoraSalud) {
		this.administradoraSalud = administradoraSalud;
	}
	public Date getFechaAfiliacionSalud() {
		return fechaAfiliacionSalud;
	}
	public void setFechaAfiliacionSalud(Date fechaAfiliacionSalud) {
		this.fechaAfiliacionSalud = fechaAfiliacionSalud;
	}
	
	public String getAdministradoraPension() {
		return administradoraPension;
	}
	public void setAdministradoraPension(String administradoraPension) {
		this.administradoraPension = administradoraPension;
	}
	public Date getFechaAfiliacionPension() {
		return fechaAfiliacionPension;
	}
	public void setFechaAfiliacionPension(Date fechaAfiliacionPension) {
		this.fechaAfiliacionPension = fechaAfiliacionPension;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
	public void tipoDocumentoSeleccionadoListener(AjaxBehaviorEvent evento) {
		SelectOneMenu componente = (SelectOneMenu) evento.getComponent();
		this.tipoDocumento =  (TipoDocumento) componente.getValue();
	}
	
	public void registrarUsuario() {
		resultado = "Se crea el usuario identificado con: " + tipoDocumento + ": " + numeroDocumento;
		System.out.println(resultado);
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
		
		System.out.println("Validando el numero de documento "+ numeroDocumento +
				" dependiendo del Tipo "+ tipoDocumento + " nombre: " + primerNombre);
		
		//Si el numero de documento es cedula de ciudadania
		if(tipoDocumento == TipoDocumento.CC) {
			System.out.println("Validando la cedula "+ numeroDocumento);
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
		if(tipoDocumento == TipoDocumento.CE) {
			System.out.println("Validando la cedula de extrajeria "+ numeroDocumento);
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
		if(tipoDocumento == TipoDocumento.RC || tipoDocumento == TipoDocumento.TI) {
			System.out.println("Validando el RC o TI "+ numeroDocumento);
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
	

}
