package br.com.alura.pizzaria.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.alura.pizzaria.model.Pizza;
import br.com.alura.pizzaria.model.Pizzaria;
import br.com.alura.pizzaria.repository.PizzaRepository;


@Service
public class PizzaService {
	
	@Autowired
	private PizzaRepository pizzaRepository;
	
	@Autowired
	private PizzariaService pizzariaService;
	
	public void save(Pizza pizza){
		
		pizza.setDono(pizzariaService.getPizzariaLogado());
		pizzaRepository.save(pizza);
	}
	
	public Iterable<Pizza> list(){
		
		Pizzaria dono = pizzariaService.getPizzariaLogado();
		return pizzaRepository.findAllByDono(dono);
	}
	
	public Pizza find(Long id){
		
		Pizzaria dono = pizzariaService.getPizzariaLogado();
		return pizzaRepository.findByIdAndDono(id, dono);
	}
	
	public void delete(Long id){
		Pizza pizza = this.find(id);
		if(pizza != null) pizzaRepository.delete(pizza);
		
	}

	public List<Pizza> listNomesPizzasDisponiveis() {
		
		List<Pizza> nomePizzas = pizzaRepository.findAll();
		
		nomePizzas.stream().map((pizza)->{ 
			return pizza.getNome();
		}).sorted().collect(Collectors.toList());
		
		return nomePizzas;
		
	}
}
