package it.uniroma3.catering.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.catering.controller.validator.BuffetValidator;
import it.uniroma3.catering.model.Buffet;
import it.uniroma3.catering.service.BuffetService;
import it.uniroma3.catering.service.DishService;

@Controller
public class AdminController {
		
	@Autowired
	private BuffetValidator bv;
	
	@Autowired
	private BuffetService bs;

	@Autowired
	private DishService ds;
	
	@GetMapping("/login")
	public String showLoginForm (Model model) {
		return "loginForm.html";
	}
	
    @GetMapping("/default")
    public String defaultAfterLogin(Model model) {
    	model.addAttribute("buffets", bs.findAll());
        return "admin/home.html";
    }
    
    @GetMapping("/admin/home/{id}")
    public String getBuffet(@PathVariable("id") Long id, Model model) {
    	Buffet buffet = this.bs.findById(id);
    	model.addAttribute(buffet);
    	return "admin/buffet.html";
    }
    
    @PostMapping("/admin/remove/{id}")
    public String remodeBuffet(@PathVariable("id") Long id, Model model) {
    	Buffet buffet = this.bs.findById(id);
    	this.bs.deleteById(id);
    	model.addAttribute("buffets", bs.findAll());
    	return "admin/home.html";
    }
    
    @GetMapping("/admin/addbuffet")
	public String showRegisterForm (Model model) {
		model.addAttribute("buffet", new Buffet());
		return "admin/buffetregister.html";
	}
}
