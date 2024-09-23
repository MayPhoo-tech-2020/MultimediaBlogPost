package com.multimedia.blogpost.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // Use a stronger encoder in production
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disable CSRF protection for testing
            .httpBasic(Customizer.withDefaults()) // Enable basic authentication
            .authorizeHttpRequests(auth -> auth
                // Permit GET requests for anyone
                .requestMatchers(HttpMethod.GET, "/api/blogposts").permitAll()
                // Require "WRITE" authority for POST requests
                .requestMatchers(HttpMethod.POST, "/api/blogposts").hasAuthority("WRITE")
                // Require authentication for all other requests
                .anyRequest().authenticated()
            );

        return http.build();
    }
}
