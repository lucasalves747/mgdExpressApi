package br.com.api.mgdexpress.MGD.EXPRESS.services;

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
                .withClaim("id",user.getId()).sign(Algorithm.HMAC256("serceta"));
    }

    public String getSubject(String token) {

        return JWT.require(Algorithm.HMAC256("serceta"))
                .withIssuer("motoboy")
                .build()
                .verify(token).getSubject();
    }
}
