package br.com.api.mgdexpress.MGD.EXPRESS.controller;

import br.com.api.mgdexpress.MGD.EXPRESS.model.gerente.DadosGerente;
import br.com.api.mgdexpress.MGD.EXPRESS.model.gerente.Gerente;
import br.com.api.mgdexpress.MGD.EXPRESS.model.users.User;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.GerenteRepository;
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
@RequestMapping("/gerente")
public class GerenteController {

    @Autowired
    private GerenteRepository gerenteRepository;

    @Autowired
    private GerenteTemporarioRepository gerenteTemporarioRepository;
    
    @Autowired
    private UserRepository userRepository;
    @PreAuthorize("hasRole('ROLE_USER_MASTER')")
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity criarGerente(@PathVariable Long id){
        var dadosGerente = gerenteTemporarioRepository.getReferenceById(id);
        var user = userRepository.findByUsername(dadosGerente.getEmail());

        if(user != null){
            throw new Error("usuario ja existe");
        }

        var bcrypt = new BCryptPasswordEncoder();
        var senha = bcrypt.encode(dadosGerente.getSenha());
        userRepository.save(new User(null, dadosGerente.getEmail(), senha,"ROLE_USER_GERENTE"));
        gerenteRepository.save(new Gerente(dadosGerente));

        return ResponseEntity.ok().build();
    }

}
