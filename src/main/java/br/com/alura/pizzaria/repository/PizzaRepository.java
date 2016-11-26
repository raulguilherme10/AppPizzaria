package br.com.alura.pizzaria.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.pizzaria.model.Pizza;
import br.com.alura.pizzaria.model.Pizzaria;

@Repository
public interface PizzaRepository extends CrudRepository<Pizza, Long>{

	List<Pizza> findAllByDono(Pizzaria dono);

	Pizza findByIdAndDono(Long id, Pizzaria dorno);

	List<Pizza> findAll();

}
