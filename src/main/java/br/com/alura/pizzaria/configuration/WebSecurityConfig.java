package br.com.alura.pizzaria.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.alura.pizzaria.service.AuthenticationService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
				.antMatchers("/pizza/**").hasRole("ADMIN")
				.antMatchers("/ingrediente/**").hasRole("ADMIN")
				.anyRequest().permitAll()
			.and()
				.formLogin()
					.loginPage("/login")
					.loginProcessingUrl("/autenticar")
					.defaultSuccessUrl("/pizza")
					.usernameParameter("usuario")
					.passwordParameter("senha")
			.and()
				.logout()
					.logoutUrl("/logout")
					.logoutSuccessUrl("/");
				
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			
		auth
			.userDetailsService(authenticationService)
			.passwordEncoder(encoder());
	}
	
	@Bean
	public BCryptPasswordEncoder encoder(){
		return new BCryptPasswordEncoder();
	}
	
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("admin"));
	}
}
