package br.com.api.mgdexpress.MGD.EXPRESS.controller;

import br.com.api.mgdexpress.MGD.EXPRESS.controller.listaLocalizacao.ListaLocalizacao;
import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.DadosCadastroListaSemColcheteNoJsom;
import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.DadosLocalizacaoMotoboy;
import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.DadosMotoboyEmEntregaToGerente;
import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.DadosMotoboyList;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.MotoboyRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.UserRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("motoboy")

public class MotoboyController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository useRepository;

    @Autowired
    private MotoboyRepository motoboyRepository;

    @Autowired
    private ListaLocalizacao listaLocalizacao;


    @PreAuthorize("hasRole('ROLE_USER_MASTER')")
    @GetMapping
    public ResponseEntity ListarMotoboys(@PageableDefault(size = 10) Pageable page){
        return ResponseEntity.ok(motoboyRepository.findAll(page).map(DadosMotoboyList::new));
    }

    @PreAuthorize("hasRole('ROLE_USER_MASTER')")
    @GetMapping("/EmEntregas&Disponivel")
    public ResponseEntity ListarMotoboysLocalizacao(){
        List<DadosCadastroListaSemColcheteNoJsom> lista = new ArrayList<>();

        listaLocalizacao.getListaLocalizacao().forEach(item ->{
            if(!Objects.isNull(item)){
                lista.add(new DadosCadastroListaSemColcheteNoJsom(item));
            }
        });
        return ResponseEntity.ok(lista);
    }


    @PreAuthorize("hasRole('ROLE_USER_MASTER') OR hasRole('ROLE_USER_GERENTE')")
    @GetMapping("/EmEntregas/gerente")
    public ResponseEntity<List<DadosMotoboyEmEntregaToGerente>> ListarMotoboysEmEntregasByGerente(@RequestHeader("Authorization") String header){

        var token = header.replace("Bearer ","");
        var subject = tokenService.getSubject(token);

        List<DadosMotoboyEmEntregaToGerente> lista = new ArrayList<>();

        listaLocalizacao.getListaLocalizacao().forEach(item ->{

            if(!(item == null)){

                if(!item.disponivel() && item.emailGerente().equals(subject)) {
                    lista.add(new DadosMotoboyEmEntregaToGerente(item));
                }
            }

        });

        return !lista.isEmpty() ? ResponseEntity.ok(lista) :ResponseEntity.noContent().build();


    }


    @PreAuthorize("hasRole('ROLE_USER_MOTOBOY')")
    @PostMapping("/localizacao")
    public ResponseEntity UpLocalizacao(@RequestBody DadosLocalizacaoMotoboy dados,@RequestHeader("Authorization") String header){

        var token = header.replace("Bearer ","");
        var id = tokenService.getId(token);
        var nome = tokenService.getNome(token);
        System.out.println("Up");

        listaLocalizacao.setListaLocalizacao(dados,id,nome);

        //messagingTemplate.convertAndSend("/topic/localizacao", listaLocalizacao);
        return ResponseEntity.ok().build();
    }
}
