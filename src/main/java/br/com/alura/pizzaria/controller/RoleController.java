package br.com.alura.pizzaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.alura.pizzaria.model.Roles;
import br.com.alura.pizzaria.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/iowqeupieuiowqioueiuowqiiewiuewuieiwupuiewq")
	@ResponseBody
	public String create(){
		
		Roles roles = new Roles();
		roles.setNome("ROLE_ADMIN");
		roleService.addRole(roles);
		
		return "role criada com sucesso!";
		
	}
}
