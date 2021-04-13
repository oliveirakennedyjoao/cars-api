package com.jkoliveira.carros.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jkoliveira.carros.repositories.UserRepository;
import com.jkoliveira.carros.services.AuthService;
import com.jkoliveira.carros.services.JwtTokenService;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtTokenService jwtTokenService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthService authService;
	
	//@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Bean	
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/users").permitAll()
		.antMatchers(HttpMethod.GET, "/users/*").permitAll()
		.antMatchers(HttpMethod.POST, "/signin").permitAll()
		.antMatchers("/h2-console/**").permitAll()		
		.anyRequest().authenticated()
		.and().csrf().disable()
		.cors().and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new AuthSecurityFilter(jwtTokenService, userRepository), UsernamePasswordAuthenticationFilter.class);
		
		//http.cors();
		http.headers().frameOptions().disable();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {}
}
