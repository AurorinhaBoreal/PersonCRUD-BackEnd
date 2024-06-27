package com.db.crud.person.service.token;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.IncorrectClaimException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.MissingClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.db.crud.person.entity.Authentication;
import com.db.crud.person.exception.ValidateTokenException;

@Service
public class TokenService {
    String password = "PedroManeiro";
    
    public String createToken(Authentication authentication) {

        try {
            // Cria um algoritmo com a senha especificada
            Algorithm algorithm = Algorithm.HMAC256(password);

            // Cria um token Json Web Token
            return JWT.create()
            // ISS - Identificar quem emitiu o Token
            .withIssuer("Person CRUD")
                // Identifica o sujeito do token (normalmente o usuário)
                .withSubject(authentication.getEmail())
                // Define uma data de expiração
                .withExpiresAt(getExpirationDate())
                .withClaim("PersonCPF", authentication.getPerson().getCpf())
                .sign(algorithm);
            } catch (Exception e) {
            throw new RuntimeException("Errou!");
        }
        
    }
    
    
    private Instant getExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String validateToken(String token) {
        
        try {
            // Valida o token, utiliza o mesmo algoritmo de criptografia
            // O mesmo Issuer e faz a validação para verificar se é um token existente
            Algorithm algorithm = Algorithm.HMAC256(password);
            
            return JWT.require(algorithm)
                .withIssuer("Person CRUD")
                .build()
                .verify(token)
                .getSubject();
        // Realiza todas as verificações para lidar com os diversos erros que podem acabar surgindo
        } catch (AlgorithmMismatchException e) {
            throw new ValidateTokenException("Algoritmo JWT não corresponde ao esperado", e);
        } catch (SignatureVerificationException e) {
            throw new ValidateTokenException("Assinatura do token JWT inválida", e);
        } catch (TokenExpiredException e) {
            throw new ValidateTokenException("Token JWT expirado", e);
        } catch (MissingClaimException e) {
            throw new ValidateTokenException("Reivindicação ausente no token JWT", e);
        } catch (IncorrectClaimException e) {
            throw new ValidateTokenException("Reivindicação incorreta no token JWT", e);
        }catch(JWTDecodeException e) {
            throw new ValidateTokenException("Erro ao validar token: "+ e.getMessage());
        }
    }
}
