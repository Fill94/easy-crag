package it.uniroma3.siw.easyCrag.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.easyCrag.controller.validator.RegioneValidator;
import it.uniroma3.siw.easyCrag.model.Falesia;
import it.uniroma3.siw.easyCrag.model.Regione;
import it.uniroma3.siw.easyCrag.services.RegioneService;

@Controller
public class RegioneController {
	@Autowired
	private RegioneService regioneService;
	@Autowired
	private RegioneValidator regioneValidator;
	@PostMapping(value = "/regioneForm/index")
	public String addRegione(@ModelAttribute("regione") Regione regione, BindingResult bindingResult, @RequestParam(name="regioneSelezionata") String nomeRegione, Model model) {
		regione.setNome(nomeRegione);
		regioneValidator.validate(regione, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.regioneService.saveRegione(regione);
			model.addAttribute("regioni",this.regioneService.findAll());
			return "admin/adminPage.html";
		}
		model.addAttribute("nomiRegioni", this.regioneService.getNomiRegioni());
		return "admin/regioneForm.html";
	}
	@GetMapping(value = "/regioneForm")
	public String addRegione(Model model) {
		model.addAttribute("regione", new Regione());
		List<String> nomiRegioni = this.regioneService.getNomiRegioni();
		model.addAttribute("nomiRegioni", nomiRegioni);
		return "admin/regioneForm.html";
	}
	@GetMapping(value="/admin/regione/{id}")
	public String getFalesieAdmin(Model model, @PathVariable("id") Long regioneId) {
		Regione regione = this.regioneService.findById(regioneId);
		List<Falesia> falesie = regione.getFalesie();
		model.addAttribute("falesie", falesie);
		model.addAttribute("regione", regione);
		return "admin/falesieAdmin.html";
	}
	@GetMapping(value = "/regione/{id}")
	public String getFalesie(Model model, @PathVariable("id") Long regioneId) {
		Regione regione = this.regioneService.findById(regioneId);
		List<Falesia> falesie = regione.getFalesie();
		model.addAttribute("falesie", falesie);
		return "falesie.html";
	}
	@GetMapping(value = "/removeRegione/{id}")
	public String removeRegione(@PathVariable("id") Long idRegione, Model model) {
		this.regioneService.remove(idRegione);
		
		model.addAttribute("regioni", this.regioneService.findAll());
		return"admin/adminPage.html";
	}
	
}
