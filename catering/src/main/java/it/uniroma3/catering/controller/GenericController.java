package it.uniroma3.catering.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.catering.model.Buffet;
import it.uniroma3.catering.service.BuffetService;

@Controller
public class GenericController {
	
	@Autowired
	private BuffetService bs;
	
	@GetMapping("/home")
	public String visitHome(Model model) {
		List<Buffet> buffets = bs.findAll();
		model.addAttribute("buffets", buffets);
		return "home.html";
	}
	
	@GetMapping("/home/{id}")
	public String getBuffet(@PathVariable("id") Long id, Model model) {
		Buffet buffet = this.bs.findById(id);
		model.addAttribute("buffet", buffet);
		model.addAttribute("chef", buffet.getChef());
		return "buffet.html";
	}
}
