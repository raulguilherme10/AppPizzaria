package br.com.alura.pizzaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.alura.pizzaria.repository.PizzariaRepository;

@Service
public class AuthenticationService implements UserDetailsService{
	
	@Autowired
	private PizzariaRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		return usuarioRepository.findOneByLogin(login);
	}

}
