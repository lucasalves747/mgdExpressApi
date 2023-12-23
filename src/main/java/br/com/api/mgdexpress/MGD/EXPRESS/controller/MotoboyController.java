package br.com.api.mgdexpress.MGD.EXPRESS.controller;

import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.DadosCadastroListaSemColcheteNoJsom;
import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.DadosLocalizacaoMotoboy;
import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.DadosMotoboyList;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.MotoboyRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.UserRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@RestController
@RequestMapping("motoboy")

public class MotoboyController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository useRepository;

    @Autowired
    private MotoboyRepository motoboyRepository;

    List<DadosMotoboyList> listaLocalizacao;



    @PreAuthorize("hasRole('ROLE_USER_MASTER')")
    @GetMapping
    public ResponseEntity ListarMotoboys(@PageableDefault(size = 10) Pageable page){
        return ResponseEntity.ok(motoboyRepository.findAll(page).map(DadosMotoboyList::new));
    }

    @PreAuthorize("hasRole('ROLE_USER_MASTER')")
    @GetMapping("/EmEntregas&Disponivel")
    public ResponseEntity<Stream<DadosCadastroListaSemColcheteNoJsom>> ListarMotoboysLocalizacao(){
        return ResponseEntity.ok(listaLocalizacao.stream().map(DadosCadastroListaSemColcheteNoJsom::new));
    }


    @PreAuthorize("hasRole('ROLE_USER_MASTER') OR hasRole('ROLE_USER_GERENTE')")
    @GetMapping("/EmEntregas/gerente")
    public ResponseEntity ListarMotoboysEmEntregasByGerente(@PageableDefault(size = 10) Pageable page,@RequestHeader("Authorization") String header){

        var token = header.replace("Bearer ","");
        var subject = tokenService.getSubject(token);

        return ResponseEntity.ok(motoboyRepository.findAllAtivosEmEntregaByGerente(page,subject).map(DadosMotoboyList::new));
    }


    @PreAuthorize("hasRole('ROLE_USER_MOTOBOY')")
    @PostMapping("/localizacao")
    public ResponseEntity UpLocalizacao(@RequestBody DadosLocalizacaoMotoboy dados,@RequestHeader("Authorization") String header){

        var token = header.replace("Bearer ","");
        var id = tokenService.getId(token);
        var nome = tokenService.getNome(token);


        if(Objects.isNull(listaLocalizacao)){

            listaLocalizacao = new ArrayList<DadosMotoboyList>(Collections.nCopies(motoboyRepository.encontrarMaiorId().intValue()+1, null));
            motoboyRepository.findAllAtivos().forEach(motoboy -> {
                var d = new DadosMotoboyList(motoboy);
                listaLocalizacao.set(motoboy.getId().intValue(),d);
            });
            return ResponseEntity.ok().build();
        }

        var dadosMotoboyList = listaLocalizacao.get(id.intValue());
        listaLocalizacao.set(id.intValue(),new DadosMotoboyList(id,nome,dados.localizacao(),dadosMotoboyList.disponivel()));

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_USER_MOTOBOY')")
    @GetMapping ("/setStatus")
    public  void setStatus(@RequestHeader("Authorization") String header){
        var token = header.replace("Bearer ","");
        var id = tokenService.getId(token);


        var dados = listaLocalizacao.get(id.intValue());
        if(dados.disponivel()) {
            listaLocalizacao.set(id.intValue(), new DadosMotoboyList(id, dados.nome(), dados.localizacao(), false));
        }else{
            listaLocalizacao.set(id.intValue(), new DadosMotoboyList(id,dados.nome(),dados.localizacao(),true));

        }
    }
}
