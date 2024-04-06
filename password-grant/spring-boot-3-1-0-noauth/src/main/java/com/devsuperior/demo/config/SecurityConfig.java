package com.devsuperior.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*
        * Ataque em aplicaçõe que guarda dados na sessão
        * Como nossa API REST não guarda estado e sessão
        * Não precisa se preocupar com isso
         */
        http.csrf(csrf -> csrf.disable());//desabilitando csrf
        /*
        *  Configurando permissão de permissão para os endpoints
         */
        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();
    }
}
