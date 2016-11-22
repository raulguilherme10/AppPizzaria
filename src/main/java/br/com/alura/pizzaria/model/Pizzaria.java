package br.com.alura.pizzaria.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Pizzaria implements UserDetails{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Embedded
	private Usuario usuario;
	
	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Calendar dataCadastro;
	
	@NotNull
	@NotEmpty
	private String nome;
	
	@NotNull
	@NotEmpty
	private String endereco;
	
	@ElementCollection
	private Set<String> email;
	
	@ElementCollection
	private Set<String> telefone;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Roles> roles;
	
	@OneToMany(mappedBy = "dono")
	private Set<Pizza> pizzas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Set<String> getEmail() {
		return email;
	}

	public void setEmail(Set<String> email) {
		this.email = email;
	}

	public Set<String> getTelefone() {
		return telefone;
	}

	public void setTelefone(Set<String> telefone) {
		this.telefone = telefone;
	}

	public Set<Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(Set<Pizza> pizzas) {
		this.pizzas = pizzas;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.usuario.getLogin() == null) ? 0 : this.usuario.getLogin().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pizzaria other = (Pizzaria) obj;
		if (this.usuario.getLogin() == null) {
			if (other.usuario.getLogin() != null)
				return false;
		} else if (!this.usuario.getLogin().equals(other.usuario.getLogin()))
			return false;
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> autorizacoes = new ArrayList<GrantedAuthority>();
		
		for(Roles roles : getRoles()){
			autorizacoes.add(new SimpleGrantedAuthority(roles.getNome()));
		}
		
		return autorizacoes;
	}

	@Override
	public String getPassword() {
		return this.usuario.getSenha();
	}

	@Override
	public String getUsername() {
		return this.usuario.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
	
	
}
