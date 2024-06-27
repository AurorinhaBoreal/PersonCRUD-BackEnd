package com.db.crud.person.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // Define que esse cara é um Bean do tipo de configuração - Pedro
@EnableWebSecurity // Informa pro Spring que essa é umaclasse de configurações de segurança - Pedro
public class SecurityConfig {
    
    @Autowired
    SecurityFilter securityFilter;
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {



        // TIRA A TELA DE LOGIN DO PRÓPRIO SECURITY
        // Desabilita o csrf -> O csrf é uthl para controle de sessão, que não é o nosso caso
        return httpSecurity.csrf(csrf -> csrf.disable())
            // Indica que nossa aplicação é do tipo Stateless, ou seja não armazena estado em requisições
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // Aqui usamos basicamente uma arrow function para definir as informações do authorize
            .authorizeHttpRequests(authorize -> authorize
            // Podemos definir mais de uma, nesse caso informamos que a criação de pessoas necessita de autenticação
            // Mas os outros métodos não
            .requestMatchers(HttpMethod.POST, "/person/create").authenticated()
            .anyRequest().permitAll()
            )
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }
}
