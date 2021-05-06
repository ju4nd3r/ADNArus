package ADNArus;

import java.util.Arrays;
//import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class ListaDesplegableTipoDocumentoView {

	
	private List<TipoDocumento> tiposDocumentos;
	
	@PostConstruct
	public void init() {
		tiposDocumentos = Arrays.asList(TipoDocumento.values());
//		tiposDocumentos = new ArrayList<String>();
//		tiposDocumentos.add("CC");
//		tiposDocumentos.add("CE");
//		tiposDocumentos.add("TI");
//		
	}
	

	public List<TipoDocumento> getTiposDocumentos() {
		return tiposDocumentos;
	}

	public void setTiposDocumentos(List<TipoDocumento> tiposDocumentos) {
		this.tiposDocumentos = tiposDocumentos;
	}




}
