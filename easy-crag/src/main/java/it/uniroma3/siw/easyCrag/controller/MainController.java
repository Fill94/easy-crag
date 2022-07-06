package it.uniroma3.siw.easyCrag.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.easyCrag.model.RegioneEnum;
import it.uniroma3.siw.easyCrag.services.RegioneService;

@Controller
public class MainController {
	@Autowired
	public RegioneService regioneService;
	@GetMapping(value = "/index")
	public String toMainPage(Model model) {
		model.addAttribute("regioni",this.regioneService.findAll());
		return "index.html";
	}
	@GetMapping(value = "")
	public String MainPage(Model model) {
		model.addAttribute("regioni",this.regioneService.findAll());
		return "index.html";
	}
}
