package it.uniroma3.catering.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
    @GetMapping("/admin/home")
    public String defaultAfterLogin(Model model) {
    	model.addAttribute("buffets", bs.findAll());
        return "admin/home.html";
    }
    
    @GetMapping("/admin/addbuffet")
	public String showRegisterForm (Model model) {
		model.addAttribute("buffet", new Buffet());
		return "buffetregister.html";
	}
    
//    @GetMapping("/admin/{id}")
//    public String getBuffet(@PathVariable("id") Long id, Model model) {
//    	Buffet buffet = this.bs.findById(id);
//    	model.addAttribute("name", buffet.getName());
//    	model.addAttribute("chef", buffet.getChef());
//    	model.addAttribute("description", buffet.getDescription());
//    	model.addAttribute("dishes", buffet.getDishes());
//    	return "admin/buffet.html";
//    }
    
    
//    @PostMapping("/admin/{id}/remove")
//    public String RemoveBuffet(@PathVariable("id") Long id, Model model) {
//    	bs.deleteById(id);
//    	return "admin/home.html";
//    }
    
//    @PostMapping("/admin/{id}/remove")
//    public String RemoveDish(@PathVariable("id") Long id, Model model) {
//    	bs.deleteById(id);
//    	return "admin/home.html";
//    }
    
//    @GetMapping("/admin/{id}")
//    public String getDish(@PathVariable("id") Long id, Model model) {
//    	Dish dish = this.ds.findById(id);
//    	model.addAttribute("ingredients", dish.getIngredients());
//    	return "admin/buffet.html";
//    }
}
