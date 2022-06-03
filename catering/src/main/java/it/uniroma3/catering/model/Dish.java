package it.uniroma3.catering.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Dish {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String description;
	
	@OneToMany(cascade = {CascadeType.MERGE})
	private List<Ingredient> ingredients;
	
	public Dish() {
		this.ingredients = new ArrayList<Ingredient>();
	}

	public Dish(String name, String description, List<Ingredient> ingredients) {
		this.name = name;
		this.description = description;
		this.ingredients = ingredients;
		this.ingredients = new ArrayList<Ingredient>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void addIngredient(Ingredient ingredient) {
		this.ingredients.add(ingredient);
	}
	
}
