package br.com.api.mgdexpress.MGD.EXPRESS.controller;

import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.DadosLocalizacaoMotoboy;
import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.DadosMotoboyCadastro;
import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.DadosMotoboyList;
import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.Motoboy;
import br.com.api.mgdexpress.MGD.EXPRESS.model.users.User;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.MotoboyRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("motoboy")

public class MotoboyController {

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



    @PreAuthorize("hasRole('ROLE_USER_MASTER')")
    @GetMapping
    public ResponseEntity ListarMotoboys(@PageableDefault(size = 10) Pageable page){
        return ResponseEntity.ok(motoboyRepository.findAllAtivos(page).map(DadosMotoboyList::new));
    }

    @PreAuthorize("hasRole('ROLE_USER_MASTER')")
    @GetMapping("/EmEntregas")
    public ResponseEntity ListarMotoboysEmEntregas(@PageableDefault(size = 10) Pageable page){
        return ResponseEntity.ok(motoboyRepository.findAllAtivosEmEntrega(page).map(DadosMotoboyList::new));
    }

    @PreAuthorize("hasRole('ROLE_USER_MASTER')")
    @GetMapping("/disponiveis")
    public ResponseEntity ListarMotoboysDisponiveis(@PageableDefault(size = 10) Pageable page){
        return ResponseEntity.ok(motoboyRepository.findAlldisponiveisAtivos(page).map(DadosMotoboyList::new));
    }

    @PreAuthorize("hasRole('ROLE_USER_MASTER') OR hasRole('ROLE_USER_GERENTE')")
    @GetMapping("/EmEntregas/gerente/{id}")
    public ResponseEntity ListarMotoboysEmEntregasByGerente(@PageableDefault(size = 10) Pageable page, @PathVariable Long id){
        return ResponseEntity.ok(motoboyRepository.findAllAtivosEmEntregaByGerente(page,id).map(DadosMotoboyList::new));
    }


    @PreAuthorize("hasRole('ROLE_USER_MOTOBOY')")
    @PostMapping("/localizacao")
    public ResponseEntity UpLocalizacao(@RequestBody DadosLocalizacaoMotoboy dados){
        var motoboy = motoboyRepository.getReferenceById(dados.id());
        motoboy.setLocalizacao(dados.localizacao());
        motoboyRepository.save(motoboy);
        return ResponseEntity.ok().build();
    }
}
