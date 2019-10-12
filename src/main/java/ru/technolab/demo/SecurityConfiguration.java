package ru.technolab.demo;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

/**
 * Конфигурация Spring Security через табличку testdb.users Пока чтобы её
 * выключить нужно в configure() раскомментировать блок Permit all config: и
 * закомментировать другой
 * 
 * @created Oct 11, 2019
 * @author Vladimir Vygodin
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private MyBasicAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    	auth.inMemoryAuthentication()
//        	.withUser("user").password("{noop}111").roles("USER")
//        	.and()
//        	.withUser("admin").password("{noop}111").roles("ADMIN");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new PasswordEncoder() {
			@Override
			public String encode(CharSequence charSequence) {
				return charSequence.toString();
			}

			@Override
			public boolean matches(CharSequence charSequence, String s) {
				return charSequence.toString().equals(s);
			}
		};
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Permit all config:
//		http.cors().and()
//	    	.csrf()			// Cross Site Request Forgery (CSRF Attacks)
//	    	.disable()		// We don't need CSRF for this example
//	    	.authorizeRequests().antMatchers("/").permitAll();	// dont authenticate this particular request

		// secured
		http.cors().and().csrf() // Cross Site Request Forgery (CSRF Attacks)
				.disable() // We don't need CSRF for this example. Без этого PF нормально не работал!!!
				.authorizeRequests().antMatchers("/books", "/users").authenticated().and().httpBasic()
				.authenticationEntryPoint(authenticationEntryPoint)
	        .and()
            .logout()
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .deleteCookies("JSESSIONID")
            .logoutSuccessUrl("/index.html").permitAll();

		http.addFilterAfter(new GenericFilterBean() {

			@Override
			public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
					throws IOException, ServletException {
				chain.doFilter(request, response);
			}
		}, BasicAuthenticationFilter.class);
	}
}