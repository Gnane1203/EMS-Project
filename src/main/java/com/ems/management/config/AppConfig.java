package com.ems.management.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.ems.management.filter.JwtFilter;
import com.ems.management.service.impl.EmployeeServiceImpl;



@Configuration
@EnableWebSecurity
public class AppConfig {
	
	private EmployeeServiceImpl userServiceImpl;
	 @Value("#{'${app.consumers}'.split(',')}")
	private List<String> appUrls;
	
	@Autowired
	private JwtFilter jwtFilter;

	public AppConfig(@Lazy EmployeeServiceImpl userServiceImpl) {
		super();
		this.userServiceImpl = userServiceImpl;
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
		auth.userDetailsService(userServiceImpl).passwordEncoder(passwordEncoder());
		return auth.build();
				
	}
	


	@Bean
	SecurityFilterChain security(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(req -> {
			req.requestMatchers("/auth/**","/v3/api-docs/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html").permitAll()
			.requestMatchers("/admin/**").hasRole("ADMIN")
			.requestMatchers("/manager/**").hasRole("MANAGER")
			
			
			.anyRequest().authenticated();
		});
		http.csrf(csrf -> csrf.disable());
		http.addFilterBefore(corsFilter(),UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);
		
		// http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	@Bean
	CorsFilter  corsFilter() {
		CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(appUrls); // frontend origin
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true); // 

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source); 
	}
	
	
	

}

