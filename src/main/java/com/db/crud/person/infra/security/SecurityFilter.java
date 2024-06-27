package com.db.crud.person.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.db.crud.person.entity.Authentication;
import com.db.crud.person.infra.security.userDetails.UserDetailsImpl;
import com.db.crud.person.repository.AuthenticationRepository;
import com.db.crud.person.service.token.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    AuthenticationRepository authenticationRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                // Pega o token
                String token = getHeaderToken(request);

                if (token == null) {
                    filterChain.doFilter(request, response);
                    return;
                }

                // Valida o token
                String userEmail = tokenService.validateToken(token);

                // Verifica se a autenticação existe
                Optional<Authentication> authentication = authenticationRepository.findByEmail(userEmail);

                if (authentication.isPresent()) {
                    // Pega a autenticação
                    Authentication auth = authentication.get();

                    // Cria um objeto UserDetails com base na autenticação
                    UserDetailsImpl userDetailsImpl = new UserDetailsImpl(auth);
                    // UsernamenPasswordAuthenticationToken  é um objeto utilizado pelo Spring para identificar um usuario
                    // Cria um UsernamePaswordAuthenticationToken com os dados do usuário/autenticação
                    // Passando a pessoa | As Credenciais | E as autoridades
                    UsernamePasswordAuthenticationToken userData = new UsernamePasswordAuthenticationToken(userDetailsImpl.getPerson(), null, userDetailsImpl.getAuthorities());

                    // Pega o usuário autenticado e insere no contexto do Spring
                    SecurityContextHolder
                        .getContext()
                        .setAuthentication(userData);
                }

        // Manda o Spring seguir o ciclo
        filterChain.doFilter(request, response);
    }

    public String getHeaderToken(HttpServletRequest request) {
        // Pega no Header o campo Authorization que contem o Token de Autenticação
        String token = request.getHeader("Authorization");

        if (token == null) {
            return null;
        }

        // Muda a formatação da String só pra pegar o token em sí
        return token.replace("Bearer ", "");
    }
    
}
