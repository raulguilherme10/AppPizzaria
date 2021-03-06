package br.com.alura.pizzaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.alura.pizzaria.model.Pizzaria;
import br.com.alura.pizzaria.model.Roles;
import br.com.alura.pizzaria.service.PizzaService;
import br.com.alura.pizzaria.service.PizzariaService;
import br.com.alura.pizzaria.service.RoleService;

import java.io.Console;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/")
public class PizzariaController {
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private PizzariaService pizzariaService;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model){
		
		model.addAttribute("nomePizzas", pizzaService.listNomesPizzasDisponiveis());
		return "public/busca-pizzaria";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/pizzas/{nome}")
	public String buscarPizzarias(Model model, @PathVariable String nome){
		
		model.addAttribute("pizzarias", pizzariaService.listarPizzaPizzarias(nome));	
		return "public/tabela-pizzas-public";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/account")
	public String createAccount(Pizzaria pizzaria, Model model){

		// 1 - Trazer a role role_admin
		Set<Roles> role = new HashSet<Roles>();
		
		role.add(roleService.findRole("ROLE_ADMIN"));
		pizzariaService.create(pizzaria, role);
		
		return "redirect:/login";
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/account")
	public String create(){
		return "public/adicionarconta";
	}
}
