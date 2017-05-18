package es.unileon.ulebankoffice.web;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

import es.unileon.ulebankoffice.domain.Authenticator;
import es.unileon.ulebankoffice.domain.Datastore;
import es.unileon.ulebankoffice.domain.Question;
import es.unileon.ulebankoffice.domain.SolicitudFinancialAdvisorDomain;
import es.unileon.ulebankoffice.repository.SolicitudesFinancialAdvisorRepository;

@Controller
public class QueryPageController {
	
	@Autowired
	private SolicitudesFinancialAdvisorRepository repo;
		
	@RequestMapping(value = "/offersconsulting/querypage", method = RequestMethod.GET, params = {"id"})
    public String add(ModelMap model, HttpServletRequest req, HttpServletResponse resp, Principal principal, @RequestParam("id") String idSolicitud) throws IOException {
        
		SolicitudFinancialAdvisorDomain solicitud = repo.findOne(idSolicitud);
		model.addAttribute("idQuery", solicitud.getId());
		model.addAttribute("enlaceArchivo", "/offersconsulting/serve?blob-key="+solicitud.getFileBlobKey());
		model.addAttribute("textoOferta", solicitud.getTextoOferta());
		model.addAttribute("urlOferta", solicitud.getUrlOferta());
		
        	//TODO comprobar que la persona que está intentando acceder es el dueño de la consulta o bien un empleado, supervisor, admin
        	
        	
        	return "querypage";
        	
        
		
    }

}
