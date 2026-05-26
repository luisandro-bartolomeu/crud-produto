package co.ao.isaf.crud_produto.api.service;

import co.ao.isaf.crud_produto.domain.model.Utilizador;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {


    private final String secret = "vjbvjondfvkjfnvgfogjeriogneriognerioeri";

    public String generateToken(Utilizador user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth-api") //Quem emitiu o token
                    .withSubject(user.getUsername()) //Informação não sensíveis do user
                    .withExpiresAt(genExpirationDate()) //Tempo de expiração
                    .sign(algorithm); //assinar as informações + secret junto com algoritmo
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Mambo gato, token não foi gerado", exception);
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build() //verifica se o token é válido
                    .verify(token) //Decodifica o token
                    .getSubject(); //busca as informações do usuário
        } catch (JWTVerificationException exception){
            return "";
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}