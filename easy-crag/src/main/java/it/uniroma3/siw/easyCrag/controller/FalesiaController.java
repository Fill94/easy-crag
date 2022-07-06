package it.uniroma3.siw.easyCrag.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.easyCrag.controller.validator.FalesiaValidator;
import it.uniroma3.siw.easyCrag.model.Falesia;
import it.uniroma3.siw.easyCrag.model.Regione;
import it.uniroma3.siw.easyCrag.model.Ripetizione;
import it.uniroma3.siw.easyCrag.model.Via;
import it.uniroma3.siw.easyCrag.services.FalesiaService;
import it.uniroma3.siw.easyCrag.services.RegioneService;
@Controller
public class FalesiaController {
	@Autowired
	private RegioneService regioneService;
	@Autowired
	private FalesiaService falesiaService;
	@Autowired
	private FalesiaValidator falesiaValidator;
	@PostMapping(value = "/falesiaForm/{id}/adminPage")
	public String addFalesia(@ModelAttribute("falesia")@Valid Falesia falesia, BindingResult bindingResult,@PathVariable("id") Long idRegione, Model model) {
		Regione regione = this.regioneService.findById(idRegione);
		List<Via> vie = new ArrayList<>();
		falesia.setRegione(regione); 
		falesia.setVie(vie);
		this.falesiaValidator.validate(falesia, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.falesiaService.save(falesia);
			List<Falesia> falesie = this.falesiaService.findByRegione(regione);
			model.addAttribute("regione", falesia.getRegione());
			model.addAttribute("falesie", falesie);
			return "admin/falesieAdmin.html";
		}
		model.addAttribute("regione", this.regioneService.findById(idRegione));
		return "admin/falesiaForm.html";
	}
	@GetMapping(value="/rimuoviFalesia/{idFalesia}/{idRegione}")
	public String rimuoviFalesia(@PathVariable("idFalesia") Long idFalesia, @PathVariable("idRegione") Long idRegione, Model model) {
		Regione regione = this.regioneService.findById(idRegione);
		this.falesiaService.removeFalesia(idFalesia);
		model.addAttribute("falesie", regione.getFalesie());
		model.addAttribute("regione", regione);
		return "admin/falesieAdmin.html";
	}
	@GetMapping(value="/falesiaForm/{id}")
	public String addFalesia(Model model, @PathVariable("id") Long idRegione) {
		Falesia nuovaFalesia = new Falesia();
		
		model.addAttribute("regione", this.regioneService.findById(idRegione));
		model.addAttribute("falesia", nuovaFalesia);
		return "admin/falesiaForm.html";
	}
	@GetMapping(value ="/falesia/{id}/admin")
	public String getFalesiaAdmin(@PathVariable("id") Long idFalesia, Model model) {
		Falesia falesia = this.falesiaService.findById(idFalesia);
		List<Via> vieFalesia = falesia.getVie();
		model.addAttribute("falesia", falesia);
		model.addAttribute("vie", vieFalesia);
		return "admin/falesiaAdmin.html";
	}
	@GetMapping(value ="/falesia/{id}")
	public String getFalesia(@PathVariable("id") Long idFalesia, Model model) {
		Falesia falesia = this.falesiaService.findById(idFalesia);
		List<Via> vieFalesia = falesia.getVie();
		model.addAttribute("falesia", falesia);
		model.addAttribute("vie", vieFalesia);
		return "falesia.html";
	}
	

}
