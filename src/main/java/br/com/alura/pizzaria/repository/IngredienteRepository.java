package br.com.alura.pizzaria.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.pizzaria.model.Ingrediente;
import br.com.alura.pizzaria.model.Pizzaria;

@Repository
public interface IngredienteRepository extends CrudRepository<Ingrediente, Long>{
	
	public List<Ingrediente> findAllByDono(Pizzaria dono);

	public Ingrediente findByIdAndDono(Long id, Pizzaria dono);

}
