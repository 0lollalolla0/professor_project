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
import it.uniroma3.catering.controller.validator.DishValidator;
import it.uniroma3.catering.model.Buffet;
import it.uniroma3.catering.model.Chef;
import it.uniroma3.catering.model.Dish;
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

	@Autowired
	private DishValidator dv;
	
	private Buffet currentBuffet;
	
	@GetMapping("/login")
	public String showLoginForm (Model model) {
		return "loginForm.html";
	}
	
	@GetMapping("/logout")
	public String returnIndex(Model model) {
		return "index.html";
	}
	
    @GetMapping("/default")
    public String defaultAfterLogin(Model model) {
    	model.addAttribute("buffets", bs.findAll());
        return "admin/home.html";
    }
    
    @GetMapping("/homead/{id}")
    public String getBuffet(@PathVariable("id") Long id, Model model) {
    	Buffet buffet = this.bs.findById(id);
    	this.currentBuffet = buffet;
    	model.addAttribute("buffet", buffet);
    	model.addAttribute("chef", buffet.getChef());
    	return "admin/buffet.html";
    }
    
    @GetMapping("/chefad/{id}")
    public String getChef(@PathVariable("id") Long id, Model model) {
    	Chef chef = this.cs.findById(id);
    	model.addAttribute("chef", chef);
    	return "admin/chef.html";
    }
    
    @GetMapping("/todeletebuffet/{id}")
    public String toDeleteBuffet(@PathVariable("id") Long id, Model model) {
    	this.bs.deleteById(id);
		model.addAttribute("buffets", this.bs.findAll());
    	return "admin/home.html";
    }
    
    @GetMapping("/todeletedish/{id}")
    public String toDeleteDish(@PathVariable("id") Long id, Model model) {
    	this.currentBuffet.removeDish(this.ds.findById(id));
    	this.bs.save(currentBuffet);
    	this.ds.deleteById(id);
		model.addAttribute("buffet", this.currentBuffet);
		model.addAttribute("chef", this.currentBuffet.getChef());
    	return "admin/buffet.html";
    }
    
    @GetMapping("/addbuffet")
	public String showBuffetForm (Model model) {
		model.addAttribute("buffet", new Buffet());
		model.addAttribute("chef", new Chef());
		return "admin/buffetregister.html";
	}
    
    @PostMapping("/addbuffet")
    public String addBuffet(@Valid @ModelAttribute("buffet") Buffet buffet, BindingResult bbr, 
    		@Valid @ModelAttribute("chef") Chef chef, BindingResult cbr, Model model) {
    	this.bv.validate(buffet, bbr);
    	if(!bbr.hasErrors()) {
    		if(this.cs.existsByFirstNameAndLastName(chef.getFirstName(), chef.getLastName())) {
    			chef = this.cs.findByFirstNameAndLastName(chef.getFirstName(), chef.getLastName());
    		}
    		   chef.addBuffets(buffet);
    		   this.cs.save(chef);
    		   buffet.setChef(chef);
    		   this.bs.save(buffet);
    		   model.addAttribute("buffets", this.bs.findAll());
    		return "admin/home.html";
    	}
    	return "admin/buffetregister.html";
    }
    
    @GetMapping("/adddishto/{id}")
    public String showDishForm(@PathVariable("id") Long id, Model model) {
    	this.currentBuffet = this.bs.findById(id);
    	model.addAttribute("dish", new Dish());
    	return "admin/dishregister.html";
    }
    
    @PostMapping("/adddish")
    public String adddish(@Valid @ModelAttribute("dish") Dish dish, BindingResult br, Model model) {
    	this.dv.validate(dish, br);
    	if(!br.hasErrors()) {
    		this.ds.save(dish);
    		this.currentBuffet.addDish(dish);
    		this.bs.save(currentBuffet);
    		model.addAttribute("buffet", currentBuffet);
    		model.addAttribute("chef", currentBuffet.getChef());
    		return "admin/buffet.html";
    	}
    	return "admin/dishregister.html";
    }
}