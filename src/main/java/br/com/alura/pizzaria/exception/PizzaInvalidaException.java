package br.com.alura.pizzaria.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class PizzaInvalidaException  extends RuntimeException {

	private static final long serialVersionUID = 1L;

}
