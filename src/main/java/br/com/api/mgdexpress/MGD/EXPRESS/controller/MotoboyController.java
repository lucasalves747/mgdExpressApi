package br.com.api.mgdexpress.MGD.EXPRESS.controller;

import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.DadosLocalizacaoMotoboy;
import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.DadosMotoboy;
import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.DadosMotoboyList;
import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.Motoboy;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.MotoboyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("motoboy")
public class MotoboyController {

    @Autowired
    private MotoboyRepository motoboyRepository;

    @PostMapping
    private ResponseEntity cadastrar(@RequestBody DadosMotoboy dadosMotoboy){
        var motoboy =motoboyRepository.save(new Motoboy(dadosMotoboy));
        return ResponseEntity.ok(motoboy);
    }


    @GetMapping
    public ResponseEntity ListarMotoboys(@PageableDefault(size = 10) Pageable page){
        return ResponseEntity.ok(motoboyRepository.findAllAtivos(page).map(DadosMotoboyList::new));
    }

    @GetMapping("/EmEntregas")
    public ResponseEntity ListarMotoboysEmEntregas(@PageableDefault(size = 10) Pageable page){
        return ResponseEntity.ok(motoboyRepository.findAllAtivosEmEntrega(page).map(DadosMotoboyList::new));
    }

    @GetMapping("/disponiveis")
    public ResponseEntity ListarMotoboysDisponiveis(@PageableDefault(size = 10) Pageable page){
        return ResponseEntity.ok(motoboyRepository.findAlldisponiveisAtivos(page).map(DadosMotoboyList::new));
    }

    @GetMapping("/EmEntregas/gerente/{id}")
    public ResponseEntity ListarMotoboysEmEntregasByGerente(@PageableDefault(size = 10) Pageable page, @PathVariable Long id){
        return ResponseEntity.ok(motoboyRepository.findAllAtivosEmEntregaByGerente(page,id).map(DadosMotoboyList::new));
    }


    @PostMapping("/localizacao")
    public ResponseEntity ListarMotoboysEmEntregasByGerente(@RequestBody DadosLocalizacaoMotoboy dados){
        var motoboy = motoboyRepository.getReferenceById(dados.id());
        motoboy.setLocalizacao(dados.localizacao());
        motoboyRepository.save(motoboy);
        return ResponseEntity.ok().build();
    }




}
