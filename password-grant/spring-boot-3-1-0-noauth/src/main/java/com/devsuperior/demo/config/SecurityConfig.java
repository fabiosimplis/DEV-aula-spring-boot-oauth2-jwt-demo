package com.devsuperior.demo.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
    * Adicionando filtro para o bando de dados H2 rodar
     */
    @Bean
    @Profile("test")// perfil permitido para rodar atraves do spring security
    @Order(1)//ordem do filtro
    public SecurityFilterChain h2SecurityFilterChain(HttpSecurity http) throws Exception {

        http.securityMatcher(PathRequest.toH2Console()).csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));
        return http.build();
    }
    @Bean
    @Order(2)
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
