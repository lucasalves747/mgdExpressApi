package br.com.api.mgdexpress.MGD.EXPRESS.controller;

import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.DadosMotoboyCadastro;
import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.Motoboy;
import br.com.api.mgdexpress.MGD.EXPRESS.model.users.User;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.MotoboyRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("crear/motoboy")
public class CreateMotoboyContrller {
    @Autowired
    private UserRepository useRepository;

    @Autowired
    private MotoboyRepository motoboyRepository;

    @PostMapping
    private ResponseEntity cadastrar(@RequestBody DadosMotoboyCadastro dadosMotoboy){

        var user = useRepository.findByUsername(dadosMotoboy.email());

        if(user != null){
            throw new Error("usuario ja existe");
        }

        var bcrypt = new BCryptPasswordEncoder();

        var senha = bcrypt.encode(dadosMotoboy.senha());
        useRepository.save(new User(null, dadosMotoboy.email(), senha,"ROLE_USER_MOTOBOY"));
        motoboyRepository.save(new Motoboy(dadosMotoboy));
        return ResponseEntity.ok().build();
    }
}
