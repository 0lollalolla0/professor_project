package it.uniroma3.catering.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.catering.controller.validator.BuffetValidator;
import it.uniroma3.catering.model.Buffet;
import it.uniroma3.catering.model.Chef;
import it.uniroma3.catering.service.BuffetService;
import it.uniroma3.catering.service.ChefService;
import it.uniroma3.catering.service.DishService;

@Controller
public class AdminController {
		
	@Autowired
	private BuffetValidator bv;
	
	@Autowired
	private BuffetService bs;

	@Autowired
	private DishService ds;
	
	@Autowired
	private ChefService cs;
	
	@GetMapping("/login")
	public String showLoginForm (Model model) {
		return "loginForm.html";
	}
	
    @GetMapping("/default")
    public String defaultAfterLogin(Model model) {
    	model.addAttribute("buffets", bs.findAll());
        return "admin/home.html";
    }
    
    @GetMapping("/homead/{id}")
    public String getBuffet(@PathVariable("id") Long id, Model model) {
    	Buffet buffet = this.bs.findById(id);
    	model.addAttribute("buffet", buffet);
    	model.addAttribute("chef", buffet.getChef());
    	return "admin/buffet.html";
    }
    
    @GetMapping("/todeletebuffet/{id}")
    public String toDeleteBuffet(@PathVariable("id") Long id, Model model) {
    	this.bs.deleteById(id);
		model.addAttribute("buffets", this.bs.findAll());
    	return "admin/home.html";
    }
    
    @GetMapping("/addbuffet")
	public String showRegisterForm (Model model) {
		model.addAttribute("buffet", new Buffet());
		model.addAttribute("chef", new Chef());
		return "admin/buffetregister.html";
	}
    
    @PostMapping("/addbuffet")
    public String addBuffet(@Valid @ModelAttribute("buffet") Buffet buffet, BindingResult bbr, 
    		@Valid @ModelAttribute("chef") Chef chef, BindingResult cbr, Model model) {
    	if(!bbr.hasErrors() && !cbr.hasErrors()) {
    		chef.addBuffet(buffet);
    		this.cs.save(chef);
    		buffet.setChef(chef);
    		this.bs.save(buffet);
    		model.addAttribute("buffets", this.bs.findAll());
    		return "admin/home.html";
    	}
    	return "admin/buffetregister.html";
    }
    
    
    
    
    
    
    
    
}
