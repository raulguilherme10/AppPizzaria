package br.com.alura.pizzaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.pizzaria.model.Ingrediente;
import br.com.alura.pizzaria.model.Pizzaria;
import br.com.alura.pizzaria.repository.IngredienteRepository;

@Service
public class IngredienteService {
	
	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	@Autowired
	private PizzariaService pizzariaService;
	
	public void save(Ingrediente ingrediente){
		
		// recebendo o ingrediente
		// vinculando o ingrediente com o usuário logado
		ingrediente.setDono(pizzariaService.getPizzariaLogado());
		ingredienteRepository.save(ingrediente);
	}
	
	public Iterable<Ingrediente> list(){
		
		Pizzaria dono = pizzariaService.getPizzariaLogado();
		return ingredienteRepository.findAllByDono(dono);
	}
	
	public Ingrediente find(Long id){
		
		Pizzaria dono = pizzariaService.getPizzariaLogado();
		return ingredienteRepository.findByIdAndDono(id, dono);
	}
	
	public void delete(Long id){
		
		Ingrediente ingrediente = this.find(id);
		if(ingrediente != null) ingredienteRepository.delete(ingrediente);
	}
}
