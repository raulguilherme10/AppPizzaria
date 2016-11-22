package br.com.alura.pizzaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.alura.pizzaria.model.Pizzaria;


@Repository
public interface PizzariaRepository extends CrudRepository<Pizzaria, Long>{
	
	@Query("SELECT p FROM Pizzaria p WHERE p.usuario.login = ?")
	public UserDetails findOneByLogin(String login);
	
	@Query("SELECT p1 FROM Pizzaria p1 INNER JOIN p1.pizzas p2 WHERE p2.nome = ?")
	public List<Pizzaria> listarPizzariasPorNomePizza(String nome);

}
