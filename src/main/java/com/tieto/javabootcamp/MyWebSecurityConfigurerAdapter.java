package com.tieto.javabootcamp;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.tieto.javabootcamp.model.user.Role;
import com.tieto.javabootcamp.repository.UserRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	@Autowired
	UserRepository userRepository;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			@Override
			@Transactional(readOnly = true)
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				com.tieto.javabootcamp.model.user.User user = userRepository.findByName(username)
						.orElseThrow(() -> new UsernameNotFoundException(username));
				
				return User.builder()
						.username(user.getName())
						.password(user.getPassword())
						.authorities(
								user.getRoles().stream().map(Role::getName).map(roleName -> new SimpleGrantedAuthority("ROLE_" + roleName)).collect(Collectors.toSet())
						)
						.build();
			}
		};
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.anonymous()
			.and().authorizeRequests()
			.antMatchers(
//					"/articles",
//					"/users",
					"/api/users**",
					"/api/roles**",
					"/api/articles**",
					"/api/comments**",
					"/*.css",
					"/*.js"
			).permitAll()
			.anyRequest().authenticated()
			.and().formLogin()
			.and().logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/users")
			.and().cors()
			.and().csrf().disable();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
