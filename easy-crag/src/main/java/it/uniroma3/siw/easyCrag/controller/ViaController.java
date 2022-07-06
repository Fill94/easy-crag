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
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.easyCrag.controller.validator.ViaValidator;
import it.uniroma3.siw.easyCrag.model.Falesia;
import it.uniroma3.siw.easyCrag.model.Ripetizione;
import it.uniroma3.siw.easyCrag.model.Via;
import it.uniroma3.siw.easyCrag.services.FalesiaService;
import it.uniroma3.siw.easyCrag.services.ViaService;

@Controller
public class ViaController {
	@Autowired
	private ViaService viaService;
	@Autowired
	private FalesiaService falesiaService;
	@Autowired
	private ViaValidator viaValidator;
	
	@PostMapping(value = "viaForm/{id}/falesiaAdmin")
	public String addVia(@ModelAttribute("via") @Valid Via via, BindingResult bindingResult,@PathVariable("id") Long idFalesia, @RequestParam(name = "votoMedio", required = false) Float votoMedio, @RequestParam(name = "difficoltaVia", required = false) Integer difficolta ,Model model) {
		Falesia falesia = falesiaService.findById(idFalesia);
		List<Ripetizione> ripetizioni = new ArrayList<Ripetizione>();
		via.setFalesia(falesia);
		via.setRipetizioni(ripetizioni);
		via.setVotoMedio(votoMedio);
		via.setDifficolta(difficolta);
		viaValidator.validate(via, bindingResult);
		if(!bindingResult.hasErrors()) {
			viaService.save(via);
			model.addAttribute("falesia", falesia);
			model.addAttribute("vie", falesia.getVie());
			return "admin/falesiaAdmin.html";
		}
		model.addAttribute("falesia", falesiaService.findById(idFalesia));
		return  "admin/viaForm.html";
	}
	@GetMapping(value = "/viaForm/{id}")
	public String addVia(@PathVariable("id") Long idFalesia, Model model) {
		model.addAttribute("falesia", falesiaService.findById(idFalesia));
		model.addAttribute("via", new Via());
		return "admin/viaForm.html";
	}
	@GetMapping(value ="via/{id}")
	public String getVia(@PathVariable("id") Long idVia, Model model) {
		model.addAttribute("via", viaService.findById(idVia));
		model.addAttribute("ripetizione",new Ripetizione());
		return "via.html";
	}
	@GetMapping(value ="/rimuoviVia/{idVia}/{idFalesia}")
	public String rimuoviVia(@PathVariable("idVia") Long idVia, @PathVariable("idFalesia") Long idFalesia, Model model) {
		Falesia falesia = this.falesiaService.findById(idFalesia);
		this.viaService.remove(idVia);
		model.addAttribute("falesia", falesia);
		model.addAttribute("vie", falesia.getVie());
		return "admin/falesiaAdmin.html";
	}
}
