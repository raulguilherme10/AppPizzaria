package br.com.alura.pizzaria.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import br.com.alura.pizzaria.model.Pizzaria;

public class PizzariaValidation {
	
	public void validation(Pizzaria pizzaria, Errors errors){
		
		ValidationUtils.rejectIfEmpty(errors, "nome", "field.required");
		
	}
}
