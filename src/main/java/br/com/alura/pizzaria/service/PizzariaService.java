package br.com.alura.pizzaria.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.alura.pizzaria.model.Pizzaria;
import br.com.alura.pizzaria.model.Roles;
import br.com.alura.pizzaria.repository.PizzariaRepository;

@Service
public class PizzariaService {
	
	@Autowired
	private PizzariaRepository pizzariaRepository;
	
	public Pizzaria getPizzariaLogado(){
		
		// Pegando o usuário logado
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication == null) throw new AuthenticationCredentialsNotFoundException("Usuário não logado");
		
		// pegando o objeto principal
		UserDetails usuarioLogado = (UserDetails) authentication.getPrincipal();
		
		// pesquisando o login no banco de dados
		return (Pizzaria) pizzariaRepository.findOneByLogin(usuarioLogado.getUsername());
	}

	public List<Pizzaria> listarPizzaPizzarias(String nome) {
	
		return pizzariaRepository.listarPizzariasPorNomePizza(nome);
	}
	
	// função para cadastrar uma pizzaria
	public void create(Pizzaria pizzaria, Set<Roles> role){
		
		pizzaria.setRoles(role);
		pizzaria.getUsuario().setSenha(this.codificarSenha(pizzaria.getUsuario().getSenha()));
		pizzariaRepository.save(pizzaria);
	}
	
	public String codificarSenha(String senha){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(senha);
	}
}
