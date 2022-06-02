package it.uniroma3.catering.start;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import it.uniroma3.catering.model.Buffet;
import it.uniroma3.catering.model.Chef;
import it.uniroma3.catering.model.Credentials;
import it.uniroma3.catering.model.Dish;
import it.uniroma3.catering.model.Ingredient;
import it.uniroma3.catering.service.BuffetService;
import it.uniroma3.catering.service.ChefService;
import it.uniroma3.catering.service.CredentialsService;
import it.uniroma3.catering.service.DishService;
import it.uniroma3.catering.service.IngredientService;

@Component
public class Start implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
    private CredentialsService credentialService;
    
    @Autowired
    DishService dishService;
    
    @Autowired
    private ChefService chefService;
    
    @Autowired
    private BuffetService buffetService;
    
    @Autowired
    private IngredientService ingredientService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    	
    	//QUESTE SONO LE CREDENZIALI
    	credentialService.saveCredentials(new Credentials("test1@test.com","p"));
    	credentialService.saveCredentials(new Credentials("test2@test.com","p"));
    	credentialService.saveCredentials(new Credentials("test3@test.com","p"));
    	credentialService.saveCredentials(new Credentials("loryc","ciaociao"));
    	
    	//QUESTI SONO GLI INGREDIENTI
    	Ingredient i1 = new Ingredient("pane", "Italia", "fatto con farina e lievito");
    	Ingredient i2 = new Ingredient("nutella", "Italia", "fatta con nocciole");
    	Ingredient i3 = new Ingredient("pomodori", "Italia", "coltivati con amore");
    	Ingredient i4 = new Ingredient("baguette", "Francia", "fatta con farina integrale");
    	Ingredient i5 = new Ingredient("ceci", "Spagna", "coltivati con amore");

    	
    	//SALVO GLI INGREDIENTI DEFINITI SOPRA
    	ingredientService.save(i1);
    	ingredientService.save(i2);
    	ingredientService.save(i3);
    	ingredientService.save(i4);
    	ingredientService.save(i5);

    	
    	//COMPONGO GLI INGREDIENTI DEL PRIMO PIATTO
    	List<Ingredient> FirstDish = new ArrayList<Ingredient>();
    	FirstDish.add(i1);
    	FirstDish.add(i2);
    	
    	//COMPONGO GLI INGREDIENTI DEL SECONDO PIATTO
    	List<Ingredient> SecondDish = new ArrayList<Ingredient>();
    	SecondDish.add(i3);
    	SecondDish.add(i4);
    	SecondDish.add(i5);
    	
    	//CREO I PIATTI CON LE LISTE
    	Dish p1 = new Dish("pane e nutella", "pane di farina 000, e nutella dell'euro spin" , FirstDish);
    	dishService.save(p1);
    	
    	Dish p2 = new Dish("Panino integrale pomodori e ceci", "per chi vuole rimanere a dieta" , SecondDish);
    	dishService.save(p2);
    	
    	//CREO UNA LISTA DI PIATTI E CI AGGIUNGO IL PIATTO APPENA CREATO
    	List<Dish> piatti = new ArrayList<Dish>();
    	piatti.add(p1);
    	piatti.add(p2);
    	
    	//CREO UNO CHEF E LO SALVO
    	Chef chefMR= new Chef("Mario", "Rossi", "Italian");
    	chefService.save(chefMR);
    	
    	//CREO UN BUFFET, CI METTO I PIATTI E LO SALVO
    	Buffet buffet1 = new Buffet("Buffet 1", "molto buono", chefMR);
    	buffet1.setDishes(piatti);
    	
    	buffetService.save(buffet1);
    }

}
