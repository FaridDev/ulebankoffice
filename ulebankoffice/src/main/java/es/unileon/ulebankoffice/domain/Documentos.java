
package es.unileon.ulebankoffice.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.unileon.ulebankoffice.repository.DocumentoRepository;

/**
 * Clase agregada de DocumentoAdjuntoDomain. Se encarga de encapsular el
 * comportamiento del array de los documentos ya que es un elemento común a los
 * Clientes, los ProductosFunaniceros y las Solicitudes. De esta forma, todas
 * las clases anteriormente mencionadas delegan su manejor del array de
 * documentos a una instancia de esta clase.
 * 
 * @author Razvan Raducu
 *
 */
public class Documentos {

	@Autowired
	DocumentoRepository repo;

	private List<DocumentoAdjuntoDomain> documentos;

	public void addDocumento(DocumentoAdjuntoDomain documento) {
		//this.documentos.add(documento);
		repo.save(documento);
		System.out.println("Me han invocado, lol");
	}

	public List<DocumentoAdjuntoDomain> getDocumentos(List<String> idDocumentos) {
		
		List<DocumentoAdjuntoDomain> listaDocumentos = new ArrayList<>();
		
		for (String idDoc : idDocumentos) {

			listaDocumentos.add(repo.findById(idDoc));
		
			}
		return listaDocumentos;
	}

	@Override
	public String toString() {
		return "Documentos [documentos=" + documentos + "]";
	}
	
	

}
