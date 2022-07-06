package it.uniroma3.siw.easyCrag.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.easyCrag.controller.validator.ripetizioneValidator;
import it.uniroma3.siw.easyCrag.model.Credentials;
import it.uniroma3.siw.easyCrag.model.Ripetizione;
import it.uniroma3.siw.easyCrag.model.Via;
import it.uniroma3.siw.easyCrag.services.CredentialsService;
import it.uniroma3.siw.easyCrag.services.RipetizioneService;
import it.uniroma3.siw.easyCrag.services.ViaService;

@Controller
public class RipetizioneController {
	@Autowired
	private CredentialsService credentialService;
	@Autowired
	private ViaService viaService;
	@Autowired
	private RipetizioneService ripetizioneService;
	@Autowired
	private ripetizioneValidator ripetizioneValidator;
	@PostMapping(value="/addRipetizione/{idVia}")
	public String addRipetizione(@ModelAttribute("ripetizione") @Valid Ripetizione ripetizione, BindingResult bindingResult, @RequestParam(name="votoSelezionato", required=false)  Float voto ,@PathVariable("idVia") Long viaId, Model model) {
		Via via = viaService.findById(viaId);
		if(SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")) {
			model.addAttribute("via", via);
			return "via.html";
		}
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = credentialService.getCredentials(userDetails.getUsername());
    	ripetizione.setScalatore(credentials.getUser());
    	ripetizione.setViaScalata(via);
		ripetizione.setVotoAssegnato(voto);
    	ripetizioneValidator.validate(ripetizione, bindingResult);
		if(!bindingResult.hasErrors()) {
    		ripetizioneService.save(ripetizione);
    		Float votoAggiornato = ripetizioneService.calcolaVotoMedio(via);
			via.setVotoMedio(votoAggiornato);
			viaService.save(via);
			model.addAttribute("via", via);
			return "via.html";
		}

		model.addAttribute("via", via);
		return "via.html";
	}
	@GetMapping(value = "/rimuoviRipetizione/{id}")
	public String rimuoviRipetizione(@PathVariable("id") Long idRipetizione, Model model) {
		Ripetizione ripetizione = ripetizioneService.findById(idRipetizione);
		Via via = ripetizione.getViaScalata();
		ripetizioneService.remove(idRipetizione);
		Float nuovoVoto =ripetizioneService.calcolaVotoMedio(via);
		via.setVotoMedio(nuovoVoto);
		viaService.save(via);
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = credentialService.getCredentials(userDetails.getUsername());
		model.addAttribute("ripetizioni", ripetizioneService.findByScalatore(credentials.getUser()));
		return "user/userPage.html";
	}
}
