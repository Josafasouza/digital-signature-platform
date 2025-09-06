package com.assinatura.digital.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

      @Bean
      public BCryptPasswordEncoder passwordEncoder() {
           return new BCryptPasswordEncoder();
      }

      @Bean
      public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
           return authenticationConfiguration.getAuthenticationManager();
      }

      @Bean
      public InMemoryUserDetailsManager userDetailsService() {
           UserDetails admin = User.withUsername("admin@email.com")
                .password(passwordEncoder().encode("admin123"))
                .roles("ADMIN")
                .build();
           return new InMemoryUserDetailsManager(admin);
      }

      @Bean
      public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
           http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                     .requestMatchers("/api/auth/**").permitAll()
                     .requestMatchers("/api/admin/**").hasRole("ADMIN")
                     .requestMatchers("/api/**").authenticated()
                     .anyRequest().permitAll()
                )
                .httpBasic(httpBasic -> {});
           return http.build();
      }
}