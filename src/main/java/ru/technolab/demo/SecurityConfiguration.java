package ru.technolab.demo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/** Конфигурация Spring Security через табличку testdb.users
 *  Пока чтобы её выключить нужно в configure() раскомментировать блок Permit all config:
 *  и закомментировать другой 
 * @created Oct 11, 2019
 * @author Vladimir Vygodin */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// Permit all config:
		http.cors().and()
	    	.csrf()			// Cross Site Request Forgery (CSRF Attacks)
	    	.disable()		// We don't need CSRF for this example
	    	.authorizeRequests().antMatchers("/").permitAll();	// dont authenticate this particular request
		
		
//		http.cors().and()
//        	.csrf()			// Cross Site Request Forgery (CSRF Attacks)
//        	.disable()		// We don't need CSRF for this example
//        	.authorizeRequests().antMatchers("/graphiql", "/actuator/health").permitAll()	// dont authenticate this particular request
//        	
//
//        	// all other requests need to be authenticated
//        	.anyRequest().authenticated().and()
//    		.addFilterBefore(jwtAuthenticationFilter(), 
//    				UsernamePasswordAuthenticationFilter.class);

	}
}