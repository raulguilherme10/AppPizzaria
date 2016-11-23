package br.com.alura.pizzaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.pizzaria.model.Roles;
import br.com.alura.pizzaria.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public Roles findRole(String nome){
		
		Roles roles = roleRepository.findByNome(nome);
		return roles;
	}
	
	public void addRole(Roles roles){
		
		roleRepository.save(roles);
	}
}
