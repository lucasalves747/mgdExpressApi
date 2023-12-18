package br.com.api.mgdexpress.MGD.EXPRESS.controller;

import br.com.api.mgdexpress.MGD.EXPRESS.model.gerente.DadosGerente;
import br.com.api.mgdexpress.MGD.EXPRESS.model.gerenteTemporario.DadosGerenteTemporarioList;
import br.com.api.mgdexpress.MGD.EXPRESS.model.gerenteTemporario.GerenteTemporario;
import br.com.api.mgdexpress.MGD.EXPRESS.model.users.User;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.GerenteTemporarioRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gerente-temporario")
public class GerenteTemporarioController {

    @Autowired
    private GerenteTemporarioRepository  gerenteRepository;
    @Autowired
    private UserRepository userRepository;


    @PostMapping
    @Transactional
    public ResponseEntity criarGerente(@Valid @RequestBody DadosGerente dadosGerente){
        var user = userRepository.findByUsername(dadosGerente.email());

        if(user != null){
            ResponseEntity.status(422).body("usuario ja cadastrado");
        }

        var bcrypt = new BCryptPasswordEncoder();
        var senha = bcrypt.encode(dadosGerente.senha());
        userRepository.save(new User(null,dadosGerente.email(),senha,"ROLE_USER_GERENTE"));
        gerenteRepository.save(new GerenteTemporario(dadosGerente));
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_USER_MASTER')")
    @GetMapping
    public ResponseEntity buscarGerentesTemporarios(){
        var gerentesTemporarios = gerenteRepository.findAll().stream().map(DadosGerenteTemporarioList::new);
        return ResponseEntity.ok(gerentesTemporarios);
    }

}