package br.com.api.mgdexpress.MGD.EXPRESS.service;

import br.com.api.mgdexpress.MGD.EXPRESS.model.users.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    public String gerarToken(User user) {
        return JWT.create()
                .withIssuer("motoboy")
                .withSubject(user.getUsername())
                .withClaim("id",user.getId())
                .withClaim("nome",user.getNome())
                .withClaim("id_user",user.getId_user())
                .sign(Algorithm.HMAC256("serceta"));
    }

    public String getSubject(String token) {

        return JWT.require(Algorithm.HMAC256("serceta"))
                .withIssuer("motoboy")
                .build()
                .verify(token).getSubject();
    }

    public Long getId(String token) {

        return JWT.require(Algorithm.HMAC256("serceta"))
                .withIssuer("motoboy")
                .build()
                .verify(token).getClaim("id_user").asLong();
    }

    public String getNome(String token) {

        return JWT.require(Algorithm.HMAC256("serceta"))
                .withIssuer("motoboy")
                .build()
                .verify(token).getClaim("nome").asString();
    }
}
