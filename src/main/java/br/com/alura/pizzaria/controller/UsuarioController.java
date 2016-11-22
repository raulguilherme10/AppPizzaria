package br.com.alura.pizzaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.alura.pizzaria.model.Pizzaria;
import br.com.alura.pizzaria.repository.PizzariaRepository;

@Controller
public class UsuarioController {

	@Autowired
	private PizzariaRepository usuarioRepository;
	
	/*@RequestMapping("/usuario")
	@ResponseBody
	public String usuario(){
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		Pizzaria usuario = new Pizzaria();
		usuario.setLogin("admin");
		usuario.setSenha(encoder.encode("admin"));
		
		usuarioRepository.save(usuario);
		
		return "usuario salvo com sucesso!";
	}*/
}
