package br.com.api.mgdexpress.MGD.EXPRESS.controller;

import br.com.api.mgdexpress.MGD.EXPRESS.model.gerente.DadosGerente;
import br.com.api.mgdexpress.MGD.EXPRESS.model.gerente.Gerente;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.GerenteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class GerenteController {

    @Autowired
    private GerenteRepository gerenteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity criarGerente(@Valid @RequestBody DadosGerente dadosGerente){
        gerenteRepository.save(new Gerente(dadosGerente));
        return ResponseEntity.ok().build();
    }

}
