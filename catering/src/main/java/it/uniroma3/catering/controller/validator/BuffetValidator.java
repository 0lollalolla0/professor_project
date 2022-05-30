package it.uniroma3.catering.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.catering.model.Buffet;

@Component
public class BuffetValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Buffet.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {}
	
	
}
