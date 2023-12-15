package br.com.api.mgdexpress.MGD.EXPRESS.controller;

import br.com.api.mgdexpress.MGD.EXPRESS.model.users.Login;
import br.com.api.mgdexpress.MGD.EXPRESS.model.users.User;
import br.com.api.mgdexpress.MGD.EXPRESS.services.TokenService;
import br.com.api.mgdexpress.MGD.EXPRESS.site.pageService.FormLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    public AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public String login(@RequestBody Login login){

        var usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(login.username(),login.password());
        var authenticate =
                authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        var usuario = (User) authenticate.getPrincipal();

        tokenService.gerarToken(usuario);
        return "estou a funcionar";
    }

    @GetMapping
    public String login(){
        return FormLogin.page();
    }

}
