package br.com.alura.pizzaria.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.alura.pizzaria.model.Roles;

public interface RoleRepository extends CrudRepository<Roles, Long>{

	public Roles findByNome(String nome);

}
