package com.debashis.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	 @Autowired
	  private JwtAuthenticationEntryPoint point;
	 
	  @Autowired
	   private JwtAuthenticationFilter filter;

	  @Bean
	   AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) 
	            throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	    }
	  
		    @Bean
		    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	
		        http.csrf(csrf -> csrf.disable())
		            .cors(cors->cors.disable())
		            .authorizeHttpRequests(auth->auth.
		            requestMatchers("/auth/login").permitAll().requestMatchers("/auth/user/**").hasAuthority("ROLE_USER").
		            requestMatchers("/user/**").permitAll().
		            requestMatchers("/hotel/**").permitAll().
		            requestMatchers("/rating/**").permitAll().
		            anyRequest().authenticated())
	            .exceptionHandling(ex->ex.authenticationEntryPoint(point))
		            .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	//	       
		        http.addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class);
		        return http.build();
		    }

}
