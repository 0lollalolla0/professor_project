package it.uniroma3.catering.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ingredient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String origin;
	
	private String description;

	public Ingredient(String name, String origin, String description) {
		this.name = name;
		this.origin = origin;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return name;
	}

	public void setNome(String nome) {
		this.name = nome;
	}

	public String getOrigine() {
		return origin;
	}

	public void setOrigine(String origine) {
		this.origin = origine;
	}

	public String getDescrizione() {
		return description;
	}

	public void setDescrizione(String descrizione) {
		this.description = descrizione;
	}
	
}
