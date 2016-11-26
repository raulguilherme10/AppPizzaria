package br.com.alura.pizzaria.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.alura.pizzaria.exception.PizzaInvalidaException;
import br.com.alura.pizzaria.model.CategoriaDePizza;
import br.com.alura.pizzaria.model.Pizza;
import br.com.alura.pizzaria.service.IngredienteService;
import br.com.alura.pizzaria.service.PizzaService;

@Controller
@RequestMapping("/pizza")
public class PizzaController {
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model){
		
		model.addAttribute("pizzas", pizzaService.list());
		model.addAttribute("categorias", CategoriaDePizza.values());
		model.addAttribute("ingredientes", ingredienteService.list());
		return "pizza/index";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Pizza pizza, BindingResult bindingResult, Model model){
		
		if(!bindingResult.hasErrors()){
			pizzaService.save(pizza);
		}else{
			throw new PizzaInvalidaException();
		}
		
		model.addAttribute("pizzas", pizzaService.list());		
		return "pizza/tabela-pizzas";
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> destroy(@PathVariable Long id){
		
		try {
			pizzaService.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public ResponseEntity<Pizza> edit(@PathVariable Long id){
		Pizza pizza = pizzaService.find(id);
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
}
