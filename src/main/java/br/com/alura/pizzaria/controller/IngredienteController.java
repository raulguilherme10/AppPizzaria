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

import br.com.alura.pizzaria.exception.IngredienteInvalidoException;
import br.com.alura.pizzaria.model.CategoriaDeIngrediente;
import br.com.alura.pizzaria.model.Ingrediente;
import br.com.alura.pizzaria.service.IngredienteService;

@Controller
@RequestMapping("/ingrediente")
public class IngredienteController {
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model){
		
		Iterable<Ingrediente> ingredientes = ingredienteService.list();
		model.addAttribute("ingredientes", ingredientes);
		model.addAttribute("categorias", CategoriaDeIngrediente.values());
		return "ingrediente/index";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String create(@Valid Ingrediente ingrediente, BindingResult bindingResult, Model model){
		
		if(!bindingResult.hasErrors()){	
			ingredienteService.save(ingrediente);
		}else{
			throw new IngredienteInvalidoException();
		}
		
		model.addAttribute("ingredientes", ingredienteService.list());
		model.addAttribute("categorias", CategoriaDeIngrediente.values());
		return "ingrediente/tabela-ingredientes";
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<String> destroy(@PathVariable Long id){
		
		try{
			ingredienteService.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public Ingrediente edit(@PathVariable Long id){
		
		Ingrediente ingrediente = ingredienteService.find(id);
		return ingrediente;
	}
	
}
