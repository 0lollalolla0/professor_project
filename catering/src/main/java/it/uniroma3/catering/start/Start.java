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
    	
    	
//    	//QUESTI SONO GLI INGREDIENTI
//    	ingridientService.saveIngridient(new Ingridient("Pomodoro", "Italia", "San Marzano DOC"));
//    	
//    	//QUESTI SONO I PIATTI
//    	plateService.savePlate(new Plate( "pasta al sugo",
//    			" pachini gialli, eco"));
//    	plateService.savePlate(new Plate("pasta al pesto",
//    			"pasta al pesto"));
//    	
//    	
//    	
//    	chefService.saveChef(new Chef("pippo", "messicano", "sa cucinare"));
//    	
//    	
//    	buffetService.saveBuffet(new Buffet("Buffet Pesce 1", "tutto a base di pesce"));
    	
    	//DEFINISCO TUTTI GLI INGREDIENTI
    	Ingredient i1 = new Ingredient("pane", "guatemala", "cagotto assicurato");
    	Ingredient i2 = new Ingredient("nutella", "italia", "fatta con nocciole");
    	Ingredient i3 = new Ingredient("pomodori", "italia", "fatta con nocciole");
    	Ingredient i4 = new Ingredient("manzo", "guatemala", "cagotto assicurato");
    	
    	//SALVO GLI INGREDIENTI NEL DB
    	ingredientService.save(i1);
    	ingredientService.save(i2);
    	ingredientService.save(i3);
    	ingredientService.save(i4);
    	
    	//METTO I PRIMI DUE IN UNA LISTA
    	List<Ingredient> listaIngredienti1 = new ArrayList<Ingredient>();
    	listaIngredienti1.add(i1);
    	listaIngredienti1.add(i2);
    	
    	//cREO IL PIATTO 1 CON QUELLA LISTA
    	Dish p1 = new Dish("pane e nutella", "pane di farina 000, e nutella dell'euro spin" , listaIngredienti1);
    	dishService.save(p1);
    	
    	//CREO UNA LISTA DI PIATTI E CI AGGIUNGO IL PIATTO APPENA CREATO
    	List<Dish> piatti = new ArrayList<Dish>();
    	piatti.add(p1);
    	
    	//CREO UNO CHEF E LO SALVO
    	Chef chefMR= new Chef("Mario", "Rossi", "San Basilio");
    	chefService.save(chefMR);
    	
    	//CREO UN BUFFET, CI METTO I PIATTI E LO SALVO
    	Buffet buffet1 = new Buffet("Buffet Carne 1", "molto buono", chefMR);
    	buffet1.setDishes(piatti);
    	
    	buffetService.save(buffet1);
    	
//    	//CREO LA SECONDA LISTA DI INGREDIENTI
//    	List<Ingredient> listaIngredienti2=new ArrayList<Ingredient>();
//    	listaIngredienti2.add(i1);
//		listaIngredienti2.add(i3);
//		
//		//CREO IL PIATTO DUE CON QUELLA LISTA
//    	Dish p2 = new Dish("bruschetta con i pomodori", "pane di farina 000, e pomodori dell'euro spin" , listaIngredienti2);
//    	dishService.save(p2);
    	
    	//piatti.add(p2);
    	
//    	List<Ingredient> listaIngredienti3=new ArrayList<Ingredient>();
//		listaIngredienti3.add(i4);
//    	Dish p3=new Dish("spezzatino al sugo", "pane di farina 000, e nutella dell'euro spin" ,listaIngredienti1);
//    	dishService.save(p3);
//    	
//    	List<Ingredient> listaIngredienti4=new ArrayList<Ingredient>();
//    	listaIngredienti4.add(new Ingredient("pollo", "guatemala", "cagotto assicurato"));
//    	listaIngredienti4.add(new Ingredient("farina", "italia", "fatta con nocciole"));
//    	Dish piatto4 = new Dish("pollo fritto", "pane di farina 000, e pomodori dell'euro spin" ,listaIngredienti2);
//    	
//    	List<Dish> secondi = new ArrayList<Dish>();
//    	secondi.add(p3);
//    	secondi.add(piatto4);
//    	
//  
//    	List<Ingredient> listaIngredienti5=new ArrayList<Ingredient>();
//    	listaIngredienti5.add(i4);
//    	listaIngredienti5.add(i5);
//    	Dish piatto5=new Dish("spezzatino al sugo", "pane di farina 000, e nutella dell'euro spin" ,listaIngredienti1);
//    	
//    	List<Ingredient> listaIngredienti6=new ArrayList<Ingredient>();
//    	listaIngredienti6.add(new Ingredient("pollo", "guatemala", "cagotto assicurato"));
//    	listaIngredienti6.add(new Ingredient("farina", "italia", "fatta con nocciole"));
//    	Dish piatto6 = new Dish("pollo fritto", "pane di farina 000, e pomodori dell'euro spin" ,listaIngredienti2);
//    	
//    	List<Dish> dessert = new ArrayList<Dish>();
//    	dessert.add(piatto5);
//    	dessert.add(piatto6);
    }

}
