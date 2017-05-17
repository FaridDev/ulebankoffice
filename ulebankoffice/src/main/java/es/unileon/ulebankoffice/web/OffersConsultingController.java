package es.unileon.ulebankoffice.web;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.datastore.Entity;

import es.unileon.ulebankoffice.domain.AdvisorUserDomain;
import es.unileon.ulebankoffice.domain.Authenticator;
import es.unileon.ulebankoffice.domain.Datastore;
import es.unileon.ulebankoffice.domain.QuestionInfo;
import es.unileon.ulebankoffice.domain.SolicitudFinancialAdvisorDomain;
import es.unileon.ulebankoffice.repository.AdvisorUserRepository;
import es.unileon.ulebankoffice.repository.SolicitudesFinancialAdvisorRepository;

@Controller
public class OffersConsultingController {
	
	@Autowired
	private SolicitudesFinancialAdvisorRepository repo;

	@RequestMapping(value= "/consuLogin", method = RequestMethod.GET) 
	public String getLogin(){
		return "offersConsultingLogin";
	}

	@RequestMapping(value = "/offersconsulting", method = RequestMethod.GET)
	public String add(ModelMap model, HttpServletRequest req, HttpServletResponse resp) throws IOException {

		
		
			List<SolicitudFinancialAdvisorDomain> lists = repo.findByEmail(req.getUserPrincipal().getName());
			model.addAttribute("lists", lists);
						
			model.addAttribute("nombre", req.getUserPrincipal().getName());
		

			return "offersconsulting";
		}

		

	
}
